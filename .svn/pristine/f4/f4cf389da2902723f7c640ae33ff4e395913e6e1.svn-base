package com.coamctech.bxloan.service.model.dashboard;

import com.coamctech.bxloan.commons.entity.BaseEntity;

public class MessageDisplayVo extends BaseEntity{
	
	private String id;
	private String senderName;
	private String sendTime;
	private String content;
	
	public MessageDisplayVo(Object[] input) {
		int index = 0;
		this.setId(str(input[index++]));
		this.setSenderName(str(input[index++]));
		this.setSendTime(str(input[index++]));
		this.setContent(str(input[index++]));
	}
	
	private String str(Object ori){
		return ori==null?"":ori.toString().trim();
	}
	////////////////////////
	////GETTERS&SETTERS/////
	///////////////////////
	public String getContent() {
		return content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
}
