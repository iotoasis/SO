package com.pineone.icbms.so.context.general.device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 11..
 * NOTE: DeviceObject(VO and CVO), ConceptService 관리
 */
public class DeviceCenter {

    public static DeviceCenter newDeviceCenter(){
        DeviceCenter deviceCenter = new DeviceCenter();
        return deviceCenter;
    }

    //NOTE : DeviceObject(VO and CVO) 조회
    public static List<DeviceObject> retrieveDeviceObjectList(){
        ArrayList<DeviceObject> deviceObjectArrayList = new ArrayList<DeviceObject>();
        for(VirtualObject virtualObject : VirtualObject.values()) {
            deviceObjectArrayList.add(virtualObject);
        }
        for(CompositeVirtualObject compositeVirtualObject : CompositeVirtualObject.values()){
            deviceObjectArrayList.add(compositeVirtualObject);
        }
        return deviceObjectArrayList;
    }

    //NOTE: DeviceObject 의 ConceptService 조회
    public List<ConceptService> retrieveConceptServiceList(DeviceObject deviceObject){
        List<ConceptService> conceptServiceList = new ArrayList<ConceptService>();
        if(deviceObject.equals(VirtualObject.TemperatureSensor001)){
            for(TempConceptService tempConceptService : TempConceptService.values()){
                conceptServiceList.add(tempConceptService);
            }
            return conceptServiceList;
        }
        else if(deviceObject.equals(VirtualObject.hanyangSensor)){
            for(HanyangConceptService hanyangConceptService : HanyangConceptService.values()){
                conceptServiceList.add(hanyangConceptService);
            }
            return conceptServiceList;
        }
            return null;
    }
}
