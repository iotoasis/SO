package com.pineone.icbms.so.web.service;

import javax.servlet.http.HttpServletRequest;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.web.model.measure.ReqMeasuring;

public interface IMeasuringService {

	Object getMeasuredData(ReqMeasuring req, HttpServletRequest request, TrackingEntity trackingEntity);

}
