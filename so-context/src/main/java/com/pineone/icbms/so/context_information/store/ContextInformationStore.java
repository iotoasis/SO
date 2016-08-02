package com.pineone.icbms.so.context_information.store;

import com.pineone.icbms.so.context_information.entity.ContextInformation;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public interface ContextInformationStore {

    void createContextInformation(ContextInformation contextInformation);
    List<ContextInformation> retrieveContextInformationList();
    ContextInformation retrieveContextInformationDetail(String contextName);
}
