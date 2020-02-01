package com.twb.robot.service;

import com.twb.robot.common.entity.MessageSend;

public interface RobotMessageService {
	
	
	void handlerReceivMsg(Object obj);	
	
	void handlerSendMsg(MessageSend messageSend);	
	
	/**
	 * 
	 * @Title: getAllGroup   
	 * @Description: 获取所有群组
	 * @param:       
	 * @return: void      
	 * @throws
	 */
	public void getAllGroup();


}
