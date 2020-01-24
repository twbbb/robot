package com.twb.robot.common.datasource.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.twb.robot.common.datasource.common.SqlMapExe;
import com.twb.robot.common.datasource.common.bean.PageModel;
import com.twb.robot.common.datasource.service.DbExecuteCallBack;
import com.twb.robot.common.datasource.service.DbExecuteItem;
import com.twb.robot.common.datasource.service.DbService;

@Service("dbService")
public class DbServiceImpl implements DbService {
 

	@Override
	public List queryListForList(String sql, String[] sqlParams, int num) {

		return SqlMapExe.getInstance().queryListForList(sql, sqlParams, num);
	}

	@Override
	public List queryForStringListEx(String sql, String[] sqlParams, int num) {

		return SqlMapExe.getInstance()
				.queryForStringListEx(sql, sqlParams, num);
	}

	@Override
	public List queryForStringListEx(String sql, String[] sqlParams) {
		return SqlMapExe.getInstance().queryForStringListEx(sql, sqlParams);
	}

	@Override
	public List queryForStringListByStringList(String sql,
			List<String> sqlParams) {
		return SqlMapExe.getInstance().queryForStringListByStringList(sql,
				sqlParams);
	}

	@Override
	public String querySingleValueByStringList(String sql, List<String> params) {
		return SqlMapExe.getInstance()
				.querySingleValueByStringList(sql, params);
	}

	@Override
	public String queryValueBySqlAndCond(String sql, String param) {
		return SqlMapExe.getInstance().queryValueBySqlAndCond(sql, param);
	}

	@Override
	public String queryValueBySqlAndCond(String sql, String[] params) {
		return SqlMapExe.getInstance().queryValueBySqlAndCond(sql, params);
	}

	@Override
	public String queryValueBySqlAndCond(String sql, List params) {
		return SqlMapExe.getInstance().queryValueBySqlAndCond(sql, params);
	}

	@Override
	public String querySingleValueBySql(String sql, List params) {
		return SqlMapExe.getInstance().querySingleValueBySql(sql, params);
	}

	@Override
	public String querySingleValue(String sql, String[] params) {
		return SqlMapExe.getInstance().querySingleValue(sql, params);
	}

	@Override
	public String querySingleValue(String sql, String[] params, String jndiName) {
		return SqlMapExe.getInstance().querySingleValue(sql, params, jndiName);
	}

	@Override
	public String querySingleValue(String sql, ArrayList params) {
		return SqlMapExe.getInstance().querySingleValue(sql, params);
	}

	@Override
	public Map queryForMapBySql(String sql, String[] param) {
		return SqlMapExe.getInstance().queryForMapBySql(sql, param);
	}

	@Override
	public Map queryForMapBySql(String sql, String[] param, String jndiNames)
			 {
		return SqlMapExe.getInstance().queryForMapBySql(sql, param, jndiNames);
	}

	@Override
	public Map queryMap(String sql, String[] param) {
		return SqlMapExe.getInstance().queryMap(sql, param);
	}

	@Override
	public List queryForMapListBySql(String sql, String[] sqlParams)
			 {
		return SqlMapExe.getInstance().queryForMapListBySql(sql, sqlParams);
	}

	@Override
	public List queryForMapListBySql(String sql, List sqlParams)
			 {
		return SqlMapExe.getInstance().queryForMapListBySql(sql, sqlParams);
	}

	@Override
	public List queryForMapListBySql(String sql, List sqlParams, int maxCount)
			 {
		return SqlMapExe.getInstance().queryForMapListBySql(sql, sqlParams,
				maxCount);
	}

	@Override
	public List queryForMapListBySql(String sql, List sqlParams,
			String dataSource) {
		return SqlMapExe.getInstance().queryForMapListBySql(sql, sqlParams,
				dataSource);
	}

	@Override
	public List queryForStringList(String sql, String[] sqlParams) {
		return SqlMapExe.getInstance().queryForStringList(sql, sqlParams);
	}

	@Override
	public Map queryRowsForMap(String sql, String[] sqlParams) {
		return SqlMapExe.getInstance().queryRowsForMap(sql, sqlParams);
	}
 

	@Override
	public String querySingleValue(String sql) {
		return SqlMapExe.getInstance().querySingleValue(sql);
	}

	@Override
	public List queryForStringList(String sql) {
		return SqlMapExe.getInstance().queryForStringList(sql);
	}

	@Override
	public List execForMapList(String sql, String[] sqlParams, String dataSource)
			 {
		return SqlMapExe.getInstance().execForMapList(sql, sqlParams,
				dataSource);
	}

	@Override
	public List execForMapList(String sql, String[] sqlParams) {
		return SqlMapExe.getInstance().execForMapList(sql, sqlParams);
	}
 
	public PageModel queryPageModelResult(String sql, ArrayList params, int pi,
			int ps) {
		return SqlMapExe.getInstance()
				.queryPageModelResult(sql, params, pi, ps);
	}

	@Override
	public List getUpcaseKeyMapList(String sql, String[] sqlParams)
			 {
		return SqlMapExe.getInstance().getUpcaseKeyMapList(sql, sqlParams);
	}

	@Override
	public List getLowercaseKeyMapList(String sql, String[] sqlParams)
			 {
		return SqlMapExe.getInstance().getLowercaseKeyMapList(sql, sqlParams);
	}

	@Override
	public List getMapList(String sql, String[] sqlParams, String Upcaseflag)
			 {
		return SqlMapExe.getInstance().getMapList(sql, sqlParams, Upcaseflag);
	}

	@Override
	public Map queryMapBySql(String sql, String[] sqlParams) {
		return SqlMapExe.getInstance().queryMapBySql(sql, sqlParams);
	}

	@Override
	public long countBySql(String sql, List sqlParams) {
		return countBySql(sql, sqlParams);
	}

	@Override
	public String queryBlobToString(String sqlString, String[] sqlParams) {
		return SqlMapExe.getInstance().queryBlobToString(sqlString, sqlParams);
	}

	@Override
	public String queryBlobToString(String sqlString) {
		return SqlMapExe.getInstance().queryBlobToString(sqlString);
	}

	@Override
	public String queryImgBlobToString(String sqlString, String[] sqlParams) {
		return SqlMapExe.getInstance().queryImgBlobToString(sqlString,
				sqlParams);
	}
 
	@Override
	public List<Map<String, String>> queryForMapListWithLimit(String sql,
			int maxReturn) {
		return SqlMapExe.getInstance().queryForMapListWithLimit(sql, maxReturn);
	}
	
	@Override
	public int excuteUpdate(String sql, List sqlParams)  {
		return SqlMapExe.getInstance().excuteUpdate(sql, sqlParams);
	}

	@Override
	public int excuteUpdate(String sql, String[] sqlParams, String jndiName)
			 {
		return SqlMapExe.getInstance().excuteUpdate(sql, sqlParams, jndiName);
	}

	@Override
	public int excuteUpdate(String sql, List sqlParams, String jndiName)
			 {
		return SqlMapExe.getInstance().excuteUpdate(sql, sqlParams, jndiName);
	}

	@Override
	public void excuteBatchUpdate(String sql, List sqlParams) {
		SqlMapExe.getInstance().excuteBatchUpdate(sql, sqlParams);
	}

	@Override
	public void excuteBatchUpdateSinglePara(String sql, List singleParaLst) {
		SqlMapExe.getInstance().excuteBatchUpdateSinglePara(sql, singleParaLst);
	}

	@Override
	public void excuteBatchUpdateSupportDate(String sql, List sqlParams) {
		SqlMapExe.getInstance().excuteBatchUpdateSupportDate(sql, sqlParams);
	}

	@Override
	public int excuteUpdate(String sql, String[] sqlParams)  {
		return SqlMapExe.getInstance().excuteUpdate(sql, sqlParams);
	}
 

	@Override
	public boolean excuteUpdateByBlog(String sql, String blogContent) {
		return SqlMapExe.getInstance().excuteUpdateByBlog(sql, blogContent);
	}

	@Override
	public boolean excuteUpdateByBlog(String sql, byte[] blogContent) {
		return SqlMapExe.getInstance().excuteUpdateByBlog(sql, blogContent);
	}

	@Override
	public void excuteUpdateItems(DbExecuteItem[] dbExecuteItems) { 
		 for(DbExecuteItem svcExecuteItem : dbExecuteItems){
			 SqlMapExe.getInstance().excuteUpdate(svcExecuteItem.getSql(), svcExecuteItem.getParams());
		 }
	}

	@Override
	public void excuteUpdateCallBack(DbExecuteCallBack dbExecuteCallBack)  { 
		dbExecuteCallBack.callback(); 
	}

}
