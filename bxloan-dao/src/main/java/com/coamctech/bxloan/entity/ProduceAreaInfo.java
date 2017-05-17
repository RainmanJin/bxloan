package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 生产区域信息
 */
@Entity
@Table(name = "PRODUCE_AREA_INFO", schema = WD_SCHEMA)
public class ProduceAreaInfo implements java.io.Serializable {

	private static final long serialVersionUID = 7785188927716081781L;
	/**
	 * 
	 */
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_PRODUCE_AREA_INFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")	
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long id;
	@Column(name = "PROJECT_ID", precision = 22, scale = 0)
	private Long projectId;
	@Column(name = "LOCATION", length = 20)
	private String location;//地点
	@Column(name = "AREA", precision = 22, scale = 0)
	private BigDecimal area;//面积
	@Column(name = "UNIT", length = 20)
	private String unit;//单位
	/**
	 * 1:自有自用,2:自有出租,3:租赁
	 */
	@Column(name = "AREA_PROPERTY", length = 20)
	private String areaProperty;//生产区域性质
	@Column(name = "RENT", precision = 22, scale = 0)
	private BigDecimal rent;//租金
	/**
	 * 0:否，1：是
	 */
	@Column(name = "WILL_PRODUCE_FUTURE", precision = 22, scale = 0)
	private BigDecimal willProduceFuture;//未来12个月是否生产
	@Column(name = "PRODUCE_CONTENT", length = 20)
	private String produceContent;//生产内容
	/**
	 * 1:间种，2：循环
	 */
	@Column(name = "CULTIVATE_BREED_TYPE", length = 20)
	private String cultivateBreedType;//种养类型
	@Column(name = "EQUIPMENT_SITUATION", length = 20)
	private String equipmentSituation;//设备情况
	@Column(name = "TENANCY", precision = 22, scale = 0)
	private Integer tenancy;//租期
	
	/**
	 * 租期单位
	 */
	@Column(name = "TENANCY_UNIT", length = 1)
	private String tenancyUnit;
	@Temporal(TemporalType.DATE)
	@Column(name = "RENT_DATE", length = 7)
	private Date rentDate;//租金支付时间
	/**
	 * 0:无,1：有
	 */
	@Column(name = "HAVE_LEASE", length = 20)
	private String haveLease;//是否有租赁协议
	/**
	 * 1：农业为主，2：非农业为主
	 */
	@Column(name = "TYPE", length = 20)
	private String type; //类型
	
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return this.projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public BigDecimal getArea() {
		return this.area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public String getUnit() {
		return this.unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAreaProperty() {
		return this.areaProperty;
	}
	public void setAreaProperty(String areaProperty) {
		this.areaProperty = areaProperty;
	}
	public BigDecimal getRent() {
		return this.rent;
	}
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}
	public BigDecimal getWillProduceFuture() {
		return this.willProduceFuture;
	}
	public void setWillProduceFuture(BigDecimal willProduceFuture) {
		this.willProduceFuture = willProduceFuture;
	}
	public String getProduceContent() {
		return this.produceContent;
	}
	public void setProduceContent(String produceContent) {
		this.produceContent = produceContent;
	}
	public String getCultivateBreedType() {
		return this.cultivateBreedType;
	}
	public void setCultivateBreedType(String cultivateBreedType) {
		this.cultivateBreedType = cultivateBreedType;
	}
	public String getEquipmentSituation() {
		return this.equipmentSituation;
	}
	public void setEquipmentSituation(String equipmentSituation) {
		this.equipmentSituation = equipmentSituation;
	}
	public Integer getTenancy() {
		return this.tenancy;
	}
	public void setTenancy(Integer tenancy) {
		this.tenancy = tenancy;
	}
	public Date getRentDate() {
		return this.rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	public String getHaveLease() {
		return this.haveLease;
	}
	public void setHaveLease(String haveLease) {
		this.haveLease = haveLease;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTenancyUnit() {
		return tenancyUnit;
	}
	public void setTenancyUnit(String tenancyUnit) {
		this.tenancyUnit = tenancyUnit;
	}

}