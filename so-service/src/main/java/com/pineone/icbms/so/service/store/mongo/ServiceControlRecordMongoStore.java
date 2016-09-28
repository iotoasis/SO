package com.pineone.icbms.so.service.store.mongo;

import com.pineone.icbms.so.service.entity.ServiceControlRecord;
import com.pineone.icbms.so.service.store.ServiceControlRecordStore;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceControlRecordMongoStore implements ServiceControlRecordStore {

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceControlRecordMongoStore.class);

    @Autowired
    ServiceControlRecordRepository serviceControlRecordRepository;

    @Override
    public void createServiceControlRecord(ServiceControlRecord serviceControlRecord) {
        logger.debug("Create ServiceControlRecord = " + serviceControlRecord.toString());
        ServiceControlRecordDataObject serviceControlRecordDataObject = serviceControlRecordToDataObject(serviceControlRecord);
        serviceControlRecordRepository.save(serviceControlRecordDataObject);
    }

    @Override
    public List<ServiceControlRecord> retrieveServiceControlRecordList() {
        logger.debug("retrieveServiceControlRecordList ServiceControlRecord ");
        List<ServiceControlRecordDataObject> serviceControlRecordDataObjectList = serviceControlRecordRepository.findAll();
        List<ServiceControlRecord> serviceControlRecordList = new ArrayList<>();
        for (ServiceControlRecordDataObject serviceControlRecordDataObject : serviceControlRecordDataObjectList){
            serviceControlRecordList.add(dataObjectToServiceControlRecord(serviceControlRecordDataObject));
        }
        for(ServiceControlRecord serviceControlRecord : serviceControlRecordList){
            logger.debug("ServiceControlRecord = " + serviceControlRecord.toString());
        }
        return serviceControlRecordList;
    }

    @Override
    public ServiceControlRecord retrieveServiceControlRecordDetail(String serviceControlRecordId) {
        ServiceControlRecordDataObject serviceControlRecordDataObject = serviceControlRecordRepository.findOne(serviceControlRecordId);
        ServiceControlRecord serviceControlRecord = dataObjectToServiceControlRecord(serviceControlRecordDataObject);
        if(serviceControlRecord != null){
            logger.debug("ServiceControlRecord  = " + serviceControlRecord.toString());
        }
        return serviceControlRecord;
    }

    @Override
    public void updateServiceControlRecord(ServiceControlRecord serviceControlRecord) {
        logger.debug("updateServiceControlRecord ServiceControlRecord  = " + serviceControlRecord.toString());
        ServiceControlRecordDataObject serviceControlRecordDataObject = serviceControlRecordToDataObject(serviceControlRecord);
        serviceControlRecordRepository.save(serviceControlRecordDataObject);
    }

    @Override
    public void deleteServiceControlRecord(String serviceControlRecordId) {
        logger.debug("deleteServiceControlRecord serviceControlRecordId  = " + serviceControlRecordId);
        serviceControlRecordRepository.delete(serviceControlRecordId);
    }

    private ServiceControlRecordDataObject serviceControlRecordToDataObject(ServiceControlRecord serviceControlRecord){
        if(serviceControlRecord == null) return null;
        return new ServiceControlRecordDataObject(serviceControlRecord.getId(), serviceControlRecord.getCreateTime(), serviceControlRecord.getModifiedTime(), serviceControlRecord.getResult(),serviceControlRecord.getPeriod());
    }

    private ServiceControlRecord dataObjectToServiceControlRecord(ServiceControlRecordDataObject serviceControlRecordDataObject){
        if(serviceControlRecordDataObject == null) return null;
        return new ServiceControlRecord(serviceControlRecordDataObject.getId(), serviceControlRecordDataObject.getCreateTime(), serviceControlRecordDataObject.getModifiedTime(), serviceControlRecordDataObject.getResult(),serviceControlRecordDataObject.getPeriod());
    }
}
