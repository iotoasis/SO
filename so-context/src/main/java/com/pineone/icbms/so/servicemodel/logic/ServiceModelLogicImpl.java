package com.pineone.icbms.so.servicemodel.logic;

import com.pineone.icbms.so.servicemodel.proxy.ServiceModelProxy;
import com.pineone.icbms.so.servicemodel.proxy.ServiceModelSDAProxy;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class ServiceModelLogicImpl implements ServiceModelLogic{
    //
    ServiceModelProxy serviceModelProxy = ServiceModelSDAProxy.newServiceModelSDAProxy();
    public static ServiceModelLogic newServiceModelLogic() {
        return new ServiceModelLogicImpl();
    }

    //NOTE : 서비스 컴포넌트에서 제공하는 ServiceNameList 를 조회하기 위해 , ServiceComponent 를 사용하는 Proxy 사용
    @Override
    public List<String> retrieveServiceNameList() {
        List<String> serviceNameList = serviceModelProxy.retrieveServiceNameList();
        return serviceNameList;
    }
}
