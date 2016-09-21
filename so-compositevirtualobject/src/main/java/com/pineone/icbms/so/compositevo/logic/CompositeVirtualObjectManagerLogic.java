package com.pineone.icbms.so.compositevo.logic;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;
import com.pineone.icbms.so.compositevo.entity.VirtualObjectControlData;
import com.pineone.icbms.so.compositevo.proxy.CompositeVirtualObjectProxy;
import com.pineone.icbms.so.compositevo.store.CompositeVirtualObjectStore;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositeVirtualObjectManagerLogic implements CompositeVirtualObjectManager{
    //

    public static final Logger logger = LoggerFactory.getLogger(CompositeVirtualObjectManagerLogic.class);

    @Autowired
    private CompositeVirtualObjectStore compositeVirtualObjectStore;

    @Autowired
    private CompositeVirtualObjectProxy compositeVirtualObjectProxy;


    @Override
    public List<CompositeVirtualObject> searchCompositeVOList() {
        logger.debug("Search CompositeVirtualObject List");
        List<CompositeVirtualObject> compositeVirtualObjects = compositeVirtualObjectStore.retrieveCompositeVirtualObjectList();
        for(CompositeVirtualObject compositeVirtualObject : compositeVirtualObjects){
            logger.debug("CompositeVirtualObject = " + compositeVirtualObject.toString());
        }
        return compositeVirtualObjects;
    }

    @Override
    public List<CompositeVirtualObject> searchLocationCompositeVO(String location) {
        logger.debug("Search CompositeVirtualObject List");
        List<CompositeVirtualObject> compositeVirtualObjects = compositeVirtualObjectStore.retrieveByLocationCompositeVirtualObjectList(location);
        for(CompositeVirtualObject compositeVirtualObject : compositeVirtualObjects){
            logger.debug("CompositeVirtualObject = " + compositeVirtualObject.toString());
        }
        return compositeVirtualObjects;
    }

    @Override
    public void createCompositeVO(CompositeVirtualObject compositeVirtualObject) {
        logger.debug("Create CompositeVirtualObject = " + compositeVirtualObject.toString());
        compositeVirtualObjectStore.create(compositeVirtualObject);
    }

    @Override
    public CompositeVirtualObject searchCompositeVO(String id) {
        logger.debug("Search CompositeVirtualObject ID = " + id);
        CompositeVirtualObject compositeVirtualObject = compositeVirtualObjectStore.retrieveByID(id);
        logger.debug("CompositeVirtualObject = " + compositeVirtualObject.toString());
        return compositeVirtualObject;
    }

    @Override
    public void updateCompositeVO(CompositeVirtualObject compositeVirtualObject) {
        logger.debug("Update CompositeVirtualObject  = " + compositeVirtualObject.toString());
        if(compositeVirtualObject == null) return;
        compositeVirtualObjectStore.update(compositeVirtualObject);
    }

    @Override
    public void deleteCompositeVO(String id) {
        logger.debug("delete CompositeVirtualObject ID = " + id);
        compositeVirtualObjectStore.delete(id);
    }

    @Override
    public void executeCompositeVO(VirtualObjectControlData virtualObjectControlData) {
        //
        logger.debug("control CompositeVirtualObject is VirtualObjectControlData= " + virtualObjectControlData.toString());
        if(virtualObjectControlData == null) return ;

        // Detail Control 과 일반 Control 구분.

        CompositeVirtualObject compositeVirtualObject = compositeVirtualObjectStore.retrieveByID(virtualObjectControlData.getId());
        List<String> virtualObjectIDList = compositeVirtualObject.getVoIdList();
        for(String virtualObjectId : virtualObjectIDList){
            logger.debug("control virtualObject : virtualObject ID = " + virtualObjectId + " Operation = " + virtualObjectControlData.getOperation());

            if(virtualObjectControlData.getFunctionality() == null){
                // Normal Control
                compositeVirtualObjectProxy.executeCompositeVO(virtualObjectId, virtualObjectControlData.getOperation());
            } else {
                // Detail Control
                VirtualObject virtualObject = compositeVirtualObjectProxy.findVirtualObject(virtualObjectId);
                if(virtualObject == null) return;

                if(virtualObject.getFunctionality().equals(virtualObjectControlData.getFunctionality())){
                    compositeVirtualObjectProxy.executeCompositeVO(virtualObjectId, virtualObjectControlData.getOperation());
                }

            }


        }
    }

}
