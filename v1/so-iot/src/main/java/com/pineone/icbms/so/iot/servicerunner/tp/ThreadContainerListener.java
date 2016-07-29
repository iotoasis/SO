package com.pineone.icbms.so.iot.servicerunner.tp;


public interface ThreadContainerListener {
	public void OnStandBy(long id);
	public void OnComplete(long id);
	public void OnFail(long id);
	public void OnThreadCrash(long id);
}
