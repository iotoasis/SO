package com.pineone.icbms.so.web.interfaces.api.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineone.icbms.so.interfaces.database.dao.SessionDao;
import com.pineone.icbms.so.interfaces.database.dao.TrackingDao;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;

import com.pineone.icbms.so.util.session.SessionData;
import com.pineone.icbms.so.util.session.SessionTransFormObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melvin on 2016. 10. 14..
 */
//@Before(@BeforeElement(BeforeTtrackingHandler.class))
@RestController
@RequestMapping(value ="/session")
@ResponseStatus(value = HttpStatus.OK)
public class SessionController {

//    @Autowired
//    SessionStore sessionStore;
//
//    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<SessionTransFormObject> retrieveSessionData(@PathVariable int number){
//        List<Session> sessionList = sessionStore.retrieveRecentlyDataList(number);
//        return sessionToTransFormObject(sessionList);
//    }

    @Autowired
    //TrackingDao trackingDao;
    SessionDao sessionDao;

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<SessionTransFormObject> retrieveSessionData(@PathVariable int number) {

        // 갯수만큼의 최신 tracking id, createDate 조회
        //List<TrackingEntity> trackingList = trackingDao.retrieveRecentlySessionList(number);
    
        List<SessionTransFormObject> sessionTransFormObjects = new ArrayList<>();
        
        List<SessionEntity> sessionList = sessionDao.retrieveRecentlySessionList(number);

        for (SessionEntity entity : sessionList) {
            SessionTransFormObject defaultSession = new SessionTransFormObject();
            defaultSession.setId(entity.getId());
            defaultSession.setCreateDate(entity.getCreateDate());
    
            // location
            //sessionDao.retrieveSessionDataLocation(entity.getId());
            // device list
            List<SessionEntity> devices = sessionDao.retrieveSessionDataDevice(entity.getId());
            
            List<String> deviceKeys = new ArrayList<>();
            List<String> deviceLocs = new ArrayList<>();
            for (SessionEntity sessionEntity : devices) {
                deviceKeys.add(sessionEntity.getDeviceKey());
                deviceLocs.add(sessionEntity.getDeviceLocation());
            }
            // vo list
            //sessionDao.retrieveSessionDataVo(entity.getId());
    
            Map<String, String> sessionData = new HashMap<>();
            sessionData.put("PRIORITY_KEY", entity.getPriorityKey() == null ? "LOW" : entity.getPriorityKey());
            sessionData.put("PROFILE_NAME", entity.getProfileName());
            sessionData.put("CONTEXTMODEL_NAME", entity.getContextmodelName());
            sessionData.put("CONTEXTMODEL_KEY", entity.getContextmodelKey());
            sessionData.put("LOCATION_ID", listToJacksonString(sessionDao.retrieveSessionDataLocation(entity.getId())));
            sessionData.put("CONTEXTMODEL_RESULT", entity.getContextmodelResult());
            sessionData.put("PROFILE_KEY", entity.getProfileKey());
            sessionData.put("SERVICEMODEL_KEY", entity.getServicemodelKey());
            sessionData.put("SERVICEMODEL_NAME", entity.getServicemodelName());
            sessionData.put("SERVICEMODEL_RESULT", entity.getServicemodelResult());
            sessionData.put("SERVICE_KEY", "["+entity.getServiceKey()+"]");//listToJacksonString());
            sessionData.put("VIRTUALOBJECT_KEY", listToJacksonString(sessionDao.retrieveSessionDataVo(entity.getId())));
            sessionData.put("VIRTUALOBJECT_RESULT", entity.getVirtualobjectResult());
            sessionData.put("DEVICE_KEY", listToJacksonString(deviceKeys));
            sessionData.put("DEVICE_LOCATION", listToJacksonString(deviceLocs));
            sessionData.put("DEVICE_RESULT", entity.getDeviceResult());
            sessionData.put("SERVICE_RESULT", entity.getServiceResult());
    
            defaultSession.setSessionData(sessionData);
    
            sessionTransFormObjects.add(defaultSession);
        }

        return sessionTransFormObjects;
    }

//    @RequestMapping(value = "/time/{time}", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Session> retrieveSessionDataByTime(@PathVariable int time){
//        List<Session> sessionList = null;//sessionStore.retrieveRecentlyDataListByTime(time);
//        return sessionList;
//    }
    
    private String listToJacksonString(List<String> list) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // List --> String
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            return null;
        }
    }

    private List<SessionTransFormObject> sessionToTransFormObject(List<Session> sessionList){
        List<SessionTransFormObject> sessionTransFormObjects = new ArrayList<>();
        for(Session session : sessionList){
            sessionTransFormObjects.add(new SessionTransFormObject(session.getId(),session.getCreateDate(),session.getSessionData()));
        }
        return sessionTransFormObjects;
    }

}
