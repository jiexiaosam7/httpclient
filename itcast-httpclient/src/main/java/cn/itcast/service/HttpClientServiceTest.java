package cn.itcast.service;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class HttpClientServiceTest {

	HttpClientService httpClientService;

	@Before
	public void setUp() {

		httpClientService = new HttpClientService();
	}

	@Test
	public void testSaveItem() throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("title", "通过HttpClientService创建");
		param.put("sellPoint", "卖点");
		param.put("price", 123);
		param.put("cid", 100);
		param.put("status", 1);
		param.put("num", 10);

		// 提交
		HttpResult httpResult = httpClientService.doPost("http://manage.taotao.com/rest/item/interface", param);
		System.out.println("响应码为:" + httpResult.getStatus());
		System.out.println("响应内容为:" + httpResult.getContent());
	}

	@Test
	public void testQueryById() throws Exception {
		String content = httpClientService.doGet("http://manage.taotao.com/rest/item/interface/44");
		System.out.println("响应内容为:" + content);
	}

	@Test
	public void updateItem() throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("title", "通过HttpClientService创建222");
		param.put("sellPoint", "卖点222");
		param.put("price", 123);
		param.put("cid", 100);
		param.put("status", 1);
		param.put("num", 10);
		param.put("id", 44L);
		HttpResult httpResult = httpClientService.doPost("http://manage.taotao.com/rest/item/interface", param);

		System.out.println("响应码为：" + httpResult.getStatus());
		System.out.println("响应内容为：" + httpResult.getContent());
	}

	public void testDeleteItem() throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("ids", 44L);
		
		// 提交
		HttpResult httpResult = httpClientService.doDelete("http://manage.taotao.com/rest/item/interface", param);
		System.out.println("响应码为：" + httpResult.getStatus());
		System.out.println("响应内容为：" + httpResult.getContent());
	}
}
