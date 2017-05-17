package com.coamctech.bxloan.service.model.corpcustomer;

import java.text.SimpleDateFormat;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.entity.CorpCustomerRelaCorp;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class CorpRelaCorpDetailVO extends CorpCustomerRelaCorp{
	@JsonIgnore
	public Long getPartyId() {
		return super.getPartyId();
	}
	
	public void setPartyId(Long partyId) {
		super.setPartyId(partyId);
	}
	
	public Long getExistingPartyId() {
		return super.getPartyId();
	}
	public void setExistingPartyId(Long partyId) {
		super.setPartyId(partyId);
	}
	
	public void setCorpType(String str){}
	public String getCorpType(){
		StringBuilder personType = new StringBuilder();
		if("1".equals(this.getCorpIsActController())){
			personType.append(GlobalConstants.CORPCUS_RELA_PERSON_TYPE_CONTROLLER)
			.append(",");
		}
		if("1".equals(this.getCorpIsStockHolder())){
			personType.append(GlobalConstants.CORPCUS_RELA_PERSON_TYPE_STOCK)
			.append(",");
		}
		return personType.toString();
	}
	
	public String getCorpEstablishDateStr() {
		if(getCorpEstablishDate()==null){
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(getCorpEstablishDate());
	}
}
