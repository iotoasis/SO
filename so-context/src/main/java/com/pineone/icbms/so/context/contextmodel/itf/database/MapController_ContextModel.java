package com.pineone.icbms.so.context.contextmodel.itf.database;

import com.pineone.icbms.so.context.contextmodel.ContextModel;
import com.pineone.icbms.so.context.DatabaseInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melvin on 2016. 7. 26..
 * NOTE : DB 로 대체할 ContextModel 관련 DatabaseInterface
 */
public class MapController_ContextModel implements DatabaseInterface {
    private static MapController_ContextModel instance;
    private MapController_ContextModel(){}
    private Map<String, ContextModel> contextModelStore = new HashMap<String, ContextModel>();

    //NOTE: DB에 ContextModel 데이터 생성
    public void createContextModel(ContextModel contextModel){
        //
        contextModelStore.put(contextModel.getName(),contextModel);
    }

    //NOTE: DB 에서 ContextModelList 조회
    public List<ContextModel> retrieveContextModelList() {
        //
        List<ContextModel> contextModelList = new ArrayList<>();
        for (String key : contextModelStore.keySet()) {
            contextModelList.add(contextModelStore.get(key));
        }
        return contextModelList;
    }

    public static MapController_ContextModel getInstance(){
        if(instance == null)
            instance = new MapController_ContextModel();
        return instance;
    }
}
