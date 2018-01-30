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
	
	final  static String certLogName = "certification"; 

	/**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected Logger certLog = LoggerFactory.getLogger(certLogName);

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

        /*
          logClassName = ClassName기존으로 상위 2단계의 값을 사용
           ex) com.pineone.icbms.so.serviceprocessor.processor.context.handler.ContextModelHandler 
            => com.pineone.icbms.so.serviceprocessor.processor.context
         */
/*
        //get Class Name
        String orgClsName = getClass().getName();

        //1st strip
        String logClassName = orgClsName.substring(0, orgClsName.lastIndexOf("."));

        //2nd strip
        logClassName = logClassName.substring(0,logClassName.lastIndexOf("."));
        
        //System.out.println("orgClsName="+orgClsName);
        //System.out.println("clsName="+logClassName);
        
        log = LoggerFactory.getLogger(logClassName);
*/        
    }

    public void setTracking(TrackingEntity tracking) {
        this.tracking = tracking;
    }

    public TrackingEntity getTracking() {
        return this.tracking;
    }
}
