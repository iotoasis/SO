package com.pineone.icbms.so.interfaces.si.ref;

/**
 * Created by melvin on 2016. 7. 11..
 * 플랫폼에서 사용할 주소 관리
 */
public class SIAddressStore {

    // SI Command Data
    public static final String  SI_CONTOL_URI                           = "/si/control";
    public static final String  SI_SUBSCRIPTION_URI                     = "/si/subscription/add";
    public static final String  SI_SUBSCRIPTION_RELEASE_URI             = "/si/subscription/del";
    // SI AuthIgnore Data
    public static final String  SI_AUTH_CONTOL_URI                      = "/si/newcontrol";


    // SI LWM2M Data
    public static final String  SI_LWM2M_CONTOL_URI                     = "/si/ipe/lwm2m";


    //SDA Data
    public static final String  SDA_DEVICE                  = "/device";
    public static final String  SDA_DEVICE_OPERATION        = "/operation";

    //SO Data
    public static final String SO_DEVICE_STATUS              = "/so/device/status";

    private SIAddressStore() {
        throw new IllegalAccessError("SIAddressStore class");
    }
}
