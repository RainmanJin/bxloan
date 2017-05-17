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
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.GainDistributionDao;
import com.coamctech.bxloan.entity.GainDistribution;
import com.coamctech.bxloan.service.aand.GainDistributionService;
import com.coamctech.bxloan.service.model.aand.GainDistributionVo;

@Transactional
@Service("gainDistributionService")
public class GainDistributionServiceImpl implements GainDistributionService{
	@Autowired
	private GainDistributionDao gainDistributionDao;
	@Autowired
	private DynamicQuery dynamicQuery;

	@SuppressWarnings("unchecked")
	@Override
	public Page<GainDistribution> findBySearch(String search,
			Integer pageNumber, Integer pageSize, String type,Long projectId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("select g from GainDistribution g where g.type = " + type);//字符串处理中不生成新的对象(内存)  1=1就是这条数据
		jpql.append(" and g.projectId = "+projectId);
		if(StringUtils.isNotEmpty(search)){//String类型操作方法的补充
			params.add("%"+search+"%");
		}
		return dynamicQuery.query(new PageRequest(pageNumber - 1,
				pageSize), jpql.toString(), params.toArray());
	}

	@Override
	public void save(GainDistributionVo gainDistributionVo) {
	    GainDistribution gainDistribution= new GainDistribution();
	    gainDistribution.setTime(CommonHelper.str2Date(gainDistributionVo.getTime(), CommonHelper.DF_DATE));
		BeanUtils.copyProperties(gainDistributionVo, gainDistribution);
		gainDistributionDao.save(gainDistribution);
	}

	@Override
	public void delete(Long id) {
		gainDistributionDao.delete(id);
	}

	@Override
	public GainDistribution get(Long id) {
		return gainDistributionDao.findOne(id);
	}

	@Override
	public void saveGainDistribution(GainDistribution gainDistribution) {
		this.gainDistributionDao.save(gainDistribution);
	}	
}
