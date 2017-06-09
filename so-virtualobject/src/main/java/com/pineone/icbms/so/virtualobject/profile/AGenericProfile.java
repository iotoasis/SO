package com.pineone.icbms.so.virtualobject.profile;

import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;

import java.util.List;

/**
 * Generic profile abstract class.<BR/>
 * Created by uni4love on 2016. 11. 17..
 */
abstract public class AGenericProfile extends AGenericServiceEntity implements IGenericProfile {

    /**
     * context model
     */
    protected IGenericContextModel contextModel;

    /**
     * orchestrationservice
     */
    protected IGenericOrchestrationService orchestrationService;

    /**
     * location
     */
    protected IGenericLocation location;

    /**
     * devicemapper list
     */
    protected List<IGenericVirtualDevice> deviceList;

    /**
     * constructor<BR/>
     */
    public AGenericProfile() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericProfile(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericProfile(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * return context model.<BR/>
     *
     * @return context model
     */
    @Override
    public IGenericContextModel getContextModel() {
        return contextModel;
    }

    /**
     * return orchestrationservice<BR/>
     *
     * @return orchestrationservice
     */
    @Override
    public IGenericOrchestrationService getOrchestrationService() {
        return orchestrationService;
    }

    /**
     * return location.<BR/>
     *
     * @return location
     */
    @Override
    public IGenericLocation getLocation() {
        return location;
    }

    /**
     * return devicemapper list.<BR/>
     *
     * @return devicemapper list
     */
    public List<IGenericVirtualDevice> getDeviceList() {
        return deviceList;
    }

    /**
     * set orchestrationservice<BR/>
     *
     * @param orchestrationService
     */
    public void setOrchestrationService(IGenericOrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    /**
     * set context model.<BR/>
     *
     * @param contextModel
     */
    public void setContextModel(IGenericContextModel contextModel) {
        this.contextModel = contextModel;
    }

    /**
     * set location.<BR/>
     *
     * @param location
     */
    public void setLocation(IGenericLocation location) {
        this.location = location;
    }
}
