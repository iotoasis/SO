package com.pineone.icbms.so.pr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pineone.icbms.so.entity.DeviceResult;
import com.pineone.icbms.so.entity.ReleaseResultMessage;
import com.pineone.icbms.so.entity.ResultMessage;
import com.pineone.icbms.so.store.DeviceResultStore;
import com.pineone.icbms.so.store.memory.DeviceResultMemory;
import com.pineone.icbms.so.util.ClientProfile;

/**
 * Created by pahnj on 2016-08-05.
 */
public class ReceivePresentationTest {

    public ReceivePresentation receivePresentation;
    public DeviceResultStore deviceResultStore;

    @Before
    public void setUp(){
        //
        receivePresentation =  new ReceivePresentation();
        DeviceResult deviceResult = new DeviceResult();
        DeviceResult BadDeviceResult = new DeviceResult();

        deviceResult.setCommandId("JunitTestCode");
        BadDeviceResult.setCommandId("JunitTestCodeBad");

        deviceResultStore = new DeviceResultMemory();
        deviceResultStore.create(deviceResult);
        deviceResultStore.create(BadDeviceResult);

    }

    @Test
    public void asynchronousControlResultTest(){

        // Happy Case
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.set_commandId("JunitTestCode");
        resultMessage.set_result(ClientProfile.RESPONSE_SUCCESS);
        resultMessage.set_resultCode(ClientProfile.RESPONSE_SUCCESS_CODE);
        String resultData = receivePresentation.asynchronousControlResult(resultMessage);
        Assert.assertEquals("ReceivePresentation failure of the asynchronousControlResultTest HappyCase command.",resultData, ClientProfile.RESPONSE_SUCCESS_CODE);

        // Bad Case
        ResultMessage badResultMessage = new ResultMessage();
        badResultMessage.set_commandId("JunitTestCodeBad");
        badResultMessage.set_result(ClientProfile.RESPONSE_FAILURE);
        badResultMessage.set_resultCode(ClientProfile.RESPONSE_FIALURE_CODE);
        String BadResultData = receivePresentation.asynchronousControlResult(badResultMessage);
        Assert.assertEquals("ReceivePresentation failure of the asynchronousControlResultTest BadCase command.",BadResultData, ClientProfile.RESPONSE_FIALURE_CODE);

    }

    @Test
    public void deviceDisableNotificationTest(){
        //
        ReleaseResultMessage releaseResultMessage = new ReleaseResultMessage();
        releaseResultMessage.setDeviceId("JuintTestDeviceDisable001");
        releaseResultMessage.setUnregisterTime("20160808T153028");

        String resultData = receivePresentation.deviceDisableNotification(releaseResultMessage);

        Assert.assertEquals("ReceivePresentation failure of the deviceDisableNotificationTest command.",resultData,"JuintTestDeviceDisable001");

    }
    @After
    public void teardown(){
        deviceResultStore.delete("JuintTestCode");
        deviceResultStore.delete("JuintTestCodeBad");
    }
}
