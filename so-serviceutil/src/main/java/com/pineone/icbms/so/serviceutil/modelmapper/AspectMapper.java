package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.AspectForMQ;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.aspect.DefaultAspect;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;

/**
 * Aspect model mapper.<BR/>
 * <p>
 * Created by uni4love on 2017. 5. 15..
 */
public class AspectMapper implements IModelMapper<IGenericAspect, AspectForDB, AspectForMQ> {
    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param aspectForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericAspect toProcessorModelFromMq(AspectForMQ aspectForMQ) {
        DefaultAspect aspect = null;
        if (aspectForMQ != null) {
            aspect = new DefaultAspect(aspectForMQ.getId(), aspectForMQ.getName(), aspectForMQ.getDescription(),
                    aspectForMQ.getUri());
        }
        return aspect;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param aspectForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericAspect toProcessorModelFromDb(AspectForDB aspectForDB) {
        DefaultAspect aspect = null;
        if (aspectForDB != null) {
            aspect = new DefaultAspect(aspectForDB.getId(), aspectForDB.getName(), aspectForDB.getDescription(),
                    aspectForDB.getUri());
        }
        return aspect;
    }
    public IGenericAspect toProcessorModelFromDb(String aspectId) {
        DefaultAspect aspect = null;
        if (aspectId != null) {
            aspect = new DefaultAspect(aspectId, "", "","");
        }
        return aspect;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param aspect PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public AspectForMQ toMqModelFromPs(IGenericAspect aspect) {
        AspectForMQ aspectForMQ = null;
        if (aspect != null) {
            aspectForMQ = new AspectForMQ(aspect.getId(), aspect.getName(), aspect.getDescription(), aspect.getUri());
        }
        return aspectForMQ;
    }
}
