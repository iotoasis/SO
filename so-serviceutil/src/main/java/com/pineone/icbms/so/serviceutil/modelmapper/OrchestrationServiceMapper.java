package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.OrchestrationServiceForMQ;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import com.pineone.icbms.so.virtualobject.orchestrationservice.DefaultOrchestrationService;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestration service mapper.<BR/>
 * <p>
 * Created by uni4love on 2017. 5. 15..
 */
public class OrchestrationServiceMapper implements IModelMapper<IGenericOrchestrationService, OrchestrationServiceForDB, OrchestrationServiceForMQ> {

    /**
     * virtualobject mapper
     */
    private static VirtualObjectMapper virtualObjectMapper = new VirtualObjectMapper();

    /**
     * composite virtual object mapper
     */
    private static CompositeVirtualObjectMapper compositeVirtualObjectMapper = new CompositeVirtualObjectMapper();

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param orchestrationServiceForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericOrchestrationService toProcessorModelFromMq(OrchestrationServiceForMQ orchestrationServiceForMQ) {
        DefaultOrchestrationService orchestrationService = null;
        if (orchestrationServiceForMQ != null) {
            orchestrationService = new DefaultOrchestrationService();
            orchestrationService.setId(orchestrationServiceForMQ.getId());
            orchestrationService.setName(orchestrationServiceForMQ.getName());
            orchestrationService.setDescription(orchestrationServiceForMQ.getDescription());
            orchestrationService.setOrchestrationServiceList(toOrchestrationServiceListFromMq(orchestrationServiceForMQ.getOrchestrationServiceList()));
            orchestrationService.setCompositeVirtualObjectList(compositeVirtualObjectMapper.toCompositeVirtualObjectList(orchestrationServiceForMQ.getCompositeVirtualObjectList()));
            orchestrationService.setVirtualObjectList(virtualObjectMapper.toVirtualObjectList(orchestrationServiceForMQ.getVirtualObjectList()));
            StateStoreUtil.copyStateStore(orchestrationServiceForMQ.getStateStore(), orchestrationService);
        }
        return orchestrationService;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param orchestrationServiceForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericOrchestrationService toProcessorModelFromDb(OrchestrationServiceForDB orchestrationServiceForDB) {
        DefaultOrchestrationService orchestrationService = null;
        if (orchestrationServiceForDB != null) {
            orchestrationService = new DefaultOrchestrationService();
            orchestrationService.setId(orchestrationServiceForDB.getId());
            orchestrationService.setName(orchestrationServiceForDB.getName());
            orchestrationService.setDescription(orchestrationServiceForDB.getDescription());
            orchestrationService.setCompositeVirtualObjectList(virtualObjectMapper.toCompositeVirtualObjectListFromDb(orchestrationServiceForDB.getCompositeVirtualObjectForDBList()));
            orchestrationService.setVirtualObjectList(virtualObjectMapper.toVirtualObjectListFromDb(orchestrationServiceForDB.getVirtualObjectForDBList()));
            //TODO: orchestration service
//            orchestrationService.setOrchestrationServiceList(toOrchestrationServiceListFromDb(orchestrationServiceForDB.get));

        }
        return orchestrationService;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param orchestrationService PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public OrchestrationServiceForMQ toMqModelFromPs(IGenericOrchestrationService orchestrationService) {
        OrchestrationServiceForMQ orchestrationServiceForMQ = null;
        if (orchestrationService != null) {
            orchestrationServiceForMQ = new OrchestrationServiceForMQ(orchestrationService.getId(), orchestrationService.getName());
            //description
            orchestrationServiceForMQ.setDescription(orchestrationService.getDescription());
            //composite virtual object for messagequeue
            orchestrationServiceForMQ.setCompositeVirtualObjectList(
                    compositeVirtualObjectMapper.toCompositevirtualObjectForMQList(orchestrationService.getCompositeVirtualObjectList()));
            //virtual object for messagequeue
            orchestrationServiceForMQ.setVirtualObjectList(
                    virtualObjectMapper.toVirtualObjectForMQList(orchestrationService.getVirtualObjectList()));
            //timestamp
            orchestrationServiceForMQ.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            StateStoreUtil.copyStateStore(orchestrationService.getStateStore(), orchestrationServiceForMQ);
        }
        return orchestrationServiceForMQ;
    }

    /**
     * convert List<OrchestrationServiceForMQ> to List<IGenericOrchestrationService>.<BR/>
     *
     * @param orchestrationServiceForMQList List<OrchestrationServiceForMQ>
     * @return List<IGenericOrchestrationService>
     */
    public List<IGenericOrchestrationService> toOrchestrationServiceListFromMq(List<OrchestrationServiceForMQ> orchestrationServiceForMQList) {
        List<IGenericOrchestrationService> orchestrationServiceList = null;
        if (orchestrationServiceForMQList != null && orchestrationServiceForMQList.size() > 0) {
            orchestrationServiceList = new ArrayList<>();
            for (OrchestrationServiceForMQ orchestrationServiceForMQ : orchestrationServiceForMQList) {
                orchestrationServiceList.add(toProcessorModelFromMq(orchestrationServiceForMQ));
            }
        }
        return orchestrationServiceList;
    }

    /**
     * List<OrchestrationServiceForDB> to List<IGenericOrchestrationService>.<BR/>
     *
     * @param orchestrationServiceForDBList List<OrchestrationServiceForDB>
     * @return List<IGenericOrchestrationService>
     */
    public List<IGenericOrchestrationService> toOrchestrationServiceListFromDb(List<OrchestrationServiceForDB> orchestrationServiceForDBList) {
        List<IGenericOrchestrationService> orchestrationServiceList = null;
        if (orchestrationServiceForDBList != null && orchestrationServiceForDBList.size() > 0) {
            orchestrationServiceList = new ArrayList<>();
            for (OrchestrationServiceForDB orchestrationServiceForDB : orchestrationServiceForDBList) {
                orchestrationServiceList.add(toProcessorModelFromDb(orchestrationServiceForDB));
            }
        }
        return orchestrationServiceList;
    }
}
