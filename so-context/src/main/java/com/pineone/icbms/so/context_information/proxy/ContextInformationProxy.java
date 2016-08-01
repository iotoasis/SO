package com.pineone.icbms.so.context_information.proxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_information.store.ContextInformationMapStore;
import com.pineone.icbms.so.context_information.store.ContextInformationStore;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.http.ClientService;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: ContextInformation Component 에서 제공하는 데이터에 접근하거나 데이터를 이용해서 외부와 연동
 */
public class ContextInformationProxy {

    ClientService clientService = new ClientService();
    ContextAddress contextAddress = ContextAddress.newContextAddress();

    public static ContextInformationProxy newContextInformationProxy(){
        return new ContextInformationProxy();
    }

    // NOTE: ContextInformation Component 의 DB에 접근해서 리스트 조회
    public List<ContextInformation> retrieveContextInformationList(){
        //
        ContextInformationStore contextInformationStore = ContextInformationMapStore.getInstance();
        List<ContextInformation> contextInformationList = contextInformationStore.retrieveContextInformationList();
        return contextInformationList;
    }

    //NOTE: SDA 에 ContextInformation 등록 TODO: Response 논의
    public String registerContextInformation(ContextInformation contextInformation){
        //
        String sendData = DataConversion.objectToString(contextInformation);
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestPostService
                (contextAddress.getAddress() + AddressStore.REGISTER_GENERALCONTEXT, sendData);
        String response = new Gson().toJson(message);
        return response;
    }

    //NOTE : SDA 에서 ContextInformation 조회
    public List<ContextInformation> retrieveContextInformationListFromSDA(){
        //
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_GENERALCONTEXT);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<List<ContextInformation>>(){}.getType();
        List<ContextInformation> contextInformationList = new Gson().fromJson(readData,type);
        return contextInformationList;
    }

    //NOTE : SDA 의 ContextInformation 상세 조회
    public ContextInformation retrieveGeneralContextDetail(String contextName){
        //
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_GENERALCONTEXT + "/" + contextName);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<ContextInformation>(){}.getType();
        ContextInformation contextInformation = new Gson().fromJson(readData,type);
        return contextInformation;
    }
}
