package com.pineone.icbms.so.servicemodel.store.map;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceModelMapStore implements ServiceModelStore {

    private static ServiceModelMapStore instance;
    private ServiceModelMapStore(){};
    private Map<String, ServiceModel> serviceModelStore = new HashMap<>();

    //NOTE: DB에 ServiceModel 데이터 생성
    @Override
    public void createServiceModel(ServiceModel serviceModel) {
        //
        serviceModelStore.put(serviceModel.getName(), serviceModel);
    }

    //NOTE: DB 에서 ServiceModel List 조회
    @Override
    public List<ServiceModel> retrieveServiceModelList() {
        List<ServiceModel> serviceModelList = new ArrayList<>();
        for(String key : serviceModelStore.keySet()){
            serviceModelList.add(serviceModelStore.get(key));
        }
        return serviceModelList;
    }

    //NOTE: DB 에서 ServiceModel 상세 조회
    @Override
    public ServiceModel retrieveServiceModelDetail(String serviceModelName) {
        ServiceModel serviceModel = serviceModelStore.get(serviceModelName);
        return serviceModel;
    }

    @Override
    public String retrieveServiceModelId(String serviceModelName) {
        return null;
    }

    public static ServiceModelMapStore getInstance(){
        if(instance == null)
            instance = new ServiceModelMapStore();
        return instance;
    }
}
