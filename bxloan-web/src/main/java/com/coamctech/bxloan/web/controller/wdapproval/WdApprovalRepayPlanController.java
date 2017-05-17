package com.coamctech.bxloan.web.controller.wdapproval;

import static com.coamctech.bxloan.commons.GlobalConstants.WDAPPROVALREPAYPLAN;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.service.approval.ApprovalRepayPlanService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;

@Controller
@RequestMapping("/" + WDAPPROVALREPAYPLAN)
public class WdApprovalRepayPlanController extends BaseController {

	@Autowired
	private ApprovalRepayPlanService approvalRepayPlanService;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;

	@RequestMapping("/searchRepaymentPlanList")
	@ResponseBody
	public DataTablesPage searchRepaymentPlanList(
			Integer sEcho,
			Integer iDisplayStart,
			Integer iDisplayLength,
			@RequestParam(required = false, value = "projectNo") String projectNo,
			@RequestParam(required = false) Long approvalId) {
		Page<Object[]> page = approvalRepayPlanService.searchRepayPlanList(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, projectNo,
				approvalId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	@RequestMapping("/batchInitRepayPlan")
	@ResponseBody
	public Result batchInitRepayPlan(Long projectId, BigDecimal applyAmt,
			Integer applyTerm, String applyTermUnit, String startDate,
			Integer monthDate, Integer repayDateForCount) {
		try {
			approvalRepayPlanService.insertRepayPlanList(projectId, applyAmt,
					applyTerm, applyTermUnit,
					new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					monthDate, repayDateForCount);
			return success("批量初始化成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("批量初始化失败！");
		}
	}

	@RequestMapping("/getRepayPlan")
	@ResponseBody
	public ApprovalHistoryRepayPlan getRepayPlan(Long id) {
		ApprovalHistoryRepayPlan repayPlan = approvalRepayPlanService
				.getRepayPlan(id);
		repayPlan.setRepayDateStr(new SimpleDateFormat("yyyy-MM-dd")
				.format(repayPlan.getRepayDate()));
		return repayPlan;
	}

	@RequestMapping("/deleteRepayPlan")
	@ResponseBody
	public Result deleteRepayPlan(Long id) {
		try {
			approvalRepayPlanService.deleteRepayPlan(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}

	@RequestMapping("/saveRepayPlan")
	@ResponseBody
	public Result saveRepayPlan(ApprovalHistoryRepayPlan form) {
		try {
			Party party = uniqueCustomerService.findPartyByPartyId(form
					.getCustomerId().toString());
			form.setRepayDate(new SimpleDateFormat("yyyy-MM-dd").parse(form
					.getRepayDateStr()));
			form.setCustomerName(party.getPartyName());
			form.setCustomerNum(party.getCustomerNum());
			form.setSysCreateDate(new Date());
			form.setSysUpdateDate(new Date());
			//同一天不能多次还款
			if(approvalRepayPlanService.checkRepayDateIsExist(form)){
				return failure("同一还款日不能有多次还款！");
			}
			approvalRepayPlanService.saveRepayPlan(form);
			return success("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}
}
