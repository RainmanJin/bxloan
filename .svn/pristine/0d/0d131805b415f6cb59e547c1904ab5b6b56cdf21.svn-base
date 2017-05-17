package com.coamctech.bxloan.web.controller.countregistration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.entity.EmployeeAttendance;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.countregistration.CountRegistrationService;
import com.coamctech.bxloan.web.security.ShiroUser;

/**
 * 统计签到
 * 
 * @author unknown7
 * 
 */
@Controller
@RequestMapping(GlobalConstants.COUNT_REGISTRATION)
public class CountRegistrationController extends BaseController {
	@Autowired
	private CountRegistrationService countRegistrationService;
	@Autowired
	private BusinessApplicationService businessApplicationService;

	@RequestMapping
	public String index() {
		return GlobalConstants.COUNT_REGISTRATION + "/main";
	}
	
	@RequestMapping("/toMap")
	public String toMap(){
		return GlobalConstants.COUNT_REGISTRATION + "/map";
	}

	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();

		Long orgId = curUser.getOrgid();//TODO
	


		String adminRoles = businessApplicationService.findAdminRoles();
		for (String adminRole : adminRoles.split(",")) {
			for (EcOrgPersonconnrole role : curUser.getRoles()) {
				if (role.getEcOrgRole().getRoleNum().equals(adminRole)) {
					orgId = null;
					break;
				}
			}
		}

		Page<Object[]> page = countRegistrationService.findBySearch(firstIndex
				/ pageSize + 1, pageSize, orgId);

		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	@RequestMapping("/findOne")
	@ResponseBody
	public EmployeeAttendance findOne(Long id) {
		return countRegistrationService.findOne(id);
	}
}
