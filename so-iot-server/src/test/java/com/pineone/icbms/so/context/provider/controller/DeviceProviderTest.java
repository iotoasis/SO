package com.pineone.icbms.so.context.provider.controller;

import com.pineone.icbms.so.interfaces.sda.collector.provider.DeviceInfoProvider;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.context.DefaultContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import com.pineone.icbms.so.util.restful.ClientService;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Melvin on 2015. 12. 30..
 */

public class DeviceProviderTest {

    DeviceInfoProvider deviceInfoProvider = new DeviceInfoProvider();


//    HttpUtil httpUtilTest = new HttpUtil();

//    GetStringData getStringData = new GetStringData();

    ClientService clientService = new ClientService();

    ArrayList<String> getDeviceList = new ArrayList<>();

    DefaultOntologyReference ontologyReference = new DefaultOntologyReference();

    DefaultLocation location = new DefaultLocation();

    DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

    ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();

    DefaultContext context = new DefaultContext<>();


//    ParsingDeviceURI parsingDeviceURI = new ParsingDeviceURI();

//    ResponseModel responseModel = new ResponseModel();


//    @Test
//    public void deviceCollectorTest() throws Exception {
//        getDeviceList = deviceInfoProvider.getDeviceListFromSDA("location", "ontologyRef");
//
//    }

    /**
     * 해당 URL을 이용하여 HttpUtil을 이용하여 SDA와 연동한 결과를 확인한다.
     *
     * @throws Exception
     */
//    @Test
//    public void HttpUtilTest() throws Exception {
//
//        String uri = "http://166.104.112.43:20080/sda/ctx/CM-1-1-100/?p=monday,0850";
//
//        IHttpResponseMessage httpResponseMessage = clientService.requestGetService(uri);
//
//        String res = DataConversion.responseDataToString(httpResponseMessage);
//
//        System.out.println(res);
//
//    }
//
////    @Test
////    public void testLocation() throws Exception {
////
//////        responseModel = deviceInfoProvider.getDeviceListFromSDA("location", "ontologyRef");
////
////        parsingDeviceURI.testGetJsonList(responseModel.getContent());
////}
//
//    @Test
//    public void DeviceCollectorTest() throws Exception {
//
//
//        location.setUri("http://www.pineone.com/campus/LR0001");
//        ontologyReference.setName("http://www.pineone.com/campus/HumidifyControl");
//
//        physicalDeviceArrayList = deviceInfoProvider.getDeviceListFromSDA(ontologyReference, location);
//
//        for (DefaultPhysicalDevice physicalDevice  : physicalDeviceArrayList ) {
//            System.out.println(physicalDevice.getUri());
//        }
//
//        /**http://166.104.112.43:20080/sda/ctx/CM-1-1-110/?p=
//        http://www.pineone.com/campus/LR0001,http://www.pineone.com/campus/HumidifyControl
//
//
//         icbms:AirCoolingControl
//         icbms:AirHeatingControl
//         icbms:DehumidifyControl
//         icbms:DoorControl
//         icbms:HumidifyControl
//         icbms:LuminosityControl
//         icbms:TemperatureControl
//         icbms:SirenControl
//         */
//
//    }
//
//    @Test
//    public void DeviceCollectorTest2() throws Exception {
//
//        location.setUri("http://www.pineone.com/campus/LR0001");
//        ontologyReference.setName("http://www.pineone.com/campus/TemperatureControl");
//
//
//
//
//        physicalDeviceArrayList = deviceInfoProvider.getDeviceListFromSDA(ontologyReference, location);
//
//        for (DefaultPhysicalDevice physicalDevice  : physicalDeviceArrayList ) {
//            System.out.println(physicalDevice.getUri());
//        }
//
//    }
//
//    @Test
//    public void DeviceControllerGetUnderValueDevice() throws Exception {
//
//        /**
//         * ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-130/?p=http://www.pineone.com/campus/LR0001
//         */
//
//
//        location.setUri("http://www.pineone.com/campus/LR0001");
//
//        physicalDeviceArrayList = deviceInfoProvider.getUnderLightValueDevice(location);
//
//        for (DefaultPhysicalDevice physicalDevice  : physicalDeviceArrayList ) {
//            System.out.println(physicalDevice.getUri());
//        }
//
//    }
//
//
//    @Test
//    public void DeviceControllerGetCamInfo() throws Exception {
//
//        /**
//         * ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-210/?p=http://www.pineone.com/campus/LB0001
//         */
//
//
//        location.setUri("http://www.pineone.com/campus/LB0001");
//
//        physicalDeviceArrayList = deviceInfoProvider.getCamInfoFromSDA(location);
//
//        for (DefaultPhysicalDevice physicalDevice  : physicalDeviceArrayList ) {
//            System.out.println(physicalDevice.getUri());
//        }
//
//    }
//
    @Test
    public void DeviceControllerGetAllDeviceListInLocation() throws Exception {

        /**
         * ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-120/?p=http://www.pineone.com/campus/LR0001
         */

        location.setUri("http://www.pineone.com/campus/LR0001");

        physicalDeviceArrayList = deviceInfoProvider.getDeviceListinLocationFromSDA(location);

        for (DefaultPhysicalDevice physicalDevice  : physicalDeviceArrayList ) {
            System.out.println(physicalDevice.getUri());
        }

    }

//    @Test
//    public void DeviceControllerGetUpValueDevice() throws Exception {
//
//        /**
//         * ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-140/?p=http://www.pineone.com/campus/LR0001
//         */
//
//
//        location.setName("http://www.pineone.com/campus/LR0001");
//
//        physicalDeviceArrayList = deviceInfoProvider.getUpLightValueDevice(location);
//
//        for (DefaultPhysicalDevice physicalDevice  : physicalDeviceArrayList ) {
//            System.out.println(physicalDevice.getUri());
//        }
//
//    }
}