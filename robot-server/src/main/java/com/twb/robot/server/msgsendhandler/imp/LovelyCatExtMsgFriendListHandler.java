package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatExtMsgFriendListHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatExtMsgFriendListHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public Map handlerMyMessageSend(SendHandlerContext sendHandlerContext) {
		Map map = new HashMap();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "204");
		String isRefresh=LovelyCatConstants.MSG_SEND_IS_REFRESH_Y;
		if(!StringUtils.isEmpty(sendHandlerContext.getMessageSend().getCol1())){
			isRefresh=sendHandlerContext.getMessageSend().getCol1();
		}
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, sendHandlerContext.getMessageSend().getLocalRobotId());
		map.put(LovelyCatConstants.MSG_SEND_IS_REFRESH, isRefresh);

		
		return map;
	}


	@Override
	public String getCheckMsgType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_TYPE_FRIENDLIST;
	}



}
