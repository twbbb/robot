package com.twb.robot.server.msgrechandler.imp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.config.RobotRecevieConstants;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.server.msgrechandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;

public class LovelyCatGroupInviceMsgRecHandler extends BaseLovelyCatMsgRecHandler{

	public LovelyCatGroupInviceMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	
	@Override	
	public void handlerMyReceivMsg(ReceiveHandlerContext receiveHandlerContext) {
		MessageReceive messageReceive =  receiveHandlerContext.getMessageReceive();
		try {
			messageReceive.setMessage(URLDecoder.decode(messageReceive.getMessage(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("decode异常:"+messageReceive.getMessage());
		}
	}

	@Override
	public boolean checkMyType(ReceiveHandlerContext receiveHandlerContext) {
		if(RobotRecevieConstants.MSG_SUBTYPE_INVITE.equals(receiveHandlerContext.getSubType())){
			return true;
		}
		else{
			return false;
		}
		
	}

}
