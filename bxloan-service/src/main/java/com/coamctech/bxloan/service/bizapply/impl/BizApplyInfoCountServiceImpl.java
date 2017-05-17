package com.coamctech.bxloan.service.bizapply.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.commons.utils.excel.ExcelUtils;
import com.coamctech.bxloan.commons.utils.excel.FunctionRowCell;
import com.coamctech.bxloan.commons.utils.excel.FunctionSheetForRowCell;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel.SheetInfo;
import com.coamctech.bxloan.dao.FamilyConsumeDao;
import com.coamctech.bxloan.dao.OtherIncomeDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.CommonInfo;
import com.coamctech.bxloan.entity.ExpectedCashFlowInfo;
import com.coamctech.bxloan.entity.FamilyConsume;
import com.coamctech.bxloan.entity.OtherIncome;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.bizapply.BizApplyInfoCountService;
import com.coamctech.bxloan.service.bizapply.BizFamilyAssetsService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.loanprocess.InterestCalCulateService;
import com.coamctech.bxloan.service.loanprocess.InterestCalCulateServiceForBusiness;
import com.coamctech.bxloan.service.model.bizapply.compute.AssetLiabilityInfoVo;
import com.coamctech.bxloan.service.model.bizapply.compute.AssetLiabilityInfoVo.AssetLiabilityItemVo;
import com.coamctech.bxloan.service.model.bizapply.compute.BizExpectedCashFlowVo;
import com.coamctech.bxloan.service.model.bizapply.compute.BizExpectedCashFlowVo.ItemOfEcfiVo;
import com.coamctech.bxloan.service.model.bizapply.compute.ProfitLossInfoVo;
import com.coamctech.bxloan.service.model.bizapply.compute.ProfitLossItemVo;
import com.coamctech.bxloan.service.model.loanprocess.InterestCalCulateForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Service
public class BizApplyInfoCountServiceImpl implements BizApplyInfoCountService{
	private Logger logger=LoggerFactory.getLogger(BizApplyInfoCountServiceImpl.class);
	private final String AGRO_BIZ_TYPE="AgroBizType";
	private final String CODE_INCOME_TYPE="IncomeType";
	private final String DF_YYYY_MM="yyyy-MM";
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	@Autowired
	private InterestCalCulateServiceForBusiness interestCalCulateServiceForBusiness;
	@Autowired
	private BizFamilyAssetsService bizFamilyAssetsService;
	
	@Autowired
	private OtherIncomeDao otherIncomeDao;
	@Autowired
	private FamilyConsumeDao  familyConsumeDao;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	
	/**
	 * 损失表数据
	 * @param projId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public ProfitLossInfoVo buildProfitLossInfo(Long projId) {
		ProfitLossInfoVo infoVo=null;
		String bizType=bizApplyQueryService.findAgroBizTypeByProjId(projId);
		logger.info("BizApply AgroBizType:{}",bizType);
		if(StringUtils.equals(dataDict.getCodeVal(AGRO_BIZ_TYPE, "S1"), bizType)){//农业
			infoVo=new ProfitLossInfoVo<ProfitLossItemVo>(){
				@Override
				public ProfitLossItemVo newInstance() {
					return new ProfitLossItemVo();
				}
				
			};
			List<ProfitLossItemVo> incomeList=Lists.newArrayList();
			final String FUTURE_PAST_TYPE_1="1";//过去
			final String FUTURE_PAST_TYPE_2="2";//过去
			//农业收入合计
			incomeList.add(new ProfitLossItemVo(this.gatherAgroIncomeOfAgro(projId, false), this.gatherAgroIncomeOfAgro(projId, true)));
			incomeList.add(new ProfitLossItemVo(this.getIndustryCommerceIncomeTotal(projId,bizType, FUTURE_PAST_TYPE_1), this.getIndustryCommerceIncomeTotal(projId,bizType, FUTURE_PAST_TYPE_2)));
			incomeList.add(new ProfitLossItemVo(this.getOtherIncome(projId,bizType, FUTURE_PAST_TYPE_1, "1"), this.getOtherIncome(projId,bizType, FUTURE_PAST_TYPE_2, "1")));//工薪收入
			incomeList.add(new ProfitLossItemVo(this.getOtherIncome(projId,bizType, FUTURE_PAST_TYPE_1, "2"), this.getOtherIncome(projId,bizType, FUTURE_PAST_TYPE_2, "2")));//其他收入
			List<ProfitLossItemVo> operatCosts=Lists.newArrayList();
			//农业成本合计
			operatCosts.add(new ProfitLossItemVo(this.gatherAgroCostOfAgroByGq(projId), this.gatherAgroCostOfAgroByWL(projId)));
			operatCosts.add(new ProfitLossItemVo(this.getIndustryCommerceCostTotal(projId,bizType, "1"), this.getIndustryCommerceCostTotal(projId,bizType, "2")));
			FamilyConsume fc=familyConsumeDao.findByProjectIdAndType(projId, bizType);
			infoVo.initData(incomeList,operatCosts,fc);
		}else if(StringUtils.equals(dataDict.getCodeVal(AGRO_BIZ_TYPE, "S2"), bizType)){//非农业
			infoVo=new ProfitLossInfoVo<BigDecimal>(){
				@Override
				public BigDecimal newInstance() {
					return new BigDecimal(0);
				}};
			List<BigDecimal> incomeList=Lists.newArrayList();
			incomeList.add(this.gatherAgroIncomeOfNoAgro(projId));
			incomeList.add(this.getIndustryCommerceIncomeTotal(projId, bizType, null));
			incomeList.add(this.getOtherIncome(projId,bizType, null, "1"));
			incomeList.add(this.getOtherIncome(projId,bizType, null, "2"));
			List<BigDecimal> operatCosts=Lists.newArrayList();
			operatCosts.add(this.gatherAgroCostOfNoAgro(projId));
			operatCosts.add(this.getIndustryCommerceCostTotalOfNoAgro(projId,bizType));
			FamilyConsume fc=familyConsumeDao.findByProjectIdAndType(projId, bizType);
			infoVo.initData(incomeList,operatCosts,fc);
		}
		return infoVo;
	}

	@Override
	public AssetLiabilityInfoVo buildAsseLliabilityInfo(Long projId) {
		String bizType=bizApplyQueryService.findAgroBizTypeByProjId(projId);
		AssetLiabilityInfoVo aliVo=new AssetLiabilityInfoVo();
		aliVo.initBizNoFixedAsset(this.findBizNoFixedAssetLiab(projId, bizType));
		BizFixedAssetCountVo fixedAssetCountVo=this.gatherBizFixedAsset(projId, bizType);
		aliVo.initBizFixedAsset(fixedAssetCountVo.getHouseProperty(), fixedAssetCountVo.getVehicle(), fixedAssetCountVo.getProductionFacility());
		if(StringUtils.equals(dataDict.getCodeVal(AGRO_BIZ_TYPE, "S1"), bizType)){//农业
			aliVo.initStockInfo(this.gatherAgroOfAgro(projId), this.gatherTotalStockOfAgro(projId,bizType,"1"));//工商业存货
		}else if(StringUtils.equals(dataDict.getCodeVal(AGRO_BIZ_TYPE, "S2"), bizType)){//非农业
			aliVo.initStockInfo(this.gatherAgroOfNoAgro(projId), this.gatherTotalStockOfNoAgro(projId));
		}
		return aliVo;
	}
	

	@Override
	public BigDecimal findCashDepositByBizNfal(Long projId) {
		String bizType=bizApplyQueryService.findAgroBizTypeByProjId(projId);
		Map<String,Object[]> map=this.findBizNoFixedAssetLiab(projId, bizType);
		Object[] objs=map.get("ND_NO_FIEXED_ASSET_NAME_01001");
		if(objs==null){
			return null;
		}else{
			return CommonHelper.toBigDecimal(objs[0]);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PoiExcel createProfitLossAsseLliabExcel(Long projId) {
		final String agroBizType=bizApplyQueryService.findAgroBizTypeByProjId(projId);//农业业务类型
		final PoiExcel poiExcel=new PoiExcel();
		//设置申请人及申请金额
		ProfitLossInfoVo profitLossInfoVo=this.buildProfitLossInfo(projId);
		BigDecimal earnedProfit=profitLossInfoVo.getEarnedProfit();//经营利润；
		int sheetIndex=0;
		if(StringUtils.equals(dataDict.getCodeVal(AGRO_BIZ_TYPE, "S1"), agroBizType)){//农业
			poiExcel.loadExcelTemplate(StringUtils.join(ExcelUtils.getTemplatePath(),File.separator,"LoanQuestionary_agro.xlsx"),"申请调查表（农业为主）.xlsx");
			List<ProfitLossItemVo> list=profitLossInfoVo.getData();//损失计提
			//处理预期现金流Sheet
			this.initEcfi(projId,poiExcel,sheetIndex++);
			//处理损失Sheet
			poiExcel.initSheetData(sheetIndex++, 4, list, new FunctionRowCell<ProfitLossItemVo>() {
				@Override
				public void apply(int rownum, Row row, ProfitLossItemVo t) {
					logger.info("RowNum:{},Data:{}",rownum,t);
					if(t!=null){
						poiExcel.getCellIfNull(row, 3).setCellValue(toBigDecimal(t.getBeforetimeVal()));
						poiExcel.getCellIfNull(row, 4).setCellValue(toBigDecimal(t.getAftertimeVal()));
						poiExcel.getCellIfNull(row, 5).setCellValue(toBigDecimal(t.getContrastResult()));
					}
				}
			});
		}else if(StringUtils.equals(dataDict.getCodeVal(AGRO_BIZ_TYPE, "S2"), agroBizType)){//非农业
			poiExcel.loadExcelTemplate(StringUtils.join(ExcelUtils.getTemplatePath(),File.separator,"LoanQuestionary_no_agro.xlsx"),"申请调查表（非农业为主）.xlsx");
			List<BigDecimal> list=profitLossInfoVo.getData();
			//处理损失Sheet
			poiExcel.initSheetData(sheetIndex++, 4, list, new FunctionRowCell<BigDecimal>() {
				@Override
				public void apply(int rownum, Row row, BigDecimal t) {
					logger.info("RowNum:{},Data:{}",rownum,t);
					if(t==null||!BigDecimal.ZERO.equals(t)){
						poiExcel.getCellIfNull(row, 3).setCellValue(toBigDecimal(t));
					}
					
				}
			});
		}else{
			throw new NullPointerException("未识别农贷业务申请类型");
		}
		BizApplyBo applyBo=this.findBizApplyByProjId(projId);
		BigDecimal applyAmt=null;//申请总贷款
		Sheet sheet=null;
		Row row=null;
		if(applyBo!=null){
			sheet=poiExcel.getSheet(1);
			row=poiExcel.getRowIfNull(sheet, 1);
			poiExcel.getCellIfNull(row, 1).setCellValue(applyBo.getCustomerName());
			row=poiExcel.getRowIfNull(sheet, 29);
			applyAmt=applyBo.getApplyAmount();
			poiExcel.getCellIfNull(row, 3).setCellValue(this.toBigDecimal(applyBo.getApplyAmount()));
		}
		AssetLiabilityInfoVo vo=this.buildAsseLliabilityInfo(projId);//资产负债
		poiExcel.initSheetData(sheetIndex++, 3, vo.toList(agroBizType, earnedProfit, applyAmt), new FunctionRowCell<AssetLiabilityItemVo>() {
			@Override
			public void apply(int rownum, Row row, AssetLiabilityItemVo itemVo) {
				logger.info("RowNum:{},Data:{}",rownum,itemVo);
				if(itemVo!=null){
					poiExcel.getCellIfNull(row, 3).setCellValue(toBigDecimal(itemVo.getAmount()));
					if(StringUtils.isNoneBlank(itemVo.getRemark())){
						poiExcel.getCellIfNull(row, 4).setCellValue(itemVo.getRemark());
					}
				}
				
			}
		});
		return poiExcel;
	}
	/**
	 * 初始化预期现金流数据
	 * @param projId
	 * @param poiExcel
	 * @param sheetIndex
	 */
	private void initEcfi(Long projId, final PoiExcel poiExcel,final int sheetIndex) {
		final List<String> dtList=this.initDateListOfEcfi(projId);
		Sheet sheet=poiExcel.getSheet(sheetIndex);
		Row row2=ExcelUtils.getRowIfNull(sheet, 1);
		Row row3=ExcelUtils.getRowIfNull(sheet, 2);
		for (int i = 0; i < dtList.size(); i++) {
			ExcelUtils.getCellIfNull(row2, 3+i).setCellValue(i+1);
			ExcelUtils.getCellIfNull(row3, 3+i).setCellValue(dtList.get(i));
			
		}
		ExcelUtils.getCellIfNull(row2, 3+dtList.size()).setCellValue("合计金额");
		BigDecimal cashDeposit=this.findCashDepositByBizNfal(projId);
		Cell cell=ExcelUtils.getCellIfNull(row3, 0);
		if(cashDeposit!=null){
			cell.setCellValue(cashDeposit.doubleValue());
		}
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3+dtList.size(),3+dtList.size()));
		BizExpectedCashFlowVo ecfiVo=this.handleItemOfEcfi(projId);
		if(ecfiVo==null){
			return;
		}
		final int dateSize=dtList.size();
		//收入
		Row row=ExcelUtils.getRowIfNull(sheet, 3);
		ExcelUtils.getCellIfNull(row,0).setCellValue("+家庭收入");
		sheet.addMergedRegion(new CellRangeAddress(3, 2+ ecfiVo.getIncomes().size(), 0,0));
		SheetInfo sheetInfo=poiExcel.initSheetData(sheetIndex, poiExcel.createSheetInfo(0, 3), ecfiVo.getIncomes(), new FunctionSheetForRowCell<ItemOfEcfiVo>() {
			@Override
			public int apply(Sheet sheet, ItemOfEcfiVo t, int dataIndex,
					int rownum) {
				Row row=ExcelUtils.getRowIfNull(sheet, rownum);
				BigDecimal bd=null;
				Cell cell=null;
				int cellIndex=1;
				poiExcel.getCellIfNull(row, cellIndex++).setCellValue(dataIndex);//设置序号
				cell=poiExcel.getCellIfNull(row, cellIndex++);
				cell.setCellValue(t.getName());
				for (String key : dtList) {
					bd=t.getTotalAmt(key);//获取该月总和
					cell=poiExcel.getCellIfNull(row, cellIndex++);
					if(bd!=null){
						cell.setCellValue(bd.doubleValue());	
					}
				}
				cell=poiExcel.getCellIfNull(row, cellIndex++);
				cell.setCellFormula(ExcelUtils.getSumOfCell(4, rownum+1, dateSize));
				return rownum;
			}
		});
		//收入合计
		int rownum=sheetInfo.getStartRow();
		int rowNumOfIncomeSum=rownum+1;///收入合计行号
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("收入现金流入合计");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		int cellIndex=3;
		for (int i=0;i<dateSize;i++) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			cell.setCellFormula(ExcelUtils.getSumOfRow(cellIndex, 4, ecfiVo.getIncomes().size()));
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		cell.setCellFormula(ExcelUtils.getSumOfCell(4, rownum+1, dateSize));
		//支出
		row=ExcelUtils.getRowIfNull(sheet, rownum+1);
		ExcelUtils.getCellIfNull(row,0).setCellValue("-生产支出");
		sheet.addMergedRegion(new CellRangeAddress(rownum+1, rownum+ ecfiVo.getExpends().size(), 0,0));
		sheetInfo=poiExcel.initSheetData(sheetIndex, poiExcel.createSheetInfo(0, rownum+1), ecfiVo.getExpends(), new FunctionSheetForRowCell<ItemOfEcfiVo>() {
			@Override
			public int apply(Sheet sheet, ItemOfEcfiVo t, int dataIndex,
					int rownum) {
				Row row=ExcelUtils.getRowIfNull(sheet, rownum);
				BigDecimal bd=null;
				Cell cell=null;
				int cellIndex=1;
				poiExcel.getCellIfNull(row, cellIndex++).setCellValue(dataIndex);//设置序号
				cell=poiExcel.getCellIfNull(row, cellIndex++);
				cell.setCellValue(t.getName());
				for (String key : dtList) {
					bd=t.getAmtMoneyMap().get(key);
					cell=poiExcel.getCellIfNull(row, cellIndex++);
					if(bd!=null){
						cell.setCellValue(bd.doubleValue());	
					}
				}
				cell=poiExcel.getCellIfNull(row, cellIndex++);
				cell.setCellFormula(ExcelUtils.getSumOfCell(4, rownum+1, dateSize));
				return rownum;
			}
		});
		//家庭支出
		rownum=sheetInfo.getStartRow();
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		FamilyConsume fc=familyConsumeDao.findByProjectIdAndType(projId, dataDict.getCodeVal(AGRO_BIZ_TYPE, "S1"));
		BigDecimal amtOfMonth=BigDecimal.ZERO;//每月家庭支出
		if(fc!=null&&fc.getFamilyConsumeTotal()!=null){
			if(fc.getTuition()!=null){//学杂费不为空时，
				amtOfMonth=fc.getFamilyConsumeTotal().subtract(fc.getTuition()).divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
			}else{
				amtOfMonth=fc.getFamilyConsumeTotal().divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
			}
		}
		ExcelUtils.getCellIfNull(row,0).setCellValue("家庭支出");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		ItemOfEcfiVo hs=ecfiVo.getHouseholdSpending();
		final boolean amtMoneyMapFlagOfHs=hs!=null;//是否有数据
		final boolean amtOfMonthFlag=amtOfMonth!=null&&!amtOfMonth.equals(BigDecimal.ZERO);//每月的家庭生活消费可用
		for (String key : dtList) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			BigDecimal bd=null;
			if(amtMoneyMapFlagOfHs){
				bd=hs.getAmtMoneyMap().get(key);
			}
			boolean bdFlag=bd!=null&&!bd.equals(BigDecimal.ZERO);//指定月生活消费可用
			if(bdFlag&&amtOfMonthFlag){
				cell.setCellValue(amtOfMonth.add(bd).doubleValue());//每月的家庭生活消费加上指定月的学费
			}else if(bdFlag){
				cell.setCellValue(bd.doubleValue());//指定月的学费
			}else{
				cell.setCellValue(amtOfMonth.doubleValue());//每月的家庭生活消费
			}
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		cell.setCellFormula(ExcelUtils.getSumOfCell(4, rownum+1, dateSize));
		//其他贷款还款
		rownum++;
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("其他贷款还款");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		ItemOfEcfiVo otherPayments=ecfiVo.getOtherPayments();
		final boolean amtMoneyMapFlagOfOtherPayments=otherPayments!=null;//是否有数据
		for (String key : dtList) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			BigDecimal bd=null;
			if(amtMoneyMapFlagOfOtherPayments){
				bd=otherPayments.getAmtMoneyMap().get(key);
				
			}
			if(bd!=null&&!bd.equals(BigDecimal.ZERO)){
				cell.setCellValue(bd.doubleValue());;
			}
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		cell.setCellFormula(ExcelUtils.getSumOfCell(4, rownum+1, dateSize));
		//支出现金流出合计
		rownum++;
		int rownumOfExpendsSum=rownum+1;
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("支出现金流出合计");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		for (int i=0;i<dateSize;i++) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			cell.setCellFormula(ExcelUtils.getSumOfRow(cellIndex, rowNumOfIncomeSum+1, ecfiVo.getExpends().size()+2));
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		cell.setCellFormula(ExcelUtils.getSumOfCell(4, rownum+1, dateSize));
		//月末现金流余额
		rownum++;
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("月末现金流余额");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		for (int i=0;i<dateSize;i++) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			String cellName=ExcelUtils.convertToColName(cellIndex);
			cell.setCellFormula(StringUtils.join(cellName,String.valueOf(rowNumOfIncomeSum),"-",cellName,String.valueOf(rownumOfExpendsSum)));
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		//cell.setCellFormula(ExcelUtils.getSumOfCell(4, rownum+1, dateSize));
		//贷款前现金流余额
		rownum++;
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("贷款前现金流余额");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		String preCellName="A3";//调查前现金及存款余额（单元格）
		for (int i=0;i<dateSize;i++) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			String cellName=ExcelUtils.convertToColName(cellIndex);
			cell.setCellFormula(StringUtils.join(preCellName,"+",cellName,String.valueOf(rownum)));
			preCellName=StringUtils.join(cellName,String.valueOf(rownum+1));
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		//贷款资金流入
		rownum++;
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("贷款资金流入");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		ItemOfEcfiVo loan=ecfiVo.getLoan();//贷款流入
		for (String key:dtList) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			if(loan.getAmtMoneyMap().containsKey(key)){
				BigDecimal bd=loan.getAmtMoneyMap().get(key);
				if(bd!=null){
					cell.setCellValue(bd.doubleValue());//贷款发放金额
				}
			}
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		//贷款还款现金流出
		rownum++;
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("贷款还款现金流出");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		ItemOfEcfiVo paymentOfLoan=ecfiVo.getPaymemtOfLoan();//贷款还款现金流出
		for (String key:dtList) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			BigDecimal bd=paymentOfLoan.getAmtMoneyMap().get(key);
			if(bd!=null){
				cell.setCellValue(bd.doubleValue());//贷款发放金额
			}
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
		//贷款后现金流余额
		rownum++;
		row=ExcelUtils.getRowIfNull(sheet,rownum);
		ExcelUtils.getCellIfNull(row,0).setCellValue("贷款后现金流余额");
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum, 0,2));
		cellIndex=3;
		preCellName=StringUtils.EMPTY;
		for (int i=0;i<dateSize;i++) {
			cell=poiExcel.getCellIfNull(row, cellIndex++);
			String cellName=ExcelUtils.convertToColName(cellIndex);
			if(i==0){
				cell.setCellFormula(StringUtils.join(cellName,String.valueOf(rownum-2),"+",cellName,String.valueOf(rownum-1),"-",cellName,String.valueOf(rownum)));
			}else{
				cell.setCellFormula(StringUtils.join(preCellName,
						String.valueOf(rownum+1), "+", cellName,
						String.valueOf(rownum - 3), "+", cellName,
						String.valueOf(rownum - 1), "-", cellName,
						String.valueOf(rownum)));
			}
			preCellName=cellName;
		}
		cell=poiExcel.getCellIfNull(row, cellIndex++);
	}
	

	/**
	 * 查询非固定资产及其负债
	 * @param projId
	 * @param bizType
	 * @return
	 */
	private Map<String,Object[]> findBizNoFixedAssetLiab(Long projId,String bizType){
		StringBuffer sb=new StringBuffer();
		sb.append("select");
		sb.append(" (select sdi.code from sys_dict_item sdi where sdi.value = bfa.asset_type and sdi.type = 'ND_NO_FIEXED_ASSET') type_code,");
		sb.append("(select sdi.code from sys_dict_item sdi where sdi.value = bfa.asset_name_code and sdi.type = 'ND_NO_FIEXED_ASSET') name_code,");
		sb.append("bfa.asset_name_code, bfa.price, bfa.remarks");
		sb.append(" from biz_no_fixed_asset_liabilities bfa");
		sb.append(" where bfa.project_id = ?1 AND bfa.biz_type=?2");
		List<Object[]> list=dynamicQuery.nativeQuery(sb.toString(), projId,bizType);
		Map<String,Object[]> map=Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(list)){
			String key=null;
			for (Object[] objs : list) {
				key=CommonHelper.toStr(objs[1]);
				if(StringUtils.isBlank(key)){
					key=CommonHelper.toStr(objs[0]);
				}
				map.put(key, Arrays.asList(objs[3],objs[4]).toArray());
			}
		}
		return map;
	}
	
	/**
	 * 固定资产信息汇总
	 * @param projId
	 * @param bizType
	 * @return
	 */
	private BizFixedAssetCountVo gatherBizFixedAsset(Long projId,String bizType){
		StringBuffer sb=new StringBuffer();
		sb.append("select bfa.original_acquisition_price,");
		sb.append("(select sdi.code from sys_dict_item sdi where sdi.value=bfa.asset_type AND sdi.type='ND_FIEXED_ASSET') type_code");
		sb.append(" from biz_fixed_assets bfa");
		sb.append(" where bfa.project_id = ?1 AND bfa.biz_type=?2");
		List<Object[]> list=dynamicQuery.nativeQuery(sb.toString(), projId,bizType);
		BigDecimal houseProperty=BigDecimal.ZERO;//房产
		BigDecimal vehicle=BigDecimal.ZERO;//车辆
		BigDecimal productionFacility=BigDecimal.ZERO;//生产设备
		final String TYPE_HOUSE_PROPERTY="ND_FIEXED_ASSET_TYPE_01";
		final String TYPE_VEHICLE="ND_FIEXED_ASSET_TYPE_02";
		List<String> TYPE_PRODUCTION_FACILITY=Arrays.asList("ND_FIEXED_ASSET_TYPE_03","ND_FIEXED_ASSET_TYPE_04","ND_FIEXED_ASSET_TYPE_05");
		if(CollectionUtils.isNotEmpty(list)){
			String key=null;
			for (Object[] objs : list) {
				key=CommonHelper.toStr(objs[1]);
				if(TYPE_HOUSE_PROPERTY.equals(key)){
					houseProperty=houseProperty.add(CommonHelper.toBigDecimal(objs[0]));
				}else if(TYPE_VEHICLE.equals(key)){
					vehicle=vehicle.add(CommonHelper.toBigDecimal(objs[0]));
				}else if(TYPE_PRODUCTION_FACILITY.contains(key)){
					productionFacility=productionFacility.add(CommonHelper.toBigDecimal(objs[0]));
				}
			}
		}
		return new BizFixedAssetCountVo(houseProperty, vehicle, productionFacility);
	}
	
	/**
	 * 计算农业库存
	 * @param projId
	 * @return
	 */
	private BigDecimal gatherAgroOfAgro(Long projId){
		BigDecimal total=BigDecimal.ZERO;
		//种植业
		StringBuffer sb=new StringBuffer();
		//,sum(ca.agriculture_capital_value) sum_acv 
		sb.append("select sum(ca.crop_value) sum_cv");
		sb.append(" from cultivate_agriculture ca where ca.project_id=?1 AND ca.type='1'");
		List<BigDecimal> bdList=dynamicQuery.nativeQuery(BigDecimal.class,sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(bdList)){
			total=CommonHelper.sumOfBigDecimal(total,bdList.get(0));
		}
		sb.setLength(0);//清空
		//种植业 调查时农资存量价值
		sb.append("from CommonInfo ci where ci.projectId=?1");
		List<CommonInfo> ciList=dynamicQuery.query(CommonInfo.class, sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(ciList)){
			CommonInfo ci=ciList.get(0);
			if(ci!=null&&ci.getPrice1()!=null){
				total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(ci.getPrice1()));
			}
		}
		sb.setLength(0);//清空
		//养殖业
		sb.append(" select sum(ba.breed_stock_value)sum_bsv,sum(ba.forage_value) sum_fv ");
		sb.append(" from breed_agriculture ba where ba.project_id=?1 AND ba.type='1'");
		List<Object[]> list=dynamicQuery.nativeQuery(sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(list)){
			Object[] objs=list.get(0);
			for (Object obj : objs) {
				total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
			}
		}
		return total;
	}
	/**
	 * 计算非农业库存
	 * @param projId
	 * @return
	 */
	private BigDecimal gatherAgroOfNoAgro(Long projId){
		BigDecimal total=BigDecimal.ZERO;
		//种植业
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ca.crop_value) sum_cv,sum(ca.agriculture_capital_value) sum_acv ");
		sb.append(" from cultivate_non_agriculture ca where ca.project_id=?1");
		List<Object[]> list=dynamicQuery.nativeQuery(sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(list)){
			Object[] objs=list.get(0);
			for (Object obj : objs) {
				total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
			}
		}
		sb.setLength(0);//清空
		//养殖业
		sb.append(" select sum(ba.breed_stock_value)sum_bsv,sum(ba.forage_value) sum_fv ");
		sb.append(" from breed_non_agriculture ba where ba.project_id=?1");
		list=dynamicQuery.nativeQuery(sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(list)){
			Object[] objs=list.get(0);
			for (Object obj : objs) {
				total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
			}
		}
		return total;
	}
	/**
	 * 查询农业过去未来的农业收入合计
	 * @param projId
	 * @param flag true ：未来，false：过去
	 * @return
	 */
	private BigDecimal gatherAgroIncomeOfAgro(Long projId,boolean flag){
		BigDecimal total=BigDecimal.ZERO;
		//种植业
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ca.sale_income_total) sum_si from cultivate_agriculture ca");
		sb.append(" where ca.project_id=?1 ");
		sb.append(" AND ca.type=?2 ");
		String type =flag?"2":"1";
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId,type);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		sb.setLength(0);//清空
		//养殖业
		sb.append(" select sum(ba.sale_income_total)sum_si");
		sb.append(" from breed_agriculture ba where ba.project_id=?1 AND ba.type=?2");
		list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId,type);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * 农业成本合计
	 * @param projId
	 * @param flag	ture 未来， false 过去
	 * @return
	 */
	private BigDecimal gatherAgroCostOfAgroByGq(Long projId){
		final String TYPE_GQ="1";
		BigDecimal total=BigDecimal.ZERO;
		//种植业
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ca.cost_total) sum_si from cultivate_agriculture ca");
		sb.append(" where ca.project_id=?1 ");
		sb.append(" AND ca.type=?2 ");
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId,TYPE_GQ);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		sb.setLength(0);//清空
		//养殖业
		sb.append(" select sum(ba.cost_total) sum_si");
		sb.append(" from breed_agriculture ba where ba.project_id=?1 AND ba.type=?2");
		list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId,TYPE_GQ);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * 未来
	 * @param projId
	 * @return
	 */
	private BigDecimal gatherAgroCostOfAgroByWL(Long projId){
		final String TYPE_WL="2";
		BigDecimal total=BigDecimal.ZERO;
		//种植业
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ca.PREDICT_COST_TOTAL) sum_si from cultivate_agriculture ca");
		sb.append(" where ca.project_id=?1 ");
		sb.append(" AND ca.type=?2 ");
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId,TYPE_WL);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		sb.setLength(0);//清空
		//养殖业
		sb.append(" select sum(ba.cost_total) sum_si");
		sb.append(" from breed_agriculture ba where ba.project_id=?1 AND ba.type=?2");
		list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId,TYPE_WL);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * @param projId
	 * @return
	 */
	private BigDecimal gatherAgroIncomeOfNoAgro(Long projId){
		BigDecimal total=BigDecimal.ZERO;
		//种植业
		StringBuffer sb=new StringBuffer();
		sb.append("  select sum(cna.income) sum_income from cultivate_non_agriculture cna");
		sb.append(" where cna.project_id=?1 ");
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		sb.setLength(0);//清空
		//养殖业
		sb.append("select sum(bna.income) sum_income from breed_non_agriculture bna");
		sb.append(" where bna.project_id=?1 ");
		list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * 农业成本合计
	 * @param projId
	 * @return
	 */
	private BigDecimal gatherAgroCostOfNoAgro(Long projId){
		BigDecimal total=BigDecimal.ZERO;
		//种植业
		StringBuffer sb=new StringBuffer();
		sb.append("  select sum(cna.cost) sum_income from cultivate_non_agriculture cna");
		sb.append(" where cna.project_id=?1 ");
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		sb.setLength(0);//清空
		//养殖业
		sb.append("select sum(bna.cost) sum_income from breed_non_agriculture bna");
		sb.append(" where bna.project_id=?1 ");
		list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId);
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * 查询工商业收入合计
	 * @param projId
	 * @param flag
	 * @return
	 */
	private BigDecimal getIndustryCommerceIncomeTotal(Long projId,String agroBizType,String futurePastType){
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ibic.year_income_total) sum_year_income");
		sb.append(" from industry_biz_income_cost ibic");
		sb.append(" where ibic.project_id=?1 ");
		List<Object> params=Lists.newArrayList(); 
		params.add(projId);
		int i=2;
		if(StringUtils.isNoneBlank(agroBizType)){
			sb.append(" and ibic.type=?").append(i++);
			params.add(agroBizType);
			
		}
		if(StringUtils.isNoneBlank(futurePastType)){
			sb.append(" and ibic.future_past_type=?").append(i++);
			params.add(futurePastType);
		}
		BigDecimal total=BigDecimal.ZERO;
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), params.toArray());
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * 工商业成本合计
	 * @param projId
	 * @param futurePastType
	 * @return
	 */
	private BigDecimal getIndustryCommerceCostTotalOfNoAgro(Long projId,String agroBizType){
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ibic.year_changeable_cost_total) sum_year_income,sum(ibic.cost_total) sum_cost_total ");
		sb.append(" from industry_biz_income_cost ibic");
		sb.append(" where ibic.project_id=?1");
		List<Object> params=Lists.newArrayList(); 
		params.add(projId);
		int i=2;
		if(StringUtils.isNoneBlank(agroBizType)){
			sb.append(" and ibic.type=?").append(i++);
			params.add(agroBizType);
			
		}
		BigDecimal total=BigDecimal.ZERO;
		List<Object[]> list=dynamicQuery.nativeQuery(Object[].class,sb.toString(),params.toArray());
		if(CollectionUtils.isNotEmpty(list)){
			Object[] obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj[0]),CommonHelper.toBigDecimal(obj[1]));
		}
		return total;
	}
	/**
	 * 工商业成本合计
	 * @param projId
	 * @param futurePastType
	 * @return
	 */
	private BigDecimal getIndustryCommerceCostTotal(Long projId,String agroBizType,String futurePastType){
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ibic.year_changeable_cost_total) sum_year_income");
		sb.append(" from industry_biz_income_cost ibic");
		sb.append(" where ibic.project_id=?1");
		List<Object> params=Lists.newArrayList(); 
		params.add(projId);
		int i=2;
		if(StringUtils.isNoneBlank(agroBizType)){
			sb.append(" and ibic.type=?").append(i++);
			params.add(agroBizType);
			
		}
		if(StringUtils.isNoneBlank(futurePastType)){
			sb.append(" and ibic.future_past_type=?").append(i++);
			params.add(futurePastType);
			
		}
		BigDecimal total=BigDecimal.ZERO;
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(),params.toArray());
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	
	/**
	 * @param projId
	 * @param futurePastType
	 * @param incomeType
	 * @return
	 */
	private BigDecimal getOtherIncome(Long projId,String agroBizType,String futurePastType,String incomeType){
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(oi.year_income) from other_income oi");
		sb.append(" where oi.project_id=?1 ");
		List<Object> params=Lists.newArrayList(); 
		params.add(projId);
		int i=2;
		if(StringUtils.isNoneBlank(futurePastType)){
			sb.append(" and oi.future_past_type=?").append(i++);
			params.add(futurePastType);
			
		}
		if(StringUtils.isNoneBlank(agroBizType)){
			sb.append(" and oi.type=?").append(i++);
			params.add(agroBizType);
			
		}
		if(StringUtils.isNoneBlank(incomeType)){
			sb.append(" and oi.OTHER_INCOME_TYPE=?").append(i++);
			params.add(incomeType);
			
		}
		BigDecimal total=BigDecimal.ZERO;
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), params.toArray());
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * @param projId
	 * @param futurePastType
	 * @return
	 */
	private BigDecimal gatherTotalStockOfAgro(Long projId,String agroBizType,String futurePastType){
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ca.stock_while_surveying) sum_stock from industry_biz_income_cost ca where ca.project_id=?1");
		List<Object> params=Lists.newArrayList(); 
		params.add(projId);
		int i=2;
		if(StringUtils.isNoneBlank(agroBizType)){
			sb.append(" AND ca.type=?").append(i++);
			params.add(agroBizType);
			
		}
		if(StringUtils.isNoneBlank(futurePastType)){
			sb.append(" AND ca.future_past_type=?").append(i++);
			params.add(futurePastType);
			
		}
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), params.toArray());
		BigDecimal total=BigDecimal.ZERO;
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	/**
	 * @param projId
	 * @param futurePastType
	 * @return
	 */
	private BigDecimal gatherTotalStockOfNoAgro(Long projId){
		StringBuffer sb=new StringBuffer();
		sb.append("select sum(ibs.total)  from industry_biz_stock ibs where ibs.project_id=?1");
		List<Object> list=dynamicQuery.nativeQuery(Object.class,sb.toString(), projId);
		BigDecimal total=BigDecimal.ZERO;
		if(CollectionUtils.isNotEmpty(list)){
			Object obj=list.get(0);
			total=CommonHelper.sumOfBigDecimal(total,CommonHelper.toBigDecimal(obj));
		}
		return total;
	}
	
	/**
	 * 查询业务申请信息
	 * @param projId
	 * @return
	 */
	private BizApplyBo findBizApplyByProjId(Long projId){
		String str="select pa.customer_name,pa.apply_amt from project_application pa where pa.project_id=?1";
		List<Object[]> list=dynamicQuery.nativeQuery(str, projId);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return new BizApplyBo(list.get(0));
	}
	private String toBigDecimal(BigDecimal bd){
		if(bd==null){
			return StringUtils.EMPTY;
		}
		return MoneyUtil.formatMoney(bd);
	}
	private class BizFixedAssetCountVo{
		BigDecimal houseProperty=BigDecimal.ZERO;
		BigDecimal vehicle=BigDecimal.ZERO;
		BigDecimal productionFacility=BigDecimal.ZERO;
		public BizFixedAssetCountVo(BigDecimal houseProperty,
				BigDecimal vehicle, BigDecimal productionFacility) {
			super();
			this.houseProperty = houseProperty;
			this.vehicle = vehicle;
			this.productionFacility = productionFacility;
		}
		public BigDecimal getHouseProperty() {
			return houseProperty;
		}
		public BigDecimal getVehicle() {
			return vehicle;
		}
		public BigDecimal getProductionFacility() {
			return productionFacility;
		}
	}
	/**
	 * 初始化预期现金流时间数据
	 * @param projectId
	 * @return
	 */
	private List<String> initDateListOfEcfi(Long projectId){
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select min(ecfi.month_of_year) start_date, max(ecfi.month_of_year) end_date");
		sqlBuffer.append(" from expected_cash_flow_info ecfi");
		sqlBuffer.append(" where ecfi.project_id = ?1");;
		List<Object[]> list=this.dynamicQuery.nativeQuery(Object[].class,sqlBuffer.toString(), projectId);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		Object[] objs=list.get(0);
		ProjectApplication pa =this.projectApplicationDao.findOne(projectId);
		Date startDate=CommonHelper.toDate(objs[0]);//开始时间
		Date endDate=CommonHelper.toDate(objs[1]);//结束时间
		Calendar calendar=Calendar.getInstance();
		if(startDate==null){
			startDate=calendar.getTime();
		}else{
			calendar.setTime(startDate);
		}
		if(endDate==null){
			calendar.add(Calendar.MONTH, 14);//开始时间增加1年多两个月
			endDate=calendar.getTime();
		}
		if(pa!=null){
			if(pa.getApplyDate()!=null&&startDate.after(pa.getApplyDate())){
				startDate=pa.getApplyDate();
			}
			calendar.setTime(pa.getApplyDate());//申请贷款日
			int add_month=this.countMonth(pa.getApplyTerm(), pa.getApplyTermUnit());
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+add_month);
			if(endDate.before(calendar.getTime())){
				endDate=calendar.getTime();
			}
		}
		return this.handleDateList(CommonHelper.date2Str(startDate, DF_YYYY_MM), CommonHelper.date2Str(endDate, DF_YYYY_MM), DF_YYYY_MM);
	}
	/**
	 * 根据期限和期限单位计算总月份
	 * @param term
	 * @param termUnit
	 * @return
	 */
	private int countMonth(int term,String termUnit){
		int totalMonth=0;
		if(StringUtils.equals(dataDict.getCodeVal("TermUnitCd", "S1"), termUnit)){//年
			totalMonth=term*12;
		}else if(StringUtils.equals(dataDict.getCodeVal("TermUnitCd", "S2"), termUnit)){//月
			totalMonth=term;
		}else if(StringUtils.equals(dataDict.getCodeVal("TermUnitCd", "S3"), termUnit)){//日
			totalMonth=((Long)Math.round(Double.valueOf(String.valueOf(term))/30)).intValue();
		}
		return (totalMonth>12?totalMonth:12)+2;//默认12，且至少十二个月
	}
	/**
	 * 开始时间和结束时间及指定格式
	 * @param startDate
	 * @param endDate
	 * @param df
	 * @return
	 */
	private List<String> handleDateList(String startDate,String endDate,final String df){
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
	
	/**
	 * @param projectId
	 * @return
	 */
	private BizExpectedCashFlowVo handleItemOfEcfi(Long projectId){
		//查询数据库
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select ecfi.month_of_year,ecfi.obj_type,ecfi.obj_id,ecfi.obj_code, ecfi.obj_name,ecfi.income_expend_flag,ecfi.amt_money ");
		sqlBuffer.append(" from expected_cash_flow_info ecfi");
		sqlBuffer.append(" where ecfi.project_id = ?1");
		sqlBuffer.append(" order by ecfi.obj_type,ecfi.obj_id,ecfi.obj_code,");
		sqlBuffer.append("ecfi.income_expend_flag,ecfi.month_of_year,ecfi.id");
		List<Object[]> dataSet=this.dynamicQuery.nativeQuery(sqlBuffer.toString(), projectId);
		if(CollectionUtils.isEmpty(dataSet)){
			return null;
		}
		List<ExpectedCashFlowInfo> dataList=Lists.transform(dataSet, new Function<Object[], ExpectedCashFlowInfo>() {
			@Override
			public ExpectedCashFlowInfo apply(Object[] objs) {
				ExpectedCashFlowInfo info=new ExpectedCashFlowInfo();
				int i=0;
				info.setMonthOfYear(CommonHelper.toDate(objs[i++]));
				info.setObjType(CommonHelper.toStr(objs[i++]));
				info.setObjId(CommonHelper.toLong(objs[i++]));
				info.setObjCode(CommonHelper.toStr(objs[i++]));
				info.setObjName(CommonHelper.toStr(objs[i++]));
				info.setIncomeExpendFlag(CommonHelper.toStr(objs[i++]));
				info.setAmtMoney(CommonHelper.toBigDecimal(objs[i++]));
				return info;
			}
		});
		BizExpectedCashFlowVo bizEcfi=new BizExpectedCashFlowVo();
		Map<String, String> nameMap=Maps.newHashMap();
		nameMap.put("01","种植业");
		nameMap.put("02","养殖业");
		nameMap.put("03","工商业");
		nameMap.put("04","其他");
		nameMap.put("05","家庭生活消费");
		nameMap.put("0501","学杂费");
		nameMap.put("0502","其他贷款");
		ExpectedCashFlowInfo old_info=null;//记录本条上次数据
		ItemOfEcfiVo item=null;
		String key_monthOfYear=null;
		for (ExpectedCashFlowInfo info : dataList) {
			key_monthOfYear=CommonHelper.date2Str(info.getMonthOfYear(), DF_YYYY_MM);
			if(Arrays.asList("01","02","03","04").contains(info.getObjType())){//
				if(old_info!=null&&StringUtils.equals(info.getObjType(), old_info.getObjType())){//类型与上次相同
					if(!StringUtils.equals(info.getIncomeExpendFlag(), old_info.getIncomeExpendFlag())){
						item=bizEcfi.new ItemOfEcfiVo();
						if(StringUtils.equals("1", info.getIncomeExpendFlag())){
							bizEcfi.getIncomes().add(item);//收入
						}else{
							bizEcfi.getExpends().add(item);//支出
						}
						if(info.getObjId()!=null&&info.getObjId()>0){
							item.setName(info.getObjName());
						}else{
							item.setName(nameMap.get(info.getObjType()));
						}
						item.getAmtMoneyMap().put(key_monthOfYear, info.getAmtMoney());
					}else{//支出类型相同且不是具体条目则合并成大类一并计算
						if(info.getObjId()!=null&&info.getObjId()>0){
							item.getAmtMoneyMap().put(key_monthOfYear, info.getAmtMoney());
						}else{
							BigDecimal bd=item.getAmtMoneyMap().get(key_monthOfYear);
							if(bd!=null){
								bd=bd.add(info.getAmtMoney());
							}
							item.getAmtMoneyMap().put(key_monthOfYear, bd);
						}
					}
				}else{//类型与上次相同
					item=bizEcfi.new ItemOfEcfiVo();
					if(StringUtils.equals("1", info.getIncomeExpendFlag())){
						bizEcfi.getIncomes().add(item);//收入
					}else{
						bizEcfi.getExpends().add(item);//支出
					}
					if(info.getObjId()!=null&&info.getObjId()>0){
						item.setName(info.getObjName());
					}else{
						item.setName(nameMap.get(info.getObjType()));
					}
					item.getAmtMoneyMap().put(key_monthOfYear, info.getAmtMoney());
				}
				
			}else if("05".equals(info.getObjType())){//家庭生活消费
				if(StringUtils.equals(info.getObjCode(), "0501")){//学杂费
					if(old_info==null||!StringUtils.equals(info.getObjCode(), old_info.getObjCode())){
						item=bizEcfi.new ItemOfEcfiVo();
						bizEcfi.setHouseholdSpending(item);
					}
					item=bizEcfi.getHouseholdSpending();
					item.getAmtMoneyMap().put(key_monthOfYear, info.getAmtMoney());
				}else if(StringUtils.equals(info.getObjCode(), "0502")){//其他贷款
					if(old_info==null||!StringUtils.equals(info.getObjCode(), old_info.getObjCode())){
						item=bizEcfi.new ItemOfEcfiVo();
						bizEcfi.setOtherPayments(item);
					}
					item=bizEcfi.getOtherPayments();
					item.getAmtMoneyMap().put(key_monthOfYear, info.getAmtMoney());
				}
			}
			old_info=info;
		}
		//其他收入
		List<OtherIncome> oiList = this.otherIncomeDao.findList(projectId,
				dataDict.getCodeVal(AGRO_BIZ_TYPE, "S1"), "2");//农业为主的未来收入
		List<ItemOfEcfiVo> incomesList= bizEcfi.getIncomes();
		if(CollectionUtils.isEmpty(incomesList)){
			incomesList=Lists.newArrayList();
			bizEcfi.setIncomes(incomesList);
		}
		if(CollectionUtils.isNotEmpty(oiList)){
			ItemOfEcfiVo oi_item=null;
			BigDecimal oi_amt=null;
			for (OtherIncome oi : oiList) {
				oi_item=bizEcfi.new ItemOfEcfiVo();
				if(StringUtils.equals(dataDict.getCodeVal(CODE_INCOME_TYPE, "S1"), oi.getOtherIncomeType())){
					oi_item.setName(dataDict.getCodeName(CODE_INCOME_TYPE,  oi.getOtherIncomeType()));
				}else{
					oi_item.setName(oi.getName());
				}
				oi_amt=oi.getYearIncome();
				if(oi_amt!=null){
					oi_amt=oi_amt.divide(new BigDecimal(12),2,BigDecimal.ROUND_HALF_UP);
				}
				oi_item.setAvgAmt(oi_amt);//每月收入
				incomesList.add(oi_item);
			}
		}
		//贷款申请处理
		ProjectApplication pa=this.projectApplicationDao.findOne(projectId);
		ItemOfEcfiVo loan=bizEcfi.new ItemOfEcfiVo();//贷款
		if(pa!=null){
			loan.getAmtMoneyMap().put(CommonHelper.date2Str(pa.getApplyDate(), DF_YYYY_MM),pa.getApplyAmt());
		}
		bizEcfi.setLoan(loan);
		ItemOfEcfiVo paymentOfLoan=bizEcfi.new ItemOfEcfiVo();
		if(pa!=null){
			InterestCalCulateForm interForm=new InterestCalCulateForm();
			interForm.setProjectId(CommonHelper.toInt(projectId));
			Calendar c=Calendar.getInstance();
			c.setTime(pa.getApplyDate());
			interForm.setLoanAmount(pa.getApplyAmt());//贷款金额
			//c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
			interForm.setLoanStartDate(c.getTime());
			interForm.setApplyTerm(pa.getApplyTerm());
			interForm.setApplyTermUnit(pa.getApplyTermUnit());
			interForm.setRepayment(pa.getRepayingMode());
			c.setTime(pa.getApplyDate());
			interForm.setRepaymentDate(c.get(Calendar.DAY_OF_MONTH));//还款日，同申请日
			interForm.setRate(pa.getBizRate());
			Date endDate =null;
			if(interForm.getLoanStartDate()!=null&&interForm.getApplyTermUnit()!=null&&interForm.getApplyTerm()!=null){
				endDate = DateTools.getEndingDate(
						interForm.getLoanStartDate(),
						interForm.getApplyTermUnit(),
						interForm.getApplyTerm());
				
			}
			interForm.setLoanEndDate(endDate);
			interForm.setRepaymentNumber(pa.getReplyPeriodNum());
			List<InterestVO> list=null;
			try {
				list = this.interestCalCulateServiceForBusiness.calCulate(interForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String, BigDecimal> paymentOfLoanMap=paymentOfLoan.getAmtMoneyMap();
			if(CollectionUtils.isNotEmpty(list)){
				for (InterestVO vo : list) {
					paymentOfLoanMap.put(CommonHelper.date2Str(CommonHelper.str2Date(vo.getRepaymentDate(), CommonHelper.DF_DATE), DF_YYYY_MM), vo.getCurrentPricipalInterest());
				}
			}
		}
		bizEcfi.setPaymemtOfLoan(paymentOfLoan);
		return bizEcfi;
	}
	@Override
	public MsgResult checkFinancialStatements(Long projId) {
		try {
			this.createProfitLossAsseLliabExcel(projId);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResult.getMsgResult("FS_01","财务报表数据不完整");
		}
		return MsgResult.getMsgResult(ResultEnums.SUCCESS);
	}
	@Override
	public void test(Long projectId) {
		logger.info("************************RESULT:{}",this.initDateListOfEcfi(projectId));
		logger.info("************************RESULT:{}",this.handleItemOfEcfi(projectId));
		
	}
	/**
	 * 业务申请信息
	 * @author AcoreHeng
	 *
	 */
	private class BizApplyBo{
		private String customerName;
		private BigDecimal applyAmount;
		
		
		public BizApplyBo(Object[] objs) {
			this.customerName = CommonHelper.toStr(objs[0]);
			this.applyAmount = CommonHelper.toBigDecimal(objs[1]);
		}
		public String getCustomerName() {
			return customerName;
		}
		public BigDecimal getApplyAmount() {
			return applyAmount;
		}
	}
}
