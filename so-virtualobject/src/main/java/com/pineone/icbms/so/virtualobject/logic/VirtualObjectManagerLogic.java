package com.pineone.icbms.so.virtualobject.logic;

import com.pineone.icbms.so.device.logic.DeviceManager;
import com.pineone.icbms.so.device.pr.DevicePresentation;
import com.pineone.icbms.so.virtualobject.entity.ServiceControl;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.proxy.VirtualObjectControlProxy;
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
    DevicePresentation devicePresentation;

    @Autowired
    VirtualObjectStore virtualObjectStore;

    @Autowired
    VirtualObjectProxy virtualObjectProxy;

    @Autowired
    VirtualObjectControlProxy virtualObjectControlProxy;

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
//        return deviceManager.deviceExecute(deviceId, operation);
        return virtualObjectControlProxy.executeDevice(deviceId, operation);
    }

    @Override
    public void produceVirtualObject(VirtualObject virtualObject) {

        // VirtualObject의 Functionality 요청
        //String responseData = requestFunctionality(virtualObject);
        // TODO : SDA 협의 후 해당 부분 수정.
        // VirtualObject의 Functionality 추가 설정
        //virtualObject.setFunctionality(responseData);

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

    @Override
    public void testSetUp() {
        SetUpData();
    }

    private void SetUpData(){
        VirtualObject virtualObject = new VirtualObject();

        virtualObject.setVoId("CR0001AirCleaner0001");
        virtualObject.setVoName("공기청정기 VO");
        virtualObject.setFunctionality("switch-control");
        virtualObject.setVoDescription("강의실 공기청정기 제어 서비스");
        virtualObject.setVoCreateTime("201608250930");
        virtualObject.setVoExpiredTime("201708250930");
        virtualObject.setDeviceService("aircleaner-power-switch-control-service");
        virtualObject.setDeviceId("/herit-in/herit-cse/Device_AirCleaner_001");
        virtualObject.setVoCommand("ON");
        virtualObject.setVoLocation("CLASSROOM001");

        produceVirtualObject(virtualObject);

        produceVirtualObject(new VirtualObject("CR0001Blinder0001", "블라인드 VO", "switch-control", "블라인드를 관리 하는 서비스", "201608250930", "201708250930", "blind-power-switch-control-service", "/herit-in/herit-cse/Blinder_CR0001BL0001", "ON", "CLASSROOM001"));
        produceVirtualObject(new VirtualObject("CR0001Illumination0001", "조명 VO", "switch-control", "조명 전원을 관리 하는 서비스", "201608250930", "201708250930", "smartlight-power-switch-control-service", "/herit-in/herit-cse/Illumination_CR0001IL0001", "OFF", "CLASSROOM001"));
        produceVirtualObject(new VirtualObject("CR0001BeamProjector0001", "빔프로젝터 VO", "switch-control", "빔 프로젝터 관리 하는 서비스", "201608250930", "201708250930", "beamprojector-power-switch-control-service", "/herit-in/herit-cse/BeamProjector_CR0001BP0001", "ON", "CLASSROOM001"));
        produceVirtualObject(new VirtualObject("CR0001BeamProjectorScreen0001", "빔스크린 VO", "switch-control", "빔 스크린을 관리 하는 서비스", "201608250930", "201708250930", "beamscreen-power-switch-control-service", "/herit-in/herit-cse/BeamProjectorScreen_CR0001BS0001", "ON", "CLASSROOM001"));
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
