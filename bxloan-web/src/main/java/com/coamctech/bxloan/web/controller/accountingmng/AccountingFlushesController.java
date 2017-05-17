package com.coamctech.bxloan.web.controller.accountingmng;

import static com.coamctech.bxloan.commons.GlobalConstants.ACCOUNTING_FLUSHES;
import static com.coamctech.bxloan.commons.GlobalConstants.ACCOUNTING_MNG;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.service.accountingmng.AccountingFlushesService;
import com.coamctech.bxloan.service.model.accountingmng.AccountingFlushesVo;
import com.coamctech.bxloan.service.model.accountingmng.AccountingFlushesVo.BillAccountVo;
import com.coamctech.bxloan.service.model.accountingmng.AccountingFlushesVo.BillDetailVo;
import com.coamctech.bxloan.service.model.accountingmng.BillVo;
import com.coamctech.bxloan.service.model.accountingmng.BizBaseVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 账务冲正
 */
@Controller
@RequestMapping("/" + ACCOUNTING_FLUSHES)
public class AccountingFlushesController {
	@Autowired
	private AccountingFlushesService accountingFlushesService;
	/**
	 * @return
	 */
	@RequestMapping
	public String init() {
		
		return ACCOUNTING_MNG+"/"+ACCOUNTING_FLUSHES;
	}
	
	/**
	 * 单据凭证相关列表
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		//参数
		AccountingFlushesVo.Params params=AccountingFlushesVo.newParams();
		params.setCustomerName(StringUtils.trimToEmpty(request.getParameter("customerName")));
		params.setBillState(StringUtils.trimToEmpty(request.getParameter("billState")));
		params.setContCd(StringUtils.trimToEmpty(request.getParameter("contCd")));
		params.setCustCd(StringUtils.trimToEmpty(request.getParameter("custCd")));
		params.setBusiTypCd(StringUtils.trimToEmpty(request.getParameter("busiTypCd")));
		if(StringUtils.equalsIgnoreCase(params.getBusiTypCd(), "all")){
			params.setBusiTypCd(null);
		}
		if(StringUtils.equalsIgnoreCase("all",params.getBillState())){
			params.setBillState(null);
		}
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		params.setCurUserId(new Long(curUser.getId()));
		//查询
		Page<Object[]> page=accountingFlushesService.findTallyCertificatePage(pageSize, (firstIndex / pageSize),params);
		//结果处理
		DataTablesPage dtPage=new DataTablesPage(sEcho, page);
		dtPage.setAaData(Lists.transform(page.getContent(), new Function<Object[], BillVo>() {
			@Override
			public BillVo apply(Object[] input) {
				return new BillVo(input);
			}
		}));
		return dtPage;
	}
	/**
	 * 冲销
	 * @param request
	 * @return
	 */
	@RequestMapping("/flushes/{id}")
	@ResponseBody
	public Result flushes(@PathVariable("id") Long id){
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		MsgResult msgResult= accountingFlushesService.updateTallyCertificateOfFlushes(id,curUser.getName());
		return new Result(ResultEnums.ACCOUNT_FLUSHES_SUCCESS.eqCode(msgResult.getCode()),msgResult);
	}
	
	/**
	 * 单据凭证详细
	 * @param request
	 * @return
	 */
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") Long id,Model model){
		BillDetailVo billDetailVo=accountingFlushesService.findBillDetail(id);
		model.addAttribute("vo", billDetailVo);
		return ACCOUNTING_MNG+"/billView";
	}
	@RequestMapping("/showBiz/{id}")
	public String showBiz(@PathVariable("id") Long id,Model model){
		BizBaseVo vo=accountingFlushesService.findBizInfo(id);
		String result=ACCOUNTING_MNG+"/bizError";
		if(vo!=null){
			model.addAttribute("vo", vo);
			switch (vo.getBusinessType()) {
			case LOAN:
				result=ACCOUNTING_MNG+"/bizPayLoan";

			default:
				break;
			}
		}
		return result;
	}
	/**
	 * 单据凭证详细
	 * @param request
	 * @return
	 */
	@RequestMapping("/accountList")
	public String accountList(String billCd,Model model){
		List<BillAccountVo> voList=accountingFlushesService.findBillAccountVoList(billCd);
		model.addAttribute("voList", voList);
		return ACCOUNTING_MNG+"/billAccountList";
	}
	
}
