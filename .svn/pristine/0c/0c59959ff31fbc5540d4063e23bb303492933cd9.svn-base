package com.coamctech.bxloan.service.model.bizapply.compute;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.coamctech.bxloan.commons.entity.BaseBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 预期现金流数据
 * @author AcoreHeng
 *
 */
public class BizExpectedCashFlowVo extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3957005042628316284L;
	/**
	 * 收入
	 */
	private List<ItemOfEcfiVo> incomes=Lists.newArrayList();
	/**
	 * 支出
	 */
	private List<ItemOfEcfiVo> expends=Lists.newArrayList();
	
	/**
	 * 家庭支出
	 */
	private ItemOfEcfiVo householdSpending;
	
	/**
	 * 其他还款
	 */
	private ItemOfEcfiVo otherPayments;
	
	
	/**
	 * 贷款
	 */
	private ItemOfEcfiVo loan;
	/**
	 * 还款
	 */
	private ItemOfEcfiVo paymemtOfLoan;
	
	
	



	public List<ItemOfEcfiVo> getIncomes() {
		return incomes;
	}



	public void setIncomes(List<ItemOfEcfiVo> incomes) {
		this.incomes = incomes;
	}



	public List<ItemOfEcfiVo> getExpends() {
		return expends;
	}



	public void setExpends(List<ItemOfEcfiVo> expends) {
		this.expends = expends;
	}



	public ItemOfEcfiVo getHouseholdSpending() {
		return householdSpending;
	}



	public void setHouseholdSpending(ItemOfEcfiVo householdSpending) {
		this.householdSpending = householdSpending;
	}



	public ItemOfEcfiVo getOtherPayments() {
		return otherPayments;
	}



	public void setOtherPayments(ItemOfEcfiVo otherPayments) {
		this.otherPayments = otherPayments;
	}
	
	public class ItemOfEcfiVo{
		private String name;
		private Map<String, BigDecimal> amtMoneyMap=Maps.newHashMap();
		private BigDecimal avgAmt;//平均金额
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Map<String, BigDecimal> getAmtMoneyMap() {
			return amtMoneyMap;
		}
		public BigDecimal getAvgAmt() {
			return avgAmt;
		}
		public void setAvgAmt(BigDecimal avgAmt) {
			this.avgAmt = avgAmt;
		}
		
		/**
		 * 平均+每月特定的
		 * @param monthOfYear
		 * @return
		 */
		public BigDecimal getTotalAmt(String monthOfYear) {
			BigDecimal bd=this.amtMoneyMap.get(monthOfYear);
			if (bd == null || BigDecimal.ZERO.equals(bd)){//如果特定月金额不存在则返回平均
				return avgAmt;
			}
			if(avgAmt != null && !BigDecimal.ZERO.equals(avgAmt)) {//如果都不为空则求和
				bd = bd.add(avgAmt);
			}
			return bd;
		}
	}

	public ItemOfEcfiVo getLoan() {
		return loan;
	}



	public void setLoan(ItemOfEcfiVo loan) {
		this.loan = loan;
	}



	public ItemOfEcfiVo getPaymemtOfLoan() {
		return paymemtOfLoan;
	}



	public void setPaymemtOfLoan(ItemOfEcfiVo paymemtOfLoan) {
		this.paymemtOfLoan = paymemtOfLoan;
	}
}
