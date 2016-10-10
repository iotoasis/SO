package com.pineone.icbms.so.util.session.store;

import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;

import java.util.List;

/**
 * Created by melvin on 2016. 10. 7..
 */
public interface SessionStore {
    void createSession(Session session);
    List<Session> retrieveSessionList();
    DefaultSession retrieveSessionDetail(String sessionId);
    void updateSession(Session session);
}
