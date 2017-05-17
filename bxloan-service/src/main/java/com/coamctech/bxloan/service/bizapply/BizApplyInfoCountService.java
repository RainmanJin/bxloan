package com.coamctech.bxloan.service.bizapply;

import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.excel.PoiExcel;
import com.coamctech.bxloan.service.model.bizapply.compute.AssetLiabilityInfoVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;

public interface BizApplyInfoCountService {
	
	/**
	 * 检查财务报表数据是否完整
	 * @param projId
	 */
	public MsgResult checkFinancialStatements(Long projId);
	/**
	 * 通过业务申请信息计算资产负债信息
	 * @param projId
	 * @return
	 */
	AssetLiabilityInfoVo buildAsseLliabilityInfo(Long projId);
	
	/**
	 * 查询现金+存款（通过非固定资产及其负债信息）
	 * @param projId	业务id
	 * @return
	 */
	BigDecimal findCashDepositByBizNfal(Long projId);
	
	
	
	/**
	 * 生成损益表及家庭负债表
	 * @param projId	业务申请ID
	 * @return
	 */
	PoiExcel createProfitLossAsseLliabExcel(Long projId);
	
	void test(Long projectId);
}
