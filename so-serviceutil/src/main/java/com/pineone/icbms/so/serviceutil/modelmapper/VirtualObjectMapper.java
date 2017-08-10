package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.AspectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.FunctionForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.VirtualObjectForMQ;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.DefaultVirtualObject;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.composite.DefaultCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Virtual object mapper.<BR/>
 *
 * Created by uni4love on 2017. 5. 15..
 */
public class VirtualObjectMapper implements IModelMapper<IGenericVirtualObject, VirtualObjectForDB,
        VirtualObjectForMQ> {
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
     * @param virtualObjectForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericVirtualObject toProcessorModelFromMq(VirtualObjectForMQ virtualObjectForMQ) {
        DefaultVirtualObject virtualObject = null;
        if (virtualObjectForMQ != null) {
            virtualObject = new DefaultVirtualObject();
            virtualObject.setId(virtualObjectForMQ.getId());
            virtualObject.setName(virtualObjectForMQ.getName());
            IGenericAspect aspect = aspectMapper.toProcessorModelFromMq(virtualObjectForMQ.getAspect());
            virtualObject.setAspect(aspect);
            IGenericFunction function = functionMapper.toProcessorModelFromMq(virtualObjectForMQ.getFunction());
            virtualObject.setFunction(function);
            StateStoreUtil.copyStateStore(virtualObjectForMQ.getStateStore(), virtualObject);
        }
        return virtualObject;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param virtualObjectForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericVirtualObject toProcessorModelFromDb(VirtualObjectForDB virtualObjectForDB) {
        DefaultVirtualObject virtualObject = null;
        if (virtualObjectForDB != null) {
            virtualObject = new DefaultVirtualObject();
            virtualObject.setId(virtualObjectForDB.getId());
            virtualObject.setName(virtualObjectForDB.getName());
//            virtualObject.setAspect(aspectMapper.toProcessorModelFromDb(new AspectForDB(virtualObjectForDB.getAspectId())));
//            virtualObject.setFunction(functionMapper.toProcessorModelFromDb(new FunctionForDB(virtualObjectForDB.getFunctionId())));
            virtualObject.setAspect(aspectMapper.toProcessorModelFromDb(virtualObjectForDB.getAspectId()));
            virtualObject.setFunction(functionMapper.toProcessorModelFromDb(virtualObjectForDB.getFunctionId()));
        }
        return virtualObject;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param virtualObject PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public VirtualObjectForMQ toMqModelFromPs(IGenericVirtualObject virtualObject) {
        VirtualObjectForMQ virtualObjectForMQ = null;
        if (virtualObject != null) {
            virtualObjectForMQ = new VirtualObjectForMQ();
            virtualObjectForMQ.setId(virtualObject.getId());
            virtualObjectForMQ.setName(virtualObject.getName());
            virtualObjectForMQ.setDescription((virtualObject.getDescription()));
            AspectForMQ aspectForMQ = aspectMapper.toMqModelFromPs(virtualObject.getAspect());
            virtualObjectForMQ.setAspect(aspectForMQ);
            FunctionForMQ functionForMQ = functionMapper.toMqModelFromPs(virtualObject.getFunction());
            virtualObjectForMQ.setFunction(functionForMQ);
            StateStoreUtil.copyStateStore(virtualObject.getStateStore(), virtualObjectForMQ);

        }
        return virtualObjectForMQ;
    }

    /**
     * convert List<IGenericVirtualObject> to List<VirtualObjectForMQ>.<BR/>
     *
     * @param virtualObjectList List<IGenericVirtualObject>
     * @return List<VirtualObjectForMQ>
     */
    public List<VirtualObjectForMQ> toVirtualObjectForMQList(List<IGenericVirtualObject> virtualObjectList) {
        List<VirtualObjectForMQ> virtualObjectForMQList = null;
        if (virtualObjectList != null && virtualObjectList.size() > 0) {
            virtualObjectForMQList = new ArrayList<>();
            for (IGenericVirtualObject virtualObject : virtualObjectList) {
                virtualObjectForMQList.add(toMqModelFromPs(virtualObject));
            }
        }
        return virtualObjectForMQList;
    }

    /**
     * convert List<VirtualObjectForMQ> to List<IGenericVirtualObject>.<BR/>
     *
     * @param virtualObjectForMQList List<VirtualObjectForMQ>
     * @return List<IGenericVirtualObject>
     */
    public List<IGenericVirtualObject> toVirtualObjectList(List<VirtualObjectForMQ> virtualObjectForMQList) {
        List<IGenericVirtualObject> virtualObjectList = null;
        if (virtualObjectForMQList != null && virtualObjectForMQList.size() > 0 ) {
            virtualObjectList = new ArrayList<>();
            for (VirtualObjectForMQ virtualObjectForMQ : virtualObjectForMQList) {
                virtualObjectList.add(toProcessorModelFromMq(virtualObjectForMQ));
            }
        }
        return virtualObjectList;
    }

    /**
     * convert to List<VirtualObjectForDB> model from List<IGenericVirtualObject>.<BR/>
     *
     * @param virtualObjectForDBList List<VirtualObjectForDB>
     * @return List<IGenericVirtualObject>
     */
    public List<IGenericVirtualObject> toVirtualObjectListFromDb(List<VirtualObjectForDB> virtualObjectForDBList) {
        List<IGenericVirtualObject> virtualObjectList = null;
        if(virtualObjectForDBList != null && virtualObjectForDBList.size() > 0) {
            virtualObjectList = new ArrayList<>();
            for(VirtualObjectForDB virtualObjectForDB:virtualObjectForDBList) {
                virtualObjectList.add(toProcessorModelFromDb(virtualObjectForDB));
            }
        }
        return virtualObjectList;
    }

    /**
     * convert CompositeVirtualObjectForDB to IGenericCompositeVirtualObject.<BR/>
     *
     * @param compositeVirtualObjectForDB CompositeVirtualObjectForDB
     * @return List<IGenericVirtualObject>
     */
    public IGenericCompositeVirtualObject toProcessModelFromDb(CompositeVirtualObjectForDB compositeVirtualObjectForDB) {
        DefaultCompositeVirtualObject compositeVirtualObject = null;
        if (compositeVirtualObjectForDB != null) {
            compositeVirtualObject = new DefaultCompositeVirtualObject(compositeVirtualObjectForDB.getId(), compositeVirtualObjectForDB.getName(), compositeVirtualObjectForDB.getDescription());
            compositeVirtualObject.setVirtualObjectList(toVirtualObjectListFromDb(compositeVirtualObjectForDB.getVirtualObjectForDBList()));
        }
        return compositeVirtualObject;
    }

    /**
     * convert to List<VirtualObjectForDB> model from List<IGenericVirtualObject>.<BR/>
     *
     * @param compositeVirtualObjectForDBList List<VirtualObjectForDB>
     * @return List<IGenericCompositeVirtualObject>
     */
    public List<IGenericCompositeVirtualObject> toCompositeVirtualObjectListFromDb(List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList) {
        List<IGenericCompositeVirtualObject> compositeVirtualObjectList = null;
        if(compositeVirtualObjectForDBList != null && compositeVirtualObjectForDBList.size() > 0) {
            compositeVirtualObjectList = new ArrayList<>();
            for(CompositeVirtualObjectForDB compositeVirtualObjectForDB:compositeVirtualObjectForDBList) {
                compositeVirtualObjectList.add(toProcessModelFromDb(compositeVirtualObjectForDB));
            }
        }
        return compositeVirtualObjectList;
    }
}
