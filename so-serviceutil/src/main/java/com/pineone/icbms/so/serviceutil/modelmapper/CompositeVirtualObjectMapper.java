package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.FunctionForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.AspectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.CompositeVirtualObjectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.FunctionForMQ;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.composite.DefaultCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite virtual object mapper.<BR/>
 *
 * Created by uni4love on 2017. 5. 16..
 */
public class CompositeVirtualObjectMapper implements IModelMapper<IGenericCompositeVirtualObject,
        CompositeVirtualObjectForDB, CompositeVirtualObjectForMQ> {

    /**
     * virtualobject mapper
     */
    private static VirtualObjectMapper virtualObjectMapper = new VirtualObjectMapper();

    /**
     * aspect mapper
     */
    private static AspectMapper aspectMapper = new AspectMapper();

    /**
     * function mapper
     */
    private static FunctionMapper functionMapper = new FunctionMapper();

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param compositeVirtualObjectForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericCompositeVirtualObject toProcessorModelFromMq(CompositeVirtualObjectForMQ compositeVirtualObjectForMQ) {
        DefaultCompositeVirtualObject compositeVirtualObject = null;
        if (compositeVirtualObjectForMQ != null) {
            compositeVirtualObject = new DefaultCompositeVirtualObject();
            compositeVirtualObject.setId(compositeVirtualObjectForMQ.getId());
            compositeVirtualObject.setName(compositeVirtualObjectForMQ.getName());
            compositeVirtualObject.setDescription((compositeVirtualObjectForMQ.getDescription()));
            compositeVirtualObject.setAspect(aspectMapper.toProcessorModelFromMq(compositeVirtualObjectForMQ.getAspect()));
            compositeVirtualObject.setFunction(functionMapper.toProcessorModelFromMq(compositeVirtualObjectForMQ.getFunction()));
            compositeVirtualObject.setVirtualObjectList(virtualObjectMapper.toVirtualObjectList(compositeVirtualObjectForMQ.getVirtualObjectList()));
            StateStoreUtil.copyStateStore(compositeVirtualObjectForMQ.getStateStore(), compositeVirtualObjectForMQ);
        }
        return compositeVirtualObject;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param compositeVirtualObjectForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericCompositeVirtualObject toProcessorModelFromDb(CompositeVirtualObjectForDB compositeVirtualObjectForDB) {
        DefaultCompositeVirtualObject compositeVirtualObject = null;
        if (compositeVirtualObjectForDB != null) {
            compositeVirtualObject = new DefaultCompositeVirtualObject();
            compositeVirtualObject.setId(compositeVirtualObjectForDB.getId());
            compositeVirtualObject.setName(compositeVirtualObjectForDB.getName());
            compositeVirtualObject.setDescription((compositeVirtualObjectForDB.getDescription()));

//            AspectForDB aspectForDB = new AspectForDB();
//            aspectForDB.setId(compositeVirtualObjectForDB.getAspect_id());
//            compositeVirtualObject.setAspect(aspectMapper.toProcessorModelFromDb(aspectForDB));
//
//            FunctionForDB functionForDB = new FunctionForDB();
//            functionForDB.setId(compositeVirtualObjectForDB.getFunction_id());
//            compositeVirtualObject.setFunction(functionMapper.toProcessorModelFromDb(functionForDB));

            compositeVirtualObject.setAspect(aspectMapper.toProcessorModelFromDb(compositeVirtualObjectForDB.getAspectId()));
            compositeVirtualObject.setFunction(functionMapper.toProcessorModelFromDb(compositeVirtualObjectForDB.getFunctionId()));
            compositeVirtualObject.setVirtualObjectList(virtualObjectMapper.toVirtualObjectListFromDb(compositeVirtualObjectForDB.getVirtualObjectForDBList()));
        }
        return compositeVirtualObject;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param compositeVirtualObject PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public CompositeVirtualObjectForMQ toMqModelFromPs(IGenericCompositeVirtualObject compositeVirtualObject) {
        CompositeVirtualObjectForMQ compositeVirtualObjectForMQ = null;
        if (compositeVirtualObject != null) {
            compositeVirtualObjectForMQ = new CompositeVirtualObjectForMQ();
            compositeVirtualObjectForMQ.setId(compositeVirtualObject.getId());
            compositeVirtualObjectForMQ.setName(compositeVirtualObject.getName());
            compositeVirtualObjectForMQ.setDescription((compositeVirtualObject.getDescription()));
            AspectForMQ aspectForMQ = aspectMapper.toMqModelFromPs(compositeVirtualObject.getAspect());
            compositeVirtualObjectForMQ.setAspect(aspectForMQ);
            FunctionForMQ functionForMQ = functionMapper.toMqModelFromPs(compositeVirtualObject.getFunction());
            compositeVirtualObjectForMQ.setFunction(functionForMQ);
            //virtual object
            compositeVirtualObjectForMQ.setVirtualObjectList(virtualObjectMapper.toVirtualObjectForMQList(compositeVirtualObject.getVirtualObjectList()));
            StateStoreUtil.copyStateStore(compositeVirtualObject.getStateStore(), compositeVirtualObjectForMQ);
        }
        return compositeVirtualObjectForMQ;
    }

    /**
     * convert List<IGenericCompositeVirtualObject> to List<CompositeVirtualObjectForMQ>.<BR/>
     *
     * @param compositeVirtualObjectList List<IGenericCompositeVirtualObject>
     * @return List<CompositeVirtualObjectForMQ>
     */
    public List<CompositeVirtualObjectForMQ> toCompositevirtualObjectForMQList(List<IGenericCompositeVirtualObject> compositeVirtualObjectList) {
        List<CompositeVirtualObjectForMQ> compositeVirtualObjectForMQList = null;
        if (compositeVirtualObjectList != null && compositeVirtualObjectList.size() > 0) {
            compositeVirtualObjectForMQList = new ArrayList<>();
            for (IGenericCompositeVirtualObject compositeVirtualObject : compositeVirtualObjectList) {
                compositeVirtualObjectForMQList.add(toMqModelFromPs(compositeVirtualObject));
            }
        }
        return compositeVirtualObjectForMQList;
    }

    /**
     * convert List<CompositeVirtualObjectForMQ> to List<IGenericCompositeVirtualObject>.<BR/>
     *
     * @param compositeVirtualObjectForMQList List<CompositeVirtualObjectForMQ>
     * @return List<IGenericCompositeVirtualObject>
     */
    public List<IGenericCompositeVirtualObject> toCompositeVirtualObjectList(List<CompositeVirtualObjectForMQ> compositeVirtualObjectForMQList) {
        List<IGenericCompositeVirtualObject> compositeVirtualObjectList = null;
        if (compositeVirtualObjectForMQList != null && compositeVirtualObjectForMQList.size() > 0) {
            compositeVirtualObjectList = new ArrayList<>();
            for (CompositeVirtualObjectForMQ compositeVirtualObjectForMQ : compositeVirtualObjectForMQList) {
                compositeVirtualObjectList.add(toProcessorModelFromMq(compositeVirtualObjectForMQ));
            }
        }
        return compositeVirtualObjectList;
    }

    /**
     * convert List<CompositeVirtualObjectForDB> to List<IGenericVirtualObject>.<BR/>
     *
     * @param compositeVirtualObjectForDBList List<CompositeVirtualObjectForDB>
     * @return List<IGenericCompositeVirtualObject>
     */
    public List<IGenericCompositeVirtualObject> toCompositeVirtualObjectListFromDb(List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList) {
        List<IGenericCompositeVirtualObject> compositeVirtualObjectList = new ArrayList<>();
        if (compositeVirtualObjectForDBList != null && compositeVirtualObjectForDBList.size() > 0) {
            compositeVirtualObjectList = new ArrayList<>();
            for (CompositeVirtualObjectForDB compositeVirtualObjectForDB : compositeVirtualObjectForDBList) {
                compositeVirtualObjectList.add(toProcessorModelFromDb(compositeVirtualObjectForDB));
            }
        }
        return compositeVirtualObjectList;
    }
}
