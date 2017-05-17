package com.coamctech.bxloan.web.controller.bizapply;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ExpectCashFlow;
import com.coamctech.bxloan.entity.ExpectCashFlowDetail;
import com.coamctech.bxloan.service.bizapply.BizApplyInfoCountService;
import com.coamctech.bxloan.service.bizapply.ExpectCashFlowService;
import com.coamctech.bxloan.web.vo.bizapply.ExpectCashFlowAgroVo;
import com.coamctech.bxloan.web.vo.bizapply.ExpectCashFlowDetailsAgroVo;
import com.coamctech.bxloan.web.vo.bizapply.ExpectCashFlowSimpleVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
/**
 * 预期现金流量表
 * 农业 控制器
 */
@Controller
@RequestMapping("/expectCashFlow")
public class ExpectCashFlowController  extends BaseController {
	
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ExpectCashFlowService expectCashFlowService;
	@Autowired
	private BizApplyInfoCountService applyInfoCountService;
	/**
	 * 预计现金流量列表（ExpectCashFlow）的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		Page qPage = expectCashFlowService.findBySearch(
				firstIndex / pageSize + 1, pageSize, projectId);
		List<ExpectCashFlowAgroVo> finalList = Lists.transform(qPage.getContent(), new Function<Object[],ExpectCashFlowAgroVo>(){

			@Override
			public ExpectCashFlowAgroVo apply(Object[] input) {
				return new ExpectCashFlowAgroVo(input);
			}
			
		});
		
		DataTablesPage page = new DataTablesPage(sEcho, qPage);
		page.setAaData(finalList);
		return page;
	}
	
	
	/**
	 * 预计现金流量列表（ExpectCashFlow）的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findDetailsBySearch")
	@ResponseBody
	public DataTablesPage findDetailsBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String expectCashFlowId = (String) request.getParameter("expectCashFlowId");
		String ecfType = (String) request.getParameter("ecfType");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.isBlank(expectCashFlowId)){
			expectCashFlowId = "0";
		}
		params.add(expectCashFlowId);
		params.add(ecfType);
		List<Object[]> queryList = expectCashFlowService.findDetailsBySearch(
				firstIndex / pageSize + 1, pageSize, params);
		List<ExpectCashFlowDetailsAgroVo> finalList = Lists.transform(queryList, new Function<Object[],ExpectCashFlowDetailsAgroVo>(){

			@Override
			public ExpectCashFlowDetailsAgroVo apply(Object[] input) {
				return new ExpectCashFlowDetailsAgroVo(input);
			}
			
		});
		
		List<ExpectCashFlowDetailsAgroVo> _finalList = Lists.newArrayList();
		
		BigDecimal count = BigDecimal.ZERO;
		for (ExpectCashFlowDetailsAgroVo expectCashFlowDetailsAgroVo : finalList) {
			if("1".equals(ecfType)){//收入
				count = count.add(expectCashFlowDetailsAgroVo.getMoney());
			}else if("2".equals(ecfType)){//支出
				expectCashFlowDetailsAgroVo.setMoney(expectCashFlowDetailsAgroVo.getMoney().negate());
				count = count.add(expectCashFlowDetailsAgroVo.getMoney());
			}
			_finalList.add(expectCashFlowDetailsAgroVo);
		}
		_finalList.add(new ExpectCashFlowDetailsAgroVo("合计",count,true));
		DataTablesPage page = new DataTablesPage(sEcho, _finalList, new Long(_finalList.size()), 0);
		return page;
	}
	
	
	@RequestMapping("saveSimpleEcf")
	@ResponseBody
	public Result saveSimpleEcf(@ModelAttribute ExpectCashFlowSimpleVo vo, @RequestParam Long projectId){
		ExpectCashFlow ecf = null;
		if(vo.getId()!=null){
			ecf = expectCashFlowService.findExpectCashFlowById(vo.getId());
		}else{
			ecf = new ExpectCashFlow();
			ecf.setBalance(BigDecimal.ZERO);
			ecf.setCostTotal(BigDecimal.ZERO);
			ecf.setBalanceAfterLoan(BigDecimal.ZERO);
			ecf.setBalanceBeforeLoan(BigDecimal.ZERO);
		}
		BeanUtils.copyProperties(vo, ecf, "id");
		ecf.setProjectId(projectId);
		
		try {
			expectCashFlowService.saveExpectCashFlow(ecf);
			this.caculateCount(ecf.getId());
		} catch (RuntimeException e) {
			return new Result(false, "请先填写家庭资产信息的‘现金+存款’并保存", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(true, "保存成功", ecf.getId());
		
	}
	
	@RequestMapping("findSimpleEcf")
	@ResponseBody
	public Result findSimpleEcf(@RequestParam Long ecfId){
		if(ecfId != null){
			ExpectCashFlow ecf = expectCashFlowService.findExpectCashFlowById(ecfId);
			if(ecf != null){
				ExpectCashFlowSimpleVo vo = new ExpectCashFlowSimpleVo();
				BeanUtils.copyProperties(ecf, vo);
				return new Result(true, "记录已不存在！请联系管理员", vo); 
			}else{
				return new Result(false, "记录已不存在！请联系管理员", null);
			}
		}else{
			return new Result(false, "传递数据不正确！请联系管理员", null);
		}
	}
	
	
	@RequestMapping("findCalculateEcf")
	@ResponseBody
	public Result findCalculateEcf(@RequestParam Long ecfId){
		if(ecfId != null){
			ExpectCashFlow ecf = expectCashFlowService.findExpectCashFlowById(ecfId);
			if(ecf == null){
				return new Result(false, "记录已不存在！请联系管理员", null); 
			}else{
				return new Result(true, "成功", ecf);
			}
		}else{
			return new Result(false, "传递数据不正确！请联系管理员", null);
		}
	}
	
	/**
	 * 根据Id删除现金流量
	 * 
	 * @param id
	 * @return
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delEcf/{id}")
	@ResponseBody
	public Result delEcf(@PathVariable Long id) {
		ExpectCashFlow ecf = expectCashFlowService.findExpectCashFlowById(id);
		
		if(ecf==null){
			return new Result(false, "该记录已被删除，请刷新页面！", null);
		}

		expectCashFlowService.deleteExpectCashFlow(id);
		
		return new Result(true, "删除现金流量成功！", null);
	}
	
	/**
	 * 根据Id删除现金流量细节
	 * 
	 * @param id
	 * @return
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delEcfd/{id}")
	@ResponseBody
	public Result delEcfd(@PathVariable Long id) {
		ExpectCashFlowDetail ecfd = expectCashFlowService.findExpectCashFlowDetailById(id);
		
		if(ecfd==null){
			return new Result(false, "该记录已被删除，请刷新页面！", null);
		}

		expectCashFlowService.deleteExpectCashFlowDetail(id);
		this.caculateCount(new Long(ecfd.getExpectCashFlowId()));
		return new Result(true, "删除现金流量成功！", null);
	}
	
	/**
	 * 保存现金流量细节
	 * 
	 * @param id
	 * @return
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/saveEcfDetail")
	@ResponseBody
	public Result saveEcfDetail(HttpServletRequest request){
		String id = (String)request.getParameter("id");
		String expectCashFlowId = (String)request.getParameter("expectCashFlowId");
		String name = (String)request.getParameter("name");
		String money = (String)request.getParameter("money");
		String type = (String)request.getParameter("type");
		ExpectCashFlowDetail ecfd = null;
		if(StringUtils.isBlank(id)){
			ecfd = new ExpectCashFlowDetail();
		}else{
			ecfd = this.expectCashFlowService.findExpectCashFlowDetailById(Long.valueOf(id));
			if(ecfd==null){
				return new Result(false, "该记录已不存在，请联系管理员！", null);
			}
		}
		ecfd.setMoney(new BigDecimal(money));
		ecfd.setName(name);
		ecfd.setType(type);
		ecfd.setExpectCashFlowId(new Long(expectCashFlowId));
		Long _id = this.expectCashFlowService.saveExpectCashFlowDetail(ecfd);
		this.caculateCount(new Long(expectCashFlowId));
		return new Result(true, "保存成功", _id);
	}
	
	private void caculateCount(Long expectCashFlowId){
		
		//现金+存款
		//注入service后调用取值方法
		
		
		ExpectCashFlow ecf = expectCashFlowService.findExpectCashFlowById(expectCashFlowId);

		BigDecimal perBalance = applyInfoCountService.findCashDepositByBizNfal(ecf.getProjectId());
		if(perBalance == null){
			throw new RuntimeException("现金存款金额为空！") ;
		}
		
		List<ExpectCashFlowDetail> consumeList = expectCashFlowService.findEcfdListByIdAndType(expectCashFlowId, dataDict.getCodeVal("ExpectCashFlowCd", "S2"));
		List<ExpectCashFlowDetail> incomeList = expectCashFlowService.findEcfdListByIdAndType(expectCashFlowId, dataDict.getCodeVal("ExpectCashFlowCd", "S1"));
		BigDecimal costTotal = BigDecimal.ZERO;
		BigDecimal income = BigDecimal.ZERO;
		BigDecimal balance = BigDecimal.ZERO;
		BigDecimal balanceBeforeLoan = BigDecimal.ZERO;
		BigDecimal balanceAfterLoan = BigDecimal.ZERO;
		
		for (ExpectCashFlowDetail ecfd_c : consumeList) {
			costTotal = costTotal.add(ecfd_c.getMoney());
		}
		for (ExpectCashFlowDetail ecfd_i : incomeList) {
			income = income.add(ecfd_i.getMoney());
		}
		//月度总支出
		costTotal = costTotal.add(ecf.getFamilyCost()).add(ecf.getOtherLoanRepayment());
		//月度余额
		balance = income.subtract(costTotal);
		//贷款前现金流余额
		balanceBeforeLoan = perBalance.add(balance);
		//贷款后现金流余额
		balanceAfterLoan = balanceBeforeLoan.add(ecf.getIncome()).subtract(ecf.getCost());
		
		ecf.setCostTotal(costTotal.negate());
		ecf.setBalance(balance);
		ecf.setBalanceBeforeLoan(balanceBeforeLoan);
		ecf.setBalanceAfterLoan(balanceAfterLoan);
		
		this.expectCashFlowService.saveExpectCashFlow(ecf);
	}
}
