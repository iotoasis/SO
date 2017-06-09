package com.pineone.icbms.so.util.messagequeue.consumer;

import com.pineone.icbms.so.util.handler.IHandler;

import java.util.Collection;

/**
 * Producer handler interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public interface IConsumerHandler<M, T> extends IHandler<T> {
    /**
     * subscribe to the given list of topics.<BR/>
     *
     * @param topics topic list
     */
    void subscribe(Collection<String> topics);

    /**
     * read message and return.<BR/>
     *
     * @return read messages
     */
    M getMessage();

    /**
     * consumer close method.<BR/>
     */
    void close();
}
