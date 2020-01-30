package com.twb.robot.handler.flow.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.twb.robot.common.entity.MessageReceiveTacheHandler;
import com.twb.robot.common.utils.DbServiceUtil;
import com.twb.robot.common.utils.SqlParseUtils;
import com.twb.robot.handler.flow.BaseMsgRecTacheFlowHandler;

public class MsgTacheFlowUpdateHandler extends BaseMsgRecTacheFlowHandler{

	@Override
	public void doMyHandler(MessageReceiveTacheHandler msgRecTacheHandler, Map param) {
		List newSqlPramList = new ArrayList<String>();
		String newSql = SqlParseUtils.getNewSql(msgRecTacheHandler.getCol1(), param, newSqlPramList);
		try {
			DbServiceUtil.getDbService().excuteUpdate(newSql, newSqlPramList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("更新异常",e);
		}
		
		
		
	}

}
