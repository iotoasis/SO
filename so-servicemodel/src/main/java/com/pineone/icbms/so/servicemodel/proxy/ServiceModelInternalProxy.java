package com.pineone.icbms.so.servicemodel.proxy;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.pr.ServicePresentation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */

@org.springframework.stereotype.Service
public class ServiceModelInternalProxy implements ServiceModelProxy {

    @Autowired
    ServicePresentation servicePresentation;
//    ServicePresentation servicePresentation = new ServicePresentation();

    //NOTE: Service 컴포넌트를 이용해서 ServiceNameList 조회
    @Override
    public List<String> retrieveServiceNameList() {

        List<String> serviceNameList = servicePresentation.retrieveServiceNameList();
        return serviceNameList;
    }

    //NOTE: Service 모듈을 이용해서 Service 조회
    @Override
    public Service retrieveServiceDetail(String serviceId) {
        Service service = servicePresentation.retrieveServiceDetailController(serviceId);
        return service;
    }

    //NOTE: Service 컴포넌트를 이용해서 ServiceIdList 조회
    @Override
    public List<String> retrieveServiceIdList() {
        List<String> serviceIdList = servicePresentation.retrieveServiceIdList();
        return serviceIdList;
    }

    @Override
    public void executeService(String serviceId) {
        // 서비스 실행
        servicePresentation.executeService(serviceId);
    }

    @Override
    public void servicetestSetUp() {
        servicePresentation.testSetup();
    }
}
