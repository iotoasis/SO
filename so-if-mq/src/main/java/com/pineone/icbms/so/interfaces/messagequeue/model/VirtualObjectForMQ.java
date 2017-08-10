package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * VirtualObject model for MQ.<BR/>
 *
 * Created by uni4love on 2017. 1. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value= JsonInclude.Include.NON_ABSENT, content= JsonInclude.Include.NON_EMPTY)
public class VirtualObjectForMQ extends ACommonForMQ {

    /**
     * function
     */
    protected FunctionForMQ function;

    /**
     * aspect
     */
    protected AspectForMQ aspect;

    /**
     * constructor<BR/>
     */
    public VirtualObjectForMQ() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public VirtualObjectForMQ(String id, String name) {
        super(id, name);
    }

    public FunctionForMQ getFunction() {
        return function;
    }

    public void setFunction(FunctionForMQ function) {
        this.function = function;
    }

    public AspectForMQ getAspect() {
        return aspect;
    }

    public void setAspect(AspectForMQ aspect) {
        this.aspect = aspect;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append(super.toString());
        sb.append(", function: ").append(function);
        sb.append(", aspect: ").append(aspect);
        sb.append("]");
        return sb.toString();
    }
}
