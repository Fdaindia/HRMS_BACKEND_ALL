package com.fdaindia.hrms.service;

import java.util.Map;

import com.fdaindia.hrms.entity.HolidayMaster;

public interface HolidayMasterService {

	// Add a new Holiday or update an existing Holiday
	Map<String, Object> addOrUpdate(HolidayMaster holidayMaster);

	// Get an Holiday by their ID
	HolidayMaster getHolidayMasterById(Long holidayId);

	// Get a list of all Holiday
	Map<String, Object> getAll(HolidayMaster holidayMaster);

	// Delete an Holiday by their ID
	void deleteHolidayMaster(Long holidayId);
}
