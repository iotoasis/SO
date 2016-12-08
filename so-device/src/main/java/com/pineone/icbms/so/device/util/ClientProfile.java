package com.pineone.icbms.so.device.util;

public class ClientProfile {

    public static final String	SO_CONTROL_TYPE				= "text/plain:0";
    public static final String  SI_CONTROL_POWER            = "Power";
    public static final String  SI_CONTROL_ACTION           = "Action";

    public static final String  SI_COMMAND_ID               = "cmd_";

    public static final String  SI_DEVICE_BEAMSCREEN        = "BeamScreen";
    public static final String  SI_DEVICE_BLIND             = "Blind";

    public static final String  SI_CONTAINER_STATUS         = "/status";
    public static final String  SI_CONTAINER_POWER          = "/Power";
    public static final String  SI_CONTAINER_ACTION         = "/Action";


    // Presentation Data
    public static final String	RESPONSE_SUCCESS			= "success";
    public static final String	RESPONSE_FAILURE			= "failure";
    public static final String	RESPONSE_SUCCESS_CODE		= "200";
    public static final String	RESPONSE_FIALURE_CODE		= "4000";
    public static final String	RESPONSE_SUCCESS_ONEM2MCODE	= "2000";


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
