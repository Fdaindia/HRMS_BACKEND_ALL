package com.fdaindia.hrms.service;

import java.util.Map;

import com.fdaindia.hrms.entity.Department;


public interface DepartmentService {

	// Add a new department or update an existing employee
	Map<String, Object> addOrUpdate(Department department);

	// Get an department by their ID
	Department getDepartmentById(Long departmentId);

	// Get a list of all department
	Map<String, Object> getAll();

	// Delete an department by their ID
	void deleteDepartment(Long departmentId);
}
