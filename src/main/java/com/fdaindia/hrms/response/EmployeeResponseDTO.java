package com.fdaindia.hrms.response;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String under;
    private String dateOfJoining;
    private String employeeNumber;
    private String workAs;
    private String location;
    private String someDate;
    private String dob;
    private String bloodGroup;
    private String fatherName;
    private String motherName;
    private String address;
    private String contactNumber;
    private String email;
    private String bankName;
    private String branch;
    private String bankAccountNumber;
    private String pancardNumber;
    private Long totalSalary;
    private String role;
	
}
