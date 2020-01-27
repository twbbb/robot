package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatFriendAgreeMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatFriendAgreeMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public void handlerMyMessageSend(SendHandlerContext sendHandlerContext) {
		Map map = sendHandlerContext.getSendParam();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "303");
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, sendHandlerContext.getMessageSend().getLocalRobotId());
		map.put(LovelyCatConstants.MSG_SEND_MSG, sendHandlerContext.getMessageSend().getMessage());// 同步消息事件中群聊邀请原消息

		
		
	}


	@Override
	public String getCheckMsgType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_TYPE_FRIENDAGREE;
	}



}
