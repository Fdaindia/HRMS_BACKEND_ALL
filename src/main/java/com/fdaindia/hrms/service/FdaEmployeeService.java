package com.fdaindia.hrms.service;


import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.response.UserResponse;

import java.util.Optional;

public interface FdaEmployeeService {
    UserResponse registerEmployee(Employee employee);
    UserResponse updateEmployee(Long id,Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    UserResponse authenticate(String username, String password);
    String generateOtp(String email); // New method for generating OTP
    String verifyOtpAndResetPassword(String email, String otp, String newPassword, String confirmPassword); // New method for verifying OTP and resetting password
    
}
