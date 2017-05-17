package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Transport;

public interface TransportDao extends PagingAndSortingRepository<Transport, Long>, JpaSpecificationExecutor<Transport> {

	Transport findByProjectId(Long projectId);
}
