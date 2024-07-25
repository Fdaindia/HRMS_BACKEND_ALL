package com.fdaindia.hrms.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private boolean status;
    private String message;
    private Object object;
    private String username;
    private String role;
    
}

