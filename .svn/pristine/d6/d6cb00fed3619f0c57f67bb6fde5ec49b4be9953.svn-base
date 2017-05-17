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
public class UnitTag extends SimpleTagSupport {

	private String codeType; // 代码类型
	private String unitFieldName; //单位隐藏域的name
	private String selected; // 选中的值
	private String excludes; // 不包括的值
    private boolean disabled; //禁用
	
	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer html = new StringBuffer();

		DataDictLoader dd = WebAppCtxHolder.getBean(DataDictLoader.class);

		Collection<CodeData> codes = dd.getOneType(codeType);
		
		html.append("<input type='hidden' name='"+ unitFieldName +"' />\n");
		html.append("<div class='input-group-btn'>\n ");
		html.append("<button type='button' class='btn-sm ' " +
				    " style='background-color:#FFFFFF; ' tabindex='-1'>单位</button>\n");
		
		html.append("<button type='button' data-flag = 'main' class='btn-sm   dropdown-toggle' data-toggle='dropdown' tabindex='-1' style='background-color:#FFFFFF;'>\n");
		html.append("<span class='caret'></span>\n");
		html.append("<span class='sr-only'>切换下拉菜单</span>\n");
		html.append("</button>\n");
		html.append("<ul class='dropdown-menu'>\n");
		
		String unitName = "";
		String unitValue = "";
		for (CodeData data : codes) {
			String text = data.getCodeName();
			String value = data.getCodeValue();
			if (StringUtils.isEmpty(excludes) || !Arrays.asList(excludes.split(",")).contains(value)) {
					
				html.append("<li data-value='").append(value).append("'><a>");
				html.append(text + "</a></li>\n");
				if (StringUtils.equals(value, selected)) {
					unitName = text;
					unitValue = value;
				}
			}
		}
		html.append("</ul>\n");
		html.append("</div> ");
		
		String finalContext = "";
		if(StringUtils.isNotBlank(unitName)&&StringUtils.isNotBlank(unitValue)){
			finalContext = html.toString().replace("单位", unitName);
			finalContext = finalContext.replace("<input type='hidden'", "<input type='hidden' value='"+ unitValue + "' ");
		}else{
			finalContext = html.toString();
		}
		
		if(disabled){
			finalContext = finalContext.replace("data-flag = 'main'", "disabled = 'disabled'");
		}
		
		getJspContext().getOut().write(finalContext);
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

	public String getUnitFieldName() {
		return unitFieldName;
	}

	public void setUnitFieldName(String unitFieldName) {
		this.unitFieldName = unitFieldName;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	
	
}
