package com.coamctech.bxloan.web.taglib.datadict;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.WebAppCtxHolder;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;

/** 
 * 获取唯一的数据字典
 * 
 * @author wanghui
 * @lastModified wanghui 2014-7-29 15:58:06
 */
public class UniqueOneTag extends SimpleTagSupport {
	
	private String codeType; // 代码类型
	private String codeKey; // 代码key
	private String attrName; // 指定放入pageScope的属性名
	
	@Override
	public void doTag() throws JspException, IOException {
		DataDict dd = WebAppCtxHolder.getBean(DataDict.class);
		CodeData uniqueCode = dd.getUniqueOne(codeType, codeKey);
		getJspContext().setAttribute(StringUtils.defaultString(attrName, "code"), uniqueCode);
	}
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
}
