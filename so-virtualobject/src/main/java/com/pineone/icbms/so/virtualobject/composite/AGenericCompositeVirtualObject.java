package com.pineone.icbms.so.virtualobject.composite;

import com.pineone.icbms.so.virtualobject.AGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Composite Virtual Object abstract generic class.<BR/>
 *
 * Created by uni4love on 2016. 11. 17..
 */
abstract public class AGenericCompositeVirtualObject extends AGenericServiceEntity /* AGenericVirtualObject*/ implements IGenericCompositeVirtualObject {

	private String cvoType;

	private String physicalDeviceTypeId;
	private String deviceId;

	private String baseCvoId;
	private String locationId;
	private String osId;

	@Override
	public String getPhysicalDeviceTypeId() {
		return physicalDeviceTypeId;
	}

	public void setPhysicalDeviceTypeId(String physicalDeviceTypeId) {
		this.physicalDeviceTypeId = physicalDeviceTypeId;
	}

	@Override
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Override
	public String getBaseCvoId() {
		return baseCvoId;
	}

	public void setBaseCvoId(String baseCvoId) {
		this.baseCvoId = baseCvoId;
	}

	@Override
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public String getCvoType() {
		return cvoType;
	}

	public void setCvoType(String cvoType) {
		this.cvoType = cvoType;
	}

	public String getOsId() {
		return osId;
	}

	public void setOsId(String osId) {
		this.osId = osId;
	}

	/**
     * virtual object list
     */
    protected List<IGenericVirtualObject> virtualObjectList = new ArrayList<>();

    /**
     * current depth
     */
    private int currentDepth = 0;

    /**
     * constructor<BR/>
     */
    public AGenericCompositeVirtualObject() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericCompositeVirtualObject(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     */
    public AGenericCompositeVirtualObject(String id, String name) {
        this(id);
        this.name = name;
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     * @param name name
     * @param description description
     */
    public AGenericCompositeVirtualObject(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    @Override
    public List<IGenericVirtualObject> getVirtualObjectList() {
        return virtualObjectList;
    }

    public void setVirtualObjectList(List<IGenericVirtualObject> virtualObjectList) {
        this.virtualObjectList = virtualObjectList;
    }

    /**
     * add a Virtual Object.<BR/>
     *
     * @param virtualObject virtual object
     */
    @Override
    public void addVirtualObject(IGenericVirtualObject virtualObject) {
        //CVO에 하위 CVO를 추가할 경우
        if(virtualObject instanceof IGenericCompositeVirtualObject)
        {
            //increase to current+1
            ((IGenericCompositeVirtualObject) virtualObject).setCurrentDepth(getCurrentDepth()+1);
        }
        this.virtualObjectList.add(virtualObject);
    }

    /**
     * return current hop count.<BR/>
     *
     * @return hop count
     */
    @Override
    public int getCurrentDepth() {
        return currentDepth;
    }

    /**
     * set current depth.<BR/>
     *
     * @param depth depth
     */
    @Override
    public void setCurrentDepth(int depth) {
        this.currentDepth = depth;
    }

    /**
     * return function list.<BR/>
     *
     * @return function list
     */
    public List<IGenericFunction> getFunctionList() {
        //implements...
        return null;
    }

    /**
     * return aspect list.<BR/>
     *
     * @return aspect list
     */
    public List<IGenericAspect> getAspectList() {
        //implements...
        return null;
    }

    /**
     * return aspect-function list.<BR/>
     *
     * @return aspect-function list
     */
    public Map<IGenericAspect, IGenericFunction> getAspectFunctionList() {
        //implements...
        return null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        //vo list
        sb.append(", \nvirtual object list: ").append(virtualObjectList);
        return sb.toString();
    }
}
