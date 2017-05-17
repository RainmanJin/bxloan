package com.coamctech.bxloan.service.model.corpcustomer;

import java.text.SimpleDateFormat;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.entity.CorpCustomerRelaPerson;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 企业客户相关人员查看详细VO
 * @author xc
 */
@SuppressWarnings("serial")
public class CorpRelaPersonDetailVO extends CorpCustomerRelaPerson{
	
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

	public void setPersonType(String str){}
	public String getPersonType(){
		StringBuilder personType = new StringBuilder();
		if("1".equals(this.getPersonIsActController())){
			personType.append(GlobalConstants.CORPCUS_RELA_PERSON_TYPE_CONTROLLER)
			.append(",");
		}
		if("1".equals(this.getPersonIsLegalRepresent())){
			personType.append(GlobalConstants.CORPCUS_RELA_PERSON_TYPE_LEGAL_REPRESENTATIVE)
			.append(",");
		}
		if("1".equals(this.getPersonIsManager())){
			personType.append(GlobalConstants.CORPCUS_RELA_PERSON_TYPE_MANAGER)
			.append(",");
		}
		if("1".equals(this.getPersonIsStockHolder())){
			personType.append(GlobalConstants.CORPCUS_RELA_PERSON_TYPE_STOCK)
			.append(",");
		}
		return personType.toString();
	}
	
	public String getBirthDateStr() {
		if(getBirthDate()==null){return "";}
		return new SimpleDateFormat("yyyy-MM-dd").format(getBirthDate());
	}
	
}

