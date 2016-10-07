package com.pineone.icbms.so.util.session.store.mongo;

import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
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
    public void createSession(Session session) {
        logger.debug("Session = " + session.toString());
        SessionDataObject sessionDataObject = sessionToDataObject(session);
        System.out.println("읭" + session.getId());
        System.out.println(sessionDataObject.getId() + "엥");
        System.out.println(sessionRepository);
        sessionRepository.save(sessionDataObject);
    }

    @Override
    public List<Session> retrieveSessionList() {
        List<SessionDataObject> sessionDataObjectList = sessionRepository.findAll();
        List<Session> sessionList = new ArrayList<>();
        for(SessionDataObject sessionDataObject : sessionDataObjectList){
            sessionList.add(dataObjectToSession(sessionDataObject));
            logger.debug("Session = " + dataObjectToSession(sessionDataObject));
        }
        return sessionList;
    }

    @Override
    public boolean isExistSession(String sessionId) {
        boolean checker = sessionRepository.exists(sessionId);
        return checker;
    }

    @Override
    public void addProfileId(Session session, String profileId) {
        SessionDataObject sessionDataObject = sessionRepository.findById(session.getId());
        sessionDataObject.setProfileId(profileId);
        sessionRepository.save(sessionDataObject);
        logger.debug("session : " + sessionDataObject.toString() );
    }

    @Override
    public void addPriority(Session session, String priority) {
        SessionDataObject sessionDataObject = sessionRepository.findById(session.getId());
        sessionDataObject.setPriority(priority);
        sessionRepository.save(sessionDataObject);
        logger.debug("session : " + sessionDataObject.toString() );
    }

    private SessionDataObject sessionToDataObject(Session session){
        if(session == null) return null;
        return new SessionDataObject((session.getId()));

    }

    private Session dataObjectToSession(SessionDataObject sessionDataObject){
        if(sessionDataObject == null) return null;
        return new DefaultSession(sessionDataObject.getId());
    }
}
