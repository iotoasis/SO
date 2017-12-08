package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.handler;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.DeviceControlValue;
import com.pineone.icbms.so.devicecontrol.model.virtualdevice.driver.IGenericDeviceDriver;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelContent;
import com.pineone.icbms.so.interfaces.si.handle.DeviceManager;
import com.pineone.icbms.so.interfaces.si.ref.ClientProfile;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pineone.icbms.so.interfaces.si.model.ResultMessage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * device control handler.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 20..
 */
public class DeviceControlHandler extends AProcessHandler {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Database Manager
     */
    //protected IDatabaseManager databaseManager;

    /**
     * device manager
     */
    protected DeviceManager deviceManager = new DeviceManager();

    /**
     * constructor
     *
     * @param databaseManager DatabaseManager
     */
    public DeviceControlHandler(IDatabaseManager databaseManager)  {
        super(databaseManager);
    }

    /**
     * handle a message.<BR/>
     *
     * @param handleMessage
     */
    @Override
    public void handle(Object handleMessage) {

    }

    /**
     * device control handle.<BR/>
     *
     * @param virtualDevice  IGenericVirtualDevice
     * @param contextModelId context model id
     */
    public void handle(IGenericVirtualDevice virtualDevice, String locationUri, String contextModelId) {

        getTracking().setProcess(getClass().getSimpleName());
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        // get DeviceDriver from sda by virtual object features
        // retrieve device list
//        SdaClient sdaClient = new SdaClient();
//        List<IGenericVirtualDevice> deviceList = sdaClient.retreiveDeviceList(null, locationUri);

        //create control list
        // TODO product : get device control value to use SDA API.
//        DeviceControlValue deviceControlValue = ModelMapper.toDeviceControlValue(deviceControlForDB);
//        LinkedHashMap<IGenericDeviceDriver, List<DeviceControlValue>> controlList = new LinkedHashMap<>();
//        if (deviceList != null) {
//            for (IGenericVirtualDevice device : deviceList) {
//                String driverClassName = device.getDriverClassName();
        //load devicemapper driver
//                IGenericDeviceDriver deviceDriver = new DeviceDriverLoader().loadDeviceDriver(Settings.DEVICE_DRIVER_PATH, driverClassName);
        //retreive values from sda
//                List<DeviceControlValue> values = sdaClient.retreiveDeviceControlValues(null, null, locationUri, device.getId());
//                controlList.put(deviceDriver, values);
//            }


        String deviceId = virtualDevice.getDeviceId();
        String aspectUri = virtualDevice.getAspect().getUri();
        String functionUri = virtualDevice.getFunction().getUri();
        String controlValue = virtualDevice.getVoVale();
        
        // cm-dd-aspect-action-value (id, aspect, functionality) 을 이용한 aspect Value 조회
        ContextModelContent content = new SdaManager().getAspectValueById_Aspect_Function(deviceId, aspectUri, functionUri);
        String aspectValue = content.getAspectValue();

        log.debug("getAspectValueById_Aspect_Function : aspect_value={}, action_value={}", aspectValue, content.getActionValue());
        
        // set TrackingEntity
        //tracking = getTracking();

        if (deviceId != null && !deviceId.isEmpty()) {
        	
        	String locLower,loc;
        	if (locationUri != null) {
        		locLower = locationUri.substring(locationUri.lastIndexOf("/")+1,locationUri.length());
        		loc = locLower.toUpperCase(); //대문자
        	}
        	else {
        		loc = "";
        	}
        	
            // simulator. 마지막 VO인 경우
            if ("Y".equals(virtualDevice.getIsLast())) {
                getTracking().setStatusCd("F");
/*
                // grib session location
                SessionEntity session = new SessionEntity();
                session.setId(getTracking().getSessionId());
                session.setContextmodelResult("Happen"); //Session Data 완료 처리
                databaseManager.updateSessionData(session);
*/
            }

            // grib session device
            SessionEntity sessionDevice = new SessionEntity();
            sessionDevice.setId(getTracking().getSessionId());
            sessionDevice.setDeviceKey(deviceId);
            sessionDevice.setDeviceLocation(loc);
            log.trace("session device : {}", sessionDevice);
            databaseManager.createSessionDataDevice(sessionDevice);
            
            if (getTracking().getSimulatorType() == null || "".equals(getTracking().getSimulatorType())) {
            	SessionEntity session = new SessionEntity();
            	session.setId(getTracking().getSessionId());
            	session.setDeviceResult("CONTROL_EXECUTION");
            	databaseManager.updateSessionData(session);

                // 실제 디바이스 실행
                //control device : SI와 통신
                controlDevice(virtualDevice, aspectValue, controlValue);
            }
            else {
            	// 시뮬레이션 : 실제 제어는 하지 않고 로그만 출력함
            	
            	//Session Data
            	SessionEntity session = new SessionEntity();
            	session.setId(getTracking().getSessionId());
            	session.setDeviceResult("SIMULATEL_EXECUTION");
            	databaseManager.updateSessionData(session);

            	//Tracking Log
            	log.debug("Simulate controlDevice: {}, {}", virtualDevice.getName(), virtualDevice.getId());
                getTracking().setProcessId(virtualDevice.getId());
                getTracking().setProcessName(virtualDevice.getName() + ", 디바이스제어");
                getTracking().setProcessValue(controlValue);
                TrackingProducer.send(getTracking());
            }
        }
        else
        {
            log.warn("DeviceControlForDB NOT exist: virtualdevice: {}, location: {}, contextmodel: {}", virtualDevice, locationUri, contextModelId);
            getTracking().setProcessId(String.format("deviceId : %s, contextModelId : %s", virtualDevice.getId(), contextModelId));
            getTracking().setProcessName("디바이스 제어정보 없음");
            TrackingProducer.send(getTracking());
        }
    }

    /**
     * control a device.<BR/>
     *
     * @param deviceControlList device control list
     */
    private void controlDevice(LinkedHashMap<IGenericDeviceDriver, List<DeviceControlValue>> deviceControlList) {
        //TODO: thread process 고민 필요
        if (deviceControlList != null) {
            for (Map.Entry<IGenericDeviceDriver, List<DeviceControlValue>> entry : deviceControlList.entrySet()) {
                IGenericDeviceDriver deviceDriver = entry.getKey();
                List<DeviceControlValue> values = entry.getValue();
                //
                //databaseManager.getDeviceControlByDeviceIdAndContextModelID
                log.debug(">>>>>>>>> size of deviceControlList: {}, {}, {} ", deviceControlList.size(), entry.getKey(), entry.getValue());
                deviceDriver.control(values);
            }
        }
    }

    /**
     * control a device.<BR/>
     *
     * @param virtualDevice      control device
     * @param deviceControlValue device control value
     */
    private void controlDevice(IGenericVirtualDevice virtualDevice, String aspect, String deviceControlValue) {

        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());
        String deviceId = virtualDevice.getDeviceId();

        try {
            String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();

            //String aspect = virtualDevice.getAspect().getUri();
            //ResultMessage resultMessage = deviceManager.deviceExecute(commandId, virtualDevice.getId(), deviceControlValue);
            
            log.debug("# Run controlDevice: {}, {}, {}, {}", commandId, deviceId, aspect, deviceControlValue);
            ResultMessage resultMessage = deviceManager.deviceExecute(commandId, deviceId, aspect, deviceControlValue);

            getTracking().setProcessId(deviceId);//resultMessage.getCode());
            getTracking().setProcessValue(deviceControlValue);

            boolean resultControl = false;
            // 정상응답이 아닌경우
            if (!"2000".equals(resultMessage.getCode())) {
                getTracking().setProcessName("Response ERROR");
                getTracking().setProcessResult(resultMessage.getMessage());
                log.error("Response ERROR: code={}, msg={}", resultMessage.getCode(), resultMessage.getMessage());
            } else {
                getTracking().setCommandId(commandId);
                getTracking().setProcessName(resultMessage.getMessage());
                getTracking().setProcessResult(resultMessage.getCode());
                resultControl = true;
            }
            TrackingProducer.send(getTracking());
            log.debug("Result controlDevice: {}", resultMessage);

            if ("Y".equals(virtualDevice.getIsLast())) {
            	if (resultControl == true) {
	                // grib session location
	                SessionEntity session = new SessionEntity();
	                session.setId(getTracking().getSessionId());
	                session.setContextmodelResult("Happen"); //Session Data 완료 처리
	                databaseManager.updateSessionData(session);
            	}
            	log.debug("===========end==============");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            getTracking().setProcessId(deviceId);
            getTracking().setProcessName("Exception");
            getTracking().setProcessResult(e.getMessage());
            TrackingProducer.send(getTracking());
        }
    }
}
