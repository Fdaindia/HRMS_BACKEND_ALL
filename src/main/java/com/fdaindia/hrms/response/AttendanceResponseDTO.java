package com.fdaindia.hrms.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceResponseDTO {
    private Long id;
    private Long employeeId; // Employee ID field
    private String username; // Employee username field
    public String getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}
	private String totalHours; // Add this field
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDateTime getPunchInTime() {
		return punchInTime;
	}
	public void setPunchInTime(LocalDateTime punchInTime) {
		this.punchInTime = punchInTime;
	}
	public LocalDateTime getPunchOutTime() {
		return punchOutTime;
	}
	public void setPunchOutTime(LocalDateTime punchOutTime) {
		this.punchOutTime = punchOutTime;
	}
	public String getPunchInAction() {
		return punchInAction;
	}
	public void setPunchInAction(String punchInAction) {
		this.punchInAction = punchInAction;
	}
	public String getPunchOutAction() {
		return punchOutAction;
	}
	public void setPunchOutAction(String punchOutAction) {
		this.punchOutAction = punchOutAction;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;
    private String punchInAction;
    private String punchOutAction;
    private String remarks;
    private double latitude;
    private double longitude;
    private LocalDate attendanceDate;

    // Getters and setters
}

