package com.pineone.icbms.so.devicecontrol.model.virtualdevice;

/**
 * generic virtual devicemapper default class.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 11..
 */
public class DefaultVirtualDevice extends AGenericVirtualDevice {

    @Override
    public String getIsLast() {
        return isLast;
    }
    @Override
    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    /**
     * constructor
     */
    public DefaultVirtualDevice() {
    }

    /**
     * constructor
     *
     * @param id id
     */
    public DefaultVirtualDevice(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public DefaultVirtualDevice(String id, String name) {
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
    public DefaultVirtualDevice(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }
}
