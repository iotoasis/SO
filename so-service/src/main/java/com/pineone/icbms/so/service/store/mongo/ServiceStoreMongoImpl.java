package com.pineone.icbms.so.service.store.mongo;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.store.ServiceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 22..
 */
@Repository
public class ServiceStoreMongoImpl implements ServiceStore {

    @Autowired
    ServiceRepository serviceRepository;

    //NOTE : Service 등록
    @Override
    public void createService(Service service) {
        ServiceDataObject serviceDataObject = serviceToDataObject(service);
        serviceRepository.save(serviceDataObject);
    }

    //NOTE: Service List 조회
    @Override
    public List<Service> retrieveServiceList() {
        List<ServiceDataObject> serviceDataObjectList = serviceRepository.findAll();
        List<Service> serviceList = new ArrayList<>();
        for (ServiceDataObject serviceDataObject : serviceDataObjectList){
            serviceList.add(dataObjectToService(serviceDataObject));
        }
        return serviceList;
    }

    //NOTE : Service 개별 조회
    @Override
    public Service retrieveServiceDetail(String serviceId) {
        ServiceDataObject serviceDataObject = serviceRepository.findOne(serviceId);
        Service service = dataObjectToService(serviceDataObject);
        return service;
    }

    private ServiceDataObject serviceToDataObject(Service service) {
        if (service == null) return null;
        return new ServiceDataObject(service.getId(), service.getName(), service.getVirtualObjectId(), service.getVirtualObjectService(), service.getStatus());
    }

    private Service dataObjectToService(ServiceDataObject serviceDataObject){
        if(serviceDataObject == null) return null;
        return new Service(serviceDataObject.getId(), serviceDataObject.getName(), serviceDataObject.getDeviceObjectId(),
                serviceDataObject.getConceptServiceId(), serviceDataObject.getStatus());
    }
}
