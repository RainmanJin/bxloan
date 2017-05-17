package com.coamctech.bxloan.service.utils;

/**
 * @author AcoreHeng
 * 逾期现金流
 *
 */
public enum EcfiObjTypeEnum {
	/**
	 * 种植业
	 */
	CULTIVATE_AGRICULTURE("01","种植业"),
	
	/**
	 * 养殖业
	 */
	BREED_AGRICULTURE("02","养殖业"),
	/**
	 * 工商业
	 */
	INDUSTRY_BIZ("03","工商业"),
	/**
	 * 其他
	 */
	OTHER("04","其他"),
	/**
	 * 家庭生活消费
	 */
	FAMILY_CONSUME("05","家庭生活消费");
	
	private EcfiObjTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	
}
