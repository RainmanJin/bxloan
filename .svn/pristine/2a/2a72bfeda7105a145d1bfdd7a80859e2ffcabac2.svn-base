package com.coamctech.bxloan.web.controller.customermng;

import static com.coamctech.bxloan.commons.GlobalConstants.CUS_MANAGER;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.service.customermng.CustomerManagerService;
import com.coamctech.bxloan.service.model.customermng.CusManagerTeamListVO;
import com.coamctech.bxloan.service.model.customermng.CusManagerTeamSaveVO;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/" + CUS_MANAGER)
public class CustomerManagerController extends BaseController {

	@Autowired
	private CustomerManagerService customerManagerService;

	@ResponseBody
	@RequestMapping("/list")
	public DataTablesPage list(@RequestParam("partyId") String partyId,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {

		Page<Object[]> pager = this.customerManagerService
				.findCostomerManagerList(Long.parseLong(partyId), firstIndex
						/ pageSize, pageSize);
		List<CusManagerTeamListVO> voList = Lists.transform(pager.getContent(),
				new Function<Object[], CusManagerTeamListVO>() {
					@Override
					public CusManagerTeamListVO apply(Object[] input) {
						return new CusManagerTeamListVO(input);
					}
				});
		DataTablesPage result = new DataTablesPage(sEcho, pager);
		result.setAaData(voList);
		return result;
	}

	@ResponseBody
	@RequestMapping("/remove")
	public Result remove(@RequestParam("cmtId") String cmtId) {
		String msg = this.checkPrivilege(cmtId);
		try {
			if("".equals(msg)) {
				msg = customerManagerService.removeCusManagerTeam(Long.parseLong(cmtId));
			}
		} catch (Exception e) {
			msg = "删除系统操作员时发生错误";
			logger.error(msg, e);
		}
		if(!"".equals(msg)) {
			return failure(msg);
		} else {
			return success();
		}
	}

	@ResponseBody
	@RequestMapping("/save")
	public Result add(CusManagerTeamSaveVO vo) {
		String msg = "";
		try {
			msg = customerManagerService.saveCusManagerTeam(vo);
		} catch (Exception e) {
			msg = "保存系统操作员时发生错误";
			logger.error(msg, e);
		}
		if(!"".equals(msg)) {
			return failure(msg);
		} else {
			return success();
		}
	}
	
	/**
	 * 检查是否有删除权限
	 * 
	 * @param cmtId
	 * @return
	 * @author xc
	 */
	private String checkPrivilege(String cmtId) {
		String userId = this.curUserId();
		return this.customerManagerService.checkRemovePrivilege(cmtId, userId);
	}

	/**
	 * 
	 * @param eop
	 * @param search
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findUserBySearch")
	@ResponseBody
	public DataTablesPage findUserBySearch(EcOrgPerson ecOrgPerson,
			@RequestParam(value = "sSearch", required = false) String search,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//Page page = customerManagerService.findUserBySearch(ecOrgPerson,shiroUser.getOrgid(), firstIndex / pageSize + 1, pageSize);
		Page page = customerManagerService.findUserBySearch(ecOrgPerson,shiroUser.getLogOrgid(), firstIndex / pageSize + 1, pageSize);
		return new DataTablesPage(sEcho, page);
	}
	
	@ResponseBody
	@RequestMapping("getCustomerManagerTeamById")
	public Result getCustomerManagerTeamById(@RequestParam("cmtId") Long cmtId) {
		return success(customerManagerService.getCustomerManagerTeamById(cmtId));
	}
	
	@ResponseBody
	@RequestMapping("isManageCustomerPrivilege")
	public Result isManageCustomerPrivilege(CustomerManagerTeam cmt) {
		String personId = this.curUserId();
		boolean isManageCustomerPrivilege = customerManagerService.isManageCustomerPrivilege(cmt, personId);
		return success(!isManageCustomerPrivilege);
	}
	
	@ResponseBody
	@RequestMapping("handOverMngPrivilege")
	public Result handOverMngPrivilege(@RequestParam("cmtIds") String cmtIds) {

		String msg = customerManagerService.handOverValid(cmtIds);
		if("".equals(msg)) {
			return success();
		} else {
			return failure(msg);
		}
	}
	
	@ResponseBody
	@RequestMapping("sureHandOver")
	public Result sureHandOver(CusManagerTeamSaveVO cusManagerTeamSaveVO) {
		
		String msg = "";
		if(cusManagerTeamSaveVO == null || cusManagerTeamSaveVO.getUserNum() == null) {
			msg = "移交到用户不存在!";
		}
		if(cusManagerTeamSaveVO == null || cusManagerTeamSaveVO.getCmTeamIds() == null) {
			msg = "要移交用户不存在!";
		}
		try {
			customerManagerService.setCustomerManagerTeam(cusManagerTeamSaveVO.getCmTeamIds(), cusManagerTeamSaveVO, curUserId());
		} catch (Exception e) {
			msg = "客户移交时发生错误";
			logger.error(msg, e);
		}
		
		if("".equals(msg)) {
			return success();
		} else {
			return failure(msg);
		}
	}
	
	@ResponseBody
	@RequestMapping("roleTransfer")
	public Result roleTransfer(@RequestParam("cmtIds") String cmtIds) {
		String msg = customerManagerService.transferManagerRole(cmtIds);
		if("".equals(msg)) {
			return success();
		} else {
			return failure(msg);
		}
	}

	private String curUserId() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getId().toString();
		}
		return curUser;
	}

}
