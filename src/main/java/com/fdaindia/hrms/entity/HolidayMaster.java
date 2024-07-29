package com.fdaindia.hrms.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "holiday_master")
public class HolidayMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public HolidayMaster(Long id, String holidayName, LocalDate holidayDate, String description, String holidayType,
			int year, String createdBy, LocalDate createdDate) {
		super();
		this.id = id;
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
		this.description = description;
		this.holidayType = holidayType;
		this.year = year;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public HolidayMaster() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "holiday_name", nullable = false)
	private String holidayName;

	@Column(name = "holiday_date", nullable = false)
	private LocalDate holidayDate;

	@Column(name = "description")
	private String description;

	@Column(name = "holiday_type", nullable = false)
	private String holidayType;

	@Column(name = "year", nullable = false)
	private int year;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;

}
