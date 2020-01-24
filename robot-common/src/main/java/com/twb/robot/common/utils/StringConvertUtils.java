package com.twb.robot.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//数据转换类
public class StringConvertUtils {
	public static String toString(Object t) {
		if (t == null)
			return "";
		if (t instanceof BigDecimal) {
			return t.toString();
		} else if (t instanceof Timestamp) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return df.format(t);
		} else if (t instanceof Clob) {
			return ClobToString((Clob) t);
		} else if (t instanceof Blob) {
			return convertBLOBtoString((Blob) t,"ISO-8859-1");
		} else {
			return t.toString().trim();
		}
	}

	public static String ClobToString(Clob clob) {
		String reString = "";
		Reader is = null;
		String s = null;
		BufferedReader br = null;
		try {
			if (clob != null) {
				is = clob.getCharacterStream();
				// 得到流
				br = new BufferedReader(is);
				s = br.readLine();
				StringBuffer sb = new StringBuffer();
				while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
					sb.append(s + "\n");
					s = br.readLine();
				}
				reString = sb.toString();
				if (br != null) {
					br.close();
				}
				if (is != null) {
					is.close();
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return reString;
	}

	public static String convertBLOBtoString(Blob blob) {
		
		return convertBLOBtoString(blob,"");
	}
	public static String convertBLOBtoString(Blob blob,String charsetName) {
		String newStr="";
		try {
			if(charsetName==null||charsetName.isEmpty()){
				newStr = new String(blob.getBytes((long)1, (int)(blob.length())));
			}else{
				newStr = new String(blob.getBytes((long)1, (int)(blob.length())),"ISO-8859-1");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newStr;
	}
}
