package com.coamctech.bxloan.service.model.bizapply.compute;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.coamctech.bxloan.commons.entity.BaseBean;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.entity.FamilyConsume;
import com.google.common.collect.Lists;

/**
 * 损益信息
 * @author AcoreHeng
 *
 */
public abstract class ProfitLossInfoVo<T> extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6240314975786618360L;
	
	private List<T> data;
	private BigDecimal earnedProfit;

	public ProfitLossInfoVo() {
		super();
		this.data = new ArrayList<T>();
		
	}
	
	/**
	 * 初始化数据
	 * @param incomeList	收入
	 * @param operatCosts	运营成本（农业成本，工商业成本）
	 * @param fc
	 */
	public void initData(List<T> incomeList,List<T> operatCosts, FamilyConsume fc) {
		T d5=this.sum(incomeList);
		data.add(d5);//收入合计
		data.addAll(incomeList);
		T t2=this.sum(operatCosts);
		data.add(t2);//经营成本合计
		data.addAll(operatCosts);
		T jylr=this.diff(d5, t2);
		data.add(jylr);//经营利润
		this.initEarnedProfit(jylr);
		
		List<T> list=Lists.newArrayList();
		if(fc==null){
			fc=new FamilyConsume();
		}
		list.add(toT(fc.getLifeConsume()));
		list.add(toT(fc.getTuition()));
		list.add(toT(fc.getMedical()));
		list.add(toT(fc.getInsurance()));
		list.add(toT(fc.getOthers1()));
		list.add(toT(fc.getOthers2()));
		list.add(toT(fc.getOthers3()));
		T totalFc=  this.sum(list);
		data.add(totalFc);//家庭支出合计
		data.addAll(list);
		//家庭收入
		data.add(this.toT(fc.getInterestCost()));
		T d23=this.diff(this.diff(jylr, totalFc),this.toT(fc.getInterestCost()));
		data.add(d23);
		T d24=this.toT(fc.getRepaymentCost());
		data.add(d24);
		//月可支配收入
		data.add(d25(d23,d24));
		data.add(divide(jylr, d5));
		data.add(divide(totalFc, jylr));
		data.add(divide(d23, d5));
		data.add(d29(d23));

	}
	/**
	 * 获取实例
	 * @return
	 */
	public abstract T newInstance();
	
	private void initEarnedProfit(T jylr){
		if(jylr instanceof BigDecimal){
			earnedProfit=(BigDecimal) jylr;
		}else if(jylr instanceof ProfitLossItemVo){
			ProfitLossItemVo itemVo=(ProfitLossItemVo) jylr;
			if(itemVo!=null){
				earnedProfit=itemVo.getBeforetimeVal();
			}
		}
	}
	/**
	 * 求和
	 * @param tList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private T sum(List<T> tList){
		if(CollectionUtils.isEmpty(tList)){
			return null;
		}
		T result=newInstance();
		if(result instanceof BigDecimal){
			BigDecimal bd=(BigDecimal)result;
			for (T t : tList) {
				bd=CommonHelper.sumOfBigDecimal(bd,(BigDecimal)t);
			}
			result=(T)bd;
		}else{
			if(result instanceof ProfitLossItemVo){
				ProfitLossItemVo bd=(ProfitLossItemVo)result;
				ProfitLossItemVo temp=null;
				for (T t : tList) {
					temp=(ProfitLossItemVo)t;
					if(temp!=null){
						bd.setBeforetimeVal(CommonHelper.sumOfBigDecimal(bd.getBeforetimeVal(),temp.getBeforetimeVal()));
						bd.setAftertimeVal(CommonHelper.sumOfBigDecimal(bd.getAftertimeVal(),temp.getAftertimeVal()));
					}
				}
				result=(T)bd;
			}
		}
		return result;
	}
	/**
	 * 求差
	 * @param t1
	 * @param t2
	 * @return
	 */
	private T diff(T t1,T t2){
		T result=newInstance();
		if(result instanceof BigDecimal){
			BigDecimal bd=CommonHelper.diffOfBigDecimal((BigDecimal)t1, (BigDecimal)t2);
			result=(T)bd;
		}else if(result instanceof ProfitLossItemVo){
				ProfitLossItemVo bd=(ProfitLossItemVo)result;
				ProfitLossItemVo temp=(ProfitLossItemVo)t1;
				if(temp!=null){
					bd.setBeforetimeVal(CommonHelper.sumOfBigDecimal(bd.getBeforetimeVal(),temp.getBeforetimeVal()));
					bd.setAftertimeVal(CommonHelper.sumOfBigDecimal(bd.getAftertimeVal(),temp.getAftertimeVal()));
				}
				temp=(ProfitLossItemVo)t2;
				if(temp!=null){
					bd.setBeforetimeVal(CommonHelper.diffOfBigDecimal(bd.getBeforetimeVal(),temp.getBeforetimeVal()));
					bd.setAftertimeVal(CommonHelper.diffOfBigDecimal(bd.getAftertimeVal(),temp.getAftertimeVal()));
				}
				result=(T)bd;
			}
		return result;
	}
	/**
	 * @param t1
	 * @param t2
	 * @return
	 */
	private T divide(T t1,T t2){
		T result=newInstance();
		if(result instanceof BigDecimal){
			result=(T)this.divide((BigDecimal)t1, (BigDecimal)t2);
		}else if(result instanceof ProfitLossItemVo){
			ProfitLossItemVo temp=(ProfitLossItemVo)result;
			ProfitLossItemVo temp_t1=(ProfitLossItemVo)t1;
			ProfitLossItemVo temp_t2=(ProfitLossItemVo)t2;
			temp.setBeforetimeVal(this.divide(temp_t1.getBeforetimeVal(), temp_t2.getBeforetimeVal()));
			temp.setAftertimeVal(this.divide(temp_t1.getAftertimeVal(),temp_t2.getAftertimeVal()));
		}
		return result;
	}
	/**
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	private BigDecimal divide(BigDecimal bd1,BigDecimal bd2){
		if(bd1==null||bd2==null||BigDecimal.ZERO.equals(bd2)){
			return BigDecimal.ZERO;
		}
		return bd1.divide(bd2,4,BigDecimal.ROUND_UP);
	}
	/**
	 * 
	 * @param d23
	 * @param d24
	 * @return
	 */
	private T d25(T d23,T d24){
		T result=newInstance();
		final BigDecimal ir=new BigDecimal(12);
		if(result instanceof BigDecimal){
			BigDecimal bd=this.divide((BigDecimal)d23, (BigDecimal)ir);
			if(d24!=null){
				bd=bd.subtract((BigDecimal)d24);
				
			}
			return (T)bd;
		}else if(result instanceof ProfitLossItemVo){
			ProfitLossItemVo temp=(ProfitLossItemVo)result;
			ProfitLossItemVo temp_t1=(ProfitLossItemVo)d23;
			ProfitLossItemVo temp_t2=(ProfitLossItemVo)d24;
			if(temp_t2.getBeforetimeVal()!=null){
				temp.setBeforetimeVal(this.divide(temp_t1.getBeforetimeVal(), ir).subtract(temp_t2.getBeforetimeVal()));
			}else{
				temp.setBeforetimeVal(this.divide(temp_t1.getBeforetimeVal(), ir));
			}
			if(temp_t2.getAftertimeVal()!=null){
				temp.setAftertimeVal(this.divide(temp_t1.getAftertimeVal(),ir));
			}else{
				temp.setAftertimeVal(this.divide(temp_t1.getAftertimeVal(), ir));
			}
		}
		return result;
	}
	/**
	 * d23*0.7
	 * @param t
	 * @return
	 */
	private T d29(T t){
		T result=newInstance();
		final BigDecimal ir=new BigDecimal(0.7);
		if(result instanceof BigDecimal){
			result=(T)((BigDecimal)t).multiply(ir);//d23*0.7
		}else if(result instanceof ProfitLossItemVo){
			ProfitLossItemVo temp=(ProfitLossItemVo)result;
			ProfitLossItemVo temp_t=(ProfitLossItemVo)t;
			temp.setBeforetimeVal(temp_t.getBeforetimeVal().multiply(ir));
			temp.setAftertimeVal(temp_t.getAftertimeVal().multiply(ir));
		}
		return result;
	}
	
	private T toT(BigDecimal bd){
		T result=newInstance();
		if(result instanceof BigDecimal){
			result=(T)bd;
		}else if(result instanceof ProfitLossItemVo){
			ProfitLossItemVo temp=(ProfitLossItemVo)result;
			temp.setBeforetimeVal(bd);
			temp.setAftertimeVal(bd);
		}
		return result;
	}

	public List<T> getData() {
		return data;
	}

	public BigDecimal getEarnedProfit() {
		return earnedProfit;
	}
	
}
