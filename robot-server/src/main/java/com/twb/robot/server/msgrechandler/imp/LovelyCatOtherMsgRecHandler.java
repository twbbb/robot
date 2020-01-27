package com.twb.robot.server.msgrechandler.imp;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.server.msgrechandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;

public class LovelyCatOtherMsgRecHandler extends BaseLovelyCatMsgRecHandler{

	public LovelyCatOtherMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	@Override	
	public void handlerMyReceivMsg(ReceiveHandlerContext receiveHandlerContext) {
	}

	@Override
	public boolean checkMyType(ReceiveHandlerContext receiveHandlerContext) {
		return true;
	}

}
