package com.coamctech.bxloan.datadict;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.coamctech.bxloan.entity.Code;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 将code表中的数据,经整理后
 * 
 * @author wanghui
 * @lastModified wanghui 2014-7-29 16:06:59
 *
 */
@Component
public class CodeRepository implements DataDict {
	
	private static Map<String, LinkedHashMap<String, CodeData>> codes = new HashMap<String, LinkedHashMap<String, CodeData>>();
	private static Map<String, String> areas = Maps.newHashMap();
	
	@Override
	public void addAll(List<Code> codes) {
		for (Code code : codes) {
			addOne(code);
		}
	}
	
	protected void addOne(Code oneCode) {
		CodeData data = new CodeData();
		BeanUtils.copyProperties(oneCode, data);
		
        String codeType = oneCode.getCodePk().getCodeType();
        LinkedHashMap<String, CodeData> codeMap = codes.get(codeType);
        if (codeMap == null) {
            codeMap = new LinkedHashMap<String, CodeData>();
            codeMap.put(oneCode.getCodePk().getCodeKey(), data);
            codes.put(codeType, codeMap);
        } else {
            codeMap.put(oneCode.getCodePk().getCodeKey(), data);
        }
    }
	
	@Override
	public Collection<CodeData> getOneType(String codeType) {
		return codes.get(codeType).values();
	}

	@Override
	public CodeData getUniqueOne(String codeType, String codeKey) {
		return codes.get(codeType).get(codeKey);
	}

	@Override
	public String getCodeName(String codeType, String codeValue) {
		Collection<CodeData> codeList = this.getOneType(codeType);
		for (CodeData codeData : codeList) {
			if(codeData.getCodeValue().equals(codeValue)) {
				return codeData.getCodeName();
			}
		}
		return "";
	}

	
	@Override
	public String getCodeVal(String codeType, String codeKey) {
		return this.getUniqueOne(codeType, codeKey).getCodeValue();
	}
	
	public List<String> getAllValue(String codeType) {
		Collection<CodeData> cdList =  this.getOneType(codeType);
		List resultVals = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(cdList)){
			for (CodeData cd : cdList) {
				resultVals.add(cd.getCodeValue());
			}
		}else{
			throw new NullPointerException("Can't find this codeType from code");
		}
		return resultVals;
	}
	
	@Override
	public List<String> getCodeValList(String codeType, String... codeKeys) {
		List<String> resultStrs=Lists.newArrayList();
		if(codeKeys==null){
			return this.getAllValue(codeType);
		}
		for (String key : codeKeys) {
			resultStrs.add(getCodeVal(codeType, key));
		}
		return resultStrs;
	}

	

	

	
	


}
