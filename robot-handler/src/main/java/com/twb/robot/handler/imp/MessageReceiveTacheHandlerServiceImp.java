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
import com.twb.robot.common.dao.MessageReceiveTacheFlowHisRepository;
import com.twb.robot.common.dao.MessageReceiveTacheFlowRepository;
import com.twb.robot.common.dao.MessageReceiveTacheHandlerRepository;
import com.twb.robot.common.entity.MessageReceiveTacheFlow;
import com.twb.robot.common.entity.MessageReceiveTacheFlowHis;
import com.twb.robot.common.entity.MessageReceiveTacheHandler;
import com.twb.robot.common.utils.DbServiceUtil;
import com.twb.robot.common.utils.SqlParseUtils;
import com.twb.robot.handler.MessageReceiveTacheHandlerService;
import com.twb.robot.handler.flow.IMsgRecTacheFlowHandler;

@Service
public class MessageReceiveTacheHandlerServiceImp implements MessageReceiveTacheHandlerService
{

	private static final Logger logger = LoggerFactory.getLogger(MessageReceiveTacheHandlerServiceImp.class);


	@Autowired
	MessageReceiveTacheHandlerRepository messageReceiveTacheHandlerRepository;
	
	
	@Autowired
	MessageReceiveTacheFlowRepository messageReceiveTacheFlowRepository	;
	
	
	@Autowired
	MessageReceiveTacheFlowHisRepository messageReceiveTacheFlowHisRepository	;
	
	
	
	@Override
	@Transactional(rollbackOn = Throwable.class)
	public void handlerMessageReceiveTacheFlow(MessageReceiveTacheFlow messageReceiveTacheFlow) {

		try {
			doHandlerMsgRecTacheFlow(messageReceiveTacheFlow);
			saveHis(messageReceiveTacheFlow);
		} catch (Exception e) {
			e.printStackTrace();
			
			logger.error("异常",e);
			throw new RuntimeException(e);
		}
		

		
	}

	@Transactional(rollbackOn = Throwable.class)
	public void saveHis(MessageReceiveTacheFlow messageReceiveTacheFlow) {
		if(RobotCommonConstants.MESSAGE_RECEIVE_FLOW_FAIL_STATE.equals(messageReceiveTacheFlow.getState())){
			messageReceiveTacheFlowRepository.save(messageReceiveTacheFlow);
		}else{
			MessageReceiveTacheFlowHis messageReceiveTacheFlowHis = new MessageReceiveTacheFlowHis();
			messageReceiveTacheFlowHis.setId(messageReceiveTacheFlow.getId());
			messageReceiveTacheFlowHis.setCreateDate(messageReceiveTacheFlow.getCreateDate());
			messageReceiveTacheFlowHis.setLocalRobotId(messageReceiveTacheFlow.getLocalRobotId());
			messageReceiveTacheFlowHis.setMessageReceiveId(messageReceiveTacheFlow.getMessageReceiveId());
			messageReceiveTacheFlowHis.setState(messageReceiveTacheFlow.getState());
			messageReceiveTacheFlowHis.setStateMsg(messageReceiveTacheFlow.getStateMsg());
			messageReceiveTacheFlowHis.setTacheCode(messageReceiveTacheFlow.getTacheCode());
			messageReceiveTacheFlowHis.setUpdateDate(new Date());
			
			messageReceiveTacheFlowHisRepository.save(messageReceiveTacheFlowHis);
			messageReceiveTacheFlowRepository.delete(messageReceiveTacheFlow);
		}
		
		
	}

	private void doHandlerMsgRecTacheFlow(MessageReceiveTacheFlow messageReceiveTacheFlow) throws Exception {
		List<MessageReceiveTacheHandler> msgRecTacheHandlerList = messageReceiveTacheHandlerRepository.getMessageReceiveQueueList(messageReceiveTacheFlow.getTacheCode());
		if(msgRecTacheHandlerList==null||msgRecTacheHandlerList.isEmpty()){
			messageReceiveTacheFlow.setState(RobotCommonConstants.MESSAGE_RECEIVE_FLOW_FAIL_STATE);
			messageReceiveTacheFlow.setStateMsg("没有配置环节");
			return;
		}
		String msgRecSql ="select * from message_receive where id = ?";
		int msgRecId = messageReceiveTacheFlow.getMessageReceiveId();
		Map msgRecMap = DbServiceUtil.getDbService().queryForMapBySql(msgRecSql, new String[]{msgRecId+""});
		if(msgRecMap==null||msgRecMap.isEmpty()){
			messageReceiveTacheFlow.setState(RobotCommonConstants.MESSAGE_RECEIVE_FLOW_FAIL_STATE);
			messageReceiveTacheFlow.setStateMsg("msgRecMap为空");
			return;
		}
		
		for(MessageReceiveTacheHandler msgRecTacheHandler:msgRecTacheHandlerList){
			if(msgRecMap.isEmpty()){
				break;
			}
			String flowCheck = msgRecTacheHandler.getFlowCheck();
			if(!StringUtils.isEmpty(flowCheck)&&!"".equals(flowCheck.trim())){
				List newSqlPramList = new ArrayList<String>();
				String newSql = SqlParseUtils.getNewSql(flowCheck, msgRecMap, newSqlPramList);
				List resultList = DbServiceUtil.getDbService().queryForMapListBySql(newSql, newSqlPramList);
				if(resultList==null||resultList.size()==0){
					continue;
				}
			}
			String flowClass = msgRecTacheHandler.getFlowClass();
			IMsgRecTacheFlowHandler msgRecTacheFlowHandler = (IMsgRecTacheFlowHandler) Class.forName(flowClass).newInstance();
			msgRecTacheFlowHandler.handler(msgRecTacheHandler, msgRecMap);
	
			
		}
		
		messageReceiveTacheFlow.setState(RobotCommonConstants.MESSAGE_RECEIVE_FLOW_SUC_STATE);
		messageReceiveTacheFlow.setStateMsg("成功");
	}

	@Override
	@Transactional(rollbackOn = Throwable.class)
	public List<MessageReceiveTacheFlow> getMessageReceiveTacheFlow() {

		return messageReceiveTacheFlowRepository.getMessageReceiveQueueList();
	}

}
