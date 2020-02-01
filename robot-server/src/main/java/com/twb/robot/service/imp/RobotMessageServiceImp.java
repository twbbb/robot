package com.twb.robot.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.config.RobotSendConstants;
import com.twb.robot.common.dao.MessageReceiveRepository;
import com.twb.robot.common.dao.MessageReceiveTacheParamRepository;
import com.twb.robot.common.dao.MessageSendHisRepository;
import com.twb.robot.common.dao.MessageSendRepository;
import com.twb.robot.common.dao.MsgReceiveQueueRepository;
import com.twb.robot.common.entity.MessageReceive;
import com.twb.robot.common.entity.MessageReceiveQueue;
import com.twb.robot.common.entity.MessageReceiveTacheParam;
import com.twb.robot.common.entity.MessageSend;
import com.twb.robot.common.entity.MessageSendHis;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.server.RobotServerManager;
import com.twb.robot.service.RobotMessageService;

@Service
public class RobotMessageServiceImp implements RobotMessageService
{

	private static final Logger logger = LoggerFactory.getLogger(RobotMessageServiceImp.class);

	@Resource
    private MessageReceiveRepository messageReceiveRepository;
	
	@Resource
    private MsgReceiveQueueRepository msgReceiveQueueRepository;
	
	@Resource
    private MessageSendRepository messageSendRepository;
	@Resource
    private MessageSendHisRepository messageSendHisRepository;
	
	@Resource
    private MessageReceiveTacheParamRepository messageReceiveTacheParamRepository;
	
	
	
	
	
	
	@Transactional
	public void handlerReceivMsg(Object obj) {
		MessageReceive messageReceive = RobotServerManager.getRobotServer().handlerReceivMsg(obj);
		if(messageReceive==null||StringUtils.isEmpty(messageReceive.getMsgId())){
			return;
		}
		messageReceiveRepository.save(messageReceive);
		//不能是自己发送的消息
		if(messageReceive.getLocalRobotId().equals(messageReceive.getFromUserId())){
			return;
		}
		
		MessageReceiveQueue messageReceiveQueue = new MessageReceiveQueue();
		messageReceiveQueue.setCreateDate(new Date());
		messageReceiveQueue.setMessageReceiveId(messageReceive.getId());
		messageReceiveQueue.setLocalRobotId(messageReceive.getLocalRobotId());
		messageReceiveQueue.setState(RobotCommonConstants.MSG_RECEIVE_QUEUE_BEGIN_STATE);
		
		msgReceiveQueueRepository.save(messageReceiveQueue);
	}



	@Transactional
	public void handlerSendMsg(MessageSend messageSend) {
		try {
			RobotServerManager.getRobotServer().handlerSendMsg(messageSend);
		} catch (Exception e) {
			messageSend.setSendState(RobotCommonConstants.MESSAGE_SEND_FAIL_STATE);
			messageSend.setSendStateMsg("异常："+e.getMessage());
			e.printStackTrace();
		}
		if(RobotCommonConstants.MESSAGE_SEND_SUC_STATE.equals(messageSend.getSendState())){
			MessageSendHis messageSendHis = new MessageSendHis();
			messageSendHis.setCol1(messageSend.getCol1());
			messageSendHis.setCol2(messageSend.getCol2());
			messageSendHis.setCol3(messageSend.getCol3());
			messageSendHis.setCreateDate(messageSend.getCreateDate());
			messageSendHis.setId(messageSend.getId());
			messageSendHis.setLocalRobotId(messageSend.getLocalRobotId());
			messageSendHis.setMessage(messageSend.getMessage());
			messageSendHis.setMsgSubType(messageSend.getMsgSubType());
			messageSendHis.setMsgType(messageSend.getMsgType());
			messageSendHis.setSendState(messageSend.getSendState());
			messageSendHis.setSendStateMsg(messageSend.getSendStateMsg());
			messageSendHis.setToGroupId(messageSend.getToGroupId());
			messageSendHis.setToUserId(messageSend.getToUserId());
			messageSendHis.setToGroupName(messageSend.getToGroupName());
			messageSendHis.setToUserName(messageSend.getToUserName());
			messageSendHis.setUpdateDate(new Date());
			messageSendHis.setBusCode(messageSend.getBusCode());
			
			messageSendRepository.delete(messageSend);
			messageSendHisRepository.save(messageSendHis);
		}else{
			messageSend.setUpdateDate(new Date());
			messageSendRepository.save(messageSend);

		}
	}
	
	
	
	@Transactional
	public void getAllGroup() {
		

		MessageSend messageSend = new MessageSend();
		messageSend.setMsgType(RobotSendConstants.MSG_TYPE_LOGGEDROBOT);

		RobotServerManager.getRobotServer().handlerSendMsg(messageSend);
		
		String state = messageSend.getSendState();
		String msg = messageSend.getSendStateMsg();
		if(!RobotCommonConstants.MESSAGE_SEND_SUC_STATE.equals(state)){
			return;
		}
		JSONArray ja= (JSONArray) JSON.parse(msg);
		for(int i=0;i<ja.size();i++){
			Map map = (Map) ja.get(i);
			String robotId = StringConvertUtils.toString(map.get("wxid"));
			logger.info("开始处理:"+robotId);
			MessageSend ms = new MessageSend();	
			ms.setMsgType(RobotSendConstants.MSG_TYPE_GROUPLIST);
			ms.setLocalRobotId(robotId);
			RobotServerManager.getRobotServer().handlerSendMsg(ms);
			if(!RobotCommonConstants.MESSAGE_SEND_SUC_STATE.equals(ms.getSendState())){
				continue;
			}
			String stateMsg = ms.getSendStateMsg();
			JSONArray groupArray= (JSONArray) JSON.parse(stateMsg);
			String key="group_all";
			List<MessageReceiveTacheParam> list = messageReceiveTacheParamRepository.getListByKeyCodeb(key,robotId);
			Map<String,MessageReceiveTacheParam> messageReceiveTacheParamMap = new HashMap();
			for(MessageReceiveTacheParam messageReceiveTacheParam:list){
				messageReceiveTacheParamMap.put(messageReceiveTacheParam.getCodea(), messageReceiveTacheParam);
			}
			List<MessageReceiveTacheParam> addList = new ArrayList();
			Map<String,String> wxGroupMap = new HashMap();
			for(int j=0;j<groupArray.size();j++){
				Map groupMap = (Map) groupArray.get(j);
				String groupId = StringConvertUtils.toString(groupMap.get("wxid"));
				String groupName = StringConvertUtils.toString(groupMap.get("nickname"));

				wxGroupMap.put(groupId, groupName);
				
			}
			
			for(Map.Entry<String, String> entry:wxGroupMap.entrySet()){
				String groupId = entry.getKey();
				String groupName = entry.getValue();
				if(messageReceiveTacheParamMap.containsKey(groupId)){
					messageReceiveTacheParamMap.remove(groupId);
				}else{
					MessageReceiveTacheParam messageReceiveTacheParam = new MessageReceiveTacheParam();
					messageReceiveTacheParam.setCodea(groupId);
					messageReceiveTacheParam.setCodeb(robotId);
					messageReceiveTacheParam.setCodec(groupName);
					messageReceiveTacheParam.setParamKey(key);
					messageReceiveTacheParam.setRemark("所有群");
					addList.add(messageReceiveTacheParam);
				}
			}
			
			
			for(Map.Entry<String, MessageReceiveTacheParam> entry:messageReceiveTacheParamMap.entrySet()){
				MessageReceiveTacheParam messageReceiveTacheParam = entry.getValue();
				logger.info("删除群:"+messageReceiveTacheParam.getCodea());
				messageReceiveTacheParamRepository.delete(messageReceiveTacheParam);
			}
			for(MessageReceiveTacheParam messageReceiveTacheParam: addList){
				logger.info("添加群:"+messageReceiveTacheParam.getCodea());
				messageReceiveTacheParamRepository.save(messageReceiveTacheParam);
			}
			
			
		}

	}



}
