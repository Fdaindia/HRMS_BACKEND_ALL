package com.fdaindia.hrms.controller;

import com.fdaindia.hrms.entity.Attendance;
import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.request.AttendanceRequest;
import com.fdaindia.hrms.service.AttendanceService;
import com.fdaindia.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private EmployeeService employeeService;
	//This is main branch 
	@CrossOrigin()
	@PostMapping("/punch")
	public ResponseEntity<?> punchIn(@RequestBody AttendanceRequest attendanceRequest) {
		Employee employee = employeeService.findById(attendanceRequest.getEmployeeId());
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
		try {
			Attendance attendance = attendanceService.punchIn(employee, attendanceRequest.getLatitude(),
					attendanceRequest.getLongitude(), attendanceRequest.getPunchInAction(),
					attendanceRequest.getRemarks());
			return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (IllegalStateException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@CrossOrigin()
	@PostMapping("/punch-out")
	public ResponseEntity<?> punchOut(@RequestBody AttendanceRequest attendanceRequest) {
		Employee employee = employeeService.findById(attendanceRequest.getEmployeeId());
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
		try {
			Attendance attendance = attendanceService.punchOut(employee, attendanceRequest.getPunchOutAction());
			return ResponseEntity.ok(attendance);
		} catch (IllegalStateException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@CrossOrigin()
	@GetMapping("/records")
	public ResponseEntity<?> getAttendanceForLast30Days(@RequestParam Long employeeId) {
		Employee employee = employeeService.findById(employeeId);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}
		List<Attendance> attendances = attendanceService.findAttendanceForLast30Days(employee);
		return ResponseEntity.ok(attendances);
	}
	
	@CrossOrigin()
	@GetMapping("/getAll")
	public ResponseEntity<?> getAttendanceForLast30Days() {
		List<Attendance> attendances = attendanceService.findAll();
		return ResponseEntity.ok(attendances);
	}
	

	@CrossOrigin()
	@GetMapping("/current-datetime")
	public LocalDateTime getCurrentDateTime() {
		return attendanceService.getCurrentDateTime();
	}

	@CrossOrigin()
	@GetMapping("/current-date")
	public LocalDate getCurrentDate() {
		return attendanceService.getCurrentDate();
	}
}