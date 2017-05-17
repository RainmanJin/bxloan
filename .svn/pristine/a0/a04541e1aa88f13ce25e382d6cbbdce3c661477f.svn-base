package com.coamctech.bxloan.service.creditcontractmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.ProjectAssurerInfoDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.SubContractDao;
import com.coamctech.bxloan.dao.credit.CreditContractDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.creditcontractmng.CreditContractMngService;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.contractmng.ProjectAssurerInfoVo;
import com.coamctech.bxloan.service.model.contractmng.ProjectPawnInfoVo;
import com.coamctech.bxloan.service.model.credit.CreditContractVo;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Transactional
@Service
public class CreditContractMngServiceImpl implements CreditContractMngService {

	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Autowired
	private CreditContractDao creditContractDao;
	
	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private SubContractDao subContractDao;
	
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	
	@Autowired
	private ProjectAssurerInfoDao projectAssurerInfoDao;
	
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;

	@Override
	public Page<CreditContractVo> creditContractList(Integer pageNumber,
			Integer pageSize, CreditContractVo vo) {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>(0);
		int index = 1;

		sb.append("SELECT ");
		sb.append("c.CREDIT_CONTRACT_ID,");//授信合同Id
		sb.append("c.CONTRACT_NUM,");//授信合同编号
		sb.append("c.CUSTOMER_NAME,");//客户名称
		sb.append("c.CUSTOMER_NUM,");//客户编号
		sb.append("p.PRODUCT_NAME,");//产品名称
		sb.append("c.CONTRACT_AMT,");//授信额度
		sb.append("c.CONTRACT_TERM,");//授信期限
		sb.append("c.CONTRACT_TERM_UNIT,");//授信期限单位
		sb.append("c.BIZ_RATE,");//授信利率
		sb.append("c.CREDIT_TYPE,");//授信类型
		sb.append("c.CONTRACT_STATUS_CD, ");//授信合同状态
		sb.append("c.PARTY_ID   ");//参与人id
		sb.append("FROM CREDIT_CONTRACT c LEFT JOIN PRODUCT p ON c.PRODUCT_TYPE = p.PRODUCT_CD ");
		sb.append("WHERE c.CONTRACT_STATUS_CD = '2'");
		// 授信合同编号
		if (StringUtils.isNotEmpty(vo.getContractNum())) {
			sb.append(" and c.CONTRACT_NUM like ?").append(index++);
			params.add("%" + vo.getContractNum() + "%");
		}
		// 客户名称
		if (StringUtils.isNotEmpty(vo.getCustomerName())) {
			sb.append(" and c.CUSTOMER_NAME like ?").append(index++);
			params.add("%" + vo.getCustomerName() + "%");
		}
		// 授信类型
		if (StringUtils.isNotEmpty(vo.getCreditType())) {
			sb.append(" and c.CREDIT_TYPE = ?").append(index++);
			params.add(vo.getCreditType());
		}
		//申请人编号
		if (StringUtils.isNotEmpty(vo.getApplyUserNum())) {
			sb.append(" and c.APPLY_USER_NUM = ?").append(index++);
			params.add(vo.getApplyUserNum());
		}
		sb.append(" order by c.CONTRACT_NUM desc");
		
		// 查询
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class, new PageRequest(
				pageNumber, pageSize), sb.toString(), params.toArray());
		//把查询结果转成VO
		Page<CreditContractVo> resultPage = new PageImpl<CreditContractVo>(Lists.transform(page.getContent(), new Function<Object[], CreditContractVo>(){
				@Override
				public CreditContractVo apply(Object[] objs) {
					return new CreditContractVo(objs) ;
				}
			}),new PageRequest(pageNumber, pageSize), page.getTotalElements());
		
		//返回查询结果
		return resultPage;
	}

	@Override
	public CreditContract findCreditContractByProjectId(Long projectId) {
		return creditContractDao.findCreditContractByProjectId(projectId);
	}

	@Override
	public Long saveCreditContract(CreditContract creditContract) {
		creditContractDao.save(creditContract);
		return creditContract.getCreditContractId();
	}

	@Override
	public CreditContract getCreditContractById(Long creditContractId) {
		return creditContractDao.findOne(creditContractId);
	}
	
	@Override
	public void saveSubContract(SubContract subcontract) {
		subContractDao.save(subcontract);
	}
	
	@Override
	public List<SubContract> findSubcontractList(Long projectId) {
		return subContractDao.findByProjectId(projectId);
	}

	@Override
	public void copyPawnInfoToSubContract(Long projectId, SessionUser sessionUser) {
		SubContract subcontract = null;
		String subcontractNum = "";
		CreditContract creditContract = this.findCreditContractByProjectId(projectId);
		//查询抵质押列表
		List<ProjectPawnInfo> pawnList = projectPawnInfoDao.findByProjectId(projectId);
		if(CollectionUtils.isNotEmpty(pawnList)) {
			Integer dycount = 0;	//抵押序号
			Integer zycount = 0;	//质押序号
			for(ProjectPawnInfo ppi : pawnList) {
				subcontract = new SubContract();
				String guaranteeType = ppi.getGuaranteeType();
				if (StringUtils.isNotBlank(guaranteeType) && !"null".equals(guaranteeType)) {
					if (dataDict.getCodeVal("MortgageType", "S1").equals(guaranteeType)) { 		//担保方式--抵押
						dycount = dycount + 1;
						subcontractNum = commonBizNumberBS.generateSubContractNumber(creditContract.getContractNum(), "抵", dycount);
					} else if (dataDict.getCodeVal("MortgageType", "S2").equals(guaranteeType)) { //担保方式--质押
						zycount = zycount + 1;
						subcontractNum = commonBizNumberBS.generateSubContractNumber(creditContract.getContractNum(), "质", zycount);
					}
				}
				this.assignSubContract(sessionUser, subcontract, creditContract);
				subcontract.setSubcontractNum(subcontractNum);
				subcontract.setGuarantCustomerNum(creditContract.getCustomerNum());
				//实际担保率
				subcontract.setGuarantyRate(ppi.getActualGuaranteeRate()); 	
				//本次实际担保债权金额
				subcontract.setGuarantyAmt(ppi.getActualCreditAmount());	
				subcontract.setGuarantyId(ppi.getProjectPawnInfoId());
				subcontract.setGuarantyNum(ppi.getGuarantyNum());
				//担保方式
				subcontract.setGuaranteeTypeCd(ppi.getGuaranteeType());	 
				subcontract.setSubcontractTypeCd(dataDict.getCodeVal("SubcontractType", "S1"));
				this.saveSubContract(subcontract);
			}
		}
	}

	@Override
	public void copyAssureInfoToSubContract(Long projectId, SessionUser sessionUser) {
		SubContract subcontract = null;
		String subcontractNum = "";
		CreditContract creditContract = this.findCreditContractByProjectId(projectId);
		//查询保证人列表
		List<ProjectAssurerInfo> assurerList = projectAssurerInfoDao.findByProjectId(projectId);
		if (CollectionUtils.isNotEmpty(assurerList)) {
			int bzcount = 0; //保证人序号
			for(ProjectAssurerInfo pai : assurerList) {
				//实际保证金额
				pai.setActualGuaranteeAmt(creditContract.getContractAmt()); 
				subcontract = new SubContract();
				this.assignSubContract(sessionUser, subcontract, creditContract);
				bzcount = bzcount + 1;
				subcontractNum = commonBizNumberBS.generateSubContractNumber(creditContract.getContractNum(), "保", bzcount);
				subcontract.setSubcontractNum(subcontractNum);
				subcontract.setGuarantCustomerNum(pai.getCustomerNum());
				//本次实际保证债权金额
				subcontract.setGuarantyAmt(creditContract.getContractAmt());
				subcontract.setGuarantyNum(pai.getCustomerNum());
				subcontract.setGuaranteeTypeCd(pai.getAssurerType()); 
				subcontract.setSubcontractTypeCd(dataDict.getCodeVal("SubcontractType", "S2")); 
				subcontract.setAssurerId(pai.getProjectAssurerInfoId());
				this.saveSubContract(subcontract);
			}
		}
	}
	
	@Override
	public Result checkSubContractDoc(Long projectId) {
		List<SubContract> subContractList = this.findSubcontractList(projectId);
		if (CollectionUtils.isNotEmpty(subContractList)) {
			int count = 0;
			for (SubContract subcontract : subContractList) {
				if (StringUtils.equals(dataDict.getCodeVal("CommonWhether", "S1"),subcontract.getIsTransDocument())) {
					count++;
				}
			}
			if(count == subContractList.size()){
				return new Result(true);
			}
		} else {
			return new Result(true);
		}
		return new Result(false, "请上传所有从合同！", null);
	}
	
	/**
	 * 装载从合同信息
	 * 
	 * @param sessionUser 
	 * @param subcontract 从合同对象
	 * @param creditContract 授信合同对象
	 */
	private void assignSubContract(SessionUser sessionUser, SubContract subcontract, CreditContract creditContract) {
		BeanUtils.copyProperties(creditContract, subcontract);
		subcontract.setContractId(creditContract.getCreditContractId());
		subcontract.setCustomerId(creditContract.getPartyId());
		subcontract.setTerm(creditContract.getContractTerm());
		subcontract.setTermUnitCd(creditContract.getContractTermUnit());
		subcontract.setHandlingOrgCd(sessionUser.getOrgId());
		subcontract.setHandlingUserNum(sessionUser.getUserId().toString());
		subcontract.setSysCreateDate(CommonHelper.getNow());
		subcontract.setSysUpdateDate(CommonHelper.getNow());
		subcontract.setSubcontractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S8"));
	}

	@Override
	public Page<CommonBorrow> findBorrowerListBySearch(Long projectId, Integer pageNumber,
			Integer pageSize) {
		//组装查询sql
		StringBuffer sql = new StringBuffer("");
		sql.append(" select cb.common_borrow_id, ");
		sql.append(" cb.customer_name, ");
		sql.append(" cb.certificate_type_cd, ");
		sql.append(" cb.certificate_num, ");
		sql.append(" cb.mobile_phone, ");
		sql.append(" cb.phone, ");
		sql.append(" cb.address, ");
		sql.append(" cb.project_id, ");
		sql.append(" cb.spouseFlag, ");
		sql.append(" cb.party_id, ");
		sql.append(" p.party_type_cd ");
		sql.append(" from common_borrow cb, party p ");
		sql.append(" where cb.party_id = p.party_id ");
		sql.append(" and cb.project_id = ?1 and  cb.flag = '1' ");
		
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), sql.toString(),
				projectId);
		
		//Object[]转换为CommonBorrow对象
		Page<CommonBorrow> resultPage = new PageImpl<CommonBorrow>(
				Lists.transform(page.getContent(),
						new Function<Object[], CommonBorrow>() {
							@Override
							public CommonBorrow apply(Object[] objs) {
								return new CommonBorrow(objs);
							}
						}), 
				new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		return resultPage;
	}

	@Override
	public Page findBailListBySearch(Long projectId,
			Integer pageNumber, Integer pageSize) {
		//组装查询sql
		StringBuffer jpql = new StringBuffer();
		jpql.append(" from ProjectAssurerInfo b where b.projectId = ?1 ");
		jpql.append(" order by b.projectAssurerInfoId desc ");
		return dynamicQuery.query( new PageRequest(pageNumber, pageSize),
				jpql.toString(), projectId);		
	}

	@Override
	public Page<ProjectPawnInfoVo> findCollateralSubcontractListBySearch(Long contractId, Integer pageNumber,
			Integer pageSize) {
		//组装查询sql
		StringBuffer sql = new StringBuffer();
		sql.append(" select ppi.project_id, ");
		sql.append(" sc.subcontract_id, ");		// 从合同ID
		sql.append(" sc.subcontract_num, ");	// 从合同编号
		sql.append(" sc.is_Trans_Document,");	// isTransDocument是否上传合同
		sql.append(" sc.document_num, ");		// 文档编号
		sql.append(" ppi.guaranty_id, ");		// 抵质押物ID
		sql.append(" ppi.guaranty_type_cd, ");	// 抵质押物类型
		sql.append(" ppi.guaranty_name,");		// 抵质押物名称
		sql.append(" ppi.guarantor_name,");		// 抵质押人名称
		sql.append(" ppi.eval_value,");			// 评估价值
		sql.append(" ppi.set_guarantee_amt, ");	// 已设定担保额
		sql.append(" ppi.actual_credit_amount, ");// 本次实际担保债权金额
		sql.append(" ppi.guarantee_type ");		// 抵质押类型
		sql.append(" from project_pawn_info ppi, subcontract sc ");
		sql.append(" where ppi.project_pawn_info_id = sc.guaranty_id ");
		sql.append(" and sc.contract_id = ?1 ");
		sql.append(" and sc.subcontract_type_cd= ?2 ");
		sql.append(" order by ppi.project_pawn_info_id ");
		
		//组装查询参数
		List<Object> params = Lists.newArrayList();
		params.add(contractId);
		params.add(dataDict.getCodeVal("SubcontractType", "S1"));
		
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), sql.toString(), params.toArray());
		
		//Object[]转换为ProjectPawnInfoVo对象
		Page<ProjectPawnInfoVo> resultPage = new PageImpl<ProjectPawnInfoVo>(
				Lists.transform(page.getContent(),
						new Function<Object[], ProjectPawnInfoVo>() {
							@Override
							public ProjectPawnInfoVo apply(Object[] objs) {
								return new ProjectPawnInfoVo(objs);
							}
						}), 
				new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		return resultPage;
	}
	
	@Override
	public Page<ProjectAssurerInfoVo> findAssureSubcontractListBySearch(
			Long contractId, Integer pageNumber, Integer pageSize) {
		//组装查询sql
		StringBuffer sql = new StringBuffer();
		sql.append(" select pai.project_id, ");
		sql.append(" pai.party_id, ");
		sql.append(" sc.subcontract_id, ");			// 从合同ID
		sql.append(" sc.subcontract_num, ");		// 从合同编号
		sql.append(" sc.is_Trans_Document,");		// isTransDocument是否上传合同
		sql.append(" sc.document_num, ");			// 文档编号
		sql.append(" pai.customer_name, ");			// 保证人名称
		sql.append(" pai.certificate_type_cd, ");	// 证件类型
		sql.append(" pai.certificate_num, ");		// 证件号码
		sql.append(" pai.actual_guarantee_amt, ");	// 本次实际担保债权金额 
		sql.append(" (select p.party_type_cd from party p where p.party_id = pai.party_id) ");	//客户类型	
		sql.append(" from project_assurer_info pai, subcontract sc ");
		sql.append(" where pai.project_assurer_info_id = sc.assurer_id ");
		sql.append(" and sc.contract_id = ?1 ");
		sql.append(" and sc.subcontract_type_cd= ?2 ");
		sql.append(" order by pai.project_assurer_info_id ");
		
		//组装查询参数
		List<Object> params = Lists.newArrayList();
		params.add(contractId);
		params.add(dataDict.getCodeVal("SubcontractType", "S2"));
		
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), sql.toString(), params.toArray());
		
		//Object[]转换为ProjectAssurerInfoVo对象
		Page<ProjectAssurerInfoVo> resultPage = new PageImpl<ProjectAssurerInfoVo>(
				Lists.transform(page.getContent(),
						new Function<Object[], ProjectAssurerInfoVo>() {
							@Override
							public ProjectAssurerInfoVo apply(Object[] objs) {
								return new ProjectAssurerInfoVo(objs);
							}
						}), 
				new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		return resultPage;
	}

	@Override
	public Page<DocumentIndex> findCustomerDocumentListBySearch(
			List<Object> params, Integer pageNumber, Integer pageSize) {
		//组装公用sql
		StringBuffer commonSql = new StringBuffer();
		commonSql.append(" select t.party_id, ");
		commonSql.append(" t.document_name, ");
		commonSql.append(" t.cust_doc_type, ");
		commonSql.append(" t.document_type, ");
		commonSql.append(" t.customer_num, ");
		commonSql.append(" co.name, ");
		commonSql.append(" t.create_date_time, ");
		commonSql.append(" t.create_type_cd, ");
		commonSql.append(" t.document_id, ");
		commonSql.append(" t.all_doc_type  ");
		commonSql.append(" from document_index t ");
		commonSql.append(" left join ec_org_person co ");
		commonSql.append(" on co.id = t.create_user_num ");
		
		//组装最终查询sql
		StringBuffer sql = new StringBuffer("select * from (");
		sql.append(commonSql);
		sql.append(" where t.document_type in ");
		sql.append(params.get(0));
		sql.append(" and t.status = '1' ");
		sql.append(" and t.biz_id = ?1 ");
		sql.append(" and t.party_id = ?2 ");
		sql.append(" union ");
		sql.append(commonSql);
		sql.append(" where t.document_type = '01' ");
		sql.append(" and t.status = '1' ");
		sql.append(" and t.party_id = ?3 ");
		sql.append(" ) doc ");
		sql.append(" where 1 = 1 ");
		
		//组装查询参数
		List<Object> paramsVar = Lists.newArrayList();
		paramsVar.add(params.get(1));	
		paramsVar.add(params.get(2));	
		paramsVar.add(params.get(2));	
		
		//文档名称
		String documentName = (String) params.get(3);
		int i = 4;
		if(StringUtils.isNotBlank(documentName)){
			sql.append(" and doc.document_name like ?").append(i++);
			paramsVar.add("%" + documentName + "%");
		}
		//内容类型字典值
		List<String> contentType = (List<String>) params.get(4);
		if(CollectionUtils.isNotEmpty(contentType)){
			sql.append(" and doc.cust_doc_type in (?").append(i++).append(") ");
			paramsVar.add(contentType);
		}
		sql.append(" order by doc.all_doc_type desc nulls last, doc.create_date_time desc,doc.document_id ");
		
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), sql.toString(), paramsVar.toArray());
		
		//Object[]转换为DocumentIndex对象
		Page<DocumentIndex> resultPage = new PageImpl<DocumentIndex>(
				Lists.transform(page.getContent(),
						new Function<Object[], DocumentIndex>() {
							@Override
							public DocumentIndex apply(Object[] objs) {
								return new DocumentIndex(objs);
							}
						}), 
				new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		return resultPage;
	}
}
