package com.fdaindia.hrms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdaindia.hrms.entity.HrPolicy;
import com.fdaindia.hrms.exceptions.ResourceNotFoundException;
import com.fdaindia.hrms.repository.HRPolicyRepo;
import com.fdaindia.hrms.service.HRPolicyService;

@Service
public class HRPolicyServiceImpl implements HRPolicyService{

	@Autowired
	private HRPolicyRepo hrPolicyRepo;
	
	@Override
	public Map<String, Object> addOrUpdate(HrPolicy hrPolicy) {
		Map<String, Object> response = new HashMap<>();
		response.put("data", this.hrPolicyRepo.save(hrPolicy));
		response.put("status", "SUCCESS");
		response.put("message", "HR Policy saved successfully.");
		return response;

	}

	@Override
	public HrPolicy getHrPolicyById(Long hrPolicyId) {
		return this.hrPolicyRepo.findById(hrPolicyId)
				.orElseThrow(() -> new ResourceNotFoundException("HR Policy", "hrPolicy Id", hrPolicyId));
	}

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("Data", this.hrPolicyRepo.findAll());
		return response;
	}


	@Override
	public void deleteHrPolicy(Long hrPolicyId) {
		HrPolicy hrPolicy = this.hrPolicyRepo.findById(hrPolicyId)
				.orElseThrow(() -> new ResourceNotFoundException("HR Policy", "hrPolicy Id", hrPolicyId));
		this.hrPolicyRepo.delete(hrPolicy);
		
	}
}
