package com.twb.robot.task;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.entity.MessageReceiveQueue;
import com.twb.robot.handler.MessageReceiveQueueService;
	
@Component	
public class MessageReceiveQueueTask {

	private static final Logger logger = LoggerFactory.getLogger(MessageReceiveQueueTask.class);

	@Autowired
	MessageReceiveQueueService messageReceiveQueueServiceImp;
	

 	@Scheduled(cron = "0/5 * * * * ?")
	public void getDate() {
 		logger.info("run once");

 		List<Map>  list = messageReceiveQueueServiceImp.getMessageReceiveQueueList();
 		
 		if(list==null || list.isEmpty()){
 			logger.info("empty data , run once end");
			return;
		}
		logger.info("待处理数据条数："+list.size());
		
		for(Map map:list){
			try{
				messageReceiveQueueServiceImp.handlerMessageReceiveQueue(map);
			}catch(Throwable e){
				logger.error(map+"处理失败",e);
				messageReceiveQueueServiceImp.saveMessageReceiveQueueHis(map, RobotCommonConstants.MSG_RECEIVE_QUEUE_FAIL_STATE, "异常："+e.getMessage());
				
				
			}
		}
 		
 		logger.info("run once end");
	}

}
