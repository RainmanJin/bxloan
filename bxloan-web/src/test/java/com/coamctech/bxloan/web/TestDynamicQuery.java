package com.coamctech.bxloan.web;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.entity.User;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class TestDynamicQuery extends BaseSpringJUnit4Tester {

	@Autowired
	private DynamicQuery dq;

	// -============================== jpql ======================================-
	@Test
	public void testQuery1() { // 查询实体
		String jpql = "from User where username = ?1";
		List<User> users = dq.query(User.class, jpql, "admin");
		System.out.println("-======================================-");
		for (User u : users) {
			System.out.println(ToStringBuilder.reflectionToString(u));
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testQuery2() { // 查询1列
		String jpql = "select id from User where username = ?1";
		List<String> ids = dq.query(String.class, jpql, "admin");
		System.out.println("-======================================-");
		for (String id : ids) {
			System.out.println(id);
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testQuery3() { // 查询多列
		String jpql = "select id, name from User where username = ?1";
		List<Object[]> idAndNames = dq.query(Object[].class, jpql, "admin");
		System.out.println("-======================================-");
		for (Object[] idAndName : idAndNames) {
			System.out.println(idAndName[0] + " - " + idAndName[1]);
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testQuerySingleResult1() { // 查询实体
		String jpql = "from User where username = ?1";
		User user = dq.querySingleResult(User.class, jpql, "admin");
		System.out.println("-======================================-");
		System.out.println(ToStringBuilder.reflectionToString(user));
		System.out.println("-======================================-");
	}

	@Test
	public void testQuerySingleResult2() { // 查询1列
		String jpql = "select id from User where username = ?1";
		String id = dq.querySingleResult(String.class, jpql, "admin");
		System.out.println("-======================================-");
		System.out.println(id);
		System.out.println("-======================================-");
	}

	@Test
	public void testQuerySingleResult3() { // 查询多列
		String jpql = "select id, name from User where username = ?1";
		Object[] idAndName = dq.querySingleResult(Object[].class, jpql, "admin");
		System.out.println("-======================================-");
		System.out.println(idAndName[0] + " - " + idAndName[1]);
		System.out.println("-======================================-");
	}

	@Test
	public void testQuerySingleResult4() { // 统计查询
		String jpql = "select count(*) from User where username = ?1";
		Number count = dq.querySingleResult(Number.class, jpql, "admin");
		System.out.println("-======================================-");
		System.out.println(count.longValue());
		System.out.println("-======================================-");
	}

	@Test
	public void testQueryPagingList() { // 分页List
		String jpql = "from User where username = ?1";
		List<User> users = dq.queryPagingList(User.class, new PageRequest(0, 5), jpql, "admin");
		System.out.println("-======================================-");
		for (User u : users) {
			System.out.println(ToStringBuilder.reflectionToString(u));
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testQueryPage() { // 分页Page
		String jpql = "from User where username = ?1";
		Page<User> user = dq.query(User.class, new PageRequest(0, 5), jpql, "admin");
		System.out.println("-======================================-");
		System.out.println("user.getNumber(): " + user.getNumber());
		System.out.println("user.getNumberOfElements(): " + user.getNumberOfElements());
		System.out.println("user.getSize(): " + user.getSize());
		System.out.println("user.getTotalElements(): " + user.getTotalElements());
		System.out.println("user.getTotalPages(): " + user.getTotalPages());
		List<User> users = user.getContent();
		for (User u : users) {
			System.out.println(ToStringBuilder.reflectionToString(u));
		}
		System.out.println("-======================================-");
	}

	// -============================== native ======================================-
	@Test
	public void testNativeQuery1() { // 查询全表
		String sql = "select * from tt_user where username = ?1";
		List<User> users = dq.nativeQuery(User.class, sql, "admin");
		System.out.println("-======================================-");
		for (User u : users) {
			System.out.println(ToStringBuilder.reflectionToString(u));
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testNativeQuery2() { // 查询1列
		String sql = "select id from tt_user where username = ?1";
		List<String> ids = dq.nativeQuery(String.class, sql, "admin");
		System.out.println("-======================================-");
		for (String id : ids) {
			System.out.println(id);
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testNativeQuery3() { // 查询多列
		String sql = "select id, name from tt_user where username = ?1";
		List<Object[]> idAndNames = dq.nativeQuery(Object[].class, sql, "admin");
		System.out.println("-======================================-");
		for (Object[] idAndName : idAndNames) {
			System.out.println(idAndName[0] + " - " + idAndName[1]);
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testSingleResult1() { // 查询全表
		String sql = "select * from tt_user where username = ?1";
		User user = dq.nativeQuerySingleResult(User.class, sql, "admin");
		System.out.println("-======================================-");
		System.out.println(ToStringBuilder.reflectionToString(user));
		System.out.println("-======================================-");
	}

	@Test
	public void testSingleResult2() { // 查询1列
		String sql = "select id from tt_user where username = ?1";
		String id = dq.nativeQuerySingleResult(String.class, sql, "admin");
		System.out.println("-======================================-");
		System.out.println(id);
		System.out.println("-======================================-");
	}

	@Test
	public void testSingleResult3() { // 查询多列
		String sql = "select id, name from tt_user where username = ?1";
		Object[] idAndName = dq.nativeQuerySingleResult(Object[].class, sql, "admin");
		System.out.println("-======================================-");
		System.out.println(idAndName[0] + " - " + idAndName[1]);
		System.out.println("-======================================-");
	}

	@Test
	public void testSingleResult4() { // 统计查询
		String sql = "select count(*) from tt_user where username = ?1";
		Number count = dq.nativeQuerySingleResult(Number.class, sql, "admin");
		System.out.println("-======================================-");
		System.out.println(count.longValue());
		System.out.println("-======================================-");
	}

	@Test
	public void testNativeQueryPagingList() { // 分页List
		String jpql = "select * from tt_user where username = ?1";
		List<User> users = dq.nativeQueryPagingList(User.class, new PageRequest(0, 5), jpql, "admin");
		System.out.println("-======================================-");
		for (User u : users) {
			System.out.println(ToStringBuilder.reflectionToString(u));
		}
		System.out.println("-======================================-");
	}

	@Test
	public void testNativeQueryPage() { // 分页Page
		String jpql = "select * from tt_user where username = ?1";
		Page<User> user = dq.nativeQuery(User.class, new PageRequest(0, 5), jpql, "admin");
		System.out.println("-======================================-");
		System.out.println("user.getNumber(): " + user.getNumber());
		System.out.println("user.getNumberOfElements(): " + user.getNumberOfElements());
		System.out.println("user.getSize(): " + user.getSize());
		System.out.println("user.getTotalElements(): " + user.getTotalElements());
		System.out.println("user.getTotalPages(): " + user.getTotalPages());
		List<User> users = user.getContent();
		for (User u : users) {
			System.out.println(ToStringBuilder.reflectionToString(u));
		}
		System.out.println("-======================================-");
	}
}