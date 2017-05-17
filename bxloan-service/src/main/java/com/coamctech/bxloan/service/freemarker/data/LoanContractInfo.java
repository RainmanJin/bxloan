package com.coamctech.bxloan.service.freemarker.data;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 *	借款合同
 *	@author Acore
 */
public class LoanContractInfo {
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
	 * 贷款产品名称
	 */
	private String loanProductName;
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
	//TODO 合同组成
	private ContractForm contractForm;
	/**
	 * 提前还款时，应提前{noticeDrDayOfPrepayment}日通知贷款人，
	 */
	private int noticeDrDayOfPrepayment;
	/**
	 * 计收补偿费
	 */
	private String scaleOfSeverance;
	//TODO 承诺与保证
	/**
	 * 条目12
	 */
	private String item12;
	//TODO 违约责任
	/**
	 * 贷款用途改变时的罚息率
	 */
	private String peIntRateOfUseChange;
	
	/**
	 * 逾期罚息率
	 */
	private String peIntRateOfOverdue;
	/**
	 * 清收费用
	 */
	private String clearFee;
	
	/**
	 * 违约率
	 */
	private String breakContRate;
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
	public String getLoanProductName() {
		return loanProductName;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanTypeAndPurpose(String loanProductName,String loanPurpose) {
		this.loanPurpose = loanPurpose;
		this.loanProductName=loanProductName;
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
	public ContractForm getContractForm() {
		return contractForm;
	}
	public void setContractForm(ContractForm contractForm) {
		this.contractForm = contractForm;
	}
	public int getNoticeDrDayOfPrepayment() {
		return noticeDrDayOfPrepayment;
	}
	public void setNoticeDrDayOfPrepayment(int noticeDrDayOfPrepayment) {
		this.noticeDrDayOfPrepayment = noticeDrDayOfPrepayment;
	}
	public String getScaleOfSeverance() {
		return scaleOfSeverance;
	}
	public void setScaleOfSeverance(String scaleOfSeverance) {
		this.scaleOfSeverance = scaleOfSeverance;
	}
	public String getItem12() {
		return item12;
	}
	public void setItem12(String item12) {
		this.item12 = item12;
	}
	public String getPeIntRateOfUseChange() {
		return peIntRateOfUseChange;
	}
	public void setPeIntRateOfUseChange(String peIntRateOfUseChange) {
		this.peIntRateOfUseChange = peIntRateOfUseChange;
	}
	public String getPeIntRateOfOverdue() {
		return peIntRateOfOverdue;
	}
	public void setPeIntRateOfOverdue(String peIntRateOfOverdue) {
		this.peIntRateOfOverdue = peIntRateOfOverdue;
	}
	public String getClearFee() {
		return clearFee;
	}
	public void setClearFee(String clearFee) {
		this.clearFee = clearFee;
	}
	public String getBreakContRate() {
		return breakContRate;
	}
	public void setBreakContRate(String breakContRate) {
		this.breakContRate = breakContRate;
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
		 * 
		 */
		private String certificateType;
		/**
		 * 证件号
		 */
		private String certificateNum;
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
		
		 
		public DebitInfo(String name,String certificateType, String certificateNum,
				String businessLicenceNum, String legalRepresentative,
				String address, String contactAddress, String phoneNum) {
			super();
			this.name = name;
			this.certificateType=certificateType;
			this.certificateNum = certificateNum;
			this.businessLicenceNum = businessLicenceNum;
			this.legalRepresentative = legalRepresentative;
			this.address = address;
			this.contactAddress = contactAddress;
			this.phoneNum = phoneNum;
		}
		public String getName() {
			return name;
		}
		public String getCertificateType() {
			return certificateType;
		}
		public String getAddress() {
			return address;
		}
		public String getCertificateNum() {
			return certificateNum;
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
	 * 合同组成
	 *
	 */
	public class ContractForm{
		private String copyTotal;
		/**
		 * 借方
		 */
		private String copyOfDebit;
		private String copyOfCredit;
		private String copyOfGuarantee;
		
		/**
		 * 抵押登记机构
		 */
		private String copyOfMortgageRegOrg;
		private String item2;
		private String item3;
		private String item4;
		public ContractForm(String copyTotal, String copyOfDebit, String copyOfCredit,
				String copyOfGuarantee, String copyOfMortgageRegOrg) {
			super();
			this.copyTotal = copyTotal;
			this.copyOfDebit = copyOfDebit;
			this.copyOfCredit = copyOfCredit;
			this.copyOfGuarantee = copyOfGuarantee;
			this.copyOfMortgageRegOrg = copyOfMortgageRegOrg;
		}
		public void addItem(String item2,String item3,String item4){
			this.item2=item2;
			this.item3=item3;
			this.item4=item4;
		}
		public String getCopyTotal() {
			return copyTotal;
		}
		public String getCopyOfDebit() {
			return copyOfDebit;
		}
		public String getCopyOfCredit() {
			return copyOfCredit;
		}
		public String getCopyOfGuarantee() {
			return copyOfGuarantee;
		}
		public String getCopyOfMortgageRegOrg() {
			return copyOfMortgageRegOrg;
		}
		public String getItem2() {
			return item2;
		}
		public String getItem3() {
			return item3;
		}
		public String getItem4() {
			return item4;
		}
		
	}
	public String getContractCompanyName() {
		return contractCompanyName;
	}
	public void setContractCompanyName(String contractCompanyName) {
		this.contractCompanyName = contractCompanyName;
	}
}
