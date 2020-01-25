package com.twb.robot.server.msgsendhandler;

import com.twb.robot.common.entity.MessageSend;

public interface IMessageSendHandler {

	public void init(MessageSend messageSend);
	
	public  Object handlerMessageSend();
	
	public boolean checkType();
}
