package com.pineone.icbms.so.servicemodel.ref;

import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.Status;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 16..
 */
public class ExecuteDummyClass {

    public void controlService(List<ServiceMessage> serviceMessageList) {

        for(ServiceMessage serviceMessage : serviceMessageList) {
            for (String domainId : serviceMessage.getDomainIdList()) {
                System.out.println(domainId);
                System.out.println(serviceMessage.getServiceConceptSerivceId());
                System.out.println(serviceMessage.getOperation());
            }
        }
     }
}
