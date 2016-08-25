package com.pineone.icbms.so.servicemodel.logic;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.proxy.ServiceModelProxy;
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
    public ServiceModel retrieveServiceModelDetail(String serviceModelId) {
        //
//        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();
        ServiceModel serviceModel = serviceModelStore.retrieveServiceModelDetail(serviceModelId);
        return serviceModel;
    }

    //NOTE: 즉시성 서비스 모델 실행
    @Override
    public void executeServiceModel(String serviceModelId) {
        //
        ServiceModel serviceModel = serviceModelStore.retrieveServiceModelDetail(serviceModelId);
        List<String> serviceIdList = serviceModel.getServiceIdList();
        List<ServiceMessage> serviceMessageList = new ArrayList<>();
        for (String serviceId : serviceIdList) {
//            Service service = serviceModelProxy.retrieveServiceDetail(serviceId);
//            ServiceMessage serviceMessage = new ServiceMessage(domainId, service.getVirtualObjectService(), service.getStatus());
//            serviceMessageList.add(serviceMessage);
            serviceModelProxy.executeService(serviceId);
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

    @Override
    public void testSetUp() {
        setupData();
        serviceModelProxy.servicetestSetUp();
    }

    @Override
    public List<ServiceModel> retrieveServiceModelList() {
        return serviceModelStore.retrieveServiceModelList();
    }

    private void setupData(){
        List<String> serviceList = new ArrayList<>();
        serviceList.add("AIRCLEANER-POWER-CONTROL-SERVICE-001");

        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setId("CLASSROOM-AIRIDEAL-SERVICE");
        serviceModel.setName("강의실 쾌적 공기제공 서비스");
        serviceModel.setServiceIdList(serviceList);
        serviceModel.setCreateTime("201608250930");
        serviceModel.setModifiedTime("201608250930");

        registerServiceModel(serviceModel);

        List<String> serviceList1 = new ArrayList<>();
        serviceList1.add("SMARTLIGHT-POWER-CONTROL-SERVICE-001");
        serviceList1.add("BLIND-POWER-CONTROL-SERVICE-001");
        serviceList1.add("BEAMPROJECTOR-POWER-CONTROL-SERVICE-001");
        serviceList1.add("BEAMSCREEN-POWER-CONTROL-SERVICE-001");
        registerServiceModel(new ServiceModel("CLASSROOM-PRESENTATIONMODE-SERVICE","강의실 발표 도우미 서비스",serviceList1,"201608250930","201608250930"));
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
////                        executeDummyClass.controlService(domain.getId(), service.getVirtualObjectId(), service.getStatus());
//                    }
//                }
//            }
//        }
//    }
//}

