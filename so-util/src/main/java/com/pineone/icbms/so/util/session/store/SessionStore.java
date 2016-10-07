package com.pineone.icbms.so.util.session.store;

import com.pineone.icbms.so.util.session.Session;

import java.util.List;

/**
 * Created by melvin on 2016. 10. 7..
 */
public interface SessionStore {

    void createSession(Session session);
    List<Session> retrieveSessionList();
    boolean isExistSession(String sessionId);
    void addProfileId(Session session, String profileId);
    void addPriority(Session session, String priority);
}
