package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.EmployeeAttendance;

public interface EmployeeAttendanceDao extends
		PagingAndSortingRepository<EmployeeAttendance, Long>,
		JpaSpecificationExecutor<EmployeeAttendance> {

}
