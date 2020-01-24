package com.twb.robot.server;

import com.twb.robot.server.imp.LovelyCatRobot;

/**
 * 机器人管理器
 *
 * @author tianwenbin 2020年1月22日
 */
public class RobotServerManager {
	
	public static IRobotServer getRobotServer(){
		return new LovelyCatRobot(); 
	}

}
