package com.pineone.icbms.so.virtualobject.pr;


import com.pineone.icbms.so.device.pr.DevicePresentation;
import com.pineone.icbms.so.virtualobject.VirtualObjectApplication;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.logic.VirtualObjectManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = VirtualObjectApplication.class)
public class VirtualObjectPresentationTest {

    @Autowired
    DevicePresentation devicePresentation;

    @Autowired
    VirtualObjectManager virtualObjectManager;

    @Before
    public void VO등록() throws Exception{
        //
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

        virtualObjectManager.produceVirtualObject(virtualObject);

        virtualObjectManager.produceVirtualObject(new VirtualObject("CR0001Blinder0001", "블라인드 VO", "switch-control", "블라인드를 관리 하는 서비스", "201608250930", "201708250930", "blind-power-switch-control-service", "/herit-in/herit-cse/Blinder_CR0001BL0001", "ON", "CLASSROOM001"));
        virtualObjectManager.produceVirtualObject(new VirtualObject("CR0001Illumination0001", "조명 VO", "switch-control", "조명 전원을 관리 하는 서비스", "201608250930", "201708250930", "smartlight-power-switch-control-service", "/herit-in/herit-cse/Illumination_CR0001IL0001", "OFF", "CLASSROOM001"));
        virtualObjectManager.produceVirtualObject(new VirtualObject("CR0001BeamProjector0001", "빔프로젝터 VO", "switch-control", "빔 프로젝터 관리 하는 서비스", "201608250930", "201708250930", "beamprojector-power-switch-control-service", "/herit-in/herit-cse/BeamProjector_CR0001BP0001", "ON", "CLASSROOM001"));
        virtualObjectManager.produceVirtualObject(new VirtualObject("CR0001BeamProjectorScreen0001", "빔스크린 VO", "switch-control", "빔 스크린을 관리 하는 서비스", "201608250930", "201708250930", "beamscreen-power-switch-control-service", "/herit-in/herit-cse/BeamProjectorScreen_CR0001BS0001", "ON", "CLASSROOM001"));


    }

}
