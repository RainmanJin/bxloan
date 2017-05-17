package com.coamctech.bxloan.service.model.tipmsg;

/**	
 *	错误信息
 *	00XXXX通用信息
 *	000200
 *	183100	账务处理-账务冲正
 */
public enum ResultEnums {
	SUCCESS("000000","成功"),
	FAILURE("000001","失败"),
	SYS_ERROR("000101","系统异常"),
	DB_ERROR("000102","数据库异常"),
	DATA_ERROR("000103","数据异常"),
	USER_NO_LOGIN("000201","用户没有登录"),
	PARAMS_ERROR("000301","参数错误"),
	
	
	/**
	 * 账务处理——账务冲正
	 */
	ACCOUNT_FLUSHES_SUCCESS("183100","冲销成功"),
	ACCOUNT_FLUSHES_NO_SELECT("183101","请选择一笔资料!"),
	ACCOUNT_FLUSHES_NO_ENTER("183102","此单据不是已入账,不能进行发起,请确认!"),
	ACCOUNT_FLUSHES_CONTRACT_NONE_OR_CX_ENTER("183103","此合同未入账或是已发冲销,不能进行发起,请确认!"),
	ACCOUNT_FLUSHES_ADVANCE_REPAY_CONTRACT("183104","此合同已发起提前还款,正在流程中,请确认!"),
	ACCOUNT_FLUSHES_ACROSS_YEAR("183105","不能跨年冲正,请确认!"),
	ACCOUNT_FLUSHES_ORDER_DESC("183106","请单据编号(倒序)依次冲销,请确认!"),
	ACCOUNT_FLUSHES_DOING_ZQ("183107","此合同已发起展期,正在流程中,请确认!"),
	ACCOUNT_FLUSHES_DOING_HX("183108","此合同已发起核销,正在流程中,请确认!"),
	ACCOUNT_FLUSHES_NO_PAY("183109","此合同已展期过,不能对放款业务进行发起,请确认!");
	private String code;
	private String desc;
	
	private ResultEnums(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public boolean eqCode(String code){
		return this.getCode().equals(code);
	}
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
}
