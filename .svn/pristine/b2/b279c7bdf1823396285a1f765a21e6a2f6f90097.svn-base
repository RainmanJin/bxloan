package com.coamctech.bxloan.web.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 当系统不是从portal跳转过来时, 抛出此异常. 如抛出该异常则停止本地认证, 跳转至CasShiroLoginFailureController处理
 * 
 * @see com.coamctech.uum.web.controller.CasShiroLoginFailureController
 * @author wh
 * @lastModified 2014-11-13 17:01:40
 */
public class IncorrectRefererException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public IncorrectRefererException() {
		super();
	}

	public IncorrectRefererException(String message) {
		super(message);
	}

	public IncorrectRefererException(Throwable cause) {
		super(cause);
	}

	public IncorrectRefererException(String message, Throwable cause) {
		super(message, cause);
	}

}
