package com.coamctech.bxloan.web.controller.approval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.InfoInputConfig;
import com.coamctech.bxloan.entity.InfoValues;
import com.coamctech.bxloan.service.approval.ApprovalApplyInfoService;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoCellVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoLineGroupVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoLineVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoTableVO;

@Controller
@RequestMapping("/approvalApplyInfo")
public class ApprovalApplyInfoController extends BaseController {

	@Autowired
	private ApprovalApplyInfoService appInfoService;

	@ResponseBody
	@RequestMapping("/submit")
	@SuppressWarnings("rawtypes")
	public Result submit(HttpServletRequest req) {
		try {
			List<InfoValues> valueList = new ArrayList<InfoValues>();
			String subjectId = req.getParameter("subjectId");
			Enumeration paramsNames = req.getParameterNames();
			while (paramsNames.hasMoreElements()) {
				String paramKey = paramsNames.nextElement().toString();
				if (paramKey.equals("subjectId")) {

				} else {
					InfoValues value = new InfoValues();
					value.setSubjectId(Long.parseLong(subjectId));
					value.setName(paramKey);
					value.setValue(req.getParameter(paramKey));
					valueList.add(value);
				}
			}
			this.appInfoService.saveValues(valueList, "1,2");
			return success();
		} catch (Exception e) {
			logger.error("保存时发生异常", e);
			return failure("保存时发生异常");
		}
	}

	@ResponseBody
	@RequestMapping("/findValue")
	public Result findBySubjectId(@RequestParam("subjectId") String subjectId) {

		try {
			Map<String, String> values = this.appInfoService
					.findValuesBySubjectId(Long.parseLong(subjectId));
			return success(values);
		} catch (Exception e) {
			logger.error("查询已填写内容时出错", e);
			return failure("查询已填写内容时出错");
		}

	}

	@RequestMapping("/rm")
	public void validateJs(HttpServletResponse resp) {
		resp.setContentType("application/javascript;charset=UTF-8");
		ApplyInfoTableVO tableTel = this.appInfoService.getTableByType("1");
		ApplyInfoTableVO tableNet = this.appInfoService.getTableByType("2");
		String js = this.buildValidatJS(tableTel, tableNet);
		try {
			resp.getWriter().write(js);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	private String buildValidatJS(ApplyInfoTableVO... tables) {
		StringBuilder js = new StringBuilder();
		js.append("define(function(require, exports, module) { var ruleAndMsg = {");
		Map<String, String> ruleMap = new HashMap<String, String>();
		Map<String, String> msgMap = new HashMap<String, String>();

		for (ApplyInfoTableVO table : tables) {
			for (ApplyInfoLineGroupVO lineG : table.getLinegroupList()) {
				for (ApplyInfoLineVO line : lineG.getLines()) {
					for (ApplyInfoCellVO cell : line.getCellList()) {
						for (InfoInputConfig input : cell.getInputList()) {

							if (StringUtils.isNotBlank(input.getValidator())) {
								ruleMap.put(cell.getCell().getCellInputName(),
										input.getValidator());
								if (StringUtils.isNotBlank(input
										.getValidatorMsg())) {
									msgMap.put(cell.getCell()
											.getCellInputName(), input
											.getValidatorMsg());
								}
							}

						}
					}
				}
			}
		}

		String rules = mapToJSON(ruleMap);
		String msges = mapToJSON(msgMap);
		js.append("rules:").append(rules).append(",");
		js.append("messages:").append(msges);
		js.append("};module.exports = ruleAndMsg;});");
		return js.toString();
	}

	private String mapToJSON(Map<String, String> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && !map.isEmpty()) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				json.append(entry.getKey()).append(":{")
						.append(entry.getValue()).append("},");
			}
		}
		json.append("}");
		return json.toString();
	}

}
