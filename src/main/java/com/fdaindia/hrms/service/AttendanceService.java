package com.fdaindia.hrms.service;

import com.fdaindia.hrms.entity.Attendance;
import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.response.AttendanceResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface AttendanceService {
    Attendance saveAttendance(Attendance attendance);
    List<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);
    Attendance punchIn(Employee employee, double latitude, double longitude, String punchInAction, String remarks);
    Attendance punchOut(Employee employee, String punchOutAction);
    LocalDateTime getCurrentDateTime();
    LocalDate getCurrentDate();
    boolean hasPunchedOutToday(Employee employee);
    List<Attendance> findAttendanceForLast30Days(Employee employee);
    List<AttendanceResponseDTO> findAll();
    String getTotalWorkingHoursForMonth(Employee employee, YearMonth yearMonth);
}