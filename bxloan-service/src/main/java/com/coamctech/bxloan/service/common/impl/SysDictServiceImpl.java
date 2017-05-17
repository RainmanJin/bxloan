package com.coamctech.bxloan.service.common.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.SysDictItemDao;
import com.coamctech.bxloan.entity.SysDictItem;
import com.coamctech.bxloan.service.common.SysDictService;
import com.coamctech.bxloan.service.model.common.NameValueVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Service("sysDictService")
public class SysDictServiceImpl implements SysDictService {
	@Autowired
	private SysDictItemDao sysDictItemDao;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Override
	public List<SysDictItem> findList(String type,Long pid) {
		StringBuffer sb=new StringBuffer();
		sb.append("FROM SysDictItem sdi where sdi.state='1' AND sdi.type=?1");
		List<Object> params=Lists.newArrayList();
		params.add(type);
		if(pid!=null&&pid>0){
			sb.append(" AND sdi.pid =?2");
			params.add(pid);
		}else{
			sb.append(" AND sdi.pid is null");
		}
		sb.append(" ORDER BY sdi.pid nulls first,sdi.sortNum");
		List<SysDictItem> list=dynamicQuery.query(SysDictItem.class, sb.toString(), params.toArray());
		return list;
	}
	@Override
	public List<SysDictItem> findListByPids(String type, Set<Long> pids) {
		StringBuffer sb=new StringBuffer();
		sb.append("FROM SysDictItem sdi where sdi.state='1' AND sdi.type=?1");
		List<Object> params=Lists.newArrayList();
		params.add(type);
		if(CollectionUtils.isNotEmpty(pids)){
			sb.append(" AND sdi.pid in (?2)");
			params.add(pids);
		}
		sb.append(" ORDER BY sdi.pid nulls first,sdi.sortNum");
		List<SysDictItem> list=dynamicQuery.query(SysDictItem.class, sb.toString(), params.toArray());
		return list;
	}
	@Override
	public List<SysDictItem> findAllList(String type) {
		return sysDictItemDao.findList(type);
	}
	@Override
	public Map<String, Object> findDictData(String type) {
		Map<String, Object> map=Maps.newHashMap();
		List<SysDictItem> list=sysDictItemDao.findList(type);
		List<NameValueVo> types=Lists.newArrayList();
		List<NameValueVo> vaules=Lists.newArrayList();
		Map<String, List<NameValueVo>> temp_map=Maps.newHashMap();
		Map<Long, NameValueVo> item_map=Maps.newHashMap();
		NameValueVo nv=null;
		for (SysDictItem sdi : list) {
			item_map.put(sdi.getId(), new NameValueVo(sdi.getName(), sdi.getValue()));
		}
		for (SysDictItem sdi : list) {
			nv=new NameValueVo(sdi.getName(), sdi.getValue());
			if(sdi.getPid()==null){
				types.add(nv);
			}else{
				vaules.add(nv);
				String key=item_map.get(sdi.getPid()).getValue();
				if(temp_map.containsKey(key)){
					temp_map.get(key).add(nv);
				}else{
					temp_map.put(key, new ArrayList<NameValueVo>(Arrays.asList(nv)));
				}
			}
		}
		map.put("types", types);
		map.put("items", vaules);
		map.put("cascadeData", temp_map);
		return map;
	}

}
