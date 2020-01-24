package com.twb.robot.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageSend;

public abstract class BaseRobotServer  implements IRobotServer{
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public abstract MessageReceive handlerMyReceivMsg(Object obj);
	
	public abstract Object handlerMySendMsg(MessageSend messageSend);

	public abstract Object sendMyMsg(Object obj);
	
	public MessageReceive handlerReceivMsg(Object obj){
		return handlerMyReceivMsg(obj);
	}
	
	public Object handlerSendMsg(MessageSend messageSend){
		return handlerMySendMsg(messageSend);
	}

	public Object sendMsg(Object obj){
		return sendMyMsg(obj);
			
	}

	
}
