package com.fdaindia.hrms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "leave_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_type_id")
	private Long id;

	public Long getId() {
		return id;
	}

	public LeaveType(Long id, String leaveName, String leaveType, boolean carryForward, int maxDays,
			String description) {
		super();
		this.id = id;
		this.leaveName = leaveName;
		this.leaveType = leaveType;
		this.carryForward = carryForward;
		this.maxDays = maxDays;
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public boolean isCarryForward() {
		return carryForward;
	}

	public void setCarryForward(boolean carryForward) {
		this.carryForward = carryForward;
	}

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "leave_name", nullable = false)
	private String leaveName;

	@Column(name = "leave_type", nullable = false)
	private String leaveType;

	@Column(name = "carry_forward")
	private boolean carryForward;

	@Column(name = "max_days", nullable = true)
	private int maxDays;

	@Column(name = "description")
	private String description;

}
