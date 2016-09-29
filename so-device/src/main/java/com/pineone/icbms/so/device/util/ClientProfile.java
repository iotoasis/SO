package com.pineone.icbms.so.device.util;

public class ClientProfile {

    // SI Command Data
    public static final String	SO_CONTROL_NOTIFICATON_URI	= "http://219.248.137.7:10080/so/device/resources/dcm";
    public static final String	SO_CONTROL_TYPE				= "text/plain:0";
    public static final String  SI_CONTROL_ACTION           = "Power";
    public static final String  SI_CONTOL_URI               = "http://166.104.112.34:8081/si/control";
    public static final String  SI_COMMAND_ID               = "cmd_";

    public static final String SO_DEVICE_STATUS_URI         = "http://166.104.112.42:10080/so/device/status";


    //SDA Data
    public static final String  SDA_DATAREQUEST_URI         = "http://oasia.org/sda/hanyang";
    public static final String  SDA_DEVICE                  = "/device";
    public static final String  SDA_DEVICE_OPERATION        = "/operation";

    // Presentation Data
    public static final String	RESPONSE_SUCCESS			= "success";
    public static final String	RESPONSE_FAILURE			= "failure";
    public static final String	RESPONSE_SUCCESS_CODE		= "200";
    public static final String	RESPONSE_FIALURE_CODE		= "4000";
    public static final String	RESPONSE_SUCCESS_ONEM2MCODE	= "2000";


}
