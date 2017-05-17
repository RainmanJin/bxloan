package com.coamctech.bxloan.service.model.applyinfo;

import java.util.ArrayList;
import java.util.List;

import com.coamctech.bxloan.entity.InfoCellConfig;
import com.coamctech.bxloan.entity.InfoInputConfig;

public class ApplyInfoCellVO {
	
	private List<InfoInputConfig> inputList = new ArrayList<InfoInputConfig>();
	private InfoCellConfig cell;

	public String getHtmlStr(){
		StringBuilder result = new StringBuilder();
		
		return result.toString();
	}
	//////////////////////////
	////GETTERS&SETTERS/////
	///////////////////////////
	public List<InfoInputConfig> getInputList() {
		return inputList;
	}
	public void setInputList(List<InfoInputConfig> inputList) {
		this.inputList = inputList;
	}
	public InfoCellConfig getCell() {
		return cell;
	}
	public void setCell(InfoCellConfig cell) {
		this.cell = cell;
	}
}
