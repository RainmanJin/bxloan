package com.coamctech.bxloan.service.model.contractmng;


public class SubContractVo {
	/** 从合同ID */
	protected Long subContractId;
	
	/** 质押物类型 */
	protected String guarantyTypeCd;
	
	/** 业务ID */
	protected Long projectId;
	
	/** 参与人ID */
	protected Long partyId;
	
	/** 从合同编号 */
	protected String subcontractNum;
	
	/** 是否上传合同 */
	protected String isTransDocument;
	
	/** 文档编号 */
	protected String documentNum;
	
	public SubContractVo() {
		super();
	}
	
	public SubContractVo(Long subContractId, String guarantyTypeCd) {
		super();
		this.subContractId = subContractId;
		this.guarantyTypeCd = guarantyTypeCd;
	}

	public Long getSubContractId() {
		return subContractId;
	}
	
	public void setSubContractId(Long subContractId) {
		this.subContractId = subContractId;
	}
	public String getGuarantyTypeCd() {
		return guarantyTypeCd;
	}
	
	public void setGuarantyTypeCd(String guarantyTypeCd) {
		this.guarantyTypeCd = guarantyTypeCd;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getSubcontractNum() {
		return subcontractNum;
	}

	public void setSubcontractNum(String subcontractNum) {
		this.subcontractNum = subcontractNum;
	}

	public String getIsTransDocument() {
		return isTransDocument;
	}

	public void setIsTransDocument(String isTransDocument) {
		this.isTransDocument = isTransDocument;
	}

	public String getDocumentNum() {
		return documentNum;
	}

	public void setDocumentNum(String documentNum) {
		this.documentNum = documentNum;
	}
}
