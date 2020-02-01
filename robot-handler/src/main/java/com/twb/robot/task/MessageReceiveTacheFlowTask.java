package com.twb.robot.task;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.entity.MessageReceiveTacheFlow;
import com.twb.robot.handler.MessageReceiveTacheHandlerService;
	
@Component	
public class MessageReceiveTacheFlowTask {

	private static final Logger logger = LoggerFactory.getLogger(MessageReceiveTacheFlowTask.class);

	@Autowired
	MessageReceiveTacheHandlerService messageReceiveTacheHandlerServiceImp;
	

 	@Scheduled(cron = "0/5 * * * * ?")
	public void handlerTacheFlowData() {
 		logger.info("run once");

 		List<MessageReceiveTacheFlow>  list = messageReceiveTacheHandlerServiceImp.getMessageReceiveTacheFlow();
 		
 		if(list==null || list.isEmpty()){
 			logger.info("empty data , run once end");
			return;
		}
		logger.info("待处理数据条数："+list.size());
		
		for(MessageReceiveTacheFlow messageReceiveTacheFlow:list){
			try{
				messageReceiveTacheHandlerServiceImp.handlerMessageReceiveTacheFlow(messageReceiveTacheFlow);
			}catch(Throwable e){
				logger.error(messageReceiveTacheFlow.getId()+",处理失败",e);
				messageReceiveTacheFlow.setState(RobotCommonConstants.MESSAGE_RECEIVE_FLOW_FAIL_STATE);
				messageReceiveTacheFlow.setStateMsg(e.getMessage());
				messageReceiveTacheHandlerServiceImp.saveHis(messageReceiveTacheFlow);
				
			}
		}
 		
 		logger.info("run once end");
	}

}
