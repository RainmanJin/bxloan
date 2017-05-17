package com.coamctech.bxloan.commons.utils.excel.upload;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coamctech.bxloan.commons.utils.CommonHelper;



/**   
 * 类名称：XlsxExcelReader
 * 类描述 ：读取excel,针对xlsx格式 
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class XlsxExcelReader {

	private XSSFWorkbook workBook = null;
	
	private static Logger logger = LoggerFactory.getLogger(XlsxExcelReader.class);
	
	public XlsxExcelReader(InputStream in) throws Exception {
		try {
			workBook = new XSSFWorkbook(in);
		} catch (IOException e) {
			logger.error("创建XSSFWorkbook失败");
			logger.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}
	
	/**
	 * 获取文件中sheet的数量
	 * @return
	 */
	public int getSheetCount() {
		if (workBook == null) {
			return 0;
		}
		
		return workBook.getNumberOfSheets();
	}
	
	/**
	 * 获取指定sheet中的记录数量
	 * @param sheetIndex
	 * @return
	 */
	public int getRowsOfSheet(int sheetIndex) throws Exception {
		int rowNum = 0;
		try {
			XSSFSheet sheet = workBook.getSheetAt(sheetIndex);
			rowNum = sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		return rowNum;
	}

	
	/**
	 * getExcelRow
	 * 功能描述：取得excel的某一行记录
	 * 逻辑描述：
	 * @param
	 * @return XSSFRow
	 * @throws Exception
	 * @since Ver 1.00
	 */
	public XSSFRow getExcelRow(int sheetIndex, int rowIndex) throws Exception {
		XSSFSheet sheet = workBook.getSheetAt(sheetIndex);
		int rowCount = sheet.getPhysicalNumberOfRows();
		if (rowIndex > rowCount -1) {
			throw new Exception("读取上传文件出错，行数:"+ rowIndex + "大于excel总记录数");
		}
		return sheet.getRow(rowIndex);
	}
	
	/**
	 * getExcelRowCount
	 * 功能描述：取得excel物理行数
	 * 逻辑描述：
	 * @param
	 * @return  int
	 * @throws 
	 * @since Ver 1.00
	 */
	public int getExcelRowCount(int sheetIndex) {
		XSSFSheet sheet = workBook.getSheetAt(sheetIndex);
		return sheet.getPhysicalNumberOfRows();
	}
}
