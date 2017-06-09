package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DeviceControl model for MQ.<BR/>
 *
 * Created by uni4love on 2017. 1. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value= JsonInclude.Include.NON_ABSENT, content= JsonInclude.Include.NON_EMPTY)
public class DeviceControlForMQ extends VirtualObjectForMQ {

    /**
     * constructor<BR/>
     */
    public DeviceControlForMQ() {
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     */
    public DeviceControlForMQ(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DeviceControlForMQ(String id, String name) {
        this(id);
        this.name = name;;
    }
}
