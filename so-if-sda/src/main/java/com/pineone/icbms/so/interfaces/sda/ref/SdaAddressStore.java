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
    public static final String CM_ASPECT_LIST_BY_FUNC = "cm-aspect-list-by-func";
    public static final String CM_DEVICE_LIST_BY_FUNC_LOC = "cm-device-list-by-function-loc";
    public static final String CM_DEVICE_LIST_BY_FUNC = "cm-device-list-by-function";
    public static final String CM_DEVICE_LIST_BY_LOC = "cm-device-list-by-loc";
    public static final String CM_CI_LIST_BY_CM = "cm-ci-list-by-cm";
    public static final String CM_LATEST_VALUE_BY_DEV = "cm-latest-value-by-device";

    /* 전체 펑션 목록 */
    public static final String CM_FUNCTION_LIST = "cm-function-list";
    /* 전체 aspect 목록 */
    public static final String CM_ASPECT_LIST = "cm-aspect-list";
    /* 전체 펑셔널리티 목록 */
    public static final String CM_FUNCTIONALITY_LIST = "cm-functionality-list";

}
