package com.fdaindia.hrms.service.impl;

import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.repository.FdaEmployeeRepository;
import com.fdaindia.hrms.response.EmployeeResponseDTO;
import com.fdaindia.hrms.response.UserResponse;
import com.fdaindia.hrms.service.EmailService;
import com.fdaindia.hrms.service.FdaEmployeeService;
import com.fdaindia.hrms.tokenconfig.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FdaEmployeeServiceImpl implements FdaEmployeeService {
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	private EmailService emailService;

	@Autowired
	private FdaEmployeeRepository employeeRepository;

	@Override
	public UserResponse registerEmployee(Employee employee) {
		UserResponse response = new UserResponse();

		// Check if the username is already taken
		if (employeeRepository.findByUsername(employee.getUsername()).isPresent()) {
			response.setStatus(false);
			response.setMessage("Username already exists");
			return response;
		}

		// Check if the email is already registered
		if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
			response.setStatus(false);
			response.setMessage("Email already exists");
			return response;
		}

		Employee savedEmployee = employeeRepository.save(employee);

		String subject = "Welcome to FDA India HRMS";
		String body = "Dear " + savedEmployee.getUsername() + ",\n\n" + "Your account has been created.\n"
				+ "Username: " + savedEmployee.getUsername() + "\n" + "Password: " + savedEmployee.getPassword()
				+ "\n\n" + "Please change your password after your first login.";

		emailService.sendEmail(savedEmployee.getEmail(), subject, body);

		response.setStatus(true);
		response.setObject(savedEmployee);
		response.setMessage("Employee registered successfully and email sent");

		return response;
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public UserResponse authenticate(String username, String password) {
	    Optional<Employee> employeeOpt = employeeRepository.findByUsername(username);
	    UserResponse response = new UserResponse();

	    if (employeeOpt.isPresent()) {
	        Employee employee = employeeOpt.get();
	        if (employee.getPassword().equals(password)) {
	            // Create DTO and map fields
	            EmployeeResponseDTO employeeDTO = convertToDto(employee);

	            // Generate session ID and expiry date
	            String sessionId = UUID.randomUUID().toString();
	            Date sessionExpiry = new Date(System.currentTimeMillis() + JwtTokenUtil.JWT_TOKEN_VALIDITY * 1000);

	            // Generate JWT token with session ID and expiry
	            String token = jwtTokenUtil.generateTokenWithSessionId(employee, sessionId, sessionExpiry);

	            response.setStatus(true);
	            response.setMessage("Login successful");
	            response.setObject(employeeDTO); // Set the DTO instead of the entity
	            response.setUsername(username);
	            response.setRole(employee.getRole());
	            response.setToken(token);
	            response.setSessionId(sessionId);
	            response.setSessionExpiry(sessionExpiry);
	        } else {
	            response.setStatus(false);
	            response.setMessage("Invalid username or password");
	            response.setObject(null);
	            response.setUsername(null);
	            response.setRole(null);
	        }
	    } else {
	        response.setStatus(false);
	        response.setMessage("Invalid username or password");
	        response.setObject(null);
	        response.setUsername(null);
	        response.setRole(null);
	    }
	    return response;
	}

	@Override
	public String generateOtp(String email) {
		Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
		if (employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			String otp = String.format("%06d", new Random().nextInt(999999));
			employee.setOtp(otp);
			employee.setOtpExpiry(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)));
			employeeRepository.save(employee);

			String subject = "Your OTP for Password Reset";
			String body = "Your OTP for password reset is: " + otp;
			emailService.sendEmail(employee.getEmail(), subject, body);

			return "OTP sent to your email.";
		} else {
			return "Email not found.";
		}
	}
	 @Override
	    public EmployeeResponseDTO convertToDto(Employee employee) {
	        EmployeeResponseDTO empDto = new EmployeeResponseDTO();
	        empDto.setId(employee.getId());
	        empDto.setName(employee.getName());
	       
	        empDto.setUnder(employee.getUnder());
	        empDto.setDateOfJoining(employee.getDateOfJoining());
	        empDto.setEmployeeNumber(employee.getEmployeeNumber());
	        empDto.setWorkAs(employee.getWorkAs());
	        empDto.setLocation(employee.getLocation());
	        empDto.setSomeDate(employee.getSomeDate());
	        empDto.setDob(employee.getDob());
	        empDto.setBloodGroup(employee.getBloodGroup());
	        empDto.setFatherName(employee.getFatherName());
	        empDto.setMotherName(employee.getMotherName());
	        empDto.setAddress(employee.getAddress());
	        empDto.setContactNumber(employee.getContactNumber());
	        empDto.setEmail(employee.getEmail());
	        empDto.setBankName(employee.getBankName());
	        empDto.setBranch(employee.getBranch());
	        empDto.setBankAccountNumber(employee.getBankAccountNumber());
	        empDto.setPancardNumber(employee.getPancardNumber());
	        empDto.setTotalSalary(employee.getTotalSalary());
	       
	        // Set department and designation if needed
	        // empDto.setDepartment(...);
	        // empDto.setDesignation(...);

	        return empDto;
	    }
	
	@Override
	public String verifyOtpAndResetPassword(String email, String otp, String newPassword, String confirmPassword) {
		if (!newPassword.equals(confirmPassword)) {
			return "Passwords do not match.";
		}

		Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
		if (employeeOpt.isPresent()) {
			Employee employee = employeeOpt.get();
			if (employee.getOtp().equals(otp) && employee.getOtpExpiry().after(new Date())) {
				employee.setPassword(newPassword);
				employee.setOtp(null);
				employee.setOtpExpiry(null);
				employeeRepository.save(employee);
				return "Password reset successfully.";
			} else {
				return "Invalid or expired OTP.";
			}
		} else {
			return "Email not found.";
		}
	}

	@Override
	public UserResponse updateEmployee(Long id, Employee employee) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee existingEmployee = optionalEmployee.get();
			UserResponse userResponse=new UserResponse();
			existingEmployee.setName(employee.getName());
			existingEmployee.setUsername(employee.getUsername());
			existingEmployee.setMotherName(employee.getMotherName());
			existingEmployee.setFatherName(employee.getFatherName());
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setContactNumber(employee.getContactNumber());
			existingEmployee.setBloodGroup(employee.getBloodGroup());
			existingEmployee.setAddress(employee.getAddress());
			existingEmployee.setDepartment(employee.getDepartment());
			existingEmployee.setDesignation(employee.getDesignation());
			existingEmployee.setBankAccountNumber(employee.getBankAccountNumber());
			existingEmployee.setLocation(employee.getLocation());
			existingEmployee.setBankName(employee.getBankName());
			existingEmployee.setEmployeeNumber(employee.getEmployeeNumber());
			existingEmployee.setDateOfJoining(employee.getDateOfJoining());
			existingEmployee.setDob(employee.getDob());
			existingEmployee.setWorkAs(employee.getWorkAs());
			existingEmployee.setSomeDate(employee.getSomeDate());
			existingEmployee.setBranch(employee.getBranch());
			existingEmployee.setTotalSalary(employee.getTotalSalary());
			existingEmployee.setUnder(employee.getUnder());
			existingEmployee.setPancardNumber(employee.getPancardNumber());
			
			userResponse.setMessage("Successfully Updated");
			userResponse.setRole("Employee");
			userResponse.setObject(employeeRepository.save(existingEmployee));
			userResponse.setStatus(true);
			return userResponse;
		} else {
			throw new RuntimeException("Employee not found with id " + id);
		}
	}
	
	
}
