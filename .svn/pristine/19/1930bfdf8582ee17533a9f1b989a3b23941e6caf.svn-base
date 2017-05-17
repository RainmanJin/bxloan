package com.coamctech.bxloan.service.pettyloan.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *	账务金额
 */
public class DoVchAmtBo {
	
	private Long partyId;//参与人ID
	private Long contractId;// 合同ID
	private Long projectId;// 业务ID
	private Long loanId;//放款ID
	private Date vchdate;//账务处理时间
	
	//放款时候提供
	private BigDecimal loanAmt = BigDecimal.ZERO; // 贷款
	private BigDecimal chargeAmt = BigDecimal.ZERO; // 手续费
	//放款时候提供
	
	// 还款的时候提供
	private BigDecimal bcPaidCurrPrincipal = BigDecimal.ZERO; // 本次还当期本金
	private BigDecimal bcPaidCurrInterest = BigDecimal.ZERO; // 本次还当期利息
	private BigDecimal setIntCurrInterest = BigDecimal.ZERO;//本次还结记利息
	// 还款的时候提供
	private BigDecimal setIntImposeInterest = BigDecimal.ZERO; //本次还90天内结记逾期利息（结记罚息）
	private BigDecimal setIntOverThan90ImposeInterest = BigDecimal.ZERO; //本次还90天以上结记逾期利息（结记罚息）
	
	private BigDecimal bcPaidOverPrincipal = BigDecimal.ZERO; // 本次还拖欠本金
	private BigDecimal bcPaidOverInterest = BigDecimal.ZERO; // 本次还拖欠利息
	private BigDecimal bcPaidImposeInterest = BigDecimal.ZERO; // 本次还逾期利息（罚息）
	private BigDecimal bcPaidOverThan90Principal = BigDecimal.ZERO; // 本次还拖欠90天以上本金
	private BigDecimal bcPaidOverThan90Interest = BigDecimal.ZERO; // 本次还拖欠90天以上利息
	private BigDecimal bcPaidOverThan90ImposeInterest = BigDecimal.ZERO; // 本次还90天以上逾期利息（罚息）
	//提前还款时候提供
	private BigDecimal bcDedit = BigDecimal.ZERO;//本次违约金收入
	//提前还款时候提供
	
	//逾期90天内转形态时候提供
	private BigDecimal bcIn90Interest = BigDecimal.ZERO;//逾期90天内利息+罚息youjg add
	private BigDecimal bcOverPrincipal = BigDecimal.ZERO;//本次转逾期贷款本金金额
	private BigDecimal bcOverInterest = BigDecimal.ZERO;//本次转逾期贷款利息金额
	
	//逾期90天内转形态时候提供
	
	//逾期90天以上转形态时候提供
	private BigDecimal bcOverThan90Principal = BigDecimal.ZERO;//本次转逾期90天以上贷款本金金额
	private BigDecimal bcOverThan90CurrInterest = BigDecimal.ZERO;//冲未还正常利息
	private BigDecimal bcOverThan90OverInterest = BigDecimal.ZERO;//冲未还逾期90天内利息
	private BigDecimal bcOverThan90Interest = BigDecimal.ZERO;//冲未还逾期90天以上利息youjg add
	//逾期90天以上转形态时候提供
	
	//还款已减值
	private BigDecimal currentAmt = BigDecimal.ZERO;
	private BigDecimal bcOverCurrentAmt = BigDecimal.ZERO;
	private BigDecimal bcOverThan90CurrentAmt = BigDecimal.ZERO;
	//还款已减值
	
	//转逾期已减值
	private BigDecimal bcOverLoanAmt = BigDecimal.ZERO;
	private BigDecimal bcOverThan90LoanAmt = BigDecimal.ZERO;
	private boolean isChangeOverFlag;
	//转逾期已减值
	
	//单项准备金
	private BigDecimal lossAmt = BigDecimal.ZERO;//贷款损失准备金
	private BigDecimal lossAmtBack = BigDecimal.ZERO;//贷款损失准备金转回
	private BigDecimal bcOverLossAmt = BigDecimal.ZERO;//逾期90天内贷款损失准备金
	private BigDecimal bcOverLossAmtBack = BigDecimal.ZERO;//逾期90天内贷款损失准备金转回
	private BigDecimal bcOverThan90LossAmt = BigDecimal.ZERO;//逾期90天以上贷款损失准备金
	private BigDecimal bcOverThan90LossAmtBack = BigDecimal.ZERO;//逾期90天以上贷款损失准备金转回
	//单项准备金
	
	//专项准备金
	private BigDecimal specialLossAmt = BigDecimal.ZERO;//贷款专项准备金
	private BigDecimal specialLossAmtBack = BigDecimal.ZERO;//贷款专项准备金转回
	//专项准备金
	
	//核销
	private BigDecimal businessAmt = BigDecimal.ZERO;//营业外收入
	//核销
	
	//还款--已减值传未减值
	private BigDecimal bcNoLossAmt = BigDecimal.ZERO;//已减值转未减值(正常贷款余额)
	private BigDecimal bcNoLossInterest = BigDecimal.ZERO;//已减值转未减值(正常剩余利息)
	private BigDecimal bcOverThanNoLossAmt = BigDecimal.ZERO;//已减值转未减值(逾期90天内贷款余额)
	private BigDecimal bcOverThanNoLossInterest = BigDecimal.ZERO;//已减值转未减值(拖欠利息与罚息)
	private BigDecimal bcOverThan90NoLossAmt = BigDecimal.ZERO;//已减值转未减值(逾期90天以上贷款余额)
	private BigDecimal bcOverThan90NoLossInterest = BigDecimal.ZERO;//已减值转未减值(拖欠利息与罚息)
	//还款--已减值传未减值
	
	// 多收金额
	private BigDecimal bcBankManyPayedAmt = BigDecimal.ZERO; // 多收金额(银行转账)
	// 多收金额
	
	// 费用类型
	private String bcCostType = "1"; //(默认:银行转账)
	// 费用类型
	
	// 费用登记
	private BigDecimal bcCostAmt = BigDecimal.ZERO; // 正常手续费
	
	private BigDecimal bcOverCostAmt = BigDecimal.ZERO;// 逾期90天内手续费
	
	private BigDecimal bcOverThan90CostAmt = BigDecimal.ZERO; // 逾期90天以上手续费
	
	private BigDecimal setAttronInterest = BigDecimal.ZERO; // 转出结计利息
	// 费用登记
	
	// 预收
	private BigDecimal setIntReceCurrInt = BigDecimal.ZERO; // 预收利息
	
	private BigDecimal setIntReceCurrAmt = BigDecimal.ZERO; // 预收本金
	
	// 预收
	private BigDecimal setReceCurrInt = BigDecimal.ZERO; // 预收利息
	
	private BigDecimal setReceCurrAmt = BigDecimal.ZERO; // 预收本金

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Date getVchdate() {
		return vchdate;
	}

	public void setVchdate(Date vchdate) {
		this.vchdate = vchdate;
	}

	public BigDecimal getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	public BigDecimal getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(BigDecimal chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public BigDecimal getBcPaidCurrPrincipal() {
		return bcPaidCurrPrincipal;
	}

	public void setBcPaidCurrPrincipal(BigDecimal bcPaidCurrPrincipal) {
		this.bcPaidCurrPrincipal = bcPaidCurrPrincipal;
	}

	public BigDecimal getBcPaidCurrInterest() {
		return bcPaidCurrInterest;
	}

	public void setBcPaidCurrInterest(BigDecimal bcPaidCurrInterest) {
		this.bcPaidCurrInterest = bcPaidCurrInterest;
	}

	public BigDecimal getSetIntCurrInterest() {
		return setIntCurrInterest;
	}

	public void setSetIntCurrInterest(BigDecimal setIntCurrInterest) {
		this.setIntCurrInterest = setIntCurrInterest;
	}

	public BigDecimal getSetIntImposeInterest() {
		return setIntImposeInterest;
	}

	public void setSetIntImposeInterest(BigDecimal setIntImposeInterest) {
		this.setIntImposeInterest = setIntImposeInterest;
	}

	public BigDecimal getSetIntOverThan90ImposeInterest() {
		return setIntOverThan90ImposeInterest;
	}

	public void setSetIntOverThan90ImposeInterest(
			BigDecimal setIntOverThan90ImposeInterest) {
		this.setIntOverThan90ImposeInterest = setIntOverThan90ImposeInterest;
	}

	public BigDecimal getBcPaidOverPrincipal() {
		return bcPaidOverPrincipal;
	}

	public void setBcPaidOverPrincipal(BigDecimal bcPaidOverPrincipal) {
		this.bcPaidOverPrincipal = bcPaidOverPrincipal;
	}

	public BigDecimal getBcPaidOverInterest() {
		return bcPaidOverInterest;
	}

	public void setBcPaidOverInterest(BigDecimal bcPaidOverInterest) {
		this.bcPaidOverInterest = bcPaidOverInterest;
	}

	public BigDecimal getBcPaidImposeInterest() {
		return bcPaidImposeInterest;
	}

	public void setBcPaidImposeInterest(BigDecimal bcPaidImposeInterest) {
		this.bcPaidImposeInterest = bcPaidImposeInterest;
	}

	public BigDecimal getBcPaidOverThan90Principal() {
		return bcPaidOverThan90Principal;
	}

	public void setBcPaidOverThan90Principal(BigDecimal bcPaidOverThan90Principal) {
		this.bcPaidOverThan90Principal = bcPaidOverThan90Principal;
	}

	public BigDecimal getBcPaidOverThan90Interest() {
		return bcPaidOverThan90Interest;
	}

	public void setBcPaidOverThan90Interest(BigDecimal bcPaidOverThan90Interest) {
		this.bcPaidOverThan90Interest = bcPaidOverThan90Interest;
	}

	public BigDecimal getBcPaidOverThan90ImposeInterest() {
		return bcPaidOverThan90ImposeInterest;
	}

	public void setBcPaidOverThan90ImposeInterest(
			BigDecimal bcPaidOverThan90ImposeInterest) {
		this.bcPaidOverThan90ImposeInterest = bcPaidOverThan90ImposeInterest;
	}

	public BigDecimal getBcDedit() {
		return bcDedit;
	}

	public void setBcDedit(BigDecimal bcDedit) {
		this.bcDedit = bcDedit;
	}

	public BigDecimal getBcIn90Interest() {
		return bcIn90Interest;
	}

	public void setBcIn90Interest(BigDecimal bcIn90Interest) {
		this.bcIn90Interest = bcIn90Interest;
	}

	public BigDecimal getBcOverPrincipal() {
		return bcOverPrincipal;
	}

	public void setBcOverPrincipal(BigDecimal bcOverPrincipal) {
		this.bcOverPrincipal = bcOverPrincipal;
	}

	public BigDecimal getBcOverInterest() {
		return bcOverInterest;
	}

	public void setBcOverInterest(BigDecimal bcOverInterest) {
		this.bcOverInterest = bcOverInterest;
	}

	public BigDecimal getBcOverThan90Principal() {
		return bcOverThan90Principal;
	}

	public void setBcOverThan90Principal(BigDecimal bcOverThan90Principal) {
		this.bcOverThan90Principal = bcOverThan90Principal;
	}

	public BigDecimal getBcOverThan90CurrInterest() {
		return bcOverThan90CurrInterest;
	}

	public void setBcOverThan90CurrInterest(BigDecimal bcOverThan90CurrInterest) {
		this.bcOverThan90CurrInterest = bcOverThan90CurrInterest;
	}

	public BigDecimal getBcOverThan90OverInterest() {
		return bcOverThan90OverInterest;
	}

	public void setBcOverThan90OverInterest(BigDecimal bcOverThan90OverInterest) {
		this.bcOverThan90OverInterest = bcOverThan90OverInterest;
	}

	public BigDecimal getBcOverThan90Interest() {
		return bcOverThan90Interest;
	}

	public void setBcOverThan90Interest(BigDecimal bcOverThan90Interest) {
		this.bcOverThan90Interest = bcOverThan90Interest;
	}

	public BigDecimal getCurrentAmt() {
		return currentAmt;
	}

	public void setCurrentAmt(BigDecimal currentAmt) {
		this.currentAmt = currentAmt;
	}

	public BigDecimal getBcOverCurrentAmt() {
		return bcOverCurrentAmt;
	}

	public void setBcOverCurrentAmt(BigDecimal bcOverCurrentAmt) {
		this.bcOverCurrentAmt = bcOverCurrentAmt;
	}

	public BigDecimal getBcOverThan90CurrentAmt() {
		return bcOverThan90CurrentAmt;
	}

	public void setBcOverThan90CurrentAmt(BigDecimal bcOverThan90CurrentAmt) {
		this.bcOverThan90CurrentAmt = bcOverThan90CurrentAmt;
	}

	public BigDecimal getBcOverLoanAmt() {
		return bcOverLoanAmt;
	}

	public void setBcOverLoanAmt(BigDecimal bcOverLoanAmt) {
		this.bcOverLoanAmt = bcOverLoanAmt;
	}

	public BigDecimal getBcOverThan90LoanAmt() {
		return bcOverThan90LoanAmt;
	}

	public void setBcOverThan90LoanAmt(BigDecimal bcOverThan90LoanAmt) {
		this.bcOverThan90LoanAmt = bcOverThan90LoanAmt;
	}

	public boolean isChangeOverFlag() {
		return isChangeOverFlag;
	}

	public void setChangeOverFlag(boolean isChangeOverFlag) {
		this.isChangeOverFlag = isChangeOverFlag;
	}

	public BigDecimal getLossAmt() {
		return lossAmt;
	}

	public void setLossAmt(BigDecimal lossAmt) {
		this.lossAmt = lossAmt;
	}

	public BigDecimal getLossAmtBack() {
		return lossAmtBack;
	}

	public void setLossAmtBack(BigDecimal lossAmtBack) {
		this.lossAmtBack = lossAmtBack;
	}

	public BigDecimal getBcOverLossAmt() {
		return bcOverLossAmt;
	}

	public void setBcOverLossAmt(BigDecimal bcOverLossAmt) {
		this.bcOverLossAmt = bcOverLossAmt;
	}

	public BigDecimal getBcOverLossAmtBack() {
		return bcOverLossAmtBack;
	}

	public void setBcOverLossAmtBack(BigDecimal bcOverLossAmtBack) {
		this.bcOverLossAmtBack = bcOverLossAmtBack;
	}

	public BigDecimal getBcOverThan90LossAmt() {
		return bcOverThan90LossAmt;
	}

	public void setBcOverThan90LossAmt(BigDecimal bcOverThan90LossAmt) {
		this.bcOverThan90LossAmt = bcOverThan90LossAmt;
	}

	public BigDecimal getBcOverThan90LossAmtBack() {
		return bcOverThan90LossAmtBack;
	}

	public void setBcOverThan90LossAmtBack(BigDecimal bcOverThan90LossAmtBack) {
		this.bcOverThan90LossAmtBack = bcOverThan90LossAmtBack;
	}

	public BigDecimal getSpecialLossAmt() {
		return specialLossAmt;
	}

	public void setSpecialLossAmt(BigDecimal specialLossAmt) {
		this.specialLossAmt = specialLossAmt;
	}

	public BigDecimal getSpecialLossAmtBack() {
		return specialLossAmtBack;
	}

	public void setSpecialLossAmtBack(BigDecimal specialLossAmtBack) {
		this.specialLossAmtBack = specialLossAmtBack;
	}

	public BigDecimal getBusinessAmt() {
		return businessAmt;
	}

	public void setBusinessAmt(BigDecimal businessAmt) {
		this.businessAmt = businessAmt;
	}

	public BigDecimal getBcNoLossAmt() {
		return bcNoLossAmt;
	}

	public void setBcNoLossAmt(BigDecimal bcNoLossAmt) {
		this.bcNoLossAmt = bcNoLossAmt;
	}

	public BigDecimal getBcNoLossInterest() {
		return bcNoLossInterest;
	}

	public void setBcNoLossInterest(BigDecimal bcNoLossInterest) {
		this.bcNoLossInterest = bcNoLossInterest;
	}

	public BigDecimal getBcOverThanNoLossAmt() {
		return bcOverThanNoLossAmt;
	}

	public void setBcOverThanNoLossAmt(BigDecimal bcOverThanNoLossAmt) {
		this.bcOverThanNoLossAmt = bcOverThanNoLossAmt;
	}

	public BigDecimal getBcOverThanNoLossInterest() {
		return bcOverThanNoLossInterest;
	}

	public void setBcOverThanNoLossInterest(BigDecimal bcOverThanNoLossInterest) {
		this.bcOverThanNoLossInterest = bcOverThanNoLossInterest;
	}

	public BigDecimal getBcOverThan90NoLossAmt() {
		return bcOverThan90NoLossAmt;
	}

	public void setBcOverThan90NoLossAmt(BigDecimal bcOverThan90NoLossAmt) {
		this.bcOverThan90NoLossAmt = bcOverThan90NoLossAmt;
	}

	public BigDecimal getBcOverThan90NoLossInterest() {
		return bcOverThan90NoLossInterest;
	}

	public void setBcOverThan90NoLossInterest(BigDecimal bcOverThan90NoLossInterest) {
		this.bcOverThan90NoLossInterest = bcOverThan90NoLossInterest;
	}

	public BigDecimal getBcBankManyPayedAmt() {
		return bcBankManyPayedAmt;
	}

	public void setBcBankManyPayedAmt(BigDecimal bcBankManyPayedAmt) {
		this.bcBankManyPayedAmt = bcBankManyPayedAmt;
	}

	public String getBcCostType() {
		return bcCostType;
	}

	public void setBcCostType(String bcCostType) {
		this.bcCostType = bcCostType;
	}

	public BigDecimal getBcCostAmt() {
		return bcCostAmt;
	}

	public void setBcCostAmt(BigDecimal bcCostAmt) {
		this.bcCostAmt = bcCostAmt;
	}

	public BigDecimal getBcOverCostAmt() {
		return bcOverCostAmt;
	}

	public void setBcOverCostAmt(BigDecimal bcOverCostAmt) {
		this.bcOverCostAmt = bcOverCostAmt;
	}

	public BigDecimal getBcOverThan90CostAmt() {
		return bcOverThan90CostAmt;
	}

	public void setBcOverThan90CostAmt(BigDecimal bcOverThan90CostAmt) {
		this.bcOverThan90CostAmt = bcOverThan90CostAmt;
	}

	public BigDecimal getSetAttronInterest() {
		return setAttronInterest;
	}

	public void setSetAttronInterest(BigDecimal setAttronInterest) {
		this.setAttronInterest = setAttronInterest;
	}

	public BigDecimal getSetIntReceCurrInt() {
		return setIntReceCurrInt;
	}

	public void setSetIntReceCurrInt(BigDecimal setIntReceCurrInt) {
		this.setIntReceCurrInt = setIntReceCurrInt;
	}

	public BigDecimal getSetIntReceCurrAmt() {
		return setIntReceCurrAmt;
	}

	public void setSetIntReceCurrAmt(BigDecimal setIntReceCurrAmt) {
		this.setIntReceCurrAmt = setIntReceCurrAmt;
	}

	public BigDecimal getSetReceCurrInt() {
		return setReceCurrInt;
	}

	public void setSetReceCurrInt(BigDecimal setReceCurrInt) {
		this.setReceCurrInt = setReceCurrInt;
	}

	public BigDecimal getSetReceCurrAmt() {
		return setReceCurrAmt;
	}

	public void setSetReceCurrAmt(BigDecimal setReceCurrAmt) {
		this.setReceCurrAmt = setReceCurrAmt;
	}
}
