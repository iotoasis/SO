package com.pineone.icbms.so.service.store.map;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.store.ServiceStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melvin on 2016. 8. 8..
 * NOTE: MAP 을 이용한 ContextInformation Create , Retrieve 기능 구현
 */


public class ServiceMapStore implements ServiceStore {

    private static ServiceMapStore instance;
    private ServiceMapStore(){};
    private Map<String, Service> serviceMapStore = new HashMap<>();

    // NOTE: DB에 Service 데이터 생성
    @Override
    public void createService(Service service) {
        //
        serviceMapStore.put(service.getName(), service);
    }

    //NOTE: DB 에서 ServiceList 조회
    @Override
    public List<Service> retrieveServiceList() {
        //
        List<Service> serviceList = new ArrayList<>();
        for(String key : serviceMapStore.keySet()){
            serviceList.add(serviceMapStore.get(key));
        }
        return serviceList;
    }

    //NOTE : DB 에서 Service 조회
    @Override
    public Service retrieveServiceDetail(String serviceName) {
        Service service = serviceMapStore.get(serviceName);
        return service;
    }

    @Override
    public void updateService(Service service) {

    }

    public static ServiceMapStore getInstance(){
        if(instance == null)
            instance = new ServiceMapStore();
        return instance;
    }
}
