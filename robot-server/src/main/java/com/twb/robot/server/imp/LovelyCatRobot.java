package com.twb.robot.server.imp;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.server.BaseRobotServer;

/**
 * 可爱猫机器人
 *
 *
 * @author tianwenbin 2020年1月22日
 */
public class LovelyCatRobot extends BaseRobotServer {

	@Override
	public MessageReceive handlerMyReceivMsg(Object obj)  {
		if(obj==null){
			return null;
		}
		Map paramMap = new HashMap();
		String body = (String) obj;
		String[] bodyMap;
		try {
			bodyMap = URLDecoder.decode(body, "UTF-8").split("&");
			for(String str:bodyMap){
				String[] keyMap = str.split("=",-1);
				if(keyMap.length==2){
					paramMap.put(keyMap[0], keyMap[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析错误",e);
		}
		if(paramMap==null||paramMap.isEmpty()){
			return null;
		}
		//type=100&msg_type=1&from_wxid=wxid_sndgxwk6qc1m21&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=田文彬 &final_from_name=田文彬 &robot_wxid=wxid_mzss9j7otj9n22&msg= wer&time=1579851064&rid=10003
		//type=100&msg_type=3&from_wxid=wxid_sndgxwk6qc1m21&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=田文彬 &final_from_name=田文彬 &robot_wxid=wxid_mzss9j7otj9n22&msg=C:\Users\Administrator\Desktop\cat\data\temp\\wxid_mzss9j7otj9n22\1703820541.jpg&file_url=http://114.67.112.14:8073/static/1703820541.jpg&time=1579851536&rid=10004
		//type=200&msg_type=1&from_wxid=23070876266@chatroom&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=测试&final_from_name=田文彬&robot_wxid=wxid_mzss9j7otj9n22&msg=123&time=1579851617&rid=10005
		//type=200&msg_type=3&from_wxid=23070876266@chatroom&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=测试&final_from_name=田文彬&robot_wxid=wxid_mzss9j7otj9n22&msg=C:\Users\Administrator\Desktop\cat\data\temp\\wxid_mzss9j7otj9n22\1703820543.jpg&file_url=http://114.67.112.14:8073/static/1703820543.jpg&time=1579851625&rid=10006
		
		
		MessageReceive messageReceive = new MessageReceive();
		
		
		
		
		
		
		return messageReceive;
	}

	@Override
	public Object handlerMySendMsg(MessageSend messageSend) {

		return null;
	}

	@Override
	public Object sendMyMsg(Object obj) {
		return null;
	}

}
