package com.pineone.icbms.so.interfaces.sda.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineone.icbms.so.interfaces.sda.handle.itf.ISdaManager;
import com.pineone.icbms.so.interfaces.sda.model.*;
import com.pineone.icbms.so.interfaces.sda.ref.DataNotExistException;
import com.pineone.icbms.so.interfaces.sda.ref.SDAException;
import com.pineone.icbms.so.interfaces.sda.ref.SdaAddressStore;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.itf.address.AddressCollector;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2017. 4. 24..
 *
 * sda 인터페이스를 처리한다.
 * 새로운 인터페이스를 작성하는 경우에는
 * 먼저 sda에서 context_model 이 만들어 지고(cm id 생성)
 * 해당 cm id 를 AddressCollector 에 등록해서 호출하는 방식으로 진행
 */

//SDA 연동 기능 구현
@Service
public class SdaManager implements ISdaManager {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    ClientService clientService = new ClientService();
    AddressCollector addressCollector = new AddressCollector();
    ObjectMapper objectMapper = new ObjectMapper();

    // 상황의 발생 여부 조회
    @Override
    public List<String> retrieveEventLocationList(String contextModelId) {
        //
        List<String> locationList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            //IHttpResponseMessage message = clientService.requestGetService(
            String message = clientService.requestGetServiceReceiveString2( 
            		addressCollector.getServerAddress(AddressCollector.SDA_SERVER) + contextModelId + SdaAddressStore.SEPARATOR_WITH_COMMA);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//            e.printStackTrace();
        }

        if (contentList == null || contentList.isEmpty()) {
            String info = "ContextModelID = " + contextModelId + " is not Happened ";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                locationList.add(contextModelContent.getLocationUri());
            }
        }
        log.debug("Location : " + locationList);
        return locationList;
    }

    // 특정 위치(location)에 존재하는 device Function 목록 조회
    @Override
    public List<String> retrieveFunctionListInLocation(String locationId) {
        //
        List<String> functionList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_FUNC_LIST_BY_LOC + SdaAddressStore.SEPARATOR_WITHOUT_COMMA
                            + locationId);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//            e.printStackTrace();
        }
        if (contentList == null || contentList.isEmpty()) {
            String info = "LocationID = " + locationId + " doesn't have Function";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                functionList.add(contextModelContent.getFunctionUri());
            }
        }
        log.debug("Function : " + functionList);
        return functionList;
    }

    // function 에 대응하는 aspect 조회, function 를 이용한 조회 지원 필요
    @Override
    public List<AspectForIf> retrieveAspectListByFunction(String functionalityId) {
        //
        List<AspectForIf> aspectList = new ArrayList<AspectForIf>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_ASPECT_LIST_BY_FUNC + SdaAddressStore.SEPARATOR_WITHOUT_COMMA
                            + functionalityId);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//            e.printStackTrace();
        }
        if (contentList == null || contentList.isEmpty()) {
            String info = "Function = " + functionalityId + " doesn't have Aspect";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                AspectForIf addItam = new AspectForIf();
                addItam.setAspect(contextModelContent.getAspectUri());
                addItam.setLabel(contextModelContent.getLabel());
                aspectList.add(addItam);
            }
        }
        log.debug("Aspect = " + aspectList);
        return aspectList;
    }

    // function, location 을 이용한 Device 목록 조회
    @Override
    public List<String> retrieveDeviceListByFunctionAndLocation(String locationId, String functionalityId) {
        //
        List<String> deviceList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_DEVICE_LIST_BY_FUNC_LOC + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            functionalityId + "," + locationId);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }
        if (contentList == null || contentList.isEmpty()) {
            String info = "DeviceList = " + locationId + " and " + functionalityId + " doesn't have Device";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        log.debug("Device = " + deviceList);
        return deviceList;
    }

    // function 를 이용한 Device 목록 조회
    @Override
    public List<String> retrieveDeviceListByFunction(String functionalityId) {
        //
        List<String> deviceList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_DEVICE_LIST_BY_FUNC + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            functionalityId);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }
        if (contentList == null || contentList.isEmpty()) {
            String info = "DeviceList = " + functionalityId + " doesn't have Device";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        log.debug("Device = " + deviceList);
        return deviceList;
    }

    // location 울 이용한 Device 목록 조회
    @Override
    public List<String> retrieveDeviceListByLocation(String locationId) {
        //
        List<String> deviceList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_DEVICE_LIST_BY_LOC + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            locationId);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }

        if (contentList == null || contentList.isEmpty()) {
            String info = "DeviceList = " + locationId + " doesn't have Device";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        log.debug("Device = " + deviceList);
        return deviceList;
    }

    // Sensor Status (측정값) 조회
    @Override
    public String retrieveSensorValue(String deviceId) {
        //
        List<ContextModelContent> contentList = new ArrayList<>();
        String value = null;
        try{
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_LATEST_VALUE_BY_DEV + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            deviceId);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }
        if(contentList == null || contentList.isEmpty()){
            String info = "Device = " + deviceId + "doesn't have Value";
            log.debug("info : " + info);
        } else {
            for(ContextModelContent contextModelContent : contentList){
                value = contextModelContent.getValue();
            }
        }
        log.debug("value : " + value);
        return value;
    }

    @Override
    public List<String> retrieveListByContextModelId(String contextModelId) {
        //
        List<String> resultList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER)
                            + contextModelId
                            + SdaAddressStore.SEPARATOR_WITH_COMMA);
            log.debug("Request URL : {}", addressCollector.getServerAddress(AddressCollector.SDA_SERVER)
                    + contextModelId
                    + SdaAddressStore.SEPARATOR_WITH_COMMA);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//            e.printStackTrace();
        }

        for (ContextModelContent contextModelContent : contentList) {
            switch (contextModelId) {
                case SdaAddressStore.CM_FUNCTION_LIST:
                    resultList.add(contextModelContent.getFunctionUri());
                    break;
                case SdaAddressStore.CM_ASPECT_LIST:
                    resultList.add(contextModelContent.getAspectUri());
                    break;
                case SdaAddressStore.CM_FUNCTIONALITY_LIST:
                    resultList.add(contextModelContent.getAspectUri());
                    break;
                default:
                    log.debug("ContextModelContent is empty");
            }

        }
        log.debug("resultList : " + resultList);

        return resultList;
    }

    @Override
    public List<AspectForIf> retrieveAspectList() {
        //
        List<AspectForIf> list = new ArrayList<AspectForIf>();
        List<ContextModelContent> contentList = new ArrayList<>();
    
        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_ASPECT_LIST + SdaAddressStore.SEPARATOR_WITH_COMMA);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//            e.printStackTrace();
        }
    
        for (ContextModelContent contextModelContent : contentList) {
            AspectForIf addItam = new AspectForIf();
            addItam.setAspect(contextModelContent.getAspectUri());
            addItam.setLabel(contextModelContent.getLabel());
            list.add(addItam);
        }
        log.debug("Aspect : " + list);
        return list;
    }

    @Override
    public List<FunctionForIf> retrieveFunctionList() {
        //
        List<FunctionForIf> functionList = new ArrayList<FunctionForIf>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_FUNCTION_LIST + SdaAddressStore.SEPARATOR_WITH_COMMA);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//            e.printStackTrace();
        }

        for (ContextModelContent contextModelContent : contentList) {
            FunctionForIf addItam = new FunctionForIf();
            addItam.setFunction(contextModelContent.getFunctionUri());
            addItam.setLabel(contextModelContent.getLabel());
            functionList.add(addItam);

        }
        log.debug("Function : " + functionList);
        return functionList;
    }

    
    /*
    public static final String CM_DD_DEVICE_LIST = "cm-dd-device-list"; 				//1) by location, aspect, functionality 
    public static final String CM_DD_DEVICETYPE_LIST = "cm-dd-devicetype-device-list";  //2) by location, deviceType, aspect, functionality 
    public static final String CM_DD_COMMAND_VALUE = "cm-dd-command-value"; 			//3) by id, aspect, cmd 
    public static final String CM_DD_ASPECT_ACTION_VALUE = "cm-dd-aspect-action-value"; //4) by id, aspect, functionality 
 */
    // 1)cm-dd-device-list(Loc,Aspect,Func) 을 이용한 Device 목록 조회
    @Override
    public List<String> getDeviceListByLoc_Aspect_Func(String locationUri, String aspectUri, String functionalityUri) {
        //
        List<String> deviceList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_DD_DEVICE_LIST + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            locationUri + "," + aspectUri + "," + functionalityUri);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }
        if (contentList == null || contentList.isEmpty()) {
            String info = "DeviceList = " + locationUri + " and " + aspectUri + " and " + functionalityUri + " doesn't have Device";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        log.debug("Device = " + deviceList);
        return deviceList;
    }
    
    // 2)cm-dd-devicetype-device-list(Loc, DeviceType, Aspect, Func) 을 이용한 Device 목록 조회
    // public static final String CM_DD_DEVICETYPE_LIST = "cm-dd-devicetype-device-list";  //2) by location, deviceType, aspect, functionality
    @Override
    public List<String> getDeviceListByLoc_DeviceType_Aspect_Func(String locationUri, String deviceType, String aspectUri, String functionalityUri) {
        //
        List<String> deviceList = new ArrayList<>();
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_DD_DEVICETYPE_LIST + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            locationUri + "," + deviceType + "," + aspectUri + ","+ functionalityUri);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }
        if (contentList == null || contentList.isEmpty()) {
            String info = "DeviceList = " + locationUri + ", " + deviceType + ", " + aspectUri+ ", " + functionalityUri + " doesn't have Device";
            log.debug("info : " + info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        log.debug("Device = " + deviceList);
        return deviceList;
    }

    // 3)cm-dd-command-value(DeviceId, Aspect, cmd) 을 이용한 Command Value 조회
    // public static final String CM_DD_COMMAND_VALUE = "cm-dd-command-value"; 			//3) by id, aspect, cmd
    @Override
    public String getCommandValueById_Aspect_Command(String deviceId, String aspectUri, String command) {
        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_DD_COMMAND_VALUE + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            deviceId + "," + aspectUri + ","+ command);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }

        String value = null;
        if (contentList == null || contentList.isEmpty()) {
            String info = "CommandValue = " + deviceId + ", " + aspectUri+ ", " + command + " doesn't have Device";
            log.debug("info : " + info);
        } else {
        	value = contentList.get(0).getCommandValue();
        }
        log.debug("CommandValue = " + value);
        return value;
    }

    // 4)cm-dd-aspect-action-value (id, aspect, functionality) 을 이용한 aspect Value 조회
    // public static final String CM_DD_ASPECT_ACTION_VALUE = "cm-dd-aspect-action-value"; //4) by id, aspect, functionality
    @Override
    public ContextModelContent getAspectValueById_Aspect_Function(String deviceId, String aspectUri, String functionUri) {

        List<ContextModelContent> contentList = new ArrayList<>();

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_DD_ASPECT_ACTION_VALUE + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            deviceId + "," + aspectUri + ","+ functionUri);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }
        if (contentList == null || contentList.isEmpty()) {
            String info = "aspectValue = " + deviceId + ", " + aspectUri+ ", " + functionUri + " doesn't have Device";
            log.debug("info : " + info);
            return null;
        }
        ContextModelContent content = contentList.get(0);
    	log.debug("aspectValue = " + content.getAspectValue());
    	return content;
    }

    // get Data
    private List<ContextModelContent> getContextModelContents(IHttpResponseMessage message) throws IOException, DataNotExistException {
        if (message.getStatusCode() == 200) {
//            System.out.println(message.getBodyByteArray().toString());
//            System.out.println("ResponseMessage : " + message);
            String readData = clientService.responseDataToString(message);
            log.debug("Message : " + readData);
            ContextModelForIf2 retrieveContextData = objectMapper.readValue(readData, ContextModelForIf2.class);
            return retrieveContextData.getContextModelContentList();
        } else {
            throw new DataNotExistException();
        }
    }

    // get Data
    private List<ContextModelContent> getContextModelContents(String message) throws IOException, DataNotExistException {
        if (message != null) {
//            System.out.println(message.getBodyByteArray().toString());
//            System.out.println("ResponseMessage : " + message);
            log.debug("Message : " + message);
            ContextModelForIf2 retrieveContextData = objectMapper.readValue(message, ContextModelForIf2.class);
            return retrieveContextData.getContextModelContentList();
        } else {
            throw new DataNotExistException();
        }
    }
}
