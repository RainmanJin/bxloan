package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统字典条目
 * @author AcoreHeng
 *
 */
@Entity
@Table(name = "SYS_DICT_ITEM", schema = WD_SCHEMA)
public class SysDictItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4983046342340988428L;
	@Id
	@Column(name="id", unique = true, nullable = false, precision = 20, scale = 0)
	private Long id;
	@Column(name="pid",precision=20,scale=0)
	private Long pid;
	/**
	 * 编号标示（系统唯一）
	 */
	@Column(name="code",length=200)
	private String code;
	/**
	 * 名称
	 */
	@Column(name="name",length=100)
	private String name;
	/**
	 * 值
	 */
	@Column(name="VALUE",length=50)
	private String value;
	/**
	 * 默认0 0：无效，1：有效
	 */
	@Column(name="STATE",length=1)
	private String state="0";
	/**
	 * 类别标示
	 */
	@Column(name="TYPE",length=100)
	private String type;
	/**
	 * 
	 */
	@Column(name="SORT_NUM",precision=20,scale=0)
	private int sortNum;
	/**
	 * 使用范围：0032,23,232逗号隔开
	 */
	@Column(name="RANGE_OF_USE",length=1000)
	private String rangeOfUse;
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=100)
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getRangeOfUse() {
		return rangeOfUse;
	}
	public void setRangeOfUse(String rangeOfUse) {
		this.rangeOfUse = rangeOfUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
