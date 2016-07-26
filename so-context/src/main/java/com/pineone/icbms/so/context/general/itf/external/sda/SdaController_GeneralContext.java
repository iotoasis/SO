package com.pineone.icbms.so.context.general.itf.external.sda;

import com.pineone.icbms.so.context.ContextInterface;
import com.pineone.icbms.so.context.general.GeneralContext;
import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.util.address.ContextAddress;
import com.pineone.icbms.so.context.util.http.ClientService;
import com.pineone.icbms.so.context.util.conversion.DataConversion;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE: SDA 와 연동하기 위한 데이터 관리 및 전송
 */
public class SdaController_GeneralContext implements ContextInterface {

    ClientService clientService;
    ContextAddress contextAddress;

    public static SdaController_GeneralContext newSdaController(){
        //
        SdaController_GeneralContext sdaControllerGeneralContext = new SdaController_GeneralContext();
        return sdaControllerGeneralContext;
    }

    //NOTE: SDA 에 GeneralContext 등록
    public String registerGeneralContext(GeneralContext generalContext){
        //
        String sendData = DataConversion.objectToString(generalContext);
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestPostService
                (contextAddress.getAddress() + AddressStore.REGISTER_GENERALCONTEXT, sendData);
        String response = DataConversion.responseDataToString(message);
        return response;
    }

    //NOTE : SDA 에서 GeneralContext 조회
    public List<Object> retrieveGeneralContextListFromSDA(){
        //
        List<Object> generalContextList = new ArrayList<>();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_GENERALCONTEXT);
        String readData = DataConversion.responseDataToString(message);
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(readData);

            // NOTE: "context"를 JSON 데이터 안의 키워드로 확인 후 변경 필요 !
            JSONArray generalContextArray = (JSONArray) jsonObject.get("context");

            for(int i=0; i<generalContextArray.size(); i++){
                JSONObject generalContextObject = (JSONObject) generalContextArray.get(i);
                generalContextList.add(generalContextObject);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return generalContextList;
    }
    //NOTE : SDA 의 GeneralContext 상세조회
    public GeneralContext retrieveGeneralContextDetail(String contextName){
        //
        GeneralContext generalContext = GeneralContext.newGeneralContext();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_GENERALCONTEXT_DETAIL + contextName);
        ObjectMapper objectMapper = new ObjectMapper();
        String readData = DataConversion.responseDataToString(message);
        try {
            generalContext = objectMapper.readValue(readData, GeneralContext.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generalContext;
    }

}
