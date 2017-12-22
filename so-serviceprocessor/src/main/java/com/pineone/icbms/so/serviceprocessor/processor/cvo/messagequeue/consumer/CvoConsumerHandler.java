package com.pineone.icbms.so.serviceprocessor.processor.cvo.messagequeue.consumer;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.CompositeVirtualObjectForMQ;
import com.pineone.icbms.so.serviceprocessor.processor.cvo.handler.CvoHandler;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.Settings2;
import com.pineone.icbms.so.util.messagequeue.consumer.AGenericConsumerHandler2;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;
import java.util.List;

/**
 * OrchestrationService Consumer handler.<BR/>
 * <p>
 * Created by uni4love on 2016. 12. 15..
 */
public class CvoConsumerHandler extends AGenericConsumerHandler2<String, String> {
    /**
     * topic list
     */
    private static final List<String> TOPIC_LIST = Arrays.asList(Settings2.TOPIC_COMPOSITE_VIRTUAL_OBJECT);

    /**
     * kafka producer group id by class name.<BR/>
     */
    private static final String GROUP_ID = CvoConsumerHandler.class.getSimpleName();
    
    private static int threadNum=0;

    /**
     * database manager
     */
    protected IDatabaseManager databaseManager;

    /**
     * cvo handler
     */
    protected CvoHandler cvoHandler;

    /**
     * constructor.<BR/>
     */
    public CvoConsumerHandler(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        Thread.currentThread().setName("T:"+Settings2.TOPIC_COMPOSITE_VIRTUAL_OBJECT + "-" +threadNum++);
    }

    /**
     * constructor.<BR/>
     */
    public CvoConsumerHandler(int id) {
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
        //read CompositeVirtualObjectForMQ from string
        CompositeVirtualObjectForMQ compositeVirtualObjectForMQ = ModelMapper.readJsonObject(record.value(), CompositeVirtualObjectForMQ.class);
        log.debug("CompositeVirtualObjectForMQ: {}", compositeVirtualObjectForMQ);

        // tracking
        TrackingEntity tracking = compositeVirtualObjectForMQ.getTrackingEntity();

        //MQ model --> CompositeVirtualObjectForDB model
        log.debug("getOrchestrationServiceById : {}", compositeVirtualObjectForMQ.getId());
/*        
        CompositeVirtualObjectForDB compositeVirtualObjectForDB = databaseManager.getCompositeVirtualObjectById(compositeVirtualObjectForMQ.getId());
        
        if (compositeVirtualObjectForDB==null) {
            log.warn("CompositeVirtualObjectForDB is null");
        	return;
        }

        //CompositeVirtualObjectForDB model --> CompositeVirtualObject model
        IGenericCompositeVirtualObject compositeVirtualObject = ModelMapper.toCompositeVirtualObject(compositeVirtualObjectForDB);
*/        
        IGenericCompositeVirtualObject compositeVirtualObject = ModelMapper.toCompositevirtualObject(compositeVirtualObjectForMQ);
        
        
        // TODO refactor copying StateStore
        StateStoreUtil.copyStateStore(compositeVirtualObjectForMQ.getStateStore(), compositeVirtualObject);

        log.debug("CompositeVirtualObject: {}", compositeVirtualObject);
        if (compositeVirtualObject != null) {
            getCvoHandler().setTracking(tracking);
            getCvoHandler().handle(compositeVirtualObject);
        } else {
            //TODO: exception 처리 필요
            log.warn("compositeVirtualObject is null");
        }
    }


    public CvoHandler getCvoHandler() {
        if(cvoHandler == null)
            cvoHandler = new CvoHandler(databaseManager);
        return cvoHandler;
    }

    public void setCvoHandler(CvoHandler cvoHandler) {
        this.cvoHandler = cvoHandler;
    }
}

