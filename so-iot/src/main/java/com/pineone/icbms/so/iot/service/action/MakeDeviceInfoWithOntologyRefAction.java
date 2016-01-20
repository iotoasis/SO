package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.DeviceInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;

import java.util.List;

/**
 * 지정된 장소에서 온톨로지 레퍼런스에 해당하는 디바이스 정보를 가져오는 액션
 * Created by Melvin on 2016. 1. 12..
 */
public class MakeDeviceInfoWithOntologyRefAction extends DefaultAction {



    DefaultLocation location ;

    DefaultOntologyReference ontologyReference ;

    @Override
    public void execute(IGenericContext context) {

        DeviceInfoProvider deviceInfoProvider = new DeviceInfoProvider();

        /**
         * Location정보를 DefaultLocation객체에 넣고
         * ontology정보를 DefaultOntology에 넣어서 SDA인터페이스 사용(DeviceInfoProvider)
         * DeviceList를 Action_Device_URI 의 value 값으로 설정
         */
        System.out.println("insert MakeAction");

        ontologyReference = (DefaultOntologyReference) context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE);
        ontologyReference.getReference();
        System.out.println(ontologyReference.getReference() + "1");
        ontologyReference.setName(ontologyReference.getReference());

        System.out.println(ontologyReference.getReference() + "2");

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);

        System.out.println(location.getUri());

        List<DefaultPhysicalDevice> deviceList;

        deviceList = deviceInfoProvider.getDeviceListFromSDA(ontologyReference, location);

        System.out.println("[Check Device1 ] : " + deviceList.get(0).getUri());
        System.out.println("[Check Device2 ] : " + deviceList.get(1).getUri());
        System.out.println("Start addValue");

        context.addValue(IotServiceContext.ACTION_DEVICE_URI, deviceList);



        List<DefaultPhysicalDevice> physicalDeviceList;

        physicalDeviceList = (List<DefaultPhysicalDevice>) context.getValue(IotServiceContext.ACTION_DEVICE_URI);
        System.out.println("[getValue_Check Device1 ] : " + physicalDeviceList.get(0).getUri());
        System.out.println("[getValue_Check Device2 ] : " + physicalDeviceList.get(1).getUri());

        System.out.println("Finish AddValue , finish MakeDeviceAction");
    }
}


