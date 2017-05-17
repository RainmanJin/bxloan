package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Code;


public interface CodeDao extends PagingAndSortingRepository<Code, String>,
		JpaSpecificationExecutor<Code> {
	
	@Query("select c from Code c where c.id.codeType = ?1")
	List<Code> findByCodeType(String codeType);
	
	@Query("select c.codeName from Code c where c.id.codeType = ?1 and c.codeValue = ?2 ")
	String transilateCode(String codeType, String codeValue);
	
	@Query("select c from Code c where c.id.codeType = ?1 and c.codeValue = ?2 ")
	List<Code> findCodeByTypeAndValue(String codeType, String codeValue);
}
