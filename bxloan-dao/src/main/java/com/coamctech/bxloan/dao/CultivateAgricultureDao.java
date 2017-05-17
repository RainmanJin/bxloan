
package com.coamctech.bxloan.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.repository.PagingAndSortingRepository;


import com.coamctech.bxloan.entity.CultivateAgriculture;


public interface CultivateAgricultureDao extends PagingAndSortingRepository<CultivateAgriculture, Long>,

JpaSpecificationExecutor<CultivateAgriculture>{


        


    CultivateAgriculture findById(Long id);

    @Query("select ca from CultivateAgriculture ca where ca.projectId = ?1")
	List<CultivateAgriculture> findByProjectId(Long projectId);
    
    @Query(value = "select nvl(sum(c.sale_income_total), 0) from cultivate_agriculture c where c.project_id = ?1 and c.type = ?2", nativeQuery = true)
    BigDecimal sumSaleIncomeTotal(Long projectId, String type);


        


}


        