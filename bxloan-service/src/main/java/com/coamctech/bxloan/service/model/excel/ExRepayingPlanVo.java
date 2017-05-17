package com.coamctech.bxloan.service.model.excel;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 还款计划导出
 * @author Acore
 *
 */
public class ExRepayingPlanVo {
	
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 合同编号
	 */
	private String contractNum;
	
	/**
	 * 还款计划id
	 */
	private Long repayingPlanId;
	/**
	 * 还款编号
	 */
	private String repayingNum;
	/**
	 * 还款计划明细
	 */
	private List<ExRepayingPlanDetailVo> exRpdList;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getRepayingNum() {
		return repayingNum;
	}
	public void setRepayingNum(String repayingNum) {
		this.repayingNum = repayingNum;
	}
	public List<ExRepayingPlanDetailVo> getExRpdList() {
		return exRpdList;
	}
	public void setExRpdList(List<ExRepayingPlanDetailVo> exRpdList) {
		this.exRpdList = exRpdList;
	}
	public Long getRepayingPlanId() {
		return repayingPlanId;
	}
	public void setRepayingPlanId(Long repayingPlanId) {
		this.repayingPlanId = repayingPlanId;
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map=Maps.newHashMap();
		map.put("orgName", this.orgName);
		map.put("customerName", this.customerName);
		map.put("repayingNum", this.repayingNum);
		map.put("contractNum", this.contractNum);
		map.put("exRpdList", this.exRpdList);
		return map;
	}
}
