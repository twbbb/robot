package com.twb.robot.server.msgrechandler.imp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.twb.robot.bean.ReceiveHandlerContext;
import com.twb.robot.common.config.RobotRecevieConstants;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.config.LovelyCatConstants;
import com.twb.robot.server.msgrechandler.BaseLovelyCatMsgRecHandler;
import com.twb.robot.server.msgrechandler.IMessageReceiveHandler;

public class LovelyCatLinkMsgRecHandler extends BaseLovelyCatMsgRecHandler{

	public LovelyCatLinkMsgRecHandler(IMessageReceiveHandler messageReceiveHandler) {
		super.wapper = messageReceiveHandler;
	}
	
	
	@Override	
	public void handlerMyReceivMsg(ReceiveHandlerContext receiveHandlerContext) {
 		MessageReceive messageReceive =  receiveHandlerContext.getMessageReceive();
 		String title="";
 		String url = "";
 		String desc = "";
 		String pic = "";
 		String msg = StringConvertUtils.toString(receiveHandlerContext.getReceiveParamMap().get("msg"));
		int titleBegin = msg.indexOf("<title>");
		int titleEnd = msg.indexOf("</title>");
		if(titleBegin>-1&&titleEnd>-1){
			title =msg.substring(titleBegin+7,titleEnd);
		}
		
		int descBegin = msg.indexOf("<des>");
		int descEnd = msg.indexOf("</des>");
		if(descBegin>-1&&descEnd>-1){
			desc = msg.substring(descBegin+5,descEnd);
		}
		
		int urlBegin  = msg.indexOf("<url>");
		int urlEnd  = msg.indexOf("</url>");
		if(urlBegin>-1&&urlEnd>-1){
			url = msg.substring(urlBegin+5,urlEnd);
		}
		
		int picBegin = msg.indexOf("<cdnthumburl>");
		int picEnd = msg.indexOf("</cdnthumburl>");
		if(picBegin>-1&&picEnd>-1){
			pic = msg.substring(picBegin+31,picEnd).trim();
		}
		
		messageReceive.setMessage(title);
		messageReceive.setCol1(desc);
		messageReceive.setCol2(url);
		if(pic.length()<=4000){
		//	messageReceive.setCol3(pic);
		}
		

	
	}

	@Override
	public boolean checkMyType(ReceiveHandlerContext receiveHandlerContext) {
		if(RobotRecevieConstants.MSG_SUBTYPE_LINK.equals(receiveHandlerContext.getSubType())){
			return true;
		}
		else{
			return false;
		}
		
	}

}
