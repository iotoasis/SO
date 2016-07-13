package com.pineone.icbms.so.service;

/**
 * Created by pahnj on 2016-07-12.
 */
public interface ClientService {
    String requestPostService(String uri, String data);
    String requestGetService(String uri);
}
