package com.twb.robot.server.msgrechandler;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.entity.MessageReceive;

public interface IMessageReceiveHandler {

	public void init(ReceiveHandlerContext receiveHandlerContext);
	
	public  MessageReceive handlerReceivMsg(ReceiveHandlerContext receiveHandlerContext);
	
	public boolean checkType(ReceiveHandlerContext receiveHandlerContext);
}
