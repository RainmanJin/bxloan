package com.coamctech.bxloan.commons.utils.excel.upload;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


/**   
 * 类名称：ExcelValidateUtil
 * 类描述 ：excle导入信息校验工具类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class ExcelValidateUtil {
	public static final String END_SYMBOL = "endoffile"; 
	public static String validateCellValue(Object cellValue, ExcelImportConfigField configField) {
		if (!configField.isAlowBlank()) {
			if (cellValue == null) {
				return configField.getFieldDesc() + "为必填字段！";
			} else if(ObjectUtils.equals(cellValue, 0.0)){
				return configField.getFieldDesc() + "不能为0！";
			}
		}else{
			if (cellValue == null) {
				return null;
			}
		}
		String regexStr = configField.getRegexStr();
		if(StringUtils.isBlank(regexStr)){
			return null;
		}
		Pattern pattern = Pattern.compile(regexStr);
		Matcher matcher = pattern.matcher(cellValue.toString());
		while (!matcher.find()) {
			return configField.getErrorMsg();
		}
		return null;
	}
}
