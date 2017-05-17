package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.HighManager;

public interface HighManagerDao extends PagingAndSortingRepository<HighManager, String>,
JpaSpecificationExecutor<HighManager>{

}
