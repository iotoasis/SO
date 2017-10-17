package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.handler;

import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.DeviceControlForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.collection.CollectionUtils;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
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
            String locationUri = (String) virtualObject.getState(Const.LOCATION_URI);

            // TODO product : get device list from repository by function+aspect+location to use SDA API.
            //
            //SdaClient sdaClient = new SdaClient();
            //List<String> values = sdaClient.retreiveDeviceList(String functionUri, locationUri);
            //retreiveDeviceControlValues(null, null, locationUri, device.getId());

            // TODO develop :  개발용 데이터베이스에서 조회처리
            log.warn("getDeviceList : {}, {}, {}", function.getId(), aspect.getId(), locationUri);
            List<DeviceForDB> deviceForDbList = databaseManager.getDeviceList(function.getId(), aspect.getId(), locationUri);

            if (deviceForDbList != null && deviceForDbList.size() > 0) {
                //get virtual device list from device information
                List<IGenericVirtualDevice> deviceList = ModelMapper.toVirtualDeviceList(deviceForDbList);
                log.info("virtual device list size: {}", deviceList.size());
                log.info("virtual device list size: {},\n{}", deviceList.size(), CollectionUtils.toStringWithLineFeed(deviceList));
                //publish a VirtualDevice
                if (deviceList != null && deviceList.size() > 0) {
                    for (IGenericVirtualDevice virtualDevice : deviceList) {
                        //tracking
                        getTracking().setProcessId(virtualDevice.getId());
                        getTracking().setProcessName(virtualDevice.getName());
                        TrackingProducer.send(getTracking());

                        //copy state
                        StateStoreUtil.copyStateStore(virtualObject.getStateStore(), virtualDevice);

                        // simulator. 마지막 VO인지 체크
                        if (deviceList.indexOf(virtualDevice) == (deviceList.size() - 1)) {
                            virtualDevice.setIsLast("Y");
                        }

                        publishVirtualDevice(virtualDevice);
                    }

                    // end calling device control, 시뮬레이션에서 사용하기 위해 컬럼값을 'F' 로 업데이트
                    // vo에 매핑된 device 전체에 대한 처리가 종료된 후 tracking 처리
                    // device control handler 로 최종 처리가 이관되면 device control handler 단에서 처리해 줘야 한다
//                    TrackingEntity trackingEntity = getTracking();
//                    trackingEntity.clearProcessInfomation();
//                    trackingEntity.setProcess("Finish");
//                    trackingEntity.setStatus("F");
//                    TrackingProducer.send(trackingEntity);
                } else {
                    log.error("Virtual device list NOT exist.");
                    getTracking().setProcessId("Virtual list NOT exist");
                    getTracking().setProcessName("");
                    TrackingProducer.send(getTracking());
                }
            } else {
                log.warn("The device list NOT exist: {}, {}", virtualObject, locationUri);
                getTracking().setProcessId("device list NOT exist");
                getTracking().setProcessName("");
                TrackingProducer.send(getTracking());
            }
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
