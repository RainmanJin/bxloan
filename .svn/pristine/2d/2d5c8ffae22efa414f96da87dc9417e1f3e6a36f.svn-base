package com.coamctech.bxloan.web.taglib.auth;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 布局内容显示不显示控制
 * @author AcoreHeng
 *
 */
public class LayoutContentTag extends TagSupport {
	
	@Override
	public int doStartTag() throws JspException {
		boolean result=true;
		switch (GlobalConstants.DEFAULT_LOGIN_MODE) {
		case PORTAL_SSO:
			result=false;
			break;
		case BIZ_SSO:
			
			break;

		default:
			break;
		}
		return result? EVAL_BODY_INCLUDE : SKIP_BODY;//EVAL_BODY_INCLUDE代表执行自定义标签中的内容，SKIP_BODY代表不执行自定义标签中的内容。
	}
}
