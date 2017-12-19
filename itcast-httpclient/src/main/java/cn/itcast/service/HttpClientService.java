package cn.itcast.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientService {
	
	private CloseableHttpClient httpClient;

	public HttpClientService() {
		//创建httpClient对象
		 httpClient = HttpClients.createDefault();
	}
	/**
	 * 不带参数的get方法
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public String doGet(String url) throws Exception {
		return doGet(url,null);
	}
	
	/**
	 * 带参数的get方法
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String doGet(String url,Map<String,Object> param) throws Exception {
		 
		URIBuilder uriBuilder = new URIBuilder(url);
		if (param !=null) {
			Set<Entry<String,Object>> entrySet = param.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		
		//创建httpGet
		HttpGet http = new HttpGet();
		CloseableHttpResponse httpResponse = null;
		try {
			//执行
			 httpResponse = httpClient.execute(http);
			
			 //处理结果
			 if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				String content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				return content;
			}
		} finally {
			if (httpResponse !=null) {
				httpResponse.close();
			}
			httpClient.close();
		}
		return null;
	}
	
	/**
	 * 带参数的post方法
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return
	 * @throws Exception
	 */
	public HttpResult doPost(String url, Map<String, Object> param) throws Exception {

		// 创建httpPost
		HttpPost http = new HttpPost(url);
		
		if (param != null) {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			Set<Entry<String, Object>> entrySet = param.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
			
			//设置参数
			http.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
		}

		CloseableHttpResponse httpResponse = null;
		try {
			// 执行
			httpResponse = httpClient.execute(http);
			// 处理结果
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				return new HttpResult(HttpStatus.SC_OK, content);
			}
		} finally {
			if (httpResponse != null) {
				httpResponse.close();
			}
			httpClient.close();
		}
		return new HttpResult(httpResponse.getStatusLine().getStatusCode());
	}
	
	/**
	 * 带参数的delete方法
	 * @param url请求地址
	 * @param param请求参数
	 * @return
	 * @throws Exception
	 */
	public HttpResult doDelete(String url,Map<String,Object> param) throws Exception {
		if (param == null) {
			param = new HashMap<String,Object>();
		}
		param.put("_method", "delete");
		return doPost(url, param);
	}
}
