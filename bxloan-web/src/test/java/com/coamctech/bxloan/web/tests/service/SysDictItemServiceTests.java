package com.coamctech.bxloan.web.tests.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.dao.SysDictItemDao;
import com.coamctech.bxloan.entity.SysDictItem;
import com.coamctech.bxloan.service.common.SysDictService;

public class SysDictItemServiceTests extends BaseSpringJUnit4Tester{
	@Autowired
	private SysDictItemDao sysDictItemDao;
	@Autowired
	private SysDictService sysDictService;
	@Test
	public void testList() throws Exception {
		sysDictItemDao.findList("ND_NO_FIEXED_ASSET");
		Map<String, Object> map=sysDictService.findDictData("ND_NO_FIEXED_ASSET");
		System.out.println(JSON.toJSONString(map.get("items")));
	}
}
