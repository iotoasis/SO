package com.pineone.icbms.so.util.session.pr;

import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Session> retrieveSessionData(@PathVariable int number){
        List<Session> sessionList = sessionStore.retrieveRecentlyDataList(number);
        return sessionList;
    }

    @RequestMapping(value = "/time/{time}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Session> retrieveSessionDataByTime(@PathVariable int time){
        List<Session> sessionList = sessionStore.retrieveRecentlyDataListByTime(time);
        return sessionList;
    }
}
