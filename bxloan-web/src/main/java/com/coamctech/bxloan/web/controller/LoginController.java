package com.coamctech.bxloan.web.controller;

import static com.coamctech.bxloan.commons.GlobalConstants.DASHBOARD;
import static com.coamctech.bxloan.commons.GlobalConstants.LOGIN;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.LoginMode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.coamctech.bxloan.web.security.ShiroUser;

@Controller
@RequestMapping("/" + LOGIN)
public class LoginController extends BaseController {
	@Autowired
	private EcOrgPersonService ecOrgPersonService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(HttpServletRequest req) {
		if(LoginMode.PORTAL_SSO.equals(GlobalConstants.DEFAULT_LOGIN_MODE)){//门户已完成登录
			return StringUtils.join("redirect:/",DASHBOARD);
		}
		return LOGIN;
	}
	
	@RequestMapping(value = "/getPersons", method = RequestMethod.POST)
	@ResponseBody
	public Result getAccAndOrgs(String logname,Integer orgid) {
		ShiroUser curUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		return success(curUser.getPersons());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletRequest req) {
		ShiroUser curUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		String resultUrl="login";
		String logname=req.getParameter("logname");
		String orgid=req.getParameter("orgid");
		if(StringUtils.isNotBlank(logname)||StringUtils.isNotBlank(orgid)){
			this.loginModeOfBiz(curUser, this.str2Long(req.getParameter("logname")), this.str2Long(req.getParameter("orgid")));
			resultUrl= StringUtils.join("redirect:/",DASHBOARD);
		}
		return resultUrl;
	}
	
	/**
	 * 业务系统多账号，选择账户后登录
	 * @param curUser
	 * @param logname
	 */
	private void loginModeOfBiz(ShiroUser curUser,Long userId,Long orgid ){
		EcOrgPerson ecOrgPerson = ecOrgPersonService.findById(userId);
		BeanUtils.copyProperties(ecOrgPerson, curUser);
		EcOrgDepartment ecOrgDepartment = ecOrgPersonService.findEcOrgDepartmentById(orgid);
		curUser.setLogOrgid(orgid);
		curUser.setLogOrgname(ecOrgDepartment.getName());
		curUser.setLogOrgDesc(ecOrgDepartment.getDescription());
		List<EcFunctiontreenode> funs = ecOrgPersonService.findMenusByPermission(ecOrgPerson.getId(), orgid);
		if(funs != null) {
			curUser.setList(funs);
		}
		//modify by HWL at 20150527
		curUser.setLoginName(ecOrgPerson.getEmpnum());//设置员工编号
		//modify by HWL at 20150527 end
		List<EcOrgPersonconnrole> roles = this.ecOrgPersonService.findRolesByPersonId(curUser.getId());
		curUser.setRoles(roles);
		//初始化数据权限
		this.initDataAuth(curUser);
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
	
	private Long str2Long(String str){
		if(StringUtils.isBlank(str)){
			return null;
		}
		return Long.parseLong(str);
	}

}
