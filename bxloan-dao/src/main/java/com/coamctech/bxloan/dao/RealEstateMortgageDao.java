package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RealEstateMortgage;

public interface RealEstateMortgageDao extends
		PagingAndSortingRepository<RealEstateMortgage, Long>,
		JpaSpecificationExecutor<RealEstateMortgage> {

}
