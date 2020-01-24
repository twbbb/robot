package com.twb.robot.server.msghandler.imp;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.config.LovelyCatRobotConstants;
import com.twb.robot.server.msghandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msghandler.IMessageReceiveHandler;

public class LovelyCatImgMsgRecHandler extends BaseLovelyCatMsgRecHandler{

	public LovelyCatImgMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	
	@Override	
	public MessageReceive handlerMyReceivMsg() {
		return this.getMessageReceive();
	}

	@Override
	public boolean checkMyType() {
		if(LovelyCatRobotConstants.MSG_SUBTYPE_IMG.equals(this.getSubType())){
			return true;
		}
		else{
			return false;
		}
		
	}

}
