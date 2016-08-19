package com.pineone.icbms.so.virtualobject.proxy;

import org.codehaus.jackson.map.ObjectMapper;

import com.pineone.icbms.so.device.util.ClientService;

public class VirtualObjectSDAProxy implements VirtualObjectProxy{
    /**
     * SDA에 DeviceID와 Service로 Functionality를 얻는다.
     */

    private ClientService clientService = new ClientService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String findFunctionality(String requestUri, String body){
        //
        String responseData = clientService.requestPostService(requestUri, body);

        return responseData;
    }

}
