package com.twb.robot.server;

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
	 * 将待发送数据转换为机器人服务需要
	 * @param messageSend
	 * @return
	 */
	public Object handlerSendMsg(MessageSend messageSend);

	/**
	 * 发送数据
	 * @param messageSend
	 * @return
	 */
	public Object sendMsg(Object obj);

	
}
