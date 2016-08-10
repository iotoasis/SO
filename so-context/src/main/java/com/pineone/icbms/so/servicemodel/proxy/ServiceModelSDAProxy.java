package com.pineone.icbms.so.servicemodel.proxy;

import com.pineone.icbms.so.service.pr.ServicePresentation;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class ServiceModelSDAProxy implements ServiceModelProxy{
    //
    public static ServiceModelProxy newServiceModelSDAProxy() {
        return new ServiceModelSDAProxy();
    }

    //NOTE: Service 컴포넌트를 이용해서 ServiceNameList 조회
    @Override
    public List<String> retrieveServiceNameList() {
        ServicePresentation servicePresentation = new ServicePresentation();
        List<String> serviceNameList = servicePresentation.retrieveServiceNameList();
        return serviceNameList;
    }




}