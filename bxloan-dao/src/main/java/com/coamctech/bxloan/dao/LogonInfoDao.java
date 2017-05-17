package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.LogonInfo;

public interface LogonInfoDao extends PagingAndSortingRepository<LogonInfo, String>,
		JpaSpecificationExecutor<LogonInfo> {

}