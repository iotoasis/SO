package com.pineone.icbms.so.service.proxy;

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

import java.util.List;

/**
 * Created by melvin on 2016. 10. 13..
 */
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
        // TODO : SDA 연결주소 협의 , RequestData 형태 협의
        // TODO : Session의 Location ID 필요.

        String location = "";
        if(session.isExistSessionData(DefaultSession.LOCATION_ID)){
            String responseLocation =  session.getSessionData().get(DefaultSession.LOCATION_ID);
            List<String> locationList = DataConversion.stringDataToList(responseLocation);
            location = locationList.get(0);
        } else {
            throw new BadRequestException();
        }

        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress()  + "cm-lack-equipment-count/?p=" + location );
        if(message.getStatusCode() == 200) {
            System.out.println(message.getBodyByteArray().toString());
            logger.debug("ResponseMessage : " + message);
            String readData = DataConversion.responseDataToString(message);
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
}
