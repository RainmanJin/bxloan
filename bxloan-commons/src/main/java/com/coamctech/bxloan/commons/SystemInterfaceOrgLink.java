package com.coamctech.bxloan.commons;

/***
 *机构与接口联动类 
 * 
 */
public class SystemInterfaceOrgLink {
  
	public static String INTERFACEVALUE_OPEN = "1"; //1打开 0关闭
	
	public static String INTERFACEVALUE_CLOSE = "0";
	
	public static String INTERFACEKEY_Accounting = "openAccounting";//财务接口
	
	public static String INTERFACEKEY_Message = "openMessage";//短信接口
	
	public static String INTERFACEKEY_Update = "openUpdate";//调整还款计划
	
	public static String INTERFACEKEY_ORG = "openOrg";//是否关闭父机构
	
	public static String INTERFACEKEY_SETINTEREST = "openSetInterest";//是否关闭利息结记
	
	public static String INTERFACEKEY_RUNNING = "openRunning"; //是否跑批
	
	//20140527 add by wangyawei 
	public static String INTERFACEKEY_OVERDUERUNNING = "openOverDueRunning"; //是否跑逾期批
}
