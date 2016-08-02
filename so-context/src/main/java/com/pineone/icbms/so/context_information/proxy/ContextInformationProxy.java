package com.pineone.icbms.so.context_information.proxy;

import com.pineone.icbms.so.context_information.entity.ContextInformation;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface ContextInformationProxy {
    String registerContextInformation(ContextInformation contextInformation);
    List<String> retrieveContextInformationListFromSDA();
    ContextInformation retrieveGeneralContextDetail(String contextName);
}
