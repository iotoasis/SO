package com.pineone.icbms.so.service.proxy;

import com.pineone.icbms.so.virtualobject.entity.VirtualObjectRequestControl;
import com.pineone.icbms.so.virtualobject.pr.VirtualObjectPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceControlProxy implements ServiceProxy{

    @Autowired
    VirtualObjectPresentation virtualObjectPresentation;

    @Override
    public void executeVirtualObject(String virtualObjectId, String operation) {
        System.out.println("\n**********  Service Proxy RequestVirtualObjectControl  **********");
        System.out.println("Request virtualObjectID = " + virtualObjectId);
        System.out.println("Request operation = " + operation);
        VirtualObjectRequestControl virtualObjectRequestControl = new VirtualObjectRequestControl(virtualObjectId,operation);
        virtualObjectPresentation.requestControlVirtualObject(virtualObjectRequestControl);
    }

    @Override
    public void voTestSetUp() {
        virtualObjectPresentation.testSetUp();
    }
}
