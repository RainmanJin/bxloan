package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INFO_INPUT_CONFIG", schema = WD_SCHEMA)
public class InfoInputConfig implements java.io.Serializable {

	private Long id;
	private Long cellId;
	private String inputType;
	private String title;
	private String defaultValue;
	private String validator;
	private String validatorMsg;
	private Integer sortId;
	

	/** default constructor */
	public InfoInputConfig() {
	}

	/** minimal constructor */
	public InfoInputConfig(Long id) {
		this.id = id;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CELL_ID", precision = 12, scale = 0)
	public Long getCellId() {
		return this.cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	@Column(name = "INPUT_TYPE", length = 20)
	public String getInputType() {
		return this.inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DEFAULT_VALUE", length = 200)
	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Column(name = "VALIDATOR", length = 200)
	public String getValidator() {
		return this.validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}
	
	@Column(name = "VALIDATOR_MSG", length = 200)
	public String getValidatorMsg() {
		return validatorMsg;
	}
	public void setValidatorMsg(String validatorMsg) {
		this.validatorMsg = validatorMsg;
	}

	@Column(name = "SORT_ID", precision = 10, scale = 0)
	public Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

}