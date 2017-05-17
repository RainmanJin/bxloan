package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ProduceAreaInfo;

public interface ProduceAreaInfoDao extends JpaSpecificationExecutor<ProduceAreaInfo>,
		PagingAndSortingRepository<ProduceAreaInfo, Long>{
	/**
	 * @param projId
	 * @return
	 */
	@Query("from ProduceAreaInfo pai where pai.projectId=?1 AND pai.type=?2 order by pai.id")
	List<ProduceAreaInfo> findListByProjId(Long projId,String type);
	/**
	 * @param projId
	 * @param type
	 * @return
	 */
	@Query("select count(pai.id) from ProduceAreaInfo pai where pai.projectId=?1 AND pai.type=?2")
	long findCountByProjId(Long projId,String type);
	

}
