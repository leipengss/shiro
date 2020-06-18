package com.howie.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
@SpringBootApplication
@MapperScan(value = "com.howie.shiro.mapper")
public class ShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		
	}

public static String doGet(String url, Map<String, String> param) {

	for (Map.Entry<String, String> map: param.entrySet()) {
		
	}
    // 创建Httpclient对象
    CloseableHttpClient httpclient = HttpClients.createDefault();

    String resultString = "";
    CloseableHttpResponse response = null;
    try {
        // 创建uri
        URIBuilder builder = new URIBuilder(url);
        if (param != null) {
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
                builder.setParameter(key, param.get(key));
            }
        }
        URI uri = builder.build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);

        // 执行请求
        response = httpclient.execute(httpGet);
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return resultString;
}

public static String doGet(String url) {
    return doGet(url, null);
}
}