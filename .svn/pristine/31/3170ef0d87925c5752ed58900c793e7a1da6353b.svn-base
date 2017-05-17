package com.coamctech.bxloan.web.controller.bizapply;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.entity.DrCreditLiabilityDetail;
import com.coamctech.bxloan.entity.InfoValues;
import com.coamctech.bxloan.service.approval.ApprovalApplyInfoService;
import com.coamctech.bxloan.service.bizapply.DrCreditInfoService;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoTableVO;

/**
 * 征信记录（借款人及其配偶）
 * 
 */
@Controller
@RequestMapping("/drCreditInfo")
public class DrCreditInfoController extends BaseController {
	@Autowired
	private DrCreditInfoService drCreditInfoService;

	@Autowired
	private ApprovalApplyInfoService approvalApplyInfoService;

	@RequestMapping("/loadDrCreditFormItem")
	@ResponseBody
	public Result loadDrCreditFormItem() {
		ApplyInfoTableVO formItem = approvalApplyInfoService
				.getTableByType("3");
		return success("保存成功", formItem);

	}

	@RequestMapping("/getDrCreditFormTemplate")
	public String getDrCreditFormTemplate(Model model) {
		ApplyInfoTableVO tbForm = approvalApplyInfoService.getTableByType("3");
		model.addAttribute("tbForm", tbForm);
		return "bizapply/drCreditForm";

	}

	@RequestMapping("/rm")
	public void validateJs(HttpServletResponse resp) {
		resp.setContentType("application/javascript;charset=UTF-8");
		String js = this.drCreditInfoService.buildValidatJS("3");
		try {
			resp.getWriter().write(js);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	@ResponseBody
	@RequestMapping("/saveDrCreditInfo")
	@SuppressWarnings("rawtypes")
	public Result saveDrCreditInfo(HttpServletRequest req) {
		try {
			List<InfoValues> valueList = new ArrayList<InfoValues>();
			String subjectId = req.getParameter("projectId");
			Enumeration paramsNames = req.getParameterNames();
			while (paramsNames.hasMoreElements()) {
				String paramKey = paramsNames.nextElement().toString();
				if (paramKey.equals("projectId")) {

				} else {
					InfoValues value = new InfoValues();
					value.setSubjectId(Long.parseLong(subjectId));
					value.setName(paramKey);
					value.setValue(req.getParameter(paramKey));
					valueList.add(value);
				}
			}
			this.approvalApplyInfoService.saveValues(valueList, "3");
			return success();
		} catch (Exception e) {
			logger.error("保存时发生异常", e);
			return failure("保存时发生异常");
		}
	}

	@ResponseBody
	@RequestMapping("/findDrCreditInfo/{projectId}")
	public Result findDrCreditInfo(@PathVariable("projectId") Long projectId) {

		try {
			Map<String, String> values = this.approvalApplyInfoService
					.findValuesBySubjectId(projectId);
			return success(values);
		} catch (Exception e) {
			logger.error("查询已填写内容时出错", e);
			return failure("查询已填写内容时出错");
		}

	}

	/**
	 * 征信负债明细列表（价款人及其配偶）
	 * 
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/findDrCldList")
	@ResponseBody
	public DataTablesPage findDrCldList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize, Long projectId) {
		Page<DrCreditLiabilityDetail> page = drCreditInfoService.findDrCldPage(
				pageSize, (firstIndex / pageSize), projectId);
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);
		return dtPage;
	}

	/**
	 * 查询单条征信负债明细列表（价款人及其配偶）
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/findDrCld/{id}")
	@ResponseBody
	public Result findDrCld(@PathVariable("id") Long id) {
		if (id == null || id < 1) {
			return failure("参数错误，请确认！");
		}
		DrCreditLiabilityDetail drCld = this.drCreditInfoService.findDrCld(id);
		return success("保存成功", drCld);
	}

	/**
	 * 保存征信负债明细列表（价款人及其配偶）支持修改和新增
	 * 
	 * @param drCld
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveDrCld")
	@ResponseBody
	public Result saveDrCld(DrCreditLiabilityDetail drCld,
			HttpServletRequest request) {
		if (drCld == null) {
			return failure("参数错误，请确认！");
		}
		drCld.setLoanAmt(CommonHelper.toBigDecimal(request
				.getParameter("loanAmtStr")));
		drCld.setLoanOverAmt(CommonHelper.toBigDecimal(request
				.getParameter("loanOverAmtStr")));
		drCld.setMonthAvgRepayAmt(CommonHelper.toBigDecimal(request
				.getParameter("monthAvgRepayAmtStr")));
		drCld.setPawns(StringUtils.join(request.getParameterValues("pawn"), ","));
		drCld.setLoanStartDate(CommonHelper.str2Date(
				request.getParameter("loanStartDateStr"), CommonHelper.DF_DATE));
		this.drCreditInfoService.saveDrCld(drCld);
		return success("保存成功");
	}

	/**
	 * 删除征信负债明细列表（价款人及其配偶）
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delDrCld/{id}")
	@ResponseBody
	public Result delDrCld(@PathVariable("id") Long id) {
		if (id == null || id < 1) {
			return failure("参数错误，请确认！");
		}
		this.drCreditInfoService.delDrCld(id);
		return success("删除成功");
	}

}
