package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.TallyCertificateDetail;

public interface TallyCertificateDetailDao extends
		PagingAndSortingRepository<TallyCertificateDetail, Long>,
		JpaSpecificationExecutor<TallyCertificateDetail> {
	/**
	 * 查询单据凭证详细 （recNo ASC）
	 * @param billCd
	 * @return
	 */
	@Query("from TallyCertificateDetail tcd where tcd.billCd=?1 order by tcd.recNo")
	List<TallyCertificateDetail> findListByBillCd(String billCd);
}
