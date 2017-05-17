package com.coamctech.bxloan.web.controller.dashboard;

import static com.coamctech.bxloan.commons.GlobalConstants.DASHBOARD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.service.dashboard.DashboardService;
import com.coamctech.bxloan.service.model.dashboard.MessageDisplayVo;
import com.coamctech.bxloan.service.model.workflow.DoneTask;
import com.coamctech.bxloan.service.model.workflow.PageTypedResultData;
import com.coamctech.bxloan.service.model.workflow.TodoTask;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.SysCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/" + DASHBOARD)
public class DashboardController extends BaseController {
	
	private Log log=LogFactory.getLog(DashboardController.class);

	@Autowired
	private DashboardService dashboardService;
	@Autowired
	private WorkFlowService workFlowService;

	@RequestMapping
	public String index(HttpServletRequest req) {
		return DASHBOARD + "/main";
	}
	
	@RequestMapping("/helpinfo")
	public String helpinfo(HttpServletRequest req) {
		return DASHBOARD + "/helpinfo";
	}
	
	@RequestMapping("/personInfo")
	public String personInfo(Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("curUserId", curUser.getId());
		//model.addAttribute("orgIdValue", curUser.getOrgid());
		model.addAttribute("orgIdValue", curUser.getLogOrgid());//TODO
		return DASHBOARD + "/personInfo";
	}
	
	@ResponseBody
	@RequestMapping("/getMenus")
	public Result getMenus() {
		Result result = new Result();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		result.setSuccess(true);
		result.setData(curUser.getList());
		return result;
	}

	/**
	 * 查询代办列表
	 * */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findTodoListBySearch")
	public DataTablesPage findByTodoListSearch(
			@RequestParam("taskDesignator") String taskDesignator,
			@RequestParam("taskCreateTimeStart") String taskCreateTimeStart,
			@RequestParam("taskCreateTimeEnd") String taskCreateTimeEnd,
			@RequestParam("workFlowCode") String workFlowCode,
			@RequestParam("taskSubject") String taskSubject,
			@RequestParam(value = "sSearch", required = false) String search,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getLogname();
		}
		List<WorkFlowCode> wfTypes = new ArrayList<WorkFlowService.WorkFlowCode>();
		if(StringUtils.isBlank(workFlowCode)){
			wfTypes.add(WorkFlowCode.EASY_LOAN);
			wfTypes.add(WorkFlowCode.MICRO_LOAN);
			wfTypes.add(WorkFlowCode.CREDIT_APPROVAL);
			wfTypes.add(WorkFlowCode.CREDIT_LOAN);
		}else{
			wfTypes.add(WorkFlowCode.getById(workFlowCode));
		}
		
		DataTablesPage dt =null;
		try {
			TypedResult<PageTypedResultData<TodoTask>> result = workFlowService.queryToDoListByCondition(curUser, wfTypes,
							SysCode.WD_SYS, taskDesignator, taskSubject,
							taskCreateTimeStart, taskCreateTimeEnd, firstIndex
									/ pageSize + 1, pageSize);
			if(result.getSuccess()){
				dt=new DataTablesPage(sEcho, result.getData());
			}else{
				dt=new DataTablesPage(sEcho, Collections.EMPTY_LIST, 0L);
				log.error("findTodoList error!>>>>>"+result.getMsg());
			}
		} catch (Exception e) {
			log.error("findTodoList error!",e);
			dt=new DataTablesPage(sEcho,  Collections.EMPTY_LIST, 0L);
		}
		return dt;
	}

	/**
	 * 查询已办列表
	 * */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findDoneListBySearch")
	public DataTablesPage findDoneListBySearch(
			@RequestParam("taskDesignator") String taskDesignator,
			@RequestParam("taskCreateTimeStart") String taskCreateTimeStart,
			@RequestParam("taskCreateTimeEnd") String taskCreateTimeEnd,
			@RequestParam("taskSubmitTimeStart") String taskSubmitTimeStart,
			@RequestParam("taskSubmitTimeEnd") String taskSubmitTimeEnd,
			@RequestParam("workFlowCode") String workFlowCode,
			@RequestParam("taskSubject") String taskSubject,
			@RequestParam(value = "sSearch", required = false) String search,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getLogname();
		}
		List<WorkFlowCode> wfTypes = new ArrayList<WorkFlowService.WorkFlowCode>();
		if(StringUtils.isBlank(workFlowCode)){
			wfTypes.add(WorkFlowCode.EASY_LOAN);
			wfTypes.add(WorkFlowCode.MICRO_LOAN);
			wfTypes.add(WorkFlowCode.CREDIT_APPROVAL);
			wfTypes.add(WorkFlowCode.CREDIT_LOAN);
		}else{
			wfTypes.add(WorkFlowCode.getById(workFlowCode));
		}
		
		DataTablesPage dt= null;;
		try {
			TypedResult<PageTypedResultData<DoneTask>> result = 
					workFlowService.queryDoneListByCondition(curUser, wfTypes,
							SysCode.WD_SYS, taskDesignator, taskSubject, 
							taskCreateTimeStart, taskCreateTimeEnd, 
							taskSubmitTimeStart, taskSubmitTimeEnd, 
							(firstIndex/pageSize+1), pageSize);
			if(result.getSuccess()){
				dt=new DataTablesPage(sEcho, result.getData());
			}else{
				dt=new DataTablesPage(sEcho,  Collections.EMPTY_LIST, 0L);
				log.error("findTodoList error!>>>>>"+result.getMsg());
			}
		} catch (Exception e) {
			logger.error("查询已办列表出错",e);
			dt=new DataTablesPage(sEcho,  Collections.EMPTY_LIST, 0L);
		}
		return dt;
	}
	
	
	/**
	 * 查询消息列表
	 * @param search
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/findMessageList")
	public DataTablesPage findMessageList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

		Page<Object[]> messagePage = dashboardService.findAllMessageList(
				curUser.getId().toString(), curUser.getOrgid().toString(),//TODO
			 firstIndex / pageSize + 1, pageSize);
		
		List<Object[]> finalList = messagePage.getContent();
		
		DataTablesPage result = new DataTablesPage(sEcho,messagePage);
		result.setAaData(finalList);
		return result;
	}
	
	// 查询 单个消息列表
	@RequestMapping("/findOneMessageList")
	@ResponseBody
	public DataTablesPage findOneMessageList(@RequestParam("id") String id,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

		Page<Object[]> messagePage = dashboardService
		.findOneTypeMessageList(id, curUser.getId().toString(), 
				firstIndex / pageSize + 1,
				pageSize);
		List<Object[]> finalList = messagePage.getContent();
		
		List<MessageDisplayVo> voList = Lists.transform(finalList,
				new Function<Object[], MessageDisplayVo>() {
			@Override
			public MessageDisplayVo apply(Object[] input) {
				return new MessageDisplayVo(input);
			}
		});
		
		DataTablesPage result = new DataTablesPage(sEcho,messagePage);
		result.setAaData(voList);
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/doReadMsg")
	public Result doReadMsg(@RequestParam("msgId")String msgId){
		try {
			this.dashboardService.doAlreadyReadMsg(Long.parseLong(msgId));
		} catch (Exception e) {
			logger.error("设置消息已读时出错",e);
			return failure("设置消息已读时出错");
		}
		return success();
	}
	
	
}
