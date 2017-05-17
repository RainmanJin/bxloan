package com.coamctech.bxloan.service.freemarker.data;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 *	借款合同
 *	@author Acore
 */
public class LoanCorpContractInfo {
	/**
	 * 合同编号
	 */
	private String contractNum;
	/**
	 * 借方信息
	 */
	private DebitInfo debitInfo;
	/**
	 * 贷方信息
	 */
	private CreditInfo creditInfo;
	/**
	 * 担保信息
	 * */
	private GuarantorInfo guarantorInfo;
	/**
	 * 贷款用途
	 */
	private String loanPurpose;
	//TODO 金额
	/**
	 * 贷款金额大写
	 */
	private String loanAmtCapital;
	/**
	 * 贷款金额小写
	 */
	private String loanAmtLower;
	//TODO 期限
	private ContractTerm contractTerm;
	
	/**
	 * 贷款利率
	 */
	private IntRate intRate;
	/**
	 * 还款方式
	 */
	private RepaymentMode repaymentMode;
	
	
	/**
	 * 提前还款时，应提前{noticeDrDayOfPrepayment}日通知贷款人，
	 */
	private String noticeDrDayOfPrepayment;
	/**
	 * 借款到期日前几天告知
	 * */
	private String noticeDrDayOfExtendEndTime;
	/**
	 * 个人危机提前几天告知
	 * */
	private String noticeDrDayOfDangerous;
	/**
	 * 贷款逾期罚息利率
	 * */
	private String loanOverduePunitiveInterestRate;
	/**
	 * 贷款挪用罚息利率
	 * */
	private String loanFundsPunitiveInterestRate;
	/**
	 * 合同一式几份
	 * */
	private String copyTotal;
	/**
	 * 违约利息百分比
	 * */
	private String scaleOfSeverance;
	
	/**
	 * 小贷公司机构名
	 * */
	private String contractCompanyName;
	
	
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public DebitInfo getDebitInfo() {
		return debitInfo;
	}
	public void setDebitInfo(DebitInfo debitInfo) {
		this.debitInfo = debitInfo;
	}
	public CreditInfo getCreditInfo() {
		return creditInfo;
	}
	public void setCreditInfo(CreditInfo creditInfo) {
		this.creditInfo = creditInfo;
	}
	
	public String getLoanPurpose() {
		return loanPurpose;
	}
	
	public String getLoanAmtCapital() {
		return loanAmtCapital;
	}
	public void setLoanAmtCapital(String loanAmtCapital) {
		this.loanAmtCapital = loanAmtCapital;
	}
	public String getLoanAmtLower() {
		return loanAmtLower;
	}
	public void setLoanAmtLower(String loanAmtLower) {
		this.loanAmtLower = loanAmtLower;
	}
	public ContractTerm getContractTerm() {
		return contractTerm;
	}
	public void setContractTerm(ContractTerm contractTerm) {
		this.contractTerm = contractTerm;
	}
	public IntRate getIntRate() {
		return intRate;
	}
	public void setIntRate(IntRate intRate) {
		this.intRate = intRate;
	}
	public RepaymentMode getRepaymentMode() {
		return repaymentMode;
	}
	public void setRepaymentMode(RepaymentMode repaymentMode) {
		this.repaymentMode = repaymentMode;
	}
	

	
	
	/**
	 *	借方
	 */
	public class DebitInfo{
		/**
		 * 借款人
		 */
		private String name;
		/**
		 * 组织机构代码
		 */
		private String deptNum;
		/**
		 * 营业执照号码
		 */
		private String businessLicenceNum;
		/**
		 * 法定代表人
		 */
		private String legalRepresentative;
		/**
		 * 住址/地址
		 */
		private String address;
		/**
		 * 通讯地址
		 */
		private String contactAddress;
		/**
		 * 电话号码/手机号
		 */
		private String phoneNum;
		
		 
		public DebitInfo(String name, String deptNum,
				String businessLicenceNum, String legalRepresentative,
				String address, String contactAddress, String phoneNum) {
			super();
			this.name = name;
			this.deptNum = deptNum;
			this.businessLicenceNum = businessLicenceNum;
			this.legalRepresentative = legalRepresentative;
			this.address = address;
			this.contactAddress = contactAddress;
			this.phoneNum = phoneNum;
		}
		public String getName() {
			return name;
		}
		
		public String getAddress() {
			return address;
		}
		public String getDeptNum() {
			return deptNum;
		}
		public String getBusinessLicenceNum() {
			return businessLicenceNum;
		}
		public String getLegalRepresentative() {
			return legalRepresentative;
		}
		public String getContactAddress() {
			return contactAddress;
		}
		public String getPhoneNum() {
			return phoneNum;
		}
		
	}
	/**
	 *	贷方
	 */
	public class CreditInfo{
		/**
		 * 贷款人
		 */
		private String name;
		/**
		 * 法定代表人
		 */
		private String legalRepresentative;
		/**
		 * 地址
		 */
		private String address;
		/**
		 * 电话号码/手机号
		 */
		private String phoneNum;
		
		public CreditInfo(String name, String legalRepresentative,
				String address, String phoneNum) {
			super();
			this.name = name;
			this.legalRepresentative = legalRepresentative;
			this.address = address;
			this.phoneNum = phoneNum;
		}
		public String getLegalRepresentative() {
			return legalRepresentative;
		}
		public String getPhoneNum() {
			return phoneNum;
		}
		public String getName() {
			return name;
		}
		public String getAddress() {
			return address;
		}
	}
	public class ContractTerm{
		private int selectResult;
		private String item1;
		private String item2;
		private String item3;
		
		public ContractTerm(int selectResult) {
			super();
			this.selectResult = selectResult;
		}
		public void addItem(String item1,String item2,String item3){
			this.item1=item1;
			this.item2=item2;
			this.item3=item3;
		}
		public void addItem(List<String> term) {
			if(!CollectionUtils.isEmpty(term)){
			this.item1 = term.get(0);
			this.item2 = term.get(1);
			this.item3 = term.get(2);
			}
		}
		public String getSelectResult() {
			String str=null;
			switch (selectResult) {
			case 1:
				str="（壹）";
				break;
			case 2:
				str="（贰）";
				break;
			case 3:
				str="（叁）";
			default:
				break;
			}
			return str;
		}
		public String getItem1() {
			return item1;
		}
		public String getItem2() {
			return item2;
		}
		public String getItem3() {
			return item3;
		}
		
	}
	//TODO 贷款利率
	/**
	 * 利率
	 */
	public class IntRate{
		/**
		 * 选择结果
		 */
		private int selectResult;
		/**
		 * 固定年利率
		 */
		private String yearRateOfFixed;
		/**
		 * 浮动年利率
		 */
		private String yearRateOfFloat;
		/**
		 * 调整方式
		 */
		private String adjustmentsOfFloat;
		
		public IntRate(int selectResult, String yearRateOfFixed,
				String yearRateOfFloat, String adjustmentsOfFloat) {
			super();
			this.selectResult = selectResult;
			this.yearRateOfFixed = yearRateOfFixed;
			this.yearRateOfFloat = yearRateOfFloat;
			this.adjustmentsOfFloat = adjustmentsOfFloat;
		}
		public IntRate(List<String> rates) {
			super();
			if(!CollectionUtils.isEmpty(rates)){
				this.selectResult = Integer.valueOf(rates.get(0));
				this.yearRateOfFixed = rates.get(1);
				this.yearRateOfFloat = rates.get(2);
				this.adjustmentsOfFloat = rates.get(3);
			}
		}
		public String getSelectResult() {
			return "（"+selectResult+"）";
		}
		public String getYearRateOfFixed() {
			return yearRateOfFixed;
		}
		public String getYearRateOfFloat() {
			return yearRateOfFloat;
		}
		public String getAdjustmentsOfFloat() {
			return adjustmentsOfFloat;
		}
		
	}
	/**
	 * 还款方式
	 *
	 */
	public class RepaymentMode{
		private int selectResult=1;
		private String item3;
		private String bankName;
		private String bankAccountName;
		private String bankAccountNum;
		public RepaymentMode(int selectResult, String bankName,
				String bankAccountName, String bankAccountNum) {
			super();
			this.selectResult = selectResult;
			this.bankName = bankName;
			this.bankAccountName = bankAccountName;
			this.bankAccountNum = bankAccountNum;
		}
		public String getSelectResult() {
			String r_str=null;
			switch (selectResult) {
			case 1:
				r_str="（1）";
				break;
			case 2:
				r_str="（2）";
				break;
			case 3:
				r_str="（3）";
				break;
			default:
				break;
			}
			return r_str;
		}
		public String getItem3() {
			return item3;
		}
		
		public void setItem3(String item3) {
			this.item3 = item3;
		}
		public String getBankName() {
			return bankName;
		}
		public String getBankAccountName() {
			return bankAccountName;
		}
		public String getBankAccountNum() {
			return bankAccountNum;
		}
	}
	
	/**
	 * 担保信息
	 * */
	public class GuarantorInfo{
		/**
		 * 担保人名字
		 * */
		private String guarantorName;
		/**
		 * 从合同编号
		 * */
		private String subcontractNum;
		
		public GuarantorInfo(String guarantorName, String subcontractNum) {
			super();
			this.guarantorName = guarantorName;
			this.subcontractNum = subcontractNum;
		}

		public String getGuarantorName() {
			return guarantorName;
		}

		public void setGuarantorName(String guarantorName) {
			this.guarantorName = guarantorName;
		}

		public String getSubcontractNum() {
			return subcontractNum;
		}

		public void setSubcontractNum(String subcontractNum) {
			this.subcontractNum = subcontractNum;
		}
		
		
	}
	
	public String getContractCompanyName() {
		return contractCompanyName;
	}
	public void setContractCompanyName(String contractCompanyName) {
		this.contractCompanyName = contractCompanyName;
	}
	public String getNoticeDrDayOfPrepayment() {
		return noticeDrDayOfPrepayment;
	}
	public void setNoticeDrDayOfPrepayment(String noticeDrDayOfPrepayment) {
		this.noticeDrDayOfPrepayment = noticeDrDayOfPrepayment;
	}
	public String getNoticeDrDayOfExtendEndTime() {
		return noticeDrDayOfExtendEndTime;
	}
	public void setNoticeDrDayOfExtendEndTime(String noticeDrDayOfExtendEndTime) {
		this.noticeDrDayOfExtendEndTime = noticeDrDayOfExtendEndTime;
	}
	public String getNoticeDrDayOfDangerous() {
		return noticeDrDayOfDangerous;
	}
	public void setNoticeDrDayOfDangerous(String noticeDrDayOfDangerous) {
		this.noticeDrDayOfDangerous = noticeDrDayOfDangerous;
	}
	public String getLoanOverduePunitiveInterestRate() {
		return loanOverduePunitiveInterestRate;
	}
	public void setLoanOverduePunitiveInterestRate(String loanOverduePunitiveInterestRate) {
		this.loanOverduePunitiveInterestRate = loanOverduePunitiveInterestRate;
	}
	public String getLoanFundsPunitiveInterestRate() {
		return loanFundsPunitiveInterestRate;
	}
	public void setLoanFundsPunitiveInterestRate(String loanFundsPunitiveInterestRate) {
		this.loanFundsPunitiveInterestRate = loanFundsPunitiveInterestRate;
	}
	public String getCopyTotal() {
		return copyTotal;
	}
	public void setCopyTotal(String copyTotal) {
		this.copyTotal = copyTotal;
	}
	public String getScaleOfSeverance() {
		return scaleOfSeverance;
	}
	public void setScaleOfSeverance(String scaleOfSeverance) {
		this.scaleOfSeverance = scaleOfSeverance;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public GuarantorInfo getGuarantorInfo() {
		return guarantorInfo;
	}
	public void setGuarantorInfo(GuarantorInfo guarantorInfo) {
		this.guarantorInfo = guarantorInfo;
	}
}
