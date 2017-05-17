package com.coamctech.bxloan.web.controller.usermng;

import static com.coamctech.bxloan.commons.GlobalConstants.USER_MNG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Report;
import com.coamctech.bxloan.commons.result.ReportExtension;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.User;
import com.coamctech.bxloan.service.usermng.UserMngService;

@Controller
@RequestMapping("/" + USER_MNG)
public class UserMngController extends BaseController {

	@Autowired
	private UserMngService userMngService;

	@RequestMapping
	public String index() {
		return USER_MNG + "/main";
	}

	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sSearch") String search, @RequestParam("sEcho") Integer sEcho, 
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		Page<User> page = userMngService.findBySearch(search, firstIndex / pageSize + 1, pageSize);
		return new DataTablesPage(sEcho, page);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
	@ResponseBody
	public User get(@PathVariable("id") String id) {
		return userMngService.get(id);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result save(User form) {
		userMngService.save(form);
		return success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@PathVariable("id") String id) {
		userMngService.delete(id);
		return success();
	}

	@RequestMapping(value = "/getAllIndustry", method = RequestMethod.POST)
	@ResponseBody
	public List<IndustryType> getAllIndustry() {
		return userMngService.getAllIndustry();
	}
	@RequestMapping("/testReport")
	public String exportReportPersonalCreditLoanApply(Model model) {
	    Report report = getReportPersonalCreditLoanApply();
	    model.addAllAttributes(report);
	    return "reportLoanVoucher";
	}

	public Report getReportPersonalCreditLoanApply() {
	    Report report = new Report(ReportExtension.XLSX);
	    report.addFields(new JREmptyDataSource());
		
	    Map<String, Object> parameters = new HashMap<String, Object>();
	    
	    
	    parameters.put("OrgName", "北京邦信小额贷款股份有限公司"); // 机构名称
	    parameters.put("loanDuebillNo", "J100001409090011"); //借据编号
	    parameters.put("customerName", "王丽丽"); //借款人全称
	    parameters.put("contractNo", "619401140050【借】-88"); // 合同号
	   
	    parameters.put("loanAmtUp", "叁万元"); // 借款金额大写
	    parameters.put("tenMillion", "4"); // 千万
	    parameters.put("million", "6"); // 百万
	    parameters.put("oneHundredThousand", "0"); // 十万
	    parameters.put("tenThousand", "3"); // 万
	    parameters.put("thousand", "5"); // 千
	    
	    parameters.put("hundred", "2"); // 百
	    parameters.put("ten", "4"); // 十
	    parameters.put("yuan", "8"); // 元
	    parameters.put("jiao", "5"); // 角
	    parameters.put("fen", "9"); // 分
	    parameters.put("rmb", "1"); // 是否显示￥ 1为显示，否则该值传0，并 在小写数字最大值前面加￥。
	    parameters.put("loanStartDate", "2014年05月22日"); // 借款起始日
	    
	    parameters.put("loanEndDate", "2014年12月22日"); // 借款到期日
	    parameters.put("yearRate", "24%"); // 年利率
	    parameters.put("ratetype", "1"); // 利率类型
	    
	    parameters.put("payOutName", "李丽丽"); // 借款转入账户 名称
	    parameters.put("payOutBank", "北京银行阜成门支行"); // 借款转入账户 开户行
	    parameters.put("payOutAccount", "622356897542569875357"); // 借款转入账户 账号
	    
	    parameters.put("rePayName", "北京邦信小贷公司"); // 借款还款账户 名称
	    parameters.put("rePayBank", "北京银行国贸门支行"); // 借款还款账户 开户行
	    parameters.put("rePayAccount", "622356897542569875357"); // 借款还款账户 账号
	    
	    report.addParameters(parameters); //
			
	    return report;
	}

}
