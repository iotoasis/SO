package com.pineone.icbms.so.device.logic;

import com.pineone.icbms.so.device.entity.*;
import com.pineone.icbms.so.device.proxy.DeviceControlProxy;
import com.pineone.icbms.so.device.proxy.DeviceICollectionProxy;
import com.pineone.icbms.so.device.store.DeviceStore;
import com.pineone.icbms.so.device.store.DeviceSubscriptionStore;
import com.pineone.icbms.so.device.store.mongo.DeviceSubscriptionObject;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.logprint.LogPrint;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.util.session.store.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceManagerLogic implements DeviceManager {

    public static final Logger logger = LoggerFactory.getLogger(DeviceManagerLogic.class);

    @Autowired
    private DeviceStore deviceStore;

    @Autowired
    private SessionStore sessionStore;

    @Autowired
    private DeviceICollectionProxy deviceICollectionProxy;

    @Autowired
    private DeviceControlProxy deviceControlProxy;

    @Autowired
    private DeviceSubscriptionStore deviceSubscriptionStore;

    @Autowired
    ContextAddress contextAddress;

    @Override
    public void deviceRegister(String deviceUri, String time){
        // NOTE : 디바이스 생성.
        logger.debug("Device Uri = " + deviceUri + " Time = " + time);
        // 디바이스 ID로 SDA에 데이터 요청.
        // TODO: SDA에 Device Uri만으로 Device정보 연동 규격 필요.
        Device device = deviceRequest(contextAddress.getServerAddress(ContextAddress.SI_SERVER) + AddressStore.SDA_DEVICE + deviceUri);
        logger.debug("Device = " + device.toString());
        // 디바이스 저장.
        deviceCreate(device);
    }

    @Override
    public void deviceRelease(String deviceId){
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device ID = " + deviceId);
        deviceStore.delete(deviceId);
    }

    @Override
    public String deviceExecute(String deviceId,String deviceCommand, String sessionId){

        logger.debug(LogPrint.LogMethodNamePrint() + " | DeviceID = " + deviceId + " , DeviceCommand = " + deviceCommand + " , Session Id = " + sessionId);

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

        sessionDataUpdate(sessionStore,session,deviceId, DefaultSession.DEVICE_KEY);

        String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();
        Device device = deviceSearchById(deviceId);
        if(device == null){
            sessionDataUpdate(sessionStore,session,"The device does not exist.", DefaultSession.DEVICE_RESULT);
            logger.info("The device does not exist.");
            return "The device does not exist.";
        } else if(device.getDeviceServices() != null && ClientProfile.DEVICE_SERVICE_NOTI_TYPE.equals(device.getDeviceServices().get(0))){
            sessionDataUpdate(sessionStore, session, device.getDeviceLocation(),DefaultSession.DEVICE_LOCATION);
            sessionDataUpdate(sessionStore,session,DefaultSession.CONTROL_EXECUTION + "_" + ClientProfile.SERVICE_ALARM_TYPE, DefaultSession.DEVICE_RESULT);
            return ClientProfile.SERVICE_ALARM_TYPE;
        }

        // SI를 제어할수 있는 DeviceControlMessage로 변환
        DeviceControlMessage deviceControlMessage = deviceDataConversion(deviceId,commandId,deviceCommand);
        logger.debug("DeviceControlMessage = " + deviceControlMessage.toString());

        session = sessionStore.retrieveSessionDetail(localSessionId);
        sessionDataUpdate(sessionStore, session, device.getDeviceLocation(),DefaultSession.DEVICE_LOCATION);

        /*
        if(device.checkStatus(deviceCommand)){
            return "same device state to is.";
        }
        */

        // Device 제어 요청 보냄.
        ResultMessage resultMessage = deviceControlProxy.deviceControlRequest(contextAddress.getServerAddress(ContextAddress.SI_SERVER) + AddressStore.SI_CONTOL_URI,deviceControlMessage);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device Control Result : " + " , Device Uri = " + device.getDeviceUri() + " , Result : " + resultMessage + " , Session ID = " + sessionId);
        sessionDataUpdate(sessionStore,session,resultMessage.getCode(), DefaultSession.DEVICE_RESULT);
        /**
         * Device 제어 후 제어 결과가 Success면 Device Subscription 요청
         */
        if(resultMessage.getCode().equals(ClientProfile.RESPONSE_SUCCESS_ONEM2MCODE)) {
            // 디바이스 상태 저장.
            device.setDeviceStatus(deviceCommand);
            deviceStore.update(device);

            String subscriptionUri = device.getDeviceUri() + (ClientProfile.actionDeviceCommand(device.getDeviceUri()) ? ClientProfile.SI_CONTAINER_ACTION : ClientProfile.SI_CONTAINER_POWER) + ClientProfile.SI_CONTAINER_STATUS;
            String response = deviceSubscription(subscriptionUri, deviceControlMessage.get_commandId());
            logger.debug(LogPrint.LogMethodNamePrint() + " | Device Subscription : " + " , Device Uri = " + device.getDeviceUri() + " , Result : " + response + " , Session ID = " + sessionId);

            /**
             * Device Subscription 데이터 저장
             */
            saveDeviceSubscriptionData(deviceControlMessage.get_commandId(),device.getDeviceUri(), deviceControlMessage.getCon(), response);

            /*
                디바이스 해제는 Controller에서 상태 업데이트 후 해제.
            if(response.equals(ResultMessage.RESPONSE_SUCCESS_ONEM2MCODE)){
                *//**
                 * Device Subscription이 성공이면 30초 후 Subscription 해지 요청.
                 * 시간은 정책으로 수정 가능.
                 *//*

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            logger.info("Subscription 대기");
                            TimeUnit.SECONDS.sleep(300);
                            String responese = deviceSubscriptionRelease(device.getDeviceUri());
                            logger.info(String.format("Device SubscriptionRelease : Device Uri = %s DeviceRelease result = %s", device.getDeviceUri(), responese));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
            */

        }



        // Device 제어 결과 저장.
        // 2차년도 제어 결과 저장을 session으로 대처.
//        controlResultsStorage(deviceId, commandId, deviceCommand, resultMessage);

        return resultMessage.getCode();
    }

    @Override
    public Device deviceSearchById(String deviceId) {
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device ID = " + deviceId);
        return deviceStore.retrieveByID(deviceId);
    }

    @Override
    public List<Device> deviceSearchByLocation(String location) {
        logger.debug(LogPrint.LogMethodNamePrint() + " | Location = " + location);
        return deviceStore.retrieveByLocation(location);
    }

    @Override
    public List<String> requestDeviceServiceList(String location) {
        logger.debug(LogPrint.LogMethodNamePrint() + " | Location = " + location);
        return deviceStore.retrieveDeviceService(location);
    }

    @Override
    public String searchOperation(String deviceId, String deviceService) {
        //SDA에 DeviceId와 deviceService를 보낸다.
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device ID = " + deviceId + " , DeviceService = " + deviceService);
        return deviceICollectionProxy.findDeviceOperation(deviceId,deviceService);
    }

    @Override
    public List<Device> searchDeviceList() {
        List<Device> deviceList = deviceStore.retrieveDeviceList();
        for(Device device : deviceList){
            logger.debug(LogPrint.LogMethodNamePrint() + " | Device = " + device.toString());
        }
        return deviceList;
    }

    /**
     * Device 상태값 Update
     * @param deviceStatusData
     */
    @Override
    public void deviceUpdate(DeviceStatusData deviceStatusData) {
        //
        logger.debug(LogPrint.LogMethodNamePrint() + "Device Update Start!!!   DeviceStatusData = " + deviceStatusData.toString());
        if(!deviceStatusData.get_uri().isEmpty()){
            String deviceUri = getOnem2mDeviceUri(deviceStatusData.get_uri());
            Device device = deviceSearchById(deviceUri);
            DeviceSubscriptionObject deviceSubscriptionObject = deviceSubscriptionStore.retrieve(deviceStatusData.get_commandId());
            String response;
            if(deviceSubscriptionObject != null && deviceSubscriptionObject.get_commandId().equals(deviceStatusData.get_commandId()) && deviceSubscriptionObject.getDeviceStatus().equals(deviceStatusData.getCon())){
                logger.debug(LogPrint.LogMethodNamePrint() + "Device Data Update");
                device.setDeviceStatus(deviceStatusData.getCon());
                deviceStore.update(device);
                /**
                 * Device Subscription 해제 요청
                 */
                response = deviceSubscriptionRelease(deviceUri + (ClientProfile.actionDeviceCommand(device.getDeviceUri()) ? ClientProfile.SI_CONTAINER_ACTION : ClientProfile.SI_CONTAINER_POWER) + ClientProfile.SI_CONTAINER_STATUS);

            } else {
                logger.debug("Device Status = " + device.getDeviceStatus() + " DeviceCommand = " + deviceStatusData.getCon());
                logger.debug(LogPrint.LogMethodNamePrint() + "The state or command of the device is different.");
                response = "Status not same.";
            }
            if (deviceSubscriptionObject != null) {
                deviceSubscriptionObject.setReleaseResult(response);
                deviceSubscriptionStore.update(deviceSubscriptionObject);
            }
        }
    }

    @Override
    public String deviceSubscription(String uri, String commandId) {
        //
        return deviceControlProxy.deviceSubscriptionRequest(uri, commandId);
    }

    @Override
    public String deviceSubscriptionRelease(String uri) {
        //
        return deviceControlProxy.deviceSubscriptionReleaseRequest(uri);
    }

    @Override
    public void produceDevice(Device device) {
        deviceCreate(device);
    }

    private DeviceControlMessage deviceDataConversion(String deviceId, String commandId, String deviceCommand){
        DeviceControlMessage deviceControlMessage = new DeviceControlMessage();

        deviceControlMessage.set_uri(deviceId);
        deviceControlMessage.set_commandId(commandId);
        if(deviceId != null && ClientProfile.actionDeviceCommand(deviceId)){
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
        return deviceICollectionProxy.findDeviceByID(uri);
    }

    /**
     * Device 생성
     * Device Id, createTime,exfitedTime 추가
     * @param device
     */
    private void deviceCreate(Device device){

        long currentTime = System.currentTimeMillis();
        long modifiedTime = currentTime + 30240000000L;
        device.setId(ClientProfile.DEVICE_PRE + UUID.randomUUID().toString());
        device.setDeviceCreateTime(currentTime);
        device.setDeviceExfiredTime(modifiedTime);
        // TODO : Device Command는 언제 요청으로 얻어 올까??
        deviceStore.create(device);
    }

    private void sessionDataUpdate(SessionStore sessionStore, Session session, String data, String key){
        List<String> sessionList = null;
        if(session.isExistSessionData(key)){
            sessionList = DataConversion.stringDataToList(session.findSessionData(key));
        }
        if(sessionList == null){
            sessionList = new ArrayList<>();
        }
        sessionList.add(data);
        session.insertSessionData(key, DataConversion.listDataToString(sessionList));
        sessionStore.updateSession(session);
    }

    public String getOnem2mDeviceUri(String uri){
        //
        int stringlength = 3;
        String[] strings = uri.split("/");

        for(int i =1; i<4 ; i++){
            stringlength += strings[i].length();
        }
        return uri.substring(0, stringlength);
    }

    public void saveDeviceSubscriptionData(String commandId, String deviceUri, String status, String result){
        deviceSubscriptionStore.create(new DeviceSubscriptionObject(commandId, deviceUri, status, result));
    }

}
