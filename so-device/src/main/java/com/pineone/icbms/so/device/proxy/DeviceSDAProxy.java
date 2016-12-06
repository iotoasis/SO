package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final Logger logger = LoggerFactory.getLogger(DeviceSDAProxy.class);

    @Autowired
    ContextAddress contextAddress;


    /**
     * 1. Domain 조회(get)
     * @param requestUri
     * @return
     */
    @Override
    public List<String> findDomain(String requestUri){
        //
        logger.info(LogPrint.outputInfoLogPrint() + "RequestUri = " + requestUri);
        logger.debug("RequestUri = " + requestUri);
        List<String> domainList = new ArrayList<>();

        String responseData = clientService.requestGetServiceReceiveString(requestUri);
        logger.debug("ResponseData = " + responseData);
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
        logger.info(LogPrint.outputInfoLogPrint() + "RequestUri = " + requestUri);
        logger.debug("RequestUri = " + requestUri);
        Device device = null;

        String responseData = clientService.requestGetServiceReceiveString(requestUri);
        logger.debug("ResponseData = " + responseData);
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
        logger.info((LogPrint.outputInfoLogPrint()) + "RequestUri = " + requestUri);
        logger.debug("RequestUri = " + requestUri);
        List<Device> deviceList = new ArrayList<>();

        String responseData = clientService.requestGetServiceReceiveString(requestUri);
        logger.debug("ResponseData = " + responseData);
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
        logger.info((LogPrint.outputInfoLogPrint()) + "RequestUri = " + requestUri);
        logger.debug("RequestUri = " + requestUri);
        List<String> deviceFunctionalityList = new ArrayList<>();

        String responseData = clientService.requestGetServiceReceiveString(requestUri);
        logger.debug("ResponseData = " + responseData);
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
        logger.info((LogPrint.outputInfoLogPrint())+ "DeviceID = " + deviceId);
        logger.debug("DeviceID = " + deviceId + "DeviceService = " + deviceService);

        String requestUri = contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.SDA_DEVICE + AddressStore.SDA_DEVICE_OPERATION;
        JSONObject obj = new JSONObject();
        obj.put("deviceId", deviceId);
        obj.put("deviceService", deviceService);
        String requestData = obj.toString();

        String responseData = clientService.requestPostServiceReceiveString(requestUri,requestData);
        logger.debug("ResponseData = " + responseData);
        return responseData;
    }

}
