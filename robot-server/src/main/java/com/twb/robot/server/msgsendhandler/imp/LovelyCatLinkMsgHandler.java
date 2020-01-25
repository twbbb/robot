package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatLinkMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatLinkMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public Map handlerMyMessageSend() {
		Map linkMap = new HashMap();
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_TEXT, messageSend.getMessage());
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_TITLE, messageSend.getCol1());
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_URL, messageSend.getCol2());
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_PIC, messageSend.getCol3());

		
		Map map = new HashMap();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "107");
		map.put(LovelyCatConstants.MSG_SEND_MSG, linkMap);
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
