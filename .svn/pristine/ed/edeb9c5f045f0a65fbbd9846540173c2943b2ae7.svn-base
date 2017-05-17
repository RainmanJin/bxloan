package com.coamctech.bxloan.web.controller.workflowmonitor;

import static com.coamctech.bxloan.commons.GlobalConstants.WORKFLOW_MONITOR;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.common.SysCommonService;
import com.coamctech.bxloan.service.model.workflow.HandledWorkflows;
import com.coamctech.bxloan.service.model.workflow.PageTypedResultData;
import com.coamctech.bxloan.service.model.workflow.TaskTransferProcess;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.SysCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.service.workflowmonitor.WorkflowMonitorService;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.google.common.collect.Lists;

/**
 * 流程监控模块
 * 
 * @author xc
 */
@Controller
@RequestMapping("/" + WORKFLOW_MONITOR)
public class WorkflowMonitoController extends BaseController {

	@Autowired
	private WorkflowMonitorService wfMonitorService;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private ApprovalService approvalService;
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private SysCommonService sysCommonService;
	
	@Autowired
	private CreditContractApprovalService creditContractApprovalService;
	
	@RequestMapping
	public String index(Model model) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("sys_adm_level", sysCommonService.getAdminLevels(shiroUser.getId()));
		return WORKFLOW_MONITOR + "/main";
	}

	/**
	 * 分页查询
	 * 
	 * @param customerName
	 * @param processType
	 * @param processStatus
	 * @param submitTimeStart
	 * @param submitTimeEnd
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/search")
	public DataTablesPage search(
			@RequestParam("taskSubject") String taskSubject,
			@RequestParam("processType") String processType,
			@RequestParam("processStatus") String processStatus,
			@RequestParam("submitTimeStart") String submitTimeStart,
			@RequestParam("submitTimeEnd") String submitTimeEnd,
			@RequestParam("taskEndDateStart") String taskEndDateStart,
			@RequestParam("taskEndDateEnd") String taskEndDateEnd,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		//获取用户名和机构id
		String logName = this.userLogonName();
		String orgId = this.userOrgIds();
		/*目前这样取权限的方法不对，写的太死*/
//		AdminLevel curUserLevel = this.wfMonitorService.findUserAdminLevel(curUserId());//查看单个工作流的详细过程
//		switch (curUserLevel) {
//		case COMMON:
//			logName = curUserLogonName();
//			break;
//		case CLASS_TWO:
//			orgId = curUserOrgId();
//			break;
//		case CLASS_ONE:
//			break;
//		default:
//			break;
//		}

		// 流程类型处理
		List<WorkFlowCode> workFlowCodes = Lists.newArrayList();
		if (StringUtils.isNotBlank(processType)) {
			WorkFlowCode wfCode = WorkFlowCode.getById(processType);
			if (wfCode != null) {
				workFlowCodes = Lists.newArrayList();
				workFlowCodes.add(wfCode);
			}
		}
		DataTablesPage dt = null;
		try {
			TypedResult<PageTypedResultData<HandledWorkflows>> result = workFlowService
					.getHandledWorkflowsByUser(
							orgId != null ? String.valueOf(orgId) : "",
							logName, SysCode.WD_SYS, "DESC", workFlowCodes,
							processStatus, taskSubject, submitTimeStart,
							submitTimeEnd,taskEndDateStart,taskEndDateEnd, firstIndex / pageSize + 1, pageSize);
			if (result.getSuccess()) {
				dt = new DataTablesPage(sEcho, result.getData());
			} else {
				dt = new DataTablesPage(sEcho, Collections.EMPTY_LIST, 0L);
				logger.error("findTodoList error!>>>>>" + result.getMsg());
			}
		} catch (Exception e) {
			logger.error("查询出错", e);
			dt = new DataTablesPage(sEcho, Collections.EMPTY_LIST, 0L);
		}
		return dt;
	}

	/**
	 * 查看工作流详细
	 * 
	 * @param workflowId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/detail")
	public Result viewDetail(@RequestParam("workflowId") String workflowId) {
		// List<WorkflowMonitorDetailVO> wfDetails =
		// this.wfMonitorService.findDetaiByWorkId(workflowId);
		try {
			TypedResult<List<TaskTransferProcess>> result = workFlowService
					.getTaskTransferProcessOfWorkflow(workflowId, "DESC");
			logger.info(JSON.toJSONString(result.getData()));
			return success(this.curUserOrgId().toString(), result.getData());
		} catch (Exception e) {
			String err = "查询失败";
			logger.error(err, e);
			return failure(err);
		}
	}

	/**
	 * 终止操作
	 * 
	 * @param workflowId
	 * @param taskId
	 * @param taskTypeId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/cancel")
	public Result cancelWorkflow(HandledWorkflows handle) {
		String curUserLogonName = this.curUserLogonName();
		String curUserName = this.curUserName();
		//校验必须字段
		if (StringUtils.isEmpty(handle.getNodeId())
				|| StringUtils.isEmpty(handle.getWorkflowTypeCode())
				|| StringUtils.isEmpty(handle.getNodeId())
				|| StringUtils.isEmpty(handle.getTaskId())) {
			return failure("终止操作失败！");
		}
		try {
			//根据流程类型代码进行枚举转换
			WorkFlowCode workflowCode = WorkFlowCode.getById(handle.getWorkflowTypeCode());
			switch(workflowCode){
				case EASY_LOAN:
					approvalService.cancelApproval(
							handle.getWorkflowId(),
							curUserLogonName, 
							handle.getTaskId(),
							handle.getNodeId(), 
							handle.getComments(), 
							curUserName,
							handle.getTaskStatus());
					break;
				case MICRO_LOAN:
					approvalService.dropBackApproval(
							handle.getWorkflowId(),
							handle.getTaskId(), 
							handle.getComments(), 
							curUserLogonName,
							handle.getNodeId(), 
							curUserName,
							handle.getTaskStatus());
					
					break;
				case CREDIT_APPROVAL:
					//撤销授信审批流程
					this.dropBack(handle);
					break;
				case CREDIT_LOAN:
					//撤销授信借款流程
					this.dropBack(handle);
					break;
				default:
					break;
			}
			return success("终止操作成功！");
		} catch (Exception e) {
			logger.error("终止操作是调用工作流webservice时出现异常", e.getMessage());
			return failure("终止操作时出现异常");
		}
	}
	
	/**
	 * 暂停或继续流程
	 * @author:gph
	 * @createTime:2015年5月28日
	 * @param taskId 
	 * @param workflowId
	 * @param taskStatus 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pauseOrPlayTask")
	public Result pauseOrPlayTask(HandledWorkflows handle){
		try {
			String curUserLogonName = this.curUserLogonName();
			// 调用暂停接口
			Result result = workFlowService.pauseOrPlayTask(
					handle.getWorkflowTypeCode(),
					handle.getWorkflowId(),
					handle.getTaskId(),
					handle.getTaskStatus(),
					curUserLogonName,
					"");
			if (result.getSuccess()) {
				return success("操作成功！");
			} else {
				return failure("操作失败！");
			}
		} catch (Exception e) {
			logger.error("操作失败！", e.getMessage());
			return failure("操作失败！");
		} 
	}

	/** 
	 * 撤销授信审批流程和授信借款流程
	 * 
	 * @param handle
	 */
	private void dropBack(HandledWorkflows handle){
		String curUserLogonName = this.curUserLogonName();
		String curUserName = this.curUserName();
		String workflowTypeCode = handle.getWorkflowTypeCode();
		String workflowId = handle.getWorkflowId();
		String taskStageCode = handle.getNodeId();
		String taskId = handle.getTaskId();
		String comments = handle.getComments();
		WorkFlowNode workflowNode = WorkFlowNode.getNodeById(taskStageCode);
		WorkFlowCode workflowCode = WorkFlowCode.getById(workflowTypeCode);
		//撤销流程
		creditContractApprovalService.dropBackApproval(
				workflowCode,
				workflowNode, 
				workflowId,
				taskId,
				null, 
				comments, 
				curUserLogonName, 
				curUserName);
	}
	
	// ///////////////////
	// /////PRIVATE///////
	// ///////////////////
	private String curUserName() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getName();
		}
		return curUser;
	}

	private String curUserLogonName() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getLogname();
		}
		return curUser;
	}

	private String curUserNum() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getUsernum();
		}
		return curUser;
	}

	private String curUserId() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getId().toString();
		}
		return curUser;
	}
	
	private Long curUserOrgId() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		//modify by wangyawei 20150702 start 将所属机构修改为登陆机构
		if (shiroUser != null) {
			return shiroUser.getLogOrgid();
		}
		//modify by wangyawei 20150702 end
		return null;
	}

	/**
	 * 获取用户下有权限的机构列表</br>
	 * 如果是总公司：机构是小贷公司列表</br>
	 * 如果是小贷公司或客户经理：机构是shiroUser中的logOrgid</br>
	 * @author:gph</br>
	 * @createTime:2015年5月25日</br>
	 * @return
	 */
	private String userOrgIds() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		List<Long> orgIds = Lists.newArrayList();
		if(shiroUser.getDataAuthType() != null){
			switch (shiroUser.getDataAuthType()) {
			case HeadOffice:// 总部
				orgIds = shiroUser.getDataAuthOrgIds();
				if (orgIds.size() > 0) {
					return CommonHelper.list2Str(shiroUser.getDataAuthOrgIds(), ",");
				}
			case LoanCompany:// 小贷公司
				return String.valueOf(shiroUser.getLogOrgid());
			default:// 客户经理
				break;
			}
		}
		return null;
	}
	
	/**
	 * 获取当前用户的名字拼音</br>
	 * 如果是客户经理，获取的是当前用户的名字拼音。例如:houhailiang</br>
	 * 如果是总部或者小贷公司，获取的名字为""</br>
	 * @author:gph</br>
	 * @createTime:2015年5月25日</br>
	 * @return
	 */
	private String userLogonName() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(shiroUser.getDataAuthType() != null){
			switch (shiroUser.getDataAuthType()) {
			case CustomerManager:
				return shiroUser.getLogname();
			default:
				return "";
			}
		}else{
			return shiroUser.getLogname();
		}
	}
}
