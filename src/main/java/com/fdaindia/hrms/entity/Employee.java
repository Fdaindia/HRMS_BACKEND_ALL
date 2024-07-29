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
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Past;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
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
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Attendance> attendance;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", insertable = true, updatable = false)
	private Department department;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "designation_id", insertable = true, updatable = false)
	private Designation designation;

	
	public Employee(Long id, String name, String username, String under, String dateOfJoining, String employeeNumber,
			String workAs, String location, String someDate, String dob, String bloodGroup, String fatherName,
			String motherName, String address, String contactNumber, String email, String bankName, String branch,
			String bankAccountNumber, String pancardNumber, Long totalSalary, String password, String otp,
			Date otpExpiry, String role, List<Attendance> attendance, Department department, Designation designation) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.under = under;
		this.dateOfJoining = dateOfJoining;
		this.employeeNumber = employeeNumber;
		this.workAs = workAs;
		this.location = location;
		this.someDate = someDate;
		this.dob = dob;
		this.bloodGroup = bloodGroup;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.bankName = bankName;
		this.branch = branch;
		this.bankAccountNumber = bankAccountNumber;
		this.pancardNumber = pancardNumber;
		this.totalSalary = totalSalary;
		this.password = password;
		this.otp = otp;
		this.otpExpiry = otpExpiry;
		this.role = role;
		this.attendance = attendance;
		this.department = department;
		this.designation = designation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUnder() {
		return under;
	}

	public void setUnder(String under) {
		this.under = under;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getWorkAs() {
		return workAs;
	}

	public void setWorkAs(String workAs) {
		this.workAs = workAs;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSomeDate() {
		return someDate;
	}

	public void setSomeDate(String someDate) {
		this.someDate = someDate;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public Long getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(Long totalSalary) {
		this.totalSalary = totalSalary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(Date otpExpiry) {
		this.otpExpiry = otpExpiry;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public Employee() {
		
	}
}
	