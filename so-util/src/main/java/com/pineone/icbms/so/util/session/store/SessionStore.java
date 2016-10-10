package com.pineone.icbms.so.util.session.store;

import com.pineone.icbms.so.util.session.DefaultSession;

import java.util.List;

/**
 * Created by melvin on 2016. 10. 7..
 */
public interface SessionStore {
    void createSession(DefaultSession session);
    List<DefaultSession> retrieveSessionList();
    DefaultSession retrieveSessionDetail(String sessionId);
    void updateSession(DefaultSession session);
}
