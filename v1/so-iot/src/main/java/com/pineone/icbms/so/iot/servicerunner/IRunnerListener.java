package com.pineone.icbms.so.iot.servicerunner;

public interface IRunnerListener {
	public void OnFail(String id, ServiceWorkData data);
	public void OnComplete(String id, ServiceWorkData data);
}
