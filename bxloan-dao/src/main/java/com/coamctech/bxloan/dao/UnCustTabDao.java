package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.UnCustTab;


public interface UnCustTabDao extends PagingAndSortingRepository<UnCustTab, String>,
JpaSpecificationExecutor<UnCustTab>{
	
	@Modifying
	@Query("delete from UnCustTab uct where uct.unGuId =?1 ")
	void deleteByUnGuId(String unGuId);
	@Query("select uct from UnCustTab uct where uct.unGuId =?1 ")
	List<UnCustTab> findByUnGuId(String unGuId);
	
	/**
	 * 根据联保体id查询联保体客户列表
	 * @param uniteGuNegoId
	 * @return
	 */
	@Query("select uct from UnCustTab uct where uct.uniteGuNegoId =?1 and uct.status=?2")
	List<UnCustTab> findListByUniteGuNegoId(Long uniteGuNegoId,String status);
}
