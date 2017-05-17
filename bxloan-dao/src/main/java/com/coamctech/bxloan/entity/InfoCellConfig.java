package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INFO_CELL_CONFIG", schema = WD_SCHEMA)
public class InfoCellConfig implements java.io.Serializable {

	private Long id;
	private String tableTypeCd;
	private String tableTypeName;
	private Integer rowGroupCd;
	private String rowGroupName;
	private String rowDescript;
	private Integer rowCd;
	private String cellInputName;
	private Integer sortId;

	public InfoCellConfig() {
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TABLE_TYPE_CD", length = 20)
	public String getTableTypeCd() {
		return this.tableTypeCd;
	}

	public void setTableTypeCd(String tableTypeCd) {
		this.tableTypeCd = tableTypeCd;
	}

	@Column(name = "TABLE_TYPE_NAME", length = 100)
	public String getTableTypeName() {
		return this.tableTypeName;
	}

	public void setTableTypeName(String tableTypeName) {
		this.tableTypeName = tableTypeName;
	}

	@Column(name = "ROW_GROUP_CD", length = 10)
	public Integer getRowGroupCd() {
		return this.rowGroupCd;
	}

	public void setRowGroupCd(Integer rowGroupCd) {
		this.rowGroupCd = rowGroupCd;
	}

	@Column(name = "ROW_GROUP_NAME", length = 200)
	public String getRowGroupName() {
		return this.rowGroupName;
	}

	public void setRowGroupName(String rowGroupName) {
		this.rowGroupName = rowGroupName;
	}

	@Column(name = "ROW_DESCRIPT", length = 200)
	public String getRowDescript() {
		return this.rowDescript;
	}

	public void setRowDescript(String rowDescript) {
		this.rowDescript = rowDescript;
	}

	@Column(name = "ROW_CD", precision = 10, scale = 0)
	public Integer getRowCd() {
		return this.rowCd;
	}

	public void setRowCd(Integer rowCd) {
		this.rowCd = rowCd;
	}

	@Column(name = "CELL_INPUT_NAME", length = 200)
	public String getCellInputName() {
		return this.cellInputName;
	}

	public void setCellInputName(String cellInputName) {
		this.cellInputName = cellInputName;
	}

	@Column(name = "SORT_ID", precision = 10, scale = 0)
	public Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

}