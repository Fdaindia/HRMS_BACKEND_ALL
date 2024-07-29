package com.fdaindia.hrms.service.impl;

import com.fdaindia.hrms.entity.Attendance;
import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.repository.AttendanceRepository;
import com.fdaindia.hrms.repository.EmployeeRepo;
import com.fdaindia.hrms.response.AttendanceResponseDTO;
import com.fdaindia.hrms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private EmployeeRepo employeeRepository;
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

        // Find today's attendance record
        List<Attendance> todayAttendances = findByEmployeeAndDate(employee, LocalDate.now());
        Attendance attendance;

        if (!todayAttendances.isEmpty()) {
            attendance = todayAttendances.get(todayAttendances.size() - 1);
            // Update existing punch-in details
            attendance.setPunchInTime(LocalDateTime.now());
            attendance.setLatitude(latitude);
            attendance.setLongitude(longitude);
            attendance.setPunchInAction(punchInAction);
            attendance.setRemarks(remarks);
        } else {
            // Create a new punch-in record for a new day
            attendance = new Attendance();
            attendance.setEmployee(employee);
            attendance.setPunchInTime(LocalDateTime.now());
            attendance.setLatitude(latitude);
            attendance.setLongitude(longitude);
            attendance.setPunchInAction(punchInAction);
            attendance.setRemarks(remarks);
            attendance.setAttendanceDate(LocalDate.now());
            attendance.setUsername(employee.getUsername());
        }

        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance punchOut(Employee employee, String punchOutAction) {
        List<Attendance> attendances = findByEmployeeAndDate(employee, LocalDate.now());

        if (attendances.isEmpty()) {
            throw new IllegalStateException("No punch-in record found for today.");
        }

        Attendance attendance = attendances.get(attendances.size() - 1);
        if (attendance.getPunchOutTime() != null) {
            throw new IllegalStateException("Already punched out today.");
        }
        attendance.setPunchOutTime(LocalDateTime.now());
        attendance.setPunchOutAction(punchOutAction);

        // Calculate total working hours
        if (attendance.getPunchInTime() != null && attendance.getPunchOutTime() != null) {
            Duration duration = Duration.between(attendance.getPunchInTime(), attendance.getPunchOutTime());
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            attendance.setTotalHours(String.format("%02d:%02d", hours, minutes));
        }

        return attendanceRepository.save(attendance);
    }

    @Scheduled(cron = "0 0 18 * * ?") // Runs every day at 6 PM
    public void autoPunchOutEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            List<Attendance> attendances = attendanceRepository.findByEmployeeAndAttendanceDate(employee, LocalDate.now());

            if (!attendances.isEmpty()) {
                Attendance attendance = attendances.get(attendances.size() - 1);

                if (attendance.getPunchOutTime() == null) {
                    attendance.setPunchOutTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0)));
                    attendance.setPunchOutAction("AUTO_PUNCH_OUT");

                    // Calculate total working hours
                    if (attendance.getPunchInTime() != null) {
                        Duration duration = Duration.between(attendance.getPunchInTime(), attendance.getPunchOutTime());
                        long hours = duration.toHours();
                        long minutes = duration.toMinutes() % 60;
                        attendance.setTotalHours(String.format("%02d:%02d", hours, minutes));
                    }

                    attendanceRepository.save(attendance);
                }
            }}}
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
    public List<AttendanceResponseDTO> findAll() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendances.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AttendanceResponseDTO convertToDTO(Attendance attendance) {
        AttendanceResponseDTO dto = new AttendanceResponseDTO();
        dto.setId(attendance.getId());
        dto.setEmployeeId(attendance.getEmployeeId()); // Use appropriate getter if needed
        dto.setPunchInTime(attendance.getPunchInTime());
        dto.setPunchOutTime(attendance.getPunchOutTime());
        dto.setPunchInAction(attendance.getPunchInAction());
        dto.setPunchOutAction(attendance.getPunchOutAction());
        dto.setRemarks(attendance.getRemarks());
        dto.setLatitude(attendance.getLatitude());
        dto.setLongitude(attendance.getLongitude());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setUsername(attendance.getUsername()); // Assuming username is stored
        dto.setTotalHours(attendance.getTotalHours());

        return dto;
    }

    @Override
    public String getTotalWorkingHoursForMonth(Employee employee, YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        List<Attendance> attendances = attendanceRepository.findByEmployeeAndAttendanceDateBetween(employee, startDate, endDate);

        long totalMinutes = 0;

        for (Attendance attendance : attendances) {
            if (attendance.getPunchInTime() != null && attendance.getPunchOutTime() != null) {
                Duration duration = Duration.between(attendance.getPunchInTime(), attendance.getPunchOutTime());
                totalMinutes += duration.toMinutes(); // Accumulate total minutes
            }
        }

        // Convert total minutes to HH:mm format
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
    
//	@Override
//	public List<Attendance> findAll() {
//		return attendanceRepository.findAll();
//	}
}