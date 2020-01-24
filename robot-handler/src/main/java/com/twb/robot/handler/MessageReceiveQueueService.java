package com.twb.robot.handler;

import java.util.List;
import java.util.Map;

public interface MessageReceiveQueueService {
	
	public List<Map> getMessageReceiveQueueList();
	public void handlerMessageReceiveQueue(Map map);
	public void saveMessageReceiveQueueHis(Map map,String state,String stateMsg);

}
