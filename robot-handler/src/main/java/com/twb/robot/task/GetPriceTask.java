package com.twb.robot.task;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.twb.robot.handler.QadataService;



@Component
public class GetPriceTask {
	Logger logger = LoggerFactory.getLogger(GetPriceTask.class);

	public static double TEMP_WAVE=0;
	@Autowired
	QadataService qadataServiceImp;
	
//	@Scheduled(cron = "0 */2 * * * ?")
	@Scheduled(cron = "0 * * * * ?")
	public void getDate() {

		logger.info("GetPriceTask.getDate start");
		
		try {
			qadataServiceImp.updateSwtcPrice();
			
		} catch (Exception e) {
			logger.error("报价更新失败",e);
			e.printStackTrace();
		}

		logger.info("GetPriceTask.getDate end");

	}
}
