package com.twb.robot.server.msgrechandler;

import com.twb.robot.server.msgrechandler.imp.LovelyCatImgMsgRecHandler;
import com.twb.robot.server.msgrechandler.imp.LovelyCatOtherMsgRecHandler;

public class MessageRecHandlerManager {


	public static IMessageReceiveHandler getMessageReceiveHandler(){
		IMessageReceiveHandler messageReceiveHandler = null ;
		messageReceiveHandler = new LovelyCatOtherMsgRecHandler(messageReceiveHandler);
		messageReceiveHandler = new LovelyCatImgMsgRecHandler(messageReceiveHandler);

		return messageReceiveHandler;
	}
	
}
