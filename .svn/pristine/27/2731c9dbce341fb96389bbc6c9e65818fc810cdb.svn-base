package com.coamctech.bxloan.service.bizapply;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.CommonInfo;
import com.coamctech.bxloan.entity.FamilyConsume;
import com.coamctech.bxloan.entity.IndustryBizIncomeCost;
import com.coamctech.bxloan.entity.IndustryBizStock;
import com.coamctech.bxloan.entity.OtherIncomeCommon;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizIncomeCostAgrVO;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizIncomeCostVO;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizVO;
import com.coamctech.bxloan.service.model.bizapply.OtherIncomeCommonVO;

public interface IndustryBizService {

	public Map<String, Long> saveBasicInfo(IndustryBizVO vo) throws Exception;

	public enum Msg {
		SUC("<strong>保存成功！</strong>"), ERR("<strong>保存失败！</strong>");

		private final String msg;

		private Msg(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}
	}

	public Page<IndustryBizIncomeCost> findIncomeCostPageByProIdAndTypeAndFuturePastType(Integer pageNumber, Integer pageSize, Long projectId, String type, String futurePastType);

	public void saveIndustryBizIncomeCost(IndustryBizIncomeCostVO vo) throws Exception;
	
	public void saveIndustryBizIncomeCost(IndustryBizIncomeCostAgrVO vo) throws Exception;

	public IndustryBizIncomeCostVO findOneBizIncomeVO(Long id);
	
	public IndustryBizIncomeCostAgrVO findOneBizIncomeAgrVO(Long id);

	public void deleteBizIncome(Long id);

	public Page<IndustryBizStock> findStockPageByProId(Integer pageNumber, Integer pageSize, Long projectId);

	public void saveStock(IndustryBizStock industryBizStock);

	public IndustryBizStock findOneStock(Long id);

	public void deleteStock(Long id);

	public Page<OtherIncomeCommon> findOtherIncomeCommonPageByProId(Integer pageNumber, Integer pageSize, Long projectId, String type);

	public void saveOtherIncomeCommon(OtherIncomeCommonVO vo);

	public OtherIncomeCommon findOneOtherIncomeCommon(Long id);

	public void deleteOtherIncomeCommon(Long id);

	public Long saveFamilyConsume(FamilyConsume vo);

	public IndustryBizVO findOneIndustryBizVOByProjectId(Long projectId);

	public FamilyConsume findOneFamilyConsumeByProIdAndType(Long projectId, String type);

	public List<CommonInfo> findOneCommonInfoByProIdAndType(Long projectId, String type);

	public Long saveCommonInfo(CommonInfo vo);
	
	public BigDecimal getMaxId();
	
	public IndustryBizIncomeCost findOne(Long id);
}
