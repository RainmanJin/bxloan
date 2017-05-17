package com.coamctech.bxloan.service.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coamctech.bxloan.entity.SysDictItem;

public interface SysDictService {
	/**
	 * @param type
	 * @param pid
	 * @return
	 */
	List<SysDictItem> findList(String type,Long pid);
	/**
	 * @param type
	 * @param pids
	 * @return
	 */
	List<SysDictItem> findListByPids(String type,Set<Long> pids);
	
	/**
	 * @param type
	 * @return
	 */
	List<SysDictItem> findAllList(String type);
	
	
	/**
	 * @param type
	 * @return
	 */
	Map<String, Object> findDictData(String type);
	
}
