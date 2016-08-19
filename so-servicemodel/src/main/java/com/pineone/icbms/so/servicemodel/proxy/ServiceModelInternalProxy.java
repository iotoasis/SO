package com.pineone.icbms.so.servicemodel.proxy;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.pr.ServicePresentation;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class ServiceModelInternalProxy extends AbstractServiceModelProxy {

    public static ServiceModelInternalProxy newServiceModelSDAProxy() {
        return new ServiceModelInternalProxy();
    }

    ServicePresentation servicePresentation = new ServicePresentation();

    //NOTE: Service 컴포넌트를 이용해서 ServiceNameList 조회
    @Override
    public List<String> retrieveServiceNameList() {

        List<String> serviceNameList = servicePresentation.retrieveServiceNameList();
        return serviceNameList;
    }

    //NOTE: Service 모듈을 이용해서 Service 조회
    @Override
    public Service retrieveServiceDetail(String serviceName) {
        Service service = servicePresentation.retrieveServiceDetailController(serviceName);
        return service;
    }
}
