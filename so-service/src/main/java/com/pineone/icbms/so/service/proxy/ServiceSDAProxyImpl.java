package com.pineone.icbms.so.service.proxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.service.entity.SDAData;
import com.pineone.icbms.so.service.entity.TempLocation;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.exception.BadRequestException;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ServiceSDAProxyImpl implements ServiceSDAProxy{

    public static final Logger logger = LoggerFactory.getLogger(ServiceSDAProxyImpl.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContextAddress contextAddress;

    //NOTE : PC Count
    @Override
    public String getPCCountUri(Session session) throws BadRequestException {
        String location = "";
        if(session.isExistSessionData(DefaultSession.LOCATION_ID)){
            String responseLocation =  session.getSessionData().get(DefaultSession.LOCATION_ID);
            List<String> locationList = DataConversion.stringDataToList(responseLocation);
            location = locationList.get(0);
        } else {
            location = ClientProfile.LOCATION_ENGCENTER_616;
        }

        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER)  + ClientProfile.CM_LACK_EQUIPMENT_COUNT + location );
        if(message.getStatusCode() == 200) {
            System.out.println(message.getBodyByteArray().toString());
            logger.debug("ResponseMessage : " + message);
            String readData = clientService.responseDataToString(message);
            return readData;
            /*
            // Response Data Mapping
            Type type = new TypeToken<RequestData>() {
            }.getType();
            RequestData requestData = new Gson().fromJson(readData, type);
            return "";
//            return requestData.getCount();
            */
        }
        else{
            throw new BadRequestException();
        }
    }

    @Override
    public String getTemperatureLookup(Session session) throws BadRequestException {
        String location = "";
        if(session.isExistSessionData(DefaultSession.LOCATION_ID)){
            String responseLocation =  session.getSessionData().get(DefaultSession.LOCATION_ID);
            List<String> locationList = DataConversion.stringDataToList(responseLocation);
            location = locationList.get(0);
        } else {
            location = ClientProfile.LOCATION_CAMPUS_001;
        }

        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getServerAddress(ContextAddress.SDA_SERVER)  + ClientProfile.CM_TEMP + location );
        if(message.getStatusCode() == 200) {
            System.out.println(message.getBodyByteArray().toString());
            logger.debug("ResponseMessage : " + message);
            String readData = clientService.responseDataToString(message);
            // Response Data Mapping
            Type type = new TypeToken<SDAData<TempLocation>>() {
            }.getType();
            SDAData sdaData = new Gson().fromJson(readData, type);
            TempLocation tempLocation = (TempLocation)sdaData.getContents().get(0);
            String tempdata = tempLocation.getCon().substring(ClientProfile.PREFIX_ONTOLOGY.length());
            return tempdata;
        }
        else{
            throw new BadRequestException();
        }

    }
}
