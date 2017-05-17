package com.coamctech.bxloan.service.contractmng;


public interface RenewaPledgeService {

	/**
	 * 功能说明：根据项目id，处理抵质押物列表
	 * 当合同结清或者提前还款时，合同状态为【合同结清】，累计抵押金额（即已设定金额）需要释放
	 * 主要对象：【关联新增】，【已关联】，【关联置换】计抵押金额（即已设定金额）需要释放
	 * @param projectId  
	 * @throws RollbackableBizException(小贷)
	 * @author lijing
	 * @date 2014-09-04
	 */
	void handlePledgeAmtWhenContractEnd(Long projectId) throws RuntimeException;

}
