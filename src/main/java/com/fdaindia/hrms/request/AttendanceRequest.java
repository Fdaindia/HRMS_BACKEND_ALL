package com.fdaindia.hrms.request;

import java.time.LocalDateTime;

public class AttendanceRequest {
    private Long employeeId;
    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;
    private double latitude;
    private double longitude;
    private String punchInAction;
    private String punchOutAction;
    private String remarks; // Add remarks field

    // Getters and Setters
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
}
