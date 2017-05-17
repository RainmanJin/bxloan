
package com.coamctech.bxloan.dao;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import org.springframework.data.repository.PagingAndSortingRepository;


import com.coamctech.bxloan.entity.ExpectCashFlow;


public interface ExpectCashFlowDao extends PagingAndSortingRepository<ExpectCashFlow, Long>,

JpaSpecificationExecutor<ExpectCashFlow>{

    ExpectCashFlow findById(Long id);

}


        