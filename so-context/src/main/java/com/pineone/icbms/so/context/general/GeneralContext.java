package com.pineone.icbms.so.context.general;

import com.pineone.icbms.so.context.Context;
import com.pineone.icbms.so.context.general.device.ConceptService;
import com.pineone.icbms.so.context.general.device.DeviceCenter;
import com.pineone.icbms.so.context.general.device.DeviceObject;
import com.pineone.icbms.so.context.general.itf.database.MapController_GeneralContext;
import com.pineone.icbms.so.context.general.itf.external.sda.SdaController_GeneralContext;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by melvin on 2016. 7. 7..
 * NOTE: GeneralContext Entity
 */
public class GeneralContext implements Context {
    private String id;
    private String name;
    private DeviceObject deviceObject;
    private int minValue;
    private int maxValue;
    private ConceptService conceptService;


    public GeneralContext(){}

    public static GeneralContext newGeneralContext(){
        //
        GeneralContext generalContext = new GeneralContext();
        return generalContext;
    }

    public GeneralContext(String id, String name, DeviceObject deviceObject, ConceptService conceptService,
                                          int minValue, int maxValue){
        //
        this.id = id;
        this.name = name;
        this.deviceObject = deviceObject;
        this.conceptService = conceptService;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public GeneralContext(String name, DeviceObject deviceObject, ConceptService conceptService,
                          int minValue, int maxValue){
        //
        this.name = name;
        this.deviceObject = deviceObject;
        this.conceptService = conceptService;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceObject getDeviceObject() {
        return deviceObject;
    }

    public void setDeviceObject(DeviceObject deviceObject) {
        this.deviceObject = deviceObject;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public ConceptService getConceptService() {
        return conceptService;
    }

    public void setConceptService(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    //NOTE: DeviceObject List 조회
    public List<DeviceObject> retrieveDeviceObjectList(){
        //
        return DeviceCenter.retrieveDeviceObjectList();
    }

    //NOTE: DeviceObject 의 ConceptService List 조회
    public List<ConceptService> retrieveConceptService(DeviceObject deviceObject){
        //
        return DeviceCenter.newDeviceCenter().retrieveConceptServiceList(deviceObject);
    }

    //NOTE: DB에 GeneralContext 저장
    public void createGeneralContext(GeneralContext generalContext){
        //
        MapController_GeneralContext mapControllerGeneralContext = MapController_GeneralContext.getInstance();
        mapControllerGeneralContext.createGeneralContext(generalContext);
    }
    //NOTE: SDA 에 GeneralContext 저장
    public void registerGeneralContext(GeneralContext generalContext){
        //
        SdaController_GeneralContext.newSdaController().registerGeneralContext(generalContext);
        this.createGeneralContext(generalContext);
    }

    // NOTE: DB 에서 GeneralContext 조회(User 의 조회요청에 따라)
    public List<GeneralContext> retrieveGeneralContextList(){
        //
        MapController_GeneralContext mapControllerGeneralContext = MapController_GeneralContext.getInstance();
        List<GeneralContext> generalContextList = mapControllerGeneralContext.retrieveGeneralContextList();
        return generalContextList;
    }

    // NOTE: SDA 에서 GeneralContextList 조회(SO 에서 주기적으로 동기화시키기 위해서)
    public List<GeneralContext> retrieveGeneralContextListFromSDA(){
        //
        List<Object> objectList = SdaController_GeneralContext.newSdaController().retrieveGeneralContextListFromSDA();
        List<GeneralContext> generalContextList = new ArrayList<>();
        for(Object object : objectList){
            generalContextList.add((GeneralContext)object);
        }
        return generalContextList; //generalContextList
    }

    // NOTE: SDA 에서 GeneralContext 상세 조회
    public GeneralContext retrieveGeneralContext(String generalContextName){
        //
        GeneralContext generalContext = SdaController_GeneralContext.newSdaController().retrieveGeneralContextDetail(generalContextName);
        return generalContext;
    }
}
