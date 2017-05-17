package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.coamctech.bxloan.commons.entity.BaseEntity;

/**
 * 类名称：WfActionTreeNode 
 * 类描述 ： 审批流程对应功能页面实体（树形结构）
 * 创建人: wangyawei 
 * 创建时间：2015年7月20日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
@Entity
@Table(name = "WF_ACTIONTREENODE", schema = WD_SCHEMA)
public class WfActionTreeNode extends BaseEntity{
	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "seq_wf_actiontreenode", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	/** 流程/环节/功能名称 */
	@Column(name = "NAME", length = 100)
	private String name;
	
	/** 描述 */
	@Column(name = "DESCRIPTION", length = 200)
	private String description;
	
	/** 节点类型 */
	@Column(name = "TYPE", length = 50)
	private String type;
	
	/** 链接地址 */
	@Column(name = "RESOURCES", length = 200)
	private String resources;
	
	/** 父级节点ID */
	@Column(name = "PARENTNODEID")
	private Long parentNodeId;
	
	/** 树ID */
	@Column(name = "TREEID", precision = 22)
	private Long treeId;
	
	/** 序号 */
	@Column(name = "ORDERS", precision = 22)
	private Integer orders;
	
	/** 状态 0:失效；1:生效  */
	@Column(name = "STATE", precision = 22)
	private Integer state;
	
	/** 叶子节点 1:是；0:否  */
	@Column(name = "LEAF", length = 1)
	private String leaf;
	
	@Transient
	private List<WfActionTreeNode> childs = new ArrayList<WfActionTreeNode>();

	/** default constructor */
	public WfActionTreeNode() {
	}
	
	public WfActionTreeNode(Long id, String name, String description,
			String type, String resources, Long parentNodeId, Long treeId,
			Integer orders, Integer state, String leaf,
			List<WfActionTreeNode> childs) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.resources = resources;
		this.parentNodeId = parentNodeId;
		this.treeId = treeId;
		this.orders = orders;
		this.state = state;
		this.leaf = leaf;
		this.childs = childs;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResources() {
		return this.resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public Long getParentNodeId() {
		return parentNodeId;
	}
	
	public void setParentNodeId(Long parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getTreeId() {
		return treeId;
	}

	public void setTreeId(Long treeId) {
		this.treeId = treeId;
	}

	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public List<WfActionTreeNode> getChilds() {
		return childs;
	}
	
	public void setChilds(List<WfActionTreeNode> childs) {
		this.childs = childs;
	}
}