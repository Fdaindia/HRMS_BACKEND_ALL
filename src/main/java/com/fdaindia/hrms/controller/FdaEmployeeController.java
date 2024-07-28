package com.fdaindia.hrms.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.request.UserRequest;
import com.fdaindia.hrms.response.EmployeeResponseDTO;
import com.fdaindia.hrms.response.UserResponse;
import com.fdaindia.hrms.service.FdaEmployeeService;
import com.fdaindia.hrms.service.impl.FdaEmployeeServiceImpl;
import com.fdaindia.hrms.tokenconfig.JwtTokenUtil;

@RestController
@RequestMapping("/hrms/emp")
public class FdaEmployeeController {

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	private FdaEmployeeService fdaEmployeeService;
	@Autowired
	private FdaEmployeeServiceImpl employeeService;

	@CrossOrigin()
	@GetMapping("/test")
	public String user() {
		System.out.println("Employee hit");
		return "Employee hit";
	}

	@CrossOrigin()
	@PostMapping("/registration")
	public UserResponse registerEmployee(@RequestBody Employee employee) {
		return fdaEmployeeService.registerEmployee(employee);
	}
	
	@CrossOrigin()
	@PutMapping("/update/{id}")
	public UserResponse updatEmployee(@PathVariable Long id,@RequestBody Employee employee) {
		return fdaEmployeeService.updateEmployee(id,employee);
	}

	@CrossOrigin()
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email) {
		return employeeService.generateOtp(email);
	}

	@CrossOrigin()
	@PostMapping("/reset-password")
	public String resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword,
			@RequestParam String confirmPassword) {
		return employeeService.verifyOtpAndResetPassword(email, otp, newPassword, confirmPassword);
	}

	@CrossOrigin()
	@PostMapping("/login")
	public UserResponse loginEmployee(@RequestParam String username, @RequestParam String password) {
		UserResponse response = fdaEmployeeService.authenticate(username, password);

		if (response.isStatus()) {
			// Get the EmployeeResponseDTO from the response object
			EmployeeResponseDTO employeeDTO = (EmployeeResponseDTO) response.getObject();
			if (employeeDTO != null) {

				response.setObject(employeeDTO); // Set the EmployeeResponseDTO in the response
				response.setRole("Employee"); // Set the role in the UserResponse
				response.setMessage("Login Successful");
				response.setStatus(true);
			}
		} else {
			response.setMessage("Invalid username or password");
			response.setObject(null); // Ensure object is null on failure
			response.setRole(null); // Ensure role is null on failure
			response.setStatus(false);
		}

		return response;
	}

//	@CrossOrigin()
//	@PostMapping("/hrmslogin")
//	public UserResponse hrmsUser(@RequestBody UserRequest request) {
//		UserResponse response = new UserResponse();
//		System.out.println(request.getUsername() + " " + request.getPassword());
//		if (request.getUsername().equals("ADMIN") && request.getPassword().equals("Fdaindia@2024")) {
//			response.setStatus(true);
//			response.setRole("ADMIN");
//			response.setUsername(request.getUsername());
//			response.setMessage("HRMS Login Successful");
//		} else {
//			response.setStatus(false);
//			response.setMessage("Failed");
//			response.setUsername(request.getUsername());
//		}
//		return response;
//	}
	@PostMapping("/hrmslogin")
    public UserResponse hrmsUser(@RequestBody UserRequest request) {
        UserResponse response = new UserResponse();
        System.out.println(request.getUsername() + " " + request.getPassword());
        if (request.getUsername().equals("ADMIN") && request.getPassword().equals("Fdaindia@2024")) {
            response.setStatus(true);
            String role = "ADMIN"; // Admin role
            String sessionId = UUID.randomUUID().toString();
            Date sessionExpiry = new Date(System.currentTimeMillis() + JwtTokenUtil.JWT_TOKEN_VALIDITY * 1000);

            // Initialize the Employee object properly
            Employee employee = new Employee();
            employee.setUsername(request.getUsername());
            employee.setRole(role); // Set the role
        EmployeeResponseDTO empDto=new EmployeeResponseDTO();
            // Generate JWT token with session ID and expiry
            String token = jwtTokenUtil.generateTokenWithSessionId(employee, sessionId, sessionExpiry);
            response.setToken(token);
            response.setSessionId(sessionId);
            response.setSessionExpiry(sessionExpiry);
            response.setRole(role); // Set the role
            response.setMessage("success");
            response.setUsername(employee.getUsername());
            response.setObject(empDto);
        } else {
            response.setStatus(false);
            response.setMessage("Failed");
        }
        return response;
    }}