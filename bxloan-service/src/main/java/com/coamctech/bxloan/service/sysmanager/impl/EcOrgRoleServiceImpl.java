package com.coamctech.bxloan.service.sysmanager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.EcFunctiontreenodeDao;
import com.coamctech.bxloan.dao.EcOrgRoleDao;
import com.coamctech.bxloan.dao.EcOrgrolemenuDao;
import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgRole;
import com.coamctech.bxloan.entity.EcOrgrolemenu;
import com.coamctech.bxloan.entity.EcOrgrolemenuId;
import com.coamctech.bxloan.service.sysmanager.EcOrgRoleService;
@Transactional
@Service("roleService")
public class EcOrgRoleServiceImpl implements EcOrgRoleService{
	@Autowired
	DynamicQuery dynamicQuery;
	@Autowired
	EcOrgRoleDao ecOrgRoleDao;
	@Autowired
	EcFunctiontreenodeDao ecfunctiontreenodeDao;
	@Autowired
	EcOrgrolemenuDao ecOrgrolemenuDao;
	public Page<EcOrgRole> findByCondition(Integer pageNumber, Integer pageSize) {
		
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("select eor from EcOrgRole eor where 1=1 ");
		jpql.append("order by eor.endTime");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),jpql.toString(), params.toArray());
	}

	public Page<EcOrgRole> findBySearch(String serach, Integer pageNumber,
			Integer pageSize) {

		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("select eor from EcOrgRole eor where 1=1 ");
		if (StringUtils.isNotEmpty(serach)) {
			jpql.append("and eor.name like ?1 or eor.roleNum like ?1 ");
			params.add("%" + serach + "%");
		}
		jpql.append("order by eor.endTime");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),jpql.toString(), params.toArray());
	}

	public EcOrgRole getRole(String roleNum) {
		EcOrgRole role = ecOrgRoleDao.findByRoleNum(roleNum);
		List<EcFunctiontreenode> efts=ecfunctiontreenodeDao.findFunctionByRoleId(role.getId());
		List<String> functionIds=new ArrayList<String>();
		if(efts!=null && efts.size()>0){
			for(EcFunctiontreenode eft:efts){
				functionIds.add(eft.getId().toString());
			}
		}
		role.setFunctionId(functionIds);
		return role;
	}

	public List<EcFunctiontreenode> getAllFunctionnode() {
		return ecfunctiontreenodeDao.findAllItems();
	}

	public void saveFunctionnode(String rolenum, String[] id) {
		EcOrgRole eor=ecOrgRoleDao.findByRoleNum(rolenum);
		//先清楚当前角色的权限再保存新的权限
		ecOrgrolemenuDao.deleteRoleMenuByRoleId(eor.getId());
		if(id.length>0){
			for(String s:id){
				EcOrgrolemenu eorm=new EcOrgrolemenu();
				EcOrgrolemenuId eormId=new EcOrgrolemenuId();
				eormId.setRoleId(eor.getId());
				eormId.setMenuId(new Long(s));
				eorm.setId(eormId);
				ecOrgrolemenuDao.save(eorm);
			}
		}
		
	}

	public void deleteFunctionnode(String roleNum) {
		EcOrgRole eor=ecOrgRoleDao.findByRoleNum(roleNum);
		//先清楚当前角色的权限再保存新的权限
		ecOrgrolemenuDao.deleteRoleMenuByRoleId(eor.getId());
		
	}

}
