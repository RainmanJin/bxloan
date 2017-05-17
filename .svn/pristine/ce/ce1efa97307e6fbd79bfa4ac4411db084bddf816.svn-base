package com.coamctech.bxloan.web.controller.afterloan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.afterloan.LoanRecoverService;
import com.coamctech.bxloan.service.model.afterloan.ContractListParams;
import com.coamctech.bxloan.service.model.afterloan.LoanRecoverContractVo;
import com.coamctech.bxloan.service.model.afterloan.LrFeeRegisterVo;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoParams;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoVo;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoVo.LrRepayItem;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.afterloan.LrRepayPlanItemVo;
import com.coamctech.bxloan.web.vo.afterloan.LrRepayRecordVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 *	贷后管理-贷款回收
 */
@Controller
@RequestMapping("/" + "loanRecovery")
public class LoanRecoveryController extends BaseController{
	@Autowired
	private LoanRecoverService loanRecoverService;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private DataDict dataDict;
	/**
	 * @return
	 */
	@RequestMapping
	public String index() {
		return "afterloan/loanRecovery";
	}
	
	
	@RequestMapping("/contractList")
	@ResponseBody
	public DataTablesPage contractList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			ContractListParams params) {
		//参数
		//查询
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Page<Object[]> page=loanRecoverService.findContractPage(pageSize, (firstIndex / pageSize),CommonHelper.toStr(curUser.getId()),params);
		//结果处理
		DataTablesPage dtPage=new DataTablesPage(sEcho, page);
		dtPage.setAaData(Lists.transform(page.getContent(), new Function<Object[], LoanRecoverContractVo>() {
			@Override
			public LoanRecoverContractVo apply(Object[] input) {
				return new LoanRecoverContractVo(input);
			}
		}));
		return dtPage;
	}
	//TODO  费用登记
	/**
	 * 费用登记
	 * @return
	 */
	@RequestMapping("/feeRegister")
	@ResponseBody
	public Result feeRegister(Long contractId){
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		LrFeeRegisterVo lrVo= loanRecoverService.findFeeRegisterInfo(contractId, curUser.getOrgid());
		return success(lrVo);
	}
	/**
	 * 费用登记保存
	 * @return
	 */
	@RequestMapping("/saveFeeRegister")
	@ResponseBody
	public Result saveFeeRegister(@ModelAttribute LrFeeRegisterVo lrVo){
		loanRecoverService.saveFeeRegisterInfo(lrVo);
		return success("费用登记成功");
	}
	//TODO 还款
	/**
	 * 正常还款
	 * @return
	 */
	@RequestMapping("/normalRepay/{contractId}")
	@ResponseBody
	public Result normalRepay(@PathVariable("contractId")Long contractId, @RequestParam(required=false)String repayDate){
		final boolean overdueFlag=false;//正常还款
		LrRepayInfoParams lrParams=new LrRepayInfoParams();
		lrParams.setContractId(contractId);
		lrParams.setOverdueFlag(overdueFlag);
		if(StringUtils.isNotBlank(repayDate)){
		lrParams.setRepayDate(CommonHelper.str2Date(repayDate, CommonHelper.DF_DATE));
		}
		//默认normal2Overdue是false
		//lrParams.setNormal2Overdue(false);
		MsgResult msgResult=loanRecoverService.findRepayInfoBeforeCheck(lrParams);
		if(!msgResult.equalsMsgResult(ResultEnums.SUCCESS)){
			return new Result(false, msgResult);
		}
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		lrParams.setOrgId(curUser.getOrgid());
		try {
			LrRepayInfoVo repayInfoVo=loanRecoverService.findRepayInfo(lrParams);
			return new Result(true, repayInfoVo);
		} catch (Exception e) {
			return new Result(false, e.getMessage(), null);
		}
	}
	/**
	 * 正常还款，正常转逾期
	 * @return
	 */
	@RequestMapping("/overdueRepay/{contractId}")
	@ResponseBody
	public Result overdueRepay(@PathVariable("contractId")Long contractId
			,@RequestParam(required=false,defaultValue="0")int normal2Overdue
			,@RequestParam(required=false)String repayDate){
		boolean overdueFlag=true;//逾期
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		LrRepayInfoParams lrParams=new LrRepayInfoParams();
		lrParams.setContractId(contractId);
		lrParams.setOrgId(curUser.getOrgid());
		lrParams.setOverdueFlag(overdueFlag);
		lrParams.setNormal2Overdue(normal2Overdue==1);//normal2Overdue等于1，表把正常转逾期还款
		lrParams.setRepayDate(CommonHelper.str2Date(repayDate, CommonHelper.DF_DATE));
		MsgResult msgResult=loanRecoverService.findRepayInfoBeforeCheck(lrParams);
		if(!msgResult.equalsMsgResult(ResultEnums.SUCCESS)){
			return new Result(false, msgResult);
		}
		try {
			LrRepayInfoVo repayInfoVo=loanRecoverService.findRepayInfo(lrParams);
			repayInfoVo.setOverdue(1);
			repayInfoVo.setNormal2Overdue(normal2Overdue);
			return new Result(true, repayInfoVo);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(true, "还款失败",null);
		}
	}
	
	/**
	 * 逾期试算
	 * @return
	 */
	@RequestMapping("/overDueRepayTrial/{contractId}")
	@ResponseBody
	public Result overDueRepayTrial(@PathVariable("contractId")Long contractId){
		final boolean overdueFlag=false;//正常还款
		LrRepayInfoParams lrParams=new LrRepayInfoParams();
		lrParams.setContractId(contractId);
		lrParams.setOverdueFlag(overdueFlag);
		//默认normal2Overdue是false
		//lrParams.setNormal2Overdue(false);
		MsgResult msgResult=loanRecoverService.findRepayInfoBeforeCheck(lrParams);
		if(!msgResult.equalsMsgResult(ResultEnums.SUCCESS)){
			return new Result(false, msgResult);
		}
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		lrParams.setOrgId(curUser.getOrgid());
		try {
			LrRepayInfoVo repayInfoVo=loanRecoverService.findRepayInfo(lrParams);
			if(repayInfoVo.getLastRepayDate() == null){
				if(CollectionUtils.isNotEmpty(repayInfoVo.getRepayItems())){
					Date lastRepayDate = CommonHelper.str2Date(repayInfoVo.getRepayItems().get(0).getRepayingDate(), CommonHelper.DF_DATE);
					repayInfoVo.setLastRepayDate(lastRepayDate);
				}
			}
			return new Result(true, repayInfoVo);
		} catch (Exception e) {
			return new Result(false, e.getMessage(), null);
		}
	}
	/**
	 * 还款保存
	 * @return
	 */
	@RequestMapping("/repaySubmit")
	@ResponseBody
	public Result repaySubmit(HttpServletRequest request){
		ShiroUser curUser=(ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Long contractId=CommonHelper.toLong(request.getParameter("contractId"));
		BigDecimal actualAmt=CommonHelper.str2BigDecimal(request.getParameter("actualAmt"));//实还金额
		if(actualAmt.compareTo(BigDecimal.ZERO)<=0){
			return new Result(false, "还款金额不能为空！",null);
		}
		String repayLoanNum=request.getParameter("repayLoanNum");//还款编号
		String fundsSource=request.getParameter("fundsSource");//资金来源
		String remark=request.getParameter("remark");//备注
		Long repayingPlanDetailId = CommonHelper.toLong(request.getParameter("repayingPlanDetailId"));
		Long repayingPlanId = CommonHelper.toLong(request.getParameter("repayingPlanId"));
		int overdue= CommonHelper.toInt(request.getParameter("overdue"),0);
		int normal2Overdue= CommonHelper.toInt(request.getParameter("normal2Overdue"),0);
		Date repayDate=CommonHelper.str2Date(request.getParameter("repayDate"), CommonHelper.DF_DATE);
		LrRepayInfoParams lrParams=new LrRepayInfoParams();
		lrParams.setContractId(contractId);//合同Id
		lrParams.setRepayLoanNum(repayLoanNum);
		lrParams.setActualAmt(actualAmt);//实还金额
		lrParams.setRepayDate(repayDate);//还款日期
		lrParams.setRepayingPlanId(repayingPlanId);
		lrParams.setRepayingPlanDetailId(repayingPlanDetailId);
		lrParams.setFundsSource(fundsSource);
		lrParams.setRemark(remark);
		lrParams.setOverdueFlag(overdue==1);
		lrParams.setNormal2Overdue(normal2Overdue==1);
		lrParams.setApplyUserNum(curUser.getId());
		lrParams.setOrgId(curUser.getOrgid());
		try {
			MsgResult msgResult=loanRecoverService.saveRepayLoanBeforeCheck(lrParams);//参数校验
			if(!msgResult.equalsMsgResult(ResultEnums.SUCCESS)){
				return new Result(false, msgResult);
			}
			loanRecoverService.saveRepayLoan(lrParams);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			return new Result(false, "还款提交失败",null);
		}
		return new Result(true, "还款提交成功",null);
	}
	//TODO 逾期试算
	/**
	 * 逾期试算
	 * @return
	 */
	@RequestMapping("/overdueTrial")
	@ResponseBody
	public Result overdueTrial(@RequestParam String lastRepayDate, @RequestParam String repayDate, @RequestParam Long contractId){
		
		Date _repayDate = CommonHelper.str2Date(repayDate, CommonHelper.DF_DATE);
		Date _lastRepayDate = null;
		if(lastRepayDate!=null){
			_lastRepayDate = new Date(Long.valueOf(lastRepayDate));
			if(_repayDate.before(_lastRepayDate)){
				return new Result(false, "实还日期应大于最近一期还款日期，若未曾还款，实还日期应大于最近一期的应还日期", null);
			}else{
			}
		}
		LrRepayInfoVo repayInfoVo = loanRecoverService.findRepayingPlanDetailCountData(contractId, null, null);
		List<LrRepayItem> repayItems = repayInfoVo.getRepayItems();
		List<LrRepayItem> trialResult = Lists.newArrayList();
		BigDecimal foverdueInt = new BigDecimal("0.00");//逾期利息汇总
		BigDecimal overduePi = BigDecimal.ZERO; // 拖欠本息
		BigDecimal day = new BigDecimal("360");
		repayInfoVo.setLastRepayDate(_lastRepayDate);
		
		if(CollectionUtils.isNotEmpty(repayItems)){
			BigDecimal notyetImposeInterest = null;
			int overdueDate = 0;
			for (LrRepayItem lrItem : repayItems) {
				if(!"1".equals(lrItem.getStatus())){//不是已还
					Date currentEndDate = CommonHelper.str2Date(lrItem.getRepayingDate(), CommonHelper.DF_DATE);
					if(_repayDate.after(currentEndDate)){
						
						lrItem.setStatus(dataDict.getCodeName("PlanStatus", "2"));//逾期未还
						
						overdueDate = DateTools.getDateDiff(currentEndDate, _repayDate );//逾期天数
						int overdueTime = DateTools.getDateDiff(lrItem.getLastDate(), _repayDate );
						lrItem.setOverdueDay(overdueDate);
						//修改逾期利息的计算方法---本金*逾期日期*(逾期利息/360)----add by zhangcong 20140527
						notyetImposeInterest =(lrItem.getRepayingPricipal().add(lrItem.getRepayingInterest())
								.subtract(lrItem.getRepayedPricipal().add(lrItem.getRepayedInterest()))).
						multiply(new BigDecimal(overdueTime)).multiply(lrItem.getOverdueRate().divide(day, 50,BigDecimal.ROUND_DOWN));
						//end by zhangcong20140527
						notyetImposeInterest=notyetImposeInterest.add(lrItem.getNotyetImposeInterest());
						notyetImposeInterest = notyetImposeInterest.setScale(2, BigDecimal.ROUND_HALF_UP);
						foverdueInt =foverdueInt.add(notyetImposeInterest);
						lrItem.setNotyetImposeInterest(notyetImposeInterest);//逾期利息
						overduePi = overduePi.add(lrItem.getRepayingPricipal().add(lrItem.getRepayingInterest())
								.subtract(lrItem.getRepayedPricipal().add(lrItem.getRepayedInterest())));//拖欠本息
						//lrItem.setOverduePi(overduePi);
						lrItem.setRepayingDate(CommonHelper.date2Str(currentEndDate,CommonHelper.DF_DATE));//格式化日期
					}
					
				}
				trialResult.add(lrItem);
			}
			repayInfoVo.setOverdueInt(foverdueInt);//逾期利息
			repayInfoVo.setOverduePi(overduePi);//拖欠本息
			repayInfoVo.setRepayItems(trialResult);
			return new Result(true, repayInfoVo);
		}else{
			return new Result(false, "逾期试算失败！请联系管理员。", null);
		}
	}
	//TODO 查看
	/**
	 * 查看还款明细
	 * @return
	 */
	@RequestMapping("/showRepay")
	public String showRepay(){
		//TODO not do something!
		return "";
	}
	@RequestMapping("/findRepayList")
	@ResponseBody
	public DataTablesPage findRepayList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			Long contractId) {
		Page<Object[]> page=loanRecoverService.findRepayLoanList(pageSize, (firstIndex / pageSize), contractId);
		DataTablesPage dtPage=new DataTablesPage(sEcho, page);
		dtPage.setAaData(Lists.transform(page.getContent(), new Function<Object[], LrRepayRecordVo>() {
			@Override
			public LrRepayRecordVo apply(Object[] objs) {
				return new LrRepayRecordVo(objs);
			}
		}));
		return dtPage;
	}
	/**
	 * 查看还款计划
	 * @return
	 */
	@RequestMapping("/showRepayPlanInfo")
	public String showRepayPlanInfo(){
		//TODO not do something!
		return null;
	}
	@RequestMapping("/findRepayPlanInfoList")
	@ResponseBody
	public DataTablesPage findRepaymentPlanInfoList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			Long contractId) {
		Page<Object[]> page=loanRecoverService.findRepayingPlanInfoList(pageSize, (firstIndex / pageSize), contractId);
		DataTablesPage dtPage=new DataTablesPage(sEcho, page);
		dtPage.setAaData(Lists.transform(page.getContent(), new Function<Object[], LrRepayPlanItemVo>() {
			@Override
			public LrRepayPlanItemVo apply(Object[] objs) {
				return new LrRepayPlanItemVo(objs);
			}
		}));
		return dtPage;
	}
}
