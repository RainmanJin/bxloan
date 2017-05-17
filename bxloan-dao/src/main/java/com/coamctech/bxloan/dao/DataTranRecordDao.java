package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.DataTranRecord;

public interface DataTranRecordDao extends PagingAndSortingRepository<DataTranRecord, Long>,
		JpaSpecificationExecutor<DataTranRecord> {

}