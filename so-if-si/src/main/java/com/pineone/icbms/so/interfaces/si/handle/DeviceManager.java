package com.pineone.icbms.so.interfaces.si.handle;

import com.pineone.icbms.so.interfaces.si.handle.itf.IDeviceManager;
import com.pineone.icbms.so.interfaces.si.model.DeviceControlMessage;
import com.pineone.icbms.so.interfaces.si.model.LWM2MDeviceControl;
import com.pineone.icbms.so.interfaces.si.model.ResultMessage;
import com.pineone.icbms.so.interfaces.si.proxy.DeviceControlProxy;
import com.pineone.icbms.so.interfaces.si.proxy.DeviceSIProxy;
import com.pineone.icbms.so.interfaces.si.ref.SIAddressStore;
import com.pineone.icbms.so.interfaces.si.ref.ClientProfile;
import com.pineone.icbms.so.util.itf.address.AddressCollector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by melvin on 2017. 4. 4..
 */

public class DeviceManager implements IDeviceManager{
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    AddressCollector addressCollector = new AddressCollector();
    DeviceControlProxy deviceControlProxy = new DeviceSIProxy();

    /*
     * Tracking
     */
//    private TrackingEntity tracking;
//
//    public void setTrackingEntity(TrackingEntity tracking) {
//        this.tracking = tracking;
//    }

    @Override
    public ResultMessage deviceExecute(String commandId, String deviceUri, String aspectId, String deviceCommand) {

    	//deviceCommand : 제어값
    	
//        // so-serviceprocessor 에서 최종 device control 단에서 commandId 를 생성해서 인자로 받도록 수정
//        String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();

        //TODO: SO에서 디바이스 관련 리소스를 가지고 있지 않고 SDA 에 질의할텐데 실제로 수신받는 데이터 형태는?
//        Device device = deviceSearchById(deviceId)

        DeviceControlMessage deviceControlMessage = new DeviceControlMessage();

        LWM2MDeviceControl lwm2MDeviceControl = new LWM2MDeviceControl();

        //TODO : Device ID = Device Uri 일 경우 해당
        if(deviceUri.contains(ClientProfile.SI_CONTROL_LWM2M)) {
        	String resourceUri;
        	String displayName;
        	if (aspectId.startsWith("LEDPower")) {
        		resourceUri = ClientProfile.SI_CONTROL_LWM2M_DISPLAYNAME_LED; //LED : "/1024/12/1", SOUND : "/1024/12/3"
        		displayName = ClientProfile.SI_CONTROL_LWM2M_DISPLAYNAME_LED; //LED : "LED", sound:"Sound"
        	} else if (aspectId.startsWith("SoundPower")) {
        		resourceUri = ClientProfile.SI_CONTROL_LWM2M_DISPLAYNAME_SOUND; //LED : "/1024/12/1", SOUND : "/1024/12/3"
        		displayName = ClientProfile.SI_CONTROL_LWM2M_DISPLAYNAME_SOUND; //LED : "LED", sound:"Sound"
        	} else {
        		displayName = null;
        		resourceUri = null;
        	}
        	
            lwm2MDeviceControl.setOperation(ClientProfile.SI_CONTROL_LWM2M_EXECUTE);
            lwm2MDeviceControl.setResourceUri(resourceUri);
            lwm2MDeviceControl.setDisplayName(displayName);
            lwm2MDeviceControl.setOui(ClientProfile.SI_CONTROL_LWM2M_OUI);
            lwm2MDeviceControl.setModelName(ClientProfile.SI_CONTROL_LWM2M_MODELNAME);
            lwm2MDeviceControl.setSn(ClientProfile.SI_CONTROL_LWM2M_SN);
            lwm2MDeviceControl.setAuthId(ClientProfile.SI_CONTROL_LWM2M_AUTHID);
            lwm2MDeviceControl.setAuthPwd(ClientProfile.SI_CONTROL_LWM2M_AUTHPWD);
            lwm2MDeviceControl.setSv(deviceCommand);
            deviceControlMessage = lwm2mDeviceDataConversion(deviceUri, commandId, aspectId, deviceCommand);
        } else{
            deviceControlMessage = deviceDataConversion(deviceUri, commandId, aspectId, deviceCommand);
        }

        //디바이스 제어 요청 보냄
        ResultMessage resultMessage = new ResultMessage();
        if(deviceUri.contains("lwm2m")){
            resultMessage = deviceControlProxy.lwm2mDeviceControlRequest(
                    addressCollector.getServerAddress(AddressCollector.SI_SERVER) + SIAddressStore.SI_LWM2M_CONTOL_URI
                    , deviceControlMessage, lwm2MDeviceControl);
        } else {
            resultMessage = deviceControlProxy.deviceControlRequest(
                    addressCollector.getServerAddress(AddressCollector.SI_SERVER) + SIAddressStore.SI_AUTH_CONTOL_URI
                    ,deviceControlMessage
            );
        }

        //Device 제어 후 제어 결과가 Success 면 Device 제어값 Subscription 요청
//        if(resultMessage.getCode().equals(ClientProfile.RESPONSE_SUCCESS_ONEM2MCODE) && !(deviceUri.contains("lwm2m"))){
//
//            TODO : 디바이스 제어 상태 저장 - 어디에? 어떤 정보를?
//            디바이스 명령 정보   device.setDeviceStatus(deviceCommand);
//            디바이스 내용 정보  deviceStore.update(device);
//
//            String subscriptionUri = deviceUri + (ClientProfile.actionDeviceCommand(deviceUri) ? ClientProfile.SI_CONTAINER_ACTION : ClientProfile.SI_CONTAINER_POWER) + ClientProfile.SI_CONTAINER_STATUS;
//            String response = deviceControlProxy.deviceSubscriptionRequest(subscriptionUri, commandId);

//            TODO : 디바이스 subscription 데이터 저장
//              deviceSubscriptionStore.create(new DeviceSubscriptionObject(deviceControlMessage.get_commandId(), deviceUri,
//                    deviceControlMessage.getCon(), response));

//            Device Subscription 이 성공이면 30초 후 Subscription 해지 요청. 정채에 따라 시간 수정 가능

//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        TimeUnit.SECONDS.sleep(300);
//                        String response = deviceControlProxy.deviceSubscriptionReleaseRequest(deviceUri);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//                    thread.start();

//        }


        return resultMessage;//.getCode();
    }



    private DeviceControlMessage lwm2mDeviceDataConversion(String deviceId, String commandId, String aspectId, String deviceCommand){
        DeviceControlMessage deviceControlMessage = new DeviceControlMessage();
        deviceControlMessage.set_uri(deviceId);
        deviceControlMessage.set_commandId(commandId);
        deviceControlMessage.set_command(aspectId);
        deviceControlMessage.setCnf(ClientProfile.SI_CONTROL_JSON_TYPE);
        deviceControlMessage.setCon(deviceCommand);
        return deviceControlMessage;
    }

    private DeviceControlMessage deviceDataConversion(String deviceId, String commandId, String aspectId, String deviceCommand){
        DeviceControlMessage deviceControlMessage = new DeviceControlMessage();

        deviceControlMessage.set_uri(deviceId);
        deviceControlMessage.set_commandId(commandId);

        // 2017.9.8 SI Spect 변경 by gibubi
/*
        if(deviceId != null && ClientProfile.actionDeviceCommand(deviceId)){
            deviceControlMessage.set_command(ClientProfile.SI_CONTROL_ACTION);
        }else {
            deviceControlMessage.set_command(ClientProfile.SI_CONTROL_POWER);
        }
*/      
        //get Last aspect-Name part from aspectUri
        String aspectName = aspectId.substring(aspectId.lastIndexOf("/")+1,aspectId.length());
        deviceControlMessage.set_command(aspectName);

        deviceControlMessage.setCnf(ClientProfile.SO_CONTROL_TYPE);
        deviceControlMessage.setCon(deviceCommand);

        return deviceControlMessage;
    }


    public static void testmain(String[] args) {
    	DeviceManager dm = new DeviceManager();
    	String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();
    	String aspectId;
    	String controlValue = "1";
    	int count=1;

    	DeviceControlMessage dcMsg;
    	
    	aspectId = "http://www.iotoasis.org/ontology/electronicpower-aspect";
    	System.out.println(aspectId);
    	dcMsg = dm.deviceDataConversion("deviceId",commandId , aspectId, controlValue);
    	System.out.println(" case"+Integer.toString(count++)+"==> "+dcMsg.toString());

    	aspectId = "electronicpower-aspect";
    	System.out.println(aspectId);
    	dcMsg = dm.deviceDataConversion("deviceId",commandId , aspectId, controlValue);
    	System.out.println(" case"+Integer.toString(count++)+"==> "+dcMsg.toString());

    	aspectId = "/electronicpower-aspect";
    	System.out.println(aspectId);
    	dcMsg = dm.deviceDataConversion("deviceId",commandId , aspectId, controlValue);
    	System.out.println(" case"+Integer.toString(count++)+"==> "+dcMsg.toString());

    	aspectId = "";
    	System.out.println(aspectId);
    	dcMsg = dm.deviceDataConversion("deviceId",commandId , aspectId, controlValue);
    	System.out.println(" case"+Integer.toString(count++)+"==> "+dcMsg.toString());

    	//
    	String deviceUri = "/herit-in/herit-cse/ONDB_BeamProjector01_001";
    	aspectId = "http://www.iotoasis.org/ontology/lecture-aspect";
    	String deviceCommand="1";
    	ResultMessage resultMessage = dm.deviceExecute(commandId, deviceUri, aspectId, deviceCommand);
    	System.out.println(resultMessage);
    }
}
