package com.coamctech.bxloan.service.sysmanager;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.entity.EcOrgRole;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.exceptions.NoAuthorityException;

public interface EcOrgPersonService {
	Page<EcOrgPerson> findByCondition(EcOrgPerson eop, Integer pageNumber,
			Integer pageSize);

	Page<EcOrgPerson> findBySearch(String serach, Integer pageNumber,
			Integer pageSize);

	Page<EcOrgRole> findBySearchRole(String serach, String usernum,
			Integer pageNumber, Integer pageSize);

	EcOrgPerson findPersonByUsernum(String usernum);

	Page<EcOrgRole> findByCondition(String usernum, Integer pageNumber,
			Integer pageSize);

	void deleteRoleByRolenum(String rolenum, String usernum);

	// Integer deleteRoleByRolenum(String rolenum,String usernum);
	void saveEcOrgPersonconnrole(String rolenum, String usernum);

	Page<EcOrgRole> findRole(String usernum, Integer pageNumber,
			Integer pageSize);

	/**
	 * 新增修改用户信息
	 * 
	 * @param person
	 *            用户实体
	 * @return
	 * */
	void save(EcOrgPerson person);

	/**
	 * 根据登陆名称查询用户信息
	 * 
	 * @param logname
	 *            登陆名
	 * @return 用户实体
	 * 
	 * */
	EcOrgPerson findByLogname(String logname);

	/**
	 * 登陆失败后 判断登陆次数 超过指定次数即锁定.
	 * 
	 * @param ecOrgPerson
	 *            登陆时获取的实体
	 * @return
	 * */
	void updatePersonState(EcOrgPerson ecOrgPerson);

	/**
	 * 登陆成功后 清零错误登陆次数 保存登陆日志 登陆详细信息从Shiro的session中取得
	 * 
	 * @param ecOrgPerson
	 *            用户清零错误登陆次数
	 * @return
	 * */
	void saveLogonInfo(EcOrgPerson ecOrgPerson);
	/**
	 * 查询用户有权限的机构
	 * @param id 用户ID
	 * */
	List<EcOrgDepartment> findPermOrgsByUserId(Long id);

	List<EcFunctiontreenode> findMenusByPermission(Long personid,
			Long orgid);

	EcOrgDepartment findEcOrgDepartmentById(Long orgid);
	
	List<EcOrgPersonconnrole> findRolesByPersonId(Long personId);

	EcOrgPerson findById(Long id);

	List<EcOrgPerson> findByEmpnum(String empnum);
	
	EcOrgPerson findByEmpnumAndLogname(String empnum,String logname);

	/**
	 * 通过机构列表查找对应的机构信息</br> 
	 * 包含三个字段：<b>id</b>,<b>name</b>,<b>description</b>
	 * @author:gph
	 * @createTime:2015年5月26日
	 * @return
	 */
	List<EcOrgDepartment> findEcOrgsInfoByIds(List<Long> orgIds);
	/**
	 * 得到客户类型
	 * @author:gph
	 * @createTime:2015年5月22日
	 * @param orgId 机构Id
	 * @param personId 用户Id
	 * @return DataAuthorityType枚举类型:</br> 
	 * HeadOffice:总部，LoanCompany：小贷公司，CustomerManager:客户经理
	 */
	DataAuthorityType getCustomerType(Long orgId,Long personId) throws NoAuthorityException;
	/**
	 * 是否是客户经理
	 * @author:gph
	 * @createTime:2015年5月22日
	 * @param personId
	 * @return true:是，false:不是
	 */
	boolean isNotCustomerManager(Long personId);
	/**
	 * 查询用户所拥有的机构
	 * @author:gph
	 * @createTime:2015年5月22日
	 * @param personId 用户Id
	 * @return 所拥有的机构Id集合
	 */
	List<Long> findOrgsByPersonId(Long personId);
	
	
	/**
	 * 查询用户所拥有的数据权限机构
	 * @param personId	登录人id
	 * @param logOrgId	登录机构id
	 * @param dataAuthType	数据权限类型
	 * @return
	 * @author HWL
	 * @since 2015-05-25
	 */
	List<Long> findDataAuthOrgIds(Long personId,Long logOrgId,DataAuthorityType dataAuthType);
}
