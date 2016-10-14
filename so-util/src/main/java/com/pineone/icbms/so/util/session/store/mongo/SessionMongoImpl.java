package com.pineone.icbms.so.util.session.store.mongo;

import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.InvalidMongoDbApiUsageException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by melvin on 2016. 10. 7..
 */
@Repository
public class SessionMongoImpl implements SessionStore {

    public static final Logger logger = LoggerFactory.getLogger(SessionMongoImpl.class);

    Long currentDate = System.currentTimeMillis();
    Long pastDate;

    public Long changeDate(int time) {
        pastDate = currentDate - (long)(time*1000);
        return pastDate;
    }

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    MongoOperations mongoOperations;

    Query query = new Query();


    @Override
    public List<Session> retrieveRecentlyDataListByTime(int time){

        pastDate = changeDate(time);
        query.addCriteria(
                Criteria.where("calculateTime").exists(true)
                .andOperator(
                        Criteria.where("calculateTime").gt(pastDate),
                            Criteria.where("calculateTime").lt(currentDate)
                )
        );
        
        List<SessionDataObject> sessionDataObjectList = mongoOperations.find(query, SessionDataObject.class);
        List<Session> sessionList = new ArrayList<>();
        for(SessionDataObject sessionDataObject : sessionDataObjectList){
            sessionList.add(dataObjectToSession(sessionDataObject));
            logger.debug("Session = " + dataObjectToSession(sessionDataObject));
        }
        return sessionList;
    }



    @Override
    public List<Session> retrieveRecentlyDataList(int num){
        query.limit(num);
        query.with(new Sort(Sort.Direction.DESC, "mongoTime"));


        List<SessionDataObject> sessionDataObjectList = mongoOperations.find(query, SessionDataObject.class);
        List<Session> sessionList = new ArrayList<>();
        for(SessionDataObject sessionDataObject : sessionDataObjectList){
            sessionList.add(dataObjectToSession(sessionDataObject));
            logger.debug("Session = " + dataObjectToSession(sessionDataObject));
        }
        return sessionList;
    }

    //NOTE : DB에 세션 데이터 등록
    @Override
    public void createSession(Session session) {
        logger.debug("Session = " + session.toString());
        SessionDataObject sessionDataObject = sessionToDataObject(session);
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
    public DefaultSession retrieveSessionDetail(String sessionId) {
        SessionDataObject sessionDataObject = sessionRepository.findOne(sessionId);
        DefaultSession session = dataObjectToSession(sessionDataObject);
        return session;
    }


    @Override
    public void updateSession(Session session) {
        SessionDataObject sessionDataObject = sessionToDataObject(session);
        sessionRepository.save(sessionDataObject);
        }

    private SessionDataObject sessionToDataObject(Session session){
        if(session == null) return null;
        return new SessionDataObject(session.getId(), session.getSessionData(), session.getCreateDate(),
                session.getMongoTime(), session.getCalculateTime());

    }

    private DefaultSession dataObjectToSession(SessionDataObject sessionDataObject){
        if(sessionDataObject == null) return null;
        return new DefaultSession(sessionDataObject.getId(), sessionDataObject.getSessionData(),
                sessionDataObject.getCreateDate(), sessionDataObject.getMongoTime(), sessionDataObject.getCalculateTime());
    }
}
