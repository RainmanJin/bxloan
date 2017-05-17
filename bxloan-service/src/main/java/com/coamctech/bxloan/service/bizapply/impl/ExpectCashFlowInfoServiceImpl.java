package com.coamctech.bxloan.service.bizapply.impl;

import java.util.Date;
import java.util.List;





import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.ExpectedCashFlowInfoDao;
import com.coamctech.bxloan.entity.ExpectedCashFlowInfo;
import com.coamctech.bxloan.service.bizapply.ExpectCashFlowInfoService;
import com.coamctech.bxloan.service.utils.EcfiObjTypeEnum;
import com.google.common.collect.Lists;

@Transactional
@Service
public class ExpectCashFlowInfoServiceImpl implements ExpectCashFlowInfoService {

	@Autowired
	private ExpectedCashFlowInfoDao ecfDao;
	@Autowired
	private DynamicQuery dynamicQuery;

	@Override
	public Long getMaxId() {
		return ecfDao.getMaxId();
	}

	@Override
	public ExpectedCashFlowInfo save(ExpectedCashFlowInfo ecf) {
		if(ecf==null){
			return null;
		}
		ExpectedCashFlowInfo db_ecfi=null;
		if(ecf.getId()!=null&&ecf.getId()>0){
			db_ecfi=ecfDao.findOne(ecf.getId());//加载数据库数据
		}
		Date now =new Date();
		if(db_ecfi==null){
			db_ecfi=new ExpectedCashFlowInfo();//新增
			db_ecfi.setObjId(ecf.getObjId());
			db_ecfi.setObjCode(ecf.getObjCode());
			db_ecfi.setCreateTime(now);
		}
		db_ecfi.setUpdateTime(now);
		BeanUtils.copyProperties(ecf, db_ecfi, "id","objId","objCode","updateTime","createTime");
		ecfDao.save(db_ecfi);
		return db_ecfi;
	}

	@Override
	public void delete(Long eid) {
		ecfDao.delete(eid);
	}

	@Override
	public void deleteByObjId(Long objId) {
		ecfDao.deleteByObjId(objId);
		
	}

	@Override
	public List<ExpectedCashFlowInfo> findAll(Long projectId,String objType, Long objId, String objCode,
			String incomeExpendFlag) {
		List<Object> params = Lists.newArrayList();
		StringBuffer jpql = new StringBuffer("from ExpectedCashFlowInfo ecf where ecf.projectId =?1  and ecf.incomeExpendFlag=?2");
		jpql.append(" and ecf.objType =?3");
		params.add(projectId);
		params.add(incomeExpendFlag);
		params.add(objType);
		int i=4;
		if (StringUtils.isNotEmpty(String.valueOf(objCode))) {
			jpql.append(" and ecf.objCode =?"+i++);
			params.add(objCode);
		} else if (objId != null && objId > 0) {
			jpql.append(" and ecf.objId =?"+i++);
			params.add(objId);
			
		}
		jpql.append(" order by ecf.id desc");
		return dynamicQuery.query(ExpectedCashFlowInfo.class,jpql.toString(), params.toArray());
	}

	@Override
	public ExpectedCashFlowInfo getExpectedCashFlowInfoById(Long id) {
		return ecfDao.findOne(id);
	}

	@Override
	public Long checkMonthIsNotExist(ExpectedCashFlowInfo info) {
		return this.getMonthCount(info);
	}
	
	@Override
	public boolean checkMonthIsExist(ExpectedCashFlowInfo info){
		return this.getMonthCount(info) > 0;
	}
	
	private Long getMonthCount(ExpectedCashFlowInfo info){
		StringBuffer jpql = new StringBuffer("from ExpectedCashFlowInfo ecf where ecf.projectId =?1  and ecf.incomeExpendFlag=?2");
		jpql.append(" and ecf.objType =?3 AND ecf.monthOfYear=?4");
		List<Object> params=Lists.newArrayList();
		params.add(info.getProjectId());
		params.add(info.getIncomeExpendFlag());
		params.add(info.getObjType());
		params.add(info.getMonthOfYear());
		int i=5;
		if (StringUtils.isNotEmpty(info.getObjCode())) {
			jpql.append(" and ecf.objCode =?").append(i++);
			params.add(info.getObjCode());
		} else if (info.getObjId() != null && info.getObjId() > 0) {
			jpql.append(" and ecf.objId =?").append(i++);
			params.add(info.getObjId());
		}
		if(info.getId()!=null&&info.getId()>0){
			jpql.append(" and ecf.id !=?").append(i++);
			params.add(info.getId());
		}
		return dynamicQuery.queryCount(jpql.toString(), params.toArray());
	}
	@Override
	public void deleteEcfiByObj(Long projectId,EcfiObjTypeEnum objType, Long objId, String objCode) {
		String sql="";
		switch (objType) {
		case CULTIVATE_AGRICULTURE:
			sql="delete from ExpectedCashFlowInfo ecfi where ecfi.projectId=?1 and ecfi.objType=?2 and ecfi.objId=?3";
			dynamicQuery.executeUpdate(sql, projectId,EcfiObjTypeEnum.CULTIVATE_AGRICULTURE.getCode(),objId);
			break;
		case BREED_AGRICULTURE:
			sql="delete from ExpectedCashFlowInfo ecfi where ecfi.projectId=?1 and ecfi.objType=?2 and ecfi.objId=?3";
			dynamicQuery.executeUpdate(sql, projectId,EcfiObjTypeEnum.BREED_AGRICULTURE.getCode(),objId);
			break;
		case INDUSTRY_BIZ:
			sql="delete from ExpectedCashFlowInfo ecfi where ecfi.projectId=?1 and ecfi.objType=?2 and ecfi.objId=?3";
			dynamicQuery.executeUpdate(sql, projectId,EcfiObjTypeEnum.INDUSTRY_BIZ.getCode(),objId);
			break;
		case FAMILY_CONSUME:
			sql="delete from ExpectedCashFlowInfo ecfi where ecfi.projectId=?1 and ecfi.objType=?2 and ecfi.objCode=?3";
			dynamicQuery.executeUpdate(sql, projectId,EcfiObjTypeEnum.FAMILY_CONSUME.getCode(),objCode);
		default:
			break;
		}
	}
}
