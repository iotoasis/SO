package com.pineone.icbms.so.virtualobject;

import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;

/**
 * abstract Generic virtual object.<BR/>
 * <p>
 * Created by uni4love on 2016. 11. 16..
 */
abstract public class AGenericVirtualObject extends AGenericServiceEntity
        implements IGenericVirtualObject {
	
	/**
     * function
     */
    protected IGenericFunction function;

    /**
     * aspect
     */
    protected IGenericAspect aspect;

    protected String deviceId;
    protected String voValueType;
    protected String voValue;
    protected String isLast;
    
    /**
     * constructor
     */
    public AGenericVirtualObject() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericVirtualObject(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id   id
     * @param name name
     */
    public AGenericVirtualObject(String id, String name) {
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
    public AGenericVirtualObject(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public IGenericFunction getFunction() {
        return function;
    }

    public void setFunction(IGenericFunction function) {
        this.function = function;
    }

    @Override
    public IGenericAspect getAspect() {
        return aspect;
    }

    public void setAspect(IGenericAspect aspect) {
        this.aspect = aspect;
    }

	@Override
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String getVoValueType() {
		return voValueType;
	}

    public void setVoValueType(String voValueType) {
		this.voValueType = voValueType;
	}
	
	@Override
	public String getVoValue() {
		return voValue;
	}

	public void setVoValue(String voValue) {
		this.voValue = voValue;
	}
	
    public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}

	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", function: ").append(function);
        sb.append(", aspect: ").append(aspect);
        return sb.toString();
    }
}
