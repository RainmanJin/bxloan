package com.coamctech.bxloan.service.bizapply;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.DrCreditLiabilityDetail;

public interface DrCreditInfoService {
	/**
	 * @param pageSize
	 * @param pageNum
	 * @param projectId
	 * @return
	 */
	Page<DrCreditLiabilityDetail> findDrCldPage(int pageSize, int pageNum,Long projectId);
	/**
	 * @param id
	 * @return
	 */
	DrCreditLiabilityDetail findDrCld(Long id);
	/**
	 * @param drCld
	 */
	void saveDrCld(DrCreditLiabilityDetail drCld);
	/**
	 * @param id
	 */
	void delDrCld(Long id);
	
	String buildValidatJS(String... tableTypeCd);
}
