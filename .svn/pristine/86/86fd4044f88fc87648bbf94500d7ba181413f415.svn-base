
package com.coamctech.bxloan.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.repository.PagingAndSortingRepository;


import com.coamctech.bxloan.entity.ExpectCashFlowDetail;


public interface ExpectCashFlowDetailDao extends PagingAndSortingRepository<ExpectCashFlowDetail, Long>,

JpaSpecificationExecutor<ExpectCashFlowDetail>{

    ExpectCashFlowDetail findById(Long id);
    
    @Query("select ecf from ExpectCashFlowDetail ecf where ecf.expectCashFlowId = ?1")
	List<ExpectCashFlowDetail> findByExpectCashFlowId(Long expectCashFlowId);
    @Query("select ecf from ExpectCashFlowDetail ecf where ecf.expectCashFlowId = ?1 and ecf.type = ?2")
	List<ExpectCashFlowDetail> findByExpectCashFlowIdAndType(Long expectCashFlowId, String type);

}


        