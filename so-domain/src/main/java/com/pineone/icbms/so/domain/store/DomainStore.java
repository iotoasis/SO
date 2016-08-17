package com.pineone.icbms.so.contextmodel.store;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface ContextModelStore {
    //
    void createContextModel(ContextModel contextModel);
    List<ContextModel> retrieveContextModelList();
    ContextModel retrieveContextModelDetail(String contextModelName);
}
