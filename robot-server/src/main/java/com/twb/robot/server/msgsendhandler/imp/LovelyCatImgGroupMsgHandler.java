package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatImgGroupMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatImgGroupMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public void handlerMyMessageSend(SendHandlerContext sendHandlerContext) {
		Map map = sendHandlerContext.getSendParam();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "103");
		map.put(LovelyCatConstants.MSG_SEND_MSG, sendHandlerContext.getMessageSend().getMessage());
		map.put(LovelyCatConstants.MSG_SEND_TO_WXID, sendHandlerContext.getMessageSend().getToGroupId());
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, sendHandlerContext.getMessageSend().getLocalRobotId());

		
	}


	@Override
	public String getCheckMsgType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_TYPE_GROUP_CHAT;
	}


	@Override
	public String getCheckMsgSubType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_SUBTYPE_IMG;
	}

}
