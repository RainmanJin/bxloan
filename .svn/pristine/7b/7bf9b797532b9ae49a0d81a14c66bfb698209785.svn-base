package com.coamctech.bxloan.service.sysmng.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.SystemInterfaceOrgLink;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.service.sysmng.SystemInterfaceOrgLinkService;
@Transactional
@Service
public class SystemInterfaceOrgLinkServiceImpl implements SystemInterfaceOrgLinkService{

	@Autowired
	private DynamicQuery dynamicQuery;
	
	 /**
	 *  判断指定接口是否打开
	 * 
	 * @param orgId:机构Id 
	 * @param key: 接口名称
	 * @return true 打开 false 关闭 如果数据没有填充默认关闭
	 * 
	 */
	@SuppressWarnings("unchecked")
	public boolean isInterfaceOpen(String orgId, String key) {
		Map map = null;
		String sql = "select interface_value from system_interfaceOrgLink where orgId = ?1 "
				+ " and interface_key = ?2 ";
		List<String> params = new ArrayList<String>();
		params.add(orgId);
		params.add(key);
		String interfaceValue = dynamicQuery.nativeQuerySingleResult(String.class, sql, params.toArray());
		if (SystemInterfaceOrgLink.INTERFACEVALUE_OPEN.equals(interfaceValue)) {
			return true;
		}
		return false;
	}

}
