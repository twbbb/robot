package com.twb.robot.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;



public class HttpClientUtils
{
	
	public static Map sendGet(String getUrl) throws HttpException, IOException
	{
		HttpClient client = new HttpClient();
		GetMethod httpget = new GetMethod(getUrl);

		try
		{
//			httpget.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
			httpget.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
			client.executeMethod(httpget);
			int statusCode = httpget.getStatusCode();
			String responseBody = httpget.getResponseBodyAsString();
			//hcrb.setResponseBody(responseBody);
			//hcrb.setStatusCode(statusCode);
		}
		finally
		{
			httpget.releaseConnection();
		}

		return null;
	}

}
