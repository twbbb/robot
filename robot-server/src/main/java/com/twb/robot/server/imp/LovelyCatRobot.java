package com.twb.robot.server.imp;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.server.BaseRobotServer;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;
import com.twb.robot.server.msgrechandler.MessageRecHandlerManager;

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
	public Object handlerMySendMsg(MessageSend messageSend) {

		return null;
	}

	@Override
	public Object sendMyMsg(Object obj) {
		return null;
	}

}
