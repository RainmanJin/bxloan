package com.coamctech.bxloan.service.creditcontractmng.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
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
import com.coamctech.bxloan.dao.SubContractDao;
import com.coamctech.bxloan.dao.TbMultiopinionDao;
import com.coamctech.bxloan.dao.VerificatPersonInfoDao;
import com.coamctech.bxloan.dao.WorkDraftDao;
import com.coamctech.bxloan.dao.credit.CreditContractDao;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.ChargeInfo;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.creditcontractmng.UnderCreditContractMngService;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
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
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

@Transactional
@Service
public class UnderCreditContractMngServiceImpl implements UnderCreditContractMngService{

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
	@Autowired
	EcOrgPersonService ecOrgPersonService;
	@Autowired
	private SubContractDao subContractDao;
	@Autowired
	private CreditContractDao creditContractDao;
	@Autowired
	private ContractMngService contractMngService;
	
	
	/** ***********授信下借款申请所用方法************ */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findUnderCreditExpenseRateByProject(String underProjectNo,String creditProjectNo,
			Integer pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer sql = new StringBuffer("select c.code_name expense_collection_type,d.code_name expense_name,");
		sql.append("b.standard_expense_rate,b.expense_rate,b.standared_amt,b.expense_amt,to_char(b.sys_create_date,'yyyy-MM-dd'),");
		sql.append("b.biz_expense_rate_id, b.project_no from biz_expense_rate b ");
		sql.append("join code c on b.expense_collection_type=c.code_value and c.code_type='FeeUsedType' ");
		sql.append("join code d on b.expense_name=d.code_value and d.code_type='FeeType' where b.project_no in (?1,?2 )");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}
	/**
	 * @author lbq 2015-8-17
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchBailList(Integer pageNumber, Integer pageSize,
			Long parentProjectId, Long businessId) {
		StringBuffer sql = new StringBuffer("select p.customer_name,c.code_name certificate_type_cd,p.certificate_num,'人民币' currency,");
		sql.append("p.guarantee_amt,p.project_assurer_info_id ,p.project_id from PROJECT_ASSURER_INFO p ");
		sql.append("join code c on c.code_value = p.certificate_type_cd and c.code_type = 'CertificateType'");
		sql.append("where p.project_id in( ?1,?2 )");
		List<Object> params = new ArrayList<Object>();
		params.add(parentProjectId);
		params.add(businessId);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}
	/**
	 * @author lbq 2015-8-17
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchCommonBorrowerList(Integer pageNumber,
			Integer pageSize, Long creditProjectId, Long underProjectId) {
		StringBuffer sql = new StringBuffer("select cb.customer_name,c.code_name certificate_type_cd,cb.certificate_num,cb.mobile_phone,");
		sql.append("cb.phone,cb.address,cb.common_borrow_id,cb.spouseflag,cb.project_id from COMMON_BORROW cb ");
		sql.append("left join code c on c.code_value=cb.certificate_type_cd and c.code_type='CertificateType' ");
		sql.append("where cb.project_id in( ?1,?2 ) and cb.flag = '1'");
		List<Object> params = new ArrayList<Object>();
		params.add(creditProjectId);
		params.add(underProjectId);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}
	/**
	 * @author lbq 2015-8-17
	 */
	@Override
	public Page findDocumentIndexBySearch(int pageNumber, Integer pageSize,
			List<Object> params) {
		StringBuffer nativesql = new StringBuffer("select t.party_id,t.document_name, t.cust_doc_type, t.document_type,t.customer_num,co.name,t.create_date_time,t.create_type_cd,t.document_id,t.biz_id  ");
		nativesql.append(" from DOCUMENT_INDEX t left JOIN EC_ORG_PERSON co ON co.id = t.create_user_num ");
		nativesql.append(" where ((t.party_id = ?1 AND t.document_type = '01') ");
		nativesql.append(" or (t.biz_id in( ?2,?3 ) AND t.document_type in ('02','06','45'))) ");
		List<Object> paramsVar = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(nativesql);
		paramsVar.add(params.get(0));
		paramsVar.add(params.get(1));
		paramsVar.add(params.get(4));
		
		if (!params.isEmpty()) {
			sql.append("and t.status = '1' ");
			sql.append("and t.cust_doc_type is not null  ");
			if (params.size() > 2) {
				sql.append(" and (t.document_name like ?4 or ?5 is null) ");
				paramsVar.add("%" + params.get(2) + "%");
				paramsVar.add(params.get(2));

				if (StringUtils.isNotBlank((String) params.get(3))) {
					List<String> custDocValues = documentService
							.findCustDocTypeNames((String) (params.get(3)));
					if (CollectionUtils.isNotEmpty(custDocValues)) {
						sql.append(" and t.cust_doc_type in (?6) ");
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
	/**
	 * @author lbq 2015-8-17
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchUCProjectPawnInfoList(Integer pageNumber,
			Integer pageSize, Long creditProjectId,Long underProjectId) {
		StringBuffer sql = new StringBuffer("select t.guaranty_name,t.guarantor_name,t.eval_value,t.currency,");
		sql.append("t.app_gua_debt_inter_amt,t.set_guarantee_amt,t.pawn_ratio * 100 pawn_ratio,");
		sql.append("d.code_name guaranty_type_cd,c.code_name pawn_state,t.project_pawn_info_id ,t.project_id from PROJECT_PAWN_INFO t ");
		sql.append("left join code c on c.code_value=t.pawn_state and c.code_type='PawnState' ");
		sql.append("left join code d on d.code_value=t.guaranty_type_cd and d.code_type='ResvAssetTypeCode' ");
		sql.append("where t.project_id in (?1,?2)");
		List<Object> params = new ArrayList<Object>();
		params.add(creditProjectId);
		params.add(underProjectId);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}
	@Override
	public Page findCollateralsAllBySearch(Long businessId,Long parentProjectId ,Integer pageNumber,
			Integer pageSize) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select b from ProjectPawnInfo b where b.projectId in( ?1,?2) ");
		jpql.append("ORDER BY b.projectPawnInfoId DESC ");
		return dynamicQuery.query(new PageRequest(pageNumber - 1, pageSize),
				jpql.toString(), businessId,parentProjectId);
	}
	//保存业务申请信息
	@Override
	public Long saveUnderCreditLoanBusiness(BusinessApplicationWdVO form){
		
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
		if (p!=null){
			
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
		}

		/**
		 * 业务申请表
		 */
		ProjectApplication projectApplication = projectApplicationDao.findOne(form.getProjectId());
		BeanUtils.copyProperties(form, projectApplication);
		projectApplication.setApproveAmt(form.getApplyAmt());
		try {
			projectApplication.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").parse(form.getApplyDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		projectApplication.setSysUpdateTime(new Date());
		projectApplication.setTerm(form.getApplyTerm());
		projectApplication.setTermUnit(form.getApplyTermUnit());
		if (dataDict.getCodeVal("InterestRateAdjustment", "S1").equals(
				form.getIrTypeCd())) {// 固定利率
			projectApplication.setBizRate(form.getBizRate().divide(new BigDecimal("100")));
		} else {// 浮动利率
			projectApplication.setBizRate(form.getBizRate().divide(new BigDecimal("100")));
		}

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

		projectApplication.setPreRepaymentRate(form.getPreRepaymentRate().divide(new BigDecimal("100")));
		projectApplication.setCurrency("156");
		projectApplication.setMateBorrower(projectApplication.getMateBorrower().substring(0, 1));
		
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
				bizRate.setRateValue(form.getBizRate().divide(
						new BigDecimal("100")));
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
				bizRate.setRateValue(
						form.getBizRate().divide(
						new BigDecimal("100")));
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
	 * 功能：组装费用列表 说明：
	 * 
	 */
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
	public void checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode, String workFlowCode) throws Exception {
			// 检查基本信息是否已保存
			BizRate bizRate = bizRateDao.findByProjectId(projectId.toString());
			List<ApprovalHistoryRepayPlan> ahrps = approvalHistoryRepayPlanDao.findByProjectId(projectId);
			ProjectApplication pa = projectApplicationDao.findOne(projectId);
		if (bizRate == null || (CollectionUtils.isNotEmpty(ahrps) && !dataDict.getCodeVal("RepaymentMode", "S3").equals(pa.getRepayingMode()))) {
			throw new RuntimeException("请保存基本项目信息。");
		}
			// 获取此业务所有文档类型
			List<String> cusDocTypes = documentIndexDao.findCustDocTypesByBizId(projectId);
		if (cusDocTypes == null || cusDocTypes.size() == 0) {
			throw new RuntimeException("请上传文档。");
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
			
	}
	@Override
	public void copyPawnInfoToSubContract(Long projectId,Long parentProjectId,SessionUser sessionUser) {
		SubContract subcontract = null;
		String subcontractNum = "";
		//授信下合同信息
		Contract UCCreditContract = contractMngService.findContractByProjectId(projectId);
		//查询抵质押列表
		List<ProjectPawnInfo> pawnList = projectPawnInfoDao.findByProjectIdAndParentProId(projectId,parentProjectId);
		if(CollectionUtils.isNotEmpty(pawnList)) {
			Integer dycount = 0;	//抵押序号
			Integer zycount = 0;	//质押序号
			for(ProjectPawnInfo ppi : pawnList) {
				subcontract = new SubContract();
				String guaranteeType = ppi.getGuaranteeType();
				if (StringUtils.isNotBlank(guaranteeType) && !"null".equals(guaranteeType)) {
					if (dataDict.getCodeVal("MortgageType", "S1").equals(guaranteeType)) { 		//担保方式--抵押
						dycount = dycount + 1;
						subcontractNum = commonBizNumberBS.generateSubContractNumber(UCCreditContract.getContractNum(), "抵", dycount);
					} else if (dataDict.getCodeVal("MortgageType", "S2").equals(guaranteeType)) { //担保方式--质押
						zycount = zycount + 1;
						subcontractNum = commonBizNumberBS.generateSubContractNumber(UCCreditContract.getContractNum(), "质", zycount);
					}
				}
				this.assignSubContract(sessionUser, subcontract, UCCreditContract);
				subcontract.setSubcontractNum(subcontractNum);
				subcontract.setGuarantCustomerNum(UCCreditContract.getCustomerNum());
				//实际担保率
				subcontract.setGuarantyRate(ppi.getActualGuaranteeRate()); 	
				//本次实际担保债权金额
				subcontract.setGuarantyAmt(ppi.getActualCreditAmount());	
				subcontract.setGuarantyId(ppi.getProjectPawnInfoId());
				subcontract.setGuarantyNum(ppi.getGuarantyNum());
				//担保方式
				subcontract.setGuaranteeTypeCd(ppi.getGuaranteeType());	 
				subcontract.setSubcontractTypeCd(dataDict.getCodeVal("SubcontractType", "S1"));
				this.saveSubContract(subcontract);
			}
		}
	}
	@Override
	public void saveSubContract(SubContract subcontract) {
		subContractDao.save(subcontract);
	}

	/**
	 * 装载从合同信息
	 * 
	 * @param sessionUser 
	 * @param subcontract 从合同对象
	 * @param creditContract 授信借款合同对象
	 */
	private void assignSubContract(SessionUser sessionUser, SubContract subcontract, Contract UCCreditContract) {
		BeanUtils.copyProperties(UCCreditContract, subcontract);
		subcontract.setContractId(UCCreditContract.getContractId());
		subcontract.setCustomerId(UCCreditContract.getPartyId());
		subcontract.setTerm(UCCreditContract.getContractTerm());
		subcontract.setTermUnitCd(UCCreditContract.getContractTermUnit());
		subcontract.setHandlingOrgCd(sessionUser.getOrgId());
		subcontract.setHandlingUserNum(sessionUser.getUserId().toString());
		subcontract.setSysCreateDate(CommonHelper.getNow());
		subcontract.setSysUpdateDate(CommonHelper.getNow());
		subcontract.setSubcontractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S8"));
	}
	@Override
	public void copyAssureInfoToSubContract(Long projectId,Long parentProjectId, SessionUser sessionUser) {
		SubContract subcontract = null;
		String subcontractNum = "";
		Contract UCCreditContract = contractMngService.findContractByProjectId(projectId);
		//查询保证人列表
		List<ProjectAssurerInfo> assurerList = projectAssurerInfoDao.findByProjectIdAndParentProId(projectId,parentProjectId);
		if (CollectionUtils.isNotEmpty(assurerList)) {
			int bzcount = 0; //保证人序号
			for(ProjectAssurerInfo pai : assurerList) {
				//实际保证金额
				pai.setActualGuaranteeAmt(UCCreditContract.getContractAmt()); 
				subcontract = new SubContract();
				this.assignSubContract(sessionUser, subcontract, UCCreditContract);
				bzcount = bzcount + 1;
				subcontractNum = commonBizNumberBS.generateSubContractNumber(UCCreditContract.getContractNum(), "保", bzcount);
				subcontract.setSubcontractNum(subcontractNum);
				subcontract.setGuarantCustomerNum(pai.getCustomerNum());
				//本次实际保证债权金额
				subcontract.setGuarantyAmt(UCCreditContract.getContractAmt());
				subcontract.setGuarantyNum(pai.getCustomerNum());
				subcontract.setGuaranteeTypeCd(pai.getAssurerType()); 
				subcontract.setSubcontractTypeCd(dataDict.getCodeVal("SubcontractType", "S2")); 
				subcontract.setAssurerId(pai.getProjectAssurerInfoId());
				this.saveSubContract(subcontract);
			}
		}
	}

	@Override
	public Result submitWorkFlow(Long projectId, String workFlowType,
			String workflowId, String taskId, String userId, String nextUser,
			String nextUserOrgId, String comments) {
		//
		Set<String> params = ImmutableSet.of("05", "27", "10", "40", "41",
				"42", "44", "45", "13", "14", "15", "16", "17", "29");
		documentService.updateDocumentIndexByProjectId(projectId, params,dataDict.getCodeVal("CreateType", "S2"));
		NextTaskReceiver receiver = null;
		if (StringUtils.isBlank(nextUser)) {
			TypedResult<List<NextTaskReceiver>> resultList = this.workFlowService
					.getNextTaskReceivers(taskId, ActionCode.COMMIT);
			for (NextTaskReceiver rec : resultList.getData()) {
				if (receiver == null
						|| Integer.parseInt(receiver.getTotal()) > Integer
								.parseInt(rec.getTotal())) {
					receiver = rec;
					nextUser = receiver.getLogName();
				}
			}
		}
			TypedResult<WorkFlowTaskIds> wfResult = this.workFlowService
					.executeTask(ExeTaskParam.newExeTaskParam(
							WorkFlowService.WorkFlowCode.CREDIT_LOAN,
							workflowId, taskId, userId,
							WorkFlowNode.LA_SignedContract, ActionCode.COMMIT,
							nextUser, comments, "", nextUserOrgId));

			if (!wfResult.getSuccess()) {
				return new Result(false, "流程接口返回false，请联系管理员", null);
			}
		
		return new Result(true, "合同提交成功！", null);
	}
	@Override
	public void updateCreditAvaiableAmt(Long projectId ,BigDecimal amount) {
		try {
			creditContractDao.updateCreditAvaiableAmtByPId(projectId,amount);
		} catch (Exception e) {
			 throw new RuntimeException("更新授信剩余可用金额失败");
		}
	}
	
	@Override
	public String checkBusinessInfoBeforeSave(Long creditContractId,
			BusinessApplicationWdVO form) {
		CreditContract creditContract=creditContractDao.findOne(creditContractId);
		//授信合同结束日期
		Date creditEndDate = DateTools.getEndingDate(
				creditContract.getApplyDate(), 
				creditContract.getContractTermUnit(), 
				creditContract.getContractTerm());
		//授信借款结束日期
		Date underCreditEndDate = DateTools.getEndingDate(
				CommonHelper.str2Date(form.getApplyDate(), CommonHelper.DF_DATE), 
				form.getApplyTermUnit(), 
				form.getApplyTerm());   
		//校验授信借款贷款期限不得超过授信合同的贷款期限
		boolean flag = DateTools.getDateIsBefore(creditEndDate, underCreditEndDate);
		if (flag) {
			return "授信借款贷款期限不得超过授信合同的贷款期限";
		}
		//校验授信借款申请额度不能大于授信可用额度
		if (form.getApplyAmt().compareTo(creditContract.getCreditAvaiableAmt()) == 1) {
			return "授信借款申请额度大于授信可用额度";
		}
		return null;
	}
}
