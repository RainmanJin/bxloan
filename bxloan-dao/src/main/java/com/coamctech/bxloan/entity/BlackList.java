package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;




import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="BLACK_LIST",schema=WD_SCHEMA)
public class BlackList  implements java.io.Serializable {


     private Long blackListId;
     private String certificateTypeCd;
     private String certificateCd;
     private String customerName;
     private Timestamp enterDate;
     private String enterCause;
     private String createUser;
     private Timestamp createDate;
     private Timestamp sysUpdateTime;
     private String blackListStatus;



    public BlackList() {
    }

    public BlackList(Long blackListId, String certificateTypeCd, String certificateCd) {
        this.blackListId = blackListId;
        this.certificateTypeCd = certificateTypeCd;
        this.certificateCd = certificateCd;
    }
    

   
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "BLACK_LIST_ID", unique = true, nullable = false, precision = 10, scale = 0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_BLACK_LIST", allocationSize = 1)
    public Long getBlackListId() {
        return this.blackListId;
    }
    public void setBlackListId(Long blackListId) {
        this.blackListId = blackListId;
    }
    
    @Column(name="CERTIFICATE_TYPE_CD", nullable=false, length=30)
    public String getCertificateTypeCd() {
        return this.certificateTypeCd;
    }
    public void setCertificateTypeCd(String certificateTypeCd) {
        this.certificateTypeCd = certificateTypeCd;
    }
    
    @Column(name="CERTIFICATE_CD", nullable=false, length=30)
    public String getCertificateCd() {
        return this.certificateCd;
    }
    public void setCertificateCd(String certificateCd) {
        this.certificateCd = certificateCd;
    }
    
    @Column(name="CUSTOMER_NAME", length=100)
    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    @Column(name="ENTER_DATE", length=7)
    public Timestamp getEnterDate() {
        return this.enterDate;
    }
    public void setEnterDate(Timestamp enterDate) {
        this.enterDate = enterDate;
    }
    
    @Column(name="ENTER_CAUSE", length=4000)
    public String getEnterCause() {
        return this.enterCause;
    }
    public void setEnterCause(String enterCause) {
        this.enterCause = enterCause;
    }
    
    @Column(name="CREATE_USER", length=100)
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    @Column(name="CREATE_DATE", length=7)
    public Timestamp getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    @Column(name="SYS_UPDATE_TIME", length=11)
    public Timestamp getSysUpdateTime() {
        return this.sysUpdateTime;
    }
    public void setSysUpdateTime(Timestamp sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }
    
    @Column(name="BLACK_LIST_STATUS", length=10)
    public String getBlackListStatus() {
        return this.blackListStatus;
    }
    public void setBlackListStatus(String blackListStatus) {
        this.blackListStatus = blackListStatus;
    }
   








}