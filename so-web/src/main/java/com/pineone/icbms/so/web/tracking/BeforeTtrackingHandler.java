package com.pineone.icbms.so.web.tracking;

import com.kastkode.springsandwich.filter.api.BeforeHandler;
import com.kastkode.springsandwich.filter.api.Flow;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.util.id.IdUtils;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017-04-18.
 */
@Component
@CrossOrigin(origins = "*")
public class BeforeTtrackingHandler implements BeforeHandler {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

//    @Autowired
//    public DefaultProducerHandler producerHandler;

    @Override
    public Flow handle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, String[] flags) throws Exception {

        // session id
        String sessionId = IdUtils.createRandomUUID();
        TrackingEntity tracking = new TrackingEntity(sessionId);
            tracking.setUri(request.getRequestURI());
            tracking.setMethod(request.getMethod());
            tracking.setRemoteAddr(request.getRemoteAddr());
            tracking.setRemoteHost(request.getRemoteHost());
            //tracking.addProcess("API요청 : " + request.getRequestURI());
        tracking.setProcess("API요청 : " + request.getRequestURI());
        //Logging.insert(logging);
        HttpSession session = request.getSession();
        // set session
        session.setAttribute("tracking", tracking);

        // TODO tracking
        DefaultProducerHandler trackingHandler = new DefaultProducerHandler(0, "tracking");
        trackingHandler.send(tracking);
        trackingHandler.close();

        //or return Flow.HALT to halt this request and prevent execution of the context
        //you may also wish to redirect to a login page here
        return Flow.CONTINUE;
    }
}
