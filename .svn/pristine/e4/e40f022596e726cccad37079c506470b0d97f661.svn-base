package com.coamctech.bxloan.web.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MultiAccControlFilter extends AccessControlFilter {
	
	private Logger logger =LoggerFactory.getLogger(MultiAccControlFilter.class);
	public static final String DEFAULT_LOGIN_URL = "/login.jsp";
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		if(logger.isInfoEnabled()){
			HttpServletRequest httpReq=(HttpServletRequest)request;
			logger.info("URL:{}",httpReq.getRequestURI());
		}
		ShiroUser su = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		if (null != su&& StringUtils.isNotEmpty(su.getLogname())) {
			if(logger.isInfoEnabled()){
				logger.info("Current User dataAuthType:{},dataAuthOrgIds:{}",su.getDataAuthType(),su.getDataAuthOrgIds());
			}
			return true;
		} 
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, "/login");
		return false;
	}

}
