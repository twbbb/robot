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
		String start = "[@at,";
		int endIndex = message.indexOf(end);
		int startIndex = message.indexOf(start);
		if(startIndex>-1&&endIndex>-1){//是@的消息
			String messageStart = message.substring(0,startIndex).trim();
			String messageEnd =message.substring(endIndex+end.length()).trim();
			messageReceive.setMsgSubType(RobotRecevieConstants.MSG_SUBTYPE_NOTICE);
			messageReceive.setMessage(messageStart+messageEnd);
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
