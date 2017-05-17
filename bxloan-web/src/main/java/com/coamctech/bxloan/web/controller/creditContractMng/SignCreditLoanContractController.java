package com.coamctech.bxloan.web.controller.creditContractMng;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.creditcontractmng.CreditContractMngService;
import com.coamctech.bxloan.service.creditcontractmng.UnderCreditContractMngService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.contractmng.ContractVO;

@Controller
@RequestMapping(GlobalConstants.SIGN_CREDIT_LOAN_CONTRACT)
public class SignCreditLoanContractController extends BaseController {
	@Autowired
	private DataDict dataDict;
	@Autowired
	private  WfActionTreeNodeService wfActionTreeNodeService;
	@Autowired
	private UnderCreditContractMngService underCreditContractMngService;
	@Autowired
	private ContractMngService contractMngService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private CreditContractMngService creditContractMngService;
	@Autowired
	private CreditContractApprovalService creditContractApprovalService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	/** 合同状态 */
	private final String CODETYPE_CONTRACTSTATUS = "ContractStatusCode";
	
	@RequestMapping
	public String index(Model model) {
		// 担保方式列表
		Collection<CodeData> guaranteeModes = dataDict.getOneType("CdsGuarantMode");
		model.addAttribute("guaranteeModes", guaranteeModes);
		// 面向客户类型列表
		Collection<CodeData> orientedCustomerTypes = dataDict.getOneType("OrientedCustomerType");
		model.addAttribute("orientedCustomerTypes", orientedCustomerTypes);

		return GlobalConstants.CREDIT_CONTRACT_MNG + "/" + GlobalConstants.UNDER_CREDIT_CONTRACT_MNG + "/" + GlobalConstants.SIGN_CREDIT_LOAN_CONTRACT + "/main";
	}
	/**
	 * 待办列表跳转到制定授信借款签订合同页面方法
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @param projectId 业务ID
	 * @param partyType 客户类型
	 * @param model
	 * @return String 跳转路径
	 */
	@RequestMapping("/perCondition/{wfCode}/{workflowId}/{taskId}/{taskStageCode}")
	public String perCondition(@PathVariable("wfCode") String wfCode, @PathVariable("workflowId") String workflowId,
			@PathVariable("taskId") String taskId, @PathVariable("taskStageCode") String taskStageCode, Model model) {
			//获取流程与业务关系对象
			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
			//授信下借款业务Id
			Long projectId = wfBusinessRelation.getBusinessId();
			//授信合同业务id
			Long parentProjectId=wfBusinessRelation.getParentBusinessId();
			//查询授信合同信息
			CreditContract creditContract=creditContractMngService.findCreditContractByProjectId(parentProjectId);
			Long creditId=creditContract.getCreditContractId();
			
			//查看是否已经生成了该合同
			Contract contract = contractMngService.findContractByProjectId(projectId);
			ProjectApplication project = contractMngService.getProject(projectId);
			Long partyId = project.getPartyId();
			Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
			BizRate bizRate = contractMngService.findBizRateByProjectId(project.getProjectId());
			
		if (contract == null) {
				// 创建授信借款合同
				contract = new Contract();
				ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
				// 生成合同编号
				String contractNum;
			if (dataDict.getCodeVal("CustomerType", "S1").equals(project.getBusinessType())) { // 个人贷款
				contractNum = commonBizNumberBS.generateContractNumber(curUser.getLogOrgid().toString(), "1", "借", 1);
				contract.setContractNum(contractNum);
			} else if (dataDict.getCodeVal("CustomerType", "S2").equals(project.getBusinessType())) { // 企业贷款
				contractNum = commonBizNumberBS.generateContractNumber(curUser.getLogOrgid().toString(), "0", "借", 1);
				contract.setContractNum(contractNum);
			}
				BeanUtils.copyProperties(project, contract);
				//客户名称
				contract.setCustomerName(party.getPartyName());
				//客户编号
				contract.setCustomerNum(party.getCustomerNum());
				// 设置展期笔数
				contract.setContractIndex(new Long(0));
				// 设置可用金额
				contract.setContractAmt(project.getApproveAmt());
				contract.setContractAvailableAmt(project.getApproveAmt());
				//授信借款额度
				contract.setContractAmt(project.getApproveAmt());
			// 合同期限和期限单位
			if (project.getTerm() != null) {
				contract.setContractTerm(project.getTerm().intValue());
				contract.setContractTermTotal(project.getTerm().intValue());
			}
				//授信借款期限单位
				contract.setContractTermUnit(project.getTermUnit());
				contract.setContractTermUnitTotal(project.getTermUnit());
				//还款周期月数
				contract.setRepayPrincipalMonthes(project.getReplyPeriodNum());
				//还款方式
				contract.setRepayModeCd(project.getRepayingMode());
			if (project.getReplyPeriodNum() != null) {
				contract.setRepayPrincipalMonthes(project.getReplyPeriodNum()
						.intValue());
			}
			// 设置合同性质
			if (project.getBusinessProcessType() != null) {
				contract.setContractNatureCd(project.getBusinessProcessType());
			}
				contract.setLoanDateStyle(project.getLoanDateStyle());
				// 设置时间属性
				java.util.Date currency = new Date();
				currency = uniqueCustomerService.getDBTime();
				contract.setSysCreateDate(currency);
				contract.setSysUpdateDate(currency);
				// 审批后的贷款年利率
				contract.setContractRate(bizRate.getApproveRateValue());
				//授信合同ID
				contract.setCreditContractId(creditId);
				contract.setContractNatureCd(dataDict.getCodeVal("BussinessProperty", "S1"));//新增合同
				// 设置发起者
				contract.setApplyUserNum(project.getCustomerManagerNum());
				// 设置贷款产品
				contract.setProductType(project.getProductType());
				// 设置检查状态
				contract.setCheckStatus("1");
				contract.setCurrency(project.getCurrency());
				contract.setPreRepaymentRate(project.getPreRepaymentRate());//提前还款违约金比例
				String applyOrg = project.getApplyOrg();
			if (StringUtils.isNotBlank(applyOrg) && !"null".equals(applyOrg)) {
				contract.setApplyOrgId(Long.valueOf(applyOrg));
			}
				// 设置客户Id
				contract.setPartyId(project.getPartyId());
				Long underCreditContractId  = contractMngService.saveContract(contract);
				model.addAttribute("underCreditContractId", underCreditContractId);
		} else {
				model.addAttribute("underCreditContractId", contract.getContractId());
		}
			/** 授信借款合同VO组装，制定电子合同页面回显数据使用 start */
			ContractVO ucContractVO = new ContractVO();
			BeanUtils.copyProperties(contract, ucContractVO);
			BeanUtils.copyProperties(bizRate, ucContractVO);
			//贷款用途
			ucContractVO.setPurpose(project.getPurpose());
		//预约还款日
		if (contract.getArrangeRepayDay() != null){
			ucContractVO.setArrangeRepayDay(contract.getArrangeRepayDay().toString());
		}
		//年利率
		if (bizRate.getFinalRateValue() != null){
			ucContractVO.setFinalRateValue(
					MathUtil.BDmultiply(
							ucContractVO.getFinalRateValue(),
						new BigDecimal("100"), GlobalConstants.BIGDECIMAL_SCALE));
		}
		//利率浮动比例
		if(ucContractVO.getFinalFloatRate() != null){
			ucContractVO.setFinalFloatRate(
					MathUtil.BDmultiply(
							ucContractVO.getFinalFloatRate(),
						new BigDecimal("100"), GlobalConstants.BIGDECIMAL_SCALE));
		}
			//贷款产品
			String productName = businessApplicationService.findProductNameByProductCd(ucContractVO.getProductType());
			ucContractVO.setProductType(productName);
			
			String businessType = project.getBusinessType();
		if (dataDict.getCodeVal("BusinessTypeCD", "S2").equals(businessType)) {
			ucContractVO.setCustomerType(dataDict.getCodeName("CustomerType", "1"));
		} else if (dataDict.getCodeVal("BusinessTypeCD", "S1").equals(businessType)) {
			ucContractVO.setCustomerType(dataDict.getCodeName("CustomerType", "2"));
		}
			ucContractVO.setContractTerm(contract.getContractTerm().toString());
			ucContractVO.setContractTermUnit(dataDict.getCodeName("TermUnitCd",contract.getContractTermUnit()));
			//投放行业
			String investmentIndustry = contractMngService.findIndustryNameByIndustryCd(project.getInvestmentIndustry());
			ucContractVO.setInvestmentIndustry(investmentIndustry);
			/** 授信借款合同VO组装，签订合同页面回显数据使用 end */
			//存储相关参数
			model.addAttribute("wfCode", wfCode);
			model.addAttribute("workflowId", workflowId);
			model.addAttribute("taskId", taskId);
			model.addAttribute("taskStageCode", taskStageCode);
			model.addAttribute("projectId", projectId);
			model.addAttribute("partyId", partyId);
			model.addAttribute("partyType", party.getPartyTypeCd());
			model.addAttribute("productTypeCd", project.getProductType());
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("allDocType", DocStageCode.HT_DOC.getCodeId());
			model.addAttribute("vo", ucContractVO);
		return GlobalConstants.CREDIT_CONTRACT_MNG+"/"+GlobalConstants.SIGN_CREDIT_LOAN_CONTRACT + "/main";
	}
	/**
	 * 获取当前登录人登录名 
	 */
	private String curUserLogName(){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getLogname();
	}
	/** 获取当前用户名字 */
	private String curUserName() {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getName();
	}
	/**
	 * 抵质押物列表的查询方法
	 * 签订合同时显示抵质押物列表
	 */
	@RequestMapping("/searchCollateralList")
	@ResponseBody
	public DataTablesPage searchCollateralList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
			//获取流程与业务关系对象
			String workflowId =  request.getParameter("workflowId");
			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
			Long businessId = wfBusinessRelation.getBusinessId();
			Long parentProjectId=wfBusinessRelation.getParentBusinessId();
			Page queryList = underCreditContractMngService.findCollateralsAllBySearch(businessId,parentProjectId,
					(firstIndex / pageSize) + 1, pageSize);
			DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}
	/**
	 * 保存授信下借款合同信息(同时存储从合同信息)
	 * 
	 * @param projectId 业务Id
	 * @return String 修改是否成功
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveUCCreditSubContract")
	@ResponseBody
	public Result saveUCCreditSubContract(@RequestParam String workflowId ,ContractVO contractSaveVO) {
			SessionUser sessionUser = new SessionUser();
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
					sessionUser.setOrgId(shiroUser.getLogOrgid());
					sessionUser.setUserId(shiroUser.getId());
		try {
			//获取流程与业务关系对象
			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
			Long projectId = wfBusinessRelation.getBusinessId();
			Long parentProjectId=wfBusinessRelation.getParentBusinessId();
			//得到合同信息
			Contract oldContract = contractMngService.findContractByProjectId(projectId);
			ProjectApplication project = businessApplicationService.searchProjectApplication(projectId);
			
			BizRate bizRate = contractMngService.findBizRateByProjectId(projectId);
			oldContract.setBankName(contractSaveVO.getBankName());
			oldContract.setLoanNum(contractSaveVO.getLoanNum());
			
			if (contractSaveVO.getLoanDateStyle().equals("1")) {
				oldContract.setArrangeRepayDay(Integer.valueOf(contractSaveVO.getArrangeRepayDay().trim()));
			}
			oldContract.setLoanDateStyle(contractSaveVO.getLoanDateStyle());
			oldContract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S8"));
			oldContract.setSysCreateDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd")); // 合同创建日期
			oldContract.setSysUpdateDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd"));
			
			BigDecimal interestRate = saveBizInfo(contractSaveVO, project, bizRate);
			if (interestRate != null) {
				oldContract.setInterestRate(interestRate.divide(new BigDecimal("100"))); // 基准利率值
			}
			
			oldContract.setContractRate(bizRate.getFinalRateValue());// 最终的贷款年利率
			BigDecimal ovdueIrNegoRate = bizRate.getOvdueIrNegoRate(); // 逾期加减值
			
			if (ovdueIrNegoRate != null && oldContract.getContractRate() != null) {
				ovdueIrNegoRate = new BigDecimal(MathUtil.BDadd(1,ovdueIrNegoRate.doubleValue(), 6));
				// 逾期利率=贷款利率*(1+逾期加减值)
				oldContract.setContractOvdueRate(MathUtil.BDmultiply(
						oldContract.getContractRate(), ovdueIrNegoRate, 6)); // 逾期贷款利率
			}
			bizRate.setFinalAdjustPeriod("1");
			contractMngService.saveContract(oldContract);
			contractMngService.saveBizRate(bizRate);
			//查询从合同信息
			List<SubContract> subContractList = contractMngService.findSubcontractList(projectId);
			if(CollectionUtils.isEmpty(subContractList)) {
				//根据抵质押信息生成抵质押从合同信息
				underCreditContractMngService.copyPawnInfoToSubContract(projectId, parentProjectId,sessionUser);
				//根据保证人信息生成保证人从合同信息
				underCreditContractMngService.copyAssureInfoToSubContract(projectId, parentProjectId,sessionUser);
			} else {
				//修改从合同表系统更新时间
				for (SubContract subcontract : subContractList) {
					subcontract.setSysUpdateDate(CommonHelper.getNow());
					underCreditContractMngService.saveSubContract(subcontract);
				}
			}
			return success("保存授信借款合同信息成功！");
		} catch (Exception e) {
			logger.error("报错失败，错误原因："+e.getMessage());
			return failure("保存失败！");
		}
	}
	public BigDecimal saveBizInfo(ContractVO contractSaveVO,ProjectApplication project, BizRate bizRate) {
		BigDecimal interestRate = null;
		if (null != bizRate) {
			String irTypeCd = bizRate.getFinalIrTypeCd(); // 利率类型及调整方式
			if (StringUtils.isNotBlank(irTypeCd) && !"null".equals(irTypeCd)) {
				if (dataDict.getCodeVal("InterestRateAdjustment", "S2").equals(irTypeCd)) { // 浮动利率
					bizRate.setFinalAdjustPeriod(bizRate.getFinalAdjustPeriod()); // 最终调整周期
					Integer term = project.getTerm();
					String termUnit = project.getTermUnit();
					MoneyRate moneyRate = businessApplicationService.findValidMoneyRate(term.toString(), termUnit);
					interestRate = moneyRate.getInterestRate(); // 基准利率
					// contractVo.setInterestRate(interestRate);
					BigDecimal	fe=contractSaveVO.getFinalFloatRate();
					BigDecimal finalFloatRate = fe.divide(new BigDecimal("100")); // 利率加减值
					if (null != finalFloatRate) {
						bizRate.setFinalFloatRate(finalFloatRate); // 最终上浮比例值
						finalFloatRate = MathUtil.BDadd(new BigDecimal("1"),finalFloatRate, 6);
						bizRate.setFinalRateValue(MathUtil.BDmultiply(interestRate, finalFloatRate, 6)); // 贷款利率
					}
				} else if (dataDict.getCodeVal("InterestRateAdjustment", "S1").equals(irTypeCd)) {
					bizRate.setFinalRateValue(contractSaveVO.getFinalRateValue().divide(new BigDecimal("100")));
				}
			} else {
				return null;
			}
		}
		bizRate.setRateAdjustmentReason(contractSaveVO
				.getRateAdjustmentReason());
		contractMngService.saveBizRate(bizRate);
		return interestRate;
	}
	/**
	 * 保证人从合同列表的查询方法
	 * 
	 * @param request HttpServletRequest的对象
	 * @param sEcho  datatables的被请求次数
	 * @param firstIndex 起始页数
	 * @param pageSize 每页多少条记录
	 * @return page DataTablesPage对象的实例
	 */
	@RequestMapping("/searchAssureSubcontractList")
	@ResponseBody
	public DataTablesPage searchAssureSubcontractList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String creditContractId = (String) request.getParameter("contractIdField");
		Page queryList = contractMngService.getAssureListByContractId(
				Long.parseLong(creditContractId), (firstIndex / pageSize) + 1,pageSize);
		return new DataTablesPage(sEcho, queryList);
	}
	/**
	 * 抵质押从合同列表的查询方法
	 * 
	 * @param request HttpServletRequest的对象
	 * @param sEcho datatables的被请求次数
	 * @param firstIndex 起始页数
	 * @param pageSize 每页多少条记录
	 * @return page DataTablesPage对象的实例
	 */
	@RequestMapping("/searchCollateralSubcontractList")
	@ResponseBody
	public DataTablesPage searchCollateralSubcontractList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
 			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String UCContractId = (String) request.getParameter("contractIdField");
		Page queryList = contractMngService.getPawnListByContractId(Long.parseLong(UCContractId),(firstIndex / pageSize), pageSize);
		return new DataTablesPage(sEcho, queryList);
	}
	/**
	 * 提交合同，更改合同状态
	 */
	@RequestMapping("/submitContract")
	@ResponseBody
	public Result submitContract(
			@RequestParam("wfCode") String wfCode,
			@RequestParam("contractId") Long contractId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode") String taskStageCode,
			@RequestParam("workflowId") String workflowId,
			@RequestParam(value = "nextUser", required = false) String nextUser,
			@RequestParam(value = "nextUserOrgId", required = false) String nextUserOrgId,
			@RequestParam("comments") String comments,
			@RequestParam("instruction") String instruction) {
		try {
			WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
			WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
			//组装参数信息
			BizApprovalOfWfParam bizApprovalOfWfParam = BizApprovalOfWfParam.newBizApprovalOfWfParam(workflowId, taskId, comments,
					this.curUserLogName(), this.curUserName(),
					nextUser, nextUserOrgId, workFlowCode, workFlowNode);
			
			//提交下一环节
			creditContractApprovalService.submitSignContract(bizApprovalOfWfParam);
			//更新授信合同信息
			Contract contract = contractMngService.getContract(Long.valueOf(contractId));
					contract.setInitContractId(contract.getContractId());
					contract.setStartDate(uniqueCustomerService.getDBTime());
					contract.setSysUpdateDate(uniqueCustomerService.getDBTime());
					contract.setContractStatusCd(dataDict.getCodeVal(CODETYPE_CONTRACTSTATUS, "S8"));
					contract.setFulfillInstructionCd(instruction);
			  contractMngService.saveContract(contract);
			return new Result(true, "提交下一环节成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "提交时发生异常，请联系管理员", null);
		}
	}
}
