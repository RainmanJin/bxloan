package com.coamctech.bxloan.commons.utils.excel.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.coamctech.bxloan.commons.GlobalConstants;
/**   
 * 类名称：ExcelImportConfig
 * 类描述 ：excel导入配置类 
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class ExcelImportConfig {
	private static Logger logger = LoggerFactory.getLogger(ExcelImportConfig.class);

	private static HashMap<String, ExcelImportConfigItem> map = new HashMap<String, ExcelImportConfigItem>(); 
	
	private static long configFileLastModifyTime;
	
	private static String configFilePath = GlobalConstants.CONFIG_PROPERTIES_PATH + "ExcelImportConfig.xml";
	
	//获取配置文件
	public synchronized static void init() {
		String excelConfig = "";
		init(excelConfig);
	}
	
	//获取配置文件进行读取
	public synchronized static void init(String excelConfig) {
		try {
			ClassPathResource configFile = new ClassPathResource(configFilePath);
			if (configFile != null && configFile.lastModified() != configFileLastModifyTime) {
				readConfigFile(configFile.getFile());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 读取配置文件信息
	 * @param file
	 */
	private static void readConfigFile(File file) {
		if (file == null) {
			return;
		}
		InputStream configIn = null;
		try {
			configIn = new FileInputStream(file);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(configIn);
			Element root = document.getRootElement();
			List nodes = root.elements("config");
			Iterator iter = nodes.iterator();
			while(iter.hasNext()){
				Element element = (Element) iter.next();
				ExcelImportConfigItem config = new ExcelImportConfigItem();
				String importName = element.elementText("importName");
				if (StringUtils.isBlank(importName)) {
					logger.error("读取Excel导入配置文件，config缺少importName");
					continue;
				}
				config.setImportName(importName);
				String boClass = element.elementText("boClass");
				if (StringUtils.isBlank(boClass)) {
					logger.error("读取Excel导入配置文件，config缺少boClass");
					continue;
				}
				config.setBoClass(boClass);
				
				try {
					Class boClazz = Class.forName(boClass);
					config.setBoClazz(boClazz);
				} catch (Exception e) {
					logger.error("读取Excel导入配置文件，config转换boClass失败，没找到响应的Class");
					continue;
				}
				
				String startLineStr = element.elementText("startLine");
				if (StringUtils.isBlank(startLineStr)) {
					logger.error("读取Excel导入配置文件，config缺少起始行");
					continue;
				}
				try {
					int startLine = Integer.parseInt(startLineStr.trim());
					config.setStartLine(startLine);
				} catch (Exception e) {
					logger.error("读取Excel导入配置文件，config起始行必须为数字");
					continue;
				}
				
				String serviceBeanId = element.elementText("serviceBeanId");
				
				if (StringUtils.isBlank(serviceBeanId)) {
					logger.error("读取Excel导入配置文件，config缺少执行导入业务逻辑的serviceBeanId");
					continue;
				}
				
				config.setServiceBeanId(serviceBeanId);
				
				String serviceMethodName = element.elementText("serviceMethodName");
				
				if (StringUtils.isBlank(serviceMethodName)) {
					logger.error("读取Excel导入配置文件，config缺少执行导入业务逻辑的serviceMethodName");
					continue;
				}
				
				config.setServiceMethodName(serviceMethodName);
				
				Element fields = element.element("fields");
				if (fields == null) {
					logger.error("读取Excel导入配置文件，config缺少fields");
					continue;
				}
				List fieldList = fields.elements("field");
				
				if (fieldList == null || fieldList.size() == 0) {
					logger.error("读取Excel导入配置文件，config缺少field");
					continue;
				}
				// excel列配置信息
				List<ExcelImportConfigField> configFieldList = new ArrayList<ExcelImportConfigField>();
				for (int i = 0; i < fieldList.size(); i++) {
					// 字段配置信息
					ExcelImportConfigField configField = new ExcelImportConfigField();
					Element field = (Element)fieldList.get(i);
					// 字段对应vo属性值ֵ
					String name = field.elementText("name");
					// 字段对应描述
					String desc = field.elementText("desc");
					// 字段对应excel的列号
					String columnIndex = field.elementText("columnIndex");
					// 正则表达式
					String regexStr = field.elementText("regex");
					// 是否允许为空
					String allowBlank = field.elementText("allowBlank");
					// 正则验证出错提示
					String errorMsg = field.elementText("regexMsg");
					if (StringUtils.isBlank(name)) {
						logger.error("读取Excel导入配置文件，field缺少name，config = " + importName);
						continue;
					}
					if (StringUtils.isBlank(desc)) {
						logger.error("读取Excel导入配置文件，field缺少desc，config = " + importName);
						continue;
					}
					if (StringUtils.isBlank(columnIndex)) {
						logger.error("读取Excel导入配置文件，field缺少columnIndex，config = " + importName);
						continue;
					}
					if (StringUtils.isBlank(allowBlank)) {
						allowBlank = "true";
					}
					
					configField.setFieldName(name);
					configField.setFieldDesc(desc);
					configField.setColumnIndex(Integer.parseInt(columnIndex));
					if (StringUtils.isNotBlank(regexStr)) {
						configField.setRegexStr(regexStr);
						configField.setErrorMsg(errorMsg);
					}
					configField.setAlowBlank("true".equalsIgnoreCase(allowBlank)); 
					config.getFields().add(new String[] {name, desc, allowBlank});
					configFieldList.add(configField); 
				}
				config.setFieldList(configFieldList);
				if (config.getFields().size() == 0) {
					logger.error("读取Excel导入配置文件，config的field List size = 0，config = " + importName);
					continue;
				}
				map.put(importName, config);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(configIn);
		}
	}
	
	//通过名称获取配置文件
	public synchronized static ExcelImportConfigItem getConfigByName(String importName) {
		init();
		if (map.containsKey(importName)) {
			return map.get(importName);
		}
		return null;
	}
	
}
