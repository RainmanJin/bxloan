package com.coamctech.bxloan.service.bizapply.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.ApprovalHistoryRepayPlanDao;
import com.coamctech.bxloan.dao.BizExpenseRateDao;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.CommonBorrowDao;
import com.coamctech.bxloan.dao.CorporationCustomerDao;
import com.coamctech.bxloan.dao.CustomerIndividualDao;
import com.coamctech.bxloan.dao.DocumentIndexDao;
import com.coamctech.bxloan.dao.FamilyFriendDao;
import com.coamctech.bxloan.dao.ProductConfigDao;
import com.coamctech.bxloan.dao.ProductDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.ProjectAssurerInfoDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.WfBusinessRelationDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.service.bizapply.BizCreditApplicationService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.exceptions.ServiceException;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowId;
import com.coamctech.bxloan.service.model.workflow.WorkFlowTaskIds;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.google.common.collect.Lists;

@Transactional
@Service
public class BizCreditApplicationServiceImpl implements BizCreditApplicationService{
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private FamilyFriendDao familyFriendDao;
	@Autowired
	private CommonBorrowDao commonBorrowDao;
	@Autowired
	private ProductConfigDao productConfigDao;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private ProjectAssurerInfoDao projectAssurerInfoDao;
	@Autowired
	private BizRateDao bizRateDao;
	@Autowired
	private BizExpenseRateDao bizExpenseRateDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private CorporationCustomerDao corporationCustomerDao;
	@Autowired
	private CustomerIndividualDao customerIndividualDao;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ApprovalHistoryRepayPlanDao approvalHistoryRepayPlanDao;
	@Autowired
	private DocumentIndexDao documentIndexDao;
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private WfBusinessRelationDao wfBusinessRelationDao;
	
	@Override
	public BusinessApplicationWdVO startMicroloanBusiness(Long productCd, Party party, 
			SessionUser sessionUser, WorkFlowCode workFlowCode, WorkFlowNode workFlowNode,
			String projectBusinessType) throws RuntimeException {
		//登录用户名
		String curUserLongName = sessionUser.getLoginName();
		//登录机构ID
		Long curUserOrgId = sessionUser.getOrgId();
		//登录机构描述，暂时使用机构名称字段
		String curUserOrgDesc = sessionUser.getOrgName();
		//获取贷款产品对象
		Product product = productDao.findOne(productCd);
		/** 组装任务主题 */
		String taskSubject = this.assembelTaskSubject(party, product, curUserOrgDesc);
		/** 启动流程  */
		TypedResult<WorkFlowTaskIds> workflowProcess = null;
		try {
			workflowProcess = workFlowService.startWorkflow(
					WorkFlowService.SysCode.WD_SYS,
					workFlowCode,
					workFlowNode, 
					curUserLongName,
					taskSubject,
					CommonHelper.long2StrIfEmpty(curUserOrgId));
			if (!workflowProcess.getSuccess()) {
				throw new RuntimeException("startProcess");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("startProcess");
		}
		try {
			/** 开始处理任务  */
			TypedResult<WorkFlowId> workflowId = workFlowService.startProcessingTask(
						workflowProcess.getData().getWorkflowId(), 
						workflowProcess.getData().getTaskId(), 
						curUserLongName);
			if (!workflowId.getSuccess()) {
				throw new RuntimeException("disposeTask");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException("disposeTask");
		}
		/** 组装业务申请对象 */
		ProjectApplication projectApplication = new ProjectApplication();
		projectApplication.setBpInfoId(Long.valueOf(workflowProcess.getData().getWorkflowId()));
		projectApplication.setWorkflowId(Long.valueOf(workflowProcess.getData().getWorkflowId()));
		//贷款产品
		projectApplication.setProductType(productCd.toString());
		//业务类型
		projectApplication.setProjectBusinessType(projectBusinessType);
		
		this.assembelApplication(party, projectApplication, sessionUser);

		/** 更新文档状态 */
		Set<String> set = new HashSet<String>();
		set.add("01");
		documentService.updateDocumentIndexByPartyId(party.getPartyId(), set, dataDict.getCodeVal("CreateType", "S2"));

		/** 组装vo */
		BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
		businessApplicationWdVO.setProjectId(projectApplication.getProjectId());
		businessApplicationWdVO.setWorkflowId(Long.valueOf(workflowProcess.getData().getWorkflowId()));
		businessApplicationWdVO.setTaskId(workflowProcess.getData().getTaskId());
		return businessApplicationWdVO;
	}
	
	@Override
	public String assembelTaskSubject(Party party, Product product, String curUserOrgDesc){
		CorporationCustomer corporationCustomer = null;
		Individual individual = null;
		String taskSubject = product.getProductName();
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerDao.findOne(party.getPartyId());
			taskSubject += "【" + curUserOrgDesc + "：" + corporationCustomer.getCustomerName() + "】";
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = customerIndividualDao.findByPartyId(party.getPartyId());
			taskSubject += "【" + curUserOrgDesc + "：" + individual.getCustomerName() + "】";
		}
		return taskSubject;
	}
	
	/** 
	 * 组装ProjectAppcation对象
	 *
	 * @param party 参与人对象
	 * @param pa 业务申请对象
	 * @param sessionUser 登录用户对象
	 */
	private void assembelApplication(Party party, ProjectApplication pa, SessionUser sessionUser) {
		pa.setCustomerManagerNum(sessionUser.getUserId().toString());
		pa.setCustomerManagerName(sessionUser.getUserNameCN());
		pa.setApplicant(sessionUser.getUserId().toString());
		pa.setApplyOrg(sessionUser.getOrgId().toString());
		pa.setSysCreateTime(new Date());
		pa.setSysUpdateTime(new Date());
		pa.setProjectStatus(dataDict.getCodeVal("ProjectStatus", "S0"));
		//系统标识
		pa.setSysCd("1");
		pa.setCustomerName(party.getPartyName());
		pa.setCustomerNum(party.getCustomerNum());
		pa.setPartyId(party.getPartyId());
		pa.setCertificateTypeCd(party.getCertificateTypeCd());
		pa.setCertificateNum(party.getCertificateNum());
		if (party.getPartyTypeCd().equals(dataDict.getCodeVal("CustomerType", "S1"))) {		// 企业客户
			pa.setBusinessType(dataDict.getCodeVal("CustomerType", "S2"));
			//业务编号：Y + 客户类型（1位字符） + 机构编码后四位（4位数字）+ 时间戳（6位数字yymmdd）+ 序列号（4位数字）
			pa.setProjectNo(commonBizNumberBS.generateBizEventNumber("Y0", sessionUser.getOrgId().toString()));
		} else if (party.getPartyTypeCd().equals(dataDict.getCodeVal("CustomerType", "S2"))) {// 个人客户
			pa.setBusinessType(dataDict.getCodeVal("CustomerType", "S1"));
			pa.setProjectNo(commonBizNumberBS.generateBizEventNumber("Y1", sessionUser.getOrgId().toString()));
		}
		//业务流程类型
		pa.setBusinessProcessType(dataDict.getCodeVal("BussinessProperty", "S1"));
		projectApplicationDao.save(pa);
		pa.setInitProjectId(pa.getProjectId());
	}
	
	@Override
	public void saveMicroloanBusiness(BusinessApplicationWdVO form){
		//页面传过来的配偶是否为共同借款人为空时，默认为否
		if (StringUtils.isBlank(form.getMateBorrower())) {  
			form.setMateBorrower(dataDict.getCodeVal("CommonWhether", "S2"));
		}
		
		//关联共同借款人-配偶
		this.relateCommonBorrow(form);
		
		//保存授信业务基本信息
		this.saveProjectApplication(form);

		//关联保证人表，修改保证人的本次申请保证债权金额
		this.updateProjectAssurerInfo(form);

		//bizrateid为空，表示为新增业务信息，此时需保存利率信息
		if (form.getBizRateId() == null) {// 新增
			this.saveBizRate(form);
		} else {//表示此业务信息已保存过，此时需修改利率信息
			this.updateBizRate(form);
		}
	}

	/**
	 * 关联共同借款人-配偶
	 * 
	 * @param form 前台封装的页面参数
	 */
	public void relateCommonBorrow(BusinessApplicationWdVO form){
		FamilyFriend spouse = familyFriendDao.getSpouseByPartyId(form.getPartyId()); //重要联系人
		String marriageCd = form.getMarriageCd();
		if (StringUtils.isNotBlank(marriageCd)// 防止错误数据
				&& marriageCd.equals(dataDict.getCodeVal("MaritalStatus", "S20"))// 此人已婚
				&& form.getMateBorrower().equals(dataDict.getCodeVal("CommonWhether", "S1"))) {// 选中配偶作为共同借款人
			if (spouse == null) {
				throw new RuntimeException("此客户无配偶信息！请确认客户信息后完成业务申请！");
			}
			CommonBorrow commonBorrow = commonBorrowDao.findByProjectIdAndPartyId(form.getProjectId(), spouse.getCorrelativeRelationsId());
			if (commonBorrow == null) {
				commonBorrow = new CommonBorrow();
				BeanUtils.copyProperties(spouse, commonBorrow);
				commonBorrow.setProjectId(form.getProjectId());
				commonBorrow.setCertificateNum(spouse.getCertificateCd());
				commonBorrow.setCustomerName(spouse.getName());
				commonBorrow.setMobilePhone(spouse.getMobileTel());
				commonBorrow.setPhone(spouse.getTelphone());
				commonBorrow.setPartyId(spouse.getCorrelativeRelationsId());// 此处partyId为此联系人的ID，关联时通过Spouseflag分辨
				commonBorrow.setFlag(dataDict.getCodeVal("CorrelativeCustomerTypeCd", "S1"));
				commonBorrow.setSpouseflag(dataDict.getCodeVal("CorrelativeCustomerTypeCd", "S1"));
				commonBorrowDao.save(commonBorrow);
			}
		} else if(dataDict.getCodeVal("CommonWhether", "S2").equals(form.getMateBorrower()) && spouse != null) {//选中配偶不作为共同借款人时，将共同借款人中配偶相应的信息删除
			commonBorrowDao.deleteByProjectIdAndPartyId(form.getProjectId(), spouse.getCorrelativeRelationsId());
		}
	}
	
	/**
	 * 保存授信业务基本信息
	 * 
	 * @param form 前台封装的页面参数
	 */
	public void saveProjectApplication(BusinessApplicationWdVO form){
		ProjectApplication pa = projectApplicationDao.findOne(form.getProjectId());
		BeanUtils.copyProperties(form, pa, "customerName", "customerNum");
		pa.setApproveAmt(form.getApplyAmt());  //授信额度
		pa.setApplyDate(CommonHelper.str2Date(form.getApplyDate(), CommonHelper.DF_DATE));
		pa.setSysUpdateTime(new Date());
		pa.setTerm(form.getApplyTerm());         //授信期限
		pa.setTermUnit(form.getApplyTermUnit()); //授信期限单位
		pa.setCreditType(form.getCreditType());  //授信类型
		pa.setBizRate(form.getBizRate().divide(new BigDecimal("100"))); //保存年利率(除以100)
		pa.setCurrency("156"); //币种(人民币)
		projectApplicationDao.save(pa);
	}
	 
	/**
	 * 关联保证人表，修改保证人的本次申请保证债权金额
	 * 
	 * @param form 前台封装的页面参数
	 */
	public void updateProjectAssurerInfo(BusinessApplicationWdVO form){
		ProjectApplication projectApplication = projectApplicationDao.findOne(form.getProjectId());
		List<ProjectAssurerInfo> projectAssurerInfos = projectAssurerInfoDao.findByProjectId(form.getProjectId());
		if (projectAssurerInfos != null && projectAssurerInfos.size() > 0) {
			for (ProjectAssurerInfo projectAssurerInfo : projectAssurerInfos) {
				projectAssurerInfo.setGuaranteeAmt(projectApplication.getApplyAmt());
				projectAssurerInfoDao.save(projectAssurerInfo);
			}
		}
	}
	
	/**
	 * 保存利率信息
	 * 
	 * @param form 前台封装的页面参数
	 */
	public void saveBizRate(BusinessApplicationWdVO form){
		BizRate bizRate = new BizRate();
		BeanUtils.copyProperties(form, bizRate);
		bizRate.setProjectId(form.getProjectId().toString());
		bizRate.setSysCreateDate(new Date());
		bizRate.setSysUpdateDate(new Date());
		bizRate.setRateValue(form.getBizRate().divide(new BigDecimal("100")));
		bizRate.setApproveRateValue(form.getBizRate().divide(new BigDecimal("100")));
		if (form.getIrNegoSymbCd() != null) {
			bizRate.setIrNegoSymbCd(form.getIrNegoSymbCd().divide(
					new BigDecimal("100")));
			bizRate.setApproveFloatRate(form.getIrNegoSymbCd().divide(
					new BigDecimal("100")));
		}
		bizRateDao.save(bizRate);
		ProjectApplication projectApplication = projectApplicationDao.findOne(form.getProjectId());
		//保存费用，此费用为产品产生的费用
		businessApplicationService.addBizExpenseRate(projectApplication);
	}
	
	/**
	 * 修改利率信息
	 * 
	 * @param form 前台封装的页面参数
	 */
	public void updateBizRate(BusinessApplicationWdVO form){
		BizRate bizRate = bizRateDao.findOne(form.getBizRateId());
		BeanUtils.copyProperties(form, bizRate, new String[] { "bizRateId" });
		bizRate.setSysUpdateDate(new Date());
		bizRate.setRateValue(form.getBizRate().divide(new BigDecimal("100")));
		bizRate.setApproveRateValue(form.getBizRate().divide(new BigDecimal("100")));
		if (form.getIrNegoSymbCd() != null) {
			bizRate.setIrNegoSymbCd(form.getIrNegoSymbCd().divide(
					new BigDecimal("100")));
			bizRate.setApproveFloatRate(form.getIrNegoSymbCd().divide(
					new BigDecimal("100")));
		}
		bizRateDao.save(bizRate);
		List<BizExpenseRate> list = bizExpenseRateDao.findByProjectId(form.getProjectId());
		// update业务申请表时修改费用表的标准收费、实际收费
		if (list != null && list.size() > 0) {
			for (BizExpenseRate bizExpenseRate : list) {
				if (bizExpenseRate.getExpenseRate() != null) {
					bizExpenseRate.setExpenseAmt(bizExpenseRate
							.getExpenseRate().multiply(form.getApplyAmt())
							.divide(new BigDecimal(100)));
					bizExpenseRateDao.save(bizExpenseRate);
				}
				if (bizExpenseRate.getStandardExpenseRate() != null) {
					bizExpenseRate.setStandaredAmt(bizExpenseRate
							.getStandardExpenseRate()
							.multiply(form.getApplyAmt())
							.divide(new BigDecimal(100)));
					bizExpenseRateDao.save(bizExpenseRate);
				}
			}
		}
	}

	@Override
	public void checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode, String workFlowCode) throws RuntimeException {
		// 检查基本信息是否已保存
		BizRate bizRate = bizRateDao.findByProjectId(projectId.toString());
		if (bizRate == null) {
			throw new RuntimeException("请保存基本项目信息。");
		}
		// 获取此业务所有文档类型
		List<String> cusDocTypes = documentIndexDao.findCustDocTypesByBizId(projectId);
		if (cusDocTypes == null || cusDocTypes.size() == 0) {
			throw new RuntimeException("请上传文档。");
		}
		// 检查抵质押保证表、保证人保证表信息是否已保存
		List<ProjectPawnInfo> projectPawnInfos = null;
		if (guaranteeMode.contains(dataDict.getUniqueOne("CdsGuarantMode", "S1").getCodeValue())) {// 抵押
			projectPawnInfos = projectPawnInfoDao.findOneGuaranteeTypeByProjectId(
					           projectId,dataDict.getUniqueOne("CdsGuarantMode", "S1").getCodeValue());
			if (projectPawnInfos == null || projectPawnInfos.size() == 0) {
				throw new RuntimeException("请关联抵押信息。");
			}
			if (!guaranteeMode.contains(dataDict.getUniqueOne("CdsGuarantMode", "S2").getCodeValue())) {
				projectPawnInfos = projectPawnInfoDao.findOneGuaranteeTypeByProjectId(
								   projectId, dataDict.getUniqueOne("CdsGuarantMode", "S2").getCodeValue());
				if (!CollectionUtils.isEmpty(projectPawnInfos)) {
					throw new RuntimeException("担保方式没有质押，请删除质押信息。");
				}
			}
		}
		if (guaranteeMode.contains(dataDict.getUniqueOne("CdsGuarantMode", "S2").getCodeValue())) {// 质押
			projectPawnInfos = projectPawnInfoDao.findOneGuaranteeTypeByProjectId(
							   projectId, dataDict.getUniqueOne("CdsGuarantMode", "S2").getCodeValue());
			if (projectPawnInfos == null || projectPawnInfos.size() == 0) {
				throw new RuntimeException("请关联质押信息。");
			}
			if (!guaranteeMode.contains(dataDict.getUniqueOne("CdsGuarantMode", "S1").getCodeValue())) {
				projectPawnInfos = projectPawnInfoDao.findOneGuaranteeTypeByProjectId(
								   projectId, dataDict.getUniqueOne("CdsGuarantMode", "S1").getCodeValue());
				if (!CollectionUtils.isEmpty(projectPawnInfos)) {
					throw new RuntimeException("担保方式没有抵押，请删除抵押信息。");
				}
			}
		}
		if (guaranteeMode.contains(dataDict.getUniqueOne("CdsGuarantMode", "S3").getCodeValue())) {// 保证
			List<ProjectAssurerInfo> projectAssurerInfos = projectAssurerInfoDao.findByProjectId(projectId);
			if (projectAssurerInfos == null || projectAssurerInfos.size() == 0) {
				throw new RuntimeException("请关联保证人信息。");
			}
		}
		// 检查贷款申请表、尽调报告是否上传
		if (!cusDocTypes.contains(dataDict.getCodeVal("CustProjectAllDocType", "S44"))) {
			throw new RuntimeException("请上传贷款申请表。");
		} else if (!cusDocTypes.contains(dataDict.getCodeVal("CustProjectAllDocType", "S45"))) {
			throw new RuntimeException("请上传尽调报告。");
		}
	}
}
