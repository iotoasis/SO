package com.pineone.icbms.so.virtualobject.logic;

import com.pineone.icbms.so.device.logic.DeviceManager;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.device.util.ClientService;
import com.pineone.icbms.so.virtualobject.entity.ExternalVirtulaObject;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.store.VirtualObjectStore;
import com.pineone.icbms.so.virtualobject.store.memory.VirtualObjectMemory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VirtualObjectManagerLogic implements VirtualObjectManager {

    @Autowired
    DeviceManager deviceManager;

    ClientService clientService = new ClientService();

    VirtualObjectStore virtualObjectStore = VirtualObjectMemory.getInstance();

    @Override
    public VirtualObject searchVirtualObject(String id) {
        return virtualObjectStore.retrieveByID(id);
    }

    @Override
    public void deleteVirtualObject(String id) {
        virtualObjectStore.delete(id);
    }

    @Override
    public List<VirtualObject> searchVirtualObjectList(String location) {
        return virtualObjectStore.retrievceByLocation(location);
    }

    @Override
    public String controlDevice(String deviceId, String operation) {
        return deviceManager.deviceExecute(deviceId, operation);
    }

    @Override
    public void produceVirtualObject(ExternalVirtulaObject eVirtulaObject) {

        // VirtualObject 생성
        VirtualObject virtualObject = virtualObjectMapping(eVirtulaObject);

        // VirtualObjectdml Functionality 요청
        String responseData = requestFunctionality(virtualObject);

        // VirtualObject의 Functionality 추가 설정
        virtualObject.setFunctionality(responseData);

        // VirtualObject 저장
        saveVirtualDevice(virtualObject);

    }

    private VirtualObject virtualObjectMapping(ExternalVirtulaObject eVirtulaObject)
    {
        VirtualObject virtualObject = new VirtualObject();
        virtualObject.setVoId(eVirtulaObject.getVoId());
        virtualObject.setDeviceId(eVirtulaObject.getDeviceId());
        virtualObject.setDeviceService(eVirtulaObject.getDeviceService());
        virtualObject.setFunctionality(eVirtulaObject.getFunctionality());
        virtualObject.setVoCommand(eVirtulaObject.getVoCommand());
        virtualObject.setVoCreateTime(eVirtulaObject.getVoCreateTime());
        virtualObject.setVoExfiredTime(eVirtulaObject.getVoExfiredTime());
        virtualObject.setVoDiscription(eVirtulaObject.getVoDiscription());
        virtualObject.setVoName(eVirtulaObject.getVoName());
        return virtualObject;
    }

    private void saveVirtualDevice(VirtualObject virtualObject){
        virtualObjectStore.create(virtualObject);
    }

    private String requestFunctionality(VirtualObject virtualObject){
        String requestUri = ClientProfile.SDA_DATAREQUEST_URI + ClientProfile.SDA_DEVICE;

        JSONObject obj = new JSONObject();
        obj.put("deviceId", virtualObject.getDeviceId());
        obj.put("deviceService", virtualObject.getDeviceService());
        String requestData = obj.toString();

        String responseData = clientService.requestPostService(requestUri,requestData);
        return responseData;
    }
}
