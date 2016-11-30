package com.pineone.icbms.so.device.logic;

import com.pineone.icbms.so.device.entity.*;
import com.pineone.icbms.so.device.proxy.DeviceControlProxy;
import com.pineone.icbms.so.device.proxy.DeviceICollectionProxy;
import com.pineone.icbms.so.device.store.DeviceResultStore;
import com.pineone.icbms.so.device.store.DeviceStore;
import com.pineone.icbms.so.device.util.ClientProfile;
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
import java.util.concurrent.TimeUnit;

@Service
public class DeviceManagerLogic implements DeviceManager {

    public static final Logger logger = LoggerFactory.getLogger(DeviceManagerLogic.class);

    public static final String DEVICE_SERVICE_NOTI_TYPE     = "admin-noti";
    public static final String DEVICE_PRE                   = "device-";

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
    public void deviceRegister(String deviceUri, String time){
        // NOTE : 디바이스 생성.
        logger.debug("Device Uri = " + deviceUri + " Time = " + time);
        // 디바이스 ID로 SDA에 데이터 요청.
        // TODO: SDA에 Device Uri만으로 Device정보 연동 규격 필요.
        Device device = deviceRequest(ClientProfile.SDA_DATAREQUEST_URI + ClientProfile.SDA_DEVICE + deviceUri);
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
            logger.info("The device does not exist.");
            return "The device does not exist.";
        } else if(device.getDeviceServices() != null && DEVICE_SERVICE_NOTI_TYPE.equals(device.getDeviceServices().get(0))){
            sessionDataUpdate(sessionStore, session, device.getDeviceLocation(),DefaultSession.DEVICE_LOCATION);
            session.insertSessionData(DefaultSession.DEVICE_RESULT, DefaultSession.CONTROL_EXECUTION + "_" + DEVICE_SERVICE_NOTI_TYPE);
            // DB에 Session을 저장.
            sessionStore.updateSession(session);
            return DEVICE_SERVICE_NOTI_TYPE;
        }

        // SI를 제어할수 있는 DeviceControlMessage로 변환
        DeviceControlMessage deviceControlMessage = deviceDataConversion(deviceId,commandId,deviceCommand);
        logger.debug("DeviceControlMessage = " + deviceControlMessage.toString());

        session = sessionStore.retrieveSessionDetail(localSessionId);
        sessionDataUpdate(sessionStore, session, device.getDeviceLocation(),DefaultSession.DEVICE_LOCATION);
        session.insertSessionData(DefaultSession.DEVICE_RESULT, DefaultSession.CONTROL_EXECUTION);
        sessionStore.updateSession(session);

        if(device.checkStatus(deviceCommand)){
            return "same device state to is.";
        }

        // Device 제어 요청 보냄.
        ResultMessage resultMessage = deviceControlProxy.deviceControlRequest(ClientProfile.SI_CONTOL_URI,deviceControlMessage);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device Control Result : " + " , Device Uri = " + device.getDeviceUri() + " , Result : " + resultMessage + " , Session ID = " + sessionId);

        /**
         * Device 제어 후 제어 결과가 Success면 Device Subscription 요청
         */
        if(resultMessage.getCode().equals(ResultMessage.RESPONSE_SUCCESS_ONEM2MCODE)) {
            String response = deviceSubscription(device.getDeviceUri());
            logger.debug(LogPrint.LogMethodNamePrint() + " | Device Subscription : " + " , Device Uri = " + device.getDeviceUri() + " , Result : " + response + " , Session ID = " + sessionId);
            if(response.equals(ResultMessage.RESPONSE_SUCCESS_ONEM2MCODE)){
                /**
                 * Device Subscription이 성공이면 30초 후 Subscription 해지 요청.
                 * 시간은 정책으로 수정 가능.
                 */
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
        }



        // Device 제어 결과 저장.
        // 2차년도 제어 결과 저장을 session으로 대처.
//        controlResultsStorage(deviceId, commandId, deviceCommand, resultMessage);

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
        if(!deviceStatusData.get_uri().isEmpty()){
            Device device = deviceSearchById(deviceStatusData.get_uri());
            if(!deviceStatusData.getDeviceStatus().isEmpty() && !deviceStatusData.checkDeviceStatus(device.getDeviceStatus())) {
                device.setDeviceStatus(deviceStatusData.getDeviceStatus());
                deviceStore.update(device);
            }
        }
    }

    @Override
    public String deviceSubscription(String uri) {
        //
        return deviceControlProxy.deviceSubscriptionRequest(uri);
    }

    @Override
    public String deviceSubscriptionRelease(String uri) {
        //
        return deviceControlProxy.deviceSubscriptionReleaseRequest(uri);
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
        return deviceICollectionProxy.findDeviceByID(uri);
    }

    /**
     * Device 제어 결과 저장. 2차년도에는 Device Subscription으로 불필요.
     * @param deviceId
     * @param commandId
     * @param deviceCommand
     * @param resultMessage
     */
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

    /**
     * Device 생성
     * Device Id, createTime,exfitedTime 추가
     * @param device
     */
    private void deviceCreate(Device device){

        long currentTime = System.currentTimeMillis();
        long modifiedTime = currentTime + 30240000000L;
        device.setId(DEVICE_PRE + UUID.randomUUID().toString());
        device.setDeviceCreateTime(currentTime);
        device.setDeviceExfiredTime(modifiedTime);
        // TODO : Device Command는 언제 요청으로 얻어 올까??
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
