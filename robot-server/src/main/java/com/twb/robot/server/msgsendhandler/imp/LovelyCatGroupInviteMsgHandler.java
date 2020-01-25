package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatGroupInviteMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatGroupInviteMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public Map handlerMyMessageSend() {
		Map map = new HashMap();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "311");
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, messageSend.getLocalRobotId());
		map.put(LovelyCatConstants.MSG_SEND_GROUP_WXID, messageSend.getToGroupId()); 
		map.put(LovelyCatConstants.MSG_SEND_FRIEND_WXID, messageSend.getToUserId()); 
		
		
		return map;
	}


	@Override
	public String getCheckMsgType() {
		return RobotSendConstants.MSG_TYPE_GROUPINVITE;
	}



}
