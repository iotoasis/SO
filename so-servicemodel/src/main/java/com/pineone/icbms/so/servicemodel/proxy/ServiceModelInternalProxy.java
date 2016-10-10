package com.pineone.icbms.so.servicemodel.proxy;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.pr.ServicePresentation;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */

@org.springframework.stereotype.Service
public class ServiceModelInternalProxy implements ServiceModelProxy {

    public static final Logger logger = LoggerFactory.getLogger(ServiceModelInternalProxy.class);

    @Autowired
    ServicePresentation servicePresentation;
//    ServicePresentation servicePresentation = new ServicePresentation();

    //NOTE: Service 컴포넌트를 이용해서 ServiceNameList 조회
    @Override
    public List<String> retrieveServiceNameList() {
        logger.info(LogPrint.outputInfoLogPrint());
        List<String> serviceNameList = servicePresentation.retrieveServiceNameList();
        logger.debug("ServiceNameList = " + serviceNameList);
        return serviceNameList;
    }

    //NOTE: Service 모듈을 이용해서 Service 조회
    @Override
    public Service retrieveServiceDetail(String serviceId) {
        logger.info(LogPrint.outputInfoLogPrint() + "Service ID = " + serviceId);
        logger.debug("Retrieve Service ID = " + serviceId);
        Service service = servicePresentation.retrieveServiceDetailController(serviceId);
        logger.debug("Service = " + service.toString());
        return service;
    }

    //NOTE: Service 컴포넌트를 이용해서 ServiceIdList 조회
    @Override
    public List<String> retrieveServiceIdList() {
        logger.info(LogPrint.outputInfoLogPrint());
        List<String> serviceIdList = servicePresentation.retrieveServiceIdList();
        logger.debug("ServiceIDList = " + serviceIdList.toString());
        return serviceIdList;
    }

    @Override
    public void executeService(String serviceId, String sessionId) {
        // 서비스 실행
        logger.info(LogPrint.outputInfoLogPrint() + "Service ID = " + serviceId + "Session ID = " + sessionId);
        logger.debug("Execute Service ID = " + serviceId + "Session ID = " + sessionId);
        servicePresentation.executeService(servicePresentation.settingServiceId(serviceId, sessionId));
    }

}
