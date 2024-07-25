package com.fdaindia.hrms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdaindia.hrms.entity.Attendance;
import com.fdaindia.hrms.entity.Employee;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	// If your query is returning multiple results, change the method to return a list
	@Query("SELECT a FROM Attendance a WHERE a.employee = :employee AND a.attendanceDate = :date")
	List<Attendance> findByEmployeeAndAttendanceDate(@Param("employee") Employee employee, @Param("date") LocalDate date);
	 List<Attendance> findByEmployeeAndAttendanceDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);
    
}