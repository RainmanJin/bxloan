package com.coamctech.bxloan.service.freemarker.data;

import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo.ApplyInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo.BizBaseInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo.LoanSuggestInfo;

public class ApprovalCSInfo {
	/**审查人*/
	private String applyUserName;
	/**日期*/
	private String approveDate;
	/**建议意见*/
	private String suggest;
	
	/**申报信息*/
	private ApplyInfo ai;
	/**业务基本信息*/
	private BizBaseInfo bbi;
	/**贷款建议*/
	private LoanSuggestInfo lsi;
	
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public ApplyInfo getAi() {
		return ai;
	}
	public void setAi(ApplyInfo ai) {
		this.ai = ai;
	}
	public BizBaseInfo getBbi() {
		return bbi;
	}
	public void setBbi(BizBaseInfo bbi) {
		this.bbi = bbi;
	}
	public LoanSuggestInfo getLsi() {
		return lsi;
	}
	public void setLsi(LoanSuggestInfo lsi) {
		this.lsi = lsi;
	}
	public ApprovalCSInfo(String applyUserName, String approveDate,
			String suggest, ApplyInfo ai, BizBaseInfo bbi, LoanSuggestInfo lsi) {
		super();
		this.applyUserName = applyUserName;
		this.approveDate = approveDate;
		this.suggest = suggest;
		this.ai = ai;
		this.bbi = bbi;
		this.lsi = lsi;
	}
	public ApprovalCSInfo() {
		super();
	}
	
	
}
