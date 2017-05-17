package com.coamctech.bxloan.service.bizapply.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.commons.utils.NumberUtil;
import com.coamctech.bxloan.dao.ApprovalHistoryRepayPlanDao;
import com.coamctech.bxloan.dao.BizExpenseRateDao;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.CommonBorrowDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.CorporationCustomerDao;
import com.coamctech.bxloan.dao.CustomerIndividualDao;
import com.coamctech.bxloan.dao.CustomerManagerTeamDao;
import com.coamctech.bxloan.dao.CustomerPartyDao;
import com.coamctech.bxloan.dao.DocumentIndexDao;
import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.dao.EcOrgRoleDao;
import com.coamctech.bxloan.dao.FamilyAssetsDetailDao;
import com.coamctech.bxloan.dao.FamilyFriendDao;
import com.coamctech.bxloan.dao.IndividualDao;
import com.coamctech.bxloan.dao.InfoValuesDao;
import com.coamctech.bxloan.dao.InsuranceCompanyDao;
import com.coamctech.bxloan.dao.InsuranceRebateDao;
import com.coamctech.bxloan.dao.MoneyRateDao;
import com.coamctech.bxloan.dao.MonthDominateIncludeMeasureDao;
import com.coamctech.bxloan.dao.PartyDao;
import com.coamctech.bxloan.dao.ProduceAreaInfoDao;
import com.coamctech.bxloan.dao.ProductConfigDao;
import com.coamctech.bxloan.dao.ProductDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.ProjectAssurerInfoDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.QuotaMeasureDao;
import com.coamctech.bxloan.dao.RepayPlanDao;
import com.coamctech.bxloan.dao.RepayPlanTempDao;
import com.coamctech.bxloan.dao.SalaBusiCustFinalcialDao;
import com.coamctech.bxloan.dao.TbMultiopinionDao;
import com.coamctech.bxloan.dao.VerificatPersonInfoDao;
import com.coamctech.bxloan.dao.WorkDraftDao;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.ChargeInfo;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.FamilyAssetsDetail;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.InfoValues;
import com.coamctech.bxloan.entity.InsuranceCompany;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.MonthDominateIncludeMeasure;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProductPrice;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.QuotaMeasure;
import com.coamctech.bxloan.entity.RepayPlan;
import com.coamctech.bxloan.entity.RepayPlanTemp;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.entity.TbMultiopinion;
import com.coamctech.bxloan.entity.UnCustTab;
import com.coamctech.bxloan.entity.WorkDraft;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.exceptions.ServiceException;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.customermng.UniteGuCustomerService;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.bizapply.NewCommonBorrowerVO;
import com.coamctech.bxloan.service.model.bizapply.QuotaMeasureVO;
import com.coamctech.bxloan.service.model.bizapply.WorkDraftVO;
import com.coamctech.bxloan.service.model.collateral.CollateralVO;
import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TaskAction;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowId;
import com.coamctech.bxloan.service.model.workflow.WorkFlowTaskIds;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @Description: 业务申请servie实现类
 * @author syy
 * @version V1.0
 * @Date 2014年7月25日
 */
@Transactional
@Service
public class BusinessApplicationServiceImpl implements
		BusinessApplicationService {
	protected Logger logger = LoggerFactory.getLogger(BusinessApplicationServiceImpl.class);

	@Autowired
	private ProjectApplicationDao projectApplicationDao;

	@Autowired
	private BizRateDao bizRateDao;

	@Autowired
	private BizExpenseRateDao bizExpenseRateDao;

	@Autowired
	private TbMultiopinionDao tbMultiopinionDao;

	@Autowired
	private WorkDraftDao workDraftDao;

	@Autowired
	private QuotaMeasureDao quotaMeasureDao;

	@Autowired
	private DynamicQuery dynamicQuery;

	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;

	@Autowired
	private MoneyRateDao moneyRateDao;

	@Autowired
	private DataDict dataDict;

	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;

	@Autowired
	private CommonBorrowDao commonBorrowDao;

	@Autowired
	private ProjectAssurerInfoDao projectAssurerInfoDao;

	@Autowired
	private SalaBusiCustFinalcialDao salaBusiCustFinalcialDao;

	@Autowired
	private CustomerPartyDao customerPartyDao;

	@Autowired
	private FamilyFriendDao familyFriendDao;

	@Autowired
	private RepayPlanDao repayPlanDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CorporationCustomerDao corporationCustomerDao;

	@Autowired
	private CustomerManagerTeamDao customerManagerTeamDao;

	@Autowired
	private CustomerIndividualDao customerIndividualDao;

	@Autowired
	private ProductConfigDao productConfigDao;

	@Autowired
	private CollateralDao collateralDao;

	@Autowired
	private InsuranceRebateDao insuranceRebateDao;

	@Autowired
	private InsuranceCompanyDao insuranceCompanyDao;

	@Autowired
	private PartyDao partyDao;

	@Autowired
	private MonthDominateIncludeMeasureDao monthDominateIncludeMeasureDao;

	@Autowired
	private DocumentIndexDao documentIndexDao;

	@Autowired
	private FamilyAssetsDetailDao familyAssetsDetailDao;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private IndividualDao individualDao;

	@Autowired
	private InfoValuesDao infoValuesDao;
	
	@Autowired
	private VerificatPersonInfoDao verificatPersonInfoDao;

	@Autowired
	private ApprovalHistoryRepayPlanDao approvalHistoryRepayPlanDao;

	@Autowired
	private EcOrgRoleDao ecOrgRoleDao;
	
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	@Autowired
	private ProduceAreaInfoDao produceAreaInfoDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private RepayPlanTempDao repayPlanTempDao;
	@Autowired
	EcOrgDepartmentDao ecOrgDepartmentDao;
	EcOrgPersonService ecOrgPersonService;
	
	//联保体
	@Autowired
	UniteGuCustomerService uniteGuCustomerService;
	
	
	/**
	 * 保存工作底稿
	 */
	public WorkDraft saveWorkDraft(WorkDraftVO form) {
		final BigDecimal YES = new BigDecimal("1");
		final BigDecimal NO = new BigDecimal("2");
		final BigDecimal ONE = new BigDecimal("1");
		final BigDecimal TWO = new BigDecimal("2");
		WorkDraft workDraft = new WorkDraft();
		BeanUtils.copyProperties(form, workDraft, new String[] {
				"creditComdition", "ageCondition", "workYear", "workerAccount",
				"employerAccount", "requirementCondition", "dataCondition" });
		// 诚信状况是否符合
		if (StringUtils.isNotBlank(form.getCreditComdition()))
			workDraft.setCreditComdition(YES);
		else
			workDraft.setCreditComdition(NO);
		// 年龄是否符合
		if (StringUtils.isNotBlank(form.getAgeCondition()))
			workDraft.setAgeCondition(YES);
		else
			workDraft.setAgeCondition(NO);
		// 工作或经营年限是否符合
		if (StringUtils.isNotBlank(form.getWorkYear()))
			workDraft.setWorkYear(YES);
		else
			workDraft.setWorkYear(NO);
		// 授薪人士银行账户
		if (StringUtils.isNotBlank(form.getWorkerAccount()))
			workDraft.setWorkerAccount(TWO);
		else
			workDraft.setWorkerAccount(ONE);
		// 自雇人士银行流水
		if (StringUtils.isNotBlank(form.getEmployerAccount()))
			workDraft.setEmployerAccount(TWO);
		else
			workDraft.setEmployerAccount(ONE);
		// 月收入负债比是否符合
		if (StringUtils.isNotBlank(form.getRequirementCondition()))
			workDraft.setRequirementCondition(YES);
		else
			workDraft.setRequirementCondition(NO);
		// 资料的完整性是否符合
		if (StringUtils.isNotBlank(form.getDataCondition()))
			workDraft.setDataCondition(YES);
		else
			workDraft.setDataCondition(NO);
		workDraftDao.save(workDraft);
		return workDraft;
	}

	/**
	 * 保存项目基本信息
	 */
	public void saveProjectApplication(ProjectApplication form) {
		projectApplicationDao.save(form);
	}

	/**
	 * 查找ProjectApplication详细信息
	 */
	public ProjectApplication searchProjectApplication(Long id) {
		ProjectApplication projectApplication = projectApplicationDao.findOne(id);
		return projectApplication;
	}

	/**
	 * 新增费用信息
	 */
	public void saveBizExpenseRate(BizExpenseRate form) {
		bizExpenseRateDao.save(form);
	}

	/**
	 * 修改保存费用信息
	 */
	public void updateBizExpenseRate(BizExpenseRate form) {
		BizExpenseRate ber = bizExpenseRateDao.findOne(form
				.getBizExpenseRateId());
		ber.setExpenseCollectionType(form.getExpenseCollectionType());
		ber.setExpenseName(form.getExpenseName());
		ber.setExpenseRate(form.getExpenseRate());
		ber.setExpenseAmt(form.getExpenseAmt());
		bizExpenseRateDao.save(ber);
	}

	/**
	 * 删除费用信息
	 */
	public void deleteBizExpenseRate(Long id) {
		bizExpenseRateDao.delete(id);

	}

	/**
	 * 查询费用列表
	 */
	public Page findBizExpenseRateByProjectNo(String projectNo, int pageNumber,
			Integer pageSize, List<Object> params) {
		String sql = "from BizExpenseRate b where b.projectNo = ?1";
		params.add(projectNo);
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				sql, params);
	}

	/**
	 * 提交意见
	 */
	public void submitOpinion(String opinion, Long opinionId, Long projectId) {
		ProjectApplication projectApplication = projectApplicationDao
				.findOne(projectId);
		projectApplication.setProjectStatus("2");
		saveProjectApplication(projectApplication);
		TbMultiopinion t = tbMultiopinionDao.findOne(opinionId);
		t.setOpinion(opinion);
		tbMultiopinionDao.save(t);
	}

	/**
	 * 查询工作底稿信息
	 */
	public WorkDraft searchWorkDraft(Long projectId) {
		return workDraftDao.findByProjectId(projectId);
	}

	/**
	 * 保存额度预算
	 */
	public QuotaMeasure saveQuotaMeasure(QuotaMeasureVO form) throws Exception {
		// 检查
		if (form.getControllableIncome().multiply(new BigDecimal("0.6"))
				.compareTo(form.getRepaymentThisTime()) == -1)
			throw new RuntimeException("认定的月可支配收入检查失败！");
		else if (form.getCalcLoanAmt().compareTo(form.getApplyAmt()) == -1)
			throw new RuntimeException("推荐额度检查失败！");
		else {
			QuotaMeasure quotaMeasure = new QuotaMeasure();
			BeanUtils.copyProperties(form, quotaMeasure);
			quotaMeasureDao.save(quotaMeasure);
			return quotaMeasure;
		}
	}

	/**
	 * 查询费用信息
	 * 
	 * @param orgId
	 *            机构id
	 * @param feeType
	 *            费用类型
	 * @param productId
	 *            产品id
	 * @param yearLimit
	 *            年限
	 * @return
	 */
	public List findChargeInfoListByFeeTypeOrgId(String orgId, String feeType,
			String productId, String yearLimit) {
		StringBuffer hqlsb = new StringBuffer("from ChargeInfo where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		int i = 1;
		if (StringUtils.isNotBlank(feeType)) {
			hqlsb.append(" and chargeType = ?" + i);
			i++;
			param.add(feeType);
		}
		if (StringUtils.isNotBlank(productId)) {
			hqlsb.append(" and productId = ?" + i);
			i++;
			param.add(productId);
		}
		if (StringUtils.isNotBlank(yearLimit)) {
			hqlsb.append(" and yearLimit = ?" + i);
			param.add(yearLimit);
		}
		if (StringUtils.isNotBlank(orgId)) {
			hqlsb.append(" and instr(','||orgId||',',',");
			hqlsb.append(orgId);
			hqlsb.append(",')>0 ");
		}
		return dynamicQuery.query(hqlsb.toString(), param.toArray());
	}

	/**
	 * 功能：组装费用列表 说明：
	 * 
	 */
	@Override
	public void addBizExpenseRate(ProjectApplication pa) {
		// 根据机构、贷款产品取相应的费用列表
		List list = this.findChargeInfoListByFeeTypeOrgId(pa.getApplyOrg(),
				null, pa.getProductType(), null);
		if (list.size() > 0) {
			ChargeInfo chargeInfo = null;
			BizExpenseRate bizExpenseRate = null;
			for (int i = 0; i < list.size(); i++) {
				chargeInfo = (ChargeInfo) list.get(i);
				bizExpenseRate = new BizExpenseRate();
				bizExpenseRate.setExpenseName(chargeInfo.getChargeType()); // 费用名称
				// 费用类型
				String chargeCompType = chargeInfo.getChargeCompType();
				if (dataDict.getUniqueOne("FeeUsedType", "S1").getCodeValue()
						.equals(chargeCompType)) { // 费用比例
					Double chargeRatio = chargeInfo.getChargeRatio();
					if (null != chargeRatio) { // 标准费率(%)
						bizExpenseRate.setStandardExpenseRate(new BigDecimal(
								chargeRatio));
						bizExpenseRate.setStandaredAmt(new BigDecimal(MathUtil
								.BDmultiply(Double.valueOf(chargeRatio),
										Double.valueOf(pa.getApplyAmt()
												.doubleValue()), 6))
								.divide(new BigDecimal("100")));
					}
				} else if (dataDict.getUniqueOne("FeeUsedType", "S2")
						.getCodeValue().equals(chargeCompType)) { // 费用金额
					bizExpenseRate.setStandaredAmt(new BigDecimal(chargeInfo
							.getChargeAmt()).divide(new BigDecimal("100")));
				}
				bizExpenseRate.setExpenseCollectionType(chargeInfo
						.getChargeCompType()); // 费用类型
				bizExpenseRate.setProjectId(pa.getProjectId());
				bizExpenseRate.setProjectNo(pa.getProjectNo());
				bizExpenseRate.setProductCd(pa.getProductType());
				bizExpenseRate.setSysCreateDate(Timestamp
						.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())));
				bizExpenseRate.setSysUpdateDate(Timestamp
						.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())));
				bizExpenseRateDao.save(bizExpenseRate);
			}
		}
	}

	@Override
	public boolean checkState(Long partyId, String projectStatus) {
		String hql = "from ProjectApplication p where p.partyId = ?1 and p.projectStatus in ("
				+ projectStatus + ")";
		List<Object> param = new ArrayList<Object>();
		param.add(partyId);
		List appls = dynamicQuery.query(hql.toString(), param.toArray());
		if (!CollectionUtils.isEmpty(appls)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean checkStatus(Long partyId) {
		List<ProjectApplication> inFlowApp = projectApplicationDao
				.findInFlowAppByPartyId(partyId);
		if (inFlowApp != null && inFlowApp.size() > 0)
			return false;
		return true;
	}

	@Override
	public boolean checkCreditAppStatus(Long partyId) {
		List<Object[]> inFlowCreditApp = projectApplicationDao
				.findInFlowCreditAppByPartyId(partyId);
		if (inFlowCreditApp != null && inFlowCreditApp.size() > 0)
			return false;
		return true;
	}

	@Override
	public BizExpenseRate getBizExpenseRateById(Long id) {
		return bizExpenseRateDao.findOne(id);
	}

	@Override
	public QuotaMeasure searchQuotaMeasure(Long projectId) {
		return quotaMeasureDao.findByProjectId(projectId);
	}

	@Override
	public List getBizExpenseRatesByProjectId(Long projectId) {
		String hql = "from BizExpenseRate b where b.projectId = ?1";
		List<Object> param = new ArrayList<Object>();
		param.add(projectId);
		return dynamicQuery.query(hql, param.toArray());
	}

	@Override
	public ProjectApplication getProjectApplicationByProjectNo(String projectNo) {
		return projectApplicationDao
				.getProjectApplicationByProjectNo(projectNo);
	}

	@Override
	public String findProductNameByProductCd(String productCd) {
		String hql = "select d.productName from Product d where d.productCd = ?1 ";
		List<Object> params = new ArrayList<Object>();
		params.add(Long.valueOf(productCd));
		return (String) dynamicQuery.query(hql, params).get(0);
	}

	@Override
	public MoneyRate findValidMoneyRate(String timeLimit, String termUnitCd) {
		if (StringUtils.isBlank(timeLimit) || "null".equals(timeLimit)
				|| StringUtils.isBlank(termUnitCd) || "null".equals(termUnitCd)) {
			try {
				throw new RuntimeException("期限值或者期限单位已丢失");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int limitMonth = 0;
		if (dataDict.getUniqueOne("TermUnitCd", "S1").getCodeValue()
				.equals(termUnitCd)) { // 年
			limitMonth = (Integer.valueOf(timeLimit).intValue()) * 12;
		} else if (dataDict.getUniqueOne("TermUnitCd", "S2").getCodeValue()
				.equals(termUnitCd)) { // 月
			limitMonth = Integer.valueOf(timeLimit).intValue();
		} else if (dataDict.getUniqueOne("TermUnitCd", "S3").getCodeValue()
				.equals(termUnitCd)) {
			limitMonth = Integer.valueOf(timeLimit) / 30;
		}

		if (limitMonth <= 6) { // 六个月以内(含6个月)

			timeLimit = dataDict.getUniqueOne("TimeLimitType", "S1")
					.getCodeValue();
		} else if (limitMonth > 6 && limitMonth <= 12) { // 六个月至一年(含一年)
			timeLimit = dataDict.getUniqueOne("TimeLimitType", "S2")
					.getCodeValue();
		} else if (limitMonth > 12 && limitMonth <= 36) { // 一至三年(含三年)
			timeLimit = dataDict.getUniqueOne("TimeLimitType", "S3")
					.getCodeValue();
		} else if (limitMonth > 36 && limitMonth <= 60) { // 三至五年(含五年)
			timeLimit = dataDict.getUniqueOne("TimeLimitType", "S4")
					.getCodeValue();
		} else if (limitMonth > 60) { // 五年以上
			timeLimit = dataDict.getUniqueOne("TimeLimitType", "S5")
					.getCodeValue();
		}
		String hql = "from MoneyRate p where p.valid='1' and p.timeLimit=?";
		List<MoneyRate> list = moneyRateDao.findListByTimeLimit(timeLimit);
		if (null != list && list.size() > 0) {
			MoneyRate moneyRate = list.get(0);
			return moneyRate;
		}
		return null;
	}

	@Override
	public void sendProccessAndUpdateApplication(WorkFlowCode workflowCode,
			String workflowId, String taskId, String logName,
			WorkFlowNode node, ActionCode actionCode, String taskReceiver,
			String comments, String taskSubject, String taskStageCode) throws Exception {
		/**
		 * 修改业务状态
		 */
		ProjectApplication p = projectApplicationDao.findProjectApplicationByWorkflowId(Long.valueOf(workflowId));
		if (!dataDict.getCodeVal("ProjectStatus", "S0").equals(p.getProjectStatus()))
			throw new RuntimeException("任务已提交，请勿重复提交任务！");
		p.setProjectStatus(dataDict.getCodeVal("ProjectStatus", "S1"));
		
		// 若不是自定义还款计划，清空还款计划表
		String repayingMode = p.getRepayingMode();
		if (!dataDict.getCodeVal("RepaymentMode", "S3").equals(repayingMode)) {
			approvalHistoryRepayPlanDao.deleteByProjectId(p.getProjectId());
			repayPlanDao.deleteByProjectId(p.getProjectId());
		}
		repayPlanTempDao.deleteByProjectNo(p.getProjectNo());
/**
		 * 更新文档
		 */
		Set<String> set = new HashSet<String>();
		set.add("02");
		set.add("06");
		set.add("45");
		documentService.updateDocumentIndexByProjectId(p.getProjectId(), set,
				dataDict.getCodeVal("CreateType", "S2"));

		// 切割taskReceiver，0为logName，1为orgId
		String receiverLogName = taskReceiver.split(",")[0];
		String receiverOrgId = taskReceiver.split(",")[1];
		
		// 流程跳转
		String actionCodeString = null;
		StringBuilder jql = new StringBuilder();
		jql.append("SELECT pa,pwc.ruleconfig ");
		jql.append("FROM ProjectApplication pa,ProductWfConfig pwc  ");
		jql.append("WHERE pa.workflowId=?1 AND pwc.pcId=( ");
		jql.append("  SELECT pcId  ");
		jql.append("  FROM ProductConfig pc ");
		jql.append("  WHERE cast(pc.productCd as string) = pa.productType ");
		jql.append("  AND  cast(pc.orgId as string) = pa.applyOrg ");
		jql.append(") ");
		jql.append("AND pwc.taskStageCode = ?2 ");

		List<Object[]> result = dynamicQuery.query(Object[].class,
				jql.toString(), Long.valueOf(workflowId), taskStageCode);
		if (result != null && result.size() > 0) {
			ProjectApplication pa = (ProjectApplication) result.get(0)[0];

			String rule = result.get(0)[1] == null ? "" : result.get(0)[1]
					.toString();
			if (StringUtils.isNotBlank(rule)) {
				ExpressionParser parser = new SpelExpressionParser();
				EvaluationContext context = new StandardEvaluationContext();
				context.setVariable("pa", pa);
				Expression exp = parser.parseExpression(rule);
				actionCodeString = exp.getValue(context, String.class);
				actionCode = ActionCode.getActionCodeById(actionCodeString);
			}
		}

		// 调用工作流
		workFlowService.executeTask(ExeTaskParam.newExeTaskParam(workflowCode,
				workflowId, taskId, logName, node, actionCode, receiverLogName,
				comments, "", receiverOrgId));

	}

	@Override
	public BizExpenseRate checkExpenseName(BizExpenseRate bizExpenseRate) {
		if (bizExpenseRate.getBizExpenseRateId() != null) {
			BizExpenseRate persistentBizExpenseRate = bizExpenseRateDao
					.findOne(bizExpenseRate.getBizExpenseRateId());
			if (bizExpenseRate.getExpenseName().equals(
					persistentBizExpenseRate.getExpenseName())) {
				return null;
			}
		}
		return bizExpenseRateDao.findByProjectIdAndExpenseName(
				bizExpenseRate.getProjectId(), bizExpenseRate.getExpenseName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize, String orgId, 
			String projectNo, String productType, String customerName,
			String applyDateStart, String applyDateEnd, String projectStatus,
			String applyAmtMin, String applyAmtMax, String customerManagerNum, Long logOrgId, Long personId, DataAuthorityType dataAuthType, List<Long> dataAuthOrgIds)
			throws ParseException {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(
				"select pa.project_no,p.product_name,eod.name,pa.customer_name,pa.apply_amt,");
		sql.append("to_char(pa.apply_date,'yyyy-MM-dd'),pa.customer_manager_name,pa.project_status,pa.project_id,pa.workflow_id ");
		sql.append("from EC_ORG_DEPARTMENT eod,PROJECT_APPLICATION pa left join PRODUCT p on pa.product_type = p.product_cd ");
		sql.append("where pa.sys_cd in (1,2) and pa.apply_org = eod.id ");
		
		int i = 0;
		
		if(dataAuthType == DataAuthorityType.HeadOffice) {
			sql.append(" and pa.apply_org in (?" + ++i).append(")");
			params.add(dataAuthOrgIds);
		} else if (dataAuthType == DataAuthorityType.LoanCompany) {
			sql.append(" and pa.apply_org = ?" + ++i);
			params.add(logOrgId);
		} else if (dataAuthType == DataAuthorityType.CustomerManager) {
			sql.append(" and pa.apply_org = ?" + ++i);
			params.add(logOrgId);
			sql.append(" and pa.customer_manager_num = ?" + ++i);
			params.add(String.valueOf(personId));
			
		}
		
		if (StringUtils.isNotBlank(orgId)) {
			sql.append(" and pa.apply_org = ?" + ++i);
			params.add(orgId);
		}
		if (StringUtils.isNotBlank(projectNo)) {
			sql.append(" and pa.project_no like ?" + ++i);
			params.add("%" + projectNo + "%");
		}
		if (StringUtils.isNotBlank(productType)) {
			sql.append(" and pa.product_type = ?" + ++i);
			params.add(productType);
		}
		if (StringUtils.isNotBlank(customerName)) {
			sql.append(" and pa.customer_name like ?" + ++i);
			params.add("%" + customerName + "%");
		}
		if (StringUtils.isNotBlank(applyDateStart)) {
			sql.append(" and pa.apply_date >= ?" + ++i);
			params.add(DateUtils.parseDate(applyDateStart, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(applyDateEnd)) {
			sql.append(" and pa.apply_date <= ?" + ++i);
			params.add(DateUtils.parseDate(applyDateEnd, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(projectStatus)) {
			sql.append(" and pa.project_status = ?" + ++i);
			params.add(projectStatus);
		}
		if (StringUtils.isNotBlank(applyAmtMin)) {
			sql.append(" and pa.apply_amt >= ?" + ++i);
			params.add(new BigDecimal(applyAmtMin));
		}
		if (StringUtils.isNotBlank(applyAmtMax)) {
			sql.append(" and pa.apply_amt <= ?" + ++i);
			params.add(new BigDecimal(applyAmtMax));
		}
		if (StringUtils.isNotBlank(customerManagerNum)) {
			sql.append(" and pa.customer_manager_num = ?" + ++i);
			params.add(customerManagerNum);
		}
		
		sql.append(" order by pa.sys_update_time desc");
		
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}
	
	/**
	 * 客户查询-->业务查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findList(Integer pageNumber, Integer pageSize, String partyId)
			throws ParseException {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(
				"select pa.project_no,p.product_name,pa.customer_name,pa.apply_amt,");
		sql.append("to_char(pa.apply_date,'yyyy-MM-dd'),pa.customer_manager_name,pa.project_status,pa.project_id,pa.workflow_id,pa.sys_cd ");
		sql.append("from PROJECT_APPLICATION pa left join PRODUCT p on pa.product_type = p.product_cd ");
		sql.append("where pa.sys_cd in (1,2) ");
		int i = 0;
		if (partyId != null) {
			sql.append(" and pa.party_id = ?" + ++i);
			params.add(String.valueOf(partyId));
		}
		sql.append(" order by pa.sys_update_time desc");
		
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findBizExpenseRateByProjectNo2(String projectNo,
			Integer pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer sql = new StringBuffer(
				"select c.code_name expense_collection_type,d.code_name expense_name,");
		sql.append("b.standard_expense_rate,b.expense_rate,b.standared_amt,b.expense_amt,to_char(b.sys_create_date,'yyyy-MM-dd'),");
		sql.append("b.biz_expense_rate_id from biz_expense_rate b ");
		sql.append("join code c on b.expense_collection_type=c.code_value and c.code_type='FeeUsedType' ");
		sql.append("join code d on b.expense_name=d.code_value and d.code_type='FeeType' where b.project_no=?1");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchProjectPawnInfoList(Integer pageNumber,
			Integer pageSize, Long projectId) {
		String sql = "select t.guaranty_name,t.guarantor_name,t.eval_value,t.currency,"
				+ "t.app_gua_debt_inter_amt,t.set_guarantee_amt,t.pawn_ratio pawn_ratio,"
				+ "d.code_name guaranty_type_cd,c.code_name pawn_state,t.project_pawn_info_id from PROJECT_PAWN_INFO t "
				+ "left join code c on c.code_value=t.pawn_state and c.code_type='PawnState' "
				+ "left join code d on d.code_value=t.guaranty_type_cd and d.code_type='ResvAssetTypeCode' "
				+ "where t.project_id = ?1";
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@Override
	public void saveProjectPawnInfo(String guarantyId,
			String appGuaDebtInterAmt, String pawnRatio, Long projectId) {
		BigDecimal appGuaDebtInterAmtB = new BigDecimal(appGuaDebtInterAmt);
		/*BigDecimal pawnRatioB = new BigDecimal(pawnRatio)
				.divide(new BigDecimal("100"));*/
		BigDecimal pawnRatioB = new BigDecimal(pawnRatio);
		/** 修改抵质押表状态 */
		Collateral collateral = collateralDao.findOne(Long.valueOf(guarantyId));
		collateral
				.setLockingAmount(collateral.getLockingAmount() == null ? appGuaDebtInterAmtB
						: collateral.getLockingAmount()
								.add(appGuaDebtInterAmtB));
		collateral.setGuarantyStatusCd(dataDict.getUniqueOne(
				"RevGuaranteeStatusCd", "S2").getCodeValue());

		/** save抵质押关联表 */
		ProjectPawnInfo projectPawnInfo = new ProjectPawnInfo();
		BeanUtils.copyProperties(collateral, projectPawnInfo);
		projectPawnInfo.setSetGuaranteeAmt(collateral.getSetGuarantyAmt());
		projectPawnInfo.setAppGuaDebtInterAmt(appGuaDebtInterAmtB);
		projectPawnInfo.setCreateDate(new Date());
		projectPawnInfo.setSysUpdateTime(new Date());
		projectPawnInfo.setProjectId(projectId);
		projectPawnInfo.setPawnRatio(pawnRatioB);
		projectPawnInfo.setCurrency("156");
		projectPawnInfo.setPawnState(dataDict.getUniqueOne("PawnState", "S2")
				.getCodeValue());
		projectPawnInfo.setActualCreditAmount(appGuaDebtInterAmtB);
		projectPawnInfo.setActualGuaranteeRate(pawnRatioB);
		projectPawnInfoDao.save(projectPawnInfo);
	}

	@Override
	public void deleteProjectPawnInfo(Long projectPawnInfoId) {
		try {
			//业务抵质押物中间表ID释放抵质押物
			bizApplyQueryService.releasePledgeWhenDeletePawnInfo(projectPawnInfoId);
			//从中间表删除业务申请中关联的抵押物
			projectPawnInfoDao.delete(projectPawnInfoId);
		} catch (Exception e) {
			logger.error("删除抵质押物出现异常", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchBailList(Integer pageNumber, Integer pageSize,
			Long projectId) {
		String sql = "select p.customer_name,c.code_name certificate_type_cd,p.certificate_num,'人民币' currency,"
				+ "p.guarantee_amt,p.project_assurer_info_id,p.ASSURER_SOURCE from PROJECT_ASSURER_INFO p "
				+ "join code c on c.code_value = p.certificate_type_cd and c.code_type = 'CertificateType'"
				+ "where p.project_id = ?1";
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchCustomerForBailList(Integer pageNumber,
			Integer pageSize, String userNum, Integer partyId,
			String customerName, String customerNum, String certificateTypeCd,
			String certificateNum, Long projectId) {

		List<ProjectAssurerInfo> projectAssurerInfos = projectAssurerInfoDao
				.findByProjectId(projectId);
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < projectAssurerInfos.size(); i++) {
			s.append(projectAssurerInfos.get(i).getPartyId());
			if (i != projectAssurerInfos.size() - 1)
				s.append(",");
		}

		StringBuffer sql = new StringBuffer(
				"select '',pp.party_Name,pp.customer_Num,cc.code_name,pp.certificate_Num,pp.status,pp.mark_type,"
						+ "pp.party_id from (select p.party_id,p.customer_Num,p.party_Type_Cd,p.party_Name,p.certificate_Num,"
						+ "p.certificate_Type_Cd,p.sys_update_time,case when p.party_Type_Cd = '1' then (select co.states "
						+ "from corporation_customer co where co.party_id = p.party_id) else (select indiv.status from "
						+ "individual indiv where indiv.party_id = p.party_id) end as status, case when p.party_Type_Cd = '1' "
						+ "then (select co.mark_type from corporation_customer co where co.party_id = p.party_id) else "
						+ "(select indiv.mark_type from individual indiv where indiv.party_id = p.party_id) end as mark_type "
						+ "from party p where 1 = 1 and exists (select 1 from customer_manager_team cmt where "
						+ "cmt.user_num = ?1 and cmt.party_id = p.party_id)) pp "
						+ "join code cc on cc.code_value=pp.certificate_Type_Cd and cc.code_type='CertificateType' "
						+ "where pp.status = '2' and pp.mark_type like '%02%' and pp.party_id <> ?2");
		if (StringUtils.isNotBlank(s))
			sql.append(" and pp.party_id not in (" + s + ")");

		List<Object> params = new ArrayList<Object>();
		params.add(userNum);
		params.add(partyId);
		int i = 2;
		if (StringUtils.isNotBlank(customerName)) {
			sql.append(" and pp.party_Name like ?" + ++i);
			params.add("%" + customerName + "%");
		}
		if (StringUtils.isNotBlank(customerNum)) {
			sql.append(" and pp.customer_Num like ?" + ++i);
			params.add("%" + customerNum + "%");
		}
		if (StringUtils.isNotBlank(certificateTypeCd)) {
			sql.append(" and pp.certificate_Type_Cd = ?" + ++i);
			params.add(certificateTypeCd);
		}
		if (StringUtils.isNotBlank(certificateNum)) {
			sql.append(" and pp.certificate_Num like ?" + ++i);
			params.add("%" + certificateNum + "%");
		}
		sql.append(" order by pp.sys_update_time desc, pp.party_id desc");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchCommonBorrowerList(Integer pageNumber,
			Integer pageSize, Long projectId) {
		String sql = "select cb.customer_name,c.code_name certificate_type_cd,cb.certificate_num,cb.mobile_phone,"
				+ "cb.phone,cb.address,cb.common_borrow_id,cb.spouseflag from COMMON_BORROW cb "
				+ "left join code c on c.code_value=cb.certificate_type_cd and c.code_type='CertificateType' "
				+ "where cb.project_id = ?1 and cb.flag = '1'";
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchCustomerForCommonBorrower(Integer pageNumber,
			Integer pageSize, String userNum, Integer partyId,
			String customerName, String customerNum, String certificateTypeCd,
			String certificateNum, Long projectId) {

		List<CommonBorrow> commonBorrows = commonBorrowDao
				.findByProjectId(projectId);
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < commonBorrows.size(); i++) {
			s.append(commonBorrows.get(i).getPartyId());
			if (i != commonBorrows.size() - 1)
				s.append(",");
		}

		StringBuffer sql = new StringBuffer(
				"select '',pp.party_Name,pp.customer_Num,cc.code_name,pp.certificate_Num,pp.status,pp.mark_type,"
						+ "pp.party_id from (select p.party_id,p.customer_Num,p.party_Type_Cd,p.party_Name,p.certificate_Num,"
						+ "p.certificate_Type_Cd,p.sys_update_time,case when p.party_Type_Cd = '1' then (select co.states "
						+ "from corporation_customer co where co.party_id = p.party_id) else (select indiv.status from "
						+ "individual indiv where indiv.party_id = p.party_id) end as status,case when p.party_Type_Cd = '1' then "
						+ "(select co.mark_type from corporation_customer co where co.party_id = p.party_id) else (select "
						+ "indiv.mark_type from individual indiv where indiv.party_id = p.party_id) end as mark_type from party p "
						+ "where 1 = 1 and p.party_id != ?1 and exists (select 1 from customer_manager_team cmt where "
						+ "cmt.user_num = ?2 and cmt.party_id = p.party_id)) pp join code cc on "
						+ "cc.code_value = pp.certificate_Type_Cd and cc.code_type = 'CertificateType' where pp.status = '2' "
						+ "and pp.mark_type like '%01%'");
		if (StringUtils.isNotBlank(s))
			sql.append(" and pp.party_id not in (" + s + ")");

		List<Object> params = new ArrayList<Object>();
		params.add(partyId);
		params.add(userNum);
		int i = 2;
		if (StringUtils.isNotBlank(customerName)) {
			sql.append(" and pp.party_Name like ?" + ++i);
			params.add("%" + customerName + "%");
		}
		if (StringUtils.isNotBlank(customerNum)) {
			sql.append(" and pp.customer_Num like ?" + ++i);
			params.add("%" + customerNum + "%");
		}
		if (StringUtils.isNotBlank(certificateTypeCd)) {
			sql.append(" and pp.certificate_Type_Cd = ?" + ++i);
			params.add(certificateTypeCd);
		}
		if (StringUtils.isNotBlank(certificateNum)) {
			sql.append(" and pp.certificate_Num like ?" + ++i);
			params.add("%" + certificateNum + "%");
		}
		sql.append(" order by pp.sys_update_time desc");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@Override
	public void saveCommonBorrow(CommonBorrow commonBorrow) {
		commonBorrowDao.save(commonBorrow);
	}

	@Override
	public void saveprojectAssurerInfo(ProjectAssurerInfo projectAssurerInfo,
			String bailMateBorrower) {
		/**
		 * 关联保证人-配偶
		 */
		if (dataDict.getCodeVal("CommonWhether", "S1").equals(bailMateBorrower)) {
			FamilyFriend spouse = familyFriendDao.getSpouseByPartyId(projectAssurerInfo.getPartyId());
			if (spouse == null) {
				throw new RuntimeException("此客户无配偶信息！请确认客户信息后完成保证人关联！");
			}
			List<Individual> individuals = individualDao.findByCertificateTypeCdAndCertificateNum(spouse.getCertificateTypeCd(), spouse.getCertificateCd());
			if (CollectionUtils.isEmpty(individuals)) {
				throw new RuntimeException("此客户配偶不是有效客户！请确认客户信息后完成保证人关联！");
			} else if (individuals.size() > 1) {
				throw new RuntimeException("数据异常，请联系管理员！");
			} else if (StringUtils.isNoneBlank(individuals.get(0).getMarkType()) && !Arrays.asList(individuals.get(0).getMarkType().split(",")).contains(dataDict.getCodeVal("CustomerMarkType", "S02"))) {
				throw new RuntimeException("此客户配偶不是担保人！请确认客户信息后完成保证人关联！");
			} else if (StringUtils.isNoneBlank(individuals.get(0).getStatus()) && !individuals.get(0).getStatus().equals(dataDict.getCodeVal("CustomerStatusCode", "S2"))) {
				throw new RuntimeException("此客户配偶未生效！请确认客户信息后完成保证人关联！");
			}
			
			ProjectAssurerInfo proAssSpouse = new ProjectAssurerInfo();
			BeanUtils.copyProperties(projectAssurerInfo, proAssSpouse, "customerNum", "customerName", "certificateTypeCd", "certificateNum");
			BeanUtils.copyProperties(individuals.get(0), proAssSpouse);
			projectAssurerInfoDao.save(proAssSpouse);
		}
		projectAssurerInfoDao.save(projectAssurerInfo);
	}
	
	@Override
	public void saveUniteProjectAssurerInfo(ProjectAssurerInfo projectAssurerInfo) {

		projectAssurerInfoDao.save(projectAssurerInfo);
	}

	@Override
	public void saveFinance(SalaBusiCustFinalcial salaBusiCustFinalcial) {
		salaBusiCustFinalcialDao.save(salaBusiCustFinalcial);
	}

	@Override
	public SalaBusiCustFinalcial getFinance(Long id) {
		return salaBusiCustFinalcialDao.findOne(id);
	}

	@Override
	public SalaBusiCustFinalcial getFinanceByProjectId(String projectId) {
		return salaBusiCustFinalcialDao
				.findByProjectId(Long.valueOf(projectId));
	}

	@Override
	public void deleteProjectAssurerInfo(Long projectAssurerInfoId) {
		//ProjectAssurerInfo projectAssurerInfo = projectAssurerInfoDao.findOne(projectAssurerInfoId);
		//FamilyFriend spouse = familyFriendDao.getSpouseByPartyId(projectAssurerInfo.getPartyId());
		//commonBorrowDao.deleteByProjectIdAndPartyId(projectAssurerInfo.getProjectId(), spouse.getCorrelativeRelationsId());
		projectAssurerInfoDao.delete(projectAssurerInfoId);
	}

	@Override
	public void deleteCommonBorrow(Long commonBorrowId) {
		commonBorrowDao.delete(commonBorrowId);
	}

	@Override
	public List<ProjectPawnInfo> getProjectPawnInfoByGuarantyId(Long guarantyId) {
		return projectPawnInfoDao.findByGuarantyId(guarantyId);
	}

	@Override
	public ProjectPawnInfo getProjectPawnInfo(Long id) {
		return projectPawnInfoDao.findOne(id);
	}

	@Override
	public Party getPartyByProjectAssurerInfoId(Long projectAssurerInfoId) {
		Long partyId = projectAssurerInfoDao.findOne(projectAssurerInfoId)
				.getPartyId();
		return customerPartyDao.findByPartyId(partyId);
	}

	@Override
	public Party getPartyByCommonBorrowerId(Long commonBorrowerId) {
		Long partyId = commonBorrowDao.findOne(commonBorrowerId).getPartyId();
		return customerPartyDao.findByPartyId(partyId);
	}

	@Override
	public List<ProjectAssurerInfo> getProjectAssurerInfoByProjectId(
			Long projectId) {
		return projectAssurerInfoDao.findByProjectId(projectId);
	}

	@Override
	public List<Object[]> findRepayingModesByProductPrice(
			ProductPrice productPrice) {
		String sql = "select c.code_value,c.code_name from CODE c where c.code_type='RepaymentMode' and c.code_value in ("
				+ productPrice.getRepaymentType().substring(0,
						productPrice.getRepaymentType().length() - 1) + ")";
		return dynamicQuery.nativeQuery(sql, new ArrayList<Object>().toArray());
	}

	@Override
	public List<FamilyFriend> findFamilyFriendsByPartyId(Long partyId) {
		return familyFriendDao.findByPartyId(partyId);
	}

	@Override
	public FamilyFriend getSpouseByPartyId(Long partyId) {
		return familyFriendDao.getSpouseByPartyId(partyId);
	}

	@Override
	public FamilyFriend getSpouseByCommonBorrowerId(Long commonBorrowerId) {
		CommonBorrow commonBorrow = commonBorrowDao.findOne(commonBorrowerId);
		return familyFriendDao.findOne(commonBorrow.getPartyId());
	}

	@Override
	public void insertRepayPlanList(Long projectId, BigDecimal applyAmt,
			Integer applyTerm, String applyTermUnit, Date startDate,
			Integer monthDate, Integer repayDate) throws Exception {
		try {
			ProjectApplication pa = projectApplicationDao
					.findProjectApplicationByProjectId(projectId);
			String jpql = "from RepayPlan where projectNo = ?1";
			List<Object> params = new ArrayList<Object>();
			params.add(pa.getProjectNo());
			List list = dynamicQuery.query(jpql, params);
			/*if (list != null && list.size() > 0) {
				throw new Exception("1");
			}*/
			RepayPlan repayPlan = null;
			Date endDate = DateTools.getEndingDate(startDate, applyTermUnit,
					applyTerm);
			Date systemDate = DateTools.stringToDate(
					DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			int cnt = 0;
			Integer repaymentDate = null;
			while (true) {
				repayPlan = new RepayPlan();
				repayPlan.setCustomerId(pa.getPartyId());
				repayPlan.setCustomerName(pa.getCustomerName());
				repayPlan.setCustomerNum(pa.getCustomerNum());
				repayPlan.setProjectNo(pa.getProjectNo());
				repayPlan.setProjectId(pa.getProjectId());
				repayPlan.setSysCreateDate(systemDate);
				repayPlan.setSysUpdateDate(systemDate);
				calendar.setTime(startDate);
				repaymentDate = repayDate;
				cnt = DateTools.getActualMaximum(calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH) + 1 + monthDate);
				if (cnt < repayDate) {
					repaymentDate = cnt;
				}
				startDate = this.getDate(calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH) + 1 + monthDate,
						repaymentDate);
				// 当月只有一次还款
				if (DateTools.getDateDiff(endDate, startDate) >= 0
						|| DateTools.dateToString(startDate, "yyyy-MM").equals(
								DateTools.dateToString(endDate, "yyyy-MM"))) {
					startDate = endDate;
					repayPlan.setRepayAmt(applyAmt);
				} else {
					repayPlan.setRepayAmt(BigDecimal.ZERO);
				}
				repayPlan.setRepayDate(startDate);

				/**
				 * 向还款计划历史表中插入数据
				 */
				ApprovalHistoryRepayPlan approvalHistoryRepayPlan = new ApprovalHistoryRepayPlan();
				BeanUtils.copyProperties(repayPlan, approvalHistoryRepayPlan);
				approvalHistoryRepayPlan.setApprovalHistoryId(0L);

				ApprovalHistoryRepayPlan approvalHistoryRepayPlanForUpdate = new ApprovalHistoryRepayPlan();
				BeanUtils.copyProperties(repayPlan, approvalHistoryRepayPlanForUpdate);
				
				RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
				BeanUtils.copyProperties(repayPlan, repayPlanTemp);

				approvalHistoryRepayPlanDao.save(approvalHistoryRepayPlan); 
				approvalHistoryRepayPlanDao.save(approvalHistoryRepayPlanForUpdate);
				repayPlanDao.save(repayPlan);
				repayPlanTempDao.save(repayPlanTemp);
				
				if (DateTools.getDateDiff(endDate, startDate) >= 0) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if ("1".equals(e.getMessage())) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception("还款计划初始化出错,请联系管理员!");
			}
		}
	}

	/**
	 * 字串转成时间
	 * 
	 * @param year年
	 * @param month月
	 * @param day日
	 * @return
	 */
	private Date getDate(int year, int month, int day) {
		StringBuffer sb = new StringBuffer();
		sb.append(year).append("-");
		if (month < 10 && month > 0) {
			sb.append(0).append(month);
		} else {
			sb.append(month);
		}
		sb.append("-");
		if (day < 10 && day > 0) {
			sb.append(0).append(day);
		} else {
			sb.append(day);
		}
		return DateTools.stringToDate(sb.toString(), "yyyy-MM-dd");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchRepayPlanList(Integer pageNumber,
			Integer pageSize, String projectNo) {
		String sql = "select to_char(r.repay_date,'yyyy-mm-dd'),r.repay_amt,r.approval_history_repay_plan_id from "
				+ "APPROVAL_HISTORY_REPAY_PLAN r where r.project_no = ?1 and r.approval_history_id = 0 order by r.repay_date asc";
		List<Object> params = new ArrayList<Object>();
		params.add(projectNo);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@Override
	public void saveRepayPlan(ApprovalHistoryRepayPlan form) {
		form.setApprovalHistoryId(0L);
		approvalHistoryRepayPlanDao.save(form);
		Long projectId = form.getProjectId();
		Long approvalHistoryId = form.getApprovalHistoryId();
		
		String delRepayPlanSql = "DELETE FROM REPAY_PLAN RP WHERE RP.PROJECT_ID = " + form.getProjectId();
		String delApprovalHistoryRepayPlanSql = "DELETE FROM APPROVAL_HISTORY_REPAY_PLAN AHRP WHERE AHRP.PROJECT_ID =" + projectId + " AND AHRP.APPROVAL_HISTORY_ID IS NULL";
		String delRepayPlanTempSql = "DELETE FROM REPAY_PLAN_TEMP RPT WHERE RPT.PROJECT_ID =" + projectId;
		dynamicQuery.nativeExecuteUpdate(delRepayPlanSql);
		dynamicQuery.nativeExecuteUpdate(delApprovalHistoryRepayPlanSql);
		dynamicQuery.nativeExecuteUpdate(delRepayPlanTempSql);
		
		List<ApprovalHistoryRepayPlan> approvalRepayPlans = approvalHistoryRepayPlanDao.findByApprovalHistoryIdAndProjectId(approvalHistoryId, projectId);
		List<RepayPlan> newRepayPlans = new ArrayList<RepayPlan>();
		List<ApprovalHistoryRepayPlan> newApprovalHistoryRepayPlans = new ArrayList<ApprovalHistoryRepayPlan>();
		List<RepayPlanTemp> newRepayPlanTemps = new ArrayList<RepayPlanTemp>();
		for (ApprovalHistoryRepayPlan newApprovalHistoryRepayPlan : approvalRepayPlans) {
			RepayPlan repayPlan = new RepayPlan();
			BeanUtils.copyProperties(newApprovalHistoryRepayPlan, repayPlan);
			newRepayPlans.add(repayPlan);
			
			ApprovalHistoryRepayPlan approvalHistoryRepayPlan2 = new ApprovalHistoryRepayPlan();
			BeanUtils.copyProperties(newApprovalHistoryRepayPlan, approvalHistoryRepayPlan2);
			approvalHistoryRepayPlan2.setApprovalHistoryRepayPlanId(null);
			approvalHistoryRepayPlan2.setApprovalHistoryId(null);
			newApprovalHistoryRepayPlans.add(approvalHistoryRepayPlan2);
			
			RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
			BeanUtils.copyProperties(repayPlan, repayPlanTemp);
			newRepayPlanTemps.add(repayPlanTemp);
		}
		repayPlanDao.save(newRepayPlans);
		approvalHistoryRepayPlanDao.save(newApprovalHistoryRepayPlans);
		repayPlanTempDao.save(newRepayPlanTemps);
	}

	@Override
	public ApprovalHistoryRepayPlan getRepayPlan(Long id) {
		return approvalHistoryRepayPlanDao.findOne(id);
	}

	@Override
	public void deleteRepayPlan(Long id) {
		ApprovalHistoryRepayPlan approvalHistoryRepayPlan = approvalHistoryRepayPlanDao.findOne(id);
		Long projectId = approvalHistoryRepayPlan.getProjectId();
		Long approvalHistoryId = approvalHistoryRepayPlan.getApprovalHistoryId();
		approvalHistoryRepayPlanDao.delete(id);
		
		String delRepayPlanSql = "DELETE FROM REPAY_PLAN RP WHERE RP.PROJECT_ID = " + projectId;
		String delApprovalHistoryRepayPlanSql = "DELETE FROM APPROVAL_HISTORY_REPAY_PLAN AHRP WHERE AHRP.PROJECT_ID =" + projectId + " AND AHRP.APPROVAL_HISTORY_ID IS NULL";
		String delRepayPlanTempSql = "DELETE FROM REPAY_PLAN_TEMP APT WHERE APT.PROJECT_ID =" + projectId;
		dynamicQuery.nativeExecuteUpdate(delRepayPlanSql);
		dynamicQuery.nativeExecuteUpdate(delApprovalHistoryRepayPlanSql);
		dynamicQuery.nativeExecuteUpdate(delRepayPlanTempSql);
		
		List<ApprovalHistoryRepayPlan> approvalRepayPlans = approvalHistoryRepayPlanDao.findByApprovalHistoryIdAndProjectId(approvalHistoryId, projectId);
		List<RepayPlan> newRepayPlans = new ArrayList<RepayPlan>();
		List<ApprovalHistoryRepayPlan> newApprovalHistoryRepayPlans = new ArrayList<ApprovalHistoryRepayPlan>();
		List<RepayPlanTemp> newRepayPlanTemps = new ArrayList<RepayPlanTemp>();
		for (ApprovalHistoryRepayPlan newApprovalHistoryRepayPlan : approvalRepayPlans) {
			RepayPlan repayPlan = new RepayPlan();
			BeanUtils.copyProperties(newApprovalHistoryRepayPlan, repayPlan);
			newRepayPlans.add(repayPlan);
			
			ApprovalHistoryRepayPlan approvalHistoryRepayPlan2 = new ApprovalHistoryRepayPlan();
			BeanUtils.copyProperties(newApprovalHistoryRepayPlan, approvalHistoryRepayPlan2);
			approvalHistoryRepayPlan2.setApprovalHistoryRepayPlanId(null);
			approvalHistoryRepayPlan2.setApprovalHistoryId(null);
			newApprovalHistoryRepayPlans.add(approvalHistoryRepayPlan2);
			
			RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
			BeanUtils.copyProperties(repayPlan, repayPlanTemp);
			newRepayPlanTemps.add(repayPlanTemp);
			
		}
		repayPlanDao.save(newRepayPlans);
		approvalHistoryRepayPlanDao.save(newApprovalHistoryRepayPlans);
		repayPlanTempDao.save(newRepayPlanTemps);
	}

	@Override
	public BusinessApplicationWdVO startMicroloanBusiness(Long productCd,
			Party party, String curUserLongName, Long curUserId,
			String curUserName, Long curUserOrgId, String curUserOrgDesc)
			throws Exception {

		CorporationCustomer corporationCustomer = null;

		Individual individual = null;

		TypedResult<WorkFlowTaskIds> workflowProcess = null;

		Product product = productDao.findOne(productCd);
		
		if (GlobalConstants.FARMER_LOAN_PRODUCT_MARK.equals(product.getProductCd().toString())) {// 农贷申请
			
		}

		try {

			String taskSubject = product.getProductName();

			if (party.getPartyTypeCd().equals("1")) {// 企业客户
				corporationCustomer = corporationCustomerDao.findOne(party.getPartyId());
				taskSubject += "【" + curUserOrgDesc + "：" + corporationCustomer.getCustomerName() + "】";
			} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
				individual = customerIndividualDao.findByPartyId(party.getPartyId());
				taskSubject += "【" + curUserOrgDesc + "：" + individual.getCustomerName() + "】";
			}

			/**
			 * 启动流程
			 */
			workflowProcess = this.workFlowService.startWorkflow(
					WorkFlowService.SysCode.WD_SYS,
					WorkFlowService.WorkFlowCode.MICRO_LOAN,
					WorkFlowNode.ML_EntryBusiApplInfo, curUserLongName,
					taskSubject, CommonHelper.long2StrIfEmpty(curUserOrgId));

			if (!workflowProcess.getSuccess()) {
				throw new RuntimeException("startProcess");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("startProcess");
		}

		/**
		 * 任务处理
		 */
		try {
			TypedResult<WorkFlowId> workflowId = this.workFlowService
					.startProcessingTask(workflowProcess.getData()
							.getWorkflowId(), workflowProcess.getData()
							.getTaskId(), curUserLongName);
			if (!workflowId.getSuccess()) {
				throw new RuntimeException("disposeTask");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException("disposeTask");
		}

		/**
		 * 获取按钮
		 */
		TypedResult<List<TaskAction>> buttons = this.workFlowService
				.getTaskActions(WorkFlowNode.ML_EntryBusiApplInfo);
		if (!buttons.getSuccess()) {
			throw new RuntimeException("getNextTaskActions");
		}

		/**
		 * 下一环节所有人
		 */
		List<NextTaskReceiver> receiverList = this.workFlowService
				.getNextTaskReceivers(workflowProcess.getData().getTaskId(),
						WorkFlowService.ActionCode.COMMIT).getData();

		/**
		 * 新建业务申请
		 */
		ProjectApplication projectApplication = new ProjectApplication();
		projectApplication.setBpInfoId(Long.valueOf(workflowProcess.getData().getWorkflowId()));
		projectApplication.setWorkflowId(Long.valueOf(workflowProcess.getData().getWorkflowId()));
		projectApplication.setProductType(productCd.toString());
		projectApplication.setCustomerManagerNum(curUserId.toString());
		projectApplication.setCustomerManagerName(curUserName);
		projectApplication.setApplicant(curUserId.toString());
		projectApplication.setApplyOrg(curUserOrgId.toString());
		projectApplication.setSysCreateTime(new Date());
		projectApplication.setSysUpdateTime(new Date());
		projectApplication.setProjectStatus("0");
		projectApplication.setProjectNo(commonBizNumberBS.generateBizEventNumber("Y1", curUserOrgId.toString()));
		projectApplication.setSysCd("1");

		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			projectApplication.setCustomerName(corporationCustomer.getCustomerName());
			projectApplication.setCustomerNum(corporationCustomer.getCustomerNum());
			projectApplication.setPartyId(corporationCustomer.getPartyId());
			projectApplication.setCertificateTypeCd("210");
			projectApplication.setCertificateNum(corporationCustomer.getCertificateNum());
			projectApplication.setBusinessType("2");
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			projectApplication.setCustomerName(individual.getCustomerName());
			projectApplication.setCustomerNum(individual.getCustomerNum());
			projectApplication.setPartyId(individual.getPartyId());
			projectApplication.setCertificateTypeCd(individual.getCertificateTypeCd());
			projectApplication.setCertificateNum(individual.getCertificateNum());
			projectApplication.setBusinessType("1");
		}
		projectApplication.setBusinessProcessType("1");

		projectApplicationDao.save(projectApplication);
		projectApplication.setInitProjectId(projectApplication.getProjectId());

		/**
		 * 更新文档
		 */
		Set<String> set = new HashSet<String>();
		set.add("01");
		documentService.updateDocumentIndexByPartyId(party.getPartyId(), set, dataDict.getCodeVal("CreateType", "S2"));

		/**
		 * fill VO
		 */
		BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
		BeanUtils.copyProperties(
				party.getPartyTypeCd().equals("1") ? corporationCustomer
						: individual, businessApplicationWdVO);
		BeanUtils.copyProperties(projectApplication, businessApplicationWdVO);
		businessApplicationWdVO.setWorkflowId(Long.valueOf(workflowProcess
				.getData().getWorkflowId()));
		businessApplicationWdVO
				.setTaskId(workflowProcess.getData().getTaskId());
		businessApplicationWdVO.setButtons(buttons.getData());
		businessApplicationWdVO.setReceivers(receiverList);
		businessApplicationWdVO.setProduct(product);

		return businessApplicationWdVO;

	}

	@Override
	public Long saveMicroloanBusiness(BusinessApplicationWdVO form){
		
		//检查申请金额是否满足条件
		this.checkApplyAmountIsAvailable(form.getApplyAmt(),
				Long.valueOf(form.getProductCd()), form.getCurUserOrgId());
		
		if (StringUtils.isBlank(form.getMateBorrower())) {
			form.setMateBorrower(dataDict.getCodeVal("CommonWhether", "S2"));
		}
		/**
		 * 关联共同借款人-配偶
		 */
		FamilyFriend spouse = familyFriendDao.getSpouseByPartyId(form.getPartyId());
		if (StringUtils.isNotBlank(form.getMarriageCd())// 防止错误数据
				&& form.getMarriageCd().equals(dataDict.getCodeVal("MaritalStatus", "S20"))// 此人已婚
				&& form.getMateBorrower().equals(dataDict.getCodeVal("CommonWhether", "S1"))) {// 选中配偶作为共同借款人
			if (spouse == null) {
				throw new RuntimeException("此客户无配偶信息！请确认客户信息后完成业务申请！");
			}
			
			CommonBorrow commonBorrow = commonBorrowDao.findByProjectIdAndPartyId(form.getProjectId(), spouse.getCorrelativeRelationsId());
			if(commonBorrow == null) {
				commonBorrow = new CommonBorrow();
				BeanUtils.copyProperties(spouse, commonBorrow);
				commonBorrow.setProjectId(form.getProjectId());
				commonBorrow.setCertificateNum(spouse.getCertificateCd());
				commonBorrow.setCustomerName(spouse.getName());
				commonBorrow.setMobilePhone(spouse.getMobileTel());
				commonBorrow.setPhone(spouse.getTelphone());
				commonBorrow.setPartyId(spouse.getCorrelativeRelationsId());// 此处partyId为此联系人的ID，关联时通过Spouseflag分辨
				commonBorrow.setFlag("1");
				commonBorrow.setSpouseflag("1");
				commonBorrowDao.save(commonBorrow);
			}
		} else if(dataDict.getCodeVal("CommonWhether", "S2").equals(form.getMateBorrower()) && spouse != null) {
			commonBorrowDao.deleteByProjectIdAndPartyId(form.getProjectId(), spouse.getCorrelativeRelationsId());
		}

		/**
		 * 还款计划
		 */
		if (form.getRepayingMode().equals(dataDict.getUniqueOne("RepaymentMode", "S3").getCodeValue())) {// 自定义还款计划
			BigDecimal total = repayPlanDao.findTotalByProjectNo(form.getProjectNo());
			if (total == null)
				throw new RuntimeException("请完善还款计划！");
			else if (total.doubleValue() != form.getApplyAmt().doubleValue())
				throw new RuntimeException("还款计划的总金额必须和申报金额相等！还款计划总额为："
						+ total.doubleValue() + "，申报金额为："
						+ form.getApplyAmt().doubleValue());
		} else {
			repayPlanDao.deleteByProjectId(form.getProjectId());
			approvalHistoryRepayPlanDao.deleteByProjectId(form.getProjectId());
			repayPlanTempDao.deleteByProjectNo(form.getProjectNo());
		}

		/**
		 * 检查年利率是否大于央行基准利率的N倍
		 * 
		 * N从product_config表中取出
		 */
		ProductConfig p = productConfigDao.findByProductCdAndOrgId(Long.valueOf(form.getProductCd()), form.getCurUserOrgId());
		if (p.getRateMultiple() != null) {
			// 得到前台利率
			BigDecimal rate = form.getBizRate().divide(new BigDecimal("100"));
			Integer term = null;
			// 判断期限单位，计算前台期限（单位：日）
			if (form.getApplyTermUnit().equals(
					dataDict.getCodeVal("TermUnitCd", "S1"))) {// 年
				term = form.getApplyTerm() * GlobalConstants.YEAR_TO_DAY;
			} else if (form.getApplyTermUnit().equals(
					dataDict.getCodeVal("TermUnitCd", "S2"))) {// 月
				term = form.getApplyTerm() * GlobalConstants.MONTH_TO_DAY;
			} else {// 日
				term = form.getApplyTerm();
			}
			// 获取央行基准利率
			MoneyRate moneyRate = moneyRateDao.findListByTimeLimit(
					this.new TimeLimitUtil().decideTimeLimit(term).toString())
					.get(0);

			if (rate.compareTo(moneyRate.getInterestRate().multiply(
					new BigDecimal(p.getRateMultiple().toString()))) == 1)
				throw new RuntimeException("利率值必须小于或等于央行基准利率的"
						+ p.getRateMultiple()
						+ "倍，利率值为："
						+ rate.multiply(new BigDecimal("100"))
						+ "%，央行基准利率的"
						+ p.getRateMultiple()
						+ "倍为："
						+ moneyRate
								.getInterestRate()
								.multiply(
										new BigDecimal(p.getRateMultiple()
												.toString()))
								.multiply(new BigDecimal("100")) + "%");

		}

		/**
		 * 业务申请表
		 */
		ProjectApplication projectApplication = projectApplicationDao.findOne(form.getProjectId());
		
		//业务申请时，联保体客户处理
		this.saveProjectAssurerInfoOfUgnCust(projectApplication.getProjectId(),
				projectApplication.getProjectNo(),
				projectApplication.getPartyId(),
				projectApplication.getApplyAmt(), form.getUnId(),
				projectApplication.getUnId(), form.getCurUserId());
		
		BeanUtils.copyProperties(form, projectApplication);
		projectApplication.setApproveAmt(form.getApplyAmt());
		try {
			projectApplication.setApplyDate(new SimpleDateFormat("yyyy-MM-dd")
					.parse(form.getApplyDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		projectApplication.setSysUpdateTime(new Date());
		projectApplication.setTerm(form.getApplyTerm());
		projectApplication.setTermUnit(form.getApplyTermUnit());
		if (dataDict.getCodeVal("InterestRateAdjustment", "S1").equals(
				form.getIrTypeCd())) {// 固定利率
			projectApplication.setBizRate(form.getBizRate().divide(
					new BigDecimal("100")));
		} else {// 浮动利率
			projectApplication.setBizRate(form.getBizRate());
		}
		//add by wangpeng on 2015-07-27 start
		projectApplication.setAssistance(form.getAssistancer());
		//add by wangpeng on 2015-07-27 end
		// 逾期利率上浮比例
		/*projectApplication.setOvdueRate(MathUtil.BDmultiply(
				projectApplication.getBizRate(),
				new BigDecimal(MathUtil.BDadd(1, form.getOvdueRate()
						.doubleValue(), 6)), 6));*/
		if(form.getOvdueRate()!=null){
			projectApplication.setOvdueRate(form.getOvdueRate().movePointLeft(2));
		}
		// 挪用利率上浮比例
		/*projectApplication.setPerculNegoRate(MathUtil.BDmultiply(
				projectApplication.getBizRate(),
				new BigDecimal(MathUtil.BDadd(1, projectApplication
						.getPerculNegoRate().doubleValue(), 6)), 6));*/
		if(form.getPerculNegoRate()!=null){
			projectApplication.setPerculNegoRate(form.getPerculNegoRate().movePointLeft(2));
		}

		projectApplication.setPreRepaymentRate(form.getPreRepaymentRate()
				.divide(new BigDecimal("100")));
		projectApplication.setCurrency("156");
		projectApplicationDao.save(projectApplication);
		
		
		
		/**
		 * 关联保证人表，修改保证人的本次申请保证债权金额
		 */
		List<ProjectAssurerInfo> projectAssurerInfos = projectAssurerInfoDao
				.findByProjectId(form.getProjectId());
		if (projectAssurerInfos != null && projectAssurerInfos.size() > 0) {
			for (ProjectAssurerInfo projectAssurerInfo : projectAssurerInfos) {
				projectAssurerInfo.setGuaranteeAmt(projectApplication
						.getApplyAmt());
				projectAssurerInfoDao.save(projectAssurerInfo);
			}
		}

		/**
		 * 利率表
		 */
		BizRate bizRate = null;
		if (form.getBizRateId() == null) {// 新增
			bizRate = new BizRate();
			BeanUtils.copyProperties(form, bizRate);
			bizRate.setProjectId(form.getProjectId().toString());
			bizRate.setSysCreateDate(new Date());
			bizRate.setSysUpdateDate(new Date());

			bizRate.setOvdueIrNegoRate(form.getOvdueRate().divide(
					new BigDecimal("100")));
			bizRate.setPerculIrNegoRate(form.getPerculNegoRate().divide(
					new BigDecimal("100")));

			if (dataDict.getCodeVal("InterestRateAdjustment", "S1").equals(
					form.getIrTypeCd())) {// 固定利率
				bizRate.setRateValue(form.getBizRate().divide(
						new BigDecimal("100")));
			} else {// 浮动利率
				bizRate.setRateValue(form.getBizRate());
			}

			bizRate.setApproveRateValue(form.getBizRate().divide(
					new BigDecimal("100")));
			if (form.getIrNegoSymbCd() != null) {
				bizRate.setIrNegoSymbCd(form.getIrNegoSymbCd().divide(
						new BigDecimal("100")));
				bizRate.setApproveFloatRate(form.getIrNegoSymbCd().divide(
						new BigDecimal("100")));
			}
			bizRateDao.save(bizRate);

			/**
			 * 保存费用
			 * 
			 * Description 如果前台能够获取到bizRateId，说明用户已点击保存，因此当bizRateId为空时才保存费用
			 * 
			 */
			addBizExpenseRate(projectApplication);
		} else {// 修改
			bizRate = bizRateDao.findOne(form.getBizRateId());
			BeanUtils.copyProperties(form, bizRate,
					new String[] { "bizRateId" });
			bizRate.setSysUpdateDate(new Date());

			bizRate.setOvdueIrNegoRate(form.getOvdueRate().divide(
					new BigDecimal("100")));
			bizRate.setPerculIrNegoRate(form.getPerculNegoRate().divide(
					new BigDecimal("100")));

			if (dataDict.getCodeVal("InterestRateAdjustment", "S1").equals(
					form.getIrTypeCd())) {// 固定利率
				bizRate.setRateValue(form.getBizRate().divide(
						new BigDecimal("100")));
			} else {// 浮动利率
				bizRate.setRateValue(form.getBizRate());
			}
			bizRate.setApproveRateValue(form.getBizRate().divide(
					new BigDecimal("100")));
			if (form.getIrNegoSymbCd() != null) {
				bizRate.setIrNegoSymbCd(form.getIrNegoSymbCd().divide(
						new BigDecimal("100")));
				bizRate.setApproveFloatRate(form.getIrNegoSymbCd().divide(
						new BigDecimal("100")));
			}
			bizRateDao.save(bizRate);

			List<BizExpenseRate> list = bizExpenseRateDao.findByProjectId(form
					.getProjectId());

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

		return bizRate.getBizRateId();
	}

	/**
	 * 如果选择了联保体将，将该联保体的成员添加到保证人列表。
	 * @param pa
	 * @param curUserId 当前登录人id
	 */
	private void saveProjectAssurerInfoOfUgnCust(Long projectId,
			String projectNo, Long partyId, BigDecimal applyAmt,
			String newUgnId, String oldUgnId, Long curUserId) {
		final String ugnIdStr=newUgnId;//联保体客户id
		if(StringUtils.equals(newUgnId, oldUgnId)){//联保体未改变
			return;
		}
		boolean isUgn=StringUtils.isNoneBlank(ugnIdStr);//是否是联保体
		
		final String assurerSource=dataDict.getCodeVal("AssurerSource", "S4");
		if(StringUtils.isNoneBlank(oldUgnId)){
			//联保体改变则，删除原有来自联保体的保证人
			this.projectAssurerInfoDao.deleteProjectAssurerInfoOfUgn(projectId, assurerSource);
		}
		
		if(!isUgn){
			return;
		}
		Long ugnId=CommonHelper.toLong(ugnIdStr);
		List<UnCustTab> uctList=this.uniteGuCustomerService.findUgnMembersByUgnId(ugnId);
		if(CollectionUtils.isEmpty(uctList)){
			throw new ServiceException("该联保体客户，没有有效的成员");
		}
		//校验是否重复添加
		List<Long> partyIds=Lists.newArrayList();
		for (UnCustTab unCustTab : uctList) {
			partyIds.add(CommonHelper.toLong(unCustTab.getPartyId()));
		}
		List<ProjectAssurerInfo> list=this.projectAssurerInfoDao.findByProjIdAndPartyIds(projectId, partyIds);
		if(CollectionUtils.isNotEmpty(list)){
			throw new ServiceException("该联保体下的部分成员，在该业务申请中的担保人列表已存在");
		}
		//保证人保存
		ProjectAssurerInfo pai=null;
		for (UnCustTab uct : uctList) {
			if(NumberUtil.compareLong(partyId, CommonHelper.toLong(uct.getPartyId()))){
				//将除该客户以外的所有联保体客户成员添加到保证人列表
				continue;
			}
			pai=new ProjectAssurerInfo();
			pai.setProjectId(projectId);
			pai.setProjectNo(projectNo);
			pai.setGuaranteeAmt(applyAmt);
			// projectAssurerInfo.setActualGuaranteeAmt(new BigDecimal(
			// guaranteeAmt));
			pai.setCreateDate(new Date());
			pai.setSysUpdateTime(new Date());
			pai.setCreatePerson(CommonHelper.toStr(curUserId));//当前登录人id
			pai.setAssureState("1");
			pai.setCurrency("156");
			pai.setCertificateTypeCd(uct.getCertificateTypeCd());
			pai.setCertificateNum(uct.getCertificateNum());
			pai.setPartyId(CommonHelper.toLong(uct.getPartyId()));
			pai.setCustomerName(uct.getCustomerName());
			pai.setCustomerNum(uct.getCustomerNum());
			pai.setAssurerSource(assurerSource);//来源自联保体
			this.projectAssurerInfoDao.save(pai);
		}
	}

	@Override
	public BusinessApplicationWdVO startEasyLoanBusiness(Long productCd, Party party,
			String curUserLongName, Long curUserId, String curUserName,
			Long curUserOrgId, String curUserOrgDesc, Model model)
			throws Exception {

		Individual individual = customerIndividualDao.findByPartyId(party.getPartyId());
		
		Product product = productDao.findOne(productCd);
		
		ProductConfig productConfig = productConfigDao.findByProductCdAndOrgId(product.getProductCd(), curUserOrgId);
		
		this.checkPartyPropsWhenEasyLoan(individual, productConfig);

		BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();

		TypedResult<WorkFlowTaskIds> workflowProcess = null;


		try {

			String taskSubject = product.getProductName();

			taskSubject += "【" + curUserOrgDesc + "："
					+ individual.getCustomerName() + "】";

			workflowProcess = this.workFlowService.startWorkflow(
					WorkFlowService.SysCode.WD_SYS,
					WorkFlowService.WorkFlowCode.EASY_LOAN,
					WorkFlowNode.EL_EntryBusiApplInfo, curUserLongName,
					taskSubject, CommonHelper.long2StrIfEmpty(curUserOrgId));

			if (!workflowProcess.getSuccess()) {
				throw new RuntimeException("startProcess");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("startProcess");
		}

		/**
		 * 任务处理
		 */
		try {
			TypedResult<WorkFlowId> workflowId = this.workFlowService
					.startProcessingTask(workflowProcess.getData()
							.getWorkflowId(), workflowProcess.getData()
							.getTaskId(), curUserLongName);
			if (!workflowId.getSuccess()) {
				throw new RuntimeException("disposeTask");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException("disposeTask");
		}

		/**
		 * 获取按钮
		 */
		TypedResult<List<TaskAction>> buttons = this.workFlowService
				.getTaskActions(WorkFlowNode.EL_EntryBusiApplInfo);
		;

		if (!buttons.getSuccess()) {
			throw new RuntimeException("getNextTaskActions");
		}
		businessApplicationWdVO.setButtons(buttons.getData());

		/**
		 * 更新文档
		 */
		Set<String> set = new HashSet<String>();
		set.add("01");
		documentService.updateDocumentIndexByPartyId(party.getPartyId(), set, dataDict.getCodeVal("CreateType", "S2"));

		ProjectApplication pa = new ProjectApplication();
		pa.setBpInfoId(Long.valueOf(workflowProcess.getData().getWorkflowId()));
		pa.setWorkflowId(Long.valueOf(workflowProcess.getData().getWorkflowId()));
		pa.setBusinessType("1");
		pa.setBusinessProcessType("1");
		pa.setProductType(productCd.toString());
		pa.setCustomerManagerNum(curUserId.toString());
		pa.setCustomerName(individual.getCustomerName());
		pa.setCustomerNum(individual.getCustomerNum());
		pa.setCustomerManagerName(curUserName);
		pa.setApplicant(curUserId.toString());
		pa.setApplyOrg(curUserOrgId.toString());
		pa.setSysCreateTime(new Date());
		pa.setSysUpdateTime(new Date());
		pa.setPartyId(individual.getPartyId());
		pa.setProjectStatus("0");
		pa.setProjectNo(commonBizNumberBS.generateBizEventNumber("Y1", curUserOrgId.toString()));
		pa.setCertificateTypeCd(individual.getCertificateTypeCd());
		pa.setCertificateNum(individual.getCertificateNum());
		pa.setSysCd("1");
		projectApplicationDao.save(pa);
		pa.setInitProjectId(pa.getProjectId());
		
		businessApplicationWdVO.setProjectId(pa.getProjectId());
		businessApplicationWdVO.setWorkflowId(pa.getWorkflowId());
		businessApplicationWdVO.setTaskId(workflowProcess.getData().getTaskId());

		return businessApplicationWdVO;
	}

	@Override
	public BizRate saveEasyLoanBusiness(ProjectApplication projectApplication,
			String bizRateId, String interestRateAdjustment,
			BigDecimal overFloatRate, BigDecimal divertFloatRate,
			BigDecimal rateValue) throws Exception {
		
		// 批量额度
//		BigDecimal batchLimit = productConfigDao.findByProductCdAndOrgId(Long.valueOf(projectApplication.getProductType()), Long.valueOf(projectApplication.getApplyOrg())).getBatchLimit();
//		if (batchLimit != null) {
//			// 已用额度
//			BigDecimal usedAmount = contractDao.getUsedAmountOfProduct(projectApplication.getProductType(), Long.valueOf(projectApplication.getApplyOrg()));
//			// 可用额度
//			BigDecimal availableAmount = batchLimit.subtract(usedAmount);
//			// 申请额度
//			BigDecimal applyAmount = projectApplication.getApplyAmt();
//			if (applyAmount.compareTo(availableAmount) == 1) {
//				throw new RuntimeException("批量产品可用额度不足！批量额度为" + batchLimit + "，可用额度为：" + availableAmount);
//			}
//		}
		//申请金额不满足条件
		this.checkApplyAmountIsAvailable(projectApplication.getApplyAmt(),
				Long.valueOf(projectApplication.getProductType()), Long.valueOf(projectApplication.getApplyOrg()));

		Individual individual = individualDao.findOne(projectApplication.getPartyId());
		/**
		 * 关联共同借款人-配偶
		 */
		FamilyFriend spouse = familyFriendDao.getSpouseByPartyId(individual
				.getPartyId());
		if (StringUtils.isNotBlank(individual.getMarriageCd())// 防止错误数据
				&& individual.getMarriageCd().equals(
						dataDict.getCodeVal("MaritalStatus", "S20"))// 此人已婚
				&& projectApplication.getMateBorrower().equals(
						dataDict.getCodeVal("CommonWhether", "S1"))// 选中配偶作为共同借款人
						) {// 第一次点击保存基本信息
			if (spouse == null)
				throw new RuntimeException("此客户无配偶信息！请确认客户信息后完成业务申请！");
			//update by hengwanli 20150429
			CommonBorrow commonBorrow = commonBorrowDao.findByProjectIdAndPartyId(projectApplication.getProjectId(), spouse.getCorrelativeRelationsId());
			if(commonBorrow==null){
				commonBorrow=new CommonBorrow();
				BeanUtils.copyProperties(spouse, commonBorrow);
				commonBorrow.setProjectId(projectApplication.getProjectId());
				commonBorrow.setCertificateNum(spouse.getCertificateCd());
				commonBorrow.setCustomerName(spouse.getName());
				commonBorrow.setMobilePhone(spouse.getMobileTel());
				commonBorrow.setPhone(spouse.getTelphone());
				commonBorrow.setPartyId(spouse.getCorrelativeRelationsId());// 此处partyId为此联系人的ID，关联时通过Spouseflag分辨
				commonBorrow.setFlag("1");
				commonBorrow.setSpouseflag("1");
				commonBorrowDao.save(commonBorrow);
			}
			//update by hengwanli 20150429 end
		} else if(dataDict.getCodeVal("CommonWhether", "S2").equals(projectApplication.getMateBorrower()) && spouse != null) {
			//共同借款人选否，则从共同借款人列表中删除
			commonBorrowDao.deleteByProjectIdAndPartyId(projectApplication.getProjectId(), spouse.getCorrelativeRelationsId());
		}

		/**
		 * 检查年利率是否大于央行基准利率的N倍
		 * 
		 * N从product_config表中取出
		 */
		ProductConfig p = productConfigDao.findByProductCdAndOrgId(
				Long.valueOf(projectApplication.getProductType()),
				Long.valueOf(projectApplication.getApplyOrg()));
		if (p.getRateMultiple() != null) {
			BigDecimal rate = null;
			Integer term = null;
			// 得到前台利率
			rate = rateValue.divide(new BigDecimal("100"));
			// 判断期限单位，计算前台期限（单位：日）
			if (projectApplication.getApplyTermUnit().equals(
					dataDict.getCodeVal("TermUnitCd", "S1"))) {// 年
				term = projectApplication.getApplyTerm()
						* GlobalConstants.YEAR_TO_DAY;
			} else if (projectApplication.getApplyTermUnit().equals(
					dataDict.getCodeVal("TermUnitCd", "S2"))) {// 月
				term = projectApplication.getApplyTerm()
						* GlobalConstants.MONTH_TO_DAY;
			} else {// 日
				term = projectApplication.getApplyTerm();
			}
			// 获取央行基准利率
			MoneyRate moneyRate = moneyRateDao.findListByTimeLimit(
					this.new TimeLimitUtil().decideTimeLimit(term).toString())
					.get(0);

			if (rate.compareTo(moneyRate.getInterestRate().multiply(
					new BigDecimal(p.getRateMultiple().toString()))) == 1)
				throw new RuntimeException("利率值必须小于或等于央行基准利率的"
						+ p.getRateMultiple()
						+ "倍，利率值为："
						+ rate.multiply(new BigDecimal("100"))
						+ "%，央行基准利率的"
						+ p.getRateMultiple()
						+ "倍为："
						+ moneyRate
								.getInterestRate()
								.multiply(
										new BigDecimal(p.getRateMultiple()
												.toString()))
								.multiply(new BigDecimal("100")) + "%");

		}
		projectApplication.setSysUpdateTime(new Date());
		projectApplicationDao.save(projectApplication);
		/**
		 * 保存利率
		 */
		BizRate bizRate = null;
		if (StringUtils.isNotBlank(bizRateId)) {// 修改
			bizRate = bizRateDao.findOne(Long.valueOf(bizRateId));
			bizRate.setIrTypeCd("1");
			bizRate.setOvdueIrNegoRate(overFloatRate
					.divide(new BigDecimal(100)));
			bizRate.setPerculIrNegoRate(divertFloatRate.divide(new BigDecimal(
					100)));
			bizRate.setSysUpdateDate(new Date());
			bizRate.setRateValue(rateValue.divide(new BigDecimal(100)));
			bizRate.setApproveRateValue(rateValue.divide(new BigDecimal(100)));

			List<BizExpenseRate> list = bizExpenseRateDao
					.findByProjectId(projectApplication.getProjectId());
			if (list != null && list.size() > 0) {
				for (BizExpenseRate bizExpenseRate : list) {
					if (bizExpenseRate.getExpenseRate() != null) {
						bizExpenseRate.setExpenseAmt(bizExpenseRate
								.getExpenseRate()
								.multiply(projectApplication.getApplyAmt())
								.divide(new BigDecimal(100)));
						bizExpenseRateDao.save(bizExpenseRate);
					}
					if (bizExpenseRate.getStandardExpenseRate() != null) {
						bizExpenseRate.setStandaredAmt(bizExpenseRate
								.getStandardExpenseRate()
								.multiply(projectApplication.getApplyAmt())
								.divide(new BigDecimal(100)));
						bizExpenseRateDao.save(bizExpenseRate);
					}

				}
			}
		} else {// 新增
			bizRate = new BizRate();
			bizRate.setProjectId(projectApplication.getProjectId().toString());
			bizRate.setProjectNo(projectApplication.getProjectNo());
			bizRate.setProductCd(projectApplication.getProductType());
			bizRate.setSysCreateDate(new Date());
			bizRate.setIrTypeCd("1");
			bizRate.setOvdueIrNegoRate(overFloatRate
					.divide(new BigDecimal(100)));
			bizRate.setPerculIrNegoRate(divertFloatRate.divide(new BigDecimal(
					100)));
			bizRate.setSysUpdateDate(new Date());
			bizRate.setRateValue(rateValue.divide(new BigDecimal(100)));
			bizRate.setApproveRateValue(rateValue.divide(new BigDecimal(100)));
			bizRateDao.save(bizRate);

			/**
			 * 保存费用
			 * 
			 * Description 如果前台能够获取到bizRateId，说明用户已点击保存，因此当bizRateId为空时才保存费用
			 * 
			 */
			addBizExpenseRate(projectApplication);
		}
		return bizRate;
	}

	@Override
	public BizRate getBizRateByProjectId(String projectId) {
		return bizRateDao.findByProjectId(projectId);
	}

	@Override
	public boolean checkManagerType(Long partyId, String userNum) {
		String managerType = customerManagerTeamDao.findManagerTypeByPartyIdAndUserNum(partyId, userNum);
		if (StringUtils.isBlank(managerType) || (!managerType.equals("01") && !managerType.equals("02")))
			return false;
		// 01、02可以发起业务
		return true;
	}

	@Override
	public ProductConfig getProductConfigByProductCd(Long productCd, Long orgId) {
		return productConfigDao.findByProductCdAndOrgId(productCd, orgId);
	}

	@Override
	public ProjectApplication findProjectApplicationByWorkflowId(Long workflowId) {
		return projectApplicationDao
				.findProjectApplicationByWorkflowId(workflowId);
	}

	@Override
	public TypedResult<WorkFlowTaskIds> cancelApply(Long projectId,
			String workflowId, String taskId, String curUserLogname,
			WorkFlowCode workFlowCode, WorkFlowNode workFlowNode) {

		ProjectApplication pa = projectApplicationDao.findOne(projectId);
		pa.setProjectStatus("4");// 否决

		List<ProjectPawnInfo> pawnInfoList = projectPawnInfoDao
				.findByProjectId(projectId);
		for (ProjectPawnInfo projectPawnInfo : pawnInfoList) {
			List<ProjectPawnInfo> pawnInfos = projectPawnInfoDao
					.findByProjectIdAndPawnStateAndGuarantyId(projectId,
							dataDict.getCodeVal("PawnState", "S2"),
							projectPawnInfo.getGuarantyId());
			if (pawnInfos.size() == 0) {
				Collateral collateral = collateralDao.findOne(projectPawnInfo
						.getGuarantyId());
				collateral.setGuarantyStatusCd(dataDict.getCodeVal(
						"RevGuaranteeStatusCd", "S1"));
			}
			projectPawnInfo
					.setPawnState(dataDict.getCodeVal("PawnState", "S4"));
		}

		ActionCode actCode = ActionCode
				.getActionCodeById(WorkFlowService.ActionCode.CANCEL
						.getCodeId());

		return this.workFlowService.executeTask(ExeTaskParam.newExeTaskParam(
				workFlowCode, workflowId, taskId, curUserLogname, workFlowNode,
				actCode, null, null, null, null));

	}

	@Override
	public Product findProductByProductCd(String productCd) {
		return productDao.findOne(Long.valueOf(productCd));
	}

	@Override
	public List<InsuranceCompany> findAllInsuranceCompany() {
		return insuranceCompanyDao.findEffectiveCompanies();
		// return (List<InsuranceCompany>) insuranceCompanyDao.findAll();
	}

	@Override
	public BigDecimal countPremiumFee(Long insuranceOrgId, BigDecimal applyAmt,
			BigDecimal bizRate, String applyDate, String applyTerm,
			String applyTermUnit, String guaranteeMode, String irTypeCd) {

		Date startDate = DateTools.stringToDate(applyDate, "yyyy-MM-dd");
		// 计算合同到期日
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int field = -1;
		GregorianCalendar gregorianCalendar = new GregorianCalendar(
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));

		if (applyTermUnit.equals(dataDict.getUniqueOne("TermUnitCd", "S1")
				.getCodeValue()))
			field = Calendar.YEAR;
		if (applyTermUnit.equals(dataDict.getUniqueOne("TermUnitCd", "S2")
				.getCodeValue()))
			field = Calendar.MONTH;
		if (applyTermUnit.equals(dataDict.getUniqueOne("TermUnitCd", "S3")
				.getCodeValue()))
			field = Calendar.DAY_OF_MONTH;
		gregorianCalendar.add(field, Integer.valueOf(applyTerm));
		Date endingDate = gregorianCalendar.getTime();

		// 利息
		BigDecimal rate = applyAmt.multiply(
				bizRate.divide(new BigDecimal("100")).divide(
						new BigDecimal("360"), 10, BigDecimal.ROUND_HALF_EVEN))
				.multiply(
						new BigDecimal((endingDate.getTime() - startDate
								.getTime()) / (1000 * 3600 * 24)));

		// 保险费率
		BigDecimal premiumRate = null;
		// 折扣
		BigDecimal disCount = null;

		if (guaranteeMode.contains(dataDict
				.getUniqueOne("CdsGuarantMode", "S1").getCodeValue())) {// 抵押
			premiumRate = insuranceCompanyDao.findOne(insuranceOrgId)
					.getPledgeRate();
			disCount = insuranceRebateDao.findUniqueOne(
					insuranceOrgId,
					dataDict.getUniqueOne("CdsGuarantMode", "S1")
							.getCodeValue(), applyTermUnit,
					Long.valueOf(applyTerm)).getDiscount();
			return applyAmt.add(rate)
					.multiply(premiumRate.divide(new BigDecimal("100")))
					.multiply(disCount.divide(new BigDecimal("100")));
		} else if (guaranteeMode.contains(dataDict.getUniqueOne(
				"CdsGuarantMode", "S2").getCodeValue())) {// 质押
			premiumRate = insuranceCompanyDao.findOne(insuranceOrgId)
					.getImpawnRate();
			disCount = insuranceRebateDao.findUniqueOne(
					insuranceOrgId,
					dataDict.getUniqueOne("CdsGuarantMode", "S2")
							.getCodeValue(), applyTermUnit,
					Long.valueOf(applyTerm)).getDiscount();
			return applyAmt.add(rate)
					.multiply(premiumRate.divide(new BigDecimal("100")))
					.multiply(disCount.divide(new BigDecimal("100")));
		} else if (guaranteeMode.contains(dataDict.getUniqueOne(
				"CdsGuarantMode", "S3").getCodeValue())) {// 保证
			premiumRate = insuranceCompanyDao.findOne(insuranceOrgId)
					.getAssureRate();
			disCount = insuranceRebateDao.findUniqueOne(
					insuranceOrgId,
					dataDict.getUniqueOne("CdsGuarantMode", "S3")
							.getCodeValue(), applyTermUnit,
					Long.valueOf(applyTerm)).getDiscount();
			return applyAmt.add(rate)
					.multiply(premiumRate.divide(new BigDecimal("100")))
					.multiply(disCount.divide(new BigDecimal("100")));
		} else if (guaranteeMode.contains(dataDict.getUniqueOne(
				"CdsGuarantMode", "S4").getCodeValue())) {// 信用
			premiumRate = insuranceCompanyDao.findOne(insuranceOrgId)
					.getCreditRate();
			disCount = insuranceRebateDao.findUniqueOne(
					insuranceOrgId,
					dataDict.getUniqueOne("CdsGuarantMode", "S4")
							.getCodeValue(), applyTermUnit,
					Long.valueOf(applyTerm)).getDiscount();
			return applyAmt.add(rate)
					.multiply(premiumRate.divide(new BigDecimal("100")))
					.multiply(disCount.divide(new BigDecimal("100")));
		}
		return null;
	}

	@Override
	public void checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode, String workFlowCode) throws Exception {
		
		// 检查基本信息是否已保存
		BizRate bizRate = bizRateDao.findByProjectId(projectId.toString());
		List<ApprovalHistoryRepayPlan> ahrps = approvalHistoryRepayPlanDao.findByProjectId(projectId);
		ProjectApplication pa = projectApplicationDao.findOne(projectId);
		if (bizRate == null || (CollectionUtils.isNotEmpty(ahrps) && !dataDict.getCodeVal("RepaymentMode", "S3").equals(pa.getRepayingMode()))) {
			throw new RuntimeException("请保存基本项目信息。");
		}
		//根据产品代码判断是不是农贷
		final boolean farmerLoanProductMark=this.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, pa.getProductType());//StringUtils.equals(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, pa.getProductType());
		//根据团结贷
		final boolean unityLoanProductMark=this.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, pa.getProductType());//StringUtils.equals(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, pa.getProductType());
		//农贷产品客户信息校验
		if(farmerLoanProductMark||unityLoanProductMark){
			this.checkCustomerInfoOfAgroLoan(pa.getPartyId(),pa.getProjectId());
		}

		// 获取此业务所有文档类型
		List<String> cusDocTypes = documentIndexDao.findCustDocTypesByBizId(projectId);
		if (cusDocTypes == null || cusDocTypes.size() == 0) {
			throw new RuntimeException("请上传文档。");
		}

		/** 易贷 */
		if (workFlowCode.equals(WorkFlowService.WorkFlowCode.EASY_LOAN
				.getCodeId())) {

			if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S44"))) {
				throw new RuntimeException("请上传申请表。");
			}

			else if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S46"))) {
				throw new RuntimeException("请上传银行按揭还款流水。");
			}

			else if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S47"))) {
				throw new RuntimeException("请上传收入证明。");
			}

			else if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S5"))) {
				throw new RuntimeException("请上传个人征信报告。");
			}

			else if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S49"))) {
				throw new RuntimeException("请上传房产证明。");
			}

			else if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S50"))) {
				throw new RuntimeException("请上传工作证明。");
			}

			if (monthDominateIncludeMeasureDao.findByProjectId(projectId) == null) {
				throw new RuntimeException("请保存月可支配收入测算。");
			}

			if (quotaMeasureDao.findByProjectId(projectId) == null) {
				throw new RuntimeException("请保存额度测算。");
			}

			List<InfoValues> infoValues = infoValuesDao.findBySubjectId(projectId);
			if (infoValues == null || infoValues.size() == 0) {
				throw new RuntimeException("请保存征信情况。");
			}

		}
		/** 微贷 */
		else if (workFlowCode.equals(WorkFlowService.WorkFlowCode.MICRO_LOAN
				.getCodeId())) {
			// 检查抵质押保证表、保证人保证表信息是否已保存
			List<ProjectPawnInfo> projectPawnInfos = null;
			guaranteeMode = "," + guaranteeMode;
			if (guaranteeMode.contains(","
					+ dataDict.getUniqueOne("CdsGuarantMode", "S1")
							.getCodeValue() + ",")) {// 抵押
				projectPawnInfos = projectPawnInfoDao
						.findOneGuaranteeTypeByProjectId(projectId, dataDict
								.getUniqueOne("CdsGuarantMode", "S1")
								.getCodeValue());
				if (projectPawnInfos == null || projectPawnInfos.size() == 0) {
					throw new RuntimeException("请关联抵押信息。");
				}
				
				if (!guaranteeMode.contains("," + dataDict.getUniqueOne("CdsGuarantMode", "S2").getCodeValue() + ",")) {
					projectPawnInfos = projectPawnInfoDao.findOneGuaranteeTypeByProjectId(projectId, dataDict.getUniqueOne("CdsGuarantMode", "S2").getCodeValue());
					if(!CollectionUtils.isEmpty(projectPawnInfos)) {
						throw new RuntimeException("担保方式没有质押，请删除质押信息。");
					}
				}
				
				
			}
			if (guaranteeMode.contains(","
					+ dataDict.getUniqueOne("CdsGuarantMode", "S2")
							.getCodeValue() + ",")) {// 质押
				projectPawnInfos = projectPawnInfoDao
						.findOneGuaranteeTypeByProjectId(projectId, dataDict
								.getUniqueOne("CdsGuarantMode", "S2")
								.getCodeValue());
				if (projectPawnInfos == null || projectPawnInfos.size() == 0) {
					throw new RuntimeException("请关联质押信息。");
				}
				
				if (!guaranteeMode.contains("," + dataDict.getUniqueOne("CdsGuarantMode", "S1").getCodeValue() + ",")) {
					projectPawnInfos = projectPawnInfoDao.findOneGuaranteeTypeByProjectId(projectId, dataDict.getUniqueOne("CdsGuarantMode", "S1").getCodeValue());
					if(!CollectionUtils.isEmpty(projectPawnInfos)) {
						throw new RuntimeException("担保方式没有抵押，请删除抵押信息。");
					}
				}

			}
			if (guaranteeMode.contains(","
					+ dataDict.getUniqueOne("CdsGuarantMode", "S3")
							.getCodeValue() + ",")) {// 保证
				List<ProjectAssurerInfo> projectAssurerInfos = projectAssurerInfoDao
						.findByProjectId(projectId);
				if (projectAssurerInfos == null
						|| projectAssurerInfos.size() == 0) {
					throw new RuntimeException("请关联保证人信息。");
				}

			}
			//add by HWL 20150812 start 团结贷身份证、户口本、婚姻状况必填
			if(unityLoanProductMark){
				//是否上传身份证
				if(!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S1"))){
					throw new ServiceException("请上传身份证！");
				}
				//是否上传户口本
				if(!cusDocTypes.contains(dataDict.getCodeVal(
						"CustProjectAllDocType", "S3"))){
					throw new ServiceException("请上传户口本！");
				}
				//已婚，则上传婚姻状况
				if(this.isMarriedOfCust(pa.getPartyId())&&!cusDocTypes.contains(dataDict.getCodeVal(
						"CustProjectAllDocType", "S4"))){
					throw new ServiceException("请上传婚姻状况！");
				}
				
			}
			//add by HWL 20150812 end 
			if(farmerLoanProductMark||unityLoanProductMark){
				 //农贷校验
				final String agroBizType=this.bizApplyQueryService.findAgroBizTypeByProjId(pa.getProjectId());
				if(StringUtils.equals("1", agroBizType)){//农业为主
					//家庭资产校验
					this.checkFamilyAsset(pa.getProjectId(),agroBizType);
					this.checkIncomeExpenditureOfAgro(pa.getProjectId(),"1");//是否填写过去的种植业养殖业信息
				}else if(StringUtils.equals("2", agroBizType)){//非农业为主
					//TODO 非农业为主没有校验
				}
				this.checkAgroLoan(pa.getProjectId());
				
			}else{
				// 检查财务信息是否保存
				SalaBusiCustFinalcial salaBusiCustFinalcial = salaBusiCustFinalcialDao
						.findByProjectId(projectId);
				if (salaBusiCustFinalcial == null) {
					throw new RuntimeException("请保存财务信息。");
				}
			}

			// 检查贷款申请表、尽调报告是否上传
			if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S44"))) {
				throw new RuntimeException("请上传贷款申请表。");
			}

			else if (!cusDocTypes.contains(dataDict.getCodeVal(
					"CustProjectAllDocType", "S45"))) {
				throw new RuntimeException("请上传尽调报告。");
			}

			// List<DocumentIndex> applyTables = documentIndexDao
			// .findByBizIdAndDocumentTypeAndStatus(projectId,
			// dataDict.getCodeVal("DocumentType", "S06"), "1");
			// if (applyTables == null || applyTables.size() == 0)
			// throw new RuntimeException("请上传贷款申请表。");
		}
	}

	private void checkAgroLoan(Long projectId) {
		ProjectApplication pa =this.projectApplicationDao.findOne(projectId);
		//申请金额5万元（含）~10万元的借款人需至少1名自然人作为担保人 双方不能互保
		if(new BigDecimal(50000).compareTo(pa.getApplyAmt())<0&&
				new BigDecimal(100000).compareTo(pa.getApplyAmt())>=0){
			//1.在本地生产、经营或工作时间不少于2年，即“从事本行业年份”必须大于2年；
			Individual cust =this.individualDao.findOne(pa.getPartyId());
			if(cust.getYearsInIndustry()==null){
				throw new RuntimeException("客户信息中从事本行业年份错误！请更新...");
			}
			if(this.isYearsInIndustryLtEq2(cust.getYearsInIndustry().toString())){
				throw new RuntimeException("在本地生产、经营或工作时间少于2年");
			}
			//若婚姻状况为未婚/离异/丧偶，则需追加共同借款人
			if(dataDict.getCodeValList("MaritalStatus", "S10","S30","S40").contains(cust.getMarriageCd())){//未婚/离异/丧偶
				String commonLoanJpql="from CommonBorrow cb where cb.projectId=?1 and cb.flag = ?2";
				Long count=this.dynamicQuery.queryCount(commonLoanJpql, projectId,"1");
				if(count<1){
					throw new RuntimeException("婚姻状况为未婚/离异/丧偶，请追加共同借款人！");
				}
			}
			//需至少1名自然人作为担保人 双方不能互保
			StringBuffer sb=new StringBuffer();
			sb.append("select p.party_id");
			sb.append(" from PROJECT_ASSURER_INFO pai");
			sb.append(" left join party p");
			sb.append(" on p.party_id = pai.party_id");
			sb.append(" where pai.project_id = ?1");
			sb.append(" and pai.party_id in");
			sb.append(" (select pa.party_id");
			sb.append(" from PROJECT_ASSURER_INFO pai");
			sb.append(" left join project_application pa");
			sb.append(" on pa.project_id = pai.project_id");
			sb.append(" left join contract c on c.project_id=pai.project_id");  
			sb.append(" where pai.party_id = ?2 and c.contract_status_cd != 422)");//贷款不是已结清
			Long count=this.dynamicQuery.nativeQueryCount(sb.toString(), projectId,pa.getPartyId());
			if(count>0){
				throw new RuntimeException("贷款人与担保人双方不能互保，请修改关联担任人信息！");
			}
			sb.setLength(0);
			sb.append("select p.party_id");
			sb.append(" from PROJECT_ASSURER_INFO pai");
			sb.append(" left join party p");
			sb.append(" on p.party_id = pai.party_id");
			sb.append(" where pai.project_id = ?1");
			sb.append(" and p.party_type_cd = 2");//个人做担保人 
			count=this.dynamicQuery.nativeQueryCount(sb.toString(), projectId);
			if(count<1){
				throw new RuntimeException("请至少添加1位自然人，作为担保人！");
			}
		}
		
	}
	private boolean isYearsInIndustryLtEq2(String yearsInIndustry){
		Calendar c=Calendar.getInstance();
		int i_yearsInIndustry=0;
		try {
			i_yearsInIndustry=Integer.parseInt(yearsInIndustry);
		} catch (NumberFormatException e) {
			throw new RuntimeException("客户信息中从事本行业年份错误！请更新...");
		}
		return c.get(Calendar.YEAR)-i_yearsInIndustry<2;
		
	}
	/**
	 * 农贷收入支出情况校验
	 * @param projectId
	 * @param agroBizType
	 */
	private void checkIncomeExpenditureOfAgro(Long projectId, String futurePastType) {
		long count=0;
		//种植业或养殖业信息校验
		String caJpql="from CultivateAgriculture ca where ca.projectId=?1 and ca.type=?2";
		count+=dynamicQuery.queryCount(caJpql, projectId,futurePastType);
		String baJpql="from BreedAgriculture ba where ba.projectId=?1 and ba.type=?2";
		count+=dynamicQuery.queryCount(baJpql, projectId,futurePastType);
		if(count<1){
			throw new RuntimeException("请填写收入支出情况中的种植业或养殖业信息（二者必填其一）！");
		}
		//年家庭生活消费判断
		String fcJpql=" from FamilyConsume fc where fc.projectId=?1 and fc.type=?2";
		count=dynamicQuery.queryCount(fcJpql, projectId,"1");//农业为主
		if(count<1){
			throw new RuntimeException("请填写收入支出情况中的年家庭生活消费！");
		}
	}

	/**
	 * 农贷家庭资产校验
	 * @param projectId
	 * @param agroBizType
	 */
	private void checkFamilyAsset(Long projectId, String agroBizType) {
		long count=produceAreaInfoDao.findCountByProjId(projectId, agroBizType);
		if(count<1){
			throw new RuntimeException("请填写家庭资产中生产区域信息（至少一条）");
		}
		
	}

	/**
	 * 农贷客户信息校验
	 * @param partyId
	 */
	private void checkCustomerInfoOfAgroLoan(Long partyId,Long projectId) {
		Individual cust=this.individualDao.findOne(partyId);
		if(cust==null){
			throw new RuntimeException("请填写客户信息");
		}
		if(StringUtils.isBlank(cust.getInhabitancyStatus())||
				cust.getLivingArea()==null||StringUtils.isBlank(cust.getFamilyAddress())
				||StringUtils.isBlank(cust.getAppliancesCase())
				||cust.getFamilySize()==null
				||cust.getWorkForce()==null
				||cust.getDependentPopulation()==null
				||cust.getDependentPopulationRate()==null){
			throw new RuntimeException("请填写客户信息中的必填信息");
		}
		if(StringUtils.equals(cust.getMarriageCd(), dataDict.getCodeVal("MaritalStatus", "S20"))){//已婚
			if(StringUtils.isBlank(cust.getSpouseCase())
					||StringUtils.isBlank(cust.getSpouseEmployment())||
					StringUtils.equals(dataDict.getCodeVal("SpouseCase", "S3"),cust.getSpouseCase())
					||StringUtils.equals(dataDict.getCodeVal("SpouseEmployment", "S6"),cust.getSpouseEmployment())){
				throw new RuntimeException("请填写客户信息中的配偶信息");
			}
		}
		//至少有两名核实人
		//modify by HWL at 2015-06-03 start
		//核实人暂定为和客户有关
		/*boolean veriPersonFlag=verificatPersonInfoDao.findVeriPersonListCount(projectId)<2;*/
		final boolean veriPersonFlag=verificatPersonInfoDao.findVeriPersonListCountByPartyId(partyId)<2;
		if(veriPersonFlag){
			throw new RuntimeException("请填写至少两名核实人信息");
		}
		//modify by HWL at 2015-06-03 end
	}

	@Override
	public void saveNewCommonBorrower(NewCommonBorrowerVO newCommonBorrowerVO,
			CustomerManagerTeam customerManagerTeam) {
		String customerNum = commonBizNumberBS.generateCustomerNumber(
				dataDict.getCodeVal("CustomerType", "S2"),
				dataDict.getCodeVal("CertificateType", "S100"),
				newCommonBorrowerVO.getCertificateNum());
		// save party
		Party party = new Party();
		party.setCustomerNum(customerNum);
		party.setPartyName(newCommonBorrowerVO.getCustomerName());
		party.setPartyTypeCd(dataDict.getCodeVal("CustomerType", "S2"));
		party.setCustomerSource("2");// 微贷系统录入的客户标识
		party.setSysUpdateTime(new Date());
		party.setCertificateNum(newCommonBorrowerVO.getCertificateNum());
		party.setCertificateTypeCd(dataDict.getCodeVal("CertificateType",
				"S100"));
		party.setStatus(dataDict.getCodeVal("CustomerStatusCode", "S1"));
		partyDao.save(party);

		// save customerManagerTeam
		customerManagerTeam.setPartyId(party.getPartyId());
		customerManagerTeam.setCustomerNum(party.getCustomerNum());
		customerManagerTeamDao.save(customerManagerTeam);

		// save individual
		Individual individual = new Individual();
		BeanUtils.copyProperties(newCommonBorrowerVO, individual);
		individual.setPartyId(party.getPartyId());
		individual.setCustomerNum(customerNum);
		individual.setCertificateTypeCd(dataDict.getCodeVal("CertificateType",
				"S100"));
		individual.setStatus(dataDict.getCodeVal("CustomerStatusCode", "S1"));
		individual.setCreateDate(new Date());
		individual.setSysUpdateTime(new Date());
		individual.setMarkType(dataDict.getCodeVal("CustomerMarkType", "S01"));// 前期针对邦易贷产品客户默认为借款人
		individual.setProfessionalLevelCd(dataDict.getCodeVal("CustomerTypeCG",
				"S2"));// 客户类型默认是个体工商
		individual.setNationalityCd("156");
		customerIndividualDao.save(individual);

		// save commonBorrow
		CommonBorrow commonBorrow = new CommonBorrow();
		BeanUtils.copyProperties(individual, commonBorrow);
		commonBorrow.setMobilePhone(individual.getMobileTel());
		commonBorrow.setPhone(individual.getFamilyTel());
		commonBorrow.setAddress(individual.getCompanyAddress());
		commonBorrow.setProjectId(newCommonBorrowerVO.getProjectId());
		commonBorrow.setFlag("1");
		commonBorrow.setSpouseflag("2");
		commonBorrowDao.save(commonBorrow);
	}

	@Override
	public Page findDocumentIndexBySearch(int pageNumber, Integer pageSize,
			List<Object> params) {
		String nativesql = "select t.party_id,t.document_name, t.cust_doc_type, t.document_type,t.customer_num,co.name,t.create_date_time,t.create_type_cd,t.document_id  "
				+ " from DOCUMENT_INDEX t left JOIN EC_ORG_PERSON co ON co.id = t.create_user_num "
				+ " where ((t.party_id = ?1 AND t.document_type = '01')  "
				+ " or (t.biz_id = ?2 AND t.document_type in ('02','06','45')))  ";
		List<Object> paramsVar = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(nativesql);
		paramsVar.add(params.get(0));
		paramsVar.add(params.get(1));
		// sql.append(" and t.document_type in " +
		// String.valueOf(params.get(2)));
		if (!params.isEmpty()) {
			sql.append("and t.status = '1' ");
			sql.append("and t.cust_doc_type is not null  ");
			if (params.size() > 2) {
				// sql.append("and ( 1=1 OR t.biz_id = ?4) ");
				sql.append(" and (t.document_name like ?3 or ?4 is null) ");
				paramsVar.add("%" + params.get(2) + "%");
				paramsVar.add(params.get(2));

				if (StringUtils.isNotBlank((String) params.get(3))) {
					List<String> custDocValues = documentService
							.findCustDocTypeNames((String) (params.get(3)));
					if (CollectionUtils.isNotEmpty(custDocValues)) {
						sql.append(" and t.cust_doc_type in (?5) ");
						paramsVar.add(custDocValues);
					} else {
						sql.append(" and t.cust_doc_type in ('') ");
					}
				}
			}

			sql.append(" order by t.all_doc_type desc nulls last ,t.create_date_time desc ");
		}
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), paramsVar.toArray());
	}

	@Override
	public MonthDominateIncludeMeasure saveMeasure(MonthDominateIncludeMeasure form) {
		monthDominateIncludeMeasureDao.save(form);
		return form;
	}

	@Override
	public MonthDominateIncludeMeasure getMeasureByProjectId(Long projectId) {
		return monthDominateIncludeMeasureDao.findByProjectId(projectId);
	}

	@Override
	public FamilyAssetsDetail saveFamilyAssets(FamilyAssetsDetail form) {
		familyAssetsDetailDao.save(form);
		return form;
	}

	@Override
	public FamilyAssetsDetail getFamilyAssetsByProjectId(Long projectId) {
		return familyAssetsDetailDao.findByProjectId(projectId);
	}

	@Override
	public List<String> findDocumentCustDocTypes(Long partyId, Long projectId) {
		String jpql = "SELECT DISTINCT t.custDocType FROM DocumentIndex t WHERE "
				+ " ((t.partyId = ?1 AND t.documentType = '01') "
				+ "  OR (t.bizId = ?2 AND t.documentType in ('02', '06', '45'))) "
				+ " AND t.status = 1 " + " and t.custDocType is not null ";
		return (List<String>) dynamicQuery.query(jpql, partyId, projectId);
	}

	@Override
	public BigDecimal countRateByFloatRate(Integer applyTerm,
			String applyTermUnit, BigDecimal floatRate) {
		Integer timeLimit = applyTerm;
		int limitMonth = 0;
		if (dataDict.getCodeVal("TermUnitCd", "S1").equals(applyTermUnit)) { // 年
			limitMonth = timeLimit * 12;
		} else if (dataDict.getCodeVal("TermUnitCd", "S2")
				.equals(applyTermUnit)) { // 月
			limitMonth = timeLimit;
		} else if (dataDict.getCodeVal("TermUnitCd", "S3")
				.equals(applyTermUnit)) {
			limitMonth = timeLimit / 30;
		}

		if (limitMonth <= 6) { // 六个月以内(含6个月)
			timeLimit = Integer.parseInt(dataDict.getCodeVal("TimeLimitType",
					"S1"));
		} else if (limitMonth > 6 && limitMonth <= 12) { // 六个月至一年(含一年)
			timeLimit = Integer.parseInt(dataDict.getCodeVal("TimeLimitType",
					"S2"));
		} else if (limitMonth > 12 && limitMonth <= 36) { // 一至三年(含三年)
			timeLimit = Integer.parseInt(dataDict.getCodeVal("TimeLimitType",
					"S3"));
		} else if (limitMonth > 36 && limitMonth <= 60) { // 三至五年(含五年)
			timeLimit = Integer.parseInt(dataDict.getCodeVal("TimeLimitType",
					"S4"));
		} else if (limitMonth > 60) { // 五年以上
			timeLimit = Integer.parseInt(dataDict.getCodeVal("TimeLimitType",
					"S5"));
		}
		List<MoneyRate> moneyRates = moneyRateDao.findListByTimeLimit(timeLimit
				.toString());
		BigDecimal interestRate = moneyRates.get(0).getInterestRate();
		BigDecimal bizRate = interestRate.multiply(
				floatRate.divide(new BigDecimal("100"))
						.add(new BigDecimal("1"))).multiply(
				new BigDecimal("100"));
		return bizRate;
	}

	@Override
	public void checkPartyPropsWhenEasyLoan(Individual individual, ProductConfig productConfig) throws Exception {
		// 检查此客户是否为授薪或经营类客户，若不是，则不允许发起易贷业务
		if (individual.getEmploymentType() != null && !dataDict.getCodeVal("EmploymentType", "S1").equals(individual.getEmploymentType()) && !dataDict.getCodeVal("EmploymentType", "S3").equals(individual.getEmploymentType())) {
			throw new RuntimeException("employmentType");
		}
		
		/**
		 * 房易贷业务
		 */
		if (productConfig.getProductCd() == 343) {
			// 1、应至少添加儿女联系方式（如有）
			if (StringUtils.isNotBlank(individual.getChiledNum()) && Integer.parseInt(individual.getChiledNum()) > 0) {
				Integer childNum = familyFriendDao.countChildsByPartyId(individual.getPartyId());
				if (childNum == null || childNum == 0) {
					throw new RuntimeException("childNum");
				}
			}
			// 2、除配偶/儿女外三位亲属
			Integer relationsExceptSpouseAndChilds = familyFriendDao.countRelationsExceptSpouseAndChildsByPartyId(individual.getPartyId());
			if (relationsExceptSpouseAndChilds == null || relationsExceptSpouseAndChilds == 0) {
				throw new RuntimeException("relationsNum");
			}
			// 3、三位非亲属
			Integer nonRelations = familyFriendDao.countNonRelationsByPartyId(individual.getPartyId());
			if (nonRelations == null || nonRelations == 0) {
				throw new RuntimeException("nonRelationsNum");
			}
		}
	}

	@Override
	public void deleteRepayPlanByProjectNo(String projectNo) {
		repayPlanDao.deleteByProjectNo(projectNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchCalRepaymentPlanList(Integer pageNumber,
			Integer pageSize) {
		String sql = "select to_char(r.repay_date,'yyyy-mm-dd'),r.repay_amt,r.repay_plan_id from "
				+ "REPAY_PLAN r where r.project_no is null order by r.repay_date asc";
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString());
	}

	@Override
	public void deleteCalRepaymentPlan() {
		String sql = "delete from REPAY_PLAN_TEMP r where r.project_no is null";
		dynamicQuery.nativeExecuteUpdate(sql);
	}

	@Override
	public void deleteRepayPlanByProjectId(Long projectId) {
		repayPlanDao.deleteByProjectId(projectId);
	}

	private class TimeLimitUtil {
		private final int TIMELIMIT1 = 1;
		private final int TIMELIMIT2 = 2;
		private final int TIMELIMIT3 = 3;
		private final int TIMELIMIT4 = 4;
		private final int TIMELIMIT5 = 5;

		private Integer decideTimeLimit(Integer term) {
			if (isTimeLimit1(term))
				return TIMELIMIT1;
			else if (isTimeLimit2(term))
				return TIMELIMIT2;
			else if (isTimeLimit3(term))
				return TIMELIMIT3;
			else if (isTimeLimit4(term))
				return TIMELIMIT4;
			else if (isTimeLimit5(term))
				return TIMELIMIT5;
			else
				return null;
		}

		private boolean isTimeLimit1(Integer term) {
			int min = 0;
			int max = 6 * GlobalConstants.MONTH_TO_DAY;
			return term > min && term <= max;
		}

		private boolean isTimeLimit2(Integer term) {
			int min = 6 * GlobalConstants.MONTH_TO_DAY;
			int max = 1 * GlobalConstants.YEAR_TO_DAY;
			return term > min && term <= max;
		}

		private boolean isTimeLimit3(Integer term) {
			int min = 1 * GlobalConstants.YEAR_TO_DAY;
			int max = 3 * GlobalConstants.YEAR_TO_DAY;
			return term > min && term <= max;
		}

		private boolean isTimeLimit4(Integer term) {
			int min = 3 * GlobalConstants.YEAR_TO_DAY;
			int max = 5 * GlobalConstants.YEAR_TO_DAY;
			return term > min && term <= max;
		}

		private boolean isTimeLimit5(Integer term) {
			int min = 5 * GlobalConstants.YEAR_TO_DAY;
			return term > min;
		}
	}

	@Override
	public void isMarried(Long partyId) throws Exception {
		Party party = partyDao.findByPartyId(partyId);
		if ("1".equals(party.getPartyTypeCd())) {// 企业客户
			throw new RuntimeException();
		}
		Individual individual = individualDao.findOne(partyId);
		if (!dataDict.getCodeVal("MaritalStatus", "S20").equals(
				individual.getMarriageCd())) {
			throw new RuntimeException();
		}
	}
	/**
	 * 获取微贷事业部管理人员角色
	 * 
	 */
	@Override
	public String findAdminRoles() {
		String sql = "select scp.standby_desc1 from system_common_parameters scp where SYSTEM_COMMON_PARAMETER_TYPE = '03' and SYSTEM_COMMON_PARAMETER_KEY = 'WDSearchAuthConfig'";
		return dynamicQuery.nativeQuerySingleResult(String.class, sql);
	}

	@Override
	public boolean validateCustomerProjectInfo(String partyId, String businessProcessType, String contractNum) {
		List paramVar = Lists.newArrayList();
		int paramIndex = 1;
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT nvl(count(*),0) AS count " +
				   "FROM project_application pa " +
				   "WHERE 1=1 ");
		
		if(StringUtils.isNotBlank(businessProcessType)){
			sql.append("AND pa.business_process_type = ?" + paramIndex++ +" ");
			paramVar.add(businessProcessType);
		}
		sql.append(" AND pa.project_status IN (?"+paramIndex++ +") ");
		paramVar.add(dataDict.getCodeValList("ProjectStatus", "S0","S1","S2"));
		sql.append(" AND pa.party_id = ?"+ paramIndex++ +" ");
		paramVar.add(new Long(partyId));
		if(StringUtils.isNotBlank(contractNum)){
			sql.append("AND pa.init_contract_num = ?"+ paramIndex++ +" ");
			paramVar.add(contractNum);
		}
		BigDecimal count =  dynamicQuery.nativeQuerySingleResult(BigDecimal.class, sql.toString(), paramVar.toArray());
		if(count.longValue()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Result validateRate(Long productCd, Long orgId, Integer applyTerm, String applyTermUnit, BigDecimal rate) {
		ProductConfig p = productConfigDao.findByProductCdAndOrgId(productCd, orgId);
		if (p.getRateMultiple() != null) {
			// 得到前台利率
			rate = rate.divide(new BigDecimal("100"));
			Integer term = null;
			// 判断期限单位，计算前台期限（单位：日）
			if (dataDict.getCodeVal("TermUnitCd", "S1").equals(applyTermUnit)) {// 年
				term = applyTerm * GlobalConstants.YEAR_TO_DAY;
			} else if (dataDict.getCodeVal("TermUnitCd", "S2").equals(applyTermUnit)) {// 月
				term = applyTerm * GlobalConstants.MONTH_TO_DAY;
			} else {// 日
				term = applyTerm;
			}
			// 获取央行基准利率
			MoneyRate moneyRate = moneyRateDao.findListByTimeLimit(
					this.new TimeLimitUtil().decideTimeLimit(term).toString())
					.get(0);
			//modify by wangyawei 20150429 start 利率比较时先保留6位小数
			//	获取央行基准利率4倍利率值
			BigDecimal interestRate = moneyRate.getInterestRate().multiply(new BigDecimal(p.getRateMultiple()));
			int flag = rate.setScale(6).compareTo(interestRate.setScale(6));
			
			if (flag > 0) {
				String message = "利率值必须小于或等于央行基准利率的定值倍数，请咨询管理员";
				//String message = "利率值必须小于或等于央行基准利率的" + p.getRateMultiple() + "倍，利率值为：" + rate.multiply(new BigDecimal("100")).setScale(2) + "%，央行基准利率的" + p.getRateMultiple() + "倍为：" + interestRate.multiply(new BigDecimal("100")) + "%";
				return new Result(Boolean.FALSE, message, null);
			}
			//modify by wangyawei 20150429 end
		}
		return new Result(Boolean.TRUE);
	}

	@Override
	public Page<Object[]> searchRepayPlanTempList(Integer pageNumber,
			Integer pageSize, String projectNo) {
		String sql = "select to_char(r.repay_date,'yyyy-mm-dd'),r.repay_amt,r.id from "
				+ "REPAY_PLAN_TEMP r where r.project_no = ?1 order by r.repay_date asc";
		List<Object> params = new ArrayList<Object>();
		if(null == projectNo){
			sql = "select to_char(r.repay_date,'yyyy-mm-dd'),r.repay_amt,r.id from "
					+ "REPAY_PLAN_TEMP r where r.project_no is null order by r.repay_date asc";
		}else{
			params.add(projectNo);
		}
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@Override
	public RepayPlanTemp getRepayPlanTemp(Long id) {
		return repayPlanTempDao.findOne(id);
	}

	@Override
	public void deleteRepayPlanTemp(Long id) {
		repayPlanTempDao.delete(id);
	}

	@Override
	public void saveRepayPlanTemp(RepayPlanTemp form) {
		repayPlanTempDao.save(form);
	}

	@Override
	public List<CollateralVO> searchProjectPawnInfoList(String guaranteeMode,Long projectId) {
		//先判断是否是抵质押物
		if (this.isCollateral(guaranteeMode)) {//是，则查询
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>(0);

			sb.append("select p.eval_value/10000,p.pawn_ratio*100,c.right_certification_num ");
			sb.append("from project_pawn_info p ");
			sb.append("inner join collateral c on p.guaranty_num = c.guaranty_num ");
			sb.append("where p.pawn_state in ('1','2') ");
			// 业务编号
			sb.append(" and p.project_id =?1");
			params.add(projectId);
			return Lists.transform(dynamicQuery.nativeQuery(Object[].class,
					sb.toString(), params.toArray()),
					new Function<Object[], CollateralVO>() {
						@Override
						public CollateralVO apply(Object[] objs) {
							return new CollateralVO(objs);
						}
					});
		} else {//否，不查询
			return null;
		}
	}
	/**
	 * 判断担保方式是否有抵质押物类型
	 * @param guaranteeMode 担保方式
	 * @return boolean
	 */
	private boolean isCollateral(String guaranteeMode){
		List<String> list = Arrays.asList(guaranteeMode.split(","));
		boolean flag = false;
		if (list.size() > 0 && (list.contains("1") || list.contains("2"))) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public void checkApplyAmountIsAvailable(BigDecimal applyAmount,
			Long productCd, Long orgId) {
		// 批量额度
		ProductConfig pc = productConfigDao.findByProductCdAndOrgId(productCd, orgId);
		// 是否是批量：1表示是批量额度
		if (pc != null && ("1").equals(pc.getIsBatch())) {
			// 已用额度
			BigDecimal usedAmount = contractDao.getUsedAmountOfProduct(
					String.valueOf(productCd), orgId, this.getContractStatusValStr());
			// 可用额度
			BigDecimal availableAmount = pc.getBatchLimit()
					.subtract(usedAmount);
			// 申请额度
			BigDecimal amount = applyAmount;
			//判断申请金额是否大于可用金额
			if (amount.compareTo(availableAmount) == 1) {
				//大于：不可用
				throw new RuntimeException("此产品可用额度不足！批量额度为" + pc.getBatchLimit() + "，可用额度为：" + availableAmount);
			} 
		}
	}
	
	/**
	 * 得到合同状态编号的list列表
	 * @return
	 */
	private List<String> getContractStatusValStr(){
		Collection<CodeData> contractStatusList = dataDict.getOneType("ContractStatusCode");
		Iterator<CodeData> its = contractStatusList.iterator();
		List<String> status = Lists.newArrayList();
		while (its.hasNext()) {
			CodeData codeData = (CodeData) its.next();
			//合同状态等于'已放款','已逾期'
			if(codeData.getCodeValue().equals("316") || codeData.getCodeValue().equals("330")){
				status.add(codeData.getCodeValue());
			}
		}
		return status;
	}
	
	
	/***
	 * 获取某种贷款产品业务的标识
	 * 
	 * @param productCd 贷款产品ID
	 * @param productConfigName 贷款产品配置名称（在GlobalConstants中配置的名称）
	 * @return true:贷款产品业务； false:其他业务
	 * @author wangyawei
	 * @date 2015-07-02
	 */
	public boolean getProductLoanFlag(String productConfigName, String productCd){
		List<String> productConfigNameLists = Arrays.asList(productConfigName.split(","));
		return productConfigNameLists.contains(productCd);
		/*boolean flag  = Boolean.FALSE;
		 * for (String farmerLoanProductMark : Arrays.asList(productConfigName.split(","))) {
			if(farmerLoanProductMark.equals(productCd)) {
				flag = Boolean.TRUE;
				break;
			}
		}
		return flag;*/
	}
	
	
	/**
	 * 客户是否已婚
	 * @param partyId
	 * @return true 已婚，false：未婚、离异、丧偶等
	 */
	public boolean isMarriedOfCust(Long partyId){
		Party party = partyDao.findByPartyId(partyId);
		if (!StringUtils.equals("2", party.getPartyTypeCd())) {// 企业客户
			throw new ServiceException("该客户不是个人客户，无法判断是否已婚");
		}
		Individual individual = individualDao.findOne(partyId);
		return StringUtils.equals(dataDict.getCodeVal("MaritalStatus", "S20"), individual.getMarriageCd());
	}
}
