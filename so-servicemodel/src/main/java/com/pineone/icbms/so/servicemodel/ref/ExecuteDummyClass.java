package com.pineone.icbms.so.servicemodel.ref;

import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.Status;

/**
 * Created by melvin on 2016. 8. 16..
 */
public class ExecuteDummyClass {
    public void controlService(String domainId, String deviceObject, String status) {
        System.out.println(domainId + ", " +  deviceObject + ", " +  status);
    }
}
