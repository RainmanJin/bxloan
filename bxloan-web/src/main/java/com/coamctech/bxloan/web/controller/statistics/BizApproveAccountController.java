package com.coamctech.bxloan.web.controller.statistics;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.excel.ExcelUtils;
import com.coamctech.bxloan.commons.utils.excel.FunctionSheetForRowCell;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel.SheetInfo;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
import com.coamctech.bxloan.service.model.statistics.BizAppAccItemVo;
import com.coamctech.bxloan.service.model.statistics.BizApproveResultVo;
import com.coamctech.bxloan.service.model.statistics.BizExcelExportVo;
import com.coamctech.bxloan.service.model.statistics.BizPayLoanInfoVo;
import com.coamctech.bxloan.service.model.statistics.BizWfItemVo;
import com.coamctech.bxloan.service.model.statistics.BizWfNodeVo;
import com.coamctech.bxloan.service.statistics.BizApproveAccountService;
import com.google.common.collect.Maps;

/**
 * 业务审批台账
 * @author AcoreHeng
 *
 */
@Controller
@RequestMapping("/bizApproveAccount")
public class BizApproveAccountController extends BaseController {
	private final String STATISTICS="statistics/";
	private final String SEPARATOR_COMMA=",";
	private final static Map<String, String> bizTypeMap; 
	static {
		bizTypeMap=Maps.newHashMap();
		bizTypeMap.put("1", "普通业务");
		bizTypeMap.put("2", "授信业务");
	}
	@Autowired
	private BizApproveAccountService bizApproveAccountService; 
	@Autowired
	private OrgDeptmentService orgDeptmentService;
	@Autowired
	private DataDict dataDict;
	@RequestMapping
	public String index(Model model){
		List<EcOrgDepartment> list=orgDeptmentService.findAllOrgList();
		model.addAttribute("orgs", list);//小贷机构
		return StringUtils.join(STATISTICS,"bizApproveAccount");
	}
	
	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request){
		final Date startDate=CommonHelper.str2Date(request.getParameter("startDate"), CommonHelper.DF_DATE);
		final Date endDate=CommonHelper.str2Date(request.getParameter("endDate"), CommonHelper.DF_DATE);
		final String orgIds=request.getParameter("orgIds");
		//查询
		Page<BizAppAccItemVo> page=bizApproveAccountService.findPageByProj((firstIndex / pageSize),pageSize,startDate,endDate,CommonHelper.str2SetOfLong(orgIds));
		//结果处理
		DataTablesPage dtPage=new DataTablesPage(sEcho, page);
		
		return dtPage;
	}
	/**
	 * 下载Excel
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/downloadExcel")
	@ResponseBody
	public void downloadExcel(HttpServletRequest req,HttpServletResponse resp){
		final Date startDate=CommonHelper.str2Date(req.getParameter("startDate"), CommonHelper.DF_DATE);
		final Date endDate=CommonHelper.str2Date(req.getParameter("endDate"), CommonHelper.DF_DATE);
		final String orgIds=req.getParameter("orgIds");
		PoiExcel poiExcel=new PoiExcel();
		final String EXCEL_TPL="wd_approval_tpl.xlsx";//Excel模板
		final String EXCEL_TPL_PATH=StringUtils.join(ExcelUtils.getTemplatePath(),File.separator,EXCEL_TPL);
		poiExcel.loadExcelTemplate(EXCEL_TPL_PATH);
		final int PAGE_SIZE=1000;
		Set<Long> set_orgIds=CommonHelper.str2SetOfLong(orgIds);
		//查询，//默认查询10000条
		Page<BizExcelExportVo> page=bizApproveAccountService.findPageForExcel(0,PAGE_SIZE,startDate,endDate,set_orgIds);
		SheetInfo sheetInfo1=poiExcel.createSheetInfo(0, 2);
		SheetInfo sheetInfo2=poiExcel.createSheetInfo(0, 2);
		for (int i = 0; i < page.getTotalPages(); i++) {
			if(i>0){
				page=bizApproveAccountService.findPageForExcel(i,PAGE_SIZE,startDate,endDate,set_orgIds);
			}
			sheetInfo1=this.initSheet1(poiExcel, page, sheetInfo1);
			sheetInfo2=this.initSheet2(poiExcel, page,sheetInfo2);
		}
		try {
			poiExcel.write(FileUtils.initDownload(req, resp, "微贷审批台账.xlsx"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 初始化Sheet1
	 * @param poiExcel
	 * @param page
	 * @param sheetInfo
	 * @return
	 */
	private SheetInfo initSheet1(final PoiExcel poiExcel,Page<BizExcelExportVo> page,SheetInfo sheetInfo) {
		return poiExcel.initSheetData(0,sheetInfo, page.getContent(), new FunctionSheetForRowCell<BizExcelExportVo>() {
			@Override
			public int apply(Sheet sheet,BizExcelExportVo t,int dataIndex,int rownum) {
				BizAppAccItemVo appAcc=t.getAppAccItem();
				Row row=ExcelUtils.getRowIfNull(sheet, rownum);
				poiExcel.getCellIfNull(row, 0).setCellValue(dataIndex);//设置序号
				poiExcel.getCellIfNull(row, 1).setCellValue(appAcc.getOrgName());
				poiExcel.getCellIfNull(row, 2).setCellValue(appAcc.getProjectNo());
				poiExcel.getCellIfNull(row, 3).setCellValue(appAcc.getCustomerName());
				poiExcel.getCellIfNull(row, 4).setCellValue(toBizType(appAcc.getBizType()));
				poiExcel.getCellIfNull(row, 5).setCellValue(appAcc.getProductTypeName());
				poiExcel.getCellIfNull(row, 6).setCellValue(appAcc.getProductName());
				poiExcel.getCellIfNull(row, 7).setCellValue(appAcc.getCustomerManagerName());
				if(appAcc.getApplyAmt()!=null){
					poiExcel.getCellIfNull(row, 8).setCellValue(appAcc.getApplyAmt().toString());
				}
				if(appAcc.getYearIrRate()!=null){
					poiExcel.getCellIfNull(row, 9).setCellValue(appAcc.getYearIrRate().movePointRight(2).toString());
				}
				poiExcel.getCellIfNull(row, 10).setCellValue(toTermStr(appAcc.getApplyTerm(), appAcc.getApplyTermUnit()));
				poiExcel.getCellIfNull(row, 11).setCellValue(toGuaranteeMode(appAcc.getGuaranteeMode()));
				poiExcel.getCellIfNull(row, 12).setCellValue(toRepaymentMode(appAcc.getRepayingMode()));
				//流程信息处理
				rownum=this.initWfDataForSheet(row,t.getWfItems(),rownum);
				this.initApproveResultForSheet(row,t.getApproveResult());
				this.initPayLoanInfoForSheet(row,t.getPayLoanInfo());
				return rownum;
			}
			/**
			 * 28
			 * 36
			 * @param row
			 * @param payLoanInfo
			 */
			private void initPayLoanInfoForSheet(Row row, BizPayLoanInfoVo payLoanInfo) {
				if(payLoanInfo==null){
					return;
				}
				poiExcel.getCellIfNull(row, 28).setCellValue(payLoanInfo.getCustomerName());
				poiExcel.getCellIfNull(row, 29).setCellValue(payLoanInfo.getContractNo());
				poiExcel.getCellIfNull(row, 30).setCellValue(CommonHelper.date2Str(payLoanInfo.getPayLoanDate(), CommonHelper.DF_DATE));
				poiExcel.getCellIfNull(row, 31).setCellValue(payLoanInfo.getCustManagerName());
				
			}
			/**
			 * 23-27
			 * 31-35
			 * @param row
			 * @param approveResult
			 */
			private void initApproveResultForSheet(Row row,
					BizApproveResultVo approveResult) {
				if(approveResult==null){
					return;
				}
				if(approveResult.getLoanAmt()!=null){
					poiExcel.getCellIfNull(row, 23).setCellValue(approveResult.getLoanAmt().toString());
				}
				if(approveResult.getYearIrRate()!=null){
					poiExcel.getCellIfNull(row, 24).setCellValue(approveResult.getYearIrRate().movePointRight(2).toString());
				}
				poiExcel.getCellIfNull(row, 25).setCellValue(toTermStr(approveResult.getTerm(), approveResult.getTermUnit()));
				poiExcel.getCellIfNull(row, 27).setCellValue(toRepaymentMode(approveResult.getRepaymentMode()));
				
			}
			/**
			 * 13-22
			 * @param row
			 * @param wfItems
			 */
			private int initWfDataForSheet(Row row,List<BizWfItemVo> wfItems,int rownum) {
				if(CollectionUtils.isNotEmpty(wfItems)){
					int START_INDEX=13;
					Sheet sheet=row.getSheet();
					CellStyle cellStyle=sheet.getWorkbook().createCellStyle();
					cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					cellStyle.setBorderTop(CellStyle.BORDER_THIN);
					cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
					cellStyle.setBorderRight(CellStyle.BORDER_THIN);
					//设置跨行
					int mergerRow= wfItems.size()-1;
					for (int i = 0; mergerRow>0&&i < 34; i++) {
						if(i==START_INDEX){
							i+=10;
						}
						CellRangeAddress region= new CellRangeAddress(rownum, rownum+mergerRow, i,i);
						sheet.addMergedRegion(region);
						ExcelUtils.setRegionStyle(sheet,region, cellStyle);
					}
					int startIndex=START_INDEX;
					for (int i = 0,len=wfItems.size(); i < len; i++) {
						BizWfItemVo wfItem=wfItems.get(i);
						startIndex=START_INDEX;
						if(i>0){
							row=ExcelUtils.getRowIfNull(sheet, ++rownum);
						}
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(i+1);//设置序号
						BizWfNodeVo wfNode=wfItem.getWfNodes().get(0);//分配岗
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(CommonHelper.date2Str(wfNode.getReceiveTime(), CommonHelper.DF_DATE_SHORT_TIME));
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandleResult());
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(CommonHelper.date2Str(wfNode.getHandleTime(), CommonHelper.DF_DATE_SHORT_TIME));
						if(wfItem.getWfNodes().size()==1){
							continue;
						}
						wfNode=wfItem.getWfNodes().get(1);//一级审批
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandlePersonName());
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandleResult());
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(CommonHelper.date2Str(wfNode.getHandleTime(), CommonHelper.DF_DATE_SHORT_TIME));
						if(wfItem.getWfNodes().size()==2){
							continue;
						}
						wfNode=wfItem.getWfNodes().get(2);//二级审批
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandlePersonName());
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandleResult());
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(CommonHelper.date2Str(wfNode.getHandleTime(), CommonHelper.DF_DATE_SHORT_TIME));
					}
				}
				return rownum;
				
			}
		});
		
	}
	
	/**
	 * @param poiExcel
	 * @param page
	 * @param sheetInfo
	 * @return
	 */
	private SheetInfo initSheet2(final PoiExcel poiExcel, Page<BizExcelExportVo> page,
			SheetInfo sheetInfo) {
		return poiExcel.initSheetData(1, sheetInfo, page.getContent(), new FunctionSheetForRowCell<BizExcelExportVo>() {
			@Override
			public int apply(Sheet sheet,BizExcelExportVo t,int dataIndex,int rownum) {
				BizAppAccItemVo appAcc=t.getAppAccItem();
				Row row=ExcelUtils.getRowIfNull(sheet, rownum);
				poiExcel.getCellIfNull(row, 0).setCellValue(dataIndex);
				poiExcel.getCellIfNull(row, 1).setCellValue(appAcc.getProjectNo());
				BizPayLoanInfoVo payLoanInfo=t.getPayLoanInfo();
				if(payLoanInfo!=null){
					poiExcel.getCellIfNull(row, 2).setCellValue(payLoanInfo.getContractNo());
				}
				poiExcel.getCellIfNull(row, 3).setCellValue(appAcc.getCustomerName());
				poiExcel.getCellIfNull(row, 4).setCellValue(appAcc.getBizType());
				poiExcel.getCellIfNull(row, 5).setCellValue(appAcc.getProductTypeName());
				poiExcel.getCellIfNull(row, 6).setCellValue(appAcc.getProductName());
				if(appAcc.getApplyAmt()!=null){
					poiExcel.getCellIfNull(row, 7).setCellValue(appAcc.getApplyAmt().toString());
				}
				poiExcel.getCellIfNull(row, 8).setCellValue(toGuaranteeMode(appAcc.getGuaranteeMode()));
				poiExcel.getCellIfNull(row, 9).setCellValue(appAcc.getOrgName());
				poiExcel.getCellIfNull(row, 10).setCellValue(appAcc.getCustomerManagerName());
				//流程信息处理
				rownum=this.initWfDataForSheet(row,t.getWfItems(),rownum);
				return rownum;
			}

			/**
			 * 11-13
			 * @param row
			 * @param wfItems
			 */
			private int initWfDataForSheet(Row row, List<BizWfItemVo> wfItems,int rownum) {
				if(CollectionUtils.isNotEmpty(wfItems)){
					final int START_INDEX=11;//起始列
					Sheet sheet=row.getSheet();
					CellStyle cellStyle=sheet.getWorkbook().createCellStyle();
					cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					cellStyle.setBorderTop(CellStyle.BORDER_THIN);
					cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
					cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
					cellStyle.setBorderRight(CellStyle.BORDER_THIN);
					//设置跨行
					int mergerRow= wfItems.size()-1;
					for (int i = 0; mergerRow>0&&i < START_INDEX; i++) {
						CellRangeAddress region= new CellRangeAddress(rownum, rownum+mergerRow, i,i);
						sheet.addMergedRegion(region);
						ExcelUtils.setRegionStyle(sheet,region, cellStyle);
					}
					for (int i = 0,len=wfItems.size(); i < len; i++) {//只处理两次
						int startIndex=START_INDEX;
						if(i>0){
							row=ExcelUtils.getRowIfNull(sheet, ++rownum);
						}
						BizWfItemVo wfItem=wfItems.get(i);
						if(wfItem.getWfNodes().size()==1){
							continue;
						}
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(i+1);
						BizWfNodeVo wfNode=wfItem.getWfNodes().get(1);//一级审批
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandlePersonName());
						if(wfItem.getWfNodes().size()==2){
							poiExcel.getCellIfNull(row, ++startIndex).setCellValue(wfNode.getHandleResult());
							continue;
						}
						wfNode=wfItem.getWfNodes().get(2);//二级审批
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandlePersonName());
						poiExcel.getCellIfNull(row, startIndex++).setCellValue(wfNode.getHandleResult());
					}
					
				}
				return rownum;
			}
		});
		
	}
	/**
	 * 默认为普通业务
	 * @param bizType
	 * @return
	 */
	private String toBizType(String bizType){
		if(StringUtils.isBlank(bizType)){
			return bizTypeMap.get("1");
		}
		return bizTypeMap.get(bizType);
	}
	/**
	 * 贷款期限
	 * @param term
	 * @param termUnit
	 * @return
	 */
	private String toTermStr(Integer term,Integer termUnit){
		if(term==null){
			return StringUtils.EMPTY;
		}
		return term+dataDict.getCodeName("TermUnitCd", String.valueOf(termUnit));
	}
	/**
	 * 还款方式
	 * @param repaymentMode
	 * @return
	 */
	private String toRepaymentMode(String repaymentMode){
		if(StringUtils.isBlank(repaymentMode)){
			return StringUtils.EMPTY;
		}
		return dataDict.getCodeName("RepaymentMode", repaymentMode);
	}
	/**
	 * 担保方式转换
	 * @param guaranteeMode
	 * @return
	 */
	private String toGuaranteeMode(String guaranteeMode){
		if(StringUtils.isBlank(guaranteeMode)){
			return StringUtils.EMPTY;
		}
		String[] strs=StringUtils.split(guaranteeMode,SEPARATOR_COMMA);
		for (int i = 0,len=strs.length; i < len; i++) {
			strs[i]=dataDict.getCodeName("CdsGuarantMode", strs[i]);
		}
		return StringUtils.join(strs,SEPARATOR_COMMA);
	}
	
}
