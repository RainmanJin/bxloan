package com.coamctech.bxloan.web.tests.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.service.model.workflowmonitor.WorkflowMonitorDIsplayVO;
import com.coamctech.bxloan.service.model.workflowmonitor.WorkflowMonitorDetailVO;
import com.coamctech.bxloan.service.workflowmonitor.WorkflowMonitorService;

public class WorkFlowMonitorTests extends BaseSpringJUnit4Tester {

	@Autowired
	private WorkflowMonitorService service ;
	
//	@Test
//	public void list(){
//		String customerName = "";
//		String processType ="30";
//		String processStatus = "";
//		String submitTimeStart = "";
//		String submitTimeEnd = ""; 
//		//String managerUserNum = "66752";
//		String managerUserNum = "";
//		Long managerOrgId = 61940L;
//		int startIndex = 0;
//		Integer pageSize = 1000;
//		
//		Page<Object[]> result = 
//		this.service.search(customerName, 
//				processType, processStatus, 
//				submitTimeStart, submitTimeEnd, 
//				managerUserNum,managerOrgId,
//				startIndex, pageSize);
//		
//		System.out.println(CollectionUtils.size(result.getContent()));
//		System.out.println(JSON.toJSON(result.getContent()));
//	}
	
//	@Test
//	public void detail(){
//		String workflowId = "2298660";
//		List<WorkflowMonitorDetailVO> result = this.service.findDetaiByWorkId(workflowId);
//		System.out.println(JSON.toJSON(result));
//	}
	
}
