package com.pineone.icbms.so.web.interfaces.api.admin.controller;

import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import com.pineone.icbms.so.interfaces.database.dao.TrackingDao;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.ContextModelForMQ;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf2;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.web.interfaces.api.service.context.ContextModelController;
import com.pineone.icbms.so.web.tracking.BeforeTtrackingHandler;
import com.pineone.icbms.so.web.util.ContextModelMapper2;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by jonghee on 2017-06-29.
 */
@Before(@BeforeElement(BeforeTtrackingHandler.class))
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(ContextModelController.class);

    @Autowired
    TrackingDao trackingDao;

    @RequestMapping(value = "/tracking/{sessionId}", method = RequestMethod.GET)
    @ResponseBody
    public List<TrackingEntity> getTrackingBySessionId(@PathVariable String sessionId, HttpServletRequest request) {

        log.debug("sessionId: {}", sessionId);

        List<TrackingEntity> trackingList = trackingDao.retrieveTrackingBySessionId(sessionId);

        return trackingList;
    }
}
