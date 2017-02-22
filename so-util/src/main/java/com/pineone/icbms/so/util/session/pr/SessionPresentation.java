package com.pineone.icbms.so.util.session.pr;

import com.pineone.icbms.so.util.logprint.LogPrint;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 10. 14..
 */

@RestController
@RequestMapping(value ="/session")
@ResponseStatus(value = HttpStatus.OK)
public class SessionPresentation {

    @Autowired
    SessionStore sessionStore;

    public static final Logger logger = LoggerFactory.getLogger(SessionPresentation.class);

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<SessionTransFormObject> retrieveSessionData(@PathVariable int number){
        List<Session> sessionList = sessionStore.retrieveRecentlyDataList(number);
        return sessionToTransFormObject(sessionList);
    }

    @RequestMapping(value = "/time/{time}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Session> retrieveSessionDataByTime(@PathVariable int time){
        List<Session> sessionList = sessionStore.retrieveRecentlyDataListByTime(time);
        return sessionList;
    }

    @RequestMapping(value = "/collection/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Session retrieveSessionDetail(@PathVariable String id){
        Session session = sessionStore.retrieveSessionDetail(id);
        logger.info(LogPrint.inputInfoLogPrint() + "Session = " + session.toString());
        return session;
    }


    private List<SessionTransFormObject> sessionToTransFormObject(List<Session> sessionList){
        List<SessionTransFormObject> sessionTransFormObjects = new ArrayList<>();
        for(Session session : sessionList){
            sessionTransFormObjects.add(new SessionTransFormObject(session.getId(),session.getCreateDate(),session.getSessionData()));
        }
        return sessionTransFormObjects;
    }

}
