package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.TrafficCar;

public interface TrafficCarDao extends
		PagingAndSortingRepository<TrafficCar, Long>,
		JpaSpecificationExecutor<TrafficCar> {

}
