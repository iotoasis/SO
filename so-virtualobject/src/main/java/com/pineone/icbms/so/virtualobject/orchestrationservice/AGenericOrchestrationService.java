package com.pineone.icbms.so.virtualobject.orchestrationservice;

import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;

import java.util.List;

/**
 * Generic orchestrationservice abstract class.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
abstract public class AGenericOrchestrationService extends AGenericServiceEntity
        implements IGenericOrchestrationService {
    /**
     * orchestrationservice list
     */
    protected List<IGenericOrchestrationService> orchestrationServiceList = null;

    /**
     * composite virtual object list
     */
    protected List<IGenericCompositeVirtualObject> compositeVirtualObjectList = null;

    /**
     * virtual object: composite or general
     */
    protected List<IGenericVirtualObject> virtualObjectList;

    /**
     * constructor
     */
    public AGenericOrchestrationService() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericOrchestrationService(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericOrchestrationService(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id          id
     * @param name        name
     * @param description description
     */
    public AGenericOrchestrationService(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public List<IGenericOrchestrationService> getOrchestrationServiceList() {
        return orchestrationServiceList;
    }

    public void setOrchestrationServiceList(List<IGenericOrchestrationService> orchestrationServiceList) {
        this.orchestrationServiceList = orchestrationServiceList;
    }

    @Override
    public List<IGenericCompositeVirtualObject> getCompositeVirtualObjectList() {
        return compositeVirtualObjectList;
    }

    public void setCompositeVirtualObjectList(List<IGenericCompositeVirtualObject> compositeVirtualObjectList) {
        this.compositeVirtualObjectList = compositeVirtualObjectList;
    }

    @Override
    public List<IGenericVirtualObject> getVirtualObjectList() {
        return virtualObjectList;
    }

    public void setVirtualObjectList(List<IGenericVirtualObject> virtualObjectList) {
        this.virtualObjectList = virtualObjectList;
    }

//    /**
//     * interface for returning Store<BR/>
//     *
//     * @return Map
//     */
//    protected abstract Map createStore();

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        if (orchestrationServiceList != null)
            sb.append(", OrchestrationServiceList: ").append(orchestrationServiceList);
        if (compositeVirtualObjectList != null)
            sb.append(", CompositeVirtualObjectList: ").append(compositeVirtualObjectList);
        if (virtualObjectList != null)
            sb.append(", VirtualObjectList: ").append(virtualObjectList);
        return sb.toString();
    }
}
