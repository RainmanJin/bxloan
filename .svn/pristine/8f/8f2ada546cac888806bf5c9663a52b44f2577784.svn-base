package com.coamctech.bxloan.commons.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coamctech.bxloan.commons.GlobalConstants;

public class ReportExcelUtils {
	private static Logger logger = LoggerFactory.getLogger(ReportExcelUtils.class);

	/**
	 * 模版加载并且生成导出文件落地
	 * 
	 * @param excelKey doc.properties名称
	 * @param uploadKey doc.properties名称
	 * @param params
	 * @throws BizException
	 */
	//public String genernateExcelFileName(String excelKey,String uploadKey, Map params) throws Exception {
	public String genernateExcelFileName(InputStream is,String dfzcReportPath, String fileName,Map params) throws Exception {
		String nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
		fileName += nowDate;
		try {
			// 模板加载
			//InputStream is = PropertiesUtil.getExcelTemplateFile(excelKey);
			XLSTransformer transformer = new XLSTransformer();
			Workbook resultWorkbook = transformer.transformXLS(is, params);
			// 设置列宽
			//this.setColWidth(resultWorkbook);
			is.close();
			//String dfzcReportPath = PropertiesUtil.getConfigFileName(uploadKey); 
			if (StringUtils.isNotBlank(dfzcReportPath)) {
				// 主目录不存在，新建
				java.io.File myFilePath = new java.io.File(dfzcReportPath);
				if (!myFilePath.exists()) {
					myFilePath.mkdir();
				}
			}
			dfzcReportPath = dfzcReportPath + fileName;
			File file = new File(dfzcReportPath);
			if (!file.exists()) {
				// 文件不存在，新建
				file.createNewFile();
			}
			OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
			resultWorkbook.write(os);
			os.flush();
			os.close();
			return fileName;
		}
		catch (Exception eIo){
			if (logger.isInfoEnabled()) {
				logger.info("===========报表落地异常:" + eIo.getMessage());
			}
		}
		return fileName;
	}
	
	/**
	 * 模版加载并且生成导出文件落地
	 * @param excelKey  doc.properties配置的excel模板名称
	 * @param uploadKey doc.properties配置的文件路径
	 * @param fileName  下载模板的名称
	 * @param params
	 * @throws Exception
	 */
	public String genernateExcelFileName(String excelKey, String uploadKey, String fileName, Map params) throws Exception {
		String nowDate = DateTools.dateToString(new Date(), "yyyyMMddHHmmss") + ".xlsx";
		fileName += nowDate;
		try {
			// 模板加载
			InputStream is = PropertiesUtils.getExcelDownloadTemplateFile(excelKey);
			XLSTransformer transformer = new XLSTransformer();
			Workbook resultWorkbook = transformer.transformXLS(is, params);
			is.close();
			String dfzcReportPath = PropertiesUtils.getConfigFileName(uploadKey); 
			if (StringUtils.isNotBlank(dfzcReportPath)) {
				// 主目录不存在，新建
				java.io.File myFilePath = new java.io.File(dfzcReportPath);
				if (!myFilePath.exists()) {
					myFilePath.mkdirs();
				}
			}
			dfzcReportPath = dfzcReportPath + fileName;
			File file = new File(dfzcReportPath);
			if (!file.exists()) {
				// 文件不存在，新建
				file.createNewFile();
			}
			OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
			resultWorkbook.write(os);
			os.flush();
			os.close();
			return fileName;
		}
		catch (Exception eIo){
			if (logger.isInfoEnabled()) {
				logger.info("===========报表落地异常:" + eIo.getMessage());
			}
		}
		return fileName;
	}
}
