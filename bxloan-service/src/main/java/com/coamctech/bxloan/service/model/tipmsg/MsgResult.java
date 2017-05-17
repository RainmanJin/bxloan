package com.coamctech.bxloan.service.model.tipmsg;

public class MsgResult {
	private String code;
	private String desc;
	
	private MsgResult() {
		super();
	}
	private MsgResult(String code, String desc) {
		super();
		this.code=code;
		this.desc=desc;
	}
	public static MsgResult getMsgResult(String code, String desc) {
		return new MsgResult(code, desc);
	}
	public static MsgResult getMsgResult(ResultEnums re) {
		return new MsgResult(re.getCode(), re.getDesc());
	}
	public boolean equalsMsgResult(ResultEnums re){
		return re.eqCode(this.code);
	}
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
}
