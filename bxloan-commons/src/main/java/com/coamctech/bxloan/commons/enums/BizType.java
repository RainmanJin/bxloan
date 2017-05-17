package com.coamctech.bxloan.commons.enums;

import com.coamctech.bxloan.commons.entity.BaseEnum;

/**   
 * 类名称：BizType
 * 类描述 ：业务类型 
 * 创建人: wangyawei 
 * 创建时间：2015年7月7日 下午4:29:22  
 * 修改人：
 * 修改时间：  
 * 修改备注：
 * 版本： V1.0
 */
public enum BizType implements BaseEnum {
	Biz("普通业务"), 		
	CreditBiz("授信业务"), 	
	BatchBiz("批量业务"); 	
    private final String key;

    BizType(String key) {
        this.key = key;
    }
    
	@Override
	public String getKey() {
		return key;
	}
}
