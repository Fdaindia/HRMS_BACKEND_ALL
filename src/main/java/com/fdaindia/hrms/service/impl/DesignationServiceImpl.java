package com.fdaindia.hrms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdaindia.hrms.entity.Designation;
import com.fdaindia.hrms.exceptions.ResourceNotFoundException;
import com.fdaindia.hrms.repository.DesignationRepo;
import com.fdaindia.hrms.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepo designationRepo;
	
	@Override
	public Map<String, Object> addOrUpdate(Designation designation) {
		Map<String, Object> response = new HashMap<>();
		response.put("data", this.designationRepo.save(designation));
		response.put("status", "SUCCESS");
		return response;
	}

	@Override
	public Designation getDesignationById(Long DesignationId) {
		return this.designationRepo.findById(DesignationId)
				.orElseThrow(() -> new ResourceNotFoundException("Designation", "Designation Id", DesignationId));
	}

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("Data", this.designationRepo.findAll());
		return response;
	}

	@Override
	public void deleteDesignation(Long DesignationId) {
		Designation designation = this.designationRepo.findById(DesignationId)
				.orElseThrow(() -> new ResourceNotFoundException("Designation", "Designation Id", DesignationId));
		this.designationRepo.delete(designation);
		
	}

}
