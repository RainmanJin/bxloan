package com.coamctech.bxloan.web.controller.approval;

import static com.coamctech.bxloan.commons.GlobalConstants.ANTIFRAUD;
import static com.coamctech.bxloan.commons.GlobalConstants.APPROVAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.service.approval.AntiFraudService;

/**
 * 风险-反欺诈
 * @author Acore
 *
 */
@Controller
@RequestMapping("/"+APPROVAL+"/"+ANTIFRAUD)
public class AntifraudController {
	@Autowired
	private AntiFraudService antifraudQueryService;

	@RequestMapping
	public String index(@RequestParam("businessNum")String businessNum,Model model) {
		model.addAttribute("businessNum", businessNum);
		return APPROVAL+"/antiFraud";
	}
	/**
	 * 详细分页
	 * @param id
	 * @param search
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findDetailBySearch")
	@ResponseBody
	public DataTablesPage findAntifraudDeatilByBizNum(
			@RequestParam("businessNum") String businessNum,
			@RequestParam(value = "sSearch", required = false) String search,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		Page page = antifraudQueryService.findAntiFraudDetailByBizNum(search,firstIndex
				/ pageSize + 1, pageSize, businessNum);
		return new DataTablesPage(sEcho, page);
	}
}
