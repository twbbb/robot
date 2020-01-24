package com.twb.robot.common.datasource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DbService {

    public List queryListForList(String sql, String[] sqlParams, int num) ;
    
	
	public List queryForStringListEx(String sql, String[] sqlParams, int num) ;
	public List queryForStringListEx(String sql, String[] sqlParams) ;
	
	
	public List queryForStringListByStringList(String sql, List<String> sqlParams);

	
	public String querySingleValueByStringList(String sql, List<String> params);
	
	public String queryValueBySqlAndCond(String sql, String param) ;

	public String queryValueBySqlAndCond(String sql, String params[]) ;

	public String queryValueBySqlAndCond(String sql, List params) ;
 
 
	 
	public String querySingleValueBySql(String sql, List params) ;

	public String querySingleValue(String sql, String[] params);
	public String querySingleValue(String sql, String[] params,String jndiName) ;
	
	
	public String querySingleValue(String sql, ArrayList params) ;

	public Map queryForMapBySql(String sql, String[] param) ;
	
	public Map queryForMapBySql(String sql, String[] param, String jndiNames);
	
	public Map queryMap(String sql, String[] param) ;

	// 直接通过SQL 获取结果集
	public List queryForMapListBySql(String sql, String[] sqlParams) ;

	// 直接通过SQL 获取结果集
	public List queryForMapListBySql(String sql, List sqlParams) ;
	
	
	// 直接通过SQL 获取结果集
	public List queryForMapListBySql(String sql, List sqlParams,int maxCount);
	/**
	 * 直接通过SQL 获取结果集
	 * 指定数据源
	 * @param sql
	 * @param sqlParams
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public List queryForMapListBySql(String sql, List sqlParams,String dataSource);

	public List queryForStringList(String sql, String[] sqlParams);
  

	public Map queryRowsForMap(String sql, String[] sqlParams) ;
 
 
	
	  
	public String querySingleValue(String sql)  ;
 
	public List queryForStringList(String sql) ;
	   
  


	//add by AyaKoizumi 110304
	public List execForMapList(String sql, String[] sqlParams,String dataSource) ;

	public List execForMapList(String sql, String[] sqlParams) ;
 

	public List getUpcaseKeyMapList(String sql, String[] sqlParams);

	public List getLowercaseKeyMapList(String sql, String[] sqlParams) ;

	// 现在系统都是默认小写key，增加一个方法，用来选定MAP中是大写还是小写
	// T 大写 否则小写
	public List getMapList(String sql, String[] sqlParams, String Upcaseflag);
 

	public Map queryMapBySql(String sql, String[] sqlParams);
	
	public long countBySql(String sql,List sqlParams);
	
	public String queryBlobToString(String sqlString,String[] sqlParams);
	
	public String queryBlobToString(String sqlString);
	
	public String queryImgBlobToString(String sqlString,String[] sqlParams) ;
	 
	/**
	 * @param sql SQL
	 * @param maxReturn maxReturn when less then 0,the count of return result is unlimited;
	 * 	  	  when equals to 0,it decided by configuration file;
	 * 		  when larger then 0,limited by the parameter.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryForMapListWithLimit(String sql, int maxReturn);
	
	
	
	/**
	 * 根据条件更新数据。
	 *
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws Exception
	 */
	public int excuteUpdate(String sql, List sqlParams) throws Exception ;

	/**
	 * 根据条件更新数据。
	 * @author AyaKoizumi
	 * @date 101217
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws Exception
	 */
	public int excuteUpdate(String sql, String[] sqlParams,String jndiName) throws Exception  ;
	
	/**
	 * 
	 * @author AyaKoizumi
	 * @date 101217
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws Exception
	 */
	public void excuteUpdateItems(DbExecuteItem[] dbExecuteItems) throws Exception  ;
	
	/**
	 * 
	 * @author AyaKoizumi
	 * @date 101217
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws Exception
	 */
	public void excuteUpdateCallBack(DbExecuteCallBack svcExecuteCallBack) throws Exception  ;
	
	
	
	/**
	 * 根据条件更新数据。
	 * @author AyaKoizumi
	 * @date 101217
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws Exception
	 */
	public int excuteUpdate(String sql, List sqlParams,String jndiName) throws Exception ;
	
	
	public void excuteBatchUpdate(String sql, List sqlParams);

	/**
	 * 支持单个参数值的批量更新
	 * @param sql
	 * @param singleParaLst<String>
	 * @param commParam  公共
	 */
	public void excuteBatchUpdateSinglePara(String sql, List singleParaLst);

	/***
	 * 批量更新，支持日期类型
	 * @param sql
	 * @param sqlParams
	 */
	public void excuteBatchUpdateSupportDate(String sql, List sqlParams);

	/**
	 * 根据条件更新数据。
	 *
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws Exception
	 */
	public int excuteUpdate(String sql, String[] sqlParams);
	
 

 
	/**
	 * [715786]SELF-取消订单时记录代码轨迹
	 * @param sql ： 示例：select print_content from CUSTOMER_ORDER_PRINT where cust_order_id = ?
	 * @param printContent
	 * @return
	 */
	public boolean excuteUpdateByBlog(String sql,String blogContent);
	
	/**
	 * [715786]SELF-取消订单时记录代码轨迹
	 * @param sql ： 示例：select print_content from CUSTOMER_ORDER_PRINT where cust_order_id = ?
	 * @param printContent
	 * @return
	 */	
	public boolean excuteUpdateByBlog(String sql,byte[] blogContent);
	
	
}
