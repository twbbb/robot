package com.twb.robot.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.service.MessageSendService;
import com.twb.robot.service.RobotMessageService;

@Component	
public class SendMsgTask {

	@Autowired
	RobotMessageService robotMessageServiceImp;
	
	@Autowired
	MessageSendService messageSendServiceImp;
	@Scheduled(cron = "0/5 * * * * ?")
	public void handlerSendDate() {
		
		List<MessageSend> list = messageSendServiceImp.getSendMsg();
		try {
			for(MessageSend messageSend: list){
				robotMessageServiceImp.handlerSendMsg(messageSend);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("end");
	}

}
