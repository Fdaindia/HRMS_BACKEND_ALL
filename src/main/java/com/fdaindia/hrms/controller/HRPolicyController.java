package com.fdaindia.hrms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fdaindia.hrms.entity.HrPolicy;
import com.fdaindia.hrms.response.ApiResponse;
import com.fdaindia.hrms.service.HRPolicyService;

@RestController
@RequestMapping(value = "/hrPolicy")
public class HRPolicyController {

	@Autowired
	private HRPolicyService hrPolicyService;

	@RequestMapping(value = { "/add", "/update" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody HrPolicy hrPolicy) {
		return ResponseEntity.ok(this.hrPolicyService.addOrUpdate(hrPolicy));
	}

	@GetMapping(value = "/getById/{hrPolicyId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("hrPolicyId") Long hrPolicyId) {
		return ResponseEntity.ok(this.hrPolicyService.getHrPolicyById(hrPolicyId));
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.ok(this.hrPolicyService.getAll());
	}

	@DeleteMapping("/{hrPolicyId}")
	public ResponseEntity<ApiResponse> deleteHrPolicy(@PathVariable Long hrPolicy) {
		this.hrPolicyService.deleteHrPolicy(hrPolicy);
		return new ResponseEntity<ApiResponse>(new ApiResponse("HrPolicy delete successfully !!", true),
				HttpStatus.OK);
	}
}
