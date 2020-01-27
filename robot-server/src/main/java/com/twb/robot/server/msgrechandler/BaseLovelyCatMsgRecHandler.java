package com.twb.robot.server.msgrechandler;

import java.util.Date;
import java.util.Map;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.utils.StringConvertUtils;

public abstract class BaseLovelyCatMsgRecHandler implements IMessageReceiveHandler {

	public IMessageReceiveHandler wapper;
	
	
	public abstract MessageReceive handlerMyReceivMsg(ReceiveHandlerContext receiveHandlerContext);
	public abstract boolean checkMyType(ReceiveHandlerContext receiveHandlerContext);
	
	@Override
	public void init(ReceiveHandlerContext receiveHandlerContext) {
		if(receiveHandlerContext.getReceiveParamMap()==null||receiveHandlerContext.getReceiveParamMap().isEmpty()){
			return;
		}
		String type = StringConvertUtils.toString(receiveHandlerContext.getReceiveParamMap().get("type"));
		String subType = StringConvertUtils.toString(receiveHandlerContext.getReceiveParamMap().get("msg_type"));
		
		receiveHandlerContext.setType(type);
		receiveHandlerContext.setSubType(subType);
		initMessageReceive(receiveHandlerContext);
	}
	
	public void initMessageReceive(ReceiveHandlerContext receiveHandlerContext){
		Map paramMap = receiveHandlerContext.getReceiveParamMap();
		
		String time = StringConvertUtils.toString(paramMap.get("time"));
		String localRobotId = StringConvertUtils.toString(paramMap.get("robot_wxid"));
		String rid = StringConvertUtils.toString(paramMap.get("rid"));
		String from_wxid = StringConvertUtils.toString(paramMap.get("from_wxid"));
		String from_name = StringConvertUtils.toString(paramMap.get("from_name"));
		
		MessageReceive messageReceive = new MessageReceive();
		receiveHandlerContext.setMessageReceive(messageReceive);
		
		messageReceive.setFromGroupId(from_wxid);
		messageReceive.setFromGroupName(from_name); 
		messageReceive.setFromUserId(StringConvertUtils.toString(paramMap.get("final_from_wxid")));
		messageReceive.setFromUserName(StringConvertUtils.toString(paramMap.get("final_from_name")));
		messageReceive.setLocalRobotId(localRobotId);
		messageReceive.setMessage(StringConvertUtils.toString(paramMap.get("msg")));
		messageReceive.setMsgType(StringConvertUtils.toString(paramMap.get("type")));
		messageReceive.setMsgSubType(StringConvertUtils.toString(paramMap.get("msg_type")));
		
		messageReceive.setMsgId(rid+"_"+localRobotId); 
		messageReceive.setTimestamp(new Date(Long.parseLong(time)*1000l));
		messageReceive.setCol1("");
		messageReceive.setCol2("");
		messageReceive.setCol3("");
		
	} 
	
	


	public boolean checkType(ReceiveHandlerContext receiveHandlerContext ) {
		return checkMyType(receiveHandlerContext);
	}

	public MessageReceive handlerReceivMsg(ReceiveHandlerContext receiveHandlerContext) {
		if(checkType(receiveHandlerContext)){
			return this.handlerMyReceivMsg(receiveHandlerContext);
		}else{
			if(wapper!=null){
				return wapper.handlerReceivMsg(receiveHandlerContext);
			} 
		}
		return null;
	}
	
	



	
	

}
