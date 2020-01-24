package com.twb.robot.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

@SuppressWarnings( { "unchecked","rawtypes"})
public final class StrTools {

	private StrTools() {
	}
	
	/**
     * 用16进制表示给定的byte数组
     * @param b byte[]
     * @return String
     */
    public static String bytes2HexString(byte[] b) {
        String hexStr = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            hexStr += hex;
        }
        return hexStr.toUpperCase();
    }

    /**
     * 在字符串str左边补齐0直到长度等于length
     *
     * @param str
     * @param len
     * @return
     */
    public static String enoughZero(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }
    
    
    public static String concat(String seperator, String str1, String str2) {
		if (isEmpty(str1) && isEmpty(str2)) {
			return "";
		} else if (isEmpty(str1) && isNotEmpty(str2))
			return str2;
		else if (isNotEmpty(str1) && isEmpty(str2))
			return str1;
		else
			return str1 + seperator + str2;
	}
    
	public static String join(String seperator, String[] strings) {
		if(strings==null)
			return "";
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
		for (int i = 1; i < length; i++) {
			buf.append(seperator).append(strings[i]);
		}
		return buf.toString();
	}

	public static String join(String seperator, Iterator objects) {
		StringBuffer buf = new StringBuffer();
		if (objects.hasNext())
			buf.append(objects.next());
		while (objects.hasNext()) {
			buf.append(seperator).append(objects.next());
		}
		return buf.toString();
	}
	
	
	public static String joinWithQMarks(String seperator, String[] strings) {
		
		List list=Arrays.asList(strings);
		return joinWithQMarks(seperator,list.iterator());
	}
	
	public static String joinWithQMarks(String seperator, Iterator objects) {
		StringBuffer buf = new StringBuffer();
		if (objects.hasNext())
			buf.append("'").append(objects.next()).append("'");
		while (objects.hasNext()) {
			buf.append(seperator).append("'").append(objects.next()).append("'");
		}
		return buf.toString();
	}

	public static boolean booleanValue(String tfString) {
		String trimmed = tfString.trim().toLowerCase();
		return trimmed.equals("true") || trimmed.equals("t");
	}
	
	public static boolean isEqual(String o, boolean c){
		
		if(o==null||"".equals(o))
			return false;
		return o.equals(String.valueOf(c).toLowerCase());
		
	}
	
	public static boolean isEqual(String o, String c){
		if(StrTools.isEmpty(o)){
			o = ""; 
		}
		if(StrTools.isEmpty(c)){
			c = ""; 
		} 
		return o.equals(c); 
	}

	public static String toString(Object[] array) {
		int len = array.length;
		if (len == 0)
			return "";
		StringBuffer buf = new StringBuffer(len * 12);
		for (int i = 0; i < len - 1; i++) {
			buf.append(array[i]).append(", ");
		}
		return buf.append(array[len - 1]).toString();
	}

	public static String[] toArray(List list) {
		int len = list.size();
		if (len == 0)
			return null;
		String[] array = new String[len];
		for (int i = 0; i < len; i++) {
			array[i] = list.get(i).toString();
		}
		return array;
	}

	public static boolean isNotEmpty(String string) {
		return string != null && string.length() > 0;
	}

	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}

	public static String truncate(String string, int length) {
		if (string.length() <= length) {
			return string;
		} else {
			return string.substring(0, length);
		}
	}

	
	public static  String getStrValue(Map m , String name) {
		if(m == null || m.isEmpty()){ return ""; }
		Object t = m.get(name) ;
		if(t == null )
			return "" ;
		return (m.get(name).toString()).trim() ;
	}
	
	public static String toUpperCase(String str) {
		return str == null ? null : str.toUpperCase();
	}

	public static String toLowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}

	public static HashMap toMap(String[] array) {

		if(array==null)
			return null;
		int len = array.length;
		
		
		if (len == 0)
			return null;
		
		HashMap map=new HashMap();
		
		for (int i = 0; i < len; i++) {
			map.put(array[i],array[i]);
		}
		return map;
		
	}/*--repl将被with替换 -1为替换字符串text中所有的repl--*/
	public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }
	/*--max为被替换的个数--*/
	public static String replace(String text, String repl, String with, int max) {
        if (text == null || repl==null || with == null || max == 0) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0, end = 0;
        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

	/**
	 * 将两个字符串转换成数字类型 返回大的那个
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String max(String str1,String str2){
		int num1 = Integer.parseInt(str1);
		int num2 = Integer.parseInt(str2);
		if(num1>num2){
			return str1;
		}else{
			return str2;
		} 
	}
	
	/**
	 * 将两个字符串转换成数字类型 返回小的那个
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String min(String str1,String str2){
		int num1 = Integer.parseInt(str1);
		int num2 = Integer.parseInt(str2);
		if(num1>num2){
			return str2;
		}else{
			return str1;
		} 
	}
	/**
	 * 校验是否为数字
	 * */
	public static  boolean isNum(String num) {		
		
	   if(num==null ||num.equals(""))return false;
	   
	   if(num.startsWith("-")){
		   return isNum(num.substring(1));
	   }
	   
	   Pattern pattern = Pattern.compile("^\\d+$");
	   Matcher isNum = pattern.matcher(num);
	   return isNum.matches();
	}
	
	/**
	 * 校验字符串是否匹配正则表达式 add by xiaof
	 * */
	public static  boolean isMatche(String checkStr,String regex) {		
		
	   if(checkStr==null ||checkStr.equals(""))return false;
	   
	   
	   Pattern pattern = Pattern.compile(regex);
	   Matcher isMathe = pattern.matcher(checkStr);
	   return isMathe.matches();
	}

	//浮点数
	public static boolean isFloat(String num) {
		if(isNum(num))return true;
		String reg="^[1-9]\\d*\\.\\d*|0\\.\\d*[0-9]\\d*$";
		
		if(num==null ||num.equals(""))return false;
		   
		if(num.startsWith("-")){
			return isMatche(num.substring(1), reg);
		}
		return isMatche(num, reg);
	}
	//手机号码
	public static  boolean isPhoneNo(String checkStr) {		
		   String regex="^1[3|4|5|8][0-9]\\d{8}$";
		   return isMatche(checkStr,regex);
	}
	//防止如包含误判. 如判断"1234,222"是否包含"123",如果直接用String.indexOf()方法,会认为包含
	//我们在源字符串和子字符串的前后都加上分隔标志,变成判断",1234,222,"中是否包含",123,",这样就不会误判了
	
	/**
	* @Title: isIndexOf
	* @Description: 判断包含关系
	* @param   str  源字符串
	* @param   subStr 子字符串
	* @param   splitFlag 源字符串的分隔标志
	* @return  true 包含   false  不包含
	* @throws
	*/
	public static boolean isIndexOf(String str, String subStr, String splitFlag) {
		
		if (isEmpty(str)) {
			return false;
		}
		
		//子字符串为空,认为包含
		if (isEmpty(subStr)) {
			return true;
		}
		
		if (null == splitFlag) {
			splitFlag = "";
		}
		
		String tmpStr = splitFlag+str+splitFlag;
		String tmpSubStr = splitFlag+subStr+splitFlag;
		
		return tmpStr.indexOf(tmpSubStr) >= 0;
		
	}
	
	/**
	* @Title: isTelNumbers
	* @Description: 判断判断是否为电话号码，规则：手机为1开头11位数字，固话为包含-的小于13位的两组数字
	* @param   str  源字符串
	* @throws
	*/
	
	public static boolean isTelNumbers(String num){
		if(isEmpty(num)){
			return false;
		}
		if(num.indexOf("-")>0){
			String[] numsplit=num.split("-");
			if(numsplit.length!=2){return false;}
			if(isNum(numsplit[0])&&isNum(numsplit[1])&&num.length()<=13&&numsplit[0].length()<=4){
				return true;
			}
			return false;
		}
		else{
			String match="[1][0-9]{10}";
			return isMatche(num, match);
		}
	}
	public static void main1(String[] args) {
		System.out.println(StrTools.isFloat("0"));
		System.out.println(StrTools.isFloat("4.3"));
		System.out.println(StrTools.isFloat("43.4"));
		System.out.println(StrTools.isFloat("4.3.4"));
		System.out.println(StrTools.isFloat("-2.3"));
		System.out.println(StrTools.genRandowNum(2));
		
//		System.out.println(isNum("-2"));
	}
	
	/**
	 * 克隆String
	 * @param warn
	 * @return
	 */
	public static Object cloneMySelf(Object object) {
		Object cloneObj = null;
		ObjectOutputStream oo = null;
		ObjectInputStream oi = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(out);
			oo.writeObject(object);
			ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			oi = new ObjectInputStream(in);
			cloneObj = (Object) oi.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(oo!=null){
				try {
					oo.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			if(oi!=null){
				try {
					oi.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}	
		}
		return cloneObj;
	}

	//把一个HashMap拼凑成key:val|..的格式
	public static String hashMapToString(HashMap sqlGetKeyVals){
		StringBuffer data=new StringBuffer("");
		if(sqlGetKeyVals!=null)
		for(Iterator it=sqlGetKeyVals.entrySet().iterator();it.hasNext();){
			Map.Entry map=(Map.Entry)it.next();
			String key=(String)map.getKey();
			String val=(String)map.getValue();
			data.append(key);
			data.append(":");
			data.append(val);
			data.append("|");
		}
		return data.toString();
	}
	//把一个key:val|..格式的String拼凑成HashMap
	public static HashMap StringToHashMap(String str){
		if(str==null || str.equals(""))return null;
		String []_hashmap=str.split("\\|");
		if(_hashmap==null || _hashmap.length==0)return null;
		HashMap ret=new HashMap();
		for(int i=0;i<_hashmap.length;i++){
			String it=_hashmap[i];
			if(it==null || it.equals(""))continue;
			String []_maps=it.split(":");
			if(_maps==null || _maps.length<1)continue;
			String key=_maps[0];
			String val="";
			if(_maps.length == 2)
			 val=_maps[1];
			ret.put(key, val);
		}
		return ret;
	}
	
	//把一个key:val|..格式的String拼凑成HashMap
	public static HashMap StringToHashMap(String str,String regex1, String regex2){
		if(str==null || str.equals(""))return null;
		String []_hashmap=str.split(regex1);
		if(_hashmap==null || _hashmap.length==0)return null;
		HashMap ret=new HashMap();
		for(int i=0;i<_hashmap.length;i++){
			String it=_hashmap[i];
			if(it==null || it.equals(""))continue;
			String []_maps=it.split(regex2);
			if(_maps==null || _maps.length<1)continue;
			String key=_maps[0];
			String val="";
			if(_maps.length == 2)
			 val=_maps[1];
			ret.put(key, val);
		}
		return ret;
	}
	
	/** 将Map中的值按照规则进行替换 ，为了保证替换的正确性，key最好用一个符包围起来，如：acc_nbr 用${acc_nbr} */
	public static Map mapValReplVal(Map data, Map source, String key){
		if(data == null || source == null || data.isEmpty() || source.isEmpty()){ return source; }
		
		Iterator it = data.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry me = (Map.Entry) it.next();
			String valKey = (String)me.getKey();
			Object valObj = me.getValue();
			if(valObj != null && !(valObj instanceof String)) { continue; }
			String valVal = (String)valObj;
			if(StrTools.isEmpty(valVal)){
				valVal = "";
			}
			if(StrTools.isNotEmpty(key)){
				valKey = StrTools.replace(key, "key", valKey);
			}
			Iterator retIt = source.entrySet().iterator();
			while(retIt.hasNext()){
				Map.Entry retMe = (Map.Entry) retIt.next();
				String retKey = (String)retMe.getKey();
				String retVal = (String)retMe.getValue();
				source.put(retKey, StrTools.replace(retVal, valKey, valVal));
			}
		}
		
		return source;
	}
	
	/**
	 * 判断s 是否包含 value
	 * @param s
	 * @param value
	 * @return boolean
	 */
	public static boolean isIncluded(String[] s,String value)
	{
		boolean result =false;
		if(s==null||s.length==0){
			return false;
		}
		for(int i=0;i<s.length;i++)
		{
			 if(value!=null&&value.equals(s[i]))
		     {
		    	 result=true;
		    	 return result;
		     }
		}
		return result;
	}

	/**
	 * //根据随机数长度生成随机数
	 * @param pwdLen
	 * @return
	 */
	public static String genRandowNum(int pwdLen){

		if (pwdLen <= 0) {
			return "";
		}
		int  maxNumber = 1;
		int minNumber = 0;
		int randomValue = 0;

		//生成随机数能取的最大值+1, 如1000000
		for(int i=0; i< pwdLen; i++){
			maxNumber = maxNumber*10;
    	}

		//生成随机数能取的最小数-1: 如 99999;
		if(pwdLen > 1){
    		minNumber = 1;
    		for(int i=0; i<pwdLen-1; i++){
    			minNumber = minNumber*10;
    		}
    		minNumber = minNumber - 1;
		}

		while(true){
    		randomValue = (int)Math.ceil(Math.random()*maxNumber);
    		if((randomValue > minNumber) && (randomValue < maxNumber)){
    			return randomValue +"" ;
    		}

		}

	}
	
	/**
	 * 例如字符串为str='1,2,3,,4,3,2,5,,,2,7'经过处理后的结果为str='1,2,3,4,5,7'
	 * @param orginalStr 数组
	 * @param seperator 分割符
	 * @return
	 */
	public static String removeRedundancyItem(String orginalStr,
			String seperator) {
		String retStr = "";
		int i = 0;
		if (StrTools.isEmpty(orginalStr) || StrTools.isEmpty(seperator)) {
			return retStr;
		}
		String str[] = orginalStr.split(seperator);
		for (String string : str) {
			if (StrTools.isNotEmpty(string)) {
				if (retStr.indexOf(string) > -1) {
					continue;
				} else {
					if (i != 0&&StrTools.isNotEmpty(retStr))
						retStr = retStr + seperator;
					retStr = retStr + string;
				}
			}
			i++;
		}
		return retStr;
	}
 
	
	/**
	 * 替换str中的{:fieldName}成KeyVals 中的键值  add by xiaof 111012
	 */
	public static String convertKeyValsStr(Map KeyVals,String str){
//		String nameEpx = "\\u007B:([a-zA-Z0-9_\\u002E\\u002A]+)\\}";
		
		if(str==null)return "";
		//1.不存在需要转换则直接返回
		if(str.indexOf("{")<0)return str;
		if(KeyVals==null||KeyVals.isEmpty())return str;
		
		for(Iterator it=KeyVals.entrySet().iterator();it.hasNext();){
			Map.Entry map=(Map.Entry)it.next();
			String key=(String)map.getKey();
			Object val=map.getValue();
			if(val instanceof String){
				String _val=(String)val;
				String newFieldName="{:"+key+"}";
				str=str.replace(newFieldName, _val);
			}
		}
		//过滤无效{:标签} 
		str=str.replaceAll("\\{\\:[\\w]+\\}", "");
		return str;
	}
	
	/**
	 * 从KeyVals 中提取键名生成 关键字数据String[] add by xiaof 111025
	 */
	public static String[] getMapToKey(Map  KeyVals){
		if(KeyVals==null||KeyVals.isEmpty()){return new String[0];}
		
		List keyList=new ArrayList();
		for(Iterator it=KeyVals.keySet().iterator();it.hasNext();){
			Object key=it.next();
			if ( key instanceof String) {
				String keyStr=(String)key;
				keyList.add(keyStr);
			}
			
		}
		
		return getListToKey(keyList);
	}
	
	public static String[] getListToKey(List<String> keyList){
		if(keyList.isEmpty()){
			return new String[0];
		}
		String[] keys=new String[keyList.size()];
		
		for (int i = 0; i < keyList.size(); i++) {
			keys[i]=keyList.get(i);
		}
		return keys;
	}
	/**获取对象某属性值**/
	public  static Object getObjectValue(Object item,String key) {
		if(item==null)return "";
		
		if(item instanceof Map){
			Map itemMap=(Map) item;
			return (String)itemMap.get(key);
		}
		Field field =getDeclaredField(item,key);
		try {
			if(field!=null){
				field.setAccessible(true) ;
				return  field.get(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
			
		
		return "";
	}
	
	
    /**  
     * 循环向上转型, 获取对象的 DeclaredField  
     * @param object : 子类对象  
     * @param fieldName : 父类中的属性名  
     * @return 父类中的属性对象  xiaof 111122
     */  
    private static Field getDeclaredField(Object object, String fieldName){   
        Field field = null ;   
           
        Class<?> clazz = object.getClass() ;   
           
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {   
            try {   
                field = clazz.getDeclaredField(fieldName) ;   
                return field ;   
            } catch (Exception e) {   
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了   
            }    
        }   
        return null;   
    }   
    public static void main(String args[]) {
//		int num1[] = new int[10];
//		Random random = new Random();
//		
//		for (int i = 0; i < num1.length; i++) {
//			num1[i] = Math.abs(random.nextInt()) % 23 + 1;
//			
//		}
//		Arrays.sort(num1);
//		int num2[] = new int[num1.length];
//		for (int i = 0, j = 0; i < num1.length; i++, j++) {
//			num2[j] = num1[i];
//			System.out.println(num2[i]);
//		}
    	String url = "http://134.160.170.5:9011/CrmWeb/Servlet?in={'dd':'xx'}";
    	if(StrTools.isIndexOf(url, "?", "")){
    		String par = url.substring(0,url.indexOf("?"));
    		System.out.println(par);
    	}
    	//System.out.println((10.2-20.1));
	}  
    //生成不重复的随机数
    public static int[] unRepeatNo(int maxNum,int size){
    	int num1[] = new int[size];
		Random random = new Random();
		
		for (int i = 0; i < size; i++) {
			num1[i] = Math.abs(random.nextInt()) % maxNum + 1;
			for(int j=0;j<i;j++){
				if(num1[j]==num1[i]){//有重复
					i--;
				}
			}
		}
		return num1; 
    }
    
    /**
     * 获取特定长度的字符串，中文算两个字符
     * @param str
     * @param length
     * @return
     * @author liuyuzhong 2012.2.9
     */
    public static String getLimitLenString(String str, int length) {
    	if(isEmpty(str) || length < 1) {
    		return "";
    	}
    	char[] charArr = str.toCharArray();
    	if(length > charArr.length) {
    		length = charArr.length;
    	}
    	int charCount = 0;
    	int strCount = 0;
    	for(int i = 0; i < length && charCount < length; i++) {
    		if(isChinese(charArr[i])) {
    			i++ ;
    			strCount++;
    			charCount += 2;
    		} else {
    			i++;
    			strCount++;
    			charCount++;
    		}
    	}
    	if(strCount % 2 == 1) {
    		strCount -= 1;
    	}
    	return str.substring(0, strCount);
    }
    
    /**
     * 获取字符串的长度，中文算两个字符
     * @param str
     * @return
     */
    public static int getStrLength(String str) {
    	if(isEmpty(str)) {
    		return 0;
    	}
    	char[] charArr = str.toCharArray();
    	int charCount = 0;
    	for(int i = 0; i < charArr.length ; i++) {
    		if(isChinese(charArr[i])) {
    			charCount+= 2;
    		} else {
    			charCount++;
    		}
    	}
    	return charCount;
    }
    
    public static final boolean isChinese(char c) {
    	Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
    	if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
    	    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
    	    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
    	    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
    	    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
    	    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
    	    return true;
    	}
    	return false;
    }
	
    public static String isEmptyDefalut(String val,String defVal){
		if(StrTools.isEmpty(val)){return defVal;}
		return val;
    }


	public static String safe(String val){
		if(val==null){
			return "";
		}
		return val;
	}
	
//	/** <li> 将List<String>转换为字符串 */
//	public static String listStoString(List<String> retList, String seperator){
//		String retStr = "";
//		if(ListUtil.isEmpty(retList)){ return retStr; }
//		
//		List unique = new ArrayList();
//		for(String str : retList){
//			if(StrTools.isIndexOf(str, seperator, "")){
//				String[] s = str.split(seperator);
//				for(String t : s){
//					if(StrTools.isEmpty(t)){ continue; }
//					if(unique.contains(t)){ continue; }
//					unique.add(t);
//				}
//				continue;
//			}
//			if(unique.contains(str)){ continue; }
//			unique.add(str);
//		}
//		
//		return org.apache.commons.lang.StringUtils.join(unique.toArray(), seperator);
//	}
	
	/** <li> 将List<Map>转换成Map<key,value(多个用逗号隔开)>,*/
	public static Map listMtoString(List<Map> retList, String seperator){
		Map retMap = new HashMap();
		if(ListUtil.isEmpty(retList)){ return retMap; }
		String key = "";
		String value = "";
		String values = "";
		for(Map m : retList){
			key = StrTools.getStrValue(m, "key");
			value = StrTools.getStrValue(m, "value");
			if(retMap.containsKey(key)){
				values = StrTools.getStrValue(retMap, key);
				if(StrTools.isIndexOf(values, value, seperator)){ continue; }
				values = values + seperator +value;
				retMap.put(key, values);
				continue;
			}
			retMap.put(key, value);
		}
		return retMap;
	}
	
//	/** <li> 将html进行转码 UR694625-songfq-150703*/
//	public static String unescapeHtml(String old_html){
//		Pattern pattern = Pattern.compile("\\u0000");
//		Matcher matcher = pattern.matcher(old_html);
//		StringBuffer newHtml = new StringBuffer();
//		while (matcher.find()) {
//			matcher.appendReplacement(newHtml, "");
//		}
//		matcher.appendTail(newHtml);
//		return StringEscapeUtils.unescapeHtml(newHtml.toString());
//	}
//	
	/** <li> 中文格式的姓名 */
	public static boolean isChinaName(String name){
		String reg = "[\u4E00-\u9FA5]{1,10}(?:·[\u4E00-\u9FA5]{1,10})*";
		return isMatche(name, reg);
	}
	
	public static List strToList(String str, String seperator){
		List ret = new ArrayList();
		if(StrTools.isEmpty(str)){ return null; }
		String[] s = str.split(seperator);
		for(String t : s){
			if(StrTools.isEmpty(t)){ continue; }
			ret.add(t);
		}
		return ret;
	}
	
	/** <li> 数字乘法 */
	public static String numMultiply(String val1, String val2){
		if(StrTools.isEmpty(val1) || StrTools.isEmpty(val2)){ return ""; }
    	BigDecimal b1 = new BigDecimal(val1);
    	BigDecimal b2 = new BigDecimal(val2);
    	return b1.multiply(b2).toString();
	}
	
	/** <li> 数字加法 */
	public static String numAdd(String val1, String val2){
		if(StrTools.isEmpty(val1) || StrTools.isEmpty(val2)){ return ""; }
    	BigDecimal b1 = new BigDecimal(val1);
    	BigDecimal b2 = new BigDecimal(val2);
    	return b1.add(b2).toString();
	}
	
	/** <li> 数字减法 */
	public static String numSub(String val1, String val2){
		if(StrTools.isEmpty(val1) || StrTools.isEmpty(val2)){ return ""; }
    	BigDecimal b1 = new BigDecimal(val1);
    	BigDecimal b2 = new BigDecimal(val2);
    	return b1.subtract(b2).toString();	
	}
	
	/** <li> 数字除法 */
	public static String numDivide(String val1, String val2){
		if(StrTools.isEmpty(val1) || StrTools.isEmpty(val2)){ return ""; }
    	BigDecimal b1 = new BigDecimal(val1);
    	BigDecimal b2 = new BigDecimal(val2);
    	return b1.divide(b2).toString();			
	}
	
	/** <li> 取整并且四舍五入 */
	public static String numIntRoundUp(String val1){
		if(StrTools.isEmpty(val1)){ return val1; }
		return new BigDecimal(val1).setScale(0, BigDecimal.ROUND_HALF_UP).toString(); 
	}
	
	/** <li> 身份证校验 */
	public static boolean isIdCardNo(String idCardNo){
		if (idCardNo == null) {
			return false;
		}
		String idCardNoUpperCase = idCardNo.toUpperCase().trim();
		String regexIdLength = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";

		if (!isMatcher(regexIdLength, idCardNoUpperCase)) {
			return false;
		}
		HashMap<String,String> h = GetAreaCode();
		if (h.get(idCardNoUpperCase.substring(0, 2)) == null) {
			return false;
		}
		if (idCardNoUpperCase.length() == 15) {
			String strYear = idCardNoUpperCase.substring(6, 8);// 年份
			String strMonth = idCardNoUpperCase.substring(8, 10);// 月份
			String strDay = idCardNoUpperCase.substring(10, 12);// 天
			String dateStr = "19" + strYear + "-" + strMonth + "-" + strDay;

			if (!checkDate(dateStr)) {
				return false;
			}
		} else if (idCardNoUpperCase.length() == 18) {
			String strYear = idCardNoUpperCase.substring(6, 10);// 年份
			String strMonth = idCardNoUpperCase.substring(10, 12);// 月份
			String strDay = idCardNoUpperCase.substring(12, 14);// 天
			String dateStr = strYear + "-" + strMonth + "-" + strDay;
			String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3",
					"7", "9", "10", "5", "8", "4", "2" };
			String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5",
					"4", "3", "2" };

			if (!checkDate(dateStr)) {
				return false;
			}
			int TotalmulAiWi = 0;
			for (int i = 0; i < 17; i++) {
				TotalmulAiWi = TotalmulAiWi
						+ Integer.parseInt(String.valueOf(idCardNoUpperCase
								.charAt(i))) * Integer.parseInt(Wi[i]);
			}
			int modValue = TotalmulAiWi % 11;
			String strVerifyCode = ValCodeArr[modValue];

			if (idCardNoUpperCase.equals(idCardNoUpperCase.substring(0, 17)
					+ strVerifyCode) == false) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	private static boolean isMatcher(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	private static Pattern linePattern = Pattern.compile("\r|\n|\r\n");

	public static String[] splitLines(String lines) {
		return linePattern.split(lines);
	}

	private static boolean checkDate(String dateStr) {
		String regexDate = "^(?:([0-9]{4}-(?:(?:0?[1,3-9]|1[0-2])-(?:29|30)|"
				+ "((?:0?[13578]|1[02])-31)))|"
				+ "([0-9]{4}-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|1\\d|2[0-8]))|"
				+ "(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|"
				+ "(?:0[48]00|[2468][048]00|[13579][26]00))-0?2-29)))$";

		if (!isMatcher(regexDate, dateStr)) {
			return false;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = s.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(dateStr.substring(0, 4))) > 150
				|| (gc.getTime().getTime() - date.getTime()) < 0) {
			return false;
		}
		return true;
	}
	
	public static String lpad(String s, int n, String replace) {  
        while (s.length() < n) {  
            s = replace+s;  
        }  
        return s;  
    }  
	public static String rpad(String s, int n, String replace) {  
        while (s.length() < n) {  
            s = s+replace;  
        }  
        return s;  
    }  
    

	private static HashMap<String,String> GetAreaCode() {
		HashMap<String,String> hashtable = new HashMap<String,String>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}
	
}
