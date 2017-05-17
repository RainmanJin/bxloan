package com.coamctech.bxloan.service.sysmng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.dao.EcOrgPersonDao;
import com.coamctech.bxloan.dao.EcOrgPersonconnroleDao;
import com.coamctech.bxloan.dao.PersonDetailsDao;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.PersonDetails;
import com.coamctech.bxloan.service.sysmng.PersonMngService;

@Transactional
@Service("personMngService")
public class PersonMngServiceImpl implements PersonMngService{
	
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private EcOrgDepartmentDao ecOrgDepartmentDao;
	@Autowired
	private EcOrgPersonDao ecOrgPersonDao;
	@Autowired
	private PersonDetailsDao personDetailsDao;
	@Autowired
	private EcOrgPersonconnroleDao ecOrgPersonconnroleDao;
	
	@Override
	public EcOrgPerson findPersonById(String personId){
		return ecOrgPersonDao.findOne(new Long(personId));
	}
	@Override
	public PersonDetails findPersonDetailsById(String personId){
		return personDetailsDao.findOne(new Long(personId));
	}
	@Override
	public List<EcOrgDepartment> getAllDepartment() {
		return (List<EcOrgDepartment>)ecOrgDepartmentDao.findAllItems();
	}
	@Override
	public List findPersonConnRoleList(String curUserId, Long orgId, Long deptId, Long roleId) {
		return ecOrgPersonconnroleDao.findPersonConnRoleList(new Long(curUserId), orgId, deptId, roleId);
	}
	@Override
	public List findPersonDetailByCertificateNum(String certificateNum, String certificateTypeCd) {
		return personDetailsDao.findByCertificateNumAndCertificateTypeCd(certificateNum, certificateTypeCd);
	}
	@Override
	public void savePersonDetail(PersonDetails pd) {
		personDetailsDao.save(pd);
	}
	@Override
	public void saveEcOrgPerson(EcOrgPerson eop) {
		ecOrgPersonDao.save(eop);
	}
	@Override
	public Page findRolesBySearch(String personId, Integer pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer nativeSql = new StringBuffer("");
		nativeSql.append("select eopr.roleid,")
		.append("(select org.name from ec_org_department org where eopr.orgid = org.id) as orgname,")
		.append("(select dept.name from ec_org_department dept where eopr.deptid = dept.id) as deptname,")
		.append("(select eor.name from ec_org_role eor where eor.id = eopr.roleid) as rolename,")
		.append("(select eop.name from ec_org_person eop where eopr.autor = to_char(eop.id)) as authorname,")
		.append("eopr.state ,eopr.description,")
		.append("eopr.personid from ec_org_personconnrole eopr  where eopr.personid = ?1");
		
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), nativeSql.toString(), personId);
	}
}
