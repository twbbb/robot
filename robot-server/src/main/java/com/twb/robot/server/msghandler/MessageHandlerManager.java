package com.twb.robot.server.msghandler;

import com.twb.robot.server.msghandler.imp.LovelyCatImgMsgRecHandler;
import com.twb.robot.server.msghandler.imp.LovelyCatOtherMsgRecHandler;

public class MessageHandlerManager {


	public static IMessageReceiveHandler getMessageReceiveHandler(){
		IMessageReceiveHandler messageReceiveHandler = null ;
		messageReceiveHandler = new LovelyCatOtherMsgRecHandler(messageReceiveHandler);
		messageReceiveHandler = new LovelyCatImgMsgRecHandler(messageReceiveHandler);

		return null;
	}
	
}
