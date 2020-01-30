package com.twb.robot.service.imp;

import java.util.Date;
import java.util.List;

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
import com.twb.robot.service.MessageSendService;

@Service
public class MessageSendServiceImp implements MessageSendService
{

	private static final Logger logger = LoggerFactory.getLogger(MessageSendServiceImp.class);

	@Resource
    private MessageSendRepository messageSendRepository;

	@Override
	@Transactional
	public List<MessageSend> getSendMsg() {
		return messageSendRepository.getMessageSendList();
	}
	
	


}
