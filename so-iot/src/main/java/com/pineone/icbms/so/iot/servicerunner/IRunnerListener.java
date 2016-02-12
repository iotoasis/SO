package com.pineone.icbms.so.iot.servicerunner;

public interface IRunnerListener {
	public void OnFail(String id, ServiceData data);
	public void OnComplete(String id, ServiceData data);
}
