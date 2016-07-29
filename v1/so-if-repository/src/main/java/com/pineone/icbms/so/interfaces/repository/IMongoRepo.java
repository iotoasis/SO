package com.pineone.icbms.so.interfaces.repository;


import java.util.List;

public interface IMongoRepo<K, R> {
	public <V, T> T getData(K key, V value, Class<T> cls);              // Data DB select (Default V data : Model Object)
	public <V, T> List<T> getDataList(K key, V value, Class<T> cls);    // Data DB select (Default V data : Model Object List)
	public <D> R setData(D data);              // Data DB insert (Default V data : Model Object)
	public <V, D> R updateData(K key, V value, D data);           // Data DB update (Default V data : Model Object) 
	public <V> R removeData(K key, V value);           // Data DB
}
