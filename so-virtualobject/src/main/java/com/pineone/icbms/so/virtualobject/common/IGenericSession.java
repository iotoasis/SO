package com.pineone.icbms.so.virtualobject.common;

/**
 * IGenericSession generic interface.<BR/>
 *
 * Created by jonghee on 2016. 11. 16..
 */
public interface IGenericSession<T> {
	T getSessionId();
	void setSessionId(T id);
}
