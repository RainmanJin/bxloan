package com.coamctech.bxloan.service.customermng.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.CustomerPartyDao;
import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.dao.UnCustTabDao;
import com.coamctech.bxloan.dao.UniteGuNegoDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.UnCustTab;
import com.coamctech.bxloan.entity.UniteGuNego;
import com.coamctech.bxloan.service.customermng.UniteGuCustomerService;
import com.coamctech.bxloan.service.exceptions.ServiceException;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Transactional
@Service
public class UniteGuCustomerServiceImpl implements UniteGuCustomerService{
	private final String RELATIONS_TYPE="RelationsType";
	private final String CERTIFICATE_TYPE="CertificateType";
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private UniteGuNegoDao uniteGuNegoDao;
	@Autowired
	private UnCustTabDao unCustTabDao;
	@Autowired
	private EcOrgDepartmentDao ecOrgDepartmentDao;
	@Autowired
	private CustomerPartyDao customerPartyDao;
	
	@Override
	public List<UniteGuNego> findUniteCustomerByType(String unType){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select new UniteGuNego(id,unGuId,unGroupName) from UniteGuNego u ");
		sb.append("where u.unStatus='2' and u.unType='" + StringUtils.join(unType) + "' ");
		
		return dynamicQuery.query(UniteGuNego.class, sb.toString());
	};
	@Override
	public List<UniteGuNego> findUgnCustListByPartyId(Long partyId,
			String unType, int minMemberSize, int maxMemberSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ugn.id,ugn.UN_GU_ID,ugn.UN_GROUP_NAME from unite_gu_nego ugn");
		sb.append(" where ugn.id in (select DISTINCT uct.unite_gu_nego_id from un_cust_tab uct where uct.party_id = ?1)");
		sb.append(" and ugn.un_status=?2 and ugn.un_type=?3 ");
		List<Object> params=Lists.newArrayList();
		params.add(partyId);
		params.add(dataDict.getCodeVal("UnStatus", "S2"));
		params.add(unType);
		int paramsIndex=4;
		if(minMemberSize>0){
			sb.append(" AND TO_NUMBER(ugn.CUSTOMER_QUANTITY) >= ?").append(paramsIndex++);
			params.add(minMemberSize);
		}
		if(maxMemberSize>0){
			sb.append(" AND TO_NUMBER(ugn.CUSTOMER_QUANTITY) <= ?").append(paramsIndex++);
			params.add(maxMemberSize);
		}
		sb.append(" ORDER BY UGN.ID DESC");
		List<Object[]> list=dynamicQuery.nativeQuery(Object[].class, sb.toString(), params.toArray());//
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return Lists.transform(list, new Function<Object[], UniteGuNego>() {
			@Override
			public UniteGuNego apply(Object[] objs) {
				return new UniteGuNego(CommonHelper.toLong(objs[0]), CommonHelper.toStr(objs[1]), CommonHelper.toStr(objs[2]));
			}
		});
	}
	@Override
	public List<UnCustTab> findUgnMembersByUgnId(Long ugnId) {
		return this.unCustTabDao.findListByUniteGuNegoId(ugnId, "2");//生效的客户
	}
	@Override
	public List<UnCustTab> findUniteMembersByUnId(String unGuId){
		
		StringBuffer sb = new StringBuffer();
		sb.append("from UnCustTab u ");
		sb.append("where u.unGuId='" + StringUtils.join(unGuId) +"'");
		
		return dynamicQuery.query(UnCustTab.class, sb.toString());
	}
	
	@Override
	public Page<Object[]> findUniteCustomerBySearch(Integer pageNumber, Integer pageSize, Map<String, String> params) {
		StringBuffer nativeSql = new StringBuffer("");
		List<Object> sqlParams = Lists.newArrayList();
		nativeSql.append("SELECT DISTINCT a.id, ");
		nativeSql.append(" a.un_gu_id, ");
		nativeSql.append(" a.un_group_name, ");
		nativeSql.append(" a.un_type, ");
		nativeSql.append(" a.un_trust_all_amt, ");
		nativeSql.append(" a.beg_date, ");
		nativeSql.append(" a.end_date, ");
		nativeSql.append(" a.guaran_way,  ");
		nativeSql.append(" a.un_status, ");
		nativeSql.append(" a.manager_id, ");
		nativeSql.append(" a.cust_manager, ");
		nativeSql.append(" a.SEGI_MAN, ");
		nativeSql.append(" a.SEGI_MAN_ID, ");
		nativeSql.append(" a.customer_quantity, ");
		nativeSql.append(" a.sys_cd ");
		/**
		 * 2015-7-2 libingqing 修改sql
		 */
		//nativeSql.append(" FROM ec_org_person p, ");
		nativeSql.append(" FROM unite_gu_nego a ");
		nativeSql.append(" LEFT JOIN un_cust_tab b ON a.un_gu_id = b.un_gu_id WHERE 1=1 ");
		nativeSql.append(" AND a.segi_org = ?1 ");
		sqlParams.add(new Long(params.get("orgId")));//查询当前登录机构
		int paramIndex = 2;
		if(StringUtils.isNotBlank(params.get("unGuId"))){// 按协议编号查询
			nativeSql.append(" AND a.un_gu_id = ?" + paramIndex++);
			sqlParams.add(params.get("unGuId"));
		}
		if(StringUtils.isNotBlank(params.get("unGroupName"))){// 按联保体小组名称
			nativeSql.append(" AND a.un_group_name LIKE ?" + paramIndex++);
			sqlParams.add("%" + params.get("unGroupName") + "%");
		}
		if(StringUtils.isNotBlank(params.get("customerNum"))){// 按联保体成员客户编号
			nativeSql.append(" AND b.customer_num = ?" + paramIndex++);
			sqlParams.add(params.get("customerNum"));
		}
		if(StringUtils.isNotBlank(params.get("customerName"))){// 联保体成员名称
			nativeSql.append(" AND b.customer_name LIKE ?" + paramIndex++);
			sqlParams.add("%" + params.get("customerName") + "%");
		}
		if(StringUtils.isNotBlank(params.get("certificateTypeCd"))){ // 联保体成员证件类型
			nativeSql.append(" AND b.certificate_type_cd = ?" + paramIndex++);
			sqlParams.add(params.get("certificateTypeCd"));
		}
		if(StringUtils.isNotBlank(params.get("certificateNum"))){ // 联保体成员证件号码
			nativeSql.append(" AND b.certificate_num = ?" + paramIndex++);
			sqlParams.add(params.get("certificateNum"));
		}
		if(StringUtils.isNotBlank(params.get("unStatus"))){ // 联保体协议状态
			nativeSql.append(" AND a.un_status = ?" + paramIndex++);
			sqlParams.add(params.get("unStatus"));
		}
		if(StringUtils.isNotBlank(params.get("custManager"))){ // 客户经理
			nativeSql.append(" AND a.cust_manager LIKE ?" + paramIndex++);
			sqlParams.add("%" + params.get("custManager") + "%");
		}
		nativeSql.append(" ORDER BY a.id DESC ");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber, pageSize), nativeSql.toString(), sqlParams.toArray());
		
	}

	@Override
	public UniteGuNego findUniteCustomerById(Long id) {
		return this.uniteGuNegoDao.findOne(id);
	}

	@Override
	public UniteGuNego saveUniteCustomer(UniteGuNego ugn) {
		this.uniteGuNegoDao.save(ugn);
		return ugn;
	}

	@Override
	public void deleteUniteGuNego(Long id) {
		this.uniteGuNegoDao.delete(id);		
	}

	@Override
	public void deleteUniteCustomer(UniteGuNego ugn) {
		this.unCustTabDao.deleteByUnGuId(ugn.getUnGuId());
		this.uniteGuNegoDao.delete(ugn);
	}

	@Override
	public String getOrgNameByOrgId(String segiOrg) {
		return ecOrgDepartmentDao.findDeptNameById(Long.valueOf(segiOrg));
	}

	@Override
	public Page<Object[]> findCustManagerBySearch(Integer pageNumber, Integer pageSize,Long orgId, String custManager) {
		List<Object> params = Lists.newArrayList();
		StringBuffer nativeSql = new StringBuffer("");
		nativeSql.append("SELECT A.ID,A.NAME CUSTMENGNAME ,(select eod.name from ec_org_department eod where eod.id=C.ORGID) ORGNAME, C.ORGID,A.STATE ");
		nativeSql.append("FROM EC_ORG_PERSON A,EC_ORG_ROLE B,EC_ORG_PERSONCONNROLE C");
		nativeSql.append(" WHERE A.ID = C.PERSONID ");
		nativeSql.append(" AND  C.ROLEID = B.ID ");
		nativeSql.append(" AND  B.ROLE_NUM='R00001' ");
		nativeSql.append(" AND A.STATE = '1' ");
		nativeSql.append(" AND C.ORGID = ?1 ");
		params.add(String.valueOf(orgId));
		if (StringUtils.isNotBlank(custManager)) { // 按客户名称查询
			nativeSql.append(" AND A.NAME Like ?2 ");
			params.add("%"+custManager+"%");
		}
		nativeSql.append(" ORDER BY A.ID DESC ");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber, pageSize), nativeSql.toString(), params.toArray());
	}

	@Override
	public Page<Object[]> findUniteMembersBySearch(Integer pageNumber, Integer pageSize, String unId) {
		List<Object> params = Lists.newArrayList();
		StringBuffer jpql = new StringBuffer("from UnCustTab ");
		jpql.append("where unGuId = '"+ unId + "' ");
		jpql.append(" order by id desc");
		//jpql.append("select * from un_cust_tab uct where uct.un_gu_id = '"+unId+"' order by uct.id desc");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize), jpql.toString());
	}

	@Override
	public Page<Object[]> findCustomersBySearch(Integer pageNumber, Integer pageSize,String unGuId, List<String> params) {
		List<Object> sqlParams = Lists.newArrayList();
		StringBuffer nativeSql = new StringBuffer();
		nativeSql.append("SELECT p.party_id," +
				         "p.customer_num,  " +
				         "p.party_name, " +
				         "p.party_type_cd, " +
				         "p.certificate_type_cd, " +
				         "p.certificate_num ," +
				         "p.status, " +
				         "p.cust_manager_name " +
				         "FROM ( " 
				         );
		nativeSql.append("SELECT p.party_id, " +
								"p.customer_num, " +
								"p.party_name, " +
								"p.party_type_cd, " +
								"p.certificate_type_cd, " +
								"p.certificate_num, " +
								"CASE WHEN p.party_type_cd = '1' THEN " +
								"(SELECT co.create_date " +
								"FROM corporation_customer co " +
								"WHERE co.party_id = p.party_id ) " +
								"ELSE " +
								"(SELECT indiv.create_date " +
								"FROM individual indiv " +
								"WHERE indiv.party_id = p.party_id ) " +
								"END AS create_date, ");
		nativeSql.append(		"CASE WHEN p.party_type_cd = '1' THEN " +
								"(SELECT co.mark_type " +
								"FROM corporation_customer co " +
								"WHERE co.party_id = p.party_id ) " +
								"ELSE " +
								"(SELECT indiv.mark_type " +
								"FROM individual indiv " +
								"WHERE indiv.party_id = p.party_id ) " +
								"END AS mark_type, ");
		nativeSql.append("		CASE WHEN p.party_type_cd = '1' THEN " +
								"(SELECT co.states " +
								"FROM corporation_customer co " +
								"WHERE co.party_id = p.party_id ) " +
								"ELSE " +
								"(SELECT indiv.status " +
								"FROM individual indiv " +
								"WHERE indiv.party_id = p.party_id ) " +
								"END AS status,  "+ 
								" (SELECT eop.name "+
								" FROM ec_org_person eop "+
								" WHERE eop.id= "+
								"  (SELECT cmt.user_num FROM customer_manager_team cmt WHERE cmt.party_id= p.party_id and cmt.manager_type='01')  "+
								" )"+
								"  cust_manager_name "); 
		nativeSql.append("FROM Party p ");
		nativeSql.append("WHERE EXISTS (SELECT 1 FROM customer_manager_team cmt " +
												"WHERE cmt.party_id = p.party_id " +
												  "AND (cmt.user_num = ?1 " +
												       "OR cmt.dept_cd IN (SELECT l1.orgid " +
												                          "FROM person_privilege_level l1 " +
												                          "WHERE l1.personid = ?2 " +
												                          "AND l1.privilege_level = '1' " +
												                          "AND l1.state = 1) " +
												       "OR (cmt.org_cd IN (SELECT d.id " +
												       					  "FROM ec_org_department d " +
												       					  "START WITH d.id IN (SELECT l2.orgid " +
												       					  					  "FROM person_privilege_level l2 " +
												       					  					  "WHERE l2.personid = ?3 " +
												       					  					  "AND l2.privilege_level = '2' " +
												       					  					  "AND l2.state = 1) " +
												       					  "CONNECT BY prior d.id = d.parentdepartmentid )))) AND p.party_type_cd !='3' ");
	    int paramIndex = 1;
		while (paramIndex<=3) {
			sqlParams.add(params.get(4));
			paramIndex++;
		}
		Integer queryType = CommonHelper.toInt(params.get(3));
		if(queryType.compareTo(0)==0){//全部
			nativeSql.append(" AND  p.party_type_cd in (?"+ paramIndex++ +") ");
			sqlParams.add(dataDict.getCodeValList("CustomerType", "S1", "S2"));
		}else if(queryType.compareTo(1)==0){//企业
			nativeSql.append(" AND  p.party_type_cd= ?"+ paramIndex++ +" ");
			sqlParams.add(dataDict.getCodeVal("CustomerType", "S1"));
		}else if(queryType.compareTo(2)==0){//个人
			nativeSql.append(" AND  p.party_type_cd= ?"+ paramIndex++ +" ");
			sqlParams.add(dataDict.getCodeVal("CustomerType", "S2"));
		}

		
		if (StringUtils.isNotBlank(params.get(0))) { // 按客户名称查询
			nativeSql.append(" AND p.party_name LIKE ?" +paramIndex++);
			sqlParams.add("%"+params.get(0)+"%");
		}
		if (StringUtils.isNotBlank(params.get(1))) { // 按客户名称查询
			nativeSql.append(" AND p.certificate_type_cd LIKE ?" +paramIndex++);
			sqlParams.add("%"+params.get(1)+"%");
		}
		if (StringUtils.isNotBlank(params.get(2))) { // 按客户名称查询
			nativeSql.append(" AND p.certificate_num LIKE ?" +paramIndex++);
			sqlParams.add("%"+params.get(2)+"%");
		}
		nativeSql.append(" ) p ");
		// 选用既是借款人又是担保人的客户------zhangcong
		nativeSql.append(" WHERE p.status = '2' AND p.mark_type = '01,02'");
		// 去掉已被选用的客户-----zhangcong 20131219
		/*nativeSql.append(" MINUS ");
		nativeSql.append("(SELECT PA.PARTY_ID, " +
						         "PA.CUSTOMER_NUM, " +
						         "PA.PARTY_NAME, " +
						         "PA.PARTY_TYPE_CD, " +
						         "PA.CERTIFICATE_TYPE_CD," + 
				                 "PA.CERTIFICATE_NUM," +
				                 "PA.STATUS," +
				                 " (SELECT eop.name  " +
				                 "FROM ec_org_person eop  " +
				                 "WHERE eop.id = (SELECT cmt.user_num   " +
				                 				 "FROM customer_manager_team cmt " +
				                 				" WHERE cmt.party_id = PA.party_id  " +
				                 				"and cmt.manager_type = '01')) cust_manager_name " +
				          "FROM PARTY PA," +
				               "UN_CUST_TAB UC " +
				              
				          "WHERE PA.PARTY_ID = UC.PARTY_ID " +
				          		" )");
*/
		nativeSql.append(" AND p.party_id not in (select uct.party_id from UN_CUST_TAB uct where uct.un_gu_id=?"+(paramIndex++)+")");
		sqlParams.add(unGuId);
		nativeSql.append(" ORDER BY PARTY_ID DESC ");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber, pageSize), nativeSql.toString(), sqlParams.toArray());
	}

	@Override
	public Party findPartyByPartyId(String partyId) {
		if(StringUtils.isNotBlank(partyId)){
			return customerPartyDao.findOne(new Long(partyId));
		}else{
			return null;
		}
	}

	@Override
	public void saveUnCustTab(UnCustTab tab) {
		unCustTabDao.save(tab);
	}

	@Override
	public String findNextId() {
		String sql = "select SEQ_UN_CUST_TAB.nextval from dual";
		BigDecimal a = dynamicQuery.nativeQuerySingleResult(BigDecimal.class, sql);
		return a.toPlainString();
	}

	@Override
	public List findUnCustTabByPartyId(Long partyId, String unGuId) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select u from UnCustTab u where u.unGuId = ?1 and u.partyId = ?2 ");
		return dynamicQuery.query(jpql.toString(), unGuId, partyId.toString());
	}

	@Override
	public UnCustTab getUnCustTabById(String meb_id) {
		/*String sql = "select * from un_cust_tab uct where uct.id = ?1 ";
		Object[] obj = dynamicQuery.nativeQuery(sql, meb_id).get(0);
		return new UnCustTab(obj);*/
		return unCustTabDao.findOne(meb_id);
	}

	@Override
	public void deleteUnCustTab(String id) {
		/*String sql = "delete from un_cust_tab uct where uct.id = ?1 ";
		dynamicQuery.nativeExecuteUpdate(sql, id);*/
		unCustTabDao.delete(id);
	}

	@Override
	public UniteGuNego findUniteCustomerByUnGuId(String unGuId) {
		return uniteGuNegoDao.findByUnGuId(unGuId);
	}

	@Override
	public String findDocumentCount(String unGuId) {
		StringBuffer sql = new StringBuffer("SELECT to_char(nvl(count(*),'')) as count " +
				"FROM DOCUMENT_INDEX di " +
				"WHERE di.CUSTOMER_NUM = ?1 " +
				"AND di.STATUS = '1' ");
		String num =  dynamicQuery.nativeQuerySingleResult(String.class, sql.toString(), unGuId);
		return num;
	}

	@Override
	public List<UnCustTab> findUnCustTabList(String unGuId) {
		return unCustTabDao.findByUnGuId(unGuId);
	}

	@Override
	public void updateUniteStatus(String unGuId, Integer action) {
		String _action = action.compareTo(1)==0?"2":"3";
		String jpql = "update UniteGuNego  set unStatus ='"+ _action +"' WHERE unGuId = ?1 ";
		dynamicQuery.executeUpdate(jpql, unGuId);
	}

	@Override
	public UnCustTab findUnCustTabById(String id) {
		return unCustTabDao.findOne(id);
	}

	@Override
	public String findNextSeq() {
		String sql = "select SEQ_UN_CUST_TAB.nextval from dual";
		return CommonHelper.toStr(dynamicQuery.nativeQuery(sql).get(0));
	}
	@Override
	public String addMembersOfBatch(Long ugnId, Set<Long> custIds) {
		if(ugnId==null||CollectionUtils.isEmpty(custIds)){
			throw new ServiceException("参数错误");
		}
		UniteGuNego ugn=this.uniteGuNegoDao.findOne(ugnId);
		if(ugn==null){
			throw new ServiceException("该联保体不存在");
		}
		List<String> custIdSet=Lists.newArrayList();
		for (Long l : custIds) {
			custIdSet.add(String.valueOf(l));
		}
		List<Party> partys=this.findPartyListByPartyId(custIds);
		String unGuId=ugn.getUnGuId();
		UnCustTab uct=null;
		Map<String, String> dbCustMap=this.findSpouseInfoMapByUgnId(ugnId);
		Map<Long, String> newCustMap=this.findSpouseInfoMapByPartyIds(custIds);
		StringBuffer strBuffer=new StringBuffer();
		for (Party party : partys) {
			//配偶不能添加在联保体成员中
			if (StringUtils.equals(
					dataDict.getCodeVal(CERTIFICATE_TYPE, "S100"),
					party.getCertificateTypeCd())
					&& dbCustMap.containsKey(party.getCertificateNum())) {
				//配偶
				strBuffer.append(MessageFormat.format("【{0}】和【{1}】是配偶，", dbCustMap.get(party.getCertificateNum()), party.getPartyName()));
				continue;
			}else{
				if(newCustMap.containsKey(party.getPartyId())){//获取该客户的配偶身份证号
					dbCustMap.put(newCustMap.get(party.getPartyId()), party.getCertificateNum());
				}
			}
			uct=new UnCustTab();
			uct.setId(this.findNextId());
			uct.setPartyId(String.valueOf(party.getPartyId()));
			uct.setUniteGuNegoId(ugn.getId());//联保体id
			uct.setUnGuId(unGuId);//联保体编号
			uct.setCustomerNum(party.getCustomerNum());
			uct.setCustomerName(party.getPartyName());
			uct.setCertificateNum(party.getCertificateNum());
			uct.setCertificateTypeCd(party.getCertificateTypeCd());
			uct.setCurrency("156");// 人民币
			uct.setStatus(party.getStatus());//客户状态
			this.unCustTabDao.save(uct);
		}
		String msg=StringUtils.EMPTY;
		if(strBuffer.length()>0){
			msg=StringUtils.join(strBuffer.toString(),"其他处理成功！");
		}
		return msg;
	}
	
	/**
	 * 判断客户是否已存在在该联保体成员列表
	 * @param unGuId
	 * @param custIds
	 * @return
	 */
	private boolean isExistUnCustByUnGuIdCustIds(String unGuId, List<String> custIds){
		if(StringUtils.isBlank(unGuId)||CollectionUtils.isEmpty(custIds)){
			throw new ServiceException("参数错误");
		}
		Long count=this.dynamicQuery.queryCount("from UnCustTab uct where uct.unGuId=? and partyId in (?2)", unGuId,custIds);
		return count>0;
	}
	
	/**
	 * 该联保体成员的配偶信息
	 * @param ugnId
	 * @return	key：配偶身份证号，value：该客户名称
	 */
	private Map<String, String> findSpouseInfoMapByUgnId(Long ugnId){
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("select ff.certificate_cd,uct.customer_name");
		strBuffer.append(" from un_cust_tab uct inner join family_friend ff ");
		strBuffer.append(" on ff.party_id = uct.party_id ");
		strBuffer.append(" and ff.relations_type = ?2 and ff.certificate_type_cd = ?3");
		strBuffer.append(" where uct.unite_gu_nego_id=?1 ");
		List<Object> params=Lists.newArrayList();
		params.add(ugnId);
		params.add(dataDict.getCodeVal(RELATIONS_TYPE, "S1"));
		params.add(dataDict.getCodeVal(CERTIFICATE_TYPE, "S100"));
		List<Object[]> list=dynamicQuery.nativeQuery(strBuffer.toString(), params.toArray());
		Map<String, String> map=Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(list)){
			for (Object[] objs : list) {
				map.put(CommonHelper.toStr(objs[0]), CommonHelper.toStr(objs[1]));
			}
		}
		return map;
	}
	
	/**
	 * 该联保体成员的配偶信息
	 * @param partyIds
	 * @return key:partyId,value:配偶身份证
	 */
	private Map<Long, String> findSpouseInfoMapByPartyIds(Set<Long> partyIds){
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("select ff.party_id, ff.certificate_cd from family_friend ff");
		strBuffer.append(" where ff.relations_type = ?1 ");
		strBuffer.append(" AND ff.party_id in (?2)");
		strBuffer.append(" and ff.certificate_type_cd = ?3 ");
		List<Object> params=Lists.newArrayList();
		params.add(dataDict.getCodeVal(RELATIONS_TYPE, "S1"));
		params.add(partyIds);
		params.add(dataDict.getCodeVal(CERTIFICATE_TYPE, "S100"));
		List<Object[]> list=dynamicQuery.nativeQuery(strBuffer.toString(), params.toArray());
		Map<Long, String> map=Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(list)){
			for (Object[] objs : list) {
				map.put(CommonHelper.toLong(objs[0]), CommonHelper.toStr(objs[1]));
			}
		}
		return map;
	}
	
	private List<Party> findPartyListByPartyId(Set<Long> partyIds){
		return this.dynamicQuery.query(Party.class,"from Party p where p.partyId in(?1)", partyIds);
	}
	
	@Override
	public MsgResult checkAllBizStatus(Long ugnId) {
		StringBuilder sb=new StringBuilder();
		List<Object> params=Lists.newArrayList();
		//业务
		sb.append("select distinct pa.project_id ");
		sb.append(" from project_application pa");
		sb.append(" where pa.un_id = ?1 and pa.project_status in (?2)");
		params.add(ugnId);
		//未提交、已提交、已批复
		params.add(dataDict.getCodeValList("ProjectStatus", "S0","S1","S2"));
		//普通借款合同
		sb.append("union select distinct pa.project_id ");
		sb.append(" from project_application pa");
		sb.append(" inner join contract c");
		sb.append(" on pa.project_id = c.project_id  and c.contract_status_cd in (?3)");
		sb.append(" where pa.un_id = ?1 ");
		//300：已签订，316：已放款，330：已逾期，423：放款中，424:核销中，521：审批中
		params.add(dataDict.getCodeValList("ContractStatusCode", "S1","S2","S3","S8","S9","S10"));
		//授信借款合同
		sb.append("union select distinct pa.project_id");
		sb.append(" from project_application pa");
		sb.append(" inner join credit_contract cc");
		sb.append(" on pa.project_id = cc.project_id and cc.contract_status_cd in (?4)");
		sb.append(" left join contract c");
		sb.append(" on cc.credit_contract_id = c.credit_contract_id");
		sb.append(" and c.contract_status_cd in (?3)");
		sb.append(" where pa.un_id = ?1");
		//未生效、已生效
		params.add(dataDict.getCodeValList("CreditContractStatus", "S1","S2"));
		Long count=dynamicQuery.nativeQueryCount(sb.toString(), params.toArray());
		if(count>0){
			return MsgResult.getMsgResult("0001", "该联保体有未结束的业务！");
		}
		return MsgResult.getMsgResult(ResultEnums.SUCCESS);
	}
}
