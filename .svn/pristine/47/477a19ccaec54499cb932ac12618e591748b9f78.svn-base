package com.coamctech.bxloan.web.tests.webservice;

import java.util.List;

import org.hibernate.jdbc.Work;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.service.model.workflow.DoneTask;
import com.coamctech.bxloan.service.model.workflow.HandledWorkflows;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.PageTypedResultData;
import com.coamctech.bxloan.service.model.workflow.TaskAction;
import com.coamctech.bxloan.service.model.workflow.TodoTask;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowId;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.SysCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;

public class WorkFlowServiceTests extends BaseSpringJUnit4Tester {

	@Autowired
	private WorkFlowService wfService;
	
	@Test
	public void start(){
		try {
			TypedResult<List<TaskAction>>  result = this.wfService.getTaskActions(WorkFlowNode.EL_EntryBusiApplInfo);
			System.out.println(result.getData());
			System.out.println(JSON.toJSON(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getNextTaskAct(){
		try {
			TypedResult<List<TaskAction>>  result = this.wfService.getTaskActions(WorkFlowNode.EL_EntryBusiApplInfo);
			System.out.println(result.getData());
			System.out.println(JSON.toJSON(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void gettodolist(){
		String logName = "0101002";
		List<WorkFlowCode> workflowCodes = null;
		SysCode sysCode = SysCode.WD_SYS;
		String taskCreatorName  = "";
		String taskSubject  = "";
		String taskCreateDateStart  = "";
		String taskCreateDateEnd  = ""; 
		Integer pageNumber =1; 
		Integer pageSize = 10;
		TypedResult<PageTypedResultData<TodoTask>> result
			= this.wfService.queryToDoListByCondition(logName, 
					workflowCodes, 
					sysCode, 
					taskCreatorName, 
					taskSubject,
					taskCreateDateStart,
					taskCreateDateEnd, 
					pageNumber, 
					pageSize);
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void getDoneList(){
		String logName = "0101002";
		List<WorkFlowCode> workflowCodes = null;
		SysCode sysCode = SysCode.WD_SYS;
		String taskCreatorName  = "";
		String taskSubject  = "";
		Integer pageNumber =1; 
		Integer pageSize = 10;
		String taskConfirmDateStart = "";
		String taskConfirmDateEnd= "";
		String taskAssignDateStart= "";
		String taskAssignDateEnd= "";
		
		TypedResult<PageTypedResultData<DoneTask>> result
		= this.wfService.queryDoneListByCondition(
				logName,
				workflowCodes, 
				sysCode, 
				taskCreatorName,
				taskSubject, 
				taskConfirmDateStart,
				taskConfirmDateEnd, 
				taskAssignDateStart, 
				taskAssignDateEnd, 
				pageNumber, 
				pageSize);
	System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void monitor(){
		
		String deptCode = "";
		String logName = "0101002";
		SysCode sysCode = SysCode.WD_SYS;
		String sortFlag = "DESC";
		List<WorkFlowCode> workflowCodes =null;
		String taskStatus = "";
		String taskCreateDateStart = "";
		String taskCreateDateEnd = "";
		String taskendDateStart = "";
		String taskendDateEnd = "";
		String taskSubject = "";
		Integer pageNumber = 1;
		Integer pageSize = 10;
		TypedResult<PageTypedResultData<HandledWorkflows>> result = 
		this.wfService.getHandledWorkflowsByUser(
				deptCode, 
				logName, 
				sysCode, 
				sortFlag, 
				workflowCodes, 
				taskStatus, 
				taskSubject,
				taskCreateDateStart, 
				taskCreateDateEnd, 
				taskendDateStart,
				taskendDateEnd,
				pageNumber, 
				pageSize);
		
		
		System.out.println(JSON.toJSON(result));
		
		
	}
	
	@Test
	public void transfer(){
		TypedResult<WorkFlowId> result = 
				this.wfService.transferTask("2299447", "641130", "shfk03"  , "spg01", "no");
		System.out.println(result);
	}
	
	@Test
	public void getNextUser(){
		try {
			String workFlowId = "2300141";
			String taskId = "642255";
			
			TypedResult<List<NextTaskReceiver>> resultList = 
					this.wfService.getNextTaskReceivers(taskId,ActionCode.COMMIT,
							Long.parseLong(workFlowId),
							WorkFlowService.WorkFlowNode.EL_PhoneNetworkCheck);
			System.out.println(JSON.toJSON(resultList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getNextUser1(){
		try {
			String taskId = "643811";
			
			TypedResult<List<NextTaskReceiver>> resultList = 
					this.wfService.getNextTaskReceivers(taskId,ActionCode.COMMIT);
			System.out.println(JSON.toJSON(resultList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void sendBack(){
		String curuser = "wdejsp1";
		String workflowId = "2300190";
		String taskId = "642428";
		WorkFlowNode node = WorkFlowNode.ML_SignedContract;
		this.wfService.executeTask(WorkFlowCode.MICRO_LOAN, workflowId,
				taskId,curuser,node, ActionCode.SEND_BACK,
				"", "", "");
	}
}
