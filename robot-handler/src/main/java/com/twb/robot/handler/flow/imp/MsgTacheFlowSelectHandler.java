package com.twb.robot.handler.flow.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.twb.robot.common.entity.MessageReceiveTacheHandler;
import com.twb.robot.common.utils.DbServiceUtil;
import com.twb.robot.common.utils.SqlParseUtils;
import com.twb.robot.handler.flow.BaseMsgRecTacheFlowHandler;

public class MsgTacheFlowSelectHandler extends BaseMsgRecTacheFlowHandler{

	@Override
	public void doMyHandler(MessageReceiveTacheHandler msgRecTacheHandler, Map param) {
		List newSqlPramList = new ArrayList<String>();
		String sql = msgRecTacheHandler.getCol1();
		if("clear".equals(msgRecTacheHandler.getCol2())){
			param.clear();
		} 
		if(StringUtils.isEmpty(sql)){
			return;
		}
		String newSql = SqlParseUtils.getNewSql(sql, param, newSqlPramList);
		List<Map> resultList = DbServiceUtil.getDbService().queryForMapListBySql(newSql, newSqlPramList);
		
		if(resultList==null||resultList.isEmpty()){
			return ;
		}
		
		Map resultMap= resultList.get(0);
		param.putAll(resultMap);
		
		
		
		
		
	}

}
