package com.pineone.icbms.so.servicemodel.logic;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.proxy.ServiceModelProxy;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(ServiceModelLogicImpl.class);

    @Autowired
    ServiceModelProxy serviceModelProxy;

    @Autowired
    ServiceModelStore serviceModelStore;

    @Autowired
    SessionStore sessionStore;

//    ServiceModelProxy serviceModelProxy = ServiceModelInternalProxy.newServiceModelSDAProxy();

    public static ServiceModelLogic newServiceModelLogic() {
        return new ServiceModelLogicImpl();
    }

    //NOTE : 서비스 컴포넌트에서 제공하는 ServiceNameList 를 조회하기 위해 , ServiceComponent 를 사용하는 Proxy 사용
    @Override
    public List<String> retrieveServiceNameList() {
        List<String> serviceNameList = serviceModelProxy.retrieveServiceNameList();
        logger.debug("ServiceNameList = " + serviceNameList.toString());
        return serviceNameList;
    }

    //NOTE: ServiceModel 등록정보를 수신받고 SO DB에 저장하고 보여줘야할 내용(결정 필요)을 리턴
    @Override
    public String registerServiceModel(ServiceModel serviceModel) {
        //
        if(serviceModel == null){
            logger.warn("You can not register a service model. serviceModel is Null");
            return null;
        }
        logger.debug("ServiceModel = " + serviceModel);
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
        if(serviceModelId == null){
            logger.warn("You can not view the service model. serviceModelID is Null");
            return null;
        }
        logger.debug("ServiceModel ID = " + serviceModelId);
//        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();
        ServiceModel serviceModel = serviceModelStore.retrieveServiceModelDetail(serviceModelId);
        if(serviceModel == null){
            logger.warn("You can not view the service model. serviceModelID is Null");
            return null;
        }
        logger.debug("ServiceModel = " + serviceModel);
        return serviceModel;
    }

    //NOTE: 즉시성 서비스 모델 실행
    @Override
    public void executeServiceModel(String serviceModelId, String sessionId) {
        //
        logger.debug("Execute ServiceModel ID = " + serviceModelId + " Sesseion ID = " + sessionId);

        // DB에서 Session을 검색
        Session session = null;
        if(sessionId != null){
            session = sessionStore.retrieveSessionDetail(sessionId);
        }

        // Session이 없으면 Session을 생성. 외부에서 ServiceModel을 제어서 Session이 없을수 있음.
        if(session == null){
            session = new DefaultSession();
        }

        session.insertSessionData(DefaultSession.SERVICEMODEL_KEY,serviceModelId);

        ServiceModel serviceModel = serviceModelStore.retrieveServiceModelDetail(serviceModelId);

        if(serviceModel == null){
            session.insertSessionData(DefaultSession.SERVICEMODEL_RESULT, DefaultSession.CONTROL_ERROR);
            logger.warn("You can not run a service model. serviceModel is Null");
            // DB에 Session을 저장.
            sessionStore.updateSession(session);
            return;
        }

        //ServiceModel Filter
        // 서비스 발생 상황과 서비스 모델의 발생 상황이 다르면 무시 한다.
        if(!locationCompare(session, serviceModel.getLocaton())){
            logger.info("Where the difference occurs.");
            session.insertSessionData(DefaultSession.SERVICEMODEL_RESULT,DefaultSession.CONTROL_IGNORE);
            sessionStore.updateSession(session);
            return;
        }

        // ServiceModel의 Session 결과를 저장
        session.insertSessionData(DefaultSession.SERVICEMODEL_RESULT,DefaultSession.CONTROL_EXECUTION);

        // DB에 Session을 저장.
        sessionStore.updateSession(session);

        logger.debug("Execute ServiceModel = " + serviceModel.toString());
        List<String> serviceIdList = serviceModel.getServiceIdList();
//        List<ServiceMessage> serviceMessageList = new ArrayList<>();
        for (String serviceId : serviceIdList) {
//            Service service = serviceModelProxy.retrieveServiceDetail(serviceId);
//            ServiceMessage serviceMessage = new ServiceMessage(domainId, service.getVirtualObjectService(), service.getStatus());
//            serviceMessageList.add(serviceMessage);
            logger.debug("Execute Service ID = " + serviceId + " Session Id = " + sessionId);
            serviceModelProxy.executeService(serviceId, sessionId);
        }
    }

    @Override
    public List<String> retrieveServiceIdList() {
        List<String> serviceIdList = serviceModelProxy.retrieveServiceIdList();
        if(serviceIdList == null){
            logger.warn("You can not view the serviceIDList. serviceIDList is Null");
            return null;
        }
        logger.debug("ServiceIDList = " + serviceIdList.toString());
        return serviceIdList;
    }

    @Override
    public List<String> retrieveServiceModelIdList() {
        List<String> serviceModelIdList = new ArrayList<>();
        List<ServiceModel> serviceModelList = serviceModelStore.retrieveServiceModelList();
        for (ServiceModel serviceModel : serviceModelList) {
            serviceModelIdList.add(serviceModel.getId());
        }
        logger.debug("ServiceModelIDList = " + serviceModelIdList);
        return serviceModelIdList;
    }

    @Override
    public List<ServiceModel> retrieveServiceModelList() {
        List<ServiceModel> serviceModelList = serviceModelStore.retrieveServiceModelList();
        if(serviceModelList == null){
            logger.warn("You can not view the serviceModelList. serviceModelList is Null");
            return null;
        }
        for(ServiceModel serviceModel : serviceModelList){
            logger.debug("ServiceModel = " + serviceModel.toString());
        }
        return serviceModelList;
    }

    private boolean locationCompare(Session session, String serviceModelLocation){
        String contextLocation = session.getSessionData().get(DefaultSession.LOCATION_ID);
        if(contextLocation != null && contextLocation.equals(serviceModelLocation)){
            return true;
        } else {
            return false;
        }
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

