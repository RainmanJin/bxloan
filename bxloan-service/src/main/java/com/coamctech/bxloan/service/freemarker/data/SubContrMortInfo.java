package com.coamctech.bxloan.service.freemarker.data;


/**
 * 抵押从合同
 *
 */
public class SubContrMortInfo extends SubContrBaseInfo {

	/**
	 * 抵押物名称
	 */
	private String mortName;
	/**
	 * 评估价值
	 */
	private String mortAssessValue;
	/**
	 * 已设抵质押值
	 */
	private String mortYesSetValue;
	public void initMort(String mortName,String mortAssessValue,String mortYesSetValue){
		this.mortName=mortName;
		this.mortAssessValue=mortAssessValue;
		this.mortYesSetValue=mortYesSetValue;
	}
	public String getMortName() {
		return mortName;
	}
	public void setMortName(String mortName) {
		this.mortName = mortName;
	}
	public String getMortAssessValue() {
		return mortAssessValue;
	}
	public void setMortAssessValue(String mortAssessValue) {
		this.mortAssessValue = mortAssessValue;
	}
	public String getMortYesSetValue() {
		return mortYesSetValue;
	}
	public void setMortYesSetValue(String mortYesSetValue) {
		this.mortYesSetValue = mortYesSetValue;
	}
}
