package com.coamctech.bxloan.webservices.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.coamctech.bxloan.webservices.BxloanWebServices;

@Component("bxloanWebServices")
@WebService(endpointInterface = "com.coamctech.bxloan.webservices.BxloanWebServices", 
	targetNamespace = "http://webservices.bxloan.coamctech.com/", serviceName = "bxloanWebServices")
public class BxloanWebServicesImpl implements BxloanWebServices {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String sayHi(String text) {
		logger.info("remote request data: {}", text);
		return "Hi, " + text;
	}

}
