package com.pineone.icbms.so.device.proxy;

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
    public ResultMessage deviceControlRequest(String requestUrl,String requestBody) {
        //
        logger.info(LogPrint.outputInfoLogPrint() + "RequestUri = " + requestUrl + "RequestBody = " + requestBody);
        logger.debug("RequestUri = " + requestUrl + "RequestBody = " + requestBody);
        ResultMessage resultMessage = new ResultMessage();

        String responseData = clientService.requestPostService(requestUrl, requestBody);
        logger.debug("ResponseData = " + responseData);
        try {
            resultMessage = mapper.readValue(responseData, ResultMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            resultMessage.set_resultCode(ClientProfile.RESPONSE_FIALURE_CODE);
        } finally {
            return resultMessage;
        }
    }

}
