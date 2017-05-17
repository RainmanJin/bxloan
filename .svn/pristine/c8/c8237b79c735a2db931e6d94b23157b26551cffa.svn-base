package com.coamctech.bxloan.service.sysmanager.impl;

import static com.coamctech.bxloan.commons.GlobalConstants.MAXLOGERRTIME;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.EcFunctiontreenodeDao;
import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.dao.EcOrgPersonDao;
import com.coamctech.bxloan.dao.EcOrgPersonconnroleDao;
import com.coamctech.bxloan.dao.EcOrgRoleDao;
import com.coamctech.bxloan.dao.LogonInfoDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.entity.EcOrgPersonconnroleId;
import com.coamctech.bxloan.entity.EcOrgRole;
import com.coamctech.bxloan.entity.LogonInfo;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.exceptions.NoAuthorityException;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.google.common.collect.Lists;

@Transactional
@Service("ecOrgPersonService")
public class EcOrgPersonServiceImpl implements EcOrgPersonService {
	@Autowired
	DynamicQuery dynamicQuery;
	@Autowired
	EcOrgPersonDao ecOrgPersonDao;
	@Autowired
	EcOrgRoleDao ecOrgRoleDao;
	@Autowired
	EcOrgDepartmentDao ecOrgDepartmentDao;
	@Autowired
	EcFunctiontreenodeDao ecFunctiontreenodeDao;
	@Autowired
	private LogonInfoDao logonInfoDao;
	@Autowired
	EcOrgPersonconnroleDao ecOrgPersonconnroleDao;
	@Autowired
	private DataDict dataDict;

	public Page<EcOrgPerson> findByCondition(EcOrgPerson eop,
			Integer pageNumber, Integer pageSize) {

		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer(
				"select new EcOrgPerson(eop.usernum,eop.logname,eop.name,eod.name) from EcOrgPerson eop,EcOrgDepartment eod where eop.orgid=eod.id ");
		jpql.append("and eop.name like ?1 and eod.name like ?2 ");
		if (StringUtils.isNotEmpty(eop.getName())) {
			params.add("%" + eop.getName() + "%");
		} else {
			params.add("%" + "" + "%");
		}
		if (StringUtils.isNotEmpty(eop.getDeptName())) {
			params.add("%" + eop.getDeptName() + "%");
		} else {
			params.add("%" + "" + "%");
		}
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), params.toArray());
	}

	public Page<EcOrgPerson> findBySearch(String serach, Integer pageNumber,
			Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer(
				"select new EcOrgPerson(eop.usernum,eop.logname,eop.name,eod.name) from EcOrgPerson eop,EcOrgDepartment eod where eop.orgid=eod.id ");
		if (StringUtils.isNotEmpty(serach)) {
			jpql.append("and eop.name like ?1 or eop.usernum like ?1 ");
			params.add("%" + serach + "%");
		}
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), params.toArray());
	}

	public EcOrgPerson findPersonByUsernum(String usernum) {
		return ecOrgPersonDao.findPersonByUsernum(usernum);
	}

	@Override
	public Page<EcOrgRole> findByCondition(String usernum, Integer pageNumber,
			Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer(
				"select eor from EcOrgRole eor,EcOrgPersonconnrole eopr,EcOrgPerson eop where eor.id= eopr.id.roleid and eopr.id.personid=eop.id and eop.usernum= ?1  ");
		params.add(usernum);
		jpql.append(" order by eor.endTime");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), params.toArray());
	}

	public Page<EcOrgRole> findBySearchRole(String serach, String usernum,
			Integer pageNumber, Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer(
				"select eor from EcOrgRole eor,EcOrgPersonconnrole eopr,EcOrgPerson eop where eor.id= eopr.id.roleid and eopr.id.personid=eop.id and eop.usernum= ?1  ");
		params.add(usernum);
		if (StringUtils.isNotEmpty(serach)) {
			jpql.append("and (eor.name like ?2 or eor.roleNum like ?2) ");
			params.add("%" + serach + "%");
		}

		jpql.append(" order by eor.endTime");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), params.toArray());
	}

	public void deleteRoleByRolenum(String rolenum, String usernum) {
		ecOrgPersonconnroleDao.deleteByRolenum(rolenum, usernum);
	}

	public void saveEcOrgPersonconnrole(String rolenum, String usernum) {
		EcOrgPerson eop = ecOrgPersonDao.findUserByUsernum(usernum);
		EcOrgRole eor = ecOrgRoleDao.findByRoleNum(rolenum);
		EcOrgPersonconnroleId eoprId = new EcOrgPersonconnroleId();
		eoprId.setRoleid(eor.getId());
		eoprId.setPersonid(eop.getId());
		eoprId.setDeptid(eop.getOrgid());
		EcOrgPersonconnrole eopr = new EcOrgPersonconnrole();
		eopr.setId(eoprId);
		eopr.setOrgid(eop.getOrgid());
		eopr.setState(eor.getState());
		eopr.setDescription(eor.getDescription());
		ecOrgPersonconnroleDao.save(eopr);
	}

	public Page<EcOrgRole> findRole(String usernum, Integer pageNumber,
			Integer pageSize) {
		EcOrgPerson eop = ecOrgPersonDao.findUserByUsernum(usernum);
		List<EcOrgPersonconnrole> eoprs = ecOrgPersonconnroleDao
				.findEcOrgPersonconnroleByPersonId(eop.getId());
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer(
				"select eor from EcOrgRole eor where 1=1 ");
		if (eoprs != null && eoprs.size() > 0) {
			jpql.append("and eor.id not in (");
			for (int i = 0; i < eoprs.size() - 1; i++) {
				jpql.append("?" + (i + 1) + ",");
			}
			jpql.append("?" + eoprs.size() + ")");
			for (EcOrgPersonconnrole eopr : eoprs) {
				params.add(eopr.getId().getRoleid());
			}
		}
		jpql.append(" order by eor.endTime");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), params.toArray());
	}

	@Override
	public EcOrgPerson findByLogname(String logname) {
		return ecOrgPersonDao.findBylogname(logname);
	}

	@Override
	public void updatePersonState(EcOrgPerson ecOrgPerson) {
		int oErrtime = ecOrgPerson.getLogineortimes() == null ? 0 : ecOrgPerson
				.getLogineortimes();
		ecOrgPerson.setLogineortimes(++oErrtime);
		if (oErrtime == MAXLOGERRTIME) {
			ecOrgPerson.setState(0); // 0代表禁用
		}
		this.save(ecOrgPerson);
	}

	@Override
	public void saveLogonInfo(EcOrgPerson ecOrgPerson) {
		ecOrgPerson.setLogineortimes(0);
		this.save(ecOrgPerson);
		LogonInfo logonInfo = new LogonInfo();
		Session session = SecurityUtils.getSubject().getSession();
		logonInfo.setIp(session.getHost());
		logonInfo.setLogname(ecOrgPerson.getLogname());
		logonInfo.setLogintime(session.getStartTimestamp());
		logonInfo.setSessionid(session.getId().toString());
		logonInfo.setEvent("");
		logonInfo.setEventtype("");
		logonInfoDao.save(logonInfo);
	}

	@Override
	public void save(EcOrgPerson person) {
		ecOrgPersonDao.save(person);
	}

	@Override
	public List<EcOrgDepartment> findPermOrgsByUserId(Long id) {
		List<EcOrgDepartment> list = ecOrgDepartmentDao.findByPersonId(id);
		return list;
	}

	@Override
	public List<EcFunctiontreenode> findMenusByPermission(Long personid,Long orgid) {
		List<EcFunctiontreenode> list = ecFunctiontreenodeDao
				.findMenusByPermission(personid, orgid);
		List<EcFunctiontreenode> menuList = createFrontMenu(list,-1L);
		return menuList;
	}
	
	/**
	 * 递归遍历菜单
	 * */
	private List<EcFunctiontreenode> createFrontMenu(
			List<EcFunctiontreenode> list, Long parentid) {
		List<EcFunctiontreenode> menuList = new ArrayList<EcFunctiontreenode>();
		for (int i = 0; i < list.size(); i++) {
			EcFunctiontreenode curNode = list.get(i);
			if (curNode.getParentnodeid().equals(parentid)) {
				curNode.setChilds(createFrontMenu(list, curNode.getId()));
				menuList.add(curNode);
			}
		}
		return menuList;
	}

	@Override
	public EcOrgDepartment findEcOrgDepartmentById(Long orgid) {
		return ecOrgDepartmentDao.findById(orgid);
	}

	@Override
	public List<EcOrgPersonconnrole> findRolesByPersonId(Long personId) {
		return this.dynamicQuery.query(EcOrgPersonconnrole.class, 
				"FROM EcOrgPersonconnrole WHERE id.personid=?1", personId);
	}

	@Override
	public List<EcOrgPerson> findByEmpnum(String empnum) {
		return ecOrgPersonDao.findByEmpnum(empnum);
	}

	@Override
	public EcOrgPerson findById(Long id) {
		return ecOrgPersonDao.findById(id);
	}
	@Override
	public EcOrgPerson findByEmpnumAndLogname(String empnum, String logname) {
		return ecOrgPersonDao.findByEmpnumAndLogname(empnum, logname);

	}

	@Override
	public DataAuthorityType getCustomerType(Long orgId, Long personId)
			throws NoAuthorityException {
		EcOrgDepartment ecOrg = this.findEcOrgDepartmentById(orgId);
		if (ecOrg == null ||StringUtils.isBlank(ecOrg.getOrgAttr())) {
			throw new NoAuthorityException("没有查询权限！");
		}
		if (ecOrg.getOrgAttr().equals(dataDict.getCodeVal("OrgType", "S0"))) {// 总部
			return DataAuthorityType.HeadOffice;
		}
		if (isNotLoanCompany(personId,orgId)) {// 小贷公司
			return DataAuthorityType.LoanCompany;
		}
		/*if (isNotCustomerManager(personId)) {// 客户经理
			return DataAuthorityType.CustomerManager;
		}*/
		return DataAuthorityType.CustomerManager;
	}

	/**
	 * 是否是小贷公司
	 * @author:gph
	 * @createTime:2015年5月22日
	 * @param personId 用户id
	 * @param orgId		机构id
	 * @return true:是，false:不是
	 */
	public boolean isNotLoanCompany(Long personId,Long orgId) {
		StringBuffer sb = new StringBuffer();
		List<Object> params = Lists.newArrayList();
		
		sb.append("select count(1)  from person_privilege_level ppl, ec_org_person ec ");
		sb.append("where ec.id = ppl.personid and ppl.privilege_level =?1 ");
		sb.append("and ppl.state = '1' ");
		sb.append("and ppl.personid =?2 ");
		sb.append("and ppl.orgid =?3");
		//封装查询参数
		params.add(dataDict.getCodeVal("privilegeLevelCd", "S2"));
		params.add(personId);//当前登录人
		params.add(orgId);//当前登录人机构
		
		//返回查询结果
		List<Object[]> list = dynamicQuery.nativeQuery(
				sb.toString(), params.toArray());
		Long count= Long.valueOf(String.valueOf(list.get(0)));
		return count> 0 ? true : false;
	}

	@Override
	public boolean isNotCustomerManager(Long personId) {
		return customerTypeSearch(personId,
				dataDict.getCodeVal("privilegeLevelCd", "S1")).compareTo(0L) > 0 ? true : false;
	}
	@Deprecated
	private Long customerTypeSearch(Long personId,String level){
		StringBuffer sb = new StringBuffer();
		List<Object> params = Lists.newArrayList();
		
		sb.append("select count(1)  from person_privilege_level ppl, ec_org_person ec ");
		sb.append("where ec.id = ppl.personid and ppl.privilege_level =?1 ");
		sb.append("and ppl.state = '1' ");
		sb.append("and ppl.personid =?2");
		//封装查询参数
		params.add(level);
		params.add(personId);
		//返回查询结果
		List<Object[]> list = dynamicQuery.nativeQuery(
				sb.toString(), params.toArray());
		return Long.valueOf(String.valueOf(list.get(0)));
	}

	@Override
	public List<Long> findOrgsByPersonId(Long personId) {
		StringBuffer sb = new StringBuffer();
		List<Object> params = Lists.newArrayList();

		sb.append("select distinct ppl.orgid from person_privilege_level ppl, ec_org_person ec ");
		sb.append("where ec.id = ppl.personid  and ppl.privilege_level =?1 ");
		sb.append("and ppl.state = '1' and ppl.personid =?2");

		// 封装查询参数
		params.add(dataDict.getCodeVal("privilegeLevelCd", "S2"));
		params.add(personId);
		//返回查询结果
		return dynamicQuery.nativeQuery(Long.class, sb.toString(),
				params.toArray());
	}
	@Override
	public List<Long> findDataAuthOrgIds(Long personId, Long logOrgId,
			DataAuthorityType dataAuthType) {
		List<Long> dataAuthOrgIds=Lists.newArrayList();
		if(dataAuthType==null){
			return dataAuthOrgIds;
		}
		switch (dataAuthType) {
		case HeadOffice://总部
			List<Long> list=this.findOrgsByPersonId(personId);
			if(!CollectionUtils.isEmpty(list)){
				dataAuthOrgIds.addAll(list);
			}
			break;
		default://小贷公司及客户经理
			dataAuthOrgIds.add(logOrgId);
			break;
		}
		return dataAuthOrgIds;
	}

	@Override
	public List<EcOrgDepartment> findEcOrgsInfoByIds(List<Long> orgIds) {
		StringBuffer sb = new StringBuffer();
		if (orgIds.size() <= 0) {
			return null;
		}
		sb.append("select new EcOrgDepartment(id,name,description) from EcOrgDepartment ec ");
		sb.append("where ec.state = '1' and ec.id in (");
		sb.append(StringUtils.join(orgIds, ","));
		sb.append(") order by ec.name");
		return dynamicQuery.query(EcOrgDepartment.class, sb.toString());
	}
}
