package com.twb.robot.server.msghandler.imp;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.server.msghandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msghandler.IMessageReceiveHandler;

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
