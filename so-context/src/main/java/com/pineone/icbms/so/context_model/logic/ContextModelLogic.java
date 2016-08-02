package com.pineone.icbms.so.context_model.logic;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_model.entity.ContextModel;
import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.context_model.ref.ContextType;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface ContextModelLogic {
    List<String> retrieveContextInformationNameList();
    List<Domain> retrieveDomainList();
    List<ContextType> retrieveContextTypeList();
    String registerContextModel(ContextModel contextModel);
    List<String> retrieveContextModelNameList();
    ContextModel retrieveContextModelDetail(String contextModelName);
    List<Domain> isHappenContextModel(ContextModel contextModel);
}
