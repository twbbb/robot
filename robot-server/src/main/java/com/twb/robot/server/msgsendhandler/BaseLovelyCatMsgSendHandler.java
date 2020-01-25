package com.twb.robot.server.msgsendhandler;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.common.entity.MessageSend;

public abstract class BaseLovelyCatMsgSendHandler implements IMessageSendHandler {

	public IMessageSendHandler wapper;
	protected MessageSend messageSend = new MessageSend();
	protected String type = "";
	protected String subType = "";
	
	public abstract Map handlerMyMessageSend();
	public abstract String getCheckMsgType();
	public String getCheckMsgSubType(){
		return "";
	}

	public boolean checkMyType(){
		if(!getCheckMsgType().equals(getType())){
			return false;
		}
		if(!StringUtils.isEmpty(getCheckMsgSubType())&&!getCheckMsgSubType().equals(getSubType())){
			return false;
		}
		
		
		return true;
		
	}
	
	@Override
	public void init(MessageSend messageSend) {
		if(messageSend==null){
			return;
		}
		type = messageSend.getMsgType();
		subType =messageSend.getMsgSubType();
		
	}


	public boolean checkType() {
		return checkMyType();
	}

	public Object handlerMessageSend() {
		if(checkType()){
			return handlerMyMessageSend();
		}else{
			if(wapper!=null){
				wapper.init(messageSend);
				return wapper.handlerMessageSend();
			} 
		}
		return null;
	}
	public IMessageSendHandler getWapper() {
		return wapper;
	}
	public void setWapper(IMessageSendHandler wapper) {
		this.wapper = wapper;
	}
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
