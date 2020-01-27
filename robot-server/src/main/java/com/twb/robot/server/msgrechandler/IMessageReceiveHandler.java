package com.twb.robot.server.msgrechandler;

import com.twb.robot.bean.ReceiveHandlerContext;

public interface IMessageReceiveHandler {

	public void init(ReceiveHandlerContext receiveHandlerContext);
	
	public  void handlerReceivMsg(ReceiveHandlerContext receiveHandlerContext);
	
	public boolean checkType(ReceiveHandlerContext receiveHandlerContext);
}
