package com.pineone.icbms.so.servicemodel.store.mongo;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * NOTE: Mongo DB를 - ServiceModel 정보 연결
 */
@Repository
public class ServiceModelStoreMongoImpl implements ServiceModelStore {

    public static final Logger logger = LoggerFactory.getLogger(ServiceModelStoreMongoImpl.class);

    @Autowired
    ServiceModelRepository serviceModelRepository;

    //NOTE : SM 등록
    @Override
    public void createServiceModel(ServiceModel serviceModel) {
        ServiceModelDataObject serviceModelDataObject = serviceModelToDataObject(serviceModel);
        serviceModelRepository.save(serviceModelDataObject);
    }

    //NOTE: SM 리스트 조회
    @Override
    public List<ServiceModel> retrieveServiceModelList() {
        List<ServiceModelDataObject> serviceModelDataObjectList = serviceModelRepository.findAll();
        List<ServiceModel> serviceModelList = new ArrayList<>();
        for(ServiceModelDataObject serviceModelDataObject : serviceModelDataObjectList){
            serviceModelList.add(dataObjectToServiceModel(serviceModelDataObject));
        }
        return serviceModelList;
    }

    //NOTE : SM 개별 조회
    @Override
    public ServiceModel retrieveServiceModelDetail(String serviceModelId) {
        ServiceModelDataObject serviceModelDataObject = serviceModelRepository.findOne(serviceModelId);
        ServiceModel serviceModel = dataObjectToServiceModel(serviceModelDataObject);
        return serviceModel;
    }

    @Override
    public String retrieveServiceModelId(String servicemodelName) {
        ServiceModelDataObject serviceModelDataObject = serviceModelRepository.findByname(servicemodelName);
        return serviceModelDataObject.getId();
    }

    @Override
    public ServiceModel retrieveServiceModelDetailByDescription(String description) {
        ServiceModelDataObject serviceModelDataObject = serviceModelRepository.findByDescription(description);
        logger.debug("ServiceModel = " + dataObjectToServiceModel(serviceModelDataObject));
        return dataObjectToServiceModel(serviceModelDataObject);
    }

    private ServiceModelDataObject serviceModelToDataObject(ServiceModel serviceModel) {
        if(serviceModel == null) return null;
        return new ServiceModelDataObject(serviceModel.getId(), serviceModel.getName(), serviceModel.getServiceIdList());
    }

    private ServiceModel dataObjectToServiceModel(ServiceModelDataObject serviceModelDataObject){
        if(serviceModelDataObject == null) return null;
        return new ServiceModel(serviceModelDataObject.getId(), serviceModelDataObject.getName(), serviceModelDataObject.getServiceIdList(), serviceModelDataObject.getCreateTime(),serviceModelDataObject.getModifiedTime(),serviceModelDataObject.getLocation(), serviceModelDataObject.getDescription());
    }
}
