package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Individual;

public interface IndividualDao extends PagingAndSortingRepository<Individual, Long>,JpaSpecificationExecutor<Individual>{

	List<Individual> findByCertificateTypeCdAndCertificateNum(String certificateTypeCd, String certificateNum);
}