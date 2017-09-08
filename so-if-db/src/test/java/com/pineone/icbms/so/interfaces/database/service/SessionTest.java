//package com.pineone.icbms.so.interfaces.database.service;
//
//import com.pineone.icbms.so.interfaces.database.dao.TrackingDao;
//import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
//import com.pineone.icbms.so.interfaces.database.session.store.SessionStore;
//import com.pineone.icbms.so.util.session.Session;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
///**
// * Created by jonghee on 2017-06-15.
// * test/resource
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
////@MapperScan(value = {"com.pineone.icbms.so.interfaces.database.dao"})
//public class SessionTest {
//
//    /**
//     * logger
//     */
//    protected Logger log = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    SessionStore sessionStore;
//    //DataBaseStore dao;
//
//    @Test
////    @Ignore
//    public void test() throws Exception {
//        List<Session> sessionList = sessionStore.retrieveRecentlyDataList(10);
//        for (Session session : sessionList)
//            System.out.println("Session id = " + session.getId());
//            //log.debug("tracking : {}", tracking);
//    }
//
//}