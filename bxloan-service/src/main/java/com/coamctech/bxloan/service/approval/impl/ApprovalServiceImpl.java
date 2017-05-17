package com.coamctech.bxloan.service.approval.impl;

import static com.coamctech.bxloan.commons.GlobalConstants.WF_SCHEMA;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.commons.utils.MoneyRateUtils;
import com.coamctech.bxloan.dao.ApprovalHistoryBxloanDao;
import com.coamctech.bxloan.dao.ApprovalHistoryRepayPlanDao;
import com.coamctech.bxloan.dao.ApprovalOpinionDao;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.DocumentIndexDao;
import com.coamctech.bxloan.dao.ProductConfigDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.RepayPlanDao;
import com.coamctech.bxloan.dao.RepayPlanTempDao;
import com.coamctech.bxloan.dao.SubContractDao;
import com.coamctech.bxloan.dao.TbMessageDao;
import com.coamctech.bxloan.dao.TbMultiopinionDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ApprovalHistoryBxloan;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.ApprovalOpinion;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RepayPlan;
import com.coamctech.bxloan.entity.RepayPlanTemp;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.TbMessage;
import com.coamctech.bxloan.entity.TbMultiopinion;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.creditcontractmng.CreditContractMngService;
import com.coamctech.bxloan.service.creditcontractmng.UnderCreditContractMngService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.message.MessageCenterService;
import com.coamctech.bxloan.service.model.ApprovalContentVO;
import com.coamctech.bxloan.service.model.LoanTrialVo;
import com.coamctech.bxloan.service.model.ProcessSuggestionVO;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ElApprInfoVo;
import com.coamctech.bxloan.service.model.approval.ElProjApplVo;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;
import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowTaskIds;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.google.common.collect.Sets;

@Service
@Transactional
public class ApprovalServiceImpl implements ApprovalService {
	/**
	 * 利率类型
	 */
	private final String INTEREST_RATE_ADJUSTMENT = "InterestRateAdjustment";

	/** 合同状态-300 */
	private final String CONTRACT_STATUS_CD_300 = "300";
	/** 业务状态-5 */
	private final String PROJECT_STATUS_5 = "5";

	@Autowired
	private ProjectApplicationDao proAppDao;

	@Autowired
	private ContractDao contractDao;

	@Autowired
	private DocumentIndexDao documentIndexDao;

	@Autowired
	private ProjectApplicationDao projectApplicationDao;

	@Autowired
	private DynamicQuery query;

	@Autowired
	private ProductConfigDao productConfigDao;

	@Autowired
	private BizRateDao bizRateDao;

	@Autowired
	private TbMessageDao msgDao;

	@Autowired
	private SubContractDao subContractDao;

	@Autowired
	private ApprovalHistoryBxloanDao approvalHistoryBxloanDao;

	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private DataDict dataDict;

	@Autowired
	private TbMultiopinionDao tbMultiopinionDao;
	@Autowired
	private ApprovalOpinionDao approvalOpinionDao;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ApprovalHistoryRepayPlanDao approvalHistoryRepayPlanDao;
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	@Autowired
	private CollateralDao collateralDao;
	@Autowired
	private MessageCenterService messageCenterService;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private RepayPlanDao repayPlanDao;
	@Autowired
	private RepayPlanTempDao repayPlanTempDao;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	@Autowired
	private WfActionTreeNodeService wfActionTreeNodeService;
	@Autowired
	private UnderCreditContractMngService underCreditContractMngService;
	@Autowired
	private CreditContractMngService creditContractMngService;
	@Override
	public ApprovalContentVO findApproValContentByProjectId(Long projectId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("pa.project_no as project_no, ");
		sql.append("pa.customer_name as customer_name, ");
		sql.append("pr.product_name as product_name, ");
		sql.append("pa.apply_amt as apply_amt, ");//申报金额
		sql.append(" pa.apply_term||tt.code_name   as apply_term, ");//期限
		sql.append("pa.approve_amt as approve_amt, ");//批复金额
		sql.append("pa.applicant as applicant, ");
		sql.append("pa.apply_date as apply_date, ");
		sql.append("pa.biz_rate  as biz_rate, ");//年利率
		sql.append("pa.party_id as paParty, ");
		sql.append("pa.CUSTOMER_MANAGER_NAME as customerManagerName, ");
		sql.append("c.code_name as repaying_mode,");
		sql.append("pa.guarantee_mode, ");
		sql.append("it.INDUSTRY_TYPE_NAME, ");
		sql.append("eod.name as org_name ");
		sql.append("FROM ");
		sql.append("project_application pa ");
		sql.append("LEFT JOIN individual iv ");
		sql.append("ON iv.CUSTOMER_NUM=pa.CUSTOMER_NUM ");
		sql.append("LEFT JOIN product pr ");
		sql.append("ON pr.product_cd=pa.product_type ");
		sql.append("LEFT JOIN code c  ");
		sql.append("ON c.code_value = pa.repaying_mode And c.code_type='RepaymentMode' ");
		sql.append("LEFT JOIN code tt  ");
		sql.append("ON tt.code_value = pa.apply_term_unit ");
		sql.append("And tt.code_type = 'TermUnitCd' ");
		sql.append("LEFT JOIN INDUSTRY_TYPE it ");
		sql.append("ON pa.INVESTMENT_INDUSTRY = it.INDUSTRY_TYPE_CD ");
		sql.append("LEFT JOIN ec_org_department eod  ");
		sql.append("ON eod.id = pa.apply_org ");
		
		sql.append("WHERE ");
		sql.append("pa.project_id=?1 ");
		List<Object[]> resultSet = this.query.nativeQuery(sql.toString(),
				projectId);
		if (CollectionUtils.isEmpty(resultSet)) {
			return null;
		}

		return new ApprovalContentVO(resultSet.get(0));
	}
	
	@Override
	public ApprovalContentVO findApproValContentByProjectId_EasyLoan(Long projectId) {
		/*pa.setTerm(apprInfo.getProposalTerm());
		pa.setTermUnit(apprInfo.getProposalTermUnit());// 月
		pa.setApproveRepayingMode(pa.getRepayingMode());
		br.setFinalIrTypeCd(inteRateTypeCd);
		br.setFinalRateValue(yearIr);*/
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("pa.project_no as project_no, ");
		sql.append("pa.customer_name as customer_name, ");
		sql.append("pr.product_name as product_name, ");
		sql.append("pa.apply_amt as apply_amt, ");//申报金额
		//sql.append(" pa.apply_term||tt.code_name   as apply_term, ");//期限
		sql.append(" pa.term||tt.code_name   as apply_term, ");//期限
		sql.append("pa.approve_amt as approve_amt, ");//批复金额
		sql.append("pa.applicant as applicant, ");
		sql.append("pa.apply_date as apply_date, ");
		//sql.append("pa.biz_rate  as biz_rate, ");//年利率
		sql.append("br.final_rate_value as biz_rate, ");//年利率
		sql.append("pa.party_id as paParty, ");
		sql.append("pa.CUSTOMER_MANAGER_NAME as customerManagerName, ");
		sql.append("c.code_name as repaying_mode,");
		sql.append("pa.guarantee_mode, ");
		sql.append("it.INDUSTRY_TYPE_NAME, ");
		sql.append("eod.name as org_name ");
		sql.append("FROM ");
		sql.append("project_application pa ");
		sql.append("LEFT JOIN individual iv ");
		sql.append("ON iv.CUSTOMER_NUM=pa.CUSTOMER_NUM ");
		sql.append("LEFT JOIN product pr ");
		sql.append("ON pr.product_cd=pa.product_type ");
		sql.append("LEFT JOIN code c  ");
		//sql.append("ON c.code_value = pa.repaying_mode And c.code_type='RepaymentMode' ");
		sql.append("ON c.code_value = pa.approve_repaying_mode And c.code_type='RepaymentMode' ");
		sql.append("LEFT JOIN code tt  ");
		sql.append("ON tt.code_value = pa.apply_term_unit ");
		sql.append("And tt.code_type = 'TermUnitCd' ");
		sql.append("LEFT JOIN INDUSTRY_TYPE it ");
		sql.append("ON pa.INVESTMENT_INDUSTRY = it.INDUSTRY_TYPE_CD ");
		sql.append("LEFT JOIN ec_org_department eod  ");
		sql.append("ON eod.id = pa.apply_org ");
		
		//2015-04-30沈祚鑫新增
		sql.append("LEFT JOIN biz_rate br  ");
		sql.append("ON br.project_id = pa.project_id ");
		
		sql.append("WHERE ");
		sql.append("pa.project_id=?1 ");
		List<Object[]> resultSet = this.query.nativeQuery(sql.toString(),
				projectId);
		if (CollectionUtils.isEmpty(resultSet)) {
			return null;
		}

		return new ApprovalContentVO(resultSet.get(0));
	}

	@Override
	public LoanTrialVo findLoanByProjId(Long projId) {
		ProjectApplication projApp = proAppDao.findOne(projId);
		return new LoanTrialVo(projApp);
	}

	@Override
	@Deprecated
	public String endWorkFlowAndUpdateContract(String workflowId,
			String taskId, String comments, String curUserLogonName,
			String curUserName, String nextUser) throws Exception {

		this.contractDao.updateContractStatusCdByWorkFlowId(
				Long.parseLong(workflowId), "300");

		this.proAppDao.updateProjectStatusByWorkflowId(
				Long.parseLong(workflowId), "5");

		this.sendMessage(workflowId, curUserLogonName, curUserName, "审批通过",
				GlobalConstants.MSG_TYPE_APPROVAL_FINISH);

		this.updateDocumentIndex(workflowId, true);

		// WorkflowProcess workflowProcess = invoker.sendProcess(workflowType,
		// workflowId, curUserLogonName, taskTypeId, taskId, actionCode,
		// nextUser,
		// StringUtils.trimToEmpty(comments));
		// if (StringUtils.isBlank(workflowProcess.getWorkflowId())) {
		// throw new RuntimeException("落实放款工作流返回结果为空");
		// }
		// return workflowProcess.getWorkflowId();
		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(
				WorkFlowService.WorkFlowCode.EASY_LOAN, workflowId, taskId,
				curUserLogonName, WorkFlowNode.EL_FulfillPayLoan,
				ActionCode.COMMIT, nextUser, comments, "");
		if (!result.getSuccess()) {
			throw new RuntimeException("落实放款工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}
	@Override
	public String endWorkFlowAndUpdateContract(BizApprovalOfWfParam wfParam)
			throws Exception {
		String workflowIdStr=wfParam.getWorkflowId();
		Long workflowId=Long.parseLong(wfParam.getWorkflowId());
		this.contractDao.updateContractStatusCdByWorkFlowId(
				workflowId, "300");

		this.proAppDao.updateProjectStatusByWorkflowId(
				workflowId, "5");

//		this.sendMessage(workflowIdStr, wfParam.getCurUserLogName(), wfParam.getCurUserName(), "审批通过",
//				GlobalConstants.MSG_TYPE_APPROVAL_FINISH);
		this.messageCenterService.sendPassedMsg(workflowId, wfParam.getCurUserLogName(), wfParam.getCurUserName());
		
		this.updateDocumentIndex(wfParam.getWorkflowId(), true);
		TypedResult<WorkFlowTaskIds> result = this.workFlowService
				.executeTask(wfParam.toExeTaskParam(
						WorkFlowService.WorkFlowCode.EASY_LOAN,
						WorkFlowNode.EL_FulfillPayLoan, ActionCode.COMMIT));
		if (!result.getSuccess()) {
			throw new RuntimeException("落实放款工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}

	private void sendMessage(String workflowId, String curUserLogonName,
			String curUserName, String contentSuffix, Long msgTypeCd) {
		TbMessage msg = new TbMessage();
		Long workflowIdLong = Long.parseLong(workflowId);
		ProjectApplication proApp = this.proAppDao
				.findProjectApplicationByWorkflowId(workflowIdLong);
		Contract contract = this.contractDao.findByProjectId(proApp
				.getProjectId());
		Long contractId = contract == null ? null : contract.getContractId();

		StringBuilder content = new StringBuilder();
		content.append(proApp.getCustomerName()).append("业务编号:")
				.append(proApp.getProjectNo()).append(",贷款金额")
				.append(proApp.getApplyAmt()).append(contentSuffix);

		msg.setTitle(contentSuffix);
		msg.setContent(content.toString());
		msg.setWorkitemid(workflowIdLong);
		msg.setPartyId(proApp.getPartyId());
		msg.setContractid(contractId);
		msg.setSendtime(new Date());
		msg.setProjectid(proApp.getProjectId());
		msg.setReceiver(proApp.getCustomerManagerNum());
		msg.setReceivername(proApp.getCustomerManagerName());
		msg.setSender(curUserLogonName);
		msg.setSendername(curUserName);
		msg.setIfAlready(0L);
		msg.setState(0L);
		msg.setType(msgTypeCd);

		this.msgDao.save(msg);
	}

	@Override
	public List<ProcessSuggestionVO> getSuggestion(String workflowId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" c.comments, ");
		sql.append(" c.task_assignee, ");
		sql.append(" to_char(c.create_time,'yyyy-MM-dd HH:MM:ss'), ");
		sql.append(" c.task_type_id ");
		sql.append(" from ").append(WF_SCHEMA).append(".ccbl_worklist_task c ");
		sql.append(" WHERE c.workflow_id = ?1 ");
		List<Object[]> resultSet = this.query.nativeQuery(sql.toString(),
				workflowId);
		if (CollectionUtils.isEmpty(resultSet)) {
			return null;
		} else {
			List<ProcessSuggestionVO> queryList = new ArrayList<ProcessSuggestionVO>();
			for (Object[] obj : resultSet) {
				ProcessSuggestionVO p = new ProcessSuggestionVO(
						(String) obj[0], (String) obj[1], (String) obj[2],
						String.valueOf(obj[3]));
				queryList.add(p);
			}
			return queryList;
		}
	}

	@Override
	public List<DocumentIndex> findDocumentIndexList(Long projectId,
			String custDocType) {
		return documentIndexDao.findByBizIdAndCustDocType(projectId,
				custDocType);
	}

	@Override
	@Deprecated
	public String submitInspect(String workflowId, String taskId,
			String comments, String userId, String nextUser) throws Exception {

		ProjectApplication pa = this.query.querySingleResult(
				ProjectApplication.class,
				"FROM ProjectApplication WHERE workflowId=?1",
				Long.parseLong(workflowId));

		pa.setApproveAmt(pa.getApplyAmt());
		pa.setApproveDate(new Timestamp(System.currentTimeMillis()));
		pa.setApproveRepayingMode(pa.getRepayingMode());

		this.proAppDao.save(pa);

		BizRate br = this.query
				.querySingleResult(BizRate.class,
						"FROM BizRate WHERE projectId=?1", pa.getProjectId()
								.toString());

		br.setFinalIrTypeCd(br.getIrTypeCd());
		br.setApproveRateValue(br.getRateValue());
		br.setApproveIrTypeCd(br.getIrTypeCd());
		br.setFinalRateValue(br.getRateValue());
		br.setFinalFloatRate(br.getRateValue());
		this.bizRateDao.save(br);

		this.updateDocumentIndex(workflowId, false);

		// WorkflowProcess workflowProcess =
		// invoker.sendProcess(workflowType, workflowId, userId,
		// taskTypeId, taskId, actionCode, nextUser, comments);
		// if(workflowProcess==null||StringUtils.isBlank(workflowProcess.getWorkflowId())){
		// throw new RuntimeException("稽核岗-审查提交时调用webservice返回为空值");
		// }
		//
		// return workflowProcess.getWorkflowId();

		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(
				WorkFlowService.WorkFlowCode.EASY_LOAN, workflowId, taskId,
				userId, WorkFlowNode.EL_InspectPostReview, ActionCode.COMMIT,
				nextUser, comments, "");
		if (!result.getSuccess()) {
			throw new RuntimeException("稽核岗-审查提交时工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}
	@Override
	public String submitInspect(BizApprovalOfWfParam wfParam) throws Exception {
		final String workflowIdStr=wfParam.getWorkflowId();
		ProjectApplication pa = this.query.querySingleResult(
				ProjectApplication.class,
				"FROM ProjectApplication WHERE workflowId=?1",
				Long.parseLong(workflowIdStr));

		pa.setApproveAmt(pa.getApplyAmt());
		pa.setApproveDate(new Timestamp(System.currentTimeMillis()));
		pa.setApproveRepayingMode(pa.getRepayingMode());

		this.proAppDao.save(pa);

		BizRate br = this.query
				.querySingleResult(BizRate.class,
						"FROM BizRate WHERE projectId=?1", pa.getProjectId()
								.toString());

		br.setFinalIrTypeCd(br.getIrTypeCd());
		br.setApproveRateValue(br.getRateValue());
		br.setApproveIrTypeCd(br.getIrTypeCd());
		br.setFinalRateValue(br.getRateValue());
		br.setFinalFloatRate(br.getRateValue());
		this.bizRateDao.save(br);

		this.updateDocumentIndex(workflowIdStr, false);

		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(wfParam.toExeTaskParam(WorkFlowService.WorkFlowCode.EASY_LOAN, WorkFlowNode.EL_InspectPostReview, ActionCode.COMMIT));
		if (!result.getSuccess()) {
			throw new RuntimeException("稽核岗-审查提交时工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}
	@Override
	@Deprecated
	public String submitInternetAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser)
			throws Exception {

		this.updateDocumentIndex(workflowId, false);

		// WorkflowProcess workflowProcess = invoker
		// .sendProcess(workflowType, workflowId, curUserId,
		// taskTypeId, taskId, actionCode, nextUser, comments);
		// if(workflowProcess==null||StringUtils.isBlank(workflowProcess.getWorkflowId())){
		// throw new RuntimeException("电核网核提交时调用webservice返回为空值");
		// }
		//
		// return workflowProcess.getWorkflowId();

		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(
				WorkFlowService.WorkFlowCode.EASY_LOAN, workflowId, taskId,
				curUserId, WorkFlowNode.EL_PhoneNetworkCheck,
				ActionCode.COMMIT, nextUser, comments, "");
		if (!result.getSuccess()) {
			throw new RuntimeException("电核网核提交时工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}
	@Override
	public String submitInternetAppr(BizApprovalOfWfParam wfParam)
			throws Exception {
		final String workflowId=wfParam.getWorkflowId();
		this.updateDocumentIndex(workflowId, false);
		
		TypedResult<WorkFlowTaskIds> result = this.workFlowService
				.executeTask(wfParam.toExeTaskParam(WorkFlowCode.EASY_LOAN,
						WorkFlowNode.EL_PhoneNetworkCheck, ActionCode.COMMIT));
		if (!result.getSuccess()) {
			throw new RuntimeException("电核网核提交时工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}

	@Override
	public String submitShgAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser,String nextUserOrgId)
			throws Exception {

		this.updateDocumentIndex(workflowId, false);

		// WorkflowProcess workflowProcess = invoker
		// .sendProcess(workflowType, workflowId, curUserId,
		// taskTypeId, taskId, actionCode, nextUser, comments);
		//
		// if(workflowProcess==null||StringUtils.isBlank(workflowProcess.getWorkflowId())){
		// throw new RuntimeException("审批岗审批提交时调用webservice返回为空值");
		// }
		//
		// return workflowProcess.getWorkflowId();

		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(ExeTaskParam.newExeTaskParam(
				WorkFlowService.WorkFlowCode.EASY_LOAN, workflowId, taskId,
				curUserId, WorkFlowNode.EL_ApproPostApproval,
				ActionCode.COMMIT, nextUser, comments, "",nextUserOrgId));
		if (!result.getSuccess()) {
			throw new RuntimeException("审批岗审批提交时工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();

	}
	@Override
	@Deprecated
	public String approvalLoan(String workflowId, String taskId,
			String comments, String curUserId, String nextUser)
			throws Exception {

		this.updateDocumentIndex(workflowId, false);

		// WorkflowProcess workflowProcess = invoker
		// .sendProcess(workflowType, workflowId, curUserId,
		// taskTypeId, taskId, actionCode, nextUser, comments);
		// if(workflowProcess==null||StringUtils.isBlank(workflowProcess.getWorkflowId())){
		// throw new RuntimeException("审核放款提交时调用webservice返回为空值");
		// }
		//
		// return workflowProcess.getWorkflowId();

		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(
				WorkFlowService.WorkFlowCode.EASY_LOAN, workflowId, taskId,
				curUserId, WorkFlowNode.EL_CheckPayLoan, ActionCode.COMMIT,
				nextUser, comments, "");
		if (!result.getSuccess()) {
			throw new RuntimeException("审批岗审批提交时工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}
	@Override
	public String approvalLoan(BizApprovalOfWfParam wfParam) throws Exception {
		this.updateDocumentIndex(wfParam.getWorkflowId(), false);
		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(wfParam.toExeTaskParam(WorkFlowService.WorkFlowCode.EASY_LOAN, WorkFlowNode.EL_CheckPayLoan, ActionCode.COMMIT));
		if (!result.getSuccess()) {
			throw new RuntimeException("审批岗审批提交时工作流错误" + result.getMsg());
		}
		return result.getData().getWorkflowId();
	}

	/**
	 * 修改相关文档状态
	 * 
	 * @param workflowId
	 * @param flag
	 *            true流程结束false流程中
	 * @author xc
	 */
	@Override
	public void updateDocumentIndex(String workflowId, boolean flag) {
		ProjectApplication proApp = this.proAppDao
				.findProjectApplicationByWorkflowId(Long.parseLong(workflowId));
		this.documentIndexDao.updateCreateType(proApp.getPartyId(),
				proApp.getProjectNo());
		if (flag) {
			this.documentIndexDao.changeCreateTypeWhenWorkflowFinish(proApp
					.getPartyId());
		}

		//
		//
	}

	@Override
	public String sendBackApproval(String workflowId, String curUserLogname,
			String taskId, String taskStageCode, String comments,
			String curUserName) throws Exception {

		this.proAppDao.updateProjectStatusByWorkflowId(
				Long.parseLong(workflowId), "0");
//		this.sendMessage(workflowId, curUserLogname, curUserName, "申请被退回",
//				GlobalConstants.MSG_TYPE_SEND_BACK);
		
		this.messageCenterService.sendRetreatedMsg(Long.valueOf(workflowId), curUserLogname, curUserName);
		
		// String workflowType = EL_WF_TYPE;
		// String nextUsers = "";
		// String actionCode = "2";//退回
		//
		// String taskTypeId = taskStageCode;
		// WorkflowProcess workflowProcess =
		// invoker.sendProcess(workflowType,
		// workflowId, userId,
		// taskTypeId, taskId,
		// actionCode, nextUsers,
		// comments);
		// return workflowProcess.getWorkflowId();
		
		/*TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(
				WorkFlowCode.EASY_LOAN, workflowId, taskId, curUserLogname,
				WorkFlowNode.getNodeById(taskStageCode), ActionCode.SEND_BACK,
				"", comments, "");*/
		//退回时下一环节（执行人及执行人机构id为空）
		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(ExeTaskParam.newExeTaskParam(
				WorkFlowCode.EASY_LOAN, workflowId, taskId, curUserLogname,
				WorkFlowNode.getNodeById(taskStageCode), ActionCode.SEND_BACK,
				StringUtils.EMPTY, comments, StringUtils.EMPTY,null));
		if (!result.getSuccess()) {
			throw new RuntimeException("撤销申请调用工作流发生异常");
		}
		return result.getData().getWorkflowId();
	}

	@Override
	public String cancelApproval(String workflowId, String curUserLogname,
			String taskId, String taskStageCode, String comments,
			String curUserName,String actionCode) throws Exception {
		this.updateDocumentIndex(workflowId, true);
		
		this.proAppDao.updateProjectStatusByWorkflowId(
				Long.parseLong(workflowId), "4");
		
		this.messageCenterService.sendRefusedMsg(Long.valueOf(workflowId), curUserLogname, curUserName);
		ActionCode acode = ActionCode.CANCEL;
		if(StringUtils.isNotEmpty(actionCode) && ActionCode.FINISH.getCodeId().equals(actionCode)){
			acode = ActionCode.FINISH;
		}else{
			acode = ActionCode.CANCEL;
		}
		TypedResult<WorkFlowTaskIds> result = this.workFlowService.executeTask(ExeTaskParam.newExeTaskParam(
				WorkFlowCode.EASY_LOAN, workflowId, taskId, curUserLogname,
				WorkFlowNode.getNodeById(taskStageCode), acode, null,
				comments, null, null));
		if (!result.getSuccess()) {
			throw new RuntimeException("调用工作流发生异常");
		}
		return result.getData().getWorkflowId();

	}

	@Override
	public ProjectApplication findProjectAppByWorkflowId(Long workFlowId) {
		List list = this.query.query(ProjectApplication.class,
				"FROM ProjectApplication p WHERE p.workflowId=?1", workFlowId);
		if (!CollectionUtils.isEmpty(list)) {
			return (ProjectApplication) list.get(0);
		} else {
			return null;
		}
	}

	// 贷款审查 100411
	@Override
	@Deprecated
	public String submitWdscAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_11;// 贷款审查 100411
		Long projectId = this.proAppDao.findProjectByWorkflowId(new Long(
				workflowId));
		WorkFlowTaskIds workFlowTaskIds;
		try {
			Set<String> params = Sets.newHashSet();
			params.add("44");
			params.add("41");
			this.documentService.updateDocumentIndexByProjectId(projectId,
					params, "2");
			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT,
					nextUser, comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("贷款审查提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("贷款审查提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submitWdscAppr(BizApprovalOfWfParam wfParam) {
		final String workflowId=wfParam.getWorkflowId();
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_11;// 贷款审查 100411
		Long projectId = this.proAppDao.findProjectByWorkflowId(new Long(
				workflowId));
		WorkFlowTaskIds workFlowTaskIds;
		try {
			Set<String> params = Sets.newHashSet();
			params.add("44");
			params.add("41");
			this.documentService.updateDocumentIndexByProjectId(projectId,
					params, "2");
			workFlowTaskIds = workFlowService.executeTask(wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN, WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("贷款审查提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("贷款审查提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	// 初审 100412
	@Override
	@Deprecated
	public String submitWdfxzjAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_12;// 初审 100412
		Long projectId = this.proAppDao.findProjectByWorkflowId(new Long(
				workflowId));
		WorkFlowTaskIds workFlowTaskIds;
		try {
			Set<String> params = Sets.newHashSet();
			params.add("44");
			params.add("42");
			this.documentService.updateDocumentIndexByProjectId(projectId,
					params, "2");
			ActionCode nextCode = this.findNextAction(
					Long.parseLong(workflowId), taskTypeId);
			if (nextCode != ActionCode.COMMIT) {
				nextUser = this.workFlowService
						.getNextTaskReceivers(taskId, nextCode).getData()
						.get(0).getLogName();
			}

			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), nextCode, nextUser,
					comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初审提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("初审提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submitWdfxzjAppr(BizApprovalOfWfParam wfParam) {
		final String workflowId=wfParam.getWorkflowId();
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_12;// 初审 100412
		Long projectId = this.proAppDao.findProjectByWorkflowId(new Long(
				workflowId));
		WorkFlowTaskIds workFlowTaskIds;
		try {
			Set<String> params = Sets.newHashSet();
			params.add("44");
			params.add("42");
			this.documentService.updateDocumentIndexByProjectId(projectId,
					params, "2");
			ActionCode nextCode = this.findNextAction(
					Long.parseLong(workflowId), taskTypeId);
			boolean showContract = ActionCode.SUBMIT_CONTACTB.equals(nextCode);
			if(showContract){
				
			}else if(nextCode != ActionCode.COMMIT) {
				NextTaskReceiver nextTaskReceiver  = this.workFlowService
						.getNextTaskReceivers(wfParam.getTaskId(), nextCode).getData()
						.get(0);
				wfParam.setNextUser(nextTaskReceiver.getLogName());
				wfParam.setNextUserOrgId(nextTaskReceiver.getOrgId());
			}
			workFlowTaskIds = workFlowService.executeTask(wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN, WorkFlowNode.getNodeById(taskTypeId), nextCode)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初审提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("初审提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}

	// 任务分配 100413
	@Override
	@Deprecated
	public String submitWdfpgAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_13;// 任务分配 100413
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT,
					nextUser, comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("任务分配提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("任务分配提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submitWdfpgAppr(BizApprovalOfWfParam wfParam) {
		final String workflowId=wfParam.getWorkflowId();
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_13;// 任务分配 100413
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN,
							WorkFlowNode.getNodeById(taskTypeId),
							ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("任务分配提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("任务分配提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	// 一级审批 100414
	@Override
	@Deprecated
	public String submitWdfprwAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_14;// 一级审批 100414
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT,
					nextUser, comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("一级审批提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("一级审批提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submitWdfprwAppr(BizApprovalOfWfParam wfParam) {
		final String workflowId=wfParam.getWorkflowId();
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_14;// 一级审批 100414
		WorkFlowTaskIds workFlowTaskIds;
		try {
			ActionCode nextCode = this.findNextAction(Long.parseLong(workflowId), taskTypeId);
			boolean showContract = ActionCode.SUBMIT_CONTACTA.equals(nextCode);
			if(!showContract){
				workFlowTaskIds = workFlowService.executeTask(
						wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN,
								WorkFlowNode.getNodeById(taskTypeId),
								ActionCode.COMMIT)).getData();
			}else{
				workFlowTaskIds = workFlowService.executeTask(
						wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN,
								WorkFlowNode.getNodeById(taskTypeId),
								ActionCode.SUBMIT_CONTACTA)).getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("一级审批提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("一级审批提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	// 任务分配 100415
	@Override
	@Deprecated
	public String submitWdyjspAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_15;// 一级审批 100415
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT,
					nextUser, comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("任务分配 4015提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("任务分配 4015提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submitWdyjspAppr(BizApprovalOfWfParam wfParam) {
		final String workflowId=wfParam.getWorkflowId();
		this.updateDocumentIndex(workflowId, false);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_15;// 一级审批 100415
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN,
							WorkFlowNode.getNodeById(taskTypeId),
							ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("任务分配 4015提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("任务分配 4015提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}

	// 二级审批 100416
	@Override
	@Deprecated
	public String submitWdfpg2Appr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_16;// 二级审批 100416
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT,
					nextUser, comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("二级审批岗提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("二级审批岗提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submitWdfpg2Appr(BizApprovalOfWfParam wfParam) {
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_16;// 二级审批 100416
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN,
							WorkFlowNode.getNodeById(taskTypeId),
							ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("二级审批岗提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("二级审批岗提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	// 总经理审核合同 100418
	@Override
	@Deprecated
	public String submit2jspAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_18;// // 总经理审核合同
																// 100418
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT,
					nextUser, comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("总经理审核合同提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("总经理审核合同提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submit2jspAppr(BizApprovalOfWfParam wfParam) {
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_18;// // 总经理审核合同
		// 100418
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN, WorkFlowNode.getNodeById(taskTypeId),  ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("总经理审核合同提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("总经理审核合同提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	// 落实贷款条件 100419
	@Override
	@Deprecated
	public String submitlsfkAppr(String workflowId, String taskId,
			String comments, String curUserId, String nextUser) {

		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_19; // 落实贷款条件 100419
		Long projectId = this.proAppDao.findProjectByWorkflowId(new Long(
				workflowId));
		WorkFlowTaskIds workFlowTaskIds;
		try {
			Set<String> params = Sets.newHashSet();
			params.add("13");
			params.add("14");
			params.add("15");
			params.add("16");
			params.add("17");
			params.add("29");
			params.add("44");
			this.documentService.updateDocumentIndexByProjectId(projectId,
					params, "2");

			workFlowTaskIds = workFlowService.executeTask(
					WorkFlowCode.MICRO_LOAN, workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT,
					nextUser, comments, "").getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("落实贷款条件提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("落实贷款条件提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	@Override
	public String submitlsfkAppr(BizApprovalOfWfParam wfParam) {
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_19; // 落实贷款条件 100419
		final String workflowId=wfParam.getWorkflowId();
		Long projectId = this.proAppDao.findProjectByWorkflowId(new Long(
				workflowId));
		WorkFlowTaskIds workFlowTaskIds;
		try {
			Set<String> params = Sets.newHashSet();
			params.add("13");
			params.add("14");
			params.add("15");
			params.add("16");
			params.add("17");
			params.add("29");
			params.add("44");
			this.documentService.updateDocumentIndexByProjectId(projectId,
					params, "2");

			workFlowTaskIds = workFlowService.executeTask(wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN, WorkFlowNode.getNodeById(taskTypeId), ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("落实贷款条件提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("落实贷款条件提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}

	// 审核贷款条件 100420
	@Override
	@Deprecated
	public String submitshdkAppr(String workflowId, String taskId,
			String comments, String curUserId, String curUserName,
			String nextUser) {
		this.contractDao.updateContractStatusCdByWorkFlowId(
				Long.parseLong(workflowId), CONTRACT_STATUS_CD_300);
		this.proAppDao.updateProjectStatusByWorkflowId(
				Long.parseLong(workflowId), PROJECT_STATUS_5);
		this.sendMessage(workflowId, curUserId, curUserName, "审批通过",
				GlobalConstants.MSG_TYPE_APPROVAL_FINISH);
		this.updateDocumentIndex(workflowId, true);
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_20;// 审核贷款条件 100420
		boolean flag = false;
		try {
			flag = this.workFlowService.executeTask(WorkFlowCode.MICRO_LOAN,
					workflowId, taskId, curUserId,
					WorkFlowNode.getNodeById(taskTypeId), ActionCode.FINISH,
					nextUser, comments, "").getSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("审核贷款条件工作流报错");
		}
		if (!flag) {
			throw new RuntimeException("审核贷款条件工作流返回结果为空");
		}
		return "success";
	}
	@Override
	public String submitshdkAppr(BizApprovalOfWfParam wfParam) {
		final String workflowId=wfParam.getWorkflowId();
		this.contractDao.updateContractStatusCdByWorkFlowId(
				Long.parseLong(workflowId), CONTRACT_STATUS_CD_300);
		this.proAppDao.updateProjectStatusByWorkflowId(
				Long.parseLong(workflowId), PROJECT_STATUS_5);
//		this.sendMessage(workflowId, wfParam.getCurUserLogName(), wfParam.getCurUserName(), "审批通过",
//				GlobalConstants.MSG_TYPE_APPROVAL_FINISH);
		this.messageCenterService.sendPassedMsg(Long.valueOf(workflowId),  wfParam.getCurUserLogName(), wfParam.getCurUserName());
		this.updateDocumentIndex(workflowId, true);
		
		// 修改关联抵押物的已设定担保金额
		ProjectApplication projectApplication=projectApplicationDao.findProjectApplicationByWorkflowId(Long.valueOf(wfParam.getWorkflowId()));
		List<ProjectPawnInfo> projectPawnInfos = projectPawnInfoDao.findByProjectId(projectApplication.getProjectId());
		for (ProjectPawnInfo projectPawnInfo : projectPawnInfos) {
			Collateral collateral=collateralDao.findOne(projectPawnInfo.getGuarantyId());
			BigDecimal setGuarantyAmt=collateral.getSetGuarantyAmt();
			if(setGuarantyAmt==null) {
				setGuarantyAmt=new BigDecimal(0);
			}
			collateral.setSetGuarantyAmt(setGuarantyAmt.add(projectPawnInfo.getAppGuaDebtInterAmt()));
		}
		
		String taskTypeId = GlobalConstants.WD_WF_TASK_ID_20;// 审核贷款条件 100420
		boolean flag = false;
		try {
			flag = this.workFlowService.executeTask(
					wfParam.toExeTaskParam(WorkFlowCode.MICRO_LOAN,
							WorkFlowNode.getNodeById(taskTypeId),
							ActionCode.COMMIT)).getSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("审核贷款条件工作流报错");
		}
		if (!flag) {
			throw new RuntimeException("审核贷款条件工作流返回结果为空");
		}
		return "success";
	}
	@Override
	public void updateProjectStatusByWorkflowId(Long workflowId, String status) {
		this.proAppDao.updateProjectStatusByWorkflowId(workflowId, status);
	}

	@Override
	public void quitWdApproval(String workflowId, String taskId,
			String comments, String curUserId, String taskStageCode,
			String curUserName) {
		this.updateProjectStatusByWorkflowId(Long.parseLong(workflowId), "0");
		this.messageCenterService.sendRetreatedMsg(Long.valueOf(workflowId), curUserId, curUserName);
//		this.sendMessage(workflowId, curUserId, curUserName, "申请被退回",
//				GlobalConstants.MSG_TYPE_SEND_BACK);
		/*boolean flag = workFlowService.executeTask(WorkFlowCode.MICRO_LOAN,
				workflowId, taskId, curUserId,
				WorkFlowNode.getNodeById(taskStageCode), ActionCode.SEND_BACK,
				"", comments, "").getSuccess();*/
		ProjectApplication projectApplication = projectApplicationDao.findProjectApplicationByWorkflowId(Long.valueOf(workflowId));
		String sql = "delete from approval_history_repay_plan ahrp where ahrp.approval_history_id is not null and ahrp.approval_history_id != 0 and ahrp.project_id = " + projectApplication.getProjectId();
		dynamicQuery.nativeExecuteUpdate(sql);
		
		TypedResult<WorkFlowTaskIds> result=workFlowService.executeTask(ExeTaskParam.newExeTaskParam(WorkFlowCode.MICRO_LOAN,
				workflowId, taskId, curUserId,
				WorkFlowNode.getNodeById(taskStageCode), ActionCode.SEND_BACK,
				StringUtils.EMPTY, comments, StringUtils.EMPTY,null));
		if (!result.getSuccess()) {
			throw new RuntimeException("退回工作流返回结果为空");
		}
	}

	@Override
	public void dropBackApproval(String workflowId, String taskId,
			String comments, String curUserId, String taskStageCode,
			String curUserName,String actionCode) {
		this.updateDocumentIndex(workflowId, true);
		String taskTypeId = taskStageCode;
		this.updateProjectStatusByWorkflowId(Long.parseLong(workflowId), "4");
		this.messageCenterService.sendRefusedMsg(Long.valueOf(workflowId), curUserId, curUserName);
		
		try {
			//审批流程中释放抵质押物
			ProjectApplication pa = this.findProjectAppByWorkflowId(Long.parseLong(workflowId));
			bizApplyQueryService.releasePledgeWhenRejectOrCancel(pa.getProjectId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("释放抵质押物失败");
		}
		
		ActionCode acode = ActionCode.CANCEL;
		if(StringUtils.isNotEmpty(actionCode) && ActionCode.FINISH.getCodeId().equals(actionCode)){
			acode = ActionCode.FINISH;
		}else{
			acode = ActionCode.CANCEL;
		}
		boolean flag = workFlowService.executeTask(ExeTaskParam.newExeTaskParam(WorkFlowCode.MICRO_LOAN,
				workflowId, taskId, curUserId,
				WorkFlowNode.getNodeById(taskTypeId), acode, StringUtils.EMPTY,
				comments, StringUtils.EMPTY, null)).getSuccess();
		if (!flag) {
			throw new RuntimeException("撤销工作流返回结果为空");
		}
	}

	@Override
	public ProjectApplication findProjectApplication(Long projectId) {
		return proAppDao.findOne(projectId);
	}

	@Override
	@Transactional(readOnly = true)
	public ProjAppVo findProjAppInfo(Long projectId) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer
				.append("select pa.project_id,pa.project_no,pa.apply_amt,pa.apply_term,pa.apply_term_unit,pa.repaying_mode,")
				.append("br.ir_type_cd,br.rate_value,br.adjust_period,br.ir_nego_symb_cd, ")
				//add by mz 20150803 start
				.append(" pa.credit_type ")
				//add by mz 20150803 end
				.append(" from project_application pa")
				.append(" left join biz_rate br on pa.project_id = br.project_id");
		strBuffer.append(" where pa.project_id =?1");
		ProjAppVo projApp = new ProjAppVo(query.nativeQuerySingleResult(
				Object[].class, strBuffer.toString(), projectId));
		final BigDecimal loanAmtLevel1 = new BigDecimal(300000);// 三十万
		// int approveLevel=0;//默认不做批复
		int approveLevel = 4;// 默认每步都做批复
		/*
		 * if(loanAmtLevel1.compareTo(projApp.getApplyAmt())>=0){
		 * approveLevel=1;//一级审批做批复 }else{ approveLevel=2;//二级审批做批复 }
		 */
		projApp.setApproveLevel(approveLevel);
		return projApp;
	}

	@Override
	public void updateProjAppInfo(ProjAppVo projApp, boolean agree,
			SessionUser user, List params) {
		if (projApp == null || projApp.getProjectId() == null) {
			throw new NullPointerException("参数错误");
		}
		ApprovalHistoryBxloan ahb = new ApprovalHistoryBxloan();
		ProjectApplication pa = proAppDao.findOne(projApp.getProjectId());
		if (pa == null) {
			throw new NullPointerException("ProjectApplication 不存在");
		}
		//判断批复金额是否满足条件 modify gph 201506081630
		businessApplicationService.checkApplyAmountIsAvailable(
				projApp.getApproveAmt(), Long.valueOf(pa.getProductType()),
				Long.valueOf(pa.getApplyOrg()));
		
		ahb.setProjectId(pa.getProjectId());
		ahb.setWorkflowId(Long.valueOf((String) params.get(0)));
		ahb.setTaskId(Long.valueOf((String) params.get(1)));
		ahb.setTaskStageCode(String.valueOf(params.get(2)));
		//
		pa.setCurrentApproveNum(CommonHelper.toStr(user.getUserId()));
		pa.setCurrentApproveOrg(CommonHelper.toStr(user.getOrgId()));
		pa.setCurrentApproveRole(CommonHelper.toStr(user.getRoleId()));

		pa.setApproveAmt(projApp.getApproveAmt());
		ahb.setApproveAmt(projApp.getApproveAmt());

		pa.setApproveDate(projApp.getApproveDate());

		pa.setApproveRepayingMode(projApp.getApproveRepayingMode());
		ahb.setApproveRepayingMode(projApp.getApproveRepayingMode());

		pa.setTerm(projApp.getTerm());
		ahb.setApproveTerm(new Long(projApp.getTerm()));
		pa.setTermUnit(projApp.getTermUnit());
		ahb.setTermUnit(projApp.getTermUnit());

		pa.setProjectStatus("2");// 已批复
		BizRate br = bizRateDao.findByProjectId(String.valueOf(projApp
				.getProjectId()));
		if (br == null) {
			throw new NullPointerException("BizRate 不存在");
		}
		// 审批利率设置
		br.setApproveIrTypeCd(projApp.getApproveIrTypeCd());
		ahb.setApproveIrTypeCd(projApp.getApproveIrTypeCd());

		br.setApproveAdjustPeriod(projApp.getApproveAdjustPeriod());
		ahb.setApproveAdjustPeriod(projApp.getApproveAdjustPeriod());

		br.setApproveFloatRate(projApp.getApproveFloatRate());
		ahb.setApproveFloatRate(projApp.getApproveFloatRate());

		// 最终利率设置
		br.setFinalIrTypeCd(projApp.getApproveIrTypeCd());
		br.setFinalAdjustPeriod(projApp.getApproveAdjustPeriod());
		br.setFinalFloatRate(projApp.getApproveFloatRate());

		// 重新计算年利率
		BigDecimal yearIr = projApp.getApproveRateValue();
		if (StringUtils.equals(
				dataDict.getCodeVal(INTEREST_RATE_ADJUSTMENT, "S2"),
				projApp.getApproveIrTypeCd())) {
			MoneyRate moneyRate = this.findBenchmarkMoneyRate(
					projApp.getTerm(), projApp.getTermUnit(),
					pa.getBusinessProcessType());
			yearIr = MoneyRateUtils.countYearIr(
					moneyRate.getInterestRate(),
					MathUtil.BDadd(new BigDecimal("1"),
							projApp.getApproveFloatRate(), 6));
		}
		br.setApproveRateValue(yearIr);
		ahb.setApproveRateValue(yearIr);

		br.setFinalRateValue(yearIr);
		// 保存批复意见
		TbMultiopinion multiopinion = new TbMultiopinion();

		multiopinion.setOpinion(projApp.getReplyOpinion());
		ahb.setApprovalOpinion(projApp.getReplyOpinion());

		multiopinion.setOperationid(pa.getProjectId());// ProjectID
		multiopinion.setOpiniontype("conclusion");//
		multiopinion.setSigntime(new Date());
		multiopinion.setPersonid(user.getLoginName());// 当前用户登录名
		multiopinion.setPersoncn(user.getUserNameCN());// 当前用户中文名
		multiopinion.setIssueorgid(user.getOrgId());// 当前用户机构id
		multiopinion.setConclusion(agree ? "01" : "02");// 01：同意，02：否决
		multiopinion.setRoleid(user.getRoleId());

		ahb.setApproveDate(projApp.getApproveDate());
		ahb.setCreateDate(new Date());

		tbMultiopinionDao.save(multiopinion);
		projectApplicationDao.save(pa);
		bizRateDao.save(br);
		approvalHistoryBxloanDao.save(ahb);

		/**
		 * 还款计划
		 */
		String nativeSql = "";
		List<Object> repayPlanParams = new ArrayList<Object>();
		// 自定义还款方式
		if (dataDict.getCodeVal("RepaymentMode", "S3").equals(
				ahb.getApproveRepayingMode())) {
			nativeSql = "UPDATE APPROVAL_HISTORY_REPAY_PLAN SET APPROVAL_HISTORY_ID = ?1 WHERE "
					+ "APPROVAL_HISTORY_ID IS NULL AND PROJECT_ID = "
					+ pa.getProjectId();
			repayPlanParams.add(ahb.getId());
		} else {
			nativeSql = "DELETE FROM APPROVAL_HISTORY_REPAY_PLAN WHERE PROJECT_ID = ?1 AND APPROVAL_HISTORY_ID IS NULL";
			repayPlanParams.add(pa.getProjectId());
		}
		
		String delRepayPlanSql = "DELETE FROM REPAY_PLAN RP WHERE RP.PROJECT_ID = " + pa.getProjectId();
		
		List<ApprovalHistoryRepayPlan> approRepayPlans = approvalHistoryRepayPlanDao.findByApprovalHistoryIdAndProjectId(null, pa.getProjectId());
		List<RepayPlan> newRepayPlans = new ArrayList<RepayPlan>();
		List<RepayPlanTemp> newRepayPlanTemps = new ArrayList<RepayPlanTemp>();
		List<ApprovalHistoryRepayPlan> newApprovalRepayPlans = new ArrayList<ApprovalHistoryRepayPlan>();
		for (ApprovalHistoryRepayPlan approRepayPlan : approRepayPlans) {
			RepayPlan newRepayPlan = new RepayPlan();
			BeanUtils.copyProperties(approRepayPlan, newRepayPlan);
			newRepayPlans.add(newRepayPlan);
			
			RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
			BeanUtils.copyProperties(newRepayPlan, repayPlanTemp);
			repayPlanTemp.setProjectNo(pa.getProjectNo());
			newRepayPlanTemps.add(repayPlanTemp);
			
			ApprovalHistoryRepayPlan newApprovalHistoryRepayPlan = new ApprovalHistoryRepayPlan();
			BeanUtils.copyProperties(approRepayPlan, newApprovalHistoryRepayPlan, "approvalHistoryRepayPlanId", "approvalHistoryId");
			newApprovalRepayPlans.add(newApprovalHistoryRepayPlan);
		}
		
		query.nativeExecuteUpdate(nativeSql, repayPlanParams);
		query.nativeExecuteUpdate(delRepayPlanSql);
		repayPlanDao.save(newRepayPlans);
		repayPlanTempDao.save(newRepayPlanTemps);
		approvalHistoryRepayPlanDao.save(newApprovalRepayPlans);

	}

	/**
	 * 查询基准利率
	 * 
	 * @param term
	 *            期限
	 * @param termUnitCd
	 *            期限单位类型
	 * @param businessProcessType
	 *            业务类型
	 * @return
	 */
	private MoneyRate findBenchmarkMoneyRate(Integer term, String termUnitCd,
			String businessProcessType) {
		if (StringUtils.isBlank(businessProcessType)
				|| StringUtils.equals(
						dataDict.getCodeVal("BussinessProperty", "S1"),
						businessProcessType)) {
			// 业务申请
			int limitMonth = 0;
			if (dataDict.getCodeVal("TermUnitCd", "S1").equals(termUnitCd)) { // 年
				limitMonth = term * 12;
			} else if (dataDict.getCodeVal("TermUnitCd", "S2").equals(
					termUnitCd)) { // 月
				limitMonth = term;
			} else if (dataDict.getCodeVal("TermUnitCd", "S3").equals(
					termUnitCd)) {
				limitMonth = term / 30;
			}
			String timeLimit = null;
			if (limitMonth <= 6) { // 六个月以内(含6个月)
				timeLimit = dataDict.getCodeVal("TimeLimitType", "S1");
			} else if (limitMonth > 6 && limitMonth <= 12) { // 六个月至一年(含一年)
				timeLimit = dataDict.getCodeVal("TimeLimitType", "S2");
			} else if (limitMonth > 12 && limitMonth <= 36) { // 一至三年(含三年)
				timeLimit = dataDict.getCodeVal("TimeLimitType", "S3");
			} else if (limitMonth > 36 && limitMonth <= 60) { // 三至五年(含五年)
				timeLimit = dataDict.getCodeVal("TimeLimitType", "S4");
			} else if (limitMonth > 60) { // 五年以上
				timeLimit = dataDict.getCodeVal("TimeLimitType", "S5");
			}
			List<MoneyRate> list = query.query(MoneyRate.class,
					"from MoneyRate p where p.valid='1' and p.timeLimit=?",
					timeLimit);
			if (CollectionUtils.isEmpty(list)) {
				return null;
			}
			return list.get(0);
		} else {
			// 展期申请
			// ?
		}
		return null;
	}

	// Add 2014-11-14
	@Override
	@Transactional(readOnly = true)
	public ElProjApplVo findElProjApplInfo(Long projectId) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer
				.append("select pa.customer_name,pa.customer_manager_name,pa.apply_amt,");
		strBuffer
				.append("pa.apply_term,pa.apply_term_unit,pa.biz_rate,eod.name ");
		strBuffer.append("  from project_application pa");
		strBuffer.append(" left join ec_org_department eod ");
		strBuffer.append(" on eod.id = pa.apply_org ");
		strBuffer.append("  where pa.project_id = ?1");
		ElProjApplVo vo = new ElProjApplVo(query.nativeQuerySingleResult(
				Object[].class, strBuffer.toString(), projectId));
		//共同借款人
		this.initCommonBorrowOfElProjApp(projectId,vo);
		return vo;
	}

	/**
	 * 初始化共同借款人
	 * @param projectId
	 */
	private void initCommonBorrowOfElProjApp(Long projectId,ElProjApplVo vo) {
		//查询共同借款人
		List<CommonBorrow> list=dynamicQuery.query(CommonBorrow.class,"from CommonBorrow cb where cb.projectId=?1 and cb.flag = ?2", projectId,"1");
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		StringBuffer sb=new StringBuffer();
		for (CommonBorrow cr : list) {
			sb.append(",").append(cr.getCustomerName());
		}
		String result=sb.toString();
		vo.setCommonBorrowerName(result.length()>0?result.substring(1):result);
	}

	@Override
	public String saveElProjApplOfDhwh(ElApprInfoVo apprInfo) {
		
		ProjectApplication pa = proAppDao.findOne(apprInfo.getProjectId());
		//判断建议金额是否满足条件 modify gph 201506081840
		businessApplicationService.checkApplyAmountIsAvailable(
				apprInfo.getProposalAmt(), Long.valueOf(pa.getProductType()),
				Long.valueOf(pa.getApplyOrg()));
		
		// 更新文档创建状态为引用
		Set<String> documentTypes = Sets.newHashSet();
		documentTypes.add("40");
		documentTypes.add("44");
		this.documentService.updateDocumentIndexByProjectId(
				apprInfo.getProjectId(), documentTypes, "2");
		// 保存
		this.saveApprovalOpinion(apprInfo);
		try {
			return this.submitInternetAppr(BizApprovalOfWfParam.newBizApprovalOfWfParam(
					String.valueOf(apprInfo.getWorkflowId()),
					String.valueOf(apprInfo.getTaskId()),
					apprInfo.getComments(), apprInfo.getCurUserLoginName(),apprInfo.getCurUserName(),
					apprInfo.getNextUser(),apprInfo.getNextUserOrgId()));
		} catch (Exception e) {
			throw new RuntimeException("workflow error!");
		}
	}

	@Override
	public String saveElProjApplOfSpg(ElApprInfoVo apprInfo) {
		
		ProjectApplication pa = proAppDao.findOne(apprInfo.getProjectId());
		//判断建议金额是否满足条件 modify gph 201506081840
		businessApplicationService.checkApplyAmountIsAvailable(
				apprInfo.getProposalAmt(), Long.valueOf(pa.getProductType()),
				Long.valueOf(pa.getApplyOrg()));
		
		// 更新文档创建状态为引用
		Set<String> documentTypes = Sets.newHashSet();
		documentTypes.add("41");
		documentTypes.add("44");
		this.documentService.updateDocumentIndexByProjectId(
				apprInfo.getProjectId(), documentTypes, "2");
		// 保存审批建议
		this.saveApprovalOpinion(apprInfo);
		// 更新最终审批费率
		this.updateProjAppFinalAppr(apprInfo);
		// WorkFlow
		try {
			return this.submitShgAppr(String.valueOf(apprInfo.getWorkflowId()),
					String.valueOf(apprInfo.getTaskId()),
					apprInfo.getComments(), apprInfo.getCurUserLoginName(),
					apprInfo.getNextUser(),apprInfo.getNextUserOrgId());
		} catch (Exception e) {
			throw new RuntimeException("workflow error!");
		}
	}

	@Override
	public ApprovalOpinion findApprOpinion(Long projectId, WorkFlowNode node) {
		List<ApprovalOpinion> list = approvalOpinionDao
				.findApprovalOpinionList(projectId, node.getId());
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return this.findApprOpinion(projectId, node.getId());
	}

	/**
	 * 更新最终审批结果(易贷)
	 */
	private void updateProjAppFinalAppr(ElApprInfoVo apprInfo) {
		ProjectApplication pa = proAppDao.findOne(apprInfo.getProjectId());
		if (pa == null) {
			throw new NullPointerException("ProjectApplication 不存在");
		}
		//
		pa.setCurrentApproveNum(CommonHelper.toStr(apprInfo.getCurUserId()));
		pa.setCurrentApproveOrg(CommonHelper.toStr(apprInfo.getCurUserOrgId()));
		pa.setCurrentApproveRole(CommonHelper.toStr(apprInfo.getCurUserRoleId()));
		pa.setApproveAmt(apprInfo.getProposalAmt());
		pa.setApproveDate(new Date());
		pa.setApproveRepayingMode(pa.getRepayingMode());
		pa.setTerm(apprInfo.getProposalTerm());
		pa.setTermUnit(apprInfo.getProposalTermUnit());// 月
		pa.setProjectStatus("2");// 已批复
		BizRate br = bizRateDao.findByProjectId(String.valueOf(apprInfo
				.getProjectId()));
		if (br == null) {
			throw new NullPointerException("BizRate 不存在");
		}
		final String inteRateTypeCd = dataDict.getCodeVal(
				INTEREST_RATE_ADJUSTMENT, "S1");
		BigDecimal yearIr = apprInfo.getProposalRate();// 批复利率
		// 审批利率设置
		br.setApproveIrTypeCd(inteRateTypeCd);
		br.setApproveRateValue(yearIr);
		// 最终利率设置
		br.setFinalIrTypeCd(inteRateTypeCd);
		br.setFinalRateValue(yearIr);
	}

	private ApprovalOpinion findApprOpinion(Long projectId, String taskStageCode) {
		List<ApprovalOpinion> list = approvalOpinionDao
				.findApprovalOpinionList(projectId, taskStageCode);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 保存审批结果
	 * 
	 * @param apprInfo
	 */
	private void saveApprovalOpinion(ElApprInfoVo apprInfo) {
		ApprovalOpinion ao = this.findApprOpinion(apprInfo.getProjectId(),
				apprInfo.getTaskStageCode());
		if (ao == null) {
			ao = new ApprovalOpinion();
		}
		ao.setProjectId(apprInfo.getProjectId());
		ao.setTaskStageCode(apprInfo.getTaskStageCode());
		ao.setProposalAmt(apprInfo.getProposalAmt());
		ao.setProposalTerm(apprInfo.getProposalTerm());
		ao.setProposalTermUnit(apprInfo.getProposalTermUnit());
		ao.setProposalRate(apprInfo.getProposalRate());
		ao.setOperationId(apprInfo.getCurUserId());
		ao.setOpinion(apprInfo.getComments());
		ao.setSigntime(new Date());
		this.approvalOpinionDao.save(ao);
	}

	// TODO
	public ActionCode findNextAction(Long workflowId, String taskStageCode) {
		String actionCode = null;
		StringBuilder jql = new StringBuilder();
		jql.append("SELECT pa,pwc.ruleconfig ");
		jql.append("FROM ProjectApplication pa,ProductWfConfig pwc  ");
		jql.append("WHERE pa.workflowId=?1 AND pwc.pcId=( ");
		jql.append("  SELECT pcId  ");
		jql.append("  FROM ProductConfig pc ");
		jql.append("  WHERE cast(pc.productCd as string) = pa.productType ");
		jql.append("  AND  cast(pc.orgId as string) = pa.applyOrg ");
		jql.append(") ");
		jql.append("AND pwc.taskStageCode = ?2 ");

		List<Object[]> result = this.query.query(Object[].class,
				jql.toString(), workflowId, taskStageCode);
		if (result == null || result.isEmpty()) {
			return ActionCode.COMMIT;
		}

		ProjectApplication pa = (ProjectApplication) result.get(0)[0];

		String rule = result.get(0)[1] == null ? "" : result.get(0)[1]
				.toString();
		if (StringUtils.isBlank(rule)) {
			return null;
		}
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("pa", pa);
		Expression exp = parser.parseExpression(rule);
		actionCode = exp.getValue(context, String.class);
		return ActionCode.getActionCodeById(actionCode);
	}

	@Override
	public String findWorkFlowTypeByProductCd(String productType) {
		return productConfigDao.findWfCodeByProductCd(new Long(productType));
	}
	
	@Override
	public Result checkUploadFileList(Long projectId, List<String> custDocTypes){
		Result result = null;
		for (String custDocType : custDocTypes) {
			 result = checkUploadFiles(projectId, custDocType);
			 if(!result.getSuccess()){
				 break;
			 }
		}
		return result;
	};
	@Override
	public Result checkUploadFiles(Long projectId, String custDocType) {
		String sql = "select count(di.cust_doc_type) from document_index di "
				+ "where di.status = '1' and di.biz_id = ?1 " + "and di.cust_doc_type in (?2) "
				+ "group by di.cust_doc_type ";
		Long count = query.nativeQueryCount(sql, projectId, custDocType)
				.longValue();
		if (count < 1) {
			String docName = dataDict.getCodeName("CustProjectAllDocType",
					custDocType);
			return new Result(false, "请您上传" + docName, null);
		} else {
			return new Result(true);
		}
	}

	@Override
	public List<DocumentIndex> findApplyDocuments(Long projectId,
			String documentType) {
		//20150612 szx 修改贷款审查申请表无法下载的问题
		return documentIndexDao.findByBizIdAndCustDocType(projectId,
				documentType);
//		return documentIndexDao.findByBizIdAndDocumentType(projectId,
//				documentType);
	}

	@Override
	public Result compareSubContractDocNum(Long projectId) {
		List<SubContract> sbList = subContractDao.findByProjectId(projectId);
		Integer countSize;
		if (CollectionUtils.isEmpty(sbList)) {
			return new Result(true);
		} else {
			countSize = sbList.size();
			String custDocType = "58";
			Integer uploadSize = documentService.findCustDocTypeNum(
					projectId, custDocType);
			if (uploadSize.compareTo(countSize) >= 0) {
				return new Result(true);
			} else {
				return new Result(false, "该业务有" + countSize + "个从合同，已上传扫描件"
						+ uploadSize + "个，请上传对应数量的从合同扫描件!", null);
			}
		}
	}

	@Override
	public ProjAppVo findProjAppInfoApproval(Long projectId) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select pa.project_id,pa.project_no,pa.approve_amt,pa.term,pa.term_unit,pa.approve_repaying_mode,")
				.append("br.approve_ir_type_cd,br.approve_rate_value,br.approve_adjust_period,br.approve_float_Rate, ");
		//add by mazheng 20150803 start 增加授信类型，批复阶段反显上一环节批复信息使用
		strBuffer.append(" pa.temp_credit_type ");
		//add by mazheng 20150803 end 
		strBuffer.append(" from project_application pa")
				.append(" left join biz_rate br on pa.project_id = br.project_id");
		strBuffer.append(" where pa.project_id =?1");
		ProjAppVo projApp = new ProjAppVo(query.nativeQuerySingleResult(
				Object[].class, strBuffer.toString(), projectId));
		final BigDecimal loanAmtLevel1 = new BigDecimal(300000);// 三十万
		// int approveLevel=0;//默认不做批复
		int approveLevel = 4;// 默认每步都做批复
		/*
		 * if(loanAmtLevel1.compareTo(projApp.getApplyAmt())>=0){
		 * approveLevel=1;//一级审批做批复 }else{ approveLevel=2;//二级审批做批复 }
		 */
		projApp.setApproveLevel(approveLevel);
		return projApp;
	}

	@Override
	public ApprovalHistoryBxloan findApprovalMsg(List params) {
		String jpql = "SELECT ahb FROM ApprovalHistoryBxloan ahb "
				+ "WHERE ahb.projectId = ?1 And ahb.taskStageCode = ?2 ORDER BY ahb.createDate DESC ";
		List list = query.query(jpql, params.toArray());
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return (ApprovalHistoryBxloan) list.get(0);
		}
	}

	@Override
	public ApprovalContentVO findFinalApproValContentByProjectId(Long projectId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("pa.project_no as project_no, ");
		sql.append("pa.customer_name as customer_name, ");
		sql.append("pr.product_name as product_name, ");
		sql.append("pa.approve_amt as approve_amt, ");
		sql.append("pa.term||tt.code_name  as apply_term, ");
		sql.append("pa.apply_amt as apply_amt, ");
		sql.append("pa.applicant as applicant, ");
		sql.append("pa.approve_date as apply_date, ");
		sql.append("br.final_rate_value as biz_rate, ");
		sql.append("pa.party_id as paParty, ");
		sql.append("pa.CUSTOMER_MANAGER_NAME as customerManagerName, ");
		sql.append("c.code_name as repaying_mode,");
		sql.append("pa.guarantee_mode, ");
		sql.append("it.INDUSTRY_TYPE_NAME, ");
		sql.append("eod.name as org_name ");
		sql.append("FROM ");
		sql.append("project_application pa ");
		sql.append("LEFT JOIN individual iv ");
		sql.append("ON iv.CUSTOMER_NUM=pa.CUSTOMER_NUM ");
		sql.append("LEFT JOIN product pr ");
		sql.append("ON pr.product_cd=pa.product_type ");
		sql.append("LEFT JOIN code c  ");
		sql.append("ON c.code_value = pa.approve_repaying_mode And c.code_type='RepaymentMode' ");
		sql.append("LEFT JOIN code tt  ");
		sql.append("ON tt.code_value = pa.term_unit ");
		sql.append("And tt.code_type = 'TermUnitCd' ");
		sql.append("LEFT JOIN INDUSTRY_TYPE it ");
		sql.append("ON pa.INVESTMENT_INDUSTRY = it.INDUSTRY_TYPE_CD ");
		sql.append("LEFT JOIN ec_org_department eod  ");
		sql.append("ON eod.id = pa.apply_org ");
		sql.append("LEFT JOIN biz_rate br  ");
		sql.append("ON br.project_id = pa.project_id ");
		sql.append("WHERE ");
		sql.append("pa.project_id=?1 ");
		List<Object[]> resultSet = this.query.nativeQuery(sql.toString(),
				projectId);
		if (CollectionUtils.isEmpty(resultSet)) {
			return null;
		}

		return new ApprovalContentVO(resultSet.get(0));
	}
	@Override
	public String submitUCshdkAppr(BizApprovalOfWfParam wfParam) {
		final String workflowId=wfParam.getWorkflowId();
		
		//获取流程与业务关系对象
		WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		Long parentBusinessId = wfBusinessRelation.getParentBusinessId();
		//得到授信合同信息
		CreditContract creditContract=creditContractMngService.findCreditContractByProjectId(parentBusinessId);
		//得到审批批复信息
		ApprovalContentVO approvalContent = this.findFinalApproValContentByProjectId(businessId);

		
		this.contractDao.updateContractStatusCdByWorkFlowId(Long.parseLong(workflowId), CONTRACT_STATUS_CD_300);
		this.proAppDao.updateProjectStatusByWorkflowId(Long.parseLong(workflowId), PROJECT_STATUS_5);
		this.messageCenterService.sendPassedMsg(Long.valueOf(workflowId),  wfParam.getCurUserLogName(), wfParam.getCurUserName());
		this.updateDocumentIndex(workflowId, true);
		
		// 修改关联抵押物的已设定担保金额
		ProjectApplication projectApplication=projectApplicationDao.findProjectApplicationByWorkflowId(Long.valueOf(wfParam.getWorkflowId()));
		List<ProjectPawnInfo> projectPawnInfos = projectPawnInfoDao.findByProjectId(projectApplication.getProjectId());
		for (ProjectPawnInfo projectPawnInfo : projectPawnInfos) {
			Collateral collateral=collateralDao.findOne(projectPawnInfo.getGuarantyId());
			BigDecimal setGuarantyAmt=collateral.getSetGuarantyAmt();
			if(setGuarantyAmt==null) {
				setGuarantyAmt=new BigDecimal(0);
			}
			collateral.setSetGuarantyAmt(setGuarantyAmt.add(projectPawnInfo.getAppGuaDebtInterAmt()));
		}
		
		boolean flag = false;
		try {
			//审批贷款条件通过，修改授信合同剩余可用金额
			BigDecimal applyAmont= new BigDecimal(Double.valueOf(approvalContent.getApplyAmont().replaceAll(",", "")));
			BigDecimal amt=creditContract.getCreditAvaiableAmt();
			BigDecimal creditAvaiableAmt=MathUtil.BDsubtract(amt,applyAmont,2);
			underCreditContractMngService.updateCreditAvaiableAmt(parentBusinessId,creditAvaiableAmt);
		 	flag = this.workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),wfParam.getWorkFlowNode(),
							ActionCode.COMMIT)).getSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("审核贷款条件工作流报错或更新授信可用余额失败");
		}
		if (!flag) {
			throw new RuntimeException("审核贷款条件工作流返回结果为空");
		}
		return "success";
	}
}
