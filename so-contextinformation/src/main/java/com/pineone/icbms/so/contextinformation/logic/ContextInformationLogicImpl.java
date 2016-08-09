package com.pineone.icbms.so.contextinformation.logic;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.proxy.ContextInformationProxy;
import com.pineone.icbms.so.contextinformation.proxy.ContextInformationSDAProxy;
import com.pineone.icbms.so.contextinformation.ref.ResponseMessage;
import com.pineone.icbms.so.contextinformation.store.ContextInformationMapStore;
import com.pineone.icbms.so.contextinformation.store.ContextInformationStore;
import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceCenter;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 29..
 * NOTE : ContextInformationLogic 에 연관된 로직 및 서비스들을 수행하기 위한 객체
 */
public class ContextInformationLogicImpl implements ContextInformationLogic {

    public static ContextInformationLogicImpl newContextInformationLogic(){
        return new ContextInformationLogicImpl();
    }

    //NOTE: ContextInformationLogic 에 사용할 가상 장치를 고르기 위해 So의 VO-CVO 리스트 조회 (DeviceObject List) 조회
    public List<DeviceObject> retrieveDeviceObjectList(){
        //
        return DeviceCenter.retrieveDeviceObjectList();
    }

    //NOTE: ContextInformationLogic 에 사용할 ConceptService 를 선택하기 위해 가상 장치의 ConceptService List 조회
    public List<ConceptService> retrieveConceptService(DeviceObject deviceObject){
        //
        return DeviceCenter.newDeviceCenter().retrieveConceptServiceList(deviceObject);
    }

    //NOTE: ContextInformationLogic 등록정보를 수신받고 TODO : SO DB에 저장하고 SDA 에 등록하며 보여줘야할 내용(결정 필요)을 리턴
    public String registerContextInformation(ContextInformation contextInformation){

        ContextInformationStore contextInformationStore = ContextInformationMapStore.getInstance();
        ContextInformationProxy contextInformationProxy = ContextInformationSDAProxy.newContextInformationProxy();
        contextInformation.setId("CI-" + contextInformation.getId());
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        String contextInformationStr = responseMessage.contextInformationResultMessage(contextInformation);
        contextInformationStore.createContextInformation(contextInformation);
        contextInformationProxy.registerContextInformation(contextInformation);
        return contextInformationStr;
    }

    //NOTE : ContextInformation 상세 조회
    public ContextInformation retrieveContextInformationDetail(String contextName) {
        ContextInformationStore contextStore = ContextInformationMapStore.getInstance();
        ContextInformation contextInformation = contextStore.retrieveContextInformationDetail(contextName);
//        SDA 이용할 경우 :
//        ContextInformationProxy contextInformationProxy = new ContextInformationSDAProxy();
//        ContextInformation contextInformation = contextInformationProxy.retrieveGeneralContextDetail(contextName);
        return contextInformation;
    }

    //NOTE : DB에서 ContextInformation NameList 조회
    public List<String> retrieveContextInformationNameList() {
        ContextInformationStore contextStore = ContextInformationMapStore.getInstance();
        List<ContextInformation> contextInformationList = contextStore.retrieveContextInformationList();
        List<String> contextInformationNameList = new ArrayList<>();
        for(ContextInformation contextInformation : contextInformationList){
            contextInformationNameList.add(contextInformation.getName());
        }
        // SDA 이용할 경우 :
        // ContextInformationProxy contextInformationProxy = new ContextInformationSDAProxy();
        // List<ContextInformation> contextInformationList = contextInformationProxyImpl.retrieveContextInformationListFromSDA();
        return contextInformationNameList;
    }
}
