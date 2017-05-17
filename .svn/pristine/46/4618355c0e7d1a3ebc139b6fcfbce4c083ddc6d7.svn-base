package com.coamctech.bxloan.service.model.bizapply.compute;

import java.math.BigDecimal;

import com.coamctech.bxloan.commons.entity.BaseBean;

public class ProfitLossItemVo extends BaseBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 过去
	 */
	private BigDecimal beforetimeVal;
	/**
	 * 现在
	 */
	private BigDecimal aftertimeVal;
	
	

	
	public ProfitLossItemVo() {
		super();
	}




	public ProfitLossItemVo(BigDecimal beforetimeVal, BigDecimal aftertimeVal) {
		super();
		this.beforetimeVal = beforetimeVal;
		this.aftertimeVal = aftertimeVal;
	}




	/**
	 * 对比结果
	 * @return
	 */
	public BigDecimal getContrastResult(){
		BigDecimal bd=BigDecimal.ZERO;
		if(aftertimeVal!=null){
			bd=bd.add(aftertimeVal);
		}
		if(beforetimeVal!=null){
			bd=bd.subtract(beforetimeVal);
		}
		return bd;
	}




	public BigDecimal getBeforetimeVal() {
		return beforetimeVal;
	}




	public BigDecimal getAftertimeVal() {
		return aftertimeVal;
	}




	public void setBeforetimeVal(BigDecimal beforetimeVal) {
		this.beforetimeVal = beforetimeVal;
	}




	public void setAftertimeVal(BigDecimal aftertimeVal) {
		this.aftertimeVal = aftertimeVal;
	}

}
