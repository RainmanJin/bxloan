package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.SysDictItem;

public interface SysDictItemDao extends
		PagingAndSortingRepository<SysDictItem, Long>,
		JpaSpecificationExecutor<SysDictItem> {
	@Query("FROM SysDictItem sdi where sdi.state='1' AND sdi.type=?1 ORDER BY sdi.pid nulls first,sdi.sortNum")
	List<SysDictItem> findList(String type);
}
