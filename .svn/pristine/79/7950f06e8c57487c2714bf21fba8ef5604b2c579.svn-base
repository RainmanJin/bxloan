package com.coamctech.bxloan.web.tests.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.model.ApprovalContentVO;
import com.coamctech.bxloan.service.repayplan.RepayPlanService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;


public class ApprovalServiceTests extends BaseSpringJUnit4Tester {

	@Autowired
	private ApprovalService service;
	@Autowired
	private ContractDao contraDao;
	
	@Autowired
	ProjectApplicationDao proAppDao;
	
	@Autowired
	RepayPlanService repayPlanService ;
	
	@Test
	public void findApproValContentByProjectId(){
		Long proId = 9715L;
		ApprovalContentVO vo = this.service.findApproValContentByProjectId(proId);
		System.out.println(JSON.toJSON(vo));
	}
	
	@Test
	public void actCode(){
		try {
			Long workflowId = Long.parseLong("2300175");
			String taskStageCode = "100414";
			ActionCode code = this.service.findNextAction(workflowId, taskStageCode);
			System.out.println(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testName() throws Exception {
		repayPlanService.findRepayingPlanForExcelByContractId(705L);
	}
	
	
}
