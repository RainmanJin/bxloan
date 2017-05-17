package com.coamctech.bxloan.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

	User findById(String id);

	@Modifying
	@Query("update User u set u.name = ?1 where u.id = ?2")
	void updateUserName(String name, String id);

	@Query(value = "select * from t_user where name = ?1", nativeQuery = true)
	User findByName(String name);

	@Query("select u from User u where u.name = ?1 order by u.id")
	Page<User> pagingQueryByName(String name, Pageable pageable);

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

}