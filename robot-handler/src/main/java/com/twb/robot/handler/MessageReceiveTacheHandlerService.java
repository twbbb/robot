package com.twb.robot.handler;

import java.util.List;

import com.twb.robot.common.entity.MessageReceiveTacheFlow;

public interface MessageReceiveTacheHandlerService {
	
	public void handlerMessageReceiveTacheFlow(MessageReceiveTacheFlow messageReceiveTacheFlow);

	public List<MessageReceiveTacheFlow> getMessageReceiveTacheFlow();
	
	
	
	public void saveHis(MessageReceiveTacheFlow messageReceiveTacheFlow);
}
