package com.twb.robot.server.msgsendhandler.imp;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.twb.robot.bean.SendHandlerContext;
import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.RobotServerManager;
import com.twb.robot.server.msgsendhandler.BaseLovelyCatMsgSendHandler;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;

public class LovelyCatGroupInviteMsgHandler extends BaseLovelyCatMsgSendHandler{

	public LovelyCatGroupInviteMsgHandler(IMessageSendHandler messageSendHandler) {
		super.wapper = messageSendHandler;
	}




	@Override
	public void handlerMyMessageSend(SendHandlerContext sendHandlerContext) {
		String groupId = sendHandlerContext.getMessageSend().getToGroupId();
		String  robotId= sendHandlerContext.getMessageSend().getLocalRobotId();
		MessageSend messageSend = new MessageSend();
		messageSend.setMsgType(RobotSendConstants.MSG_TYPE_GROUPMEMBERLIST);
		messageSend.setToGroupId(groupId);
		messageSend.setLocalRobotId(robotId);
		
		RobotServerManager.getRobotServer().handlerSendMsg(messageSend);
		String state = messageSend.getSendState();
		String msg = messageSend.getSendStateMsg();//the content is null
		if(!RobotCommonConstants.MESSAGE_SEND_SUC_STATE.equals(state)){
			return;
		}
		String friendId = sendHandlerContext.getMessageSend().getToUserId();
		JSONArray ja= (JSONArray) JSON.parse(msg);
		for(int i=0;i<ja.size();i++){
			Map map = (Map) ja.get(i);
			String wxId = StringConvertUtils.toString(map.get("wxid"));
			if(friendId.equals(wxId)){
				return;
			}
		}
		
		Map map = sendHandlerContext.getSendParam();
		
		
		map.put(LovelyCatConstants.MSG_SEND_TYPE, "311");
		map.put(LovelyCatConstants.MSG_SEND_ROBOT_WXID, robotId);
		map.put(LovelyCatConstants.MSG_SEND_GROUP_WXID, groupId); 
		map.put(LovelyCatConstants.MSG_SEND_FRIEND_WXID, friendId); 
		
		
		
	}


	@Override
	public String getCheckMsgType(SendHandlerContext sendHandlerContext) {
		return RobotSendConstants.MSG_TYPE_GROUPINVITE;
	}



}
