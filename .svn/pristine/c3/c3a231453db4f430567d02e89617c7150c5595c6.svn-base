package com.coamctech.bxloan.service.countregistration.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.EmployeeAttendanceDao;
import com.coamctech.bxloan.entity.EmployeeAttendance;
import com.coamctech.bxloan.service.countregistration.CountRegistrationService;

@Service
public class CountRegistrationServiceImpl implements CountRegistrationService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private EmployeeAttendanceDao employeeAttendanceDao;

	@Override
	public Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize,
			Long orgId) {
		StringBuffer sql = new StringBuffer(
				"select ea.name, ea.address, ea.logtime, eod.name org_name, ea.id from "
						+ "EMPLOYEE_ATTENDANCE ea "
						+ "join EC_ORG_DEPARTMENT eod on ea.org_id = eod.id "
						+ "where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (orgId != null) {
			sql.append(" and ea.org_id = ?1");
			params.add(orgId);
		}
		sql.append(" order by ea.logtime desc");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@Override
	public EmployeeAttendance findOne(Long id) {
		return employeeAttendanceDao.findOne(id);
	}

}
