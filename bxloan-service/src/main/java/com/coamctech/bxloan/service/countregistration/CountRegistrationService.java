package com.coamctech.bxloan.service.countregistration;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.EmployeeAttendance;

public interface CountRegistrationService {

	/**
	 * 查询签到列表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param orgId
	 * @return
	 */
	Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize, Long orgId);

	EmployeeAttendance findOne(Long id);

}
