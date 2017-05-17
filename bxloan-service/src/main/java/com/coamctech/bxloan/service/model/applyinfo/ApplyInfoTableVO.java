package com.coamctech.bxloan.service.model.applyinfo;

import java.util.ArrayList;
import java.util.List;

public class ApplyInfoTableVO {

	private String tableHead;
	private Integer columnCount;
	private List<ApplyInfoLineGroupVO> linegroupList = new ArrayList<ApplyInfoLineGroupVO>();
	
	//////////////////////////
	////GETTERS&SETTERS/////
	///////////////////////////
	public List<ApplyInfoLineGroupVO> getLinegroupList() {
		return linegroupList;
	}
	public void setLinegroupList(List<ApplyInfoLineGroupVO> linegroupList) {
		this.linegroupList = linegroupList;
	}
	public Integer getColumnCount() {
		return columnCount;
	}
	public void setColumnCount(Integer columnCount) {
		this.columnCount = columnCount;
	}
	public String getTableHead() {
		return tableHead;
	}
	public void setTableHead(String tableHead) {
		this.tableHead = tableHead;
	}
	
	
}
