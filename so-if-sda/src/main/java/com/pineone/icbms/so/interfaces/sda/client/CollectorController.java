package com.pineone.icbms.so.interfaces.sda.client;

import com.pineone.icbms.so.util.restful.ClientService;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

/**
 * Make HttpResponseMessage to get necessary information<BR/>
 * Created by Melvin on 2016. 1. 7..<BR/>

 */


public class CollectorController {

//    private final Logger log = LoggerFactory.getLogger(CollectorController.class);

    public String getDevice(String getDataURL) throws NotExistDataException {
        ClientService clientService = new ClientService();
//ArrayList<String> deviceInfo = ideviceInfo.getDeviceInfo(ontologyRef,location);

        IHttpResponseMessage httpResponseMessage = clientService.requestGetService(getDataURL);

        String devicesData = null;
        String message = null;

        /**
         * Check HttpResponseMessage's statusCode</BR>
         * 200 : get Http Body </BR>
         * else : Make Exception </BR>
         */
        if(httpResponseMessage.getStatusCode() ==200 ) {

//        log.info("[Get Data from SDA]");
//            devicesData = DataConversion.responseDataToString(httpResponseMessage);
            devicesData = new String(httpResponseMessage.getBodyByteArray());
//        log.info(devicesData);
            return devicesData;
        }


        else{
//            message = "{\"content\": [{ \"msg\": \" StatusCode = " + httpResponseMessage.getStatusCode() + "\"}]}";
//            message = new String("error");
//            return message;

            throw new NotExistDataException();
        }
    }

    /**
     * {"code":200,"message":"OK", <BR/>
     * "content":"[{\"uri\":\"http://www.pineone.com/herit-in/herit-cse/DoorLock_LB0001DL0001\"}, <BR/>
     * {\"uri\":\"http://www.pineone.com/herit-in/herit-cse/DoorLock_LB0001DL0002\"}, <BR/>
     * {\"uri\":\"http://www.pineone.com/herit-in/herit-cse/Siren_LB0001SR0001\"}]"} <BR/>
     */
}
