package com.coamctech.bxloan.service.pettyloan.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.ArrearsDetailDao;
import com.coamctech.bxloan.dao.AssTranProjectApplicationDao;
import com.coamctech.bxloan.dao.BizExpenseRateDao;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.OverdueStateDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.RepayLoanDao;
import com.coamctech.bxloan.dao.RepayingDetailDao;
import com.coamctech.bxloan.dao.RepayingPlanDao;
import com.coamctech.bxloan.dao.RepayingPlanDetailDao;
import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.AssTranProjectApplication;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.LossProvision;
import com.coamctech.bxloan.entity.OverdueState;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.afterloan.LoanOperationsService;
import com.coamctech.bxloan.service.loanprocess.InterestCalCulateService;
import com.coamctech.bxloan.service.model.loanprocess.InterestResultVO;
import com.coamctech.bxloan.service.pettyloan.LoanAccountingService;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanRepayService;
import com.coamctech.bxloan.service.pettyloan.LoanRpInfoService;
import com.coamctech.bxloan.service.pettyloan.LoanService;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchAmtBo;
import com.coamctech.bxloan.service.pettyloan.bo.LoanResult;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfInMoney;
import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanEvent;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
@Transactional
@Service("loanRepayService")
public class LoanRepayServiceImpl implements LoanRepayService{
	@Autowired
	private BizExpenseRateDao bizExpenseRateDao;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private RepayLoanDao repayLoanDao;
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	@Autowired
	private CollateralDao collateralDao;
	@Autowired
	private AssTranProjectApplicationDao assTranProjectApplicationDao;
	@Autowired
	private RepayingPlanDao repayingPlanDao;
	@Autowired
	private RepayingPlanDetailDao repayingPlanDetailDao;
	@Autowired
	private RepayingDetailDao repayingDetailDao;
	@Autowired
	private ArrearsDetailDao arrearsDetailDao;
	@Autowired
	private OverdueStateDao overdueStateDao;
	@Autowired
	private InterestCalCulateService interestCalCulateService;
	@Autowired
	private LoanAccountingService loanAccountingService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private LoanRpInfoService loanRpInfoService;
	@Autowired
	private LoanOperationsService loanOperationsService;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Override
	public BizExpenseRate updateBizExpenseRate(String transactionNO,
			String repayLoanStatus) {
		List<BizExpenseRate> berList=bizExpenseRateDao.findListByTransNo(transactionNO);
		if(CollectionUtils.isEmpty(berList)){
			throw new LoanBizException("");
		}
		BizExpenseRate ber=berList.get(0);
		LoanStatus ls=loanCommonServeice.getLoanStatus(LoanConstants.PAY_LOAN_STATUS,repayLoanStatus);
		switch (ls) {
		case PAY_LOAN_STATUS_S3:
			String transStatus=ber.getTransactionStatus();
			if(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1).equals(transStatus)){
				ber.setTransactionStatus(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
			}else if(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S4).equals(transStatus)){
				ber.setTransactionStatus(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
				
			}
			break;
		case PAY_LOAN_STATUS_S2:
		case PAY_LOAN_STATUS_S4:
		case PAY_LOAN_STATUS_S5:
			ber.setTransactionStatus(repayLoanStatus);
			break;
		default:
			break;
		}
		bizExpenseRateDao.save(ber);
		return ber;
	}

	@Override
	public void updateRepayLoan(String transNo, String repayLoanStatus) {
		List<RepayLoan> list=repayLoanDao.findListByTransNo(transNo);
		RepayLoan repayLoan=list.get(0);
		Contract contract =contractDao.findByContractId(repayLoan.getContractId());
		if (contract==null) {
			throw new LoanBizException("查无合同资料,请联系管理员!");
		}
		LoanStatus ls=loanCommonServeice.getLoanStatus(LoanConstants.PAY_LOAN_STATUS, repayLoanStatus);
		switch (ls) {
		case PAY_LOAN_STATUS_S3:
			if(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1).equals(repayLoan.getRepaymentStatusCd())){
				dynamicQuery
						.nativeExecuteUpdate(
								"update project_application pa set pa.project_status=?1  where pa.project_id=?2",
								repayLoan.getProjectId(), loanCommonServeice
										.getCodeVal("ProjectStatus", "S5"));// 已签订合同
				contract.setContractBalance(repayLoan.getContractBalance());
				contract.setLastRepayDate(repayLoan.getOrigLastRepayDate());
//				contract.setContractStatusCd(repayLoanBO.getOrigConStatusCd());
				contract.setLastOvdueDate(repayLoan.getOrigLastOvdueDate());
				contract.setOvdueDays(repayLoan.getOrigOvdueDays());
				contract.setLastRepayDate(repayLoan.getExpirationDate());
				if (contract.getCumulativeRepayAmt() == null) {
					contract.setCumulativeRepayAmt(new BigDecimal("0"));
				} else if (repayLoan.getActualPrincipalAmt() != null) {
					contract.setCumulativeRepayAmt(contract.getCumulativeRepayAmt().subtract(
							repayLoan.getActualPrincipalAmt()));
				}
				contractDao.save(contract);
				repayLoan.setRepaymentStatusCd(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
				repayLoan.setValid(loanCommonServeice.getCodeVal("CtrlIndicator", "S2"));// 失效
				if (contract.getFpoolProjectId() != null) {
					BigDecimal repayedAmt = repayLoan.getActualAmt();
					if (repayLoan.getPenalty() != null) {
						repayedAmt = repayedAmt.subtract(repayLoan.getPenalty());
					}
					if (repayLoan.getTableinterest() != null) {
						repayedAmt = repayedAmt.add(repayLoan.getTableinterest());
					}
					//TODO 根据ID更新机构收回本金
					updateAsstranProjectAppById(contract.getFpoolProjectId(), BigDecimal.ZERO
						.subtract(repayedAmt));
				}
			}
			break;
		case PAY_LOAN_STATUS_S2:
			if (contract.getContractAvailableAmt().compareTo(BigDecimal.ZERO) == 0) {
				// 已结清的项目需要把相关联的文档状态由“引用”改成“创建”youjg start
				Integer partyId = findPartyIdById(repayLoan.getProjectId());
				if (validateModifyDocumentType(partyId, repayLoan.getProjectId())) {
					updateCreateTypeAll(partyId, loanCommonServeice.getCodeVal("CreateType", "S1"));
			}
			repayLoan.setRepaymentStatusCd(loanCommonServeice.getCodeVal("PayLoanStatus", "S2"));
			repayLoan.setValid(loanCommonServeice.getCodeVal("CtrlIndicator", "S1"));// 有效
			if (!loanCommonServeice.getCodeVal("CleanCutCode", "S2").equals(repayLoan.getCleanCutCd())) { // 提前还款不发短信
				// 发送短信
				try {
					if (repayLoan.getActualPrincipalAmt() == null) {
						repayLoan.setActualPrincipalAmt(BigDecimal.ZERO);
					}
					if (repayLoan.getActualCurrPrincipal() == null) {
						repayLoan.setActualCurrPrincipal(BigDecimal.ZERO);
					}
					repayLoan.setLoanAmt(repayLoan.getActualPrincipalAmt().subtract(
							repayLoan.getActualCurrPrincipal()));
					this.sendShortMessage(repayLoan);
				} catch (Exception e) {
					//logger.debug("短信发送失败:" + e.getMessage());
				}
			}
			if (contract.getContractAvailableAmt().compareTo(BigDecimal.ZERO) == 0
					&& contract.getContractBalance().compareTo(BigDecimal.ZERO) == 0
					&& contract.getIfReleaseFlag() == null) {
				// 释放抵质押金额 20130106
				handlePledgeAmtWhenContractEnd(repayLoan.getProjectId());
				contract.setIfReleaseFlag("1");// 抵质押物已释放且金额已扣减的标志
			}
			contractDao.save(contract);
			if (contract.getFpoolProjectId() != null) {
				BigDecimal repayedAmt = repayLoan.getActualAmt();
				if (repayLoan.getPenalty() != null) {
					repayedAmt = repayedAmt.subtract(repayLoan.getPenalty());
				}
				if (repayLoan.getTableinterest() != null) {
					repayedAmt = repayedAmt.subtract(repayLoan.getTableinterest());
				}
				//this.fpoolProjectsBS.updateAsstranProjectAppById(contract.getFpoolProjectId(), repayedAmt);
					updateAsstranProjectAppById(contract.getFpoolProjectId(), repayedAmt);
				}
			}
			break;
		case PAY_LOAN_STATUS_S4:
			repayLoan.setRepaymentStatusCd(loanCommonServeice.getCodeVal("PayLoanStatus", "S4"));
			repayLoan.setValid(loanCommonServeice.getCodeVal("CtrlIndicator", "S1"));// 有效
			break;
		case PAY_LOAN_STATUS_S5:
			contract.setContractBalance(repayLoan.getContractBalance());
			contract.setLastRepayDate(repayLoan.getOrigLastRepayDate());
			contract.setContractStatusCd(repayLoan.getOrigConStatusCd());
			contract.setLastOvdueDate(repayLoan.getOrigLastOvdueDate());
			contract.setOvdueDays(repayLoan.getOrigOvdueDays());
			contract.setLastRepayDate(repayLoan.getExpirationDate());
			if (contract.getCumulativeRepayAmt() == null) {
				contract.setCumulativeRepayAmt(new BigDecimal("0"));
			} else if (repayLoan.getActualPrincipalAmt() != null) {
				contract.setCumulativeRepayAmt(contract.getCumulativeRepayAmt().subtract(
						repayLoan.getActualPrincipalAmt()));
			}
			contractDao.save(contract);

			repayLoan.setRepaymentStatusCd(loanCommonServeice.getCodeVal("PayLoanStatus", "S5"));
			repayLoan.setValid(loanCommonServeice.getCodeVal("CtrlIndicator", "S1"));// 有效
			if (contract.getFpoolProjectId() != null) {
				BigDecimal repayedAmt = repayLoan.getActualAmt();
				if (repayLoan.getPenalty() != null) {
					repayedAmt = repayedAmt.subtract(repayLoan.getPenalty());
				}
				if (repayLoan.getTableinterest() != null) {
					repayedAmt = repayedAmt.add(repayLoan.getTableinterest());
				}
				updateAsstranProjectAppById(contract.getFpoolProjectId(), BigDecimal.ZERO
						.subtract(repayedAmt));
			}
			break;
		default:
			break;
		}
		
	}
	/**
	 * @param projectId
	 * @return
	 */
	private Integer findPartyIdById(Long projectId){
		List<?> list =dynamicQuery.nativeQuery("select party_id from project_application where project_id=?1", projectId);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return Integer.valueOf(String.valueOf(list.get(0)));
	}
	/**
	 * 校验是否可以修改客户的文档类型
	 * @param partyId
	 * @param projectId
	 * @return
	 */
	private boolean validateModifyDocumentType(Integer partyId,Long projectId){
		StringBuffer nativeSql=new StringBuffer();
		nativeSql.append("select  pa.project_id from project_application pa")
		.append(" where pa.project_status in (?1)  and pa.party_id = ?2  and pa.project_id != ?3");
		nativeSql.append(" union  select pa.project_id from project_application pa");
		nativeSql.append("  where  pa.project_id != ?3");
		nativeSql.append("  and exists (select 1  from contract c where c.project_id = pa.project_id");
		nativeSql.append(" and pa.party_id = ?2");
		nativeSql.append(" and c.contract_status_cd in (?4))");
		List<Object> params=Lists.newArrayList();
		params.add(Arrays.asList(
				loanCommonServeice.getCodeVal("ProjectStatus", "S0"),
				loanCommonServeice.getCodeVal("ProjectStatus", "S1"),
				loanCommonServeice.getCodeVal("ProjectStatus", "S2"),
				loanCommonServeice.getCodeVal("ProjectStatus", "S5")));
		params.add(partyId);
		params.add(projectId);
		params.add(Arrays.asList(
				loanCommonServeice.getCodeVal("ContractStatusCode", "S1"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S2"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S3"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S6"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S8"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S10")));
		Long count=dynamicQuery.nativeQueryCount(nativeSql.toString(), params.toArray());
		if(count>0){
			return false;
		}
		return true;
	}
	/**
	 * 批量修改客户文档的关联方式
	 * @param partyId
	 * @param createTypeCd
	 */
	private void updateCreateTypeAll(Integer partyId,String createTypeCd){
		StringBuffer sql = new StringBuffer();
		sql.append("update document_index set create_type_cd = ?1");
		sql.append(",system_update_time = to_date(?2,'yyyy-MM-dd')");
		sql.append(" where party_id = ?3");
		sql.append(" and document_type = ?4");
		sql.append(" and status = ?5");
		List<Object> list=Lists.newArrayList();
		list.add(createTypeCd);
		list.add(DateTools.getCurrentDate("yyyy-MM-dd"));
		list.add(partyId);
		list.add(loanCommonServeice.getCodeVal("DocumentType", "S01"));//文档类型，01-客户文档
		list.add(loanCommonServeice.getCodeVal("CtrlIndicator", "S1"));//文档状态，1-有效
		dynamicQuery.nativeExecuteUpdate(sql.toString(), list.toArray());
	}
	/**
	 * 根据项目id，处理抵质押物列表
	 */
	private void handlePledgeAmtWhenContractEnd(Long projectId){
		List<ProjectPawnInfo> pawnInfos =projectPawnInfoDao.findByProjectId(projectId);
		if (CollectionUtils.isEmpty(pawnInfos)) {
		}
		Collateral collateral=null;
		for (ProjectPawnInfo ppInfo : pawnInfos) {
			String pawnState=ppInfo.getPawnState();
			//状态为【关联新增】和【关联置换】需要 设定金额 计入抵押物累计抵押金额
			collateral = collateralDao.findByGuarantyId(ppInfo.getGuarantyId());
			if(pawnState.equals(loanCommonServeice.getCodeVal("PawnState", "S1"))
					||pawnState.equals(loanCommonServeice.getCodeVal("PawnState", "S3"))){
				//累计抵押金额=累计抵押金额+贷款放款金额    累计锁定金额=累计锁定金额-贷款放款金额
				sumSetGuarantyAmt(collateral, ppInfo.getActualCreditAmount());
			//状态为【关联已删除】需要 释放金额 从抵押物累计抵押金额中扣除
			}else if(pawnState.equals(loanCommonServeice.getCodeVal("PawnState", "S4"))){
				//累计抵押金额=累计抵押金额-贷款放款金额
				deductSetGuarantyAmt(collateral, ppInfo.getActualCreditAmount());
				//抵押物状态需要改，但是有点复杂，判断（所有合同）已解除关联
				if(!this.isPledgeIsUsedByGuarantyId(collateral.getGuarantyId())){
					collateral.setGuarantyStatusCd(loanCommonServeice.getCodeVal("RevGuaranteeStatusCd", "S3"));
				}
				collateralDao.save(collateral);
			}
		}
	}
	/**
	 * 功能说明：抵质押物实际担保金额：从抵押物累计抵押金额中扣除
	 *  累计抵押金额=累计抵押金额-贷款放款金额
	 * @param collateral
	 * @param actualCreditAmount
	 */
	private void deductSetGuarantyAmt(Collateral collateral,
			BigDecimal actualCreditAmount) {
		try {
			//已设定金额（即累计抵押金额）
			BigDecimal setGuarantyAmt=collateral.getSetGuarantyAmt();
			if(null==setGuarantyAmt){
				setGuarantyAmt=new BigDecimal(0);
			}
			//实际担保物的担保金额
			if(null==actualCreditAmount){
				actualCreditAmount=new BigDecimal(0);
			}
			collateral.setSetGuarantyAmt(MathUtil.BDsubtract(setGuarantyAmt, actualCreditAmount, 2));
		} catch (Exception e) {
			throw new  LoanBizException(e.getMessage());
		}
		
	}

	/**
	 * 功能说明：根据抵质押物id,判断（所有合同）是否已解除关联
	 * @param guarantyId
	 * @return
	 */
	private boolean isPledgeIsUsedByGuarantyId(Long guarantyId) {
		
		StringBuffer sql =new StringBuffer("select count(1) from project_application pa  where 1 = 1");
		sql.append(" and ((pa.project_id in (select p.project_id  from project_pawn_info p where 1=1" );
		sql.append(" and p.pawn_state!=?1");
		sql.append(" and p.guaranty_id =?2");
		sql.append(" and pa.project_status in (?3)");
		sql.append(" or pa.project_id in  (select c.project_id from contract c where 1 = 1  and c.project_id in");
		sql.append("(select p.project_id  from project_pawn_info p where 1=1 ");
		sql.append(" and p.pawn_state!=?4");
		sql.append(" and p.guaranty_id =?5");
		sql.append(" and  (c.contract_status_cd in (?6) and c.if_release_flag is null)))");
		List<Object> parmas=Lists.newArrayList();
		parmas.add(loanCommonServeice.getCodeVal("PawnState", "S4"));
		parmas.add(guarantyId);
		parmas.add(Arrays.asList(
				loanCommonServeice.getCodeVal("ProjectStatus", "S0"),
				loanCommonServeice.getCodeVal("ProjectStatus", "S1"),
				loanCommonServeice.getCodeVal("ProjectStatus", "S2")));
		parmas.add(loanCommonServeice.getCodeVal("PawnState", "S4"));
		parmas.add(guarantyId);
		parmas.add(Arrays.asList(
				loanCommonServeice.getCodeVal("ContractStatusCode", "S1"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S2"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S3"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S5"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S6"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S8"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S9"),
				loanCommonServeice.getCodeVal("ContractStatusCode", "S10")));
		Long count=dynamicQuery.nativeQuerySingleResult(Long.class,sql.toString(), parmas.toArray());
		if(count>0){
			return true;
		}
		return false;
	}

	/**
	 * 功能说明：抵质押物实际担保金额：计入抵押物累计抵押金额
	 * 累计抵押金额=累计抵押金额+贷款放款金额
	 * 累计锁定金额=累计锁定金额-贷款放款金额
	 * @param collateral  
	 * @author zhengsijing
	 * @date 2012-12-18
	 */
	private void sumSetGuarantyAmt(Collateral collateral,BigDecimal actualCreditAmount){
		try {
			//已设定金额（即累计抵押金额）
			BigDecimal setGuarantyAmt=collateral.getSetGuarantyAmt();
			if(null==setGuarantyAmt){
				setGuarantyAmt=new BigDecimal(0);
			}
			//实际担保物的担保金额
			if(null==actualCreditAmount){
				actualCreditAmount=new BigDecimal(0);
			}
			collateral.setSetGuarantyAmt(MathUtil.BDadd(setGuarantyAmt, actualCreditAmount, 2));
			//锁定金额
			BigDecimal lockingAmount=collateral.getLockingAmount();
			if(null==lockingAmount){
				lockingAmount=new BigDecimal(0);
			}
			collateral.setLockingAmount(MathUtil.BDsubtract(lockingAmount, actualCreditAmount, 2));
			collateralDao.save(collateral);
		} catch (Exception e) {
			throw new  LoanBizException(e.getMessage());
		}
	}
	/**
	 * 根据ID更新机构收回本金
	 * @param projectId
	 * @param repayedAmt
	 */
	private void updateAsstranProjectAppById(Long projectId, BigDecimal repayedAmt){
		AssTranProjectApplication atpa=assTranProjectApplicationDao.findByProjectId(projectId);
		if (atpa.getOrgrepayamt()==null) {
				atpa.setOrgrepayamt(BigDecimal.ZERO);
			}
		atpa.setOrgrepayamt(atpa.getOrgrepayamt().add(repayedAmt));
		atpa.setUtilizationOfFunds(atpa.getFpoolAmt().subtract(repayedAmt)
				.divide(atpa.getAssetPackageScale(), 6, BigDecimal.ROUND_HALF_UP));
		assTranProjectApplicationDao.save(atpa);
	}
	private void sendShortMessage(RepayLoan repayLoan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String updateRepayLoanOfRpd(Long rpdId, BigDecimal repayingAmount,
			boolean norm2over, Date repayedDate, String fundsSource) {
		if(rpdId==null||repayingAmount==null){
			throw new NullPointerException("还款计划详细id或还款金额不可为空");
		}
		RepayingPlanDetail cur_rpd=repayingPlanDetailDao.findOne(rpdId);//当前还款计划明细
		repayedDate=CommonHelper.formatDate(repayedDate, "yyyy-MM-dd");// 还款时间
		RepayingPlan rp =repayingPlanDao.findOne(cur_rpd.getRepayingPlanId());//还款计划
		//TODO norm2over判断？
		
		Integer currentPriod = cur_rpd.getCurrentPeriod(); // 当期期次
		final String STATUS_WH =loanCommonServeice.getCodeVal(LoanStatus.PLAN_STATUS_S0);// 未还
		final String STATUS_YH = loanCommonServeice.getCodeVal(LoanStatus.PLAN_STATUS_S1);// 已还
		final String STATUS_YQ = loanCommonServeice.getCodeVal(LoanStatus.PLAN_STATUS_S2);// 逾期未还
		boolean normalFlag = false; // 正常还款标识
		if (STATUS_WH.equals(cur_rpd.getStatus()) && !norm2over) {
			normalFlag = true;
		}
		//当期之前未还还款计划
		List<RepayingPlanDetail> rpdYetRepayedList=findRpdList(cur_rpd.getRepayingPlanId(), currentPriod, repayedDate,STATUS_YH);
		if(CollectionUtils.isEmpty(rpdYetRepayedList)){
			throw new LoanBizException("查询还款计划详细为空");
		}
		Set<Long> rpdIds=Sets.newHashSet();
		for (RepayingPlanDetail temp : rpdYetRepayedList) {
			rpdIds.add(temp.getRepayingPlanDetailId());
		}
		List<RepayingDetail> rdList=loanRpInfoService.findRdList(rpdIds);
		List<ArrearsDetail> adList=loanRpInfoService.findAdList(rpdIds);
		List<OverdueState> osList=loanRpInfoService.findOsList(rpdIds);
		Contract contract = contractDao.findOne(rp.getContractId());
		if (contract != null && contract.getOvdueDays() == null) {
			contract.setOvdueDays(0L);
		}
		if(norm2over&& CollectionUtils.isEmpty(osList)){//正常转逾期
			OverdueState insertVO = null;
			for (RepayingPlanDetail temp : rpdYetRepayedList) {
				if(currentPriod<=temp.getCurrentPeriod()){//? 重复判断
					insertVO = new OverdueState();
					insertVO.setContractId(rp.getContractId()); // 合同ID
					insertVO.setLoanId(rp.getPayLoanId()); // 放款ID
					insertVO.setRepayingPlanDetailId(cur_rpd.getRepayingPlanDetailId()); // 还款计划明细ID
					insertVO.setSysUpdateDate(CommonHelper.getNow());
					insertVO.setOverdueDate(cur_rpd.getCurrentEndDate());// 逾期日期
					insertVO.setPartyId(rp.getPartyId());// 参与人ID
					insertVO.setProjectId(rp.getProjectId());// 业务ID
					insertVO.setPayLoanStatus(LoanConstants.BATCH_ONE);// 跑批情况
					overdueStateDao.save(insertVO);
				}
			}
		}else if(normalFlag&&CollectionUtils.isNotEmpty(osList)){
			overdueStateDao.delete(osList);//清除逾期信息
		}
		RepayingPlanDetail repayingPlanDetail = null;
		ArrearsDetail arrearsDetail = null;
		RepayingDetail repayingDetail = null;
		BigDecimal overdueOutAmt = BigDecimal.ZERO; // 逾期转出金额
		if(norm2over){
			//正常转逾期
			if (contract.getLastOvdueDate() == null) {
				contract.setLastOvdueDate(cur_rpd.getCurrentEndDate()); // 最近逾期日期
			}
			BigDecimal overduePrincipal = BigDecimal.ZERO;
			BigDecimal overdueInterest = BigDecimal.ZERO;
			for (int int_i = 0; int_i < rpdYetRepayedList.size(); int_i++) {
				repayingPlanDetail = rpdYetRepayedList.get(int_i);
				repayingDetail = rdList.get(int_i);
				arrearsDetail = adList.get(int_i);
				if (!(LoanConstants.REPAYING_FLAG_OVERDUE.equals(arrearsDetail.getRepayingFlag()) || LoanConstants.REPAYING_FLAG_OVERTHEN90
						.equals(arrearsDetail.getRepayingFlag()))) {
					arrearsDetail.setRepayingFlag(STATUS_YH);
				}
				if (STATUS_WH.equals(repayingPlanDetail.getStatus())) {
					repayingPlanDetail.setStatus(STATUS_YQ); // 状态
					arrearsDetail.setStatus(STATUS_YQ);
					arrearsDetail.setNotyetPricipal(repayingPlanDetail.getCurrentPrincipal().subtract(
							repayingDetail.getRepayedPrincipal())); // 未还本金
					arrearsDetail.setNotyetInterest(repayingPlanDetail.getCurrentInterest().subtract(
							repayingDetail.getRepayedInterest())); // 未还利息
					if (int_i == 0) {
						arrearsDetail.setRepayingFirstFlag(STATUS_YH);
						if (contract.getFirstOvdueDate() == null) {
							contract.setFirstOvdueDate(arrearsDetail.getPlanRepayintDate());
						}
						contract.setLastOvdueDate(arrearsDetail.getPlanRepayintDate());
						contract.setOvdueTime(contract.getOvdueTime() == null ? 1 : (contract.getOvdueTime() + 1));
						contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S3"));
					}
					overdueOutAmt = overdueOutAmt.add(arrearsDetail.getNotyetPricipal());
				}
				overduePrincipal = overduePrincipal.add(arrearsDetail.getNotyetPricipal());
				overdueInterest = overdueInterest.add(arrearsDetail.getNotyetInterest());
			}
			contract.setOvdueAmt(overduePrincipal);
			contract.setOvdueInterest(overdueInterest);
			//TODO 发送消息this.overDueSendMessage(contract, overduePrincipal.add(overdueInterest), repayedDate);
		}
		rp.setTransactionStatus(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1));
		//fpoolFlag ?
		if(contract.getFpoolFlag()!=null){
			repayingPlanDetailDao.save(rpdYetRepayedList);
			repayingDetailDao.save(rdList);
			arrearsDetailDao.save(adList);
			//? 
			return null;
		}
		//损失计提设置准备金
		LossProvision lp =null;
		List<LossProvision> lpList=loanCommonServeice.findLossProvisionList(rp.getContractId(), LoanConstants.OBJECTDIMENSIONTYPE_CONTRACT, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		if(CollectionUtils.isNotEmpty(lpList)){
			lp=lpList.get(0);
		}
		if(lp!=null){
			rp.setPrivisionAmt(lp.getPrivisionAmt());//准备金
		}
		
		//账务
		DoVchAmtBo vchAmtBo=new DoVchAmtBo();
		InterestResultVO resultVO=new InterestResultVO();
		vchAmtBo.setContractId(rp.getContractId()); // 合同ID
		vchAmtBo.setPartyId(rp.getPartyId()); // 参加人ID
		vchAmtBo.setLoanId(rp.getPayLoanId()); // 贷款人ID
		vchAmtBo.setProjectId(rp.getProjectId()); // 业务ID
		vchAmtBo.setVchdate(DateTools.stringToDate((DateTools.dateToString(repayedDate, "yyyy-MM-dd"))
				+ " " + CommonHelper.date2Str(CommonHelper.getNow(), "HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")); // 账务处理时间
		if (CommonHelper.isNotNull(fundsSource)) {
			vchAmtBo.setBcCostType(fundsSource); // 资金来源
		}
		BigDecimal impose_interest = null; // 罚息
		Integer period = 0;// 本次还清期数
		Date overdue90Date = null; // 逾期90天以上日期
		BigDecimal dailyInterestRate=BigDecimal.ZERO;//日利率
		if(rpdYetRepayedList.size()>1||norm2over||(rpdYetRepayedList.size()==1&&STATUS_YQ.equals(cur_rpd.getStatus()))){
			//逾期
			if (contract.getOvdueAmt() == null) {
				contract.setOvdueAmt(BigDecimal.ZERO);
			}
			if (contract.getOvdueInterest() == null) {
				contract.setOvdueInterest(BigDecimal.ZERO);
			}
			final int rpdYetRepayedListSize=rpdYetRepayedList.size();
 			int overduePeriod = norm2over ? rpdYetRepayedListSize : (!normalFlag ? rpdYetRepayedListSize
					: rpdYetRepayedListSize - 1);
			Date lastOvdueDate = null;
			dailyInterestRate=rp.getOverdueRate().divide(LoanConstants.DCNT, 50, BigDecimal.ROUND_HALF_UP);
			arrearsDetail = adList.get(0);
			overdue90Date = DateTools.addDay(arrearsDetail.getPlanRepayintDate(), 90);
			// 逾期90天内转逾期90天以上
			if (!"2".equals(arrearsDetail.getRepayingFlag())
					&& DateTools.getDateDiff(overdue90Date, repayedDate) > 0) {
				vchAmtBo.setChangeOverFlag(true);
				// 出帐-逾期90天转逾期90天以上
				vchAmtBo.setBcOverThan90Principal(rp.getRepayedAmount());
				// 逾期90天以上(补出90逾期科目)
				for (int int_i = 0; int_i < rpdYetRepayedList.size(); int_i++) {
					arrearsDetail = adList.get(int_i);
					if (STATUS_YQ.equals(arrearsDetail.getStatus())) {
						// 计算罚息,最近计息日期--逾期90天日期
						impose_interest = this.calCulateImposeInterest(dailyInterestRate, arrearsDetail.getNotyetPricipal(),
								arrearsDetail.getNotyetInterest(), arrearsDetail.getNotyetImposeInterest(),
								DateTools.getDateDiff(arrearsDetail.getRecentlyDate(), overdue90Date));
						// 出帐-逾期90天结计利息
						vchAmtBo.setSetIntImposeInterest(vchAmtBo.getSetIntImposeInterest().add(impose_interest).add(
								arrearsDetail.getYearImposeInterest()));
						// 未还罚息
						arrearsDetail.setNotyetImposeInterest(arrearsDetail.getNotyetImposeInterest().add(
								impose_interest).add(arrearsDetail.getYearImposeInterest()));
						arrearsDetail.setYearImposeInterest(BigDecimal.ZERO);
						if (DateTools.getDateDiff(arrearsDetail.getRecentlyDate(), overdue90Date) > 0) {
							arrearsDetail.setRecentlyDate(overdue90Date); // 最近计息日
						}
						arrearsDetail.setRepayingFlag(LoanConstants.REPAYING_FLAG_OVERTHEN90); // 还款标识
						arrearsDetail.setRepayingCalFlag(STATUS_YH); // 还款计算标识
						// 首次逾期(利息正常->冲转)
						if (STATUS_YH.equals(arrearsDetail.getRepayingFirstFlag())) {
							// 出帐-冲未还正常利息
							vchAmtBo.setBcOverThan90CurrInterest(arrearsDetail.getNotyetInterest());
						} else {
							// 出帐-冲未还逾期90天内利息
							vchAmtBo.setBcOverThan90OverInterest(vchAmtBo.getBcOverThan90OverInterest().add(
									arrearsDetail.getNotyetInterest()));
						}
					}
				}
			}
			arrearsDetail = adList.get(0);
			if (DateTools.getDateDiff(arrearsDetail.getPlanRepayintDate(), repayedDate) > 90
					|| LoanConstants.REPAYING_FLAG_OVERTHEN90.equals(arrearsDetail.getRepayingFlag())) { // 逾期90天以上
				// 还款顺序 ：最远一期开始还,本金;
				// 最远一期开始还,利息;
				// 最远一期开始还,罚息;
				// (第1,2,3逾期,最远一期指:第1-3期本金,第1-3期利息,第1-3期罚息)
				// 本金
				for (int int_i = 0; int_i < overduePeriod; int_i++) {
					repayingPlanDetail = rpdYetRepayedList.get(int_i);
					repayingDetail = rdList.get(int_i);
					arrearsDetail = adList.get(int_i);
					arrearsDetail.setRepayingFlag(STATUS_YQ);
					// 计算罚息
					impose_interest = this.calCulateImposeInterest(dailyInterestRate, arrearsDetail.getNotyetPricipal(),
							arrearsDetail.getNotyetInterest(), arrearsDetail.getNotyetImposeInterest(), DateTools
									.getDateDiff(arrearsDetail.getRecentlyDate(), repayedDate));
					// 出帐-逾期90天以上结计利息
					vchAmtBo.setSetIntOverThan90ImposeInterest(vchAmtBo.getSetIntOverThan90ImposeInterest().add(
							impose_interest).add(arrearsDetail.getYearImposeInterest()));
					// 未还90天以上未还罚息
					arrearsDetail.setCarryoverImposeInterest(arrearsDetail.getCarryoverImposeInterest().add(
							impose_interest).add(arrearsDetail.getYearImpose90Interest()));// 罚息
					arrearsDetail.setYearImpose90Interest(BigDecimal.ZERO);
					arrearsDetail.setRecentlyDate(repayedDate);// 最近计息日
					repayingDetail.setRepayingDate(repayedDate); // 还款日期
					if (repayingAmount.compareTo(BigDecimal.ZERO) > 0) {
						if (repayingAmount.compareTo(arrearsDetail.getNotyetPricipal()) > 0) {
							contract
									.setOvdueAmt(contract.getOvdueAmt().subtract(arrearsDetail.getNotyetPricipal()));
							repayingAmount = repayingAmount.subtract(arrearsDetail.getNotyetPricipal());
							resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
									arrearsDetail.getNotyetPricipal())); // 返回已还本金
							resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
									arrearsDetail.getNotyetPricipal())); // 返回已还本息
							resultVO.setLoanAmount(resultVO.getLoanAmount().add(arrearsDetail.getNotyetPricipal())); // 返回实还拖欠本金
							// 出帐-还款本金
							vchAmtBo.setBcPaidOverThan90Principal(vchAmtBo.getBcPaidOverThan90Principal().add(
									arrearsDetail.getNotyetPricipal()));
							repayingDetail.setRepayedPrincipal(repayingPlanDetail.getCurrentPrincipal());// 已还本金
							arrearsDetail.setNotyetPricipal(BigDecimal.ZERO);// 未还本金
						} else {
							contract.setOvdueAmt(contract.getOvdueAmt().subtract(repayingAmount));
							resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(repayingAmount)); // 返回已还本金
							resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
									repayingAmount)); // 返回已还本息
							resultVO.setLoanAmount(resultVO.getLoanAmount().add(repayingAmount)); // 返回实还拖欠本金
							// 出帐-还款本金
							vchAmtBo.setBcPaidOverThan90Principal(vchAmtBo.getBcPaidOverThan90Principal().add(
									repayingAmount));
							arrearsDetail.setNotyetPricipal(arrearsDetail.getNotyetPricipal().subtract(
									repayingAmount));// 未还本金
							repayingDetail.setRepayedPrincipal(repayingDetail.getRepayedPrincipal().add(
									repayingAmount));// 已还本金
							repayingAmount = BigDecimal.ZERO;
							break;
						}
					}
				}

				repayingPlanDetail = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
				// 正常还款-当期本金
				if (repayingAmount.compareTo(BigDecimal.ZERO) > 0 && !norm2over
						&& STATUS_WH.equals(repayingPlanDetail.getStatus())) {
					repayingDetail = rdList.get(rpdYetRepayedList.size() - 1);
					repayingDetail.setRepayingDate(repayedDate); // 还款日期
					if (repayingAmount.compareTo(repayingPlanDetail.getCurrentPrincipal().subtract(
							repayingDetail.getRepayedPrincipal())) > 0) {
						resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
								repayingPlanDetail.getCurrentPrincipal().subtract(
										repayingDetail.getRepayedPrincipal()))); // 返回已还本金
						resultVO.setRepayedCurrentPricipal(repayingPlanDetail.getCurrentPrincipal().subtract(
								repayingDetail.getRepayedPrincipal())); // 返回已还当期本金
						// 出帐-还款本金
						vchAmtBo.setBcPaidOverThan90Principal(vchAmtBo.getBcPaidOverThan90Principal().add(
								repayingPlanDetail.getCurrentPrincipal().subtract(
										repayingDetail.getRepayedPrincipal())));
						repayingAmount = repayingAmount.subtract(repayingPlanDetail.getCurrentPrincipal().subtract(
								repayingDetail.getRepayedPrincipal()));
						repayingDetail.setRepayedPrincipal(repayingPlanDetail.getCurrentPrincipal()); // 已还本金
					} else {
						resultVO.setRepayedCurrentPricipal(repayingAmount); // 返回已还当期本金
						// 出帐-还款本金
						vchAmtBo
								.setBcPaidOverThan90Principal(vchAmtBo.getBcPaidOverThan90Principal().add(
										repayingAmount));
						repayingDetail
								.setRepayedPrincipal(repayingDetail.getRepayedPrincipal().add(repayingAmount)); // 已还本金
						repayingAmount = BigDecimal.ZERO;
					}
				}
				if (repayingAmount.compareTo(BigDecimal.ZERO) > 0) {
					// 利息
					for (int int_i = 0; int_i < overduePeriod; int_i++) {
						repayingPlanDetail = rpdYetRepayedList.get(int_i);
						repayingDetail = rdList.get(int_i);
						arrearsDetail = adList.get(int_i);
						if (repayingAmount.compareTo(arrearsDetail.getNotyetInterest()) > 0) {
							contract.setOvdueInterest(contract.getOvdueInterest().subtract(
									arrearsDetail.getNotyetInterest()));
							repayingAmount = repayingAmount.subtract(arrearsDetail.getNotyetInterest());
							resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
									arrearsDetail.getNotyetInterest()));// 返回已还本息
							// 逾期90天内
							if (STATUS_YH.equals(arrearsDetail.getRepayingCalFlag())) {
								// 出帐-逾期90天内利息
								vchAmtBo.setBcIn90Interest(vchAmtBo.getBcIn90Interest().add(
										arrearsDetail.getNotyetInterest()));
							}
							// 逾期90天以上
							else {
								// 出帐-逾期90天以上利息
								vchAmtBo.setBcPaidOverThan90Interest(vchAmtBo.getBcPaidOverThan90Interest().add(
										arrearsDetail.getNotyetInterest()));
							}
							repayingDetail.setRepayedInterest(repayingPlanDetail.getCurrentInterest()); // 已还利息
							arrearsDetail.setNotyetInterest(BigDecimal.ZERO); // 未还利息
						} else {
							contract.setOvdueInterest(contract.getOvdueInterest().subtract(repayingAmount));
							resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
									repayingAmount));// 返回已还本息
							// 逾期90天内
							if (STATUS_YH.equals(arrearsDetail.getRepayingCalFlag())) {
								// 出帐-逾期90天内利息
								vchAmtBo.setBcIn90Interest(vchAmtBo.getBcIn90Interest().add(repayingAmount));
							}
							// 逾期90天以上
							else {
								// 出帐-逾期90天以上利息
								vchAmtBo.setBcPaidOverThan90Interest(vchAmtBo.getBcPaidOverThan90Interest().add(
										repayingAmount));
							}
							repayingDetail.setRepayedInterest(repayingDetail.getRepayedInterest().add(
									repayingAmount)); // 已还利息
							arrearsDetail.setNotyetInterest(arrearsDetail.getNotyetInterest().subtract(
									repayingAmount)); // 未还利息
							repayingAmount = BigDecimal.ZERO;
							break;
						}
					}
				}

				repayingPlanDetail = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
				// 正常还款-当期利息
				if (repayingAmount.compareTo(BigDecimal.ZERO) > 0 && !norm2over
						&& STATUS_WH.equals(repayingPlanDetail.getStatus())) {
					repayingDetail = rdList.get(rpdYetRepayedList.size() - 1);
					arrearsDetail = adList.get(rpdYetRepayedList.size() - 1);
					if (repayingAmount.compareTo(repayingPlanDetail.getCurrentInterest().subtract(
							repayingDetail.getRepayedInterest())) > 0) {
						period++;
						resultVO.setRepayedCurrentInterest(repayingPlanDetail.getCurrentInterest().subtract(
								repayingDetail.getRepayedInterest())); // 返回已还当期利息
						repayingAmount = repayingAmount.subtract(repayingPlanDetail.getCurrentInterest().subtract(
								repayingDetail.getRepayedInterest()));
						repayingDetail.setRepayedInterest(repayingPlanDetail.getCurrentInterest()); // 已还利息
						arrearsDetail.setStatus(STATUS_YH);
						repayingPlanDetail.setStatus(STATUS_YH);
					} else {
						resultVO.setRepayedCurrentInterest(repayingAmount); // 返回已还当期利息
						repayingDetail.setRepayedInterest(repayingDetail.getRepayedInterest().add(repayingAmount)); // 已还利息
						// 账务出账--已减值转未减值(逾期90天以上利息)
						vchAmtBo.setBcOverThan90NoLossInterest(vchAmtBo.getBcOverThan90NoLossInterest().add(
								repayingPlanDetail.getCurrentInterest().subtract(
										repayingDetail.getRepayedInterest())));
						repayingAmount = BigDecimal.ZERO;
					}
					// 逾期90天内
					if (STATUS_YH.equals(arrearsDetail.getRepayingCalFlag())) {
						// 出帐-逾期90天内利息
						vchAmtBo
								.setBcIn90Interest(vchAmtBo.getBcIn90Interest().add(
										resultVO.getRepayedCurrentInterest()));
					}
					// 逾期90天以上
					else {
						// 出帐-逾期90天以上利息
						vchAmtBo.setBcPaidOverThan90Interest(vchAmtBo.getBcPaidOverThan90Interest().add(
								resultVO.getRepayedCurrentInterest()));
					}
				}

				// 罚息
				for (int int_i = 0; int_i < overduePeriod; int_i++) {
					repayingPlanDetail = rpdYetRepayedList.get(int_i);
					repayingDetail = rdList.get(int_i);
					arrearsDetail = adList.get(int_i);
					// 罚息
					if (repayingAmount.compareTo(arrearsDetail.getNotyetImposeInterest().add(
							arrearsDetail.getCarryoverImposeInterest())) >= 0) {
						period++;
						resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
								arrearsDetail.getNotyetImposeInterest().add(
										arrearsDetail.getCarryoverImposeInterest()))); // 返回已还罚息
						// 出帐-逾期90天内利息
						vchAmtBo.setBcIn90Interest(vchAmtBo.getBcIn90Interest().add(
								arrearsDetail.getNotyetImposeInterest()));
						// 出帐-逾期90天以上利息
						vchAmtBo.setBcPaidOverThan90Interest(vchAmtBo.getBcPaidOverThan90Interest().add(
								arrearsDetail.getCarryoverImposeInterest()));
						lastOvdueDate = contract.getLastOvdueDate(); // 合同最近逾期日期
						repayingAmount = repayingAmount.subtract(arrearsDetail.getNotyetImposeInterest().add(
								arrearsDetail.getCarryoverImposeInterest()));
						repayingPlanDetail.setStatus(STATUS_YH); // 已还
						repayingDetail.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest().add(
								arrearsDetail.getNotyetImposeInterest().add(
										arrearsDetail.getCarryoverImposeInterest()))); // 已还罚息
						arrearsDetail.setStatus(STATUS_YH); // 已还
						arrearsDetail.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
						arrearsDetail.setCarryoverImposeInterest(BigDecimal.ZERO); // 未还90天以上罚息
						if ((int_i + 1) == overduePeriod) {// 逾期全部还清
							contract.setLastOvdueDate(null);
						} else {
							contract.setLastOvdueDate(adList.get(int_i + 1).getPlanRepayintDate());
						}
						// 计算合同连续逾期天数
						if (contract.getLastOvdueDate() == null) {
							contract.setOvdueDays(contract.getOvdueDays()
									+ DateTools.getDateDiff(lastOvdueDate, repayedDate));
						} else {
							contract.setOvdueDays(contract.getOvdueDays()
									+ DateTools.getDateDiff(lastOvdueDate, contract.getLastOvdueDate()));
						}

						// 更新逾期情况表
						if (arrearsDetail.getRecentlyDate().compareTo(repayedDate) < 0) {
							this.updateOverdueStatus(repayingPlanDetail.getRepayingPlanDetailId(), repayedDate);
						} else {
							this.updateOverdueStatus(repayingPlanDetail.getRepayingPlanDetailId(), arrearsDetail.getRecentlyDate());
						}
						if (repayingAmount.compareTo(BigDecimal.ZERO) == 0) {
							break;
						}
					}
					// 还逾期90天内,部分90天以上
					else if (repayingAmount.compareTo(arrearsDetail.getNotyetImposeInterest()) > 0) {
						resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(repayingAmount)); // 返回已还罚息
						// 出帐-逾期90天内利息
						vchAmtBo.setBcIn90Interest(vchAmtBo.getBcIn90Interest().add(
								arrearsDetail.getNotyetImposeInterest()));
						repayingDetail.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest().add(
								repayingAmount));// 已还罚息
						repayingAmount = repayingAmount.subtract(arrearsDetail.getNotyetImposeInterest());
						// 出帐-逾期90天以上利息
						vchAmtBo.setBcPaidOverThan90Interest(vchAmtBo.getBcPaidOverThan90Interest().add(repayingAmount));
						arrearsDetail.setNotyetImposeInterest(BigDecimal.ZERO);// 未还90天内罚息
						arrearsDetail.setCarryoverImposeInterest(arrearsDetail.getCarryoverImposeInterest()
								.subtract(repayingAmount));
						repayingAmount = BigDecimal.ZERO;
						break;
					}
					// 部分90天以上
					else {
						resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(repayingAmount)); // 返回已还罚息
						// 出帐-逾期90天内利息
						vchAmtBo.setBcIn90Interest(vchAmtBo.getBcIn90Interest().add(repayingAmount));
						repayingDetail.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest().add(
								repayingAmount));// 已还罚息
						arrearsDetail.setNotyetImposeInterest(arrearsDetail.getNotyetImposeInterest().subtract(
								repayingAmount));// 未还90天内罚息
						repayingAmount = BigDecimal.ZERO;
						break;
					}
				}

			} else { // 逾期90天以内
				vchAmtBo.setChangeOverFlag(norm2over);
				// 出帐-转逾期金额
				vchAmtBo.setBcOverPrincipal(overdueOutAmt);
				// 还款顺序 ：最远一期开始还,罚息->利息->本金(第1,2,3逾期,最远一期指:第1期)
				// 逾期
				for (int int_i = 0; int_i < overduePeriod; int_i++) {
					repayingPlanDetail = rpdYetRepayedList.get(int_i);
					repayingDetail = rdList.get(int_i);
					arrearsDetail = adList.get(int_i);
					// 计算罚息
					impose_interest = this.calCulateImposeInterest(dailyInterestRate, arrearsDetail.getNotyetPricipal(),
							arrearsDetail.getNotyetInterest(), arrearsDetail.getNotyetImposeInterest(), DateTools
									.getDateDiff(arrearsDetail.getRecentlyDate(), repayedDate));
					// 出帐-计提罚息
					vchAmtBo.setSetIntImposeInterest(vchAmtBo.getSetIntImposeInterest().add(impose_interest).add(
							arrearsDetail.getYearImposeInterest()));
					arrearsDetail.setNotyetImposeInterest(arrearsDetail.getNotyetImposeInterest().add(
							impose_interest).add(arrearsDetail.getYearImposeInterest()));// 罚息
					arrearsDetail.setYearImposeInterest(BigDecimal.ZERO);
					arrearsDetail.setRecentlyDate(repayedDate);// 最近计息日
					repayingDetail.setRepayingDate(repayedDate); // 还款日期
					if (repayingAmount.compareTo(BigDecimal.ZERO) > 0) {
						// 全部还
						if (repayingAmount.compareTo(arrearsDetail.getNotyetInterest().add(
								arrearsDetail.getNotyetPricipal()).add(arrearsDetail.getNotyetImposeInterest())) >= 0) {
							lastOvdueDate = contract.getLastOvdueDate(); // 合同最近逾期日期
							contract
									.setOvdueAmt(contract.getOvdueAmt().subtract(arrearsDetail.getNotyetPricipal()));
							contract.setOvdueInterest(contract.getOvdueInterest().subtract(
									arrearsDetail.getNotyetInterest()));
							resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
									arrearsDetail.getNotyetPricipal())); // 返回已还本金
							resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
									arrearsDetail.getNotyetPricipal().add(arrearsDetail.getNotyetInterest())));// 返回已还本息
							resultVO.setLoanAmount(resultVO.getLoanAmount().add(arrearsDetail.getNotyetPricipal()));// 返回实还拖欠本金
							resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));// 返回已还罚息
							// 出帐-逾期90天内本金
							vchAmtBo.setBcPaidOverPrincipal(vchAmtBo.getBcPaidOverPrincipal().add(
									arrearsDetail.getNotyetPricipal()));
							// 出帐-逾期90天内利息
							vchAmtBo.setBcPaidOverInterest(vchAmtBo.getBcPaidOverInterest().add(
									arrearsDetail.getNotyetInterest()));
							// 是否跑过利息结计
							if (STATUS_WH.equals(repayingPlanDetail.getIsInterestInContractRecord())) {
								vchAmtBo.setSetIntImposeInterest(vchAmtBo.getSetIntImposeInterest().add(
										arrearsDetail.getNotyetInterest()));
							}
							// 出帐-逾期90天内罚息
							vchAmtBo.setBcPaidImposeInterest(vchAmtBo.getBcPaidImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));
							repayingAmount = repayingAmount
									.subtract(arrearsDetail.getNotyetInterest().add(
											arrearsDetail.getNotyetPricipal()).add(
											arrearsDetail.getNotyetImposeInterest()));
							period++;
							repayingPlanDetail.setStatus(STATUS_YH);// 已还
							repayingDetail.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));// 已换罚息
							repayingDetail.setRepayedInterest(repayingPlanDetail.getCurrentInterest());// 已换利息
							repayingDetail.setRepayedPrincipal(repayingPlanDetail.getCurrentPrincipal());// 已换本金
							arrearsDetail.setStatus(STATUS_YH); // 已还
							arrearsDetail.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
							arrearsDetail.setNotyetInterest(BigDecimal.ZERO); // 未还利息
							arrearsDetail.setNotyetPricipal(BigDecimal.ZERO); // 未还本金
							if ((int_i + 1) == overduePeriod) {// 逾期全部还清
								contract.setLastOvdueDate(null);
							} else {
								contract.setLastOvdueDate(adList.get(int_i + 1).getPlanRepayintDate());
							}
							// 计算合同连续逾期天数
							if (contract.getLastOvdueDate() == null) {
								contract.setOvdueDays(contract.getOvdueDays()
										+ DateTools.getDateDiff(lastOvdueDate, repayedDate));
							} else {
								contract.setOvdueDays(contract.getOvdueDays()
										+ DateTools.getDateDiff(lastOvdueDate, contract.getLastOvdueDate()));
							}
							// 更新逾期情况表
							if (arrearsDetail.getRecentlyDate().compareTo(repayedDate) < 0) {
								this.updateOverdueStatus(repayingPlanDetail.getRepayingPlanDetailId(), repayedDate);
							} else {
								this.updateOverdueStatus(repayingPlanDetail.getRepayingPlanDetailId(), arrearsDetail
										.getRecentlyDate());
							}
						}
						// 还部分本金,罚息+利息全部还
						else if (repayingAmount.compareTo(arrearsDetail.getNotyetInterest().add(
								arrearsDetail.getNotyetImposeInterest())) > 0) {
							repayingAmount = repayingAmount.subtract(arrearsDetail.getNotyetInterest().add(
									arrearsDetail.getNotyetImposeInterest()));
							contract.setOvdueAmt(contract.getOvdueAmt().subtract(repayingAmount));
							contract.setOvdueInterest(contract.getOvdueInterest().subtract(
									arrearsDetail.getNotyetInterest()));
							resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(repayingAmount)); // 返回已还本金
							resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
									repayingAmount.add(arrearsDetail.getNotyetInterest())));// 返回已还本息
							resultVO.setLoanAmount(resultVO.getLoanAmount().add(repayingAmount));// 返回实还拖欠本金
							resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));// 返回已还罚息
							// 出帐-逾期90天内本金
							vchAmtBo.setBcPaidOverPrincipal(vchAmtBo.getBcPaidOverPrincipal().add(repayingAmount));
							// 出帐-逾期90天内利息
							vchAmtBo.setBcPaidOverInterest(vchAmtBo.getBcPaidOverInterest().add(
									arrearsDetail.getNotyetInterest()));
							// 是否跑过利息结计
							if (STATUS_WH.equals(repayingPlanDetail.getIsInterestInContractRecord())) {
								vchAmtBo.setSetIntImposeInterest(vchAmtBo.getSetIntImposeInterest().add(
										arrearsDetail.getNotyetInterest()));
							}
							// 出帐-逾期90天内罚息
							vchAmtBo.setBcPaidImposeInterest(vchAmtBo.getBcPaidImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));
							repayingDetail.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));// 已换罚息
							repayingDetail.setRepayedInterest(repayingPlanDetail.getCurrentInterest());// 已换利息
							repayingDetail.setRepayedPrincipal(repayingDetail.getRepayedPrincipal().add(
									repayingAmount)); // 已换本金
							arrearsDetail.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
							arrearsDetail.setNotyetInterest(BigDecimal.ZERO); // 未还利息
							arrearsDetail.setNotyetPricipal(arrearsDetail.getNotyetPricipal().subtract(
									repayingAmount));// 未还本金
							repayingAmount = BigDecimal.ZERO;
						}
						// 还部分利息,罚息全部还
						else if (repayingAmount.compareTo(arrearsDetail.getNotyetImposeInterest()) > 0) {
							repayingAmount = repayingAmount.subtract(arrearsDetail.getNotyetImposeInterest());
							contract.setOvdueInterest(contract.getOvdueInterest().subtract(repayingAmount));
							resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
									repayingAmount));// 返回已还本息
							resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));// 返回已还罚息
							// 出帐-逾期90天内利息
							vchAmtBo.setBcPaidOverInterest(vchAmtBo.getBcPaidOverInterest().add(repayingAmount));
							// 是否跑过利息结计
							if (STATUS_WH.equals(repayingPlanDetail.getIsInterestInContractRecord())) {
								vchAmtBo.setSetIntImposeInterest(vchAmtBo.getSetIntImposeInterest().add(repayingAmount));
							}
							// 出帐-逾期90天内罚息
							vchAmtBo.setBcPaidImposeInterest(vchAmtBo.getBcPaidImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));
							repayingDetail.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest().add(
									arrearsDetail.getNotyetImposeInterest()));// 已换罚息
							repayingDetail.setRepayedInterest(repayingDetail.getRepayedInterest().add(
									repayingAmount));// 已换利息
							arrearsDetail.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
							arrearsDetail.setNotyetInterest(arrearsDetail.getNotyetInterest().subtract(
									repayingAmount)); // 未还利息
							repayingAmount = BigDecimal.ZERO;
						}
						// 还部分罚息
						else {
							// 出帐-逾期90天内罚息
							vchAmtBo.setBcPaidImposeInterest(vchAmtBo.getBcPaidImposeInterest().add(repayingAmount));
							resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
									repayingAmount));// 返回已还罚息
							repayingDetail.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest().add(
									repayingAmount));// 已换罚息
							arrearsDetail.setNotyetImposeInterest(arrearsDetail.getNotyetImposeInterest().subtract(
									repayingAmount)); // 未还90天内罚息
							repayingAmount = BigDecimal.ZERO;
						}
					}
				}
				repayingPlanDetail = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
				// 正常还款
				if (!norm2over && repayingAmount.compareTo(BigDecimal.ZERO) > 0
						&& STATUS_WH.equals(repayingPlanDetail.getStatus())) {
					repayingDetail = rdList.get(rpdYetRepayedList.size() - 1);
					arrearsDetail = adList.get(rpdYetRepayedList.size() - 1);
					repayingDetail.setRepayingDate(repayedDate); // 还款日期
					// 当期全部还清
					if (repayingAmount.compareTo(repayingPlanDetail.getCurrentPrincipal().add(
							repayingPlanDetail.getCurrentInterest()).subtract(
							repayingDetail.getRepayedInterest().add(repayingDetail.getRepayedPrincipal()))) >= 0) {
						repayingAmount = repayingAmount.subtract(repayingPlanDetail.getCurrentPrincipal().add(
								repayingPlanDetail.getCurrentInterest()).subtract(
								repayingDetail.getRepayedInterest().add(repayingDetail.getRepayedPrincipal())));
						resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
								repayingPlanDetail.getCurrentPrincipal().subtract(
										repayingDetail.getRepayedPrincipal()))); // 返回已还本金
						resultVO.setRepayedCurrentPricipal(repayingPlanDetail.getCurrentPrincipal().subtract(
								repayingDetail.getRepayedPrincipal())); // 返回已还当期本金
						resultVO.setRepayedCurrentInterest(repayingPlanDetail.getCurrentInterest().subtract(
								repayingDetail.getRepayedInterest())); // 返回已还当期利息
						period++;
						repayingPlanDetail.setStatus(STATUS_YH);// 已还
						arrearsDetail.setStatus(STATUS_YH);// 已还
						repayingDetail.setRepayedInterest(repayingPlanDetail.getCurrentInterest()); // 已换利息
						repayingDetail.setRepayedPrincipal(repayingPlanDetail.getCurrentPrincipal());// 已换本金
					}
					// 还部分本金,利息全部还
					else if (repayingAmount.compareTo(repayingPlanDetail.getCurrentInterest().subtract(
							repayingDetail.getRepayedInterest())) > 0) {
						repayingAmount = repayingAmount.subtract(repayingPlanDetail.getCurrentInterest().subtract(
								repayingDetail.getRepayedInterest()));
						resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(repayingAmount)); // 返回已还本金
						resultVO.setRepayedCurrentPricipal(repayingAmount); // 返回已还当期本金
						resultVO.setRepayedCurrentInterest(repayingPlanDetail.getCurrentInterest().subtract(
								repayingDetail.getRepayedInterest())); // 返回已还当期利息
						repayingDetail.setRepayedInterest(repayingPlanDetail.getCurrentInterest()); // 已换利息
						repayingDetail
								.setRepayedPrincipal(repayingDetail.getRepayedPrincipal().add(repayingAmount));// 已换本金
						repayingAmount = BigDecimal.ZERO;
					}
					// 还部分利息
					else {
						resultVO.setRepayedCurrentInterest(repayingAmount); // 返回已还当期利息
						repayingDetail.setRepayedInterest(repayingDetail.getRepayedInterest().add(repayingAmount)); // 已换利息
						// 账务出账-逾期90天内利息
						vchAmtBo.setBcOverThanNoLossInterest(vchAmtBo.getBcOverThanNoLossInterest().add(
								repayingPlanDetail.getCurrentInterest().subtract(
										repayingDetail.getRepayedInterest())));
						repayingAmount = BigDecimal.ZERO;
					}
					// 出帐-应收本金
					vchAmtBo.setBcPaidOverPrincipal(vchAmtBo.getBcPaidOverPrincipal().add(
							resultVO.getRepayedCurrentPricipal()));
					// 出帐-应收利息
					vchAmtBo.setBcPaidOverInterest(vchAmtBo.getBcPaidOverInterest().add(
							resultVO.getRepayedCurrentInterest()));
					// 是否跑过利息结计
					if (STATUS_WH.equals(repayingPlanDetail.getIsInterestInContractRecord())) {
						vchAmtBo.setSetIntImposeInterest(vchAmtBo.getSetIntImposeInterest().add(
								vchAmtBo.getBcPaidCurrInterest()));
					}
				}
			}
			if (contract.getOvdueAmt().compareTo(BigDecimal.ZERO) < 0) {
				contract.setOvdueAmt(BigDecimal.ZERO);
			}
			if (contract.getOvdueInterest().compareTo(BigDecimal.ZERO) < 0) {
				contract.setOvdueInterest(BigDecimal.ZERO);
			}
			// 出转科目(已减值--未减值)
			for (int int_i = 0; int_i < overduePeriod; int_i++) {
				arrearsDetail = adList.get(int_i);
				// 逾期90天内
				if (LoanConstants.REPAYING_FLAG_OVERDUE.equals(arrearsDetail.getRepayingFlag())) {
					// 账务出账--已减值转未减值(逾期90天内利息+罚息)
					vchAmtBo.setBcOverThanNoLossInterest(vchAmtBo.getBcOverThanNoLossInterest().add(
							arrearsDetail.getNotyetImposeInterest()).add(arrearsDetail.getNotyetInterest()));
				}
				// 逾期90天以上
				else if (LoanConstants.REPAYING_FLAG_OVERTHEN90.equals(arrearsDetail.getRepayingFlag())) {
					// 账务出账--已减值转未减值(逾期90天以上利息+罚息)
					vchAmtBo.setBcOverThan90NoLossInterest(vchAmtBo.getBcOverThan90NoLossInterest().add(
							arrearsDetail.getNotyetImposeInterest()).add(arrearsDetail.getNotyetInterest()).add(
							arrearsDetail.getCarryoverImposeInterest()));
				}
			}
			// 逾期90天内
			if (LoanConstants.REPAYING_FLAG_OVERDUE.equals(arrearsDetail.getRepayingFlag())) {
				// 账务出账--已减值转未减值(逾期90天内贷款余额)
				vchAmtBo.setBcOverThanNoLossAmt(rp.getRepayedAmount()
						.subtract(resultVO.getRepayedPricipal()));
			}
			// 逾期90天以上
			else if (LoanConstants.REPAYING_FLAG_OVERTHEN90.equals(arrearsDetail.getRepayingFlag())) {
				// 账务出账--已减值转未减值(逾期90天以上贷款余额)
				vchAmtBo.setBcOverThan90NoLossAmt(rp.getRepayedAmount().subtract(
						resultVO.getRepayedPricipal()));
			}
		}else{
			//正常还款
			repayOfNormal(vchAmtBo, resultVO, rp, rdList.get(rdList.size()-1), rpdYetRepayedList.get(rpdYetRepayedList.size()-1), adList.get(adList.size()-1), repayingAmount, repayedDate, STATUS_YH, period);
		}
		// 多还部分
		if(repayingAmount.compareTo(BigDecimal.ZERO)>0){
			vchAmtBo.setBcBankManyPayedAmt(vchAmtBo.getBcBankManyPayedAmt().add(repayingAmount));//预收
		}
		// 更新计划表
		rp.setRepayedAmount(rp.getRepayedAmount().subtract(resultVO.getRepayedPricipal()));// 剩余本金
		rp.setRepayedPeriod(rp.getRepayedPeriod() + period); // 已还期次
		rp.setSurplusPeriod(rp.getSurplusPeriod() - period); // 剩余期次
		if (contract.getLastOvdueDate() != null) { // 逾期
			resultVO.setStatus(STATUS_YQ);
			contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S3"));
			rp.setContContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode","S3"));
		} else if (rp.getSurplusPeriod() <= 0) { // 结清
			resultVO.setStatus(STATUS_YH);
			contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S5"));
			rp.setContContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S5"));
		} else {
			contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S2"));
			rp.setContContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S2"));
			resultVO.setStatus(STATUS_WH);
		}
		rp.setContLastOvdueDate(contract.getLastOvdueDate());// 还款计划--合同最近逾期日期
		rp.setContFirstOvdueDate(contract.getFirstOvdueDate());// 还款计划--合同首次逾期日期
		rp.setContFiveclassification(contract.getFiveclassification());// 还款计划--合同五级分类
		rp.setContOvdueAmt(contract.getOvdueAmt());// 还款计划--合同逾期金额
		rp.setContOvdueDays(contract.getOvdueDays().intValue());// 还款计划--合同逾期天数
		rp.setContOvdueInterest(contract.getOvdueInterest());// 还款计划--合同逾期利息
		rp.setContOvdueTime(contract.getOvdueTime().intValue());// 还款计划--合同逾期次数
		rp.setContContractStatusCd(contract.getContractStatusCd()); // 还款计划--合同状态
		//财务还款接口
		String transactionNO =loanAccountingService.doVchByPaid(vchAmtBo, contract.getApplyOrgId(), Long.parseLong(contract.getApplyUserNum()));//单据编号
		//更新欠款明细
		arrearsDetail = adList.get(adList.size() - 1);
		repayingPlanDetail = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
		List<ArrearsDetail> update_adList =dynamicQuery.query(ArrearsDetail.class, "from ArrearsDetail where repayingPlanDetailId in (select rpd.repayingPlanDetailId from RepayingPlanDetail rpd  where rpd.repayingPlanId =?1  and rpd.currentPeriod >?2)", rp.getRepayingPlanId(),repayingPlanDetail.getCurrentPeriod());
		// 改变当期以后状态
		if (repayingAmount.compareTo(BigDecimal.ZERO) > 0 || contract.getLastOvdueDate() == null) { // 逾期已还or多还
			if (STATUS_WH.equals(arrearsDetail.getRepayingFlag())) { // 当期未还
				arrearsDetail.setRepayingCalFlag(STATUS_WH);
				arrearsDetail.setRepayingFirstFlag(STATUS_WH);
				arrearsDetail.setRepayingFlag(STATUS_WH);
			}
			if (update_adList != null && update_adList.size() > 0) {
				if (update_adList != null && update_adList.size() > 0) {
					for (ArrearsDetail arrearsDetailVO : update_adList) {
						arrearsDetailVO.setRepayingCalFlag(STATUS_WH);
						arrearsDetailVO.setRepayingFirstFlag(STATUS_WH);
						arrearsDetailVO.setRepayingFlag(STATUS_WH);
					}
					arrearsDetailDao.save(adList);
				}
			}
		} else {
			if (update_adList != null && update_adList.size() > 0) {
				if (update_adList != null && update_adList.size() > 0) {
					for (ArrearsDetail arrearsDetailVO : update_adList) {
						arrearsDetailVO.setRepayingCalFlag(arrearsDetail.getRepayingCalFlag());
					}
					arrearsDetailDao.save(adList);
				}
			}
		}
		contractDao.save(contract);
		repayingPlanDao.save(rp);
		repayingPlanDetailDao.save(rpdYetRepayedList);
		repayingDetailDao.save(rdList);
		arrearsDetailDao.save(adList);
		//更新还款明细
		this.updateRepayingDetail(rp);
		//更新换计划明细单据编号
		this.updateTransactionNoOfRpRpd(rp.getTransactionNo(),transactionNO,false, null);
		return transactionNO;
		
	}
	/**
	 * 更新逾期情况表
	 * @param repayingPlanDetailId
	 * @param repayedDate
	 */
	private void updateOverdueStatus(Long repayingPlanDetailId,
			Date repayedDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("from OverdueState where repayingPlanDetailId = ?1");
		// 更新逾期情况表
		List<OverdueState> overdueStatusList = dynamicQuery.query(OverdueState.class, sb.toString(), repayingPlanDetailId);
		if (overdueStatusList != null && overdueStatusList.size() > 0) {
			OverdueState updateVO = overdueStatusList.get(0);
			updateVO.setRepaymentDate(repayedDate);// 还款日期
			updateVO.setDueDay(DateTools.getDateDiff(updateVO.getOverdueDate(), repayedDate));// 逾期天数
			updateVO.setSysUpdateDate(CommonHelper.getNow());
			updateVO.setPayLoanStatus(LoanConstants.BATCH_ONE); // 未入账
			overdueStateDao.save(updateVO);
		}
	}
	/**
	 * 计算总罚息
	 * 
	 * @param dll逾期日利率
	 * @param pricipal本金
	 * @param interest利息
	 * @param imposeInterest罚息
	 * @param dateCnt逾期天数
	 * @return
	 */
	private BigDecimal calCulateImposeInterest(BigDecimal dll, BigDecimal pricipal, BigDecimal interest,
			BigDecimal imposeInterest, int dateCnt) {
		if (dateCnt <= 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal temp = pricipal;
		// 关闭时使用单利算法 0:关闭 1:打开
		if ("0".equals(loanCommonServeice.getSysParamVal("05", "openCompoundInterest"))) {
			pricipal = pricipal.add(interest);
			pricipal = pricipal.multiply(dll).multiply(new BigDecimal(dateCnt));
		} else {// 打开时时使用复利算法
			pricipal = pricipal.add(interest).add(imposeInterest);
			pricipal = pricipal.multiply(dll.add(new BigDecimal(1)).pow(dateCnt));
			pricipal = pricipal.subtract(temp).subtract(interest).subtract(imposeInterest);
		}
		pricipal = pricipal.setScale(2, BigDecimal.ROUND_HALF_UP);
		return pricipal;

	}

	/**
	 * 正常还款
	 * @param doVchAmtBo
	 * @param resultVO
	 * @param rp
	 * @param rd
	 * @param rpd
	 * @param ad
	 * @param repayingAmount
	 * @param repayedDate
	 * @param endStatus
	 * @param numOfYesYet 
	 */
	private int repayOfNormal(DoVchAmtBo doVchAmtBo,InterestResultVO resultVO, RepayingPlan rp, RepayingDetail rd,RepayingPlanDetail rpd,ArrearsDetail ad, BigDecimal repayingAmount,Date repayedDate,String endStatus, int numOfYesYet){
		rd.setRepayingDate(repayedDate); // 还款日期
		// 当期全部还清					
		//当期应还本金+当期应还利息-当期已还利息-当期已还本金
		if (repayingAmount.compareTo(rpd.getCurrentPrincipal().add(
				rpd.getCurrentInterest()).subtract(
				rd.getRepayedInterest().add(rd.getRepayedPrincipal()))) >= 0) {
			//本次实还 = 当期应还本金+当期应还利息-当期已还利息（不含本次还款）-当期已还本金（不含本次还款）
			repayingAmount = repayingAmount.subtract(rpd.getCurrentPrincipal().add(
					rpd.getCurrentInterest()).subtract(
					rd.getRepayedInterest().add(rd.getRepayedPrincipal())));
			resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
					rpd.getCurrentPrincipal().subtract(rd.getRepayedPrincipal()))); // 返回本次还款中已还本金 = 本次还款中已还本金 + 本期应还本金 - 本期已还本金（不含本次还款）
			resultVO.setRepayedCurrentPricipal(rpd.getCurrentPrincipal().subtract(
					rd.getRepayedPrincipal())); // 返回此次还款中已还当期本金 = 本期应还本金 - 本期已还本金（不含此次还款）
			resultVO.setRepayedCurrentInterest(rpd.getCurrentInterest().subtract(
					rd.getRepayedInterest())); // 返回此次还款中已还当期利息 = 本期已还利息 - 本期已还利息（不含此次还款）
			numOfYesYet++;
			rpd.setStatus(endStatus);// 已还
			ad.setStatus(endStatus);// 已还
			rd.setRepayedInterest(rpd.getCurrentInterest()); // 已还利息 = 本期应还利息
			rd.setRepayedPrincipal(rpd.getCurrentPrincipal());// 已还本金 = 本期应还本金
		}
		// 还部分本金,利息全部还
		else if (repayingAmount.compareTo(rpd.getCurrentInterest().subtract(
				rd.getRepayedInterest())) > 0) {
			//此次还款中的还本金额（除还息外的多余部分） = 本期应还利息 - 本期已还利息（不含此次还款）
			repayingAmount = repayingAmount.subtract(rpd.getCurrentInterest().subtract(
					rd.getRepayedInterest())); 
			resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(repayingAmount)); // 返回已还本金 = 已还本金 + 此次还款中的还本金额
			resultVO.setRepayedCurrentPricipal(repayingAmount); // 返回此次还款中已还当期本金 = 此次还款中的还本金额
			resultVO.setRepayedCurrentInterest(rpd.getCurrentInterest().subtract(
					rd.getRepayedInterest())); // 返回此次还款中已还当期利息 = 当期应还利息 - 当期已还利息 （不含本次）
			rd.setRepayedInterest(rpd.getCurrentInterest()); // 已还利息 = 当期应还利息
			rd.setRepayedPrincipal(rd.getRepayedPrincipal().add(repayingAmount));// 已还本金 = 当期已还本金 （不含本次） + 此次还款中的还本金额
			repayingAmount = BigDecimal.ZERO;
		}
		// 还部分利息
		else {
			resultVO.setRepayedCurrentInterest(repayingAmount); // 返回此次还款中已还当期利息 = 本次还款金额
			rd.setRepayedInterest(rd.getRepayedInterest().add(repayingAmount)); // 已还利息 = 当期已还利息（不含本次） + 本次还款金额
			// 账务出账--已减值转未减值(正常利息)
			doVchAmtBo.setBcNoLossInterest(rpd.getCurrentInterest().subtract(
					rd.getRepayedInterest()));
			repayingAmount = BigDecimal.ZERO;
		}
		// 是否跑过利息结计
		if (endStatus.equals(rpd.getIsInterestInContractRecord())) {
			// 出帐-应收本金
			doVchAmtBo.setBcPaidCurrPrincipal(resultVO.getRepayedCurrentPricipal());
			// 出帐-应收利息
			doVchAmtBo.setBcPaidCurrInterest(resultVO.getRepayedCurrentInterest());
		} else {
			// 预收利息
			doVchAmtBo.setBcBankManyPayedAmt(resultVO.getRepayedCurrentInterest());
			// 预收本金
			doVchAmtBo.setSetIntReceCurrAmt(resultVO.getRepayedCurrentPricipal());
		}
		// 账务出账--已减值转未减值(正常贷款余额)
		doVchAmtBo.setBcNoLossAmt(rp.getRepayedAmount().subtract(resultVO.getRepayedPricipal()));
		return numOfYesYet;
	}
	
	/**逾期*/
	private int repayOfOverdue(Contract contract,RepayingPlan rp,BigDecimal repayingAmount,BigDecimal totalOverdueOutAmt,
			ParamsOfInMoney inMoney,final Date overdue90Date,int curYetOffCount,
			DoVchAmtBo amtVO,InterestResultVO resultVO, List<RepayingDetail> rdList,
			List<RepayingPlanDetail> rpdYetRepayedList,List<ArrearsDetail> adList){
		//rpdYetRepayedList 当期之前未还款的
		if (contract.getOvdueAmt() == null) { //逾期金额
			contract.setOvdueAmt(BigDecimal.ZERO);
		}
		if (contract.getOvdueInterest() == null) { //逾期利息
			contract.setOvdueInterest(BigDecimal.ZERO);
		}
		int overduePeriod = inMoney.isNormal2Overdue() ? rpdYetRepayedList.size() : (inMoney.isOverdueFlag() ? rpdYetRepayedList.size()
				: rpdYetRepayedList.size() - 1);
		Date lastOvdueDate = null;
		BigDecimal dll = rp.getOverdueRate().divide(LoanConstants.DCNT, 50, BigDecimal.ROUND_HALF_UP);
		ArrearsDetail first_ad = adList.get(0);
		ArrearsDetail t_ad=null;//临时
		RepayingPlanDetail t_rpd=null;//临时
		RepayingDetail item_rd=null;//临时
		BigDecimal interest = BigDecimal.ZERO;
		BigDecimal impose_interest=BigDecimal.ZERO;
		final String NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S0");
		final String YES_YET=loanCommonServeice.getCodeVal("PlanStatus", "S1");
		final String OVERDUE_NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S2");
		// 逾期90天内转逾期90天以上
		if (!"2".equals(first_ad.getRepayingFlag())
				&& DateTools.getDateDiff(overdue90Date, inMoney.getRepayDate()) > 0) {
			amtVO.setChangeOverFlag(true);
			// 出帐-逾期90天转逾期90天以上
			amtVO.setBcOverThan90Principal(rp.getRepayedAmount());
			// 逾期90天以上(补出90逾期科目)
			for (int int_i = 0; int_i < rpdYetRepayedList.size(); int_i++) {
				t_ad = adList.get(int_i);
				if (OVERDUE_NOT_YET.equals(t_ad.getStatus())) {
					// 计算罚息,最近计息日期--逾期90天日期
					impose_interest = this.calCulateImposeInterest(dll, t_ad.getNotyetPricipal(),
							t_ad.getNotyetInterest(), t_ad.getNotyetImposeInterest(),
							DateTools.getDateDiff(t_ad.getRecentlyDate(), overdue90Date));
					// 出帐-逾期90天结计利息
					amtVO.setSetIntImposeInterest(amtVO.getSetIntImposeInterest().add(impose_interest).add(
							t_ad.getYearImposeInterest()));
					// 未还罚息
					t_ad.setNotyetImposeInterest(t_ad.getNotyetImposeInterest().add(
							impose_interest).add(t_ad.getYearImposeInterest()));
					t_ad.setYearImposeInterest(BigDecimal.ZERO);
					if (DateTools.getDateDiff(t_ad.getRecentlyDate(), overdue90Date) > 0) {
						t_ad.setRecentlyDate(overdue90Date); // 最近计息日
					}
					t_ad.setRepayingFlag(LoanConstants.REPAYING_FLAG_OVERTHEN90); // 还款标识
					t_ad.setRepayingCalFlag("1"); // 还款计算标识
					// 首次逾期(利息正常->冲转)
					if ("1".equals(t_ad.getRepayingFirstFlag())) {
						// 出帐-冲未还正常利息
						amtVO.setBcOverThan90CurrInterest(t_ad.getNotyetInterest());
					} else {
						// 出帐-冲未还逾期90天内利息
						amtVO.setBcOverThan90OverInterest(amtVO.getBcOverThan90OverInterest().add(
								t_ad.getNotyetInterest()));
					}
				}
			}
		}
		if (DateTools.getDateDiff(first_ad.getPlanRepayintDate(), inMoney.getRepayDate()) > 90
				|| LoanConstants.REPAYING_FLAG_OVERTHEN90.equals(t_ad.getRepayingFlag())) { // 逾期90天以上
			// 还款顺序 ：最远一期开始还,本金;
			// 最远一期开始还,利息;
			// 最远一期开始还,罚息;
			// (第1,2,3逾期,最远一期指:第1-3期本金,第1-3期利息,第1-3期罚息)
			// 本金
			for (int int_i = 0; int_i < overduePeriod; int_i++) {
				t_rpd = rpdYetRepayedList.get(int_i);
				item_rd = rdList.get(int_i);
				t_ad = adList.get(int_i);
				t_ad.setRepayingFlag("2");
				// 计算罚息
				impose_interest = this.calCulateImposeInterest(dll, t_ad.getNotyetPricipal(),
						t_ad.getNotyetInterest(), t_ad.getNotyetImposeInterest(), DateTools
								.getDateDiff(t_ad.getRecentlyDate(), inMoney.getRepayDate()));
				// 出帐-逾期90天以上结计利息
				amtVO.setSetIntOverThan90ImposeInterest(amtVO.getSetIntOverThan90ImposeInterest().add(
						impose_interest).add(t_ad.getYearImposeInterest()));
				// 未还90天以上未还罚息
				t_ad.setCarryoverImposeInterest(t_ad.getCarryoverImposeInterest().add(
						impose_interest).add(t_ad.getYearImpose90Interest()));// 罚息
				t_ad.setYearImpose90Interest(BigDecimal.ZERO);
				t_ad.setRecentlyDate(inMoney.getRepayDate());// 最近计息日
				item_rd.setRepayingDate(inMoney.getRepayDate()); // 还款日期
				if (repayingAmount.compareTo(BigDecimal.ZERO) > 0) {
					if (repayingAmount.compareTo(t_ad.getNotyetPricipal()) > 0) {
						contract
								.setOvdueAmt(contract.getOvdueAmt().subtract(t_ad.getNotyetPricipal()));
						repayingAmount = repayingAmount.subtract(t_ad.getNotyetPricipal());
						resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
								t_ad.getNotyetPricipal())); // 返回已还本金
						resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
								t_ad.getNotyetPricipal())); // 返回已还本息
						resultVO.setLoanAmount(resultVO.getLoanAmount().add(t_ad.getNotyetPricipal())); // 返回实还拖欠本金
						// 出帐-还款本金
						amtVO.setBcPaidOverThan90Principal(amtVO.getBcPaidOverThan90Principal().add(
								t_ad.getNotyetPricipal()));
						item_rd.setRepayedPrincipal(t_rpd.getCurrentPrincipal());// 已还本金
						t_ad.setNotyetPricipal(BigDecimal.ZERO);// 未还本金
					} else {
						contract.setOvdueAmt(contract.getOvdueAmt().subtract(repayingAmount));
						resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(repayingAmount)); // 返回已还本金
						resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
								repayingAmount)); // 返回已还本息
						resultVO.setLoanAmount(resultVO.getLoanAmount().add(repayingAmount)); // 返回实还拖欠本金
						// 出帐-还款本金
						amtVO.setBcPaidOverThan90Principal(amtVO.getBcPaidOverThan90Principal().add(
								repayingAmount));
						t_ad.setNotyetPricipal(t_ad.getNotyetPricipal().subtract(
								repayingAmount));// 未还本金
						item_rd.setRepayedPrincipal(item_rd.getRepayedPrincipal().add(
								repayingAmount));// 已还本金
						repayingAmount = BigDecimal.ZERO;
						break;
					}
				}
			}
			final int lastIndexOfRpdList=rpdYetRepayedList.size() - 1;
			t_rpd = rpdYetRepayedList.get(lastIndexOfRpdList);
			// 正常还款-当期本金
			if (repayingAmount.compareTo(BigDecimal.ZERO) > 0 && !inMoney.isNormal2Overdue()
					&& NOT_YET.equals(t_rpd.getStatus())) {
				item_rd = rdList.get(lastIndexOfRpdList);
				item_rd.setRepayingDate(inMoney.getRepayDate()); // 还款日期
				if (repayingAmount.compareTo(t_rpd.getCurrentPrincipal().subtract(
						item_rd.getRepayedPrincipal())) > 0) {
					resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
							t_rpd.getCurrentPrincipal().subtract(
									item_rd.getRepayedPrincipal()))); // 返回已还本金
					resultVO.setRepayedCurrentPricipal(t_rpd.getCurrentPrincipal().subtract(
							item_rd.getRepayedPrincipal())); // 返回已还当期本金
					// 出帐-还款本金
					amtVO.setBcPaidOverThan90Principal(amtVO.getBcPaidOverThan90Principal().add(
							t_rpd.getCurrentPrincipal().subtract(
									item_rd.getRepayedPrincipal())));
					repayingAmount = repayingAmount.subtract(t_rpd.getCurrentPrincipal().subtract(
							item_rd.getRepayedPrincipal()));
					item_rd.setRepayedPrincipal(t_rpd.getCurrentPrincipal()); // 已还本金
				} else {
					resultVO.setRepayedCurrentPricipal(repayingAmount); // 返回已还当期本金
					// 出帐-还款本金
					amtVO.setBcPaidOverThan90Principal(amtVO.getBcPaidOverThan90Principal().add(
									repayingAmount));
					item_rd.setRepayedPrincipal(item_rd.getRepayedPrincipal().add(repayingAmount)); // 已还本金
					repayingAmount = BigDecimal.ZERO;
				}
			}
			if (repayingAmount.compareTo(BigDecimal.ZERO) > 0) {
				// 利息
				for (int int_i = 0; int_i < overduePeriod; int_i++) {
					t_rpd = rpdYetRepayedList.get(int_i);
					item_rd = rdList.get(int_i);
					t_ad = adList.get(int_i);
					if (repayingAmount.compareTo(t_ad.getNotyetInterest()) > 0) {
						contract.setOvdueInterest(contract.getOvdueInterest().subtract(
								t_ad.getNotyetInterest()));
						repayingAmount = repayingAmount.subtract(t_ad.getNotyetInterest());
						resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
								t_ad.getNotyetInterest()));// 返回已还本息
						// 逾期90天内
						if ("1".equals(t_ad.getRepayingCalFlag())) {
							// 出帐-逾期90天内利息
							amtVO.setBcIn90Interest(amtVO.getBcIn90Interest().add(
									t_ad.getNotyetInterest()));
						}
						// 逾期90天以上
						else {
							// 出帐-逾期90天以上利息
							amtVO.setBcPaidOverThan90Interest(amtVO.getBcPaidOverThan90Interest().add(
									t_ad.getNotyetInterest()));
						}
						item_rd.setRepayedInterest(t_rpd.getCurrentInterest()); // 已还利息
						t_ad.setNotyetInterest(BigDecimal.ZERO); // 未还利息
					} else {
						contract.setOvdueInterest(contract.getOvdueInterest().subtract(repayingAmount));
						resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
								repayingAmount));// 返回已还本息
						// 逾期90天内
						if ("1".equals(t_ad.getRepayingCalFlag())) {
							// 出帐-逾期90天内利息
							amtVO.setBcIn90Interest(amtVO.getBcIn90Interest().add(repayingAmount));
						}
						// 逾期90天以上
						else {
							// 出帐-逾期90天以上利息
							amtVO.setBcPaidOverThan90Interest(amtVO.getBcPaidOverThan90Interest().add(
									repayingAmount));
						}
						item_rd.setRepayedInterest(item_rd.getRepayedInterest().add(
								repayingAmount)); // 已还利息
						t_ad.setNotyetInterest(t_ad.getNotyetInterest().subtract(
								repayingAmount)); // 未还利息
						repayingAmount = BigDecimal.ZERO;
						break;
					}
				}
			}

			t_rpd = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
			// 正常还款-当期利息
			if (repayingAmount.compareTo(BigDecimal.ZERO) > 0 && !inMoney.isNormal2Overdue()
					&& NOT_YET.equals(t_rpd.getStatus())) {
				item_rd = rdList.get(rpdYetRepayedList.size() - 1);
				t_ad = adList.get(rpdYetRepayedList.size() - 1);
				if (repayingAmount.compareTo(t_rpd.getCurrentInterest().subtract(
						item_rd.getRepayedInterest())) > 0) {
					curYetOffCount++;
					resultVO.setRepayedCurrentInterest(t_rpd.getCurrentInterest().subtract(
							item_rd.getRepayedInterest())); // 返回已还当期利息
					repayingAmount = repayingAmount.subtract(t_rpd.getCurrentInterest().subtract(
							item_rd.getRepayedInterest()));
					item_rd.setRepayedInterest(t_rpd.getCurrentInterest()); // 已还利息
					t_ad.setStatus(YES_YET);
					t_rpd.setStatus(YES_YET);
				} else {
					resultVO.setRepayedCurrentInterest(repayingAmount); // 返回已还当期利息
					item_rd.setRepayedInterest(item_rd.getRepayedInterest().add(repayingAmount)); // 已还利息
					// 账务出账--已减值转未减值(逾期90天以上利息)
					amtVO.setBcOverThan90NoLossInterest(amtVO.getBcOverThan90NoLossInterest().add(
							t_rpd.getCurrentInterest().subtract(
									item_rd.getRepayedInterest())));
					repayingAmount = BigDecimal.ZERO;
				}
				// 逾期90天内
				if ("1".equals(t_ad.getRepayingCalFlag())) {
					// 出帐-逾期90天内利息
					amtVO
							.setBcIn90Interest(amtVO.getBcIn90Interest().add(
									resultVO.getRepayedCurrentInterest()));
				}
				// 逾期90天以上
				else {
					// 出帐-逾期90天以上利息
					amtVO.setBcPaidOverThan90Interest(amtVO.getBcPaidOverThan90Interest().add(
							resultVO.getRepayedCurrentInterest()));
				}
			}

			// 罚息
			for (int int_i = 0; int_i < overduePeriod; int_i++) {
				t_rpd = rpdYetRepayedList.get(int_i);
				item_rd = rdList.get(int_i);
				t_ad = adList.get(int_i);
				// 罚息
				if (repayingAmount.compareTo(t_ad.getNotyetImposeInterest().add(
						t_ad.getCarryoverImposeInterest())) >= 0) {
					curYetOffCount++;
					resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
							t_ad.getNotyetImposeInterest().add(
									t_ad.getCarryoverImposeInterest()))); // 返回已还罚息
					// 出帐-逾期90天内利息
					amtVO.setBcIn90Interest(amtVO.getBcIn90Interest().add(
							t_ad.getNotyetImposeInterest()));
					// 出帐-逾期90天以上利息
					amtVO.setBcPaidOverThan90Interest(amtVO.getBcPaidOverThan90Interest().add(
							t_ad.getCarryoverImposeInterest()));
					lastOvdueDate = contract.getLastOvdueDate(); // 合同最近逾期日期
					repayingAmount = repayingAmount.subtract(t_ad.getNotyetImposeInterest().add(
							t_ad.getCarryoverImposeInterest()));
					t_rpd.setStatus(YES_YET); // 已还
					item_rd.setRepayedImposeInterest(item_rd.getRepayedImposeInterest().add(
							t_ad.getNotyetImposeInterest().add(
									t_ad.getCarryoverImposeInterest()))); // 已还罚息
					t_ad.setStatus(YES_YET); // 已还
					t_ad.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
					t_ad.setCarryoverImposeInterest(BigDecimal.ZERO); // 未还90天以上罚息
					if ((int_i + 1) == overduePeriod) {// 逾期全部还清
						contract.setLastOvdueDate(null);
					} else {
						contract.setLastOvdueDate(adList.get(int_i + 1).getPlanRepayintDate());
					}
					// 计算合同连续逾期天数
					if (contract.getLastOvdueDate() == null) {
						contract.setOvdueDays(contract.getOvdueDays()
								+ DateTools.getDateDiff(lastOvdueDate, inMoney.getRepayDate()));
					} else {
						contract.setOvdueDays(contract.getOvdueDays()
								+ DateTools.getDateDiff(lastOvdueDate, contract.getLastOvdueDate()));
					}

					// 更新逾期情况表
					if (t_ad.getRecentlyDate().compareTo(inMoney.getRepayDate()) < 0) {
						this.updateOverdueStatus(t_rpd.getRepayingPlanDetailId(), inMoney.getRepayDate());
					} else {
						this.updateOverdueStatus(t_rpd.getRepayingPlanDetailId(), t_ad.getRecentlyDate());
					}
					if (repayingAmount.compareTo(BigDecimal.ZERO) == 0) {
						break;
					}
				}
				// 还逾期90天内,部分90天以上
				else if (repayingAmount.compareTo(t_ad.getNotyetImposeInterest()) > 0) {
					resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(repayingAmount)); // 返回已还罚息
					// 出帐-逾期90天内利息
					amtVO.setBcIn90Interest(amtVO.getBcIn90Interest().add(
							t_ad.getNotyetImposeInterest()));
					item_rd.setRepayedImposeInterest(item_rd.getRepayedImposeInterest().add(
							repayingAmount));// 已还罚息
					repayingAmount = repayingAmount.subtract(t_ad.getNotyetImposeInterest());
					// 出帐-逾期90天以上利息
					amtVO.setBcPaidOverThan90Interest(amtVO.getBcPaidOverThan90Interest().add(repayingAmount));
					t_ad.setNotyetImposeInterest(BigDecimal.ZERO);// 未还90天内罚息
					t_ad.setCarryoverImposeInterest(t_ad.getCarryoverImposeInterest()
							.subtract(repayingAmount));
					repayingAmount = BigDecimal.ZERO;
					break;
				}
				// 部分90天以上
				else {
					resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(repayingAmount)); // 返回已还罚息
					// 出帐-逾期90天内利息
					amtVO.setBcIn90Interest(amtVO.getBcIn90Interest().add(repayingAmount));
					item_rd.setRepayedImposeInterest(item_rd.getRepayedImposeInterest().add(
							repayingAmount));// 已还罚息
					t_ad.setNotyetImposeInterest(t_ad.getNotyetImposeInterest().subtract(
							repayingAmount));// 未还90天内罚息
					repayingAmount = BigDecimal.ZERO;
					break;
				}
			}

		} else { // 逾期90天以内
			amtVO.setChangeOverFlag(inMoney.isNormal2Overdue());
			// 出帐-转逾期金额
			amtVO.setBcOverPrincipal(totalOverdueOutAmt);
			// 还款顺序 ：最远一期开始还,罚息->利息->本金(第1,2,3逾期,最远一期指:第1期)
			// 逾期
			for (int int_i = 0; int_i < overduePeriod; int_i++) {
				t_rpd = rpdYetRepayedList.get(int_i);
				item_rd = rdList.get(int_i);
				t_ad = adList.get(int_i);
				// 计算罚息
				impose_interest = this.calCulateImposeInterest(dll, t_ad.getNotyetPricipal(),
						t_ad.getNotyetInterest(), t_ad.getNotyetImposeInterest(), DateTools
								.getDateDiff(t_ad.getRecentlyDate(), inMoney.getRepayDate()));
				// 出帐-计提罚息
				amtVO.setSetIntImposeInterest(amtVO.getSetIntImposeInterest().add(impose_interest).add(
						t_ad.getYearImposeInterest()));
				t_ad.setNotyetImposeInterest(t_ad.getNotyetImposeInterest().add(
						impose_interest).add(t_ad.getYearImposeInterest()));// 罚息
				t_ad.setYearImposeInterest(BigDecimal.ZERO);
				t_ad.setRecentlyDate(inMoney.getRepayDate());// 最近计息日
				item_rd.setRepayingDate(inMoney.getRepayDate()); // 还款日期
				if (repayingAmount.compareTo(BigDecimal.ZERO) > 0) {
					// 全部还
					if (repayingAmount.compareTo(t_ad.getNotyetInterest().add(
							t_ad.getNotyetPricipal()).add(t_ad.getNotyetImposeInterest())) >= 0) {
						lastOvdueDate = contract.getLastOvdueDate(); // 合同最近逾期日期
						contract
								.setOvdueAmt(contract.getOvdueAmt().subtract(t_ad.getNotyetPricipal()));
						contract.setOvdueInterest(contract.getOvdueInterest().subtract(
								t_ad.getNotyetInterest()));
						resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
								t_ad.getNotyetPricipal())); // 返回已还本金
						resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
								t_ad.getNotyetPricipal().add(t_ad.getNotyetInterest())));// 返回已还本息
						resultVO.setLoanAmount(resultVO.getLoanAmount().add(t_ad.getNotyetPricipal()));// 返回实还拖欠本金
						resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
								t_ad.getNotyetImposeInterest()));// 返回已还罚息
						// 出帐-逾期90天内本金
						amtVO.setBcPaidOverPrincipal(amtVO.getBcPaidOverPrincipal().add(
								t_ad.getNotyetPricipal()));
						// 出帐-逾期90天内利息
						amtVO.setBcPaidOverInterest(amtVO.getBcPaidOverInterest().add(
								t_ad.getNotyetInterest()));
						// 是否跑过利息结计
						if ("1".equals(t_rpd.getIsInterestInContractRecord())) {
							amtVO.setSetIntImposeInterest(amtVO.getSetIntImposeInterest().add(
									t_ad.getNotyetInterest()));
						}
						// 出帐-逾期90天内罚息
						amtVO.setBcPaidImposeInterest(amtVO.getBcPaidImposeInterest().add(
								t_ad.getNotyetImposeInterest()));
						repayingAmount = repayingAmount
								.subtract(t_ad.getNotyetInterest().add(
										t_ad.getNotyetPricipal()).add(
										t_ad.getNotyetImposeInterest()));
						curYetOffCount++;
						t_rpd.setStatus(YES_YET);// 已还
						item_rd.setRepayedImposeInterest(item_rd.getRepayedImposeInterest().add(
								t_ad.getNotyetImposeInterest()));// 已换罚息
						item_rd.setRepayedInterest(t_rpd.getCurrentInterest());// 已换利息
						item_rd.setRepayedPrincipal(t_rpd.getCurrentPrincipal());// 已换本金
						t_ad.setStatus(YES_YET); // 已还
						t_ad.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
						t_ad.setNotyetInterest(BigDecimal.ZERO); // 未还利息
						t_ad.setNotyetPricipal(BigDecimal.ZERO); // 未还本金
						if ((int_i + 1) == overduePeriod) {// 逾期全部还清
							contract.setLastOvdueDate(null);
						} else {
							contract.setLastOvdueDate(adList.get(int_i + 1).getPlanRepayintDate());
						}
						// 计算合同连续逾期天数
						if (contract.getLastOvdueDate() == null) {
							contract.setOvdueDays(contract.getOvdueDays()
									+ DateTools.getDateDiff(lastOvdueDate, inMoney.getRepayDate()));
						} else {
							contract.setOvdueDays(contract.getOvdueDays()
									+ DateTools.getDateDiff(lastOvdueDate, contract.getLastOvdueDate()));
						}
						// 更新逾期情况表
						if (t_ad.getRecentlyDate().compareTo(inMoney.getRepayDate()) < 0) {
							this.updateOverdueStatus(t_rpd.getRepayingPlanDetailId(), inMoney.getRepayDate());
						} else {
							this.updateOverdueStatus(t_rpd.getRepayingPlanDetailId(), t_ad
									.getRecentlyDate());
						}
					}
					// 还部分本金,罚息+利息全部还
					else if (repayingAmount.compareTo(t_ad.getNotyetInterest().add(
							t_ad.getNotyetImposeInterest())) > 0) {
						repayingAmount = repayingAmount.subtract(t_ad.getNotyetInterest().add(
								t_ad.getNotyetImposeInterest()));
						contract.setOvdueAmt(contract.getOvdueAmt().subtract(repayingAmount));
						contract.setOvdueInterest(contract.getOvdueInterest().subtract(
								t_ad.getNotyetInterest()));
						resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(repayingAmount)); // 返回已还本金
						resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
								repayingAmount.add(t_ad.getNotyetInterest())));// 返回已还本息
						resultVO.setLoanAmount(resultVO.getLoanAmount().add(repayingAmount));// 返回实还拖欠本金
						resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
								t_ad.getNotyetImposeInterest()));// 返回已还罚息
						// 出帐-逾期90天内本金
						amtVO.setBcPaidOverPrincipal(amtVO.getBcPaidOverPrincipal().add(repayingAmount));
						// 出帐-逾期90天内利息
						amtVO.setBcPaidOverInterest(amtVO.getBcPaidOverInterest().add(
								t_ad.getNotyetInterest()));
						// 是否跑过利息结计
						if ("1".equals(t_rpd.getIsInterestInContractRecord())) {
							amtVO.setSetIntImposeInterest(amtVO.getSetIntImposeInterest().add(
									t_ad.getNotyetInterest()));
						}
						// 出帐-逾期90天内罚息
						amtVO.setBcPaidImposeInterest(amtVO.getBcPaidImposeInterest().add(
								t_ad.getNotyetImposeInterest()));
						item_rd.setRepayedImposeInterest(item_rd.getRepayedImposeInterest().add(
								t_ad.getNotyetImposeInterest()));// 已换罚息
						item_rd.setRepayedInterest(t_rpd.getCurrentInterest());// 已换利息
						item_rd.setRepayedPrincipal(item_rd.getRepayedPrincipal().add(
								repayingAmount)); // 已换本金
						t_ad.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
						t_ad.setNotyetInterest(BigDecimal.ZERO); // 未还利息
						t_ad.setNotyetPricipal(t_ad.getNotyetPricipal().subtract(
								repayingAmount));// 未还本金
						repayingAmount = BigDecimal.ZERO;
					}
					// 还部分利息,罚息全部还
					else if (repayingAmount.compareTo(t_ad.getNotyetImposeInterest()) > 0) {
						repayingAmount = repayingAmount.subtract(t_ad.getNotyetImposeInterest());
						contract.setOvdueInterest(contract.getOvdueInterest().subtract(repayingAmount));
						resultVO.setRepayedPricipalInterest(resultVO.getRepayedPricipalInterest().add(
								repayingAmount));// 返回已还本息
						resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
								t_ad.getNotyetImposeInterest()));// 返回已还罚息
						// 出帐-逾期90天内利息
						amtVO.setBcPaidOverInterest(amtVO.getBcPaidOverInterest().add(repayingAmount));
						// 是否跑过利息结计
						if ("1".equals(t_rpd.getIsInterestInContractRecord())) {
							amtVO.setSetIntImposeInterest(amtVO.getSetIntImposeInterest().add(repayingAmount));
						}
						// 出帐-逾期90天内罚息
						amtVO.setBcPaidImposeInterest(amtVO.getBcPaidImposeInterest().add(
								t_ad.getNotyetImposeInterest()));
						item_rd.setRepayedImposeInterest(item_rd.getRepayedImposeInterest().add(
								t_ad.getNotyetImposeInterest()));// 已换罚息
						item_rd.setRepayedInterest(item_rd.getRepayedInterest().add(
								repayingAmount));// 已换利息
						t_ad.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
						t_ad.setNotyetInterest(t_ad.getNotyetInterest().subtract(
								repayingAmount)); // 未还利息
						repayingAmount = BigDecimal.ZERO;
					}
					// 还部分罚息
					else {
						// 出帐-逾期90天内罚息
						amtVO.setBcPaidImposeInterest(amtVO.getBcPaidImposeInterest().add(repayingAmount));
						resultVO.setRepayedImposeInterest(resultVO.getRepayedImposeInterest().add(
								repayingAmount));// 返回已还罚息
						item_rd.setRepayedImposeInterest(item_rd.getRepayedImposeInterest().add(
								repayingAmount));// 已换罚息
						t_ad.setNotyetImposeInterest(t_ad.getNotyetImposeInterest().subtract(
								repayingAmount)); // 未还90天内罚息
						repayingAmount = BigDecimal.ZERO;
					}
				}
			}
			t_rpd = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
			// 正常还款
			if (!inMoney.isNormal2Overdue() && repayingAmount.compareTo(BigDecimal.ZERO) > 0
					&& NOT_YET.equals(t_rpd.getStatus())) {
				item_rd = rdList.get(rpdYetRepayedList.size() - 1);
				t_ad = adList.get(rpdYetRepayedList.size() - 1);
				item_rd.setRepayingDate(inMoney.getRepayDate()); // 还款日期
				// 当期全部还清
				if (repayingAmount.compareTo(t_rpd.getCurrentPrincipal().add(
						t_rpd.getCurrentInterest()).subtract(
						item_rd.getRepayedInterest().add(item_rd.getRepayedPrincipal()))) >= 0) {
					repayingAmount = repayingAmount.subtract(t_rpd.getCurrentPrincipal().add(
							t_rpd.getCurrentInterest()).subtract(
							item_rd.getRepayedInterest().add(item_rd.getRepayedPrincipal())));
					resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(
							t_rpd.getCurrentPrincipal().subtract(
									item_rd.getRepayedPrincipal()))); // 返回已还本金
					resultVO.setRepayedCurrentPricipal(t_rpd.getCurrentPrincipal().subtract(
							item_rd.getRepayedPrincipal())); // 返回已还当期本金
					resultVO.setRepayedCurrentInterest(t_rpd.getCurrentInterest().subtract(
							item_rd.getRepayedInterest())); // 返回已还当期利息
					curYetOffCount++;
					t_rpd.setStatus(YES_YET);// 已还
					t_ad.setStatus(YES_YET);// 已还
					item_rd.setRepayedInterest(t_rpd.getCurrentInterest()); // 已换利息
					item_rd.setRepayedPrincipal(t_rpd.getCurrentPrincipal());// 已换本金
				}
				// 还部分本金,利息全部还
				else if (repayingAmount.compareTo(t_rpd.getCurrentInterest().subtract(
						item_rd.getRepayedInterest())) > 0) {
					repayingAmount = repayingAmount.subtract(t_rpd.getCurrentInterest().subtract(
							item_rd.getRepayedInterest()));
					resultVO.setRepayedPricipal(resultVO.getRepayedPricipal().add(repayingAmount)); // 返回已还本金
					resultVO.setRepayedCurrentPricipal(repayingAmount); // 返回已还当期本金
					resultVO.setRepayedCurrentInterest(t_rpd.getCurrentInterest().subtract(
							item_rd.getRepayedInterest())); // 返回已还当期利息
					item_rd.setRepayedInterest(t_rpd.getCurrentInterest()); // 已换利息
					item_rd
							.setRepayedPrincipal(item_rd.getRepayedPrincipal().add(repayingAmount));// 已换本金
					repayingAmount = BigDecimal.ZERO;
				}
				// 还部分利息
				else {
					resultVO.setRepayedCurrentInterest(repayingAmount); // 返回已还当期利息
					item_rd.setRepayedInterest(item_rd.getRepayedInterest().add(repayingAmount)); // 已换利息
					// 账务出账-逾期90天内利息
					amtVO.setBcOverThanNoLossInterest(amtVO.getBcOverThanNoLossInterest().add(
							t_rpd.getCurrentInterest().subtract(
									item_rd.getRepayedInterest())));
					repayingAmount = BigDecimal.ZERO;
				}
				// 出帐-应收本金
				amtVO.setBcPaidOverPrincipal(amtVO.getBcPaidOverPrincipal().add(
						resultVO.getRepayedCurrentPricipal()));
				// 出帐-应收利息
				amtVO.setBcPaidOverInterest(amtVO.getBcPaidOverInterest().add(
						resultVO.getRepayedCurrentInterest()));
				// 是否跑过利息结计
				if ("1".equals(t_rpd.getIsInterestInContractRecord())) {
					amtVO.setSetIntImposeInterest(amtVO.getSetIntImposeInterest().add(
							amtVO.getBcPaidCurrInterest()));
				}
			}
		}
		if (contract.getOvdueAmt().compareTo(BigDecimal.ZERO) < 0) {
			contract.setOvdueAmt(BigDecimal.ZERO);
		}
		if (contract.getOvdueInterest().compareTo(BigDecimal.ZERO) < 0) {
			contract.setOvdueInterest(BigDecimal.ZERO);
		}
		// 出转科目(已减值--未减值)
		for (int int_i = 0; int_i < overduePeriod; int_i++) {
			t_ad = adList.get(int_i);
			// 逾期90天内
			if (LoanConstants.REPAYING_FLAG_OVERDUE.equals(t_ad.getRepayingFlag())) {
				// 账务出账--已减值转未减值(逾期90天内利息+罚息)
				amtVO.setBcOverThanNoLossInterest(amtVO.getBcOverThanNoLossInterest().add(
						t_ad.getNotyetImposeInterest()).add(t_ad.getNotyetInterest()));
			}
			// 逾期90天以上
			else if (LoanConstants.REPAYING_FLAG_OVERTHEN90.equals(t_ad.getRepayingFlag())) {
				// 账务出账--已减值转未减值(逾期90天以上利息+罚息)
				amtVO.setBcOverThan90NoLossInterest(amtVO.getBcOverThan90NoLossInterest().add(
						t_ad.getNotyetImposeInterest()).add(t_ad.getNotyetInterest()).add(
						t_ad.getCarryoverImposeInterest()));
			}
		}
		if (LoanConstants.REPAYING_FLAG_OVERDUE.equals(t_ad.getRepayingFlag())) {// 逾期90天内
			// 账务出账--已减值转未减值(逾期90天内贷款余额)
			amtVO.setBcOverThanNoLossAmt(rp.getRepayedAmount()
					.subtract(resultVO.getRepayedPricipal()));
		}else if (LoanConstants.REPAYING_FLAG_OVERTHEN90.equals(t_ad.getRepayingFlag())) {// 逾期90天以上
			// 账务出账--已减值转未减值(逾期90天以上贷款余额)
			amtVO.setBcOverThan90NoLossAmt(rp.getRepayedAmount().subtract(
					resultVO.getRepayedPricipal()));
		}
		
		return curYetOffCount;
	}
	/**
	 * 更新业务编号（repayingPlan,repayingPlanDetail）
	 * @param oldTransactionNo
	 * @param newTransactionNo
	 * @param backUp
	 * @param lastDate
	 */
	private void updateTransactionNoOfRpRpd(String oldTransactionNo, String newTransactionNo, boolean backUp, Date lastDate){
		List<RepayingPlan> rpList =dynamicQuery.query(RepayingPlan.class,"from RepayingPlan where transactionNo = ?1",oldTransactionNo);
		if(CollectionUtils.isEmpty(rpList)){
			throw new LoanBizException("["+oldTransactionNo+"]该业务编号的还款计划不存在");
		}
		Date curSysDate=CommonHelper.getNow();
		final String TANS_STATUS=loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1);//未入账
		RepayingPlan rp=rpList.get(0);
		rp.setTransactionNo(newTransactionNo); // 单据编号
		rp.setTransactionStatus(TANS_STATUS);
		rp.setSystemDate(curSysDate);
		//rp.setSerialNum(rp.getSerialNum()); // 序号
		repayingPlanDao.save(rp);
		// 查询计划明细表
		List<RepayingPlanDetail> rpdYetRepayedList=dynamicQuery.query(RepayingPlanDetail.class, "from RepayingPlanDetail where transactionNo = ?1 order by currentPeriod asc ", oldTransactionNo);
		// 查询还款明细表
		List<RepayingDetail> rdList=dynamicQuery.query(RepayingDetail.class, "from RepayingDetail where transactionNo = ?1 order by repayingNum asc  ", oldTransactionNo);
		// 查询欠款明细表
		List<ArrearsDetail> adList=dynamicQuery.query(ArrearsDetail.class, "from ArrearsDetail where transactionNo = ?1 order by period asc ", oldTransactionNo);
		if(CollectionUtils.isNotEmpty(rpdYetRepayedList)&&CollectionUtils.isNotEmpty(rdList)&&CollectionUtils.isNotEmpty(rdList)
				&&rpdYetRepayedList.size()==rdList.size()&&rdList.size()==adList.size()){
			for (RepayingPlanDetail rpd : rpdYetRepayedList) {
				rpd.setTransactionNo(newTransactionNo); // 新单据编号
				rpd.setSystemDate(curSysDate);
				rpd.setTransactionStatus(TANS_STATUS); // 单据状态
				if (lastDate != null) {
					rpd.setLastDate(lastDate); // 计息日期
				}
				repayingPlanDetailDao.save(rpd);
			}
			for (RepayingDetail rd : rdList) {
				rd.setTransactionNo(newTransactionNo);// 新单据编号
				rd.setSystemDate(curSysDate);
				rd.setTransactionStatus(TANS_STATUS);// 单据状态
				repayingDetailDao.save(rd);
			}
			for (ArrearsDetail ad : adList) {
				ad.setTransactionNo(newTransactionNo);// 新单据编号
				ad.setSystemDate(curSysDate);
				ad.setTransactionStatus(TANS_STATUS);// 单据状态
				arrearsDetailDao.save(ad);
			}
			if (backUp) {
				// 备份
				loanService.approvalService(newTransactionNo, rp, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2),LoanEvent.INTEREST_RECORD.toStr());
			} else {
				// 财务开关断开								
				if (!loanCommonServeice.isOpenOfOrgInterface(rp.getOperatorMechanism(),	//操作机构
						"openAccounting")) {
					// 备份
					loanService.approvalService(newTransactionNo, rp, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2), LoanEvent.REPAYED.toStr());
				}

			}
		}else{
			throw new LoanBizException("查无资料");
		}
	}
	@Override
	public LoanResult addFeeRegisterInfo(Long contractId,
			BigDecimal expenseAmt, String fundsSource, String repayDate) {
		LoanResult loanResult=new LoanResult();
		//1.构建账务
		//TODO 费用登记账务处理
		String orgId=null;
		if(loanAccountingService.isInterfaceOpenOfAccounting(orgId)){
			//等待财务入账
			loanResult.setStatus(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1));
		}else{
			//直接执行入账
			loanResult.setStatus(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		}
		return loanResult;
	}
	@Override
	public LoanResult updateAllInfoOfRepayLoan(ParamsOfInMoney inMoney, RepayLoan repayLoan) {
		
		Map<String, Object> paramsMap = Maps.newHashMap(); 
		LoanResult lf = new LoanResult();
		
		Contract contract=contractDao.findOne(inMoney.getContractId());//合同
		//OvdueDays累计逾期天数
		if(contract.getOvdueDays()==null){
			contract.setOvdueDays(0L);//初始化合同逾期天数  
		}
		//原始合同Id（initContractId），展期后合同id改变，但原始合同id不变
		RepayingPlan rp=loanRpInfoService.findRepayingPlan(contract.getInitContractId());//还款计划
		RepayingPlanDetail curRpd = loanRpInfoService.findRepayingPlanDetailById(inMoney.getCurRpdId());
		
		// 状态 1 已还 状态2 逾期 状态0
		String bstatus = curRpd.getStatus(); //当前业务数据状态
		final String NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S0");//未还
		final String YES_YET=loanCommonServeice.getCodeVal("PlanStatus", "S1");//已还
		final String OVERDUE_NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S2");//逾期未还
		Date repayedDate = inMoney.getRepayDate();//还款时间
		Date currentEndDate = curRpd.getCurrentEndDate(); // 当期应还日期
		Integer currentPriod = curRpd.getCurrentPeriod(); // 当期期次
		
		//是否正常转逾期
		boolean isNormal2Overdue = inMoney.isNormal2Overdue();
		if (!isNormal2Overdue && DateTools.getDateDiff(curRpd.getCurrentEndDate(), repayedDate) > 0
				//FIXME 小贷源码 NOT_YET.equals(curRpd.getCurrentEndDate())
				&& NOT_YET.equals(curRpd.getStatus())) {
			isNormal2Overdue = true;
		}
		//是否正常还款
		boolean isNormalRepay = false; 
		if (NOT_YET.equals(curRpd.getStatus()) && !isNormal2Overdue) {
			isNormalRepay = true;
		}
		
		//组装参数map
		paramsMap.put("curBizStatus", bstatus);
		paramsMap.put("repayedDate", repayedDate);
		paramsMap.put("currentEndDate", currentEndDate);
		paramsMap.put("currentPriod", currentPriod);
		paramsMap.put("PlanStatus_NOT_YET", NOT_YET);
	    paramsMap.put("PlanStatus_YES_YET", YES_YET);
	    paramsMap.put("PlanStatus_OVERDUE_NOT_YET", OVERDUE_NOT_YET);
	    paramsMap.put("isNormal2Overdue", isNormal2Overdue);
	    paramsMap.put("isNormalRepay", isNormalRepay);
		paramsMap.put("inMoney", inMoney);
		
		// 查询当期之前未还还款计划(包含当期)
		List yetRepayedParams = Lists.newArrayList();
		yetRepayedParams.add(rp.getRepayingPlanId());
		yetRepayedParams.add(currentPriod);
		yetRepayedParams.add(repayedDate);
		yetRepayedParams.add(YES_YET);
		List<RepayingPlanDetail> rpdYetRepayedList = loanRpInfoService.findYetRepayedList(yetRepayedParams);
		Set<Long> rpdIds = Sets.newHashSet();
		for (RepayingPlanDetail _rpd : rpdYetRepayedList) {
			rpdIds.add(_rpd.getRepayingPlanDetailId());
		}
		//查询还款明细
		List<RepayingDetail> rdList=loanRpInfoService.findRdList(rpdIds);//还款记录
		//查询欠款明细
		List<ArrearsDetail> adList=loanRpInfoService.findAdList(rpdIds);//欠款记录
		//查询逾期明细
		List<OverdueState> osList=loanRpInfoService.findOsList(rpdIds);//逾期处理
		//查询该还款计划所有明细
		List<RepayingPlanDetail> rpdAllList=loanRpInfoService.findAllRpdListByRpId(rp.getRepayingPlanId());//所有还款计划明细

		////////////////小贷已注释////////////////
		/*if(CollectionUtils.isNotEmpty(rpdInfoVo.getOverduePpds())){
			//逾期未还
			for (RepayingPlanDetail rpd : rpdInfoVo.getOverduePpds()) {
				rpdIds.add(rpd.getRepayingPlanDetailId());
			}
		}*/	
		/*if(curRpd!=null){
			//未还当期
			rpdIds.add(curRpd.getRepayingPlanDetailId());
		}*/
		
		
		// 正常转逾期-保存逾期情况
		loanOperationsService.recordOverdueState(paramsMap, rp, rpdYetRepayedList, osList);
		
		//欠款明细处理
		List arrearsResults = loanOperationsService.recordArrearsDetail(paramsMap, rpdYetRepayedList, rdList, adList, contract);
		BigDecimal totalOverdueOutAmt = CommonHelper.toBigDecimal(arrearsResults.get(0));
		/*FIXME 
		 * 下面三个属性需要作为recordArrearsDetail的返回值使用
		 */
		RepayingPlanDetail rpd = (RepayingPlanDetail) arrearsResults.get(1);
		RepayingDetail rd = (RepayingDetail) arrearsResults.get(2);
		ArrearsDetail ad = (ArrearsDetail) arrearsResults.get(3);
		
		rp.setTransactionStatus(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1));//未入账
		if(contract.getFpoolFlag()!= null){//TODO 资产转出
			/*this.commonDBService.saveOrUpdateAll(repayingPlanDetailList);
			this.commonDBService.saveOrUpdateAll(repayingDetailList);
			this.commonDBService.saveOrUpdateAll(arrearsDetailList);
			this.commonDBService.getHibernateTemplate().flush();
			return this.updateAttornRepayingService(repayingPlanDetail, repayedDate, repayingPlan, contract,
					repayingAmount, sourceType, flag);*/
		}
		
		
		//损失计提 初始化准备金
		this.initProvisionAmtByLp(rp);
		
		
		//账务
		DoVchAmtBo vchAmt=new DoVchAmtBo();
		InterestResultVO resultVO = new InterestResultVO();
		BigDecimal imposeInterest = null; // 罚息
		
		vchAmt.setContractId(rp.getContractId()); // 合同ID
		vchAmt.setPartyId(rp.getPartyId()); // 参加人ID
		vchAmt.setLoanId(rp.getPayLoanId()); // 贷款人ID
		vchAmt.setProjectId(rp.getProjectId()); // 业务ID
		vchAmt.setVchdate(getVchDate(inMoney.getRepayDate()));
		if (StringUtils.isNotBlank(repayLoan.getFundsSource()) && !"null".equals(repayLoan.getFundsSource())) {
			vchAmt.setBcCostType(repayLoan.getFundsSource()); // 资金来源
		}
		
		
		BigDecimal actualAmt=inMoney.getActualAmt();
		int numOfYesYet=0;//本次已还期数                              				
		final Date overdue90Date =DateTools.addDay(adList.get(0).getPlanRepayintDate(), 90);//逾期90天时间 = 计划还款日+90
		if(rpdYetRepayedList.size()>1||inMoney.isNormal2Overdue()||(rpdYetRepayedList.size()==1&&OVERDUE_NOT_YET.equals(curRpd.getStatus()))){
			//逾期（包括正常转逾期）
			numOfYesYet = this.repayOfOverdue(contract, rp, actualAmt,totalOverdueOutAmt, inMoney, overdue90Date,numOfYesYet, vchAmt, resultVO, rdList, rpdYetRepayedList, adList);
		}else if(rpdYetRepayedList.size()==1&&NOT_YET.equals(curRpd.getStatus())&&!inMoney.isNormal2Overdue()){
			//正常还款
			numOfYesYet = this.repayOfNormal(vchAmt, resultVO, rp, rd, rpd, ad, actualAmt, inMoney.getRepayDate(), YES_YET, numOfYesYet);
		}else{
			throw new LoanBizException("还款计划有误");
		}
		// 多还部分
		if (actualAmt.compareTo(rpd.getCurrentPrincipalInterest()) > 0) {
			vchAmt.setBcBankManyPayedAmt(vchAmt.getBcBankManyPayedAmt().add(actualAmt.subtract(rpd.getCurrentPrincipalInterest())));//预收
		}
		// 更新计划表
		rp.setRepayedAmount(rp.getRepayedAmount().subtract(resultVO.getRepayedPricipal()));// 剩余本金
		rp.setRepayedPeriod(rp.getRepayedPeriod() + numOfYesYet); // 已还期次
		rp.setSurplusPeriod(rp.getSurplusPeriod() - numOfYesYet); // 剩余期次
		if (contract.getLastOvdueDate() != null) { // 逾期
			resultVO.setStatus(OVERDUE_NOT_YET);
			lf.setStatus(OVERDUE_NOT_YET);
			contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S3"));//已逾期
			rp.setContContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode","S3"));//已逾期
		} else if (rp.getSurplusPeriod() <= 0) { // 结清
			resultVO.setStatus(YES_YET);
			lf.setStatus(YES_YET);
			contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S5"));//已结清
			rp.setContContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S5"));//已结清
		} else {
			contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S2"));//已放款
			rp.setContContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S2"));//已放款
			resultVO.setStatus(NOT_YET);
			lf.setStatus(NOT_YET);
		}
		rp.setContLastOvdueDate(contract.getLastOvdueDate());// 还款计划--合同最近逾期日期
		rp.setContFirstOvdueDate(contract.getFirstOvdueDate());// 还款计划--合同首次逾期日期
		rp.setContFiveclassification(contract.getFiveclassification());// 还款计划--合同五级分类
		rp.setContOvdueAmt(contract.getOvdueAmt());// 还款计划--合同逾期金额
		rp.setContOvdueDays(contract.getOvdueDays().intValue());// 还款计划--合同逾期天数
		rp.setContOvdueInterest(contract.getOvdueInterest());// 还款计划--合同逾期利息
		if(contract.getOvdueTime()==null){
			contract.setOvdueTime(0L);
		}
		rp.setContOvdueTime(contract.getOvdueTime().intValue());// 还款计划--合同逾期次数
		rp.setContContractStatusCd(contract.getContractStatusCd()); // 还款计划--合同状态
		//财务还款接口
		String transactionNO =loanAccountingService.doVchByPaid(vchAmt, contract.getApplyOrgId(), Long.parseLong(contract.getApplyUserNum()));//单据编号
		//更新欠款明细
		ArrearsDetail last_ad = adList.get(adList.size() - 1);
		RepayingPlanDetail lastOfRpdList = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
		List<ArrearsDetail> update_adList =loanRpInfoService.findAdListOfCurPeriodAfter(rp.getRepayingPlanId(), lastOfRpdList.getCurrentPeriod());
		// 改变当期以后状态
		if (actualAmt.compareTo(BigDecimal.ZERO) > 0 || contract.getLastOvdueDate() == null) { // 逾期已还or多还
			//还款标识(0:未逾期1:逾期90天内2:逾期90天以上)
			if ("0".equals(last_ad.getRepayingFlag())) { // 当期未还
				//FIXME 未逾期设置逾期90天以上？
				last_ad.setRepayingCalFlag("0");//计算标识(1:逾期90天内,紧接后面 0逾期90天以上)
				last_ad.setRepayingFirstFlag("0");//首次逾期标识(1:首次逾期)
				last_ad.setRepayingFlag("0");//还款标识(0:未逾期1:逾期90天内2:逾期90天以上)
			}
			if (update_adList != null && update_adList.size() > 0) {
					for (ArrearsDetail adOfTemp : update_adList) {
						adOfTemp.setRepayingCalFlag("0");
						adOfTemp.setRepayingFirstFlag("0");
						adOfTemp.setRepayingFlag("0");
					}
					arrearsDetailDao.save(adList);
				}
		} else {
			if (update_adList != null && update_adList.size() > 0) {
				if (update_adList != null && update_adList.size() > 0) {
					for (ArrearsDetail arrearsDetailVO : update_adList) {
						//计算标识(1:逾期90天内,紧接后面 0逾期90天以上)
						arrearsDetailVO.setRepayingCalFlag(last_ad.getRepayingCalFlag());
					}
					arrearsDetailDao.save(adList);
				}
			}
		}
		


		repayLoan.setActualOverdueInt(resultVO.getRepayedImposeInterest());// 实还逾期利息
		repayLoan.setActualCurrInt(resultVO.getRepayedCurrentInterest());// 实还当期利息
		repayLoan.setActualOverduePi(resultVO.getRepayedPricipalInterest());// 实还拖欠本息
		repayLoan.setActualCurrPrincipal(resultVO.getRepayedCurrentPricipal());// 实还当期本金
		repayLoan.setActualPrincipalAmt(resultVO.getRepayedPricipal());// 实还本金
		repayLoan.setLoanAmount(resultVO.getLoanAmount());// 实还拖欠本金
		repayLoan.setTableinterest(resultVO.getRepayedTableInterest()); // 表内利息
		repayLoan.setContractBalance(contract.getContractBalance());// 合同余额
		
		repayLoanDao.save(repayLoan);
		contractDao.save(contract);
		repayingPlanDao.save(rp);
		repayingPlanDetailDao.save(rpdYetRepayedList);
		repayingDetailDao.save(rdList);
		arrearsDetailDao.save(adList);
		//更新还款明细
		this.updateRepayingDetail(rp);
		//更新还款计划明细单据编号
		this.updateTransactionNoOfRpRpd(rp.getTransactionNo(),transactionNO,false, null);
		
		return lf;
 	}
	/** 
	 * 初始化准备金（损失计提）
	 * @param rp
	 */
	private void initProvisionAmtByLp(RepayingPlan rp){
		LossProvision lp =null;
		List<LossProvision> lpList=loanCommonServeice.findLossProvisionList(rp.getContractId(), LoanConstants.OBJECTDIMENSIONTYPE_CONTRACT, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		if(CollectionUtils.isNotEmpty(lpList)){
			lp=lpList.get(0);
		}
		if(lp!=null){
			rp.setPrivisionAmt(lp.getPrivisionAmt());//准备金
		}
	}
	/**
	 * 更新还款明细
	 * @param rp 还款计划
	 */
	private void updateRepayingDetail(RepayingPlan rp){
		//子查询 order by currentPeriod asc   查所有还款明细                                                            //还款编号
		List<RepayingDetail> rdList=dynamicQuery.query(RepayingDetail.class, "from RepayingDetail where repayingPlanDetailId in (select rpd.repayingPlanDetailId from RepayingPlanDetail rpd where rpd.repayingPlanId = ?1 ) order by repayingNum asc", rp.getRepayingPlanId());
		if(CollectionUtils.isEmpty(rdList)){
			throw new LoanBizException("还款明细为空");
		}
		BigDecimal interestCnt = BigDecimal.ZERO; // 利息累计
		BigDecimal ppalCnt = BigDecimal.ZERO; // 本金累计
		BigDecimal loanBalance = rp.getLoanAmount(); // 贷款金额
		Date curSysDate=CommonHelper.getNow();
		for (RepayingDetail rd : rdList) {
			loanBalance = loanBalance.subtract(rd.getRepayedPrincipal());
			rd.setRepayedTotalAmount(rd.getRepayedInterest().add(rd.getRepayedPrincipal()).add(
					rd.getRepayedImposeInterest())); // 已还总金额 = 每笔实还利息之和 + 每笔实还本金之和 + 每笔已还罚息之和
			rd.setSystemDate(curSysDate);
			interestCnt = interestCnt.add(rd.getRepayedInterest());
			ppalCnt = ppalCnt.add(rd.getRepayedPrincipal());
			rd.setEndCurrentInterestcnt(interestCnt);// 截止当前累计还息
			rd.setRepayedTotalPricipal(ppalCnt);// 合计已还款本金
			rd.setEndCurrentPricipalcnt(loanBalance);// 截止当前累计本金余额
			repayingDetailDao.save(rd);
		}
	}
	/**
	 * 查询当期之前未还还款计划(包含当期)
	 * @return
	 */
	private List<RepayingPlanDetail> findRpdList(Long rpId,int currentPriod,Date repayedDate,String status){
		StringBuffer strBuffer=new StringBuffer("from RepayingPlanDetail where repayingPlanId = ?1");
		strBuffer.append(" and ( currentPeriod <= ?2 or currentEndDate < to_date(?3,'yyyy-MM-dd'))");
		strBuffer.append(" and status !=?4 order by currentPeriod asc");
		return dynamicQuery.query(RepayingPlanDetail.class,strBuffer.toString(), rpId,currentPriod,CommonHelper.date2Str(repayedDate, "yyyy-MM-dd"),status);
	}
	/**
	 * @param repayDate
	 * @return
	 */
	private Date getVchDate(Date repayDate){
		String dateStr=CommonHelper.date2Str(repayDate, CommonHelper.DF_DATE)+" "+CommonHelper.date2Str(CommonHelper.getNow(), CommonHelper.DF_TIME);
		return CommonHelper.str2Date(dateStr, CommonHelper.DF_DATE_TIME);
	}

}
