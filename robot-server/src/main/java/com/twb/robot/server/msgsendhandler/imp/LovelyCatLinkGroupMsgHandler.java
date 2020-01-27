package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatLinkGroupMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatLinkGroupMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public Map handlerMyMessageSend(SendHandlerContext sendHandlerContext) {
		Map linkMap = new HashMap();
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_TEXT, sendHandlerContext.getMessageSend().getMessage());
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_TITLE, sendHandlerContext.getMessageSend().getCol1());
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_URL, sendHandlerContext.getMessageSend().getCol2());
		linkMap.put(LovelyCatConstants.MSG_SEND_LINK_PIC, sendHandlerContext.getMessageSend().getCol3());

		
		Map map = new HashMap();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "107");
		map.put(LovelyCatConstants.MSG_SEND_MSG, linkMap);
		map.put(LovelyCatConstants.MSG_SEND_TO_WXID, sendHandlerContext.getMessageSend().getToGroupId());
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, sendHandlerContext.getMessageSend().getLocalRobotId());

		return map;
	}


	@Override
	public String getCheckMsgType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_TYPE_PRIVATE_CHAT;
	}


	@Override
	public String getCheckMsgSubType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_SUBTYPE_IMG;
	}

}
