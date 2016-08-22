package com.pineone.icbms.so.contextinformation.store;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: MAP 을 이용한 ContextInformation Create , Retrieve 기능 구현
 */

@Service
public class ContextInformationMapStore implements ContextInformationStore{

    private static ContextInformationMapStore instance;
    private ContextInformationMapStore(){};
    private Map<String, ContextInformation> contextInformationStore = new HashMap<>();

    //NOTE: DB에 ContextInformation 데이터 생성
    @Override
    public void createContextInformation(ContextInformation contextInformation) {
        //
        contextInformationStore.put(contextInformation.getName(), contextInformation);
    }

    //NOTE: DB 에서 ContextInformationList 조회
    @Override
    public List<ContextInformation> retrieveContextInformationList() {
        //
        List<ContextInformation> contextInformationList = new ArrayList<>();
        for(String key : contextInformationStore.keySet()){
            contextInformationList.add(contextInformationStore.get(key));
        }
        return contextInformationList;
    }

    //NOTE: DB에서 ContextInformation 조회
    @Override
    public ContextInformation retrieveContextInformationDetail(String contextName) {
        ContextInformation contextInformation = contextInformationStore.get(contextName);
        return contextInformation;
    }

    public static ContextInformationMapStore getInstance(){
        if(instance == null)
            instance = new ContextInformationMapStore();
        return instance;
    }
}
