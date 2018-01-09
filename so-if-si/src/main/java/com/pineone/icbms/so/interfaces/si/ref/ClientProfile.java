package com.pineone.icbms.so.interfaces.si.ref;

/**
 * Created by melvin on 2017. 4. 4..
 */

//SO 에서 사용하는 타 연동 포인트 주소 및 name 지정
public class ClientProfile {

    //oneM2M controlData
    public static final String	SO_CONTROL_TYPE				= "text/plain:0";
    public static final String  SI_CONTROL_ACTION           = "Action";
    public static final String  SI_CONTROL_POWER            = "Power";
    public static final String  SI_COMMAND_ID               = "cmd_";

    //LWM2M ControlData
    public static final String	SI_CONTROL_JSON_TYPE		= "application/json:1";
    public static final String  SI_CONTROL_LWM2M_SOUND      = "sound__-1024-12-3";
    public static final String  SI_CONTROL_LWM2M            = "lwm2m";
    public static final String  SI_CONTROL_LWM2M_EXECUTE    = "execute";
    public static final String  SI_CONTROL_LWM2M_RESOURCEURI= "/1024/12/3";
    public static final String  SI_CONTROL_LWM2M_DISPLAYNAME= "sound";
    public static final String  SI_CONTROL_LWM2M_OUI        = "000001";
    public static final String  SI_CONTROL_LWM2M_MODELNAME  = "LWM2M Client(raspberry)";
    public static final String  SI_CONTROL_LWM2M_SN         = "90:9F:33:EF:D8:ED";
    public static final String  SI_CONTROL_LWM2M_AUTHID     = "testlwm2mclient";
    public static final String  SI_CONTROL_LWM2M_AUTHPWD    = "1234567890abcdef1234567890abcdef";

    //oneM2M device Data
    public static final String SI_DEVICE_BEAMSCREEN         = "BeamScreen";
    public static final String SI_DEVICE_BLIND              = "Blind";

    //oneM2M Result Data
    public static final String	RESPONSE_FAILURE_CODE		= "4001";
    public static final String	RESPONSE_SUCCESS_ONEM2MCODE	= "2001";
	/*
	    2001 성공 / 
	    2002 삭제
	    2003 업데이트
	    2004 리트리브    
	    4001 FAIL    
	*/    

    //oneM2M Container Data
    public static final String  SI_CONTAINER_STATUS         = "/status";
    public static final String  SI_CONTAINER_POWER          = "/Power";
    public static final String  SI_CONTAINER_ACTION         = "/Action";

    public static boolean actionDeviceCommand(String deviceUri){
        if(deviceUri.contains(SI_DEVICE_BEAMSCREEN) || deviceUri.contains(SI_DEVICE_BLIND)){
            return true;
        } else {
            return false;
        }
    }
}
