package com.coamctech.bxloan.service.model.bizapply.compute;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.entity.BaseBean;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.google.common.collect.Lists;

/**
 * 资产负债
 * @author AcoreHeng
 *
 */
public class AssetLiabilityInfoVo extends BaseBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 870877077087722322L;
	
	/**
	 * 资产
	 */
	private AssetVo asset=null;
	private LiabilityVo liability=null;
	
	private AssetLiabilityItemVo nonFamilyMembersGuarantee;
	
	//TODO 固定资产
	private BigDecimal houseProperty;
	private BigDecimal vehicle;
	private  BigDecimal productionFacility;
	
	//TODO 存货
	private BigDecimal totalStock;
	private BigDecimal agroStock;
	private  BigDecimal industryCommerceStock;
	
	
	
	
	
	public AssetLiabilityInfoVo() {
		super();
		asset=new AssetVo();
		liability=new LiabilityVo();
	}
	
	/**
	 * 初始化非固定资产及其负债信息
	 * @param bizNfalMap
	 */
	public void initBizNoFixedAsset(Map<String, Object[]> bizNfalMap){
		asset.cashDeposit=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01001", bizNfalMap);
		asset.receivableAccount=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01002", bizNfalMap);
		asset.prepayments=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01003", bizNfalMap);
		asset.lendMoney=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01004", bizNfalMap);
		asset.prepaidRent=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01005", bizNfalMap);
		asset.domesticAppliance=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01006", bizNfalMap);
		asset.equityInvestment=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01007", bizNfalMap);
		asset.otherAssets=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_01008", bizNfalMap);
		liability.payableAccount=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_02001", bizNfalMap);
		liability.paymentInAdvance=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_02002", bizNfalMap);
		liability.privateBorrow=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_02003", bizNfalMap);
		liability.bankLoan=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_02004", bizNfalMap);
		liability.otherLiabilities=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_NAME_02005", bizNfalMap);
		this.nonFamilyMembersGuarantee=this.createAssetLliabilityItemByBizNfalMap("ND_NO_FIEXED_ASSET_TYPE_00", bizNfalMap);
	}
	
	/**
	 * 初始化固定资产
	 * @param houseProperty
	 * @param vehicle
	 * @param productionFacility
	 */
	public void initBizFixedAsset(BigDecimal houseProperty,
			BigDecimal vehicle, BigDecimal productionFacility){
		this.houseProperty=houseProperty;
		this.vehicle=vehicle;
		this.productionFacility=productionFacility;
	}
	/**
	 * 初始化存货信息
	 * @param agroStock
	 * @param industryCommerceStock
	 */
	public void initStockInfo(BigDecimal agroStock, BigDecimal industryCommerceStock){
		this.agroStock=agroStock;
		this.industryCommerceStock=industryCommerceStock;
		this.totalStock=CommonHelper.sumOfBigDecimal(agroStock,industryCommerceStock);
	}
	public AssetLiabilityItemVo createAssetLliabilityItemByBizNfalMap(String key,Map<String,Object[]> bizNfalMap){
		Object[] objs=bizNfalMap.get(key);
		if(objs==null){
			return new AssetLiabilityItemVo();
		}else{
			return new AssetLiabilityItemVo(CommonHelper.toBigDecimal(objs[0]), CommonHelper.toStr(objs[1]));
		}
	}

	//TODO 
	
	public class AssetVo{
		/**
		 * 现金+存款
		 */
		private AssetLiabilityItemVo cashDeposit;
		/**
		 * 应收账款
		 */
		private AssetLiabilityItemVo receivableAccount;
		
		
		/**
		 * 预付款项
		 */
		private AssetLiabilityItemVo prepayments;
		/**
		 * 借出款项
		 */
		private AssetLiabilityItemVo lendMoney;
		/**
		 * 待摊房租
		 */
		private AssetLiabilityItemVo prepaidRent;
		/**
		 * 家用电器
		 */
		private AssetLiabilityItemVo domesticAppliance; 
		/**
		 * 股权投资
		 */
		private AssetLiabilityItemVo equityInvestment; 
		/**
		 * 其他资产
		 */
		private AssetLiabilityItemVo otherAssets;
	}
	
	public class LiabilityVo{
		/**
		 * 应付账款
		 */
		private AssetLiabilityItemVo payableAccount;
		
		
		/**
		 * 预收款项
		 */
		private AssetLiabilityItemVo paymentInAdvance;
		/**
		 * 私人借款
		 */
		private AssetLiabilityItemVo privateBorrow;
		/**
		 * 银行贷款
		 */
		private AssetLiabilityItemVo bankLoan;
		/**
		 * 其他负债
		 */
		private AssetLiabilityItemVo otherLiabilities;
		
	}
	public  class AssetLiabilityItemVo{
		private BigDecimal amount;
		/**
		 * 备注
		 */
		private String remark;
		
		public AssetLiabilityItemVo(){
			
		}
		
		public AssetLiabilityItemVo(BigDecimal amount, String remark) {
			super();
			this.amount = amount;
			this.remark = remark;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public String getRemark() {
			return remark;
		}
	}
	
	/**
	 * @param agroBizType	业务申请（农业、非农业）
	 * @param earnedProfit	经营利润（损益表）
	 * @param applyAmt	贷款金额
	 * @return
	 */
	public List<AssetLiabilityItemVo> toList(String agroBizType,BigDecimal earnedProfit,BigDecimal applyAmt){
		List<AssetLiabilityItemVo> allList=Lists.newArrayList();
		List<AssetLiabilityItemVo> list=Lists.newArrayList();
		list.add(this.asset.cashDeposit);
		list.add(this.asset.receivableAccount);
		list.add(this.asset.prepayments);
		list.add(this.asset.lendMoney);
		if(StringUtils.equals(agroBizType, "2")){//非农业
			list.add(this.asset.prepaidRent);
		}
		AssetLiabilityItemVo chTotal=new AssetLiabilityItemVo(this.totalStock, null);//存货合计
		list.add(chTotal);
		list.add(new AssetLiabilityItemVo(this.agroStock, null));
		list.add(new AssetLiabilityItemVo(this.industryCommerceStock, null));
		list.add(this.asset.domesticAppliance);
		allList.addAll(list);
		AssetLiabilityItemVo ldzcTotal=this.differe(this.sum(list),chTotal);
		allList.add(ldzcTotal);//流动资产小计
		list.clear();//清空
		AssetLiabilityItemVo fangchan=new AssetLiabilityItemVo(this.houseProperty, null);
		list.add(fangchan);
		list.add(new AssetLiabilityItemVo(this.vehicle, null));
		list.add(new AssetLiabilityItemVo(this.productionFacility, null));
		allList.addAll(list);
		AssetLiabilityItemVo gdzcTotal=this.sum(list);
		allList.add(gdzcTotal);//固定资产小计
		allList.add(this.asset.equityInvestment);
		allList.add(this.asset.otherAssets);
		AssetLiabilityItemVo zzcTotal=this.sum(Arrays.asList(ldzcTotal,gdzcTotal,this.asset.equityInvestment,this.asset.otherAssets));
		allList.add(zzcTotal);//总资产
		List<AssetLiabilityItemVo> fzList=Lists.newArrayList();
		fzList.add(this.liability.payableAccount);
		fzList.add(this.liability.paymentInAdvance);
		fzList.add(this.liability.privateBorrow);
		fzList.add(this.liability.bankLoan);//银行贷款
		if(StringUtils.equals(agroBizType, "2")){//非农业
			fzList.add(this.liability.otherLiabilities);
		}
		allList.addAll(fzList);
		AssetLiabilityItemVo fzTotal=this.sum(fzList);
		allList.add(fzTotal);//总负债
		AssetLiabilityItemVo qyTotal=this.differe(zzcTotal, fzTotal);
		allList.add(qyTotal);//权益
		AssetLiabilityItemVo fzqyTotal=this.sum(Arrays.asList(fzTotal,qyTotal));
		allList.add(fzqyTotal);//权益
		allList.add(this.nonFamilyMembersGuarantee);

		allList.add(new AssetLiabilityItemVo(this.divide(earnedProfit, this.getAmountByItem(zzcTotal)), null));//总资产回报率=经营利润/总资产
		allList.add(new AssetLiabilityItemVo(this.divide(earnedProfit, this.getAmountByItem(this.differe(zzcTotal, fangchan))), null));//经营资产回报率=经营利润/(总资产-房产）
		allList.add(new AssetLiabilityItemVo(this.divide(earnedProfit, this.getAmountByItem(qyTotal)), null));//权益回报率=经营利润/权益
			
		allList.add(new AssetLiabilityItemVo(this.divide(this.liability.bankLoan, earnedProfit), null));//贷款承受能力比率=总贷款/经营利润
		allList.add(new AssetLiabilityItemVo(this.divide(CommonHelper.sumOfBigDecimal(this.getAmountByItem(fzTotal),applyAmt), this.getAmountByItem(zzcTotal)), null));//贷款承受能力比率=（总负债+申请贷款）/总资产
		allList.add(new AssetLiabilityItemVo(this.divide(CommonHelper.sumOfBigDecimal(this.getAmountByItem(fzTotal),applyAmt), this.getAmountByItem(this.differe(zzcTotal, fangchan))), null));//贷款承受能力比率=（总负债+申请贷款）/（总资产-房产）
		return allList;
	}
	private AssetLiabilityItemVo sum(List<AssetLiabilityItemVo> list){
		AssetLiabilityItemVo vo=new AssetLiabilityItemVo();
		BigDecimal total=BigDecimal.ZERO;
		if(CollectionUtils.isNotEmpty(list)){
			for (AssetLiabilityItemVo item : list) {
				if(item!=null){
					total=CommonHelper.sumOfBigDecimal(total,item.amount);
				}
			}
		}
		vo.amount=total;
		return vo;
	}
	
	private AssetLiabilityItemVo differe(AssetLiabilityItemVo item1,AssetLiabilityItemVo item2){
		AssetLiabilityItemVo vo=new AssetLiabilityItemVo();
		BigDecimal differe=BigDecimal.ZERO;
		if(item1!=null){
			differe=CommonHelper.sumOfBigDecimal(differe,item1.amount);
		}
		if(item2!=null){
			differe=CommonHelper.diffOfBigDecimal(differe, item2.amount);
		}
		vo.amount=differe;
		return vo;
	}
	private BigDecimal getAmountByItem(AssetLiabilityItemVo item){
		if(item==null){
			return null;
		}
		return item.amount;
	}
	/**
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	private BigDecimal divide(AssetLiabilityItemVo alItem,BigDecimal bd2){
		if(alItem==null){
			return BigDecimal.ZERO;
		}
		BigDecimal bd1=alItem.getAmount();
		if(bd1==null||bd2==null||BigDecimal.ZERO.equals(bd2)){
			return BigDecimal.ZERO;
		}
		return bd1.divide(bd2,4,BigDecimal.ROUND_UP);
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
}
