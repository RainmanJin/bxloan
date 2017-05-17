package com.coamctech.bxloan.service.model.statistics;

import java.util.List;

public class BizWfItemVo {
	private int seqNum;
	private List<BizWfNodeVo> wfNodes;
	public List<BizWfNodeVo> getWfNodes() {
		return wfNodes;
	}
	public void setWfNodes(List<BizWfNodeVo> wfNodes) {
		this.wfNodes = wfNodes;
	}
	public int getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}
}
