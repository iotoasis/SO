package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.util.ClientProfile;
import com.pineone.icbms.so.device.util.ClientService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceSDAProxy implements DeviceICollectionProxy {
    /**
     * SDA 데이터 요청
     * 1. Domain 조회(get)
     * 2. Device By ID 조회(get)
     * 3. Device By Domain 조회(get)
     * 4. Device service 조회(get)
     * 5. Device operation 조회(POST)
     */

    private ClientService clientService = new ClientService();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 1. Domain 조회(get)
     * @param requestUri
     * @return
     */
    @Override
    public List<String> findDomain(String requestUri){
        //
        List<String> domainList = new ArrayList<>();

        String responseData = clientService.requestGetService(requestUri);

        try {
            domainList = mapper.readValue(responseData, new TypeReference<List<String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return domainList;
    }

    /**
     * 2. Device By ID 조회(get)
     * @param requestUri
     * @return
     */
    @Override
    public Device findDeviceByID(String requestUri){
        //
        Device device = null;

        String responseData = clientService.requestGetService(requestUri);

        try {
            device = mapper.readValue(responseData,Device.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        return device;
    }

    /**
     * 3. Device By Domain 조회(get)
     * @param requestUri
     * @return
     */
    @Override
    public List<Device> findDeviceByDomain(String requestUri){
        List<Device> deviceList = new ArrayList<>();

        String responseData = clientService.requestGetService(requestUri);

        try {
            deviceList = mapper.readValue(responseData, new TypeReference<List<Device>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    /**
     * 4. Device service 조회(get)
     * @param requestUri
     * @return
     */
    @Override
    public List<String> findDeviceServiceList(String requestUri){
        //
        List<String> deviceFunctionalityList = new ArrayList<>();

        String responseData = clientService.requestGetService(requestUri);

        try {
            deviceFunctionalityList = mapper.readValue(responseData, new TypeReference<List<String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deviceFunctionalityList;
    }

    /**
     * 5. Device operation 조회(Post)
     */

    @Override
    public String findDeviceOperation(String deviceId, String deviceService) {

        String requestUri = ClientProfile.SDA_DATAREQUEST_URI + ClientProfile.SDA_DEVICE + ClientProfile.SDA_DEVICE_OPERATION;
        JSONObject obj = new JSONObject();
        obj.put("deviceId", deviceId);
        obj.put("deviceService", deviceService);
        String requestData = obj.toString();

        String responseData = clientService.requestPostService(requestUri,requestData);
        return responseData;
    }

}
