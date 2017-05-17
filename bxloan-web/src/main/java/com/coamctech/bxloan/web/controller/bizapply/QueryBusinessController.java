package com.coamctech.bxloan.web.controller.bizapply;

import static com.coamctech.bxloan.commons.GlobalConstants.QUERYBUSINESS;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
import com.coamctech.bxloan.service.common.SysCommonService;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.model.workflow.TaskTransferProcess;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.web.security.ShiroUser;


@Controller
@RequestMapping("/" + QUERYBUSINESS)
public class QueryBusinessController extends BaseController {

	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private ApprovalService approvalService;
	@Autowired
	private OrgDeptmentService orgDeptmentService;
	@Autowired
	private EcOrgPersonService ecOrgPersonService;
	@Autowired
	private SysCommonService sysCommonService;
	@RequestMapping
	public String index(Model model) {
		List<Object[]> products = productPriceService.findProduct();
		model.addAttribute("products", products);
//		Collection<CodeData> status = dataDict.getOneType("ProjectStatus");
//		model.addAttribute("status", status);
		
		
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("sys_adm_level", sysCommonService.getAdminLevels(curUser.getId()));
		int orgLevel = 0;
		if(curUser.getDataAuthType() != null){
			switch (curUser.getDataAuthType()) {
			case HeadOffice:// 总部
				orgLevel = 1;
				break;
			default:// 小贷公司或客户经理
				orgLevel = 2;
				break;
			}
		model.addAttribute("orgLevel", orgLevel);
		model.addAttribute("orgs", ecOrgPersonService
				.findEcOrgsInfoByIds(curUser.getDataAuthOrgIds()));
		}else{
			//TODO
			model.addAttribute("orgName", curUser.getLogOrgname());
		}
		return QUERYBUSINESS + "/main";
	}

	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		Long personId = curUser.getId();
		Long logOrgId = curUser.getLogOrgid();
		//客户类型
		DataAuthorityType dataAuthType = curUser.getDataAuthType();
		
		List<Long> dataAuthOrgIds = curUser.getDataAuthOrgIds();
		
		String orgId = request.getParameter("orgId");
		String projectNo = request.getParameter("projectNo");
		String productType = request.getParameter("productType");
		String customerName = request.getParameter("customerName");
		String applyDateStart = request.getParameter("applyDateStart");
		String applyDateEnd = request.getParameter("applyDateEnd");
		String projectStatus = request.getParameter("projectStatus");
		String applyAmtMin = request.getParameter("applyAmtMin");
		String applyAmtMax = request.getParameter("applyAmtMax");
		/*String customerManagerNum = curUser.getId().toString();
		
		String adminRoles = businessApplicationService.findAdminRoles();
		a : for (String adminRole : adminRoles.split(",")) {
			for (EcOrgPersonconnrole role : curUser.getRoles()) {
				if (role.getEcOrgRole().getRoleNum().equals(adminRole)) {
					customerManagerNum = "";
					break a;
				}
			}
		}*/
		Page<Object[]> page = null;
		try {
			page = businessApplicationService.findBySearch(firstIndex
					/ pageSize + 1, pageSize, orgId, projectNo, productType,
					customerName, applyDateStart, applyDateEnd, projectStatus,
					applyAmtMin, applyAmtMax, null, logOrgId, personId, dataAuthType, dataAuthOrgIds);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
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
	 * 撤销
	 * 
	 * @param workflowId
	 * @param taskId
	 * @param taskTypeId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/cancel")
	public Result cancelWorkflow(@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskTypeId") String taskTypeId) {
		String curUserLogonName = this.curUserLogonName();

		String comments = "";
		String taskStageCode = taskTypeId;
		String receiveWfId = null;
		String curUserName = curUserName();
		try {
			receiveWfId = this.approvalService.cancelApproval(workflowId,
					curUserLogonName, taskId, taskStageCode, comments,
					curUserName,null);

			if (StringUtils.isBlank(receiveWfId)) {
				logger.error("取消工作流调用webservice时返回值为空");
				return failure("撤销任务时出现异常");
			}
			return success();
		} catch (Exception e) {
			logger.error("取消工作流调用webservice时出现异常", e);
			return failure("撤销任务时出现异常");
		}
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

	private String curUserLogonName() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getLogname();
		}
		return curUser;
	}

	private String curUserName() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getName();
		}
		return curUser;
	}
}
