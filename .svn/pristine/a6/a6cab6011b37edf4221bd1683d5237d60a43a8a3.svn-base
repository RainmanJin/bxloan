package com.coamctech.bxloan.service.freemarker.utils;

import org.apache.commons.lang3.StringUtils;




public class TemplateDataHelper {
	public static final String SPACE = " ";
    public static final String EMPTY = "";
	public static final String BLANK_10="          ";
	public static final String BLANK_20="                    ";// 20空格
	public static final String BLANK30="                              ";// 30空格
	/**
	 * @param str
	 * @param minLength	大于0左对齐，小于右对齐，不足用空格补充
	 * @return
	 */
	public static String formatLength(final String str,final int minLength){
		if(minLength==0){
			return str!=null?str:EMPTY;
		}
		return String.format("%"+(minLength!=0?-minLength:minLength)+"s", str!=null?str:EMPTY);         
	}
	/**
	 * @param str
	 * @return
	 */
	public static String defaultString(final String str){
		return StringUtils.defaultString(str);         
	}
	public static void main(String[] args) {
		System.out.println(formatLength(null, 5).length());
	}
}
