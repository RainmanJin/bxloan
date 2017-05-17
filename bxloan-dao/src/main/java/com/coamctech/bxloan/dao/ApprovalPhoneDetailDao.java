package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ApprovalPhoneDetail;

public interface ApprovalPhoneDetailDao extends JpaSpecificationExecutor<ApprovalPhoneDetail>,
	PagingAndSortingRepository<ApprovalPhoneDetail, Long>{

	
}
