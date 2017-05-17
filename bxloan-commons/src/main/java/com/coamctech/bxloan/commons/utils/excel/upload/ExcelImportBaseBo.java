package com.coamctech.bxloan.commons.utils.excel.upload;

import java.util.HashMap;
import java.util.Map;


/**   
 * 类名称：ExcelImportBaseBo
 * 类描述 ：excel导入，输出vo的基类 
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class ExcelImportBaseBo {
	private int dataLine ;
	private Integer subContractId;
	
	protected Map<String, String> errors;
	

	/**
	 * 所有错误
	 */
	private String allErrors = "";
	
	/**
	 * hasErrors
	 * 功能描述：是否验证通过
	 * 逻辑描述：
	 * @param
	 * @return true, 有错误，false 无错误
	 * @throws 
	 * @since Ver 1.00
	 */
	public boolean hasErrors() {
		if (errors == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * addFieldError
	 * 功能描述：设置验证消息
	 * 逻辑描述：fieldName 自动名称， errorMsg 错误信息
	 * @param
	 * @return  无
	 * @throws 
	 * @since Ver 1.00
	 */
	public void addFieldError(String fieldName, String errorMsg) {
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
		if (errors.containsKey(fieldName)) {
			String msg = errors.get(fieldName);
			errors.put(fieldName, msg + "," + errorMsg);
		} else {
			errors.put(fieldName, errorMsg);
		}
		
		allErrors = allErrors + errorMsg + ";";
	}
	
	/**
	 * add by wangyawei 20140324
	 * 功能描述：设置验证信息
	 * 逻辑描述：fieldName 字段名称， errorMsg 错误信息
	 * @param fieldName 字段名称
	 * @param errorMsg 错误信息
	 */
	public void addFieldErrors(String fieldName, String errorMsg) {
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
		if (errors.containsKey(fieldName)) {
			String msg = errors.get(fieldName);
			errors.put(fieldName, msg + "," + errorMsg);
		} else {
			errors.put(fieldName, errorMsg);
		}
		
		allErrors = allErrors + errorMsg;
	}
	
	/**
	 * getFieldError
	 * 功能描述：取某字段的错误信息.
	 * 逻辑描述：fieldName 自动名称
	 * @param
	 * @return  String
	 * @throws 
	 * @since Ver 1.00
	 */
	public String getFieldError(String fieldName) {
		if (errors == null) {
			return null;
		}
		if (errors.containsKey(fieldName)) {
			return errors.get(fieldName);
		}
		return null;
	}

	public String getAllErrors() {
		return allErrors;
	}

	public void setAllErrors(String allErrors) {
		this.allErrors = allErrors;
	}

	public int getDataLine() {
		return dataLine;
	}

	public void setDataLine(int dataLine) {
		this.dataLine = dataLine;
	}

	public Integer getSubContractId() {
		return subContractId;
	}

	public void setSubContractId(Integer subContractId) {
		this.subContractId = subContractId;
	}
}
