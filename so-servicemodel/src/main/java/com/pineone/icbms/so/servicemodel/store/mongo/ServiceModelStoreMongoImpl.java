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
 * Created by melvin on 2016. 8. 23..
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
        logger.debug("CreateServiceModel in Data = " + serviceModel.toString());
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
        for(ServiceModel serviceModel : serviceModelList){
            logger.debug("Retrieve ServiceModel List is ServiceModel = " + serviceModel.toString());
        }
        return serviceModelList;
    }

    //NOTE : SM 개별 조회
    @Override
    public ServiceModel retrieveServiceModelDetail(String serviceModelId) {
        logger.debug("RetrieveServiceModel Detail is ServiceModelID = " + serviceModelId);
        ServiceModelDataObject serviceModelDataObject = serviceModelRepository.findOne(serviceModelId);
        ServiceModel serviceModel = dataObjectToServiceModel(serviceModelDataObject);
        logger.debug("RetrieveServiceModel Detail is ServiceModel = " + serviceModel.toString());
        return serviceModel;
    }

    private ServiceModelDataObject serviceModelToDataObject(ServiceModel serviceModel) {
        if(serviceModel == null) return null;
        return new ServiceModelDataObject(serviceModel.getId(), serviceModel.getName(), serviceModel.getServiceIdList());
    }

    private ServiceModel dataObjectToServiceModel(ServiceModelDataObject serviceModelDataObject){
        if(serviceModelDataObject == null) return null;
        return new ServiceModel(serviceModelDataObject.getId(), serviceModelDataObject.getName(), serviceModelDataObject.getServiceIdList());
    }
}
