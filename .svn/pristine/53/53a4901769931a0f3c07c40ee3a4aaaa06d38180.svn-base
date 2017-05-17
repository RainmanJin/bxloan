package com.coamctech.bxloan.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
@Service

public class OrgDeptmentServiceImpl implements OrgDeptmentService {
	@Autowired
	private EcOrgDepartmentDao ecOrgDepartmentDao;
	/*@Autowired
	private DynamicQuery dynamicQuery;*/
	@Override
	public List<EcOrgDepartment> findAllOrgList(){
		return ecOrgDepartmentDao.findAllOrgList(2);//全部小贷机构
	}
	@Override
	public String findDeptNameById(Long orgId) {
		return ecOrgDepartmentDao.findDeptNameById(orgId);
	}
}
