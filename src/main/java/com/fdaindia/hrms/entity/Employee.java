package com.fdaindia.hrms.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Past;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
//	@NotBlank(message = "Username is mandatory")
//	@Size(max = 100, message = "Username should not exceed 100 characters")
	@Column(name = "username")
	private String username;

	@Column(name = "under")
	private String under;

//	@NotBlank(message = "Date of joining is mandatory")
	@Column(name = "date_of_joining")
	private String dateOfJoining;

	@Column(name = "employee_number")
	private String employeeNumber;

	@Column(name = "work_as")
	private String workAs;

	@Column(name = "location")
	private String location;

	@Column(name = "some_date")
	private String someDate;

	@Column(name = "date_of_birth")
//	@NotNull(message = "Date of birth is mandatory")
//	@Past(message = "Date of birth must be in the past")
	private String dob;

	@Column(name = "blood_group")
//	@NotBlank(message = "Blood group is mandatory")
//	@Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood group")
	private String bloodGroup;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "mother_name")
	private String motherName;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_number")
//	@NotBlank(message = "Contact number is mandatory")
//	@Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits")
	private String contactNumber;

//	@Email(message = "Email should be valid")
//	@NotBlank(message = "Email is mandatory")
	@Column(name = "email")
	private String email;

	@Column(name = "bank_name")
//	@NotBlank(message = "Bank name is mandatory")
//	@Size(max = 100, message = "Bank name should not exceed 100 characters")
	private String bankName;

	@Column(name = "branch")
//	@NotBlank(message = "Branch is mandatory")
//	@Size(max = 100, message = "Branch should not exceed 100 characters")
	private String branch;

	@Column(name = "bank_account_number")
//	@NotBlank(message = "Bank account number is mandatory")
//	@Size(min = 10, max = 20, message = "Bank account number should be between 10 and 20 characters long")
	private String bankAccountNumber;

	@Column(name = "pan_card_number")
//	@NotBlank(message = "PAN card number is mandatory")
//	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN card number format")
	private String pancardNumber;

	@Column(name = "total_salary")
	private Long totalSalary;

	@Column(name = "password")
	private String password;

	@Column(name = "otp")
	private String otp;

	@Column(name = "otpExpiry")
	private Date otpExpiry;

	@Column(name = "role")
	private String role;

	@JsonManagedReference
	@OneToMany(mappedBy = "employee")
	private List<Attendance> attendance;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", insertable = true, updatable = false)
	private Department department;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "designation_id", insertable = true, updatable = false)
	private Designation designation;
}