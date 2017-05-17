package com.coamctech.bxloan.service.usermng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.User;

public interface UserMngService {
	
	void save(User user);

	User get(String id);
	
	User findByNameInNativeSql(String name);
	
	void update(String name, String id);
	
	Long count();
	
	Boolean exists(String id);
	
	void delete(String id);
	
	Page<User> pagingQueryByName(String name, Integer pageNumber, Integer pageSize);
	
	List<User> query(String name);
	
	@SuppressWarnings("rawtypes")
	Page query(String name,Integer pageNumber, Integer pageSize);
	
	List<User> queryPagingList(String name, Integer pageNumber, Integer pageSize);

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	Page<User> findBySearch(String search, Integer pageNumber, Integer pageSize);

	List<Object[]> nativeQuery(String name);
	
	List<Object[]> nativeQueryPagingList(String name, Integer pageNumber, Integer pageSize);

	@SuppressWarnings("rawtypes")
	Page nativeQuery(String role, Integer pageNumber, Integer pageSize);

	List<IndustryType> getAllIndustry();
}
