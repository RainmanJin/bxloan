package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.MoneyRate;


public interface MoneyRateDao extends PagingAndSortingRepository<MoneyRate, Long>,
JpaSpecificationExecutor<MoneyRate> {

	MoneyRate findByMoneyRateId(Long moneyRateId);
	
	@Query("from MoneyRate p where p.valid='1' and p.timeLimit=?")
	List<MoneyRate> findListByTimeLimit(String timeLimit);
}
