package com.twb.robot.common.utils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class SqlParseUtils {

	public static String getNewSql(String sql, Map param, List<String> newSqlPramList) {
		 String regEx = "#\\{(\\w+)\\}";  
	     Pattern pat = Pattern.compile(regEx);  
	     Matcher mat = pat.matcher(sql); 
	     StringBuffer sb = new StringBuffer();
	    while(mat.find()){
	    	mat.appendReplacement(sb, "?") ; 
	    	String paramKey = mat.group(1);
	    	if(!param.containsKey(paramKey)||param.get(paramKey)==null){
	    		throw new RuntimeException("不存在参数:"+paramKey);
	    	}
	    	String paramValue = StringConvertUtils.toString(param.get(paramKey));
	    	newSqlPramList.add(paramValue);
	    }
	    mat.appendTail(sb) ;
	    String newSql = sb.toString();
	    if(StringUtils.isEmpty(newSql)){
	    	newSql = sql;
	    }
	    return newSql;
	}
	
	public static String getNewSqlExistsNull(String sql, Map param, List<String> newSqlPramList) {
		 String regEx = "#\\{(\\w+)\\}";  
	     Pattern pat = Pattern.compile(regEx);  
	     Matcher mat = pat.matcher(sql); 
	     StringBuffer sb = new StringBuffer();
	    while(mat.find()){
	    	mat.appendReplacement(sb, "?") ; 
	    	String paramKey = mat.group(1);
	    	String paramValue = StringConvertUtils.toString(param.get(paramKey));
	    	newSqlPramList.add(paramValue);
	    }
	    mat.appendTail(sb) ;
	    String newSql = sb.toString();
	    if(StringUtils.isEmpty(newSql)){
	    	newSql = sql;
	    }
	    return newSql;
	}
}
