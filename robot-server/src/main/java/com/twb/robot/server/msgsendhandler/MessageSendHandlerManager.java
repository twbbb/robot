package com.twb.robot.server.msgsendhandler;

import com.twb.robot.server.msgsendhandler.imp.LovelyCatExtMsgFriendListHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatExtMsgGroupListHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatExtMsgGroupMemberHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatExtMsgLoggedRobotHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatExtMsgRobotNameHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatFriendAgreeMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatFriendDelMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatGroupAgreeMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatGroupInviteMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatGroupMemberDelMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatGroupQuitMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatImgGroupMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatImgMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatLinkGroupMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatLinkMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatTextGroupMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatTextGroupNoticeMsgHandler;
import com.twb.robot.server.msgsendhandler.imp.LovelyCatTextMsgHandler;

public class MessageSendHandlerManager {
	public static IMessageSendHandler messageSendHandler = null ;
	static{
		messageSendHandler = new LovelyCatExtMsgFriendListHandler(messageSendHandler);
		messageSendHandler = new LovelyCatExtMsgGroupListHandler(messageSendHandler);
		messageSendHandler = new LovelyCatExtMsgGroupMemberHandler(messageSendHandler);
		messageSendHandler = new LovelyCatExtMsgLoggedRobotHandler(messageSendHandler);
		messageSendHandler = new LovelyCatExtMsgRobotNameHandler(messageSendHandler);
		messageSendHandler = new LovelyCatFriendAgreeMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatFriendDelMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatGroupAgreeMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatGroupInviteMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatGroupMemberDelMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatGroupQuitMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatImgGroupMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatImgMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatLinkGroupMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatLinkMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatTextGroupMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatTextGroupNoticeMsgHandler(messageSendHandler);
		messageSendHandler = new LovelyCatTextMsgHandler(messageSendHandler);
	}	

	public static IMessageSendHandler getMessageSendHandler(){
		
		return messageSendHandler;
	}
	
	
}
