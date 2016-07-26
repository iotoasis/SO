package com.pineone.icbms.so.context.contextmodel.itf.external.sda;

import com.pineone.icbms.so.context.contextmodel.ContextModel;
import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.util.address.ContextAddress;
import com.pineone.icbms.so.context.util.http.ClientService;
import com.pineone.icbms.so.context.util.conversion.DataConversion;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

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
}
