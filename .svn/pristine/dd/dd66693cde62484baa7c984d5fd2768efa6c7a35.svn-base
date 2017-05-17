package com.coamctech.bxloan.service.userinfo.impl;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.EcOrgPersonDao;
import com.coamctech.bxloan.dao.PersonDetailsDao;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.PersonDetails;
import com.coamctech.bxloan.service.model.userinfo.UserInfoDetailVO;
import com.coamctech.bxloan.service.model.userinfo.UserInfoUpdateVO;
import com.coamctech.bxloan.service.userinfo.UserInfoService;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private EcOrgPersonDao personDao;
	
	@Autowired
	private PersonDetailsDao personDetailsDao;

	@Autowired
	private DynamicQuery query;
	
	@Override
	public UserInfoDetailVO findUserInfoById(Long id) {
		StringBuilder jql = new StringBuilder();
		jql.append("SELECT  ");
		jql.append("  p.logname , ");
		jql.append("  pd.fixedphone, ");
		jql.append("  pd.mobilephone, ");
		jql.append("  pd.email, ");
		jql.append("  pd.remarks ");
		jql.append("FROM EcOrgPerson p,PersonDetails pd ");
		jql.append("  WHERE p.id = ?1 AND pd.id=?1");
		Object[] resultSet = this.query.querySingleResult(Object[].class, jql.toString(), id);
		
		return new UserInfoDetailVO(resultSet);
	}

	@Override
	public String updateUserInfo(UserInfoUpdateVO vo) {
		String errMsg = null;
		errMsg = updatePassWord(vo);
		if(StringUtils.isNotBlank(errMsg)){return errMsg;}
		updateDetails(vo);
		return errMsg;
	}
	
	private String updatePassWord(UserInfoUpdateVO vo){
		if(isNotBlank(vo.getLogpass())){
			if(isNotBlank(vo.getNewPass())&&
				isNotBlank(vo.getRepeatNewPass())
				&& vo.getNewPass().equals(vo.getRepeatNewPass())){
				
				EcOrgPerson person = this.personDao.findOne(vo.getPersonId());
				if(vo.getLogpass().equals(person.getLogpass())){
					person.setLogpass(vo.getNewPass());
					personDao.save(person);
				}else{
					return "密码输入错误";
				}
			}else{
				return "两次密码输入不一致";
			}
		}
		return null;
	}

	private void updateDetails(UserInfoUpdateVO vo) {
		PersonDetails detail = this.personDetailsDao.findOne(vo.getPersonId());
		detail.setLastUpdateDate(new Date());
		detail.setLastUpdateUser(vo.getPersonId().toString());
		BeanUtils.copyProperties(vo, detail);
		this.personDetailsDao.save(detail);
	}
	
	
}
