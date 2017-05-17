package com.coamctech.bxloan.service.approval.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.ApprovalHistoryBxloanDao;
import com.coamctech.bxloan.dao.ApprovalHistoryRepayPlanDao;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.DocumentIndexDao;
import com.coamctech.bxloan.dao.MoneyRateDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.RepayPlanDao;
import com.coamctech.bxloan.dao.RepayPlanTempDao;
import com.coamctech.bxloan.dao.TbMultiopinionDao;
import com.coamctech.bxloan.dao.WfBusinessRelationDao;
import com.coamctech.bxloan.dao.credit.CreditContractDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ApprovalHistoryBxloan;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RepayPlan;
import com.coamctech.bxloan.entity.RepayPlanTemp;
import com.coamctech.bxloan.entity.TbMultiopinion;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.message.MessageCenterService;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;
import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowTaskIds;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.google.common.collect.Lists;

@Service
@Transactional
public class CreditContractApprovalServiceImpl implements CreditContractApprovalService {

	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Autowired
	private ProjectApplicationDao proAppDao;

	@Autowired
	private DocumentIndexDao documentIndexDao;

	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private MessageCenterService messageCenterService;
	
	@Autowired
	private WfBusinessRelationDao wfBusinessRelationDao;
	
	@Autowired
	private BizRateDao bizRateDao;
	
	@Autowired
	private TbMultiopinionDao tbMultiopinionDao;
	
	@Autowired
	private RepayPlanDao repayPlanDao;
	
	@Autowired
	private RepayPlanTempDao repayPlanTempDao;
	
	@Autowired
	private ApprovalHistoryBxloanDao approvalHistoryBxloanDao;
	
	@Autowired
	private ApprovalHistoryRepayPlanDao approvalHistoryRepayPlanDao;
	
	@Autowired
	private MoneyRateDao moneyRateDao;
	
	@Autowired
	private CreditContractDao creditContractDao;
	
	@Autowired
	private CollateralDao collateralDao;
	
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	
	@Autowired
	private BusinessApplicationService businessApplicationService;
	
	@Override
	public void quitApproval(WorkFlowCode workFlowCode,
			WorkFlowNode workFlowNode, String workflowId, String taskId,
			String comments, String curUserLogName, String curUserName) {
		//获取流程与业务关系对象
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//若流程类型为授信审批流程，退回环节为总经理审核合同和签订合同，则业务状态修改为"已批复"
		//若流程类型为授信借款流程，退回环节为总经理审核合同，落实放款条件和审核放款条件，则业务状态修改为"已批复"
		if(workFlowNode.equals(WorkFlowNode.CA_GeneralManagerCheckLoan) || 
			workFlowNode.equals(WorkFlowNode.CA_FulfillPayLoanFactor) ||
			workFlowNode.equals(WorkFlowNode.LA_GeneralManagerCheckLoan) ||
			workFlowNode.equals(WorkFlowNode.LA_FulfillPayLoanFactor) ||
			workFlowNode.equals(WorkFlowNode.LA_CheckLoanFactor)){
			this.updateProjectStatusByProjectId(businessId, dataDict.getCodeVal("ProjectStatus", "S2"));
		} else{
			this.updateProjectStatusByProjectId(businessId, dataDict.getCodeVal("ProjectStatus", "S0"));
		}
		
		//发送退回消息
		this.messageCenterService.sendRetreatedMsg(Long.valueOf(workflowId), curUserLogName, curUserName);
		
		//删除批复自定义还款计划信息
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from approval_history_repay_plan ahrp ")
		   .append(" where ahrp.approval_history_id is not null ")
		   .append(" and ahrp.approval_history_id != 0 ")
		   .append(" and ahrp.project_id = ").append(wfBusinessRelation.getBusinessId());
		dynamicQuery.nativeExecuteUpdate(sql.toString());
		
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(ExeTaskParam.newExeTaskParam(workFlowCode,
							workflowId, taskId, curUserLogName, workFlowNode,
							ActionCode.SEND_BACK, StringUtils.EMPTY, comments,
							StringUtils.EMPTY, null)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("退回工作流返回结果为空");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("退回工作流返回结果为空");
		}
	}
	
	@Override
	public void dropBackApproval(WorkFlowCode workFlowCode,
			WorkFlowNode workFlowNode, String workflowId, String taskId,
			String actionCode, String comments, String curUserLogName,
			String curUserName) {
		//获取流程与业务关系对象
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//修改业务状态
		this.updateProjectStatusByProjectId(businessId, dataDict.getCodeVal("ProjectStatus", "S4"));
		
		//授信审批流程撤销后，修改授信合同状态"已失效"
		if(workFlowCode.equals(WorkFlowCode.CREDIT_APPROVAL)){
			this.updateCreditContractByProjectId(businessId, dataDict.getCodeVal("CreditContractStatus", "S4"));
		}

		//释放抵质押物信息
		this.handlePledgeWhenRejectOrCancel(businessId);
		
		//修改文档信息
		this.updateDocumentIndex(businessId, true);
		
		//发送拒绝消息
		messageCenterService.sendRefusedMsg(Long.valueOf(workflowId), curUserLogName, curUserName);
		
		ActionCode acode = ActionCode.CANCEL;
		if(StringUtils.isNotEmpty(actionCode) && ActionCode.FINISH.getCodeId().equals(actionCode)){
			acode = ActionCode.FINISH;
		}else{
			acode = ActionCode.CANCEL;
		}
		
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					ExeTaskParam.newExeTaskParam(workFlowCode, workflowId, taskId,
							curUserLogName, workFlowNode, acode, StringUtils.EMPTY,
							comments, StringUtils.EMPTY, null)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("撤销工作流返回结果为空");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("撤销工作流返回结果为空");
		}
	}
	
	@Override
	public void handlePledgeWhenRejectOrCancel(Long projectId){
		List<ProjectPawnInfo> pawnInfoList = projectPawnInfoDao.findByProjectId(projectId);
		if(pawnInfoList == null || pawnInfoList.size() <= 0){
			return;
		}
		for(ProjectPawnInfo projectPawnInfo : pawnInfoList){
			//修改业务担保物表抵质押状态为"关联已删除"
			projectPawnInfo.setPawnState(dataDict.getCodeVal("PawnState", "S4"));
			
			//通过项目id、抵质押物状态、抵质押物id，查询除当前业务外,抵质押物是否关联其他业务
			List<ProjectPawnInfo> otherPawnList = projectPawnInfoDao.findByProjectIdAndPawnStateAndGuarantyId(
							projectId,
							dataDict.getCodeVal("PawnState", "S2"),
							projectPawnInfo.getGuarantyId());
			//获取抵质押物对象
			Long guarantyId = projectPawnInfo.getGuarantyId();
			Collateral collateral = collateralDao.findOne(guarantyId);
			
			if (otherPawnList != null && otherPawnList.size() > 0) {
				//若抵质押物关联其他业务且均"已解除关联"，则设置抵质押物状态为"已解除关联"
				if (checkPawnInfoRelease(guarantyId)) {
					collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S3").getCodeValue());
					//锁定金额回滚
					collateral.setLockingAmount(MathUtil.BDsubtract(
							collateral.getLockingAmount(),
							projectPawnInfo.getAppGuaDebtInterAmt(), 
							GlobalConstants.BIGDECIMAL_SCALE));
				} else {
					//若抵质押物关联其他业务且均"未解除关联"，则设置抵质押物状态为"已关联"
					collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S2").getCodeValue());
				}
			} else {
				//若当前业务关联的抵质押物没有关联其他业务，则设置抵质押物状态为"未关联"
				collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S1").getCodeValue());
			}
		}
	}
	
	@Override
	public boolean checkPawnInfoRelease(Long guarantyId) {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append(" select pa.project_id from project_application pa, ")
		  .append(" (select p.project_id from project_pawn_info p where p.pawn_state != '4' and p.guaranty_id = ?1) pb ")
		  .append(" where pa.project_id = pb.project_id and pa.project_status in ('0', '1', '2') ")
          .append(" union ")
          .append(" select pa.project_id from project_application pa,  ")
          .append(" (select c.project_id from contract c where c.project_id in ")
          .append(" (select p.project_id from project_pawn_info p where p.pawn_state != '4'  and p.guaranty_id = ?2) ")
          .append(" and c.contract_status_cd not in ('425', '331', '500') ")
          .append(" and c.if_release_flag is null) pc ")
          .append(" where pa.project_id = pc.project_id ");
		//封装查询参数
		params.add(guarantyId);
		params.add(guarantyId);
		return dynamicQuery.nativeQueryCount(sb.toString(), params.toArray()).compareTo(0L) > 0 ? false : true;
	}
	
	@Override
	public void updateProjectStatusByProjectId(Long projectId, String status) {
		proAppDao.updateProjectStatusByProjectId(projectId, status);
	}
	
	@Override
	public void updateCreditContractByProjectId(Long projectId, String contractStatusCd){
		creditContractDao.updateCreditContractByProjectId(projectId, contractStatusCd);
	}
	
	@Override
	public void updateDocumentIndex(Long businessId, boolean flag) {
		ProjectApplication proApp = proAppDao.findOne(businessId);
		//修改审批流程中上传文档的文档状态
		documentIndexDao.updateCreateType(proApp.getPartyId(), proApp.getProjectNo());
		//修改维护客户信息时上传的文档状态
		if(flag) {
			documentIndexDao.changeCreateTypeWhenWorkflowFinish(proApp.getPartyId());
		}
	}
	
	@Override
	public NextTaskReceiver getLeastTaskPerson(String taskId, String actionCode){
		TypedResult<List<NextTaskReceiver>> resultList = workFlowService.getNextTaskReceivers(taskId, ActionCode.getActionCodeById(actionCode));
		NextTaskReceiver receiver = null;
		for(NextTaskReceiver rec : resultList.getData()){
			if(receiver== null ||
					Integer.parseInt(receiver.getTotal()) > Integer.parseInt(rec.getTotal())){
				receiver = rec;
			}
		}
		return receiver;
	}
	
	@Override
	public ActionCode findNextAction(Long workflowId, String taskStageCode) {
		String actionCode = null;
		StringBuilder jql = new StringBuilder();
		jql.append("select pa,pwc.ruleconfig ");
		jql.append("from ProjectApplication pa,ProductWfConfig pwc  ");
		jql.append("where pa.workflowId=?1 AND pwc.pcId=( ");
		jql.append("  select pcId  ");
		jql.append("  from ProductConfig pc ");
		jql.append("  where cast(pc.productCd as string) = pa.productType ");
		jql.append("  and  cast(pc.orgId as string) = pa.applyOrg ");
		jql.append(") ");
		jql.append("and pwc.taskStageCode = ?2 ");

		List<Object[]> result = dynamicQuery.query(Object[].class, jql.toString(), workflowId, taskStageCode);
		if (result == null || result.isEmpty()) {
			return ActionCode.COMMIT;
		}
		ProjectApplication pa = (ProjectApplication) result.get(0)[0];
		String rule = result.get(0)[1] == null ? "" : result.get(0)[1].toString();
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
	public String submitLoanReviewApproval(BizApprovalOfWfParam wfParam) {
		//获取流程与业务关系对象
		String workflowId = wfParam.getWorkflowId();
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//修改文档信息
		this.updateDocumentIndex(businessId, false);
		
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(), wfParam.getWorkFlowNode(), ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("贷款审查提交时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null	|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("贷款审查提交时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	
	@Override
	public String submitBasisCheckApproval(BizApprovalOfWfParam wfParam) {
		//获取流程与业务关系对象
		String workflowId = wfParam.getWorkflowId();
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();

		//修改文档信息
		this.updateDocumentIndex(businessId, false);
		
		String taskStageCode = wfParam.getWorkFlowNode().getId();
		WorkFlowTaskIds workFlowTaskIds;
		try {
			//获取下一环节任务操作
			ActionCode nextCode = this.findNextAction(Long.parseLong(workflowId), taskStageCode);
			
			//调用执行任务接口
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
							wfParam.getWorkFlowNode(), nextCode)).getData();
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
	public String submitOneTaskAssignApproval(BizApprovalOfWfParam wfParam) {
		//获取流程与业务关系对象
		String workflowId = wfParam.getWorkflowId();
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//修改文档信息
		this.updateDocumentIndex(businessId, false);
		
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
							wfParam.getWorkFlowNode(),
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
	
	@Override
	public String submitOneLevelApproval(BizApprovalOfWfParam wfParam) {
		//获取流程与业务关系对象
		String workflowId = wfParam.getWorkflowId();
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//修改文档信息
		this.updateDocumentIndex(businessId, false);
		
		String taskTypeId = wfParam.getWorkFlowNode().getId();
		WorkFlowTaskIds workFlowTaskIds;
		try {
			ActionCode nextCode = this.findNextAction(Long.parseLong(workflowId), taskTypeId);
			boolean showContract = ActionCode.CA_SUBMIT_TO_CONTACT.equals(nextCode);
			if(!showContract){
				//下一环节为二级审批
				workFlowTaskIds = workFlowService.executeTask(
						wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
								wfParam.getWorkFlowNode(),
								ActionCode.COMMIT)).getData();
			} else{
				//下一环节为制定电子合同
				workFlowTaskIds = workFlowService.executeTask(
						wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
								wfParam.getWorkFlowNode(),
								ActionCode.CA_SUBMIT_TO_CONTACT)).getData();
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

	@Override
	public String submitTwoLevelApproval(BizApprovalOfWfParam wfParam) {
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
							wfParam.getWorkFlowNode(),
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

	@Override
	public String submitSignContract(BizApprovalOfWfParam wfParam){
		//获取流程与业务关系对象
		String workflowId = wfParam.getWorkflowId();
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//修改文档信息
		this.updateDocumentIndex(businessId, false);
		
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
							wfParam.getWorkFlowNode(),
							ActionCode.COMMIT)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("提交下一环节时调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("提交下一环节时调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	
	@Override
	public String submitGeMgCheckLoanApproval(BizApprovalOfWfParam wfParam) {
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
							wfParam.getWorkFlowNode(), ActionCode.COMMIT)).getData();
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
	public String submitFulfillPayLoanFactApproval(BizApprovalOfWfParam wfParam) {
		//获取流程与业务关系对象
		String workflowId = wfParam.getWorkflowId();
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//修改文档信息
		this.updateDocumentIndex(businessId, false);
		
		//授信审批流程流程结束后，修改授信合同状态"已生效"，修改业务状态"已签合同"
		if(wfParam.getWorkFlowCode().equals(WorkFlowCode.CREDIT_APPROVAL)){
			//修改授信合同状态
			this.updateCreditContractByProjectId(businessId, dataDict.getCodeVal("CreditContractStatus", "S2"));
			
			//修改业务状态
			this.updateProjectStatusByProjectId(businessId, dataDict.getCodeVal("ProjectStatus", "S5"));
			
			//发送通过消息
			this.messageCenterService.sendPassedMsg(Long.valueOf(workflowId),  wfParam.getCurUserLogName(), wfParam.getCurUserName());
		}
		//调用执行任务接口
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
							wfParam.getWorkFlowNode(), ActionCode.COMMIT)).getData();
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
	public String submitToSignContractApproval(BizApprovalOfWfParam wfParam){
		ActionCode actionCode = ActionCode.CA_SUBMIT_TO_CONTACT;
		//获取下一环节操作人
		NextTaskReceiver nextTaskReceiver = this.getLeastTaskPerson(wfParam.getTaskId(), actionCode.getCodeId());
		wfParam.setNextUser(nextTaskReceiver.getLogName());
		wfParam.setNextUserOrgId(nextTaskReceiver.getOrgId());

		//获取流程与业务关系对象
		String workflowId = wfParam.getWorkflowId();
		WfBusinessRelation wfBusinessRelation = wfBusinessRelationDao.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		//修改文档信息
		this.updateDocumentIndex(businessId, false);
				
		WorkFlowTaskIds workFlowTaskIds;
		try {
			workFlowTaskIds = workFlowService.executeTask(
					wfParam.toExeTaskParam(wfParam.getWorkFlowCode(),
							wfParam.getWorkFlowNode(), actionCode)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("提交到制定电子合同环节调用webservice抛出异常");
		}
		if (workFlowTaskIds == null
				|| StringUtils.isBlank(workFlowTaskIds.getWorkflowId())) {
			throw new RuntimeException("提交到制定电子合同环节调用webservice返回为空值");
		}
		return workFlowTaskIds.getWorkflowId();
	}
	
	@Override
	public void updateProjectApplicationApprovalInfo(ProjAppVo projApp, boolean agree, SessionUser sessionUser, List params) {
		if (projApp == null || projApp.getProjectId() == null) {
			throw new NullPointerException("参数错误");
		}
		//保存业务信息
		this.saveProjectApplication(projApp, sessionUser, params);
		
		//保存利率信息
		this.saveBizRate(projApp);
		
		//保存批复历史信息
		Long approvalHistoryBxloanID = this.saveApprovalHistoryBxloan(projApp, params);
		
		//保存批复意见信息
		this.saveTbMultiopinion(projApp, agree, sessionUser);
		
		//保存批复自定义还款计划信息
		this.saveApprovalHistoryRepayPlan(projApp, approvalHistoryBxloanID);
	}

	/** 
	 * 保存业务信息
	 * 
	 * @param projApp
	 * @param sessionUser
	 */
	private void saveProjectApplication(ProjAppVo projApp, SessionUser sessionUser, List params) {
		//保存业务申请信息
		ProjectApplication pa = proAppDao.findOne(projApp.getProjectId());
		if (pa == null) {
			throw new NullPointerException("ProjectApplication 不存在");
		}
		pa.setCurrentApproveNum(CommonHelper.toStr(sessionUser.getUserId()));
		pa.setCurrentApproveOrg(CommonHelper.toStr(sessionUser.getOrgId()));
		pa.setCurrentApproveRole(CommonHelper.toStr(sessionUser.getRoleId()));
		pa.setApproveAmt(projApp.getApproveAmt());
		pa.setApproveDate(projApp.getApproveDate());
		pa.setApproveRepayingMode(projApp.getApproveRepayingMode());
		pa.setTerm(projApp.getTerm());
		pa.setTermUnit(projApp.getTermUnit());
		pa.setProjectStatus(dataDict.getCodeVal("ProjectStatus", "S2"));// 已批复
		String workflowId = String.valueOf(params.get(0));
		String taskStageCode = String.valueOf(params.get(2));
		WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
		ActionCode actionCode = this.findNextAction(Long.parseLong(workflowId), taskStageCode);
		//若当前环节为二级审批或者初审环节、一级审批下一环节为制定电子合同环节，保存授信类型
		if (WorkFlowNode.CA_TwoLevelApproval.equals(workFlowNode) || 
			ActionCode.CA_SUBMIT_TO_CONTACT.equals(actionCode)) {
			pa.setCreditType(projApp.getCreditType());
			pa.setTempCreditType(null);
		}
		//存储授信类型-批复阶段反显上一环节批复信息使用
		pa.setTempCreditType(projApp.getCreditType());
		proAppDao.save(pa);
	}
	
	/** 
	 * 保存利率信息
	 * 
	 * @param projApp
	 * @param approveRate
	 */
	private void saveBizRate(ProjAppVo projApp) {
		//保存利率信息
		BizRate br = bizRateDao.findByProjectId(String.valueOf(projApp.getProjectId()));
		if(br == null) {
			throw new NullPointerException("BizRate 不存在");
		}
		//审批利率设置
		br.setApproveIrTypeCd(projApp.getApproveIrTypeCd());
		br.setApproveAdjustPeriod(projApp.getApproveAdjustPeriod());
		br.setApproveFloatRate(projApp.getApproveFloatRate());
		//最终利率设置
		br.setFinalIrTypeCd(projApp.getApproveIrTypeCd());
		br.setFinalAdjustPeriod(projApp.getApproveAdjustPeriod());
		br.setFinalFloatRate(projApp.getApproveFloatRate());
		//重新计算年利率
		br.setApproveRateValue(projApp.getApproveRateValue());
		br.setFinalRateValue(projApp.getApproveRateValue());
		bizRateDao.save(br);
	}
	
	/** 
	 * 保存批复历史信息
	 * 
	 * @param projApp
	 * @param params
	 * @param approveRate
	 * @return 批复历史ID
	 */
	private Long saveApprovalHistoryBxloan(ProjAppVo projApp, List params) {
		//保存批复信息
		ApprovalHistoryBxloan ahb = new ApprovalHistoryBxloan();
		ahb.setProjectId(projApp.getProjectId());
		ahb.setWorkflowId(Long.valueOf((String) params.get(0)));
		ahb.setTaskId(Long.valueOf((String) params.get(1)));
		ahb.setTaskStageCode(String.valueOf(params.get(2)));
		ahb.setApproveAmt(projApp.getApproveAmt());
		ahb.setApproveRepayingMode(projApp.getApproveRepayingMode());
		ahb.setApproveTerm(new Long(projApp.getTerm()));
		ahb.setTermUnit(projApp.getTermUnit());
		ahb.setApproveIrTypeCd(projApp.getApproveIrTypeCd());
		ahb.setApproveAdjustPeriod(projApp.getApproveAdjustPeriod());
		ahb.setApproveFloatRate(projApp.getApproveFloatRate());
		ahb.setApproveRateValue(projApp.getApproveRateValue());
		ahb.setApprovalOpinion(projApp.getReplyOpinion());
		ahb.setApproveDate(projApp.getApproveDate());
		ahb.setCreateDate(new Date());
		ahb.setCreditType(projApp.getCreditType());
		return approvalHistoryBxloanDao.save(ahb).getId();		
	}
	
	/** 
	 * 保存意见详情信息
	 * 
	 * @param projApp
	 * @param agree
	 * @param sessionUser
	 */
	private void saveTbMultiopinion(ProjAppVo projApp, boolean agree, SessionUser sessionUser) {
		//保存意见信息
		TbMultiopinion multiopinion = new TbMultiopinion();
		multiopinion.setOpinion(projApp.getReplyOpinion());
		multiopinion.setOperationid(projApp.getProjectId());
		multiopinion.setOpiniontype("conclusion");//
		multiopinion.setSigntime(new Date());
		multiopinion.setPersonid(sessionUser.getLoginName());// 当前用户登录名
		multiopinion.setPersoncn(sessionUser.getUserNameCN());// 当前用户中文名
		multiopinion.setIssueorgid(sessionUser.getOrgId());// 当前用户机构id
		multiopinion.setConclusion(agree ? "01" : "02");// 01：同意，02：否决
		multiopinion.setRoleid(sessionUser.getRoleId());
		tbMultiopinionDao.save(multiopinion);		
	}
	
	/** 
	 * 保存自定义还款计划信息
	 * 
	 * @param projApp
	 * @param approvalHistoryBxloanID
	 */
	private void saveApprovalHistoryRepayPlan(ProjAppVo projApp, Long approvalHistoryBxloanID) {
		Long projectId = projApp.getProjectId();
		//复制一份新的自定义还款计划，下一环节反显使用
		List<ApprovalHistoryRepayPlan> approRepayPlans = approvalHistoryRepayPlanDao.findByApprovalHistoryIdAndProjectId(null, projectId);
		if(approRepayPlans == null || approRepayPlans.size() <= 0){
			return;
		}
		List<RepayPlan> newRepayPlans = new ArrayList<RepayPlan>();
		List<RepayPlanTemp> newRepayPlanTemps = new ArrayList<RepayPlanTemp>();
		List<ApprovalHistoryRepayPlan> newApprovalRepayPlans = new ArrayList<ApprovalHistoryRepayPlan>();
		for (ApprovalHistoryRepayPlan approRepayPlan : approRepayPlans) {
			RepayPlan newRepayPlan = new RepayPlan();
			BeanUtils.copyProperties(approRepayPlan, newRepayPlan);
			newRepayPlans.add(newRepayPlan);
			
			RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
			BeanUtils.copyProperties(newRepayPlan, repayPlanTemp);
			newRepayPlanTemps.add(repayPlanTemp);
			
			ApprovalHistoryRepayPlan newApprovalHistoryRepayPlan = new ApprovalHistoryRepayPlan();
			BeanUtils.copyProperties(approRepayPlan, newApprovalHistoryRepayPlan, "approvalHistoryRepayPlanId", "approvalHistoryId");
			newApprovalRepayPlans.add(newApprovalHistoryRepayPlan);
		}
		//更改批复时存储的自定义还款计划
		StringBuffer nativeSql = new StringBuffer();
		List<Object> repayPlanParams = new ArrayList<Object>();
		//自定义还款方式
		if (dataDict.getCodeVal("RepaymentMode", "S3").equals(projApp.getApproveRepayingMode())) {
			nativeSql.append(" update approval_history_repay_plan  ")
					 .append(" set approval_history_id = ?1 ")
					 .append(" where approval_history_id is null and project_id =?2 ");
			repayPlanParams.add(approvalHistoryBxloanID);
			repayPlanParams.add(projectId);
		} else {
			nativeSql = new StringBuffer(" delete from approval_history_repay_plan where project_id = ?1 and approval_history_id is null ");
			repayPlanParams.add(projectId);
		}
		dynamicQuery.nativeExecuteUpdate(nativeSql.toString(), repayPlanParams.toArray());
		//删除原有自定义还款计划
		repayPlanDao.deleteByProjectId(projectId);
		
		//存储自定义还款计划信息，下一环节反显使用
		repayPlanDao.save(newRepayPlans);
		repayPlanTempDao.save(newRepayPlanTemps);
		approvalHistoryRepayPlanDao.save(newApprovalRepayPlans);		
	}
	
	@Override
	public List<String> assembleCustProjectAllDocTypes(
			WorkFlowCode workFlowCode, WorkFlowNode workFlowNode) {
		List<String> custDocTypes = Lists.newArrayList();
		
		switch(workFlowNode){
			case CA_LoanReview:
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S52"));
				break;
			case CA_BasisCheck:
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S53"));
				break;
			case CA_FulfillPayLoanFactor:
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S57"));
				break;
			case LA_LoanReview:
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S52"));
				break;
			case LA_BasisCheck:
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S53"));
				break;
			case LA_FulfillPayLoanFactor:
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S57"));
				break;
			default: 
				break;
		}
		return custDocTypes;
	}
	
	@Override
	public List<String> assembleApprovalAllDocTypes(WorkFlowCode workFlowCode,
			WorkFlowNode workFlowNode) {
		List<String> allDocTypes = Lists.newArrayList();
		
		switch(workFlowNode){
			case CA_LoanReview:
				allDocTypes.add(DocStageCode.DKSC_WD_DOC.getCodeId());
				break;
			case CA_BasisCheck:
				allDocTypes.add(DocStageCode.CS_DOC.getCodeId());
				break;
			case CA_FulfillPayLoanFactor:
				allDocTypes.add(DocStageCode.LSFK_WD_DOC.getCodeId());
				break;
			case LA_LoanReview:
				allDocTypes.add(DocStageCode.DKSC_WD_DOC.getCodeId());
				break;
			case LA_BasisCheck:
				allDocTypes.add(DocStageCode.CS_DOC.getCodeId());
				break;
			case LA_FulfillPayLoanFactor:
				allDocTypes.add(DocStageCode.LSFK_WD_DOC.getCodeId());
				break;
			default: 
				break;
		}
		return allDocTypes;
	}
}
