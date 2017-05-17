package com.coamctech.bxloan.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ExpectedCashFlowInfo;

public interface ExpectedCashFlowInfoDao extends
		PagingAndSortingRepository<ExpectedCashFlowInfo, Long>,
		JpaSpecificationExecutor<ExpectedCashFlowInfo> {

	@Query("select max(ecf.id) from ExpectedCashFlowInfo ecf order by ecf.id desc")
	Long getMaxId();

	@Modifying
	@Query("delete from ExpectedCashFlowInfo ecf where ecf.objId=?1")
	void deleteByObjId(Long objId);

	@Query("from ExpectedCashFlowInfo ecf where ecf.objId =?1 and ecf.objType =?2 and ecf.incomeExpendFlag =?3 order by ecf.id desc")
	List<ExpectedCashFlowInfo> findAll(Long objId, String objType, String flag);

	@Query("select count(*) from ExpectedCashFlowInfo ecf where ecf.objId=?1 and ecf.monthOfYear=?2 and ecf.incomeExpendFlag=?3")
	int checkMonthIsNotExist(Long objId, Date monthOfYear, String flag);
}
