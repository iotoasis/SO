package com.pineone.icbms.so.util.conversion;

/**
 * Model mapper.<BR/>
 *
 * Created by uni4love on 2017. 4. 11..
 */
public interface IModelMapper<PS_MODEL, DB_MODEL, MQ_MODEL> {

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param mqModel MQ_MODEL
     * @return PS_MODEL
     */
    PS_MODEL toProcessorModelFromMq(MQ_MODEL mqModel);

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param dbModel DB_MODEL
     * @return PS_MODEL
     */
    PS_MODEL toProcessorModelFromDb(DB_MODEL dbModel);

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param psModel PS_MODEL
     * @return MQ_MODEL
     */
    MQ_MODEL toMqModelFromPs(PS_MODEL psModel);



}
