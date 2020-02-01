package com.twb.robot.server.msgrechandler.imp;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.config.RobotRecevieConstants;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.server.msgrechandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;
//@的消息处理
public class LovelyCatGroupNoticeMsgRecHandler extends BaseLovelyCatMsgRecHandler{

	public LovelyCatGroupNoticeMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	@Override	
	public void handlerMyReceivMsg(ReceiveHandlerContext receiveHandlerContext) {
		MessageReceive messageReceive = receiveHandlerContext.getMessageReceive();
		String message = messageReceive.getMessage();
		String robotId = messageReceive.getLocalRobotId();
		String end=robotId+"]";
		int index = message.indexOf(end);
		if(message.startsWith("[@at,")&&index>-1){//是@的消息
			message =message.substring(index+end.length());
			messageReceive.setMsgSubType(RobotRecevieConstants.MSG_SUBTYPE_NOTICE);
			messageReceive.setMessage(message.trim());
		}

		
	}

	@Override
	public boolean checkMyType(ReceiveHandlerContext receiveHandlerContext) {
		if(RobotRecevieConstants.MSG_TYPE_GROUP_CHAT.equals(receiveHandlerContext.getType())&&RobotRecevieConstants.MSG_SUBTYPE_TEXT.equals(receiveHandlerContext.getSubType())){
			return true;
		}
		else{
			return false;
		}
		
	}


}
