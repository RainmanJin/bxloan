package com.coamctech.bxloan.service.usermng.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.IndustryTypeDao;
import com.coamctech.bxloan.dao.UserDao;
import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.User;
import com.coamctech.bxloan.service.usermng.UserMngService;

@Transactional
@Service("userMngService")
public class UserMngServiceImpl implements UserMngService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IndustryTypeDao industryTypeDao;
	
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Override
	public void save(User user) {
		user.setPassword("5f4dcc3b5aa765d61d8327deb882cf99"); // md5加密后的password
		user.setRole("guest");
		userDao.save(user);
	}

	@Override
	public User get(String id) {
		return userDao.findById(id);
	}
	
	@Override
	public User findByNameInNativeSql(String name) {
		return userDao.findByName(name);
	}

	@Override
	public void update(String name, String id) {
		userDao.updateUserName(name, id);
	}

	@Override
	public Long count() {
		return userDao.count();
	}

	@Override
	public Boolean exists(String id) {
		return userDao.exists(id);
	}
	
	@Override
	public void delete(String id) {
		userDao.delete(id);
	}

	@Override
	public Page<User> pagingQueryByName(String name, Integer pageNumber, Integer pageSize) {
		return userDao.pagingQueryByName(name, new PageRequest(pageNumber - 1, pageSize));
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<User> findBySearch(String search, Integer pageNumber,
			Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("select u from User u where 1=1 ");
		if (StringUtils.isNotEmpty(search)) {
			jpql.append("and u.username like ?1 or u.name like ?1 ");
			params.add("%" + search + "%");
		}
		jpql.append("order by u.username");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize), jpql.toString(), params.toArray());
	}

	/********************* 以下为扩展的动态jpql查询 ********************/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> query(String name) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from User u where u.name = ?1 order by u.password";
		params.add(name);
		return (List<User>) dynamicQuery.query(hql, params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryPagingList(String name, Integer pageNumber, Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from User u where u.name = ?1 order by u.password";
		params.add(name);
		return (List<User>) dynamicQuery.queryPagingList(new PageRequest(pageNumber - 1, pageSize), hql, params.toArray());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Page query(String name, Integer pageNumber, Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from User u where u.name = ?1 order by u.password";
		params.add(name);
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize), hql, params.toArray());
	}

	/********************* 以下为扩展的动态nativeSql查询 ********************/

	@Override
	public List<Object[]> nativeQuery(String name) {
		String sql = "select t.id, t.name from tt_user t where t.username = ?1 ";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		//return dynamicQuery.nativeQuery(sql, params);
		return dynamicQuery.nativeQuery(sql, params.toArray());
	}

	@Override
	public List<Object[]> nativeQueryPagingList(String name, Integer pageNumber, Integer pageSize) {
		String sql = "select t.id, t.name from tt_user t where t.username = ?1 ";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		return dynamicQuery.nativeQueryPagingList(new PageRequest(pageNumber - 1, pageSize), sql, params.toArray());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Page nativeQuery(String role, Integer pageNumber, Integer pageSize) {
		String sql = "select t.id, t.name from tt_user t where t.role = ?1 ";
		List<Object> params = new ArrayList<Object>();
		params.add(role);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql, params.toArray());
	}

	@Override
	public List<IndustryType> getAllIndustry() {
		return (List<IndustryType>) industryTypeDao.findAllItems();
	}
	
}
