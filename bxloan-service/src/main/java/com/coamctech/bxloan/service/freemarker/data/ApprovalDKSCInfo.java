package com.coamctech.bxloan.service.freemarker.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.service.model.collateral.CollateralVO;

/**
 * 贷款审查 贷款审查表
 * */
public class ApprovalDKSCInfo {
	/**申报信息*/
	private ApplyInfo ai;
	/**业务基本信息*/
	private BizBaseInfo bbi;
	/**贷款建议*/
	private LoanSuggestInfo lsi;
	
	private ApproveSuggestInfo asi;
	
	/**审查人*/
	private String applyUserName;
	/**日期*/
	private String approveDate;
	/**建议意见*/
	private String suggest;
	
	/**申报信息*/
	public class ApplyInfo{
		/**申请机构*/
		String applyOrgName;
		/**申请日期*/
		String applyDate;
		/**申报团队*/
		String applyTeam;
		/**经办人主办*/
		String applyHost;
		/**经办人协办*/
		String applyGest;
		public String getApplyOrgName() {
			return applyOrgName;
		}
		public void setApplyOrgName(String applyOrgName) {
			this.applyOrgName = applyOrgName;
		}
		public String getApplyDate() {
			return applyDate;
		}
		public void setApplyDate(String applyDate) {
			this.applyDate = applyDate;
		}
		public String getApplyTeam() {
			return applyTeam;
		}
		public void setApplyTeam(String applyTeam) {
			this.applyTeam = applyTeam;
		}
		public String getApplyHost() {
			return applyHost;
		}
		public void setApplyHost(String applyHost) {
			this.applyHost = applyHost;
		}
		public String getApplyGest() {
			return applyGest;
		}
		public void setApplyGest(String applyGest) {
			this.applyGest = applyGest;
		}
		public ApplyInfo(String applyOrgName, String applyDate,
				String applyTeam, String applyHost, String applyGest) {
			super();
			this.applyOrgName = applyOrgName;
			this.applyDate = applyDate;
			this.applyTeam = applyTeam;
			this.applyHost = applyHost;
			this.applyGest = applyGest;
		}
		public ApplyInfo() {
			super();
		}
		
	}
	
	
	
	/**业务基本信息*/
	public class BizBaseInfo{
		/**借款人名称*/
		String customerName;
		/**借款人证件号码*/
		String certificateNum;
		/**共同借款人名称*/
		String pawnLoanName;
		/**共同借款人证件号码*/
		String pawnCertificateNum;
		/**贷款用途*/
		String loanUse;
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getCertificateNum() {
			return certificateNum;
		}
		public void setCertificateNum(String certificateNum) {
			this.certificateNum = certificateNum;
		}
		public String getPawnLoanName() {
			return pawnLoanName;
		}
		public void setPawnLoanName(String pawnLoanName) {
			this.pawnLoanName = pawnLoanName;
		}
		public String getPawnCertificateNum() {
			return pawnCertificateNum;
		}
		public void setPawnCertificateNum(String pawnCertificateNum) {
			this.pawnCertificateNum = pawnCertificateNum;
		}
		public String getLoanUse() {
			return loanUse;
		}
		public void setLoanUse(String loanUse) {
			this.loanUse = loanUse;
		}
		public BizBaseInfo(String customerName, String certificateNum,
				String pawnLoanName, String pawnCertificateNum, String loanUse) {
			super();
			this.customerName = customerName;
			this.certificateNum = certificateNum;
			this.pawnLoanName = pawnLoanName;
			this.pawnCertificateNum = pawnCertificateNum;
			this.loanUse = loanUse;
		}
		public BizBaseInfo() {
			super();
		}
		
	}
	
	/**贷款建议*/
	public class LoanSuggestInfo{
		/**贷款金额*/
		String loanAmt;
		/**贷款期限*/
		String loanTerm;
		/**利率*/
		String loanRate;
		/**还款方式*/
		String repayMode;
		/**担保人*/
		List<ProjectAssurerInfo> guaranteerList;
		/**抵质押列表*/
		List<CollateralVO> pawnInfoList;
		/**担保方式*/
		String item1 ="□";
		String item2 ="□";
		String item3 ="□";
		String item4 ="□";
		
		public void setItems(String[] guranteeTypes){
			for (String str : guranteeTypes) {
				if(StringUtils.isNotBlank(str)){
					switch (Integer.valueOf(str)) {
					case 1:{this.item1 = "☑";break;}
					case 2:{this.item2 = "☑";break;}
					case 3:{this.item3 = "☑";break;}
					case 4:{this.item4 = "☑";break;}
					default:
						break;
					}
				}
			}
		}
		
		public String getLoanAmt() {
			return loanAmt;
		}
		public void setLoanAmt(String loanAmt) {
			this.loanAmt = loanAmt;
		}
		public String getLoanTerm() {
			return loanTerm;
		}
		public void setLoanTerm(String loanTerm) {
			this.loanTerm = loanTerm;
		}
		public String getLoanRate() {
			return loanRate;
		}
		public void setLoanRate(String loanRate) {
			this.loanRate = loanRate;
		}
		public String getRepayMode() {
			return repayMode;
		}
		public void setRepayMode(String repayMode) {
			this.repayMode = repayMode;
		}
		public LoanSuggestInfo(String loanAmt, String loanTerm,
				String loanRate, String repayMode) {
			super();
			this.loanAmt = loanAmt;
			this.loanTerm = loanTerm;
			this.loanRate = loanRate;
			this.repayMode = repayMode;
		}
		public LoanSuggestInfo() {
			super();
		}

		public String getItem1() {
			return item1;
		}

		public void setItem1(String item1) {
			this.item1 = item1;
		}

		public String getItem2() {
			return item2;
		}

		public void setItem2(String item2) {
			this.item2 = item2;
		}

		public String getItem3() {
			return item3;
		}

		public void setItem3(String item3) {
			this.item3 = item3;
		}

		public String getItem4() {
			return item4;
		}

		public void setItem4(String item4) {
			this.item4 = item4;
		}

		public List<ProjectAssurerInfo> getGuaranteerList() {
			return guaranteerList;
		}

		public void setGuaranteerList(List<ProjectAssurerInfo> guaranteerList) {
			this.guaranteerList = guaranteerList;
		}

		public List<CollateralVO> getPawnInfoList() {
			return pawnInfoList;
		}

		public void setPawnInfoList(List<CollateralVO> pawnInfoList) {
			this.pawnInfoList = pawnInfoList;
		}
		
	}
	
	/**审查意见*/
	public class ApproveSuggestInfo{
		/**批复金额*/
		String approveAmt;
		/**批复利率*/
		String approveRate;
		/**批复期限*/
		String approveTerm;
		/**还款方式*/
		String approveRepayMode;
		/**担保方式*/
		String gurranteMode;
		public String getApproveAmt() {
			return approveAmt;
		}
		public void setApproveAmt(String approveAmt) {
			this.approveAmt = approveAmt;
		}
		public String getApproveRate() {
			return approveRate;
		}
		public void setApproveRate(String approveRate) {
			this.approveRate = approveRate;
		}
		public String getApproveTerm() {
			return approveTerm;
		}
		public void setApproveTerm(String approveTerm) {
			this.approveTerm = approveTerm;
		}
		public String getApproveRepayMode() {
			return approveRepayMode;
		}
		public void setApproveRepayMode(String approveRepayMode) {
			this.approveRepayMode = approveRepayMode;
		}
		public String getGurranteMode() {
			return gurranteMode;
		}
		public void setGurranteMode(String gurranteMode) {
			this.gurranteMode = gurranteMode;
		}
		public ApproveSuggestInfo(String approveAmt, String approveRate,
				String approveTerm, String approveRepayMode, String gurranteMode) {
			super();
			this.approveAmt = approveAmt;
			this.approveRate = approveRate;
			this.approveTerm = approveTerm;
			this.approveRepayMode = approveRepayMode;
			this.gurranteMode = gurranteMode;
		}
		public ApproveSuggestInfo() {
			super();
		}
		
		
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

	public ApprovalDKSCInfo(ApplyInfo ai, BizBaseInfo bbi, LoanSuggestInfo lsi,
			String applyUserName, String approveDate, String suggest) {
		super();
		this.ai = ai;
		this.bbi = bbi;
		this.lsi = lsi;
		this.applyUserName = applyUserName;
		this.approveDate = approveDate;
		this.suggest = suggest;
	}

	public ApprovalDKSCInfo() {
		super();
	}

	public ApproveSuggestInfo getAsi() {
		return asi;
	}

	public void setAsi(ApproveSuggestInfo asi) {
		this.asi = asi;
	}
	
	
}
