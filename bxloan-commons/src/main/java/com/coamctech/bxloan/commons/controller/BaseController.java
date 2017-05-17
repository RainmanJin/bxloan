package com.coamctech.bxloan.commons.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coamctech.bxloan.commons.result.Result;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String WS_FAIL = "远程服务连接超时或远程服务不可用";
	
	/**
	 * 只返回成功的布尔值
	 * @return
	 */
	protected Result success() {
		return new Result(true);
	}
	
	/**
	 * 返回成功的布尔值和数据
	 * @param data
	 * @return
	 */
	protected Result success(Object data) {
		return new Result(true, data);
	}
	
	/**
	 * 返回成功的布尔值、消息和数据
	 * @param data
	 * @return
	 */
	protected Result success(String msg, Object data) {
		return new Result(true, msg, data);
	}
	
	/**
	 * 返回成功的布尔值、消息
	 * @param data
	 * @return
	 */
	protected Result success(String msg) {
		return new Result(true, msg, null);
	}
	
	/**
	 * 只返回失败的布尔值
	 * @return
	 */
	protected Result failure() {
		return new Result(false);
	}
	
	/**
	 * 返回失败的布尔值和数据
	 * @param data
	 * @return
	 */
	protected Result failure(Object data) {
		return new Result(false, data);
	}
	
	/**
	 * 返回失败的布尔值、消息和数据
	 * @param data
	 * @return
	 */
	protected Result failure(String msg, Object data) {
		return new Result(false, msg, data);
	}
	/**
	 * 返回失败的布尔值、消息
	 * @param data
	 * @return
	 */
	protected Result failure(String msg) {
		return new Result(false, msg, null);
	}
	/**
	 * WebServices调用失败
	 * @return
	 */
	protected Result failureWs() {
		return new Result(false, WS_FAIL, null);
	}
}
