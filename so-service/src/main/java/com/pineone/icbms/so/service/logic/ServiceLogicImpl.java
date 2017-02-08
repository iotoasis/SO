package com.pineone.icbms.so.service.logic;


import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.proxy.DataServiceObject;
import com.pineone.icbms.so.service.proxy.ServiceProxy;
import com.pineone.icbms.so.service.proxy.ServiceSDAProxy;
import com.pineone.icbms.so.service.ref.ConceptService;
import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.ResponseMessage;
import com.pineone.icbms.so.service.ref.Status;
import com.pineone.icbms.so.service.store.ServiceControlRecordStore;
import com.pineone.icbms.so.service.store.ServiceStore;
import com.pineone.icbms.so.util.TimeStamp;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.exception.BadRequestException;
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

    @Autowired
    ServiceSDAProxy serviceSDAProxy;

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
    //NOTE: Service 등록정보를 수신받고 SO DB에 저장
    public String registerService(Service service) {
        logger.debug("Service = " + service.toString());
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        String serviceMessageStr = responseMessage.serviceResultMessage(service);
        serviceStore.createService(service);
        return serviceMessageStr;
    }

    @Override
    public Service retrieveServiceDetail(String serviceId) {
        logger.debug("Service ID = " + serviceId);
        return serviceStore.retrieveServiceDetail(serviceId);
    }

    @Override
    public List<String> retrieveServiceNameList() {
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

        // DB에서 Session을 검색
        Session session = null;
        String localSessionId = sessionId;
        if(localSessionId != null){
            session = sessionStore.retrieveSessionDetail(localSessionId);
        }

        if(session == null){
            session = new DefaultSession();
            localSessionId =  session.getId();
        }

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
         *    - Priority가 없으면 외부 요청으로 간주하여 우선순위가 HIGH.
         *   2. Period
         *   3. ContextLocation
         */
        if(!session.isExistSessionData(DefaultSession.PRIORITY_KEY) || Priority.HIGH.equals(session.getSessionData().get(DefaultSession.PRIORITY_KEY)) ||
                serviceData.checkActivedPeriod(currentTime)){
            logger.info("Execute Service Start" + TimeStamp.currentTime());

            String operation = serviceData.getStatus();
            if("si-lack-equipment-noti".equals(serviceId)){
                try {
                    operation = serviceSDAProxy.getPCCountUri(session);
                    logger.info("Equipment Data = " + operation);
                    sessionDataUpdate(sessionStore,session,operation,DefaultSession.ADMIN_NOTI_DATA);
                } catch (BadRequestException e) {
                    logger.warn("operation is not Count");
                    session = sessionStore.retrieveSessionDetail(localSessionId);
                    session.insertSessionData(DefaultSession.SERVICE_RESULT, DefaultSession.CONTROL_ERROR + " operation is null");
                }
            } else if("si-domitory-optimal-environment".equals(serviceId)){
                try {
                    operation = serviceSDAProxy.getTemperatureLookup(session);
                    logger.info("Environment Data = " + operation);
                } catch (BadRequestException e) {
                    logger.warn("operation is not Count");
                    session = sessionStore.retrieveSessionDetail(localSessionId);
                    session.insertSessionData(DefaultSession.SERVICE_RESULT, DefaultSession.CONTROL_ERROR + " operation is null");
                }
            }

            for(String virtualObjectId : serviceData.getVirtualObjectIdList()){
                if(operation.equals(ClientProfile.TEMP_COLD)){
                    // Heater
                    if(virtualObjectId.contains(ClientProfile.SI_DEVICE_HEATER)){
                        serviceProxy.executeVirtualObject(virtualObjectId, ClientProfile.SI_DEVICE_OPERTAION, localSessionId);
                    }
                } else if(operation.equals(ClientProfile.TEMP_HOT)){
                    // Airconditioner
                    if(virtualObjectId.contains(ClientProfile.SI_DEVICE_AIRCONDITONER)){
                        serviceProxy.executeVirtualObject(virtualObjectId, ClientProfile.SI_DEVICE_OPERTAION, localSessionId);
                    }
                } else {
                    serviceProxy.executeVirtualObject(virtualObjectId, operation, localSessionId);
                }
            }
            serviceData.setModifiedTime(currentTime);
            serviceStore.updateService(serviceData);
            session = sessionStore.retrieveSessionDetail(localSessionId);
            session.insertSessionData(DefaultSession.SERVICE_RESULT, DefaultSession.CONTROL_EXECUTION);
        } else {
            logger.info("Execute Service Ignore : " + "ServiceActiveTime = " + TimeStamp.currentTime(serviceData.getModifiedTime()) + " FilterTime = " + serviceData.getFilterTime()/1000);
            session = sessionStore.retrieveSessionDetail(localSessionId);
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

    @Override
    public String requestDataService(DataServiceObject dataServiceObject) {
        String response = "";
        try{
            response =  serviceSDAProxy.getDataService(dataServiceObject);
        }catch (BadRequestException e){
            response = e.toString();
        }
        return response;
    }

    //NOTE: Service 이름으로 Id 조회 기능
    @Override
    public Service retrieveServiceDetailByName(String serviceName) {
        logger.debug("serviceName = " + serviceName);
        Service service = serviceStore.retrieveServiceDetailByName(serviceName);
        return service;
    }


    private void sessionDataUpdate(SessionStore sessionStore, Session session, String data, String key){
        session.insertSessionData(key, data);
        sessionStore.updateSession(session);
    }

}
