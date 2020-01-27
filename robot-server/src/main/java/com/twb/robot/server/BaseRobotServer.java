package com.twb.robot.server;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageSend;

public abstract class BaseRobotServer  implements IRobotServer{
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public abstract MessageReceive handlerMyReceivMsg(Object obj);
	
	public abstract void handlerMySendMsg(MessageSend messageSend);

	
	public MessageReceive handlerReceivMsg(Object obj){
		return handlerMyReceivMsg(obj);
	}
	
	public void handlerSendMsg(MessageSend messageSend){
		 handlerMySendMsg(messageSend);
	}


	
}
