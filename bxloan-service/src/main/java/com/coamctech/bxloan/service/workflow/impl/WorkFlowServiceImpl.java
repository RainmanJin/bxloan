package com.coamctech.bxloan.service.workflow.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.webservices.WebServices;
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
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class WorkFlowServiceImpl implements WorkFlowService{
	
	private static final Logger logger = LoggerFactory.getLogger(WorkFlowServiceImpl.class);
	
	private static final String WORK_FLOW_SERVICE_NAME = "wfiService";
	
	private static final String TASK_SERVICE_NAME = "taskService";
	
	private static final String SERVICE_START_WORKFLOW = "startWorkflow";
	
	private static final String SERVICE_START_PROCESSING_TASK = "startProcessingTask";
	
	private static final String SERVICE_GET_TASK_ACTIONS = "getTaskActions";
	
	private static final String SERVICE_GET_NEXT_TASK_RECEIVERS = "getNextTaskReceivers";
	
	private static final String SERVICE_GET_NEXT_TASK_RECEIVERS_BY_ROLES = "getNextTaskReceiversByRoles";
	
	private static final String SERVICE_GET_NEXT_TASK_RECEIVERS_BY_ROLES_AND_DEPTID = "getNextTaskReceiversByRolesAndDeptId";
	
	private static final String SERVICE_EXECUTE_TASK = "executeTask";
	
	private static final String SERVICE_TRANSFER_TASK = "transferTask";
	
	private static final String SERVICE_GET_TASK_TRANSFER_PROCESS_OF_WORKFLOW = "getTaskTransferProcessOfWorkflow";
	
	private static final String SERVICE_GET_HANDLED_WORK_FLOWS_BY_USER = "getHandledWorkflowsByUser";
	
	private static final String SERVICE_QUERY_TODO_LIST_BY_CONDITION="queryToDoListByCondition";
	
	private static final String SERVICE_QUERY_DONE_LIST_BY_CONDITION="queryDoneListByCondition";
	
	private static final String PAUSE_OR_PLAY_TASK = "pauseOrPlayTask";
	
	private static final String ERR_MSG = "调用webservice时发生异常";
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final TypedResult<?> nullResp = new TypedResult(Boolean.FALSE,"调用webservice返回结果为空", null);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final TypedResult<?> errResp = new TypedResult(Boolean.FALSE,ERR_MSG, null);
	
	@Autowired
	private WebServices webServices;
	
	@Autowired
	private DynamicQuery query;

	@Override
	@Deprecated
	public TypedResult<WorkFlowTaskIds> startWorkflow(SysCode sysCode, WorkFlowCode workflowCode, WorkFlowNode node,
			String userId, String taskSubject) {
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
										SERVICE_START_WORKFLOW, 
										new TypeReference<WorkFlowTaskIds>(){},
										sysCode==null?null:sysCode.getCodeId(),
										workflowCode==null?null:workflowCode.getCodeId(),
										node.getId(),
										userId,
										taskSubject);
	}
	@Override
	public TypedResult<WorkFlowTaskIds> startWorkflow(SysCode sysCode,
			WorkFlowCode workflowCode, WorkFlowNode node, String logName,
			String taskSubject, String orgId) {
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
				SERVICE_START_WORKFLOW, 
				new TypeReference<WorkFlowTaskIds>(){},
				sysCode==null?null:sysCode.getCodeId(),
				workflowCode==null?null:workflowCode.getCodeId(),
				node.getId(),
				logName,
				taskSubject,
				orgId);
	}

	@Override
	public TypedResult<WorkFlowId> startProcessingTask(String workflowId, String taskId,String logName) {
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
										SERVICE_START_PROCESSING_TASK, 
										new TypeReference<WorkFlowId>(){},
										workflowId,
										taskId,
										logName);
	}

	@Override
	public TypedResult<List<TaskAction>> getTaskActions(WorkFlowNode node) {
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
				SERVICE_GET_TASK_ACTIONS, 
				new TypeReference<List<TaskAction>>(){},
				node.getId());
	}
	
	@Override
	public TypedResult<List<NextTaskReceiver>> getNextTaskReceivers(
													String taskId,
													ActionCode actionCode) {
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
										SERVICE_GET_NEXT_TASK_RECEIVERS, 
										new TypeReference<List<NextTaskReceiver>>(){},
										taskId,
										actionCode.getCodeId());
	}
	
	@Override
	public TypedResult<List<NextTaskReceiver>> getNextTaskReceiversByRoles(String taskId,
																	ActionCode actionCode,
																	String roles){
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
				SERVICE_GET_NEXT_TASK_RECEIVERS_BY_ROLES, 
				new TypeReference<List<NextTaskReceiver>>(){},
				taskId,
				actionCode.getCodeId(),
				roles);
	}
	
	@Override
	public TypedResult<List<NextTaskReceiver>> getNextTaskReceiversByRolesAndDeptId(String taskId,
																	ActionCode actionCode,
																	String roles, String deptId){
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
				SERVICE_GET_NEXT_TASK_RECEIVERS_BY_ROLES_AND_DEPTID, 
				new TypeReference<List<NextTaskReceiver>>(){},
				taskId,
				actionCode.getCodeId(),
				roles,
				deptId);
	}
	
	@Override
	public TypedResult<List<NextTaskReceiver>> getNextTaskReceivers(
																String taskId,
																ActionCode actCode, 
																Long workFlowId,
																WorkFlowNode node){
		String roles = this.findNextTaskRoles(workFlowId,node);
		String deptId = this.findNextTaskDeptId(workFlowId,node);
		
		if(StringUtils.isNotBlank(roles)&&StringUtils.isNotBlank(deptId)){
			return getNextTaskReceiversByRolesAndDeptId(taskId, actCode, roles, deptId);
			//return getNextTaskReceiversByRoles(taskId,actCode, roles);
		}else if(StringUtils.isNotBlank(roles)){
			return getNextTaskReceiversByRoles(taskId, actCode, roles);
		}else{
			return getNextTaskReceivers(taskId, actCode);
		}
	}
	
	private String findNextTaskDeptId(Long workFlowId, WorkFlowNode node) {
		String taskStage = node.getId();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ORG_ID ");
		sql.append("FROM PRODUCT_WF_CONFIG ");
		sql.append("WHERE PC_ID = ");
		sql.append("  (SELECT PC_ID ");
		sql.append("  FROM PRODUCT_CONFIG ");
		sql.append("  WHERE TO_CHAR(ORG_ID) = ");
		sql.append("    (SELECT PROJECT_APPLICATION.APPLY_ORG ");
		sql.append("    FROM PROJECT_APPLICATION ");
		sql.append("    WHERE PROJECT_APPLICATION.WORKFLOW_ID=?1 ");
		sql.append("    ) ");
		sql.append("     ");
		sql.append("  AND TO_CHAR(PRODUCT_CD)= ");
		sql.append("    (SELECT PRODUCT_TYPE ");
		sql.append("    FROM PROJECT_APPLICATION ");
		sql.append("    WHERE PROJECT_APPLICATION.WORKFLOW_ID=?1 ");
		sql.append("    ) ");
		sql.append("  ) ");
		sql.append("AND TASK_STAGE_CODE = ?2 ");

		List<Object> result = this.query.nativeQuery(Object.class, sql.toString(),
				workFlowId,taskStage);
		Boolean flag = CollectionUtils.isEmpty(result);
				//flag = result.get(0)==null;
		return result==null||result.isEmpty()||flag?"":result.get(0).toString();
	}

	private String findNextTaskRoles(Long workFlowId ,WorkFlowNode node) {
		String taskStage = node.getId();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ROLECONFIG ");
		sql.append("FROM PRODUCT_WF_CONFIG ");
		sql.append("WHERE PC_ID = ");
		sql.append("  (SELECT PC_ID ");
		sql.append("  FROM PRODUCT_CONFIG ");
		sql.append("  WHERE TO_CHAR(ORG_ID) = ");
		sql.append("    (SELECT PROJECT_APPLICATION.APPLY_ORG ");
		sql.append("    FROM PROJECT_APPLICATION ");
		sql.append("    WHERE PROJECT_APPLICATION.WORKFLOW_ID=?1 ");
		sql.append("    ) ");
		sql.append("     ");
		sql.append("  AND TO_CHAR(PRODUCT_CD)= ");
		sql.append("    (SELECT PRODUCT_TYPE ");
		sql.append("    FROM PROJECT_APPLICATION ");
		sql.append("    WHERE PROJECT_APPLICATION.WORKFLOW_ID=?1 ");
		sql.append("    ) ");
		sql.append("  ) ");
		sql.append("AND TASK_STAGE_CODE = ?2 ");

		List<Object> result = this.query.nativeQuery(Object.class, sql.toString(),
				workFlowId,taskStage);
		return result==null||result.isEmpty()?"":result.get(0).toString();
	}

	@Override
	@Deprecated
	public TypedResult<WorkFlowTaskIds> executeTask(WorkFlowCode workflowCode,
												String workflowId,
												String taskId,
												String logName,
												WorkFlowNode node,
												ActionCode actionCode,
												String taskReceiver,
												String comments,
												String taskSubject) {
		
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
										SERVICE_EXECUTE_TASK, 
										new TypeReference<WorkFlowTaskIds>(){},
										workflowCode==null?null:workflowCode.getCodeId(),
										workflowId,
										taskId,
										logName,
										node.getId(),
										actionCode.getCodeId(),
										taskReceiver,
										comments,
										taskSubject);
	}
	@Override
	public TypedResult<WorkFlowTaskIds> executeTask(ExeTaskParam param) {
		WorkFlowCode workflowCode=param.getWorkflowCode();
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
				SERVICE_EXECUTE_TASK, 
				new TypeReference<WorkFlowTaskIds>(){},
				workflowCode==null?null:workflowCode.getCodeId(),
				param.getWorkflowId(),
				param.getTaskId(),
				param.getLogName(),
				param.getNode().getId(),
				param.getActionCode().getCodeId(),
				param.getTaskReceiver(),
				param.getComments(),
				param.getTaskSubject(),
				param.getOrgId());
	}
	@Override
	public TypedResult<WorkFlowId> transferTask(String workflowId, String taskId,
			String transferor, String transferee, String reason) {
		
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,
										SERVICE_TRANSFER_TASK, 
										new TypeReference<WorkFlowId>(){},
										workflowId,
										taskId,
										transferor,
										transferee,
										reason);
	}

	@Override
	public TypedResult<List<TaskTransferProcess>> getTaskTransferProcessOfWorkflow(String workflowId,
			String sortFlag) {
		return this.doInvokeWebService(WORK_FLOW_SERVICE_NAME,SERVICE_GET_TASK_TRANSFER_PROCESS_OF_WORKFLOW, 
				new TypeReference<List<TaskTransferProcess>>(){},
				workflowId,
				sortFlag);
	}

	@Override
	public TypedResult<PageTypedResultData<TodoTask>> queryToDoListByCondition(String logName, 
															List<WorkFlowCode> workflowCodes,
															SysCode sysCode, 
															String taskCreatorName,
															String taskSubject,
															String taskCreateDateStart,
															String taskCreateDateEnd, 
															Integer pageNumber, 
															Integer pageSize) {
		List<String> wfCodeList = null;
		if(workflowCodes!=null&&!workflowCodes.isEmpty()){
			wfCodeList = Lists.transform(workflowCodes,new Function<WorkFlowCode, String>(){
						@Override
						public String apply(WorkFlowCode input) {
							return input.getCodeId();
						}
					});
		}
		
		return this.doInvokeWebService(TASK_SERVICE_NAME,
				SERVICE_QUERY_TODO_LIST_BY_CONDITION,
				new TypeReference<PageTypedResultData<TodoTask>>(){},
				logName, 
				wfCodeList,
				sysCode==null?null:sysCode.getCodeId(),
				null,
				taskCreatorName,
				taskSubject,
				taskCreateDateStart,
				taskCreateDateEnd, 
				pageNumber, 
				pageSize);
	}
	
	
	@Override
	public TypedResult<PageTypedResultData<HandledWorkflows>> getHandledWorkflowsByUser(
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
															Integer pageSize){
		
		List<String> wfCodeList = Lists.newArrayList();
		if(workflowCodes!=null&&!workflowCodes.isEmpty()){
			wfCodeList = Lists.transform(workflowCodes,new Function<WorkFlowCode, String>(){
						@Override
						public String apply(WorkFlowCode input) {
							return input.getCodeId();
						}
					});
		}else{
			wfCodeList = Collections.emptyList();
		}	
		return this.doInvokeWebService(
				WORK_FLOW_SERVICE_NAME,
				SERVICE_GET_HANDLED_WORK_FLOWS_BY_USER, 
				new TypeReference<PageTypedResultData<HandledWorkflows>>(){},
				deptCode,
				logName,
				sysCode==null?null:sysCode.getCodeId(),
				sortFlag,
				wfCodeList,
				taskStatus,
				taskSubject,
				taskCreateDateStart,
				taskCreateDateEnd,
				taskEndDateStart,
				taskEndDateEnd, 
				pageNumber,
				pageSize);
	}
	
	@Override
	public TypedResult<PageTypedResultData<DoneTask>> queryDoneListByCondition(
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
														Integer pageSize) {
		
		List<String> wfCodeList = null;
		if(workflowCodes!=null&&!workflowCodes.isEmpty()){
			wfCodeList = Lists.transform(workflowCodes,new Function<WorkFlowCode, String>(){
						@Override
						public String apply(WorkFlowCode input) {
							return input.getCodeId();
						}
					});
		}		
		
		return this.doInvokeWebService(
				TASK_SERVICE_NAME,
				SERVICE_QUERY_DONE_LIST_BY_CONDITION, 
				new TypeReference<PageTypedResultData<DoneTask>>(){},
				logName, 
				wfCodeList,
				sysCode==null?null:sysCode.getCodeId(), 
				taskCreatorName,
				taskSubject,
				taskConfirmDateStart,
				taskConfirmDateEnd, 
				taskAssignDateStart,
				taskAssignDateEnd, 
				pageNumber, 
				pageSize);
		
	}
	

	
	@SuppressWarnings({ "unchecked"})
	private <T> TypedResult<T> parseResult(String invokeResult,TypeReference<T> dataTypeToken){
		if(invokeResult==null){
			return (TypedResult<T>) nullResp;
		}
		TypedResult<T> result = JSON.parseObject(invokeResult,
				new TypeReference<TypedResult<T>>(){});
		
		if(result.getData() instanceof JSON){
			JSON data = (JSON)result.getData();
			result.setData(JSON.parseObject(data.toJSONString(),
					dataTypeToken));
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	private <T> TypedResult<T> doInvokeWebService(String serviceName,String methodName ,TypeReference<T> dataType,Object... params){
		String invokeResult = null;
		try {
			invokeResult = this.invokeService(serviceName,methodName,params);
		} catch (Exception e) {
			logger.error(ERR_MSG,e);
			return (TypedResult<T>) errResp;
		}
		return parseResult(invokeResult,dataType) ;
	}
	
	private String invokeService(String serviceName,String methodName,Object...params) throws Exception{
//		webServices.initClients();//FIXME
		return this.webServices.invoke(serviceName, methodName, params);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public TypedResult pauseOrPlayTask(
			String workflowCode,
			String workflowId,
			String taskId, 
			String taskStatus,
			String logname,
			String remark) {
		
		try {
			String invokeResult = this.invokeService(
					WORK_FLOW_SERVICE_NAME,
					PAUSE_OR_PLAY_TASK, 
					workflowCode,
					workflowId, 
					taskId, 
					taskStatus,
					logname,
					remark);
			
			return JSON.parseObject(invokeResult, TypedResult.class);
			
		} catch (Exception e) {
			logger.error(ERR_MSG,e);
			return errResp;
		}
		
	}

	
}
