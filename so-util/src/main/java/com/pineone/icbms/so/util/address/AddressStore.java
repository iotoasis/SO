package com.pineone.icbms.so.util.address;

/**
 * Created by melvin on 2016. 7. 11..
 * NOTE: 플랫폼에서 사용할 주소 관리
 */
public class AddressStore {
    public static final String REQUIRE_CONTEXTINFORMATION = "/requirement/contextinformation";
    public static final String RETRIEVE_CONCEPTSERVICE = "/inquiry/conceptservice/{deviceobject}";
    public static final String REGISTER_CONTEXTINFORMATION = "registration/contextinformation";
    public static final String RETRIEVE_CONTEXTINFORMATION = "inquiry/contextinformation";
    public static final String RETRIEVE_CONTEXTINFORMATION_DETAIL = "/inquiry/contextinformation/{contextname}"; //
    public static final String RETRIEVE_DOMAIN = "/inquiry/domain";
    public static final String REGISTER_CONTEXTMODEL = "registration/contextmodel";
    public static final String RETRIEVE_CONTEXTMODEL = "inquiry/contextmodel";
    public static final String RETRIEVE_CONTEXTMODEL_DETAIL = "/inquiry/contextmodel/{contextmodelname}";
    public static final String REQUIRE_CONTEXTMODEL = "/requirement/contextmodel";
    public static final String RETRIEVE_CONTEXTMODEL_EVENT = "inquiry/contextmodel/event";
    public static final String RETRIEVE_CURRENT_VALUE = "inquiry/currentvalue";
    public static final String RETRIEVE_PAST_VALUE = "inquiry/pastvalue";
    public static final String RETRIEVE_USE_AMOUNT = "inquiry/use/amount";
    public static final String RETRIEVE_OBJECT_VALUE = "inquiry/object/value";
    public static final String RETRIEVE_CURRENT_PC="inquiry/current/pc";
    public static final String RETRIEVE_NEXT_PC="inquiry/next/pc";
    public static final String RETRIEVE_CURRENT_MOUSE="inquiry/current/mouse";
    public static final String RETRIEVE_NEXT_MOUSE="inquiry/next/mouse";
    public static final String RETRIEVE_CURRENT_KEYBOARD="inquiry/current/keyboard";
    public static final String RETRIEVE_NEXT_KEYBOARD="inquiry/next/keyboard";

    // SI Command Data
    public static final String  SI_CONTOL_URI               = "/si/control";
    public static final String  SI_SUBSCRIPTION_URI         = "/si/subscription/add";
    public static final String  SI_SUBSCRIPTION_RELEASE_URI = "/si/subscription/del";

    //SDA Data
    public static final String  SDA_DEVICE                  = "/device";
    public static final String  SDA_DEVICE_OPERATION        = "/operation";

    //SO Data
    public static final String SO_DEVICE_STATUS              = "/so/device/status";

    private AddressStore() {
        throw new IllegalAccessError("AddressStore class");
    }
}
