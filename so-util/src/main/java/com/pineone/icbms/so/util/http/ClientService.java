package com.pineone.icbms.so.util.http;

import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import com.withwiz.jellyfish.service.IGenericService;
import com.withwiz.jellyfish.service.ServiceException;
import com.withwiz.service.network.http.client.httpclientservice.HttpClientService;
import com.withwiz.service.network.http.client.httpclientservice.HttpClientServiceRequestDeliveryMessage;
import com.withwiz.service.network.http.client.httpclientservice.HttpClientServiceResponseDeliveryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * ClientService class<BR/>
 * Created by use on 2015-10-12.
 * NOTE: 외부 통신을 위해 사용
 */

@Service
public class ClientService
{
	public static final int DATA_TIMEOUT_VALUE = 90000;

	public static final int CONNECTION_TIMEOUT_VALUE = 2000; // 연결
	public static final int READ_TIMEOUT_VALUE = 5000;       // 읽기
	
	private final Logger log = LoggerFactory.getLogger(ClientService.class);

	/**
	 * request post service.<BR/>
	 *
	 * @param serviceUrl
	 * @param body
	 * @return
	 * NOTE: URL 로 Body 내용 전송
	 */
	public IHttpResponseMessage requestPostService(String serviceUrl, String body)
	{
		log.info("Client Service requestPostService uri " + serviceUrl);
		log.info("Client Service requestPostService data " + body);
		// request delivery message
		HttpClientServiceRequestDeliveryMessage req = new HttpClientServiceRequestDeliveryMessage();
		// add key-value list.
		HashMap<String, String> headerList = new HashMap<String, String>();
		headerList.put("Content-Type", "application/json");
		// headerList.put("Content-Length", "238");

		req.addValue(HttpClientService.KEY_HEADER_PARAMETERS, headerList);
		req.addValue(HttpClientService.KEY_SERVICE_URL, serviceUrl);
		req.addValue(HttpClientService.KEY_CONNECTION_TIMEOUT, 50000);
		req.addValue(HttpClientService.KEY_HTTP_METHOD,
				HttpClientService.VALUE_HTTP_METHOD_POST);
		// req.addValue(HttpClientService);

		// body data
		// StringInputStream inputStream = new StringInputStream(body);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				body.getBytes());
		req.addValue(HttpClientService.KEY_BODY_INPUT_STREAM, inputStream);

		// response delivery message
		HttpClientServiceResponseDeliveryMessage res = new HttpClientServiceResponseDeliveryMessage();
		IHttpResponseMessage httpResponseMessage = null;
		// HttpClientService
		IGenericService service = new HttpClientService();
		httpResponseMessage = getiHttpResponseMessage(req, res, httpResponseMessage, service);
		return httpResponseMessage;
	}

	/**
	 * request get service.<BR/>
	 *
	 * @param uri
	 * @return
	NOTE: 외부에서 데이터를 조회하기 위함. Url 의 내용을 조회
	 */
	public IHttpResponseMessage requestGetService(String uri)
	{
		// request delivery message
		HttpClientServiceRequestDeliveryMessage req = new HttpClientServiceRequestDeliveryMessage();
		// add key-value list.
		req.addValue(HttpClientService.KEY_SERVICE_URL, uri);
		req.addValue(HttpClientService.KEY_CONNECTION_TIMEOUT,DATA_TIMEOUT_VALUE);
		req.addValue(HttpClientService.KEY_HTTP_METHOD,
				HttpClientService.VALUE_HTTP_METHOD_GET);

		// response delivery message
		HttpClientServiceResponseDeliveryMessage res = new HttpClientServiceResponseDeliveryMessage();

		// HttpClientService
		IGenericService service = new HttpClientService();
		// return IHttpResponseMessage
		IHttpResponseMessage httpResponseMessage = null;
		httpResponseMessage = getiHttpResponseMessage(req, res, httpResponseMessage, service);
		return httpResponseMessage;
	}

	private IHttpResponseMessage getiHttpResponseMessage(HttpClientServiceRequestDeliveryMessage req,
														 HttpClientServiceResponseDeliveryMessage res,
														 IHttpResponseMessage httpResponseMessage,
														 IGenericService service) {
		try
		{
			// execute a service
			service.onService(req, res);
			// get a service response
			httpResponseMessage = res
					.getValue(HttpClientService.KEY_HTTP_RESPONSE);
			// print a response
			//System.out.println(res);
		}
		catch (ServiceException e)
		{
			System.out.println("connection fail :" + e.getMessage());
			//e.printStackTrace();
		}
		return httpResponseMessage;
	}

	public String requestPostServiceReceiveString(String uri, String data) {
		return requestPostServiceReceiveString(uri,data,false);
	}

	public String requestPostServiceReceiveString(String uri, String data, boolean onlyResultValue) {
		IHttpResponseMessage httpResponseMessage = requestPostService(uri, data);
		String returnData = null;
		if (onlyResultValue) {
			returnData = String.valueOf(httpResponseMessage.getStatusCode());
		} else {
			returnData = responseDataToString(httpResponseMessage);
		}
		return returnData;
	}


	public String requestGetServiceReceiveString(String uri) {
		IHttpResponseMessage httpResponseMessage = requestGetService(uri);
		String returnData = responseDataToString(httpResponseMessage);
		return returnData;
	}

	/**
	 * response Data to String.<BR/>
	 *
	 * @param message
	 * @return String
	 */
	public String responseDataToString(IHttpResponseMessage message)
	{
		String result = null;

		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(
					message.getBodyInputStream()), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			result = sb.toString();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return result;
	}

	//=======================================================================================================
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
