package com.fdaindia.hrms.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public void sendEmail(String toEmail, String subject, String body);
}
