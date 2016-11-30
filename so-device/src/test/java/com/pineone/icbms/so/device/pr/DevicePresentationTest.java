package com.pineone.icbms.so.device.pr;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DeviceApplication.class)
@WebAppConfiguration*/
public class DevicePresentationTest {
/*
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
        deviceReleaseMessage.setId("JuintTestDeviceDisable001");
        deviceReleaseMessage.setRegisterTime("20160808T153028");

        devicePresentation.deviceDisableNotification(deviceReleaseMessage);
        Device device = deviceStore.retrieveByID(deviceReleaseMessage.getId());

        Assert.assertNull(device);

    }

    @After
    public void teardown(){
        deviceResultStore.delete("JuintTestCode");
        deviceResultStore.delete("JuintTestCodeBad");
    }
    */
}
