package com.pineone.icbms.so.contextinformation.store.mongo;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.store.ContextInformationStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 22..
 * NOTE: Mongo DB를 - CI 정보 연결
 */

@Repository
public class ContextInformationStoreMongoImpl implements ContextInformationStore {

    @Autowired
    ContextInformationRepository contextInformationRepository;

    public static final Logger logger = LoggerFactory.getLogger(ContextInformationStoreMongoImpl.class);

    // NOTE: CI 등록
    @Override
    public void createContextInformation(ContextInformation contextInformation) {
        logger.debug("ContextInformation = " + contextInformation.toString());
        ContextInformationDataObject contextInformationDataObject = contextInformationToDataObject(contextInformation);
        contextInformationRepository.save(contextInformationDataObject);
    }

    // NOTE: CI LIST 조회
    @Override
    public List<ContextInformation> retrieveContextInformationList() {
        List<ContextInformationDataObject> contextInformationDataObjectList = contextInformationRepository.findAll();
        List<ContextInformation> contextInformationList = new ArrayList<>();
        for (ContextInformationDataObject contextInformationDataObject : contextInformationDataObjectList){
            contextInformationList.add(dataObjectToContextInformation(contextInformationDataObject));
        }
        return contextInformationList;
    }

    // NOTE: CI 개별 조회
    @Override
    public ContextInformation retrieveContextInformationDetail(String contextId) {
        logger.debug("ContextId = " + contextId);
        ContextInformationDataObject contextInformationDataObject = contextInformationRepository.findOne(contextId);
        return dataObjectToContextInformation(contextInformationDataObject);
    }

    private ContextInformationDataObject contextInformationToDataObject(ContextInformation contextInformation) {
        if(contextInformation == null) return null;
        return new ContextInformationDataObject(contextInformation.getId(), contextInformation.getName(),
                contextInformation.getDeviceObjectName(), contextInformation.getMinValue() , contextInformation.getMaxValue()
        , contextInformation.getConceptServiceName());
    }

    private ContextInformation dataObjectToContextInformation(ContextInformationDataObject contextInformationDataObject){
        if(contextInformationDataObject == null) return null;
        return new ContextInformation(contextInformationDataObject.getId(), contextInformationDataObject.getName(),
                contextInformationDataObject.getDeviceObjectName(), contextInformationDataObject.getMinValue(),
                contextInformationDataObject.getMaxValue(), contextInformationDataObject.getConceptServiceName());
    }


}
