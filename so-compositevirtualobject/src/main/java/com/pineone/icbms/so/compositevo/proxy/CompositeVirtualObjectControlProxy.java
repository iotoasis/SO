package com.pineone.icbms.so.compositevo.proxy;

import com.pineone.icbms.so.util.logprint.LogPrint;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.pr.VirtualObjectPresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompositeVirtualObjectControlProxy implements CompositeVirtualObjectProxy {

    public static final Logger logger = LoggerFactory.getLogger(CompositeVirtualObjectControlProxy.class);

    @Autowired
    VirtualObjectPresentation virtualObjectPresentation;

    @Override
    public void executeCompositeVO(String virtualObjectId, String operation) {
        logger.info(LogPrint.outputInfoLogPrint() + " VirtualObject ID = " + virtualObjectId + " Operation = " + operation);
        logger.debug("executeCompositeVO is VirtualObject ID = " + virtualObjectId + " Operation = " + operation);
        virtualObjectPresentation.requestControlVirtualObject(virtualObjectPresentation.settingVirtualObjectData(virtualObjectId, operation));
    }

    @Override
    public VirtualObject findVirtualObject(String id) {
        if(id == null || id.isEmpty()) return null;
        return virtualObjectPresentation.searchVirtualObject(id);
    }
}
