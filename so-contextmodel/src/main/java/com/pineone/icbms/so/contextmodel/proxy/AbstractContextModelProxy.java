package com.pineone.icbms.so.contextmodel.proxy;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.domain.entity.Domain;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 18..
 */
public abstract class AbstractContextModelProxy implements ContextModelProxy {

    @Override
    public String registerContextModel(ContextModel contextModel){
        return null;
    }

    @Override
    public List<ContextModel> retrieveContextModelListFromSDA(){
        return null;
    };

    @Override
    public ContextModel retrieveContextModelDetail(String contextModelName){
        return null;
    };

    @Override
    public List<Domain> retrieveContextModelEvent(String contextModelName){
        return null;
    }

    @Override
    public List<String> retrieveContextInformationNameList() {
        return null;
    }

    @Override
    public List<Domain> retrieveDomainList() {
        return null;
    }
}
