package com.coamctech.bxloan.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.entity.UuidEntity;

/**
 * 客户账户表
 * CustomerAccountManagent entity. 
 * @author MyEclipse Persistence Tools
 * @modifyer gph
 * @modify date 20150526
 */
@Entity
@Table(name = "CUSTOMER_ACCOUNT_MANAGENT", schema = GlobalConstants.WD_SCHEMA)
public class CustomerAccountManagent  implements java.io.Serializable {
	private static final long serialVersionUID = -8855115177871653788L;
	/**账户编号*/
	private BigDecimal accountId;
	/**户名*/
	private String accountName;
	/**开户行*/
	private String bankAccount;
	/**账号*/
	private String accountNum;
	/**账号类型 </br> 1：放款，2：扣款，3：还款*/
	private BigDecimal accountType;
	/**客户编号*/
	private String customerNum;
	/**账号状态 </br> 1：使用，2：未使用*/
	private BigDecimal accountStatus;
	/**参与者*/
	private BigDecimal partyId;
	/**协议编号*/
	private BigDecimal agreementId;
	/**预留电话*/
	private String accountPhone;
	// Constructors

	/** default constructor */
	public CustomerAccountManagent() {
	}

	/** minimal constructor */
	public CustomerAccountManagent(BigDecimal accountId) {
		this.accountId = accountId;
	}

	/** full constructor */
	

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_CUSTOMER_ACCOUNT_MANAGENT", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false, precision = 20, scale = 0)
	public BigDecimal getAccountId() {
		return this.accountId;
	}

	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}

	@Column(name = "ACCOUNT_NAME", length = 50)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	

	@Column(name = "ACCOUNT_NUM", length = 50)
	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Column(name = "ACCOUNT_TYPE", precision = 20, scale = 0)
	public BigDecimal getAccountType() {
		return this.accountType;
	}

	public void setAccountType(BigDecimal accountType) {
		this.accountType = accountType;
	}

	
	@Column(name = "ACCOUNT_STATUS", precision = 20, scale = 0)
	public BigDecimal getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(BigDecimal accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name = "PARTY_ID", precision = 20, scale = 0)
	public BigDecimal getPartyId() {
		return this.partyId;
	}

	public void setPartyId(BigDecimal partyId) {
		this.partyId = partyId;
	}

	@Column(name = "AGREEMENT_ID", precision = 20, scale = 0)
	public BigDecimal getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(BigDecimal agreementId) {
		this.agreementId = agreementId;
	}
	@Column(name = "ACCOUNT_PHONE", length = 20)
	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}
	@Column(name = "BANK_ACCOUNT", length = 30)
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Column(name = "CUSTOMER_NUM", length = 20)
	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	
	
	
	
}