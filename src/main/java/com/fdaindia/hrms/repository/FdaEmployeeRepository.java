package com.fdaindia.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.fdaindia.hrms.entity.Employee;


@Repository
public interface FdaEmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
   
    Optional<Employee> findByEmail(String email);
}