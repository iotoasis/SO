package com.pineone.icbms.so.contextmodel.logic;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.domain.entity.Domain;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface ContextModelLogic {
    //
    List<String> retrieveContextInformationNameList();
    List<ContextType> retrieveContextTypeList();
    String registerContextModel(ContextModel contextModel);
    List<String> retrieveContextModelNameList();
    ContextModel retrieveContextModelDetail(String contextModelName);
    List<Domain> isHappenContextModel(String contextModelName);
    String retrieveContextModelType(String contextModelName);
    String useQueueSaveContextModel(ContextModel contextModel);
    ContextModel retrieveQueueData();
}
