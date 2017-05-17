package com.coamctech.bxloan.commons.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * <h2>支持2003（xls）2007(xlsx)</h2>
 * <p></p>
 * @author AcoreHeng
 *
 */
public class PoiExcel {
	private Workbook wb;
	private CellStyle defaultCs;
	private String excelName;
	/**
	 * 加载Excel模板
	 * @param exlPath
	 * @return
	 */
	public void loadExcelTemplate(String exlPath){
		try {
			File file=new File(exlPath);
			wb=WorkbookFactory.create(new FileInputStream(file));
			defaultCs=wb.createCellStyle();
			defaultCs.setBorderTop(CellStyle.BORDER_THIN);
			defaultCs.setBorderBottom(CellStyle.BORDER_THIN);
			defaultCs.setBorderLeft(CellStyle.BORDER_THIN);
			defaultCs.setBorderRight(CellStyle.BORDER_THIN);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件不存在");
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param exlPath
	 * @param excelName
	 */
	public void loadExcelTemplate(String exlPath,String excelName){
		this.loadExcelTemplate(exlPath);
		this.excelName=excelName;
	}
	
	/**
	 * 获取Sheet
	 * @param sheetIndex
	 * @param startRow
	 * @return
	 */
	public <E> void initSheetData(int sheetIndex,int startRow,List<E> list,FunctionRowCell<E> function){
		Sheet sheet=wb.getSheetAt(sheetIndex);//获取一个sheet
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		if(startRow<0){
			startRow=0;//起始行号为0
		}
		Row row=null;
		E e=null;
		for (int i = 0,rownum=startRow+i,len=list.size(); i < len; i++,rownum++) {
			row=sheet.getRow(rownum);//获取一行
			e=list.get(i);
			if(row==null){
				row=sheet.createRow(rownum);//新增一行
			}
			function.apply(rownum,row, e);
		}
	}
	/**
	 * 获取Sheet
	 * @param sheetIndex	sheet索引
	 * @param startRow 起始行 
	 * @param startNo	数据计数器开始,默认0
	 * @return counter
	 */
	public <E> SheetInfo initSheetData(int sheetIndex,SheetInfo sheetInfo,List<E> list,FunctionSheetForRowCell<E> function){
		Sheet sheet=wb.getSheetAt(sheetIndex);//获取一个sheet
		if(CollectionUtils.isEmpty(list)){
			return sheetInfo;
		}
		int startRow=sheetInfo.getStartRow()<0?0:sheetInfo.getStartRow();//起始行号为0
		int counter=sheetInfo.getCounter()>0?sheetInfo.getCounter():0;
		E e=null;
		for (int i = 0,rownum=startRow+i,len=list.size(); i < len; i++,rownum++) {
			e=list.get(i);//获取每行数据
			rownum=function.apply(sheet, e, ++counter, rownum);
			startRow=rownum;
		}
		return createSheetInfo(counter, ++startRow);
	}
	/**
	 * @param row
	 * @param cellnum
	 * @return
	 */
	public Cell getCellIfNull(Row row,int cellnum){
		Cell cell=ExcelUtils.getCellIfNull(row, cellnum);
		cell.setCellStyle(defaultCs);
		return cell;
	}
	/**
	 * @param sheetIndex
	 * @return
	 */
	public Sheet getSheet(int sheetIndex){
		return wb.getSheetAt(sheetIndex);
	}
	/**
	 * @param sheet
	 * @param rownum
	 * @return
	 */
	public Row getRowIfNull(Sheet sheet, int rownum) {
		return ExcelUtils.getRowIfNull(sheet, rownum);
	}
	/**
	 * @param os
	 */
	public void write(OutputStream os) {
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {  
            if (os != null) {  
                try {  
                	os.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
		}

	}
	/**
	 * @param counter
	 * @param startRow
	 * @return
	 */
	public SheetInfo createSheetInfo(int counter, int startRow){
		return this.new SheetInfo(counter, startRow);
	}
	/**
	 * @author AcoreHeng
	 *
	 */
	public class SheetInfo{
		private int counter=0;
		private int startRow=0;
		
		private SheetInfo(int counter, int startRow) {
			super();
			this.counter = counter;
			this.startRow = startRow;
		}
		public int getCounter() {
			return counter;
		}
		public int getStartRow() {
			return startRow;
		}
	}
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
}
