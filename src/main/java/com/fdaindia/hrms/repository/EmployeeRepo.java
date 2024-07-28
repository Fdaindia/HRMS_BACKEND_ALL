package com.fdaindia.hrms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdaindia.hrms.entity.Employee;
import com.fdaindia.hrms.response.ReportResponse;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE LOWER(e.username) LIKE LOWER(CONCAT('%', :query, '%'))")
	Page<Employee> searchEmployee(@Param("query") String query, Pageable pageable);

	Optional<Employee> findByEmail(String email);

	@Query(value = "SELECT e.username as userName, e.date_of_joining as dateOfJoining,"
			+ "e.employee_number as employeeNumber, e.date_ofbirth as dateOfBirth,"
			+ "e.address as address, e.contact_number as contactNumber,"
			+ "e.email as email, e.branch as branch, d.department as departmentName, ds.designation as designationName "
			+ "FROM employee e " + "LEFT JOIN department d ON d.department_id = e.department_id "
			+ "LEFT JOIN designation ds ON ds.id = e.designation_id", nativeQuery = true)
	List<ReportResponse> fetchReportResponses();

	Optional<Employee> findById(Long id);
	Employee findByUsername(String username);
}
