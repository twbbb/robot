package com.twb.robot.service.imp;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.dao.MessageReceiveRepository;
import com.twb.robot.common.dao.MsgReceiveQueueRepository;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageReceiveQueue;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.server.RobotServerManager;
import com.twb.robot.service.RobotMessageService;

@Service
public class RobotMessageServiceImp implements RobotMessageService
{

	private static final Logger logger = LoggerFactory.getLogger(RobotMessageServiceImp.class);

	@Resource
    private MessageReceiveRepository messageReceiveRepository;
	
	@Resource
    private MsgReceiveQueueRepository msgReceiveQueueRepository;
	
	
	
	@Transactional
	public void handlerReceivMsg(Object obj) {
		MessageReceive messageReceive = RobotServerManager.getRobotServer().handlerReceivMsg(obj);
		if(messageReceive==null||StringUtils.isEmpty(messageReceive.getMsgId())){
			return;
		}
		messageReceiveRepository.save(messageReceive);
		
		MessageReceiveQueue messageReceiveQueue = new MessageReceiveQueue();
		messageReceiveQueue.setCreateDate(new Date());
		messageReceiveQueue.setMessageReceiveId(messageReceive.getId());
		messageReceiveQueue.setLocalRobotId(messageReceive.getLocalRobotId());
		messageReceiveQueue.setState(RobotCommonConstants.MSG_RECEIVE_QUEUE_BEGIN_STATE);
		
		msgReceiveQueueRepository.save(messageReceiveQueue);
	}



	@Transactional
	public void handlerSendMsg(MessageSend messageSend) {
		
	}
	



}
