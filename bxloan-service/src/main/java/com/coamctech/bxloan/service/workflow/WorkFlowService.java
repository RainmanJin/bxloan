package com.coamctech.bxloan.service.workflow;

import java.util.List;

import com.coamctech.bxloan.service.model.workflow.DoneTask;
import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.model.workflow.HandledWorkflows;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.PageTypedResultData;
import com.coamctech.bxloan.service.model.workflow.TaskAction;
import com.coamctech.bxloan.service.model.workflow.TaskTransferProcess;
import com.coamctech.bxloan.service.model.workflow.TodoTask;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowId;
import com.coamctech.bxloan.service.model.workflow.WorkFlowTaskIds;

public interface WorkFlowService {
	
	/**系统编号**/
	
	/**流程类型编号 :易贷集中审批流程**/
	
	/**流程类型编号 :微贷产品集中审批流程**/
	
	/**系统编号**/
	public static enum SysCode{
		/**微贷系统code**/
		WD_SYS("微贷系统code","2");
		
		private final String codeName;
		private final String codeId;
		
		private SysCode(String codeName,String codeId){
			this.codeId = codeId;
			this.codeName = codeName;
		}
		public String getCodeName() {
			return codeName;
		}
		public String getCodeId() {
			return codeId;
		}
		
		public static SysCode getById(String codeId){
			for (SysCode sysCode : SysCode.values()) {
				if(sysCode.getCodeId().equals(codeId)){
					return sysCode;
				}
			}
			throw new IllegalArgumentException("没有id为" + codeId + "的系统");
		}
	}
	
	/****
	 * 流程类型
	 * @author xc
	 * @lastModified wangyawei 2015-07-21 9:29:24
	 */
	public static enum WorkFlowCode{
		/** 易贷审批流程 */
		EASY_LOAN("易贷审批流程","1003"),
		/** 微贷审批流程 */
		MICRO_LOAN("微贷审批流程","1004"),
		/** 授信审批流程 */
		CREDIT_APPROVAL("授信审批流程", "1007"),
		/** 授信借款流程 */
		CREDIT_LOAN("授信借款流程", "1008");
		
		private final String codeName;
		private final String codeId;
		
		private WorkFlowCode(String codeName,String codeId){
			this.codeId = codeId;
			this.codeName = codeName;
		}
		public String getCodeName() {
			return codeName;
		}
		public String getCodeId() {
			return codeId;
		}
		
		public static WorkFlowCode getById(String codeId){
			for (WorkFlowCode wfCode : WorkFlowCode.values()) {
				if(wfCode.getCodeId().equals(codeId)){
					return wfCode;
				}
			}
			throw new IllegalArgumentException("没有id为" + codeId + "的流程");
		}
	}
	
	
	/****
	 * 操作按钮
	 * @author xc
	 * @lastModified wangyawei 2015-07-21 9:29:24
	 */
	public static enum ActionCode{
		/**提交审核:当前节点执行完后提交到下一节点。**/
		COMMIT("提交审核","1"),
		/**撤销:如果在某个节点设置有“撤消”的权限，说明当前执行人有权在此时撤消此流程。**/
		CANCEL("撤销","3"),
		/**退回:可退回到某个节点继续流转，<br>
		 * 具体退回到哪个节点不是由执行人指定，<br>
		 * 而是由流程设计时指定<br>
		 * （一般可指定退回到流程发起节点或前面的审批节点，默认是退回到上一节点）**/
		SEND_BACK("退回","2"),
		/**撤回:当前节点执行完后、下一节点执行前，可以收回进行修改然后再提交。**/
		RECALL("撤回","4"),
		/**转交 :转交人与接收人有完全相同的权限，转交后任务在原节点且任务状态为待处理。**/
		TRANSFER("转交","5"),
		/**挂起:相当于该流程处于挂起状态，当前节点及之后的其它节点不再执行。<br>
		 * “挂起”一般是由管理员或项目负责人操作的，执行“恢复”操作后能继续该流程。**/
		HANG_UP("挂起","6"),
		/**恢复:将处于挂起状态的流程重新恢复执行，并继续流程流转。**/
		CONTINUE("恢复","7"),
		/**完成:流程流转到最后一个节点，且最后一个节点任务处理完成时执行该操作。**/
		FINISH("终止","8"),
		/**从4014提交签订合同 **/
		SUBMIT_CONTACTA("提交签订合同","11"),
		/**从4012提交签订合同 **/
		SUBMIT_CONTACTB("提交签订合同","12"),
		/**从3010提交到审批*/
		SUBMIT_APPROVAL("提交审批","13"),
		
		/** 初审环节和一级审批环节提交到制定电子合同环节 */
		CA_SUBMIT_TO_CONTACT("提交制定电子合同","14");
		
		private final String codeName;
		private final String codeId;
		
		private ActionCode(String codeName,String codeId){
			this.codeId = codeId;
			this.codeName = codeName;
		}

		public String getCodeName() {
			return codeName;
		}

		public String getCodeId() {
			return codeId;
		}
		
		public static ActionCode getActionCodeById(String id){
			for (ActionCode actCode : ActionCode.values()) {
				if(actCode.getCodeId().equals(id)){
					return actCode;
				}
			}
			throw new IllegalArgumentException("没有id为" + id + "的操作");
		}
	}
	
	/***
	 * 流程节点
	 * @author xc
	 * @lastModified wangyawei 2015-07-21 9:29:24
	 */
	public static enum WorkFlowNode{
		/**易贷:录入业务申请信息 100310**/
		EL_EntryBusiApplInfo("录入业务申请信息","100310"),
		/**易贷:电核网核 100311**/
		EL_PhoneNetworkCheck("电核网核","100311"),
		/**易贷:审批岗审批 100312**/
		EL_ApproPostApproval("审批岗审批","100312"),
		/**易贷:稽核岗-审查 100313**/
		EL_InspectPostReview("稽核岗-审查","100313"),
		/**易贷:签订合同 100314**/
		EL_SignedContract("签订合同","100314"),
		/**易贷:审核放款 100315**/
		EL_CheckPayLoan("审核放款","100315"),
		/**易贷:落实放款 100316**/
		EL_FulfillPayLoan("落实放款","100316"),
		
		/**微贷:录入业务申请信息 100410**/
		ML_EntryBusiApplInfo("录入业务申请信息","100410"),
		/**微贷:贷款审查 100411**/
		ML_LoanReview("贷款审查","100411"),
		/**微贷:初审 100412**/
		ML_BasisCheck("初审","100412"),
		/**微贷:任务分配 100413**/
		ML_OneTaskAssign("任务分配","100413"),
		/**微贷:一级审批 100414**/
		ML_OneLevelApproval("一级审批","100414"),
		/**微贷:任务分配 100415**/
		ML_TwoTaskAssign("任务分配","100415"),
		/**微贷:二级审批 100416**/
		ML_TwoLevelApproval("二级审批","100416"),
		/**微贷:签订合同 100417**/
		ML_SignedContract("签订合同","100417"),
		/**微贷:总经理审核合同 100418**/
		ML_GeneralManagerCheckLoan("总经理审核合同","100418"),
		/**微贷:落实贷款条件 100419**/
		ML_FulfillPayLoanFactor("落实贷款条件","100419"),
		/**微贷:审核贷款条件 100420**/
		ML_CheckLoanFactor("审核贷款条件","100420"),
		
		/** 授信审批流程:录入业务申请信息 100710 **/
		CA_EntryBusiApplInfo("录入业务信息","100710"),
		/** 授信审批流程:贷款审查 100711 **/
		CA_LoanReview("贷款审查","100711"),
		/** 授信审批流程:初审 100712 **/
		CA_BasisCheck("初审环节","100712"),
		/** 授信审批流程:任务分配 100713 **/
		CA_OneTaskAssign("任务分配","100713"),
		/** 授信审批流程:一级审批 100714 **/
		CA_OneLevelApproval("一级审批","100714"),
		/** 授信审批流程:二级审批 100715 **/
		CA_TwoLevelApproval("二级审批","100715"),
		/** 授信审批流程:签订合同 100716 **/
		CA_SignedContract("制定电子合同","100716"),
		/** 授信审批流程:总经理审核合同 100717 **/
		CA_GeneralManagerCheckLoan("总经理审核合同","100717"),
		/** 授信审批流程:落实贷款条件 100718 **/
		CA_FulfillPayLoanFactor("签订合同","100718"),
		
		/** 授信借款流程:录入业务申请信息 100810 **/
		LA_EntryBusiApplInfo("录入业务信息","100810"),
		/** 授信借款流程:贷款审查 100811 **/
		LA_LoanReview("贷款审查","100811"),
		/** 授信借款流程:初审 100812 **/
		LA_BasisCheck("初审环节","100812"),
		/** 授信借款流程:签订合同 100813 **/
		LA_SignedContract("签订合同","100813"),
		/** 授信借款流程:总经理审核合同 100814 **/
		LA_GeneralManagerCheckLoan("总经理审核合同","100814"),
		/** 授信借款流程:落实贷款条件 100815 **/
		LA_FulfillPayLoanFactor("落实贷款条件","100815"),
		/** 授信借款流程:审核贷款条件 100816 **/
		LA_CheckLoanFactor("审核贷款条件","100816");
		
		private final String name;
		private final String id;
		
		private WorkFlowNode(String name,String id){
			this.name = name;
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		public String getId() {
			return id;
		}
		
		public static WorkFlowNode getNodeById(String nodeId){
			for (WorkFlowNode node : values()) {
				if(node.getId().equals(nodeId)){
					return node;
				}
			}
			throw new IllegalArgumentException("没有id为" + nodeId + "的流程");
		}
	}
	
	
	/**
	 * <b>启动流程</b><BR>
	 * 1. 发起流程的先决条件，必须先调用此接口，创建任务流程才能继续以下操作。<BR>
	 * 2. taskSubject是当前任务的主题（每个任务都可以有自己的主题）<BR>
	 * 3. 返回流程编号和当前环节对应的任务节点的编号<BR>
	 * @param sysCode 所属系统编号
	 * @param workflowCode 流程编号
	 * @param nodeId 启动环节的环节ID
	 * @param userId 用户ID
	 * @param taskSubject 任务主题
	 * @return
	 * @author xc
	 * @see startWorkflow
	 */
	@Deprecated
	TypedResult<WorkFlowTaskIds> startWorkflow(SysCode sysCode,
						WorkFlowCode workflowCode,
						WorkFlowNode node,
						String logName,
						String taskSubject
						);
	
	/**
	 * <b>启动流程</b><BR>
	 * 1. 发起流程的先决条件，必须先调用此接口，创建任务流程才能继续以下操作。<BR>
	 * 2. taskSubject是当前任务的主题（每个任务都可以有自己的主题）<BR>
	 * 3. 返回流程编号和当前环节对应的任务节点的编号<BR>
	 * @param sysCode 所属系统编号
	 * @param workflowCode 流程编号
	 * @param nodeId 启动环节的环节ID
	 * @param userId 用户ID
	 * @param taskSubject 任务主题
	 * @param orgId 当前登录人有权限的orgId
	 * @return
	 * @author AcoreHeng
	 * @since 2015-01-14
	 */
	TypedResult<WorkFlowTaskIds> startWorkflow(SysCode sysCode,
			WorkFlowCode workflowCode,
			WorkFlowNode node,
			String logName,
			String taskSubject,
			String orgId
			);
	
	/**
	 * <b>开始处理任务</b><br>
	 * 1. 根据待办任务状态进行调用<br>
	 * 2. 如果当待办任务状态为未处理时，则必须调用此方法，设置任务状态为处理中<br>
	 * 3. 如果当待办任务状态为处理中时，无需再次调用<br>
	 * @param workflowId 流程ID
	 * @param taskId 任务ID
	 * @param logName 登录账号
	 * @return
	 * @author xc
	 */
	TypedResult<WorkFlowId> startProcessingTask(String workflowId,
								String taskId,
								String logName);
	/**
	 * 获取本环节的操作功能信息
	 * @param nodeId
	 * @return
	 * @author xc
	 */
	TypedResult<List<TaskAction>> getTaskActions(WorkFlowNode node);
	
	/**
	 * 获取当前环节发送给下一环节的人员列表及用户待处理的代办数量
	 * @param workflowId 流程ID 
	 * @param taskId 任务ID 
	 * @param logName 登录账号
	 * @param nodeId 环节ID
	 * @param actionNameEn
	 * @param actionCode
	 * @return
	 * @author xc
	 * @param node 
	 * @param taskId 
	 */
	TypedResult<List<NextTaskReceiver>> getNextTaskReceivers(String taskId, ActionCode actionCode);
	
	
	TypedResult<List<NextTaskReceiver>> getNextTaskReceivers(String taskId, 
																ActionCode actionCode,
																Long workFlowId,
																WorkFlowNode node);
	/**
	 * 按指定角色
	 * 获取当前环节发送给下一环节的人员列表及用户待处理的代办数量
	 * @param taskId
	 * @param actionCode
	 * @param roles
	 * @return
	 * @author xc
	 */
	TypedResult<List<NextTaskReceiver>> getNextTaskReceiversByRoles(String taskId,
																	ActionCode actionCode,
																	String roles);
	/**
	 * 1、同时传入角色编号、机构编号，根据传入的角色机构编号取人：
     * a、角色编号：可传入多个角色编号，中间由逗号（，）隔开。
     * b、机构编号：只能传入一个机构编号
     * c、例如：
     * String roles = "R00001,R00004";
     * String deptId = "61940";
     * client.invoke("getNextTaskReceiversByRoles", "641569","1",roles,deptId);
	 * 2、如果只传入角色编号或机构编号，将根据环节取人方式判断（BY_ROLE、BY_ROLE_IN_DEPTD等），获取人员列表
	 * @param taskId
	 * @param actionCode
	 * @param roles
	 * @param deptId
	 * @return
	 * @author lj
	 */
	TypedResult<List<NextTaskReceiver>> getNextTaskReceiversByRolesAndDeptId(String taskId,
			ActionCode actionCode,
			String roles, String deptId);
	
	/**
	 * <b>执行任务:</b><br>
	 * 1. 根据操作按钮执行当前任务，并将当前任务的状态设置为已完成<br>
	 * 2. 设置并创建下一任务，设置下一任务的状态为待处理，并将下一任务分配给任务接收人<br>
	 * 3. taskSubject是当前用户给下一任务接收人设置的任务主题<br>
	 * 4.分为4种调用情况：<br>
	 * &nbsp;正常发送（本环节发送给下一环节）、退回、撤销、最后一环节发送。<br>
	 * &nbsp;其中除了正常发送情况需要传任务接收人Id，其他情况都可不传；<br>
	 * &nbsp;最后一环节的发送可不传任务主题。<br>
	 * @param workflowCode
	 * @param workflowId
	 * @param logName
	 * @param nodeId
	 * @param taskId
	 * @param actionCode
	 * @param taskReceiver
	 * @param comments
	 * @param taskSubject
	 * @return
	 * @author xc
	 */
	@Deprecated
	TypedResult<WorkFlowTaskIds> executeTask(WorkFlowCode workflowCode,
						String workflowId,
						String taskId,
						String logName,
						WorkFlowNode node,
						ActionCode actionCode,
						String taskReceiver,
						String comments,
						String taskSubject);
	/**
	 * <b>执行任务:</b><br>
	 * 1. 根据操作按钮执行当前任务，并将当前任务的状态设置为已完成<br>
	 * 2. 设置并创建下一任务，设置下一任务的状态为待处理，并将下一任务分配给任务接收人<br>
	 * 3. taskSubject是当前用户给下一任务接收人设置的任务主题<br>
	 * 4.分为4种调用情况：<br>
	 * &nbsp;正常发送（本环节发送给下一环节）、退回、撤销、最后一环节发送。<br>
	 * &nbsp;其中除了正常发送情况需要传任务接收人Id，其他情况都可不传；<br>
	 * &nbsp;最后一环节的发送可不传任务主题。<br>
	 * @since 2015-01-14
	 * @param param	参数
	 * @return
	 */
	TypedResult<WorkFlowTaskIds> executeTask(ExeTaskParam param);
	/**
	 * <b>转交任务:</b><br>
	 * @param workflowId
	 * @param taskId
	 * @param transferor
	 * @param transferee
	 * @param reason
	 * @return
	 * @author xc
	 */
	TypedResult<WorkFlowId> transferTask(String workflowId,
						String taskId,
						String transferor,
						String transferee,
						String reason);
	/**
	 * 获取流程中任务的流转过程
	 * @param workflowId
	 * @param sortFlag
	 * @return
	 * @author xc
	 */
	TypedResult<List<TaskTransferProcess>> getTaskTransferProcessOfWorkflow(String workflowId,String sortFlag);
	
	/**
	 * 获取用户经办的所有流程列表
	 * @param deptCode
	 * @param logName
	 * @param sysCode
	 * @param sortFlag
	 * @param workflowCodes
	 * @param taskStatus
	 * @param taskSubject
	 * @param taskCreateDateStart
	 * @param taskCreateDateEnd
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	TypedResult<PageTypedResultData<HandledWorkflows>> getHandledWorkflowsByUser(
														String deptCode,
														String logName,
														SysCode sysCode,
														String sortFlag,
														List<WorkFlowCode> workflowCodes,
														String taskStatus,
														String taskSubject,
														String taskCreateDateStart,
														String taskCreateDateEnd,
														String taskEndDateStart,
														String taskEndDateEnd, 
														Integer pageNumber, 
														Integer pageSize);
	
	/**
	 * 获取待办列表
	 * @param logName
	 * @param workflowCodes
	 * @param sysCode
	 * @param taskCreatorName
	 * @param taskSubject
	 * @param taskCreateDateStart
	 * @param taskCreateDateEnd
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	TypedResult<PageTypedResultData<TodoTask>> queryToDoListByCondition (
														String logName, 
														List<WorkFlowCode> workflowCodes,
														SysCode sysCode, 
														String taskCreatorName,
														String taskSubject,
														String taskCreateDateStart,
														String taskCreateDateEnd, 
														Integer pageNumber, 
														Integer pageSize);
	
	
	/***
	 * 获取已办列表
	 * @param logName
	 * @param workflowCodes
	 * @param sysCode
	 * @param taskCreatorName
	 * @param taskSubject
	 * @param taskConfirmDateStart
	 * @param taskConfirmDateEnd
	 * @param taskAssignDateStart
	 * @param taskAssignDateEnd
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	TypedResult<PageTypedResultData<DoneTask>> queryDoneListByCondition(
														String logName, 
														List<WorkFlowCode> workflowCodes,
														SysCode sysCode, 
														String taskCreatorName,
														String taskSubject,
														String taskConfirmDateStart,
														String taskConfirmDateEnd, 
														String taskAssignDateStart,
														String taskAssignDateEnd, 
														Integer pageNumber, 
														Integer pageSize);

	/**
	 * 暂停、继续任务
	 * @author:gph
	 * @createTime:2015年6月11日
	 * @param workflowCode 流程类型
	 * @param workflowId 流程id
	 * @param taskId 任务id
	 * @param taskStatus 任务状态
	 * @param logname 登录名
	 * @param remark 备注
	 * @return
	 */
	TypedResult pauseOrPlayTask(
			String workflowCode,
			String workflowId, 
			String taskId, 
			String taskStatus,
			String logname,
			String remark);

}
