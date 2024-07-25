package com.fdaindia.hrms.service.impl;

import com.fdaindia.hrms.entity.Attendance;
import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.repository.AttendanceRepository;
import com.fdaindia.hrms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date) {
        return attendanceRepository.findByEmployeeAndAttendanceDate(employee, date);
    }

    @Override
    public boolean hasPunchedOutToday(Employee employee) {
        List<Attendance> attendances = findByEmployeeAndDate(employee, LocalDate.now());
        return attendances.stream().anyMatch(att -> att.getPunchOutTime() != null);
    }

    @Override
    public Attendance punchIn(Employee employee, double latitude, double longitude, String punchInAction, String remarks) {
        if (LocalDateTime.now().getHour() >= 10 && (remarks == null || remarks.isEmpty())) {
            throw new IllegalArgumentException("Remarks are required for punch in after 10 AM.");
        }

        if (hasPunchedOutToday(employee)) {
            throw new IllegalStateException("Cannot punch in again on the same day after punching out.");
        }

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setPunchInTime(LocalDateTime.now());
        attendance.setLatitude(latitude);
        attendance.setLongitude(longitude);
        attendance.setPunchInAction(punchInAction);
        attendance.setRemarks(remarks);
        attendance.setAttendanceDate(LocalDate.now());

        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance punchOut(Employee employee, String punchOutAction) {
        List<Attendance> attendances = findByEmployeeAndDate(employee, LocalDate.now());

        if (attendances.isEmpty()) {
            throw new IllegalStateException("No punch-in record found for today.");
        }

        // Assuming you want to update the most recent punch-in record
        Attendance attendance = attendances.get(attendances.size() - 1);
        attendance.setPunchOutTime(LocalDateTime.now());
        attendance.setPunchOutAction(punchOutAction);

        return attendanceRepository.save(attendance);
    }
    @Override
    public List<Attendance> findAttendanceForLast30Days(Employee employee) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        return attendanceRepository.findByEmployeeAndAttendanceDateBetween(employee, startDate, endDate);
    }
    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

	@Override
	public List<Attendance> findAll() {
		return attendanceRepository.findAll();
	}
}