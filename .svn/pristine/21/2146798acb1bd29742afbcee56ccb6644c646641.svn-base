package com.coamctech.bxloan.service.contractmng.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.ReportExcelUtils;
import com.coamctech.bxloan.commons.utils.excel.ExcelUtils;
import com.coamctech.bxloan.dao.BizExpenseRateDao;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.CodeDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.CustomerAccountManagentDao;
import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.dao.IndustryTypeDao;
import com.coamctech.bxloan.dao.ProductConfigDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.ProjectAssurerInfoDao;
import com.coamctech.bxloan.dao.SubContractDao;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.CustomerAccountManagent;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.model.PayLoanInfoVO;
import com.coamctech.bxloan.service.model.statistics.BizContractVo;
import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowTaskIds;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

@Transactional
@Service
public class ContractMngServiceImpl implements ContractMngService {

	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private DocumentService documentService;;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private BizRateDao bizRateDao;
	@Autowired
	private ProductConfigDao productConfigDao;
	@Autowired
	private CustomerAccountManagentDao customerAccountManagentDao;
	@Autowired
	private BizExpenseRateDao bizExpenseRateDao;
	@Autowired
	private IndustryTypeDao industryTypeDao;
	@Autowired
	private SubContractDao subContractDao;
	@Autowired
	private ProjectAssurerInfoDao projectAssurerInfoDao;
	@Autowired
	private CodeDao codeDao;
	// 注入支持原生sql的查询实现类
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private EcOrgDepartmentDao ecOrgDepartmentDao;
	// 合同表的查询方法
	@SuppressWarnings("unchecked")
	@Override
	public Page findContractBySearch(Integer pageNumber, Integer pageSize,
			List<Object> params) {
		String flag = "4";
		String nowDate = DateTools.dateToString(new Date(), "yyyy-MM-dd");
		String contractStatusCd = String.valueOf(params.get(2));
		String customerName = String.valueOf(params.get(1));
		StringBuffer resultSqlbf = new StringBuffer();
		resultSqlbf
				.append("select  co.contract_id,  co.contract_num ,co.customer_name , b.product_name , ");
		resultSqlbf
				.append("co.contract_amt ,co.contract_Available_Amt , co.contract_balance , ");
		resultSqlbf
				.append(" to_char(co.last_repay_date,'yyyy-MM-dd'), to_char(co.last_upload_time,'yyyy-MM-dd'), ");
		resultSqlbf.append(" pa.project_id, co.contract_status_cd");
		resultSqlbf.append(" from project_application pa , contract co ");
		resultSqlbf
				.append(" join product b on b.product_cd = co.product_type ");
		// 有发起展期的合同，合同使用新的合同ID，否则，使用原来的合同ID
		resultSqlbf.append(" where pa.project_id = co.project_id ");
		if (StringUtils.isNotBlank(contractStatusCd)) {
			if (contractStatusCd.equals("300")) {
				resultSqlbf.append(" and  co.contract_status_cd ='300'  ");// 合同状态
			} else if (contractStatusCd.equals("3")) {
				resultSqlbf
						.append(" and  co.contract_status_cd ='316' and co.contract_Available_Amt > 0 ");// 合同状态
			} else if (contractStatusCd.equals("316")) {
				resultSqlbf
						.append(" and  co.contract_status_cd ='316' and co.contract_Available_Amt = 0 ");// 合同状态
			} else {
				resultSqlbf.append(" and  co.contract_status_cd ='"
						+ contractStatusCd + "'");// 合同状态
			}
			if (contractStatusCd.equals("1") || contractStatusCd.equals("300")) {
				resultSqlbf
						.append(" and (co.contrac_valid_state != '3' or co.contrac_valid_state is null) ");
			} else if (contractStatusCd.equals("425")) {
				resultSqlbf
						.append(" and (co.contrac_valid_state = '3' or co.contrac_valid_state is null) ");
			}
		} else {
			List rs = dataDict.getCodeValList("ContractFindCd", null);
			String condition = rs.toString();
			condition = condition.substring(1, condition.length() - 1);
			resultSqlbf.append("and co.contract_status_cd in (" + condition
					+ ") ");
		}
		if ("1".equals(flag)) {// 逾期一个月内
			resultSqlbf
					.append(" and add_months((select min(os.overdue_date) from overdue_state os where os.contract_id = co.contract_id and os.repayment_date is null),1)>to_date('"
							+ nowDate + "','yyyy-MM-dd')");
		} else if ("2".equals(flag)) {// 逾期1-3个月
			resultSqlbf
					.append(" and add_months((select min(os.overdue_date) from overdue_state os where os.contract_id = co.contract_id and os.repayment_date is null),1)<to_date('"
							+ nowDate
							+ "','yyyy-MM-dd') and add_months((select min(os.overdue_date) from overdue_state os where os.contract_id = co.contract_id and os.repayment_date is null),3)>to_date('"
							+ nowDate + "','yyyy-MM-dd')");
		} else if ("3".equals(flag)) {// 逾期三个月以上
			resultSqlbf
					.append(" and add_months((select min(os.overdue_date) from overdue_state os where os.contract_id = co.contract_id and os.repayment_date is null),3)<to_date('"
							+ nowDate + "','yyyy-MM-dd')");
		}
		if (customerName != null && !"".equals(customerName)) {
			resultSqlbf.append(" and co.customer_name like '%" + customerName
					+ "%'");
		}
		if (customerName != null && !"".equals(customerName)) {
			resultSqlbf.append(" and co.customer_name like '%" + customerName
					+ "%'");
		}
		resultSqlbf.append(" and co.apply_User_Num= to_char(" + params.get(0)
				+ ") ");
		if (StringUtils.isNoneBlank((String) params.get(3))
				&& dataDict.getUniqueOne("CtrlIndicator", "S1").getCodeValue()
						.equals(params.get(3))) {
			resultSqlbf.append(" and (co.last_upload_time is not null) ");
		} else if (StringUtils.isNoneBlank((String) params.get(3))
				&& dataDict.getUniqueOne("CtrlIndicator", "S2").getCodeValue()
						.equals(params.get(3))) {
			resultSqlbf.append(" and (co.last_upload_time is null) ");
		}
		;
		resultSqlbf
				.append(" order by co.sys_update_date desc nulls last, co.last_upload_time desc  nulls last, rownum");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), resultSqlbf.toString());
	}

	@Override
	public Long saveContract(Contract contract) {
		contractDao.save(contract);
		return contract.getContractId();
	}

	@Override
	public Contract getContract(Long contractId) {
		return contractDao.findByContractId(contractId);
	}

	@Override
	public Long countContract() {
		return contractDao.count();
	}

	@Override
	public Boolean existsContract(Long contractId) {
		return contractDao.exists(contractId);
	}

	@Override
	public void deleteContract(Long contractId) {
		contractDao.delete(contractId);
	}

	@Override
	public ProjectApplication getProject(Long projectId) {
		return projectApplicationDao
				.findProjectApplicationByProjectId(projectId);
	}

	@Override
	public BizRate findBizRateByProjectId(Long projectId) {
		return bizRateDao.findByProjectId(projectId.toString());
	}

	@Override
	public Contract findContractByProjectId(Long projectId) {
		return contractDao.findByProjectId(projectId);
	}

	@Override
	public List<CustomerAccountManagent> findAccountsByPartyId(Long partyId) {
		return customerAccountManagentDao.findListByPartyId(new BigDecimal(
				partyId));
	}

	@Override
	public List<String> findAccountNumByAccountId(Long accountId) {
		CustomerAccountManagent cam = customerAccountManagentDao
				.findByAccountId(new BigDecimal(accountId));
		List<String> list = new ArrayList<String>();
		list.add(cam.getAccountNum());
		list.add(cam.getBankAccount());
		return list;
	}

	@Override
	public Page findExpenseBySearch(String projectId, int pageNumber,
			Integer pageSize, List<Object> params) {
		StringBuffer jpql = new StringBuffer(
				"select b from BizExpenseRate b where b.projectId = ?1 ");
		params.add(Long.valueOf(projectId));
		jpql.append("order by b.bizExpenseRateId");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), params.toArray());
	}

	@Override
	public void saveBizRate(BizRate bizRate) {
		bizRateDao.save(bizRate);
	}

	@Override
	public List<String> getLoanerInfo(Long orgId) {
		StringBuffer sql = new StringBuffer(" SELECT ");
		sql.append(" eod.name, eod.principal, dd.address");
		sql.append(" FROM ");
		sql.append(" ec_org_department eod, ec_org_departmentdetails dd ");
		sql.append(" WHERE eod.id= ?1 AND eod.id=dd.id");
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(orgId));
		List<String> valueList = new ArrayList<String>();
		List<Object[]> queryList = dynamicQuery.nativeQuery(sql.toString(),
				params);
		if (queryList != null && queryList.size() > 0) {
			for (int i = 0; i < queryList.get(0).length; i++) {
				valueList.add(queryList.get(0)[i].toString());
			}
		}
		return valueList;
	}

	@Override
	public String codeTransilate(String codeType, String codeValue) {
		return codeDao.transilateCode(codeType, codeValue);
	}

	@Override
	public String findIndustryNameByIndustryCd(String investmentIndustry) {
		return industryTypeDao.findIndustryNameByIndustryCd(investmentIndustry);
	}

	@Override
	public String getCodeByTypeAndValue(String codeType, String contractStatusCd) {
		return codeDao.findCodeByTypeAndValue(codeType, contractStatusCd)
				.get(0).getCodePk().getCodeKey();
	}

	@Override
	public Long getCommonCountByProjectId(String projectId) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT * ");
		sql.append("FROM common_borrow cb ");
		sql.append("WHERE cb.project_id = ?1 ");
		Long count = dynamicQuery.nativeQueryCount(sql.toString(), projectId);
		return count;
	}

	@Override
	public Page findBorrowerListBySearch(String projectId, Integer pageNumber,
			Integer pageSize) {
		//modify by wangyawei 20150426 start 增加客户类型
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT cb.common_borrow_id, ");
		sql.append("cb.customer_name, ");
		sql.append("cb.certificate_type_cd, ");
		sql.append("cb.certificate_num, ");
		sql.append("cb.mobile_phone, ");
		sql.append("cb.phone, ");
		sql.append("cb.address, ");
		sql.append("cb.project_id, ");
		sql.append("cb.spouseFlag, ");
		sql.append("cb.party_id, ");
		sql.append("p.party_type_cd ");
		sql.append("FROM common_Borrow cb, party p ");
		sql.append("WHERE cb.party_id = p.party_id ");
		sql.append("and cb.project_id = ?1 AND  cb.flag = '1' ");
		//modify by wangyawei 20150426 end
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), projectId);
	}

	@Override
	public Page findCollateralsBySearch(String projectId, Integer pageNumber,
			Integer pageSize) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select b from ProjectPawnInfo b where b.projectId = ?1 ");
		jpql.append("ORDER BY b.projectPawnInfoId DESC ");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), new Long(projectId));
	}
	/**
	 * 保证从合同
	 */
	@Override
	public Page getAssureListByContractId(Long contractId, Integer pageNumber,
			Integer pageSize) {
		StringBuffer sql = new StringBuffer("SELECT ");
		sql.append("sc.subcontract_id, ");// 从合同id
		sql.append("sc.subContract_num, ");// 从合同编号
		// sql.append("pai.customer_num, ");//保证人编号
		sql.append("pai.customer_name, ");// 小贷guarantorName 保证人姓名
		sql.append("pai.certificate_type_cd, ");// 证件类型
		sql.append("pai.certificate_num, ");// 证件号
		// sql.append("pai.currency, ");//小贷currency 币种
		sql.append("pai.actual_guarantee_amt, ");// actualGuaranteeAmt 担保债权金额
		sql.append("sc.is_Trans_Document,");// isTransDocument是否上传合同
		sql.append("sc.document_num, ");// 文档编号
		// sql.append("pai.guarantee_amt, ");
		sql.append("pai.project_id, ");
		//add by wangyawei 20150424 start 增加partyId 和 partyTypeCd
		sql.append(" pai.party_id, ").append("(select p.party_type_cd from party p where p.party_id = pai.party_id) ");
		//add by wangyawei 20150424 end
		sql.append("FROM project_assurer_info  pai, subcontract sc ");
		sql.append("WHERE pai.project_assurer_info_id = sc.assurer_id ");
		sql.append("and sc.contract_id = ?1 ");
		sql.append("AND sc.subcontract_type_cd= ?2 ");
		sql.append("ORDER BY pai.project_assurer_info_id ");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), contractId, dataDict.getCodeVal(
				"SubcontractType", "S2"));

	}
	/**
	 * 抵质押从合同
	 */
	@Override
	@Deprecated
	public Page getPawnListByContractId(Long contractId, Long projectId,
			Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer("SELECT ");
		sql.append("sc.subcontract_id, ");
		sql.append("sc.subcontract_num, ");// 从合同编号
		// sql.append(" ppi.guaranty_num, ");//抵质押物编号
		sql.append(" ppi.guaranty_name,");// 抵质押物名称
		sql.append(" ppi.guarantor_name,");// 抵质押人名称
		sql.append(" ppi.eval_value,");// 评估价值
		// sql.append(" ppi.currency,");//币种
		sql.append(" ppi.set_guarantee_amt, ");// 已设定担保额
		sql.append(" ppi.actual_credit_amount, ");// 本次实际担保债权金额
		sql.append(" ppi.guarantee_type, ");// 抵质押类型
		sql.append(" sc.is_Trans_Document,");// isTransDocument是否上传合同
		sql.append(" sc.document_num, ");// 文档编号
		sql.append(" ppi.project_id, ");//
		sql.append(" ppi.guaranty_id, ");//
		sql.append(" co.guaranty_type_cd ");
		// sql.append(" ppi.app_gua_debt_inter_amt, ");
		// sql.append(" ppi.pawn_ratio, ");
		// sql.append(" ppi.guaranty_type_cd,");//质押类型
		// sql.append(" ppi.actual_guarantee_rate, ");//实际担保率
		sql.append(" FROM project_pawn_info  ppi, subcontract sc,  collateral co ");
		sql.append(" WHERE ppi.guaranty_id = sc.guaranty_id ");
		sql.append(" AND co.guaranty_id = ppi.guaranty_id  ");
		sql.append(" AND ppi.project_Id = ?1 ");
		sql.append(" AND sc.project_Id = ?2 ");
		sql.append(" AND ppi.guaranty_id in (");
		sql.append(" SELECT  sc.guaranty_id  FROM subcontract sc WHERE contract_id = ?3 ");
		sql.append(" AND sc.subcontract_type_cd= ?4 )");
		sql.append(" ORDER BY ppi.project_pawn_info_id ");
		List params = new ArrayList();
		params.add(projectId);
		params.add(projectId);
		params.add(contractId);
		params.add(dataDict.getCodeVal("SubcontractType", "S1"));
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}
	/**
	 * 根据主合同id查询抵质押从合同列表
	 * @param contractId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page getPawnListByContractId(Long contractId,Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer("SELECT ");
		sql.append("sc.subcontract_id, ");
		sql.append("sc.subcontract_num, ");// 从合同编号
		// sql.append(" ppi.guaranty_num, ");//抵质押物编号
		sql.append(" ppi.guaranty_name,");// 抵质押物名称
		sql.append(" ppi.guarantor_name,");// 抵质押人名称
		sql.append(" ppi.eval_value,");// 评估价值
		// sql.append(" ppi.currency,");//币种
		sql.append(" ppi.set_guarantee_amt, ");// 已设定担保额
		sql.append(" ppi.actual_credit_amount, ");// 本次实际担保债权金额
		sql.append(" ppi.guarantee_type, ");// 抵质押类型
		sql.append(" sc.is_Trans_Document,");// isTransDocument是否上传合同
		sql.append(" sc.document_num, ");// 文档编号
		sql.append(" ppi.project_id, ");//
		sql.append(" ppi.guaranty_id, ");//
		sql.append(" ppi.guaranty_type_cd");//抵质押类型
		sql.append(" FROM project_pawn_info  ppi, subcontract sc");
		sql.append(" WHERE ppi.project_pawn_info_id = sc.guaranty_id ");
	/*	sql.append(" AND ppi.project_pawn_info_id in (");
		sql.append(" SELECT  sc.guaranty_id  FROM subcontract sc");
		sql.append(" WHERE contract_id = ?1 ");
		sql.append(" AND sc.subcontract_type_cd= ?2 )");*/
		sql.append("and sc.contract_id=?1 ");
		sql.append("and sc.subcontract_type_cd=?2 ");
		sql.append(" ORDER BY ppi.project_pawn_info_id ");
		List<Object> params = Lists.newArrayList();
		params.add(contractId);
		params.add(dataDict.getCodeVal("SubcontractType", "S1"));
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber,
				pageSize), sql.toString(), params.toArray());
	}

	@Override
	public List findSubcontractList(Long projectId) {
		return this.subContractDao.findByProjectId(projectId);
	}

	@Override
	public List<ProjectAssurerInfo> findProjectAssurerByPid(Long projectId) {
		return this.projectAssurerInfoDao.findByProjectId(projectId);
	}

	@Override
	public void saveSubContract(SubContract subcontract) {
		this.subContractDao.save(subcontract);
	}

	@Override
	public void saveProjectAssurerInfo(ProjectAssurerInfo pai) {
		this.projectAssurerInfoDao.save(pai);
	}

	@Override
	public BigDecimal caculatePledgeSumByProjectId(Long projectId,
			String state, String type) {

		BigDecimal total = new BigDecimal("0");
		BigDecimal assuerAmt = new BigDecimal("0");
		List params = new ArrayList();
		params.add(projectId);

		if (null == projectId) {
			total = new BigDecimal("-1");
		}

		if (StringUtils.isBlank(type)) {
			total = new BigDecimal("-1");
		}
		StringBuffer sql = new StringBuffer("select ");
		// 申请时抵质押物总和
		if (type.equals(dataDict.getCodeVal("CtrlIndicator", "S1"))) {
			sql.append(" sum(pw.app_gua_debt_inter_amt) ");
			// 合同时实际抵质押物总和
		} else if (type.equals(dataDict.getCodeVal("CtrlIndicator", "S2"))) {
			sql.append(" sum(pw.actual_credit_amount) ");
		}
		sql.append(" as guarantee_amt from project_pawn_info pw where  pw.project_id= ?1 ");
		// 抵质押物状态
		if (StringUtils.isNotBlank(state)) {
			sql.append(" and pw.pawn_state in (?2) ");
			params.add(state);
		}

		total = dynamicQuery.nativeQuerySingleResult(BigDecimal.class,
				sql.toString(), params.toArray());
		return total;
	}

	@Override
	public String findWorkFlowTypeByProductCd(String productType) {
		return productConfigDao.findWfCodeByProductCd(new Long(productType));
	}

	@Override
	public Result submitWorkFlow(Long projectId, String workFlowType,
			String workflowId, String taskId, String userId, String nextUser,
			String nextUserOrgId, String comments) {
		//
		Set<String> params = ImmutableSet.of("05", "27", "10", "40", "41",
				"42", "44", "45", "13", "14", "15", "16", "17", "29");
		documentService.updateDocumentIndexByProjectId(projectId, params,
				dataDict.getCodeVal("CreateType", "S2"));
		NextTaskReceiver receiver = null;
		if (StringUtils.isBlank(nextUser)) {
			TypedResult<List<NextTaskReceiver>> resultList = this.workFlowService
					.getNextTaskReceivers(taskId, ActionCode.COMMIT);
			for (NextTaskReceiver rec : resultList.getData()) {
				if (receiver == null
						|| Integer.parseInt(receiver.getTotal()) > Integer
								.parseInt(rec.getTotal())) {
					receiver = rec;
					nextUser = receiver.getLogName();
				}
			}
		}

		if (Integer.parseInt(workFlowType) == GlobalConstants.WD_WF_TYPE_40_INT) {// 微贷
			TypedResult<WorkFlowTaskIds> wfResult = this.workFlowService
					.executeTask(ExeTaskParam.newExeTaskParam(
							WorkFlowService.WorkFlowCode.MICRO_LOAN,
							workflowId, taskId, userId,
							WorkFlowNode.ML_SignedContract, ActionCode.COMMIT,
							nextUser, comments, "", nextUserOrgId));

			if (!wfResult.getSuccess()) {
				return new Result(false, "流程接口返回false，请联系管理员", null);
			}
		} else if (Integer.parseInt(workFlowType) == GlobalConstants.EL_WF_TYPE_30_INT) {// 易贷
			TypedResult<WorkFlowTaskIds> wfResult = this.workFlowService
					.executeTask(ExeTaskParam.newExeTaskParam(
							WorkFlowService.WorkFlowCode.EASY_LOAN, workflowId,
							taskId, userId, WorkFlowNode.EL_CheckPayLoan,
							ActionCode.COMMIT, nextUser, comments, "",
							nextUserOrgId));

			if (!wfResult.getSuccess()) {
				return new Result(false, "流程接口返回false，请联系管理员", null);
			}
		} else {
		}

		return new Result(true, "合同提交成功！", null);
	}

	@Override
	public SubContract findSubContractBySubId(Long subcontractId) {
		return this.subContractDao.findOne(subcontractId);
	}

	@Override
	public Contract findByContractNum(String contractNum) {
		StringBuffer hql = new StringBuffer(
				"from Contract c where c.contractNum = ?1 order by c.sysUpdateDate desc");
		List<Object> params = new ArrayList<Object>();
		params.add(contractNum);
		List<Contract> contracts = dynamicQuery.query(Contract.class,
				hql.toString(), params.toArray());
		return contracts.get(0);
	}

	@Override
	public SubContract findByDocumentNum(String documentNum) {
		List<SubContract> list = subContractDao.findByDocumentNum(documentNum);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<DocumentIndex> findDocumentIndexByPartyId(Long partyId,
			Long projectId) {
		return documentService.findByPartyIdAndProject(partyId, projectId);
	}
	
	/**
	 * 组装根据合同编号查询放款记录sql 
	 * 
	 * @return
	 */
	public String buildPayLoanRecordSearchSql(){
		//组装查询sql
		StringBuffer sql = new StringBuffer();
		sql.append(" select pi.pay_loan_id, pi.pay_loan_num,  pi.loan_regist_time, ")
			.append(" pi.loan_amt, pi.sum_amt, pi.pay_status_cd, pi.loan_actul_time, ")
			.append(" (select eop.name from ec_org_person eop where eop.id = pi.apply_user_num), ")
			.append(" pi.create_date, (select eod.name from ec_org_department eod where eod.id = pi.apply_org_id), ")
			.append(" (select eoe.name from ec_org_person eoe where eoe.id = c.apply_user_num) ")
			.append(" from pay_loan_info pi ")
			.append("  left join contract c ")
			.append("  on pi.contract_id = c.init_contract_id ")
			.append(" where pi.contract_id = ?1 ")
			.append(" order by pi.pay_loan_num desc ");
		
		return sql.toString();
	}
	
	/**
	 * 根据合同Id获取该笔合同的放款记录
	 * 
	 * @param contractId 合同Id
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Page<Object[]> findPayLoanRecordSearch(Integer pageNumber, Integer pageSize, Long contractId){
		List<Object> params = new ArrayList<Object>(); //sql所用参数
		if(contractId != null){
			params.add(contractId);
		}
		//组装查询放款记录sql
		String sql = this.buildPayLoanRecordSearchSql();
		
		Page<Object[]> page = dynamicQuery.nativeQuery(new PageRequest(pageNumber, pageSize), sql, params.toArray());
		return page;
	}
	
	/**
	 * 根据Page对象获取该笔合同的放款记录
	 * 
	 * @param page 
	 * @return
	 */
	@Override
	public List<PayLoanInfoVO> findPayLoanRecordInfo(Page<Object[]> page){
		List<PayLoanInfoVO> payLoanInfoList = 
			Lists.transform(page.getContent(), new Function<Object[], PayLoanInfoVO>() {
				@Override
				public PayLoanInfoVO apply(Object[] objs) {
					return new PayLoanInfoVO(objs);
				}
			});
		return payLoanInfoList;
	}

	@Override
	public Page<BizContractVo> findContractBySearch(Integer pageNumber,
			Integer pageSize, BizContractVo vo) {
		StringBuffer stringBuffer = new StringBuffer();
		List<Object> params = new ArrayList<Object>(0);
		this.dealConditionJudge(params,stringBuffer,vo);
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), stringBuffer.toString(),params.toArray());

		Page<BizContractVo> resultPage = new PageImpl<BizContractVo>(
				Lists.transform(page.getContent(),
						new Function<Object[], BizContractVo>() {
							@Override
							public BizContractVo apply(Object[] objs) {
								return new BizContractVo(objs);
							}
						}), new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		this.changeModel(resultPage);
		return resultPage;
	}
	/**
	 * 客户查询-->合同台账
	 */
	@Override
	public Page<BizContractVo> findContractByCondition(Integer pageNumber,
			Integer pageSize, BizContractVo vo, String partyId) {
		StringBuffer stringBuffer = new StringBuffer();
		List<Object> params = new ArrayList<Object>(0);
		this.dealConditionJudgeForCustomerQuery(params,stringBuffer,vo,partyId);
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), stringBuffer.toString(),params.toArray());

		Page<BizContractVo> resultPage = new PageImpl<BizContractVo>(
				Lists.transform(page.getContent(),
						new Function<Object[], BizContractVo>() {
							@Override
							public BizContractVo apply(Object[] objs) {
								return new BizContractVo(objs);
							}
						}), new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		this.changeModel(resultPage);
		return resultPage;
	}
	
	/**
	 * 属性转换
	 * @param resultPage
	 */
	public void changeModel(Page<BizContractVo> resultPage){
		List<BizContractVo> dataList = resultPage.getContent();
		for(BizContractVo infoVO : dataList){
			String contractStatus = infoVO.getContractStatus();
			if(StringUtils.isNoneBlank(contractStatus)){//合同状态名称
				String contractStatusName = dataDict.getCodeName("ContractStatusCode", String.valueOf(contractStatus));
				infoVO.setContractStatusName(contractStatusName);
			}
			String contractTerm = infoVO.getContractTerm();
			String contractTermUnit = infoVO.getContractTermUnit();
			if(StringUtils.isNoneBlank(contractTerm) && StringUtils.isNoneBlank(contractTermUnit) ){//合同期限(加单位)
				contractTermUnit = dataDict.getCodeName("TermUnitCd", String.valueOf(contractTermUnit));
				String contractTermName = contractTerm + contractTermUnit;
				infoVO.setContractTermName(contractTermName);
			}
			String cdsGuarantMode = infoVO.getCdsGuarantMode();
			if(StringUtils.isNoneBlank(cdsGuarantMode)){//担保方式名称
				String cdsGuarantModeName = "";
				if(cdsGuarantMode.contains(",")){
					String[] cdsGuarantModeArray = cdsGuarantMode.split(",");
					for(int i=0;i<cdsGuarantModeArray.length;i++){
						if(i==0){
							cdsGuarantModeName += dataDict.getCodeName("CdsGuarantMode", String.valueOf(cdsGuarantModeArray[i]));
						}else{
							cdsGuarantModeName += "，"+dataDict.getCodeName("CdsGuarantMode", String.valueOf(cdsGuarantModeArray[i]));
						}
					}
				}else{
					cdsGuarantModeName = dataDict.getCodeName("CdsGuarantMode", String.valueOf(cdsGuarantMode));
				}
				infoVO.setCdsGuarantModeName(cdsGuarantModeName);
			}
		}
	}
	/**
	 * 得到合同状态编号拼接字符串
	 * @return
	 */
	private String getContractStatusValStr(){
		Collection<CodeData> contractStatusList = dataDict.getOneType("ContractStatusCode");
		Iterator<CodeData> its = contractStatusList.iterator();
		StringBuffer sbf = new StringBuffer();
		while (its.hasNext()) {
			CodeData codeData = (CodeData) its.next();
			//合同状态不等于'失效','否决','审批中'
			if(!codeData.getCodeValue().equals("331") && !codeData.getCodeValue().equals("425") && !codeData.getCodeValue().equals("500") && !codeData.getCodeValue().equals("521")){
				sbf.append(codeData.getCodeValue()+",");
			}
		}
		return StringUtils.substringBeforeLast(sbf.toString(), ",");
	}
	
	/**
	 * 合同台账条件处理
	 * 
	 * @param params
	 *            参数
	 * @param sb
	 *            StringBuffer
	 */
	public void dealConditionJudge(List<Object> params, StringBuffer sb, BizContractVo vo) {
		int index = 1;
		sb.append("select pa.init_project_id,pa.project_id,c.init_contract_id,c.contract_id,eod.name,");
		sb.append("c.party_id,c.contract_num,c.customer_name,c.product_type,c.contract_status_cd,c.contract_amt,");
		sb.append("case");
		sb.append(" when c.contract_term_unit_total = '1' then");
		sb.append(" 	c.contract_term_total * 360");
		sb.append(" when c.contract_term_unit_total = '2' then");
		sb.append(" 	c.contract_term_total * 30");
		sb.append(" else");
		sb.append("		c.contract_term_total * 1 ");
		sb.append("end,");
		sb.append("c.contract_term_total,c.contract_term_unit_total,c.contract_balance,pa.guarantee_mode,c.payloan_date,");
		sb.append("case");
		sb.append(" when c.contract_term_unit_total = '1' then");
		sb.append(" 	add_months(c.payloan_date, c.contract_term_total * 12)");
		sb.append(" when c.contract_term_unit_total = '2' then");
		sb.append(" 	add_months(c.payloan_date, c.contract_term_total)");
		sb.append(" else");
		sb.append("		c.payloan_date + c.contract_term_total ");
		sb.append("end,");
		sb.append("eop.name customer_manager_name,");
		sb.append("(select pd.product_name from product pd where pd.product_cd = c.product_type) ");
		sb.append(" from contract c, project_application pa, party p, ec_org_department eod,ec_org_person eop");
		sb.append(" where pa.party_id = p.party_id and c.project_id = pa.project_id and pa.SYS_CD in('1','2') and eod.id=c.apply_org_id ");
		sb.append(" and c.apply_user_num = eop.id ");
		sb.append(" and c.contract_status_cd in ("+this.getContractStatusValStr()+") ");
//		sb.append("and c.contract_status_cd in (300, 316, 330, 331, 422, 421, 423, 424) ");
		
		
		switch (vo.getDataAuthType()) {
		case CustomerManager://客户经理
			sb.append(" and c.apply_user_num = ?").append(index++);
			params.add(String.valueOf(vo.getLoginUserId()));
			break;
		default:
			break;
		}
		
		if(vo.getOrgId()!=null){//
			sb.append(" and c.apply_org_id = ?").append(index++);
			params.add(vo.getOrgId());
		}else{
			sb.append(" and c.apply_org_id in(?").append(index++).append(")");
			params.add(vo.getDataAuthOrgIds());
		}
		if(StringUtils.isNotEmpty(vo.getCustomerName())){//客户名称
			sb.append(" and c.customer_name like ?").append(index++);
			params.add("%"+vo.getCustomerName()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getCustomerType())){//客户类型
			sb.append(" and p.party_type_cd=?").append(index++);
			params.add(vo.getCustomerType());
		}
		if(StringUtils.isNotEmpty(vo.getCustomerNum())){//客户编号
			sb.append(" and c.customer_num like ?").append(index++);
			params.add("%"+vo.getCustomerNum()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getCustomerMng())){//客户经理
			sb.append(" and eop.name like ?").append(index++);
			params.add("%"+vo.getCustomerMng()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getContractNum())){//合同编号
			sb.append(" and c.contract_num like ?").append(index++);
			params.add("%"+vo.getContractNum()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getContractTerm()) && StringUtils.isNotEmpty(vo.getContractTermUnit())){//合同期限&单位
			sb.append(" and c.contract_term=?").append(index++);
			params.add(Integer.parseInt(vo.getContractTerm()));
			sb.append(" and c.contract_term_unit=?").append(index++);
			params.add(vo.getContractTermUnit());
		}
		if(StringUtils.isNotEmpty(vo.getContractStatus())){//合同状态
			sb.append(" and c.contract_status_cd =?").append(index++);
			params.add(vo.getContractStatus());
		}
		if(StringUtils.isNotEmpty(vo.getCdsGuarantMode())){//担保方式
			sb.append(" and pa.guarantee_mode like ?").append(index++);
			params.add("%"+vo.getCdsGuarantMode()+"%");
			
		}if(StringUtils.isNotEmpty(vo.getIsInsure())){//是否有保险
			sb.append(" and pa.if_insure=?").append(index++);
			params.add(vo.getIsInsure());
		}
		if(StringUtils.isNotEmpty(vo.getIsHeadcol())){//是否总部协同业务
			sb.append(" and pa.isheadcol=?").append(index++);
			params.add(vo.getIsHeadcol());
		}
		if(vo.getLoanStartTime() != null && vo.getLoanEndTime() == null){//放款开始时间
			sb.append(" and c.payloan_date>=?").append(index++);
			params.add(vo.getLoanStartTime());
		}
		if(vo.getLoanStartTime() == null && vo.getLoanEndTime() != null){//放款结束时间
			sb.append(" and c.payloan_date<=?").append(index++);
			params.add(vo.getLoanEndTime());
		}
		if(vo.getLoanStartTime() != null && vo.getLoanEndTime() != null){//放款起止时间
			sb.append(" and c.payloan_date >=?").append(index++);
			params.add(vo.getLoanStartTime());
			sb.append(" and c.payloan_date <=?").append(index++);
			params.add(vo.getLoanEndTime());
		}
		sb.append(" order by c.contract_num desc");
	}
	public void dealConditionJudgeForCustomerQuery(List<Object> params, StringBuffer sb, BizContractVo vo,String partyId) {
		int index = 1;
		sb.append("select pa.init_project_id,pa.project_id,c.init_contract_id,c.contract_id,eod.name,");
		sb.append("c.party_id,c.contract_num,c.customer_name,c.product_type,c.contract_status_cd,c.contract_amt,");
		sb.append("case");
		sb.append(" when c.contract_term_unit_total = '1' then");
		sb.append(" 	c.contract_term_total * 360");
		sb.append(" when c.contract_term_unit_total = '2' then");
		sb.append(" 	c.contract_term_total * 30");
		sb.append(" else");
		sb.append("		c.contract_term_total * 1 ");
		sb.append("end,");
		sb.append("c.contract_term_total,c.contract_term_unit_total,c.contract_balance,pa.guarantee_mode,c.payloan_date,");
		sb.append("case");
		sb.append(" when c.contract_term_unit_total = '1' then");
		sb.append(" 	add_months(c.payloan_date, c.contract_term_total * 12)");
		sb.append(" when c.contract_term_unit_total = '2' then");
		sb.append(" 	add_months(c.payloan_date, c.contract_term_total)");
		sb.append(" else");
		sb.append("		c.payloan_date + c.contract_term_total ");
		sb.append("end,");
		sb.append("(select eop.name from ec_org_person eop where c.apply_user_num = eop.id),");
		sb.append("(select pd.product_name from product pd where pd.product_cd = c.product_type) ");
		sb.append("from contract c, project_application pa, party p,ec_org_department eod ");
		sb.append("where pa.party_id = p.party_id and c.project_id = pa.project_id and pa.SYS_CD in('1','2') and eod.id=c.apply_org_id ");
		sb.append("and c.contract_status_cd in ("+this.getContractStatusValStr()+") ");
//		sb.append("and c.contract_status_cd in (300, 316, 330, 331, 422, 421, 423, 424) ");
		
		if(StringUtils.isNotEmpty(partyId)) {
			sb.append(" and c.party_id =?").append(index++);
			params.add(Long.valueOf(partyId));
		}
		
		if (vo.getOrgId() != null && vo.getOrgId() > 0) {// 机构
			sb.append(" and c.apply_org_id =?").append(index++);
			params.add(vo.getOrgId());
		}
		if(StringUtils.isNotEmpty(vo.getCustomerName())){//客户名称
			sb.append(" and c.customer_name like ?").append(index++);
			params.add("%"+vo.getCustomerName()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getCustomerType())){//客户类型
			sb.append(" and p.party_type_cd=?").append(index++);
			params.add(vo.getCustomerType());
		}
		if(StringUtils.isNotEmpty(vo.getCustomerNum())){//客户编号
			sb.append(" and c.customer_num like ?").append(index++);
			params.add("%"+vo.getCustomerNum()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getCustomerMng())){//客户经理
			sb.append(" and pa.customer_manager_name like ?").append(index++);
			params.add("%"+vo.getCustomerMng()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getContractNum())){//合同编号
			sb.append(" and c.contract_num like ?").append(index++);
			params.add("%"+vo.getContractNum()+"%");
		}
		if(StringUtils.isNotEmpty(vo.getContractTerm()) && StringUtils.isNotEmpty(vo.getContractTermUnit())){//合同期限&单位
			sb.append(" and c.contract_term=?").append(index++);
			params.add(Integer.parseInt(vo.getContractTerm()));
			sb.append(" and c.contract_term_unit=?").append(index++);
			params.add(vo.getContractTermUnit());
		}
		if(StringUtils.isNotEmpty(vo.getContractStatus())){//合同状态
			sb.append(" and c.contract_status_cd =?").append(index++);
			params.add(vo.getContractStatus());
		}
		if(StringUtils.isNotEmpty(vo.getCdsGuarantMode())){//担保方式
			sb.append(" and pa.guarantee_mode like ?").append(index++);
			params.add("%"+vo.getCdsGuarantMode()+"%");
			
		}if(StringUtils.isNotEmpty(vo.getIsInsure())){//是否有保险
			sb.append(" and pa.if_insure=?").append(index++);
			params.add(vo.getIsInsure());
		}
		if(StringUtils.isNotEmpty(vo.getIsHeadcol())){//是否总部协同业务
			sb.append(" and pa.isheadcol=?").append(index++);
			params.add(vo.getIsHeadcol());
		}
		if(vo.getLoanStartTime() != null && vo.getLoanEndTime() == null){//放款开始时间
			sb.append(" and c.payloan_date>=?").append(index++);
			params.add(vo.getLoanStartTime());
		}
		if(vo.getLoanStartTime() == null && vo.getLoanEndTime() != null){//放款结束时间
			sb.append(" and c.payloan_date<=?").append(index++);
			params.add(vo.getLoanEndTime());
		}
		if(vo.getLoanStartTime() != null && vo.getLoanEndTime() != null){//放款起止时间
			sb.append(" and c.payloan_date >=?").append(index++);
			params.add(vo.getLoanStartTime());
			sb.append(" and c.payloan_date <=?").append(index++);
			params.add(vo.getLoanEndTime());
		}
		sb.append(" order by c.contract_num desc");
	}
	@Override
	public String ExportExcel(BizContractVo vo) throws Exception {
		//查询，//默认查询10000条
		int PAGE_SIZE = 100000;
		String EXCEL_TPL = "wd_contract_tpl.xlsx";//Excel模板
		String dfzcReportPath = StringUtils.join(ExcelUtils.getTemplatePath(),File.separator);
		String EXCEL_TPL_PATH = dfzcReportPath + EXCEL_TPL;
		//查询合同台账列表
		Page<BizContractVo> page = this.findContractBySearch(0,PAGE_SIZE, vo);
		if(page == null || page.getContent() == null || page.getContent().size() == 0){
			throw new RuntimeException("无资料,不能导出报表,请确认!");
		}
		Map map = new HashMap();
		List<BizContractVo> dataList = page.getContent();
		List<BizContractVo> contractReportList = null;
		for(BizContractVo infoVO : dataList){
			if(null == contractReportList){
				contractReportList = new ArrayList<BizContractVo>();
			}
			contractReportList.add(infoVO);
		}
		map.put("contractReportList", contractReportList);
		ReportExcelUtils reportExcelUtils= new ReportExcelUtils();
		//InputStream is = FileUtils.getResourceAsStream(EXCEL_TPL_PATH);
		File file = new File(EXCEL_TPL_PATH);
		InputStream is = new FileInputStream(file);
		String fileName = reportExcelUtils.genernateExcelFileName(is,dfzcReportPath,"合同台账", map);
		return fileName;
	}
	@Override
	public String getApplyOrgName(Long contractId) {
		
		return ecOrgDepartmentDao.findNameByContractId(contractId);
	}
}









