package com.twb.robot.server.msgsendhandler.imp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatTextGroupNoticeMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatTextGroupNoticeMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}


	@Override
	public String getCheckMsgType() {
		return RobotSendConstants.MSG_TYPE_GROUP_CHAT;
	}


	@Override
	public String getCheckMsgSubType() {
		return RobotSendConstants.MSG_SUBTYPE_NOTICE_TEXT;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map handlerMyMessageSend() {
		Map map = new HashMap();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "102");
		String msg="";
		try {
			msg = URLEncoder.encode(messageSend.getMessage(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("URLEncoder异常："+messageSend.getId(),e);
		} 
		map.put(LovelyCatConstants.MSG_SEND_MSG, msg);
		map.put(LovelyCatConstants.MSG_SEND_TO_WXID, messageSend.getToGroupId());
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, messageSend.getLocalRobotId());

		map.put(LovelyCatConstants.MSG_SEND_AT_WXID, messageSend.getToUserId());
		map.put(LovelyCatConstants.MSG_SEND_AT_NAME, messageSend.getToUserName());

		return map;
	}

}
