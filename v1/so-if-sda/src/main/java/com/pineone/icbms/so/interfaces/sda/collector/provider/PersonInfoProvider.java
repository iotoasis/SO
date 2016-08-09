package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.client.NotExistDataException;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * get Person Information<BR/>
 * Created by Melvin on 2016. 1. 10..
 */
public class PersonInfoProvider {

    public String init() {

//        String path = LocationInfoProvider.class.getResource("").getPath();
//        System.out.println("path --> "+path);




        Properties sdaInfo = new Properties();
        InputStream in = PersonInfoProvider.class.getClassLoader().getResourceAsStream("sda.properties");
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
//     * HostName Include ContextID : search Manager about specific Location<BR/>
//     */
//    public static final String cm170Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-170/?p=";
//
//    /**
//     * HostName Include ContextID : search Location about Scheduled<BR/>
//     */
//    public static final String cm190Host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-190/?p=";

    private final Logger log = LoggerFactory.getLogger(PersonInfoProvider.class);

    /***
     * get Manager Information

     * @param location
     * @return
     */

    public ArrayList<DefaultStudent> getManagerInfoFromSDA(DefaultLocation location) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        DefaultStudent student = new DefaultStudent();
        ArrayList<DefaultStudent> studentList = new ArrayList<>();

        String locationInfo = location.getUri();

        log.info(" >> Location :" + locationInfo);
        log.info(" Get Manager of Location");


        /**
         *  use locationInformation to make url
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-170/?p=http://www.pineone.com/campus/LB0001
         *
         */
        String cm170Host = "CM-1-1-170/?p=";
        String getPersonURL = sdaConn + cm170Host + locationInfo;

        /**
         * get String Type Person InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getPersonURL);

        /**
         * Convert To Object from Jason Type Sting Data<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only Student Information and add Student as Manager (setManager) <BR/> */

        for (int i = 0; i < responseModel.getContent().size(); i++) {

            student = new DefaultStudent();
            student.setUri(responseModel.getContent().get(i).getManager());
            studentList.add(student);
        }

        return studentList;
    }

    /***
     * ger Person to ring Alarm<BR/>
     *
     * @param time
     * @return
     */
    public ArrayList<DefaultStudent> getPersonInfoAboutAlarm(String time) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        DefaultStudent student = new DefaultStudent();
        ArrayList<DefaultStudent> studentList = new ArrayList<>();

        log.info(" >> Time :" + time);
        log.info(" Get Person Who Set Alarm");

        /**
         * use time to make URL<BR/>
         */
        String cm190Host = "CM-1-1-190/?p=";
        String getPersonURL = sdaConn + cm190Host + time;

        /**
         * get String Type Person InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getPersonURL);

        /**
         * Convert To Object from Jason Type Sting Data<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** get Only Student Information and add Student as Manager (setManager) <BR/> */

        for (int i = 0; i < responseModel.getContent().size(); i++) {

            student = new DefaultStudent();
            student.setUri(responseModel.getContent().get(i).getUser_id());
            studentList.add(student);
        }

        return studentList;
    }
}

