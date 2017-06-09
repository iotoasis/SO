package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.FunctionalityForMQ;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.functionlity.DefaultFunctionality;
import com.pineone.icbms.so.virtualobject.functionlity.IGenericFunctionality;

/**
 * Functionality mapper.<BR/>
 *
 * Created by uni4love on 2017. 5. 15..
 */
public class FunctionalityMapper implements IModelMapper<IGenericFunctionality, FunctionalityForDB,
        FunctionalityForMQ> {
    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param functionalityForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericFunctionality toProcessorModelFromMq(FunctionalityForMQ functionalityForMQ) {
        DefaultFunctionality functionality = null;
        if (functionalityForMQ != null) {
            functionality = new DefaultFunctionality(functionalityForMQ.getId(), functionalityForMQ.getName(),
                    functionalityForMQ.getDescription(), functionalityForMQ.getUri());
        }
        return functionality;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param functionalityForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericFunctionality toProcessorModelFromDb(FunctionalityForDB functionalityForDB) {
        DefaultFunctionality functionality = null;
        if (functionalityForDB != null) {
            functionality = new DefaultFunctionality(functionalityForDB.getId(), functionalityForDB.getName(),
                    functionalityForDB.getDescription(), functionalityForDB.getUri());
        }
        return functionality;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param functionality PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public FunctionalityForMQ toMqModelFromPs(IGenericFunctionality functionality) {
        FunctionalityForMQ functionalityForMQ = null;
        if (functionality != null) {
            functionalityForMQ = new FunctionalityForMQ(functionality.getId(), functionality.getName(),
                    functionality.getDescription(), functionality.getUri());
        }
        return functionalityForMQ;
    }
}
