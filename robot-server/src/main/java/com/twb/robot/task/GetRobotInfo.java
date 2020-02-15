package com.twb.robot.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.twb.robot.service.RobotMessageService;
@Component	
public class GetRobotInfo {

	@Autowired
	RobotMessageService robotMessageServiceImp;
	
	@Scheduled(cron = "30 0 0 * * ? ")
	//获取所有群组
	public void getAllGroup() {
		try{
			robotMessageServiceImp.getAllGroup();
		}catch(Exception e){
			e.printStackTrace();
		}
		}
}
