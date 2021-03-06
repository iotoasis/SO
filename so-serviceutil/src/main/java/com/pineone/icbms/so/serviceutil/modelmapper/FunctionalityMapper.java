package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.FunctionForMQ;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.function.DefaultFunction;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;

/**
 * Function mapper.<BR/>
 *
 * Created by uni4love on 2017. 5. 15..
 */
public class FunctionalityMapper implements IModelMapper<IGenericFunction, FunctionalityForDB,
        FunctionForMQ> {
    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param functionForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericFunction toProcessorModelFromMq(FunctionForMQ functionForMQ) {
        DefaultFunction function = null;
        if (functionForMQ != null) {
            function = new DefaultFunction(functionForMQ.getId(), functionForMQ.getName(),
                    functionForMQ.getDescription(), functionForMQ.getUri());
        }
        return function;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param functionForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericFunction toProcessorModelFromDb(FunctionalityForDB functionForDB) {
        DefaultFunction function = null;
        if (functionForDB != null) {
            function = new DefaultFunction(functionForDB.getId(), functionForDB.getName(),
                    functionForDB.getDescription(), functionForDB.getUri());
        }
        return function;
    }
    public IGenericFunction toProcessorModelFromDb(String functionalityId) {
        DefaultFunction function = null;
        if (functionalityId != null) {
            function = new DefaultFunction(functionalityId, "","", "");
        }
        return function;
    }

    public IGenericFunction toProcessorModelFromDb(String functionalityId,String functionalityUri) {
        DefaultFunction function = null;
        if (functionalityId != null) {
            function = new DefaultFunction(functionalityId, "","", functionalityUri);
        }
        return function;
    }
    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param function PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public FunctionForMQ toMqModelFromPs(IGenericFunction function) {
        FunctionForMQ functionForMQ = null;
        if (function != null) {
            functionForMQ = new FunctionForMQ(function.getId(), function.getName(),
                    function.getDescription(), function.getUri());
        }
        return functionForMQ;
    }
}
