package com.pineone.icbms.so.devicecontrol.model.virtualdevice;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.driver.IGenericDeviceDriver;
import com.pineone.icbms.so.virtualobject.AGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;

/**
 * generic virtual devicemapper abstract class.<BR/>
 *
 * Created by uni4love on 2017. 1. 11..
 */
abstract public class AGenericVirtualDevice extends AGenericVirtualObject implements IGenericVirtualDevice {
    /**
     * driver for devicemapper
     */
    protected IGenericDeviceDriver deviceDriver;

    /**
     * device driver class name
     */
    protected String driverClassName;

    /**
     * constructor
     */
    public AGenericVirtualDevice() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericVirtualDevice(String id) {
        this();
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericVirtualDevice(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     * @description description
     */
    public AGenericVirtualDevice(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public String getDriverClassName() {
        return this.driverClassName;
    }

    public IGenericDeviceDriver getDeviceDriver() {
        return deviceDriver;
    }

    public void setDeviceDriver(IGenericDeviceDriver deviceDriver) {
        this.deviceDriver = deviceDriver;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", driverClassName: ").append(driverClassName);
        if (deviceDriver != null)
            sb.append(", deviceDriver: ").append(deviceDriver);
        return sb.toString();
    }
}
