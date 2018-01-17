package com.pineone.icbms.so.util.itf.address;

import org.springframework.stereotype.Component;

import com.pineone.icbms.so.util.Settings2;

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
    public static final String LWM2M_SERVER = "LWM2M_Connection";
    public static final String SI_SERVER = "SI_Connection";
    public static final String SO_SERVER = "SO_Connection";
    public static final String SDA_SERVER = "Sda_Connection";


    public String getServerAddress_old(String server){
        //
        String serverAddress = null;
        Properties serverInfo = new Properties();

        InputStream inputStream = AddressCollector.class.getClassLoader().getResourceAsStream(SERVER_PROPERTIES);

        try{
            serverInfo.load(inputStream);
            switch(server){
                case SI_SERVER : serverAddress = serverInfo.getProperty(SI_SERVER);
                break;
                case LWM2M_SERVER : serverAddress = serverInfo.getProperty(LWM2M_SERVER);
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

    public String getServerAddress(String server){
        //
        String serverAddress = null;

        switch(server){
            case SI_SERVER : 
            				serverAddress = Settings2.getSiConnectionUri();
            				break;
            case LWM2M_SERVER : 
							serverAddress = Settings2.getLwm2mConnectionUri();
							break;
            case SDA_SERVER :
            				serverAddress = Settings2.getSdaConnectionUri();
            				break;
            case SO_SERVER :
							serverAddress = Settings2.getSoConnectionUri();
            				break;
        }
        //데이타가 없을시에 기존 방법을 통한 데이타 취득
		if (serverAddress==null)
			serverAddress = getServerAddress_old(server);

		return serverAddress;
    }
}
