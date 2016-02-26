package com.pineone.icbms.so.interfaces.sda.collector.provider;

import com.pineone.icbms.so.interfaces.sda.client.CollectorController;
import com.pineone.icbms.so.interfaces.sda.client.NotExistDataException;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseMapper;
import com.pineone.icbms.so.interfaces.sda.collector.ResponseModel;
import com.pineone.icbms.so.iot.resources.value.DefaultValue;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * get Optimal Value from SDA<BR/>
 * Created by Melvin on 2016. 1. 10..

 */
public class OptimalValueInfoProvider {

    public String init() {

//        String path = LocationInfoProvider.class.getResource("").getPath();
//        System.out.println("path --> "+path);




        Properties sdaInfo = new Properties();
        InputStream in = OptimalValueInfoProvider.class.getClassLoader().getResourceAsStream("sda.properties");
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
//     * HostName Include ContextID : search Optimal Value<BR/>
//     */
//    public static final String host = "http://166.104.112.43:20080/sda/ctx/CM-1-1-160/?p=";
////    public static final String basicContextId = "CM-1-1-110";

    private final Logger log = LoggerFactory.getLogger(OptimalValueInfoProvider.class);

    /**
     *
     * get OptimalValue about Specific Location
     *
     * @param location
     * @return
     * @throws NotExistDataException
     */

    public DefaultValue getOptimalValueFromSDA(DefaultLocation location) throws NotExistDataException {

        CollectorController collectorController = new CollectorController();
        ResponseMapper responseMapper = new ResponseMapper();
        ResponseModel responseModel = new ResponseModel();
        DefaultValue value = new DefaultValue();

        String locationInfo = location.getUri();

        log.info(" >> Location : " + location);
        log.info(" >> Get Optimal Temperature");


        /**
         * Use Location Information to make URL
         *  ex)http://166.104.112.43:20080/sda/ctx/CM-1-1-160/?p=http://www.pineone.com/campus/DT0001
         */
        String cm160Host = "CM-1-1-160/?p=";
        String getDeviceURL = sdaConn + cm160Host + locationInfo;

        /**
         * get String Type Value InformationList<BR/>
         */
        String afterConnectData = collectorController.getDevice(getDeviceURL);


        /**
         * Convert To Object from Jason Type Sting Data<BR/>
         */
        responseModel = responseMapper.getList(afterConnectData);

        /**
         * get Max and Min value and setting
         */
        value = new DefaultValue();
        value.setMaxValue(responseModel.getContent().get(0).getMax_value());
        value.setMinValue(responseModel.getContent().get(0).getMin_value());



        return value;
    }
}
