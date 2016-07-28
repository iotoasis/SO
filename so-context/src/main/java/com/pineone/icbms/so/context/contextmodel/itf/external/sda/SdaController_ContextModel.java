package com.pineone.icbms.so.context.contextmodel.itf.external.sda;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pineone.icbms.so.context.contextmodel.ContextModel;
import com.pineone.icbms.so.context.contextmodel.domain.Domain;
import com.pineone.icbms.so.context.contextmodel.domain.Domain_SDA;
import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.util.address.ContextAddress;
import com.pineone.icbms.so.context.util.http.ClientService;
import com.pineone.icbms.so.context.util.conversion.DataConversion;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 26..
 */
public class SdaController_ContextModel {

    private ClientService clientService = new ClientService();
    private ContextAddress contextAddress = ContextAddress.newContextAddress();

    public static SdaController_ContextModel newSdaController_ContextModel(){
        SdaController_ContextModel sdaController_contextModel = new SdaController_ContextModel();
        return sdaController_contextModel;
    }

    //NOTE: SDA 에 GeneralContext 등록
    public String registerContextModel(ContextModel contextModel){
        //
        String sendData = DataConversion.objectToString(contextModel);
        IHttpResponseMessage message = clientService.requestPostService
                (contextAddress.getAddress() + AddressStore.REGISTER_CONTEXTMODEL, sendData);
        String response = DataConversion.responseDataToString(message);
        return response;
    }

    //NOTE: SDA 에서 ContextModelList 를 조회하기 위해 사용
    public List<ContextModel> retrieveContextModelListFromSDA() {
        //
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CONTEXTMODEL);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<List<ContextModel>>(){}.getType();
        List<ContextModel> contextModelList = new Gson().fromJson(readData,type);

        return contextModelList;
    }

    //NOTE : SDA 의 ContextModel 상세조회
    public ContextModel retrieveContextModelDetail(String contextModelName) {
        //
        ContextModel contextModel = ContextModel.newContextModel();
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CONTEXTMODEL + "/" + contextModelName);
        ObjectMapper objectMapper = new ObjectMapper();
        String readData = DataConversion.responseDataToString(message);
        try {
            contextModel = objectMapper.readValue(readData, ContextModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contextModel;
    }

    //NOTE: ContextModel 의 상황이 발생했는지 질의, 발생한 도메인 리스트 수신
    public List<Domain> retrieveContextModelEvent(ContextModel contextModel) {
        //
        String sendData = DataConversion.objectToString(contextModel);
        IHttpResponseMessage message = clientService.requestPostService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_CONTEXTMODEL_EVENT, sendData);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<List<Domain_SDA>>(){}.getType();
        List<Domain> domainList = new Gson().fromJson(readData,type);

        return domainList;
    }
}
