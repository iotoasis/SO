package com.pineone.icbms.so.util.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by existmaster on 2016. 10. 5..
 */
public class DefaultSession implements Session {

    public static final String PROFILE_ID_KEY = "profileId";
    public static final String PRIORITY_KEY = "priorityName";

    /**
     *  The session unique id.
     */
    private String id;

    /**
     *  The sessionData is pair of key, value.
     */
    private Map<String, String> sessionData;

    public DefaultSession() {
        id = UUID.randomUUID().toString();
        sessionData = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isExistSessionData(String key) {
        return sessionData.containsKey(key);
    }
    @Override
    public String findSessionData(String key) {
        return sessionData.get(key);
    }

    @Override
    public String insertSessionData(String key, String value) {
        return sessionData.put(key, value);
    }

    @Override
    public String removeSessionData(String key) {
        return sessionData.remove(key);
    }

}
