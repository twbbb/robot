package com.twb.robot.server.msgrechandler.imp;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.server.msgrechandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;

public class LovelyCatOtherMsgRecHandler extends BaseLovelyCatMsgRecHandler{

	public LovelyCatOtherMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	@Override	
	public MessageReceive handlerMyReceivMsg() {
		return this.getMessageReceive();
	}

	@Override
	public boolean checkMyType() {
		return true;
	}

}
