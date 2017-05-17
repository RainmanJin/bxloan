package com.coamctech.bxloan.web.taglib.datadict;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.WebAppCtxHolder;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.service.DataDictLoader;

/**
 * 获取一类数据字典
 * 
 * @author wanghui
 * @lastModified wanghui 2014-7-29 15:58:06
 */
public class OneTypeTag extends SimpleTagSupport {

	private String codeType; // 代码类型
	private String selected; // 选中的值
	private String excludes; // 不包括的值
    private String codeVals; // 所需选项的codeValue
    
	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer html = new StringBuffer();

		DataDictLoader dd = WebAppCtxHolder.getBean(DataDictLoader.class);

		Collection<CodeData> codes = dd.getOneType(codeType);
		if(StringUtils.isNotBlank(codeVals)){
			codes = dd.getOneType(codeType, codeVals.split(","));
		}
		for (CodeData data : codes) {
			String text = data.getCodeName();
			String value = data.getCodeValue();
			if (StringUtils.isEmpty(excludes) || !Arrays.asList(excludes.split(",")).contains(value)) {
				html.append("<option value='").append(value).append("'");
				if(StringUtils.isNotBlank(selected)){
					String[] selecteds = selected.split(",");
					for(String selectedSingle:selecteds){
						if (StringUtils.equals(value, selectedSingle)) {
							html.append(" selected='selected'");
							break;
						}
					}
				}
				html.append(">").append(text).append("</option>\n");
			}
		}

		getJspContext().getOut().write(html.toString());
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getExcludes() {
		return excludes;
	}

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}

	public String getCodeVals() {
		return codeVals;
	}

	public void setCodeVals(String codeVals) {
		this.codeVals = codeVals;
	}
	
}
