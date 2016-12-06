package com.pineone.icbms.so.util.address;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by melvin on 2016. 7. 11..
 * NOTE: Context 를 등록하기 위한 Address 를 관리 - 기본 Address 의 변경을 통합적으로 관리
 **/

@Service
public class ContextAddress {

//    private ContextAddress(){};
//    public static ContextAddress newContextAddress(){
//        ContextAddress contextAddress = new ContextAddress();
//        return contextAddress;
//    }

    public String getSDAAddress(){
        //
        String sdaConnection = null;
        Properties sdaInfo = new Properties();
        InputStream inputStream = ContextAddress.class.getClassLoader().getResourceAsStream("server.properties");
        try{
            sdaInfo.load(inputStream);
            sdaConnection = sdaInfo.getProperty("Sda_Connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sdaConnection;
    }

    public String getSIAddress(){
        //
        String siConnection = null;
        Properties siInfo = new Properties();
        InputStream inputStream = ContextAddress.class.getClassLoader().getResourceAsStream("server.properties");
        try{
            siInfo.load(inputStream);
            siConnection = siInfo.getProperty("SI_Connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return siConnection;
    }

    public String getSOAddress(){
        //
        String soConnection = null;
        Properties siInfo = new Properties();
        InputStream inputStream = ContextAddress.class.getClassLoader().getResourceAsStream("server.properties");
        try{
            siInfo.load(inputStream);
            soConnection = siInfo.getProperty("SO_Connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soConnection;
    }

}
