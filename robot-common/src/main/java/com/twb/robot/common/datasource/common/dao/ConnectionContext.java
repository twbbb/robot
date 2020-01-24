/**  
 * Project Name:order-public  
 * File Name:ConnectionContext.java  
 * Package Name:com.ztesoft.bss.common.dao  
 * Date:2017年7月5日下午8:43:53  
 * Copyright (c) 2017, ZTEsoft Technology Co., Ltd. All Rights Reserved.  
 *  
*/  
package com.twb.robot.common.datasource.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.twb.robot.common.datasource.common.constant.Constants;
import com.twb.robot.common.utils.SpringUtil;


/**  
 * ClassName:ConnectionContext <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年7月5日 下午8:43:53 <br/>  
 * @author     
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class ConnectionContext implements InitializingBean{
	public static final String DEFAULT_JNDINAME = Constants.DATASOURCE;
	
	private ThreadLocal<Map<String, Connection>> connContext = new ThreadLocal<Map<String, Connection>>();
	
	private static ConnectionContext INSTANCE = new ConnectionContext();
	
	
	public Connection getConnection()  {
		return getConnection(DEFAULT_JNDINAME) ;
	}
	
	
	/**
	 * 获取Connection
	 * @param aDBName
	 * @return
	 * @throws FrameException 
	 */
	public Connection getConnection(String aDBName )  {
		aDBName = (aDBName == null || "".equals(aDBName)) ?
					DEFAULT_JNDINAME : aDBName ;
		
		
		//spring管理事务
		if(!hasSpringTransaction(aDBName)){
			throw new RuntimeException("未开启事务");
		}
		
		//自己管理事务
		Connection conn = getDBConnection(aDBName);


		return conn ;	
	}
	
	/**
	 * @param :source
	 *            数据源名字
	 */
	private static Connection getDBConnection(String source) {
		
		DataSource datasource = null;
		Connection conn = null;
		try {
			datasource = (DataSource) SpringUtil.getBean(source);
			conn = DataSourceUtils.getConnection(datasource);
			conn.setAutoCommit(false) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception while getting datasource: \n" + e);
		}
		
		return conn;
	}
	/**
	 * close connection
	 * @param aDBName
	 * @return
	 * @throws SQLException
	 * @throws FrameException 
	 */
	public boolean closeConnection(String aDBName ) throws SQLException{
		//spring管理事务
		if(hasSpringTransaction(aDBName)){
			return false;
		}
		
		if (null == connContext.get()) {
			return true;
		}
		for (Entry<String, Connection> entry : connContext.get().entrySet()) {
			Connection conn = entry.getValue();
			try {
				if (null != conn && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connContext.set(new HashMap<String, Connection>());
		return true;
	}
	
	/**
	 * commit operation
	 * @param aDBName
	 * @return
	 * @throws SQLException
	 * @throws FrameException 
	 */
	public boolean commit(String aDBName ) throws SQLException{
		//spring管理事务
		if(hasSpringTransaction(aDBName)){
			return false;
		}
		
		Connection conn = getConnection( aDBName ) ;
		if( conn == null )
			return false ;

		if(!conn.isClosed()){
			conn.commit() ;
		}		

		return true ;
	}
	
	/**
	 * batch commit for multi db evn
	 * @return
	 * @throws SQLException
	 */
	public boolean allCommit() throws SQLException{
		
		if (null == connContext.get()) {
			return false;
		}
		for (Entry<String, Connection> entry : connContext.get().entrySet()) {
			Connection conn = entry.getValue();
			try {
				if(!conn.isClosed()){
					conn.commit() ;
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		connContext.set(null);
		
		return true ;
	}
	
	/**
	 * batch rollback for multi db evn
	 * @return
	 * @throws SQLException
	 */
	public boolean allRollback() throws SQLException{
		if (null == connContext.get()) {
			return false;
		}
		for (Entry<String, Connection> entry : connContext.get().entrySet()) {
			Connection conn = entry.getValue();
			try {
				if(!conn.isClosed()){
					conn.rollback();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connContext.set(null);
		return true;
	}
	
	/**
	 * batch close conn
	 * @return
	 * @throws SQLException
	 */
	public boolean allCloseConnection() throws SQLException {
		
		if (null == connContext.get()) {
			return false;
		}
		for (Entry<String, Connection> entry : connContext.get().entrySet()) {
			Connection conn = entry.getValue();
			try {
				if(!conn.isClosed()){
					conn.close();
				}
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connContext.set(null);
		return true;
	}
	
	/**
	 * rollback operation
	 * @param aDBName
	 * @return
	 * @throws SQLException
	 * @throws FrameException 
	 */
	public boolean rollback(String aDBName ) throws SQLException {
		
		//spring管理事务
		if(hasSpringTransaction(aDBName)){
			return false;
		}
				
		Connection conn = getConnection( aDBName ) ;
		if( conn == null )
			return false ;

		if(!conn.isClosed()){
			conn.rollback() ;
		}		

		return true ;
	}
	
	
	public static ConnectionContext getContext() {
		return INSTANCE;
	}
	
	
	
	
	public static boolean hasSpringTransaction(String source){
		DataSource datasource = (DataSource) SpringUtil.getBean(source);
		return TransactionSynchronizationManager.hasResource(datasource);
	}
	
	public static boolean checkConnectionContext(){
		return false;
	}
	

	public void clearAll()    {}

	@Override
	public void afterPropertiesSet() throws Exception {
		INSTANCE = this;
	}

	
	
}
