package com.pineone.icbms.so.interfaces.sda.handle;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.DeviceControlValue;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;
import com.pineone.icbms.so.virtualobject.operation.IGenericOperation;
import com.pineone.icbms.so.virtualobject.unit.IGenericUnit;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;

import java.util.List;

/**
 * SDA system interface implements.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 23.
 */
public class SdaClient {
    /**
     * retreive function list.<BR/>
     *
     * @param locationUri location uri
     * @return function list
     */
    public List<IGenericFunction> retreiveFunctionlaityListByLocation(String locationUri) {
        //implements..
        return null;
    }

    /**
     * retreive aspect.<BR/>
     *
     * @param functionUri function uri
     * @return aspect
     */
    public IGenericAspect retreiveAspectByFunction(String functionUri) {
        //implements..
        return null;
    }

    /**
     * retreive unit.<BR/>
     *
     * @param aspectUri aspect uri
     * @return unit
     */
    public IGenericUnit retreiveUnit(String aspectUri) {
        //implements..
        return null;
    }

    /**
     * retreive devicemapper list.<BR/>
     *
     * @param functionUri function uri
     * @param locationUri      location uri
     * @return devicemapper list
     */
    public List<IGenericVirtualDevice> retreiveDeviceList(String functionUri, String locationUri) {
        //implements..
        return null;
    }

    /**
     * retreive operation list.<BR/>
     *
     * @param deviceUri devicemapper uri
     * @return operation list
     */
    public List<IGenericOperation> retreiveOperationList(String deviceUri) {
        //implements..
        return null;
    }

    /**
     * retreive control values.<BR/>
     *
     * @param functionUri function
     * @param aspect           aspect
     * @param locationUri      location uri
     * @param deviceUri        device uri
     * @return List<IDeviceControlValue>
     */
    public List<DeviceControlValue> retreiveDeviceControlValues(String functionUri, String aspect, String locationUri, String deviceUri) {
        return null;
    }
}
