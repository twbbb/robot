package com.twb.robot.handler.flow.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.common.entity.MessageReceiveTacheHandler;
import com.twb.robot.common.utils.DbServiceUtil;
import com.twb.robot.common.utils.SqlParseUtils;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.handler.flow.BaseMsgRecTacheFlowHandler;
import com.twb.robot.utils.HttpAnswerUtils;

public class MsgTacheFlowAnswerHandler extends BaseMsgRecTacheFlowHandler{

	@Override
	public void doMyHandler(MessageReceiveTacheHandler msgRecTacheHandler, Map param) {
		String question = StringConvertUtils.toString(param.get("question")) ;
		String message = StringConvertUtils.toString(param.get("message")) ;
		if(StringUtils.isEmpty(question)){
			question = message;
		}
		String answer = HttpAnswerUtils.getAnswerContent(question);
		param.put("answer", answer);
		param.put("to_message", answer);
		
		
		
		
		
		
	}

}
