package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.UniteGuNego;


public interface UniteGuNegoDao extends
PagingAndSortingRepository<UniteGuNego, Long>,
JpaSpecificationExecutor<UniteGuNego>{
	
	UniteGuNego findByUnGuId(String unGuId);
}
