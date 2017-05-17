package com.coamctech.bxloan.web.controller.userinfo;

import static com.coamctech.bxloan.commons.GlobalConstants.USER_INFO;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.service.model.userinfo.UserInfoDetailVO;
import com.coamctech.bxloan.service.model.userinfo.UserInfoUpdateVO;
import com.coamctech.bxloan.service.userinfo.UserInfoService;
import com.coamctech.bxloan.web.security.ShiroUser;

/**
 * 用户信息修改
 * @author xc
 */
@Controller
@RequestMapping("/" + USER_INFO)
public class UserInfoController extends BaseController{
	
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping
	public String index(){
		return USER_INFO + "/main";
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public Result detail(){
		UserInfoDetailVO userInfoVO = this.userInfoService
				.findUserInfoById(this.curUserId());
		return success(userInfoVO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Result update(UserInfoUpdateVO vo){
		
		try {
			vo.setPersonId(this.curUserId());
			String errMsg = this.userInfoService.updateUserInfo(vo);
			if(StringUtils.isNotBlank(errMsg)){
				return failure(errMsg);
			}else{
				return success();
			}
		} catch (Exception e) {
			String errMsg = "更新用户基本信息时发生异常";
			logger.error(errMsg,e);
			return failure(errMsg);
		}
		
		
	}
	
	////////////////////
	////PRIVATE/////
	//////////////////
	
	private Long curUserId(){
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		Long curUser = null;
		if (shiroUser != null) {
			curUser = shiroUser.getId();
		}
		return curUser;
	}
}
