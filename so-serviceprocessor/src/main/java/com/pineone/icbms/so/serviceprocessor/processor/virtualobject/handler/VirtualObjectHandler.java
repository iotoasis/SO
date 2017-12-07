package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.handler;

import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.virtualobject.AGenericVirtualObject;
import com.pineone.icbms.so.devicecontrol.model.virtualdevice.AGenericVirtualDevice;
import com.pineone.icbms.so.devicecontrol.model.virtualdevice.DefaultVirtualDevice;
import com.pineone.icbms.so.interfaces.messagequeue.model.DeviceControlForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

/**
 * Virtual Object handler.<BR/>
 * 가상오브젝트를 처리 하는 로직
 * Created by uni4love on 2017. 1. 20..
 */
public class VirtualObjectHandler extends AProcessHandler<IGenericVirtualObject> {

    /**
     * constructor
     *
     * @param databaseManager DatabaseManager
     */
    public VirtualObjectHandler(IDatabaseManager databaseManager) {
        super(databaseManager);
    }

    /**
     * A VO process.<BR/>
     * 가상오브젝트 처리
     * @param virtualObject IGenericVirtualObject
     */
    @Override
    public void handle(IGenericVirtualObject virtualObject) {
        getTracking().setProcess(getClass().getSimpleName());
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        if (virtualObject != null) {
            IGenericFunction function = virtualObject.getFunction();
            IGenericAspect aspect = virtualObject.getAspect();
            
            String deviceId = virtualObject.getDeviceId();
            String valueType = virtualObject.getVoValeType();
            String aspectUri = aspect.getUri();
            String isLast = virtualObject.getIsLast();

            String value;
            if (valueType.equals("PARAM")) { //CM에서 전달한 값을 사용
            	value = (String)virtualObject.getState(Const.RESULT_CM_VALUE);
            } else {
	            // cm-dd-command-value(DeviceId, Aspect, cmd) 을 이용한 Command Value 조회
	            value = new SdaManager().getCommandValueById_Aspect_Command(deviceId, aspectUri, valueType);
	            if (valueType.equals("SET")) {
	            	//min,max 체크
	            	String[] rangeValues = value.split("~");
	            	Integer min = Integer.valueOf(rangeValues[0]);
	            	Integer max = Integer.valueOf(rangeValues[1]);
	            	Integer checkV = Integer.valueOf(value);
	            	if (checkV<min || max<checkV) {
	            		log.warn("Invalid command value:range="+ value);
	            		return;
	            	}
	            } else { 
	            	if (valueType.equals("ON") || valueType.equals("OFF")) {
	            		
		            } else {//if (valueType.equals("GET")) 
		            	log.warn("This command is not supportted");
		            	return;
		            }
	        	}
            }
			log.info("value=" + valueType + "("+value + ")" );

            DefaultVirtualDevice defaultVirtualDevice = new DefaultVirtualDevice();
            AGenericVirtualObject virtualDevice = (AGenericVirtualObject)defaultVirtualDevice;
            
            virtualDevice.setId(deviceId);
            virtualDevice.setDeviceId(deviceId);
            virtualDevice.setVoVale(value);
            virtualDevice.setAspect(aspect);
            virtualDevice.setFunction(function);
            defaultVirtualDevice.setIsLast(isLast);
            
            //tracking
            getTracking().setProcessId(virtualDevice.getId());
            getTracking().setProcessName(virtualDevice.getName());
            TrackingProducer.send(getTracking());

            //copy state
            StateStoreUtil.copyStateStore(virtualObject.getStateStore(), virtualDevice);

            publishVirtualDevice((AGenericVirtualDevice)virtualDevice);

        } else {
            log.warn("A VirtualObject is NULL.");
            getTracking().setProcessId("VirtualObject is NULL");
            getTracking().setProcessName("");
            TrackingProducer.send(getTracking());

        }
    }

    /**
     * publish a VirtualDevice to MQ.<BR/>
     * virtualDevice 클래스를 mq 클래스로 변환
     * @param virtualDevice IGenericVirtualDevice
     */
    private void publishVirtualDevice(IGenericVirtualDevice virtualDevice) {
        //generate a VirtualDeviceForMQ model
        DeviceControlForMQ deviceControlForMQ = ModelMapper.toVirtualDevice(virtualDevice);
        deviceControlForMQ.setTrackingEntity(getTracking());

        //generate to string.
        String jsonString = toJsonString(deviceControlForMQ);
        //publish by producer
        publishToMq(jsonString);
    }

    /**
     * DeviceControlForMQ to json String.<BR/>
     * virtualDevice mq 클래스를 큐로 전송하기 위해 json 으로 변환
     * @param deviceControlForMQ DeviceControlForMQ
     * @return json String
     */
    private String toJsonString(DeviceControlForMQ deviceControlForMQ) {
        return ModelMapper.writeJsonString(deviceControlForMQ);
    }

    /**
     * publish a data.<BR/>
     * 큐로 전송
     * @param data data
     * @return result
     */
    private Future<RecordMetadata> publishToMq(String data) {
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "devicecontrol");
        Future<RecordMetadata> result = producerHandler.send(data);
        producerHandler.close();
        return result;
    }
}
