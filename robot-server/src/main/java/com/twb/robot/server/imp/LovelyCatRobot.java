package com.twb.robot.server.imp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.bean.SendHandlerContext;
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
			
			bodyMap =body.split("&");
			for(String str:bodyMap){
				int index = str.indexOf("=");
				if(index>-1){
					String key = str.substring(0,index);
					String value = str.substring(index+1);
					paramMap.put(key, URLDecoder.decode(value, "UTF-8"));
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
		ReceiveHandlerContext receiveHandlerContext = new ReceiveHandlerContext();
		receiveHandlerContext.setReceiveParamMap(paramMap);
		messageReceiveHandler.init(receiveHandlerContext);
		messageReceiveHandler.handlerReceivMsg(receiveHandlerContext);
		
		
			
		
		return receiveHandlerContext.getMessageReceive();
	}

	@Override
	public void handlerMySendMsg(MessageSend messageSend) {
		if(messageSend==null){
			return ;
		}
		
		IMessageSendHandler messageSendHandler = MessageSendHandlerManager.getMessageSendHandler();
		SendHandlerContext sendHandlerContext = new SendHandlerContext();
		sendHandlerContext.setMessageSend(messageSend);
		messageSendHandler.init(sendHandlerContext);
		messageSendHandler.handlerMessageSend(sendHandlerContext);
		if(sendHandlerContext.getSendParam()==null||sendHandlerContext.getSendParam().isEmpty()){
			messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_FAIL_STATE);
			messageSend.setSendStateMsg("发送数据转换为空:"+messageSend.getId());
			return;
		}
		
		Map resultMap = LovelyCatRobotHttpUtils.sendMsg(sendHandlerContext.getSendParam());
		if(checkSuccess(resultMap)){
			String str = StringConvertUtils.toString(resultMap.get("data"));
			try {
				str	= URLDecoder.decode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_SUC_STATE);
			messageSend.setMessage(str);
		}else{
			messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_FAIL_STATE);

		}
		
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


}
