package com.twb.robot.common.datasource.service;


public class DbExecuteItem {
	private String sql ="";
	
	private String[] params = null;
	
	public DbExecuteItem(String sql,String[] params){
		this.sql =sql;
		this.params = params;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
	
	
	
}
