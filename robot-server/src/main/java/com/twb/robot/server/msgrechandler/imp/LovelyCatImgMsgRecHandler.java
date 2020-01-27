package com.twb.robot.server.msgrechandler.imp;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.config.RobotRecevieConstants;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.server.msgrechandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;

public class LovelyCatImgMsgRecHandler extends BaseLovelyCatMsgRecHandler{

	public LovelyCatImgMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	
	@Override	
	public MessageReceive handlerMyReceivMsg(ReceiveHandlerContext receiveHandlerContext) {
		MessageReceive messageReceive =  receiveHandlerContext.getMessageReceive();
		messageReceive.setCol1(StringConvertUtils.toString(receiveHandlerContext.getReceiveParamMap().get("file_url")));
		return messageReceive;
	}

	@Override
	public boolean checkMyType(ReceiveHandlerContext receiveHandlerContext) {
		if(RobotRecevieConstants.MSG_SUBTYPE_IMG.equals(receiveHandlerContext.getSubType())){
			return true;
		}
		else{
			return false;
		}
		
	}

}
