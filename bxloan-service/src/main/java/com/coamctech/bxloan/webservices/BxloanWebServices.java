package com.coamctech.bxloan.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface BxloanWebServices {
	String sayHi(@WebParam(name="text") String text);
}
