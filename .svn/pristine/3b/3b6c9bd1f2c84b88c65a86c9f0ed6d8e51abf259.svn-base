package com.coamctech.bxloan.web.controller.test;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.FreemarkerUtils;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.ContractForm;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.ContractTerm;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.CreditInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.DebitInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.IntRate;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.RepaymentMode;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {
	@RequestMapping
	public String index(){
		return "test/freemarkerDoc";
	}
	/**
	 * 下载合同
	 */
	@RequestMapping("/downloadLoanContract")
	public void downloadLoanContract(HttpServletRequest request,HttpServletResponse response){
		LoanContractInfo lcInfo=getLoanContract();
		String filename=lcInfo.getContractNum()+".doc";
		FreemarkerUtils.createDoc("LoanContract.ftl",filename, lcInfo);
		try {
			FileUtils.downloadFile(new File("D:/temp/"+filename), filename, request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/downloadLoanContract2")
	public void downloadLoanContract2(HttpServletRequest request,HttpServletResponse response){
		LoanContractInfo lcInfo=getLoanContract();
		String fileName=lcInfo.getContractNum()+".doc";
		try {
			FreemarkerUtils.createDoc("LoanContract.ftl", FileUtils.initDownload(request, response, fileName), lcInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private LoanContractInfo getLoanContract(){
		LoanContractInfo lcInfo=new LoanContractInfo();
		lcInfo.setContractNum("01011140228【借】-01");
		DebitInfo debitInfo=lcInfo.new DebitInfo("王颖","身份证号", "011253198402152315",
				"2345342634666666634", "李亚东", "身份的骄傲和大厦附近卡死", "235423423", "1412412312");
		lcInfo.setDebitInfo(debitInfo);
		CreditInfo creditInfo=lcInfo.new CreditInfo("京邦信小额sdafaf贷款股份有限公司", "刘虎那", "北京市西城区阜成门内大街410号", "010-66078860");
		lcInfo.setCreditInfo(creditInfo);
		lcInfo.setLoanTypeAndPurpose("个人贷款产品", "开拓欧美市场");
		lcInfo.setLoanAmtCapital("壹佰万零壹元整");
		lcInfo.setLoanAmtLower("￥ 1,000,001.00");
		ContractForm contractForm =lcInfo.new ContractForm("3", "1", "1", "1", "0");
		contractForm.addItem("itemsafdsdf1", "itemsafdsdf12", "itemsafdsdf14");
		lcInfo.setContractForm(contractForm);
		ContractTerm contractTerm=lcInfo.new ContractTerm(2);
		contractTerm.addItem(null, "12", null);
		lcInfo.setContractTerm(contractTerm);
		IntRate intRate=lcInfo.new IntRate(2, "6.6", "7.8875", "2");
		lcInfo.setIntRate(intRate);
		RepaymentMode repaymentMode=lcInfo.new RepaymentMode(2, "北京支行", "王杰", "235436347347534534");
		repaymentMode.setItem3("23465236542362345");
		lcInfo.setRepaymentMode(repaymentMode);
		lcInfo.setNoticeDrDayOfPrepayment(30);
		lcInfo.setScaleOfSeverance("2%");
		lcInfo.setPeIntRateOfUseChange("4");
		lcInfo.setPeIntRateOfOverdue("5");
		lcInfo.setClearFee("400");
		lcInfo.setBreakContRate("50");
		return lcInfo;
	}
}
