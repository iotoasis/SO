package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * OrchestrationService model for MQ.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class OrchestrationServiceForMQ extends ACommonForMQ {

    /**
     * orchestration service list
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<OrchestrationServiceForMQ> orchestrationServiceList;

    /**
     * composite virtual object list
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<CompositeVirtualObjectForMQ> compositeVirtualObjectList;

    /**
     * virtual object list 
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<VirtualObjectForMQ> virtualObjectList;

    /**
     * constructor
     */
    public OrchestrationServiceForMQ() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public OrchestrationServiceForMQ(String id, String name) {
        super(id, name);
    }

    public List<OrchestrationServiceForMQ> getOrchestrationServiceList() {
//        return orchestrationServiceList.stream().filter(element -> element instanceof IGenericOrchestrationService).collect(Collectors.toList());
//        return CollectionUtils.castCollection(orchestrationServiceList, IGenericOrchestrationService.class);
        return orchestrationServiceList;
    }

    public void setOrchestrationServiceList(List<OrchestrationServiceForMQ> orchestrationServiceList) {
        this.orchestrationServiceList = orchestrationServiceList;
    }

    public List<CompositeVirtualObjectForMQ> getCompositeVirtualObjectList() {
//        return compositeVirtualObjectList.stream().filter(element -> element instanceof IGenericCompositeVirtualObject).collect(Collectors.toList());
//        return CollectionUtils.castCollection(compositeVirtualObjectList, IGenericCompositeVirtualObject.class);
        return compositeVirtualObjectList;
    }

    public void setCompositeVirtualObjectList(List<CompositeVirtualObjectForMQ> compositeVirtualObjectList) {
        this.compositeVirtualObjectList = compositeVirtualObjectList;
    }

    public List<VirtualObjectForMQ> getVirtualObjectList() {
//        return virtualObjectList.stream().filter(element -> element instanceof IGenericVirtualObject).collect(Collectors.toList());
//        return CollectionUtils.castCollection(virtualObjectList, IGenericVirtualObject.class);
        return virtualObjectList;
    }

    public void setVirtualObjectList(List<VirtualObjectForMQ> virtualObjectList) {
        this.virtualObjectList = virtualObjectList;
    }

    public void addVirtualObject(VirtualObjectForMQ virtualObject) {
        if (virtualObjectList == null)
            virtualObjectList = new ArrayList<>();
        virtualObjectList.add(virtualObject);
    }

    public void addCompositeVirtualObject(CompositeVirtualObjectForMQ compositeVirtualObject) {
        if (compositeVirtualObjectList == null)
            compositeVirtualObjectList = new ArrayList<>();
        compositeVirtualObjectList.add(compositeVirtualObject);
    }

    public void addOrchestrationService(OrchestrationServiceForMQ orchestrationService) {
        if (orchestrationServiceList == null)
            orchestrationServiceList = new ArrayList<>();
        orchestrationServiceList.add(orchestrationService);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString());
        if (orchestrationServiceList != null) {
            for (OrchestrationServiceForMQ os : orchestrationServiceList) {
                sb.append(",\nos: ").append(os);
            }
        }
        if (compositeVirtualObjectList != null) {
            for (CompositeVirtualObjectForMQ cv : compositeVirtualObjectList) {
                sb.append(",\ncvo: ").append(cv);
            }
        }
        if (virtualObjectList != null) {
            for (VirtualObjectForMQ vo : virtualObjectList) {
                sb.append(",\nvo: ").append(vo);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
