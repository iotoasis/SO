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
import com.pineone.icbms.so.web.model.smarthelper.ReqSmartHelper;
import com.pineone.icbms.so.web.model.smarthelper.ResSmartHelper;
import com.pineone.icbms.so.web.service.ISmartHelperService;
import com.pineone.icbms.so.web.tracking.BeforeTtrackingHandler;

@Before(@BeforeElement(BeforeTtrackingHandler.class))
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/servicemodel")
public class SmartHelperController {
	
    @Autowired
    ISmartHelperService smartHelperService;
    
	@RequestMapping(value = "/smarthelper",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResSmartHelper sendHelperMessage(@RequestBody ReqSmartHelper req, HttpServletRequest request) {

		ResSmartHelper res = new ResSmartHelper();
        TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
        String sessionId = trackingEntity.getSessionId();
		res.setSessionId(sessionId);
		
		String r = smartHelperService.callOsService(req, request, trackingEntity);
		if (r==null) {
			String failMsg = "사용자 요청이 정상적으로 수행되지 않았습니다.";
			res.setResultMessage(failMsg);
			res.setResultCode(404); // No Information
		}else {
			res.setResultMessage(r);
			res.setResultCode(200); //SUCCESS
		}
		
    	return res;
    }
}
