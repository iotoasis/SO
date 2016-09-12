package com.pineone.icbms.so.virtualobject.proxy;

import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.device.util.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VirtualObjectSDAProxy implements VirtualObjectProxy{
    /**
     * SDA에 DeviceID와 Service로 Functionality를 얻는다.
     */

    public static final Logger logger = LoggerFactory.getLogger(VirtualObjectSDAProxy.class);

    private ClientService clientService = new ClientService();

    @Override
    public String findFunctionality(String deviceId, String deviceService){
        logger.info(LogPrint.outputInfoLogPrint());
        //
        String requestUri = ClientProfile.SDA_DATAREQUEST_URI + ClientProfile.SDA_DEVICE;
        JSONObject obj = new JSONObject();
        obj.put("deviceId", deviceId);
        obj.put("deviceService", deviceService);
        String requestData = obj.toString();

        String responseData = clientService.requestPostService(requestUri, requestData);

        return responseData;
    }

}
