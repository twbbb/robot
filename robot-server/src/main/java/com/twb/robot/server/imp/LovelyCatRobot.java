package com.twb.robot.server.imp;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.server.BaseRobotServer;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;
import com.twb.robot.server.msgrechandler.MessageRecHandlerManager;
import com.twb.robot.server.msgsendhandler.IMessageSendHandler;
import com.twb.robot.server.msgsendhandler.MessageSendHandlerManager;
import com.twb.robot.utils.LovelyCatRobotHttpUtils;

/**
 * 可爱猫机器人
 *
 *
 * @author tianwenbin 2020年1月22日	
 */
public class LovelyCatRobot extends BaseRobotServer {

	@Override
	public MessageReceive handlerMyReceivMsg(Object obj)  {
		if(obj==null){
			return null;
		}
		Map paramMap = new HashMap();
		String body = (String) obj;
		String[] bodyMap;
		try {
			bodyMap = URLDecoder.decode(body, "UTF-8").split("&");
			for(String str:bodyMap){
				String[] keyMap = str.split("=",-1);
				if(keyMap.length==2){
					paramMap.put(keyMap[0], keyMap[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析错误",e);
		}
		if(paramMap==null||paramMap.isEmpty()){
			return null;
		}

		IMessageReceiveHandler messageReceiveHandler = MessageRecHandlerManager.getMessageReceiveHandler();
		messageReceiveHandler.init(paramMap);
		MessageReceive messageReceive = messageReceiveHandler.handlerReceivMsg();
		
		
			
		
		return messageReceive;
	}

	@Override
	public Map handlerMySendMsg(MessageSend messageSend) {
		if(messageSend==null){
			return null;
		}
		
		IMessageSendHandler messageSendHandler = MessageSendHandlerManager.getMessageSendHandler();
		messageSendHandler.init(messageSend);
		Map sendParam =  (Map) messageSendHandler.handlerMessageSend();
		Map resultMap = LovelyCatRobotHttpUtils.sendMsg(sendParam);
		if(checkSuccess(resultMap)){
			messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_SUC_STATE);

		}else{
			messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_FAIL_STATE);

		}
		return resultMap;
		
	}
	
	public  boolean checkSuccess(Map map){
		if(map==null||map.isEmpty()){
			return false;
		}
		String code = StringConvertUtils.toString(map.get("code"));
		
		if("0".equals(code)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Map sendMyMsg(Object obj) {
		return null;
	}

}
