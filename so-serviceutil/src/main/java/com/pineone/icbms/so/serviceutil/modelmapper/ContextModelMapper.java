package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.ContextModelForMQ;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.context.contextmodel.DefaultContextModel;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;

/**
 * ContextModel mapper.<BR/>
 *
 * Created by uni4love on 2017. 5. 15..
 */
public class ContextModelMapper implements IModelMapper<IGenericContextModel, ContextModelForDB, ContextModelForMQ> {

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param contextModelForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericContextModel toProcessorModelFromMq(ContextModelForMQ contextModelForMQ) {
        DefaultContextModel contextModel = null;
        if (contextModelForMQ != null) {
            contextModel = new DefaultContextModel();
            contextModel.setId(contextModelForMQ.getId());
            contextModel.setName(contextModelForMQ.getName());
            contextModel.setDescription(contextModelForMQ.getDescription());
            StateStoreUtil.copyStateStore(contextModelForMQ.getStateStore(), contextModel);
        }
        return contextModel;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param contextModelForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericContextModel toProcessorModelFromDb(ContextModelForDB contextModelForDB) {
        DefaultContextModel contextModel = null;
        if (contextModelForDB != null) {
            contextModel = new DefaultContextModel();
            contextModel.setId(contextModelForDB.getId());
            contextModel.setName(contextModelForDB.getName());
            contextModel.setDescription(contextModelForDB.getDescription());
        }
        return contextModel;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param contextModel PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public ContextModelForMQ toMqModelFromPs(IGenericContextModel contextModel) {
        //TODO: biz
        return null;
    }
}
