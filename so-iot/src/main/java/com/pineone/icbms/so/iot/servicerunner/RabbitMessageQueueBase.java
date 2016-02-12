package com.pineone.icbms.so.iot.servicerunner;

abstract public class RabbitMessageQueueBase implements IRabbitMessageQueueBase {
	
	private static final String RMO_IP = "166.104.112.37";
	private static final int RMQ_PORT = 5671;
	private static final String RMO_USER_ID = "pineone";
	private static final String RMO_USER_PASSWORD = "vkdls1dnjs";
	
	@Override
	public String getIp() {
		return RMO_IP;
	}

	@Override	
	public int getPort() {
		return RMQ_PORT;
	}

	@Override
	public String getUserId() {
		return RMO_USER_ID;
	}

	@Override
	public String getPassword() {
		return RMO_USER_PASSWORD;
	}
}

