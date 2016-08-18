package com.pineone.icbms.so.servicemodel;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.Status;
import com.pineone.icbms.so.service.ref.VirtualObject;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogic;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogicImpl;
import org.junit.Test;

/**
 * Created by melvin on 2016. 8. 16..
   NOTE: 실제 서비스 구동 과정 테스트
 */
public class ServiceModelExecuteTest {
    @Test
    public void executeTest() throws Exception {
        ServiceModelLogic serviceModelLogic = ServiceModelLogicImpl.newServiceModelLogic();

        Domain domain = new Domain("강의실" , "http://xxx.xxx.xx");
        DeviceObject deviceObject = VirtualObject.SonamooHeater;
        Status status = Status.Active;

//        ServiceMessage serviceMessage = new ServiceMessage(domain, deviceObject, status);
//        serviceModelLogic.executeEmergencyServiceModel(serviceMessage);
    }
}
