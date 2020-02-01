package com.twb.robot.service.imp;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.twb.robot.common.dao.MessageSendRepository;
import com.twb.robot.common.entity.MessageSend;
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
