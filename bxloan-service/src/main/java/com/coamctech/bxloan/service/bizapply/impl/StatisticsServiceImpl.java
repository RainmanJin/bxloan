package com.coamctech.bxloan.service.bizapply.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.dao.BreedAgricultureDao;
import com.coamctech.bxloan.dao.BreedNonAgricultureDao;
import com.coamctech.bxloan.dao.CultivateAgricultureDao;
import com.coamctech.bxloan.dao.CultivateNonAgricultureDao;
import com.coamctech.bxloan.dao.IndustryBizIncomeCostDao;
import com.coamctech.bxloan.dao.IndustryBizStockDao;
import com.coamctech.bxloan.dao.OtherIncomeDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.bizapply.StatisticsService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.model.bizapply.Statistics;
import com.google.common.collect.Lists;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	private BizApplyQueryService bizApplyQueryService;
	private DataDict dataDict;
	private IndustryBizIncomeCostDao industryBizIncomeCostDao;
	private CultivateNonAgricultureDao cultivateNonAgricultureDao;
	private BreedNonAgricultureDao breedNonAgricultureDao;
	private OtherIncomeDao otherIncomeDao;
	private IndustryBizStockDao industryBizStockDao;
	private BreedAgricultureDao breedAgricultureDao;
	private CultivateAgricultureDao cultivateAgricultureDao;
	@Autowired
	public void setBizApplyQueryService(BizApplyQueryService bizApplyQueryService) {
		this.bizApplyQueryService = bizApplyQueryService;
	}
	@Autowired
	public void setDataDict(DataDict dataDict) {
		this.dataDict = dataDict;
	}
	@Autowired
	public void setIndustryBizIncomeCostDao(IndustryBizIncomeCostDao industryBizIncomeCostDao) {
		this.industryBizIncomeCostDao = industryBizIncomeCostDao;
	}
	@Autowired
	public void setCultivateNonAgricultureDao(CultivateNonAgricultureDao cultivateNonAgricultureDao) {
		this.cultivateNonAgricultureDao = cultivateNonAgricultureDao;
	}
	@Autowired
	public void setBreedNonAgricultureDao(BreedNonAgricultureDao breedNonAgricultureDao) {
		this.breedNonAgricultureDao = breedNonAgricultureDao;
	}
	@Autowired
	public void setOtherIncomeDao(OtherIncomeDao otherIncomeDao) {
		this.otherIncomeDao = otherIncomeDao;
	}
	@Autowired
	public void setIndustryBizStockDao(IndustryBizStockDao industryBizStockDao) {
		this.industryBizStockDao = industryBizStockDao;
	}
	@Autowired
	public void setBreedAgricultureDao(BreedAgricultureDao breedAgricultureDao) {
		this.breedAgricultureDao = breedAgricultureDao;
	}
	@Autowired
	public void setCultivateAgricultureDao(CultivateAgricultureDao cultivateAgricultureDao) {
		this.cultivateAgricultureDao = cultivateAgricultureDao;
	}
	@Override
	public List<Statistics> count(Long projectId) {
		List<Statistics> statisticss = Lists.newArrayList();
		for (StatisticsService.IncomeType incomeType : StatisticsService.IncomeType.values()) {
			Statistics statistics = new Statistics();
			statistics.setTypeValue(incomeType.getValue());
			statistics.setTypeName(incomeType.getName());
			statistics.setIncomeType(incomeType);
			statisticss.add(statistics);
		}
		
		StatisticsService.Type type = toEnum(bizApplyQueryService.findAgroBizTypeByProjId(projectId));
		switch (type) {
		case AGRICULTURE:
			this.initPastPredictableIncomeInfo(projectId, statisticss, StatisticsService.FuturePastType.PAST)   // 过去收入
				.initPastPredictableIncomeInfo(projectId, statisticss, StatisticsService.FuturePastType.FUTURE) // 预测收入
				.initPredictableGainInfo(statisticss);															// 预测利润
			break;
		case NON_AGRICULTURE:
			this.initIncomeInfo(projectId, statisticss) // 收入
				.initCostInfo(projectId, statisticss)   // 成本
				.initGainInfo(statisticss)              // 利润
				.initStockInfo(projectId, statisticss); // 存货
			break;
		default:
			break;
		}
		return statisticss;
	}

	@Override
	public List<Statistics> count(Long projectId, IncomeType... incomeTypes) {
		return null;
	}

	@Override
	public Statistics count(Long projectId, IncomeStructure incomeStructure, IncomeDetail incomeDetail, IncomeType incomeType) {
		return null;
	}
	
	@Override
	public Page<Statistics> findBySearch(Integer pageNumber, Integer pageSize, Long projectId) {
		List<Statistics> list = this.count(projectId);
		return new PageImpl<Statistics>(list, new PageRequest(pageNumber, pageSize), list.size());
	}
	
	private StatisticsService.Type toEnum(String type) {
		if (dataDict.getCodeVal("AgroBizType", "S1").equals(type)) {
			return StatisticsService.Type.AGRICULTURE;
		} else if (dataDict.getCodeVal("AgroBizType", "S2").equals(type)) {
			return StatisticsService.Type.NON_AGRICULTURE;
		}
		return null;
	}
	
	private StatisticsServiceImpl initIncomeInfo(Long projectId, List<Statistics> statisticss) {
		// 工商业
		BigDecimal total_ind_com = industryBizIncomeCostDao.sumYearIncomeTotal(projectId, StatisticsService.Type.NON_AGRICULTURE.getValue());
		// 种植业
		BigDecimal total_crop_farm = cultivateNonAgricultureDao.sumIncome(projectId);
		// 养殖业
		BigDecimal total_breed = breedNonAgricultureDao.sumIncome(projectId);
		// 其他收入
		BigDecimal total_other = otherIncomeDao.sumYearIncome(projectId, StatisticsService.Type.NON_AGRICULTURE.getValue());
		// 合计
		BigDecimal total = total_ind_com.add(total_crop_farm).add(total_breed).add(total_other);
		
		// 工商业占比
		BigDecimal total_ind_com_per = divide(total_ind_com, total);
		// 种植业占比
		BigDecimal total_crop_farm_per = divide(total_crop_farm, total);
		// 养殖业占比
		BigDecimal total_breed_per = divide(total_breed, total);
		// 其他收入占比
		BigDecimal total_other_per = divide(total_other, total);
		
		for (Statistics statistics : statisticss) {
			BigDecimal amount = BigDecimal.ZERO;
			BigDecimal percent = BigDecimal.ZERO;
			switch (statistics.getIncomeType()) {
			case INDUSTRY_COMMERCE:
				amount = total_ind_com;percent = total_ind_com_per;
				break;
			case BREED:
				amount = total_crop_farm;percent = total_crop_farm_per;
				break;
			case CROP_FARMING:
				amount = total_breed;percent = total_breed_per;
				break;
			case OTHER:
				amount = total_other;percent = total_other_per;
				break;
			case SUM:
				amount = total;
			default:
				break;
			}
			statistics.setIncomeAmount(amount);
			statistics.setIncomePercent(percent);
		}
		return this;
	}
	
	private StatisticsServiceImpl initCostInfo(Long projectId, List<Statistics> statisticss) {
		// 工商业
		BigDecimal total_ind_com = industryBizIncomeCostDao.sumCostTotal(projectId, StatisticsService.Type.NON_AGRICULTURE.getValue());
		// 种植业
		BigDecimal total_crop_farm = cultivateNonAgricultureDao.sumCost(projectId);
		// 养殖业
		BigDecimal total_breed = breedNonAgricultureDao.sumCost(projectId);
		// 合计
		BigDecimal total = total_ind_com.add(total_crop_farm).add(total_breed);
		
		// 工商业占比
		BigDecimal total_ind_com_per = divide(total_ind_com, total);
		// 种植业占比
		BigDecimal total_crop_farm_per = divide(total_crop_farm, total);
		// 养殖业占比
		BigDecimal total_breed_per = divide(total_breed, total);
		
		for (Statistics statistics : statisticss) {
			BigDecimal amount = BigDecimal.ZERO;
			BigDecimal percent = BigDecimal.ZERO;
			switch (statistics.getIncomeType()) {
			case INDUSTRY_COMMERCE:
				amount = total_ind_com;percent = total_ind_com_per;
				break;
			case BREED:
				amount = total_crop_farm;percent = total_crop_farm_per;
				break;
			case CROP_FARMING:
				amount = total_breed;percent = total_breed_per;
				break;
			case SUM:
				amount = total;
			default:
				break;
			}
			statistics.setCostAmount(amount);
			statistics.setCostPercent(percent);
		}
		return this;
	}
	
	private StatisticsServiceImpl initGainInfo(List<Statistics> statisticss) {
		BigDecimal total = BigDecimal.ZERO;
		for (Statistics statistics : statisticss) {
			switch (statistics.getIncomeType()) {
			case SUM:
				break;
			default:
				total = total.add(statistics.getIncomeAmount().subtract(statistics.getCostAmount()));
				break;
			}
		}
		
		for (Statistics statistics : statisticss) {
			switch (statistics.getIncomeType()) {
			case SUM:
				statistics.setGainAmount(total);
				statistics.setGainPercent(divide(statistics.getGainAmount(), total));
				break;
			default:
				statistics.setGainAmount(statistics.getIncomeAmount().subtract(statistics.getCostAmount()));
				statistics.setGainPercent(divide(statistics.getGainAmount(), total));
				break;
			}
		}
		return this;
	}
	
	private StatisticsServiceImpl initStockInfo(Long projectId, List<Statistics> statisticss) {
		// 工商业
		BigDecimal total_ind_com = industryBizStockDao.sumTotal(projectId);
		// 种植业
		BigDecimal total_crop_farm = cultivateNonAgricultureDao.sumCropValue(projectId);
		// 养殖业
		BigDecimal total_breed = breedNonAgricultureDao.sumBreedStockValue(projectId);
		
		for (Statistics statistics : statisticss) {
			BigDecimal amount = BigDecimal.ZERO;
			switch (statistics.getIncomeType()) {
			case INDUSTRY_COMMERCE:
				amount = total_ind_com;
				break;
			case CROP_FARMING:
				amount = total_crop_farm;
				break;
			case BREED:
				amount = total_breed;
				break;
			default:
				break;
			}
			statistics.setStock(amount);
		}
		return this;
	}
	
	private StatisticsServiceImpl initPastPredictableIncomeInfo(Long projectId, List<Statistics> statisticss, StatisticsService.FuturePastType futurePastType) {
		// 工商业
		BigDecimal total_ind_com = industryBizIncomeCostDao.sumYearIncomeTotal(projectId, StatisticsService.Type.AGRICULTURE.getValue(), futurePastType.getValue());
		// 种植业
		BigDecimal total_crop_farm = cultivateAgricultureDao.sumSaleIncomeTotal(projectId, futurePastType.getValue());
		// 养殖业
		BigDecimal total_breed = breedAgricultureDao.sumSaleIncomeTotal(projectId, futurePastType.getValue());
		// 其他收入
		BigDecimal total_other = otherIncomeDao.sumYearIncome(projectId, StatisticsService.Type.AGRICULTURE.getValue(), futurePastType.getValue());
		// 合计
		BigDecimal total = total_ind_com.add(total_crop_farm).add(total_breed).add(total_other);
		
		// 工商业占比
		BigDecimal total_ind_com_per = divide(total_ind_com, total);
		// 种植业占比
		BigDecimal total_crop_farm_per = divide(total_crop_farm, total);
		// 养殖业占比
		BigDecimal total_breed_per = divide(total_breed, total);
		// 其他收入占比
		BigDecimal total_other_per = divide(total_other, total);
		// 合计占比
		BigDecimal total_per = total_ind_com_per.add(total_crop_farm_per).add(total_breed_per).add(total_other_per);
		
		for (Statistics statistics : statisticss) {
			BigDecimal amount = BigDecimal.ZERO;
			BigDecimal percent = BigDecimal.ZERO;
			switch (statistics.getIncomeType()) {
			case INDUSTRY_COMMERCE:
				amount = total_ind_com;percent = total_ind_com_per;
				break;
			case CROP_FARMING:
				amount = total_crop_farm;percent = total_crop_farm_per;
				break;
			case BREED:
				amount = total_breed;percent = total_breed_per;
				break;
			case OTHER:
				amount = total_other;percent = total_other_per;
				break;
			case SUM:
				amount = total;percent = total_per;
				break;
			default:
				break;
			}
			switch (futurePastType) {
			case FUTURE:
				statistics.setPredictableIncomeAmount(amount);
				statistics.setPredictableIncomePercent(percent);
				break;
			case PAST:
				statistics.setPastIncomeAmount(amount);
				statistics.setPastIncomePercent(percent);
				break;
			default:
				break;
			}
		}
		return this;
	}
	
	private StatisticsServiceImpl initPredictableGainInfo(List<Statistics> statisticss) {
		BigDecimal total = null;
		for (Statistics statistics : statisticss) {
			if (statistics.getIncomeType().equals(StatisticsService.IncomeType.SUM)) {
				total = statistics.getPastIncomeAmount().subtract(statistics.getPredictableIncomeAmount());
				break;
			}
		}
		for (Statistics statistics : statisticss) {
			statistics.setPredictableGainAmount(statistics.getPastIncomeAmount().subtract(statistics.getPredictableIncomeAmount()));
			statistics.setPredictableGainPercent(divide(statistics.getPredictableGainAmount(), total));
		}
		return this;
	}
	
	private BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
		if (divisor.equals(BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}
		return dividend.divide(divisor, 4, BigDecimal.ROUND_UP);
	}
	
}
