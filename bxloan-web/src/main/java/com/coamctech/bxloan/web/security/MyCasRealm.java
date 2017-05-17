package com.coamctech.bxloan.web.security;



import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.coamctech.bxloan.web.security.cas.MyCasToken;



public class MyCasRealm extends CasRealm {

	private static final Logger logger = LoggerFactory.getLogger(MyCasRealm.class);

	private EcOrgPersonService ecOrgPersonService;

	
	public EcOrgPersonService getEcOrgPersonService() {
		return ecOrgPersonService;
	}

	public void setEcOrgPersonService(EcOrgPersonService ecOrgPersonService) {
		this.ecOrgPersonService = ecOrgPersonService;
	}

	/**
	 * 认证
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) super.doGetAuthenticationInfo(token);
		PrincipalCollection principalCollection = info.getPrincipals();
		List<Object> listPrincipals = principalCollection.asList();
		
		String loginName = (String) listPrincipals.get(0);
		logger.info("cas-username:" + loginName);
		 ShiroUser shiroUser = new ShiroUser();
		Map<String, String> attributes = (Map<String, String>) listPrincipals.get(1);
        switch (GlobalConstants.DEFAULT_LOGIN_MODE) {
		case PORTAL_SSO:
			MyCasToken myCasToken = (MyCasToken) token;
			String logname = myCasToken.getLogName();
			String logorgid = myCasToken.getLogOrgId();
			
			EcOrgPerson ep = ecOrgPersonService.findByEmpnumAndLogname(loginName, logname);//参数：直接通过token中的账号
			if (ep != null) {
				BeanUtils.copyProperties(ep, shiroUser);
				EcOrgDepartment ed = ecOrgPersonService.findEcOrgDepartmentById(Long.parseLong(logorgid));//参数：直接通过token中的机构id
				if (ed != null) {
					shiroUser.setLogOrgid(ed.getId());
					shiroUser.setLogOrgname(ed.getName());
					shiroUser.setLogOrgDesc(ed.getDescription());
					//权限保存
					List<EcFunctiontreenode> funs = ecOrgPersonService.findMenusByPermission(ep.getId(), ed.getId());
					if(funs != null) {
						shiroUser.setList(funs);
					}
				}
				//modify by HWL at 20150527
				shiroUser.setLoginName(ep.getEmpnum());//设置员工编号
				//modify by HWL at 20150527 end
				List<EcOrgPersonconnrole> roles = this.ecOrgPersonService.findRolesByPersonId(shiroUser.getId());
				shiroUser.setRoles(roles);
				//TODO 加载数据权限
				this.initDataAuth(shiroUser);
			}
			break;
		case BIZ_SSO:
			this.loginOfBiz(shiroUser, loginName);
			break;
		default:
			throw new RuntimeException("Unknown login mode !!!!!");
		}
        List<Object> shiroPrincipals = CollectionUtils.asList(shiroUser, attributes);
        principalCollection = new SimplePrincipalCollection(shiroPrincipals, getName());
        info.setPrincipals(principalCollection);
        
        return info;
	}
	
	/**
	 * 业务系统登录
	 * @param shiroUser
	 * @param loginName
	 */
	private void loginOfBiz(ShiroUser shiroUser,String loginName){
		List<EcOrgPerson> list = ecOrgPersonService.findByEmpnum(loginName);
		//首先判断是否有多个账号
		if(null != list && list.size() !=0) {
			if (list.size()==1) {
				EcOrgPerson ep = list.get(0);
				List<EcOrgDepartment> orgs = ecOrgPersonService.findPermOrgsByUserId(ep.getId());
				//判断有权限的机构
				if(orgs.size()==1){//单一机构，直接登录
					EcOrgDepartment ed = orgs.get(0);
					shiroUser.setLogOrgid(ed.getId());
					shiroUser.setLogOrgname(ed.getName());
					shiroUser.setLogOrgDesc(ed.getDescription());
					List<EcFunctiontreenode> funs = ecOrgPersonService.findMenusByPermission(ep.getId(), ed.getId());
					if(funs != null) {
						shiroUser.setList(funs);
					}
					BeanUtils.copyProperties(ep, shiroUser);
					//modify by HWL at 20150527
					shiroUser.setLoginName(ep.getEmpnum());//设置员工编号
					//modify by HWL at 20150527 end
					List<EcOrgPersonconnrole> roles = this.ecOrgPersonService.findRolesByPersonId(shiroUser.getId());
					shiroUser.setRoles(roles);
					//TODO 加载数据权限
					this.initDataAuth(shiroUser);
				}else {
					list.get(0).setOrgs(orgs);
					shiroUser.setPersons(list);
				}
			} else {
				for (EcOrgPerson ecOrgPerson:list){
					List<EcOrgDepartment> orgs = ecOrgPersonService.findPermOrgsByUserId(ecOrgPerson.getId());
					//获取权限菜单
					ecOrgPerson.setOrgs(orgs);
				}
				shiroUser.setPersons(list);
			}
		}	
	}
	
	/**
	 * 初始化数据权限
	 * @param shiroUser
	 * @author hwl
	 * @since 2015-05-25
	 */
	private void initDataAuth(ShiroUser shiroUser){
		if(shiroUser!=null&&shiroUser.getLogOrgid()!=null&&shiroUser.getId()!=null){
			DataAuthorityType dataAuth=this.ecOrgPersonService.getCustomerType(shiroUser.getLogOrgid(), shiroUser.getId());
			shiroUser.setDataAuthType(dataAuth);
			shiroUser.setDataAuthOrgIds(this.ecOrgPersonService.findDataAuthOrgIds( shiroUser.getId(),shiroUser.getLogOrgid(), dataAuth));
		}
	}
	/**
	 * 鉴权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return super.doGetAuthorizationInfo(principals);
	}

}
