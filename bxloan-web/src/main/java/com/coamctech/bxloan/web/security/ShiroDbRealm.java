package com.coamctech.bxloan.web.security;


import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;

import com.coamctech.bxloan.commons.security.OrgUsernamePasswordToken;
import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;

public class ShiroDbRealm extends AuthorizingRealm {
	

	private EcOrgPersonService ecOrgPersonService;
	

	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		OrgUsernamePasswordToken token = (OrgUsernamePasswordToken) authToken;
		String username = token.getUsername();
		Long orgid = Long.parseLong(token.getOrgid());
		EcOrgPerson ecOrgPerson = ecOrgPersonService.findByLogname(username);
		EcOrgDepartment ecOrgDepartment = ecOrgPersonService.findEcOrgDepartmentById(orgid);
		//获取权限菜单
		List<EcFunctiontreenode> list = ecOrgPersonService.findMenusByPermission(ecOrgPerson.getId(), orgid);
		ShiroUser shiroUser = new ShiroUser();
		if(list != null){
			shiroUser.setList(list);
		}
		shiroUser.setLogOrgid(orgid);
		shiroUser.setLogOrgname(ecOrgDepartment.getName());
		BeanUtils.copyProperties(ecOrgPerson, shiroUser);
		
		List<EcOrgPersonconnrole> roles = this.ecOrgPersonService.findRolesByPersonId(shiroUser.getId());
		shiroUser.setRoles(roles);
		return new SimpleAuthenticationInfo(shiroUser, ecOrgPerson.getLogpass(), ByteSource.Util.bytes(ecOrgPerson.getCredentialsSalt()), getName());
	}

	/**
	 * 鉴权--系统暂时没有用到
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("admin");
		return info;
	}


	public EcOrgPersonService getEcOrgPersonService() {
		return ecOrgPersonService;
	}


	public void setEcOrgPersonService(EcOrgPersonService ecOrgPersonService) {
		this.ecOrgPersonService = ecOrgPersonService;
	}
	
	
}
