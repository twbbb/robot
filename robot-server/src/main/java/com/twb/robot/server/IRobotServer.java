package com.twb.robot.server;

import java.util.Map;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageSend;

public interface IRobotServer {

	/**
	 * 将机器人获取数据转换为MessageReceive
	 * @param obj
	 * @return
	 */
	public MessageReceive handlerReceivMsg(Object obj);
	
	/**
	 * 发送数据
	 * @param handlerSendMsg
	 * @return
	 */
	public void handlerSendMsg(MessageSend messageSend);


	
}
