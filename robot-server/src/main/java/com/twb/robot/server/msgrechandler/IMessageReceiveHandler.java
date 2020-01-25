package com.twb.robot.server.msgrechandler;

import com.twb.robot.common.entity.MessageReceive;

public interface IMessageReceiveHandler {

	public void init(Object obj);
	
	public  MessageReceive handlerReceivMsg();
	
	public boolean checkType();
}
