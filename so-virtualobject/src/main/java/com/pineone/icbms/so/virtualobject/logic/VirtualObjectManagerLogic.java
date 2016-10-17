package com.pineone.icbms.so.virtualobject.logic;

import com.pineone.icbms.so.device.logic.DeviceManager;
import com.pineone.icbms.so.device.pr.DevicePresentation;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.proxy.VirtualObjectControlProxy;
import com.pineone.icbms.so.virtualobject.proxy.VirtualObjectProxy;
import com.pineone.icbms.so.virtualobject.store.VirtualObjectStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VirtualObjectManagerLogic implements VirtualObjectManager {

    public static final Logger logger = LoggerFactory.getLogger(VirtualObjectManagerLogic.class);

    @Autowired
    DeviceManager deviceManager;

    @Autowired
    DevicePresentation devicePresentation;

    @Autowired
    VirtualObjectStore virtualObjectStore;

    @Autowired
    SessionStore sessionStore;

    @Autowired
    VirtualObjectProxy virtualObjectProxy;

    @Autowired
    VirtualObjectControlProxy virtualObjectControlProxy;

    @Override
    public VirtualObject searchVirtualObject(String id) {
        logger.debug("VirtualObject ID = " + id);
        if(id == null || id.isEmpty()){
            logger.debug("VirtualObject id is null");
            return null;
        }
        VirtualObject virtualObject = virtualObjectStore.retrieveByID(id);
        if(virtualObject == null){
            logger.debug("VirtualObject data is null");
            return null;
        }
        logger.debug("VirtualObject = " + virtualObject.toString());
        return virtualObject;
    }

    @Override
    public void deleteVirtualObject(String id) {
        logger.debug("VirtualObject ID = " + id);
        virtualObjectStore.delete(id);
    }

    @Override
    public List<VirtualObject> searchVirtualObjectList(String location) {
        logger.debug("Location = " + location);
        List<VirtualObject> virtualObjectList = virtualObjectStore.retrieveByLocation(location);
        for(VirtualObject virtualObject : virtualObjectList){
            logger.debug("VirtualObject = " + virtualObject.toString());
        }
        return virtualObjectList;
    }

    @Override
    public List<VirtualObject> searchVirtualObjectList() {
        List<VirtualObject> virtualObjectList = virtualObjectStore.retrieveVirtualObjectList();
        for(VirtualObject virtualObject : virtualObjectList){
            logger.debug("VirtualObject = " + virtualObject.toString());
        }
        return virtualObjectList;
    }

    @Override
    public String requestControlDevice(String voId, String operation, String sessionId) {
        // DB에서 VO를 검색
        logger.debug("VirtualObject Id = " + voId + " Operation = " + operation + " Session ID = " + sessionId);

        // DB에서 Session을 검색
        Session session = null;
        if(sessionId != null){
            session = sessionStore.retrieveSessionDetail(sessionId);
        }

        if(session == null){
            session = new DefaultSession();
        }

        List<String> virtulaobjectIdList = null;
        if(session.isExistSessionData(DefaultSession.VIRTUALOBJECT_KEY)){
            virtulaobjectIdList = DataConversion.stringDataToList(session.findSessionData(DefaultSession.VIRTUALOBJECT_KEY));
        }
        if(virtulaobjectIdList == null){
            virtulaobjectIdList = new ArrayList<>();
        }
        virtulaobjectIdList.add(voId);
        session.insertSessionData(DefaultSession.VIRTUALOBJECT_KEY, DataConversion.listDataToString(virtulaobjectIdList));
        sessionStore.updateSession(session);
        VirtualObject virtualObject = searchVirtualObject(voId);
        if(virtualObject == null){
            session.insertSessionData(DefaultSession.VIRTUALOBJECT_RESULT, DefaultSession.CONTROL_ERROR);
            // DB에 Session을 저장.
            sessionStore.updateSession(session);
            return DefaultSession.CONTROL_ERROR;
        }

        // 해당 Device ID를 도출
        String deviceId = virtualObject.getDeviceId();
        // operation -> command SDA 확인.고려.. 형식으로 변경.
        //        return deviceManager.deviceExecute(deviceId, operation);
        logger.debug("Device ID = " + deviceId + " Operation = " + operation + " Session ID = " + sessionId);
        session.insertSessionData(DefaultSession.VIRTUALOBJECT_RESULT, DefaultSession.CONTROL_EXECUTION);
        sessionStore.updateSession(session);
        return virtualObjectControlProxy.executeDevice(deviceId, operation, sessionId);
    }

    @Override
    public void produceVirtualObject(VirtualObject virtualObject) {

        // VirtualObject의 Functionality 요청
        //String responseData = requestFunctionality(virtualObject);
        // TODO : SDA 협의 후 해당 부분 수정.
        // VirtualObject의 Functionality 추가 설정
        //virtualObject.setFunctionality(responseData);

        // VirtualObject 저장
        logger.debug("VirtualObject = " + virtualObject.toString());
        saveVirtualDevice(virtualObject);

    }

/*
    @Override
    public String controlDevice(List<ServiceControl> serviceControls) {
        // DB에서 domain과 VOService로 해당 VO들 조회.
        logger.debug("ServiceControl = " +  serviceControls.toString());
        for(ServiceControl control : serviceControls){
            List<VirtualObject> virtualObjects = virtualObjectStore.retrieveByLocationAndService(control.getDomain(),control.getFunctionality());
            // DB에서 조회된 VO 실행.
            for(VirtualObject vo : virtualObjects){
                logger.debug("VirtualObject ID = " + vo.getVoId() + " Device ID = " + vo.getDeviceId() + " Operation = " + vo.getVoCommand());
                deviceManager.deviceExecute(vo.getDeviceId(),control.getOperation());
            }
        }

        //VO 제어
        return null;
    }
*/

    private void saveVirtualDevice(VirtualObject virtualObject){
        virtualObjectStore.create(virtualObject);
    }

    private String requestFunctionality(VirtualObject virtualObject){
        String responseData = virtualObjectProxy.findFunctionality(virtualObject.getDeviceId(),virtualObject.getDeviceService());

        // 컨트롤 프록시
        // 정보 수접 프록시.
        return responseData;
    }
}
