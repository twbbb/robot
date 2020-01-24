package com.twb.robot.service.imp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.twb.robot.common.dao.MessageReceiveRepository;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.server.RobotServerManager;
import com.twb.robot.service.RobotMessageService;

@Service
public class RobotMessageServiceImp implements RobotMessageService
{

	private static final Logger logger = LoggerFactory.getLogger(RobotMessageServiceImp.class);

	@Resource
    private MessageReceiveRepository messageReceiveRepository;
	
	
	@Transactional
	public void handlerReceivMsg(Object obj) {
		MessageReceive messageReceive = RobotServerManager.getRobotServer().handlerReceivMsg(obj);
		messageReceiveRepository.save(messageReceive);
	}
	
//	@Override
//	@Transactional
//	public void handlerReceivMsg(Map obj) {
//		String sql = "INSERT student VALUES('2','2','3')";
//		DbServiceUtil.getDbService().excuteUpdate(sql, new String[]{});
//		System.out.println("end1");
//		String sql2 = "select * from student a";
//		List list = DbServiceUtil.getDbService().queryForMapListBySql(sql2, new String[]{});
//		System.out.println(list);
//	}



}
