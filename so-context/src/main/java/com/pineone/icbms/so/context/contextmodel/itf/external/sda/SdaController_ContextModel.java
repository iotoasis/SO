package com.pineone.icbms.so.context.contextmodel.itf.external.sda;

import com.pineone.icbms.so.context.contextmodel.ContextModel;
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
 * Created by melvin on 2016. 7. 26..
 */
public class SdaController_ContextModel {

    private ClientService clientService;
    private ContextAddress contextAddress;

    public static SdaController_ContextModel newSdaController_ContextModel(){
        SdaController_ContextModel sdaController_contextModel = new SdaController_ContextModel();
        return sdaController_contextModel;
    }

    //NOTE: SDA 에 GeneralContext 등록
    public String registerContextModel(ContextModel contextModel){
        //
        String sendData = DataConversion.objectToString(contextModel);
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestPostService
                (contextAddress.getAddress() + AddressStore.REGISTER_CONTEXTMODEL, sendData);
        String response = DataConversion.responseDataToString(message);
        return response;
    }

    //NOTE: SDA 에서 ContextModelList 를 조회하기 위해 사용
    public List<Object> retrieveContextModelListFromSDA() {
        //
        List<Object> contextModelList = new ArrayList<>();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CONTEXTMODEL);
        String readData = DataConversion.responseDataToString(message);
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(readData);

            // NOTE: "contextmodel" 을 JSON 데이터 안의 키워드로 확인 후 변경 필요 !
            JSONArray contextModelArray = (JSONArray) jsonObject.get("contextmodel");

            for(int i=0; i<contextModelArray.size(); i++){
                JSONObject contextModelObject = (JSONObject) contextModelArray.get(i);
                contextModelList.add(contextModelObject);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return contextModelList;
    }

    //NOTE : SDA 의 ContextModel 상세조회
    public ContextModel retrieveContextModelDetail(String contextModelName) {
        //
        ContextModel contextModel = ContextModel.newContextModel();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CONTEXTMODEL_DETAIL + contextModelName);
        ObjectMapper objectMapper = new ObjectMapper();
        String readData = DataConversion.responseDataToString(message);
        try {
            contextModel = objectMapper.readValue(readData, ContextModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contextModel;
    }
}
