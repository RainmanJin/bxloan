package com.coamctech.bxloan.service.sysmng;
/**
 * 机构与接口联动
 * 
 * */
public interface SystemInterfaceOrgLinkService {
	/***
	 * 判断指定接口是否打开
	 *@param 
	 *机构Id
	 *接口名称
	 *@return  true 打开 false 关闭
	 *
	 */
	public boolean isInterfaceOpen(String orgId,String key);
}
