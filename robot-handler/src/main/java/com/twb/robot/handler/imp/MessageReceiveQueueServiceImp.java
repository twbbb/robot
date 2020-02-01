package com.twb.robot.handler.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twb.robot.common.config.RobotCommonConstants;
import com.twb.robot.common.dao.MessageReceiveTacheFlowRepository;
import com.twb.robot.common.dao.MessageReceiveTacheRepository;
import com.twb.robot.common.dao.MsgReceiveQueueHisRepository;
import com.twb.robot.common.dao.MsgReceiveQueueRepository;
import com.twb.robot.common.entity.MessageReceiveQueue;
import com.twb.robot.common.entity.MessageReceiveQueueHis;
import com.twb.robot.common.entity.MessageReceiveTache;
import com.twb.robot.common.entity.MessageReceiveTacheFlow;
import com.twb.robot.common.utils.DbServiceUtil;
import com.twb.robot.common.utils.SqlParseUtils;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.handler.MessageReceiveQueueService;

@Service
public class MessageReceiveQueueServiceImp implements MessageReceiveQueueService
{

	private static final Logger logger = LoggerFactory.getLogger(MessageReceiveQueueServiceImp.class);

	@Autowired
	MsgReceiveQueueRepository msgReceiveQueueRepository;
	
	@Autowired
	MsgReceiveQueueHisRepository msgReceiveQueueHisRepository;
	
	@Autowired
	MessageReceiveTacheRepository messageReceiveTacheRepository;

	@Autowired
	MessageReceiveTacheFlowRepository messageReceiveTacheFlowRepository;
	
	@Transactional(rollbackOn = Throwable.class)
	public List<Map> getMessageReceiveQueueList(){
		String sql = "select c.* from (select b.*,a.id queue_id from message_receive_queue a,message_receive b where a.message_receive_id=b.id order by message_receive_id ) c limit 0,1000";
		return DbServiceUtil.getDbService().queryForMapListBySql(sql, new String[]{});
	}
	
	
	@Transactional(rollbackOn = Throwable.class)
	public void handlerMessageReceiveQueue(Map param) {
		List<MessageReceiveTache> list = messageReceiveTacheRepository.getMessageReceiveQueueList();
		 List<String> newSqlPramList ;
		for(MessageReceiveTache messageReceiveTache:list){
			String sql = messageReceiveTache.getTacheContent();
			if(StringUtils.isEmpty(sql)){
				continue;
			}
			newSqlPramList = new ArrayList<String>();
			String newSql = SqlParseUtils.getNewSql(sql, param, newSqlPramList);
			List resultList = DbServiceUtil.getDbService().queryForMapListBySql(newSql, newSqlPramList);
			if(resultList!=null&&resultList.size()>0){
				saveFlow(param,messageReceiveTache);
			}
		}
		saveMessageReceiveQueueHis(param,RobotCommonConstants.MSG_RECEIVE_QUEUE_SUC_STATE,"成功");
	}


	private void saveFlow(Map param, MessageReceiveTache messageReceiveTache) {
		String message_receive_id = StringConvertUtils.toString(param.get("id"));
		String local_robot_id = StringConvertUtils.toString(param.get("local_robot_id"));

		MessageReceiveTacheFlow messageReceiveTacheFlow = new MessageReceiveTacheFlow();
		messageReceiveTacheFlow.setCreateDate(new Date());
		messageReceiveTacheFlow.setLocalRobotId(local_robot_id);
		messageReceiveTacheFlow.setMessageReceiveId(Integer.parseInt(message_receive_id));
		messageReceiveTacheFlow.setTacheCode(messageReceiveTache.getTacheCode());
		messageReceiveTacheFlow.setState(RobotCommonConstants.MESSAGE_RECEIVE_FLOW_BEGIN_STATE);
		messageReceiveTacheFlowRepository.save(messageReceiveTacheFlow);
		
	}


	@Override
	public void saveMessageReceiveQueueHis(Map map, String state, String stateMsg) {
		String id = StringConvertUtils.toString(map.get("queue_id"));
		MessageReceiveQueue messageReceiveQueue = msgReceiveQueueRepository.getOne(Integer.parseInt(id));
		if(stateMsg.length()>2000){
			stateMsg =stateMsg.substring(0,2000);
		}
		
		if(RobotCommonConstants.MSG_RECEIVE_QUEUE_SUC_STATE.equals(state)){
			MessageReceiveQueueHis messageReceiveQueueHis = new MessageReceiveQueueHis();
			messageReceiveQueueHis.setUpdateDate(new Date());
			messageReceiveQueueHis.setState(state);
			messageReceiveQueueHis.setStateMsg(stateMsg);
			messageReceiveQueueHis.setId(messageReceiveQueue.getId());
			messageReceiveQueueHis.setCreateDate(messageReceiveQueue.getCreateDate());
			messageReceiveQueueHis.setLocalRobotId(messageReceiveQueue.getLocalRobotId());
			messageReceiveQueueHis.setMessageReceiveId(messageReceiveQueue.getMessageReceiveId());
			msgReceiveQueueRepository.delete(messageReceiveQueue);
			msgReceiveQueueHisRepository.save(messageReceiveQueueHis);

			
		}else{
			messageReceiveQueue.setUpdateDate(new Date());
			messageReceiveQueue.setState(state);
			messageReceiveQueue.setStateMsg(stateMsg);
			msgReceiveQueueRepository.save(messageReceiveQueue);
		}
	}

}
