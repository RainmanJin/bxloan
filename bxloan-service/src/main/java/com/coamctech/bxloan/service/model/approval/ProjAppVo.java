package com.coamctech.bxloan.service.model.approval;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.entity.ApprovalHistoryBxloan;


/**
 * @author Acore
 *
 */
public class ProjAppVo implements Serializable{

	/**
	 *   
	 */
	private static final long serialVersionUID = 7535613499947999575L;
	/**
	 * 业务id
	 */
	private Long projectId;
	/**
	 * 业务编号
	 */
	private String projectNo;
	/**
	 * 审批级别 0:不做批复，1：一级审批做批复，2：二级审批做批复
	 */
	private int approveLevel=0;
	//TODO 申请
	/**
	 * 申请金额
	 */
	private BigDecimal applyAmt;
	/**
	 * 申请周期
	 */
	private Integer applyTerm;
	/**
	 * 申请周期单位
	 */
	private String applyTermUnit;
	/**
	 * 还款方式
	 */
	private String repayingMode;
	
	//TODO 申请利率
	/**
	 * 利率类型
	 */
	private String irTypeCd;
	/**
	 * 年利率
	 */
	private BigDecimal rateValue;
	/**
	 * 调整方式
	 */
	private String adjustPeriod;
	/**
	 * 浮动利率
	 */
	private BigDecimal floatRate;
	
	//TODO 批复
	/**
	 * 批复日期
	 */
	private Date approveDate;
	/**
	 * 批复金额
	 */
	private BigDecimal approveAmt;
	/**
	 * 批复周期
	 */
	private Integer term;
	/**
	 * 批复周期单位
	 */
	private String termUnit;
	/**
	 * 批复还款方式
	 */
	private String approveRepayingMode;
	
	/**
	 * 批复利率类型
	 */
	private String approveIrTypeCd;
	/**
	 * 批复利率值
	 */
	private BigDecimal approveRateValue;
	/**
	 * 批复调整方式
	 */
	private String approveAdjustPeriod;
	/**
	 * 批复浮动利率
	 */
	private BigDecimal approveFloatRate;
	/**
	 * 批复意见
	 */
	private String replyOpinion;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 授信类型
	 * 20150803 mz
	 */
	private String creditType;
	/**
	 * 查询(findProjAppInfo)
	 */
	public ProjAppVo(Object[] objs) {
		super();
		int i=0;
		this.projectId=CommonHelper.toLong(objs[i++]);
		this.projectNo=CommonHelper.toStr(objs[i++]);
		this.applyAmt=scaleHalfUp(objs[i++],2);
		this.applyTerm=CommonHelper.toInt(objs[i++]);
		this.applyTermUnit=CommonHelper.toStr(objs[i++]);
		this.repayingMode=CommonHelper.toStr(objs[i++]);
		this.irTypeCd=CommonHelper.toStr(objs[i++]);
		this.rateValue=scaleHalfUp(objs[i++],6);
		this.adjustPeriod=CommonHelper.toStr(objs[i++]);
		this.floatRate=scaleHalfUp(objs[i++],6);
		//add by mz 20150803 start
		this.creditType=CommonHelper.toStr(objs[i++]);
		//add by mz 20150803 end
	}
	
	public ProjAppVo(Map<String, String> map) {
		super();
		this.projectId=CommonHelper.toLong(map.get("projectId"));
		Integer temp=CommonHelper.toInt(map.get("approveLevel"));
		if(temp!=null){
			this.approveLevel=temp;
		}
		try {
			this.approveDate=CommonHelper.str2Date(map.get("approveDateStr"),CommonHelper.DF_DATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(this.approveDate==null){
			this.approveDate=new Date();
		}
		this.approveAmt=CommonHelper.toBigDecimal(map.get("approveAmt"));
		this.term=CommonHelper.toInt(map.get("term"));
		this.termUnit=CommonHelper.toStr(map.get("termUnit"));
		this.approveRepayingMode=CommonHelper.toStr(map.get("approveRepayingMode"));
		this.approveIrTypeCd=CommonHelper.toStr(map.get("approveIrTypeCd"));
		this.setApproveRateValueStr(map.get("approveRateValueStr"));
		this.approveAdjustPeriod=CommonHelper.toStr(map.get("approveAdjustPeriod"));
		this.setApproveFloatRateStr(map.get("approveFloatRateStr"));
		this.replyOpinion = CommonHelper.toStr(map.get("replyOpinion"));
		//add by mz 20150803 start
		this.creditType = CommonHelper.toStr(map.get("approveCreditType"));
		//add by mz 20150803 end
	}
	
	public ProjAppVo(ApprovalHistoryBxloan ahb) {
		this.approveAmt = scaleHalfUp(ahb.getApproveAmt(), 2);
		this.term = ahb.getApproveTerm().intValue();
		this.termUnit = ahb.getTermUnit();
		this.approveRepayingMode = ahb.getApproveRepayingMode();
		this.approveIrTypeCd = ahb.getApproveIrTypeCd();
		this.approveAdjustPeriod = ahb.getApproveAdjustPeriod();
		this.approveFloatRate = scaleHalfUp(ahb.getApproveFloatRate(), 6);
		this.approveRateValue = scaleHalfUp(ahb.getApproveRateValue(), 6);
		this.approveDate = ahb.getApproveDate();
		this.replyOpinion = ahb.getApprovalOpinion();
		//add by mz 20150803 start
		this.creditType = CommonHelper.toStr(ahb.getCreditType());
		//add by mz 20150803 end
	}

	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public BigDecimal getApplyAmt() {
		return applyAmt;
	}
	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getApplyTermUnit() {
		return applyTermUnit;
	}
	public void setApplyTermUnit(String applyTermUnit) {
		this.applyTermUnit = applyTermUnit;
	}
	public String getRepayingMode() {
		return repayingMode;
	}
	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}
	public BigDecimal getRateValue() {
		return rateValue;
	}
	public void setRateValue(BigDecimal rateValue) {
		this.rateValue = rateValue;
	}
	public String getAdjustPeriod() {
		return adjustPeriod;
	}
	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}
	public BigDecimal getFloatRate() {
		return floatRate;
	}
	public void setFloatRate(BigDecimal floatRate) {
		this.floatRate = floatRate;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public String getApproveDateStr() {
		return CommonHelper.date2Str(approveDate, CommonHelper.DF_DATE);
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public void setApproveDateStr(String approveDate) {
		this.approveDate =CommonHelper.str2Date(approveDate, CommonHelper.DF_DATE);
	}
	public BigDecimal getApproveAmt() {
		return approveAmt;
	}
	public void setApproveAmt(BigDecimal approveAmt) {
		this.approveAmt = approveAmt;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public String getTermUnit() {
		return termUnit;
	}
	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}
	public String getApproveRepayingMode() {
		return approveRepayingMode;
	}
	public void setApproveRepayingMode(String approveRepayingMode) {
		this.approveRepayingMode = approveRepayingMode;
	}
	public String getApproveIrTypeCd() {
		return approveIrTypeCd;
	}
	public void setApproveIrTypeCd(String approveIrTypeCd) {
		this.approveIrTypeCd = approveIrTypeCd;
	}
	public BigDecimal getApproveRateValue() {
		return approveRateValue;
	}
	public void setApproveRateValue(BigDecimal approveRateValue) {
		this.approveRateValue = approveRateValue;
	}
	public String getApproveRateValueStr() {
		if(this.approveRateValue!=null){
			return MoneyUtil.formatMoney(this.approveRateValue.movePointRight(2));
		}
		return StringUtils.EMPTY;
	}
	/**
	 * 将字符串利率除以100
	 * @param approveRateValue
	 */
	public void setApproveRateValueStr(String approveRateValue) {
		this.approveRateValue = CommonHelper.toBigDecimal(approveRateValue);
		if(this.approveRateValue!=null){
			this.approveRateValue=this.approveRateValue.movePointLeft(2);
		}
	}
	public String getApproveAdjustPeriod() {
		return approveAdjustPeriod;
	}
	public void setApproveAdjustPeriod(String approveAdjustPeriod) {
		this.approveAdjustPeriod = approveAdjustPeriod;
	}
	public BigDecimal getApproveFloatRate() {
		return approveFloatRate;
	}
	public void setApproveFloatRate(BigDecimal approveFloatRate) {
		this.approveFloatRate = approveFloatRate;
	}
	/**
	 * 将字符串利率乘以100
	 * @return
	 */
	public String getApproveFloatRateStr() {
		if(this.approveFloatRate!=null){
			this.approveFloatRate.movePointRight(2);
			return this.approveFloatRate.toPlainString();
		}
		return StringUtils.EMPTY;
	}
	/**
	 * 将字符串利率除以100
	 * @param approveRateValue
	 */
	public void setApproveFloatRateStr(String approveFloatRate) {
		this.approveFloatRate = CommonHelper.toBigDecimal(approveFloatRate);
		if(this.approveFloatRate!=null){
			this.approveFloatRate=this.approveFloatRate.movePointLeft(2);
		}
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getIrTypeCd() {
		return irTypeCd;
	}

	public void setIrTypeCd(String irTypeCd) {
		this.irTypeCd = irTypeCd;
	}

	public int getApproveLevel() {
		return approveLevel;
	}

	public void setApproveLevel(int approveLevel) {
		this.approveLevel = approveLevel;
	}

	public String getReplyOpinion() {
		return replyOpinion;
	}

	public void setReplyOpinion(String replyOpinion) {
		this.replyOpinion = replyOpinion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private BigDecimal scaleHalfUp(Object obj, int scale){
		BigDecimal val = CommonHelper.toBigDecimal(obj);
		if(val!=null){
			return val.setScale(scale, BigDecimal.ROUND_HALF_UP);
		}else{
			return val;
		}
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	
}
