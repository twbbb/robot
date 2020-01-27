package com.twb.robot.server.msgsendhandler;

import com.twb.robot.bean.SendHandlerContext;

public interface IMessageSendHandler {

	public void init(SendHandlerContext sendHandlerContext);
	
	public  void handlerMessageSend(SendHandlerContext sendHandlerContext);
	
	public boolean checkType(SendHandlerContext sendHandlerContext);
}
