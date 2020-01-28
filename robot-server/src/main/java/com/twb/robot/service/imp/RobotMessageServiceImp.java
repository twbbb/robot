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
import com.twb.robot.common.dao.MessageSendHisRepository;
import com.twb.robot.common.dao.MessageSendRepository;
import com.twb.robot.common.dao.MsgReceiveQueueRepository;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageReceiveQueue;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.common.entity.MessageSendHis;
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
	
	@Resource
    private MessageSendRepository messageSendRepository;
	@Resource
    private MessageSendHisRepository messageSendHisRepository;
	
	
	
	
	
	
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
		try {
			RobotServerManager.getRobotServer().handlerSendMsg(messageSend);
		} catch (Exception e) {
			messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_FAIL_STATE);
			messageSend.setSendStateMsg("异常："+e.getMessage());
			e.printStackTrace();
		}
		if(RobotCommonConstants.MESSAGE_SEND_SUC_STATE.equals(messageSend.getSendState())){
			MessageSendHis messageSendHis = new MessageSendHis();
			messageSendHis.setCol1(messageSend.getCol1());
			messageSendHis.setCol2(messageSend.getCol2());
			messageSendHis.setCol3(messageSend.getCol3());
			messageSendHis.setCreateDate(messageSend.getCreateDate());
			messageSendHis.setId(messageSend.getId());
			messageSendHis.setLocalRobotId(messageSend.getLocalRobotId());
			messageSendHis.setMessage(messageSend.getMessage());
			messageSendHis.setMsgSubType(messageSend.getMsgSubType());
			messageSendHis.setMsgType(messageSend.getMsgType());
			messageSendHis.setSendState(messageSend.getSendState());
			messageSendHis.setSendStateMsg(messageSend.getSendStateMsg());
			messageSendHis.setToGroupId(messageSend.getToGroupId());
			messageSendHis.setToUserId(messageSend.getToUserId());
			messageSendHis.setToGroupName(messageSend.getToGroupName());
			messageSendHis.setToUserName(messageSend.getToUserName());
			messageSendHis.setUpdateDate(new Date());
			messageSendHis.setBusCode(messageSend.getBusCode());
			
			messageSendRepository.delete(messageSend);
			messageSendHisRepository.save(messageSendHis);
		}else{
			messageSend.setUpdateDate(new Date());
			messageSendRepository.save(messageSend);

		}
	}
	



}
