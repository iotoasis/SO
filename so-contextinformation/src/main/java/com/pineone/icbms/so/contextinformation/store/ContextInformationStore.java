package com.pineone.icbms.so.contextinformation.store;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public interface ContextInformationStore {
    //
    void createContextInformation(ContextInformation contextInformation);
    List<ContextInformation> retrieveContextInformationList();
    ContextInformation retrieveContextInformationDetail(String contextId);
}
