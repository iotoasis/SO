package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.messagequeue.consumer;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.interfaces.messagequeue.model.DeviceControlForMQ;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.handler.DeviceControlHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.util.messagequeue.consumer.AGenericConsumerHandler2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;
import java.util.List;

/**
 * DeviceControl Consumer handler.<BR/>
 * <p>
 * Created by uni4love on 2016. 12. 15..
 */
public class DeviceControlConsumerHandler extends AGenericConsumerHandler2<String, String> {
    /**
     * topic list
     */
    private static final List<String> TOPIC_LIST = Arrays.asList(Settings.TOPIC_DEVICE_CONTROL);

    /**
     * kafka producer group id by class name.<BR/>
     */
    private static final String GROUP_ID = DeviceControlConsumerHandler.class.getSimpleName();

    /**
     * database manager
     */
    protected IDatabaseManager databaseManager;

    /**
     * devicecontrol handler
     */
    protected DeviceControlHandler deviceControlHandler;

    /**
     * constructor.<BR/>
     */
    public DeviceControlConsumerHandler(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * constructor.<BR/>
     */
    public DeviceControlConsumerHandler(int id) {
        super(id);
    }

    /**
     * return group id.<BR/>
     *
     * @return group id
     */
    @Override
    public String getGroupId() {
        return GROUP_ID;
    }

    /**
     * return topic list.<BR/>
     *
     * @return topic list
     */
    @Override
    public List<String> getTopicList() {
        return TOPIC_LIST;
    }

    /**
     * handle method.<BR/>
     *
     * @param records ConsumerRecords
     */
    @Override
    public void handle(ConsumerRecords<String, String> records) {
        if (!records.isEmpty()) {
            for (ConsumerRecord<String, String> record : records) {
                handle(record);
            }
        }
    }

    /**
     * handle method.<BR/>
     *
     * @param record ConsumerRecord
     */
    @Override
    public void handle(ConsumerRecord<String, String> record) {
        log.debug("received message: {}", record);
        //read DeviceControlForMQ from string
        DeviceControlForMQ deviceControlForMQ = ModelMapper.readJsonObject(record.value(), DeviceControlForMQ.class);
        log.debug("DeviceControlForMQ: {}", deviceControlForMQ);

        // tracking
        TrackingEntity tracking = deviceControlForMQ.getTrackingEntity();

        //MQ model --> DeviceControl model
        IGenericVirtualDevice virtualDevice = ModelMapper.toVirtualDevice(deviceControlForMQ);

        log.debug("VirtualDevice: {}", virtualDevice);
        if (virtualDevice != null) {
            String locationUri = (String)deviceControlForMQ.getState(Const.LOCATION_URI);
            String contextModelId = (String)deviceControlForMQ.getState(Const.CONTEXTMODEL_ID);
//            getDeviceControlHandler().setTracking(tracking);
//            getDeviceControlHandler().handle(virtualDevice, locationUri, contextModelId);
            deviceControlHandler = new DeviceControlHandler(databaseManager);
            deviceControlHandler.setTracking(tracking);
            deviceControlHandler.handle(virtualDevice, locationUri, contextModelId);
        }
    }

    public DeviceControlHandler getDeviceControlHandler() {
//        if (deviceControlHandler == null)
//            deviceControlHandler = new DeviceControlHandler(databaseManager);
        return deviceControlHandler;
    }

    public void setDeviceControlHandler(DeviceControlHandler deviceControlHandler) {
        this.deviceControlHandler = deviceControlHandler;
    }
}
