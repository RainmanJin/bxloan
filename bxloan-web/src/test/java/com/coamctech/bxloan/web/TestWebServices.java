package com.coamctech.bxloan.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.commons.webservices.WebServices;

public class TestWebServices extends BaseSpringJUnit4Tester {

	@Autowired
	private WebServices webServices;
	
	@Before
	public void init() {
		webServices.initClients();
	}

	@Test
	public void testLocal() throws Exception {
		String text = "WangHui";
		String result = webServices.invoke("local", "sayHi", text);
		assertThat(result, is("Hi, " + text));
	}

	@Test
	public void testRemote1() {
		try {
			String json = webServices.invoke("wfiService", "startWFI", "10", "1010", "fxtest01");
			System.out.println(json); // 简单类型直接打印
		} catch (Exception e) {
			System.out.println("远程服务连接超时或远程服务不可用");
			// return failureWs();
		}
	}

	@Test
	public void testRemote2() {
		try {
			String json = webServices.invoke("wfiService", "getNextTaskActions", "1010");
			List<Map<String, String>> actions = JSON.parseObject(json, new TypeReference<List<Map<String, String>>>() {}); // 泛型使用TypeReference
			for (Map<String, String> action : actions) {
				System.out.println("actioncode: " + action.get("actioncode"));
				System.out.println("nameCn: " + action.get("nameCn"));
				System.out.println("nameEn: " + action.get("nameEn"));
				System.out.println("selectActor: " + action.get("selectActor"));
				System.out.println("-=====================-");
			}
		} catch (Exception e) {
			System.out.println("远程服务连接超时或远程服务不可用");
			// return failureWs();
		}
	}

	@Test
	public void testRemote3() {
		try {
			String json = webServices.invoke("wfiService", "getNextTaskUsers",
					"2298456", "639025", "fxtest01", "1010", "SUBMIT", "1");
			List<Map<String, String>> users = JSON.parseObject(json, new TypeReference<List<Map<String, String>>>() {}); // 泛型使用TypeReference
			for (Map<String, String> user : users) {
				System.out.println("loginId:" + user.get("loginId"));
				System.out.println("userName:" + user.get("userName"));
				System.out.println("-=====================-");
			}
		} catch (Exception e) {
			System.out.println("远程服务连接超时或远程服务不可用");
			// return failureWs();
		}
	}

	@Test
	public void testRemote4() {
		try {
			String json = webServices.invoke("wfiService", "sendWFI", "10",
					"2298456", "fxtest02", "1011", "639026", "1", "fxtest01");
			System.out.println(json); // 简单类型直接打印
		} catch (Exception e) {
			System.out.println("远程服务连接超时或远程服务不可用");
			// return failureWs();
		}
	}

	@Test
	public void testAntiFraudWebService() {
		try {
			String json = webServices.invoke("wdService", "antiFraudWebService",
					"Y1619401408200014", "");
			System.out.println("====================================");
			System.out.println(json);
		} catch (Exception e) {
			System.out.println("远程服务连接超时或远程服务不可用");
			// return failureWs();
		}
	}

}
