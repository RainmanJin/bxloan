package com.coamctech.bxloan.service.pettyloan.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.LossProvision;
import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.BillStatus;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.sysmng.IGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
@Transactional
@Service("loanCommonServeice")
public class LoanCommonServeiceImpl implements LoanCommonServeice{
	@Autowired
	DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private IGenerator generator;
	@Override
	public List<String> getCodeValList(String codeType,String... keys){
		List<String> resultStrs=Lists.newArrayList();
		if(keys==null){
			throw new NullPointerException("keys isn't null");
		}
		for (String key : keys) {
			resultStrs.add(getCodeVal(codeType, key));
		}
		return resultStrs;
	}
	@Override
	public String  getCodeVal(String codeType,String key){
		return dataDict.getUniqueOne(codeType, key).getCodeValue();
	}
	@Override
	public String  getCodeVal(LoanStatus loanStatus){
		return dataDict.getUniqueOne(loanStatus.getCodeType(), loanStatus.getKey()).getCodeValue();
	}
	@Override
	public String getCodeName(String codeType, String value) {
		return dataDict.getCodeName(codeType, value);
	}
	@Override
	public LoanStatus getLoanStatus(String codeType,String val) {
		if(StringUtils.isBlank(val)){
			return null;
		}
		EnumSet<LoanStatus> enumSet=LoanStatus.getEnumSet(codeType);
		if (CollectionUtils.isEmpty(enumSet)) {
			return null;
		}
		for (LoanStatus ls : enumSet) {
			if(getCodeVal(ls).equals(val)){
				return ls;
			}
		}
		throw new LoanBizException("贷款状态不存在");
	}
	@Override
	public String getSysParamVal(String sysParamType,String sysParamKey){
		return dynamicQuery.nativeQuerySingleResult(String.class,"select scp.system_common_parameter_value from system_common_parameters scp where scp.system_common_parameter_type=?1 and scp.system_common_parameter_key=?2",sysParamType,sysParamKey);
	}
	@Override
	public Set<String> getProductConfForCustRepayPlan() {
		StringBuffer sb=new StringBuffer();
		final String type="03";
		final String key="ProductRepayConfig";
		sb.append("select scp.standby_desc1 from system_common_parameters scp ");
		sb.append("where scp.SYSTEM_COMMON_PARAMETER_TYPE=?1 and scp.SYSTEM_COMMON_PARAMETER_KEY=?2");
		String resultStr=dynamicQuery.nativeQuerySingleResult(String.class, sb.toString(), type,key);
		return org.springframework.util.StringUtils.commaDelimitedListToSet(resultStr);
	}
	@Override
	public boolean isOpenOfOrgInterface(String orgId, String key) {
		String result=dynamicQuery.nativeQuerySingleResult(String.class,"select interface_value from system_interfaceOrgLink where orgId = ?1 and interface_key = ?2", orgId,key);
		return StringUtils.equals("1", result);
	}
	@Override
	public String getTransNo(String bussType, String bchId, int customerType) {
		return commonBizNumberBS.getTxRefNo(bussType, bchId, customerType);
	}
	@Override
	public Long getMaxSeqno(String txrefNo) {
		try {
			return  this.generator.generateSequenceNumber(txrefNo);
		} catch (Exception e) {
			throw new LoanBizException("获取账务信息表最大序号出错");
		}
	}
	@Override
	public String getCustomerName(String customerNum) {
		Object[] strs=findCustomerByNum(customerNum);
		return strs!=null?String.valueOf(strs[3]):StringUtils.EMPTY;
	}
	private Object[] findCustomerByNum(String customerNum){
		String sql ="select case when p.party_type_cd = '1' then (select cc.linkman_tel　from corporation_customer cc where cc.customer_num = p.customer_num) else (select aa.mobile_tel　from individual aa where aa.customer_num=p.customer_num) end phone,p.party_type_cd, p.customer_num,p.party_name from party p where p.customer_num=?1";
		return dynamicQuery.nativeQuerySingleResult(Object[].class, sql, customerNum);
	}
	@Override
	public String getOrgDeptmentName(Long orgId) {
		return dynamicQuery.nativeQuerySingleResult(String.class,"select name from ec_org_department where id = ?1", orgId);
	}
	@Override
	public String findBillUrl(int flag) {
		if(!Arrays.asList(1,2).contains(flag)){
			return StringUtils.EMPTY;
		}
		List<Object[]> list=dynamicQuery.nativeQuery(Object[].class,"select scp.system_common_parameter_key,scp.standby_desc1 from system_common_parameters scp where scp.system_common_parameter_type=?1","07");
		HashMap<String, String> map=Maps.newHashMap();
		if(CollectionUtils.isNotEmpty(list)){
			for (Object[] strs : list) {
				map.put(String.valueOf(strs[0]), String.valueOf(strs[1]));
			}
		}
		StringBuffer urlBuffer=new StringBuffer();
		urlBuffer.append(StringUtils.defaultIfBlank(map.get("approoturl"), StringUtils.EMPTY));//应用服务器访问地址
		switch (flag) {
		case 1:
			urlBuffer.append(StringUtils.defaultIfBlank(map.get("payouturl"), StringUtils.EMPTY));//放款信息访问地址
			break;
		case 2:
			urlBuffer.append(StringUtils.defaultIfBlank(map.get("paidurl"), StringUtils.EMPTY));//还款信息访问地址
			break;
		default:
			break;
		}
		return urlBuffer.toString();
	}
	@Override
	public Date getRepayDateOfLastRepayingPlan(Long contractId){
		StringBuffer sql = new StringBuffer();
		sql.append("select rpd.current_end_date ");
		sql.append("from repaying_plan rp,repaying_plan_detail rpd,");
		sql.append("pay_loan_info pl,contract c where c.init_contract_id = pl.contract_id");
		sql.append(" and rp.contract_id = c.contract_id ");
		sql.append(" and rp.contract_id = ?1");
		sql.append(" and rp.pay_loan_id = pl.pay_loan_id and pl.pay_status_cd in (?2)");
		sql.append(" and rp.repaying_plan_id = rpd.repaying_plan_id ");
		sql.append(" order by rpd.current_end_date desc ");
		List<Object> params=Lists.newArrayList();
		params.add(contractId);
		params.add(Arrays.asList(getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2),getCodeVal(LoanStatus.PAY_LOAN_STATUS_S4)));
		List<Date> result=dynamicQuery.nativeQuery(Date.class, sql.toString(), params.toArray());
		if(CollectionUtils.isNotEmpty(result)){
			return result.get(0);
		}
		return null;
	}
	@Override
	public boolean isAdvanceRepayOfContract(String contractNum) {
		//repaymentStatusCd 既不是退单也不是冲销已入账,cleanCutCd是S2或S3，CtrlIndicator（S2）正在流程中
		StringBuffer sqlBuffer=new StringBuffer("from RepayLoan rl where rl.customerNum=?1");
		sqlBuffer.append(" and rl.repaymentStatusCd not in (?2)");
		sqlBuffer.append(" and rl.cleanCutCd in (?3)");
		sqlBuffer.append(" and rl.valid in (?4)");
		sqlBuffer.append(" order by rl.repayDate desc");
		List<Object> params=Lists.newArrayList();
		params.add(contractNum);
		params.add(getCodeValList("PayLoanStatus", "S3","S5"));
		params.add(getCodeValList("CleanCutCode", "S2", "S3"));
		params.add(getCodeVal("CtrlIndicator", "S2"));
		Long count=dynamicQuery.queryCount(sqlBuffer.toString(), params.toArray());
		if(count>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean isExtensionOfContact(String contractNum) {
		StringBuffer sql=new StringBuffer("select * from project_application pa  where 1=1 ");
		sql.append(" and pa.business_process_type in (?1)");
		sql.append(" and pa.project_status in (?2)");
		sql.append(" and pa.init_contract_num=?3");
		List<Object> params=Lists.newArrayList();
		params.add(Arrays.asList(getCodeVal("BussinessProperty", "S3"),getCodeVal("BussinessProperty", "S2")));
		params.add(Arrays.asList(getCodeVal("ProjectStatus", "S0"),getCodeVal("ProjectStatus", "S1"),getCodeVal("ProjectStatus", "S2")));
		params.add(contractNum);
		Long count=dynamicQuery.nativeQueryCount(sql.toString(), params.toArray());
		if(count>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean isVerifCancelOfContact(String contractNum) {
		Long count = dynamicQuery
				.nativeQueryCount(
						"select * from  loan_cancle lc where lc.contract_id in (select distinct c.contract_id from contract c where c.contract_num=?1) and lc.loan_cancle_state_cd=?2",
						contractNum, getCodeVal("PayLoanStatus", "S1"));
		if (count > 0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean isNoneEnterOfContact(String contractNum) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tally_certificate tally where tally.cont_cd = ?1");
		sql.append(" and tally.bill_sts in (?2)");
		List<Object> params=Lists.newArrayList();
		params.add(contractNum);
		params.add(Arrays.asList(BillStatus.SEND_NONE.toStr(),BillStatus.CX_NONE.toStr()));
		Long resultInt =  dynamicQuery
				.nativeQueryCount(sql.toString(),params.toArray());
		if (resultInt > 0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean isLastTallyCertificate(String billCd,String contractNum) {
		String result=dynamicQuery.nativeQuerySingleResult(String.class,"select t.bill_cd from (select tc.bill_cd from tally_certificate tc where tc.cont_cd=?1 and tc.bill_sts=?2  order by tc.bill_cd desc) t where rownum <2", contractNum,BillStatus.ENTER.toStr());
		if(StringUtils.isNotBlank(result)&&result.equals(billCd)){
			return true;
		}
		return false;
	}
	@Override
	public boolean isAcrossYearFlushes(String billCd, String contractNum) {
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("from RepayLoan rl where rl.contractNum=?1");
		strBuffer.append(" and rl.repaymentStatusCd=?2 and rl.valid=?3");
		strBuffer.append(" and rl.payLoanId=?4 order by createDate desc");
		List<RepayLoan> rlList=dynamicQuery.query(RepayLoan.class,strBuffer.toString(), contractNum,getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2),"1",getPayLoanId(billCd));
		RepayLoan repayLoan=null;
		if(CollectionUtils.isNotEmpty(rlList)){
			repayLoan=rlList.get(0);
		}
		if(repayLoan==null){
			throw new LoanBizException("查询还款信息出错");
		}
		List<RepayingPlanDetail> rpdList=dynamicQuery.query(RepayingPlanDetail.class, "from RepayingPlanDetail where transactionNo = ?1  order by currentPeriod asc ", billCd);
		if(CollectionUtils.isEmpty(rpdList)){
			throw new LoanBizException("查询还款计划信息出错");
		}
		RepayingPlanDetail rpd=null;
		for (RepayingPlanDetail temp_rpd : rpdList) {
			if (DateTools.getDateDiff(temp_rpd.getCurrentStartDate(), repayLoan.getRepayDate()) >= 0
					&& DateTools.getDateDiff(repayLoan.getRepayDate(), temp_rpd.getCurrentEndDate()) >= 0) {
				rpd = temp_rpd;
				break;
			}
		}
		if(rpd==null){
			return false;
		}
		final Date curSysDate= new Date();
		final Date januaryFirst=getJanuaryFirst(rpd.getCurrentStartDate(),curSysDate);
		if(DateTools.getDateDiff(januaryFirst, curSysDate)>0){//当前日期过了一月一日
			if (repayLoan.getRepayDate().compareTo(januaryFirst) <= 0) { // 还款日期小于1月1
				if (DateTools.getDateDiff(rpd.getCurrentStartDate(), januaryFirst) > 0
						&& DateTools.getDateDiff(januaryFirst, rpd.getCurrentEndDate()) > 0) {
					if(getCountOfBizRate(rpd.getRepayingPlanId(), getCodeVal("InterestRateAdjustment","S2"), getCodeVal("AdjustPeriod", "S1"))>0){
						return true;
						}
					}
				}
		}else{
			if (repayLoan.getRepayDate().compareTo(januaryFirst) > 0) { // 还款日期大于1月1
				if (DateTools.getDateDiff(rpd.getCurrentStartDate(), januaryFirst) > 0
						&& DateTools.getDateDiff(januaryFirst, rpd.getCurrentEndDate()) > 0) {
					if(getCountOfBizRate(rpd.getRepayingPlanId(), getCodeVal("InterestRateAdjustment","S2"), getCodeVal("AdjustPeriod", "S1"))>0){
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public boolean isAllowContractNature(String contractNum) {
		StringBuffer stringBuffer = new StringBuffer();
		//不是展期合同和重组合同，只有原有合同可以
		stringBuffer.append("from Contract c where c.contractNatureCd in ('2','3') and c.contractNum = ?1");
		Long count=dynamicQuery.queryCount(stringBuffer.toString(), contractNum);
		if(count>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean isLtDateRepayAndPay(Long repayingPlanId) {
		List<RepayingPlanDetail> rpdList=dynamicQuery.query(RepayingPlanDetail.class, "from RepayingPlanDetail rpd where rpd.repayingPlanId = ?1  order by rpd.currentPeriod asc", repayingPlanId);
		if(CollectionUtils.isNotEmpty(rpdList)){
			for (RepayingPlanDetail rpd : rpdList) {
				if(rpd.getCurrentEndDate().compareTo(rpd.getCurrentStartDate())<0){//当期计划还款日<当期计息起日	
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean isNotEqTotalAmtOfRepayAndPay(Long repayingPlanId,BigDecimal loanAmount) {
		List<RepayingPlanDetail> rpdList=dynamicQuery.query(RepayingPlanDetail.class, "from RepayingPlanDetail rpd where rpd.repayingPlanId = ?1  order by rpd.currentPeriod asc", repayingPlanId);
		if(CollectionUtils.isNotEmpty(rpdList)){
			BigDecimal totalRepayAmt = BigDecimal.ZERO;
			for (RepayingPlanDetail rpd : rpdList) {
				totalRepayAmt=totalRepayAmt.add(rpd.getCurrentPrincipal());
			}
			return totalRepayAmt.compareTo(loanAmount)!=0;
		}
		return false;
	}
	/**
	 * 返回日期1月1
	 * 
	 * @param date日期
	 * @param systemDate系统日期
	 * @return
	 * @throws BizException
	 */
	private Date getJanuaryFirst(Date date, Date systemDate){
		if (date == null) {
			throw new NullPointerException("时间异常!");
		}
		int systemYear = Integer.parseInt(DateTools.dateToString(systemDate, "yyyy"));
		int dateYear = Integer.parseInt(DateTools.dateToString(date, "yyyy"));
		if (systemYear == dateYear) {
			systemYear = systemYear + 1;
		}
		return  DateTools.stringToDate(systemYear + "-01-01","yyyy-MM-dd");
	}
	private Long getCountOfBizRate(Long repayingPlanId,String finalIrTypeCd,String finalAdjustPeriod){
		return dynamicQuery.nativeQuerySingleResult(Long.class, "select count(1) from biz_rate br where br.project_id in (select * from repaying_plan rp where rp.repaying_plan_id=?1) and br.final_ir_type_cd=?2 and br.final_adjust_period=?3", repayingPlanId,finalIrTypeCd,finalAdjustPeriod);
	}
	/**
	 * 通过单据编号查询放款id
	 * @param billCd
	 * @return
	 */
	private Long getPayLoanId(String billCd){
		return dynamicQuery.nativeQuerySingleResult(BigDecimal.class, "select distinct ai.loanid from accounting_info ai where ai.txrefno=?1", billCd).longValue();
	}
	@Override
	public List<LossProvision> findLossProvisionList(Long orgIdOrContId,
			String objectDimensionType, String transactionStatus) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from LossProvision where orgIdOrContractId = ?1");
		strBuffer.append(" and objectDimensionType = ?2");//对象维度类型 (1:按经办机构计提2:按单笔合同计提)
		strBuffer.append(" and transactionStatus = ?3");//单据状态(1:发送未入账2:已入账3:已退单4:冲销未入账5:冲销已入账)
		strBuffer.append(" order by serialNum desc ");
		return dynamicQuery.query(LossProvision.class, strBuffer.toString(), orgIdOrContId,objectDimensionType,transactionStatus);
	}
}
