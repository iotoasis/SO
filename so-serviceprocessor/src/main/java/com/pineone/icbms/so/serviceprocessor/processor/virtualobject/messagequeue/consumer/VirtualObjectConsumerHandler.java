package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.messagequeue.consumer;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.VirtualObjectForMQ;
import com.pineone.icbms.so.serviceprocessor.processor.virtualobject.handler.VirtualObjectHandler;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.util.messagequeue.consumer.AGenericConsumerHandler2;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;
import java.util.List;

/**
 * VirtualObject Consumer handler.<BR/>
 * <p>
 * Created by uni4love on 2016. 12. 15..
 */
public class VirtualObjectConsumerHandler extends AGenericConsumerHandler2<String, String> {
    /**
     * topic list
     */
    private static final List<String> TOPIC_LIST = Arrays.asList(Settings.TOPIC_VIRTUAL_OBJECT);

    private static int threadNum = 0;
    
    /**
     * kafka producer group id by class name.<BR/>
     */
    private static final String GROUP_ID = VirtualObjectConsumerHandler.class.getSimpleName();

    /**
     * database manager
     */
    protected IDatabaseManager databaseManager;

    /**
     * virtual object handler
     */
    protected VirtualObjectHandler virtualObjectHandler;

    /**
     * constructor.<BR/>
     */
    public VirtualObjectConsumerHandler(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        Thread.currentThread().setName("T:"+Settings.TOPIC_VIRTUAL_OBJECT + "-" +threadNum++);
}

    /**
     * constructor.<BR/>
     */
    public VirtualObjectConsumerHandler(int id) {
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
     * @param record ConsumerRecords
     */
    @Override
    public void handle(ConsumerRecord<String, String> record) {
        //read OrchestrationServiceForMQ from string
        VirtualObjectForMQ virtualObjectForMQ = ModelMapper.readJsonObject(record.value(), VirtualObjectForMQ.class);
        log.trace("VirtualObjectForMQ: {}", virtualObjectForMQ);

        // tracking
        TrackingEntity tracking = virtualObjectForMQ.getTrackingEntity();

        //MQ model --> VirtualObject model
        IGenericVirtualObject virtualObject = ModelMapper.toVirtualObject(virtualObjectForMQ);
        log.debug("VirtualObject: {}", virtualObject);
        if (virtualObject != null) {
            getVirtualObjectHandler().setTracking(tracking);
            getVirtualObjectHandler().handle(virtualObject);
        }
    }

    public synchronized VirtualObjectHandler getVirtualObjectHandler() {
        if (virtualObjectHandler == null)
            virtualObjectHandler = new VirtualObjectHandler(databaseManager);
        return virtualObjectHandler;
    }

    public void setVirtualObjectHandler(VirtualObjectHandler virtualObjectHandler) {
        this.virtualObjectHandler = virtualObjectHandler;
    }
}
