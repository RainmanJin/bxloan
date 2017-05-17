package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.PersonDetails;


public interface PersonDetailsDao extends PagingAndSortingRepository<PersonDetails, Long>,
JpaSpecificationExecutor<PersonDetails>{
	@Query("select pd from PersonDetails pd where pd.certificateNum = ?1 and pd.certificateTypeCd = ?2")
	List<PersonDetails> findByCertificateNumAndCertificateTypeCd(String certificateNum, String certificateTypeCd);
}
