package com.coamctech.bxloan.service.userinfo;

import com.coamctech.bxloan.service.model.userinfo.UserInfoDetailVO;
import com.coamctech.bxloan.service.model.userinfo.UserInfoUpdateVO;

public interface UserInfoService {

	
	UserInfoDetailVO findUserInfoById(Long id);

	/**
	 * 更新用户信息
	 * @param vo
	 * @return 错误消息
	 * @author xc
	 */
	String updateUserInfo(UserInfoUpdateVO vo);
}
