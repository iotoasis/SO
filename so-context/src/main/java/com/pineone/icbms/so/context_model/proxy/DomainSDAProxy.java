package com.pineone.icbms.so.context_model.proxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.http.ClientService;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: SDA 인터페이스 이용, 도메인 관련 (예비)
 */
public class DomainSDAProxy {

    ContextAddress contextAddress;
    ClientService clientService;

    public static DomainSDAProxy newDomainProxy(){
        DomainSDAProxy domainProxy = new DomainSDAProxy();
        return domainProxy;
    }

    //NOTE: SDA 에서 제공하는 Domain_SDA 조회
    public List<Domain> retrieveDomainFromSDA(){
        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService
                (contextAddress.getAddress() + AddressStore.REGISTER_CONTEXTINFORMATION);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<List<Domain>>(){}.getType();
        List<Domain> domainList = new Gson().fromJson(readData,type);
        return domainList;
    }
}
