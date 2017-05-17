package com.coamctech.bxloan.commons.utils.excel.upload;

import java.util.ArrayList;
import java.util.List;

/**   
 * 类名称：ExcelImportConfigItem
 * 类描述 ：excel配置模板中的公共配置信息，如 类型，对应vo，开始行
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class ExcelImportConfigItem {
	//文件名称
	private String importName;
	private String boClass;
	private Class boClazz;
	//执行导入业务逻辑的serviceBeanId
	private String serviceBeanId;
	//执行导入业务逻辑的serviceMethodName
	private String serviceMethodName;
	//起始行，物理行数
	private int startLine;
	private List<String[]> fields = new ArrayList<String[]>();
	//excel配置字段定义列表
	private List<ExcelImportConfigField> fieldList;
	
	public String getImportName() {
		return importName;
	}
	public void setImportName(String importName) {
		this.importName = importName;
	}
	public String getBoClass() {
		return boClass;
	}
	public void setBoClass(String boClass) {
		this.boClass = boClass;
	}
	public Class getBoClazz() {
		return boClazz;
	}
	public void setBoClazz(Class boClazz) {
		this.boClazz = boClazz;
	}
	public List<String[]> getFields() {
		return fields;
	}
	public void setFields(List<String[]> fields) {
		this.fields = fields;
	}
	public String getServiceBeanId() {
		return serviceBeanId;
	}
	public void setServiceBeanId(String serviceBeanId) {
		this.serviceBeanId = serviceBeanId;
	}
	public String getServiceMethodName() {
		return serviceMethodName;
	}
	public void setServiceMethodName(String serviceMethodName) {
		this.serviceMethodName = serviceMethodName;
	}
	public int getStartLine() {
		return startLine;
	}
	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}
	public List<ExcelImportConfigField> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<ExcelImportConfigField> fieldList) {
		this.fieldList = fieldList;
	}
	
	
}
