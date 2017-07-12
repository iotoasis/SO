package com.pineone.icbms.so.interfaces.database.service;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jonghee on 2017-06-15.
 * test/resource
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value = {"com.pineone.icbms.so.interfaces.database.service"})
public class DataBaseStoreTest {

    @Autowired
    DataBaseStore mapper;

    @Test
    public void createTracking() throws Exception {
        TrackingEntity tracking = new TrackingEntity();
        tracking.setSessionId("DataBaseStore test");
        tracking.setUri("test");
        tracking.setMethod("test");
        tracking.setRemoteAddr("test");
        tracking.setRemoteHost("test");
        //tracking.setProcess("test");

        mapper.createTracking(tracking);
    }

}