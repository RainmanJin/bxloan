package com.coamctech.bxloan.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.entity.User;
import com.coamctech.bxloan.service.usermng.UserMngService;

public class TestUserService extends BaseSpringJUnit4Tester {

	@Autowired
	private UserMngService userService;
	
	@Test
	public void testGet() {
		User user = userService.get("c7b56b31-eb03-4698-8022-be4568c201bb");
		logger.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testNativeSql() {
		User user = userService.findByNameInNativeSql("wh");
		logger.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testSave() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("5f4dcc3b5aa765d61d8327deb882cf99"); // md5加密后的password
		user.setName("管理员");
		user.setRole("admin");
		userService.save(user);
	}
	
	@Test
	public void testUpdate() {
		User user = userService.get("2c90b147471460e701471460e9590000");
		user.setPassword("testWh");
		userService.save(user); // spring data jpa的save相当于saveOrUpdate
	}
	
	@Test
	public void testUpdateHql() {
		userService.update("wh", "2c90b147471460e701471460e9590000");
	}
	
	@Test
	public void testExists() {
		assertThat(userService.exists("2c90b147471460e701471460e9590000"), is(true));
	}
	
	@Test
	public void testDelete() {
		userService.delete("2c90b1474714a412014714a414d30000");
	}
	
	@Test
	public void testPagingQueryByName() {
		Page<User> pagingUsers = userService.pagingQueryByName("wh", 4, 2);
		List<User> users = pagingUsers.getContent();
		for (User u : users) {
			logger.info(u.getId());
		}
		logger.info("query users by page: number - {}", pagingUsers.getNumber());
		logger.info("query users by page: numberOfElements - {}", pagingUsers.getNumberOfElements());
		logger.info("query users by page: size - {}", pagingUsers.getSize());
		logger.info("query users by page: totalElements - {}", pagingUsers.getTotalElements());
		logger.info("query users by page: totalPages - {}", pagingUsers.getTotalPages());
	}
	
	@Test
	public void testQueryListByDynaHql() {
		List<User> users = userService.query("wh");
		for (User user : users) {
			ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE);
		}
	}
	
	@Test
	public void testQueryPageListByDynaHql() {
		List<User> users = userService.queryPagingList("wh", 4, 2);
		for (User user : users) {
			ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testQueryPageByDynaHql() {
		Page page = userService.query("wh", 4, 2);
		List<User> users = page.getContent();
		for (User user : users) {
			ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE);
		}
		logger.info("query users by page: number - {}", page.getNumber());
		logger.info("query users by page: numberOfElements - {}", page.getNumberOfElements());
		logger.info("query users by page: size - {}", page.getSize());
		logger.info("query users by page: totalElements - {}", page.getTotalElements());
		logger.info("query users by page: totalPages - {}", page.getTotalPages());
	}
}
