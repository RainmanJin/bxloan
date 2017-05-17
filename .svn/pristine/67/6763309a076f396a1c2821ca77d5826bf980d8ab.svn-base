package com.coamctech.bxloan.web.controller.creditContractMng;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;

@Controller
@RequestMapping(GlobalConstants.CHANGE_GUARANTEE_MODE)
public class ChangeGuaranteeModeController extends BaseController {
	@Autowired
	private DataDict dataDict;
	
	@RequestMapping
	public String index(Model model) {
		
		// 担保方式列表
		Collection<CodeData> guaranteeModes = dataDict.getOneType("CdsGuarantMode");
		model.addAttribute("guaranteeModes", guaranteeModes);
		
		return GlobalConstants.CREDIT_CONTRACT_MNG + "/" + GlobalConstants.CHANGE_GUARANTEE_MODE + "/main";
	}
}
