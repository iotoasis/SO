package com.pineone.icbms.so.util.handler;

/**
 * handler interface.<BR/>
 *
 * Created by uni4love on 2016. 5. 24..
 */
public interface IHandler<T> {
    /**
     * handle message.<BR/>
     *
     * @param handleMessage handle message
     */
    void handle(T handleMessage);
}
