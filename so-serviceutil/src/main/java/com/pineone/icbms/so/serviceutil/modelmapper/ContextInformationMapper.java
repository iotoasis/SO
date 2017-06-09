package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.ContextInformationForMQ;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.context.contextinformation.DefaultContextInformation;
import com.pineone.icbms.so.virtualobject.context.contextinformation.IGenericContextInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Context information mapper.<BR/>
 * <p>
 * Created by uni4love on 2017. 5. 16..
 */
public class ContextInformationMapper implements IModelMapper<IGenericContextInformation, ContextInformationForDB,
        ContextInformationForMQ> {
    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param contextInformationForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericContextInformation toProcessorModelFromMq(ContextInformationForMQ contextInformationForMQ) {
        DefaultContextInformation contextInformation = null;
        if (contextInformationForMQ != null) {
            contextInformation = new DefaultContextInformation(contextInformationForMQ.getId(),
                    contextInformationForMQ.getName(), contextInformationForMQ.getUri(), contextInformationForMQ.getDescription());
        }
        return contextInformation;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param contextInformationForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericContextInformation toProcessorModelFromDb(ContextInformationForDB contextInformationForDB) {
        DefaultContextInformation contextInformation = null;
        if (contextInformationForDB != null) {
            contextInformation = new DefaultContextInformation(contextInformationForDB.getId(),
                    contextInformationForDB.getName(), contextInformationForDB.getUri(), contextInformationForDB.getDescription());
        }
        return contextInformation;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param iGenericContextInformation PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public ContextInformationForMQ toMqModelFromPs(IGenericContextInformation iGenericContextInformation) {
        //TODO: biz
        return null;
    }

    /**
     * convert List<ContextInformationForDB> to List<IGenericContextInformation> <BR/>
     *
     * @param contextInformationForMQList List<ContextInformationForMQ>
     * @return List<IGenericContextInformation>
     */
    public List<IGenericContextInformation> toContextInformationListFromContextInformationForMQList(List<ContextInformationForMQ> contextInformationForMQList) {
        List<IGenericContextInformation> contextInformationList = null;
        if (contextInformationForMQList != null && contextInformationForMQList.size() > 0) {
            contextInformationList = new ArrayList<>();
            for (ContextInformationForMQ contextInformationForMQ : contextInformationForMQList) {
                contextInformationList.add(toProcessorModelFromMq(contextInformationForMQ));
            }
        }
        return contextInformationList;
    }

    /**
     * convert List<ContextInformationForDB> to List<IGenericContextInformation> <BR/>
     *
     * @param contextInformationForDBList List<ContextInformationForDB>
     * @return List<IGenericContextInformation>
     */
    public List<IGenericContextInformation> toContextInformationList(List<ContextInformationForDB> contextInformationForDBList) {
        List<IGenericContextInformation> contextInformationList = null;
        if (contextInformationForDBList != null && contextInformationForDBList.size() > 0) {
            contextInformationList = new ArrayList<>();
            for (ContextInformationForDB contextInformationForDB : contextInformationForDBList) {
                contextInformationList.add(toProcessorModelFromDb(contextInformationForDB));
            }
        }
        return contextInformationList;
    }
}
