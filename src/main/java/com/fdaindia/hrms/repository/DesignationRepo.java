package com.fdaindia.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdaindia.hrms.entity.Designation;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {

}
