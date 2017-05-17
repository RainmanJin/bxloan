package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.coamctech.bxloan.entity.InfoCellConfig;

public interface InfoCellConfigDao extends PagingAndSortingRepository<InfoCellConfig, Long>,JpaSpecificationExecutor<InfoCellConfig>{

}