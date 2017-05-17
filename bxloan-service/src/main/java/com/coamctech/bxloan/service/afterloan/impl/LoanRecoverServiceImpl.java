package com.coamctech.bxloan.service.afterloan.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.taglibs.standard.tei.ForEachTEI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.SystemInterfaceOrgLink;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.BizExpenseRateDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.RepayLoanDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.afterloan.AfterLoanCalCulateService;
import com.coamctech.bxloan.service.afterloan.LoanOperationsService;
import com.coamctech.bxloan.service.afterloan.LoanRecoverService;
import com.coamctech.bxloan.service.assloan.AssOperationService;
import com.coamctech.bxloan.service.collateral.CollateralService;
import com.coamctech.bxloan.service.model.afterloan.ContractListParams;
import com.coamctech.bxloan.service.model.afterloan.LrFeeRegisterVo;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoParams;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoVo;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoVo.LrRepayItem;
import com.coamctech.bxloan.service.model.loanprocess.InterestResultVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanFunctionService;
import com.coamctech.bxloan.service.pettyloan.LoanRpInfoService;
import com.coamctech.bxloan.service.pettyloan.bo.LoanResult;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfInMoney;
import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
@Service
@Transactional
public class LoanRecoverServiceImpl implements LoanRecoverService {
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private BizExpenseRateDao bizExpenseRateDao;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private LoanOperationsService loanOperationsService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private RepayLoanDao repayLoanDao;
	@Autowired
	private LoanFunctionService loanFunctionService;
	@Autowired
	private LoanRpInfoService loanRpInfoService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private CollateralService collateralService;
	@Autowired
	private AssOperationService assOperationService;
	@Autowired
	private AfterLoanCalCulateService afterLoanCalCulateService;
	@Override
	public Page<Object[]> findContractPage(int pageSize, int pageNum,String userId,ContractListParams params) {
		
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select cont.contract_id,cont.contract_num,cont.customer_name,cont.contract_status_cd,");
		strBuffer.append("cont.contract_amt,cont.contract_balance,");
		strBuffer.append("cont.payloan_date,cont.last_repay_date,rp.repayed_period,rp.total_period,");//已还期数，总期数
		strBuffer.append("p.product_name, cont.project_id");
		strBuffer.append(" from contract cont left join repaying_plan rp on cont.project_id=rp.project_id");
		strBuffer.append(" left join product p on cont.product_type =p.product_cd");
		strBuffer.append(" where cont.contract_id in (select distinct pli.contract_id from pay_loan_info pli where pli.pay_status_cd in ('2','5')  and pli.many_pay_status = '0')");
		strBuffer.append(" and cont.apply_user_num=?1"); //已入账和冲销已入账
		
		List<Object> list=Lists.newArrayList();
		list.add(userId);
		if(params!=null&&StringUtils.isNoneBlank(params.getContractStatus())){
			strBuffer.append(" and cont.CONTRACT_STATUS_CD = ?2");
			list.add(params.getContractStatus());
		}else{
			strBuffer.append(" and cont.CONTRACT_STATUS_CD in (?2)");
			list.add(loanCommonServeice.getCodeValList("ContractStatusCode", "S2","S3"));
		}
		if(params!=null){
			int paramsCounter=3;
			if(StringUtils.isNoneBlank(params.getCustomerName())){
				strBuffer.append(" and cont.customer_name like ?").append(paramsCounter++);
				list.add("%"+params.getCustomerName()+"%");
			}
			if(StringUtils.isNoneBlank(params.getCustomerNum())){
				strBuffer.append(" and cont.customer_num like ?").append(paramsCounter++);
				list.add("%"+params.getCustomerNum()+"%");
			}
			if(StringUtils.isNoneBlank(params.getContractNum())){
				strBuffer.append(" and cont.customer_name like ?").append(paramsCounter++);
				list.add("%"+params.getContractNum()+"%");
			}
			if(params.getStartDate()!=null){
				strBuffer.append(" and cont.payLoan_date >= ?").append(paramsCounter++);
				list.add(params.getStartDate());
			}
			if(params.getEndDate()!=null){
				strBuffer.append(" and cont.payLoan_date <= ?").append(paramsCounter++);
				list.add(params.getEndDate());
			}
		}
		strBuffer.append(" order by cont.contract_num desc");
		return dynamicQuery.nativeQuery(Object[].class,new PageRequest(pageNum, pageSize), strBuffer.toString(), list.toArray());
	}

	@Override
	public Contract findContract(Long id) {
		return contractDao.findOne(id);
	}
	@Override
	public List<RepayLoan> findRepayLoanList(Long contractId,
			String contractNum, String repaymentStatusCd, String valid) {
		StringBuffer strBuffer = new StringBuffer("from RepayLoan rl where 1=1");
		List<Object> params=Lists.newArrayList();
		int paramsCounter=0;
		if(contractId!=null){
			strBuffer.append(" and rl.contractId=?").append(++paramsCounter);
			params.add(contractId);
		}
		if(StringUtils.isNoneBlank(contractNum)){
			strBuffer.append(" and rl.contractNum=?").append(++paramsCounter);
			params.add(contractNum);
		}
		if(StringUtils.isNoneBlank(repaymentStatusCd)){
			strBuffer.append(" and rl.repaymentStatusCd=?").append(++paramsCounter);
			params.add(repaymentStatusCd);
		}
		if(StringUtils.isNoneBlank(valid)){
			strBuffer.append(" and rl.valid=?").append(++paramsCounter);
			params.add(valid);
		}
		return dynamicQuery.query(RepayLoan.class, strBuffer.toString(), params.toArray());
	}
	@Override
	@Transactional(readOnly=true)
	public LrFeeRegisterVo findFeeRegisterInfo(Long contractId,Long curOrgId) {
		Contract contract=contractDao.findOne(contractId);
		LrFeeRegisterVo lrVo=new LrFeeRegisterVo();
		lrVo.setContractId(contractId);
		lrVo.setCustomerName(contract.getCustomerName());
		lrVo.setContractTerm(contract.getContractTerm());//合同期限
		lrVo.setContractTermUnit(dataDict.getCodeName("TermUnitCd", contract.getContractTermUnit()));
		lrVo.setLoanAmt(contract.getContractAmt());//贷款金额
		List<BizExpenseRate> berList = bizExpenseRateDao.findByProjectId(contract.getProjectId());
		if(!CollectionUtils.isEmpty(berList)){
			BizExpenseRate ber = berList.get(0);
			lrVo.setBizExpenseRateId(ber.getBizExpenseRateId());
			lrVo.setFundsSource(ber.getFundsSource());
			if(ber.getExpenseAmt()!=null){
				lrVo.setExpenseAmt(ber.getExpenseAmt().toPlainString());
			}
			lrVo.setRemark(ber.getRemark());
			lrVo.setFeeType(ber.getExpenseName());
			lrVo.setRepayDateStr(CommonHelper.date2Str(ber.getSysCreateDate(), CommonHelper.DF_DATE));
			lrVo.setRepayDate(CommonHelper.date2Str(ber.getSysCreateDate(), CommonHelper.DF_DATE));
		}else{
			lrVo.setRepayDateStr(CommonHelper.date2Str(new Date(), CommonHelper.DF_DATE));
			lrVo.setRepayDate(CommonHelper.date2Str(new Date(), CommonHelper.DF_DATE));
		}
		//计算一个编号
		lrVo.setRepayLoanNum(this.getRepayLoanNum(contract.getCustomerNum(), curOrgId));
		lrVo.setLoanProductCd(contract.getProductType());
		this.addContractOtherInfo(lrVo, contractId);
		return lrVo;
	}
	@Override
	public void saveFeeRegisterInfo(LrFeeRegisterVo lrVo) {
		Contract contr = contractDao.findOne(lrVo.getContractId());
		ProjectApplication pa= projectApplicationDao.findOne(contr.getProjectId());
		BizExpenseRate bizExpenseRate = null;
		if(lrVo.getBizExpenseRateId()!=null){
			 bizExpenseRate= bizExpenseRateDao.findOne(lrVo.getBizExpenseRateId());
		}else{
			bizExpenseRate = new BizExpenseRate();
		}
		bizExpenseRate.setProjectId(pa.getProjectId());//项目id
		bizExpenseRate.setProjectNo(pa.getProjectNo());//项目编号
		bizExpenseRate.setProductCd(pa.getProductType());//授信品种
		//写死币种---人民币
		bizExpenseRate.setCurrencyCd("156");//币种
		bizExpenseRate.setExpenseName(lrVo.getFeeType());//费用类型
		bizExpenseRate.setExpenseAmt(new BigDecimal(lrVo.getExpenseAmt()));//费用金额
		///////////////////小贷已注释////////////////////
		//bizExpenseRate.setExpenseCollectionType(dataDict.getCodeVal("FeeUsedType", "S2"));//收费方式
		bizExpenseRate.setFundsSource(lrVo.getFundsSource());//资金来源
		bizExpenseRate.setSysCreateDate(DateTools.stringToDate(lrVo.getRepayDate(), "yyyy-MM-dd"));
		bizExpenseRate.setSysUpdateDate(new Date());
		bizExpenseRate.setRemark(lrVo.getRemark());//备注
		
		InterestResultVO interestResultVO = new InterestResultVO();
		////////////////////////小贷已注释/////////////////////////
		interestResultVO.setContractId(contr.getContractId().intValue());
		
		interestResultVO.setCostAmt(new BigDecimal(lrVo.getExpenseAmt()));//费用金额
		interestResultVO.setSourceFundType(lrVo.getFundsSource());//资金来源
		interestResultVO.setRepayedDate(DateTools.stringToDate(lrVo.getRepayDateStr(), "yyyy-MM-dd"));//实还时间（账务处理时间）
		//费用登记
		interestResultVO = afterLoanCalCulateService.registerCostService(interestResultVO);
		bizExpenseRate.setTransactionNo(interestResultVO.getTransactionNo());//单据编号
		bizExpenseRate.setTransactionStatus(interestResultVO.getStatus());//状态
		bizExpenseRateDao.save(bizExpenseRate);
		
	}
	@Override
	public MsgResult findRepayInfoBeforeCheck(LrRepayInfoParams lrParams) {
		final Long contractId=lrParams.getContractId();
		final boolean overdueFlag=lrParams.isOverdueFlag();//逾期
		if(contractId==null||contractId<=0){
			return MsgResult.getMsgResult("001", "请选择一笔合同！");
		}
		Contract contract=contractDao.findOne(contractId);
		if(contract==null){
			return MsgResult.getMsgResult("001", "请选择一笔合同！");
		}
		lrParams.setContractNum(contract.getContractNum());
		//转出
		if(StringUtils.equals("2", contract.getFpoolFlag())){
			return MsgResult.getMsgResult("007", "此合同转出中，不能进行还款,请确认！");
		}
		//已还清
		if(StringUtils.equals(loanCommonServeice.getCodeVal("ContractStatusCode", "S5"), contract.getContractStatusCd())){
			return MsgResult.getMsgResult("002", "已结清合同不能发起还款,请确认!");
		}
		if(overdueFlag){//逾期
			//正常转逾期
			if(!lrParams.isNormal2Overdue()){
				if(!StringUtils.equals(loanCommonServeice.getCodeVal("ContractStatusCode", "S3"), contract.getContractStatusCd())){
					return MsgResult.getMsgResult("003", "该合同未逾期，不能进行逾期还款,请确认！");
				}
				List<RepayLoan> rlList=this.findRepayLoanList(contract.getContractId(), null, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2), "1");
				if(CollectionUtils.isNotEmpty(rlList)){
					if (DateTools.getDateDiff(new Date(), rlList.get(0).getRepayDate()) > 0) {
						return MsgResult.getMsgResult("004", "当前日期小于最近还款日期,请确认!");
					}
				}
			}
		}else{//正常
			if(StringUtils.equals(loanCommonServeice.getCodeVal("ContractStatusCode", "S3"), contract.getContractStatusCd())){
				return MsgResult.getMsgResult("005", "该合同已逾期，请先进行逾期还款,请确认！");
			}
			Date repayingPlanDate =loanCommonServeice.getRepayDateOfLastRepayingPlan(contractId);
			if(repayingPlanDate==null){
				return MsgResult.getMsgResult("006", "数据异常!");
			}
		}
		//放款信息校验
		Long payLoanId=getPayLoanInfoId(contract.getInitContractId());//合同展期原始合同Id不变
		if(payLoanId==null){
			return MsgResult.getMsgResult("008", "放款id为空,请确认!");
		}
		//合同未入账或是冲销未入账
		if(loanCommonServeice.isNoneEnterOfContact(contract.getContractNum())){
			return MsgResult.getMsgResult("009", "此合同未入账或是冲销未入账,不能发起还款登记,请确认!");
		}
		if(loanCommonServeice.isExtensionOfContact(contract.getContractNum())){
			return MsgResult.getMsgResult("010", "此合同已发展期,不能发起还款登记,请确认!");
		}
		if(loanCommonServeice.isVerifCancelOfContact(contract.getContractNum())){
			return MsgResult.getMsgResult("010", "此合同已发核销,不能发起还款登记,请确认!");
		}
		//提前还款申请
		if(loanCommonServeice.isAdvanceRepayOfContract(contract.getContractNum())){
			return MsgResult.getMsgResult("011", "该合同下存在提前还款申请,不能发起还款登记！");
		}
		//异常还款计划（展期还款计划唯一，InitContractId不变）
		RepayingPlan rp=this.findRepayingPlan(contract.getInitContractId(),payLoanId);
		if(rp==null){
			return MsgResult.getMsgResult("011", "还款计划不存在，请确认！");
		}
		lrParams.setRepayingPlanId(rp.getRepayingPlanId());
		if (loanCommonServeice.isLtDateRepayAndPay(rp.getRepayingPlanId())) {
			return MsgResult.getMsgResult("012", "还款计划中出现还款日期小于贷款放款日期，请调整还款计划!");
		}
		if(loanCommonServeice.isNotEqTotalAmtOfRepayAndPay(rp.getRepayingPlanId(),rp.getLoanAmount())){
			return MsgResult.getMsgResult("013", "累计还款本金不等于累计放款金额，请调整还款计划!");
		}
		return MsgResult.getMsgResult(ResultEnums.SUCCESS);
	}
	private String getRepayLoanNum(String customerNum,Long curOrgId) {
		List<Party> partyList=dynamicQuery.query(Party.class, "from Party p where p.customerNum=?1", customerNum);
		Party party=null;
		if(CollectionUtils.isNotEmpty(partyList)){
			party=partyList.get(0);
		}
		if(party==null){
			throw new NullPointerException("客户信息不存在");
		}
		return commonBizNumberBS.generateAppNumber("K", party.getPartyTypeCd(),String.valueOf(curOrgId));
	}
	@Override
	@Transactional(readOnly=true)
	public LrRepayInfoVo findRepayInfo(LrRepayInfoParams lrParams) {
		Contract contract=contractDao.findOne(lrParams.getContractId());
		//计算还款编号
		String repayLoanNum=this.getRepayLoanNum(contract.getCustomerNum(),lrParams.getOrgId());//还款编号
		LrRepayInfoVo lrVo=new LrRepayInfoVo();//返回结果
		lrVo.setContractId(lrParams.getContractId());
		lrVo.setContractNum(contract.getContractNum());
		lrVo.setRepayLoanNum(repayLoanNum);//还款编号
		lrVo.setCustomerName(contract.getCustomerName());
		lrVo.setContractTerm(contract.getContractTerm());//合同期限
		lrVo.setContractTermUnit(dataDict.getCodeName("TermUnitCd", contract.getContractTermUnit()));
		lrVo.setLoanAmt(contract.getContractAmt());//贷款金额
		lrVo.setContractBalance(contract.getContractBalance());
		lrVo.setRepayPrincipalMonthes(contract.getRepayPrincipalMonthes());
		lrVo.setPayloanDate(contract.getPayloanDate());
		lrVo.setContractInterestRate(contract.getContractRate());//合同利率
		//补全合同信息
		lrVo.setRepayModeCd(contract.getRepayModeCd());
		lrVo.setRepayModeName(loanCommonServeice.getCodeName("RepaymentMode", contract.getRepayModeCd()));
		lrVo.setLoanProductCd(contract.getProductType());
		
		lrVo.setRepayDate(lrParams.getRepayDate());//还款日期
		lrVo.setRepayingPlanId(lrParams.getRepayingPlanId());
		//增加贷款产品名称、客户经理名称、还款方式等信息
		this.addContractOtherInfo(lrVo, contract.getContractId());
		
		List<RepayingPlanDetail> rpdAllList=loanRpInfoService.findAllRpdListByRpId(lrParams.getRepayingPlanId());
		if(!lrParams.isOverdueFlag()){//正常还款
			//还款状态，S0未还
			final String NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S0");
			boolean isNotNomalRepay=true;//不能正常还款
			for (RepayingPlanDetail rpd : rpdAllList) {
				if(StringUtils.equals(NOT_YET, rpd.getStatus())){
					//当期还款计划明细
					buildRepayInfoVo(lrVo, rpd,false);
					isNotNomalRepay=false;
					lrVo.setRepayingPlanDetailId(rpd.getRepayingPlanDetailId());
					break;
				}
			}
			if(isNotNomalRepay){
				throw new LoanBizException("合同已到期,不能正常还款,请确认!");
			}
		}else{//逾期
			//逾期
			final String OVERDUE_NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S2");//逾期未还
			final String HAS_YET=loanCommonServeice.getCodeVal("PlanStatus", "S1");//已还
			int index=-1;//期数
			for (RepayingPlanDetail rpd : rpdAllList) {
				if (OVERDUE_NOT_YET.equals(rpd.getStatus())
						|| HAS_YET.equals(rpd.getStatus())) {
					index++;
				}
			}
			if(lrParams.isNormal2Overdue()){
				index++;
			}else{
				RepayingPlanDetail rpd = rpdAllList.get(index);
				lrVo.setRepayingPlanDetailId(rpd.getRepayingPlanDetailId());
				if (CommonHelper.formatDate(new Date(), "yyyy-MM-dd").compareTo(rpd.getCurrentEndDate()) <= 0) {
					throw new LoanBizException("还款日期应大于等于上期还款起始日期,请确认！");
				}
			}
			//rpdAllList全部未还
			if (index == -1) {
				throw new LoanBizException("当前所有期次都为正常(不包括已还),请确认!");
			}
			buildRepayInfoVo(lrVo, rpdAllList.get(index),true);
		}
		return lrVo;
	}
	@Override
	@Transactional(readOnly=true)
	public MsgResult saveRepayLoanBeforeCheck(LrRepayInfoParams lrParams) {
		return findRepayInfoBeforeCheck(lrParams);
	}
	@Override
	@Transactional
	public void saveRepayLoan(LrRepayInfoParams lrParams) {
		RepayLoan repayLoan=new RepayLoan();
		this.copyDataOfRepay(repayLoan, lrParams);
		repayLoanDao.save(repayLoan);
		
		
		
		ParamsOfInMoney inMoney=new ParamsOfInMoney();
		//copyDataToInMony(inMoney, repayLoan, lrParams);
		inMoney.setContractId(repayLoan.getContractId());
		inMoney.setRepayLoanId(repayLoan.getRepayLoanId());
		inMoney.setCurRpdId(lrParams.getRepayingPlanDetailId());
		inMoney.setActualAmt(repayLoan.getActualAmt());
		inMoney.setRepayDate(repayLoan.getRepayDate());
		inMoney.setOverdueFlag(lrParams.isOverdueFlag());//正常还款
		inMoney.setNormal2Overdue(lrParams.isNormal2Overdue());//是否正常转逾期
		//更新还款相关表
		LoanResult loanResult = loanFunctionService.inOfMoney(inMoney, repayLoan);
		BigDecimal repayedAmt = BigDecimal.ZERO;
		// 接口开关关闭时
		if (!loanCommonServeice.isOpenOfOrgInterface(String.valueOf(lrParams.getOrgId()), SystemInterfaceOrgLink.INTERFACEKEY_Accounting)) {
			Contract contract = this.copyDataOfRepayLoan(repayLoan, loanResult, true);
			// 开关关闭状态直接新增还款记录更新状态为已入账(S2)
			repayLoan.setRepaymentStatusCd(dataDict.getCodeVal("PayLoanStatus", "S2"));// 已入账
			repayLoan.setValid(dataDict.getCodeVal("CtrlIndicator", "S1"));// 表示有效
			if (contract.getFpoolProjectId() != null) {//协议编号
				repayedAmt = repayLoan.getActualAmt();
				if (repayLoan.getPenalty() != null) {
				   repayedAmt = repayedAmt.subtract(repayLoan.getPenalty());//实还金额-违约金
				}
				if (repayLoan.getTableinterest() != null) {
				   repayedAmt = repayedAmt.subtract(repayLoan.getTableinterest());
				}
				//根据ID更新机构收回本金
				this.assOperationService.updateAsstranProjectAppById(contract.getFpoolProjectId(), repayedAmt);
		  }
			repayLoanDao.save(repayLoan);
		}else{
		//接口打开时
		// 开关打开状态等待反馈信息：成功更新状态已入账(S2),失败更新状态已退单(S3)且发消息给客户经理重录
			Contract contract = this.copyDataOfRepayLoan(repayLoan, loanResult, false);
			repayLoan.setRepaymentStatusCd(dataDict.getCodeVal("PayLoanStatus", "S1"));// 未入账
			repayLoan.setValid(dataDict.getCodeVal("CtrlIndicator", "S1"));// 表示有效
			repayLoanDao.save(repayLoan);
		}
	}
	
	/**组装合同信息(不含已退单处理)*/
	private Contract copyDataOfRepayLoan(RepayLoan repayLoan, LoanResult loanResult, boolean switchFlag) {
		Contract contract = null;
		contract = this.contractDao.findByContractId(repayLoan.getContractId());
		//资料补充
		repayLoan.setCustomerName(contract.getCustomerName());
		repayLoan.setCustomerNum(repayLoan.getCustomerNum());
		repayLoan.setProjectId(contract.getProjectId());
		repayLoan.setContractNum(contract.getContractNum());
		
		if (contract == null) {
			throw new RuntimeException("查无合同资料,请联系管理员!");
		}
		//应还总额
		if (repayLoan.getPayableTotalAmt() == null) {
			repayLoan.setPayableTotalAmt(new BigDecimal("0.00"));
		}
		BigDecimal balance = MathUtil.BDsubtract(repayLoan.getPayableTotalAmt(), repayLoan.getActualAmt(), 2);
		// 更新合同余额:合同余额-实还本金(实还当期本金,实还拖欠本金)
		BigDecimal contractBalance = null;
		if (repayLoan.getActualPrincipalAmt() == null) {
			repayLoan.setActualPrincipalAmt(new BigDecimal("0.00"));
		}
		
		if(repayLoan.getContractBalance()!=null){
		contractBalance = MathUtil.BDsubtract(repayLoan.getContractBalance(), repayLoan.getActualPrincipalAmt(), 2);
		contract.setContractBalance(contractBalance);
		}
		
		if (contract.getCumulativeRepayAmt() == null) {
			contract.setCumulativeRepayAmt(new BigDecimal("0.00"));
		}
		//合同累计还款金额
		contract.setCumulativeRepayAmt(contract.getCumulativeRepayAmt().add(repayLoan.getActualPrincipalAmt()));
		//贷款截止日期
		repayLoan.setExpirationDate(contract.getLastRepayDate());// 最近还款日期
		contract.setLastRepayDate(repayLoan.getRepayDate());//最近还款日期 更新为 本次还款日期

		if (balance.compareTo(BigDecimal.ZERO) > 0) {// 不足额还款:更新合同状态为已逾期(S3),还款计划状态为已逾期(S3)
			// 在应还期限内的合同仍为已放款(期限内可以多次正常还款)
												//应还日期-还款日期
			int diffdays = DateTools.getDateDiff(repayLoan.getPayableDate(), repayLoan.getRepayDate());
			//FIXME 小贷原为if(diffdays <= 0)
			if (diffdays >= 0) {
				//PlanStatus S0未还，S1已还，S2逾期未还
				if (loanResult != null         //FIXME 小贷原为dataDict.getCodeVal("PlanStatus", "S0")
						&& dataDict.getCodeVal("PlanStatus", "S0").equals(loanResult.getStatus())) {// 表示前面所有还款计划都已还
					contract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S2"));// 已放款
				} else if (loanResult != null
						&& dataDict.getCodeVal("PlanStatus", "S2").equals(loanResult.getStatus())) {// 表示前面所有还款计划含未还清
					contract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S3"));// 已逾期
				}
			} else {
				contract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S3"));// 已逾期
			}
		} else {// 足额还款
			if (loanResult != null && dataDict.getCodeVal("PlanStatus", "S1").equals(loanResult.getStatus())) {// 全部还清
				if (contract.getContractAvailableAmt().compareTo(BigDecimal.ZERO) == 0
						&& contract.getContractBalance().compareTo(BigDecimal.ZERO) == 0) {
					contract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S5"));// 已结清
					//判断关联抵质押物是否释放的标志
					if (contract.getIfReleaseFlag() == null) {
						if (switchFlag) {
							// 已结清的项目需要把相关联的文档状态由“引用”改成“创建”
							Long partyId = null;
							try {
								partyId = projectApplicationDao.findPartyIdByProjectId(repayLoan.getProjectId());
							} catch (Exception e1) {
								throw new RuntimeException("数据异常，请确认！");
							}
							try {//校验是否可以修改客户的文档类型
								if (loanOperationsService.validateIsModifyDocumentType(partyId, repayLoan
										.getProjectId())) {
									Set docSet = ImmutableSet.of("01");
									documentService.updateDocumentIndexByPartyId(partyId, docSet, dataDict.getCodeVal("CreateType", "S1"));
								}
							} catch (Exception e) {
								e.getMessage();
								e.printStackTrace();
							}
							// 已结清的项目需要把相关联的文档状态由“引用”改成“创建”youjg end
							// 释放抵质押金额20130106
							collateralService.handlePledgeAmtWhenContractEnd(repayLoan.getProjectId());
							contract.setIfReleaseFlag("1");// 抵质押物已释放且金额已扣减的标志
						}
					}
					// bizProjectApplicationBS.updateProjectState(repayLoanBO.getProjectId(),
					// CodeTable.getValue(
					// "ProjectStatus", "S6"));// 已终止
				} else {
					contract.setContractBalance(new BigDecimal(0));
					contract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S5"));// 已结清
				}
			} else {
				contract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S2"));// 已放款
			}
		}
		contractDao.save(contract);
		return contract;
	}

	@Override
	public Page<Object[]> findRepayLoanList(int pageSize, int pageNum,
			Long contractId) {
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("select rl.repay_loan_num,rl.repay_date,rl.actual_amt,")
		.append("rl.funds_source,rl.repayment_status_cd from repay_loan rl");
		strBuffer.append(" where rl.contract_id=?1 ");
		strBuffer.append(" order by rl.repay_loan_id desc ");
		return dynamicQuery.nativeQuery(Object[].class, new PageRequest(pageNum, pageSize), strBuffer.toString(), contractId);
	}
	@Override
	public Page<Object[]> findRepayingPlanInfoList(int pageSize, int pageNum,
			Long contractId) {
		RepayingPlan rp=loanRpInfoService.findRepayingPlan(contractId);
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("select rpd.current_period,rpd.current_end_date,rpd.current_principal,")
		.append("rpd.current_interest,rpd.current_principal_interest,rpd.status");
		strBuffer.append(" from repaying_plan_detail rpd where rpd.repaying_plan_id=?1");
		strBuffer.append(" order by rpd.current_period");
		return dynamicQuery.nativeQuery(Object[].class, new PageRequest(pageNum, pageSize), strBuffer.toString(), rp.getRepayingPlanId());
	}
	/**
	 * 还款的数据复制
	 * @param repayLoan
	 * @param lrParams
	 */
	private void copyDataOfRepay(RepayLoan repayLoan,LrRepayInfoParams lrParams){
		Date now =CommonHelper.getNow();
		repayLoan.setContractId(lrParams.getContractId());
		//repayLoan.setRepayingPlan(lrParams.getRepayingPlanId());
		repayLoan.setRepaymentStatusCd(loanCommonServeice.getCodeVal("PayLoanStatus","S1"));//设置还款记录状态
		repayLoan.setRepayLoanNum(lrParams.getRepayLoanNum());
		repayLoan.setActualAmt(lrParams.getActualAmt());
		repayLoan.setRepayDate(lrParams.getRepayDate());
		repayLoan.setApplyUserNum(lrParams.getApplyUserNum().toString());
		repayLoan.setRemarks(lrParams.getRemark());
		repayLoan.setFundsSource(lrParams.getFundsSource());
		repayLoan.setCreateDate(now);
		repayLoan.setUpdateTime(now);
		
		repayLoan.setCurrencyCd("156");//币种：人民币
	}
	/**
	 * 根据当期还款计划明细构建还款信息明细
	 * @param lrVo		
	 * @param curRpd	当期还款计划
	 * @param overdueFlag	true:逾期；false：正常还款，未逾期
	 * @return
	 */
	private LrRepayInfoVo buildRepayInfoVo(LrRepayInfoVo lrVo,RepayingPlanDetail curRpd,boolean overdueFlag){
		Date repayDate = null; // 还款日期
		if(lrVo.getRepayDate()==null){
			if(!overdueFlag){// 正常还款 (默认还款日期-->当期约定还款日期) currentEndDate当期计划还款日
				repayDate = curRpd.getCurrentEndDate();
			}else{
				repayDate = CommonHelper.formatDate(CommonHelper.getNow(), "yyyy-MM-dd");//当前日期
			}
			lrVo.setRepayDate(repayDate);
		}else{
			repayDate=CommonHelper.formatDate(lrVo.getRepayDate(), "yyyy-MM-dd");
		}
		// ?查询下一期还款计划明细
		
		List<LrRepayItem> lrRepayItems=lrVo.getRepayItems();
		BigDecimal totalPrincipalInt = BigDecimal.ZERO;//总拖欠本息
		BigDecimal totalImposeInt = BigDecimal.ZERO;// 总罚息
		BigDecimal totalRepayingPricipal= BigDecimal.ZERO;//总应还本金
		BigDecimal curNotYetInt=BigDecimal.ZERO;//当期未还利息
		BigDecimal curNotYetPricipal=BigDecimal.ZERO;//当期未还本金
		
		List<RepayingDetail> rdList=null;//还款明细
		
		//还款计划明细(未还包括逾期未还)
		List<RepayingPlanDetail> rpdList=this.findRpdListOfAllNotYet(curRpd.getRepayingPlanId(), repayDate);
		if(CollectionUtils.isNotEmpty(rpdList)){
			Set<Long> rpdIds=Sets.newHashSet();
			for (RepayingPlanDetail t_rpd : rpdList) {
				rpdIds.add(t_rpd.getRepayingPlanDetailId());
			}
			//欠款明细
			List<ArrearsDetail> adList=loanRpInfoService.findAdList(rpdIds);
			//还款明细
			rdList=loanRpInfoService.findRdList(rpdIds);
			// 有逾期进入(包括正常转逾期) S2逾期未还
			final String STATUS_OVERDUE=loanCommonServeice.getCodeVal("PlanStatus", "S2");
			if(CollectionUtils.isNotEmpty(rdList)&&CollectionUtils.isNotEmpty(adList)&&rdList.size()==adList.size()){
				ArrearsDetail ad=null;
				RepayingPlanDetail rpd=null;
				ArrearsDetail first_ad=adList.get(0);
				RepayingDetail rd=null;
				if(overdueFlag){
					boolean dateFlag = false;
					for (int i = 0,size=adList.size(); i <size; i++) {
						ad=adList.get(i);
						rpd=rpdList.get(i);
						if(curRpd.getRepayingPlanDetailId().equals(rpd.getRepayingPlanDetailId())){
							// 当期(正常)
							dateFlag = true;
							// 查询当期还款情况
							List<RepayingDetail> repayingList =dynamicQuery.
									query(RepayingDetail.class, "from RepayingDetail where repayingPlanDetailId = ?1", curRpd.getRepayingPlanDetailId());
							if (CollectionUtils.isNotEmpty(repayingList)) {
								// 正常情况,欠款明细没有值,在这里先负值与状态
								// 未还利息
								ad.setNotyetInterest(curRpd.getCurrentInterest().subtract(
										repayingList.get(0).getRepayedInterest()));
								// 未还本金
								ad.setNotyetPricipal(curRpd.getCurrentPrincipal().subtract(
										repayingList.get(0).getRepayedPrincipal()));
								// 逾期状态
								ad.setStatus(STATUS_OVERDUE);
							}
						} else if(dateFlag){
							ad.setNotyetInterest(rpd.getCurrentInterest());
							// 未还本金
							ad.setNotyetPricipal(rpd.getCurrentPrincipal());
							// 逾期状态
							ad.setStatus(STATUS_OVERDUE);
						}
					}
				}
				int type = 2; // calCulateImposeInterest 方法,默认2 正常
				int changeType = 2;// calCulateImposeInterest,默认2 正常
				// 原来是逾期90天内-->逾期90天以上
				if (LoanConstants.REPAYING_FLAG_OVERDUE.equals(first_ad.getRepayingFlag())
						&& DateTools.getDateDiff(first_ad.getPlanRepayintDate(), repayDate) > 90) {
					type = 1;// 逾期
					changeType = 1;// 转变标识
				} else if (LoanConstants.REPAYING_FLAG_OVERTHEN90.equals(first_ad.getRepayingFlag())) {
					type = 1; // 逾期
				}
				//计算，欠款明细
				adList = this.calCulateImposeInterest(adList, type, changeType, repayDate,
						curRpd.getOverdueRate(),STATUS_OVERDUE);
				for (int int_i = 0; int_i < adList.size(); int_i++) {
					ad = adList.get(int_i);
					rpd = rpdList.get(int_i);
					rd = rdList.get(int_i);
					LrRepayItem lrItem = lrVo.new LrRepayItem();
					lrItem.setPeriod(rpd.getCurrentPeriod());//期次
					lrItem.setRepayingPricipal(rpd.getCurrentPrincipal());//当期应还本金
					lrItem.setRepayingInterest(rpd.getCurrentInterest());//当期应还利息
					lrItem.setRepayingPricipalInterest(rpd.getCurrentPrincipalInterest());//当期应还本息
					lrItem.setRepayingDate(CommonHelper.date2Str(rpd.getCurrentEndDate(), "yyyy-MM-dd"));//当期还款日期
					lrItem.setRepayedPricipal(rd.getRepayedPrincipal());//当期还款本金
					lrItem.setRepayedInterest(rd.getRepayedInterest());//当期还款利息
					lrItem.setNotyetImposeInterest(ad.getCarryoverImposeInterest().add(
							ad.getNotyetImposeInterest()).add(ad.getYearImposeInterest()).add(
									ad.getYearImpose90Interest()));//当期未还罚息
					lrItem.setOverdueDay(DateTools.getDateDiff(rpd.getCurrentEndDate(), repayDate));//逾期天数
					totalRepayingPricipal=totalRepayingPricipal.add(ad.getNotyetPricipal());//应还本金
					totalPrincipalInt=totalPrincipalInt.add(ad.getNotyetInterest()).add(ad.getNotyetPricipal());//拖欠本息
					totalImposeInt=totalImposeInt.add(lrItem.getNotyetImposeInterest());//总罚息
					lrItem.setStatus(dataDict.getCodeName("PlanStatus", "2"));
					lrRepayItems.add(lrItem);
				}
			}
		}
		if(!overdueFlag){//正常还款
			LrRepayItem lrItem= lrVo.new LrRepayItem();
			lrItem.setPeriod(curRpd.getCurrentPeriod());//当期期数
			lrItem.setRepayingInterest(curRpd.getCurrentInterest());//当期应还利息
			lrItem.setRepayingPricipal(curRpd.getCurrentPrincipal());//当期应还本金
			lrItem.setRepayingPricipalInterest(curRpd.getCurrentPrincipalInterest());//当期应还总额
			rdList=dynamicQuery.query(RepayingDetail.class, "from RepayingDetail rd where rd.repayingPlanDetailId=?1", curRpd.getRepayingPlanDetailId());
			if(CollectionUtils.isNotEmpty(rdList)&&rdList.get(0)!=null){
				RepayingDetail rd= rdList.get(0);
				curNotYetInt=curRpd.getCurrentInterest().subtract(rd.getRepayedInterest());//-已还利息
				curNotYetPricipal=curRpd.getCurrentPrincipal().subtract(rd.getRepayedPrincipal());//-已还本金
				//总应还本金+本期未还本金
				totalRepayingPricipal=totalRepayingPricipal.add(curNotYetPricipal);
				lrItem.setRepayedInterest(rd.getRepayedInterest());
				lrItem.setRepayedPricipal(rd.getRepayedPrincipal());
			}
			//currentEndDate 当期计划还款日
			lrItem.setRepayingDate(CommonHelper.date2Str(curRpd.getCurrentEndDate(), "yyyy-MM-dd"));
			lrItem.setOverdueDay(0);//逾期天数为0
			lrItem.setStatus("未还");//
			lrRepayItems.add(lrItem);
		}
		lrVo.setRepayItems(lrRepayItems);
		//应还本金
		lrVo.setPayablePrincipalAmt(totalRepayingPricipal);
		lrVo.setPayableDate(curRpd.getCurrentEndDate());
		//应还金额=总拖欠本息+总罚息+本期未还利息+本期未还本金
		lrVo.setPayableTotalAmt(totalPrincipalInt.add(totalImposeInt)
				.add(curNotYetInt).add(curNotYetPricipal)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		lrVo.setOverdueInt(totalImposeInt.setScale(2, BigDecimal.ROUND_HALF_UP)); //逾期利息
		lrVo.setOverduePi(totalPrincipalInt);
		lrVo.setContractInterestRate(curRpd.getYearRate());//合同利率(实时算) yearRate年利率
		return lrVo;
	}
	/**
	 * 增加贷款产品名称、客户经理名称、还款方式等信息
	 * @param lrVo
	 * @param contractId
	 */
	private void addContractOtherInfo(LrRepayInfoVo lrVo,Long contractId){
		StringBuffer strbBuffer=new StringBuffer();
		strbBuffer.append("select p.product_name,eop.usernum,eop.name")
		.append(" from contract cont  left join product p on cont.product_type =p.product_cd")
		.append(" left join ec_org_person eop on eop.id=cont.apply_user_num where cont.contract_id=?1");
		Object[] objs=dynamicQuery.nativeQuerySingleResult(Object[].class, strbBuffer.toString(), contractId);
		if (objs!=null) {
			lrVo.setLoanProductName(CommonHelper.toStr(objs[0]));
			lrVo.setCustMngNum(CommonHelper.toStr(objs[1]));
			lrVo.setCustMngName(CommonHelper.toStr(objs[2]));
		}
	}
	/**
	 * 增加贷款产品名称、客户经理名称等信息
	 * @param lrVo
	 * @param contractId
	 */
	private void addContractOtherInfo(LrFeeRegisterVo lrVo,Long contractId){
		StringBuffer strbBuffer=new StringBuffer();
		strbBuffer.append("select p.product_name,eop.usernum,eop.name")
		.append(" from contract cont  left join product p on cont.product_type =p.product_cd")
		.append(" left join ec_org_person eop on eop.id=cont.apply_user_num where cont.contract_id=?1");
		Object[] objs=dynamicQuery.nativeQuerySingleResult(Object[].class, strbBuffer.toString(), contractId);
		if (objs!=null) {
			lrVo.setLoanProductName(CommonHelper.toStr(objs[0]));
			lrVo.setCustMngNum(CommonHelper.toStr(objs[1]));
			lrVo.setCustMngName(CommonHelper.toStr(objs[2]));
		}
	}
	/**
	 * 提前还款,计算罚息
	 * 
	 * @param arrearsDetailList欠款明细集
	 * @param type标识(正常2,逾期1)
	 * @param changeType转变标识(逾期90天->逾期90天以上)
	 * @param repayedDate还款日期
	 * @param overdueRate逾期利率
	 * @param ovdueStatus逾期状态
	 * @return
	 */
	private List<ArrearsDetail> calCulateImposeInterest(List<ArrearsDetail> arrearsDetailList, int type,
			int changeType, Date repayedDate, BigDecimal overdueRate, String ovdueStatus) {
		if (arrearsDetailList != null && arrearsDetailList.size() > 0 && type > 0 && repayedDate != null
				&& overdueRate != null) {
			BigDecimal impose_interest = BigDecimal.ZERO; // 罚息
			// 日逾期利率
			overdueRate = overdueRate.divide(LoanConstants.DCNT, 50, BigDecimal.ROUND_HALF_UP);
			if (type == 1 && changeType == 1) { // 逾期90天以上(特殊情况)逾期90天内-->逾期90天以上
				Date overdue90Date = null; // 逾期90天日期
				BigDecimal impose_90_interest = null; // 逾期90天以上罚息
				for (ArrearsDetail arrearsDetail : arrearsDetailList) {
					if (ovdueStatus.equals(arrearsDetail.getStatus())) { // 逾期
						// 集合第一条逾期记录
						if (DateTools.getDateDiff(arrearsDetail.getPlanRepayintDate(), repayedDate) > 90) {
							overdue90Date = DateTools.addDay(arrearsDetail.getPlanRepayintDate(), 90);
						}
						break;
					}
				}
				if (overdue90Date != null) { // 防止出现异常
					for (ArrearsDetail arrearsDetail : arrearsDetailList) {
						if (ovdueStatus.equals(arrearsDetail.getStatus())) {
							// 约定还款日期都小于逾期90天日期时
							if (arrearsDetail.getPlanRepayintDate().compareTo(overdue90Date) < 0) {
								// 逾期90天内产生罚息
								impose_interest = this.calCulateImposeInterest(overdueRate, arrearsDetail
										.getNotyetPricipal(), arrearsDetail.getNotyetInterest(), arrearsDetail
										.getNotyetImposeInterest().add(arrearsDetail.getCarryoverImposeInterest()),
										DateTools.getDateDiff(arrearsDetail.getRecentlyDate(), overdue90Date));
								// 逾期90天以上产生罚息
								impose_90_interest = this.calCulateImposeInterest(overdueRate, arrearsDetail
										.getNotyetPricipal(), arrearsDetail.getNotyetInterest(), arrearsDetail
										.getNotyetImposeInterest().add(arrearsDetail.getCarryoverImposeInterest()),
										DateTools.getDateDiff(overdue90Date, repayedDate));
								// 逾期90天内存放罚息
								arrearsDetail.setNotyetImposeInterest(arrearsDetail.getNotyetImposeInterest().add(
										impose_interest));
								// 逾期90天以上存放罚息
								arrearsDetail.setCarryoverImposeInterest(arrearsDetail.getCarryoverImposeInterest()
										.add(impose_90_interest));
							} else {// 约定还款日期大于等于90天日期
								// 逾期90天以上产生罚息
								impose_90_interest = this.calCulateImposeInterest(overdueRate, arrearsDetail
										.getNotyetPricipal(), arrearsDetail.getNotyetInterest(), arrearsDetail
										.getNotyetImposeInterest().add(arrearsDetail.getCarryoverImposeInterest()),
										DateTools.getDateDiff(arrearsDetail.getRecentlyDate(), repayedDate));
								// 逾期90天以上存放罚息
								arrearsDetail.setCarryoverImposeInterest(arrearsDetail.getCarryoverImposeInterest()
										.add(impose_90_interest));
							}
						}
					}
				}
			} else { // 逾期90天内or逾期90天以上
				for (ArrearsDetail arrearsDetail : arrearsDetailList) {
					if (ovdueStatus.equals(arrearsDetail.getStatus())) {
						// 逾期产生罚息
						impose_interest = this.calCulateImposeInterest(overdueRate, arrearsDetail.getNotyetPricipal(),
								arrearsDetail.getNotyetInterest(), arrearsDetail.getNotyetImposeInterest().add(
										arrearsDetail.getCarryoverImposeInterest()), DateTools.getDateDiff(
										arrearsDetail.getRecentlyDate(), repayedDate));
						if (type == 1) { // 逾期90天以上
							arrearsDetail.setCarryoverImposeInterest(arrearsDetail.getCarryoverImposeInterest().add(
									impose_interest));
						} else { // 逾期90内天
							arrearsDetail.setNotyetImposeInterest(arrearsDetail.getNotyetImposeInterest().add(
									impose_interest));
						}
					}
				}
			}
		}
		return arrearsDetailList;
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
	 * 查询还款计划明细（逾期未还和未还）
	 * @param repayingPlanId
	 * @param repayDate
	 * @return
	 */
	private List<RepayingPlanDetail> findRpdListOfAllNotYet(Long repayingPlanId,Date repayDate){
		StringBuffer strBuffer=new StringBuffer("from RepayingPlanDetail rpd where rpd.repayingPlanId = ?1");
		strBuffer.append(" and rpd.status in (?2) and rpd.currentEndDate < to_date(?3,'yyyy-MM-dd') ");
		strBuffer.append(" order by currentPeriod asc");
		//rpd.currentEndDate<repayDate的都会变成逾期未还
		List<Object> params=Lists.newArrayList();
		params.add(repayingPlanId);
		params.add(loanCommonServeice.getCodeValList("PlanStatus", "S0","S2"));
		params.add(CommonHelper.date2Str(repayDate, "yyyy-MM-dd"));
		return dynamicQuery.query(RepayingPlanDetail.class, strBuffer.toString(), params.toArray());
	}
	/**
	 * 根据合同id查询放款id
	 * @param contractId 合同id
	 * @return
	 */
	private Long getPayLoanInfoId(Long contractId){
		String jpql="from PayLoanInfo pli where pli.contractId =?1 and pli.payStatusCd in (?2) and pli.manyPayStatus != ?3";
		List<Object> params=Lists.newArrayList();
		params.add(contractId);
		params.add(Arrays.asList(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2)));
		params.add("1");
		List<PayLoanInfo> payLoanInfos=dynamicQuery.query(PayLoanInfo.class, jpql, params.toArray());
		if(CollectionUtils.isNotEmpty(payLoanInfos)&&payLoanInfos.get(0)!=null){
			return payLoanInfos.get(0).getPayLoanId();
		}
		return null;
	}
	/**
	 * 查询还款计划
	 * @param contractId
	 * @param payLoanId
	 * @return
	 */
	private RepayingPlan findRepayingPlan(Long contractId,Long payLoanId){
		List<RepayingPlan> rpList=dynamicQuery.query(RepayingPlan.class, "from RepayingPlan rp where rp.contractId=?1 and rp.payLoanId=?2", contractId,payLoanId);
		if(CollectionUtils.isNotEmpty(rpList)){
			return rpList.get(0);
		}
		return null;
	}

	@Override
	public LrRepayInfoVo findRepayingPlanDetailCountData(Long contractId, Long projectId, Long payLoanId) {
		if (contractId == null) {
			throw new RuntimeException("逾期试算时contraictId不能为空");
		}
		List params = Lists.newArrayList();
		int paramIndex = 2;
		StringBuffer jpql = new StringBuffer("SELECT rp FROM RepayingPlan rp WHERE 1=1 ");
		jpql.append(" AND rp.contractId = ?1 ");
		params.add(contractId);
		if(projectId != null){
			jpql.append(" AND rp.projectId = ?"+ paramIndex );
			params.add(projectId);
			paramIndex++;
		}
		if(payLoanId != null){
			jpql.append(" AND rp.payLoanId = ?"+ paramIndex );
			params.add(payLoanId);
			paramIndex++;
		}
		List<RepayingPlan> rpList = dynamicQuery.query(RepayingPlan.class, jpql.toString(), params.toArray());
		if(CollectionUtils.isEmpty(rpList)){
			throw new RuntimeException("逾期试算，查询还款计划无记录");
		}
		
		Long first_rpId = rpList.get(0).getRepayingPlanId();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT pd.repaying_plan_detail_id, " +
				           "pd.current_period, " +
				           "pd.current_end_date, ");
		sql.append("        pd.current_principal_interest, " +
						   "pd.current_principal, " +
						   "pd.current_interest, ");
	    sql.append(        "rd.repayed_principal, " +
	    				   "rd.repayed_interest, " +
	    				   "pd.overdue_rate, " +
	    				   "pd.status, ");
		sql.append(        "ad.notyet_pricipal," +
						   "ad.notyet_interest," +
						   "ad.recently_date, ");
		sql.append(      " (ad.carryover_impose_interest+ad.notyet_impose_interest) notyet_impose_interest " +
				          "FROM repaying_plan_detail pd, ");
		sql.append("            repaying_detail rd," +
				               "arrears_detail ad  ");
		sql.append(" WHERE pd.repaying_plan_detail_id = rd.REPAYING_PLAN_DETAIL_ID ");
		sql.append(" AND pd.repaying_plan_detail_id = ad.repaying_plan_detail_id");
		sql.append(" AND pd.repaying_plan_id = ?1 order by pd.CURRENT_PERIOD asc");
		List<Object[]>  resultItems = dynamicQuery.nativeQuery(sql.toString(), first_rpId);
		
		LrRepayInfoVo lriv =  new LrRepayInfoVo();
		List<LrRepayItem> items = Lists.newArrayList();
		for (Object[] objs : resultItems) {
			Object o = lriv.new LrRepayItem(objs);
			items.add((LrRepayItem)o);
		}
		lriv.setRepayItems(items);
		return lriv;
	}

}
