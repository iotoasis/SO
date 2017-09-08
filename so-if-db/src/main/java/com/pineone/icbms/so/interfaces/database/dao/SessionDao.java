package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jonghee on 2017-06-02.
 */
@Component
public class SessionDao extends AbstractDao {

    // create session
    public void createSessionData(SessionEntity sessionEntity) {
        super.sqlSession.insert("createSessionData", sessionEntity);
    }

    public void createSessionDataDevice(SessionEntity sessionEntity) {
        super.sqlSession.insert("createSessionDataDevice", sessionEntity);
    }

    public void createSessionDataLocation(SessionEntity sessionEntity) {
        super.sqlSession.insert("createSessionDataLocation", sessionEntity);
    }

    public void createSessionDataVo(SessionEntity sessionEntity) {
        super.sqlSession.insert("createSessionDataVo", sessionEntity);
    }

    // retrieve session_data
    public List<SessionEntity> retrieveRecentlySessionList(int number) {
        return super.sqlSession.selectList("retrieveRecentlySessionList", number);
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
        super.sqlSession.update("updateSessionData", sessionEntity);
    }
    
    // delete
    
}
