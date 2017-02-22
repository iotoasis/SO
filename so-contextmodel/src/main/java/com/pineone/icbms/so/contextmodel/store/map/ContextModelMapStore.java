package com.pineone.icbms.so.contextmodel.store.map;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.store.ContextModelStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melvin on 2016. 8. 2..
 */

public class ContextModelMapStore {
    private static ContextModelMapStore instance;
    private ContextModelMapStore(){};
    private Map<String, ContextModel> contextModelStore = new HashMap<>();

    //NOTE: DB에 ContextModel 데이터 생성
    public void createContextModel(ContextModel contextModel) {
        //
        contextModelStore.put(contextModel.getName(), contextModel);
    }

    //NOTE: DB 에서 ContextModel List 조회
    public List<ContextModel> retrieveContextModelList() {
        List<ContextModel> contextModelList = new ArrayList<>();
        for(String key : contextModelStore.keySet()){
            contextModelList.add(contextModelStore.get(key));
        }
        return contextModelList;
    }

    //NOTE: DB 에서 ContextModel 상세 조회
    public ContextModel retrieveContextModelDetail(String contextModelName) {
        ContextModel contextModel = contextModelStore.get(contextModelName);
        return contextModel;
    }

    public ContextModel retrieveContextModelDetailByName(String contextModelName) {
        return null;
    }

    public static ContextModelMapStore getInstance(){
        if(instance == null)
            instance = new ContextModelMapStore();
        return instance;
    }
}
