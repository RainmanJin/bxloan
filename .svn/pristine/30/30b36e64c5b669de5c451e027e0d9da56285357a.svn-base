package com.coamctech.bxloan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CodePk implements Serializable {
	
	@Column(name = "CODE_KEY")
	private String codeKey;

	@Column(name = "CODE_TYPE")
	private String codeType;

	public CodePk() {

	}

	public CodePk(String codeKey, String codeType) {
		super();
		this.codeKey = codeKey;
		this.codeType = codeType;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}
