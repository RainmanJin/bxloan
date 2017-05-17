package com.coamctech.bxloan.web.tests.excel;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.commons.utils.excel.ExcelUtils;
import com.coamctech.bxloan.commons.utils.excel.FunctionRowCell;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel.SheetInfo;
import com.coamctech.bxloan.service.model.bizapply.compute.ProfitLossInfoVo;
import com.google.common.collect.Lists;

public class ExcelTest {
	private Logger logger=LoggerFactory.getLogger(ExcelTest.class);
	private static final String XLS_ROOT_PATH = "D:\\dev\\test";
	@Test
	public void testExcel() {
		StopWatch sw=new StopWatch();
		sw.start();
		PoiExcel exlExcel=new PoiExcel();
		exlExcel.loadExcelTemplate(XLS_ROOT_PATH+"/wd_approval_tpl.xls");
		List<TestVo> list=getData();
		int sd=3;
		for (int i = 0; i < 80; i++) {
			//初始化第一个sheet
			exlExcel.initSheetData(0, sd, list, new FunctionRowCell<TestVo>() {
				@Override
				public void apply(int dataIndex,Row row, TestVo t) {
					Sheet sheet=row.getSheet();
					dataIndex+=10;
					sheet.addMergedRegion(new CellRangeAddress(4, 6, 4, 4));
					ExcelUtils.getCellIfNull(row, 0).setCellValue(dataIndex+1);
					ExcelUtils.getCellIfNull(row, 1).setCellValue(t.name);
					ExcelUtils.getCellIfNull(row, 2).setCellValue(t.name);
					ExcelUtils.getCellIfNull(row, 3).setCellValue(t.name);
					ExcelUtils.getCellIfNull(row, 4).setCellValue(t.name);
					ExcelUtils.getCellIfNull(row, 5).setCellValue(t.name);
					ExcelUtils.getCellIfNull(row, 6).setCellValue(t.name);
				}
			});
			System.out.println(sd);
			sd+=list.size();
		}
		//初始化第二个sheet
		exlExcel.initSheetData(1, 2, list, new FunctionRowCell<TestVo>() {
			@Override
			public void apply(int dataIndex,Row row, TestVo t) {
				ExcelUtils.getCellIfNull(row, 0).setCellValue(dataIndex+1);
				ExcelUtils.getCellIfNull(row, 1).setCellValue(t.name);
				ExcelUtils.getCellIfNull(row, 2).setCellValue(t.name);
				ExcelUtils.getCellIfNull(row, 3).setCellValue(t.name);
				ExcelUtils.getCellIfNull(row, 4).setCellValue(t.name);
				ExcelUtils.getCellIfNull(row, 5).setCellValue(t.name);
				ExcelUtils.getCellIfNull(row, 6).setCellValue(t.name);
			}
		});
		try {
			exlExcel.write(new FileOutputStream(XLS_ROOT_PATH+"/243234234234234.xls"));
			sw.stop();
			System.out.println(sw.getTime()/1000);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testExcelFormula(){
		StopWatch sw=new StopWatch();
		sw.start();
		PoiExcel poiExcel=new PoiExcel();
		poiExcel.loadExcelTemplate(XLS_ROOT_PATH+"/test.xlsx");
		try {
			Sheet sheet=poiExcel.getSheet(0);
			Row row=sheet.getRow(2);
			ExcelUtils.getCellIfNull(row, 3).setCellValue("2015-09");
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 5));
			row=ExcelUtils.getRowIfNull(sheet, 3);
			ExcelUtils.getCellIfNull(row, 1).setCellValue(2323.234);
			ExcelUtils.getCellIfNull(row, 2).setCellValue(232.234);
			ExcelUtils.getCellIfNull(row, 10).setCellFormula("SUM(A4:E4)");
			poiExcel.write(new FileOutputStream(XLS_ROOT_PATH+"/test01.xlsx"));
			sw.stop();
			logger.info("耗时{}秒",sw.getTime()/1000);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Excel动态表头
	 */
	@Test
	public void testExcelDynamicHead(){
		StopWatch sw=new StopWatch();
		sw.start();
		PoiExcel poiExcel=new PoiExcel();
		poiExcel.loadExcelTemplate(XLS_ROOT_PATH+"/test.xlsx");
		try {
			Sheet sheet=poiExcel.getSheet(0);
			Row row2=ExcelUtils.getRowIfNull(sheet, 1);
			Row row3=ExcelUtils.getRowIfNull(sheet, 2);
			List<String> dtList=this.initDateList("2015-04", "2017-09");
			for (int i = 0; i < dtList.size(); i++) {
				ExcelUtils.getCellIfNull(row2, 3+i).setCellValue(i+1);
				ExcelUtils.getCellIfNull(row3, 3+i).setCellValue(dtList.get(i));
				
			}
			ExcelUtils.getCellIfNull(row2, 3+dtList.size()).setCellValue("合计金额");
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 3+dtList.size(),3+dtList.size()));
			poiExcel.write(new FileOutputStream(XLS_ROOT_PATH+"/test01.xlsx"));
			sw.stop();
			logger.info("耗时{}秒",sw.getTime()/1000);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDateList(){
		System.out.println(initDateList("2015-04", "2015-09"));
	}
	private List<String> initDateList(String startDate,String endDate){
		final String df="yyyy-MM";
		Date startDt=CommonHelper.str2Date(startDate,df);
		Date endDt=CommonHelper.str2Date(endDate,df);
		List<String> list=Lists.newArrayList();
		Calendar c=Calendar.getInstance();
		c.setTime(startDt);
		list.add(CommonHelper.date2Str(c.getTime(), df));
		while (c.getTime().before(endDt)) {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
			list.add(CommonHelper.date2Str(c.getTime(), df));
		}
 		return list;
	}
	
	
	
	
	
	
	private static List<TestVo> getData(){
		ExcelTest t=new ExcelTest();
		List<TestVo> list=Lists.newArrayList();
		TestVo vo=t.new TestVo("发房间的卡萨发生的");
		for (int i = 0; i < 20; i++) {
			list.add(vo);
		}
		return list;
	}
	
	class TestVo{
		private String name;

		public TestVo(String name) {
			super();
			this.name = name;
		}
		
	}
	
	@Test
	public void testList() throws Exception {
		List<String> list=new ArrayList<String>(20);
		BigDecimal bd=new BigDecimal(3244234234L);
		System.out.println(bd.toEngineeringString());
		System.out.println(bd.toPlainString());
		System.out.println(MoneyUtil.formatMoney(bd));
	}
}
