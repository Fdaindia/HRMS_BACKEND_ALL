package com.fdaindia.hrms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fdaindia.hrms.entity.Designation;
import com.fdaindia.hrms.response.ApiResponse;
import com.fdaindia.hrms.service.DesignationService;

@RestController
@RequestMapping("/designation")
public class DesignationController {

	@Autowired
	private DesignationService designationService;

	@CrossOrigin()
	@RequestMapping(value = { "/add", "/update" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody Designation designation) {
		return ResponseEntity.ok(this.designationService.addOrUpdate(designation));
	}

	@CrossOrigin()
	@GetMapping(value = "/getById/{designationId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("designationId") Long designationId) {
		return ResponseEntity.ok(this.designationService.getDesignationById(designationId));
	}

	@CrossOrigin()
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.ok(this.designationService.getAll());
	}

	@CrossOrigin()
	@DeleteMapping("/{designationId}")
	public ResponseEntity<ApiResponse> deleteDesignation(@PathVariable Long designationId) {
		this.designationService.deleteDesignation(designationId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("designation delete successfully !!", true),
				HttpStatus.OK);
	}
}
