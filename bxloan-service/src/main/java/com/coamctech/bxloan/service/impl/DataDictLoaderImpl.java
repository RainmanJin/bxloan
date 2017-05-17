package com.coamctech.bxloan.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Code;
import com.coamctech.bxloan.entity.CodePk;
import com.coamctech.bxloan.service.DataDictLoader;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Transactional
@Service
public class DataDictLoaderImpl implements DataDictLoader {
	
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Autowired
	private DataDict dataDict;
	
	@SuppressWarnings("unchecked")
	@Override
	public void preLoad() {
		String jpql = "select c from Code c where c.usableStatusInd = '1' order by c.orderNumber";
		List<Code> codes = (List<Code>) dynamicQuery.query(jpql);
		dataDict.addAll(codes);
		loadAreas();
		loadRoleType();
		loadCurrencyType();
	}

	@Override
	public Collection<CodeData> getOneType(String codeType) {
		return dataDict.getOneType(codeType);
	}
	
	private void loadAreas(){
		final String CodeType = "NationArea";
		String jpql = "select na.codeKey, na.nationAreaCd, na.nationAreaName from NationArea na where na.usableStatusInd = '1' order by na.nationAreaCd ";
		List<Object[]> resultSet = (List<Object[]>) dynamicQuery.query(jpql);
		List<Code> codes = Lists.transform(resultSet, new Function<Object[], Code>() {
			@Override
			public Code apply(Object[] input) {
				String codeKey = str(input[0]);
				String areaCd = str(input[1]);
				String areaName = str(input[2]);
				
				CodePk pk = new CodePk(codeKey, CodeType);
				Code result = new Code();
				result.setCodePk(pk);
				result.setCodeValue(areaCd);
				result.setCodeName(areaName);
				return result;
			}
		});
		this.dataDict.addAll(codes);
	}
	
	private void loadRoleType(){
		final String CodeType = "RoleCode";
		String sql = "SELECT 'S'||ID AS CODE_KEY,ID AS ROLE_CD,NAME AS ROLE_NAME FROM EC_ORG_ROLE";
		
		List<Object[]> resultSet = this.dynamicQuery.nativeQuery(sql);
		
		List<Code> codes = Lists.transform(resultSet, new Function<Object[], Code>() {
			@Override
			public Code apply(Object[] input) {
				String codeKey = str(input[0]);
				String roleCd = str(input[1]);
				String roleName = str(input[2]);
				
				CodePk pk = new CodePk(codeKey, CodeType);
				Code result = new Code();
				result.setCodePk(pk);
				result.setCodeValue(roleCd);
				result.setCodeName(roleName);
				return result;
			}
		});
		this.dataDict.addAll(codes);
	}
	
	private void loadCurrencyType(){
		final String CodeType = "CurrencyType";
		String sql = "SELECT code_key,currency_type_cd,currency_name FROM currency_type";
		
		List<Object[]> resultSet = this.dynamicQuery.nativeQuery(sql);
		List<Code> codes = Lists.transform(resultSet, new Function<Object[], Code>() {
			@Override
			public Code apply(Object[] input) {

				String codeKey = str(input[0]);
				String currencyTypeCd = str(input[1]);
				String currencyName = str(input[2]);
				
				CodePk pk = new CodePk(codeKey, CodeType);
				Code result = new Code();
				result.setCodePk(pk);
				result.setCodeValue(currencyTypeCd);
				result.setCodeName(currencyName);
				return result;
			}
			
		});
		this.dataDict.addAll(codes);
				
	}
	
	private String str(Object obj){
		return obj==null?"":obj.toString().trim();
	}

	@Override
	public String getCodeName(String codeType, String codeValue) {
		return dataDict.getCodeName(codeType, codeValue);
	}

	@Override
	public Collection<CodeData> getOneType(String codeType, String[] codeKeys) {
		Collection<CodeData> codeList = Lists.newArrayList();
		
		for (CodeData codeData : this.getOneType(codeType)) {
			if(Arrays.asList(codeKeys).contains(codeData.getCodeValue())){
				codeList.add(codeData);
			}
		}
		return codeList;
	}

	@Override
	public String getCodeVal(String codeType, String codeKey) {
		return dataDict.getCodeVal(codeType, codeKey);
	}
}
