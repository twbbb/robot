package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatImgMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatImgMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public Map handlerMyMessageSend() {
		Map map = new HashMap();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "103");
		map.put(LovelyCatConstants.MSG_SEND_MSG, messageSend.getMessage());
		map.put(LovelyCatConstants.MSG_SEND_TO_WXID, messageSend.getToUserId());
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, messageSend.getLocalRobotId());

		return map;
	}


	@Override
	public String getCheckMsgType() {
		return RobotSendConstants.MSG_TYPE_PRIVATE_CHAT;
	}


	@Override
	public String getCheckMsgSubType() {
		return RobotSendConstants.MSG_SUBTYPE_IMG;
	}

}
