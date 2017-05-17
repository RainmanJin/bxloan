package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.coamctech.bxloan.entity.ActualontrollerCorporatinon;

public interface ActualontrollerCorporatinonDao extends PagingAndSortingRepository<ActualontrollerCorporatinon, String>,JpaSpecificationExecutor<ActualontrollerCorporatinon>{

}