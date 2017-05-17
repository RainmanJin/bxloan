package com.coamctech.bxloan.service.exceptions;
/**   
 * 类名称：NoAuthorityException
 * 类描述 ：没有查选权限异常类
 * 创建人: gph 
 * 创建时间：2015年5月22日 下午4:36:52  
 * 修改人：gph
 * 修改时间：2015年5月22日 下午4:36:52  
 * 修改备注：
 * 版本： V1.0
 */
public class NoAuthorityException extends RuntimeException {

	private static final long serialVersionUID = 100057525313878724L;
	public NoAuthorityException() {
		super();
	}
	
	public NoAuthorityException(String messages, Throwable cause){
		super(messages,cause);
	}
	
	public NoAuthorityException(String messages){
		super(messages);
	}
	
	public NoAuthorityException(Throwable cause){
		super(cause);
	}
}
