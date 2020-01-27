package com.twb.robot.server.msgsendhandler;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.entity.MessageSend;

public abstract class BaseLovelyCatMsgSendHandler implements IMessageSendHandler {

	public IMessageSendHandler wapper;

	
	public abstract void handlerMyMessageSend(SendHandlerContext sendHandlerContext);
	public abstract String getCheckMsgType(SendHandlerContext sendHandlerContext);
	public String getCheckMsgSubType(SendHandlerContext sendHandlerContext){
		return "";
	}

	public boolean checkMyType(SendHandlerContext sendHandlerContext){
		if(!getCheckMsgType(sendHandlerContext).equals(sendHandlerContext.getType())){
			return false;
		}
		if(!StringUtils.isEmpty(getCheckMsgSubType(sendHandlerContext))&&!getCheckMsgSubType(sendHandlerContext).equals(sendHandlerContext.getSubType())){
			return false;
		}
		
		
		return true;
		
	}
	
	@Override
	public void init(SendHandlerContext sendHandlerContext) {
		if(sendHandlerContext.getMessageSend()==null){
			return;
		}
		MessageSend messageSend = sendHandlerContext.getMessageSend();
		String type = messageSend.getMsgType();
		String subType =messageSend.getMsgSubType();
		sendHandlerContext.setType(type);
		sendHandlerContext.setSubType(subType);
		
	}


	public boolean checkType(SendHandlerContext sendHandlerContext) {
		return checkMyType(sendHandlerContext);
	}

	public void handlerMessageSend(SendHandlerContext sendHandlerContext) {
		if(checkType(sendHandlerContext)){
			 handlerMyMessageSend(sendHandlerContext);
		}else{
			if(wapper!=null){
				 wapper.handlerMessageSend(sendHandlerContext);
			} 
		}
	}



	
	

}
