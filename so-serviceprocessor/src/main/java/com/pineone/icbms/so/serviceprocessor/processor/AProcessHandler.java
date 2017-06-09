package com.pineone.icbms.so.serviceprocessor.processor;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceutil.interfaces.processor.handler.IProcessorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProcessorHandler abstract class.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 15..
 */
abstract public class AProcessHandler<T> implements IProcessorHandler<T> {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * tracking
     */
    private TrackingEntity tracking = null;

    /**
     * Database Manager
     */
    protected IDatabaseManager databaseManager;

    /**
     * constructor
     *
     * @param databaseManager
     */
    public AProcessHandler(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setTracking(TrackingEntity tracking) {
        this.tracking = tracking;
    }

    public TrackingEntity getTracking() {
        return this.tracking;
    }
}
