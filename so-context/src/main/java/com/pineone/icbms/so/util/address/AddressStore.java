package com.pineone.icbms.so.util.address;

/**
 * Created by melvin on 2016. 7. 11..
 * NOTE: 플랫폼에서 사용할 주소 관리
 */
public class AddressStore {
    public static final String REQUIRE_CONTEXTINFORMATION = "requirement/contextinformation";
    public static final String RETRIEVE_CONCEPTSERVICE = "inquiry/conceptservice/{deviceobject}";
    public static final String REGISTER_CONTEXTINFORMATION = "registration/contextinformation";
    public static final String RETRIEVE_CONTEXTINFORMATION = "inquiry/contextinformation";
    public static final String RETRIEVE_CONTEXTINFORMATION_DETAIL = "inquiry/contextinformation/{contextname}"; //
    public static final String RETRIEVE_DOMAIN = "inquiry/domain";
    public static final String REGISTER_CONTEXTMODEL = "registration/contextmodel";
    public static final String RETRIEVE_CONTEXTMODEL = "inquiry/contextmodel";
    public static final String RETRIEVE_CONTEXTMODEL_DETAIL = "inquiry/contextmodel/{contextmodelname}";
    public static final String REQUIRE_CONTEXTMODEL = "requirement/contextmodel";
    public static final String RETRIEVE_CONTEXTMODEL_EVENT = "inquiry/contextmodel/event";
}
