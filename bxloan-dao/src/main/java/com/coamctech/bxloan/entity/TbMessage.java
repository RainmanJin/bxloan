package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.entity.BaseEntity;

@Entity
@Table(name="TB_MESSAGE",schema = WD_SCHEMA)
public class TbMessage  extends BaseEntity {

     private Long id;
     private String title;
     private Long type;
     private String content;
     private String attachment;
     private String sender;
     private String sendername;
     private String receiver;
     private String receivername;
     private String receiverdepartment;
     private String receiverdepartmentname;
     private String receiverrole;
     private String receiverrolename;
     private Date sendtime;
     private Date readtime;
     private Long state;
     private Long workitemid;
     private Long processinstanceid;
     private Long projectid;
     private Long contractid;
     private Long partyId;
     private Long policySystemId;
     private Long ifAlready;


    /** default constructor */
    public TbMessage() {
    }
	/** minimal constructor */
    public TbMessage(Long id) {
        this.id = id;
    }
    

    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_TB_MESSAGE", allocationSize = 1)
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="TITLE", length=200)
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="TYPE", precision=0)
    public Long getType() {
        return this.type;
    }
    public void setType(Long type) {
        this.type = type;
    }
    
    @Column(name="CONTENT", length=2000)
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="ATTACHMENT", length=200)
    public String getAttachment() {
        return this.attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    
    @Column(name="SENDER", length=20)
    public String getSender() {
        return this.sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    @Column(name="SENDERNAME", length=20)
    public String getSendername() {
        return this.sendername;
    }
    public void setSendername(String sendername) {
        this.sendername = sendername;
    }
    
    @Column(name="RECEIVER", length=1000)
    public String getReceiver() {
        return this.receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    @Column(name="RECEIVERNAME", length=1000)
    public String getReceivername() {
        return this.receivername;
    }
    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }
    
    @Column(name="RECEIVERDEPARTMENT", length=200)
    public String getReceiverdepartment() {
        return this.receiverdepartment;
    }
    public void setReceiverdepartment(String receiverdepartment) {
        this.receiverdepartment = receiverdepartment;
    }
    
    @Column(name="RECEIVERDEPARTMENTNAME", length=200)
    public String getReceiverdepartmentname() {
        return this.receiverdepartmentname;
    }
    public void setReceiverdepartmentname(String receiverdepartmentname) {
        this.receiverdepartmentname = receiverdepartmentname;
    }
    
    @Column(name="RECEIVERROLE", length=200)
    public String getReceiverrole() {
        return this.receiverrole;
    }
    public void setReceiverrole(String receiverrole) {
        this.receiverrole = receiverrole;
    }
    
    @Column(name="RECEIVERROLENAME", length=200)
    public String getReceiverrolename() {
        return this.receiverrolename;
    }
    public void setReceiverrolename(String receiverrolename) {
        this.receiverrolename = receiverrolename;
    }
    
    @Column(name="SENDTIME", length=7)
    public Date getSendtime() {
        return this.sendtime;
    }
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }
    
    @Column(name="READTIME", length=7)
    public Date getReadtime() {
        return this.readtime;
    }
    public void setReadtime(Date readtime) {
        this.readtime = readtime;
    }
    
    @Column(name="STATE", precision=0)
    public Long getState() {
        return this.state;
    }
    public void setState(Long state) {
        this.state = state;
    }
    
    @Column(name="WORKITEMID", precision=0)
    public Long getWorkitemid() {
        return this.workitemid;
    }
    public void setWorkitemid(Long workitemid) {
        this.workitemid = workitemid;
    }
    
    @Column(name="PROCESSINSTANCEID", precision=0)
    public Long getProcessinstanceid() {
        return this.processinstanceid;
    }
    public void setProcessinstanceid(Long processinstanceid) {
        this.processinstanceid = processinstanceid;
    }
    
    @Column(name="PROJECTID", precision=0)
    public Long getProjectid() {
        return this.projectid;
    }
    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }
    
    @Column(name="CONTRACTID", precision=0)
    public Long getContractid() {
        return this.contractid;
    }
    public void setContractid(Long contractid) {
        this.contractid = contractid;
    }
    
    @Column(name="PARTY_ID", precision=12, scale=0)
    public Long getPartyId() {
        return this.partyId;
    }
    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
    
    @Column(name="POLICY_SYSTEM_ID", precision=0)
    public Long getPolicySystemId() {
        return this.policySystemId;
    }
    public void setPolicySystemId(Long policySystemId) {
        this.policySystemId = policySystemId;
    }
    
    @Column(name="IF_ALREADY", precision=0)
    public Long getIfAlready() {
        return this.ifAlready;
    }
    public void setIfAlready(Long ifAlready) {
        this.ifAlready = ifAlready;
    }
   








}