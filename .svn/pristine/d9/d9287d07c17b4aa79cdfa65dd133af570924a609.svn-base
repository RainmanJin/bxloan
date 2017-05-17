
package com.coamctech.bxloan.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.BreedAgriculture;


public interface BreedAgricultureDao extends PagingAndSortingRepository<BreedAgriculture, Long>,

JpaSpecificationExecutor<BreedAgriculture>{


        


    BreedAgriculture findById(Long id);


    @Query("select ba from BreedAgriculture ba where ba.projectId = ?1")
	List<BreedAgriculture> findByProjectId(Long projectId);
    
    @Query(value = "select nvl(sum(b.sale_income_total), 0) from breed_agriculture b where b.project_id = ?1 and b.type = ?2", nativeQuery = true)
    BigDecimal sumSaleIncomeTotal(Long projectId, String type);


        


}


        