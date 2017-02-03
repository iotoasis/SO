package com.pineone.icbms.so.device.util;

public class ClientProfile {

    // oneM2M control Data
    public static final String	SO_CONTROL_TYPE				= "text/plain:0";
    public static final String  SI_CONTROL_POWER            = "Power";
    public static final String  SI_CONTROL_ACTION           = "Action";
    public static final String  SI_COMMAND_ID               = "cmd_";

    public static final String DEVICE_SERVICE_NOTI_TYPE     = "http://www.iotoasis.org/ontology/NotiFunctionality";

    // oneM2M device Data
    public static final String SI_DEVICE_BEAMSCREEN         = "BeamScreen";
    public static final String SI_DEVICE_BLIND              = "Blind";
    public static final String SI_DEVICE_HEATER             = "heater";
    public static final String SI_DEVICE_AIRCONDITONER      = "airconditioner";

    // oneM2M Device Control Operation
    public static final String SI_DEVICE_OPERTAION          = "1";

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

    // Context Model
    public static final String CM_LACK_EQUIPMENT_COUNT      = "cm-lack-equipment-count/?p=";
    public static final String CM_TEMP                      = "cm-temp/?p=";
    public static final String PREFIX_CM_BACK_ATTACH        = "?p=,";
    public static final String PREFIX_CM_COMMA              = ",";
    public static final String PREFIX_CM_BACK_ATTACH_NO_COMMA = "?p=";


    // Location
    public static final String LOCATION_ENGCENTER_616       = "http://www.iotoasis.org/ontology/engcenter_616";
    public static final String LOCATION_CAMPUS_001          = "http://www.iotoasis.org/ontology/campus_001";

    // SDA prefix
    public static final String PREFIX_ONTOLOGY              = "http://www.iotoasis.org/ontology/";

    // SDA TEMP
    public static final String TEMP_COLD                    = "cold";
    public static final String TEMP_HOT                     = "hot";






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
