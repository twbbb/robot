package com.twb.robot.handler.flow;

import java.util.Map;

import com.twb.robot.common.entity.MessageReceiveTacheHandler;

public abstract class BaseMsgRecTacheFlowHandler implements IMsgRecTacheFlowHandler{

	public abstract void doMyHandler(MessageReceiveTacheHandler msgRecTacheHandler, Map param);
	
	@Override
	public void handler(MessageReceiveTacheHandler msgRecTacheHandler, Map param) {
		doMyHandler(msgRecTacheHandler,param);
	}

}
