package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.messagequeue.consumer;

import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.OrchestrationServiceForMQ;
import com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.handler.OrchestrationServiceHandler;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.util.messagequeue.consumer.AGenericConsumerHandler2;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;
import java.util.List;

/**
 * OrchestrationService Consumer handler.<BR/>
 * <p>
 * Created by uni4love on 2016. 12. 15..
 */
public class OrchestrationServiceConsumerHandler extends AGenericConsumerHandler2<String, String> {
    /**
     * topic list
     */
    private static final List<String> TOPIC_LIST = Arrays.asList(Settings.TOPIC_ORCHESTRATION_SERVICE);

    /**
     * kafka producer group id by class name.<BR/>
     */
    private static final String GROUP_ID = OrchestrationServiceConsumerHandler.class.getSimpleName();

    /**
     * database manager
     */
    protected IDatabaseManager databaseManager;

    /**
     * orchestrationservice handler
     */
    protected OrchestrationServiceHandler orchestrationServiceHandler;

    /**
     * constructor.<BR/>
     */
    public OrchestrationServiceConsumerHandler(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * constructor.<BR/>
     */
    public OrchestrationServiceConsumerHandler(int id) {
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
        OrchestrationServiceForMQ orchestrationServiceForMQ = ModelMapper.readJsonObject(record.value(), OrchestrationServiceForMQ.class);
        log.debug("OrchestrationServiceForMQ: {}", orchestrationServiceForMQ);

        // TODO tracking
        TrackingEntity tracking = orchestrationServiceForMQ.getTrackingEntity();

        //MQ model --> OrchestrationServiceForDB model
        OrchestrationServiceForDB orchestrationServiceForDB = databaseManager.getOrchestrationServiceById(orchestrationServiceForMQ.getId());
//        List<VirtualObjectForDB> virtualObjectForDBList = databaseManager.getVirtualObjectListByOrchestrationId(orchestrationServiceForDB.getId());
        //OrchestrationServiceForDB model --> OrchestrationService model
        IGenericOrchestrationService orchestrationService = ModelMapper.toOrchestrationService(orchestrationServiceForDB);
        //TODO: refactor copying StateStore
        StateStoreUtil.copyStateStore(orchestrationServiceForMQ.getStateStore(), orchestrationService);

        log.debug("OrchestrationService: {}", orchestrationService);
        if (orchestrationService != null) {
            getOrchestrationServiceHandler().setTracking(tracking);
            getOrchestrationServiceHandler().handle(orchestrationService);
        } else {
            //TODO: exception 처리 필요
            log.warn("....");
        }
    }


    public OrchestrationServiceHandler getOrchestrationServiceHandler() {
        if(orchestrationServiceHandler == null)
            orchestrationServiceHandler = new OrchestrationServiceHandler(databaseManager);
        return orchestrationServiceHandler;
    }

    public void setOrchestrationServiceHandler(OrchestrationServiceHandler orchestrationServiceHandler) {
        this.orchestrationServiceHandler = orchestrationServiceHandler;
    }
}

