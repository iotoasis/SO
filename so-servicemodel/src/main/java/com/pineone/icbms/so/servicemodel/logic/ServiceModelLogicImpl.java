package com.pineone.icbms.so.servicemodel.logic;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.proxy.ServiceModelInternalProxy;
import com.pineone.icbms.so.servicemodel.proxy.ServiceModelProxy;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.servicemodel.store.ServiceModelMapStore;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class ServiceModelLogicImpl implements ServiceModelLogic{
    //
    ServiceModelProxy serviceModelProxy = ServiceModelInternalProxy.newServiceModelSDAProxy();
    public static ServiceModelLogic newServiceModelLogic() {
        return new ServiceModelLogicImpl();
    }

    //NOTE : 서비스 컴포넌트에서 제공하는 ServiceNameList 를 조회하기 위해 , ServiceComponent 를 사용하는 Proxy 사용
    @Override
    public List<String> retrieveServiceNameList() {
        List<String> serviceNameList = serviceModelProxy.retrieveServiceNameList();
        return serviceNameList;
    }

    //NOTE: ServiceModel 등록정보를 수신받고 SO DB에 저장하고 보여줘야할 내용(결정 필요)을 리턴
    @Override
    public String registerServiceModel(ServiceModel serviceModel) {
      //
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();

        serviceModelStore.createServiceModel(serviceModel);
        String serviceModelResultMessage = responseMessage.serviceModelResultMessage(serviceModel);
        return serviceModelResultMessage;
    }

    @Override
    //NOTE: ServiceModel 상세 정보 조회 요청을 받고 디비에서 정보를 조회해서 반환
    public ServiceModel retrieveServiceModelDetail(String serviceModelName){
        //
        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();
        ServiceModel serviceModel = serviceModelStore.retrieveServiceModelDetail(serviceModelName);
        return serviceModel;
    }
}

