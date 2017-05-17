package com.coamctech.bxloan.service.afterloan;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.service.model.afterloan.NormalRepaymentDetailVo;
import com.coamctech.bxloan.service.model.afterloan.NormalRepaymentVo;

/**
 * 类名称：NormalRepaymentService
 * 类描述 ：正常还款
 * 创建人: hwl
 * 创建时间：2015年5月12日 上午10:39:46  
 * 修改人：hwl
 * 修改时间：2015年5月12日 上午10:39:46  
 * 修改备注：
 * 版本： V1.0
 */
public interface NormalRepaymentService {
	
	/**
	 * 正常还款列表分页查询
	 * @param pageSize
	 * @param pageNum
	 * @param userId 当前用户id
	 * @param orgId	当前机构id
	 * @param vo
	 * @return
	 */
	Page<NormalRepaymentVo> findPage(int pageSize, int pageNum, Long userId, Long orgId, NormalRepaymentVo vo);
	
	
	/**
	 * 查询还款详细信息
	 * @param contractId	合同id
	 * @param partyId		客户id
	 * @param rpId			还款计划id
	 * @param rpdId			还款计划明细id
	 * @return
	 */
	NormalRepaymentDetailVo findNrDetail(long contractId,long partyId,long rpId,long rpdId,Long curOrgId);
}
