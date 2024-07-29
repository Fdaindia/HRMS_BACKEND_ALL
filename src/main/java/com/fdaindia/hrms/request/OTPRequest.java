package com.fdaindia.hrms.request;

import lombok.Data;

@Data
public class OTPRequest {
	private String toEmail;
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	private int otp;
}