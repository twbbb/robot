package com.twb.robot.server.msgrechandler.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.config.RobotRecevieConstants;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.server.msgrechandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;
//新成员入群
public class LovelyCatGroupNewMemberMsgRecHandler extends BaseLovelyCatMsgRecHandler{
	private static final Logger logger = LoggerFactory.getLogger(LovelyCatGroupNewMemberMsgRecHandler.class);

	public LovelyCatGroupNewMemberMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	@Override	
	public void handlerMyReceivMsg(ReceiveHandlerContext receiveHandlerContext) {
		MessageReceive messageReceive = receiveHandlerContext.getMessageReceive();
		String message = messageReceive.getMessage();
		try {
			Map msgMap = (Map) JSON.parse(message);
			List list = (List) msgMap.get("guest");
			Map map = (Map) list.get(0);
			String newMemberId=  (String) map.get("wxid");
			String newMemberName = (String) map.get("nickname");
			if(!StringUtils.isEmpty(newMemberId)&&!StringUtils.isEmpty(newMemberName)){
				messageReceive.setCol1(newMemberId);
				messageReceive.setCol2(newMemberName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("新人入群异常",e);
		}
	}

	@Override
	public boolean checkMyType(ReceiveHandlerContext receiveHandlerContext) {
		if(RobotRecevieConstants.MSG_TYPE_ADD_MERBER.equals(receiveHandlerContext.getType())&&RobotRecevieConstants.MSG_SUBTYPE_GROUP_ADDMERBER.equals(receiveHandlerContext.getSubType())){
			return true;
		}
		else{
			return false;
		}
		
	}


}
