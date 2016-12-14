package com.pineone.icbms.so.domain.proxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.address.ContextAddress;
import com.pineone.icbms.so.util.http.ClientService;
import com.pineone.icbms.so.util.logprint.LogPrint;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: SDA 인터페이스 이용, 도메인 관련 (예비)
 */

@Service
public class DomainSDAProxy {

    public static final Logger logger = LoggerFactory.getLogger(DomainProxy.class);

    @Autowired
    ContextAddress contextAddress;

    @Autowired
    ClientService clientService;

//    public static DomainSDAProxy newDomainProxy(){
//        DomainSDAProxy domainProxy = new DomainSDAProxy();
//        return domainProxy;
//    }

    //NOTE: SDA 에서 제공하는 Domain_SDA 조회
    public List<Domain> retrieveDomainFromSDA(){
        //
        logger.info(LogPrint.outputInfoLogPrint());
//        contextAddress = ContextAddress.newContextAddress();
        IHttpResponseMessage message = clientService.requestGetService
                (contextAddress.getServerAddress(ContextAddress.SDA_SERVER) + AddressStore.REGISTER_CONTEXTINFORMATION);
        String readData = new Gson().toJson(message);
        Type type = new TypeToken<List<Domain>>(){}.getType();
        List<Domain> domainList = new Gson().fromJson(readData,type);
        return domainList;
    }
}
