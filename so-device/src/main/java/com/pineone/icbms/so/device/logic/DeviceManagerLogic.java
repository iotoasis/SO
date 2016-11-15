package com.pineone.icbms.so.device.logic;

import com.pineone.icbms.so.device.entity.*;
import com.pineone.icbms.so.device.proxy.DeviceControlProxy;
import com.pineone.icbms.so.device.proxy.DeviceICollectionProxy;
import com.pineone.icbms.so.device.store.DeviceResultStore;
import com.pineone.icbms.so.device.store.DeviceStore;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceManagerLogic implements DeviceManager {

    public static final Logger logger = LoggerFactory.getLogger(DeviceManagerLogic.class);

    public static final String DEVICE_SERVICE_NOTI_TYPE = "admin-noti";

    @Autowired
    private DeviceStore deviceStore;

    @Autowired
    private DeviceResultStore deviceResultStore;

    @Autowired
    private SessionStore sessionStore;

    @Autowired
    private DeviceICollectionProxy deviceICollectionProxy;

    @Autowired
    private DeviceControlProxy deviceControlProxy;

    @Override
    public void deviceRegister(deviceReleaseMessage deviceReleaseMessage){
        // NOTE : 디바이스 생성.
        logger.debug("DeviceReleaseMessage = " + deviceReleaseMessage.toString());
        // 디바이스 ID로 SDA에 데이터 요청.
        Device device = deviceRequest(ClientProfile.SDA_DATAREQUEST_URI + ClientProfile.SDA_DEVICE + deviceReleaseMessage.getDeviceId());
        logger.debug("Device = " + device.toString());
        // 디바이스 저장.
        deviceCreate(device);
    }

    @Override
    public void deviceRelease(String deviceId){
        logger.debug("Device ID = " + deviceId);
        deviceStore.delete(deviceId);
    }

    @Override
    public String deviceExecute(String deviceId,String deviceCommand, String sessionId){

        logger.debug("DeviceID = " + deviceId + " DeviceCommand = " + deviceCommand + " Session Id = " + sessionId);

        // DB에서 Session을 검색
        Session session = null;
        if(sessionId != null){
            session = sessionStore.retrieveSessionDetail(sessionId);
        }

        if(session == null){
            session = new DefaultSession();
        }

        List<String> sessionDeviceIdList = null;
        if(session.isExistSessionData(DefaultSession.DEVICE_KEY)){
            sessionDeviceIdList = DataConversion.stringDataToList(session.findSessionData(DefaultSession.DEVICE_KEY));
        }
        if(sessionDeviceIdList == null){
            sessionDeviceIdList = new ArrayList<>();
        }
        sessionDeviceIdList.add(deviceId);
        session.insertSessionData(DefaultSession.DEVICE_KEY, DataConversion.listDataToString(sessionDeviceIdList));

        sessionStore.updateSession(session);

        String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();
        Device device = deviceSearchById(deviceId);
        if(device == null){
            session.insertSessionData(DefaultSession.DEVICE_RESULT, DefaultSession.CONTROL_ERROR);
            // DB에 Session을 저장.
            sessionStore.updateSession(session);
            logger.debug("The device does not exist.");
            return "The device does not exist.";
        } else if(device.getDeviceServices() != null && DEVICE_SERVICE_NOTI_TYPE.equals(device.getDeviceServices())){
            sessionDataUpdate(sessionStore, session, device.getDeviceLocation(),DefaultSession.DEVICE_LOCATION);
            session.insertSessionData(DefaultSession.DEVICE_RESULT, DefaultSession.CONTROL_EXECUTION + "_" + DEVICE_SERVICE_NOTI_TYPE);
            // DB에 Session을 저장.
            sessionStore.updateSession(session);
            return DEVICE_SERVICE_NOTI_TYPE;
        }

        // SI를 제어할수 있는 DeviceControlMessage로 변환
        DeviceControlMessage deviceControlMessage = deviceDataConversion(deviceId,commandId,deviceCommand);
        logger.debug("DeviceControlMessage = " + deviceControlMessage.toString());

        session = sessionStore.retrieveSessionDetail(sessionId);
        sessionDataUpdate(sessionStore, session, device.getDeviceLocation(),DefaultSession.DEVICE_LOCATION);
        session.insertSessionData(DefaultSession.DEVICE_RESULT, DefaultSession.CONTROL_EXECUTION);
        sessionStore.updateSession(session);
        // Device 제어 요청 보냄.
        ResultMessage resultMessage = deviceControlProxy.deviceControlRequest(ClientProfile.SI_CONTOL_URI,deviceControlMessage);

        // Device 제어 결과 저장.
        controlResultsStorage(deviceId, commandId, deviceCommand, resultMessage);

        return resultMessage.getCode();
    }

    @Override
    public String deviceControlResult(ResultMessage resultMessage) {
        logger.debug("ResultMessage = " + resultMessage.toString());
        DeviceResult deviceResult = deviceResultStore.retrieve(resultMessage.get_commandId());

        if(deviceResult != null && deviceResult.getCommandId() != null){
            // It has been confirmed for the linked data.
            if(ClientProfile.RESPONSE_SUCCESS_CODE.equals(resultMessage.getCode()) ||
                    ClientProfile.RESPONSE_SUCCESS.equals(resultMessage.getCode()) ||
                    ClientProfile.RESPONSE_SUCCESS_ONEM2MCODE.equals(resultMessage.getCode())){

                deviceResult.setResult2(resultMessage.getCode());
                deviceResultStore.update(deviceResult);
            }
            return resultMessage.getCode();
        } else {
            return "No device Control Message.";
        }
    }

    @Override
    public Device deviceSearchById(String deviceId) {
        logger.debug("Device ID = " + deviceId);
        return deviceStore.retrieveByID(deviceId);
    }

    @Override
    public List<Device> deviceSearchByLocation(String location) {
        logger.debug("Location = " + location);
        return deviceStore.retrieveByLocation(location);
    }

    @Override
    public List<String> requestDeviceServiceList(String location) {
        logger.debug("Location = " + location);
        return deviceStore.retrieveDeviceService(location);
    }

    @Override
    public String searchOperation(String deviceId, String deviceService) {
        //SDA에 DeviceId와 deviceService를 보낸다.
        logger.debug("Device ID = " + deviceId + " DeviceService = " + deviceService);
        String responseData = deviceICollectionProxy.findDeviceOperation(deviceId,deviceService);
        return responseData;
    }

    @Override
    public List<Device> searchDeviceList() {
        List<Device> deviceList = deviceStore.retrieveDeviceList();
        for(Device device : deviceList){
            logger.debug("Device = " + device.toString());
        }
        return deviceList;
    }

    @Override
    public void deviceUpdate(Device device) {
        deviceStore.update(device);
    }

    @Override
    public String deviceSubscription(String uri) {
        DeviceSubscriptionData deviceSubscriptionData = new DeviceSubscriptionData();
        deviceSubscriptionData.set_uri(uri);
        deviceSubscriptionData.set_notificationuri(ClientProfile.SO_DEVICE_STATUS_URI);

        return deviceControlProxy.deviceSubscriptionRequest(ClientProfile.SI_CONTOL_URI,deviceSubscriptionData);
    }


    private DeviceControlMessage deviceDataConversion(String deviceId, String commandId, String deviceCommand){
        DeviceControlMessage deviceControlMessage = new DeviceControlMessage();

        deviceControlMessage.set_uri(deviceId);
        deviceControlMessage.set_commandId(commandId);
        if(deviceId != null && (deviceId.contains("Blind") || deviceId.contains("BeamScreen"))){
            deviceControlMessage.set_command(ClientProfile.SI_CONTROL_ACTION);
        }else {
            deviceControlMessage.set_command(ClientProfile.SI_CONTROL_POWER);
        }
        deviceControlMessage.setCnf(ClientProfile.SO_CONTROL_TYPE);
        deviceControlMessage.setCon(deviceCommand);

        return deviceControlMessage;
    }

    private Device deviceRequest(String uri){
        //
        Device device = deviceICollectionProxy.findDeviceByID(uri);
        return device;
    }

    private void controlResultsStorage(String deviceId, String commandId, String deviceCommand, ResultMessage resultMessage){

        DeviceResult deviceResult = new DeviceResult();

        deviceResult.setCommandId(commandId);
        deviceResult.setSendMessage(deviceId + deviceCommand);
        deviceResult.setDeviceUrl(deviceId);
        deviceResult.setValue(deviceCommand);
        deviceResult.setResult1(resultMessage.getCode());
        deviceResult.setResult2("");

        deviceResultStore.create(deviceResult);

    }

    private void deviceCreate(Device device){
        deviceStore.create(device);
    }

    private void sessionDataUpdate(SessionStore sessionStore, Session session, String data, String key){
        List<String> sessionDeviceLocationList = null;
        if(session.isExistSessionData(key)){
            sessionDeviceLocationList = DataConversion.stringDataToList(session.findSessionData(key));
        }
        if(sessionDeviceLocationList == null){
            sessionDeviceLocationList = new ArrayList<>();
        }
        sessionDeviceLocationList.add(data);
        session.insertSessionData(key, DataConversion.listDataToString(sessionDeviceLocationList));
        sessionStore.updateSession(session);
    }

}
