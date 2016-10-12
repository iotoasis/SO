package com.pineone.icbms.so.service.logic;


import com.pineone.icbms.so.compositevo.ref.CompositeProfile;
import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.proxy.ServiceProxy;
import com.pineone.icbms.so.service.ref.ConceptService;
import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.ResponseMessage;
import com.pineone.icbms.so.service.ref.Status;
import com.pineone.icbms.so.service.store.ServiceControlRecordStore;
import com.pineone.icbms.so.service.store.ServiceStore;
import com.pineone.icbms.so.util.TimeStamp;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.priority.Priority;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by melvin on 2016. 8. 5..
 * NOTE : ServiceLogic 에 연관된 로직 및 서비스들을 수행하기 위한 객체
 */

@org.springframework.stereotype.Service
public class ServiceLogicImpl implements ServiceLogic{

    public static final Logger logger = LoggerFactory.getLogger(ServiceLogicImpl.class);

    @Autowired
    ServiceStore serviceStore;

    @Autowired
    SessionStore sessionStore;

    @Autowired
    ServiceControlRecordStore serviceControlRecordStore;

    @Autowired
    ServiceProxy serviceProxy;

    public static ServiceLogicImpl newServiceLogicImpl(){
        return new ServiceLogicImpl();
    }

    //NOTE: Service 에 사용할 가상 장치를 고르기 위해 So의 VO-CVO 리스트 조회 (DeviceObject List) 조회
    @Override
    public List<DeviceObject> retrieveDeviceObjectList() {
        //
//        return deviceCenter.retrieveDeviceObjectList();
        return null;
    }


    //NOTE: Service 에 사용할 ConceptService 를 선택하기 위해 가상 장치의 ConceptService List 조회
    @Override
    public List<ConceptService> retrieveConceptService(DeviceObject deviceObject) {
        // serviceProxy에서 VO로 DeviceConceptService 조회 하기
//        return deviceCenter.retrieveConceptServiceList(deviceObject);
        return null;
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
//        ServiceStore serviceStore = ServiceMapStore.getInstance();
//        service.setId("SERVICE-" + service.getId());
        logger.debug("Service = " + service.toString());
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        String serviceMessageStr = responseMessage.serviceResultMessage(service);
        serviceStore.createService(service);
        return serviceMessageStr;
    }

    @Override
    public Service retrieveServiceDetail(String serviceId) {
//        ServiceStore serviceStore = ServiceMapStore.getInstance();
        logger.debug("Service ID = " + serviceId);
        Service service = serviceStore.retrieveServiceDetail(serviceId);
        return service;
    }

    @Override
    public List<String> retrieveServiceNameList() {
//        ServiceStore serviceStore = ServiceMapStore.getInstance();
        List<String> serviceNameList = new ArrayList<>();
        List<Service> serviceList = serviceStore.retrieveServiceList();
        for(Service service : serviceList){
            serviceNameList.add(service.getName());
        }
        logger.debug("ServiceNameList = " + serviceNameList.toString());
        return serviceNameList;
    }

    @Override
    public List<String> retrieveServiceIdList() {
        List<String> serviceIdList = new ArrayList<>();
        List<Service> serviceList = serviceStore.retrieveServiceList();
        for(Service service : serviceList){
            serviceIdList.add(service.getId());
        }
        logger.debug("ServiceIDList = " + serviceIdList.toString());
        return serviceIdList;
    }

    @Override
    public void executeService(String serviceId, String sessionId) {
        logger.debug("Execute Service ID = " + serviceId + " Session ID = " + sessionId);

        // Service Filter 추가.

        // Service Control Record 검색
        // serviceControlRecord가 없으면 ServiceControlRecord 생성
        //

        // DB에서 Session을 검색
        Session session = null;
        if(sessionId != null){
            session = sessionStore.retrieveSessionDetail(sessionId);
        }

        if(session == null){
            session = new DefaultSession();
        }

        // TODO : Session에서 serviceIdList를 얻는다.
        List<String> sessionServiceIdList = null;
        if(session.isExistSessionData(DefaultSession.SERVICE_KEY)){
            sessionServiceIdList = DataConversion.stringDataToList(session.findSessionData(DefaultSession.SERVICE_KEY));
        }
        if(sessionServiceIdList == null){
            sessionServiceIdList = new ArrayList<>();
        }
        sessionServiceIdList.add(serviceId);
        session.insertSessionData(DefaultSession.SERVICE_KEY, DataConversion.listDataToString(sessionServiceIdList));

        long currentTime = System.currentTimeMillis();

        Service serviceData = serviceStore.retrieveServiceDetail(serviceId);
        if(serviceData == null){
            session.insertSessionData(DefaultSession.SERVICE_RESULT, DefaultSession.CONTROL_ERROR);
            // DB에 Session을 저장.
            sessionStore.updateSession(session);
            return;
        }
        sessionStore.updateSession(session);

        /**
         * Service Filter
         * - 우선 순위,
         *   1. Priority
         *    - Priority가 없으면 외부 요청으로 간주하여 우선순위가 우선.
         *   2. Period
         *   3. ContextLocation
         */
        if(!session.isExistSessionData(DefaultSession.PRIORITY_KEY) || Priority.HIGH.equals(session.getSessionData().get(DefaultSession.PRIORITY_KEY)) ||
                serviceData.checkActivedPeriod(currentTime)){
            logger.info("Execute Service Start" + TimeStamp.currentTime());
            for(String virtualObjectId : serviceData.getVirtualObjectIdList()){
                if(virtualObjectId.startsWith(CompositeProfile.COMPOSITE_ID)) {
                    serviceProxy.executeCompositeVirtualObject(virtualObjectId, serviceData.getVirtualObjectService(), serviceData.getStatus(), sessionId);
                } else {
                    serviceProxy.executeVirtualObject(virtualObjectId, serviceData.getStatus(), sessionId);
                }
                serviceData.setModifiedTime(currentTime);
            }
            serviceStore.updateService(serviceData);
            session = sessionStore.retrieveSessionDetail(sessionId);
            session.insertSessionData(DefaultSession.SERVICE_RESULT, DefaultSession.CONTROL_EXECUTION);
        } else {
            logger.info("Execute Service Ignore : " + "ServiceActiveTime = " + TimeStamp.currentTime(serviceData.getModifiedTime()) + " FilterTime = " + serviceData.getFilterTime()/1000);
            session = sessionStore.retrieveSessionDetail(sessionId);
            session.insertSessionData(DefaultSession.SERVICE_RESULT, DefaultSession.CONTROL_IGNORE);
        }

        // DB에 Session을 저장.
        sessionStore.updateSession(session);



    }

    @Override
    public List<Service> retrieveServiceList() {
        List<Service> services = serviceStore.retrieveServiceList();
        logger.debug("Service List = " + services.toString());
        return services;
    }

}
