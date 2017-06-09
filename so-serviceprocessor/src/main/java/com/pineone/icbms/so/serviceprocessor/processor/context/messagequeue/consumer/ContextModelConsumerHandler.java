package com.pineone.icbms.so.serviceprocessor.processor.context.messagequeue.consumer;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.ContextModelForMQ;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.context.handler.ContextModelHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.util.messagequeue.consumer.AGenericConsumerHandler2;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;
import java.util.List;

/**
 * ContextModelForMQ Consumer handler.<BR/>
 * <p>
 * Created by uni4love on 2016. 12. 15..
 */
public class ContextModelConsumerHandler extends AGenericConsumerHandler2<String, String> {
    /**
     * topic list
     */
    private static final List<String> TOPIC_LIST = Arrays.asList(Settings.TOPIC_CONTEXT_MODEL);

    /**
     * kafka handler group id by class name.<BR/>
     */
    private static final String GROUP_ID = ContextModelConsumerHandler.class.getSimpleName();

    /**
     * database manager
     */
    protected IDatabaseManager databaseManager;

    /**
     * contextmodel handler
     */
    protected ContextModelHandler contextModelHandler;

    /**
     * constructor.<BR/>
     */
    public ContextModelConsumerHandler(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * constructor.<BR/>
     */
    public ContextModelConsumerHandler(int id) {
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
        //read ContextModelForMQ from string
        ContextModelForMQ contextModelForMQ = ModelMapper.readJsonObject(record.value(), ContextModelForMQ.class);
        log.debug("ContextModelForMQ: {}", contextModelForMQ);

        // TODO tracking
        TrackingEntity tracking = contextModelForMQ.getTrackingEntity();

        //ContextModelForMQ model --> ContextModel model
        IGenericContextModel contextModel = ModelMapper.toContextModel(contextModelForMQ);
        log.debug("ContextModel: {}", contextModel);
        if (contextModel != null) {
            List<IGenericLocation> locationList = ModelMapper.toLocationList(contextModelForMQ.getLocationList());
            if(locationList != null && locationList.size() > 0) {
                contextModel.addState(Const.LOCATION_URI_LIST, locationList);
                log.debug("ContextModel with locations: {}", contextModel);
            }
            //ContextModel Handler
            getContextModelHandler().setTracking(tracking);
            getContextModelHandler().handle(contextModel);
        }
    }

    public ContextModelHandler getContextModelHandler() {
        if (contextModelHandler == null)
            contextModelHandler = new ContextModelHandler(databaseManager);
        return contextModelHandler;
    }

    public void setContextModelHandler(ContextModelHandler contextModelHandler) {
        this.contextModelHandler = contextModelHandler;
    }
}

