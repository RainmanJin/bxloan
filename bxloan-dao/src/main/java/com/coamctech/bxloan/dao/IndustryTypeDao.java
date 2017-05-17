package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.IndustryType;

public interface IndustryTypeDao extends PagingAndSortingRepository<IndustryType, String>,
		JpaSpecificationExecutor<IndustryType> {

	@Query("select t from IndustryType t where t.industryTypeCd <> '0' order by t.industryTypeCd")
	List<IndustryType> findAllItems();
   
	@Query("select t.industryTypeName from IndustryType t where t.industryTypeCd = ?1 ")
	String findIndustryNameByIndustryCd(String industryCd);
}
