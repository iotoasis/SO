package com.pineone.icbms.so.service.proxy;

import com.pineone.icbms.so.compositevo.pr.CompositeVirtualObjectPresentation;
import com.pineone.icbms.so.util.logprint.LogPrint;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.pr.VirtualObjectPresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceControlProxy implements ServiceProxy {

    public static final Logger logger = LoggerFactory.getLogger(ServiceControlProxy.class);

    @Autowired
    VirtualObjectPresentation virtualObjectPresentation;

    @Autowired
    CompositeVirtualObjectPresentation compositeVirtualObjectPresentation;

    @Override
    public void executeVirtualObject(String virtualObjectId, String operation,String sessionId) {
        logger.info(LogPrint.outputInfoLogPrint() + " VirtualObject ID = " + virtualObjectId + " Operation = " + operation + " Session ID = " + sessionId);
        logger.debug(" VirtualObject ID = " + virtualObjectId + " Operation = " + operation + " Session ID = " + sessionId);
        virtualObjectPresentation.requestControlVirtualObject(virtualObjectPresentation.settingVirtualObjectData(virtualObjectId, operation,sessionId));
    }

    @Override
    public void executeCompositeVirtualObject(String compositevoId, String functionality, String operation,String sessionId) {
        logger.info(LogPrint.outputInfoLogPrint() + " CompositeVirtualObject ID = " + compositevoId + " Operation = " + operation + " Session ID = " + sessionId);
        logger.debug(" CompositeVirtualObject ID = " + compositevoId + " Operation = " + operation + " Session ID = " + sessionId);
        compositeVirtualObjectPresentation.controlCompostieVirtualObject(compositeVirtualObjectPresentation.settingVirtualObjectData(compositevoId, functionality, operation, sessionId));
    }

    @Override
    public VirtualObject findVirtualObject(String virtualObjectId) {
        logger.info(LogPrint.outputInfoLogPrint() + " VirtualObject ID = " + virtualObjectId);
        logger.debug(" VirtualObject ID = " + virtualObjectId);
        VirtualObject virtualObject = null;

        virtualObject = virtualObjectPresentation.searchVirtualObject(virtualObjectId);
        return virtualObject;
    }

}
