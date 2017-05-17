package com.coamctech.bxloan.webservices.impl;

import java.util.Date;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.service.pettyloan.LoanTallyCertificateService;
import com.coamctech.bxloan.webservices.BxloanAccountingServices;

@Component("bxloanAccountingServices")
@WebService(endpointInterface = "com.coamctech.bxloan.webservices.BxloanAccountingServices", targetNamespace = "http://webservices.bxloan.coamctech.com/", serviceName = "bxloanAccountingServices")
public class BxloanAccountingServicesImpl implements BxloanAccountingServices {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LoanTallyCertificateService loanTallyCertificateService;

	@Override
	public String dealBill(String billCd, String operator, String busiDt) {
		logger.info("remote request data: {billCd:" + billCd + ",busiDt:"
				+ busiDt + ",operator:" + operator + "}");
		String result = "FAIL,error";
		if (StringUtils.isBlank(billCd) || StringUtils.isBlank(busiDt)
				|| StringUtils.isBlank(operator)) {
			return result;
		}
		Date busiDate = DateTools.stringToDate(busiDt, "yyyyMMdd");
		if (busiDate == null) {
			return result;
		}
		try {
			loanTallyCertificateService.dealBill(billCd, operator, busiDate,LoanTallyCertificateService.STATUS_ENTER,null);
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}

		return "SUCCESS,处理成功";
	}

	@Override
	public String cancBill(String billCd, String operator, String backCause) {
		logger.info("remote request data: {billCd:" + billCd + ",rejCause:"
				+ backCause + ",operator:" + operator + "}");
		String result = "FAIL,error";
		Date busiDate =new Date();
		try {
			loanTallyCertificateService.dealBill(billCd, operator, busiDate,LoanTallyCertificateService.STATUS_BACK,backCause);
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return "SUCCESS,处理成功";
	}
}
