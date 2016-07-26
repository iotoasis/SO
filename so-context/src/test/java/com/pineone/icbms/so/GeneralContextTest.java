package com.pineone.icbms.so;

import com.pineone.icbms.so.context.general.GeneralContext;
import com.pineone.icbms.so.context.general.device.ConceptService;
import com.pineone.icbms.so.context.general.device.DeviceObject;
import com.pineone.icbms.so.context.general.device.TempConceptService;
import com.pineone.icbms.so.context.general.device.VirtualObject;

import com.pineone.icbms.so.context.util.address.AddressStore;
import com.pineone.icbms.so.context.util.address.ContextAddress;
import com.pineone.icbms.so.context.util.conversion.DataConversion;
import org.junit.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 7..
 */
public class GeneralContextTest {

    GeneralContext generalContext;
    List<DeviceObject> deviceObjectList;
    ContextAddress contextAddress;

//    NOTE : ContextModel 에 사용될 ContextType
//    @Test
//    public void requestContext() throws Exception {
//        generalContext = GeneralContext.newGeneralContext();
//        List<ContextType> contextTypeArrayList;
//        contextTypeArrayList = generalContext.retrieveDeviceObjectList();
//        System.out.println(" *** Step1 : ContextType Request ***");
//        for(ContextType contextType : contextTypeArrayList){
//            System.out.println(contextType);
//        }
//    }


    @Test
    //NOTE: GeneralContext 저작 요청을 하고 DeviceObject 를 수신
    public void requestContextMakingTest() throws Exception {
        generalContext = GeneralContext.newGeneralContext();
        deviceObjectList = generalContext.retrieveDeviceObjectList();
        System.out.println(" *** Step1 - Request : ContextMaking , Response : DeviceObjectList ***");
        for(DeviceObject deviceObject : deviceObjectList){
            System.out.println(deviceObject);
        }
        System.out.println();
    }

    @Test
    //NOTE: DeviceObject 를 선택하고 ConceptServiceLisr 를 수신
    public void chooseDeviceObjectTest() throws Exception {
        generalContext = GeneralContext.newGeneralContext();
        List<ConceptService> conceptServiceList = generalContext.retrieveConceptService(VirtualObject.TemperatureSensor001);
        System.out.println(" *** Step2 - Request : retrieveConceptServiceList , Response : ConceptServiceList ***");
        for(ConceptService conceptService : conceptServiceList){
            System.out.println(conceptService);
        }
        System.out.println();
    }

    @Test
    //NOTE : GeneralContext 정보를 입력 받아서 SDA 에 등록
    public void registerGeneralContextToSDATest() throws Exception {

        ClientAndServer mockServer = ClientAndServer.startClientAndServer(18080);
        //User Field
        String name = "EmergencyTempContext";
        int minValue = 60;
        int maxValue = 100;
        DeviceObject deviceObject = VirtualObject.TemperatureSensor001;
        ConceptService conceptService = TempConceptService.temperature_measure_service;

        //SDA register
        generalContext = GeneralContext.newGeneralContext();
        generalContext.setName(name);
        generalContext.setMinValue(minValue);
        generalContext.setMaxValue(maxValue);
        generalContext.setDeviceObject(deviceObject);
        generalContext.setConceptService(conceptService);

//        generalContext.registerGeneralContext(generalContext);

        String sendData = DataConversion.objectToString(generalContext);
        contextAddress = ContextAddress.newContextAddress();

        System.out.println(" *** Step3 - Request : registerGeneralContextToSDA , Response : result ***");
        System.out.println();
        mockServer
                .when(HttpRequest.request().withMethod("POST").withBody(sendData)
                        .withPath(AddressStore.REGISTER_GENERALCONTEXT))
                .respond(HttpResponse.response().withStatusCode(200).withBody(sendData));
    }

    //NOTE: DB 저장 및 조회
    @Test
    public void registerGeneralContextToDB() throws Exception {

        //User Field
        String name = "EmergencyTempContext";
        int minValue = 60;
        int maxValue = 100;
        DeviceObject deviceObject = VirtualObject.TemperatureSensor001;
        ConceptService conceptService = TempConceptService.temperature_measure_service;

        generalContext = GeneralContext.newGeneralContext();
        generalContext.setName(name);
        generalContext.setMinValue(minValue);
        generalContext.setMaxValue(maxValue);
        generalContext.setDeviceObject(deviceObject);
        generalContext.setConceptService(conceptService);

        generalContext.createGeneralContext(generalContext);

        System.out.println(" *** Step4 - Request : registerGeneralContextToDB , Response : generalContextList ***");
        System.out.println();

        List<GeneralContext> generalContextList = generalContext.retrieveGeneralContextList();
        for(GeneralContext generalContext : generalContextList){
            System.out.println(generalContext.getName());
        }
    }
}
