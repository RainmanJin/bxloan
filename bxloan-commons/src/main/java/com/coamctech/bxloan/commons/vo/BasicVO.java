package com.coamctech.bxloan.commons.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public abstract class BasicVO {
	
	protected String str(Object src){
		return src==null?"":src.toString().trim();
	}
	
	protected Long strToLong(String str){
		return StringUtils.isBlank(str)?null:Long.parseLong(str.trim());
	}
	
	protected BigDecimal strToDecimal(String str){
		return StringUtils.isBlank(str)?null:new BigDecimal(str.trim());
	}
	
	protected Integer strToInteger(String str){
		return StringUtils.isBlank(str)?null:Integer.parseInt(str.trim());
	}
	
	protected String dateToSimpleStr(Date date){
		return date==null?null:new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	protected String dateToFullStr(Date date){
		return date==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
}
