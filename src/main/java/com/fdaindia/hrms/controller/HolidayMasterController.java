package com.fdaindia.hrms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fdaindia.hrms.entity.HolidayMaster;
import com.fdaindia.hrms.service.HolidayMasterService;

@RestController
@RequestMapping(value = "/holidaymaster")
public class HolidayMasterController {

	@Autowired
	private HolidayMasterService holidayMasterService;

	@RequestMapping(value = { "/add", "/update" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody HolidayMaster holidayMaster) {
		return ResponseEntity.ok(holidayMasterService.addOrUpdate(holidayMaster));
	}

	@GetMapping("/getById/{holidayId}")
	public ResponseEntity<?> getById(@PathVariable("holidayId") Long holidayId) {
		return ResponseEntity.ok(holidayMasterService.getHolidayMasterById(holidayId));
	}

	@GetMapping("/list")
	public ResponseEntity<?> getAllHolidays() {
		return ResponseEntity.ok(holidayMasterService.getAll(new HolidayMaster()));
	}

	@DeleteMapping("/{holidayId}")
	public ResponseEntity<?> deleteHolidayMaster(@PathVariable Long holidayId) {
		holidayMasterService.deleteHolidayMaster(holidayId);
		return ResponseEntity.ok("Holiday deleted successfully!");
	}

}
