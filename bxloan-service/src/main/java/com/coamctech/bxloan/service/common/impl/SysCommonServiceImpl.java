package com.coamctech.bxloan.service.common.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.common.SysCommonService;

/**   
 * 类名称：SysCommonServiceImpl<br/>
 * 类描述 ：<br/>
 * 创建人: hwl<br/>
 * 创建时间：2015年5月20日 下午2:53:23  <br/>
 * 修改人：hwl<br/>
 * 修改时间：2015年5月20日 下午2:53:23  <br/>
 * 修改备注：<br/>
 * 版本： V1.0<br/>
 */
@Component
public class SysCommonServiceImpl implements SysCommonService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Override
	public Date computeExpirationDate(Date payLoanDate, Integer term,
			String termUnit) {
		if(payLoanDate==null){
			return null;
		}
		Date expirationDate=null;
		if(StringUtils.equals(dataDict.getCodeVal("TermUnitCd", "S1"), termUnit)){//年
			expirationDate=DateTools.addYear(payLoanDate, term);
		}else if(StringUtils.equals(dataDict.getCodeVal("TermUnitCd", "S2"), termUnit)){//月
			expirationDate=DateTools.addMouth(payLoanDate, term);
		}else if(StringUtils.equals(dataDict.getCodeVal("TermUnitCd", "S3"), termUnit)){//日
			expirationDate=DateTools.addDay(payLoanDate, term);
		}
		return expirationDate;
	}
	
	@Override
	public String getAdminLevels(Long personId){
		StringBuffer buf = new StringBuffer();
		buf.append("select r.role_num from EC_ORG_ROLE r where r.id in");
		buf.append("(select p.roleid from EC_ORG_PERSONCONNROLE p where p.personid = ?1)");
		List<String> roleNums = dynamicQuery.nativeQuery(String.class,
				buf.toString(), personId);
		String sys_admin = "";
		for (String roleNum : roleNums) {
			if (GlobalConstants.ONE_LEVEL_SYS_ADMIN.equals(roleNum)) {
				sys_admin = GlobalConstants.ONE_LEVEL_SYS_ADMIN;
				break;
			} else if (GlobalConstants.TWO_LEVEL_SYS_ADMIN.equals(roleNum)) {
				sys_admin = GlobalConstants.TWO_LEVEL_SYS_ADMIN;
				break;
			}
		}
		if (StringUtils.isEmpty(sys_admin)) {
			logger.info("既不是一级系统管理员也不是二级系统管理员！ SysCommonServiceImpl类的getAdminLevels方法...");
		}
		return sys_admin;
	}

}
