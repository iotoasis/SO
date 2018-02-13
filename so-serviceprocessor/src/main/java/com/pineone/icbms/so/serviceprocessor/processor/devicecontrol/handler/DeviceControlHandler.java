package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.handler;

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

        String sessionId = getTracking().getSessionId();

        String deviceId = virtualDevice.getDeviceId();
        String aspectUri = virtualDevice.getAspect().getUri();
        String functionUri = virtualDevice.getFunction().getUri();
        String controlValue = virtualDevice.getVoValue();

        // cm-dd-aspect-action-value (id, aspect, functionality) 을 이용한 aspect Value 조회
        log.debug("getAspectValueById_Aspect_Function: deviceId={}, aspectUri={}, functionUri={}", deviceId, aspectUri, functionUri);
        ContextModelContent content = new SdaManager().getAspectValueById_Aspect_Function(deviceId, aspectUri, functionUri);
        if (content==null) {
            log.error("getAspectValueById_Aspect_Function : result : content = null");
        }
        
        // set TrackingEntity
        //tracking = getTracking();

        if (content!= null && deviceId != null && !deviceId.isEmpty()) {
        	
            String aspectValue = content.getAspectValue();

            log.debug("getAspectValueById_Aspect_Function : aspect_value={}, action_value={}", aspectValue, content.getActionValue());

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
                session.setId(sessionId);
                session.setContextmodelResult("Happen"); //Session Data 완료 처리
                databaseManager.updateSessionData(session);
*/
            }

            
            if (getTracking().getSimulatorType() == null || "".equals(getTracking().getSimulatorType())) {

            	SessionEntity sessionPrev = databaseManager.getSessionData(sessionId);
            	String deviceResult = sessionPrev.getDeviceResult();
            	if (deviceResult==null)
            		deviceResult = "";
            	
                // 실제 디바이스 실행
                //control device : SI와 통신
                boolean  r = controlDevice(virtualDevice, aspectValue, controlValue);
            	SessionEntity session = new SessionEntity();
            	session.setId(sessionId);
                if (r) {
                	if (!deviceResult.equals(SessionEntity.CONTROL_FAILURE)) {
                		session.setDeviceResult(SessionEntity.CONTROL_EXECUTION);
                        databaseManager.updateSessionData(session);
                	}
                }else {
                	if (!deviceResult.equals(SessionEntity.CONTROL_FAILURE)) {
                		session.setDeviceResult(SessionEntity.CONTROL_FAILURE);
                        databaseManager.updateSessionData(session);
                	}
                }

            }
            else {
            	// 시뮬레이션 : 실제 제어는 하지 않고 로그만 출력함
            	
            	//Session Data
            	SessionEntity session = new SessionEntity();
            	session.setId(sessionId);
            	session.setDeviceResult(SessionEntity.SIMULATEL_EXECUTION);
            	databaseManager.updateSessionData(session);

            	//Tracking Log
            	log.debug("Simulate controlDevice: {}, {}", virtualDevice.getName(), virtualDevice.getId());
                getTracking().setProcessId(virtualDevice.getId());
                getTracking().setProcessName(virtualDevice.getName() + ", 디바이스제어");
                getTracking().setProcessValue(controlValue);
                TrackingProducer.send(getTracking());
            }

            // grib session device
            SessionEntity sessionDevice = new SessionEntity();
            sessionDevice.setId(sessionId);
            sessionDevice.setDeviceKey(deviceId);
            sessionDevice.setDeviceLocation(loc);
            log.debug("session device : {}", sessionDevice);
            databaseManager.createSessionDataDevice(sessionDevice);
            
            if ("Y".equals(virtualDevice.getIsLast())) {
            	log.debug("===========end==============");
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
     * @param virtualDevice      control device
     * @param deviceControlValue device control value
     */
    private boolean controlDevice(IGenericVirtualDevice virtualDevice, String aspect, String deviceControlValue) {

        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());
        String deviceId = virtualDevice.getDeviceId();

        //Device Uri중에서 "http://www.iotoasis.org" 문자열 제외하고 si로 전달
        deviceId = deviceId.replace(ClientProfile.IOT_OASIS_DOMAIN_NAME, ""); //"http://www.iotoasis.org" 삭제
        
        boolean resultControl = false;

        try {
            String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();

            //String aspect = virtualDevice.getAspect().getUri();
            //ResultMessage resultMessage = deviceManager.deviceExecute(commandId, virtualDevice.getId(), deviceControlValue);
            
            log.debug("# Run controlDevice: {}, {}, {}, {}", commandId, deviceId, aspect, deviceControlValue);
            ResultMessage resultMessage = deviceManager.deviceExecute(commandId, deviceId, aspect, deviceControlValue);

            getTracking().setProcessId(deviceId);//resultMessage.getCode());
            getTracking().setProcessValue(deviceControlValue);

            // 정상응답이 아닌경우
            if (!ClientProfile.RESPONSE_SUCCESS_ONEM2MCODE.equals(resultMessage.getCode())) {
                getTracking().setProcessName(SessionEntity.RESPONSE_ERROR);
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
	                session.setContextmodelResult(SessionEntity.HAPPEN); //Session Data 완료 처리
	                databaseManager.updateSessionData(session);
            	}
            	//log.debug("===========end==============");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            getTracking().setProcessId(deviceId);
            getTracking().setProcessName(SessionEntity.EXCEPTION);
            getTracking().setProcessResult(e.getMessage());
            TrackingProducer.send(getTracking());
        }
        return resultControl;
    }
}
