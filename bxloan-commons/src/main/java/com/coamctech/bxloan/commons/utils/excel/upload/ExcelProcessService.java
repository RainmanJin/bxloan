/**
 * 
 */
package com.coamctech.bxloan.commons.utils.excel.upload;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：ExcelProcessService
 * 类描述 ：解析excel并生成Vo，如果有错误信息，显示错误信息 
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class ExcelProcessService {
	private static Logger logger = LoggerFactory.getLogger(ExcelProcessService.class);
	
	/**
	 * 功能描述：读取excel选择的sheet
	 * 根据传入类型、文件id和获取解析的结果列表 
	 * @param importName	xml解析ID
	 * @param fileName		文件名称
	 * @param fileType		文件类型
	 * @param sheet			工作表
	 * @return  List<Object>
	 * @throws Exception
	 */
	public List<ExcelImportBaseBo> getExcelResultVoList(String importName, MultipartFile fileItem, String fileType,Integer sheet) throws Exception {
		List<ExcelImportBaseBo> objList = new ArrayList<ExcelImportBaseBo>();
		if (StringUtils.isBlank(importName)) {
			throw new Exception("上传文件类型未设置，请设置文件类型.");
		}
		InputStream in = null;
		// excel配置信息 
		ExcelImportConfigItem configItem = ExcelImportConfig.getConfigByName(importName);
		List<ExcelImportConfigField> configFieldList = configItem.getFieldList();
		Class boClass = configItem.getBoClazz();
		Object resultObj;
		try {
			resultObj = boClass.newInstance();
			if (!(resultObj instanceof ExcelImportBaseBo)) {
				logger.error(boClass.getClass().getName() + "请继承ExcelImportBaseBo");
				throw new Exception("对象解析出错，错误原因请查看log信息.");
			}
		} catch (Exception e) {
			logger.error(boClass.getClass().getName() + "生成实例出错");
			throw new Exception("对象解析出错，错误原因请查看log信息.");
		}
		
		// 解析文件.
		try {
			in = new BufferedInputStream(fileItem.getInputStream());
			if("xlsx".equals(fileType)){
				XlsxExcelReader reader = new XlsxExcelReader(in);
				int rowCount = reader.getExcelRowCount(sheet);
				for (int i = configItem.getStartLine() - 1; i < rowCount; i++) {
					XSSFRow excelRow = reader.getExcelRow(sheet, i);
					XSSFCell cell = excelRow.getCell((byte)0);
					if(null == cell){
						continue;
					}
					//遇见endoffile即结束
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
						 XSSFRichTextString value = cell.getRichStringCellValue();
						 if(ExcelValidateUtil.END_SYMBOL.equals(value.getString())){
							 break;
						 }
					}
					ExcelImportBaseBo bo = getBaseBo(boClass, configFieldList, excelRow);
					bo.setDataLine(i + 1);
					objList.add(bo);
				}
			} else {
				XlsExcelReader reader = new XlsExcelReader(in);
				int rowCount = reader.getExcelRowCount(sheet);		
				for (int i = configItem.getStartLine() - 1; i < rowCount; i++) {
					HSSFRow excelRow = reader.getExcelRow(sheet, i);

					HSSFCell cell = excelRow.getCell((byte)0);
					if(null == cell){
						continue;
					}
					//遇见endoffile即结束
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
						 HSSFRichTextString value = cell.getRichStringCellValue();
						 if(ExcelValidateUtil.END_SYMBOL.equals(value.getString())){
							 break;
						 }
					}
					ExcelImportBaseBo bo = getBaseBo(boClass, configFieldList, excelRow);
					bo.setDataLine(i + 1);
					objList.add(bo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("请导入正常的模板");
		} finally {
			IOUtils.closeQuietly(in);
		}
		return objList;
	}
	
	/**
	 * getBaseBo
	 * 功能描述：读取excel文件并生成对应BO，针对xls格式
	 * 逻辑描述：
	 * @return  ExcelImportBaseBo
	 */
	@SuppressWarnings("deprecation")
	public ExcelImportBaseBo getBaseBo(Class boClass, List<ExcelImportConfigField> configFieldList, HSSFRow row) {
		ExcelImportBaseBo voObj = null;
		try {
			voObj = (ExcelImportBaseBo) boClass.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		for (ExcelImportConfigField configField : configFieldList) {
			 int columnIndex = configField.getColumnIndex();
			 String fieldName = configField.getFieldName();
			 HSSFCell cell = row.getCell((byte)columnIndex);
			 if(null == cell){
				String validateResult = ExcelValidateUtil.validateCellValue(null, configField);
				if (StringUtils.isNotBlank(validateResult)) {
					voObj.addFieldError(fieldName, validateResult);
				}
				continue;
			 }
			 //遇见endoffile即结束
			 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
				 HSSFRichTextString value = cell.getRichStringCellValue();
				 if(ExcelValidateUtil.END_SYMBOL.equals(value.getString())){
					 break;
				 }
			 }
			 try {
				 setBaseObjProperty(cell, boClass, configField, voObj);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 } 
		return voObj;
	}
	
	/**
	 * getBaseBo
	 * 功能描述：读取excel文件并生成对应BO，针对xlsx格式
	 * 逻辑描述：
	 * @return  ExcelImportBaseBo
	 */
	@SuppressWarnings("deprecation")
	public ExcelImportBaseBo getBaseBo(Class boClass, List<ExcelImportConfigField> configFieldList, XSSFRow row) {
		ExcelImportBaseBo voObj = null;
		try {
			voObj = (ExcelImportBaseBo) boClass.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		for (ExcelImportConfigField configField : configFieldList) {
			 int columnIndex = configField.getColumnIndex();
			 String fieldName = configField.getFieldName();
			 XSSFCell cell = row.getCell((byte)columnIndex);
			 if(null == cell){
				String validateResult = ExcelValidateUtil.validateCellValue(null, configField);
				if (StringUtils.isNotBlank(validateResult)) {
					voObj.addFieldError(fieldName, validateResult);
				}
				continue;
			 }
			 //遇见endoffile即结束
			 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
			 	  XSSFRichTextString value = cell.getRichStringCellValue();
			 	 if(ExcelValidateUtil.END_SYMBOL.equals(value.getString())){
					 break;
				 }
			 }
			 try {
				 setBaseObjProperty(cell, boClass, configField, voObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		return voObj;
	}
	
	/**
	 * 读单元格的值并赋值到VO
	 * @param cell
	 * @param value
	 * @throws Exception 
	 */
	private void setBaseObjProperty(XSSFCell cell, Class clazz, ExcelImportConfigField configField, ExcelImportBaseBo obj) throws Exception {
		String fieldName = configField.getFieldName();
		String fieldDesc = configField.getFieldDesc();
		String requiredTypeStr = "";
		Object cellValue = null;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			boolean oldAccessible = field.isAccessible();
			field.setAccessible(true);
			Class c = field.getType();
			if (int.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.setInt(obj, value.intValue());
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.setInt(obj, Integer.parseInt(value.getString()));
					}
				}
			} else if (long.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.setLong(obj, value.longValue());
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.setLong(obj, Long.parseLong(value.getString()));
					}
				}
			} else if (double.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数或小数";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.setDouble(obj, value);
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.setDouble(obj, Double.parseDouble(value.getString()));
					}
				}
			} else if (Integer.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, value.intValue());
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new Integer(value.getString()));
					}
				}
			} else if (Long.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, value.longValue());
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new Long(value.getString()));
					}
				}
			} else if (Double.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数或小数";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, value);
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new Double(value.getString()));
					}
				}
			} else if (BigDecimal.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数或小数";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, new BigDecimal(value.doubleValue()));
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new BigDecimal(Double.parseDouble(value.getString())));
					}
				} else if(cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA){
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, new BigDecimal(value.doubleValue()));
				}
			} else if (String.class.isAssignableFrom(c)) {
				requiredTypeStr = "字符串";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					if (cell.getCellStyle().getDataFormat() == 0) {
						Double value = cell.getNumericCellValue();
						cellValue = value;
						BigDecimal bd = new BigDecimal(value); 
						if(bd.scale() == 0){
							field.set(obj, bd.toString());
						}else{
							field.set(obj, value.toString());
						}
						
					} else {
						Date d = cell.getDateCellValue();
						cellValue = d;
						field.set(obj, CommonHelper.date2Str(d, "yyyy-MM-dd"));
					}
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, value.getString());
					}
				}
			} else if (Date.class.isAssignableFrom(c)) {
				requiredTypeStr = "时间";
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					Date value = cell.getDateCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, value);
					}
				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					XSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, this.string2UtilDate(value.getString(), "yyyy-MM-dd"));
					}
				} else if(cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA){
					Date value = cell.getDateCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, value);
					}
				}
			}
			field.setAccessible(oldAccessible);
			
			String validateResult = ExcelValidateUtil.validateCellValue(cellValue, configField);
			if (StringUtils.isNotBlank(validateResult)) {
				obj.addFieldErrors(fieldName, validateResult);
				return;
			}
			
		} catch (Exception e) {
			obj.addFieldError(fieldName, fieldDesc + "需要：" + requiredTypeStr + "类型，当前值为：" + cellValue);
		}
	}
	
	/**
	 * 读单元格的值并赋值到VO
	 * @param cell
	 * @param value
	 * @throws Exception 
	 */
	private void setBaseObjProperty(HSSFCell cell, Class clazz, ExcelImportConfigField configField, ExcelImportBaseBo obj) throws Exception {
		String fieldName = configField.getFieldName();
		String fieldDesc = configField.getFieldDesc();
		String requiredTypeStr = "";
		Object cellValue = null;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			boolean oldAccessible = field.isAccessible();
			field.setAccessible(true);
			Class c = field.getType();
			if (int.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.setInt(obj, value.intValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.setInt(obj, Integer.parseInt(value.getString()));
					}
				}
			} else if (long.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.setLong(obj, value.longValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.setLong(obj, Long.parseLong(value.getString()));
					}
				}
			} else if (double.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数或小数";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.setDouble(obj, value);
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.setDouble(obj, Double.parseDouble(value.getString()));
					}
				}
			} else if (Integer.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, value.intValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new Integer(value.getString()));
					}
				}
			} else if (Long.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, value.longValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new Long(value.getString()));
					}
				}
			} else if (Double.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数或小数";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, value);
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new Double(value.getString()));
					}
				}
			} else if (BigDecimal.class.isAssignableFrom(c)) {
				requiredTypeStr = "整数或小数";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, new BigDecimal(value.doubleValue()));
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, new BigDecimal(Double.parseDouble(value.getString())));
					}
				} else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
					Double value = cell.getNumericCellValue();
					cellValue = value;
					field.set(obj, new BigDecimal(value.doubleValue()));
				}
			} else if (String.class.isAssignableFrom(c)) {
				requiredTypeStr = "字符串";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					if (cell.getCellStyle().getDataFormat() == 0) {
						Double value = cell.getNumericCellValue();
						cellValue = value;
						BigDecimal bd = new BigDecimal(value); 
						if(bd.scale() == 0){
							field.set(obj, bd.toString());
						}else{
							field.set(obj, value.toString());
						}
						
					} else {
						Date d = cell.getDateCellValue();
						cellValue = d;
						field.set(obj, CommonHelper.date2Str(d, "yyyy-MM-dd"));
					}
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, value.getString());
					}
				}
			} else if (Date.class.isAssignableFrom(c)) {
				requiredTypeStr = "时间";
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					Date value = cell.getDateCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, value);
					}
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					HSSFRichTextString value = cell.getRichStringCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, this.string2UtilDate(value.getString(), "yyyy-MM-dd"));
					}
				} else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
					Date value = cell.getDateCellValue();
					cellValue = value;
					if (value != null) {
						field.set(obj, value);
					}
				}
			}
			field.setAccessible(oldAccessible);
			
			String validateResult = ExcelValidateUtil.validateCellValue(cellValue, configField);
			if (StringUtils.isNotBlank(validateResult)) {
				obj.addFieldErrors(fieldName, validateResult);
				return;
			}
			
		} catch (Exception e) {
			obj.addFieldError(fieldName, fieldDesc + "需要：" + requiredTypeStr + "类型，当前值为：" + cellValue);
		}
	}
	
	/**
	 * 将字符串类型的日期转换为一个Date（java.util.Date）
	 * 
	 * @param dateString
	 *            需要转换为Date的字符串
	 * @param format
	 *            格式化的格式
	 * @return dataTime Date
	 * @throws java.text.ParseException
	 */
	public java.util.Date string2UtilDate(String dateString,
			String format) throws java.text.ParseException {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util类型
		return timeDate;
	}
}
