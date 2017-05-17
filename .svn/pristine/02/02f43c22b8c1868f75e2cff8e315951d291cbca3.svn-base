package com.coamctech.bxloan.service;

import java.util.Collection;

import com.coamctech.bxloan.datadict.CodeData;

public interface DataDictLoader {
	
	void preLoad();

	Collection<CodeData> getOneType(String codeType);
	
	Collection<CodeData> getOneType(String codeType, String[] codeVals);
	
	String getCodeName(String codeType, String codeValue);
	
	String getCodeVal(String codeType, String codeKey);
}
