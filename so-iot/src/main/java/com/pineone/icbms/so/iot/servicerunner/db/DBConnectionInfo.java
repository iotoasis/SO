package com.pineone.icbms.so.iot.servicerunner.db;

abstract public class DBConnectionInfo implements IDBConnectionInfo {
	private final static String DEFAULT_IP = "192.168.1.11";
	private final static int DEFAULT_PORT = 37017;
	
	public DBConnectionInfo() {
		
	}

	@Override
	public String getIp() {
		return DEFAULT_IP;
	}

	@Override
	public int getPort() {
		return DEFAULT_PORT;
	}
}
