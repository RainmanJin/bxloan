package com.coamctech.bxloan.web.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.service.DataDictLoader;

@Controller
@RequestMapping("/loadDataDict")
public class DataDictLoaderController {
	
	@Autowired
	private DataDictLoader dataDictLoader;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Map<String, String>> loadDataDict(@RequestParam("codeTypes[]") String[] codeTypes) {
		Map<String, Map<String, String>> dataDict = new HashMap<String, Map<String, String>>();
		for (String codeType : codeTypes) {
 			Collection<CodeData> codeData = dataDictLoader.getOneType(codeType);
			dataDict.put(codeType, toMap(codeData));
		}
		return dataDict;
	}
	
	@RequestMapping(value="/translate", method = RequestMethod.POST)
	@ResponseBody
	public String translateCodeName(@RequestParam("codeType") String codeType, @RequestParam("codeValue") String codeValue){
		StringBuilder codeName = new StringBuilder();
		for (String value : codeValue.split(",")) {
			String name = dataDictLoader.getCodeName(codeType, value);
			codeName.append(name + ",");
		}
		return codeName.toString().substring(0, codeName.length() - 1);
	}
	
	private Map<String, String> toMap(Collection<CodeData> codeData) {
		Map<String, String> map = new HashMap<String, String>();
		for (CodeData data : codeData) {
			map.put(data.getCodeValue(), data.getCodeName());
		}
		return map;
	}
	
	@RequestMapping(value="/getCodeVal", method = RequestMethod.POST)
	@ResponseBody
	public String getCodeVal(@RequestParam("codeType") String codeType, @RequestParam("codeKey") String codeKey){
		StringBuilder codeVal = new StringBuilder();
		for (String key : codeKey.split(",")) {
			String value = dataDictLoader.getCodeVal(codeType, key);
			codeVal.append(value).append(",");
		}
		return codeVal.toString().substring(0, codeVal.length() - 1);
	}
}
