package com.pineone.icbms.so.device.logic;

import com.pineone.icbms.so.device.entity.*;
import com.pineone.icbms.so.device.store.DeviceResultStore;
import com.pineone.icbms.so.device.store.DeviceStore;
import com.pineone.icbms.so.device.store.memory.DeviceMemory;
import com.pineone.icbms.so.device.store.memory.DeviceResultMemory;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.device.util.ClientService;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class DeviceManagerLogic implements DeviceManager {

    private DeviceStore deviceStore = new DeviceMemory();
    private DeviceResultStore deviceResultStore = new DeviceResultMemory();
    private ClientService clientService = new ClientService();


    public void deviceRegister(deviceReleaseMessage deviceReleaseMessage){
        // NOTE : 디바이스 생성.

        // 디바이스 ID로 SDA에 데이터 요청.
        String responseData = deviceRequest(ClientProfile.SDA_DATAREQUEST_URI + ClientProfile.SDA_DEVICE + deviceReleaseMessage.getDeviceId());

        //데이터 변환.
        Device device = convertJsonToDevice(responseData);

        // 디바이스 저장.
        deviceCreate(device);

    }

    public void deviceRelease(String deviceId){
        deviceStore.delete(deviceId);
    }

    private void deviceCreate(Device device){
        deviceStore.create(device);
    }

    public String deviceExecute(String deviceId, String deviceService, String deviceCommand){

        String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();

        // DB에서 Device를 얻음.
        // Device device = makeDeviceData(deviceId, deviceService, deviceCommand);

        // SI를 제어할수 있는 DeviceControlMessage로 변환
        DeviceControlMessage deviceControlMessage = deviceDataConversion(deviceId,commandId,deviceCommand);

        // JsonData로 요청 메시지 변환
        String jsonString = convertObjectToJson(deviceControlMessage);

        // Device 제어 요청 보냄.
        String responseData = controlRequest(ClientProfile.SI_CONTOL_URI,jsonString);

        // Device 제어 결과 Convert
        ResultMessage resultMessage = convertJsonToObject(responseData);

        // Device 제어 결과 저장.
        controlResultsStorage(deviceId, commandId, deviceCommand, resultMessage);

        return resultMessage.get_result();
    }

    @Override
    public String deviceControlResult(ResultMessage resultMessage) {

        DeviceResult deviceResult = deviceResultStore.retrieve(resultMessage.get_commandId());

        if(deviceResult != null && deviceResult.getCommandId() != null){
            // It has been confirmed for the linked data.
            if(ClientProfile.RESPONSE_SUCCESS_CODE.equals(resultMessage.get_resultCode()) ||
                    ClientProfile.RESPONSE_SUCCESS.equals(resultMessage.get_resultCode()) ||
                    ClientProfile.RESPONSE_SUCCESS_ONEM2MCODE.equals(resultMessage.get_resultCode())){

                deviceResult.setResult2(resultMessage.get_resultCode());
                deviceResultStore.update(deviceResult);
            }
            return resultMessage.get_resultCode();
        } else {
            return "No device Control Message.";
        }
    }

    @Override
    public Device deviceSearchById(String deviceId) {
        return deviceStore.retrieveByID(deviceId);
    }

    @Override
    public List<Device> deviceSearchByLocation(String location) {
        return deviceStore.retrievceByLocation(location);
    }

    private Device makeDeviceData(String deviceId, String deviceService, String deviceCommand){
        // Device 식별자로 Device를 DB에서 끄냄.
        Device device = deviceStore.retrieveByID(deviceId);

        // Device에 RealService, RealCommand 설정.
        device.setDeviceRealService(deviceService);
        device.setDeviceRealCommand(deviceCommand);
        device.setDeviceRealCommandId(ClientProfile.SI_COMMAND_ID + System.nanoTime());

        return device;
    }


    private DeviceControlMessage deviceDataConversion(String deviceId, String commandId, String deviceCommand){
        DeviceControlMessage deviceControlMessage = new DeviceControlMessage();

        deviceControlMessage.set_uri(deviceId);
        deviceControlMessage.set_notificationUri(ClientProfile.SO_CONTROL_NOTIFICATON_URI);
        deviceControlMessage.set_commandId(commandId);
        deviceControlMessage.set_command(ClientProfile.SI_CONTROL_ACTION);
        deviceControlMessage.setCnf(ClientProfile.SO_CONTROL_TYPE);
        deviceControlMessage.setCon(deviceCommand);

        return deviceControlMessage;
    }

    private String convertObjectToJson(DeviceControlMessage deviceControlMessage){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(deviceControlMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private String controlRequest(String uri,String body){
        //
        String responseData = clientService.requestPostService(uri,body);

        return responseData;
    }

    private String deviceRequest(String uri){
        //
        String responseData = clientService.requestGetService(uri);

        return responseData;
    }

    private ResultMessage convertJsonToObject(String responseData){
        //
        ObjectMapper mapper = new ObjectMapper();
        ResultMessage resultMessage = null;
        try {
            resultMessage = mapper.readValue(responseData, ResultMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMessage;
    }

    private Device convertJsonToDevice(String responseData){
        //
        ObjectMapper mapper = new ObjectMapper();
        Device device = null;
        try {
            device = mapper.readValue(responseData, Device.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return device;
    }

    private void controlResultsStorage(String deviceId, String commandId, String deviceCommand, ResultMessage resultMessage){

        DeviceResultStore deviceResultStore = new DeviceResultMemory();

        DeviceResult deviceResult = new DeviceResult();

        deviceResult.setCommandId(commandId);
        deviceResult.setSendMessage(deviceId + deviceCommand);
        deviceResult.setDeviceUrl(deviceId);
        deviceResult.setValue(deviceCommand);
        deviceResult.setResult1(resultMessage.get_result());
        deviceResult.setResult2("");

        deviceResultStore.create(deviceResult);

    }

}
