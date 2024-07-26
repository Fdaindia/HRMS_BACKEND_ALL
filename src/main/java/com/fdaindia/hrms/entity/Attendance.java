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

	@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
	
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
