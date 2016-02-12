package com.pineone.icbms.so.iot.servicerunner.tp;

public interface ThreadPoolListener {
	public void OnComplete(long id);
	public void OnFail(long id);
	public void OnThreadCrash(long id);
}
