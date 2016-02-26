package com.pineone.icbms.so.iot.servicerunner;

import com.pineone.icbms.so.resources.service.AGenericService;

public class ServiceWorkData {

	private long mDeliveryTag;
	private String mExchange;
	private String mRoutingKey;
	private String mConsumerTag;
	private AGenericService mService;
	
	public ServiceWorkData(AGenericService service, String consumerTag, long deliveryTag) {
		mService = service;
		mDeliveryTag = deliveryTag;
		mConsumerTag = new String(consumerTag);
	}
	
	public void setRoutingKey(String routingKey) {
		mRoutingKey = new String(routingKey);
	}
	
	public void setExchange(String exchange) {
		mExchange = new String(exchange);
	}

	public long getDeliveryTag() {
		return mDeliveryTag;
	}

	public Object getRoutingKey() {
		return mRoutingKey;
	}

	public AGenericService getService() {
		return mService;
	}
}
