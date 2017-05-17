package com.coamctech.bxloan.web.controller.dashboard;

import static com.coamctech.bxloan.commons.GlobalConstants.BUSINESS_APPLICATION;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;


/**
 * 统一待办、已办与门户集成
 * @author AcoreHeng
 *
 */
@Controller
@RequestMapping("/unified")
public class UnifiedController extends BaseController {
	private final String REDIRECT="redirect:/";
	
	@Autowired
	private BusinessApplicationService businessApplicationService;
	
	@RequestMapping("/todoDeal")
	public String toTodoDeal(Model model, HttpServletRequest request) {
		String workflowId = request.getParameter("workflowid");
		String workflowCode = request.getParameter("workflowcode");
		String wfTaskId = request.getParameter("wftaskid");
		String nodeId = request.getParameter("nodeid");
		String taskStatus = request.getParameter("taskstatus");
		String str="";
		switch (WorkFlowCode.getById(workflowCode)) {
		case EASY_LOAN:
			str="approval/todoOperation";
			break;
		case MICRO_LOAN:
			str="wdapproval/todoOperation";
			break;
		default:
			break;
		}
		return StringUtils.join(REDIRECT,str,"/",workflowId,"/",wfTaskId,"/",nodeId,"/",taskStatus,"?source=portal");
	}
	
	@RequestMapping("/doneDeal")
	public String toDoneDeal(Model model, HttpServletRequest request) {
		String workflowId = request.getParameter("workflowid");
		/*String workflowCode = request.getParameter("workflowcode");
		String nodeId = request.getParameter("nodeId");*/
		//return StringUtils.join(REDIRECT,"bizapply/checkApplication/",workflowId,"/taskDone","?source=portal");
		Long projectId = businessApplicationService.findProjectApplicationByWorkflowId(
				Long.parseLong(workflowId)).getProjectId();
		return  StringUtils.join(REDIRECT,"bizapply/forwardToCheckModel?projectId=",projectId.toString(),"&consultLocation=type");
	}
	
}
