package com.coamctech.bxloan.service.model.accountingmng;

import java.io.Serializable;

import com.coamctech.bxloan.entity.TallyCertificate;
import com.coamctech.bxloan.entity.TallyCertificateDetail;

/**
 *	账务冲正VO
 */
public class AccountingFlushesVo implements Serializable {
	
	private static AccountingFlushesVo afVo=new AccountingFlushesVo();
	/**
	 * 
	 */
	private static final long serialVersionUID = 2301309942560251439L;
	public Params init(){
		return afVo.new Params();
	}
	public static Params  newParams(){
		return afVo.new Params();
	}
	public static BillDetailVo  newBillDetailVo(){
		return afVo.new BillDetailVo();
	}
	public static BillAccountVo  newBillAccountVo(TallyCertificateDetail tcd){
		return afVo.new BillAccountVo(tcd);
	}
	
	/**
	 *	查询参数
	 */
	public class Params{
		/**
		 * 当前用户id
		 */
		private Long curUserId;
		private String custCd;
		private String contCd;
		private String busiTypCd;
		private String billState;
		private String customerName;
		
		public String getBillState() {
			return billState;
		}
		public void setBillState(String billState) {
			this.billState = billState;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public Long getCurUserId() {
			return curUserId;
		}
		public void setCurUserId(Long curUserId) {
			this.curUserId = curUserId;
		}
		public String getCustCd() {
			return custCd;
		}
		public void setCustCd(String custCd) {
			this.custCd = custCd;
		}
		public String getContCd() {
			return contCd;
		}
		public void setContCd(String contCd) {
			this.contCd = contCd;
		}
		public String getBusiTypCd() {
			return busiTypCd;
		}
		public void setBusiTypCd(String busiTypCd) {
			this.busiTypCd = busiTypCd;
		}
	}
	
	/**
	 *	单据详细
	 */
	public class BillDetailVo{
		private TallyCertificate tc;
		private String name;
		
		public TallyCertificate getTc() {
			return tc;
		}
		public void setTc(TallyCertificate tc) {
			this.tc = tc;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	/**
	 *	单据账务信息
	 */
	public class BillAccountVo{
		TallyCertificateDetail tcd;
		public BillAccountVo(TallyCertificateDetail tcd) {
			super();
			this.tcd=tcd;
		}
		public TallyCertificateDetail getTcd() {
			return tcd;
		}
		
	}

}
