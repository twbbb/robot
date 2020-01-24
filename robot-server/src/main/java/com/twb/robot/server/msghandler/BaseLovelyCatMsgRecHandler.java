package com.twb.robot.server.msghandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.utils.StringConvertUtils;

public abstract class BaseLovelyCatMsgRecHandler implements IMessageReceiveHandler {

	public IMessageReceiveHandler wapper;
	Map paramMap = new HashMap();
	MessageReceive messageReceive = new MessageReceive();
	String type = "";
	String subType = "";
	
	public abstract MessageReceive handlerMyReceivMsg();
	public abstract boolean checkMyType();
	
	@Override
	public void init(Object obj) {
		if(obj==null||!(obj instanceof Map)){
			return;
		}
		this.paramMap =(Map) obj;
		type = StringConvertUtils.toString(paramMap.get("type"));
		subType = StringConvertUtils.toString(paramMap.get("msg_type"));
		
	}
	
	public void initMessageReceive(){
		if(paramMap==null||paramMap.isEmpty()){
			return;
		}		
		String time = StringConvertUtils.toString(paramMap.get("time"));
		String localRobotId = StringConvertUtils.toString(paramMap.get("robot_wxid"));
		String rid = StringConvertUtils.toString(paramMap.get("rid"));
		String from_wxid = StringConvertUtils.toString(paramMap.get("from_wxid"));
		String from_name = StringConvertUtils.toString(paramMap.get("from_name"));

		
		messageReceive.setFromGroupId(from_wxid);
		messageReceive.setWxgroupName(from_name); 
		messageReceive.setFromUserId(StringConvertUtils.toString(paramMap.get("final_from_wxid")));
		messageReceive.setFromUserName(StringConvertUtils.toString(paramMap.get("final_from_name")));
		messageReceive.setLocalRobotId(localRobotId);
		messageReceive.setMessage(StringConvertUtils.toString(paramMap.get("msg")));
		messageReceive.setMsgType(StringConvertUtils.toString(paramMap.get("type")));
		messageReceive.setMsgSubType(StringConvertUtils.toString(paramMap.get("msg_type")));
		
		messageReceive.setMsgId(rid+"_"+localRobotId); 
		messageReceive.setTimestamp(new Date(Long.parseLong(time)*1000l));
		messageReceive.setCol1(rid);
		messageReceive.setCol2("");
		messageReceive.setCol3("");
		
	} 
	
	

	private boolean checkMyMsgType(Map paramMap) {
		return true;
	}



	public boolean checkType() {
		return checkMyType();
	}

	public MessageReceive handlerReceivMsg() {
		if(checkType()){
			initMessageReceive();
			return this.handlerMyReceivMsg();
		}else{
			if(wapper!=null){
				return wapper.handlerReceivMsg();
			} 
		}
		return null;
	}
	public Map getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}
	public MessageReceive getMessageReceive() {
		return messageReceive;
	}
	public void setMessageReceive(MessageReceive messageReceive) {
		this.messageReceive = messageReceive;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	



	
	

}
