package com.twb.robot.common.datasource.common.constant;

public class Constants {

	public static final String DATASOURCE = "dataSource";

	// 系统数据库类型
	public static String DATABASE_TYPE = "INFORMIX";

	public static final String DB_TYPE_INFORMIX = "INFORMIX";
	public static final String DB_TYPE_ORACLE = "ORACLE";
	public static final String DB_TYPE_MYSQL = "MYSQL";


	// 系统默认日期格式
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	// 系统默认日期时间格式
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 数据库默认日期时间格式
	public static final String DATE_TIME_FORMAT_DB = "yyyy-MM-dd HH24:mi:ss";
	// 8位日期格式
	public static final String DATE_FORMAT_8 = "yyyyMMdd";
	// 14位日期时间格式
	public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";
	// 12位日期格式
	public static final String DATE_TIME_FORMAT_12 = "yyMMddHHmmss";

	// 默认失效时间
	public static final String DEFAULT_EXPIRED_DATE = "2030-1-1 00:00:00";

	// 默认失效时间
	public static final String DEFAULT_EXPIRED_DATE_OTHER = "2030-12-31";

	// 系统数据库类型
	public static final String CRM_DATABASE_TYPE = "oracle";

	// 自动获取的web-inf绝对路径
	public static String WEB_INF_PATH = "CRM_WEB_INF_PATH";

	// 自动获取的web-inf绝对路径
	public static String SHOW_SQL = "true";

	// 自动获取的web-inf绝对路径
	public static String SHOW_METHOD_TIME = "true";

	// 分页记录数最大限制数
	public static int MAX_PAGE_SIZE = 30;

	// 用于频繁获取的列表的索引
	public static int CRM_UTIL_ZERO = 0;

	// 分页记录数默认值
	public static int DEFAULT_PAGE_SIZE = 20;

	// 查询最大记录数限制
	public static int MAX_QUERY_SIZE = 3000;

	// #系统项目编码如TIANJIN,CHONGQING等
	public static String TJ_PROJECT_CODE = "TIANJIN";
	public static String CQ_PROJECT_CODE = "CONGQING";

	public static String CRM_ROOT_ORGID_STR = "rootOrgId";

}
