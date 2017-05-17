package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EC_FUNCTIONTREENODE", schema = WD_SCHEMA)
public class EcFunctiontreenode implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String description;
	private String type;
	private String resources;
	private String authorityscript;
	private Long parentnodeid;
	private Integer orders;
	private Integer state;
	private Integer treeid;
	private String leaf;
	private String sysCd;
	private List<EcFunctiontreenode> childs = new ArrayList<EcFunctiontreenode>();


	/** default constructor */
	public EcFunctiontreenode() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TYPE", length = 64)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "RESOURCES", length = 256)
	public String getResources() {
		return this.resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	@Column(name = "AUTHORITYSCRIPT", length = 256)
	public String getAuthorityscript() {
		return this.authorityscript;
	}

	public void setAuthorityscript(String authorityscript) {
		this.authorityscript = authorityscript;
	}

	@Column(name = "PARENTNODEID")
	public Long getParentnodeid() {
		return this.parentnodeid;
	}

	public void setParentnodeid(Long parentnodeid) {
		this.parentnodeid = parentnodeid;
	}

	@Column(name = "ORDERS", precision = 22, scale = 0)
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	@Column(name = "STATE", precision = 22, scale = 0)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "TREEID", precision = 22, scale = 0)
	public Integer getTreeid() {
		return this.treeid;
	}

	public void setTreeid(Integer treeid) {
		this.treeid = treeid;
	}

	@Column(name = "LEAF", length = 1)
	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	@Column(name = "SYS_CD", length = 1)
	public String getSysCd() {
		return this.sysCd;
	}

	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	@Transient
	public List<EcFunctiontreenode> getChilds() {
		return childs;
	}

	public void setChilds(List<EcFunctiontreenode> childs) {
		this.childs = childs;
	}
}