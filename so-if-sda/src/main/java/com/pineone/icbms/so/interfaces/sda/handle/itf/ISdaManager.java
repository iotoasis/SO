package com.pineone.icbms.so.interfaces.sda.handle.itf;

import com.pineone.icbms.so.interfaces.sda.model.ContextInformationForIf;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 24..
 */
public interface ISdaManager {

    // 상황 조회시 사용
    List<String> retrieveEventLocationList(String contextModelId);

    // 특정 위치(location)에 존재하는 device Function 목록 조회
    List<String> retrieveFunctionListInLocation(String locationId);

    // function 에 대응하는 aspect 조회, function 를 이용한 조회 지원 필요
    List<String> retrieveAspectListByFunction(String functionId);

    // function, location 을 이용한 Device 목록 조회
    List<String> retrieveDeviceListByFunctionAndLocation(String locationId, String functionId);

    // function 를 이용한 Device 목록 조회
    List<String> retrieveDeviceListByFunction(String functionId);

    // location 울 이용한 Device 목록 조회
    List<String> retrieveDeviceListByLocation(String locationId);

    // CM 내 CI 목록 조회
    List<ContextInformationForIf> retrieveContextInformationList(String contextModeId);

    // Sensor Status (측정값) 조회
    String retrieveSensorValue(String deviceId);

    /* 전체 펑션 목록 */
    List<String> retrieveListByContextModelId(String contextModeId);

    /* 전체 aspect 목록 */
    List<String> retrieveAspectList();

    /* 전체 펑셔널리티 목록 */
    List<String> retrieveFunctionalityList();

}
