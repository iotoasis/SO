package com.pineone.icbms.so.service.logic;


import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.ref.*;
import com.pineone.icbms.so.service.store.ServiceMapStore;
import com.pineone.icbms.so.service.store.ServiceStore;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by melvin on 2016. 8. 5..
 * NOTE : ServiceLogic 에 연관된 로직 및 서비스들을 수행하기 위한 객체
 */
public class ServiceLogicImpl implements ServiceLogic{

    public static ServiceLogicImpl newServiceLogicImpl(){
        return new ServiceLogicImpl();
    }

    //NOTE: Service 에 사용할 가상 장치를 고르기 위해 So의 VO-CVO 리스트 조회 (DeviceObject List) 조회
    @Override
    public List<DeviceObject> retrieveDeviceObjectList() {
        //
        return DeviceCenter.retrieveDeviceObjectList();
    }

    //NOTE: Service 에 사용할 ConceptService 를 선택하기 위해 가상 장치의 ConceptService List 조회
    @Override
    public List<ConceptService> retrieveConceptService(DeviceObject deviceObject) {
        //
        return DeviceCenter.newDeviceCenter().retrieveConceptServiceList(deviceObject);
    }

    //NOTE: ConceptService 의 Status 조회 TODO : ConceptService 에 따른 Status 분기 필요 - 정해지면 적용
    @Override
    public List<Status> retrieveStatusList(ConceptService conceptService) {
        //
        List<Status> statusList = new ArrayList<>();
        if(conceptService != null){
            for(Status status : Status.values()){
                statusList.add(status);
            }
            return statusList;
        }
        return statusList;
    }

    @Override
    //NOTE: Service 등록정보를 수신받고 TODO : SO DB에 저장
    public String registerService(Service service) {
        ServiceStore serviceStore = ServiceMapStore.getInstance();
        service.setId("SERVICE-" + service.getId());
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        String serviceMessageStr = responseMessage.serviceResultMessage(service);
        serviceStore.createService(service);
        return serviceMessageStr;
    }

    @Override
    public Service retrieveServiceDetail(String serviceName) {
        ServiceStore serviceStore = ServiceMapStore.getInstance();
        Service service = serviceStore.retrieveServiceDetail(serviceName);
        return service;
    }

    @Override
    public List<String> retrieveServiceNameList() {
        ServiceStore serviceStore = ServiceMapStore.getInstance();
        List<String> serviceNameList = new ArrayList<>();
        List<Service> serviceList = serviceStore.retrieveServiceList();
        for(Service service : serviceList){
            serviceNameList.add(service.getName());
        }
        return serviceNameList;
    }
}
