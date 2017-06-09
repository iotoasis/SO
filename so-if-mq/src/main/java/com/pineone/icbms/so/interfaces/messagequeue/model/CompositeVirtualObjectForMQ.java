package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite VirtualObject model for MQ.<BR/>
 *
 * Created by uni4love on 2017. 1. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value= JsonInclude.Include.NON_ABSENT, content= JsonInclude.Include.NON_EMPTY)
public class CompositeVirtualObjectForMQ extends ACommonForMQ {

    /**
     * functionality
     */
    protected FunctionalityForMQ functionality;

    /**
     * aspect
     */
    protected AspectForMQ aspect;

    /**
     * virtual object list 
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<VirtualObjectForMQ> virtualObjectList;

    /**
     * constructor<BR/>
     */
    public CompositeVirtualObjectForMQ() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public CompositeVirtualObjectForMQ(String id, String name) {
        super(id, name);
    }

    public FunctionalityForMQ getFunctionality() {
        return functionality;
    }

    public void setFunctionality(FunctionalityForMQ functionality) {
        this.functionality = functionality;
    }

    public AspectForMQ getAspect() {
        return aspect;
    }

    public void setAspect(AspectForMQ aspect) {
        this.aspect = aspect;
    }

    public List<VirtualObjectForMQ> getVirtualObjectList() {
//        return virtualObjectList.stream().filter(element -> element instanceof IGenericVirtualObject).collect(Collectors.toList());
//        return CollectionUtils.castCollection(virtualObjectList, IGenericVirtualObject.class);
        return this.virtualObjectList;
    }

    public void setVirtualObjectList(List<VirtualObjectForMQ> virtualObjectList) {
        this.virtualObjectList = virtualObjectList;
    }

    public void addVirtualObject(VirtualObjectForMQ virtualObject) {
        if (virtualObjectList == null)
            virtualObjectList = new ArrayList<>();
        virtualObjectList.add(virtualObject);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[id = ").append(id);
        sb.append(", name = ").append(name);
        sb.append(",\nfunctionlity: ").append(functionality);
        sb.append(",\naspect: ").append(aspect);
        if (virtualObjectList != null) {
            for (VirtualObjectForMQ vo : virtualObjectList) {
                sb.append(",\nvo: ").append(vo);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
