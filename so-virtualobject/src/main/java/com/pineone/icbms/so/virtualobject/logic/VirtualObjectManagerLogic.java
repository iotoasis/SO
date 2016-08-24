package com.pineone.icbms.so.virtualobject.logic;

import com.pineone.icbms.so.device.logic.DeviceManager;
import com.pineone.icbms.so.virtualobject.entity.ServiceControl;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.proxy.VirtualObjectProxy;
import com.pineone.icbms.so.virtualobject.store.VirtualObjectStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualObjectManagerLogic implements VirtualObjectManager {

    @Autowired
    DeviceManager deviceManager;

    @Autowired
    VirtualObjectStore virtualObjectStore;

    @Autowired
    VirtualObjectProxy virtualObjectProxy;

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
        return virtualObjectStore.retrieveByLocation(location);
    }

    @Override
    public String requestControlDevice(String voId, String operation) {
        // DB에서 VO를 검색
        VirtualObject virtualObject = searchVirtualObject(voId);

        // 해당 Device ID를 도출
        String deviceId = virtualObject.getDeviceId();
    // operation -> command SDA 확인.고려.. 형식으로 변경.
        return deviceManager.deviceExecute(deviceId, operation);
    }

    @Override
    public void produceVirtualObject(VirtualObject virtualObject) {

        // VirtualObjectdml Functionality 요청
        String responseData = requestFunctionality(virtualObject);

        // VirtualObject의 Functionality 추가 설정
        virtualObject.setFunctionality(responseData);

        // VirtualObject 저장
        saveVirtualDevice(virtualObject);

    }

    @Override
    public String controlDevice(List<ServiceControl> serviceControls) {
        // DB에서 domain과 VOService로 해당 VO들 조회.
        for(ServiceControl control : serviceControls){
            List<VirtualObject> virtualObjects = virtualObjectStore.retrieveByLocationAndService(control.getDomain(),control.getVoService());
            // DB에서 조회된 VO 실행.
            for(VirtualObject vo : virtualObjects){
                deviceManager.deviceExecute(vo.getDeviceId(),control.getOperation());
            }
        }

        //VO 제어
        return null;
    }

    private void saveVirtualDevice(VirtualObject virtualObject){
        virtualObjectStore.create(virtualObject);
    }

    private String requestFunctionality(VirtualObject virtualObject){
        String responseData = virtualObjectProxy.findFunctionality(virtualObject.getDeviceId(),virtualObject.getDeviceService());

        // 컨트롤 프록시
        // 정보 수접 프록시.
        return responseData;
    }
}
