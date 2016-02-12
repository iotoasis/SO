package com.pineone.icbms.so.util.restful;

import com.withwiz.beach.network.http.message.IHttpResponseMessage;

/**
 * Created by pahnj on 2016-01-08.
 */
public interface IClientService {

    IHttpResponseMessage requestPostService(String uri, String data);
    IHttpResponseMessage requestGetService(String uri);
}
