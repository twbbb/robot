package com.twb.robot.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.service.MessageSendService;
import com.twb.robot.service.RobotMessageService;

@Component
public class SendMsgTask {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RobotMessageService robotMessageServiceImp;

	@Autowired
	MessageSendService messageSendServiceImp;

	@Scheduled(cron = "0/2 * * * * ?")
	public void handlerSendDate() {

		List<MessageSend> list = messageSendServiceImp.getSendMsg();

		logger.info("待发送消息条数:" + list.size());
		for (MessageSend messageSend : list) {
			try {
				robotMessageServiceImp.handlerSendMsg(messageSend);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("发送消息异常",e);
			}
		}

		// System.out.println("end");
	}

}
