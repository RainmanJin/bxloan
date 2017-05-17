package com.coamctech.bxloan.web.vo.corpcustomer;

/**
 * 股东列表显示VO
 * @author xc
 */
public class StockHolderListVO {
	/**
	 *  c.ID AS id,
    c.PARTY_ID,
    c.CORP_NAME,
    c.CERTIFICATE_TYPE_CD,
    c.CERTIFICATE_CD,
    c.INVESTMENT_TYPE_CD,
    c.STOCK_PROPORTION,
    c.CONTACTOR_TEL_NUM,
    c.CREATE_TIME AS ct,
    'c'           AS RELA_TYPE
	 */
	private String id;
	private String partyId;
	private String name;
	private String certificateTypeCd;
	private String certificateCd;
	private String investmentType;
	private String stockProportion;
	private String contactTelNum;
	private String createTime;
	private String relaType;
	public StockHolderListVO(){super();}
	
	public StockHolderListVO(Object[] data){
		int index = 0;
		this.id = str(data[index++]);
		this.partyId = str(data[index++]);
		this.name = str(data[index++]);
		this.certificateTypeCd = str(data[index++]);
		this.certificateCd = str(data[index++]);
		this.investmentType = str(data[index++]);
		this.stockProportion = str(data[index++]);
		this.contactTelNum = str(data[index++]);
		this.createTime = str(data[index++]);
		this.relaType = str(data[index++]);
	}
	private String str(Object obj){
		return obj==null?"":obj.toString().trim();
	}
	////////////////////
	////GETTERS&SETTERS///
	///////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertificateTypeCd() {
		return certificateTypeCd;
	}
	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}
	public String getCertificateCd() {
		return certificateCd;
	}
	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}
	public String getInvestmentType() {
		return investmentType;
	}
	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}
	public String getStockProportion() {
		return stockProportion;
	}
	public void setStockProportion(String stockProportion) {
		this.stockProportion = stockProportion;
	}
	public String getContactTelNum() {
		return contactTelNum;
	}
	public void setContactTelNum(String contactTelNum) {
		this.contactTelNum = contactTelNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRelaType() {
		return relaType;
	}
	public void setRelaType(String relaType) {
		this.relaType = relaType;
	}
	
	
}
