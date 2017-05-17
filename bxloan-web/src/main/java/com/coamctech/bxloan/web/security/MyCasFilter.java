package com.coamctech.bxloan.web.security;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.PropertiesConstants;
import com.coamctech.bxloan.web.security.cas.MyCasToken;

/**
 * 增加对登入入口的校验
 * 
 * @author wh
 * @lastModified wanghui 2014-11-13 16:59:26
 */
public class MyCasFilter extends CasFilter {

	private final String TICKET_PARAMETER = "ticket";
	public static final String ERROR_KEY_ATTRIBUTE_NAME = MyCasFilter.class.getName() + ".casShiroLoginFailure";
	
	
	private final String PORTAL_LOGNAME="logname";
	private final String PORTAL_LOGORGID="logorgid";

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		AuthenticationToken token = this.createToken(request, response);
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
					+ "must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}
		try {
			//开启HTTPReferer,并验证请求来源
			if(GlobalConstants.HTTP_REFERER){
				validateReferer(request);
			}
			Subject subject = getSubject(request, response);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

	/**
	 * 要求只能是从portal中跳转过来的才可以
	 * 
	 * @param request
	 */
	private void validateReferer(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		String referer = req.getHeader("referer");
		if (StringUtils.isEmpty(referer)
				|| !StringUtils.startsWith(referer, PropertiesConstants.getProperty("portal.url"))) { // 从portal中跳转过来的
			throw new IncorrectRefererException();
		}
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, 
			ServletRequest request, ServletResponse response) {
		String className = ae.getClass().getName();
		request.setAttribute(ERROR_KEY_ATTRIBUTE_NAME, className);
		return true;
	}
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) throws Exception {
		switch (GlobalConstants.DEFAULT_LOGIN_MODE) {
		case PORTAL_SSO:
			//模拟登录，自动切换Token
			String queryString = WebUtils.getSavedRequest(request).getQueryString();
			Map<String, String> map=new HashMap<String, String>();
			if (StringUtils.isNoneEmpty(queryString)) {
				String[] pram = queryString.split("&");
				for(String str : pram) {
					if (StringUtils.isNoneEmpty(str)) {
						String[] keyValue = str.split("=");
						if (keyValue.length == 2) {
							map.put(keyValue[0], keyValue[1]);
						}
					}
				}
			}
			if(map.size()>=2&&map.containsKey(PORTAL_LOGNAME)&&map.containsKey(PORTAL_LOGORGID)){
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				String ticket = httpRequest.getParameter(TICKET_PARAMETER);
				return new MyCasToken(ticket, map.get(PORTAL_LOGNAME), map.get(PORTAL_LOGORGID));
			}
			break;
		case BIZ_SSO:
			break;

		default:
			break;
		}
		return super.createToken(request, response);
	}
	

}
