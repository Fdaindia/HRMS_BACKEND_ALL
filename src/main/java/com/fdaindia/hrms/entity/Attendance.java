package com.fdaindia.hrms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "punch_in_time")
	private LocalDateTime punchInTime;

	@Column(name = "punch_out_time")
	private LocalDateTime punchOutTime;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "attendance_date")
	private LocalDate attendanceDate;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "punch_in_action")
	private String punchInAction;

	@Column(name = "punch_out_action")
	private String punchOutAction;
	@Column(name = "username")
	private String username;
	private String totalHours;
	public String getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	  public Attendance(Long id, LocalDateTime punchInTime, LocalDateTime punchOutTime, double latitude,
			LocalDate attendanceDate, double longitude, String remarks, String punchInAction, String punchOutAction,
			String username, String totalHours, Employee employee) {
		super();
		this.id = id;
		this.punchInTime = punchInTime;
		this.punchOutTime = punchOutTime;
		this.latitude = latitude;
		this.attendanceDate = attendanceDate;
		this.longitude = longitude;
		this.remarks = remarks;
		this.punchInAction = punchInAction;
		this.punchOutAction = punchOutAction;
		this.username = username;
		this.totalHours = totalHours;
		this.employee = employee;
	}
	public Long getEmployeeId() {
	        return employee != null ? employee.getId() : null;
	    }

	  
	public String getUsername() {
		return employee != null ? employee.getUsername() : null;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Attendance() {
		// TODO Auto-generated constructor stub
	}

	@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@PrePersist
    public void prePersist() {
        if (this.punchInTime == null) {
            this.punchInTime = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        // Automatically update punch-out time if it's not set
        if (this.punchOutTime == null && this.punchOutAction != null) {
            this.punchOutTime = LocalDateTime.now();
        }
    }
}
