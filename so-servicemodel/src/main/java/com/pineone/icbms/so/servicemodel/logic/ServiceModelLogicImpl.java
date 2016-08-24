package com.pineone.icbms.so.servicemodel.logic;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.proxy.ServiceModelProxy;
import com.pineone.icbms.so.servicemodel.ref.ExecuteDummyClass;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.servicemodel.ref.ServiceMessage;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by melvin on 2016. 8. 9..
 */

@org.springframework.stereotype.Service
public class ServiceModelLogicImpl implements ServiceModelLogic {
    //
    public static final Queue SERVICEMODEL_QUEUE = new LinkedList<>();

    @Autowired
    ServiceModelProxy serviceModelProxy;

    @Autowired
    ServiceModelStore serviceModelStore;

//    ServiceModelProxy serviceModelProxy = ServiceModelInternalProxy.newServiceModelSDAProxy();

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
//        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();

        serviceModelStore.createServiceModel(serviceModel);
        String serviceModelResultMessage = responseMessage.serviceModelResultMessage(serviceModel);
        return serviceModelResultMessage;
    }

    @Override
    //NOTE: ServiceModel 상세 정보 조회 요청을 받고 디비에서 정보를 조회해서 반환
    public ServiceModel retrieveServiceModelDetail(String serviceModelName) {
        //
//        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();
        ServiceModel serviceModel = serviceModelStore.retrieveServiceModelDetail(serviceModelName);
        return serviceModel;
    }

    //NOTE: 즉시성 서비스 모델 실행
    @Override
    public void executeEmergencyServiceModel(List<String> domainIdList, String serviceModelId) {
        //
        ServiceModel serviceModel = serviceModelStore.retrieveServiceModelDetail(serviceModelId);
        List<String> serviceIdList = serviceModel.getServiceIdList();
        List<ServiceMessage> serviceMessageList = new ArrayList<>();
        for (String serviceId : serviceIdList) {
            Service service = serviceModelProxy.retrieveServiceDetail(serviceId);
            ServiceMessage serviceMessage = new ServiceMessage(domainIdList, service.getConceptServiceId(), service.getStatus());
            serviceMessageList.add(serviceMessage);
            new ExecuteDummyClass().controlService(serviceMessageList);
        }
    }

    @Override
    public List<String> retrieveServiceIdList() {
        List<String> serviceIdList = serviceModelProxy.retrieveServiceIdList();
        return serviceIdList;
    }

    @Override
    public List<String> retrieveServiceModelIdList() {
        List<String> serviceModelIdList = new ArrayList<>();
        List<ServiceModel> serviceModelList = serviceModelStore.retrieveServiceModelList();
        for (ServiceModel serviceModel : serviceModelList) {
            serviceModelIdList.add(serviceModel.getId());
        }
        return serviceModelIdList;
    }
}

//    //NOTE : 서비스 실행 로직
//    @Override
//    public void run() {
//        //
//        while (true) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (!(SERVICEMODEL_QUEUE.isEmpty())) {
//                ExecuteDummyClass executeDummyClass = new ExecuteDummyClass();
//                ServiceMessage serviceMessage = (ServiceMessage) SERVICEMODEL_QUEUE.poll();
////                ServiceModel serviceModel = this.retrieveServiceModelDetail(serviceMessage.getServiceModelName());
////                List<Domain> domainList = serviceMessage.getDomainList();
//                for (String serviceName : serviceModel.getServiceIdList()) {
//                    Service service = serviceModelProxy.retrieveServiceDetail(serviceName);
////                    for (Domain domain : domainList) {
////                        executeDummyClass.controlService(domain.getId(), service.getDeviceObjectId(), service.getStatus());
//                    }
//                }
//            }
//        }
//    }
//}

