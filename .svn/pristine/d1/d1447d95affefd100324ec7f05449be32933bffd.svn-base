package com.coamctech.bxloan.web.controller.bizapply;

import static com.coamctech.bxloan.commons.GlobalConstants.BUSINESS_APPLICATION_WD;
import static com.coamctech.bxloan.commons.GlobalConstants.RISKMNG_FLAG;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.webservices.WebServices;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.LandProduce;
import com.coamctech.bxloan.entity.MachineEquipmentMortgage;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RealEstateMortgage;
import com.coamctech.bxloan.entity.RepayPlanTemp;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.entity.TrafficCar;
import com.coamctech.bxloan.entity.UnCustTab;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.collateral.CollateralService;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.customermng.UniteGuCustomerService;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.bizapply.NewCommonBorrowerVO;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.businessapplicationwd.CollateralAndDetailVO;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;

/**
 * 微贷产品业务申请
 * 
 * @author wf
 * 
 */
@Controller
@RequestMapping("/" + BUSINESS_APPLICATION_WD)
public class BusinessApplicationWdController extends BaseController {

	private final String CODETYPE_EMPLOYMENTTYPE = "EmploymentType";

	@Autowired
	private UniqueCustomerService uniqueCustomerService;

	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;

	@Autowired
	private BusinessApplicationService businessApplicationService;

	@Autowired
	private WebServices webServices;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private DataDict dataDict;

	@Autowired
	private CollateralService collateralService;

	@Autowired
	private CorporationCustomerService corporationCustomerService;
	
	@Autowired
	private UniteGuCustomerService uniteGuCustomerService;
	
	

	/**
	 * 保存基本信息
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/saveBusiness")
	@ResponseBody
	public Result saveBusiness(BusinessApplicationWdVO form) {
		try {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
					.getPrincipal();
			form.setCurUserId(curUser.getId());//当前登录人id
			//modify by HWL start 20150701 修改为登录机构
			form.setCurUserOrgId(curUser.getLogOrgid());
			//modify by HWL end 20150701 修改为登录机构
			form.setGuaranteeMode(form.getGuaranteeMode().substring(0,
					form.getGuaranteeMode().length() - 1));
			
			//this.saveUniteGugo(form,curUser);
			Long bizRateId = businessApplicationService
					.saveMicroloanBusiness(form);
			return success("保存成功！", bizRateId);
		} catch (Exception e) {
			logger.error("报错失败，错误原因："+e.getMessage());
			return failure(e.getMessage() != null ? e.getMessage() : "保存失败！");
		}
	}
	/**
	 * 将联保体成员添加至借款担保人列表
	 * @param form
	 * @param curUser
	 * @author wangxy 20150625
	 */
	@Deprecated
	public void saveUniteGugo(BusinessApplicationWdVO form,ShiroUser curUser){//TODO

		ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(Long.valueOf(form.getProjectId()));
		Party party = uniqueCustomerService.findPartyByPartyId(projectApplication.getPartyId().toString());
		//表单联保体id
		String unId = form.getUnId();
		//业务申请表联保体id
		String unIds = projectApplication.getUnId();
		//选择非联保体或切换联保体时清空担保人列表
		if(StringUtils.isNotEmpty(unIds)) {
			//获取联保体协议编号
			String unGuId = uniteGuCustomerService.findUniteCustomerById(Long.valueOf(unIds)).getUnGuId();
			List<UnCustTab> unList = uniteGuCustomerService.findUniteMembersByUnId(unGuId);
			List<ProjectAssurerInfo> projectAssurerInfo = businessApplicationService.getProjectAssurerInfoByProjectId(Long.valueOf(form.getProjectId()));
			//当担保人姓名与联保体成员姓名相同时删除该担保人
			for(UnCustTab un : unList) {
				for(ProjectAssurerInfo pa : projectAssurerInfo) {
					Long projectAssurerInfoId = pa.getProjectAssurerInfoId();
					if(StringUtils.isNotEmpty(pa.getCustomerName())) {
						if(pa.getCustomerName().equalsIgnoreCase(un.getCustomerName())) {
							businessApplicationService.deleteProjectAssurerInfo(projectAssurerInfoId);
						}
					}
				}
			}
		}
		//添加担保人
		if(StringUtils.isNotBlank(unId) ) {
			String unGuId = uniteGuCustomerService.findUniteCustomerById(Long.valueOf(unId)).getUnGuId();
			List<UnCustTab> unList = uniteGuCustomerService.findUniteMembersByUnId(unGuId);
			ProjectAssurerInfo projectAssurerInfo = null;
			for(UnCustTab unCustTab : unList){
				projectAssurerInfo= new ProjectAssurerInfo();
				BeanUtils.copyProperties(unCustTab, projectAssurerInfo);
				projectAssurerInfo.setPartyId(CommonHelper.toLong(unCustTab.getPartyId()));//联保体成员id
				projectAssurerInfo.setProjectId(Long.valueOf(form.getProjectId()));
				projectAssurerInfo.setProjectNo(projectApplication.getProjectNo());
				projectAssurerInfo.setGuaranteeAmt(projectApplication.getGuaranteeAmt());
				projectAssurerInfo.setCreateDate(new Date());
				projectAssurerInfo.setSysUpdateTime(new Date());
				projectAssurerInfo.setCreatePerson(curUser.getId().toString());
				projectAssurerInfo.setWifeGuarante(projectApplication.getMateBorrower());
				projectAssurerInfo.setAssureState("1");//担保状态
				projectAssurerInfo.setCurrency("156");//币种：156人民币
				businessApplicationService.saveUniteProjectAssurerInfo(projectAssurerInfo);
			}
		}
	}
	
		
	/**
	 * 检查申请金额是否满足批量额度
	 * @param projectId 项目id
	 * @param applyAmt 申请金额
	 * @return
	 */
	@RequestMapping("/checkApplyAmtIsAvailable")
	@ResponseBody
	public Result checkApplyAmtIsAvailable(String projectId, BigDecimal applyAmt){
		try {
			if(StringUtils.isEmpty(projectId)){
				return failure();
			}
			//项目实体
			ProjectApplication pa = businessApplicationService
					.searchProjectApplication(Long.valueOf(projectId));
			//检查申请金额是否满足批量额度
			businessApplicationService.checkApplyAmountIsAvailable(applyAmt,
					Long.valueOf(pa.getProductType()),
					Long.valueOf(pa.getApplyOrg()));
			return success();
		} catch (Exception e) {
			logger.info("检查申请金额是否满足批量额度",e.getMessage());
			return failure(e.getMessage());
		}
	}

	/**
	 * 查询费用列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchBizExpenseRateList")
	@ResponseBody
	public DataTablesPage searchBizExpenseRateList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request) {
		// 查询条件
		String projectNo = (String) request.getParameter("projectNo");
		List<Object> params = new ArrayList<Object>();
		params.add(projectNo);
		Page<Object[]> page = businessApplicationService
				.findBizExpenseRateByProjectNo2(projectNo, iDisplayStart
						/ iDisplayLength + 1, iDisplayLength, params);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/**
	 * 查询抵质押关联列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/searchProjectPawnInfoList")
	@ResponseBody
	public DataTablesPage searchProjectPawnInfoList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, Long projectId) {
		Page<Object[]> page = businessApplicationService
				.searchProjectPawnInfoList(iDisplayStart / iDisplayLength + 1,
						iDisplayLength, projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/**
	 * 提交申请
	 * 
	 * @param projectNo
	 * @param workflowId
	 * @param taskId
	 * @param opinion
	 * @param actionCode
	 * @param productCd
	 * @return
	 */
	@RequestMapping("/submitApply")
	@ResponseBody
	public Result submitApply(String projectNo, String workflowId,
			String taskId, String opinion, String actionCode, String productCd,
			String nextTaskReceiver,String projectId,String assistance) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();

		if (RISKMNG_FLAG) {
			String msg = "";
			boolean flag = true;
			/**
			 * 反欺诈接口
			 */
			String json = "";
			try {
				json = webServices.invoke("wdService", "antiFraudWebService",
						projectNo, new SimpleDateFormat("yyyy-MM-dd H:m:s")
								.format(new Date()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			if (StringUtils.isBlank(json)) {
				return failure("连接超时。");
			}
			Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
			Object checkResult = map.get("checkResult");
			Object code = map.get("code");
			Object feedbackTime = map.get("feedbackTime");
			if (code.equals("1")) {
				if (checkResult.equals("1")) {
					msg = "该客户不具备申请此业务的条件，系统已强制退回！。";
					flag = false;
				} else if (checkResult.equals("2")) {
					msg = "推荐审核。";
				} else if (checkResult.equals("3")) {
					msg = "通过。";
				} else {
					msg = "不适用。";
				}
			} else {
				msg = map.get("message").toString();
				flag = false;
			}
			if (!flag) {
				return failure(msg);
			}
		}

		/**
		 * 发送流程
		 */
		try {
			ActionCode actCode = ActionCode.getActionCodeById(actionCode);
			businessApplicationService.sendProccessAndUpdateApplication(
					WorkFlowService.WorkFlowCode.MICRO_LOAN, workflowId,
					taskId, curUser.getLogname(),
					WorkFlowNode.ML_EntryBusiApplInfo, actCode,
					nextTaskReceiver, opinion, "", GlobalConstants.WD_WF_TASK_ID_10);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			return failure(e.getMessage());
		}
	}

	/**
	 * 撤销流程
	 * 
	 * @param projectId
	 * @param workflowId
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/cancelApply")
	@ResponseBody
	public Result cancelApply(Long projectId, String workflowId, String taskId) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();

		return businessApplicationService.cancelApply(projectId, workflowId,
				taskId, curUser.getLogname(),
				WorkFlowService.WorkFlowCode.MICRO_LOAN,
				WorkFlowNode.ML_EntryBusiApplInfo);
	}

	/**
	 * 关联抵质押页面，新增抵质押时的方法，生成抵质押编号
	 * 
	 * @return
	 */
	@RequestMapping("/addCollateral")
	@ResponseBody
	public Result addCollateral() {
		try {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			//modify by wangyawei 20150702 start 将所属机构修改为登陆机构
			String guarantyNum = commonBizNumberBS.generateGuarantyNumber("DZY", curUser.getLogOrgid().toString());
			//modify by wangyawei 20150702 end
			Collateral collateral = new Collateral();
			collateral.setGuarantyNum(guarantyNum);
			collateral.setSysUpdateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			return success(collateral);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("生成抵质押物编号失败！请联系管理员！");
		}

	}

	/**
	 * 保存抵质押关联表
	 * 
	 * @param guarantyId
	 * @param appGuaDebtInterAmt
	 * @param pawnRatio
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/saveProjectPawnInfo")
	@ResponseBody
	public Result saveProjectPawnInfo(String guarantyId,
			String appGuaDebtInterAmt, String pawnRatio, Long projectId) {
		try {
			businessApplicationService.saveProjectPawnInfo(guarantyId,
					appGuaDebtInterAmt, pawnRatio, projectId);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
		return success("保存成功！");
	}

	/**
	 * 查询保证人列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/searchBailList")
	@ResponseBody
	public DataTablesPage searchBailList(Integer sEcho, Integer iDisplayStart,
			Integer iDisplayLength, Long projectId) {
		Page<Object[]> page = businessApplicationService.searchBailList(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/**
	 * 通过保证人ID得到此客户信息，目的：点击查看保证人信息时，判断此party的partyType，跳转至个人客户或企业客户查看页面
	 * 
	 * @param projectAssurerInfoId
	 * @return
	 */
	@RequestMapping("/getPartyByProjectAssurerInfoId")
	@ResponseBody
	public Party getPartyByProjectAssurerInfoId(Long projectAssurerInfoId) {
		return businessApplicationService
				.getPartyByProjectAssurerInfoId(projectAssurerInfoId);
	}

	/**
	 * 查询可以被关联为保证人的客户列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param customerName
	 * @param customerNum
	 * @param certificateTypeCd
	 * @param certificateNum
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/searchCustomerForBail")
	@ResponseBody
	public DataTablesPage searchCustomerForBail(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String customerName,
			String customerNum, String certificateTypeCd,
			String certificateNum, Integer partyId, Long projectId) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		Page<Object[]> page = businessApplicationService
				.searchCustomerForBailList(iDisplayStart / iDisplayLength + 1,
						iDisplayLength, curUser.getId().toString(), partyId,
						customerName, customerNum, certificateTypeCd,
						certificateNum, projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	@RequestMapping("/isMarried")
	@ResponseBody
	public Result isMarried(Long partyId) {
		try {
			businessApplicationService.isMarried(partyId);
			return success();
		} catch (Exception e) {
			return failure();
		}
	}

	/**
	 * 保存保证人表
	 * 
	 * @param projectId
	 * @param partyId
	 * @param guaranteeAmt
	 * @return
	 */
	@RequestMapping("/saveProjectAssurerInfo")
	@ResponseBody
	public Result saveProjectAssurerInfo(Long projectId, String partyId,
			String guaranteeAmt, String bailMateBorrower) {
		if (StringUtils.isBlank(bailMateBorrower)) {
			bailMateBorrower = dataDict.getCodeVal("CommonWhether", "S2");
		}
		try {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
					.getPrincipal();

			ProjectAssurerInfo projectAssurerInfo = new ProjectAssurerInfo();

			Party party = uniqueCustomerService.findPartyByPartyId(partyId);
			if (party.getPartyTypeCd().equals("1")) {// 企业客户
				CorporationCustomer corporationCustomer = corporationCustomerService
						.findById(Long.valueOf(partyId));
				BeanUtils.copyProperties(corporationCustomer,
						projectAssurerInfo);
				projectAssurerInfo.setCertificateTypeCd("210");
			} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
				Individual individual = uniqueCustomerService
						.getIndividual(partyId);
				BeanUtils.copyProperties(individual, projectAssurerInfo);
			}

			ProjectApplication projectApplication = businessApplicationService
					.searchProjectApplication(projectId);

			projectAssurerInfo.setProjectId(projectId);
			projectAssurerInfo.setProjectNo(projectApplication.getProjectNo());
			projectAssurerInfo.setGuaranteeAmt(new BigDecimal(guaranteeAmt));
			// projectAssurerInfo.setActualGuaranteeAmt(new BigDecimal(
			// guaranteeAmt));
			projectAssurerInfo.setCreateDate(new Date());
			projectAssurerInfo.setSysUpdateTime(new Date());
			projectAssurerInfo.setCreatePerson(curUser.getId().toString());
			projectAssurerInfo.setWifeGuarante(projectApplication
					.getMateBorrower());
			projectAssurerInfo.setAssureState("1");
			projectAssurerInfo.setCurrency("156");

			businessApplicationService.saveprojectAssurerInfo(
					projectAssurerInfo, bailMateBorrower);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "保存失败！";
			if (StringUtils.isNotBlank(e.getMessage())) {
				msg = e.getMessage();
			}
			return failure(msg);
		}
		return success("保存成功！");
	}

	/**
	 * 查询共同借款人列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/searchCommonBorrowerList")
	@ResponseBody
	public DataTablesPage searchCommonBorrowerList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, Long projectId) {
		Page<Object[]> page = businessApplicationService
				.searchCommonBorrowerList(iDisplayStart / iDisplayLength + 1,
						iDisplayLength, projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/**
	 * 通过共同借款人ID得到此客户，目的：点击查看保证人信息时，判断此party的partyType，跳转至个人客户或企业客户查看页面
	 * 
	 * @param commonBorrowerId
	 * @return
	 */
	@RequestMapping("/getPartyByCommonBorrowerId")
	@ResponseBody
	public Party getPartyByCommonBorrowerId(Long commonBorrowerId) {
		return businessApplicationService
				.getPartyByCommonBorrowerId(commonBorrowerId);
	}

	/**
	 * 通过共同借款人ID得到此联系人（查看保证人人（配偶）时用到）
	 * 
	 * @param commonBorrowerId
	 * @return
	 */
	@RequestMapping("/getSpouseByCommonBorrowerId")
	@ResponseBody
	public FamilyFriend getSpouseByCommonBorrowerId(Long commonBorrowerId) {
		return businessApplicationService
				.getSpouseByCommonBorrowerId(commonBorrowerId);
	}

	/**
	 * 查询可以被关联为共同借款人的客户列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param customerName
	 * @param customerNum
	 * @param certificateTypeCd
	 * @param certificateNum
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/searchCustomerForCommonBorrower")
	@ResponseBody
	public DataTablesPage searchCustomerForCommonBorrower(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String customerName,
			String customerNum, String certificateTypeCd,
			String certificateNum, Integer partyId, Long projectId) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		Page<Object[]> page = businessApplicationService
				.searchCustomerForCommonBorrower(iDisplayStart / iDisplayLength
						+ 1, iDisplayLength, curUser.getId().toString(),
						partyId, customerName, customerNum, certificateTypeCd,
						certificateNum, projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/**
	 * 保存共同借款人
	 * 
	 * @param projectId
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/saveCommonBorrower")
	@ResponseBody
	public Result saveCommonBorrower(Long projectId, String partyId) {
		try {
			CommonBorrow commonBorrow = new CommonBorrow();
			Party party = uniqueCustomerService.findPartyByPartyId(partyId);
			if (party.getPartyTypeCd().equals("1")) {// 企业客户
				CorporationCustomer corporationCustomer = corporationCustomerService
						.findById(Long.valueOf(partyId));
				BeanUtils.copyProperties(corporationCustomer, commonBorrow);
				commonBorrow.setCertificateTypeCd("210");
			} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
				Individual individual = uniqueCustomerService
						.getIndividual(partyId);
				BeanUtils.copyProperties(individual, commonBorrow);
				commonBorrow.setMobilePhone(individual.getMobileTel());
				commonBorrow.setPhone(individual.getFamilyTel());
				commonBorrow.setAddress(individual.getCompanyAddress());
			}
			commonBorrow.setProjectId(projectId);
			commonBorrow.setFlag("1");
			commonBorrow.setSpouseflag("2");
			businessApplicationService.saveCommonBorrow(commonBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
		return success("保存成功！");
	}

	/**
	 * 保存财务信息
	 * 
	 * @param salaBusiCustFinalcial
	 * @return
	 */
	@RequestMapping("/saveFinance")
	@ResponseBody
	public Result saveFinance(SalaBusiCustFinalcial salaBusiCustFinalcial) {
		try {
			salaBusiCustFinalcial.setCreateDtate(new Date());
			businessApplicationService.saveFinance(salaBusiCustFinalcial);
			return success("保存成功！", salaBusiCustFinalcial.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}

	/**
	 * 删除抵质押关联表信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePawn")
	@ResponseBody
	public Result deletePawn(Long id) {
		try {
			businessApplicationService.deleteProjectPawnInfo(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}

	/**
	 * 查看抵质押关联表，实际就是查看抵质押信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkPawn")
	@ResponseBody
	public CollateralAndDetailVO checkPawn(Long id) {
		ProjectPawnInfo projectPawnInfo = businessApplicationService
				.getProjectPawnInfo(id);
		Collateral collateral = collateralService.get(projectPawnInfo
				.getGuarantyId());
		collateral.setSysUpdateDateStr(new SimpleDateFormat("yyyy-MM-dd")
				.format(collateral.getSysUpdateDate()));
		CollateralAndDetailVO collateralAndDetailVO = new CollateralAndDetailVO();
		BeanUtils.copyProperties(collateral, collateralAndDetailVO);
		Integer guarantyTypeCd = Integer.parseInt(collateral
				.getGuarantyTypeCd());
		switch (guarantyTypeCd) {
		case 0:// 房产
			RealEstateMortgage realEstateMortgage = collateralService
					.getRealEstateMortgage(collateral.getGuarantyId());
			realEstateMortgage.setConstructedDateStr(new SimpleDateFormat(
					"yyyy-MM-dd").format(realEstateMortgage
					.getConstructedDate()));
			BeanUtils.copyProperties(realEstateMortgage, collateralAndDetailVO);
			break;
		case 1:// 地产
			LandProduce landProduce = collateralService
					.getLandProduce(collateral.getGuarantyId());
			landProduce.setLandEndDateStr(new SimpleDateFormat("yyyy-MM-dd")
					.format(landProduce.getLandEndDate()));
			BeanUtils.copyProperties(landProduce, collateralAndDetailVO);
			break;
		case 2:// 机器设备
			MachineEquipmentMortgage machineEquipmentMortgage = collateralService
					.getMachineEquipmentMortgage(collateral.getGuarantyId());
			BeanUtils.copyProperties(machineEquipmentMortgage,
					collateralAndDetailVO);
			break;
		case 3:// 机动车
			TrafficCar trafficCar = collateralService.getTrafficCar(collateral
					.getGuarantyId());
			trafficCar.setRegisterDateStr(new SimpleDateFormat("yyyy-MM-dd")
					.format(trafficCar.getRegisterDate()));
			BeanUtils.copyProperties(trafficCar, collateralAndDetailVO);
			break;
		default:
			break;
		}
		return collateralAndDetailVO;
	}

	/**
	 * 删除保证人信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAssurer")
	@ResponseBody
	public Result deleteAssurer(Long id) {
		try {
			businessApplicationService.deleteProjectAssurerInfo(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}

	/**
	 * 删除共同借款人信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCommonBorrow")
	@ResponseBody
	public Result deleteCommonBorrow(Long id) {
		try {
			businessApplicationService.deleteCommonBorrow(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}

	/**
	 * 查询还款计划列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/searchRepaymentPlanList")
	@ResponseBody
	public DataTablesPage searchRepaymentPlanList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String projectNo) {
		Page<Object[]> page = businessApplicationService.searchRepayPlanList(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, projectNo);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	
	@RequestMapping("/searchRepaymentPlanTempList")
	@ResponseBody
	public DataTablesPage searchRepaymentPlanTempList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String projectNo) {
		Page<Object[]> page = businessApplicationService.searchRepayPlanTempList(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, projectNo);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/**
	 * 查询贷款计算器的还款计划列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@RequestMapping("/searchCalRepaymentPlanList")
	@ResponseBody
	public DataTablesPage searchCalRepaymentPlanList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength) {
		Page<Object[]> page = businessApplicationService
				.searchCalRepaymentPlanList(iDisplayStart / iDisplayLength + 1,
						iDisplayLength);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/**
	 * 删除贷款计算器的还款计划列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @return
	 */
	@RequestMapping("/deleteCalRepaymentPlanList")
	@ResponseBody
	public Result deleteCalRepaymentPlanList() {
		try {
			businessApplicationService.deleteCalRepaymentPlan();
			return success();
		} catch (Exception e) {
			return failure();
		}
	}

	/**
	 * 保存还款计划
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/saveRepayPlan")
	@ResponseBody
	public Result saveRepayPlan(ApprovalHistoryRepayPlan form) {
		try {
			if (form.getCustomerId() != null) {
				Party party = uniqueCustomerService.findPartyByPartyId(form
						.getCustomerId().toString());
				form.setCustomerName(party.getPartyName());
				form.setCustomerNum(party.getCustomerNum());
			}
			if (form.getProjectId() == null) {
				form.setProjectId(Long.valueOf(0));
			}
			form.setRepayDate(new SimpleDateFormat("yyyy-MM-dd").parse(form
					.getRepayDateStr()));
			form.setSysCreateDate(new Date());
			form.setSysUpdateDate(new Date());
			businessApplicationService.saveRepayPlan(form);
			return success("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}
	@RequestMapping("/saveRepayPlanTemp")
	@ResponseBody
	public Result saveRepayPlanTemp(RepayPlanTemp form) {
		try {
			if (form.getCustomerId() != null) {
				Party party = uniqueCustomerService.findPartyByPartyId(form
						.getCustomerId().toString());
				form.setCustomerName(party.getPartyName());
				form.setCustomerNum(party.getCustomerNum());
			}
			if (form.getProjectId() == null) {
				form.setProjectId(Long.valueOf(0));
			}
			form.setRepayDate(new SimpleDateFormat("yyyy-MM-dd").parse(form
					.getRepayDateStr()));
			form.setSysCreateDate(new Date());
			form.setSysUpdateDate(new Date());
			businessApplicationService.saveRepayPlanTemp(form);
			return success("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}

	/**
	 * 批量初始化还款计划
	 * 
	 * @param projectId
	 * @param applyAmt
	 * @param applyTerm
	 * @param applyTermUnit
	 * @param startDate
	 * @param monthDate
	 * @param repayDate
	 * @return
	 */
	@RequestMapping("/batchInitRepayPlan")
	@ResponseBody
	public Result batchInitRepayPlan(Long projectId, BigDecimal applyAmt,
			Integer applyTerm, String applyTermUnit, String startDate,
			Integer monthDate, Integer repayDateForCount) {
		try {
			businessApplicationService.insertRepayPlanList(projectId, applyAmt,
					applyTerm, applyTermUnit,
					new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					monthDate, repayDateForCount);
			return success("批量初始化成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("批量初始化失败！");
		}
	}

	/**
	 * 获取还款计划
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getRepayPlan")
	@ResponseBody
	public ApprovalHistoryRepayPlan getRepayPlan(Long id) {
		ApprovalHistoryRepayPlan repayPlan = businessApplicationService.getRepayPlan(id);
		repayPlan.setRepayDateStr(new SimpleDateFormat("yyyy-MM-dd")
				.format(repayPlan.getRepayDate()));
		return repayPlan;
	}
	
	@RequestMapping("/getRepayPlanTemp")
	@ResponseBody
	public RepayPlanTemp getRepayPlanTemp(Long id) {
		RepayPlanTemp repayPlanTemp = businessApplicationService.getRepayPlanTemp(id);
		repayPlanTemp.setRepayDateStr(new SimpleDateFormat("yyyy-MM-dd").format(repayPlanTemp.getRepayDate()));
		return repayPlanTemp;
	}

	/**
	 * 单个删除还款计划
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRepayPlan")
	@ResponseBody
	public Result deleteRepayPlan(Long id) {
		try {
			businessApplicationService.deleteRepayPlan(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}
	@RequestMapping("/deleteRepayPlanTemp")
	@ResponseBody
	public Result deleteRepayPlanTemp(Long id) {
		try {
			businessApplicationService.deleteRepayPlanTemp(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}

	/**
	 * 通过projectNo批量删除还款计划
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRepayPlanByProjectNo")
	@ResponseBody
	public Result deleteRepayPlanByProjectNo(String projectNo) {
		try {
			businessApplicationService.deleteRepayPlanByProjectNo(projectNo);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			return failure();
		}
	}

	@RequestMapping("/findOneParty")
	@ResponseBody
	public Party findOneParty(String partyId) {
		return uniqueCustomerService.findPartyByPartyId(partyId);
	}

	/**
	 * 获取保险费用
	 * 
	 * @param insuranceOrgId
	 * @param applyAmt
	 * @param bizRate
	 * @param applyDate
	 * @param applyTerm
	 * @param applyTermUnit
	 * @param guaranteeMode
	 * @return
	 */
	@RequestMapping("/getPrePremium")
	@ResponseBody
	public BigDecimal getPrePremium(Long insuranceOrgId, BigDecimal applyAmt,
			BigDecimal bizRate, String applyDate, String applyTerm,
			String applyTermUnit, String guaranteeMode, String irTypeCd) {
		return businessApplicationService.countPremiumFee(insuranceOrgId,
				applyAmt, bizRate, applyDate, applyTerm, applyTermUnit,
				guaranteeMode, irTypeCd);
	}

	/**
	 * 微贷申请选择浮动利率时，通过利率上浮比计算利率
	 * 
	 * @param applyTerm
	 * @param applyTermUnit
	 * @param floatRate
	 * @return
	 */
	@RequestMapping("/countRateByFloatRate")
	@ResponseBody
	public BigDecimal countRateByFloatRate(Integer applyTerm,
			String applyTermUnit, BigDecimal floatRate) {
		return businessApplicationService.countRateByFloatRate(applyTerm,
				applyTermUnit, floatRate);
	}

	@RequestMapping("/checkBusinessInfoBeforeSendProcess")
	@ResponseBody
	public Result checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode) {
		try {
			businessApplicationService.checkBusinessInfoBeforeSendProcess(
					projectId, guaranteeMode,
					WorkFlowService.WorkFlowCode.MICRO_LOAN.getCodeId());
			return success();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.info(e.getMessage());
			return failure(e.getMessage());
		}
	}

	@RequestMapping("/saveNewCommonBorrower")
	@ResponseBody
	public Result saveNewCommonBorrower(NewCommonBorrowerVO newCommonBorrowerVO) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		CustomerManagerTeam customerManagerTeam = new CustomerManagerTeam();
		customerManagerTeam.setOrgCd(curUser.getOrgid().toString());
		customerManagerTeam.setCreateDate(new Timestamp(new Date().getTime()));
		customerManagerTeam.setUserNum(curUser.getId().toString());
		customerManagerTeam.setManagerType("01");
		
		//唯一性验证，2015-04-29 沈祚鑫
		String addCertificateNum = newCommonBorrowerVO.getCertificateNum();
		String addCertificateType = dataDict.getCodeVal("CertificateType","S100");
		if (uniqueCustomerService.findPartyByCertificateNumAndCertificateType(
				addCertificateType, addCertificateNum) != null) {
			return failure("身份证号码已被登记过！请重新输入！");
		}
		try {
			businessApplicationService.saveNewCommonBorrower(
					newCommonBorrowerVO, customerManagerTeam);
			return success("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}

	@RequestMapping("/findCollateralBySearch")
	@ResponseBody
	public DataTablesPage findCollateralBySearch(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request, String guarantorName,
			String guarantyName, String guarantyStatusCd, String guaranteeType,
			String guaranteeMode, Long projectId) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();

		Collateral collateral = new Collateral();
		collateral.setGuarantorName(guarantorName);
		collateral.setGuarantyName(guarantyName);
		collateral.setGuarantyStatusCd(guarantyStatusCd);
		collateral.setGuaranteeType(guaranteeType);
		//modify by HWL start 20150701 修改为登录机构
		Page<Object[]> page = collateralService.findBySearchForBiz(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, collateral,
				guaranteeMode, curUser.getLogOrgid(), projectId);
		//modify by HWL end 20150701 修改为登录机构
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	@RequestMapping("/findCustomersBySearchForCollateral")
	@ResponseBody
	public DataTablesPage findCustomersBySearchForCollateral(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request) {
		String customerName = request.getParameter("customerName");
		String certificateTypeCd = request.getParameter("certificateTypeCd");
		String certificateNum = request.getParameter("certificateNum");
		Individual individual = new Individual();
		individual.setCustomerName(customerName);
		individual.setCertificateTypeCd(certificateTypeCd);
		individual.setCertificateNum(certificateNum);
		Page<Object[]> page = collateralService.findCustomersBySearchForBiz(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, individual);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/*************************************************** 文档上传相关 **************************************************/
	/**
	 * 文档（DocumentIndex）的查询方法
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
	 * @lastModified lijing 2014-08-20 11:30:50
	 */
	@RequestMapping("/findDocuments")
	@ResponseBody
	public DataTablesPage findCustomerDocumentsBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("partyId");
		String projectId = (String) request.getParameter("projectId");
		String documentName = request.getParameter("query_documentName");
		String contentType = request.getParameter("query_contentType");
		List<Object> params = new ArrayList<Object>();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId);
		String customerName = "";
		if (party.getPartyTypeCd().equals("1"))// 企业客户
			customerName = corporationCustomerService.findById(
					Long.valueOf(partyId)).getCustomerName();
		else if (party.getPartyTypeCd().equals("2"))// 个人客户
			customerName = uniqueCustomerService.getIndividual(partyId)
					.getCustomerName();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		params.add(partyId);
		params.add(projectId);
		params.add(documentName);
		params.add(contentType);
		// params.add("('02','01','06')");
		List<Object[]> finalList = new ArrayList<Object[]>();

		Page queryList = businessApplicationService.findDocumentIndexBySearch(
				firstIndex / pageSize + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		for (Object[] obj : resultList) {
			obj[4] = customerName;
			// obj[5] = curUser.getName();
			obj[6] = timeStampToString((Timestamp) obj[6]);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 新增文档前的参数获取
	 * 
	 * @param partyId
	 *            客户Id
	 * @param request
	 *            HttpServletRequest
	 * @return DocumentUploadVO 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/beforeUpdate")
	@ResponseBody
	public DocumentUploadVO beforeUpdate(@RequestParam String partyId,
			@RequestParam String projectId, @RequestParam String uploadType,
			@RequestParam(required = false) String custDocTypeCd,
			HttpServletRequest request) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		documentUploadVO.setPartyId(partyId);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId);
		Individual individual = null;
		CorporationCustomer corporationCustomer = null;
		
		if (party.getPartyTypeCd().equals("1")) {// 企业
			corporationCustomer = corporationCustomerService.findById(Long.valueOf(partyId));
			documentUploadVO.setCustomerNum(corporationCustomer.getCustomerNum());
			documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(corporationCustomer.getCustomerNum(),"01"));
		} else if (party.getPartyTypeCd().equals("2")) {// 个人
			individual = uniqueCustomerService.getIndividual(partyId);
			documentUploadVO.setCustomerNum(individual.getCustomerNum());
			documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(individual.getCustomerNum(), "01"));
		}
		ProjectApplication project = businessApplicationService.searchProjectApplication(Long.valueOf(projectId));
		documentUploadVO.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		documentUploadVO.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		documentUploadVO.setCreateDateTime(timeStampToString(new Timestamp(new Date().getTime())));
		documentUploadVO.setBizNum(project.getProjectNo());
		documentUploadVO.setBizId(projectId);
		if (uploadType.equals("documents")) {
			documentUploadVO.setDocumentType("02");
		} else if (uploadType.equals("loanApply")) {
			documentUploadVO.setDocumentType("06");
		} else {
			documentUploadVO.setDocumentType("02");
		}
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");
		return documentUploadVO;
	}

	/**
	 * 查找下载地址与文件名
	 * 
	 * @param documentId
	 *            文档Id
	 * @return List 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-19 17:30:50
	 */
	@RequestMapping("/findDocumentPath")
	@ResponseBody
	public List<String> findDocumentPath(@RequestParam String documentId) {
		DocumentIndex documentIndex = uniqueCustomerService
				.findDocumentIndexByDocumentId(documentId);
		List<String> list = new ArrayList<String>();
		list.add(documentIndex.getDocumentRoute());
		list.add(documentIndex.getDocumentName());
		return list;
	}

	/**
	 * 根据文档Id删除文档
	 * 
	 * @param id
	 *            文档Id
	 * @return String 删除是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-19 9:30:50
	 */
	@RequestMapping("/delDocument/{id}")
	@ResponseBody
	public String delDocument(@PathVariable String id) {
		DocumentIndex documentIndex = uniqueCustomerService
				.findDocumentIndexByDocumentId(id);
		if (documentIndex.getCreateTypeCd().equals("2")) {
			return "createTypeCdEquals2Error";
		} else {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
					.getPrincipal();
			documentIndex.setSystemUpdateTime(new Timestamp(new Date()
					.getTime()));
			documentIndex.setUserNum(curUser.getId().toString());
			documentIndex.setStatus("2");
			uniqueCustomerService.saveDocumentIndex(documentIndex);
			return "success";
		}
	}

	/**
	 * 获取上传文档的种类
	 * 
	 * @param partyId
	 * @return Map<String,String>
	 * */
	@RequestMapping("/findUploadCustDocTypes")
	@ResponseBody
	public CustDocInfo findUploadCustDocTypes(@RequestParam Long partyId) {
		Party party = uniqueCustomerService.findPartyByPartyId(partyId
				.toString());
		String custDocTypeCd = "";
		if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S1"))) {
			custDocTypeCd = DocStageCode.QY_DOC.getCodeId();// 企业
		} else if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S2"))) {
			// 个人客户
			String employmentType = uniqueCustomerService
					.getEmploymentTypeByPartyId(partyId);
			if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
				custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); // 受薪
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))) {
				custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); // 农户
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
				custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); // 经营
			}else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S4"))) {
					custDocTypeCd = DocStageCode.STUDENT_DOC.getCodeId(); // 学生
			} 
		} else {
			custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
		}
		return documentService.findUploadCustDocTypes(null, partyId,
				custDocTypeCd + "," + DocStageCode.YW_WD_DOC.getCodeId());// 微贷
	}

	/**
	 * 获取已经上传文档的种类
	 * 
	 * @param partyId
	 * @return List<String>
	 * */
	@RequestMapping("/findDocumentCustDocTypes")
	@ResponseBody
	public List<Object[]> findDocumentCustDocTypes(@RequestParam Long partyId,
			@RequestParam Long projectId) {
		Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
		String custDocTypeCd = "";
		if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S1"))) {
			custDocTypeCd = DocStageCode.QY_DOC.getCodeId();// 企业
		} else if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S2"))) {
			// 个人客户
			String employmentType = uniqueCustomerService
					.getEmploymentTypeByPartyId(partyId);
			if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
				custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); // 受薪
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))) {
				custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); // 农户
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
				custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); // 经营
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S4"))) {
				custDocTypeCd = DocStageCode.STUDENT_DOC.getCodeId(); // 学生
			}
		} else {
			custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
		}
		return documentService.findDocumentCustDocTypes(projectId, partyId,
				custDocTypeCd + ","+DocStageCode.YW_WD_DOC.getCodeId());// 微贷
	}

	/**
	 * Timestamp转化成String的方法
	 * 
	 * @param st
	 *            Timestamp类型的变量
	 * @return String 返回时间转换成的字符串
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	public String timeStampToString(Timestamp st) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
		String str = "";
		if (st != null) {
			str = df.format(st);
		} else {
			str = df.format(new Date());
		}
		return str;
	}

	@RequestMapping("/downloadFile")
	public void downloadFile(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String filepath = request.getSession().getServletContext()
				.getRealPath("doc_templet/“邦微贷”贷款申请表.docx");
		String filename = "“邦微贷”贷款申请表.docx";
		String fullFileName = new String(filepath.getBytes("UTF-8"), "UTF-8");

		String downLoadName = null;
		String agent = request.getHeader("USER-AGENT");
		if (agent != null && agent.indexOf("Firefox") >= 0) {
			downLoadName = new String(filename.getBytes(), "iso-8859-1");
		} else {
			downLoadName = URLEncoder.encode(filename, "utf-8");
		}

		File file = new File(fullFileName);
		if (file.exists() && file.isFile()) {
			try {
				response.addHeader("Content-Disposition",
						"attachment;filename=" + downLoadName);// 设置文件名
				response.setContentType("application/octet-stream");
				FileUtils.copyFile(file, response.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("%%%%%%%%%%%%%%%%%%%%%%%文档：" + fullFileName
					+ " 不存在！");
			return;
		}
	}
	
}
