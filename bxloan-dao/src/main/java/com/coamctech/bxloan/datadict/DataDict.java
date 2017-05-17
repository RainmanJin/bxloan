package com.coamctech.bxloan.datadict;

import java.util.Collection;
import java.util.List;

import com.coamctech.bxloan.entity.Address;
import com.coamctech.bxloan.entity.Code;

public interface DataDict {
	
	public void addAll(List<Code> dataDict);
	
	
	public Collection<CodeData> getOneType(String codeType);
	
	public CodeData getUniqueOne(String codeType, String codeKey);
	
	public String getCodeName(String codeType, String codeValue);
	
	public String getCodeVal(String codeType, String codeKey);
	/**
	 * 获取同一类型指定key的一组值
	 * @param codeType
	 * @param codeKey
	 * @return
	 */
	public List<String> getCodeValList(String codeType, String... codeKeys);
	
}