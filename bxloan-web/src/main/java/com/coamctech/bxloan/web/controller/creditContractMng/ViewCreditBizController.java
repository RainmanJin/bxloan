package com.coamctech.bxloan.web.controller.creditContractMng;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;

@Controller
@RequestMapping(GlobalConstants.VIEW_CREDIT_BIZ)
public class ViewCreditBizController {
	@Autowired
	private DataDict dataDict;
	
	@RequestMapping
	public String index(Model model) {
		
		// 担保方式列表
		Collection<CodeData> guaranteeModes = dataDict.getOneType("CdsGuarantMode");
		model.addAttribute("guaranteeModes", guaranteeModes);
		
		return GlobalConstants.CREDIT_CONTRACT_MNG + "/" + GlobalConstants.VIEW_CREDIT_BIZ + "/main";
	}
}
