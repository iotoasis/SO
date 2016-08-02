package com.pineone.icbms.so.context_model.store;

import com.pineone.icbms.so.context_model.entity.ContextModel;

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
