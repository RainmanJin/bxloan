package com.coamctech.bxloan.web.controller.afterloan;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.service.afterloan.NormalRepaymentService;
import com.coamctech.bxloan.service.model.afterloan.NormalRepaymentDetailVo;
import com.coamctech.bxloan.service.model.afterloan.NormalRepaymentVo;
import com.coamctech.bxloan.web.security.ShiroUser;

/**
 * 类名称：NormalRepaymentController
 * 类描述 ：正常还款Controller
 * 创建人: hwl
 * 创建时间：2015年5月12日 上午10:39:46  
 * 修改人：hwl
 * 修改时间：2015年5月12日 上午10:39:46  
 * 修改备注：
 * 版本： V1.0
 */
@RequestMapping("/afterLoan/normalRepayment")
@Controller
public class NormalRepaymentController extends BaseController {
	@Autowired
	private NormalRepaymentService normalRepaymentService;
	/**
	 * 正常还款列表页面
	 * @return
	 */
	@RequestMapping
	public String index(Model model){
		//默认截止时间(当前时间增加一个月)
		Date endDate=DateTools.addMouth(CommonHelper.getNow(), 1);//增加一个月
		model.addAttribute("endDateStr", CommonHelper.date2Str(endDate, CommonHelper.DF_DATE));
		return "afterloan/normalRepayment/main";
	}
	/**
	 * 正常还款列表加载数据
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findPageData")
	@ResponseBody
	public DataTablesPage findPageData(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,NormalRepaymentVo vo,HttpServletRequest req){
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(vo==null){
			vo=new NormalRepaymentVo();
		}
		vo.setStartDate(CommonHelper.str2Date(req.getParameter("startDateStr"), CommonHelper.DF_DATE));
		vo.setEndDate(CommonHelper.str2Date(req.getParameter("endDateStr"), CommonHelper.DF_DATE));
		Page<NormalRepaymentVo> page=normalRepaymentService.findPage(pageSize, firstIndex/pageSize, curUser.getId(), curUser.getLogOrgid(), vo);
		return new DataTablesPage(sEcho, page);
	}
	/**
	 * 获取正常还款详细信息，用于页面正常还款页面数据显示
	 * @param contractId
	 * @param partyId
	 * @param rpId
	 * @param rpdId
	 * @return
	 */
	@RequestMapping("/getNormalRepaymentInfo")
	@ResponseBody
	public Result getNormalRepaymentInfo(long contractId,long partyId,long rpId,long rpdId){
		ShiroUser curUser=this.getShiroUser();
		if(curUser==null){
			return failure("您已登出，请重新登录！");
		}
		NormalRepaymentDetailVo vo=normalRepaymentService.findNrDetail(contractId, partyId, rpId, rpdId, curUser.getLogOrgid());
		return success(vo);
	}
	
	
	
	
	/**
	 * 获取当前用户信息
	 * @return
	 */
	private ShiroUser getShiroUser(){
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	};
}
