package com.pineone.icbms.so.interfaces.database.service;

import com.pineone.icbms.so.interfaces.database.dao.TrackingDao;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by jonghee on 2017-06-15.
 * test/resource
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@MapperScan(value = {"com.pineone.icbms.so.interfaces.database.dao"})
public class ITrackingMapperTest {

    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TrackingDao dao;
    //DataBaseStore dao;

//    @Test
    @Ignore
    public void createTracking() throws Exception {
        TrackingEntity tracking = new TrackingEntity();
        tracking.setSessionId("test");
        tracking.setUri("test");
        tracking.setMethod("test");
        tracking.setRemoteAddr("test");
        tracking.setRemoteHost("test");
        //tracking.setProcess("test");

        dao.createTracking(tracking);
    }

//    @Test
////    @Ignore
//    public void retr() throws Exception {
//        List<TrackingEntity> tracking = dao.retrieveRecentlySessionList(10);
//        for (TrackingEntity entity : tracking)
//            System.out.println("tracking id = " + entity.getSessionId());
//            //log.debug("tracking : {}", tracking);
//    }

}