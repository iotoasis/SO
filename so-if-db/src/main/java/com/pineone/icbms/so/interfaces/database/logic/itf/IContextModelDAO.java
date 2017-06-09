package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ContextModelData;
import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IContextModelDAO {
    //
    ContextModelForDB retrieveContextModel(String id);

    List<ContextModelForDB> retrieveContextModelList();

    ContextModelForDB createContextModel(ContextModelData contextModelData);

    ContextModelForDB updateContextModel(String id, ContextModelData contextModelData);

    String deleteContextModel(String id);

    // SDA 와 연동하는 데이터 정보를 통해 CM 조회
//    ContextModelForDB retrieveContextModelByContextModelAPI(String context_model_api);
}
