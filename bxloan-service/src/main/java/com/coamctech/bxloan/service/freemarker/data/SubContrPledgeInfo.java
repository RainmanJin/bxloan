package com.coamctech.bxloan.service.freemarker.data;

/**
 * 质押从合同
 *
 */
public class SubContrPledgeInfo extends SubContrBaseInfo {
	/**
	 * 质权人
	 */
	private String pledgePersonName;
	/**
	 * 质押物名称
	 */
	private String pledgeName;

	public String getPledgeName() {
		return pledgeName;
	}

	public void setPledgeName(String pledgeName) {
		this.pledgeName = pledgeName;
	}

	public String getPledgePersonName() {
		return pledgePersonName;
	}

	public void setPledgePersonName(String pledgePersonName) {
		this.pledgePersonName = pledgePersonName;
	}
}
