package com.twb.robot.bean;

import com.twb.robot.common.entity.MessageSend;

public class SendHandlerContext {

	protected MessageSend messageSend = new MessageSend();
	protected String type = "";
	protected String subType = "";
	public MessageSend getMessageSend() {
		return messageSend;
	}
	public void setMessageSend(MessageSend messageSend) {
		this.messageSend = messageSend;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	
	
}
