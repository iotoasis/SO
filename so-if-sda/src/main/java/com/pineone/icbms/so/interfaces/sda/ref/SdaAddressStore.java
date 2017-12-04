package com.pineone.icbms.so.interfaces.sda.ref;

/**
 * SDA Interface 주소 저장
 *
 * Created by melvin on 2017. 4. 24..
 */
public class SdaAddressStore {

    public static final String SEPARATOR_WITH_COMMA = "/?p=,";
    public static final String SEPARATOR_WITHOUT_COMMA = "/?p=";

    /* /sda/ctx/cm-func-list-by-loc/?p=지역uri, */
    public static final String CM_FUNC_LIST_BY_LOC = "cm-func-list-by-loc";

    /* /sda/ctx/cm-aspect-list-by-func/?p=펑션uri, */
    //functionality을 조건으로 aspect 목록 조회, 정각1시에 데이타 수집
    public static final String CM_ASPECT_LIST_BY_FUNC = "cm-aspect-list-by-func";  
    

    
    public static final String CM_DD_DEVICE_LIST = "cm-dd-device-list"; 				//1) by location, aspect, functionality 
    public static final String CM_DD_DEVICETYPE_LIST = "cm-dd-devicetype-device-list";  //2) by location, deviceType, aspect, functionality 
    public static final String CM_DD_COMMAND_VALUE = "cm-dd-command-value"; 			//3) by id, aspect, cmd 
    public static final String CM_DD_ASPECT_ACTION_VALUE = "cm-dd-aspect-action-value"; //4) by id, aspect, functionality 
    
    public static final String CM_DEVICE_LIST_BY_FUNC_LOC = "cm-device-list-by-function-loc"; //x
    public static final String CM_DEVICE_LIST_BY_FUNC = "cm-device-list-by-function"; //x

    public static final String CM_DEVICE_LIST_BY_LOC = "cm-device-list-by-loc";
    public static final String CM_CI_LIST_BY_CM = "cm-ci-list-by-cm";
    public static final String CM_LATEST_VALUE_BY_DEV = "cm-latest-value-by-device";

    /* 전체 펑션 목록 */
    public static final String CM_FUNCTION_LIST = "cm-function-list"; //정각1시에 데이타 수집
    /* 전체 aspect 목록 */
    public static final String CM_ASPECT_LIST = "cm-aspect-list";
    /* 전체 펑셔널리티 목록 */
    public static final String CM_FUNCTIONALITY_LIST = "cm-functionality-list";

}
