package com.coamctech.bxloan.service.bizapply.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.CommonInfoDao;
import com.coamctech.bxloan.dao.FamilyConsumeDao;
import com.coamctech.bxloan.dao.IndustryBizDao;
import com.coamctech.bxloan.dao.IndustryBizIncomeCostDao;
import com.coamctech.bxloan.dao.IndustryBizStockDao;
import com.coamctech.bxloan.dao.OtherIncomeCommonDao;
import com.coamctech.bxloan.dao.TransportDao;
import com.coamctech.bxloan.entity.CommonInfo;
import com.coamctech.bxloan.entity.FamilyConsume;
import com.coamctech.bxloan.entity.IndustryBiz;
import com.coamctech.bxloan.entity.IndustryBizIncomeCost;
import com.coamctech.bxloan.entity.IndustryBizStock;
import com.coamctech.bxloan.entity.OtherIncomeCommon;
import com.coamctech.bxloan.entity.Transport;
import com.coamctech.bxloan.service.bizapply.IndustryBizService;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizIncomeCostAgrVO;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizIncomeCostVO;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizVO;
import com.coamctech.bxloan.service.model.bizapply.OtherIncomeCommonVO;
import com.google.common.collect.Maps;

@Service
@Transactional
public class IndustryBizServiceImpl implements IndustryBizService {
	private IndustryBizDao industryBizDao;
	private TransportDao transportDao;
	private IndustryBizIncomeCostDao industryBizIncomeCostDao;
	private IndustryBizStockDao industryBizStockDao;
	private OtherIncomeCommonDao otherIncomeCommonDao;
	private FamilyConsumeDao familyConsumeDao;
	private CommonInfoDao commonInfoDao;
	@Autowired
	public void setIndustryBizDao(IndustryBizDao industryBizDao) {
		this.industryBizDao = industryBizDao;
	}
	@Autowired
	public void setTransportDao(TransportDao transportDao) {
		this.transportDao = transportDao;
	}
	@Autowired
	public void setOtherIncomeCommonDao(OtherIncomeCommonDao otherIncomeCommonDao) {
		this.otherIncomeCommonDao = otherIncomeCommonDao;
	}
	@Autowired
	public void setIndustryBizIncomeCostDao(IndustryBizIncomeCostDao industryBizIncomeCostDao) {
		this.industryBizIncomeCostDao = industryBizIncomeCostDao;
	}
	@Autowired
	public void setIndustryBizStockDao(IndustryBizStockDao industryBizStockDao) {
		this.industryBizStockDao = industryBizStockDao;
	}
	@Autowired
	public void setFamilyConsumeDao(FamilyConsumeDao familyConsumeDao) {
		this.familyConsumeDao = familyConsumeDao;
	}
	@Autowired
	public void setCommonInfoDao(CommonInfoDao commonInfoDao) {
		this.commonInfoDao = commonInfoDao;
	}
	@Override
	public Map<String, Long> saveBasicInfo(IndustryBizVO vo) throws Exception {
		IndustryBiz industryBiz = new IndustryBiz();
		Transport transport = new Transport();

		BeanUtils.copyProperties(vo, industryBiz);
		BeanUtils.copyProperties(vo, transport);
		industryBiz.setId(vo.getIndustryBizId());
		transport.setId(vo.getTransportId());
		industryBiz.setBoughtBuiltDate(CommonHelper.str2Date(vo.getBoughtBuiltDate(), CommonHelper.DF_DATE));
		transport.setBusinessStartDate(CommonHelper.str2Date(vo.getBusinessStartDate(), CommonHelper.DF_DATE));

		industryBizDao.save(industryBiz);
		transportDao.save(transport);
		
		Map<String, Long> idMap = Maps.newHashMap();
		idMap.put("industryBizId", industryBiz.getId());
		idMap.put("transportId", transport.getId());
		return idMap;
	}

	@Override
	public Page<IndustryBizIncomeCost> findIncomeCostPageByProIdAndTypeAndFuturePastType(Integer pageNumber, Integer pageSize, Long projectId, String type, String futurePastType) {
		List<IndustryBizIncomeCost> list = industryBizIncomeCostDao.findByProjectIdAndTypeAndFuturePastType(projectId, type, futurePastType);
		return new PageImpl<IndustryBizIncomeCost>(list, new PageRequest(pageNumber, pageSize), list.size());
	}

	@Override
	public void saveIndustryBizIncomeCost(IndustryBizIncomeCostVO vo) {
		IndustryBizIncomeCost industryBizIncomeCost = new IndustryBizIncomeCost();
		
		BeanUtils.copyProperties(vo, industryBizIncomeCost);
		industryBizIncomeCost.setBusinessStartDate(CommonHelper.str2Date(vo.getBusinessStartDate(), CommonHelper.DF_DATE));
		industryBizIncomeCost.setMonth(vo.getMonth_slack() + "," + vo.getMonth_peak());
		industryBizIncomeCost.setDailyIncome(vo.getDailyIncome_slack() + "," + vo.getDailyIncome_peak());
		industryBizIncomeCost.setDailyChangeableCost(vo.getDailyChangeableCost_slack() + "," + vo.getDailyChangeableCost_peak());
		industryBizIncomeCost.setDailyGain(vo.getDailyGain_slack() + "," + vo.getDailyGain_peak());
		industryBizIncomeCost.setBusinessDay(vo.getBusinessDay_slack() + "," + vo.getBusinessDay_peak());
		industryBizIncomeCost.setMonthlyIncome(vo.getMonthlyIncome_slack() + "," + vo.getMonthlyIncome_peak());
		industryBizIncomeCost.setMonthlyChangeableCost(vo.getMonthlyChangeableCost_slack() + "," + vo.getMonthlyChangeableCost_peak());
		
		industryBizIncomeCostDao.save(industryBizIncomeCost);
	}

	@Override
	public IndustryBizIncomeCostVO findOneBizIncomeVO(Long id) {
		IndustryBizIncomeCost industryBizIncomeCost = industryBizIncomeCostDao.findOne(id);
		
		IndustryBizIncomeCostVO vo = new IndustryBizIncomeCostVO();
		BeanUtils.copyProperties(industryBizIncomeCost, vo);
		vo.setMonth_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonth().split(",")[0]));
		vo.setMonth_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonth().split(",")[1]));
		vo.setDailyIncome_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getDailyIncome().split(",")[0]));
		vo.setDailyIncome_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getDailyIncome().split(",")[1]));
		vo.setDailyChangeableCost_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getDailyChangeableCost().split(",")[0]));
		vo.setDailyChangeableCost_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getDailyChangeableCost().split(",")[1]));
		vo.setDailyGain_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getDailyGain().split(",")[0]));
		vo.setDailyGain_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getDailyGain().split(",")[1]));
		vo.setBusinessDay_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getBusinessDay().split(",")[0]));
		vo.setBusinessDay_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getBusinessDay().split(",")[1]));
		vo.setMonthlyIncome_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonthlyIncome().split(",")[0]));
		vo.setMonthlyIncome_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonthlyIncome().split(",")[1]));
		vo.setMonthlyChangeableCost_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonthlyChangeableCost().split(",")[0]));
		vo.setMonthlyChangeableCost_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonthlyChangeableCost().split(",")[1]));
		vo.setBusinessStartDate(CommonHelper.date2Str(industryBizIncomeCost.getBusinessStartDate(), CommonHelper.DF_DATE));
		
		return vo;
	}
	
	@Override
	public IndustryBizIncomeCostAgrVO findOneBizIncomeAgrVO(Long id) {
		IndustryBizIncomeCost industryBizIncomeCost = industryBizIncomeCostDao.findOne(id);
		
		IndustryBizIncomeCostAgrVO vo = new IndustryBizIncomeCostAgrVO();
		BeanUtils.copyProperties(industryBizIncomeCost, vo);
		vo.setMonth_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonth().split(",")[0]));
		vo.setMonth_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonth().split(",")[1]));
		vo.setMonthlyIncome_slack(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonthlyIncome().split(",")[0]));
		vo.setMonthlyIncome_peak(CommonHelper.str2BigDecimal(industryBizIncomeCost.getMonthlyIncome().split(",")[1]));
		vo.setBusinessStartDate(CommonHelper.date2Str(industryBizIncomeCost.getBusinessStartDate(), CommonHelper.DF_DATE));
		
		return vo;
	}

	@Override
	public void deleteBizIncome(Long id) {
		industryBizIncomeCostDao.delete(id);
	}

	@Override
	public Page<IndustryBizStock> findStockPageByProId(Integer pageNumber, Integer pageSize, Long projectId) {
		List<IndustryBizStock> list = industryBizStockDao.findByProjectId(projectId);
		return new PageImpl<IndustryBizStock>(list, new PageRequest(pageNumber, pageSize), list.size());
	}

	@Override
	public void saveStock(IndustryBizStock industryBizStock) {
		industryBizStockDao.save(industryBizStock);
	}

	@Override
	public IndustryBizStock findOneStock(Long id) {
		return industryBizStockDao.findOne(id);
	}

	@Override
	public void deleteStock(Long id) {
		industryBizStockDao.delete(id);
	}

	@Override
	public void saveIndustryBizIncomeCost(IndustryBizIncomeCostAgrVO vo) throws Exception {
		IndustryBizIncomeCost industryBizIncomeCost = new IndustryBizIncomeCost();
		
		BeanUtils.copyProperties(vo, industryBizIncomeCost);
		industryBizIncomeCost.setBusinessStartDate(CommonHelper.str2Date(vo.getBusinessStartDate(), CommonHelper.DF_DATE));
		industryBizIncomeCost.setMonth(vo.getMonth_slack() + "," + vo.getMonth_peak());
		industryBizIncomeCost.setMonthlyIncome(vo.getMonthlyIncome_slack() + "," + vo.getMonthlyIncome_peak());
	
		industryBizIncomeCostDao.save(industryBizIncomeCost);
	}
	
	@Override
	public Page<OtherIncomeCommon> findOtherIncomeCommonPageByProId(Integer pageNumber, Integer pageSize, Long projectId, String type) {
		List<OtherIncomeCommon> list = otherIncomeCommonDao.findByProjectIdAndType(projectId, type);
		return new PageImpl<OtherIncomeCommon>(list, new PageRequest(pageNumber, pageSize), list.size());
	}

	@Override
	public void saveOtherIncomeCommon(OtherIncomeCommonVO vo) {
		OtherIncomeCommon otherIncomeCommon = new OtherIncomeCommon();
		BeanUtils.copyProperties(vo, otherIncomeCommon);
		otherIncomeCommon.setTime(CommonHelper.str2Date(vo.getTime(), CommonHelper.DF_DATE));
		otherIncomeCommonDao.save(otherIncomeCommon);
	}

	@Override
	public OtherIncomeCommon findOneOtherIncomeCommon(Long id) {
		return otherIncomeCommonDao.findOne(id);
	}

	@Override
	public void deleteOtherIncomeCommon(Long id) {
		otherIncomeCommonDao.delete(id);
	}

	@Override
	public Long saveFamilyConsume(FamilyConsume vo) {
		familyConsumeDao.save(vo);
		return vo.getId();
	}

	@Override
	public IndustryBizVO findOneIndustryBizVOByProjectId(Long projectId) {
		IndustryBiz industryBiz = industryBizDao.findByProjectId(projectId);
		Transport transport = transportDao.findByProjectId(projectId);
		
		if (industryBiz != null && transport != null) {
			IndustryBizVO vo = new IndustryBizVO();
			BeanUtils.copyProperties(industryBiz, vo);
			BeanUtils.copyProperties(transport, vo);
			vo.setIndustryBizId(industryBiz.getId());
			vo.setTransportId(transport.getId());
			vo.setBoughtBuiltDate(CommonHelper.date2Str(industryBiz.getBoughtBuiltDate(), CommonHelper.DF_DATE));
			vo.setBusinessStartDate(CommonHelper.date2Str(transport.getBusinessStartDate(), CommonHelper.DF_DATE));
			return vo;
		}
		
		return null;
	}

	@Override
	public FamilyConsume findOneFamilyConsumeByProIdAndType(Long projectId, String type) {
		return familyConsumeDao.findByProjectIdAndType(projectId, type);
	}

	@Override
	public List<CommonInfo> findOneCommonInfoByProIdAndType(Long projectId, String type) {
		return commonInfoDao.findByProjectIdAndType(projectId, type);
	}
	@Override
	public Long saveCommonInfo(CommonInfo vo) {
		commonInfoDao.save(vo);
		return vo.getId();
	}
	@Override
	public BigDecimal getMaxId() {
		return industryBizIncomeCostDao.getMaxId();
	}
	@Override
	public IndustryBizIncomeCost findOne(Long id) {
		return industryBizIncomeCostDao.findOne(id);
	}
}
