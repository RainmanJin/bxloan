package com.coamctech.bxloan.entity;
// default package
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 联保体表
 * 
 * @Modify by wangxy 20150617
 */
@Entity
@Table(name = "UNITE_GU_NEGO", schema = GlobalConstants.WD_SCHEMA)
public class UniteGuNego implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6889090446227178264L;
	// Fields
	private Long id;
	private String unGuId;//联保体协议编号
	private String unGroupName;//联保体小组名称
	private String unType;//联保体类型
	private BigDecimal unTrustAllAmt;//联保体授信总金额
	private Date begDate;//起始日期
	private Date endDate;//到期日期
	private String unStatus;//联保体协议状态
	private String custManager;//客户经理
	private String guaranWay;//保证方式
	private Date unSigeDate;//签订日期
	private String segiOrg;//登记机构
	private Date segiDate;//登记日期
	private String segiMan;//登记人
	private Long segiManId;//登记人Id
	private String remark;//备注
	private String customerQuantity;//联保体成员数量
	private String seqUniteGuNego;
	private String managerId;//经理ID
	
	private String sysCd;

	// Constructors
	public UniteGuNego(Long id,String unGuId,String unGroupName) {
		this.id = id;
		this.unGuId = unGuId;
		this.unGroupName = unGroupName;
	}
	/** default constructor */
	public UniteGuNego() {
	}

	/** minimal constructor */
	public UniteGuNego(Long id, String unGuId) {
		this.id = id;
		this.unGuId = unGuId;
	}

	/** full constructor */
	public UniteGuNego(Long id, String unGuId, String unGroupName,
			String unType, BigDecimal unTrustAllAmt, Date begDate,
			Date endDate, String unStatus, String custManager,
			String guaranWay, Date unSigeDate, String segiOrg,
			Date segiDate, String segiMan, String remark,
			String customerQuantity, String seqUniteGuNego, String managerId) {
		this.id = id;
		this.unGuId = unGuId;
		this.unGroupName = unGroupName;
		this.unType = unType;
		this.unTrustAllAmt = unTrustAllAmt;
		this.begDate = begDate;
		this.endDate = endDate;
		this.unStatus = unStatus;
		this.custManager = custManager;
		this.guaranWay = guaranWay;
		this.unSigeDate = unSigeDate;
		this.segiOrg = segiOrg;
		this.segiDate = segiDate;
		this.segiMan = segiMan;
		this.remark = remark;
		this.customerQuantity = customerQuantity;
		this.seqUniteGuNego = seqUniteGuNego;
		this.managerId = managerId;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_UNITE_GU_NEGO", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "UN_GU_ID", nullable = false, length = 20)
	public String getUnGuId() {
		return this.unGuId;
	}

	public void setUnGuId(String unGuId) {
		this.unGuId = unGuId;
	}

	@Column(name = "UN_GROUP_NAME", length = 40)
	public String getUnGroupName() {
		return this.unGroupName;
	}

	public void setUnGroupName(String unGroupName) {
		this.unGroupName = unGroupName;
	}

	@Column(name = "UN_TYPE", length = 20)
	public String getUnType() {
		return this.unType;
	}

	public void setUnType(String unType) {
		this.unType = unType;
	}

	@Column(name = "UN_TRUST_ALL_AMT", precision = 20)
	public BigDecimal getUnTrustAllAmt() {
		return this.unTrustAllAmt;
	}

	public void setUnTrustAllAmt(BigDecimal unTrustAllAmt) {
		this.unTrustAllAmt = unTrustAllAmt;
	}

	@Column(name = "BEG_DATE", length = 7)
	public Date getBegDate() {
		return this.begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "UN_STATUS", length = 2)
	public String getUnStatus() {
		return this.unStatus;
	}

	public void setUnStatus(String unStatus) {
		this.unStatus = unStatus;
	}

	@Column(name = "CUST_MANAGER", length = 50)
	public String getCustManager() {
		return this.custManager;
	}

	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}

	@Column(name = "GUARAN_WAY", length = 40)
	public String getGuaranWay() {
		return this.guaranWay;
	}

	public void setGuaranWay(String guaranWay) {
		this.guaranWay = guaranWay;
	}

	@Column(name = "UN_SIGE_DATE", length = 7)
	public Date getUnSigeDate() {
		return this.unSigeDate;
	}

	public void setUnSigeDate(Date unSigeDate) {
		this.unSigeDate = unSigeDate;
	}

	@Column(name = "SEGI_ORG", length = 20)
	public String getSegiOrg() {
		return this.segiOrg;
	}

	public void setSegiOrg(String segiOrg) {
		this.segiOrg = segiOrg;
	}

	@Column(name = "SEGI_DATE", length = 7)
	public Date getSegiDate() {
		return this.segiDate;
	}

	public void setSegiDate(Date segiDate) {
		this.segiDate = segiDate;
	}

	@Column(name = "SEGI_MAN", length = 50)
	public String getSegiMan() {
		return this.segiMan;
	}

	public void setSegiMan(String segiMan) {
		this.segiMan = segiMan;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CUSTOMER_QUANTITY", length = 10)
	public String getCustomerQuantity() {
		return this.customerQuantity;
	}

	public void setCustomerQuantity(String customerQuantity) {
		this.customerQuantity = customerQuantity;
	}

	@Column(name = "SEQ_UNITE_GU_NEGO", length = 100)
	public String getSeqUniteGuNego() {
		return this.seqUniteGuNego;
	}

	public void setSeqUniteGuNego(String seqUniteGuNego) {
		this.seqUniteGuNego = seqUniteGuNego;
	}

	@Column(name = "MANAGER_ID", length = 50)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	@Column(name="segi_man_id",precision=20,scale=0)
	public Long getSegiManId() {
		return segiManId;
	}
	public void setSegiManId(Long segiManId) {
		this.segiManId = segiManId;
	}
	@Column(name = "SYS_CD", length = 1)
	public String getSysCd() {
		return sysCd;
	}
	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
}
