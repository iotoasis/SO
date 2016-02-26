package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.client.NotExistDataException;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Get Device Information list From SDA<BR/>
 * Created by Melvin on 2016. 1. 7..
 */

public class DeviceInfoProvider {

    public String init() {

//        String path = LocationInfoProvider.class.getResource("").getPath();
//        System.out.println("path --> "+path);




        Properties sdaInfo = new Properties();
        InputStream in = DeviceInfoProvider.class.getClassLoader().getResourceAsStream("sda.properties");
        String sdaConnection = null;

        try {
//            Reader reader = new FileReader(path+"sda.properties");
//            sdaInfo.load(new FileInputStream("./sda.properties"));
            sdaInfo.load(in);
            sdaConnection = sdaInfo.getProperty("Sda_Connection");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sdaConnection;
    }

    String sdaConn = init();

//    /**
//     * HostName Include ContextID : use OntologyReference And Location to Search specific Device<BR/>
//     */
//    public static final String cm110Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-110/?p=";
//
//    /**
//     * HostName Include ContextID : Search All devices<BR/>
//     */
//    public static final String cm120Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-120/?p=";
//
//    /**
//     * HostName Include ContextID : Search Devices About Luminosity<BR/>
//     */
//    public static final String cm130Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-130/?p=";
//
//    /**
//     * HostName Include ContextID : Search Devices About Luminosity<BR/>
//     */
//    public static final String cm140Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-140/?p=";
//
//    /**
//     * HostName Include ContextID : Search Cam<BR/>
//     */
//    public static final String cm210Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-210/?p=";
//
//    /**
//     * HostName Include ContextID : Search SmartSwitch<BR/>
//     */
//    public static final String cm260Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-260/?p=";

    private final Logger log = LoggerFactory.getLogger(DeviceInfoProvider.class);

    /***
     *
     * Use Location Information and OntologyReference to make URL,
     * Use that URL to get Physical(Real) DeviceList <BR/>
     *
     * @param ontologyRef
     * @param location
     * @return
     */
    public ArrayList<DefaultPhysicalDevice> getDeviceListFromSDA(DefaultOntologyReference ontologyRef, DefaultLocation location) throws NotExistDataException {


        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String ontologyInfo = ontologyRef.getName();
        String locationInfo = location.getUri();

        log.info(" >> Location : " + locationInfo);
        log.info(" >> Get Specific Device List With OntologyRef");
        log.info(" >> OntologyRef : " + ontologyInfo);


        /**
         * Use Location Information and OntologyReference to make URL<BR/>
         * ex) http://166.104.112.43:20080/sda/ctx/CM-1-1-110/?p=
         * http://www.pineone.com/campus/LR0001,http://www.pineone.com/campus/HumidifyControl <BR/>
         *
         * cf) OntologyReference List<BR/>
         icbms:AirCoolingControl<BR/>
         icbms:AirHeatingControl<BR/>
         icbms:DehumidifyControl<BR/>
         icbms:DoorControl<BR/>
         icbms:HumidifyControl<BR/>
         icbms:LuminosityControl<BR/>
         icbms:TemperatureControl<BR/>
         icbms:SirenControl<BR/>
         */

        String cm110Host = "CM-1-1-110/?p=";
        String getDeviceURL = sdaConn + cm110Host + locationInfo + "," + ontologyInfo;

        /**
         * get String Type DeviceInformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Convert To Object from Jason Type Sting Data(DeviceList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only DeviceInfo and add DeviceList as Uri  (setUri)<BR/> */

        for (int i = 0; i < responseModel.getContent().size(); i++) {
            physicalDevice = new DefaultPhysicalDevice();
            physicalDevice.setUri(responseModel.getContent().get(i).getDev());
            physicalDeviceArrayList.add(physicalDevice);
        }

        return physicalDeviceArrayList;
    }

    /**
     * Get all Devices Within Location(ContextModel Id = 120)<BR/>
     * @param location
     * @return
     */
    public ArrayList<DefaultPhysicalDevice> getDeviceListinLocationFromSDA(DefaultLocation location) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();

        log.info(" >> Location : " + locationInfo);
        log.info(" >> Get All Device");

        /**
         * Use Location Information to make URL.<BR/>
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-120/?p=http://www.pineone.com/campus/LB0001<BR/>
         *
         *  LectureRoom - icbms:LR0001<BR/>
         *  Laboratory - icbms:LB0001<BR/>
         *  Dormitory - icbms:DT0001<BR/>
         */
        String cm120Host = "CM-1-1-120/?p=";
        String getDeviceURL = sdaConn + cm120Host + locationInfo;

        /**
         * get String Type DeviceInformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Convert To Object from Jason Type Sting Data(DeviceList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only DeviceInfo and add DeviceList as Uri  (setUri)<BR/>
         * Duplication Check<BR/> */


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
     * Get Cam Url Within Location(ContextModel Id = 210)<BR/>
     *

     * @param location
     * @return
     */
    public ArrayList<DefaultPhysicalDevice> getCamInfoFromSDA(DefaultLocation location) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();

        log.info(" >> Location : " + locationInfo);
        log.info(" >> Get Cam List");

/** Use Location Information to make URL.<BR/>
 *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-210/?p=http://www.pineone.com/campus/LB0001<BR/> */

        String cm210Host = "CM-1-1-210/?p=";
        String getDeviceURL = sdaConn + cm210Host + locationInfo;

        /**
         * get String Type DeviceInformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Convert To Object from Jason Type Sting Data(DeviceList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only DeviceInfo and add DeviceList as Uri  (setUri)<BR/> */

        for(int i = 0 ; i < responseModel.getContent().size() ; i++){
            physicalDevice = new DefaultPhysicalDevice();
            physicalDevice.setUri(responseModel.getContent().get(i).getCamurl());
            physicalDeviceArrayList.add(physicalDevice);
        }

        return physicalDeviceArrayList;
    }

    /** Get DeviceList about Luminosity Within dark Location(ContextModel Id = 130)<BR/>
     *
     * @param location
     * @return
     * @throws NotExistDataException
     */
    public ArrayList<DefaultPhysicalDevice> getUnderLightValueDevice(DefaultLocation location) throws NotExistDataException {


        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();

        log.info(" >> Location : " + locationInfo);
        log.info(" >> Get Luminous Devices");

        /** Use Location Information to make URL.<BR/>
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-130/?p=http://www.pineone.com/campus/LB0001<BR/>
         *  */
        String cm130Host = "CM-1-1-130/?p=";
        String getDeviceURL = sdaConn + cm130Host + locationInfo;

        /**
         * get String Type DeviceInformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Convert To Object from Jason Type Sting Data(DeviceList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only DeviceInfo and add DeviceList as Uri  (setUri)<BR/> */
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
     * Get DeviceList about Luminosity Within bright Location(ContextModel Id = 140)<BR/>
     * @param location
     * @return
     * @throws NotExistDataException
     */
    public ArrayList<DefaultPhysicalDevice> getUpLightValueDevice(DefaultLocation location) throws NotExistDataException {


        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();

        log.info(" >> Location : " + locationInfo);
        log.info(" >> Get Luminous Devices");


        /** Use Location Information to make URL.<BR/>
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-140/?p=http://www.pineone.com/campus/LB0001<BR/>
         *  */
        String cm140Host = "CM-1-1-140/?p=";
        String getDeviceURL = sdaConn + cm140Host + locationInfo;

        /**
         * get String Type DeviceInformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Convert To Object from Jason Type Sting Data(DeviceList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only DeviceInfo and add DeviceList as Uri  (setUri)<BR/> */
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
     * Get SmartSwitchInformation Within Location(ContextModel Id = 260)<BR/>
     *
     * @param location
     * @return
     * @throws NotExistDataException
     */
    public ArrayList<DefaultPhysicalDevice> getSmartSwitch(DefaultLocation location) throws NotExistDataException {


        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        IHttpResponseMessage httpResponseMessage;
        ArrayList<DefaultPhysicalDevice> physicalDeviceArrayList = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();

        String locationInfo = location.getUri();

        log.info(" >> Location : " + locationInfo);
        log.info(" >> Get SmartSwitch");


        /** Use Location Information to make URL.<BR/>
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-260/?p=http://www.pineone.com/campus/LB0001<BR/>
         *  */
        String cm260Host = "CM-1-1-260/?p=";
        String getDeviceURL = sdaConn + cm260Host + locationInfo;

        /**
         * get String Type DeviceInformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);

//        String agterConnectData = " {\"content\":[{\"dev\":\"http://www.pineone.com/herit-in/herit-cse/SmartSwitch_LB0001SS0001\"}]}";

        /**
         * Convert To Object from Jason Type Sting Data(DeviceList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only DeviceInfo and add DeviceList as Uri  (setUri)<BR/> */

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
