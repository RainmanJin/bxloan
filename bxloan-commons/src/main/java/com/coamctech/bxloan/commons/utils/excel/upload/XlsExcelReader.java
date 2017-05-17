package com.coamctech.bxloan.commons.utils.excel.upload;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：XlsExcelReader
 * 类描述 ：读取excel,针对xls格式 
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class XlsExcelReader {

	private HSSFWorkbook workBook = null;
	
	private static Logger logger = LoggerFactory.getLogger(XlsExcelReader.class);
	
	public XlsExcelReader(InputStream in) throws Exception {
		try {
			workBook = new HSSFWorkbook(in);
		} catch (IOException e) {
			logger.error("创建HSSFWorkbook失败");
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
			HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
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
	 * @return HSSFRow
	 * @throws Exception
	 * @since Ver 1.00
	 */
	public HSSFRow getExcelRow(int sheetIndex, int rowIndex) throws Exception {
		HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
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
		HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
		return sheet.getPhysicalNumberOfRows();
	}
}
