
package com.coamctech.bxloan.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CultivateNonAgriculture;


public interface CultivateNonAgricultureDao extends PagingAndSortingRepository<CultivateNonAgriculture, Long>,

JpaSpecificationExecutor<CultivateNonAgriculture>{


        


    CultivateNonAgriculture findById(Long id);
    
    @Query("select cna from CultivateNonAgriculture cna where cna.projectId = ?1")
	List<CultivateNonAgriculture> findByProjectId(Long projectId);


    @Query(value = "select nvl(sum(c.income), 0) from cultivate_non_agriculture c where c.project_id = ?1", nativeQuery = true)
    BigDecimal sumIncome(Long projectId);
    
    @Query(value = "select nvl(sum(c.cost), 0) from cultivate_non_agriculture c where c.project_id = ?1", nativeQuery = true)
    BigDecimal sumCost(Long projectId);

    @Query(value = "select nvl(sum(c.crop_value), 0) from cultivate_non_agriculture c where c.project_id = ?1", nativeQuery = true)
    BigDecimal sumCropValue(Long projectId);
}


        