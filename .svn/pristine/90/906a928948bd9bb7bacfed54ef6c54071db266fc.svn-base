package com.coamctech.bxloan.service.aand.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.ProduceAreaInfoDao;
import com.coamctech.bxloan.entity.ProduceAreaInfo;
import com.coamctech.bxloan.service.aand.ProduceAreaInfoService;
import com.coamctech.bxloan.service.model.aand.ProduceAreaInfoVo;

@Transactional
@Service("produceAreaInfoService")
public class ProduceAreaInfoServiceImpl implements ProduceAreaInfoService{

	@Autowired
	private ProduceAreaInfoDao produceAreaInfoDao;
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<ProduceAreaInfo> findBySearch(String search,
			Integer pageNumber, Integer pageSize,String type,Long projectId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("select p from ProduceAreaInfo p where 1=1 ");//字符串处理中不生成新的对象(内存)  1=1就是这条数据
		jpql.append(" and p.projectId = "+projectId);
		if(StringUtils.isNotEmpty(search)){//String类型操作方法的补充
			params.add("%"+search+"%");
		}
		return dynamicQuery.query(new PageRequest(pageNumber - 1,
				pageSize), jpql.toString(), params.toArray());
	}

	@Override
	public void save(ProduceAreaInfoVo produceAreaInfoVo) {
		ProduceAreaInfo produceAreaInfo = new ProduceAreaInfo();
		BeanUtils.copyProperties(produceAreaInfoVo, produceAreaInfo);
		produceAreaInfoDao.save(produceAreaInfo);
	}

	@Override
	public void delete(Long id) {
		produceAreaInfoDao.delete(id);
	}

	@Override
	public ProduceAreaInfo get(Long id) {
		return produceAreaInfoDao.findOne(id);
	}

	@Override
	public void saveProduceAreaInfo(ProduceAreaInfo produceAreaInfo) {
		this.produceAreaInfoDao.save(produceAreaInfo);
	}
	
}
