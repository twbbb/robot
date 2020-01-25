package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatExtMsgGroupListHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatExtMsgGroupListHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public Map handlerMyMessageSend() {
		Map map = new HashMap();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "205");
		String isRefresh=LovelyCatConstants.MSG_SEND_IS_REFRESH_Y;
		if(!StringUtils.isEmpty(messageSend.getCol1())){
			isRefresh=messageSend.getCol1();
		}
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, messageSend.getLocalRobotId());
		map.put(LovelyCatConstants.MSG_SEND_IS_REFRESH, isRefresh);

		
		return map;
	}


	@Override
	public String getCheckMsgType() {
		return RobotSendConstants.MSG_TYPE_GROUPLIST;
	}



}
