package com.coamctech.bxloan.commons.result;

import static com.coamctech.bxloan.commons.GlobalConstants.FIELDS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

public class Report extends HashMap<String, Object> {

	private static final long serialVersionUID = 1459570679610524378L;
	
	public Report() {
		super();
	}
	
	public Report(ReportExtension extension) {
		extension(extension);
	}
	
	public void addFields(Object fields) {
		this.put(FIELDS, fields);
	}
	
	public void addParameter(String parameterName, Object parameter) {
		this.put(parameterName, parameter);
	}
	
	public void addParameters(Map<String, Object> parameters) {
		this.putAll(parameters);
	}
	
	public void extension(ReportExtension extension) {
		this.put(JasperReportsMultiFormatView.DEFAULT_FORMAT_KEY, extension.name());
	}

}
