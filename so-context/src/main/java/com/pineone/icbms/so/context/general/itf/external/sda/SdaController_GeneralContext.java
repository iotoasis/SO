package com.pineone.icbms.so.context.general.itf.external.sda;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pineone.icbms.so.context.ContextInterface;
import com.pineone.icbms.so.context.general.GeneralContext;
import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.util.address.ContextAddress;
import com.pineone.icbms.so.context.util.http.ClientService;
import com.pineone.icbms.so.context.util.conversion.DataConversion;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE: SDA 와 연동하기 위한 데이터 관리 및 전송
 */
public class SdaController_GeneralContext implements ContextInterface {

    ClientService clientService = new ClientService();
    ContextAddress contextAddress = ContextAddress.newContextAddress();

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
        String response = new Gson().toJson(message);
        return response;
    }

    //NOTE : SDA 에서 GeneralContext 조회
    public List<GeneralContext> retrieveGeneralContextListFromSDA(){
        //
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_GENERALCONTEXT);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<List<GeneralContext>>(){}.getType();
        List<GeneralContext> generalContextList = new Gson().fromJson(readData,type);
        return generalContextList;
    }

    //NOTE : SDA 의 GeneralContext 상세조회
    public GeneralContext retrieveGeneralContextDetail(String contextName){
        //
        IHttpResponseMessage message = clientService.requestGetService(
                contextAddress.getAddress() + AddressStore.RETRIEVE_GENERALCONTEXT + "/" + contextName);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<GeneralContext>(){}.getType();
        GeneralContext generalContext = new Gson().fromJson(readData,type);
        return generalContext;
    }
}
