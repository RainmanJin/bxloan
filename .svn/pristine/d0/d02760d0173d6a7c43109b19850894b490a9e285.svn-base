package com.coamctech.bxloan.service.afterloan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.OverdueState;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;

public interface LoanOperationsService {
	/**记录逾期情况
	 * @param paramsMap 参数集
	 * @param RepayingPlan rp
	 * @param rpdAllList
	 * @param osList
	 * */
	void recordOverdueState(Map<String, Object> paramsMap, RepayingPlan rp, List<RepayingPlanDetail> rpdAllList, List<OverdueState> osList);
	/**记录欠款情况
	 * @param paramsMap 参数集
	 * @param rpdList
	 * @param rdList
	 * @param adList
	 * @param contract
	 * @return totalOverdueOutAmt,
				rpd,rd,ad
	 * */
	List recordArrearsDetail(Map<String, Object> paramsMap, List<RepayingPlanDetail> rpdList, List<RepayingDetail> rdList, List<ArrearsDetail> adList, Contract contract);
	/**
	 * 校验是否可以修改客户的文档类型
	 * @param partyId
	 * @return  boolean
	 */
	boolean validateIsModifyDocumentType(Long partyId, Long projectId);
}
