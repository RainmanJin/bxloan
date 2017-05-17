package com.coamctech.bxloan.commons.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 在表单认证的基础上添加验证码支持
 * 
 * @author wh
 *
 */
public class OrgFormAuthenticationFilter extends FormAuthenticationFilter{
	
	public static final String DEFAULT_CAPTCHA_PARAM = "orgid";
	private String orgParam = DEFAULT_CAPTCHA_PARAM;
	
	public String getOrgParam() {
		return orgParam;
	}

	public void setOrgParam(String orgParam) {
		this.orgParam = orgParam;
	}

	/**
	 * 重写创建令牌的逻辑：在令牌中添加对机构的支持
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
        String password = getPassword(request);
        String orgid = getOrgParam(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new OrgUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, orgid);
	}

	/**
	 * 重写执行登录的逻辑：添加对验证码校验的支持。如校验不通过，则抛出IncorrectCaptchaException
	 */
//	@Override
//	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
//		
//		AuthenticationToken token = createToken(request, response);
//        if (token == null) {
//            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
//                    "must be created in order to execute a login attempt.";
//            throw new IllegalStateException(msg);
//        }
//        
//        try {
//        	validateCaptcha((HttpServletRequest) request, (CaptchaUsernamePasswordToken) token);
//            Subject subject = getSubject(request, response);
//            subject.login(token);
//            return onLoginSuccess(token, subject, request, response);
//	    } catch (AuthenticationException e) {
//	        return onLoginFailure(token, e, request, response);
//	    }
//	}
//
//	private void validateCaptcha(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
//		String captcha = token.getCaptcha();
//		Boolean isResponseCorrect = captchaService.validateResponseForID(request.getSession().getId(), captcha);
//		if (!isResponseCorrect) {
//			throw new IncorrectCaptchaException();
//		}
//	}
	
	/**
	 * 重写登录成功的逻辑，以规避：登录成功后跳转到已保存的url的问题，使登录成功后只重定向到配置的successUrl中
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, 
			Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath() + getSuccessUrl());
		return false; // 直接处理登录成功的重定向，避免请求链继续执行
	}

	protected String getOrgParam(ServletRequest request) {
        return WebUtils.getCleanParam(request, getOrgParam());
    }
	

}
