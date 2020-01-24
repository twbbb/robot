package com.twb.robot.config;

public class LovelyCatRobotConstants {

	//type            
	public static final String MSG_TYPE_PRIVATE_CHAT ="100";//私聊消息
	public static final String MSG_TYPE_GROUP_CHAT ="200";//群聊消息
	public static final String MSG_TYPE_NONE="300";//暂无
	public static final String MSG_TYPE_ADD_MERBER="400";//群成员增加
	public static final String MSG_TYPE_DEL_MERBER="410";// 群成员减少
	public static final String MSG_TYPE_ADD_FRIEND="500";//收到好友请求
	public static final String MSG_TYPE_RECEIVABLES="600";//二维码收款
	public static final String MSG_TYPE_TRANSFER="700";//收到转账
	public static final String MSG_TYPE_START="800";//软件开始启动
	public static final String MSG_TYPE_START_END="900";//新的账号登录完成
	public static final String MSG_TYPE_OUT ="910";//账号下线
	
	
	public static final String MSG_SUBTYPE_SYS="10000";//系统消息
	public static final String MSG_SUBTYPE_GROUP_ADDMERBER="0";//400 type,0入群通知
	public static final String MSG_SUBTYPE_TEXT="1";//文本消息
	public static final String MSG_SUBTYPE_IMG="3";//图片消息
	public static final String MSG_SUBTYPE_VOICE="34";//语音消息
	public static final String MSG_SUBTYPE_LINK="49";//链接消息
	
	//type=100&msg_type=1&from_wxid=wxid_sndgxwk6qc1m21&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=田文彬 &final_from_name=田文彬 &robot_wxid=wxid_mzss9j7otj9n22&msg= wer&time=1579851064&rid=10003
	//type=100&msg_type=3&from_wxid=wxid_sndgxwk6qc1m21&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=田文彬 &final_from_name=田文彬 &robot_wxid=wxid_mzss9j7otj9n22&msg=C:\Users\Administrator\Desktop\cat\data\temp\\wxid_mzss9j7otj9n22\1703820541.jpg&file_url=http://114.67.112.14:8073/static/1703820541.jpg&time=1579851536&rid=10004
	//type=200&msg_type=1&from_wxid=23070876266@chatroom&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=测试&final_from_name=田文彬&robot_wxid=wxid_mzss9j7otj9n22&msg=123&time=1579851617&rid=10005
	//type=200&msg_type=3&from_wxid=23070876266@chatroom&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=测试&final_from_name=田文彬&robot_wxid=wxid_mzss9j7otj9n22&msg=C:\Users\Administrator\Desktop\cat\data\temp\\wxid_mzss9j7otj9n22\1703820543.jpg&file_url=http://114.67.112.14:8073/static/1703820543.jpg&time=1579851625&rid=10006
	//type=200&msg_type=10000&from_wxid=23070876266@chatroom&final_from_wxid=&from_name=测试&final_from_name=&robot_wxid=wxid_mzss9j7otj9n22&msg="田文彬"邀请"饭饭。。"加入了群聊&time=1579874231&rid=10010
	//type=400&msg_type=0&from_wxid=23070876266@chatroom&final_from_wxid=23070876266@chatroom&from_name=测试&final_from_name=测试&robot_wxid=wxid_mzss9j7otj9n22&msg={"group_wxid":"23070876266@chatroom","group_name":"测试","guest":[{"wxid":"F420644102","nickname":"饭饭。。"}],"inviter":{"wxid":"wxid_sndgxwk6qc1m21","nickname":"田文彬"}}&time=1579874232&rid=10011
	
	/* type=100&msg_type=49&from_wxid=wxid_sndgxwk6qc1m21&final_from_wxid=wxid_sndgxwk6qc1m21&from_name=田文彬 &final_from_name=田文彬 &robot_wxid=wxid_mzss9j7otj9n22&msg=<?xml version="1.0"?>
	<msg>
		<appmsg appid="wxaadbab9d13edff20" sdkver="0">
			<title>我抢到12元春晚红包，点击你也有份，仅限4小时</title>
			<des>看春晚，分10亿，只在快手APP！</des>
			<username />
			<action>view</action>
			<type>5</type>
			<showtype>0</showtype>
			<content />
			<url>https://666.s.kuaishou.com/0124133158/rp.html?fid=1725086035&amp;cc=share_wxms&amp;shareMethod=CARD&amp;kpn=KUAISHOU&amp;subBiz=SF2020_R3_A3B3&amp;shareId=hDIGA9V6I5Vc1sRP5OGjuE6zsaYgqyss0n-uM8Ns-uQNO6&amp;shareToken=hDIGA9V6I5Vc1sRP5OGjuE6zsaYgqyss0n-uM8Ns-uQmyJ_1SKhGj_WC242209_fA&amp;shareObjectId=hDIGA9V6I5Vc1sRP5OGjuE6zsaYgqyss0n-uM8Ns-uQ&amp;shareUrlOpened=0&amp;rid=hDIGA9V6I5Vc1sRP5OGjuE6zsaYgqyss0n-uM8Ns-uQ</url>
			<lowurl />
			<dataurl />
			<lowdataurl />
			<contentattr>0</contentattr>
			<streamvideo>
				<streamvideourl />
				<streamvideototaltime>0</streamvideototaltime>
				<streamvideotitle />
				<streamvideowording />
				<streamvideoweburl />
				<streamvideothumburl />
				<streamvideoaduxinfo />
				<streamvideopublishid />
			</streamvideo>
			<canvasPageItem>
				<canvasPageXml><![CDATA[]]></canvasPageXml>
			</canvasPageItem>
			<appattach>
				<attachid />
				<cdnthumburl>305c02010004553053020100020471ce9e9702032f4f57020416a7206f02045e2afa75042e6175706170706d73675f653462646461653233363338393230375f313537393837343933323237305f38363939300204010400030201000400</cdnthumburl>
				<cdnthumbmd5>d8d99de1a1214c924de424a7fdfd94c5</cdnthumbmd5>
				<cdnthumblength>14158</cdnthumblength>
				<cdnthumbheight>160</cdnthumbheight>
				<cdnthumbwidth>160</cdnthumbwidth>
				<cdnthumbaeskey>1c71a6b5b78673caae18bf5496271666</cdnthumbaeskey>
				<aeskey>1c71a6b5b78673caae18bf5496271666</aeskey>
				<encryver>1</encryver>
				<fileext />
				<islargefilemsg>0</islargefilemsg>
			</appattach>
			<extinfo />
			<androidsource>2</androidsource>
			<thumburl />
			<mediatagname />
			<messageaction><![CDATA[]]></messageaction>
			<messageext><![CDATA[]]></messageext>
			<emoticongift>
				<packageflag>0</packageflag>
				<packageid />
			</emoticongift>
			<emoticonshared>
				<packageflag>0</packageflag>
				<packageid />
			</emoticonshared>
			<designershared>
				<designeruin>0</designeruin>
				<designername>null</designername>
				<designerrediretcturl>null</designerrediretcturl>
			</designershared>
			<emotionpageshared>
				<tid>0</tid>
				<title>null</title>
				<desc>null</desc>
				<iconUrl>null</iconUrl>
				<secondUrl>null</secondUrl>
				<pageType>0</pageType>
			</emotionpageshared>
			<webviewshared>
				<shareUrlOriginal />
				<shareUrlOpen />
				<jsAppId />
				<publisherId />
			</webviewshared>
			<template_id />
			<md5>d8d99de1a1214c924de424a7fdfd94c5</md5>
			<weappinfo>
				<username />
				<appid />
				<appservicetype>0</appservicetype>
				<videopageinfo>
					<thumbwidth>160</thumbwidth>
					<thumbheight>160</thumbheight>
					<fromopensdk>0</fromopensdk>
				</videopageinfo>
			</weappinfo>
			<statextstr>GhQKEnd4YWFkYmFiOWQxM2VkZmYyMA==</statextstr>
			<finderFeed>
				<objectId>null</objectId>
				<objectNonceId>null</objectNonceId>
				<feedType>0</feedType>
				<nickname />
				<avatar><![CDATA[null]]></avatar>
				<desc />
				<mediaCount>0</mediaCount>
				<mediaList />
			</finderFeed>
			<findernamecard>
				<username />
				<avatar><![CDATA[]]></avatar>
				<nickname />
				<auth_job />
				<auth_icon>0</auth_icon>
			</findernamecard>
			<finderEndorsement>
				<scene><![CDATA[0]]></scene>
			</finderEndorsement>
			<directshare>0</directshare>
			<websearch>
				<rec_category>0</rec_category>
				<channelId>0</channelId>
			</websearch>
		</appmsg>
		<fromusername>wxid_sndgxwk6qc1m21</fromusername>
		<scene>0</scene>
		<appinfo>
			<version>16</version>
			<appname>快手</appname>
		</appinfo>
		<commenturl></commenturl>
	</msg>&time=1579874946&rid=10012
	【请求标识（rid）】10012
	*/
}
