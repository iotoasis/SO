package com.pineone.icbms.so.device.proxy;

import com.google.gson.Gson;
import com.pineone.icbms.so.device.entity.DeviceControlMessage;
import com.pineone.icbms.so.device.entity.DeviceSubscriptionData;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.device.util.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeviceSIProxy implements DeviceControlProxy {

    /**
     * The actual device control request.
     * 1, Device 제어(post)
     * @param requestUrl
     * @param requestBody
     * @return
     */

    private ClientService clientService = new ClientService();
    private ObjectMapper mapper = new ObjectMapper();

    public static final Logger logger = LoggerFactory.getLogger(DeviceSIProxy.class);

    @Override
    public ResultMessage deviceControlRequest(String requestUrl, DeviceControlMessage deviceControlMessage) {
        //
        logger.info(LogPrint.outputInfoLogPrint() + "RequestUri = " + requestUrl + "deviceControlMessage = " + deviceControlMessage.toString());
        logger.debug("RequestUri = " + requestUrl + "deviceControlMessage = " + deviceControlMessage.toString());
        ResultMessage resultMessage = new ResultMessage();
        String requestBody = new Gson().toJson(deviceControlMessage);
        logger.debug("DeviceControlMessage to JSON = " + requestBody);

        String responseData = clientService.requestPostService(requestUrl, requestBody);
        logger.debug("ResponseData = " + responseData);
        try {
            resultMessage = mapper.readValue(responseData, ResultMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            resultMessage.setCode(ClientProfile.RESPONSE_FIALURE_CODE);
        } finally {
            return resultMessage;
        }

    }

    @Override
    public String deviceSubscriptionRequest(String requestUri, DeviceSubscriptionData deviceSubscriptionData) {
        //
        logger.info(LogPrint.outputInfoLogPrint() + "RequestUri = " + requestUri + "DeviceSubscriptionData = " + deviceSubscriptionData.toString());
        logger.debug("RequestUri = " + requestUri + "DeviceSubscriptionData = " + deviceSubscriptionData.toString());

        String requestBody = new Gson().toJson(deviceSubscriptionData);
        // DeviceLogic에 생성되거나 제어되면 서브스크립트 걸어야 겠군.
        // 현제 정책이 안되어 있어서. 등록되면 걸지.. 제어시 걸지는 고려 필요.

        String responseData = clientService.requestPostService(requestUri, requestBody);
        logger.debug("ResponseData = " + responseData);

        return responseData;
    }
}
