package com.pineone.icbms.so.service.store.mongo;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.store.ServiceStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceStoreMongoImpl implements ServiceStore {

    public static final Logger logger = LoggerFactory.getLogger(ServiceStoreMongoImpl.class);

    @Autowired
    ServiceRepository serviceRepository;

    //NOTE : Service 등록
    @Override
    public void createService(Service service) {
        logger.debug("Create Service = " + service.toString());
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
        for(Service service : serviceList){
            logger.debug("Service = " + service.toString());
        }
        return serviceList;
    }

    //NOTE : Service 개별 조회
    @Override
    public Service retrieveServiceDetail(String serviceId) {
        ServiceDataObject serviceDataObject = serviceRepository.findOne(serviceId);
        Service service = dataObjectToService(serviceDataObject);
        logger.debug("Service  = " + service.toString());
        return service;
    }

    @Override
    public void updateService(Service service) {
        ServiceDataObject serviceDataObject = serviceToDataObject(service);
        serviceRepository.save(serviceDataObject);
    }

    @Override
    public Service retrieveServiceDetailByName(String serviceName) {
        ServiceDataObject serviceDataObject = serviceRepository.findByName(serviceName);
        logger.debug("Service = " + dataObjectToService(serviceDataObject));
        return dataObjectToService(serviceDataObject);
    }

    private ServiceDataObject serviceToDataObject(Service service) {
        if (service == null) return null;
        return new ServiceDataObject(service.getId(),service.getName(),service.getVirtualObjectIdList(),service.getVirtualObjectService(),service.getStatus(),service.getCreateTime(),service.getModifiedTime(),service.getFilterTime());
    }

    private Service dataObjectToService(ServiceDataObject serviceDataObject){
        if(serviceDataObject == null) return null;
        return new Service(serviceDataObject.getId(),serviceDataObject.getName(),serviceDataObject.getVirtualObjectIdList(),serviceDataObject.getVirtualObjectService(),serviceDataObject.getStatus(),serviceDataObject.getCreateTime(),serviceDataObject.getModifiedTime(),serviceDataObject.getFilterTime());

    }
}
