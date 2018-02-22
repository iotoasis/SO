package com.pineone.icbms.so.interfaces.si.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pineone.icbms.so.interfaces.si.model.DeviceControlMessage;
import com.pineone.icbms.so.interfaces.si.model.DeviceSubscriptionData;
import com.pineone.icbms.so.interfaces.si.model.LWM2MDeviceControl;
import com.pineone.icbms.so.interfaces.si.model.ResultMessage;
import com.pineone.icbms.so.interfaces.si.ref.SIAddressStore;
import com.pineone.icbms.so.interfaces.si.ref.ClientProfile;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.http.ClientServiceTimeout;
import com.pineone.icbms.so.util.itf.address.AddressCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//SI 연동 인터페이스 기능 구현
public class DeviceSIProxy implements DeviceControlProxy {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    private ClientServiceTimeout clientServiceTimeout = new ClientServiceTimeout();
    private ObjectMapper mapper = new ObjectMapper();

    AddressCollector addressCollector = new AddressCollector();




    @Override
    public ResultMessage deviceControlRequest(String requestUrl, DeviceControlMessage deviceControlMessage) {
        //
        String requestBody = new Gson().toJson(deviceControlMessage);
        log.debug("deviceControlRequest : requestBody {}", requestBody);
        String responseData = clientServiceTimeout.requestPostServiceReceiveString2(requestUrl, requestBody, true); //true=timeout
        
        log.debug("deviceControlRequest : responseData {}", responseData);
        ResultMessage resultMessage = parsingResultMessage(responseData);
        return resultMessage;
    }

    @Override
    public ResultMessage lwm2mDeviceControlRequest(String requestUrl, DeviceControlMessage deviceControlMessage, LWM2MDeviceControl lwm2MDeviceControl) {
        //
        String lwm2mCon = DataConversion.base64encoding(new Gson().toJson(lwm2MDeviceControl));
        deviceControlMessage.setCon(lwm2mCon);
        String requestBody = new Gson().toJson(deviceControlMessage);
        String responseData = clientServiceTimeout.requestPostServiceReceiveString2(requestUrl, requestBody, true); //true=timeout
        ResultMessage resultMessage = parsingResultMessage(responseData);
        return resultMessage;
    }

    @Override
    public String deviceSubscriptionRequest(String deviceUri, String commandId) {
        //
        DeviceSubscriptionData deviceSubscriptionData = new DeviceSubscriptionData();
        deviceSubscriptionData.set_notificationUri(addressCollector.getServerAddress(AddressCollector.SO_SERVER) + SIAddressStore.SO_DEVICE_STATUS);
        deviceSubscriptionData.set_uri(deviceUri);
        deviceSubscriptionData.set_commandId(commandId);

        String requestBody = new Gson().toJson(deviceSubscriptionData);

        String responseData = clientServiceTimeout.requestPostServiceReceiveString2(addressCollector.getServerAddress(AddressCollector.SI_SERVER) + SIAddressStore.SI_SUBSCRIPTION_URI, requestBody);
        // ResponseData{ "code" : "2000", "message" : "", "content" : "" }
        ResultMessage resultMessage = parsingResultMessage(responseData);
        return resultMessage.getCode();
    }

    @Override
    public String deviceSubscriptionReleaseRequest(String deviceUri) {
        //
        DeviceSubscriptionData deviceSubscriptionData = new DeviceSubscriptionData();
        deviceSubscriptionData.set_uri(deviceUri);

        String requestBody = new Gson().toJson(deviceSubscriptionData);
        String responseData = clientServiceTimeout.requestPostServiceReceiveString2(addressCollector.getServerAddress(AddressCollector.SI_SERVER) + SIAddressStore.SI_SUBSCRIPTION_RELEASE_URI, requestBody);
        ResultMessage resultMessage = parsingResultMessage(responseData);
        return resultMessage.getCode();
    }

    private ResultMessage parsingResultMessage(String response){
        ResultMessage resultMessage = new ResultMessage();
        try {
        	if (response==null) {
        		resultMessage.setCode(ClientProfile.RESPONSE_FAILURE_CODE);
    			resultMessage.setMessage("RESPONSE_FAILURE");
        	}
        	else {
        		resultMessage = mapper.readValue(response, ResultMessage.class);
        	}
        } catch (IOException e) {
            e.printStackTrace();
            resultMessage.setCode(ClientProfile.RESPONSE_FAILURE_CODE);
        }
        return resultMessage;
    }
}
