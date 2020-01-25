package com.twb.robot.common.config;

public class RobotSendConstants {

	//type            
	public static final String MSG_TYPE_PRIVATE_CHAT ="100";//私聊消息
	public static final String MSG_TYPE_GROUP_CHAT ="200";//群聊消息
	
	
	public static final String MSG_TYPE_ROBOTNAME="201";//机器人名称

	public static final String MSG_TYPE_LOGGEDROBOT="203";//已登录机器人
	public static final String MSG_TYPE_FRIENDLIST="204";//好友列表
	public static final String MSG_TYPE_GROUPLIST="205";//群组列表
	public static final String MSG_TYPE_GROUPMEMBERLIST="206";//群组成员列表
	
	
	public static final String MSG_TYPE_GROUPAGREE="302";//同意入群
	
	public static final String MSG_TYPE_FRIENDAGREE="303";//同意好友

	public static final String MSG_TYPE_FRIENDDEL="305";//删除好友

	public static final String MSG_TYPE_GROUPMEMBERDEL="306";//踢除成员
	
	public static final String MSG_TYPE_GROUPQUIT="310";//退群

	public static final String MSG_TYPE_GROUPINVITE="311";//邀请进群


	
	public static final String MSG_SUBTYPE_TEXT="1";//文本消息
	public static final String MSG_SUBTYPE_NOTICE_TEXT="2";//发送群消息并艾特某人

	public static final String MSG_SUBTYPE_IMG="3";//图片消息
}
