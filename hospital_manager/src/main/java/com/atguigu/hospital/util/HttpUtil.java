package com.atguigu.hospital.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
@Slf4j
public final class HttpUtil {

	static final String POST = "POST";
	static final String GET = "GET";
	static final int CONN_TIMEOUT = 30000;// ms
	static final int READ_TIMEOUT = 30000;// ms

	/**
	 * post 方式发送http请求.
	 * 
	 * @param strUrl
	 * @param reqData
	 * @return
	 */
	public static byte[] doPost(String strUrl, byte[] reqData) {
		return send(strUrl, POST, reqData);
	}

	/**
	 * get方式发送http请求.
	 * 
	 * @param strUrl
	 * @return
	 */
	public static byte[] doGet(String strUrl) {
		return send(strUrl, GET, null);
	}

	/**
	 * @param strUrl
	 * @param reqmethod
	 * @param reqData
	 * @return
	 */
	public static byte[] send(String strUrl, String reqmethod, byte[] reqData) {
		try {
			//**************建立连接对象***********************
			URL url = new URL(strUrl);//将路径字符串构建一个URL对象
			//使用URL对象 打开一个连接对象
			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();

			httpcon.setDoOutput(true); //打开连接的输出流
			httpcon.setDoInput(true);	//打开连接的输入流
			httpcon.setUseCaches(false); //关闭缓存
			httpcon.setInstanceFollowRedirects(true);
			httpcon.setConnectTimeout(CONN_TIMEOUT);//30000ms
			httpcon.setReadTimeout(READ_TIMEOUT); //30000ms
			httpcon.setRequestMethod(reqmethod); //设置连接请求方式
			httpcon.connect(); //设置完后执行连接
			//****************写入数据*****************************
			if (reqmethod.equalsIgnoreCase(POST)) { //如果请求方式是post
				OutputStream os = httpcon.getOutputStream();
				os.write(reqData);//将输出流中写入数据
				os.flush();//刷新
				os.close();//关闭流
			}
			//创建一个缓冲字符输入流对象 从连接对象中获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"utf-8"));
			String inputLine;

			StringBuilder bankXmlBuffer = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {  
			    bankXmlBuffer.append(inputLine);  
			}
			in.close();

			httpcon.disconnect();//关闭连接
			return bankXmlBuffer.toString().getBytes();//将读取到的东西转换成字节数组 返回
		} catch (Exception ex) {
			log.error(ex.toString(), ex);
			return null;
		}
	}
	
	/**
	 * 从输入流中读取数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return data;
	}
}
