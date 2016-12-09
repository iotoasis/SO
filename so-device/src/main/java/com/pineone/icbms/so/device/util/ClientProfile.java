package com.pineone.icbms.so.device.util;

public class ClientProfile {

    // oneM2M control Data
    public static final String	SO_CONTROL_TYPE				= "text/plain:0";
    public static final String  SI_CONTROL_POWER            = "Power";
    public static final String  SI_CONTROL_ACTION           = "Action";
    public static final String  SI_COMMAND_ID               = "cmd_";

    public static final String DEVICE_SERVICE_NOTI_TYPE     = "admin-noti";

    // oneM2M device Data
    public static final String  SI_DEVICE_BEAMSCREEN        = "BeamScreen";
    public static final String  SI_DEVICE_BLIND             = "Blind";

    // oneM2M Container Data
    public static final String  SI_CONTAINER_STATUS         = "/status";
    public static final String  SI_CONTAINER_POWER          = "/Power";
    public static final String  SI_CONTAINER_ACTION         = "/Action";


    // oneM2M Result Data
    public static final String	RESPONSE_SUCCESS			= "success";
    public static final String	RESPONSE_FAILURE			= "failure";
    public static final String	RESPONSE_FIALURE_CODE		= "4000";
    public static final String	RESPONSE_SUCCESS_ONEM2MCODE	= "2000";

    // Device Data Prefix
    public static final String DEVICE_PRE                   = "device-";


    private ClientProfile() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean actionDeviceCommand(String deviceUri){
        if(deviceUri.contains(SI_DEVICE_BEAMSCREEN) || deviceUri.contains(SI_DEVICE_BLIND)){
            return true;
        } else {
            return false;
        }
    }

}
