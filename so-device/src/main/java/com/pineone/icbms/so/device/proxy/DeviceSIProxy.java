package com.pineone.icbms.so.device.proxy;

import com.google.gson.Gson;
import com.pineone.icbms.so.device.entity.DeviceControlMessage;
import com.pineone.icbms.so.device.entity.DeviceSubscriptionData;
import com.pineone.icbms.so.device.entity.LWM2MDeviceControl;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ContextAddress contextAddress;

    public static final Logger logger = LoggerFactory.getLogger(DeviceSIProxy.class);

    @Override
    public ResultMessage deviceControlRequest(String requestUrl, DeviceControlMessage deviceControlMessage) {
        //
        logger.info("<================ Device Control Request Start ================>");
        logger.debug(LogPrint.LogMethodNamePrint() + " | RequestUri = " + requestUrl + " , deviceControlMessage = " + deviceControlMessage.toString());
        String requestBody = new Gson().toJson(deviceControlMessage);
        String responseData = clientService.requestPostServiceReceiveString(requestUrl, requestBody);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device Control Request responseData = " + responseData);
        ResultMessage resultMessage = parsingResultMessage(responseData);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device Control Request Result = " + resultMessage);
        logger.info("<================ Device Control Request End ================>");
        return resultMessage;
    }

    @Override
    public ResultMessage lwm2mDeviceControlRequest(String requestUrl, DeviceControlMessage deviceControlMessage, LWM2MDeviceControl lwm2MDeviceControl) {
        logger.info("<================ LWM2M Device Control Request Start ================>");
        logger.debug(LogPrint.LogMethodNamePrint() + " | RequestUri = " + requestUrl + " , deviceControlMessage = " + deviceControlMessage.toString() + "LWM2MDeviceControl = " + lwm2MDeviceControl.toString());
        String lwm2mCon = DataConversion.base64incoding(new Gson().toJson(lwm2MDeviceControl));
        logger.debug(LogPrint.LogMethodNamePrint() + " | LWM2M Device Con = " + lwm2mCon);
        deviceControlMessage.setCon(lwm2mCon);
        String requestBody = new Gson().toJson(deviceControlMessage);
        String responseData = clientService.requestPostServiceReceiveString(requestUrl, requestBody);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device Control Request responseData = " + responseData);
        ResultMessage resultMessage = parsingResultMessage(responseData);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device Control Request Result = " + resultMessage);
        logger.info("<================ LWM2M Device Control Request End ================>");
        return resultMessage;
    }

    @Override
    public String deviceSubscriptionRequest(String deviceUri, String commandId) {
        //
        logger.info("<================ Device Subscription Request Start ================>");
        logger.debug(LogPrint.LogMethodNamePrint() + " | DeviceUri = " + deviceUri);

        DeviceSubscriptionData deviceSubscriptionData = new DeviceSubscriptionData();
        deviceSubscriptionData.set_notificationUri(contextAddress.getServerAddress(ContextAddress.SO_SERVER) + AddressStore.SO_DEVICE_STATUS);
        deviceSubscriptionData.set_uri(deviceUri);
        deviceSubscriptionData.set_commandId(commandId);

        String requestBody = new Gson().toJson(deviceSubscriptionData);

        String responseData = clientService.requestPostServiceReceiveString(contextAddress.getServerAddress(ContextAddress.SI_SERVER) + AddressStore.SI_SUBSCRIPTION_URI, requestBody);
        // ResponseData{ "code" : "2000", "message" : "", "content" : "" }
        logger.debug(LogPrint.LogMethodNamePrint() + " | Subscription Request : Response Data  = " + responseData);
        ResultMessage resultMessage = parsingResultMessage(responseData);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device Subscription Request Result = " + resultMessage.getCode());
        logger.info("<================ Device Subscription Request End ================>");
        return resultMessage.getCode();
    }

    @Override
    public String deviceSubscriptionReleaseRequest(String deviceUri) {
        //
        logger.info("<================ Device SubscriptionRelease Request Start ================>");
        logger.debug(LogPrint.LogMethodNamePrint() + " | DeviceUri = " + deviceUri);

        DeviceSubscriptionData deviceSubscriptionData = new DeviceSubscriptionData();
        deviceSubscriptionData.set_uri(deviceUri);

        String requestBody = new Gson().toJson(deviceSubscriptionData);
        // DeviceLogic에 생성되거나 제어되면 서브스크립트 걸어야 겠군.
        // 현제 정책이 안되어 있어서. 등록되면 걸지.. 제어시 걸지는 고려 필요.
        String responseData = clientService.requestPostServiceReceiveString(contextAddress.getServerAddress(ContextAddress.SI_SERVER) + AddressStore.SI_SUBSCRIPTION_RELEASE_URI, requestBody);
        ResultMessage resultMessage = parsingResultMessage(responseData);
        logger.debug(LogPrint.LogMethodNamePrint() + " | Device SubscriptionRelease Request Result = " + resultMessage.getCode());
        logger.info("<================ Device SubscriptionRelease Request End ================>");
        return resultMessage.getCode();
    }


    private ResultMessage parsingResultMessage(String response){
        ResultMessage resultMessage = new ResultMessage();
        try {
            resultMessage = mapper.readValue(response, ResultMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            resultMessage.setCode(ClientProfile.RESPONSE_FIALURE_CODE);
        }
        return resultMessage;
    }

}
