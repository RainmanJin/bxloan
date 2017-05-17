package com.coamctech.bxloan.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;
/**
 * TrafficCar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRAFFIC_CAR", schema = WD_SCHEMA)
public class TrafficCar implements java.io.Serializable {

	// Fields

	private Long guarantyId;
	private String modelStyle;
	private Double runnedKm;
	private String troubleRecords;
	private String vehicleNumber;
	private Date registerDate;
	private String frameNumber;
	private String launchPlaneNumber;
	private String machineEnrol;
	private String machineSteamCard;

	private String registerDateStr;

	// Constructors

	/** default constructor */
	public TrafficCar() {
	}

	/** minimal constructor */
	public TrafficCar(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	/** full constructor */
	public TrafficCar(Long guarantyId, String modelStyle, Double runnedKm,
			String troubleRecords, String vehicleNumber, Date registerDate,
			String frameNumber, String launchPlaneNumber, String machineEnrol,
			String machineSteamCard) {
		this.guarantyId = guarantyId;
		this.modelStyle = modelStyle;
		this.runnedKm = runnedKm;
		this.troubleRecords = troubleRecords;
		this.vehicleNumber = vehicleNumber;
		this.registerDate = registerDate;
		this.frameNumber = frameNumber;
		this.launchPlaneNumber = launchPlaneNumber;
		this.machineEnrol = machineEnrol;
		this.machineSteamCard = machineSteamCard;
	}

	// Property accessors
	@Id
	@Column(name = "GUARANTY_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getGuarantyId() {
		return this.guarantyId;
	}

	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	@Column(name = "MODEL_STYLE", length = 100)
	public String getModelStyle() {
		return this.modelStyle;
	}

	public void setModelStyle(String modelStyle) {
		this.modelStyle = modelStyle;
	}

	@Column(name = "RUNNED_KM", precision = 20, scale = 6)
	public Double getRunnedKm() {
		return this.runnedKm;
	}

	public void setRunnedKm(Double runnedKm) {
		this.runnedKm = runnedKm;
	}

	@Column(name = "TROUBLE_RECORDS", length = 1000)
	public String getTroubleRecords() {
		return this.troubleRecords;
	}

	public void setTroubleRecords(String troubleRecords) {
		this.troubleRecords = troubleRecords;
	}

	@Column(name = "VEHICLE_NUMBER", length = 40)
	public String getVehicleNumber() {
		return this.vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	@Column(name = "REGISTER_DATE", length = 7)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "FRAME_NUMBER", length = 100)
	public String getFrameNumber() {
		return this.frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	@Column(name = "LAUNCH_PLANE_NUMBER", length = 100)
	public String getLaunchPlaneNumber() {
		return this.launchPlaneNumber;
	}

	public void setLaunchPlaneNumber(String launchPlaneNumber) {
		this.launchPlaneNumber = launchPlaneNumber;
	}

	@Column(name = "MACHINE_ENROL", length = 100)
	public String getMachineEnrol() {
		return this.machineEnrol;
	}

	public void setMachineEnrol(String machineEnrol) {
		this.machineEnrol = machineEnrol;
	}

	@Column(name = "MACHINE_STEAM_CARD", length = 100)
	public String getMachineSteamCard() {
		return this.machineSteamCard;
	}

	public void setMachineSteamCard(String machineSteamCard) {
		this.machineSteamCard = machineSteamCard;
	}

	@Transient
	public String getRegisterDateStr() {
		return registerDateStr;
	}

	public void setRegisterDateStr(String registerDateStr) {
		this.registerDateStr = registerDateStr;
	}

}