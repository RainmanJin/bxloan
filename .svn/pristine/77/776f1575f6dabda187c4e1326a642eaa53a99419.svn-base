package com.coamctech.bxloan.service.customermng.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.CustomerAccountManagentDao;
import com.coamctech.bxloan.dao.CustomerBrieflyFinaceDao;
import com.coamctech.bxloan.dao.CustomerBusinessInfoDao;
import com.coamctech.bxloan.dao.CustomerCorrelativeRelationsDao;
import com.coamctech.bxloan.dao.CustomerFamilyFriendDao;
import com.coamctech.bxloan.dao.CustomerIndividualDao;
import com.coamctech.bxloan.dao.CustomerManagerTeamDao;
import com.coamctech.bxloan.dao.CustomerPartyDao;
import com.coamctech.bxloan.dao.DocumentIndexDao;
import com.coamctech.bxloan.dao.IndustryTypeDao;
import com.coamctech.bxloan.dao.SalaBusiCustFinalcialDao;
import com.coamctech.bxloan.dao.VerificatPersonInfoDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.CustomerAccountManagent;
import com.coamctech.bxloan.entity.CustomerBrieflyFinace;
import com.coamctech.bxloan.entity.CustomerBusinessInfo;
import com.coamctech.bxloan.entity.CustomerCorrelativeRelations;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.entity.VerificatPersonInfo;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Transactional
@Service
public class UniqueCustomerServiceImpl implements UniqueCustomerService {
	
	@Autowired
	private CustomerPartyDao customerPartyDao;
	@Autowired
	private CustomerIndividualDao customerIndividualDao;
	@Autowired
	private CustomerBrieflyFinaceDao customerBrieflyFinaceDao;
	@Autowired
	private CustomerBusinessInfoDao customerBusinessInfoDao;
	@Autowired
	private CustomerAccountManagentDao customerAccountManagentDao;
	@Autowired
	private CustomerFamilyFriendDao customerFamilyFriendDao;
	@Autowired
	private DocumentIndexDao documentIndexDao;
	@Autowired
	private CustomerManagerTeamDao customerManagerTeamDao;
	@Autowired
	private SalaBusiCustFinalcialDao salaBusiCustFinalcialDao;
	@Autowired
	private CustomerCorrelativeRelationsDao customerCorrelativeRelationsDao;
	// 注入支持原生sql的查询实现类
	@Autowired
	private DynamicQuery dynamicQuery;
	// 注入查找行业类型的自定义标签的DAO
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private IndustryTypeDao industryTypeDao;
    
	@Autowired
	private VerificatPersonInfoDao verificatPersonInfoDao;
	
	@Override
	public void saveIndividual(Individual individual) {
		customerIndividualDao.save(individual);
		customerPartyDao.updatePartyName(individual.getPartyId(), this.getDBTime(), individual.getCustomerName());
	}

	@Override
	public Individual getIndividual(String partyId) {
		return customerIndividualDao.findByPartyId(Long.valueOf(partyId));
	}

	@Override
	public Long countIndividual() {
		return customerIndividualDao.count();
	}

	@Override
	public Boolean existsIndividual(String partyId) {
		return customerIndividualDao.exists(Long.valueOf(partyId));
	}

	@Override
	public void deleteIndividual(String partyId) {
		customerIndividualDao.delete(Long.valueOf(partyId));
		this.updateCustomerUpdateSysTime(partyId);
	}

	@Override
	public Page<Individual> pagingQueryByCustomerNameForIndividual(String customerName, Integer pageNumber, Integer pageSize) {
		return customerIndividualDao.pagingQueryByCustomerName(customerName, new PageRequest(pageNumber - 1, pageSize));
	}

	@Override
	public Individual findIndividualByCustomerName(String CustomerName) {
		return customerIndividualDao.findByCustomerName(CustomerName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page findIndividualBySearch(Integer pageNumber, Integer pageSize, List<Object> params) {
		List<Object> paramsVar = new ArrayList<Object>();
		String sql = "SELECT aro.* FROM (" 
				+ "SELECT "
				+ "  p.party_id,p.customer_num,p.party_name,p.party_type_cd,p.certificate_type_cd,p.certificate_num,"
				+ "  CASE WHEN p.party_type_cd = '1' THEN "
				+ "  (SELECT co.states "
				+ "     FROM corporation_customer co "
				+ "    WHERE co.party_id = p.party_id) "
				+ "  ELSE  (SELECT indiv.status "
				+ "  FROM individual indiv "
				+ "  WHERE indiv.party_id = p.party_id) END AS status,"
				+ " (SELECT eop.name "
				+ " FROM ec_org_person eop "
				+ " WHERE eop.id= "
				+ "  (SELECT cmt.user_num FROM customer_manager_team cmt WHERE cmt.party_id= p.party_id and cmt.manager_type='01')  "
				+ " )"
				+ "  cust_manager_name,"
				+ "  CASE WHEN  p.party_type_cd = '1' "
				+ "  THEN "
				+ "  (SELECT co.create_date FROM corporation_customer co  WHERE co.party_id = p.party_id) "
				+ "  ELSE "
				+ "  (SELECT indiv.create_date  FROM individual indiv  WHERE indiv.party_id = p.party_id) "
				+ "  END AS create_date, "
				+ "  CASE WHEN  p.party_type_cd = '1' "
				+ "  THEN "
				+ "  (SELECT co.mark_type FROM corporation_customer co WHERE co.party_id = p.party_id) "
				+ "  ELSE "
				+ "  (SELECT indiv.mark_type FROM individual indiv  WHERE indiv.party_id = p.party_id) "
				+ "  END AS mark_type, "
				+ "  p.sys_update_time, "
				+ " (SELECT cmt.manager_type "
				+ " FROM customer_manager_team cmt "
				+ " WHERE cmt.party_id = p.party_id AND cmt.user_num= ?10)"
				+ "  cust_manager_id, p.customer_source "
				+ "  FROM Party p  "
				+ "  WHERE EXISTS "
				+ "  (SELECT 1 FROM customer_manager_team cmt WHERE cmt.party_id = p.party_id AND "
				+ "    (cmt.user_num = ?1 OR cmt.dept_cd IN "
				+ "      (SELECT l1.orgid FROM person_privilege_level l1 WHERE l1.personid = ?2 AND l1.privilege_level = '1' AND l1.state = 1) OR "
				+ "      (cmt.org_cd IN (SELECT d.id FROM ec_org_department d  START WITH d.id IN "
				+ "       (SELECT l2.orgid FROM person_privilege_level l2 WHERE l2.personid = ?3 AND l2.privilege_level = '2' AND l2.state = 1) "
				+ "         CONNECT BY prior d.id = d.parentdepartmentid)))) AND p.party_type_cd != '3' ";
		sql += "AND p.party_type_cd in ('1','2') ";// 邦易贷前期只做个人客户
		StringBuffer nativeSql = new StringBuffer(sql);
		if (!params.isEmpty()) {
			paramsVar.add(params.get(4));
			paramsVar.add(params.get(4));
			paramsVar.add(params.get(4));
			nativeSql.append(" AND (p.party_name LIKE ?4 OR ?5 is null) ");
			paramsVar.add("%" + params.get(0) + "%");
			paramsVar.add(params.get(0));
			// nativeSql.append("and (mark_type like ?2 or mark_type = null)");
			// paramsVar.add("%" + params.get(1) + "%");
			nativeSql.append(" AND (p.certificate_type_cd LIKE ?6 OR ?7 is null) ");
			paramsVar.add("%" + params.get(2) + "%");
			paramsVar.add(params.get(2));
			nativeSql.append(" AND (p.certificate_num LIKE ?8 OR ?9 is null)  ");
			paramsVar.add("%" + params.get(3) + "%");
			paramsVar.add(params.get(3));
			paramsVar.add(params.get(4));
			nativeSql.append(" AND (p.party_type_cd = ?11 OR ?12 is null) ");
			paramsVar.add(params.get(5));
			paramsVar.add(params.get(5));
			
		}
		// jpql.append("order by i.customerName");
		nativeSql.append(" ) aro WHERE aro.cust_manager_id is not null order by aro.sys_update_time desc nulls last");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), nativeSql.toString(), paramsVar.toArray());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page findIndividualQuery(Integer pageNumber, Integer pageSize, List<Object> params) {
		List<Object> paramsVar = new ArrayList<Object>();
		String sql = "SELECT aro.* FROM (" 
				+ "SELECT "
				+ "  p.party_id,p.customer_num,p.party_name,p.party_type_cd,p.certificate_type_cd,p.certificate_num,"
				+ "  CASE WHEN p.party_type_cd = '1' THEN "
				+ "  (SELECT co.states "
				+ "     FROM corporation_customer co "
				+ "    WHERE co.party_id = p.party_id) "
				+ "  ELSE  (SELECT indiv.status "
				+ "  FROM individual indiv "
				+ "  WHERE indiv.party_id = p.party_id) END AS status,"
				+ " (SELECT eop.name "
				+ " FROM ec_org_person eop "
				+ " WHERE eop.id= "
				+ "  (SELECT cmt.user_num FROM customer_manager_team cmt WHERE cmt.party_id= p.party_id and cmt.manager_type='01')  "
				+ " )"
				+ "  cust_manager_name,"
				+ "  CASE WHEN  p.party_type_cd = '1' "
				+ "  THEN "
				+ "  (SELECT co.create_date FROM corporation_customer co  WHERE co.party_id = p.party_id) "
				+ "  ELSE "
				+ "  (SELECT indiv.create_date  FROM individual indiv  WHERE indiv.party_id = p.party_id) "
				+ "  END AS create_date, "
				+ "  CASE WHEN  p.party_type_cd = '1' "
				+ "  THEN "
				+ "  (SELECT co.mark_type FROM corporation_customer co WHERE co.party_id = p.party_id) "
				+ "  ELSE "
				+ "  (SELECT indiv.mark_type FROM individual indiv  WHERE indiv.party_id = p.party_id) "
				+ "  END AS mark_type, "
				+ "  p.sys_update_time, "
				+ " (SELECT cmt.manager_type "
				+ " FROM customer_manager_team cmt "
				+ " WHERE cmt.party_id = p.party_id AND cmt.user_num= ?10)"
				+ "  cust_manager_id, p.customer_source "
				+ "  FROM Party p  "
				+ "  WHERE EXISTS "
				+ "  (SELECT 1 FROM customer_manager_team cmt WHERE cmt.party_id = p.party_id AND "
				+ "    (cmt.user_num = ?1 OR cmt.dept_cd IN " 
				+ "      (SELECT l1.orgid FROM person_privilege_level l1 WHERE l1.personid = ?2 AND l1.privilege_level = '1' AND l1.state = 1) OR "
				+ "      (cmt.org_cd IN (?3) ))) AND p.party_type_cd != '3' ";
		sql += "AND p.party_type_cd in ('1','2') ";// 邦易贷前期只做个人客户
		StringBuffer nativeSql = new StringBuffer(sql);
		
		
		if (!params.isEmpty()) {
			paramsVar.add(params.get(4));
			paramsVar.add(params.get(4));
			//当前登录人拥有所有机构id集合
			List<Long> dataAuthOrgIds = (List<Long>) params.get(11);
			/*StringBuffer sbf = new StringBuffer();
			for(Long orgId : dataAuthOrgIds){
				if(StringUtils.isBlank(sbf)){
					sbf.append(orgId);
				}else{
					sbf.append(",");
					sbf.append(orgId);
				}
			}*/
			paramsVar.add(dataAuthOrgIds);
			nativeSql.append(" AND (p.party_name LIKE ?4 OR ?5 is null) ");
			paramsVar.add("%" + params.get(0) + "%");
			paramsVar.add(params.get(0));
			// nativeSql.append("and (mark_type like ?2 or mark_type = null)");
			// paramsVar.add("%" + params.get(1) + "%");
			nativeSql.append(" AND (p.certificate_type_cd LIKE ?6 OR ?7 is null) ");
			paramsVar.add("%" + params.get(2) + "%");
			paramsVar.add(params.get(2));
			nativeSql.append(" AND (p.certificate_num LIKE ?8 OR ?9 is null)  ");
			paramsVar.add("%" + params.get(3) + "%");
			paramsVar.add(params.get(3));
			paramsVar.add(params.get(4));
			nativeSql.append(" AND (p.party_type_cd = ?11 OR ?12 is null) ");
			paramsVar.add(params.get(5));
			paramsVar.add(params.get(5));
			
			String orgId = (String) params.get(6);
			String customerCd = (String) params.get(7);
			String custMng = (String) params.get(8);
			String employmentType = (String) params.get(9);
			String customerStatus = (String) params.get(10);
			
			int index = 13;
			
			if(StringUtils.isNotBlank(orgId)){
//				nativeSql.append(" AND (p.party_id in ("
//						+ "SELECT cmt.party_id "
//						+ "FROM customer_manager_team cmt,ec_org_person ep "
//						+ "WHERE cmt.user_num = ep.id "
//						+ "AND ep.orgid = ?"+(index++)+" ))");
				//modify by mz 20150702 start
				nativeSql.append(" AND (p.party_id in ("
				+ "SELECT cmt.party_id "
				+ "FROM customer_manager_team cmt,ec_org_person ep "
				+ "WHERE cmt.user_num = ep.id "
				+ "AND cmt.org_cd = ?"+(index++)+" ))");
				//modify by mz 20150702 end
				paramsVar.add(orgId);
			}
			if(StringUtils.isNotBlank(customerCd)){
				nativeSql.append(" AND (p.customer_num like ?"+(index++)+" ) ");
				paramsVar.add("%" + customerCd + "%");
			}
			if(StringUtils.isNotBlank(custMng)){
				nativeSql.append(" AND (p.party_id in ("
						+ "SELECT cmt.party_id "
						+ "FROM customer_manager_team cmt,ec_org_person ep "
						+ "WHERE cmt.user_num = ep.id "
						+ "AND cmt.manager_type = '01' "
						+ "AND ep.name like ?"+(index++)+" ))");
				paramsVar.add("%" + custMng + "%");
			}
			if(StringUtils.isNotBlank(employmentType)){
				nativeSql.append(" AND (p.party_id in ("
						+ "SELECT p.party_id"
						+ " FROM party p,individual ii"
						+ " WHERE p.party_id = ii.party_id"
						+ " AND ii.employment_type = ?"+(index++)+" ))");
				paramsVar.add(employmentType);
			}
			if(StringUtils.isNotBlank(customerStatus)){
				nativeSql.append(" AND (p.party_id in ("
						+ "CASE WHEN p.party_type_cd = '1' THEN (SELECT p.party_id FROM corporation_customer co  WHERE co.party_id = p.party_id AND co.states = ?"+(index++)+")"
						+ " ELSE (SELECT p.party_id  FROM individual indiv  WHERE indiv.party_id = p.party_id AND indiv.status = ?"+(index++)+")"
						+ " END )) ");
				paramsVar.add(customerStatus);
				paramsVar.add(customerStatus);
			}
			
		}
		// jpql.append("order by i.customerName");
		DataAuthorityType dataAuthType = (DataAuthorityType) params.get(12);
		switch (dataAuthType) {
		case CustomerManager://客户经理角色
			nativeSql.append(" ) aro WHERE aro.cust_manager_id is not null ");
			break;
		default://统计查询权限
			nativeSql.append(" ) aro ");
			break;
		}
		nativeSql.append(" order by aro.sys_update_time desc nulls last");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), nativeSql.toString(), paramsVar.toArray());
	}
	
	@Override
	public Long findCustomerListCount(Long userId, Long orgId, Long queryCount) {
		String sql = "SELECT count(*) FROM (" 
				+ "SELECT "
				+ "  p.party_id,p.customer_num,p.party_name,p.party_type_cd,p.certificate_type_cd,p.certificate_num,"
				+ "  CASE WHEN p.party_type_cd = '1' THEN "
				+ "  (SELECT co.states "
				+ "     FROM corporation_customer co "
				+ "    WHERE co.party_id = p.party_id) "
				+ "  ELSE  (SELECT indiv.status "
				+ "  FROM individual indiv "
				+ "  WHERE indiv.party_id = p.party_id) END AS status,"
				+ " (SELECT eop.name "
				+ " FROM ec_org_person eop "
				+ " WHERE eop.id= "
				+ "  (SELECT cmt.user_num FROM customer_manager_team cmt WHERE cmt.party_id= p.party_id and cmt.manager_type='01')  "
				+ " )"
				+ "  cust_manager_name,"
				+ "  CASE WHEN  p.party_type_cd = '1' "
				+ "  THEN "
				+ "  (SELECT co.create_date FROM corporation_customer co  WHERE co.party_id = p.party_id) "
				+ "  ELSE "
				+ "  (SELECT indiv.create_date  FROM individual indiv  WHERE indiv.party_id = p.party_id) "
				+ "  END AS create_date, "
				+ "  CASE WHEN  p.party_type_cd = '1' "
				+ "  THEN "
				+ "  (SELECT co.mark_type FROM corporation_customer co WHERE co.party_id = p.party_id) "
				+ "  ELSE "
				+ "  (SELECT indiv.mark_type FROM individual indiv  WHERE indiv.party_id = p.party_id) "
				+ "  END AS mark_type, "
				+ "  p.sys_update_time, "
				+ " (SELECT cmt.manager_type "
				+ " FROM customer_manager_team cmt "
				+ " WHERE cmt.party_id = p.party_id AND cmt.user_num= ?4)"
				+ "  cust_manager_id "
				+ "  FROM Party p  "
				+ "  WHERE EXISTS "
				+ "  (SELECT 1 FROM customer_manager_team cmt WHERE cmt.party_id = p.party_id AND "
				+ "    (cmt.user_num = ?1 OR cmt.dept_cd IN "
				+ "      (SELECT l1.orgid FROM person_privilege_level l1 WHERE l1.personid = ?2 AND l1.privilege_level = '1' AND l1.state = 1) OR "
				+ "      (cmt.org_cd IN (SELECT d.id FROM ec_org_department d  START WITH d.id IN "
				+ "       (SELECT l2.orgid FROM person_privilege_level l2 WHERE l2.personid = ?3 AND l2.privilege_level = '2' AND l2.state = 1) "
				+ "         CONNECT BY prior d.id = d.parentdepartmentid)))) AND p.party_type_cd != '3' ";
		sql += "AND p.party_type_cd in ('1','2') ";// 邦易贷前期只做个人客户
		StringBuffer nativeSql = new StringBuffer(sql);
		nativeSql.append(" ) aro WHERE aro.cust_manager_id is not null order by aro.sys_update_time desc nulls last");
		List<Object[]> list = dynamicQuery.nativeQuery(nativeSql.toString(), userId,userId,userId,userId);
		if(CollectionUtils.isNotEmpty(list)){
			Long count = CommonHelper.toLong(list.get(0));
			return count;
		}else{
			return null;
		}
	}

	
	
	@Override
	public String findManagerNameForIndividual(String customerNum) {
		String navtiveSql = "select ec.name from ec_org_person ec where ec.id = "
				+ "(select c.user_num from customer_manager_team c where c.manager_type='01' and c.customer_num = ?1 )";
		List<Object[]> list = dynamicQuery.nativeQuery(navtiveSql, customerNum);
		Object obj = list.get(0);
		String name = obj.toString();
		return name;
	}

	// 客户生效
	@Override
	public void updateIndividualStatus(String partyId) {
		customerIndividualDao.updateIndividualStatus(Long.valueOf(partyId), this.getDBTime());
		customerPartyDao.updatePartyStatus(Long.valueOf(partyId), this.getDBTime());
	}

	// 客户表相关方法
	@Override
	public void saveParty(Party party) {
		customerPartyDao.save(party);
	}

	@Override
	public Party findPartyByPartyId(String partyId) {
		return customerPartyDao.findByPartyId(Long.valueOf(partyId));
	}
	@Override
	public Party findParty(long partyId) {
		return this.customerPartyDao.findByPartyId(partyId);
	}
	@Override
	public Long countParty() {
		return customerPartyDao.count();
	}

	@Override
	public Boolean existsParty(String partyId) {
		return customerPartyDao.exists(new Long(partyId));
	}

	@Override
	public void deleteParty(String partyId) {
		customerPartyDao.delete(new Long(partyId));
	}

	@Override
	public Party findPartyByCertificateNum(String certificateNum) {
		return customerPartyDao.findByCertificateNum(certificateNum);
	}

	@Override
	public Party findPartyBycustomerNum(String customerNum) {
		return customerPartyDao.findByCustomerNum(customerNum);
	}

	@Override
	public Party findPartyByCertificateNumAndCertificateType(String addCertificateType, String addCertificateNum) {
		return customerPartyDao.findByCertificateNumAndCertificateType(addCertificateType, addCertificateNum);
	}

	// 文档管理
	@Override
	public void saveDocumentIndex(DocumentIndex documentIndex) {
		documentIndexDao.save(documentIndex);
	}

	@Override
	public DocumentIndex findDocumentIndexByDocumentId(String documentId) {
		return documentIndexDao.findByDocumentId(Long.valueOf(documentId));
	}

	@Override
	public Long countDocumentIndex() {
		return documentIndexDao.count();
	}

	@Override
	public Boolean existsDocumentIndex(String documentId) {
		return documentIndexDao.exists(Long.valueOf(documentId));
	}

	@Override
	public void deleteDocumentIndex(String documentId) {
		documentIndexDao.delete(Long.valueOf(documentId));
	}

	@Override
	public Page findDocumentIndexBySearch(int pageNumber, Integer pageSize, List<Object> params) {
		String nativesql = "select t.party_id,t.document_name,t.cust_doc_type,t.document_type,t.customer_num,co.name,t.create_date_time,t.create_type_cd,t.document_id  "
				+ " from DOCUMENT_INDEX t left JOIN EC_ORG_PERSON co ON co.id = t.create_user_num "
				+ " where t.party_id = ?1  ";
		List<Object> paramsVar = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(nativesql);
		paramsVar.add(params.get(0));
		sql.append(" and t.document_type in " + String.valueOf(params.get(2)));
		if (!params.isEmpty()) {
			sql.append(" and (t.document_name like ?2 or ?3 is null) ");
			sql.append("and t.status = 1 ");
			paramsVar.add("%" + params.get(1) + "%");
			paramsVar.add(params.get(1));
			
			if(StringUtils.isNotBlank((String) params.get(3))){
				List<String> custDocValues = documentService.findCustDocTypeNames((String)(params.get(3)));
				if(CollectionUtils.isNotEmpty(custDocValues)){
				sql.append(" and t.cust_doc_type in (?4) ");
				paramsVar.add(custDocValues);}else{
					sql.append(" and t.cust_doc_type in ('') ");
				}
			}
			
			if (params.size() > 4) {
				sql.append("and ( t.biz_id = "+ params.get(4) +") ");
				sql.append("order by t.create_date_time desc ");
			} else {
				sql.append("order by t.document_id, t.create_date_time desc ");
			}
		}
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql.toString(), paramsVar.toArray());
	}

	// 账户管理
	@Override
	public void saveAccountManagent(CustomerAccountManagent customerAccountManagent) {
		customerAccountManagentDao.save(customerAccountManagent);
		this.updateCustomerUpdateSysTime(String.valueOf(customerAccountManagent.getPartyId()));
	}


	@Override
	public CustomerAccountManagent findAccountManagentByAccountId(String accountId) {
		return customerAccountManagentDao.findByAccountId(new BigDecimal(Long.valueOf(accountId)));
	}

	@Override
	public Long countAccountManagent() {
		return customerAccountManagentDao.count();
	}

	@Override
	public Boolean existsAccountManagent(String accoundId) {
		return customerAccountManagentDao.exists(new BigDecimal(Integer.valueOf(accoundId)));
	}

	@Override
	public void deleteAccountManagent(String accountId) {
		customerAccountManagentDao.delete(new BigDecimal(Integer.valueOf(accountId)));
	}

	@Override
	public Page findAccountManagentBySearch(String partyId, int pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer jpql = new StringBuffer("select a from CustomerAccountManagent a where a.partyId = ?1 ");
		params.add(new BigDecimal(Long.valueOf(partyId)));
		jpql.append("order by a.accountId");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize), jpql.toString(), params.toArray());
	}

	@Override
	public CustomerAccountManagent findAccountManagentByPartyIdAndAccountNum(String partyId, String accountNum) {
		return customerAccountManagentDao.findByPartyIdAndAccountNum(new BigDecimal(Long.valueOf(partyId)), accountNum);
	}

	@Override
	public List<CustomerAccountManagent> findAccountManagentListByPartyId(String partyId) {
		return customerAccountManagentDao.findListByPartyId(new BigDecimal(Long.valueOf(partyId)));
	}

	// 联系人管理
	@Override
	public Long saveFamilyFriend(FamilyFriend familyfriend) {
		customerFamilyFriendDao.save(familyfriend);
		this.updateCustomerUpdateSysTime(String.valueOf(familyfriend.getPartyId()));
		return familyfriend.getCorrelativeRelationsId();
	}

	@Override
	public FamilyFriend findFamilyFriendByCorrelativeRelationsId(Long correlativeRelationsId) {
		return customerFamilyFriendDao.findByCorrelativeRelationsId(correlativeRelationsId);
	}

	@Override
	public Long countFamilyFriend() {
		return customerFamilyFriendDao.count();
	}

	@Override
	public Boolean existsFamilyFriend(String correlativeRelationsId) {
		return customerFamilyFriendDao.exists(Long.valueOf(correlativeRelationsId));
	}

	@Override
	public void deleteFamilyFriend(String correlativeRelationsId) {
		customerFamilyFriendDao.delete(Long.valueOf(correlativeRelationsId));
	}

	@Override
	public Page findFamilyFriendBySearch(String partyId, int pageNumber, Integer pageSize, List<Object> params) {
		String sql = "select t.correlative_relations_id,t.correlative_customer_name,ff.family_friend_type,ff.certificate_type_cd,ff.certificate_cd,ff.census_register,ff.telphone,ff.company_tel,ff.company,ff.company_address from "
				+ "(select * from CUSTOMER_CORRELATIVE_RELATIONS  cc where cc.party_id= ?1 ) t ,  FAMILY_FRIEND ff "
				+ "where ff.correlative_relations_id= t.correlative_relations_id";
		params.add(partyId);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql, params.toArray());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page findFamilyFriendPageByPartyId(String partyId, int pageNumber, Integer pageSize, List<Object> params) {
		//modify by wangyawei 20150428 start 区分小贷和微贷用户，特殊处理
		params.add(Long.valueOf(partyId));
		Page page = null;
		String sql = null;
		Party party = customerPartyDao.findByPartyId(Long.valueOf(partyId));
		String customerSource = party.getCustomerSource();
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize);
		
		if(StringUtils.isNotEmpty(customerSource) && StringUtils.isNotBlank(customerSource) && 
				(StringUtils.equals(customerSource, "3") || StringUtils.equals(customerSource, "2"))){
			sql = "select f from FamilyFriend f where f.partyId = ?1";
			page = dynamicQuery.query(pageable, sql.toString(), params);
		} else {//若是小贷用户，取值和微贷用户太不一样
			sql = this.buildFindFamilyFriendByPartyIdSql();
			Page transferPage = dynamicQuery.nativeQuery(pageable, sql.toString(), params);
			//转换
			List<FamilyFriend> queryList = this.findFamilyFriendPage(transferPage);
			page = new PageImpl(queryList, pageable, transferPage.getTotalElements());
		}
		//modify by wangyawei 20150428 end
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FamilyFriend> findFamilyFriendListByPartyId(String partyId) {
		//modify by wangyawei 20150428 start 区分小贷和微贷用户，特殊处理
		List<FamilyFriend> queryList = null;
		Party party = customerPartyDao.findByPartyId(Long.valueOf(partyId));
		String customerSource = party.getCustomerSource();
		
		if(StringUtils.isNotEmpty(customerSource) && StringUtils.isNotBlank(customerSource) && 
				(StringUtils.equals(customerSource, "3") || StringUtils.equals(customerSource, "2"))){
			queryList = customerFamilyFriendDao.findListByPartyId(Long.valueOf(partyId));
		} else {//若是小贷用户，取值和微贷用户太不一样
			String sql = this.buildFindFamilyFriendByPartyIdSql(); //组装根据partyId查询小贷用户的重要联系人信息sql
			//组装查询参数
			List<Object> params = new ArrayList<Object>();
			params.add(Long.valueOf(partyId));
			//将object数组集合转换为对象集合
			List tansferList = dynamicQuery.nativeQuery(sql, params);
			queryList = Lists.transform(tansferList, new Function<Object[], FamilyFriend>() {
				@Override
				public FamilyFriend apply(Object[] objs) {
					return new FamilyFriend(objs);
				}
			});
		}
		//modify by wangyawei 20150428 end
		return queryList;
	}

	// 简要财产信息
	@Override
	public void saveCustomerBrieflyFinace(CustomerBrieflyFinace customerBrieflyFinace) {
		customerBrieflyFinaceDao.save(customerBrieflyFinace);
		this.updateCustomerUpdateSysTime(String.valueOf(customerBrieflyFinace.getPartyId()));
	}

	@Override
	public CustomerBrieflyFinace findFinaceByPartyId(Long partyId) {
		return customerBrieflyFinaceDao.findByPartyId(partyId);
	}

	// 自定义标签获取行业分类
	@Override
	public List<IndustryType> getAllIndustry() {
		return (List<IndustryType>) industryTypeDao.findAllItems();
	}

	@Override
	public Individual findByCustomerNum(String customerNum) {
		Individual individual = customerIndividualDao.findByCustomerNum(customerNum);
		return individual;
	}

	// 设置关联关系
	@Override
	public void saveManagerTeam(CustomerManagerTeam customerManagerTeam) {
		customerManagerTeamDao.save(customerManagerTeam);
	}

	@Override
	public List<DocumentIndex> findDocumentIndexByPartyId(String partyId) {
		return documentIndexDao.findByPartyId(Long.valueOf(partyId));
	}

	@Override
	public Timestamp getDBTime() {
		StringBuffer sql = new StringBuffer("select sysdate from dual");
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString());
		if (list != null && list.size() > 0) {
			Object o = list.get(0);
			if (o instanceof Timestamp) {
				return (Timestamp) o;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Long saveCorrelativeRelation(CustomerCorrelativeRelations customerCorrelativeRelations) {
		return customerCorrelativeRelationsDao.save(customerCorrelativeRelations).getCorrelativeRelationsId();
	}

	@Override
	public void deleteCorrelativeRelation(Long correlativeRelationsId) {
		customerCorrelativeRelationsDao.delete(correlativeRelationsId);
	}

	@Override
	public CustomerCorrelativeRelations findCorrelativeRelationById(Long correlativeRelationsId) {
		return customerCorrelativeRelationsDao.findByCorrelativeRelationsId(correlativeRelationsId);
	}
	@Override
	public CustomerCorrelativeRelations findCorrelativeRelationByIdAndPartyId(Long correlativeRelationsId, Long partyId) {
		return customerCorrelativeRelationsDao.findByCorrelativeRelationsIdAndPartyId(correlativeRelationsId, partyId);
	}
	@Override
	public CustomerBusinessInfo findBussinessInfoByPartyId(Long partyId) {
		List<CustomerBusinessInfo> list = customerBusinessInfoDao.findByPartyId(partyId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveCustomerBusinessInfo(CustomerBusinessInfo customerBusinessInfo) {
		customerBusinessInfoDao.save(customerBusinessInfo);
	}

	@Override
	public void updateCustomerUpdateSysTime(String customerId) {
		String jpql = "update Individual iv set iv.sysUpdateTime = ?1 where iv.partyId = ?2";
		dynamicQuery.executeUpdate(jpql, this.getDBTime(), Long.valueOf(customerId));
	}

	@Override
	public Page findProjectFinalcials(Integer pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT sbcf.id, pa.project_no, sbcf.assets_total_amt, sbcf.liabilities_total_amt, to_char(sbcf.create_dtate,'yyyy-MM-dd'), pa.project_id FROM sala_busi_cust_finalcial sbcf ");
		sql.append(" JOIN project_application pa ON pa.project_Id = sbcf.project_Id ");
		sql.append(" WHERE pa.party_Id = ?1");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql.toString(), params.toArray());
	}

	@Override
	public SalaBusiCustFinalcial getOneSalaCustFinace(String id) {
		return salaBusiCustFinalcialDao.findOne(new Long(id));
	}

	@Override
	public String getEmploymentTypeByPartyId(Long partyId) {
		String jpql = "SELECT iv.employmentType FROM Individual iv WHERE iv.partyId = ?1 ";
		return dynamicQuery.querySingleResult(String.class, jpql, partyId);
	}

	@Override
	public boolean findIndividualStatus(Long partyId) {
		String jpql = "SELECT iv.status FROM Individual iv where iv.partyId = ?1";
		String status = dynamicQuery.query(String.class, jpql, partyId).get(0);
		if(StringUtils.isNotBlank(status)){
			if(status.equals(dataDict.getCodeVal("CustomerStatusCode", "S1"))){
				//未生效
				return false;
			}else if(status.equals(dataDict.getCodeVal("CustomerStatusCode", "S2"))){
				//生效
				return true;
			}
		}
		return false;
	}

	@Override
	public String findIndividualMarriageCd(Long partyId) {
		return this.customerIndividualDao.findMarriageCdByPartyId(partyId);
	}

	@Override
	public Long sqlSaveCustomerCorrelativeRelations(CustomerCorrelativeRelations relation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findNextSeqFromRelation() {
		String sql = "select max(ccr.correlative_relations_id + 1) from CUSTOMER_CORRELATIVE_RELATIONS ccr";
		return CommonHelper.toLong(dynamicQuery.nativeQuery(sql).get(0));
	}

	@Override
	public Page findFamilyFriendAgroBySearch(Integer pageNumber, Integer pageSize, String partyId) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select ff.correlative_Relations_Id, " +
				"ff.name, " +
				"ff.family_Friend_Type, " +
				"ff.age, " +
				"ff.person_Status, " +
				"ff.work_Company, " +
				"ff.monthly_Income, " +
				"ff.degreeCd, " +
				"ff.telphone," +
				"ff.party_Id " +
				" from Family_Friend ff where Family_Members='1' and ff.party_Id = ?1 ");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), jpql.toString(), Long.valueOf(partyId));
	}

	// 核实人人管理
	@Override
	public Long saveVerificatPersonInfo(VerificatPersonInfo verificatPersonInfo) {
		verificatPersonInfoDao.save(verificatPersonInfo);
		return verificatPersonInfo.getId();
	}
	
	@Override
	//public List<VerificatPersonInfo>  findVerificatPersonInfo(Integer pageNumber, Integer pageSize, String partyId) {
	public Page  findVerificatPersonInfo(Integer pageNumber, Integer pageSize, String partyId) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select ff.id, " +
				"ff.name, " +
				"ff.relation, " +
				"ff.familyaddress, " +
				"ff.telphone, " +
				"ff.remark, " +
				"ff.party_id  " +
				" from VerificatPersonInfo ff where ff.party_Id = ?1 order by update_date desc");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), jpql.toString(), Long.valueOf(partyId));
		/*List params = new ArrayList();
		StringBuffer jpql = new StringBuffer(" from VerificatPersonInfo a where a.partyId = ?1 ");
		params.add(new BigDecimal(Long.valueOf(partyId)));
		jpql.append("order by a.accountId");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize), jpql.toString(), params.toArray());*/
		
		//return verificatPersonInfoDao.findListByPartyId(new Long(partyId));
		
	}
	
	@Override
	public VerificatPersonInfo findVerificatPersonInfoById(Long id) {
		return verificatPersonInfoDao.findVerificatPersonInfoById(id);
	}
	
	@Override
	public void deleteVerificatPersonInfo(String id) {
		verificatPersonInfoDao.delete(Long.valueOf(id));
	}
	
	/** 
	 * 组装根据partyId查询小贷用户的重要联系人信息sql
	 * 
	 * @param partyId
	 * @return
	 */
	public String buildFindFamilyFriendByPartyIdSql(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select cc.correlative_relations_id, cc.correlative_customer_name, ")
			.append(" cc.correlative_customer_type_cd, cc.certificate_type_cd, cc.certificate_cd, ")
			.append(" ff.census_register, cc.mobile_num, ff.company_tel,  ")
			.append(" ff.work_company, ff.company_address, cc.party_id, ff.relations_type ")
			.append(" from customer_correlative_relations cc, family_friend ff, party p ")
			.append(" where ff.correlative_relations_id = cc.correlative_relations_id ")
			.append(" and p.party_id = cc.party_id ")
			.append(" and (p.customer_source is null or p.customer_source = 1) ")
			.append(" and cc.party_id = ?1 ");
		return sql.toString();
	}
	
	/**
	 * 根据Page对象获取重要联系人信息列表
	 * 
	 * @param page 
	 * @return
	 */
	@Override
	public List<FamilyFriend> findFamilyFriendPage(Page<Object[]> page){
		List<FamilyFriend> payLoanInfoList = 
			Lists.transform(page.getContent(), new Function<Object[], FamilyFriend>() {
				@Override
				public FamilyFriend apply(Object[] objs) {
					return new FamilyFriend(objs);
				}
			});
		return payLoanInfoList;
	}
	
}
