package com.pineone.icbms.so.device.pr;

import com.pineone.icbms.so.device.DeviceApplication;
import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.entity.DeviceResult;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.entity.deviceReleaseMessage;
import com.pineone.icbms.so.device.store.DeviceResultStore;
import com.pineone.icbms.so.device.store.DeviceStore;
import com.pineone.icbms.so.device.util.ClientProfile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DeviceApplication.class)
@WebAppConfiguration
public class DevicePresentationTest {

    @Autowired
    private DevicePresentation devicePresentation;
//    private DeviceResultStore deviceResultStore;
    private DeviceResultStore deviceResultStore;

    @Autowired
    private DeviceStore deviceStore;

    @Before
    public void setUp(){
        //

        DeviceResult deviceResult = new DeviceResult();
        DeviceResult BadDeviceResult = new DeviceResult();

        deviceResult.setCommandId("JunitTestCode");
        BadDeviceResult.setCommandId("JunitTestCodeBad");

        deviceResultStore.create(deviceResult);
        deviceResultStore.create(BadDeviceResult);
    }

    @Test
    public void deviceEnableNotificationTest(){
        // NOTE : 디바이스 생성시 SDA와 연관되어서 테스트 코드 구현후 작성.
    }


    @Test
    public void deviceControlTest(){
        // NOTE : 디바이스 제어시 SI에 데이터 요청

    }

    @Test
    public void deviceSearchByIdTest(){

    }

    @Test
    public void deviceSearchByLocationTest(){

    }

    @Test
    public void asynchronousControlResultTest(){

        // Happy Case
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.set_commandId("JunitTestCode");
        resultMessage.set_result(ClientProfile.RESPONSE_SUCCESS);
        resultMessage.set_resultCode(ClientProfile.RESPONSE_SUCCESS_CODE);
        String resultData = devicePresentation.asynchronousControlResult(resultMessage);
        Assert.assertEquals("ReceivePresentation failure of the asynchronousControlResultTest HappyCase command.",resultData, ClientProfile.RESPONSE_SUCCESS_CODE);

        // Bad Case
        ResultMessage badResultMessage = new ResultMessage();
        badResultMessage.set_commandId("JunitTestCodeBad");
        badResultMessage.set_result(ClientProfile.RESPONSE_FAILURE);
        badResultMessage.set_resultCode(ClientProfile.RESPONSE_FIALURE_CODE);
        String BadResultData = devicePresentation.asynchronousControlResult(badResultMessage);
        Assert.assertEquals("ReceivePresentation failure of the asynchronousControlResultTest BadCase command.",BadResultData, ClientProfile.RESPONSE_FIALURE_CODE);

    }

    @Test
    public void deviceDisableNotificationTest(){
        //
        deviceReleaseMessage deviceReleaseMessage = new deviceReleaseMessage();
        deviceReleaseMessage.setDeviceId("JuintTestDeviceDisable001");
        deviceReleaseMessage.setRegisterTime("20160808T153028");

        devicePresentation.deviceDisableNotification(deviceReleaseMessage);
        Device device = deviceStore.retrieveByID(deviceReleaseMessage.getDeviceId());

        Assert.assertNull(device);

    }

    @After
    public void teardown(){
        deviceResultStore.delete("JuintTestCode");
        deviceResultStore.delete("JuintTestCodeBad");
    }
}
