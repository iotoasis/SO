package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.SessionEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jonghee on 2017-06-02.
 */
@Component
public class SessionDao extends AbstractDao {

    protected Logger log = LoggerFactory.getLogger(getClass());
	
    // create session
    public void createSessionData(SessionEntity sessionEntity) {
    	try {
        	super.sqlSession.insert("createSessionData", sessionEntity);
    	} catch(DataIntegrityViolationException e) {
    		log.error("createSessionData: error={}", e.getMessage());
    	}
    }

    public void createSessionDataDevice(SessionEntity sessionEntity) {
    	try {
            super.sqlSession.insert("createSessionDataDevice", sessionEntity);
    	} catch(DataIntegrityViolationException e) {
    		log.error("createSessionDataDevice: error={}", e.getMessage());
    	}
    }

    public void createSessionDataLocation(SessionEntity sessionEntity) {
    	try {
            super.sqlSession.insert("createSessionDataLocation", sessionEntity);
    	} catch(DataIntegrityViolationException e) {
    		log.error("createSessionDataLocation: error={}", e.getMessage());
    	}
    }

    public void createSessionDataVo(SessionEntity sessionEntity) {
    	try {
            super.sqlSession.insert("createSessionDataVo", sessionEntity);
    	} catch(DataIntegrityViolationException e) {
    		log.error("createSessionDataVo: error={}", e.getMessage());
    	}
    }

    // retrieve session_data
    public List<SessionEntity> retrieveRecentlySessionList(int number) {
        return super.sqlSession.selectList("retrieveRecentlySessionList", number);
    }

    // retrieve session_data By Id
    public SessionEntity retrieveSessionData(String sessionId) {
        return super.sqlSession.selectOne("retrieveSessionData", sessionId);
    }
    
    // retrieve session_data_device
    public List<SessionEntity> retrieveSessionDataDevice(String sessionId) {
        return super.sqlSession.selectList("retrieveSessionDataDevice", sessionId);
    }
    
    // retrieve session_data_location
    public List<String> retrieveSessionDataLocation(String sessionId) {
        return super.sqlSession.selectList("retrieveSessionDataLocation", sessionId);
    }
    
    // retrieve session_data_vo
    public List<String> retrieveSessionDataVo(String sessionId) {
        return super.sqlSession.selectList("retrieveSessionDataVo", sessionId);
    }

//    public List<SessionEntity> retrieveSessionByUserId(String userId) {
//        return super.sqlSession.selectList("retrieveSessionByUserId", userId);
//    }

//    public SessionEntity retrieveSessionStatus(String sessionId) {
//        return super.sqlSession.selectOne("retrieveSessionStatus", sessionId);
//    }

    // update
    public void updateSessionData(SessionEntity sessionEntity) {
    	try {
    		super.sqlSession.update("updateSessionData", sessionEntity);
    	} catch(DataIntegrityViolationException e) {
    		log.error("updateSessionData: error={}", e.getMessage());
    	}
    }
    
    // delete
    
}
