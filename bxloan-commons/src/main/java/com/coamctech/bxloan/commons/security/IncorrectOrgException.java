package com.coamctech.bxloan.commons.security;

import org.apache.shiro.authc.AuthenticationException;

@SuppressWarnings("serial")
public class IncorrectOrgException extends AuthenticationException {

	public IncorrectOrgException() {
		super();
	}

	public IncorrectOrgException(String message) {
		super(message);
	}

	public IncorrectOrgException(Throwable cause) {
		super(cause);
	}

	public IncorrectOrgException(String message, Throwable cause) {
		super(message, cause);
	}
}
