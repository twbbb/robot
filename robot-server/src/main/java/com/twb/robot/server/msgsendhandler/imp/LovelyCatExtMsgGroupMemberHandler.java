package com.twb.robot.server.msgsendhandler.imp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatExtMsgGroupMemberHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatExtMsgGroupMemberHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public void handlerMyMessageSend(SendHandlerContext sendHandlerContext) {
		Map map = sendHandlerContext.getSendParam();
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "206");
		String isRefresh=LovelyCatConstants.MSG_SEND_IS_REFRESH_N;
		if(!StringUtils.isEmpty(sendHandlerContext.getMessageSend().getCol1())){
			isRefresh=sendHandlerContext.getMessageSend().getCol1();
		}
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, sendHandlerContext.getMessageSend().getLocalRobotId());
		map.put(LovelyCatConstants.MSG_SEND_IS_REFRESH, isRefresh);
		map.put(LovelyCatConstants.MSG_SEND_GROUP_WXID, sendHandlerContext.getMessageSend().getToGroupId());

		

		
		
	}


	@Override
	public String getCheckMsgType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_TYPE_GROUPMEMBERLIST;
	}



}
