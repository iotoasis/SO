package com.pineone.icbms.so.util.session.store.mongo;

import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 10. 7..
 */
@Repository
public class SessionMongoImpl implements SessionStore{

    public static final Logger logger = LoggerFactory.getLogger(SessionMongoImpl.class);

    @Autowired
    SessionRepository sessionRepository;

    //NOTE : DB에 세션 데이터 등록
    @Override
    public void createSession(DefaultSession session) {
        logger.debug("Session = " + session.toString());
        SessionDataObject sessionDataObject = sessionToDataObject(session);
        sessionRepository.save(sessionDataObject);
    }

    @Override
    public List<DefaultSession> retrieveSessionList() {
        List<SessionDataObject> sessionDataObjectList = sessionRepository.findAll();
        List<DefaultSession> sessionList = new ArrayList<>();
        for(SessionDataObject sessionDataObject : sessionDataObjectList){
            sessionList.add(dataObjectToSession(sessionDataObject));
            logger.debug("Session = " + dataObjectToSession(sessionDataObject));
        }
        return sessionList;
    }

    @Override
    public DefaultSession retrieveSessionDetail(String sessionId) {
        SessionDataObject sessionDataObject = sessionRepository.findOne(sessionId);
        DefaultSession session = dataObjectToSession(sessionDataObject);
        return session;
    }

    @Override
    public void updateSession(DefaultSession session) {
        SessionDataObject sessionDataObject = sessionToDataObject(session);
        sessionRepository.save(sessionDataObject);
    }

    private SessionDataObject sessionToDataObject(DefaultSession session){
        if(session == null) return null;
        return new SessionDataObject(session.getId(), session.getSessionData());

    }

    private DefaultSession dataObjectToSession(SessionDataObject sessionDataObject){
        if(sessionDataObject == null) return null;
        return new DefaultSession(sessionDataObject.getId(), sessionDataObject.getSessionData());
    }
}
