/***
 * 拆分updateRepayingPlanDetail里的方法
 * **/
package com.coamctech.bxloan.service.afterloan.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.OverdueStateDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.OverdueState;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.afterloan.LoanOperationsService;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfInMoney;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants;
import com.google.common.collect.Lists;

@Service
@Transactional
public class LoanOperationsServiceImpl implements LoanOperationsService{
	@Autowired
	private OverdueStateDao overdueStateDao;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;

	@Override
	public void recordOverdueState(Map<String, Object> paramsMap, RepayingPlan rp, List<RepayingPlanDetail> rpdYetRepayedList, List<OverdueState> osList) {
		ParamsOfInMoney inMoney = (ParamsOfInMoney) paramsMap.get("inMoney");
		if(CommonHelper.toBoolean(paramsMap.get("isNormal2Overdue"))&&CollectionUtils.isEmpty(osList)){
			// 记录逾期情况
			OverdueState temp_os=null;
			for (RepayingPlanDetail t_rpd : rpdYetRepayedList) {
				if (CommonHelper.toInt(paramsMap.get("currentPriod")) <= t_rpd.getCurrentPeriod()) {
				temp_os=new OverdueState();				
				temp_os.setContractId(rp.getContractId());
				temp_os.setLoanId(rp.getPayLoanId()); // 放款ID
				temp_os.setRepayingPlanDetailId(t_rpd.getRepayingPlanDetailId()); // 还款计划明细ID
				temp_os.setSysUpdateDate(CommonHelper.getNow());
				temp_os.setOverdueDate(t_rpd.getCurrentEndDate());// 逾期日期
				temp_os.setPartyId(rp.getPartyId());// 参与人ID
				temp_os.setProjectId(rp.getProjectId());// 业务ID
				//TODO ?
				temp_os.setPayLoanStatus(LoanConstants.BATCH_ONE);// 跑批情况 1：已跑批
				overdueStateDao.save(temp_os);
				}
			}
		}else if(!inMoney.isOverdueFlag()&&CollectionUtils.isNotEmpty(osList)){//正常还款清空逾期情况
			overdueStateDao.delete(osList);
		}
	}



	@Override
	public List recordArrearsDetail(Map<String, Object> paramsMap, List<RepayingPlanDetail> rpdYetRepayedList, List<RepayingDetail> rdList, List<ArrearsDetail> adList, Contract contract) {
		ParamsOfInMoney inMoney = (ParamsOfInMoney) paramsMap.get("inMoney");
		RepayingPlanDetail rpd=null;
		RepayingDetail rd=null;
		ArrearsDetail ad=null;
		//逾期转出金额
		BigDecimal totalOverdueOutAmt = BigDecimal.ZERO;
				if(inMoney.isNormal2Overdue()){
					if (contract.getLastOvdueDate() == null) {
						contract.setLastOvdueDate(CommonHelper.toDate(paramsMap.get("currentEndDate"))); // 最近逾期日期
					}
					BigDecimal overduePrincipal = BigDecimal.ZERO;
					BigDecimal overdueInterest = BigDecimal.ZERO;
					for (int int_i = 0; int_i < rpdYetRepayedList.size(); int_i++) {
						rpd = rpdYetRepayedList.get(int_i);
						rd = rdList.get(int_i);
						ad = adList.get(int_i);
						//没逾期，欠款状态设置为已还
						//ad.getRepayingFlag()还款标识(0:未逾期1:逾期90天内2:逾期90天以上)
						if (!(LoanConstants.REPAYING_FLAG_OVERDUE.equals(ad.getRepayingFlag()) || LoanConstants.REPAYING_FLAG_OVERTHEN90
								.equals(ad.getRepayingFlag()))) {
							ad.setRepayingFlag(CommonHelper.toStr(paramsMap.get("PlanStatus_YES_YET")));
						}
						//逾期
						if (CommonHelper.toStr(paramsMap.get("PlanStatus_NOT_YET")).equals(rpd.getStatus())) {
							rpd.setStatus(CommonHelper.toStr(paramsMap.get("PlanStatus_OVERDUE_NOT_YET"))); // 状态
							ad.setStatus(CommonHelper.toStr(paramsMap.get("PlanStatus_OVERDUE_NOT_YET")));
							 // 未还本金 = 当期应还本金-当期实还本金
							ad.setNotyetPricipal(rpd.getCurrentPrincipal().subtract(rd.getRepayedPrincipal()));
							// 未还利息 =  当期应还利息-当期实还利息
							ad.setNotyetInterest(rpd.getCurrentInterest().subtract(rd.getRepayedInterest())); 
							if (int_i == 0) {
								ad.setRepayingFirstFlag("1");//首次逾期标示
								//首次逾期日期
								if (contract.getFirstOvdueDate() == null) {
									contract.setFirstOvdueDate(ad.getPlanRepayintDate());//计划还款日
								}
								//最近逾期日期
								contract.setLastOvdueDate(ad.getPlanRepayintDate());
								//逾期次数
								contract.setOvdueTime(contract.getOvdueTime() == null ? 1 : (contract.getOvdueTime() + 1));
								//合同状态330 已逾期
								contract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S3"));
							}
							//逾期转出金额
							totalOverdueOutAmt = totalOverdueOutAmt.add(ad.getNotyetPricipal());
						}
						overduePrincipal = overduePrincipal.add(ad.getNotyetPricipal());
						overdueInterest = overdueInterest.add(ad.getNotyetInterest());
					}
					contract.setOvdueAmt(overduePrincipal);
					contract.setOvdueInterest(overdueInterest);
					//TODO 发送逾期短信提醒
					//this.overDueSendMessage(contract, overduePrincipal.add(overdueInterest), repayedDate);
				}else{
					rpd = rpdYetRepayedList.get(rpdYetRepayedList.size() - 1);
					rd = rdList.get(rpdYetRepayedList.size() - 1);
					ad = adList.get(rpdYetRepayedList.size() - 1);
				}
				List<Object> list = Lists.newArrayList();
				list.add(totalOverdueOutAmt);
				list.add(rpd);
				list.add(rd);
				list.add(ad);
				
				return list;
				
	}



	@Override
	public boolean validateIsModifyDocumentType(Long partyId, Long projectId) {
		String contract_status_cd = dataDict.getCodeValList("ContractStatusCode", "S1","S2","S3","S6","S8","S9","S10").toString();
		String project_status = dataDict.getCodeValList("ProjectStatus", "S0","S1","S2","S5").toString();
		
		int count=0;
		StringBuffer sql=new StringBuffer("select  pa.project_id ");
		sql.append(" from project_application pa where 1=1 ");
		sql.append(" and pa.project_status in (");
		
		sql.append(project_status.substring(1, project_status.length()-1)).append(")");
		
		sql.append(" and pa.party_id= ?1 ");
		sql.append(" and pa.project_id! = ?2 ");
		sql.append(" union  select pa.project_id from project_application pa  where 1=1 ");
		sql.append(" and pa.project_id!= ?2 ");
		sql.append("  and exists (select 1  from contract c where 1=1 and c.project_id = pa.project_id");
		sql.append(" and pa.party_id= ?1 ");
		sql.append(" and c.contract_status_cd in (");
		sql.append(contract_status_cd.substring(1,contract_status_cd.length()-1)).append("))");
		
		List<Long> list = dynamicQuery.nativeQuery(Long.class, sql.toString(), partyId, projectId);
		if(CollectionUtils.isNotEmpty(list)) {
			return false;
		} 
		return true;
	}




	
}
