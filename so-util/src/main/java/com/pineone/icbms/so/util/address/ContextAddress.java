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
    public static final String SERVER_PROPERTIES = "server.properties";
    public static final String SDA_SERVER = "Sda_Connection";
    public static final String SI_SERVER = "SI_Connection";
    public static final String SO_SERVER = "SO_Connection";

    public String getServerAddress(String server){
        //
        String soConnection = null;
        Properties siInfo = new Properties();

        InputStream inputStream = ContextAddress.class.getClassLoader().getResourceAsStream(SERVER_PROPERTIES);

        try{
            siInfo.load(inputStream);
            switch (server){
                case SDA_SERVER : soConnection = siInfo.getProperty(SDA_SERVER);
                    break;
                case SI_SERVER : soConnection = siInfo.getProperty(SI_SERVER);
                    break;
                case SO_SERVER : soConnection = siInfo.getProperty(SO_SERVER);
                    break;
                default:  soConnection = siInfo.getProperty(SI_SERVER);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soConnection;
    }

}
