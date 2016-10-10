package com.pineone.icbms.so.util.session;

/**
 * Created by existmaster on 2016. 10. 5..
 */
public interface Session {

    String getId();

    boolean isExistSessionData(String key);

    String findSessionData(String key);

    String insertSessionData(String key, String value);

    String removeSessionData(String key);

}
