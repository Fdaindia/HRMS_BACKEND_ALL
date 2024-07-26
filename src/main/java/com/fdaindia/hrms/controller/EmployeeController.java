package com.fdaindia.hrms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdaindia.hrms.config.AppConstants;
import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.exceptions.ResourceNotFoundException;
import com.fdaindia.hrms.response.ApiResponse;
import com.fdaindia.hrms.response.ReportResponse;
import com.fdaindia.hrms.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@CrossOrigin
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(this.employeeService.addEmployee(employee));
	}

	@CrossOrigin
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(this.employeeService.updateEmployee(employee));
	}

	@CrossOrigin()
	@GetMapping(value = "/getById/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("employeeId") Long employeeId) {
		return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
	}

	@CrossOrigin()
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEmployees() {
		try {
			List<Employee> employees = employeeService.getAll();
			return ResponseEntity.ok(employees);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch employees.");
		}
	}

	@CrossOrigin()
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long employeeId) {
		this.employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("employee delete successfully !!", true), HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchEmployee(@RequestParam("query") String query,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			List<Employee> employees = employeeService.searchEmployee(query, pageable);
			return ResponseEntity.ok(employees);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while searching for employees.");
		}
	}

	@GetMapping("/getByEmail")
	public ResponseEntity<?> getEmployeeByEmail(@RequestParam("email") String email) {
		try {
			Employee employee = employeeService.getEmployeeByEmail(email);
			return ResponseEntity.ok(employee);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching the employee.");
		}
	}

	@CrossOrigin()
	@GetMapping("/export")
	public ResponseEntity<InputStreamResource> exportEmployeesToExcel() throws IOException {
		List<ReportResponse> employees = employeeService.fetchReportResponses();

		ByteArrayInputStream in = employeeService.exportEmployeesToExcel(employees);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new InputStreamResource(in));
	}

}
