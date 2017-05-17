package com.coamctech.bxloan.service.sysmanager;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgRole;



public interface EcOrgRoleService {
	Page<EcOrgRole> findByCondition(Integer pageNumber, Integer pageSize);
	Page<EcOrgRole> findBySearch(String serach, Integer pageNumber, Integer pageSize);
	EcOrgRole getRole(String roleNum);
	List<EcFunctiontreenode> getAllFunctionnode();
	void saveFunctionnode(String rolenum,String[] id);
	void deleteFunctionnode(String roleNum);
}
