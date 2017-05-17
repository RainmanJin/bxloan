package com.coamctech.bxloan.service.contractmng.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.SystemInterfaceOrgLink;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.LossProvisionDao;
import com.coamctech.bxloan.dao.MoneyRateDao;
import com.coamctech.bxloan.dao.PartyDao;
import com.coamctech.bxloan.dao.PayLoanInfoDao;
import com.coamctech.bxloan.dao.PersonDetailsDao;
import com.coamctech.bxloan.dao.ProductDao;
import com.coamctech.bxloan.dao.RepayPlanDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.LossProvision;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.PersonDetails;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.entity.RepayPlan;
import com.coamctech.bxloan.service.contractmng.LoanGrantService;
import com.coamctech.bxloan.service.model.CustomerVO;
import com.coamctech.bxloan.service.model.PayLoanInfoVO;
import com.coamctech.bxloan.service.model.RepayLoanVO;
import com.coamctech.bxloan.service.model.loanprocess.InterestCalCulateForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestPlanForm;
import com.coamctech.bxloan.service.pettyloan.LoanAccountingService;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanPayService;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchAmtBo;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchResultBo;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanEvent;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
@Transactional
@Service
public class LoanGrantServiceImpl implements LoanGrantService {
	
	/** 合同状态 */
	private final String CODETYPE_CONTRACTSTATUS = "ContractStatusCode";
	/** 放款状态 */
	private final String CODETYPE_PAYLOANSATUS = "PayLoanStatus";
	/** 约定还款方式 */
	private final String CODETYPE_LOANDATESTYLE = "LoanDateStyle";
	
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private PayLoanInfoDao payloanInfoDao;
	@Autowired
	private LossProvisionDao lossProvisionDao;
	@Autowired
	private PartyDao partyDao;
	@Autowired
	private RepayPlanDao repayPlanDao;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private BizRateDao bizRateDao;
	@Autowired
	private PersonDetailsDao personDetailsDao;
	@Autowired
	private MoneyRateDao moneyRateDao;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private LoanPayService loanPayService;
	@Autowired
	private LoanAccountingService loanAccountingService;
	@Override
	public Page findPayLoanBySearch(Integer pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer sql = new StringBuffer("SELECT ");
		sql.append(" p.pay_loan_id,p.contract_num,p.customer_name,b.product_name,p.contract_amt,p.loan_amt,p.loan_regist_time,p.is_upload,p.pay_status_cd,p.customer_num ");
		sql.append(" FROM PAY_LOAN_INFO p ");
		sql.append(" JOIN PRODUCT b ON (p.product_type = b.product_cd) ");
		sql.append(" WHERE ");
		sql.append(" p.apply_User_Num = ?1 ");
		sql.append(" AND p.contract_Id = ?2 ");
		sql.append(" ORDER BY p.sys_update_date DESC ");
		List<Object> paramsAdd = new ArrayList<Object>();
		if (!params.isEmpty()) {
			paramsAdd.add(params.get(0));
			paramsAdd.add(params.get(1));
		}
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql.toString(), paramsAdd.toArray());
	}

	@Override
	public boolean validate(RepayLoanVO tmpRepayLoanVO) {
		tmpRepayLoanVO.setCleanCutCd(dataDict.getUniqueOne("CleanCutCode", "S2").getCodeValue());
		List<RepayLoan> repayLoanList = findRepayLoan(tmpRepayLoanVO);
		if (repayLoanList != null && repayLoanList.size() > 0) {
			RepayLoan repayLoan = null;
			String back = dataDict.getUniqueOne("PayLoanStatus", "S3").getCodeValue(); // 账务退单
			String reverseBack = dataDict.getUniqueOne("PayLoanStatus", "S5").getCodeValue(); // 账务冲销入账
			for (int int_i = 0; int_i < repayLoanList.size(); int_i++) {
				repayLoan = repayLoanList.get(int_i);
				if (!back.equals(repayLoan.getRepaymentStatusCd())
						&& !reverseBack.equals(repayLoan.getRepaymentStatusCd())) {
					if ((dataDict.getUniqueOne("CleanCutCode", "S2").getCodeValue().equals(repayLoan.getCleanCutCd()) || dataDict.getUniqueOne("CleanCutCode", "S3").getCodeValue().equals(repayLoan.getCleanCutCd()))
							&& dataDict.getUniqueOne("CtrlIndicator", "S2").getCodeValue().equals(repayLoan.getValid())) {// 提前还款还在流程中
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public List findRepayLoan(RepayLoanVO repayLoanVO) {
		if (repayLoanVO == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer("from RepayLoan rlb where 1=1 ");
		if (repayLoanVO.getId() != null && repayLoanVO.getId() != -1) {
			hql.append(" and rlb.id='" + repayLoanVO.getId() + "'");
		}
		if (repayLoanVO.getContractId() != null
				&& repayLoanVO.getContractId() != -1) {
			hql.append(" and rlb.contractId='" + repayLoanVO.getContractId()
					+ "'");
		}
		if (StringUtils.isNotBlank(repayLoanVO.getContractNum())) {
			hql.append(" and rlb.contractNum='" + repayLoanVO.getContractNum()
					+ "'");
		}
		if (StringUtils.isNotBlank(repayLoanVO.getTransactionNo())) {
			hql.append(" and rlb.transactionNo='"
					+ repayLoanVO.getTransactionNo() + "'");
		}
		if (StringUtils.isNotBlank(repayLoanVO.getValid())) {
			hql.append(" and rlb.valid='" + repayLoanVO.getValid() + "'");
		}
		if (StringUtils.isNotBlank(repayLoanVO.getRepaymentStatusCd())) {
			hql.append(" and rlb.repaymentStatusCd='"
					+ repayLoanVO.getRepaymentStatusCd() + "'");
		}
		if (repayLoanVO.getPayLoanId() != null
				&& repayLoanVO.getPayLoanId() != -1) {
			hql.append(" and rlb.payLoanId='" + repayLoanVO.getPayLoanId()
					+ "'");
		}
		if (repayLoanVO.getPayableCurrPrincipal() != null
				&& repayLoanVO.getPayableCurrPrincipal().doubleValue() > 0.0d) {
			hql.append(" and rlb.payableCurrPrincipal >0 ");
		}
		hql.append(" order by rlb.repayDate desc");
		List list = dynamicQuery.query(hql.toString());
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public boolean isTallyCertificateCanref(String contractNum) throws RuntimeException {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from tally_certificate tally where tally.cont_cd = '");
		sql.append(contractNum).append("' and ( tally.bill_sts = '");
		sql.append(GlobalConstants.BILLSTS_FSWRZ).append("' or tally.bill_sts = '");
		sql.append(GlobalConstants.BILLSTS_CXWRZ).append("' )");
		List list = dynamicQuery.nativeQuery(sql.toString());
		Object obj = list.get(0);
		Long resultInt = Long.valueOf(String.valueOf(obj));
		if (resultInt > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean validateContractInRenewal(String contractNum) {
		StringBuffer sql = new StringBuffer("select * from project_application pa  where 1=1 ");
		sql.append(" and pa.business_process_type in ('").append(dataDict.getUniqueOne("BussinessProperty", "S2").getCodeValue());
		sql.append("','").append(dataDict.getUniqueOne("BussinessProperty", "S3").getCodeValue()).append("') ");
		sql.append(" and pa.init_contract_num='").append(contractNum).append("'");
		sql.append(" and pa.project_status in (");
		sql.append(dataDict.getUniqueOne("ProjectStatus", "S0").getCodeValue()).append(",");
		sql.append(dataDict.getUniqueOne("ProjectStatus", "S1").getCodeValue()).append(",");
		sql.append(dataDict.getUniqueOne("ProjectStatus", "S2").getCodeValue()).append(")");
		List list = dynamicQuery.nativeQuery(sql.toString());
		if (list != null && list.size() >= 1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isAgainPayLoanBy(Long contractId) {
		// 损失准备金计提
		LossProvision lossProvision = lossProvisionDao.findByOrgIdOrContractId(contractId, dataDict.getUniqueOne("PayLoanStatus", "S2").getCodeValue()).get(0);
		if (lossProvision != null
				&& lossProvision.getPrivisionAmt().compareTo(BigDecimal.ZERO) != 0) {
			return false;
		}
		PayLoanInfo payLoanInfo = payloanInfoDao.findByContractId(contractId).get(0);
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct ad.repaying_flag from repaying_plan rp,repaying_plan_detail rpd, ");
		sql.append("arrears_detail ad where rp.repaying_plan_id = rpd.repaying_plan_id ");
		sql.append("and rpd.repaying_plan_detail_id = ad.repaying_plan_detail_id ");
		sql.append("and rp.contract_id = '").append(contractId).append("' ");
		sql.append("and rp.pay_loan_id = '").append(payLoanInfo.getPayLoanId()).append("' ");
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString());
		if (list != null && list.size() > 0) {
			Object obj = list.get(0);
			if (obj == null) {
				return false;
			} else {
				String str = String.valueOf(obj);
				if (Integer.parseInt(str) > 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Product findProductByProductCd(Long productCd) {
		return productDao.findByProductCd(productCd);
	}

	@Override
	public BigDecimal[] getLoanRate(Contract contract) {
		BigDecimal[] rates = new BigDecimal[2];
		MoneyRate moneyRate = this.findValidMoneyRate(contract.getContractTermTotal().toString(), contract.getContractTermUnitTotal());
		if (null == moneyRate)
			return null;
		BigDecimal interestRate = moneyRate.getInterestRate();
		String ratehql = "from BizRate where projectId = ?";
		BizRate bizRate = bizRateDao.findByProjectId(contract.getProjectId().toString());
		if (dataDict.getCodeVal("InterestRateAdjustment", "S2").equals(bizRate.getFinalIrTypeCd())) { // 浮动利率
			// 贷款利率=基准利率*（1+利率加减值）
			BigDecimal finalFloatRate = bizRate.getFinalFloatRate();// 利率加减值
			finalFloatRate = new BigDecimal(MathUtil.BDadd(1, finalFloatRate.doubleValue(), 6));
			rates[0] = MathUtil.BDmultiply(interestRate, finalFloatRate, 6);
			// 逾期利率值=正常利率*（1+逾期利率加减值）
			BigDecimal ovdueIrNegoRate = bizRate.getOvdueIrNegoRate();
			ovdueIrNegoRate = new BigDecimal(MathUtil.BDadd(1, ovdueIrNegoRate.doubleValue(), 6));
			rates[1] = MathUtil.BDmultiply(rates[0], ovdueIrNegoRate, 6);
		} else if (dataDict.getCodeVal("InterestRateAdjustment", "S1").equals(bizRate.getFinalIrTypeCd())) {// 固定
			rates[0] = contract.getContractRate();
			rates[1] = contract.getContractOvdueRate();
		}
		return rates;
	}

	@Override
	public MoneyRate findValidMoneyRate(String timeLimit, String termUnitCd) {
		if (StringUtils.isBlank(timeLimit) || "null".equals(timeLimit)
				|| StringUtils.isBlank(termUnitCd) || "null".equals(termUnitCd)) {
			throw new RuntimeException("期限值或者期限单位已丢失");
		}
		int limitMonth = 0;
		if (dataDict.getCodeVal("TermUnitCd", "S1").equals(termUnitCd)) { // 年
			limitMonth = (Integer.valueOf(timeLimit).intValue()) * 12;
		} else if (dataDict.getCodeVal("TermUnitCd", "S2").equals(termUnitCd)) { // 月
			limitMonth = Integer.valueOf(timeLimit).intValue();
		} else if (dataDict.getCodeVal("TermUnitCd", "S3").equals(termUnitCd)) {
			limitMonth = Integer.valueOf(timeLimit) / 30;
		}
		if (limitMonth <= 6) { // 六个月以内(含6个月)
			timeLimit = dataDict.getCodeVal("TimeLimitType", "S1");
		} else if (limitMonth > 6 && limitMonth <= 12) { // 六个月至一年(含一年)
			timeLimit = dataDict.getCodeVal("TimeLimitType", "S2");
		} else if (limitMonth > 12 && limitMonth <= 36) { // 一至三年(含三年)
			timeLimit = dataDict.getCodeVal("TimeLimitType", "S3");
		} else if (limitMonth > 36 && limitMonth <= 60) { // 三至五年(含五年)
			timeLimit = dataDict.getCodeVal("TimeLimitType", "S4");
		} else if (limitMonth > 60) { // 五年以上
			timeLimit = dataDict.getCodeVal("TimeLimitType", "S5");
		}
		List<MoneyRate> moneyRateList = moneyRateDao.findListByTimeLimit(timeLimit);
		if (moneyRateList != null && moneyRateList.size() > 0) {
			return moneyRateList.get(0);
		}
		return null;
	}

	@Override
	public String generateLoanEvidenceNum(Long partyId, Long orgid) {
		Party party = partyDao.findByPartyId(partyId);
		String loanEvidenceNum = null; // 借款凭据编号
		String partyType = ""; // 客户类型
		if (dataDict.getCodeVal("CustomerType", "S1").equals(party.getPartyTypeCd())) {// 企业客户
			partyType = "0";
		} else if (dataDict.getCodeVal("CustomerType", "S2").equals(party.getPartyTypeCd())) {// 个人客户
			partyType = "1";
		}
		try {
			loanEvidenceNum = commonBizNumberBS.generateAppNumber("J", partyType, String.valueOf(orgid));
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return loanEvidenceNum;
	}

	@Override
	public void saveLoanInfoList(List<PayLoanInfo> payLoanInfoList) {
		payloanInfoDao.save(payLoanInfoList);
	}

	@Override
	public Long savePayLoanInfo(PayLoanInfo payLoanInfo) {
		payloanInfoDao.save(payLoanInfo);
		return payLoanInfo.getPayLoanId();
	}

	@Override
	public List findRepayPlanList(Long projectId) {
		return repayPlanDao.findListByProjId(projectId);
	}

	@Override
	public PersonDetails getPersonDetailsById(Long userId) {
		return personDetailsDao.findOne(userId);
	}

	@Override
	public CustomerVO findCustomerByPartyId(Long partyId) {
		String sql = "select case when p.party_type_cd = '1' then (select cc.linkman_tel　from corporation_customer cc where cc.customer_num = p.customer_num) else (select aa.mobile_tel　from individual aa where aa.customer_num=p.customer_num) end phone,p.party_type_cd, p.customer_num,p.party_name from party p where p.party_id=?1 ";
		List list = dynamicQuery.nativeQuery(Map.class, sql, partyId);
		Map map = null;
		CustomerVO customerVO = new CustomerVO();
		if (list != null && list.size() > 0) {
			map = (Map) list.get(0);
			customerVO.setPartyId(partyId);
			if (map.get("party_name") != null) {
				customerVO.setChineseName(map.get("party_name").toString());
			}
			if (map.get("party_type_cd") != null) {
				customerVO.setPartyTypeCd(map.get("party_type_cd").toString());
			}
			if (map.get("phone") != null) {
				customerVO.setLinkmanTel(map.get("phone").toString());
			}
		}
		return customerVO;
	}

	@Override
	public PayLoanInfo findPayLoanById(Long payLoanId) {
		return payloanInfoDao.findByPayLoanId(payLoanId);
	}
	/**
	 * 根据payLoanInfo 的合同id和合同状态查对象
	 * 
	 * @param contractId
	 * @aram 合同状态值
	 * @return PayLoanInfo对象
	 */
	@Override
	public PayLoanInfo findPayLoanByContractIdAndContractStatus(String contractId, String contractStatus) {
		String jpql = " select p from PayLoanInfo p where contractId = ?1 "
				+ " and p.payStatusCd in  " + contractStatus
				+ " and p.manyPayStatus != '1' ";
		/*
		 * List<String> params = new ArrayList<String>();
		 * params.add(contractId);
		 */
		List<PayLoanInfo> queryList = dynamicQuery.query(PayLoanInfo.class, jpql, Long.valueOf(contractId));
		if (CollectionUtils.isNotEmpty(queryList)) {
			return queryList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<PayLoanInfo> findPayLoanListByContractIdAndStatus(Long contractId, List<String> status) {
		return payloanInfoDao.findByContractIdAndStatus(contractId, status);
	}

	@Override
	public Result generateLoanIssues(PayLoanInfo payLoan, Contract contract, PayLoanInfoVO payLoanInfoVO, Long orgid) throws RuntimeException {
		// 借款凭据打印
				// 生成借款凭据编号
				Long _payLoanId = null;
				{
					String loanEvidenceNum = null;
					try {
						loanEvidenceNum = this.generateLoanEvidenceNum(contract.getPartyId(), orgid);// 生成借款凭据编号
						payLoan.setLoanEvidenceNum(loanEvidenceNum);
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}
				// 验证是否合同已提交了一次放款,无可用金额
				if (contract.getContractAvailableAmt() != null
						&& contract.getContractAvailableAmt().compareTo(BigDecimal.ZERO) == 0) {
					return new Result(false, "此合同已提交了一次放款,还未执行完成,请稍等!", null);
				}
				// 多次放款序列
				List<PayLoanInfo> payLoanInfoList = null;
				List<String> _status = new ArrayList<String>();
				_status.add(dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S3"));
				_status.add(dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S5"));
				payLoanInfoList = this.findPayLoanListByContractIdAndStatus(contract.getContractId(), _status);
				if (payLoanInfoList == null || payLoanInfoList.size() == 0) {
					payLoan.setPayLoanIndex(new Long(0));
				} else {
					PayLoanInfo payLoanInfo = null;
					for (int int_i = 0; int_i < payLoanInfoList.size(); int_i++) {
						payLoanInfo = payLoanInfoList.get(int_i);
						if (int_i == 0 && (int_i + 1) == payLoanInfoList.size()) {
							if (!(StringUtils.isBlank(payLoanInfo.getPayStatusCd()) && StringUtils.isNotBlank(payLoanInfo.getLoanEvidenceNum()))) {
								if (dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S1").equals(payLoanInfo.getPayStatusCd())) {
									return new Result(false, "此合同已提交了一次放款,还未执行完成,请稍等!", null);
								}
								payLoan.setPayLoanIndex(new Long(payLoanInfoList.size()));
								payLoanInfo.setManyPayStatus("1"); // 多次放款标识 失效
							} else {
								payLoan.setPayLoanIndex(new Long(0));
							}
						} else {
							payLoan.setPayLoanIndex(new Long(payLoanInfoList.size()));
							payLoanInfo.setManyPayStatus("1"); // 失效
						}
					}
					this.saveLoanInfoList(payLoanInfoList);
				}
				// 利率
				BigDecimal rates[] = this.getLoanRate(contract);
				payLoan.setContractRate(rates[0]);
				payLoan.setOverduerate(rates[1]);
				contract.setContractRate(rates[0]);
				contract.setContractOvdueRate(rates[1]);
				// 时间
				payLoan.setLoanActulTime(new Date());
				payLoan.setLoanRegistTime(DateTools.stringToDate(payLoanInfoVO.getLoanRegistTime(), "yyyy-MM-dd"));
				Long payLoanId = this.savePayLoanInfo(payLoan);
				payLoan.setPayLoanId(payLoanId);
				// 接口开关关闭时
				if (!loanCommonServeice.isOpenOfOrgInterface(String.valueOf(orgid), SystemInterfaceOrgLink.INTERFACEKEY_Accounting)) {
					contract.setContractStatusCd(dataDict.getCodeVal(CODETYPE_CONTRACTSTATUS, "S2"));
					// 合同余额
					if (contract.getContractBalance() == null) {
						contract.setContractBalance(BigDecimal.ZERO);
					}
					// 已放款
					contract.setContractBalance(contract.getContractBalance().add(payLoan.getLoanAmt()));
					// 累计放款金额
					if (contract.getCumulativePayoutAmt() == null) {
						contract.setCumulativePayoutAmt(BigDecimal.ZERO);
					}
					contract.setCumulativePayoutAmt(contract.getCumulativePayoutAmt().add(payLoan.getLoanAmt()));
					// 本次可放款金额
					if (contract.getContractAvailableAmt() == null) {
						contract.setCumulativePayoutAmt(contract.getContractAmt().subtract(contract.getCumulativePayoutAmt()));
					} else {
						contract.setContractAvailableAmt(contract.getContractAvailableAmt().subtract(payLoan.getLoanAmt()));
					}
					// 累计收取手续费及佣金
					if (contract.getFreepayloanamtcnt() == null) {
						contract.setFreepayloanamtcnt(BigDecimal.ZERO);
					}
					if (payLoan.getSumAmt() == null) {
						payLoan.setSumAmt(BigDecimal.ZERO);
					}
					contract.setFreepayloanamtcnt(contract.getFreepayloanamtcnt().add(payLoan.getSumAmt()));
					payLoan.setLoanActulTime(payLoan.getLoanRegistTime()); // 放款实际日期赋值
					contract.setPayloanDate(payLoan.getLoanRegistTime()); // 放款开始时间
					// 生成还款计划
					InterestCalCulateForm interForm = new InterestCalCulateForm();
					interForm.setPartyId(contract.getPartyId().intValue());
					interForm.setContractId(contract.getContractId().intValue());
					interForm.setProjectId(contract.getProjectId().intValue());
					interForm.setPayLoanId(payLoan.getPayLoanId().intValue());
					interForm.setOperatorUser(contract.getApplyUserNum());
					interForm.setOperatorMechanism(contract.getApplyOrgId().toString());
					interForm.setLoanAmount(payLoan.getLoanAmt());// 放款金额
					interForm.setLoanStartDate(payLoan.getLoanActulTime());// 放款开始时间
					// 为选择实际放款时间
					interForm.setLoanEndDate(DateTools.getEndingDate(payLoan.getLoanActulTime(), contract.getContractTermUnit(), contract.getContractTerm()));
					// 若同放款日
					if (dataDict.getCodeVal(CODETYPE_LOANDATESTYLE, "S2").equals(contract.getLoanDateStyle())) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(payLoan.getLoanActulTime());
						interForm.setRepaymentDate(calendar.get(Calendar.DATE));// 约定还款日
						contract.setArrangeRepayDay(calendar.get(Calendar.DATE));
					} else {
						interForm.setRepaymentDate(contract.getArrangeRepayDay());// 约定还款日
					}
					interForm.setRepayment(contract.getRepayModeCd());// 还款方式
					interForm.setChargeAmt(payLoan.getSumAmt());
					interForm.setSourceType(payLoanInfoVO.getSourceType());
					// interForm.setRate(rates[0]);// 年利率 ！！
					// interForm.setOverdueRate(rates[1]);// 逾期利率 !!
					interForm.setRate(contract.getContractRate());// 年利率 ！！
					interForm.setOverdueRate(contract.getContractOvdueRate());// 逾期利率
					// // 自定义的要先判断
					//this.findRepayPlanList(contract.getProjectId());
					List panList = loanPayService.findCustPlanList(contract);
					interForm.setPlanList(panList);
					interForm.setRepaymentNumber(contract.getRepayPrincipalMonthes());
					String transactionNum = null;
					if (payLoan.getPayLoanIndex() > 0) {
						transactionNum = loanPayService.againPayoutLoan(interForm,payLoan );
					} else {
						// 生成还款计划
						transactionNum = loanPayService.initRepayPlanDb(interForm,payLoan);
						/**20150514 modify by hwl */
						loanPayService.backupData(transactionNum);
					}
					System.out.println("*************单据编号：" + transactionNum);
					payLoan.setTransactionNo(transactionNum);// 存放交易编号
					payLoan.setPayStatusCd(dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S2"));
					_payLoanId = this.savePayLoanInfo(payLoan);
					/*
					 * try { // 短信提醒 ChinaMessageDTO chinaMessageDTO = new
					 * ChinaMessageDTO();
					 * chinaMessageDTO.setOrgId(contract.getApplyOrgId().intValue());
					 * chinaMessageDTO.setSendUser(curUser.getName()); PersonDetails
					 * personDetails =
					 * this.getPersonDetailsById(Long.valueOf(contract
					 * .getApplyUserNum()));
					 * chinaMessageDTO.setSendMobile(personDetails.getMobilephone()); //
					 * 借款人手机 CustomerVO customerVO =
					 * this.findCustomerByPartyId(contract.getPartyId());
					 * String[] destMobiles = { customerVO.getLinkmanTel() };
					 * chinaMessageDTO.setDestMobile(destMobiles); //添加贷款产品类型
					 * chinaMessageDTO.setProductType(contract.getProductType());
					 * StringBuffer msg = new StringBuffer(); msg.append("尊敬的客户:"); if
					 * (dataDict.getCodeVal("CustomerType",
					 * "S2").equals(customerVO.getPartyTypeCd())) {// 个人客户
					 * msg.append("您"); } else { msg.append("贵公司"); }
					 * msg.append("向我司申请的" + MoneyUtil.formatMoney(payLoan.getLoanAmt())
					 * + "元借款已于" + DateTools.dateToString(payLoan.getLoanActulTime(),
					 * "yyyy年MM月dd日") + "发放，谢谢您的支持与合作。");
					 * chinaMessageDTO.setSendComtent(msg.toString());
					 * //ChinaMobileMessageUtil.sendMessage(chinaMessageDTO); // 短信提醒 }
					 * catch (Exception e) { e.printStackTrace(); throw new
					 * RuntimeException("短信发送失败"); }
					 */
				} else {
					// 接口开关打开时
					// 合同状态改为放款中
					// 放款状态改为未入账
					contract.setContractStatusCd(dataDict.getCodeVal(CODETYPE_CONTRACTSTATUS, "S9"));
					DoVchAmtBo amtVO = new DoVchAmtBo();
					amtVO.setLoanId(payLoan.getPayLoanId());
					amtVO.setContractId(contract.getContractId());
					amtVO.setPartyId(contract.getPartyId());
					amtVO.setProjectId(contract.getProjectId());
					amtVO.setVchdate(payLoan.getLoanRegistTime());
					amtVO.setLoanAmt(payLoan.getLoanAmt());
					amtVO.setChargeAmt(payLoan.getSumAmt());
					amtVO.setBcCostType(payLoanInfoVO.getSourceType());
					DoVchResultBo bo = null;
					try {
						bo = loanAccountingService.doVchByLoan(amtVO, 
								contract.getApplyOrgId().intValue(), 
								Integer.valueOf(contract.getApplyUserNum()),
								payLoan);
					} catch (Exception e) {
						e.printStackTrace();
						e.getMessage();
						throw new RuntimeException(e.getMessage());
					}
					payLoan.setTransactionNo((String) bo.getTransNo());// 存放交易编号
					payLoan.setPayStatusCd(dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S1"));
					_payLoanId = this.savePayLoanInfo(payLoan);
					System.out.println("*************单据编号：" + (String) bo.getTransNo());
				}
				// this.setVisMode("", "btn-print", "true",
				// Visibility.SHOWTYPE);//显示打印借据按钮
				// this.setVisMode("", "submit", "false", Visibility.SHOWTYPE);//隐藏打印按钮
				
				return new Result(true, "保存成功！", _payLoanId);
	}
	
	/**
	 * 获取需要的还款计划list
	 * 
	 * @param repayPlanList
	 * @return
	 */
	private List<InterestPlanForm> getPlanList(List<RepayPlan> repayPlanList) {
		List<InterestPlanForm> planList = new ArrayList<InterestPlanForm>();
		InterestPlanForm planForm = null;
		if (repayPlanList != null) {
			for (RepayPlan repayPlan : repayPlanList) {
				planForm = new InterestPlanForm();
				planForm.setPlanTime(repayPlan.getRepayDate());
				planForm.setPlanPpal(repayPlan.getRepayAmt());
				planList.add(planForm);
			}
		}
		return planList;
	}

	@Override
	public void changeIsUpload(Contract contract, PayLoanInfo payLoanInfo) {
		contract.setLastUploadTime(new Date());
		contract.setSysUpdateDate(new Date());
		payLoanInfo.setSysUpdateDate(new Date());
		this.payloanInfoDao.save(payLoanInfo);
		this.contractDao.save(contract);
	}
}
