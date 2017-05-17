package com.coamctech.bxloan.commons.utils.excel;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.core.io.ClassPathResource;

/**
 * @author AcoreHeng
 *
 */
public class ExcelUtils {
	private static String CLASS_PATH_ROOT;
	public static final String TEMPLATE = "template";
	static {
		try {
			CLASS_PATH_ROOT = new ClassPathResource(TEMPLATE).getFile()
					.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public static String getTemplatePath() {
		return CLASS_PATH_ROOT;
	}

	/**
	 * 如果cell为空则自动创建
	 * 
	 * @param row
	 * @param cellnum
	 * @return
	 */
	public static Cell getCellIfNull(Row row, int cellnum) {
		Cell cell = row.getCell(cellnum);
		if (cell == null) {
			cell = row.createCell(cellnum);
		}
		return cell;
	}

	/**
	 * 如果Row为空则自动创建
	 * 
	 * @param row
	 * @param cellnum
	 * @return
	 */
	public static Row getRowIfNull(Sheet sheet, int rownum) {
		Row row = sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}
		return row;
	}

	/**
	 * 合并单元格设置样式
	 * @param sheet
	 * @param region
	 * @param cs
	 */
	public static void setRegionStyle(Sheet sheet, CellRangeAddressBase region,
			CellStyle cs) {
		for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			Row row = CellUtil.getRow(i, sheet);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				Cell cell = CellUtil.getCell(row, (short) j);
				cell.setCellStyle(cs);
			}
		}
	}
	
	public static String convertToColName(int colIndex){

		String strRtn = "";
		if (colIndex <= 0)
			return strRtn;
		int numMod = 0;
		int numTemp = colIndex;
		char ch = 'A';
		do {
			numMod = numTemp % 26;
			numTemp = numTemp / 26;
			if (numMod == 0) {
				ch = 'Z';
				numTemp--;

			}
			else
				ch = (char) ('A' + numMod - 1);
			strRtn = ch + strRtn;
		}
		while (numTemp > 0);
		return strRtn;
	}
	
	/**
	 * 行求和
	 * @param cellnum
	 * @param rownum
	 * @param rowSize
	 * @return
	 */
	public static String getSumOfRow(int cellnum,int rownum,int rowSize){
		if(rowSize<=0||cellnum<=0){
			return "";
		}
		String cell=ExcelUtils.convertToColName(cellnum);
		return StringUtils.join("SUM(",cell,String.valueOf(rownum),":",cell,String.valueOf(rownum+rowSize-1),")");
	}
	/**
	 * 列求和
	 * @param cellnum
	 * @param rownum
	 * @param cellSize
	 * @return
	 */
	public static String getSumOfCell(int cellnum,int rownum,int cellSize){
		if(cellSize<=0||cellnum<=0){
			return "";
		}
		return StringUtils.join("SUM(",ExcelUtils.convertToColName(cellnum),String.valueOf(rownum),":",ExcelUtils.convertToColName(cellnum+cellSize-1),String.valueOf(rownum),")");
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader()
				.getResource("/"));
		System.out.println(Thread.currentThread().getContextClassLoader()
				.getResource("/"));
		System.out.println(Thread.currentThread().getContextClassLoader()
				.getResource("").getPath());
	}
}
