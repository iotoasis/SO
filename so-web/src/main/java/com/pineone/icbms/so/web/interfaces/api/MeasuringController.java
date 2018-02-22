package com.pineone.icbms.so.web.interfaces.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.web.model.measure.ReqMeasuring;
import com.pineone.icbms.so.web.model.measure.ResMeasuring;
import com.pineone.icbms.so.web.service.IMeasuringService;
import com.pineone.icbms.so.web.tracking.BeforeTtrackingHandler;

@Before(@BeforeElement(BeforeTtrackingHandler.class))
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/servicemodel")
public class MeasuringController {
	
    @Autowired
    IMeasuringService measuringService;
    
	@RequestMapping(value = "/measure",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResMeasuring getMeasuredData(@RequestBody ReqMeasuring req, HttpServletRequest request) {

		ResMeasuring res = new ResMeasuring();
        TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
        String sessionId = trackingEntity.getSessionId();
		res.setSessionId(sessionId);
		
		Object r = measuringService.getMeasuredData(req, request, trackingEntity);
		if (r==null) {
			String failMsg = "No service data";
			res.setResultMessage(failMsg);
			res.setResultCode(404); // No Information
		}else {
			res.setResultMessage(r);
			res.setResultCode(200); //SUCCESS
		}
		
    	return res;
    }
}
