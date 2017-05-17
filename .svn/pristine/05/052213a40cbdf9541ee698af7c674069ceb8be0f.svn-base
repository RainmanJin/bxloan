package com.coamctech.bxloan.service.aand.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.OtherIncomeDao;
import com.coamctech.bxloan.entity.OtherIncome;
import com.coamctech.bxloan.service.aand.OtherIncomeService;

@Transactional
@Service("otherIncomeService")
public class OtherIncomeServiceImpl implements OtherIncomeService{
	
	@Autowired
	private OtherIncomeDao otherIncomeDao;
	@Autowired
	private DynamicQuery dynamicQuery;

	@SuppressWarnings("unchecked")
	@Override
	public Page<OtherIncome> findBySearch(String search,
			Integer pageNumber, Integer pageSize, String type,Long projectId,String futurePastType) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("select o from OtherIncome o where o.type = " + type);//字符串处理中不生成新的对象(内存)  1=1就是这条数据
		jpql.append(" and o.projectId = "+projectId);
		jpql.append(" and o.futurePastType = "+futurePastType);
		if(StringUtils.isNotEmpty(search)){//String类型操作方法的补充
			params.add("%"+search+"%");
		}
		return dynamicQuery.query(new PageRequest(pageNumber - 1,
				pageSize), jpql.toString(), params.toArray());
	}

	@Override
	public void save(OtherIncome otherIncome) {
		otherIncomeDao.save(otherIncome);
	}

	@Override
	public void delete(Long id) {
		otherIncomeDao.delete(id);
	}

	@Override
	public OtherIncome get(Long id) {
		return otherIncomeDao.findOne(id);
	}

	@Override
	public void saveOtherIncome(OtherIncome otherIncome) {
		this.otherIncomeDao.save(otherIncome);
	}
}
