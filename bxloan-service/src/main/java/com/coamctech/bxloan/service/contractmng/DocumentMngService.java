package com.coamctech.bxloan.service.contractmng;


public interface DocumentMngService {

	/**
	 * 批量修改客户文档的关联方式
	 * 
	 * @author lijing
	 * @date 2014-09-04
	 * @param partyId
	 * @throws RollbackableBizException(小贷)
	 */
	void updateCreateTypeAll(Long partyId, String codeValue) throws RuntimeException;

}
