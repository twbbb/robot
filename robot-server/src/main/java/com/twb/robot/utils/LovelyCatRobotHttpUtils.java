package com.twb.robot.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.twb.robot.common.utils.StringConvertUtils;

public class LovelyCatRobotHttpUtils {
	private static CloseableHttpClient httpClient = null;
	
	private static final Logger logger = LoggerFactory.getLogger(LovelyCatRobotHttpUtils.class);

	

	
	
	public static Map sendMsg(Map param){
		if(param==null||param.isEmpty()){
			return null;
		}
		logger.debug("发送消息："+param);
		JSONObject paramsMap = new JSONObject(param);
		
		Map<String, String> data = new HashMap<>();
		data.put("data", paramsMap.toJSONString());
		String response = doPost("http://114.67.112.14:8073/send", data);
        JSONObject rspMap = JSONObject.parseObject(response);
		logger.debug("发送结果："+rspMap);
		return rspMap;

	}

	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = getHttpClient();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
				httpPost.setEntity(entity);
			}
			int timeOut = 30;
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut * 1000)
					.setConnectTimeout(timeOut * 1000).setConnectionRequestTimeout(timeOut * 1000)
					.setExpectContinueEnabled(false).build();
			httpPost.setConfig(requestConfig);
			// 执行http请求
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK) {
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");

			}else{
				try {
					resultString = EntityUtils.toString(response.getEntity(), "utf-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
				throw new RuntimeException("调用失败，code："+statusCode+",body:"+resultString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用异常",e);

		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException("连接释放失败");
				}// 自动释放连接
			}
		}
		return resultString;
	}
	
	/**
	 * 获取httpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			// 设置连接管理器
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
			connManager.setMaxTotal(30);// 设置连接池线程最大数量
			connManager.setDefaultMaxPerRoute(15);// 设置单个路由最大的连接线程数量

			httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
		}

		return httpClient;
	}

	public static void main(String args[]) throws Exception {
		Map paramsMap = new HashMap();
		paramsMap.put("type", "100");
		paramsMap.put("msg", URLEncoder.encode("测试", "UTF-8"));
		paramsMap.put("to_wxid",  "wxid_sndgxwk6qc1m21");
		paramsMap.put("robot_wxid",  "wxid_mzss9j7otj9n22");
		Map map = sendMsg( paramsMap);
		System.out.println("发送成功:"+map);

	}

}
