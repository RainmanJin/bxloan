package com.coamctech.bxloan.service.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class NationAreaVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2086326758414391505L;
	private String name;
	private String code;
	
	public NationAreaVo(Object[] input) {
		super();
		this.code = str(input[0]);
		this.name = str(input[1]);
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	private String str(Object obj){
		return obj!=null?obj.toString():StringUtils.EMPTY;
	}
}
