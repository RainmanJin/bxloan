package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.TallyCertificate;

public interface TallyCertificateDao extends PagingAndSortingRepository<TallyCertificate, Long>,JpaSpecificationExecutor<TallyCertificate>{

	/**
	 * 根据业务编号查询TallyCertificate
	 * @param BillCd
	 * @return
	 */
	@Query(value = "from TallyCertificate where billCd = ?1")
	List<TallyCertificate> findListByBillCd(String billCd);
}
