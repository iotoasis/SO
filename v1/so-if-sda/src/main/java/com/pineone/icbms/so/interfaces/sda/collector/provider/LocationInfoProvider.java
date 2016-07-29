package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.client.NotExistDataException;
import com.pineone.icbms.so.interfaces.sda.client.NotUsefulDataException;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Get Location Information list From SDA<BR/>
 * Created by Melvin on 2016. 1. 11..
 */
public class LocationInfoProvider {



    public String init() {

//        String path = LocationInfoProvider.class.getResource("").getPath();
//        System.out.println("path --> "+path);




        Properties sdaInfo = new Properties();
        InputStream in = LocationInfoProvider.class.getClassLoader().getResourceAsStream("sda.properties");
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
//     * HostName Include ContextID : search Location about Scheduled<BR/>
//     */
//    public static final String cm100Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-100/?p=";
//
//    /**
//     * HostName Include ContextID : search Location about Person<BR/>
//     */
//    public static final String cm200Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-200/?p=";
//
//    /**
//     * HostName Include ContextID : search Location about Optimum temperature<BR/>
//     */
//    public static final String cm230Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-230/?p=";
//
//    /**
//     * HostName Include ContextID : search Location about Optimum Humidify<BR/>
//     */
//    public static final String cm240Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-240/?p=";
//
//    /**
//     * HostName Include ContextID : search Location about Optimum Luminosity<BR/>
//     */
//    public static final String cm250Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-250/?p=";

    private final Logger log = LoggerFactory.getLogger(LocationInfoProvider.class);



    /**
     * get Scheduled Location<BR/>
     *
     * @param date
     * @param time
     * @return
     */
    public ArrayList<DefaultLocation> getLocationInfoFromSDA(String date, String time) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();
        DefaultLocation location;

        log.info(" >> Scheduled - Day : " + date + ", + Time : " + time);
        log.info(" >> Control Scheduled Location");

        /**
         * Use Schedule Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-100/?p=monday,0850<BR/>
         */
        String cm100Host = "CM-1-1-100/?p=";

        String getLocationURL = sdaConn + cm100Host + date + "," + time;

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only Location Information and add LocationList as Uri  (setUri)<BR/> */

        for (int i = 0; i < responseModel.getContent().size(); i++) {
            location = new DefaultLocation();
            location.setUri(responseModel.getContent().get(i).getLoc());
            locationArrayList.add(location);
        }

        return locationArrayList;
    }

    /**
     * get Current Location about Student<BR/>
     * @param student
     * @return
     * @throws NotExistDataException
     */


    public DefaultLocation getCurrentLocationFromSDA(DefaultStudent student) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();
        DefaultLocation location;


        log.info(" >> Student : " + student);
        log.info(" >> Get Current Location of Person");

        /**
         *  Use Student Id to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-200/?p=http://www.pineone.com/campus/u00001<BR/>
         */

        String cm200Host = "CM-1-1-200/?p=";
        String getLocationURL = sdaConn + cm200Host + student.getUri();
        System.out.println(getLocationURL);

        /**
         * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
         */
        /**
         * TODO : only Test
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);

//        String afterConnectData = afterConnectDataTemp.replace("nowhere","DT0001");

        /** get Only Location Information and add LocationList as Uri  (setUri)<BR/> */
        responseModel = responseMapper.getList(afterConnectData);

        location = new DefaultLocation();
        location.setUri(responseModel.getContent().get(0).getLoc());


        return location;
    }

    /**
     * filter Hot Location<BR/>
     *
     * @param location
     * @return
     * @throws NotExistDataException
     */
    public ArrayList<DefaultLocation> getHotLocationInfo(DefaultLocation location) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();




        /**
         * use location Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-230/? + location<BR/>
         *
         */
        String cm230Host = "CM-1-1-230/?p=";
        String getLocationURL = sdaConn + cm230Host + location.getUri();

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /**
         * if location's temperature is hotter than optimum temperature , return location<BR/>
         * else throw Exception<BR/>
         */
        if (afterConnectData.contains("hot")) {


            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            log.info(" >> Location : " + location);
            log.info(" >> Get Over Temp Location ");

            return locationArrayList;
        } else {

            throw new NotUsefulDataException();

        }
    }

    /**
     *
     * filter Cold Location<BR/>
     *
     * @param location
     * @return
     * @throws NotExistDataException
     * @throws NotUsefulDataException
     */

    public ArrayList<DefaultLocation> getColdLocationInfo(DefaultLocation location) throws NotExistDataException, NotUsefulDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * use location Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-230/? + location<BR/>
         *
         */
        String cm230Host = "CM-1-1-230/?p=";
        String getLocationURL = sdaConn + cm230Host + location.getUri();

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);

        /**
         * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);


        /**
         * if location's temperature is colder than optimum temperature , return location<BR/>
         * else throw Exception<BR/>
         */
        if (afterConnectData.contains("cold")) {

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }
            log.info(" >> Location : " + location);
            log.info(" >> Get Under Temp Location ");

            return locationArrayList;
        } else {

            throw new NotUsefulDataException();

        }
    }

    /**
     * filter wet Location<BR/>
     *
     * @param location
     * @return
     * @throws NotExistDataException
     * @throws NotUsefulDataException
     */
    public ArrayList<DefaultLocation> getWetLocationInfo(DefaultLocation location) throws NotExistDataException, NotUsefulDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * use location Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-240/? + location<BR/>
         *
         */
        String cm240Host = "CM-1-1-240/?p=";
        String getLocationURL = sdaConn + cm240Host + location.getUri();

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * if location's humidify is more humidify than optimum , return location<BR/>
         * else throw Exception<BR/>
         */
        if (afterConnectData.contains("wet")) {

            /**
             * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }
            log.info(" >> Location : " + location);
            log.info(" >> Get Humid Location ");

            return locationArrayList;
        } else {

            throw new NotUsefulDataException();

        }
    }

    /**
     * get dry Location<BR/>
     *
     * @param location
     * @return
     * @throws NotExistDataException
     * @throws NotUsefulDataException
     */
    public ArrayList<DefaultLocation> getDryLocationInfo(DefaultLocation location) throws NotExistDataException, NotUsefulDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         * use Location Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-240/? + location
         *
         */
        String cm240Host = "CM-1-1-240/?p=";
        String getLocationURL = sdaConn + cm240Host + location.getUri();

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * if location's humidify is less humidify than optimum , return location<BR/>
         * else throw Exception<BR/>
         */
        if (afterConnectData.contains("dry")) {

            /**
             * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            log.info(" >> Location : " + location);
            log.info(" >> Get Dry Location ");

            return locationArrayList;
        } else {

            throw new NotUsefulDataException();
        }
    }

    /**
     * filter bright Location
     *
     * @param location
     * @return
     * @throws NotExistDataException
     * @throws NotUsefulDataException
     */
    public ArrayList<DefaultLocation> getBrightLocationInfo(DefaultLocation location) throws NotExistDataException, NotUsefulDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         *  use Location Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-250/? + location
         *
         */
        String cm250Host = "CM-1-1-250/?p=";
        String getLocationURL = sdaConn + cm250Host + location.getUri();

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);


        /**
         * if location is bright , return location<BR/>
         * else throw Exception<BR/>
         */
        if (afterConnectData.contains("bright")) {

            /**
             * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            log.info(" >> Location : " + location);
            log.info(" >> Get Bright Location ");

            return locationArrayList;
        } else {

            throw new NotUsefulDataException();

        }
    }

    /**
     * filter dark Location
     *
     * @param location
     * @return
     * @throws NotExistDataException
     * @throws NotUsefulDataException
     */
    public ArrayList<DefaultLocation> getDarkLocationInfo(DefaultLocation location) throws NotExistDataException, NotUsefulDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel;
        ArrayList<DefaultLocation> locationArrayList = new ArrayList<>();

        /**
         *  use Location Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-250/? + location
         *
         */
        String cm250Host = "CM-1-1-250/?p=";
        String getLocationURL = sdaConn + cm250Host + location.getUri();

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getLocationURL);

        /**
         * if location is dark , return location<BR/>
         * else throw Exception<BR/>
         */
        if (afterConnectData.contains("dark")) {

            /**
             * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
             */
            responseModel = responseMapper.getList(afterConnectData);

            for (int i = 0; i < responseModel.getContent().size(); i++) {
                location = new DefaultLocation();
                location.setUri(responseModel.getContent().get(i).getLoc());
                locationArrayList.add(location);
            }

            log.info(" >> Location : " + location);
            log.info(" >> Get Dark Location ");

            return locationArrayList;
        } else {
            throw new NotUsefulDataException();
        }
    }
}
