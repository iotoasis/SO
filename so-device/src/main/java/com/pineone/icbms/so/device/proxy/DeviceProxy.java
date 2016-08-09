package com.pineone.icbms.so.device.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pineone.icbms.so.device.util.ClientService;
import org.codehaus.jackson.map.ObjectMapper;

import com.pineone.icbms.so.entity.ActualCommand;
import com.pineone.icbms.so.entity.DeviceModel;
import com.pineone.icbms.so.entity.Operation;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.util.ClientProfile;

public class DeviceProxy {
    /**
     * SDA 데이터 요청
     * 1. Domain 조회
     * 2. Device By ID 조회
     * 3. Device By Domain 조회
     * 4. Device AspectOperation 조회
     */

    private ClientService clientService = new ClientService();

    /**
     * Device 조회
     * @param requestUri
     * @return
     */
    public DeviceModel findDeviceData(String requestUri){
        DeviceModel deviceModel = null;

        // 해당 url로 client로 SDA 요청
        // 받은 데이터를 DeviceModel로 매핑 후 던짐.


        return deviceModel;
    }


    /**
     * Device 기능(Operation) 조회
     * @param requestUrl
     * @return
     */
    public List<Operation> findDeviceFeature(String requestUrl){

        List<Operation> operation = new ArrayList<>();

        // 해당 데이터로 client로 SDA 요청
        // 받은 데이터를 Operation으로 매핑 후 던져줌.

        return operation;
    }

    /**
     * Device ActualCommand 조회
     * @param requestUrl
     * @return
     */
    public List<ActualCommand> findActualCommand(String requestUrl){
        List<ActualCommand> commandList = new ArrayList<>();

        // SDA에게 실물 데이터를 물어봄.
        // ActualCommand의 list들이 전달됨. Json.
        // 매핑해서 전달.




        return commandList;
    }




    /**
     * The actual device control request.
     * @param requestUrl
     * @param requestBody
     * @return
     */
    public String DeviceControlRequest(String requestUrl,String requestBody) {
        //
        ObjectMapper mapper = new ObjectMapper();
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

    public Object mappingData(String data,Class T){
        ObjectMapper mapper = new ObjectMapper();
        Object o = null;
        try {
            o = mapper.readValue(data, T);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return o;
    }


}
