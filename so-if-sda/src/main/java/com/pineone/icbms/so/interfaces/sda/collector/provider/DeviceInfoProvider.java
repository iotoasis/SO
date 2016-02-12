package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

import java.util.ArrayList;

/**
 * Action3의 결과로 입력받를 정보들을 이용하여 URL을 만들고 (2가지 형태 메소드로 구분)필요에 따른 DeviceList를 생성하여 Action3에 반환<BR/>
 * Created by Melvin on 2016. 1. 7..
 */

public class DeviceInfoProvider {
    public static final String cm110Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-110/?p=";
    public static final String cm120Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-120/?p=";
    public static final String cm130Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-130/?p=";
    public static final String cm140Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-140/?p=";
    public static final String cm210Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-210/?p=";
    public static final String basicContextId = "CM-1-1-110";

    /***
     *
     * Location, OntologyRef정보를 이용하여 URL을 만들고 필요에 따른 DeviceList를 생성하여 반환<BR/>
     *
     * @param ontologyRef
     * @param location
     * @return
     */
    public ArrayList<DefaultPhysicalDevice> getDeviceListFromSDA(DefaultOntologyReference ontologyRef, DefaultLocation location) {


        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String ontologyInfo = ontologyRef.getName();
        String locationInfo = location.getUri();

        /**
         * ontologyRef 와 location 내용을 입력받아서 실제 디바이스 정보를 가지고 올 수 있는 URL을 생성.
         * ex) http://166.104.112.43:20080/sda/ctx/CM-1-1-110/?p=
         * http://www.pineone.com/campus/LR0001,http://www.pineone.com/campus/HumidifyControl
         *
         * 오퍼레이션타입
         icbms:AirCoolingControl
         icbms:AirHeatingControl
         icbms:DehumidifyControl
         icbms:DoorControl
         icbms:HumidifyControl
         icbms:LuminosityControl
         icbms:TemperatureControl
         icbms:SirenControl
         */
        String getDeviceURL = cm110Host + locationInfo + "," + ontologyInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Device 정보만 String 으로 추출하여 반환
         */


        for (int i = 0; i < responseModel.getContent().size(); i++) {
            physicalDevice = new DefaultPhysicalDevice();
            physicalDevice.setUri(responseModel.getContent().get(i).getDev());

            /**
             * Test
             */
            System.out.println("[getDeivceInfo] : " + responseModel.getContent().get(i).getDev());
            physicalDeviceArrayList.add(physicalDevice);
        }

        /**
         *   DeviceInfo Test
         */
        System.out.println("[PhysicalDevice1 Check] : " + physicalDeviceArrayList.get(0).getUri());
        System.out.println("[PhysicalDevice2 Check] : " + physicalDeviceArrayList.get(1).getUri());
        System.out.println("DeviceInfo Finish");
        return physicalDeviceArrayList;
    }

    /**
     * Location 정보와 장소의 모든 장치를 검색하는 cm120을 이용하여 URL을 만들고 필요에 따른 DeviceList를 생성하여 반환<BR/>
     *

     * @param location
     * @return
     */
    public ArrayList<DefaultPhysicalDevice> getDeviceListinLocationFromSDA(DefaultLocation location) {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();


        /**
         * Context 와 location 내용을 입력받아서 실제 디바이스 정보를 가지고 올 수 있는 URL을 생성.
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-120/?p=http://www.pineone.com/campus/LB0001
         *
         *  강의실 icbms:LR0001
         실험실 icbms:LB0001
         기숙사 icbms:DT0001
         */
        String getDeviceURL = cm120Host + locationInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Device 정보만 String 으로 추출하여 반환
         */


//        if (afterConnectData.contains("msg")) {
//            for (int i = 0; i < responseModel.getContent().size(); i++) {
//                lo.setUri(responseModel.getContent().get(i).getMsg());
//                locationArrayList.add(lo);
//            }
//            return locationArrayList;

        physicalDevice = new DefaultPhysicalDevice();
        physicalDevice.setUri(responseModel.getContent().get(0).getDev());
        physicalDeviceArrayList.add(physicalDevice);

        for(int i = 1 ; i < responseModel.getContent().size() ; i++){
            physicalDevice = new DefaultPhysicalDevice();
            physicalDevice.setUri(responseModel.getContent().get(i).getDev());

            if(!(responseModel.getContent().get(i).getDev().equals(responseModel.getContent().get(i-1).getDev()))) {
                physicalDeviceArrayList.add(physicalDevice);
            }
        }

            return physicalDeviceArrayList;
        }

    /**
     * Location 정보를 이용하여 URL을 만들고 필요에 따른 DeviceList를 생성하여 반환<BR/>
     *

     * @param location
     * @return
     */
    public ArrayList<DefaultPhysicalDevice> getCamInfoFromSDA(DefaultLocation location){

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();


        /**
         * location 내용을 입력받아서 캠을 찾아주는 컨텍스트 210과 합쳐서 실제 디바이스 정보를 가지고 올 수 있는 URL을 생성.

         *
         *  강의실 icbms:LR0001
         실험실 icbms:LB0001
         기숙사 icbms:DT0001
         */
        String getDeviceURL = cm210Host + locationInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Device 정보만 String 으로 추출하여 반환
         */

        for(int i = 0 ; i < responseModel.getContent().size() ; i++){
            physicalDevice = new DefaultPhysicalDevice();
            physicalDevice.setUri(responseModel.getContent().get(i).getUri());
            physicalDeviceArrayList.add(physicalDevice);
        }

        return physicalDeviceArrayList;
    }

    public ArrayList<DefaultPhysicalDevice> getUnderLightValueDevice(DefaultLocation location) {


        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();


        /**
         * Context 와 location 내용을 입력받아서 실제 디바이스 정보를 가지고 올 수 있는 URL을 생성.

         *
         *  강의실 icbms:LR0001
         실험실 icbms:LB0001
         기숙사 icbms:DT0001
         */
        String getDeviceURL = cm130Host + locationInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Device 정보만 String 으로 추출하여 반환
         */


//        if (afterConnectData.contains("msg")) {
//            for (int i = 0; i < responseModel.getContent().size(); i++) {
//                lo.setUri(responseModel.getContent().get(i).getMsg());
//                locationArrayList.add(lo);
//            }
//            return locationArrayList;

        physicalDevice = new DefaultPhysicalDevice();
        physicalDevice.setUri(responseModel.getContent().get(0).getDev());
        physicalDeviceArrayList.add(physicalDevice);

        for(int i = 1 ; i < responseModel.getContent().size() ; i++){
            physicalDevice = new DefaultPhysicalDevice();
            physicalDevice.setUri(responseModel.getContent().get(i).getDev());

            if(!(responseModel.getContent().get(i).getDev().equals(responseModel.getContent().get(i-1).getDev()))) {
                physicalDeviceArrayList.add(physicalDevice);
            }
        }

        return physicalDeviceArrayList;
    }

    public ArrayList<DefaultPhysicalDevice> getUpLightValueDevice(DefaultLocation location) {


        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();


        /**
         * Context 와 location 내용을 입력받아서 실제 디바이스 정보를 가지고 올 수 있는 URL을 생성.
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-120/?p=http://www.pineone.com/campus/LB0001
         *
         *  강의실 icbms:LR0001
         실험실 icbms:LB0001
         기숙사 icbms:DT0001
         */
        String getDeviceURL = cm140Host + locationInfo;

        /**
         *Controller를 이용하여 HttpResponse형태의 데이터를 String으로 수신
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Json형태를 띄고 있는 String형 데이터를 Object로 변환
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** Device 정보만 String 으로 추출하여 반환
         */


//        if (afterConnectData.contains("msg")) {
//            for (int i = 0; i < responseModel.getContent().size(); i++) {
//                lo.setUri(responseModel.getContent().get(i).getMsg());
//                locationArrayList.add(lo);
//            }
//            return locationArrayList;

        physicalDevice = new DefaultPhysicalDevice();
        physicalDevice.setUri(responseModel.getContent().get(0).getDev());
        physicalDeviceArrayList.add(physicalDevice);

        for(int i = 1 ; i < responseModel.getContent().size() ; i++){
            physicalDevice = new DefaultPhysicalDevice();
            physicalDevice.setUri(responseModel.getContent().get(i).getDev());

            if(!(responseModel.getContent().get(i).getDev().equals(responseModel.getContent().get(i-1).getDev()))) {
                physicalDeviceArrayList.add(physicalDevice);
            }
        }

        return physicalDeviceArrayList;
    }

}
