package com.twb.robot.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twb.robot.service.RobotMessageService;

@RestController
@RequestMapping("/message")
public class MessageController {
	private  Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RobotMessageService robotMessageServiceImp;
	
	@RequestMapping("/reveiveMsg")
	@ResponseBody
	public String reveiveMsg(@RequestBody String body) {
		String returnCode="0";
		Map paramMap = new HashMap();	
		try {
			//body = URLDecoder.decode(body, "UTF-8");
			logger.info("接受消息:"+body);
			robotMessageServiceImp.handlerReceivMsg(body);
			returnCode="1";
		} catch (Throwable e) {
			 logger.error("接收消息处理失败",e);
			 returnCode="2";
		}
		

		return returnCode;
	}

}
