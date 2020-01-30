package com.twb.robot.handler.flow.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.common.dao.MessageSendRepository;
import com.twb.robot.common.entity.MessageReceiveTacheHandler;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.common.utils.DbServiceUtil;
import com.twb.robot.common.utils.SpringUtil;
import com.twb.robot.common.utils.SqlParseUtils;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.handler.flow.BaseMsgRecTacheFlowHandler;

public class MsgTacheFlowInsertSendMsgHandler extends BaseMsgRecTacheFlowHandler{

	@Override
	public void doMyHandler(MessageReceiveTacheHandler msgRecTacheHandler, Map param) {
	
		MessageSendRepository messageSendRepository = SpringUtil.getBean(MessageSendRepository.class);
		
		String sendGroupIdSql = msgRecTacheHandler.getCol1();
		if(!StringUtils.isEmpty(sendGroupIdSql)&&!sendGroupIdSql.trim().isEmpty()){
			List newSqlPramList = new ArrayList<String>();
			String newSql = SqlParseUtils.getNewSql(sendGroupIdSql, param, newSqlPramList);
			List<String> sendGroupIdList = DbServiceUtil.getDbService().queryForStringList(newSql, (String[])newSqlPramList.toArray(new String[newSqlPramList.size()]));
			if(sendGroupIdList==null||sendGroupIdList.isEmpty()){
				return;
			}
			for(String groupId:sendGroupIdList){
				MessageSend messageSend = initMessageSend(msgRecTacheHandler, param);
				messageSend.setToGroupId(groupId);
				messageSend.setMsgType(RobotSendConstants.MSG_TYPE_GROUP_CHAT);
				messageSendRepository.save(messageSend);
			}
			return;
			
			
		}
		MessageSend messageSend = initMessageSend(msgRecTacheHandler, param);
		messageSendRepository.save(messageSend);
	}

	private MessageSend initMessageSend(MessageReceiveTacheHandler msgRecTacheHandler, Map param) {
		MessageSend messageSend = new MessageSend();
		messageSend.setBusCode(msgRecTacheHandler.getTacheCode());
		messageSend.setCol1(StringConvertUtils.toString(param.get("col1")));
		messageSend.setCol2(StringConvertUtils.toString(param.get("col2")));
		messageSend.setCol3(StringConvertUtils.toString(param.get("col2")));
		messageSend.setCreateDate(new Date());
		messageSend.setLocalRobotId(StringConvertUtils.toString(param.get("local_robot_id")));
		
		messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_BEGIN_STATE);
		
		String fromMsg = StringConvertUtils.toString(param.get("message")) ;
		String fromMsgSubType = StringConvertUtils.toString(param.get("msg_sub_type")) ;
		String fromMsgType = StringConvertUtils.toString(param.get("msg_type")) ;
		
		
		String fromGroupId = StringConvertUtils.toString(param.get("from_group_id"));
		String fromUserId = StringConvertUtils.toString(param.get("from_user_id"));
		String fromGroupName = StringConvertUtils.toString(param.get("from_group_name"));
		String fromUserName = StringConvertUtils.toString(param.get("from_user_name"));
		
		String toMsgSubType = StringConvertUtils.toString(param.get("to_msg_sub_type")) ;
		String toMsgType = StringConvertUtils.toString(param.get("to_msg_type")) ;

		
		String toMsg = StringConvertUtils.toString(param.get("to_message")) ;
		String toGroupId = StringConvertUtils.toString(param.get("to_group_id"));
		String toUserId = StringConvertUtils.toString(param.get("to_user_id"));
		String toGroupName = StringConvertUtils.toString(param.get("to_group_name"));
		String toUserName = StringConvertUtils.toString(param.get("to_user_name"));
		if(StringUtils.isEmpty(toMsgSubType)){
			toMsgSubType = fromMsgSubType;
		}
		if(StringUtils.isEmpty(toMsgType)){
			toMsgType = fromMsgType;
		}
		
		if(StringUtils.isEmpty(toMsg)){
			toMsg = fromMsg;
		}
		if(StringUtils.isEmpty(toGroupId)){
			toGroupId = fromGroupId;
		}
		if(StringUtils.isEmpty(toUserId)){
			toUserId = fromUserId;
		}
		
		if(StringUtils.isEmpty(toGroupName)){
			toGroupName = fromGroupName;
		}
		
		if(StringUtils.isEmpty(toUserName)){
			toUserName = fromUserName;
		}
		messageSend.setMessage(toMsg);

		messageSend.setToGroupId(toGroupId);
		messageSend.setToUserId(toUserId);
		messageSend.setToGroupName(toGroupName);
		messageSend.setToUserName(toUserName);
		
		messageSend.setMsgSubType(toMsgSubType);
		messageSend.setMsgType(toMsgType);
		return messageSend;
	}

}
