package com.pineone.icbms.so.context.contextmodel.itf.external.sda;

import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.util.address.ContextAddress;
import com.pineone.icbms.so.context.util.http.ClientService;
import com.pineone.icbms.so.context.util.conversion.DataConversion;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
 * NOTE: SDA 인터페이스 이용, 도메인 관련
 */
public class SdaController_Domain {

    ContextAddress contextAddress;
    ClientService clientService;

    public static SdaController_Domain newSdaController_Domain(){
        SdaController_Domain sdaController_domain = new SdaController_Domain();
        return sdaController_domain;
    }

    //NOTE: SDA 에서 제공하는 Domain_SDA 조회
    public List<Object> retrieveDomainFromSDA(){
        List<Object> domainTypeList = new ArrayList<>();
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService
                (contextAddress.getAddress() + AddressStore.REGISTER_GENERALCONTEXT);
        String readData = DataConversion.responseDataToString(message);
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(readData);

            // NOTE: "domain"을 JSON 데이터 안의 키워드로 확인 후 변경 필요 !
            JSONArray domainArray = (JSONArray) jsonObject.get("domain");

            for(int i=0; i<domainArray.size(); i++){
                JSONObject domainObject = (JSONObject) domainArray.get(i);
                domainTypeList.add(domainObject);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return domainTypeList;
    }
}
