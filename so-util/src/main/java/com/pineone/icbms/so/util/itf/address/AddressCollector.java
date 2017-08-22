package com.pineone.icbms.so.util.itf.address;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by melvin on 2017. 4. 5..
 */
//Address 를 관리 - 기본 Address 의 변경을 통합적으로 관리
@Component
public class AddressCollector {
    //
    public static final String SERVER_PROPERTIES = "server.properties";
    public static final String SI_SERVER = "SI_Connection";
    public static final String SO_SERVER = "SO_Connection";
    public static final String SDA_SERVER = "Sda_Connection";

    public String getServerAddress(String server){
        //
        String serverAddress = null;
        Properties serverInfo = new Properties();

        InputStream inputStream = AddressCollector.class.getClassLoader().getResourceAsStream(SERVER_PROPERTIES);

        try{
            serverInfo.load(inputStream);
            switch(server){
                case SI_SERVER : serverAddress = serverInfo.getProperty(SI_SERVER);
                break;
                case SDA_SERVER : serverAddress = serverInfo.getProperty(SDA_SERVER);
                break;
                case SO_SERVER : serverAddress = serverInfo.getProperty(SO_SERVER);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverAddress;
    }
}
