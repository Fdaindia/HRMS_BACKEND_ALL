package com.fdaindia.hrms.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "hr_policy")
public class HrPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "office_start_time")
	private LocalTime officeStartTime;

	@Column(name = "office_end_time")
	private LocalTime officeEndTime;

	@Column(name = "working_hour")
	private Integer workingHour;

	@Column(name = "min_hours_for_halfday")
	private Integer minHoursForHalfDay;

	@Column(name = "lunch_duration_minutes")
	private Integer lunchDurationMinutes;

	@Column(name = "break_duration_minutes")
	private Integer breakDurationMinutes;

	@Column(name = "lunch_start_time")
	private LocalTime lunchStartTime;

	@Column(name = "lunch_end_time")
	private LocalTime lunchEndTime;

	@Lob
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "salary_report_day")
	private Integer salaryReportDay;

	@Column(name = "min_working_hours")
	private Integer minWorkingHours;

	@Column(name = "intime_reminder")
	private LocalTime intimeReminder;

	@Column(name = "outtime_reminder")
	private LocalTime outtimeReminder;

	@Column(name = "week_off")
	private String weekOff;
}
