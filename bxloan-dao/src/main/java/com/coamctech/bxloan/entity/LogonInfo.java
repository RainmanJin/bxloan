package com.coamctech.bxloan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import  com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.entity.BaseEntity;
/**
 * 
 * 用户登陆信息实体类
 * Add by Panyanan 20140728
 * **/
@Entity
@Table(name = "logon_info", schema = GlobalConstants.WD_SCHEMA)
public class LogonInfo extends BaseEntity {
	
	@Id 
	@SequenceGenerator(name = "SEQ_LOGONINFO", sequenceName = GlobalConstants.WD_SCHEMA+".SEQ_LOGONINFO", allocationSize = 1) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOGONINFO") 
	private Long id;
	
	public LogonInfo() {
		super();
	}

	@Column(length = 50, nullable = true)
	private String logname;

	@Column(nullable = true)
	private Date logintime;

	@Column(nullable = true)
	private Date logouttime;

	@Column(length = 20,nullable = true)
	private String ip;
	
	@Column(length = 100,nullable = true)
	private String sessionid;
	
	@Column(length = 200,nullable = true)
	private String event;
	
	@Column(length = 50,nullable = true)
	private String eventtype;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	

	
}
