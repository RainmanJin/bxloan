package com.coamctech.bxloan.service.afterloan.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.SystemInterfaceOrgLink;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.service.afterloan.AfterLoanCalCulateService;
import com.coamctech.bxloan.service.model.loanprocess.InterestResultVO;
import com.coamctech.bxloan.service.pettyloan.LoanAccountingService;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchAmtBo;
import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

@Transactional
@Service
public class AfterLoanCalCulateServiceImpl implements  AfterLoanCalCulateService{
	
	/** 逾期90内标识 */
	public static String REPAYING_FLAG_OVERDUE = "1";
	/** 逾期90外标识 */
	public static String REPAYING_FLAG_OVERTHEN90 = "2";
	/** 交易编号KEY */
	public static String ACCOUNTING_TXREFNO = "txrefno";
	
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery query;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private LoanAccountingService loanAccountingService;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	/**
	 * 费用登记
	 * 
	 * @param interestResultVO
	 * @return
	 */
	@Override
	public InterestResultVO registerCostService(InterestResultVO interestResultVO) {
		if (interestResultVO == null || interestResultVO.getContractId() == null
				|| interestResultVO.getCostAmt() == null) {
			throw new RuntimeException("传入数据有误,请联系管理员!");
		}
		//------------组装参数amtVO--------------
		
		//PayLoanStatus S5 冲销已入账 ，S2 已入账
		String _payloan_code = dataDict.getCodeVal("PayLoanStatus", "S5");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select rp.contract_id, ");
		sql.append("rp.party_id, ");
		sql.append("rp.pay_loan_id, ");
		sql.append("rp.project_id, ");
		sql.append("rp.repaying_plan_id, ");
		sql.append("rp.operator_Mechanism, ");
		sql.append("rp.operator_User ");
		sql.append("from repaying_plan rp, contract c, pay_loan_info pl ");
		sql.append("where c.init_contract_id = pl.contract_id  ");
		sql.append("and c.contract_id = rp.contract_id ");
		sql.append("and rp.pay_loan_id = pl.pay_loan_id ");
		//单据状态(1:发送未入账2:已入账3:已退单4:冲销未入账5:冲销已入账)
		sql.append("and rp.transaction_status != '");
		sql.append(_payloan_code).append("' ");
		//多次放款标示
		sql.append("and pl.many_pay_status = '0' ");
		//放款状态
		sql.append("and pl.pay_status_cd != '");
		sql.append(_payloan_code).append("' ");
		sql.append("and rp.contract_id = '").append(interestResultVO.getContractId()).append("' ");
		
		List<Object[]> mapList = this.query.nativeQuery(Object[].class, sql.toString());
		if (CollectionUtils.isEmpty(mapList)) {
			throw new RuntimeException("数据异常,请联系管理员!");
		}
		DoVchAmtBo amtVO = new DoVchAmtBo();
		amtVO.setContractId(CommonHelper.toLong(mapList.get(0)[0]));// 合同ID
		amtVO.setPartyId(CommonHelper.toLong(mapList.get(0)[1])); // 参与人ID
		amtVO.setLoanId(CommonHelper.toLong(mapList.get(0)[2])); // 放款ID
		amtVO.setProjectId(CommonHelper.toLong(mapList.get(0)[3])); // 业务ID
		String dateStr = CommonHelper.date2Str(new Date(), "HH:mm:ss"); // 系统日期时分秒
		amtVO.setVchdate(CommonHelper.str2Date((CommonHelper.date2Str(interestResultVO.getRepayedDate(),
				CommonHelper.DF_DATE))
				+ " " + dateStr, "yyyy-MM-dd HH:mm:ss")); // 账务处理时间
		if (StringUtils.isNotBlank(interestResultVO.getSourceFundType())
				&& !"null".equals(interestResultVO.getSourceFundType())) {
			//费用来源
			amtVO.setBcCostType(interestResultVO.getSourceFundType());
		}
		
		//------------还款是否逾期--------------
		
		sql = new StringBuffer();
		sql.append("select distinct ");
		sql.append("ad.repaying_flag ");	
		sql.append("from ");
		sql.append("repaying_plan rp, ");
		sql.append("repaying_plan_detail rpd, ");
		sql.append("arrears_detail ad ");
		sql.append("where ");
		sql.append("rp.repaying_plan_id = rpd.repaying_plan_id ");
		sql.append("and rpd.repaying_plan_detail_id = ad.repaying_plan_detail_id ");
		sql.append("and rp.contract_id = '").append(interestResultVO.getContractId()).append("' ");
		sql.append("and rp.pay_loan_id = '").append(amtVO.getLoanId())
		   .append("' ");
		mapList = this.query.nativeQuery(Object[].class, sql.toString());
		if (CollectionUtils.isEmpty(mapList)) {
			throw new RuntimeException("数据异常,请联系管理员!");
		}
		//拿到欠款明细表appearsDetail的repayingFlag 还款标识(0:未逾期1:逾期90天内2:逾期90天以上)
		String repaying_flag = CommonHelper.toStr(mapList.get(0));
		if (REPAYING_FLAG_OVERDUE.equals(repaying_flag)) {
			//逾期未满90天
			amtVO.setBcOverCostAmt(interestResultVO.getCostAmt());
		} else if (REPAYING_FLAG_OVERTHEN90.equals(repaying_flag)) {
			//逾期90天外
			amtVO.setBcOverThan90CostAmt(interestResultVO.getCostAmt());
		} else {
			//未逾期
			amtVO.setBcCostAmt(interestResultVO.getCostAmt());
		}

		try {
			Contract contract = contractDao.findOne(new Long(interestResultVO.getContractId()));
			Map resultMap = this.loanAccountingService.doVchByCost(amtVO, contract.getApplyOrgId(), new Long(contract.getApplyUserNum()));
			interestResultVO.setTransactionNo(resultMap.get(ACCOUNTING_TXREFNO) == null ? "" : resultMap.get(ACCOUNTING_TXREFNO).toString());
			// 财务开关断开
			if (!loanCommonServeice.isOpenOfOrgInterface(contract.getApplyOrgId().toString(),
					SystemInterfaceOrgLink.INTERFACEKEY_Accounting)) {
				//接口关闭
				interestResultVO.setStatus(dataDict.getCodeVal("PayLoanStatus", "S2"));// 已入账
			} else {
				//接口打开
				interestResultVO.setStatus(dataDict.getCodeVal("PayLoanStatus", "S1"));// 未入账
			}
		} catch (Exception e) {
			throw new RuntimeException("数据异常,请联系管理员!");
		}

		return interestResultVO;
	}

		
}
