package com.pineone.icbms.so.service.proxy;

import com.pineone.icbms.so.util.logprint.LogPrint;
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

    @Override
    public void executeVirtualObject(String virtualObjectId, String operation) {
        logger.info(LogPrint.outputInfoLogPrint() + " VirtualObject ID = " + virtualObjectId + " Operation = " + operation);
        logger.debug(" VirtualObject ID = " + virtualObjectId + " Operation = " + operation);
        virtualObjectPresentation.requestControlVirtualObject(virtualObjectPresentation.settingVirtualObjectData(virtualObjectId, operation));
    }
}
