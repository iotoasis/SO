package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jonghee on 2017-06-02.
 */
@Component
public class TrackingDao extends AbstractDao {

    public void createTracking(TrackingEntity trackingEntity) {
        super.sqlSession.insert("createTracking", trackingEntity);
    }

    public List<TrackingEntity> retrieveTrackingBySessionId(String sessionId) {
        return super.sqlSession.selectList("retrieveTrackingBySessionId", sessionId);
    }

    public Integer retrieveTrackingBySessionIdStatusFinish(String sessionId) {
        return super.sqlSession.selectOne("retrieveTrackingBySessionId_Status_Finish", sessionId);
    }
    public List<TrackingEntity> retrieveTrackingByUserId(String userId) {
        return super.sqlSession.selectList("retrieveTrackingByUserId", userId);
    }

    public TrackingEntity retrieveTrackingStatus(String sessionId) {
        return super.sqlSession.selectOne("retrieveTrackingStatus", sessionId);
    }
}
