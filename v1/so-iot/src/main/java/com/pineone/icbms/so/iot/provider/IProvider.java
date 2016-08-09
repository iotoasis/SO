package com.pineone.icbms.so.iot.provider;

import java.util.List;


public interface IProvider {
	public <T> T getDataByID(String id, Class<T> cls);
	public <T> T getDataByName(String name, Class<T> cls);
	public <T> List<T> getDataListByName(String name, Class<T> cls);
}
