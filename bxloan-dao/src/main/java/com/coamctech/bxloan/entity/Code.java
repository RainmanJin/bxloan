package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.entity.BaseEntity;

@Entity
@Table(name = "CODE", schema = WD_SCHEMA)
public class Code extends BaseEntity {

	@EmbeddedId
	private CodePk codePk;

	@Column(name = "CODE_NAME")
	private String codeName;

	@Column(name = "CODE_VALUE")
	private String codeValue;

	@Column(name = "ORDER_NUMBER")
	private Integer orderNumber;

	@Column(name = "USABLE_STATUS_IND")
	private String usableStatusInd;

	public Code() {
		super();
	}

	public Code(CodePk codePk, String codeName, String codeValue,
			Integer orderNumber, String usableStatusInd) {
		super();
		this.codePk = codePk;
		this.codeName = codeName;
		this.codeValue = codeValue;
		this.orderNumber = orderNumber;
		this.usableStatusInd = usableStatusInd;
	}

	public CodePk getCodePk() {
		return codePk;
	}

	public void setCodePk(CodePk codePk) {
		this.codePk = codePk;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getUsableStatusInd() {
		return usableStatusInd;
	}

	public void setUsableStatusInd(String usableStatusInd) {
		this.usableStatusInd = usableStatusInd;
	}

}
