package com.pineone.icbms.so.util.restful;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

/**
 * Created by use on 2015-10-12.
 */
public class ClientService implements IClientService
{
    private final Logger log = LoggerFactory.getLogger(ClientService.class);

	public static final String	SIDOMAIN		= "http://localhost:8090/";
	public static final String	SICOMMAND		= "http://116.124.171.125:8081/si/command";
	public static final String	SDA_TEST_DOMAIN	= "http://localhost:18080";
	public static final String	SERVICE_ENGINE	= "http://localhost:8080/so/service/engine";

	/** Context Model 조회 */
	public static final String SDA_GET_CONTEXTMODEL = "http://192.168.1.186:18080/sda/itf/cm";

	/** Context Model 등록 - POST */
	public static final String SDA_REGIST_CONTEXTMODEL = "http://192.168.1.186:18080/sda/itf/cm";

	public static final String	SIDEVICECONTROL			= "http://localhost:8090/si/controlmessage";
	public static final String	ACCEPT_ENCODING			= "Accept-Encoding";
	public static final String	ACCEPT_ENCODING_IDNTITY	= "identity";
	public static final String	CONTENT_TYPE			= "Content-Type";
	public static final String	CONTENT_TYPE_JSON		= "application/json";
	public static final String	ENDODING_UTF8			= "UTF-8";

	public static final String	SI_DEVICE_RESOURCE_WALLY6	= "/herit-in/herit-cse/wally6";
	public static final String	SI_DEVICE_RESOURCE_HUE8		= "/herit-in/herit-cse/hue8";
	public static final String	SI_DEVICE_RESOURCE_WALLY5	= "/herit-in/herit-cse/wally5";
	public static final String	SI_DEVICE_RESOURCE_HUE7		= "/herit-in/herit-cse/hue7";

	public ClientService() {
	}

	@Override
	public HttpResponse requestData(String uri)
	{
		HttpResponse response = null;

        log.info("RequestData get start");
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(uri);
			get.setHeader(ACCEPT_ENCODING, ACCEPT_ENCODING_IDNTITY);
			get.setHeader(CONTENT_TYPE, CONTENT_TYPE_JSON);

			response = client.execute(get);

			System.out.println("Output from Server result data .... \n");

		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public HttpResponse requestData(String uri, String data)
	{

        log.info("requestData post start");
        log.debug("requestData : " + data);
		HttpResponse response = null;

		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(uri);
			post.setHeader(ACCEPT_ENCODING, ACCEPT_ENCODING_IDNTITY);
			post.setHeader(CONTENT_TYPE, CONTENT_TYPE_JSON);

			HttpEntity entity = new ByteArrayEntity(
					data.getBytes(ENDODING_UTF8));
			post.setEntity(entity);
			response = client.execute(post);

			/**
			 * String result = EntityUtils.toString(response.getEntity());
			 * System.out.println(result);
			 * 
			 * DataService에서 같은 메소드를 사용하는데, 중복이 되면 현재 메서드 안에서 사용한 이후에 스트림을 닫아버리는
			 * 오류가 발생하여 주석처리
			 */
			System.out.println("Output from Server result data .... \n");

		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return response;
	}
}
