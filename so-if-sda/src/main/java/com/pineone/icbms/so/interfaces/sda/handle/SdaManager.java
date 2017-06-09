package com.pineone.icbms.so.interfaces.sda.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineone.icbms.so.interfaces.sda.handle.itf.ISdaManager;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf2;
import com.pineone.icbms.so.interfaces.sda.model.ContextInformationForIf;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelContent;
import com.pineone.icbms.so.interfaces.sda.ref.DataNotExistException;
import com.pineone.icbms.so.interfaces.sda.ref.SDAException;
import com.pineone.icbms.so.interfaces.sda.ref.SdaAddressStore;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.itf.address.AddressCollector;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2017. 4. 24..
 */

//SDA 연동 기능 구현
public class SdaManager implements ISdaManager {


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
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) + contextModelId + SdaAddressStore.SEPARATOR_WITH_COMMA);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//            e.printStackTrace();
        }

        if (contentList == null || contentList.isEmpty()) {
            String info = "ContextModelID = " + contextModelId + " is not Happened ";
            System.out.println(info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                locationList.add(contextModelContent.getLocationUri());
            }
        }
        System.out.println("Location : " + locationList);
        return locationList;
    }

    // 특정 위치(location)에 존재하는 device Functionality 목록 조회
    @Override
    public List<String> retrieveFunctionalityListInLocation(String locationId) {
        //
        List<String> functionalityList = new ArrayList<>();
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
            String info = "LocationID = " + locationId + " doesn't have Functionality";
            System.out.println(info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                functionalityList.add(contextModelContent.getFunctionalityUri());
            }
        }
        System.out.println("Functionality : " + functionalityList);
        return functionalityList;
    }

    // functionality 에 대응하는 aspect 조회, functionality 를 이용한 조회 지원 필요
    @Override
    public List<String> retrieveAspectListByFunctionality(String functionalityId) {
        //
        List<String> aspectList = new ArrayList<>();
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
            String info = "Functionality = " + functionalityId + " doesn't have Aspect";
            System.out.println(info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                aspectList.add(contextModelContent.getAspectUri());
            }
        }
        System.out.println("Aspect = " + aspectList);
        return aspectList;
    }


    // functionality, location 을 이용한 Device 목록 조회
    @Override
    public List<String> retrieveDeviceListByFunctionalityAndLocation(String locationId, String functionalityId) {
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
            System.out.println(info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        System.out.println("Device = " + deviceList);
        return deviceList;
    }

    // functionality 를 이용한 Device 목록 조회
    @Override
    public List<String> retrieveDeviceListByFunctionality(String functionalityId) {
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
            System.out.println(info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        System.out.println("Device = " + deviceList);
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
            System.out.println(info);
        } else {
            for (ContextModelContent contextModelContent : contentList) {
                deviceList.add(contextModelContent.getDeviceUri());
            }
        }
        System.out.println("Device = " + deviceList);
        return deviceList;
    }

    // CM 내 CI 목록 조회
    @Override
    public List<ContextInformationForIf> retrieveContextInformationList(String contextModeId) {
        //
        List<ContextInformationForIf> contextInformationList = new ArrayList<>();
        List<ContextModelContent> contentList = null;

        try {
            IHttpResponseMessage message = clientService.requestGetService(
                    addressCollector.getServerAddress(AddressCollector.SDA_SERVER) +
                            SdaAddressStore.CM_CI_LIST_BY_CM + SdaAddressStore.SEPARATOR_WITHOUT_COMMA +
                            contextModeId);
            contentList = getContextModelContents(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SDAException e) {
//       e.printStackTrace();
        }
        if(contentList == null || contentList.isEmpty()){
            String info = "ContextModel = " + contextModeId + "doesn't have ContextInformation";
            System.out.println(info);
        } else {
            for(ContextModelContent contextModelContent : contentList){
                ContextInformationForIf contextInformationForIf = new ContextInformationForIf();
                contextInformationForIf.setNumber(contextModelContent.getCi_sequence_number());
                contextInformationForIf.setId(contextModelContent.getCi_id());
                contextInformationForIf.setName(contextModelContent.getCi_name());
                contextInformationList.add(contextInformationForIf);
            }
            return contextInformationList;
        }
        return contextInformationList;
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
            System.out.println(info);
        } else {
            for(ContextModelContent contextModelContent : contentList){
                value = contextModelContent.getValue();
            }
        }
        System.out.println(value);
        return value;
    }

    // get Data
    private List<ContextModelContent> getContextModelContents(IHttpResponseMessage message) throws IOException, DataNotExistException {
        if (message.getStatusCode() == 200) {
//            System.out.println(message.getBodyByteArray().toString());
//            System.out.println("ResponseMessage : " + message);
            String readData = clientService.responseDataToString(message);
            System.out.println("Message : " + readData);
            ContextModelForIf2 retrieveContextData = objectMapper.readValue(readData, ContextModelForIf2.class);
            return retrieveContextData.getContextModelContentList();
        } else {
            throw new DataNotExistException();
        }
    }
}
