package com.pineone.icbms.so.interfaces.sda.handle.itf;

import com.pineone.icbms.so.interfaces.sda.model.AspectForIf;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelContent;
import com.pineone.icbms.so.interfaces.sda.model.FunctionForIf;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 24..
 */
public interface ISdaManager {

    // 상황 조회시 사용 (only Location)
    List<String> retrieveEventLocationList(String contextModelId);

    // 상황 조회시 사용 (Location, Device, LocationList)
	List<ContextModelContent> retrieveEventList(String contextModelId);
/*
	// 특정 위치(location)에 존재하는 device Function 목록 조회
    List<String> retrieveFunctionListInLocation(String locationId);

    // function 에 대응하는 aspect 조회, function 를 이용한 조회 지원 필요
    List<AspectForIf> retrieveAspectListByFunction(String functionalityId);

    // function, location 을 이용한 Device 목록 조회
    List<String> retrieveDeviceListByFunctionAndLocation(String locationId, String functionalityId);

    // function 를 이용한 Device 목록 조회
    List<String> retrieveDeviceListByFunction(String functionalityId);

    // location 울 이용한 Device 목록 조회
    List<String> retrieveDeviceListByLocation(String locationId);

    // Sensor Status (측정값) 조회
    String retrieveSensorValue(String deviceId);

    // 전체 펑션 목록 
    List<String> retrieveListByContextModelId(String contextModeId);

 */
    // 전체 aspect 목록 
    List<AspectForIf> retrieveAspectList();

    // 전체 펑션 목록 
    List<FunctionForIf> retrieveFunctionList();

    //1)cm-dd-device-list(Loc,Aspect,Func) 을 이용한 Device 목록 조회
	List<String> getDeviceListByLoc_Aspect_Func(String locationUri, String aspectUri, String functionalityUri);

    // 2)cm-dd-devicetype-device-list(Loc, DeviceType, Aspect, Func) 을 이용한 Device 목록 조회
	List<String> getDeviceListByLoc_DeviceType_Aspect_Func(String locationUri, String deviceType, String aspectUri,
			String functionalityUri);

	// 3)cm-dd-command-value(DeviceId, Aspect, cmd) 을 이용한 Command Value 조회
	String getCommandValueById_Aspect_Command(String deviceId, String aspectUri, String command);

	// 4)cm-dd-aspect-action-value (id, aspect, functionality) 을 이용한 aspect Value 조회
	ContextModelContent getAspectValueById_Aspect_Function(String deviceId, String aspectUri, String functionUri);

    // 5)cm-dd-measuring-loc_dtype_aspect (Loc, Type, Aspect)을 이용한 Measuring 
	List<ContextModelContent> getMeasuringValueByLocDeviceTypeAspect(String locationUri, String deviceType, String aspectUri);

    // 6) cm-dd-measuring-loc_aspect (Loc, Aspect)을 이용한 Measuring 
	List<ContextModelContent> getMeasuringValueByLocAspect(String locationUri, String aspectUri);

	// 7) cm-dd-measuring-devid_aspect (devId, Aspect)을 이용한 Measuring 
	List<ContextModelContent> getMeasuringValueByDevIdAspect(String devUri, String aspectUri);

}
