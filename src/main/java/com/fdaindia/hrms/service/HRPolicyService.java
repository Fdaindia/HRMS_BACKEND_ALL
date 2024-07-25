package com.fdaindia.hrms.service;

import java.util.Map;

import com.fdaindia.hrms.entity.HrPolicy;

public interface HRPolicyService {

	// Add a new HrPolicy or update an existing HrPolicy
	Map<String, Object> addOrUpdate(HrPolicy hrPolicy);

	// Get an HrPolicy by their ID
	HrPolicy getHrPolicyById(Long hrPolicyId);

	// Get a list of all HrPolicy
	Map<String, Object> getAll();

	// Delete an HrPolicy by their ID
	void deleteHrPolicy(Long hrPolicyId);

}
