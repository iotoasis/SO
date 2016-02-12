package com.pineone.icbms.so.interfaces.sda.client;

import com.pineone.icbms.so.iot.util.service.DataConversion;
import com.pineone.icbms.so.util.restful.ClientService;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

/**
 * Created by Melvin on 2016. 1. 7..
 * SDA 에 조회하기 위한 HttpResponseMessage를 사용하고 검증
 */


public class CollectorController {

    public String getDevice(String getDataURL) {
        ClientService clientService = new ClientService();
//ArrayList<String> deviceInfo = ideviceInfo.getDeviceInfo(ontologyRef,location);

        IHttpResponseMessage httpResponseMessage = clientService.requestGetService(getDataURL);

        String devicesData = null;
        String message = null;

        /**
         * 상태코드확인후 정상일 때
         */
        if(httpResponseMessage.getStatusCode() ==200 ) {
            /**
             * 수신한 데이터에서 body부분만 추출
             */
            devicesData = DataConversion.responseDataToString(httpResponseMessage);
//            devicesData = new String(httpResponseMessage.getBodyByteArray());

            return devicesData;
        }


        else{
//            message = "{\"content\": [{ \"msg\": \" StatusCode = " + httpResponseMessage.getStatusCode() + "\"}]}";
//            message = new String("error");
//            return message;
            /** TODO : Error 후 정책은?
            throw new NotExistDataException();
        */
            return null;
        }
    }

    /**
     * {"code":200,"message":"OK",
     * "content":"[{\"uri\":\"http://www.pineone.com/herit-in/herit-cse/DoorLock_LB0001DL0001\"},
     * {\"uri\":\"http://www.pineone.com/herit-in/herit-cse/DoorLock_LB0001DL0002\"},
     * {\"uri\":\"http://www.pineone.com/herit-in/herit-cse/Siren_LB0001SR0001\"}]"}
     */
}
