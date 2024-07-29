package com.fdaindia.hrms.request;

import lombok.Data;

@Data
public class EmailRequest {
	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	private String toEmail;
}
