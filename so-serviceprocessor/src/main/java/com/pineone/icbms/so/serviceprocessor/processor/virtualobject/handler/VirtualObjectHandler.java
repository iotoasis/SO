package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.handler;

import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.DeviceControlForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.tracking.handler.TrackingHandler;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.collection.CollectionUtils;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.functionlity.IGenericFunctionality;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Virtual Object handler.<BR/>
 * <p>
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
     *
     * @param virtualObject IGenericVirtualObject
     */
    @Override
    public void handle(IGenericVirtualObject virtualObject) {
        if (virtualObject != null) {
            IGenericFunctionality functionality = virtualObject.getFunctionality();
            IGenericAspect aspect = virtualObject.getAspect();
            String locationUri = (String) virtualObject.getState(Const.LOCATION_URI);
            //get device list from repositoru by function+aspect+location.
            List<DeviceForDB> deviceForDbList = databaseManager.getDeviceList(functionality.getId(), aspect.getId(), locationUri);
            if (deviceForDbList != null && deviceForDbList.size() > 0) {
                //get virtual device list from device information
                List<IGenericVirtualDevice> deviceList = ModelMapper.toVirtualDeviceList(deviceForDbList);
                log.info("virtual device list size: {},\n{}", deviceList.size(), CollectionUtils.toStringWithLineFeed(deviceList));
                //publish a VirtualDevice
                if (deviceList != null && deviceList.size() > 0) {
                    for (IGenericVirtualDevice virtualDevice : deviceList) {
                        //tracking
                        TrackingHandler.send(getTracking()
                                , getClass().getSimpleName() + " " + virtualDevice.getName() + " " + virtualDevice.getId());
                        //copy state
                        StateStoreUtil.copyStateStore(virtualObject.getStateStore(), virtualDevice);
                        publishVirtualDevice(virtualDevice);
                    }
                } else {
                    log.error("Virtual device list NOT exist.");
                }
            } else {
                log.warn("The device list NOT exist: {}, {}", virtualObject, locationUri);
            }
        } else {
            log.warn("A VirtualObject is NULL.");
        }
    }

    private void handle() {

    }

    /**
     * publish a VirtualDevice to MQ.<BR/>
     *
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
     *
     * @param deviceControlForMQ DeviceControlForMQ
     * @return json String
     */
    private String toJsonString(DeviceControlForMQ deviceControlForMQ) {
        return ModelMapper.writeJsonString(deviceControlForMQ);
    }

    /**
     * publish a data.<BR/>
     *
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
