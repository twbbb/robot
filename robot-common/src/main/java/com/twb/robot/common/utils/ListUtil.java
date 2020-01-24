package com.twb.robot.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@SuppressWarnings( { "unchecked"})
public final class ListUtil {

	
	public static final String IS_NULL="__is null";
	public static final String IS_NOT_NULL="__is not null";
	
	public static boolean isEmpty(List list){
		if(list==null)
			return true;
		return list.size()==0;		
	}
	
	/**
	 * 取A和B两个列表的交集List
	 * @param listA
	 * @param listB
	 */
	public static List mixedList(List listA,List listB){
		List mixedList = new ArrayList();
		if(!isEmpty(listA)&&!isEmpty(listB)){
			for(int i = 0;i<listA.size();i++){
				Object objA  = listA.get(i);
				if(listB.contains(objA)){
					mixedList.add(objA);
				}
			}
		}
		return mixedList;
	}
	
	/**
	 * 取在A中而不在B中的数据
	 * @param listA
	 * @param listB
	 */
	public static List notContainList(List listA,List listB){
		List NotContainList = new ArrayList();
		if(!isEmpty(listA)){
			if(isEmpty(listB))
				return listA;
			for(int i = 0;i<listA.size();i++){
				Object objA  = listA.get(i);
				if(!listB.contains(objA)){
					NotContainList.add(objA);
				}
			}
		}
		return NotContainList;
	}
	

	/** 
	 * 取A和B两个列表的并集List
	 * @param listA
	 * @param listB
	 */
	public static List mergeList(List listA,List listB){
		List res = new ArrayList();
		//将 两个列表做合并
		if (listA == null && listB==null)
			return res;
		if(ListUtil.isEmpty(listA)){ 
			return listB;
		}else{
			if(ListUtil.isEmpty(listB)){
				return listA;
			}else{
				for(int i =0;i<listB.size();i++){
					Object obj = listB.get(i);
					if(listA.contains(obj))continue;
					listA.add(obj);
				}
				return listA;
			}  
		}
	}
	
	
	/**
	 * 将str根据seperator 拆分组织成 列表
	 * @param seperator
	 * @param str
	 * @return
	 */
	public static List strToList(String seperator,String str ){
		List resList = new ArrayList();
		if(StrTools.isEmpty(str)){
			return resList;
		}
		String[] strArray = str.split(seperator);
		if(strArray.length==0)
			return resList;
		if(strArray.length==1){
			resList.add(strArray[0]);
			return resList;
		}
		for(int i = 0;i<strArray.length;i++){
			String strCell = strArray[i];
			if(!resList.contains(strCell))
				resList.add(strCell); 
		}
		return resList;
	}
	
	/**
	 * 根据List<Map> 中个Map, 的key建值组装新的List<Stirng> add by xiaof 111020
	 * @param mapList
	 * @param key
	 * @return
	 */
	public static List<String> getStrValues (List<Map> mapList,String key ) {
		List<String> strList=new ArrayList<String>();
		
		if(isEmpty(mapList)||key==null||key.isEmpty()){
			return strList;
		}
		
		for (Map map : mapList) {
			String strValue=MapUtil.getStrValue(map, key);
			strList.add(strValue);
		}
		
		return strList;
	}

	/**
	 * 获取第一个对象,无返回null
	 */
	public static Object getFrist(List o) {

		if(isEmpty(o)){
			return null;
		}
		
		return o.get(0);
	}
    //listtostring
    public static String listToString(List<String> stringList){
  	   if (stringList==null || stringList.size()==0) {
  		   return null;
  	   }
  	   StringBuilder result=new StringBuilder();
  	   for (String string : stringList) {
  		   result.append(string).append(",");
  	   }
  	   result.setLength(result.length()-1);
  	   return result.toString();
     }
	
  //根据id查找对象
  	public static List findByCond(List lstData,HashMap whereCond){
  		//如果条件是空，那么返回全部结果
  		if (whereCond==null || whereCond.isEmpty()) return lstData; 
  		//定义一个返回值
  		ArrayList returnLstResult=new ArrayList();
  		//如果传入的查找池是空，那么返回查找池
  		if (lstData==null || lstData.isEmpty()) return lstData;
  		//定义规格上规定需要多少个字段相等
  		int intClsEqualCount=whereCond.size();
  		//定义实际每条记录的相等字段数
  		int intInstEqualCount=0;
  		int lstDataSize=lstData.size();
  		//定义实例数据循环时的每个字段的值和规格上每个字段值
  		String clsKey="",clsVal="",instKey="",instVal="";
  		//定义实例数据的一条记录
  		HashMap instRecord=null;
  		Map.Entry map=null;
  		for(int i=0;i<lstDataSize;i++){
  			instRecord=(HashMap)lstData.get(i);
  			intInstEqualCount=0;
  			for(Iterator it=whereCond.entrySet().iterator();it.hasNext();){
  				map=(Map.Entry)it.next();
  				clsKey=(String)map.getKey();
  				clsVal=(String)map.getValue();
  				instVal=(String)instRecord.get(clsKey);
  				if(clsVal==null)clsVal="";
  				if(instVal==null)instVal=""; 
  				if (clsVal.equals(instVal))intInstEqualCount++;
  				else if (clsVal.equals(IS_NULL) && instVal.equals(""))intInstEqualCount++;
  				else if (clsVal.equals(IS_NOT_NULL) && !instVal.equals(""))intInstEqualCount++;
  				else break;
  			}
  			//如果每个字段都相等，那么这条记录符合条件，需要加入放回结果列表里
  			if (intInstEqualCount==intClsEqualCount)returnLstResult.add(instRecord);
  		}
  		return returnLstResult;
  	}
}
