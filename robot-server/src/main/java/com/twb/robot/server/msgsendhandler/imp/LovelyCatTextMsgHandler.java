package com.twb.robot.server.msgsendhandler.imp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatTextMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatTextMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}


	@Override
	public String getCheckMsgType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_TYPE_PRIVATE_CHAT;
	}


	@Override
	public String getCheckMsgSubType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_SUBTYPE_TEXT;
	}


	@Override
	public void handlerMyMessageSend(SendHandlerContext sendHandlerContext) {
		Map map = sendHandlerContext.getSendParam();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "100");
		String msg="";
		try {
			msg = URLEncoder.encode(sendHandlerContext.getMessageSend().getMessage(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("URLEncoder异常："+sendHandlerContext.getMessageSend().getId(),e);
		} 
		map.put(LovelyCatConstants.MSG_SEND_MSG, msg);
		map.put(LovelyCatConstants.MSG_SEND_TO_WXID, sendHandlerContext.getMessageSend().getToUserId());
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, sendHandlerContext.getMessageSend().getLocalRobotId());

		
	}

}
