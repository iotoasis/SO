package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.device.util.ClientService;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class DeviceSIProxy implements DeviceProxy{

    /**
     * The actual device control request.
     * 1, Device 제어(post)
     * @param requestUrl
     * @param requestBody
     * @return
     */

    private ClientService clientService = new ClientService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String deviceControlRequest(String requestUrl,String requestBody) {
        //
        ResultMessage resultMessage = new ResultMessage();

        String responseData = clientService.requestPostService(requestUrl, requestBody);
        try {
            resultMessage = mapper.readValue(responseData, ResultMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            resultMessage.set_resultCode(ClientProfile.RESPONSE_FIALURE_CODE);
        } finally {
            return resultMessage.get_resultCode();
        }
    }

    @Override
    public List<String> findDomain(String requestUri) {
        return null;
    }

    @Override
    public Device findDeviceByID(String requestUri) {
        return null;
    }

    @Override
    public List<Device> findDeviceByDomain(String requestUri) {
        return null;
    }

    @Override
    public List<String> findDeivceFunctionality(String requestUri) {
        return null;
    }

}
