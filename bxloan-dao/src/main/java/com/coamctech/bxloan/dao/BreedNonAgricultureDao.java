
package com.coamctech.bxloan.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.BreedNonAgriculture;


public interface BreedNonAgricultureDao extends PagingAndSortingRepository<BreedNonAgriculture, Long>,

JpaSpecificationExecutor<BreedNonAgriculture>{


        


    BreedNonAgriculture findById(Long id);


    @Query("select bna from BreedNonAgriculture bna where bna.projectId = ?1")
	List<BreedNonAgriculture> findByProjectId(Long projectId);


    @Query(value = "select nvl(sum(b.income), 0) from breed_non_agriculture b where b.project_id = ?1", nativeQuery = true)
    BigDecimal sumIncome(Long projectId);
    
    @Query(value = "select nvl(sum(b.cost), 0) from breed_non_agriculture b where b.project_id = ?1", nativeQuery = true)
    BigDecimal sumCost(Long projectId);

    @Query(value = "select nvl(sum(b.breed_stock_value), 0) from breed_non_agriculture b where b.project_id = ?1", nativeQuery = true)
    BigDecimal sumBreedStockValue(Long projectId);
}


        