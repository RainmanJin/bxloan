package com.coamctech.bxloan.web.taglib.datadict;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.WebAppCtxHolder;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.service.DataDictLoader;

public class SingleselectTag extends SimpleTagSupport{
	private String codeType; // 代码类型
	private String codeVals; // 所需选项的codeValue
	private String checked; // 被选中的选项
	private String cbName; // 多选框的name
	private String cssClass; //生成多选框的class
	private boolean aceStyle = false; //是否使用ace风格
	
	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer html = new StringBuffer();
		DataDictLoader dd = WebAppCtxHolder.getBean(DataDictLoader.class);
		Collection<CodeData> codes = dd.getOneType(codeType);
		if (StringUtils.isNotBlank(codeVals)) {
			codes = dd.getOneType(codeType, codeVals.split(","));
		}
		
		if(aceStyle){
			for (CodeData data : codes) {
				this.makeAceStyle(html, data, checked);
			}
		}else{
			for (CodeData data : codes) {
				this.makeDefaultStyle(html, data, checked);
			}
		}
		getJspContext().getOut().write(html.toString());
	}
	
	
	private void makeDefaultStyle(StringBuffer html, CodeData data, String check){
		String text = data.getCodeName();
		String value = data.getCodeValue();
		html.append("<input type='radio' value='").append(value).append("' name='").append(cbName).append("' ");
		if (StringUtils.isNotBlank(check)) {
			html.append(" checked='checked'");
		}
		if (StringUtils.isNotBlank(cssClass)) {
			html.append(" class='"+ cssClass +"'");
		}
		html.append("/> ").append(text).append(" ");
	}
	
	public void makeAceStyle(StringBuffer html, CodeData data, String check){
		String text = data.getCodeName();
		String value = data.getCodeValue();
		html.append("<input name='").append(cbName).append("' type='radio' value='").append(value).append("' ");
		if (StringUtils.isNotBlank(cssClass)) {
			html.append(" class='ace "+ cssClass +"'");
		}else{
			html.append(" class='ace' ");
		}
		if (StringUtils.isNotBlank(check) && value.equals(check)) {
			html.append(" checked='checked'");
		}
		html.append("/> ");
		html.append("<span class='lbl'> ").append(text).append(" </span> ");
	}
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeVals() {
		return codeVals;
	}

	public void setCodeVals(String codeVals) {
		this.codeVals = codeVals;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getCbName() {
		return cbName;
	}

	public void setCbName(String cbName) {
		this.cbName = cbName;
	}

	public boolean getAceStyle() {
		return aceStyle;
	}

	public void setAceStyle(boolean aceStyle) {
		this.aceStyle = aceStyle;
	}


	public String getCssClass() {
		return cssClass;
	}


	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
}
