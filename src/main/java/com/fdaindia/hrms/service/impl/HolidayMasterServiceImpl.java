package com.fdaindia.hrms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdaindia.hrms.entity.HolidayMaster;
import com.fdaindia.hrms.exceptions.ResourceNotFoundException;
import com.fdaindia.hrms.repository.HolidayMasterRepo;
import com.fdaindia.hrms.service.HolidayMasterService;

@Service
public class HolidayMasterServiceImpl implements HolidayMasterService {

	@Autowired
	private HolidayMasterRepo holidayMasterRepo;

	@Override
	public Map<String, Object> addOrUpdate(HolidayMaster holidayMaster) {
		Map<String, Object> response = new HashMap<>();
		response.put("data", holidayMasterRepo.save(holidayMaster));
		response.put("status", "SUCCESS");
		return response;
	}

	@Override
	public HolidayMaster getHolidayMasterById(Long holidayId) {
		return holidayMasterRepo.findById(holidayId)
				.orElseThrow(() -> new ResourceNotFoundException("HolidayMaster", "holidayId", holidayId));
	}

	@Override
	public Map<String, Object> getAll(HolidayMaster holidayMaster) {
		List<HolidayMaster> holidays = holidayMasterRepo.findAll();
		Map<String, Object> response = new HashMap<>();
		response.put("data", holidays);
		response.put("status", "SUCCESS");
		return response;
	}

	@Override
	public void deleteHolidayMaster(Long holidayId) {
		HolidayMaster holidayMaster = holidayMasterRepo.findById(holidayId)
				.orElseThrow(() -> new ResourceNotFoundException("HolidayMaster", "holidayId", holidayId));
		holidayMasterRepo.delete(holidayMaster);
	}

}
