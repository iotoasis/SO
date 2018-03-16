package com.pineone.icbms.so.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ClientServiceTimeout class<BR/>
 * Created by use on 2015-10-12.
 * NOTE: 외부 통신을 위해 사용
 */

@Service
public class ClientServiceTimeout
{
	public static final int DATA_TIMEOUT_VALUE = 90000;

	public static final int CONNECTION_TIMEOUT_VALUE = 2000; // 연결
	public static final int READ_TIMEOUT_VALUE = 5000;       // 읽기
	
	private final Logger log;// = LoggerFactory.getLogger(ClientServiceTimeout.class);

	public ClientServiceTimeout() {
		log = LoggerFactory.getLogger(ClientServiceTimeout.class);
	}

	public ClientServiceTimeout(String clsName) {
		log = LoggerFactory.getLogger(clsName);
	}

	public ClientServiceTimeout(Class<?> class1) {
		log = LoggerFactory.getLogger(class1);
	}

	public String requestPostServiceReceiveString2(String uri, String param) {
		return requestPostServiceReceiveString2(uri, param, false);
	}
	
	public String requestPostServiceReceiveString2(String uri, String param, boolean timeOut)
	{
		String responseString = null;
		HttpURLConnection conn = null;
		BufferedReader in  = null;
		try {
		    log.info("requestPostServiceReceiveString2:[{}]", uri);

		    URL url = new URL(uri);
		    //log.info("openConnection..");
			conn = (HttpURLConnection)url.openConnection();
			if (timeOut) {
				conn.setConnectTimeout(CONNECTION_TIMEOUT_VALUE);
				conn.setReadTimeout(READ_TIMEOUT_VALUE);
			}
			conn.setRequestMethod("POST"); 	// 전달 방식을 설정한다. POST or GET, 기본값은 GET 이다.
			conn.setDoInput(true);  		// 서버로부터 메세지를 받을 수 있도록 한다. 기본값은 true이다.
			conn.setDoOutput(true);			// 서버로 데이터를 전송할 수 있도록 한다. GET방식이면 사용될 일이 없으나, true로 설정하면 자동으로 POST로 설정된다. 기본값은 false이다.
			//conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/json");
		    OutputStream out_stream = conn.getOutputStream();
		    out_stream.write( param.getBytes("UTF-8") );
		    log.info("requestPostServiceReceiveString2:[{}]:[{}] ", uri, out_stream.toString());
		    out_stream.flush();
		    out_stream.close();

		    log.info("out_stream close..");
		    
		    int statusCode = conn.getResponseCode();
		    if (statusCode != HttpURLConnection.HTTP_OK) {
		    	if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) { //SDA에서 쿼리 data가 없는 경우에 404리턴
				    log.info("statusCode=[{}], uri={}", statusCode, uri); 
		    	}else {
		    		log.error("error statusCode=[{}], uri={}", statusCode, uri);
		    	}
		    	return null;
		    }
		    //log.info(" == ok uri={}", uri);
		    //log.info("getInputStream..");
		    
		    InputStream is     = conn.getInputStream();
		    //log.info("new BufferedReader..");
		    in  = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8 * 1024);
		 
		    String line = null;
		    StringBuffer buff   = new StringBuffer();

		    //log.info("reading..");
		 
		    while ( ( line = in.readLine() ) != null )
		    {
		        buff.append(line + "\n");
		    }
		    responseString = buff.toString().trim();
			
		    log.info("response=[{}]", responseString);
		} catch (ConnectException e) {
		    log.error("  !###1 POST Error ConnectException: uri=[{}]", uri);
		    log.debug("error msg={}",e.getMessage());
		} catch (Exception e) {
		    log.error("  !###1 POST Exception: uri=[{}, response=[{}]", uri, responseString);
		    log.debug("error {},msg={}",e.getClass().getName(), e.getMessage());
		} finally {
		    if (in != null) {
		        try {
		            in.close();
		        } catch (IOException e) {
		        }
		    }			
			if (conn != null)
				conn.disconnect();
		}
		
		return responseString;	
	}


	public String requestGetServiceReceiveString2(String uri) { 
		return requestGetServiceReceiveString2(uri, false);
	}
	public String requestGetServiceReceiveString2(String uri, boolean timeOut) {
		String responseString = null;
		HttpURLConnection conn = null;
		BufferedReader in  = null;
		try {
		    log.info("requestGetServiceReceiveString2:[{}]", uri);

		    URL url = new URL(uri);
		    log.info("openConnection..");
			conn = (HttpURLConnection)url.openConnection();
			
			if (timeOut) {
				conn.setConnectTimeout(CONNECTION_TIMEOUT_VALUE);
				conn.setReadTimeout(READ_TIMEOUT_VALUE);
			}

			conn.setRequestMethod("GET"); 	// 전달 방식을 설정한다. POST or GET, 기본값은 GET 이다.
			conn.setDoInput(true);  		// 서버로부터 메세지를 받을 수 있도록 한다. 기본값은 true이다.
			// conn.setDoOutput(true);			// 서버로 데이터를 전송할 수 있도록 한다. GET방식이면 사용될 일이 없으나, true로 설정하면 자동으로 POST로 설정된다. 기본값은 false이다.
			//conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			
		    int statusCode = conn.getResponseCode();
		    if (statusCode != HttpURLConnection.HTTP_OK) {
		    	if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) { //SDA에서 쿼리 data가 없는 경우에 404리턴
				    log.info("statusCode=[{}], uri={}", statusCode, uri); 
		    	}else {
		    		log.error("error statusCode=[{}], uri={}", statusCode, uri);
		    	}
		    	return null;
		    }
		    //log.info(" == ok uri={}", uri);

		    //log.info("getInputStream..");
		    InputStream is     = conn.getInputStream();
		    
		    //log.info("new BufferedReader..");
		    in  = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8 * 1024);
		    String line = null;
		    StringBuffer buff   = new StringBuffer();
		    //log.info("reading..");
		 
		    while ( ( line = in.readLine() ) != null )
		    {
		        buff.append(line + "\n");
		    }
		    responseString = buff.toString().trim();
			
		    log.info("response=[{}]", responseString);
		} catch (ConnectException e) {
		    log.error("  !###2 GET Error ConnectException: uri=[{}]", uri);
		    log.debug("error msg={}",e.getMessage());
		} catch (Exception e) {
		    log.error("  !###2 GET Exception: uri=[{}, response=[{}]", uri, responseString);
		    log.debug("error {},msg={}",e.getClass().getName(), e.getMessage());
		} finally {
		    if (in != null) {
		        try {
		            in.close();
		        } catch (IOException e) {
		        }
		    }			
			if (conn != null)
				conn.disconnect();
		}
		
		return responseString;	
	}

}
