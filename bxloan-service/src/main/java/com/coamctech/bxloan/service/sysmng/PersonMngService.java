package com.coamctech.bxloan.service.sysmng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.PersonDetails;

public interface PersonMngService {
	
	/**
	 * 根据用户id找EcOrgPerson对象
	 * @param personId
	 * @return ecOrgPerson
	 * */
	public EcOrgPerson findPersonById(String personId);
	/**
	 * 根据用户id找PersonDetails对象
	 * @param personId
	 * @return personDetails
	 * */
	public PersonDetails findPersonDetailsById(String personId);
	/**
	 * 自定义标签获取所有组织机构
	 * */
	public List<EcOrgDepartment> getAllDepartment();
	
	/**
	 * 根据条件查询用户角色关联关系表
	 * @param personId 人员id
	 * @param orgId 机构id
	 * @param deptId 部门id
	 * @param roleId 角色id
	 * @return list
	 */
	public List findPersonConnRoleList(String curUserId, Long orgId, Long deptId, Long roleId);
	/**
	 * 根据证件类型和号码查找PersonDetails
	 * @param certificateNum 证件号码
	 * @param certificateTypeCd 证件类型
	 * @return list
	 * */
	public List findPersonDetailByCertificateNum(String certificateNum, String certificateTypeCd);
	/**
	 * 保存PersonDetail
	 * @param pd personDetails
	 * */
	public void savePersonDetail(PersonDetails pd);
	/**
	 * 保存EcOrgPerson
	 * @param eop EcOrgPerson
	 * */
	public void saveEcOrgPerson(EcOrgPerson eop);
	/**
	 * 根据用户id查询角色，返回page对象
	 * @param personId 用户id
	 * @param pageNumber 总页数
	 * @param pageSize 一页几条
	 * @param params 参数集
	 * */
	public Page findRolesBySearch(String personId, Integer pageNumber, Integer pageSize, List<Object> params);
}
