package com.coamctech.bxloan.entity;
// default package
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * NationArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NATION_AREA", schema = GlobalConstants.WD_SCHEMA)
public class NationArea implements java.io.Serializable {
	// Fields
	private String codeNum;
	private String nationAreaCd;
	private String nationAreaName;
	private Byte regionLevel;
	private String parentNationAreaCd;
	private Integer orderNum;
	private String usableStatusInd;
	private String remark;
	private String codeKey;

	// Constructors
	/** default constructor */
	public NationArea() {
	}

	/** minimal constructor */
	public NationArea(String codeNum) {
		this.codeNum = codeNum;
	}

	/** full constructor */
	public NationArea(String codeNum, String nationAreaCd,
			String nationAreaName, Byte regionLevel, String parentNationAreaCd,
			Integer orderNum, String usableStatusInd, String remark,
			String codeKey) {
		this.codeNum = codeNum;
		this.nationAreaCd = nationAreaCd;
		this.nationAreaName = nationAreaName;
		this.regionLevel = regionLevel;
		this.parentNationAreaCd = parentNationAreaCd;
		this.orderNum = orderNum;
		this.usableStatusInd = usableStatusInd;
		this.remark = remark;
		this.codeKey = codeKey;
	}

	// Property accessors
	@Id
	@Column(name = "CODE_NUM", unique = true, nullable = false, length = 40)
	public String getCodeNum() {
		return this.codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	@Column(name = "NATION_AREA_CD", length = 20)
	public String getNationAreaCd() {
		return this.nationAreaCd;
	}

	public void setNationAreaCd(String nationAreaCd) {
		this.nationAreaCd = nationAreaCd;
	}

	@Column(name = "NATION_AREA_NAME", length = 100)
	public String getNationAreaName() {
		return this.nationAreaName;
	}

	public void setNationAreaName(String nationAreaName) {
		this.nationAreaName = nationAreaName;
	}

	@Column(name = "REGION_LEVEL", precision = 2, scale = 0)
	public Byte getRegionLevel() {
		return this.regionLevel;
	}

	public void setRegionLevel(Byte regionLevel) {
		this.regionLevel = regionLevel;
	}

	@Column(name = "PARENT_NATION_AREA_CD", length = 20)
	public String getParentNationAreaCd() {
		return this.parentNationAreaCd;
	}

	public void setParentNationAreaCd(String parentNationAreaCd) {
		this.parentNationAreaCd = parentNationAreaCd;
	}

	@Column(name = "ORDER_NUM", precision = 6, scale = 0)
	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "USABLE_STATUS_IND", length = 1)
	public String getUsableStatusInd() {
		return this.usableStatusInd;
	}

	public void setUsableStatusInd(String usableStatusInd) {
		this.usableStatusInd = usableStatusInd;
	}

	@Column(name = "REMARK", length = 256)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CODE_KEY", length = 40)
	public String getCodeKey() {
		return this.codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
}
