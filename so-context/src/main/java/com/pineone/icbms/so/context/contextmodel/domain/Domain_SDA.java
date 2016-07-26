package com.pineone.icbms.so.context.contextmodel.domain;

import com.pineone.icbms.so.context.contextmodel.itf.external.sda.SdaController_Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
 * NOTE: 플랫폼에서 제공하는 Domain_SO 을 관리하기 위한 객체 , SDA 의 도메인을 조회할 때 사용
 */
public class Domain_SDA implements Domain {

    private String name;
    private String uri;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public static Domain_SDA newDomain(){
        Domain_SDA domainUsingSDA = new Domain_SDA();
        return domainUsingSDA;
    }

    //NOTE: SDA 에 저장되어 있는 DomainList 조회
    public List<Domain> retrieveDomainList(){
        //
        List<Object> objectList = SdaController_Domain.newSdaController_Domain().retrieveDomainFromSDA();
        List<Domain> domainList = new ArrayList<>();
        for(Object object : objectList){
            domainList.add((Domain) object);
        }
        return domainList; //domainUsingSDAList
    }
}
