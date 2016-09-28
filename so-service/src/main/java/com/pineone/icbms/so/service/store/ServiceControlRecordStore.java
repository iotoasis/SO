package com.pineone.icbms.so.service.store;

import com.pineone.icbms.so.service.entity.ServiceControlRecord;

import java.util.List;

public interface ServiceControlRecordStore {
    //
    void createServiceControlRecord(ServiceControlRecord serviceControlRecord);
    List<ServiceControlRecord> retrieveServiceControlRecordList();
    ServiceControlRecord retrieveServiceControlRecordDetail(String serviceControlRecordId);
    void updateServiceControlRecord(ServiceControlRecord serviceControlRecord);
    void deleteServiceControlRecord(String serviceControlRecordId);
}
