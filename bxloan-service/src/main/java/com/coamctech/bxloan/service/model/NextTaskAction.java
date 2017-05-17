package com.coamctech.bxloan.service.model;

/**
 * 流程环节操作功能信息
 * @author xc
 */
public class NextTaskAction {
	
	private String actionCode;//：按钮类型（发送/退回/删除）；
	private String actionNameCn;//：按钮中文名称；
	private String actionNameEn;//：按钮ID；
	private String selectActor ;//是否弹出选人窗口（OPEN_WINDOW：打开/SYS_AUTO：不打开）；
	
	/////////////////////
	///GETTERS&SETTERS///
	/////////////////////
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getActionNameCn() {
		return actionNameCn;
	}
	public void setActionNameCn(String actionNameCn) {
		this.actionNameCn = actionNameCn;
	}
	public String getActionNameEn() {
		return actionNameEn;
	}
	public void setActionNameEn(String actionNameEn) {
		this.actionNameEn = actionNameEn;
	}
	public String getSelectActor() {
		return selectActor;
	}
	public void setSelectActor(String selectActor) {
		this.selectActor = selectActor;
	}
	
}

