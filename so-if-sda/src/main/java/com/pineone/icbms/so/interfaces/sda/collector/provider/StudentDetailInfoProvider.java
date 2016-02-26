package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.client.NotExistDataException;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * get detail Information about person<BR/>
 * Created by Melvin on 2016. 1. 10..
 *
 */
public class StudentDetailInfoProvider {

    public String init() {

//        String path = LocationInfoProvider.class.getResource("").getPath();
//        System.out.println("path --> "+path);




        Properties sdaInfo = new Properties();
        InputStream in = StudentDetailInfoProvider.class.getClassLoader().getResourceAsStream("sda.properties");
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
//     * HostName Include ContextID : search Detail Information List<BR/>
//     */
//    public static final String host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-220/?p=";


    private final Logger log = LoggerFactory.getLogger(StudentDetailInfoProvider.class);



    /***
     * get Detail Information about Person <BR/>
     *
     * @param student
     * @return
     */

    public ArrayList<DefaultStudent> getStudentExtraInfoFromSDA(DefaultStudent student) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        ArrayList<DefaultStudent> studentList = new ArrayList<>();

        String personInfo = student.getUri();

        log.info(" >> Student : " + student);
        log.info(" Get Detail Information about Student");

        /**
         *  use Student Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-220/?p=http://www.pineone.com/campus/Student_S00001<BR/>
         *
         */
        String cm220Host = "CM-1-1-220/?p=";
        String getDeviceURL = sdaConn + cm220Host + personInfo;

        /**
         * get String Type Location InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);

        /**
         * Convert To Object from Jason Type Sting Data(LocationList)<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /** set Student's Detail Information */

        for (int i = 0; i < responseModel.getContent().size(); i++) {

            student = new DefaultStudent();
            student.setId(responseModel.getContent().get(i).getUser_id());
            student.setPhone(responseModel.getContent().get(i).getPhone());
            student.setName(responseModel.getContent().get(i).getName());

            studentList.add(student);
        }

        return studentList;
    }
}
