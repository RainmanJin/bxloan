package com.coamctech.bxloan.web.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coamctech.bxloan.commons.PropertiesConstants;
import com.coamctech.bxloan.web.security.IncorrectRefererException;
import com.coamctech.bxloan.web.security.MyCasFilter;





/**
 * CAS-SHIRO 登录出现异常处理
 * 
 * @author wh
 * @lastModified 2014-11-13 17:04:47
 */
@Controller
public class CasShiroLoginFailureController {
	
	@RequestMapping("/cas")
	public String failure(HttpServletRequest request) {
		String unifiedEntrance = PropertiesConstants.getProperty("portal.url") + "/unifiedEntrance";
		
		String exceptionClassName = (String) request.getAttribute(MyCasFilter.ERROR_KEY_ATTRIBUTE_NAME);
		
		if (IncorrectRefererException.class.getName().equals(exceptionClassName)) {
			return "redirect:" + unifiedEntrance + "?errCode=1";
		}
		return "casFailure";
	}
	
}
