package com.twb.robot.common.utils;

import com.twb.robot.common.datasource.service.DbService;
import com.twb.robot.common.utils.SpringUtil;

public class DbServiceUtil {

	
		
	public static DbService getDbService(){
		
		return SpringUtil.getBean(DbService.class); 
	}
	
	
}
