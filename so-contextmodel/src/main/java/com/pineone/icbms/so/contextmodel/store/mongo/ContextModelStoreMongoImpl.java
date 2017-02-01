package com.pineone.icbms.so.contextmodel.store.mongo;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.store.ContextModelStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 22..
 * NOTE: Mongo DB를 - ContextModel 정보 연결
 */

@Repository
public class ContextModelStoreMongoImpl implements ContextModelStore {

    @Autowired
    ContextModelRepository contextModelRepository;

    public static final Logger logger = LoggerFactory.getLogger(ContextModelStoreMongoImpl.class);

    //NOTE : CM 등록
    @Override
    public void createContextModel(ContextModel contextModel) {
        logger.debug("ContextModel = " + contextModel.toString());
        ContextModelDataObject contextModelDataObject = contextModelToDataObject(contextModel);
        contextModelRepository.save(contextModelDataObject);
    }

    //NOTE: CM 리스트 조회
    @Override
    public List<ContextModel> retrieveContextModelList() {
        List<ContextModelDataObject> contextModelDataObjectList = contextModelRepository.findAll();
        List<ContextModel> contextModelList = new ArrayList<>();
        for(ContextModelDataObject contextModelDataObject : contextModelDataObjectList){
            logger.debug("ContextModel = " + dataObjectToContextModel(contextModelDataObject));
            contextModelList.add(dataObjectToContextModel(contextModelDataObject));
        }
        return contextModelList;
    }

    //NOTE: CM 개별 조회
    @Override
    public ContextModel retrieveContextModelDetail(String contextModelId) {
        ContextModelDataObject contextModelDataObject = contextModelRepository.findOne(contextModelId);
        ContextModel contextModel = dataObjectToContextModel(contextModelDataObject);
        logger.debug("ContextModel = " + contextModel.toString());
        return contextModel;
    }

    //NOTE: CM 개별 조회 by Name
    @Override
    public ContextModel retrieveContextModelDetailByName(String contextModelName) {
        ContextModelDataObject contextModelDataObject = contextModelRepository.findByname(contextModelName);
        logger.debug("ContextModel = " + contextModelDataObject.toString());
        ContextModel contextModel = dataObjectToContextModel(contextModelDataObject);
        logger.debug("ContextModel = " + contextModel.toString());
        return contextModel;
    }

    private ContextModelDataObject contextModelToDataObject(ContextModel contextModel) {
        if(contextModel == null) return null;
        return new ContextModelDataObject(contextModel.getId(), contextModel.getName(), contextModel.getDomainIdList(),
                contextModel.getContextInformationIdList(), contextModel.getContextType());
    }

    private ContextModel dataObjectToContextModel(ContextModelDataObject contextModelDataObject){
        if(contextModelDataObject == null) return null;
        return new ContextModel(contextModelDataObject.getId(), contextModelDataObject.getName(), contextModelDataObject.getDomainIdList(),
                contextModelDataObject.getContextInformationIdList(), contextModelDataObject.getContextType());
    }
}
