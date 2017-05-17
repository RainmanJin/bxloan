package com.coamctech.bxloan.web.controller.creditContractMng;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.collateral.CollateralService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.creditcontractmng.CreditContractMngService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.contractmng.ProjectAssurerInfoVo;
import com.coamctech.bxloan.service.model.contractmng.ProjectPawnInfoVo;
import com.coamctech.bxloan.service.model.credit.CreditContractVo;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.controller.approval.CreditContractApprovalController;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.contractmng.ContractSaveVO;
import com.google.common.collect.Lists;

/**   
 * 类名称：CreditContractMngController
 * 类描述 ：授信合同管理
 * 创建人: gph 
 * 创建时间：2015年5月13日 上午10:44:34  
 * 修改人：gph
 * 修改时间：2015年5月13日 上午10:44:34  
 * 修改备注：
 * 版本： V1.0
 */
@Controller
@RequestMapping(GlobalConstants.CREDIT_CONTRACT_MNG)
public class CreditContractMngController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(CreditContractMngController.class);
	@Autowired
	private CreditContractMngService creditContractMngService;
	
	@Autowired
	private ContractMngService contractMngService;
	
	@Autowired
	private BusinessApplicationService businessApplicationService;
	
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	
	@Autowired
	private CollateralService collateralService;
	
	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private WfActionTreeNodeService wfActionTreeNodeService;
	
	@Autowired
	private CreditContractApprovalService creditContractApprovalService;
	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping
	public String index() {
		return GlobalConstants.CREDIT_CONTRACT_MNG + "/main";
	}

	/**
	 * 分页查询授信合同列表
	 * @author:gph
	 * @createTime:2015年5月13日
	 * @param sEcho
	 * @param pageNumber 当前页数
	 * @param pageSize 每页显示个数
	 * @param credit 授信合同对象
	 * @return 授信合同列表
	 */
	@RequestMapping(value = "/creditContractList")
	@ResponseBody
	public DataTablesPage creditContractList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer pageNumber,
			@RequestParam("iDisplayLength") Integer pageSize, CreditContractVo vo) {
		
		//只显示当前登录用户申请的授信合同列表
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		vo.setApplyUserNum(String.valueOf(curUser.getId()));
		
		Page<CreditContractVo> page = creditContractMngService.creditContractList(pageNumber/pageSize, pageSize, vo);
		return new DataTablesPage(sEcho, page);
	}
	
	/**
	 * 待办列表跳转到制定授信电子合同页面方法
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
		Long projectId = wfBusinessRelation.getBusinessId();
		CreditContract creditContract = creditContractMngService.findCreditContractByProjectId(projectId);
		ProjectApplication pa = businessApplicationService.searchProjectApplication(projectId);
		BizRate bizRate = businessApplicationService.getBizRateByProjectId(projectId.toString());
		Long partyId = pa.getPartyId();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
		if (creditContract == null) {
			// 创建授信合同
			creditContract = new CreditContract();
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			// 生成合同编号
			String contractNum;
			if (dataDict.getCodeVal("CustomerType", "S1").equals(pa.getBusinessType())) { // 个人贷款
				contractNum = commonBizNumberBS.generateContractNumber(curUser.getLogOrgid().toString(), "1", "授", 1);
				creditContract.setContractNum(contractNum);
			} else if (dataDict.getCodeVal("CustomerType", "S2").equals(pa.getBusinessType())) { // 企业贷款
				contractNum = commonBizNumberBS.generateContractNumber(curUser.getLogOrgid().toString(), "0", "授", 1);
				creditContract.setContractNum(contractNum);
			}
			BeanUtils.copyProperties(pa, creditContract);
			//客户名称
			creditContract.setCustomerName(party.getPartyName());
			//客户编号
			creditContract.setCustomerNum(party.getCustomerNum());
			//授信额度
			creditContract.setContractAmt(pa.getApproveAmt());
			//授信可用额度
			creditContract.setCreditAvaiableAmt(pa.getApproveAmt());
			//授信期限
			creditContract.setContractTerm(pa.getTerm());
			//授信期限单位
			creditContract.setContractTermUnit(pa.getTermUnit());
			//经办机构
			creditContract.setApplyOrgId(Long.parseLong(pa.getApplyOrg()));
			//经办人
			creditContract.setApplyUserNum(pa.getCustomerManagerNum());
			//授信合同开始日期
			creditContract.setApplyDate(pa.getApproveDate());
			//创建日期
			creditContract.setSysCreateDate(uniqueCustomerService.getDBTime());
			//更新日期
			creditContract.setSysUpdateDate(uniqueCustomerService.getDBTime());
			//授信年利率
			creditContract.setBizRate(bizRate.getFinalRateValue());
			//展期笔数
			creditContract.setContractIndex(new Long(0));
			//授信状态，默认为未生效
			creditContract.setContractStatusCd(dataDict.getCodeVal("CreditContractStatus", "S1"));
			Long creditContractId = creditContractMngService.saveCreditContract(creditContract);
			model.addAttribute("creditContractId", creditContractId);
		} else {
			model.addAttribute("creditContractId", creditContract.getCreditContractId());
		}
		
		/** 授信合同VO组装，制定电子合同页面回显数据使用 start */
		CreditContractVo creditContractVO = new CreditContractVo();
		BeanUtils.copyProperties(creditContract, creditContractVO);
		BeanUtils.copyProperties(bizRate, creditContractVO);

		//年利率
		if(bizRate.getFinalRateValue() != null){
			creditContractVO.setFinalRateValue(
					MathUtil.BDmultiply(
						creditContractVO.getFinalRateValue(),
						new BigDecimal("100"), GlobalConstants.BIGDECIMAL_SCALE));
		}
		//利率浮动比例
		if(creditContractVO.getFinalFloatRate() != null){
			creditContractVO.setFinalFloatRate(
					MathUtil.BDmultiply(
						creditContractVO.getFinalFloatRate(),
						new BigDecimal("100"), GlobalConstants.BIGDECIMAL_SCALE));
		}
		//贷款产品
		String productName = businessApplicationService.findProductNameByProductCd(creditContractVO.getProductType());
		creditContractVO.setProductType(productName);
		
		String businessType = pa.getBusinessType();
		if (dataDict.getCodeVal("BusinessTypeCD", "S2").equals(businessType)) {
			creditContractVO.setCustomerType(dataDict.getCodeVal("CustomerType", "S1"));
		} else if (dataDict.getCodeVal("BusinessTypeCD", "S1").equals(businessType)) {
			creditContractVO.setCustomerType(dataDict.getCodeVal("CustomerType", "S2"));
		}
		creditContractVO.setContractTerm(creditContract.getContractTerm());
		creditContractVO.setContractTermUnit(dataDict.getCodeName("TermUnitCd",creditContract.getContractTermUnit()));
		//拼接担保方式，使用逗号隔开
		String[] guarantees = pa.getGuaranteeMode().split(",");
		if(guarantees != null && guarantees.length > 0){
			String guaranteeStr = "";
			for (String value : guarantees) {
				guaranteeStr += "," +dataDict.getCodeName("CdsGuarantMode", value);
			}
			creditContractVO.setGuaranteeMode(guaranteeStr.substring(1, guaranteeStr.length()));
		}
		creditContractVO.setPurpose(pa.getPurpose());
		//投放行业
		String investmentIndustry = contractMngService.findIndustryNameByIndustryCd(pa.getInvestmentIndustry());
		creditContractVO.setInvestmentIndustry(investmentIndustry);
		/** 授信合同VO组装，制定电子合同页面回显数据使用 end */
		
		//存储相关参数
		model.addAttribute("wfCode", wfCode);
		model.addAttribute("workflowId", workflowId);
		model.addAttribute("taskId", taskId);
		model.addAttribute("taskStageCode", taskStageCode);
		model.addAttribute("projectId", projectId);
		model.addAttribute("projectNo", pa.getProjectNo());
		model.addAttribute("partyId", partyId);
		model.addAttribute("partyType", party.getPartyTypeCd());
		model.addAttribute("productTypeCd", pa.getProductType());
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		model.addAttribute("allDocType", DocStageCode.HT_DOC.getCodeId());
		model.addAttribute("vo", creditContractVO);
		return GlobalConstants.MAKE_CREDIT_CONTRACT_WL + "/main";
	}
	
	/**
	 * 保存授信合同信息(同时存储授信从合同信息)
	 * 
	 * @param projectId 业务Id
	 * @return String 修改是否成功
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveCreditSubContract")
	@ResponseBody
	public Result saveCreditSubContract(@RequestParam Long projectId) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		SessionUser sessionUser = new SessionUser();
		sessionUser.setOrgId(shiroUser.getLogOrgid());
		sessionUser.setUserId(shiroUser.getId());
		//查询从合同信息
		List<SubContract> subContractList = contractMngService.findSubcontractList(projectId);
		if(CollectionUtils.isEmpty(subContractList)) {
			//根据抵质押信息生成抵质押从合同信息
			creditContractMngService.copyPawnInfoToSubContract(projectId, sessionUser);
			//根据保证人信息生成保证人从合同信息
			creditContractMngService.copyAssureInfoToSubContract(projectId, sessionUser);
		} else {
			//修改从合同表系统更新时间
			for (SubContract subcontract : subContractList) {
				subcontract.setSysUpdateDate(CommonHelper.getNow());
				creditContractMngService.saveSubContract(subcontract);
			}
		}
		//需确认
		/*重新计算担保金额：担保金额=抵质押之和 和保证金额(单个)做比较，取最大值
		BigDecimal guaranteeAmt = contractMngService.caculatePledgeSumByProjectId(project.getProjectId(), null, dataDict.getCodeVal("CtrlIndicator", "S2"));
		BigDecimal assuerAmt = new BigDecimal("0");
		if (CollectionUtils.isNotEmpty(assurerList)){
			ProjectAssurerInfo pai = (ProjectAssurerInfo) assurerList.get(0);
			if (pai != null && pai.getActualGuaranteeAmt() != null)
				assuerAmt = pai.getActualGuaranteeAmt();
		}
		if (guaranteeAmt != null && assuerAmt.compareTo(guaranteeAmt) > 0) {
			guaranteeAmt = assuerAmt;
		}
		project.setGuaranteeAmt(guaranteeAmt);*/
		return success("保存授信合同信息成功！");
	}
	
	/**
	 * 根据授信合同Id保存意见
	 * 
	 * @param ContractSaveVO  前台传来的表单对象
	 * @param creditContractId 授信合同Id
	 * @return String 修改是否成功
	 */
	@Deprecated
	@RequestMapping("/saveSuggest")
	@ResponseBody
	public String saveSuggest(@ModelAttribute ContractSaveVO contractSaveVO,
			@RequestParam("creditContractId") Long creditContractId) {
		CreditContract creditContract = creditContractMngService.getCreditContractById(creditContractId);
		
		creditContract.setFulfillInstructionCd(contractSaveVO.getFulfillInstructionCd());
		creditContractMngService.saveCreditContract(creditContract);
		return "success";
	}
	
	/**
	 * 主合同信息页面-保证人列表查询方法
	 * 
	 * @param request HttpServletRequest的对象
	 * @param sEcho  datatables的被请求次数
	 * @param firstIndex 起始页数
	 * @param pageSize 每页多少条记录
	 * @return page DataTablesPage对象的实例
	 */
	@RequestMapping("/searchBailList")
	@ResponseBody
	public DataTablesPage searchBailList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String projectId = (String) request.getParameter("projectId");
		Page queryList = creditContractMngService.findBailListBySearch(
				Long.parseLong(projectId), (firstIndex / pageSize), pageSize);
		return new DataTablesPage(sEcho, queryList);
	}
	
	/**
	 * 从合同页面-保证人从合同列表的查询方法
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
		String creditContractId = (String) request.getParameter("creditContractId");
		Page<ProjectAssurerInfoVo> queryList = creditContractMngService.findAssureSubcontractListBySearch(
				Long.parseLong(creditContractId), (firstIndex / pageSize), pageSize);
		return new DataTablesPage(sEcho, queryList);
	}
	
	/**
	 * 从合同页面-抵质押从合同列表的查询方法
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
		String creditContractId = (String) request.getParameter("creditContractId");
		Page<ProjectPawnInfoVo> queryList = creditContractMngService.findCollateralSubcontractListBySearch(
				Long.parseLong(creditContractId), (firstIndex / pageSize), pageSize);
		return new DataTablesPage(sEcho, queryList);
	}
	
	/**
	 * 共同借款人列表的查询方法
	 * 
	 * @param request  HttpServletRequest的对象
	 * @param sEcho  datatables的被请求次数
	 * @param firstIndex 起始页数
	 * @param pageSize  每页多少条记录
	 * @return page DataTablesPage对象的实例
	 */
	@RequestMapping("/searchBorrowerList")
	@ResponseBody
	public DataTablesPage searchBorrowerList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String projectId = (String) request.getParameter("projectId");
		Page<CommonBorrow> queryList = creditContractMngService.findBorrowerListBySearch(Long.parseLong(projectId),
				(firstIndex / pageSize), pageSize);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}
	
	/**
	 *  客户和业务文档列表的查询方法
	 * 
	 * @param sEcho datatables的被请求次数
	 * @param firstIndex 起始页数
	 * @param pageSize 每页多少条记录
	 * @param request HttpServletRequest的对象
	 * @return page DataTablesPage对象的实例
	 */
	@RequestMapping("/searchCustomerDocumentList")
	@ResponseBody
	public DataTablesPage searchCustomerDocumentList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("partyId");
		String projectId = (String) request.getParameter("projectId");
		String queryDocumentName = request.getParameter("query_documentName");
		String queryContentType = request.getParameter("query_contentType");
		
		//组装查询参数
		List<Object> params = Lists.newArrayList();
		params.add("('05','27','02','06','10','40','41','42','44','45','13','14','15','16','17','29')");
		params.add(projectId);
		params.add(partyId);
		params.add(queryDocumentName);
		
		List<String> contentType = null;
		//根据内容类型名称获取内容类型字典值
		if(StringUtils.isNotBlank(queryContentType)){
			contentType = documentService.findCustDocTypeNames(queryContentType);
		}
		params.add(contentType);
		Page<DocumentIndex> queryList = creditContractMngService.findCustomerDocumentListBySearch(params,
				(firstIndex / pageSize), pageSize);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}
	
	/**
	 * 检查合同是否具备提交的条件
	 * @param creditContractId 授信合同ID
	 * @param comments 意见
	 * @param instruction 合同条件落实情况说明
	 * @return 
	 */
	@RequestMapping("/checkContractReady")
	@ResponseBody
	public Result checkContractReady(
			@RequestParam("projectId") Long projectId,
			@RequestParam("partyId") Long partyId,
			@RequestParam("comments") String comments,
			@RequestParam("instruction") String instruction) {
		if(StringUtils.isBlank(instruction)) {
			return new Result(false, "请填写合同条件落实情况说明！", null);
		}
		if(StringUtils.isBlank(comments)) {
			return new Result(false, "请填写意见！", null);
		}
		//检查主合同文本是否上传
		boolean documentFlag = Boolean.FALSE;
		List<DocumentIndex> documents = contractMngService.findDocumentIndexByPartyId(partyId, projectId);
		if(documents != null && documents.size() > 0) {
			for (DocumentIndex documentIndex : documents) {
				if (dataDict.getCodeVal("DocumentType", "S27").equals(documentIndex.getDocumentType())) {
					documentFlag = Boolean.TRUE;
				}
			}
		}
		if(!documentFlag) {
			return new Result(false, "请先上传主合同文本！", null);
		}
		//检查从合同文本是否上传完全
		Result docStatus = creditContractMngService.checkSubContractDoc(projectId);
		if(!docStatus.getSuccess()) {
			return docStatus;
		}
		return new Result(true);
	}
	
	/**
	 * 提交授信合同，更改合同状态
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @param creditContractId 授信合同Id
	 * @param nextUser 下一环节执行人
	 * @param nextUserOrgId 下一环节执行人机构ID
	 * @param comments 意见
	 * @param instruction 落实意见
	 * @return String 修改是否成功
	 */
	@RequestMapping("/submitContract")
	@ResponseBody
	public Result submitContract(
			@RequestParam("wfCode") String wfCode,
			@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode") String taskStageCode,
			@RequestParam("creditContractId") Long creditContractId,
			@RequestParam(value = "nextUser", required = false) String nextUser,
			@RequestParam(value = "nextUserOrgId", required = false) String nextUserOrgId,
			@RequestParam("comments") String comments,
			@RequestParam("instruction") String instruction) {
		try {
			//提交到下一环节
			WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
			WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
			
			//组装参数信息
			BizApprovalOfWfParam bizApprovalOfWfParam = BizApprovalOfWfParam.newBizApprovalOfWfParam(workflowId, taskId, comments,
					this.curUserLogName(), this.curUserName(),
					nextUser, nextUserOrgId, workFlowCode, workFlowNode);
			
			//提交下一环节
			creditContractApprovalService.submitSignContract(bizApprovalOfWfParam);
			
			//更新授信合同信息
			CreditContract creditContract = creditContractMngService.getCreditContractById(creditContractId);
			creditContract.setSysUpdateDate(uniqueCustomerService.getDBTime());
			//creditContract.setContractStatusCd(dataDict.getCodeVal("CreditContractStatus", "S2"));
			creditContract.setFulfillInstructionCd(instruction);
			creditContractMngService.saveCreditContract(creditContract);
			return new Result(true, "提交下一环节成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("制定电子合同提交一下环节异常，error:{}", e);
			return new Result(false, "提交时发生异常，请联系管理员", null);
		}
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
}
