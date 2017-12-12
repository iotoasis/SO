package com.pineone.icbms.so.web.service;

import javax.servlet.http.HttpServletRequest;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.web.model.smarthelper.ReqSmartHelper;

public interface ISmartHelperService {

	String callOsService(ReqSmartHelper req, HttpServletRequest request, TrackingEntity trackingEntity);

}
