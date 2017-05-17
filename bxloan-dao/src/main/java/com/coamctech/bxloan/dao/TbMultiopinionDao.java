package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.TbMultiopinion;

public interface TbMultiopinionDao extends JpaSpecificationExecutor<TbMultiopinion>,
		PagingAndSortingRepository<TbMultiopinion, Long> {

}
