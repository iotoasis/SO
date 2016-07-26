package com.pineone.icbms.so.context.general.itf.database;

import com.pineone.icbms.so.context.DatabaseInterface;
import com.pineone.icbms.so.context.general.GeneralContext;

import java.util.*;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE: DB 생성 전까지 MAP 사용.
 */
public class MapController_GeneralContext implements DatabaseInterface {

    private static MapController_GeneralContext instance;
    private MapController_GeneralContext(){}
    private Map<String, GeneralContext> generalContextStore = new HashMap<String, GeneralContext>();

    //NOTE: DB에 GeneralContext 데이터 생성
    public void createGeneralContext(GeneralContext generalContext){
        //
        generalContextStore.put(generalContext.getName(),generalContext);
    }

    //NOTE: DB 에서 GeneralContextList 조회
    public List<GeneralContext> retrieveGeneralContextList() {
        //
        List<GeneralContext> generalContextList = new ArrayList<>();
        for (String key : generalContextStore.keySet()) {
            generalContextList.add(generalContextStore.get(key));
        }
        return generalContextList;
    }

    public static MapController_GeneralContext getInstance(){
        if(instance == null)
            instance = new MapController_GeneralContext();
        return instance;
    }
}

