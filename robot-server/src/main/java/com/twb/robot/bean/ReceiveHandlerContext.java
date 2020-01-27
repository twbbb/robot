package com.twb.robot.bean;

import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.entity.MessageReceive;

public class ReceiveHandlerContext {

	Map receiveParamMap = new HashMap();
	MessageReceive messageReceive = new MessageReceive();
	String type = "";
	String subType = "";
	
	
	
	
	public Map getReceiveParamMap() {
		return receiveParamMap;
	}
	public void setReceiveParamMap(Map receiveParamMap) {
		this.receiveParamMap = receiveParamMap;
	}
	public MessageReceive getMessageReceive() {
		return messageReceive;
	}
	public void setMessageReceive(MessageReceive messageReceive) {
		this.messageReceive = messageReceive;
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
