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

	public HrPolicy(Long id, LocalTime officeStartTime, LocalTime officeEndTime, Integer workingHour,
			Integer minHoursForHalfDay, Integer lunchDurationMinutes, Integer breakDurationMinutes,
			LocalTime lunchStartTime, LocalTime lunchEndTime, String emailId, Integer salaryReportDay,
			Integer minWorkingHours, LocalTime intimeReminder, LocalTime outtimeReminder, String weekOff) {
		super();
		this.id = id;
		this.officeStartTime = officeStartTime;
		this.officeEndTime = officeEndTime;
		this.workingHour = workingHour;
		this.minHoursForHalfDay = minHoursForHalfDay;
		this.lunchDurationMinutes = lunchDurationMinutes;
		this.breakDurationMinutes = breakDurationMinutes;
		this.lunchStartTime = lunchStartTime;
		this.lunchEndTime = lunchEndTime;
		this.emailId = emailId;
		this.salaryReportDay = salaryReportDay;
		this.minWorkingHours = minWorkingHours;
		this.intimeReminder = intimeReminder;
		this.outtimeReminder = outtimeReminder;
		this.weekOff = weekOff;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getOfficeStartTime() {
		return officeStartTime;
	}

	public void setOfficeStartTime(LocalTime officeStartTime) {
		this.officeStartTime = officeStartTime;
	}

	public LocalTime getOfficeEndTime() {
		return officeEndTime;
	}

	public void setOfficeEndTime(LocalTime officeEndTime) {
		this.officeEndTime = officeEndTime;
	}

	public Integer getWorkingHour() {
		return workingHour;
	}

	public void setWorkingHour(Integer workingHour) {
		this.workingHour = workingHour;
	}

	public Integer getMinHoursForHalfDay() {
		return minHoursForHalfDay;
	}

	public void setMinHoursForHalfDay(Integer minHoursForHalfDay) {
		this.minHoursForHalfDay = minHoursForHalfDay;
	}

	public Integer getLunchDurationMinutes() {
		return lunchDurationMinutes;
	}

	public void setLunchDurationMinutes(Integer lunchDurationMinutes) {
		this.lunchDurationMinutes = lunchDurationMinutes;
	}

	public Integer getBreakDurationMinutes() {
		return breakDurationMinutes;
	}

	public void setBreakDurationMinutes(Integer breakDurationMinutes) {
		this.breakDurationMinutes = breakDurationMinutes;
	}

	public LocalTime getLunchStartTime() {
		return lunchStartTime;
	}

	public void setLunchStartTime(LocalTime lunchStartTime) {
		this.lunchStartTime = lunchStartTime;
	}

	public LocalTime getLunchEndTime() {
		return lunchEndTime;
	}

	public void setLunchEndTime(LocalTime lunchEndTime) {
		this.lunchEndTime = lunchEndTime;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getSalaryReportDay() {
		return salaryReportDay;
	}

	public void setSalaryReportDay(Integer salaryReportDay) {
		this.salaryReportDay = salaryReportDay;
	}

	public Integer getMinWorkingHours() {
		return minWorkingHours;
	}

	public void setMinWorkingHours(Integer minWorkingHours) {
		this.minWorkingHours = minWorkingHours;
	}

	public LocalTime getIntimeReminder() {
		return intimeReminder;
	}

	public void setIntimeReminder(LocalTime intimeReminder) {
		this.intimeReminder = intimeReminder;
	}

	public LocalTime getOuttimeReminder() {
		return outtimeReminder;
	}

	public void setOuttimeReminder(LocalTime outtimeReminder) {
		this.outtimeReminder = outtimeReminder;
	}

	public String getWeekOff() {
		return weekOff;
	}

	public void setWeekOff(String weekOff) {
		this.weekOff = weekOff;
	}

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
