package com.twb.robot.common.datasource.common.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.twb.robot.common.datasource.common.SqlMapExe;
import com.twb.robot.common.datasource.common.constant.Constants;
import com.twb.robot.common.utils.StrTools;

public class DAOUtils {
	private static Logger logger = Logger.getLogger(DAOUtils.class);

	private static int WAIT_SECONDS = 200;

	private static String mainSql = "";

	private static LinkedHashMap whereCond = new LinkedHashMap();

	private static HashMap manualVoType = new HashMap();

	private static HashMap manualVoName = new HashMap();


	/** sql异常报错以及参数是否做限制* */
	public final static String SQL_ERROR_FALG = "SQL_ERROR_FALG";

	/** sql异常报错以及参数是否做限制* */
	public final static String SQL_ERROR_MSG = "SQL_ERROR_MSG";

	/** SQL参数限制字符 **/
	public final static String SQL_ERROR_PARAMS = "SQL_ERROR_PARAMS";

	public final static String IFTRUE_T = "T";

	private DAOUtils() {
	}

	/**
	 * 当前系统使用的数据库类型
	 * 
	 * @return
	 */
	public static String getDatabaseType() {
		return Constants.DATABASE_TYPE;
	}

	/**
	 * @param :source
	 *            数据源名字
	 */
	public static Connection getDBConnection(String source) throws DAOSystemException {
		throw new DAOSystemException("不能直接通过此方式获取数据库连接!");
	}

	/**
	 * @param :source
	 *            数据源名字, callerObj 调用者句柄
	 */
	public static Connection getDBConnection(String source, Object callerObj) throws DAOSystemException {
		throw new DAOSystemException("不能直接通过此方式获取数据库连接!");

	}

	/**
	 * Specify informixLockModeWait if you prefer to wait for locks for the
	 * specified amount of time before an exception is thrown.
	 * 
	 * @param seconds
	 *            The number of seconds to wait. If seconds is == 0 the waiting
	 *            is disabled. If seconds is less than 0 then wait indefinitly.
	 * @exception java.sql.SQLException
	 *                If a database access error occurs.
	 */
	public synchronized static void setLockModeToWait(int seconds, Connection conn) throws SQLException {
		String sql = "";
		if (seconds > 0) {
			sql = "SET LOCK MODE TO WAIT " + seconds;
		} else if (seconds == 0) {
			sql = "SET LOCK MODE TO NOT WAIT";
		} else {
			sql = "SET LOCK MODE TO WAIT";
		}

		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
	}

	/**
	 * 直接使用JDBC的方式获取当前数据库的连接.
	 * 
	 * @return
	 */
	public static Connection getDirectConnection(String dbName) throws DAOSystemException {
		throw new DAOSystemException("不能直接通过此方式获取数据库连接!");
	}

	/**
	 * @param :dbConnection
	 *            需要关闭的数据库连接
	 */
	public static void closeConnection(Connection dbConnection) throws DAOSystemException {
		// throw new DAOSystemException("不能直接通过此方式关闭数据库连接!") ;
	}

	/**
	 * @param :dbConnection
	 *            需要关闭的数据库连接, callerObj 调用者句柄
	 */
	public static void closeConnection(Connection dbConnection, Object callerObj) throws DAOSystemException {
		/*
		 * try { if(dbConnection != null && !dbConnection.isClosed()){
		 * if(dbConnection.getTransactionIsolation()==dbConnection.
		 * TRANSACTION_READ_UNCOMMITTED)
		 * dbConnection.setTransactionIsolation(dbConnection.
		 * TRANSACTION_READ_COMMITTED); dbConnection.close(); } } catch
		 * (SQLException se) { throw new
		 * DAOSystemException("SQL Exception while closing " +
		 * "DB connection : \n" + se); }
		 */
	}

	/**
	 * @param :result
	 *            需要关闭的结果集
	 */
	public static void closeResultSet(ResultSet result) throws DAOSystemException {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException se) {
			throw new DAOSystemException("SQL Exception while closing " + "Result Set : \n" + se);
		}
	}

	/**
	 * @param :result
	 *            需要关闭的结果集, callerObj 调用者句柄
	 */
	public static void closeResultSet(ResultSet result, Object callerObj) throws DAOSystemException {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException se) {
			throw new DAOSystemException("SQL Exception while closing " + "Result Set : \n" + se);
		}
	}

	/**
	 * @param :stmt
	 *            需要关闭的陈述
	 */
	public static void closeStatement(PreparedStatement stmt) throws DAOSystemException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se) {
			throw new DAOSystemException("SQL Exception while closing " + "Statement : \n" + se);
		}
	}

	/**
	 * @param :stmt
	 *            需要关闭的陈述, callerObj 调用者句柄
	 */
	public static void closeStatement(PreparedStatement stmt, Object callerObj) throws DAOSystemException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Throwable se) {
			se.printStackTrace();
			throw new DAOSystemException("SQL Exception while closing " + "Statement : \n" + se);
		}
	}

	/**
	 * 
	 * @param stmt
	 * @throws DAOSystemException
	 */
	public static void closeStatement(Statement stmt) throws DAOSystemException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se) {
			throw new DAOSystemException("SQL Exception while closing " + "Statement : \n" + se);
		}
	}

	// /**
	// * 打开脏读设置，允许一个事务可以读取另一个事务尚未提交的修改。
	// *
	// * @param conn
	// * @throws DAOSystemException
	// */
	// public static void openDirtyRead(Connection conn) throws
	// DAOSystemException {
	// try {
	// if (conn != null) {
	// if (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_INFORMIX)) {
	// conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
	// } else if (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_ORACLE)) {
	// conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	// /*
	// * String sql = "SET TRANSACTION READ ONLY"; Statement stmt = null; try {
	// stmt =
	// * conn.createStatement(); stmt.execute(sql); } finally { if (stmt !=
	// null) stmt.close(); }
	// */
	// }
	// }
	// } catch (SQLException se) {
	// throw new DAOSystemException("SQL Exception while openDirtyRead : " +
	// se);
	// }
	// }

	// /**
	// * 关闭脏读设置，返回提交读的状态。 一个事务读取另一个事务全部提交的修改。
	// *
	// * @param conn
	// * @throws DAOSystemException
	// */
	// public static void closeDirtyRead(Connection conn) throws
	// DAOSystemException {
	// throw new DAOSystemException("不能直接通过此方式关闭数据库连接!");
	// // try {
	// // if (conn != null) {
	// // if (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_INFORMIX)) {
	// // conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	// // } else if
	// (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_ORACLE)) {
	// // //conn.commit();
	// // conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	// // }
	// // }
	// // } catch (SQLException se) {
	// // throw new DAOSystemException("SQL Exception while openDirtyRead : "
	// // + se);
	// // }
	// }

	/**
	 * 获取当前的时间。当前实现从应用服务器取时间。 如果要统一取数据库时间，也将从这里统一获取。
	 * 
	 * @return
	 */
	public static java.sql.Date getCurrentDate() {

		return new java.sql.Date(System.currentTimeMillis());

	}

	/**
	 * 获取当前的时间。当前实现从应用服务器取时间。 如果要统一取数据库时间，也将从这里统一获取。
	 * 
	 * @return
	 */
	public static java.sql.Timestamp getCurrentTimestamp() {

		return new java.sql.Timestamp(System.currentTimeMillis());

	}

	/**
	 * 获取当前的时间。当前实现从应用服务器取时间。 并且通过统一的格式化。
	 * 
	 * @return
	 */
	public static String getFormatedDate() {

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		return dateFormator.format(new java.sql.Date(System.currentTimeMillis()));

	}

	/**
	 * 获取当前的数据库时间。 并且通过统一的格式化。
	 * 
	 * @author RyoUehara 添加时间:090504
	 * @return
	 */
	public static String getFormatedDbDate() throws Exception {
		// modify by RyoUehara 090504 itime改为取数据库的sysdate
		// ComDAO commandDAO=(ComDAO)ComServiceDAO.getInstance().getComDAO("");
		mainSql = "select to_char(current,'%Y-%m-%d %H:%M:%S') from dual";
		if (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_INFORMIX)) {
			mainSql = "select to_char(current,'%Y-%m-%d %H:%M:%S') from dual";
		} else if (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_ORACLE)) {
			mainSql = "select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual";
		}
		return SqlMapExe.getInstance().querySingleValue(mainSql);

	}

	/**
	 * 获取当前的时间。当前实现从应用服务器取时间。 并且通过统一的格式化。
	 * 
	 * @return
	 */
	public static String getShortFormatedDate() {

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_FORMAT);
		return dateFormator.format(new java.sql.Date(System.currentTimeMillis()));

	}

	/**
	 * 获取默认的失效的时间
	 * 
	 * @return
	 */
	public static java.sql.Date getDefaultExpiredDate() {

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		java.util.Date tDate = dateFormator.parse(Constants.DEFAULT_EXPIRED_DATE, new ParsePosition(0));
		return new java.sql.Date(tDate.getTime());

	}

	/**
	 * 获取默认的失效的时间 并且通过统一的格式化。
	 * 
	 * @return
	 */
	public static String getFormatedExpiredDate() {

		return Constants.DEFAULT_EXPIRED_DATE;

	}

	/**
	 * 通过统一的格式将文本转换成Date。输入为日期和时间。
	 * 
	 * @return
	 */
	public static Date parseDateTime(String sdate) {
		if (null == sdate || "".equals(sdate))
			return null;

		// 只有日期类型
		if (sdate.length() <= 10) {
			return parseDate(sdate);
		}

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

		java.util.Date tDate = dateFormator.parse(sdate, new ParsePosition(0));
		if (tDate == null)
			return null;

		return new java.sql.Date(tDate.getTime());

	}

	/**
	 * 
	 * @return
	 */
	public static Date SPCPparseDateTime(String sdate) {
		if (null == sdate || "".equals(sdate))
			return null;

		// 只有日期类型
		if (sdate.length() <= 10) {
			return parseDate(sdate);
		}

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_TIME_FORMAT_14);

		java.util.Date tDate = dateFormator.parse(sdate, new ParsePosition(0));
		if (tDate == null)
			return null;

		return new java.sql.Date(tDate.getTime());

	}

	/**
	 * 通过统一的格式将文本转换成Date。输入为日期和时间。
	 * 
	 * @return
	 */
	public static Date parseDateTime(Object sdate) {
		if (null == sdate)
			return null;
		return parseDateTime((String) sdate);
	}

	/**
	 * 
	 * @return
	 */
	public static Date SPCPparseDateTime(Object sdate) {
		if (null == sdate)
			return null;
		return SPCPparseDateTime((String) sdate);
	}

	public static Timestamp parseTimestamp(Object sdate) {
		if (null == sdate)
			return null;
		return parseTimestamp((String) sdate);
	}

	/**
	 * 通过统一的格式将文本转换成Timestamp。输入为日期和时间。
	 * 
	 * @return
	 */
	public static Timestamp parseTimestamp(String sdate) {
		if (null == sdate || "".equals(sdate))
			return null;

		java.util.Date tDate = null;

		// 只有日期类型
		if (sdate.length() <= 10) {
			SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_FORMAT);

			tDate = dateFormator.parse(sdate, new ParsePosition(0));
		} else {

			SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

			tDate = dateFormator.parse(sdate, new ParsePosition(0));
		}

		if (tDate == null)
			return null;

		return new java.sql.Timestamp(tDate.getTime());

	}

	/**
	 * 通过统一的格式将文本转换成Date。输入为日期。
	 * 
	 * @return
	 */
	public static Date parseDate(String sdate) {
		if (null == sdate || "".equals(sdate))
			return null;

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_FORMAT);

		java.util.Date tDate = dateFormator.parse(sdate, new ParsePosition(0));
		if (tDate == null)
			return null;

		return new java.sql.Date(tDate.getTime());
	}

	/**
	 * 将字CLOB转成STRING类型
	 * 
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String ClobToString(Clob clob) {
		if (clob == null)
			return null;
		String reString = "";
		Reader is;
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			is = clob.getCharacterStream();
			// 得到流
			br = new BufferedReader(is);
			String s = br.readLine();

			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		reString = sb.toString();
		return reString;
	}

	/**
	 * 将Date转换成统一的日期格式文本。
	 * 
	 * @return
	 */
	public static String getFormatedDate(Date date) {
		if (null == date)
			return "";

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_FORMAT);
		return dateFormator.format(new java.sql.Date(date.getTime()));
	}

	/**
	 * 将Date转换成统一的日期时间格式文本。
	 * 
	 * @return
	 */
	public static String getFormatedDateTime(Date date) {
		if (null == date)
			return "";

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		return dateFormator.format(new java.sql.Date(date.getTime()));
	}

	/**
	 * 将Date转换成统一的日期时间格式文本。
	 * 
	 * @return
	 */
	public static String getFormatedDateTime(Timestamp stamp) {
		if (null == stamp)
			return "";

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		return dateFormator.format(new java.sql.Date(stamp.getTime()));
	}

	/**
	 * 将Date转换成统一的日期格式文本。 格式：yyyy-mm-dd
	 * 
	 * @return
	 * @author suns
	 */
	public static String getFormatedDate(Timestamp stamp) {
		if (null == stamp)
			return "";

		SimpleDateFormat dateFormator = new SimpleDateFormat(Constants.DATE_FORMAT);
		return dateFormator.format(new java.sql.Date(stamp.getTime()));
	}

	/**
	 * 提供删除字符串前后的空格的功能
	 * 
	 * @return
	 */
	public static String trimStr(String str) {

		if (null == str)
			return "";
		else
			return str.trim();

	}

	/**
	 * 用户输入的查询条件中，转义、过滤特殊的字符。 informix下的转义符为"\" 需要转义的符号： ' - % \
	 * 
	 * @param condStr
	 * @return
	 */
	public static String filterQureyLikeCond(String condStr) {
		if (condStr == null || "".equals(condStr))
			return "";
		condStr = Pattern.compile("(\\')").matcher(condStr).replaceAll("''");
		// condStr = Pattern.compile("(\\\\)").matcher(condStr).replaceAll(
		// "\\\\\\\\");
		// condStr =
		// Pattern.compile("(\\-)").matcher(condStr).replaceAll("\\\\-");
		// condStr =
		// Pattern.compile("(\\%)").matcher(condStr).replaceAll("\\\\%");
		return condStr;
	}

	/**
	 * 用户输入的查询条件中，转义、过滤特殊的字符。 需要转义的符号： '
	 * 
	 * @param condStr
	 * @return
	 */
	public static String filterQureyCond(String condStr) {
		if (condStr == null || "".equals(condStr))
			return "";
		condStr = Pattern.compile("(\\')").matcher(condStr).replaceAll("''");

		return condStr;
	}

	/**
	 * 说明：获取系统参数－－传入参数ｃｏｄｅ得到值
	 * 
	 * @param paramCode
	 * @return
	 */
	public static String getSysParamValue(String paramCode) {

		if ("".equals(paramCode.trim())) {
			throw new DAOSystemException("取系统参数出错:传入的paramCode为空!");
		}
		String GET_PARAM_VALUE = "SELECT param_val FROM dc_system_param " + " WHERE param_code='" + paramCode + "'";

		PreparedStatement stmt = null;
		ResultSet result = null;
		Connection conn = null;
		String paramVal = "";
		try {
			conn = ConnectionContext.getContext().getConnection(Constants.DATASOURCE);
			stmt = conn.prepareStatement(getFilterSQL(GET_PARAM_VALUE));
			result = stmt.executeQuery();
			if (result.next()) {
				paramVal = result.getString("param_val");
			} else {
				throw new DAOSystemException("取系统参数出错:不存在该参数:param_code:" + paramCode);
			}
			return paramVal;
		} catch (SQLException se) {
			se.printStackTrace();
			throw new DAOSystemException("SQLException while execute " + GET_PARAM_VALUE + ":\n" + se.getMessage(), se);
		} finally {
			DAOUtils.closeResultSet(result);
			DAOUtils.closeStatement(stmt);
		}
	}

	/**
	 * 获取数据库时间
	 * 
	 * @return
	 */
	public static String getDBCurrentTime() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			conn = ConnectionContext.getContext().getConnection(Constants.DATASOURCE);
			String selStr = " select to_char(current,'%Y-%m-%d %H:%M:%S') dbTime from dual ";
			if (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_INFORMIX)) {
				selStr = " select to_char(current,'%Y-%m-%d %H:%M:%S') dbTime from dual ";
			} else if (DAOUtils.getDatabaseType().equals(Constants.DB_TYPE_ORACLE)) {
				selStr = " select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') dbTime from dual ";
			}
			stmt = conn.prepareStatement(selStr);
			result = stmt.executeQuery();
			String dbTime = "";
			if (result.next()) {
				dbTime = result.getString("dbTime");
			}

			return dbTime;
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOSystemException(" 查询数据库的当前时间报错 :\n" + e.getMessage(), e);
		} finally {
			DAOUtils.closeResultSet(result);
			DAOUtils.closeStatement(stmt);
			// conn 由框架自己关闭。
		}
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static void setBlob(java.sql.Blob wrapBlob, byte[] buffer) throws SQLException {
		OutputStream outStream = null;
		try {
			/*
			 * if (wrapBlob instanceof
			 * weblogic.jdbc.vendor.oracle.OracleThinBlob)
			 * {//这里仅能在weblogic下应用，在tomcat下是会返回false OracleThinBlob blob =
			 * (OracleThinBlob) wrapBlob; outStream =
			 * blob.getBinaryOutputStream(); } else if (wrapBlob instanceof
			 * oracle.sql.BLOB) { oracle.sql.BLOB blob = (oracle.sql.BLOB)
			 * wrapBlob; outStream = blob.getBinaryOutputStream(); }
			 */
			if (outStream != null) {
				outStream.write(buffer, 0, buffer.length);
				outStream.flush();
			}
		} catch (IOException ex) {
			throw new DAOSystemException(" 大对象BLOB数据库IO报错 :\n" + ex.getMessage(), ex);
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException ex) {
				}
			}
		}

	}
	//
	// public static String tranferCode(String param_value){
	// CrmParamsConfig crmParamsConfig = CrmParamsConfig.getInstance();
	// String need_tansfer_code =
	// crmParamsConfig.getParamValue("NEED_TANSFER_CODE");
	// if("yes".equals(need_tansfer_code) &&
	// StringUtils.isNotEmpty(param_value))
	// {
	// try {
	// param_value = new String(param_value.getBytes("iso-8859-1"), "gbk");
	// } catch (UnsupportedEncodingException e) {
	//
	// e.printStackTrace();
	// }
	// }
	// return param_value;
	// }

	public static boolean ipIsValid(String ipSection, String ip) {
		if (ipSection == null)
			throw new NullPointerException("IP段不能为空！");
		if (ip == null)
			throw new NullPointerException("IP不能为空！");
		ipSection = ipSection.trim();
		ip = ip.trim();
		final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
		if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP))
			return false;
		int idx = ipSection.indexOf('-');
		String[] sips = ipSection.substring(0, idx).split("\\.");
		String[] sipe = ipSection.substring(idx + 1).split("\\.");
		String[] sipt = ip.split("\\.");
		long ips = 0L, ipe = 0L, ipt = 0L;
		for (int i = 0; i < 4; ++i) {
			ips = ips << 8 | Integer.parseInt(sips[i]);
			ipe = ipe << 8 | Integer.parseInt(sipe[i]);
			ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		}
		if (ips > ipe) {
			long t = ips;
			ips = ipe;
			ipe = t;
		}
		return ips <= ipt && ipt <= ipe;
	}

	// 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理
	public static long ipToLong(String strIP) {
		long[] ip = new long[4];
		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * 判空操作
	 * 
	 * @param o
	 * @return ret
	 * @author luowenming
	 */
	public static boolean isEmpty(Object o) {
		boolean ret = false;
		if (o != null) {
			if (o instanceof String) {
				String o2String = (String) o;
				if (o2String == null || "".equals(o2String)) {
					return true;
				}
			} else if (o instanceof List) {
				List o2List = (List) o;
				if (o2List == null || o2List.size() <= 0) {
					return true;
				}
			} else if (o instanceof Map) {
				Map o2Map = (Map) o;
				if (o2Map == null || o2Map.size() <= 0) {
					return true;
				}
			}
		} else {
			return true;
		}

		return ret;
	}

	// 根据随机数长度生成随机数
	public static String genRandowNum(int pwdLen) {

		if (pwdLen <= 0) {
			return "";
		}
		int maxNumber = 1;
		int minNumber = 0;
		int randomValue = 0;

		// 生成随机数能取的最大值+1, 如1000000
		for (int i = 0; i < pwdLen; i++) {
			maxNumber = maxNumber * 10;
		}

		// 生成随机数能取的最小数-1: 如 99999;
		if (pwdLen > 1) {
			minNumber = 1;
			for (int i = 0; i < pwdLen - 1; i++) {
				minNumber = minNumber * 10;
			}
			minNumber = minNumber - 1;
		}

		while (true) {
			randomValue = (int) Math.ceil(Math.random() * maxNumber);
			if ((randomValue > minNumber) && (randomValue < maxNumber)) {
				return randomValue + "";
			}

		}

	}

	public static String formatSqlErr(String sql) {
		String sqlErr = "SQLException while execSQL:" + sql + "\n";
		if (!DAOUtils.isNeedDetect(sql)) {
			return sqlErr;
		}
		String msg = DAOUtils.getSqlErrorMsg();
		if (StringUtils.isEmpty(msg)) {
			return sqlErr;
		}
		return msg;
	}

	public static String formatSqlErr(String sql, String sqlErr) {
		// String sqlErr = "SQLException while execSQL:" + sql + "\n";
		if (StringUtils.isNotEmpty(sql) && !DAOUtils.isNeedDetect(sql)) {
			return sqlErr;
		}
		String msg = DAOUtils.getSqlErrorMsg();
		if (StringUtils.isEmpty(msg)) {
			return sqlErr;
		}
		return msg;
	}

	public static String formatErr(String sqlErr) {
		// String sqlErr = "SQLException while execSQL:" + sql + "\n";
		String msg = DAOUtils.getSqlErrorMsg();
		if (StringUtils.isEmpty(msg)) {
			return sqlErr;
		}
		return msg;
	}

	/**
	 * 用于限制SQL参数
	 */
	private static void dealParam(String posi, String param) {
		if (!DAOUtils.getDetectFlag()) {
			return;
		}
		String limitParam = param.trim();
		if (param.startsWith("%")) {
			limitParam = param.substring(1);
		}
		if (param.endsWith("%")) {
			limitParam = limitParam.substring(0, limitParam.length() - 1);
		}

		String sqlErrorParams = getSqlErrorParams();
		if (StringUtils.isEmpty(sqlErrorParams)) {
			return;
		}
		if (StrTools.isIndexOf(sqlErrorParams, "%%", ";")) {
			if (param.startsWith("%") || param.endsWith("%")) {
				// like过来的数据，不能连续两个%%情况出现
				if (param.indexOf("%%") > -1) {
					// throw new BusiException("无法查询到数据！");
				}
			}
		}

		String[] limitParams = sqlErrorParams.split(";");
		if (limitParams.length <= 0) {
			return;
		}
		for (String str : limitParams) {
			if (StringUtils.isEmpty(str.trim()) || "%%".equals(str.trim())) {
				continue;
			}
			if (limitParam.toLowerCase().contains(str.trim().toLowerCase())) {
				// throw new BusiException("无法查询到数据！");
			}
		}
	}

	public static void dealParam(String posi, Object obj) {
		if (obj != null && "java.lang.String".equals(obj.getClass().getName())) {
			DAOUtils.dealParam(posi, (String) obj);
		}
	}

	public static void dealParam(int posi, Object obj) {
		if (obj != null && "java.lang.String".equals(obj.getClass().getName())) {
			DAOUtils.dealParam(String.valueOf(posi), (String) obj);
		}
	}

	/**
	 * 用于判断SQL是否需要进行检查
	 * 
	 * @param sql
	 * @return
	 */
	private static boolean isNeedDetect(String sql) {
		// 从缓存中获取的数据补进行检查
		if (sql.indexOf("dc_system_param") > -1 || sql.indexOf("DC_SYSTEM_PARAM") > -1 || sql.indexOf("dc_public") > -1
				|| sql.indexOf("DC_PUBLIC") > -1) {
			return false;
		}
		return DAOUtils.getDetectFlag();
	}

	/**
	 * 获取开关打开标示
	 * 
	 * @return
	 */
	private static boolean getDetectFlag() {
		String flag = "F";// DcSystemParamUtil.getSysParamByCache(SQL_ERROR_FALG);
		if (!IFTRUE_T.equals(flag)) {
			return false;
		}
		return true;
	}

	/**
	 * 用于返回错误信息
	 * 
	 * @return
	 */
	private static String getSqlErrorMsg() {
		return "";// DcSystemParamUtil.getSysParamByCache(SQL_ERROR_MSG);
	}

	/**
	 * 用于返回错误信息
	 * 
	 * @return
	 */
	private static String getSqlErrorParams() {
		return "";// DcSystemParamUtil.getSysParamByCache(SQL_ERROR_PARAMS);
	}

	public static String getFilterSQL(String sql) {
		// if( sql.trim().startsWith("PAGEMODEL")){
		// return sql.replaceAll("PAGEMODEL","");
		// }
		if ("true".equalsIgnoreCase(Constants.SHOW_SQL))
			logger.debug("newsql:[" + sql + "]");
		return sql;
		// return singleton().convertSql(sql, CRM_DB);

	}

}
