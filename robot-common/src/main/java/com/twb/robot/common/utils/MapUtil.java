package com.twb.robot.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



@SuppressWarnings( { "unchecked"})
public class MapUtil
{
	public static Map listToMap(Iterator objects)
	{
		Map map = new HashMap();

		while (objects.hasNext())
		{
			String object = objects.next().toString();
			map.put(object, object);
		}
		return map;
	}

	public static Map arrayToMap(String[] objects)
	{
		Map map = new HashMap();

		for (String str : objects)
		{
			map.put(str, str);
		}

		return map;
	}

	public static boolean isEmpty(Map map)
	{
		if (null == map)
		{
			return true;
		}

		return map.isEmpty();
	}
	
	/**
	 * 根据key或者 key对应的map
	 * @param map
	 * @param key
	 * @return
	 */
	public static Map  getInsideMap(Map map,String key){
		if(isEmpty(map)){
			return null;
		}
		Object v = map.get(key);
		if(v==null){
			return null;
		}
		
		if(v instanceof Map){
			return (Map)v;
		}
		
		return null;
		
		
	}
	
	/**
	 * 将null转为""
	 */
	public static Map nullValueToBlank(Map map)
	{
		if (null == map)
			return null;
		Iterator it_map = map.keySet().iterator();
		while(it_map.hasNext())
		{
		
			Object obj = it_map.next();
			if (map.get(obj) == null) {
				map.put(obj, "");
			}
		
		}
		return map;
	}
	
	/**
	 * 将Map中为字符串的键全部转换为小写字母。注意：转换后的结果与参数不再是同一个对象，其地址不相同了，但内容还是一样。
	 * @param map
	 * @return Map:转换后的Map对象
	 */
	public static Map mapKeyToLowerCase(Map map)
	{
		if (null == map)
			return null;
		Map map_key_lower = new HashMap();
		Iterator it_map = map.keySet().iterator();
		while(it_map.hasNext())
		{
			Object obj = it_map.next();
			if(obj.getClass().getName().endsWith("String"))
				map_key_lower.put(((String)obj).toLowerCase(), map.get(obj));
			else
				map_key_lower.put(obj, map.get(obj));
		}
		return map_key_lower;
	}
	
	/**
	 * 将Map中为字符串的键全部转换为大写字母。注意：转换后的结果与参数不再是同一个对象，其地址不相同了，但内容还是一样。
	 * @param map
	 * @return Map:转换后的Map对象
	 */
	public static Map mapKeyToUpperCase(Map map)
	{
		if (null == map)
			return null;
		Map map_key_lower = new HashMap();
		Iterator it_map = map.keySet().iterator();
		while(it_map.hasNext())
		{
			Object obj = it_map.next();
			if(obj.getClass().getName().endsWith("String"))
				map_key_lower.put(((String)obj).toUpperCase(), map.get(obj));
			else
				map_key_lower.put(obj, map.get(obj));
		}
		return map_key_lower;
	}

	/**
	 * 
	 * 源Map合并到目标Map,不修改目标Map数据
	 * add by xiaof 110819
	 */
	public static Map mergeMap(Map sm, Map tm) {
		if (sm == null || sm.isEmpty() || tm == null || tm.isEmpty())
			return new HashMap();
		Map retmp = new HashMap();
		retmp.putAll(tm);
		retmp.putAll(mergeMapFromMap(sm,tm));
		return retmp;
	}

	/**
	 * 
	 * 根据源Map合并到目标Map,不返回源Map以外的数据,并不修改目标Map数据
	 * add by xiaof 110926
	 */
	public static Map mergeMapFromMap(Map sm, Map tm) {
		if (sm == null || sm.isEmpty() || tm == null || tm.isEmpty())
			return new HashMap();
		Map retmp = new HashMap();
		
		for (Iterator it = sm.keySet().iterator(); it.hasNext();) {
			String n = (String) it.next();
			if (tm.get(n) != null&&!"".equals(tm.get(n))) {
				retmp.put(n, (String) tm.get(n));
			} else {
				retmp.put(n, (String) sm.get(n));
			}
		}
		return retmp;
	}
	/**
	 * 
	 * 源Map合并到目标Map,直接修改目标Map数据
	 * add by xiaof 110926
	 */
	public static Map mapToTargetMap(Map sm, Map tm) {
		if (sm == null || sm.isEmpty() || tm == null || tm.isEmpty())
			return new HashMap();
	
		for (Iterator it = sm.keySet().iterator(); it.hasNext();) {
			String n = (String) it.next();
			if (tm.get(n) == null||"".equals(tm.get(n))) {
				tm.put(n, (String) sm.get(n));
			}
		}
 
		return tm;
	}
	/**
	 *
	 * 对比变动生成变动的Map 
	 * add by xiaof 111207
	 */
	public static Map compareToMap(Map newMap, Map oldMap) {
		if (newMap == null || newMap.isEmpty() || oldMap == null || oldMap.isEmpty())
			return new HashMap();
		Map retmp = new HashMap();
		
		for (Iterator it = newMap.keySet().iterator(); it.hasNext();) {
			String n = (String) it.next();
			
			if (newMap.get(n) != null&&newMap.get(n).equals(oldMap.get(n))) {
				continue;
			} else if(newMap.get(n)==null&&oldMap.get(n)==null ) {
				continue;
			}
			retmp.put(n, (String) newMap.get(n));
		}
		return retmp;
	}
	/**
	 *
	 * 对比变动生成变动的Map 
	 * add by xiaof 111207
	 */
	public static Map compareToMap2(Map newMap,Map oldMap) {
		if (newMap == null || newMap.isEmpty() || oldMap == null || oldMap.isEmpty())
			return new HashMap();
		Map retmp = new HashMap();
		
		for (Iterator it = oldMap.keySet().iterator(); it.hasNext();) {
			String n = (String) it.next();
			
			if (oldMap.get(n) != null&&newMap.get(n).equals(oldMap.get(n))) {
				continue;
			} else if(newMap.get(n)==null&&oldMap.get(n)==null ) {
				continue;
			}
			retmp.put(n, (String) newMap.get(n));
		}
		return retmp;
//		if (newMap == null || newMap.isEmpty())
//			return new HashMap();
//		Map retmp = new HashMap();
//		
//		Set<String> keySet = newMap.keySet();
//		
//		//遍历key集合，获取value
//		for(String key : keySet) {
//		    if(key.contains("col")&&!key.contains("OLD_VALUE")){		
//		    	if(!StrTools.isEqual((String)newMap.get(key), (String)newMap.get(key+KeyValues.OLD_VALUE))
//		    			&& (String)newMap.get(key+KeyValues.OLD_VALUE)!=null){
//		    		retmp.put(key, (String) newMap.get(key));
//		    	}		    			
//		    }		
//		}
//		return retmp;
	}	
	/**
	 *  对比两个Map是否相等 add by xiaof 111009
	 */
	public static boolean isEqual(Map o, Map c){
		if(o==null||c==null)return false;
		
		return o.equals(c); 
		/**逻辑重复 modify by xiaof
		 * if(o.equals(c)){return true;}
		Iterator <Entry>it=o.entrySet().iterator();
		
		int k = 0;
		int o_num = o.entrySet().size();
		int c_num = c.entrySet().size();
		if(o_num!=c_num){
			return false;
		}
        
		while(it.hasNext()){
        	Entry<String,Object> entry=it.next();
        	String key = entry.getKey();
        	Object o_value=entry.getValue();
        	Object c_value=c.get(key);
        	if(o_value==null&&o_value==c_value){
        		k+=1;
        		continue;
        	}else if(o_value!=null&&o_value.equals(c_value)){
        		k+=1;
        		continue;
        	}
        	return false;
        }
		if(c_num==k){
			return true;
		}
		return false;*/
	}
	/**
	 *  根据keys对比两个Map是否相等 add by xiaof 111009
	 */	
	public static boolean isEqual(Map o, Map c,String[] keys){
		if(o==null||c==null)return false;
		if (o == c)
		    return true;
		if(keys==null||keys.length==0){
			 o.equals(c);
		}
		if (!(o instanceof Map))
		    return false;

	        try {
	        	for (String mkey : keys) {
					Object oVal=o.get(mkey);
					if(oVal==null){
						if (!(c.get(mkey)==null && c.containsKey(mkey)))
	                        return false;
					}else{
	                    if (!oVal.equals(c.get(mkey)))
	                        return false;
					}
				} 
	        	
	        } catch (ClassCastException unused) {
	            return false;
	        } catch (NullPointerException unused) {
	            return false;
	        }

		return true;

	}

 

	public static boolean isObjEqual(Object o, Map c, String[] keys) {
		
		if(o==null||c==null)return false;
		if (o == c)
		    return true;
		if(keys==null||keys.length==0){
			 o.equals(c);
		}
	        try {
	        	for (String mkey : keys) {
					Object oVal=StrTools.getObjectValue(o, mkey);
					if(oVal==null){
						if (!(c.get(mkey)==null && c.containsKey(mkey)))
	                        return false;
					}else{
	                    if (!oVal.equals(c.get(mkey)))
	                        return false;
					}
				} 
	        	
	        } catch (ClassCastException unused) {
	            return false;
	        } catch (NullPointerException unused) {
	            return false;
	        }

		return true;
	}
	
	
	/**
	 * 替换掉Map数据的key值下划线并让紧跟它后面的字母大写
	 * @param data 需要替换的Map 数据
	 * @return
	 */
	public static Map<String,Object> transferMapDataKey(Map<String,Object> data) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Set<String> keySet = data.keySet();
		for (String keyValue : keySet) {
			StringBuffer sb = new StringBuffer(keyValue);
			int count = sb.indexOf("_");
			while (count != 0) {
				int num = sb.indexOf("_", count);
				count = num + 1;
				if (num != -1) {
					char ss = sb.charAt(count);
					char ia = (char) (ss - 32);
					sb.replace(count, count + 1, ia + "");
				}
			}
			String newKey = sb.toString().replaceAll("_", "");
			resultMap.put(newKey, data.get(keyValue));
		}
		return resultMap;
	}
	
	
	/**
	 * 替换掉Map数据的key值下划线并让紧跟它后面的字母大写---包含List转换
	 * @param data 需要替换的Map 数据
	 * @return
	 */
	public static Map<String, Object> outFieldTransfromMap(Map<String, Object> paramsMap) {
		Map<String, Object> realMap = new HashMap<String, Object>();
		for (String keyValue : paramsMap.keySet()) {

			if (paramsMap.get(keyValue) instanceof List) {// 目前的报文节点，只有list里面嵌套list的，所以只做一层判断

				List<Map<String, String>> tempList = (List<Map<String, String>>) paramsMap.get(keyValue);

				List<Map<String, Object>> finalList = new ArrayList<Map<String, Object>>();// 存放内层最终值的list

				for (int j = 0; j < tempList.size(); j++) {
					Map<String, Object> finalMap = new HashMap<String, Object>();// 存放list中map的值
					Map<String, String> tempMap = tempList.get(j);

					Set<String> keySetInner = tempMap.keySet();
					for (String keyValueInner : keySetInner) {
						finalMap.put(concatString(keyValueInner), tempMap.get(keyValueInner));
					}
					finalList.add(finalMap);
				}
				realMap.put(concatString(keyValue), finalList);

			} else if (paramsMap.get(keyValue) instanceof String) {

				realMap.put(concatString(keyValue), paramsMap.get(keyValue));
			}

		}
		return realMap;
	}

	private static String concatString(String keyValue) {
			StringBuffer sb = new StringBuffer(keyValue);
			int count = sb.indexOf("_");
			while (count != 0) {
				int num = sb.indexOf("_", count);
				count = num + 1;
				if (num != -1) {
					char ss = sb.charAt(count);
					char ia = (char) (ss - 32);
					sb.replace(count, count + 1, ia + "");
				}
			}
			String  newKey = sb.toString().replaceAll("_", "");		
		return newKey;
	}

//	/**
//	 *Map转化为Bean对象
//	 * @param map
//	 * @param c
//	 * @param <T>
//     * @return
//     */
//	public static <T> T mapToBean(Map map, Class<T> c){
//		return Castors.me().castTo(map,c);
//	}
	
	public static  String getStrValue(Map m , String name) {
		if(m != null){
			Object t = m.get(name);
			if (t != null ){
				return t.toString().trim();
			}
		}
		return "";
	}

}
