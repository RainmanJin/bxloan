package com.coamctech.bxloan.service.sysmng.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.dao.DocumentIndexDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Transactional
@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private DocumentIndexDao documentIndexDao;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private DataDict dataDict;
	
	@Override
	public boolean updateDocumentIndexByPartyId(Long partyId,
			Set<String> documentTypes, String createTypeCd) {
		StringBuffer jpql = new StringBuffer("UPDATE DocumentIndex di SET di.createTypeCd = ?1 , di.systemUpdateTime = sysdate WHERE di.partyId =?2");
		List<Object> params=Lists.newArrayList();
		params.add(createTypeCd);
		params.add(partyId);
		if(CollectionUtils.isNotEmpty(documentTypes)){
			jpql.append(" and  di.documentType in (?3) ");
			params.add(documentTypes);
		}
		jpql.append(" and di.status = 1 ");
		dynamicQuery.executeUpdate(jpql.toString(), params.toArray());
		return true;
	}
	@Override
	public boolean updateDocumentIndexByProjectId(Long projectId,
			Set<String> documentTypes, String createTypeCd) {
		StringBuffer jpql = new StringBuffer("UPDATE DocumentIndex di SET di.createTypeCd = ?1 , di.systemUpdateTime = sysdate  WHERE di.bizId =?2");
		List<Object> params=Lists.newArrayList();
		params.add(createTypeCd);
		params.add(projectId);
		if(CollectionUtils.isNotEmpty(documentTypes)){
			jpql.append(" and  di.documentType in (?3) ");
			params.add(documentTypes);
		}
		jpql.append(" and di.status = 1 ");
		dynamicQuery.executeUpdate(jpql.toString(), params.toArray());
		return true;
	}
	
	@Override
	public CustDocInfo findUploadCustDocTypes(Long projectId, Long partyId, String custDocTypeCd) {
		CustDocInfo custInfo = new CustDocInfo();
		Map<String, String> map = Maps.newHashMap();
		String sql = "select c.code_name,c.code_value from code c where " +
			     "c.code_type ='CustProjectAllDocType' " ;
		String str[] = custDocTypeCd.split(",");
		if(str==null||str.length<1){
			sql += " " +
					"+or "+ custDocTypeCd + " is null ) ";
		}else{
			sql+=" and ";
			for (String custCd : str) {
				sql += " (c.meanings_instruction like '%"+ custCd +"%' or "+ custCd + " is null ) or";
			}
			sql = sql.substring(0, sql.length()-2);
		}
				    
		List<Object[]> list = dynamicQuery.nativeQuery(sql);
		for (Object[] obj : list) {
			map.put(obj[1].toString(), obj[0].toString());
		}
		String countSql = "select count(*) from ("+ sql +")";
		custInfo.setCount(dynamicQuery.nativeQueryCount(sql).intValue());
		custInfo.setCustMap(map);
		return custInfo;
	}
	@Override
	public List<Object[]> findDocumentCustDocTypes(Long projectId, Long partyId, String custDocTypeCd) {
		String sql = " select di.cust_doc_type,count(di.cust_doc_type) from DOCUMENT_INDEX di where 1=1 ";
		
		if(partyId!=null&&projectId!=null){
			sql+="  and (( di.party_id = " + partyId + " and di.document_type = '01' ) or (di.biz_id = " + projectId + " and di.document_type in ('02','06','45') ))";
		}else if(partyId!=null){
			sql+=" and di.party_id = " + partyId + " and di.document_type = '01' ";
		}
		sql += " and  di.status = '1' ";
		sql += " and di.cust_doc_type is not null ";
		sql += " group by di.cust_doc_type ";
		return (List<Object[]>) dynamicQuery.nativeQuery(sql);
	}
	@Override
	public List<String> findCustDocTypeNames(String queryName) {
		String sql = "SELECT C.CODE_VALUE FROM CODE C WHERE C.CODE_TYPE = 'CustProjectAllDocType' "+
					 "AND C.CODE_NAME LIKE ?1 ";
		return dynamicQuery.nativeQuery(String.class, sql,"%"+queryName+"%");
	}
	@Override
	public List<Object[]> findDocumentCustDocTypes(Long projectId, String custDocTypeCd) {
		String sql  = "select di.cust_doc_type, count(di.cust_doc_type) from DOCUMENT_INDEX di " 
					+ "left join Code c on c.code_value = di.cust_doc_type "
					+ "where c.code_type = 'CustProjectAllDocType' and  1=1 ";
					
		if(projectId!=null){
			sql+="  and (di.biz_id = " + projectId + ") ";
		}
		if(StringUtils.isNotBlank(custDocTypeCd)){
			sql+= "and (c.meanings_instruction like '%"+ custDocTypeCd +"%' or "+ custDocTypeCd + " is null ) ";
		}
		sql += " and  di.status = '1' ";
		sql += " and di.cust_doc_type is not null ";
		sql += " group by di.cust_doc_type ";
		return (List<Object[]>) dynamicQuery.nativeQuery(sql);
	}
	
	
	@Override
	public Page findDocumentsInApproval(int pageNumber, Integer pageSize, List<Object> params) {
		String commonSql = "SELECT * FROM (( ";
		String nativesql = "";
		       nativesql+= "SELECT t.party_id, " +
		       			   "t.document_name, "+
		       			   "t.cust_doc_type, " +
		       			   "t.document_type, " +
		       			   "t.customer_num, " +
		       			   "co.name, " +
		       			   "t.create_date_time, " +
		       			   "t.create_type_cd, " +
		       			   "t.document_id, " +
		       			   "t.all_doc_type " +
		       			   "FROM DOCUMENT_INDEX t " +
		       			   "LEFT JOIN EC_ORG_PERSON co " +
		       			   "ON co.id = t.create_user_num " +
		       			   "WHERE t.document_type IN  " + params.get(0) +
		       			   "AND t.status = 1 " +
		       			   "AND (t.biz_id = ?1) ";
		commonSql+= nativesql + ") UNION (";
			   commonSql+= "SELECT t.party_id, " +
					   	   "t.document_name, "+
	       			       "t.cust_doc_type, " +
	       			       "t.document_type, " +
	       			       "t.customer_num, " +
	       			       "co.name, " +
	       			       "t.create_date_time, " +
	       			       "t.create_type_cd, " +
	       			       "t.document_id, " +
	       			       "t.all_doc_type " +
	       			       "FROM DOCUMENT_INDEX t " +
	       			       "LEFT JOIN EC_ORG_PERSON co " +
	       			       "ON co.id = t.create_user_num " +
	       			       "WHERE t.document_type IN ('01') " +
	       			       "AND t.status = 1 ";
	    commonSql+= ")) doc WHERE doc.party_id = ?2 " +
	    			"AND (doc.document_name LIKE ?3 OR ?4 IS NULL) ";
	    
	    List<Object> paramsVar = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(commonSql);
		//paramsVar.add(params.get(0));//documentType
		paramsVar.add(params.get(1));//projectId
		paramsVar.add(params.get(2));//partyId
		paramsVar.add("%"+params.get(3)+"%");//partyId
		paramsVar.add(params.get(3));//partyId
	    
	    if(StringUtils.isNotBlank((String) params.get(4))){
			List<String> custDocValues = this.findCustDocTypeNames((String)(params.get(4)));
			if(CollectionUtils.isNotEmpty(custDocValues)){
				sql.append(" AND doc.cust_doc_type IN (?5) ");
			paramsVar.add(custDocValues);}else{
				sql.append(" AND t.cust_doc_type IN ('') ");
			}
		}
	    			
	    sql.append(" ORDER BY doc.all_doc_type desc nulls last, doc.create_date_time desc,doc.document_id");
		
		
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql.toString(), paramsVar.toArray());
	}
	
	@Override
	public Integer findCustDocTypeNum(Long projectId, String custDocType) {
		String sql = "select *from document_index di where di.biz_id = ?1 and di.status = '1' ";
			   sql+= "and di.cust_doc_type = ?2 ";
		return dynamicQuery.nativeQueryCount(sql, projectId,custDocType).intValue();
	}
	@Override
	public List<DocumentIndex> findByPartyIdAndProject(Long partyId, Long projectId) {
		return documentIndexDao.findByPartyIdAndProjectId(partyId, projectId);
	}
	@Override
	public Page<Object[]> findDocumentsInUnite(int pageNumber, Integer pageSize, List<Object> params) {
		List<Object> sqlParams = Lists.newArrayList();
		StringBuffer sql = new StringBuffer("");
		sql.append("select a.document_Id," +
						"  a.document_name, " +
						"  a.cust_doc_type, " +
						"  a.document_type, " +
						 "  co.name, " +
		       			   "to_char(a.create_date_time,'yyyy-MM-dd'), " +
		       			   "a.create_type_cd, " +
		       			   "a.all_doc_type, " +
		       			   "b.id," +
		       			   "b.un_group_name " +
					"from document_index a " +
					 "LEFT JOIN EC_ORG_PERSON co " +
	       			   "ON co.id = a.create_user_num " +
					     "LEFT JOIN unite_gu_nego b " +
					"ON a.customer_num = b.un_gu_id " +
					"WHERE a.customer_num = ?1 " +
					"and a.status = '1' " +
					"and a.document_type in ('01') ");
		sqlParams.add(params.get(1));
		int paramsIndex = 2;
		if(StringUtils.isNotBlank(CommonHelper.toStr(params.get(2)))){
			sql.append("and a.document_name like ?"+ paramsIndex++ +" ");
			sqlParams.add("%"+ params.get(2) +"%");
		}
		if(StringUtils.isNotBlank(CommonHelper.toStr(params.get(3)))){
			List<String> custDocValues = this.findCustDocTypeNames((String)(params.get(3)));
			if(CollectionUtils.isNotEmpty(custDocValues)){
				sql.append("AND a.cust_doc_type in (?"+ paramsIndex++ +") ");
				sqlParams.add(custDocValues);
			}else{
				sql.append("AND a.cust_doc_type in ('') ");
			}
		}
		sql.append(" order by a.document_id desc ");
		return dynamicQuery.nativeQuery(Object[].class,new PageRequest(pageNumber - 1, pageSize), sql.toString(), sqlParams.toArray());
	}
	@Override
	public Page findDocumentsInCollateral(Integer pageNumber, Integer pageSize, List<Object> params) {
		List sqlParams = Lists.newArrayList();
		StringBuffer sql = new StringBuffer("");
		sql.append("select a.document_Id," +
						"  a.document_name, " +
						"  a.cust_doc_type, " +
						"  a.document_type, " +
						 "  co.name, " +
		       			   "to_char(a.create_date_time,'yyyy-MM-dd'), " +
		       			   "a.create_type_cd, " +
		       			   "a.document_Id aid, " +
		       			   "a.all_doc_type " +
					"from document_index a " +
					 "LEFT JOIN EC_ORG_PERSON co " +
	       			   "ON co.id = a.create_user_num " +
					"WHERE a.customer_num = ?1 " +
					"and a.status = '1' " +
					"and a.document_type in (?2) ");
		sqlParams.add(params.get(0));
		sqlParams.add(params.get(1));
		int paramsIndex = 3;
		if(StringUtils.isNotBlank(CommonHelper.toStr(params.get(2)))){
			sql.append("and a.document_name like ?"+ paramsIndex++ +" ");
			sqlParams.add("%"+ params.get(2) +"%");
		}
		if(StringUtils.isNotBlank(CommonHelper.toStr(params.get(3)))){
			List<String> custDocValues = this.findCustDocTypeNames((String)(params.get(3)));
			if(CollectionUtils.isNotEmpty(custDocValues)){
				sql.append("AND a.cust_doc_type in (?"+ paramsIndex++ +") ");
				sqlParams.add(custDocValues);
			}else{
				sql.append("AND a.cust_doc_type in ('') ");
			}
		}
		sql.append(" order by a.document_id desc ");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql.toString(), sqlParams.toArray());
	}
	@Override
	public List<Object[]> findDocumentCustDocTypes(String guarantyNum, String custDocTypeCd) {
		String sql = " select di.cust_doc_type,count(di.cust_doc_type) from DOCUMENT_INDEX di where 1=1 ";
		
	    sql+=" and di.customer_num = '" + guarantyNum + "' and di.document_type = '46' ";
		sql += " and  di.status = '1' ";
		sql += " and di.cust_doc_type is not null ";
		sql += " group by di.cust_doc_type ";
		return (List<Object[]>) dynamicQuery.nativeQuery(sql);
	}
}
