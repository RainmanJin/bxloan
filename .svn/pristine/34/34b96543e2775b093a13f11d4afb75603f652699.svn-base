package com.coamctech.bxloan.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.coamctech.bxloan.commons.GlobalConstants;

/**   
 * 类名称：PropertiesUtils
 * 类描述 ：加载配置文件公用方法
 * 创建人: 王亚威  
 * 创建时间：2015年5月18日 下午5:07:01  
 * 修改人：王亚威
 * 修改时间：2015年5月18日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class PropertiesUtils {
	 private static Properties docProps = null;
	 private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

     static {
        try {
            if(docProps == null ) {
            	docProps = new Properties();
            	docProps = PropertiesUtils.loadPropertiesFileFromClassPath(GlobalConstants.DOC_TARGET);
            }
        } catch(IOException e) {
            logger.error("init PropertiesUtils error: {}", e.getMessage());
            e.printStackTrace();
        }
     }
     
     /**
      * 加载资源文件
      * @param path 资源文件路径
      * @return 
      */
 	public static Properties loadPropertiesFileFromClassPath(String path) throws IOException{
 		Properties config = new Properties();
 		try {
 			config.load(new ClassPathResource(path).getInputStream());
 			//config.load(PropertiesUtils.getResourceAsStream(path));
 		} catch (IOException e) {
 			logger.error("load properties file error: {}", e.getMessage());
 		}
 		return config;
 	}
 	
 	/**
     * 获取资源文件输入流
     * @param path 资源文件路径
     * @return 
     */
 	 public static InputStream getResourceAsStream(String resouce) {
         ClassLoader cl = PropertiesUtils.class.getClassLoader();
         if(cl != null){
        	 return cl.getResourceAsStream(resouce);
         } else{
        	 return ClassLoader.getSystemResourceAsStream(resouce);
         }
     }
 	 
 	 /**
      * 获取配置文件名称
      * @param configKey
      * @return 配置名称
      */
    public static String getConfigFileName(String configKey) {
        String returnVal = docProps.getProperty(configKey);
        if( returnVal == null){
        	logger.warn("配置文件名称: {} is null", configKey);
        }
        return returnVal;
    }
    
    /**
     * 获取导出Excel模板存放位置的输入流
     * @param configNum
     * @return InputStream
     */
    public static InputStream getExcelDownloadTemplateFile(String configNum){
    	try {
            String fileName = getExcelConfigFileName(configNum);
            return getResourceAsStream(GlobalConstants.EXCEL_TEMPLATE_DOWNLOAD_PATH + fileName);
        } catch(Exception e) {
            logger.error("获取导出Excel模板存放位置的输入流失败，失败信息：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取导入Excel模板存放位置的输入流
     * @param configNum
     * @return InputStream
     */
    public static InputStream getExcelUploadTemplateFile(String configNum){
    	try {
    		String fileName = getExcelConfigFileName(configNum);
    		return getResourceAsStream(GlobalConstants.EXCEL_TEMPLATE_UPLOAD_PATH + fileName);
    	} catch(Exception e) {
    		logger.error("获取导入Excel模板存放位置的输入流失败，失败信息：{}", e.getMessage());
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * 获取Excel模板存储的配置文件名称
     * @param configNum
     * @return 配置文件名称
     */
    public static String getExcelConfigFileName(String configNum){
        String returnVal = docProps.getProperty("excel." + configNum );
        if(returnVal == null){
        	logger.warn("Excel模板存储的配置文件名称：{} is null", configNum);
        }
        return returnVal;
    }
    
    /**
     * 获取导出Word模板存放位置的输入流
     * @param configNum
     * @return InputStream
     */
    public static InputStream getWordDownloadTemplateFile(String configNum){
    	try {
    		String fileName = getExcelConfigFileName(configNum);
    		return getResourceAsStream(GlobalConstants.WORD_TEMPLATE_DOWNLOAD_PATH + fileName);
    	} catch(Exception e) {
    		logger.error("获取导出Word模板存放位置的输入流失败，失败信息：{}", e.getMessage());
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * 获取导入Word模板存放位置的输入流
     * @param configNum
     * @return InputStream
     */
    public static InputStream getWordUploadTemplateFile(String configNum){
    	try {
    		String fileName = getExcelConfigFileName(configNum);
    		return getResourceAsStream(GlobalConstants.WORD_TEMPLATE_UPLOAD_PATH + fileName);
    	} catch(Exception e) {
    		logger.error("获取导入Word模板存放位置的输入流失败，失败信息：{}", e.getMessage());
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * 获取Word模板存储的配置文件名称
     * @param configNum
     * @return 配置文件名称
     */
    public static String getWordConfigFileName(String configNum){
    	String returnVal = docProps.getProperty("word." + configNum );
    	if(returnVal == null){
    		logger.warn("Word模板存储的配置文件名称：{} is null", configNum);
    	}
    	return returnVal;
    }
}
