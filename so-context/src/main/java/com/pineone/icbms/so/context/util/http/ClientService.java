package com.pineone.icbms.so.context.util.http;

import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import com.withwiz.jellyfish.service.IGenericService;
import com.withwiz.jellyfish.service.ServiceException;
import com.withwiz.service.network.http.client.httpclientservice.HttpClientService;
import com.withwiz.service.network.http.client.httpclientservice.HttpClientServiceRequestDeliveryMessage;
import com.withwiz.service.network.http.client.httpclientservice.HttpClientServiceResponseDeliveryMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

/**
 * ClientService class<BR/>
 * Created by use on 2015-10-12.
 * NOTE: 외부 통신을 위해 사용
 */
public class ClientService
{
	public static final int DATA_TIMEOUT_VALUE = 9000;

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
		log.info("[[Client Service requestPostService uri]] " + serviceUrl);
		log.info("[[Client Service requestPostService data]] " + body);
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
			System.out.println(res);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		return httpResponseMessage;
	}

}
