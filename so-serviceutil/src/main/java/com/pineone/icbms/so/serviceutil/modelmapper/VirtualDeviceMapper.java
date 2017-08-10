package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.DefaultVirtualDevice;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.DeviceControlForMQ;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.conversion.IModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Virtual device mapper.<BR/>
 *
 * Created by uni4love on 2017. 5. 16..
 */
public class VirtualDeviceMapper implements IModelMapper<IGenericVirtualDevice, DeviceForDB, DeviceControlForMQ> {
    /**
     * aspect mapper
     */
    private static AspectMapper aspectMapper = new AspectMapper();

    /**
     * function mapper
     */
    private static FunctionMapper functionMapper = new FunctionMapper();

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param deviceControlForMQ MQ_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericVirtualDevice toProcessorModelFromMq(DeviceControlForMQ deviceControlForMQ) {
        DefaultVirtualDevice virtualDevice = null;
        if (deviceControlForMQ != null) {
            virtualDevice = new DefaultVirtualDevice();
            virtualDevice.setId(deviceControlForMQ.getId());
            virtualDevice.setName(deviceControlForMQ.getName());
            virtualDevice.setDescription(deviceControlForMQ.getDescription());
            StateStoreUtil.copyStateStore(deviceControlForMQ.getStateStore(), virtualDevice);
            virtualDevice.setIsLast(deviceControlForMQ.getIsLast());
        }
        return virtualDevice;
    }

    /**
     * convert MQ model to Processor model.<BR/>
     *
     * @param deviceForDB DB_MODEL
     * @return PS_MODEL
     */
    @Override
    public IGenericVirtualDevice toProcessorModelFromDb(DeviceForDB deviceForDB) {
        DefaultVirtualDevice virtualDevice = null;
        if (deviceForDB != null) {
            virtualDevice = new DefaultVirtualDevice();
            virtualDevice.setId(deviceForDB.getId());
            virtualDevice.setName(deviceForDB.getName());
            virtualDevice.setFunction(functionMapper.toProcessorModelFromDb(deviceForDB.getFunction()));
            virtualDevice.setAspect(aspectMapper.toProcessorModelFromDb(deviceForDB.getAspect()));
        }
        return virtualDevice;
    }

    /**
     * convert Processor model to MQ model.<BR/>
     *
     * @param virtualDevice PS_MODEL
     * @return MQ_MODEL
     */
    @Override
    public DeviceControlForMQ toMqModelFromPs(IGenericVirtualDevice virtualDevice) {
        DeviceControlForMQ deviceControlForMQ = null;
        if(virtualDevice != null) {
            deviceControlForMQ = new DeviceControlForMQ();
            deviceControlForMQ.setId(virtualDevice.getId());
            deviceControlForMQ.setName(virtualDevice.getName());
            deviceControlForMQ.setDescription(virtualDevice.getDescription());
            deviceControlForMQ.setFunction(functionMapper.toMqModelFromPs(virtualDevice.getFunction()));
            deviceControlForMQ.setAspect(aspectMapper.toMqModelFromPs(virtualDevice.getAspect()));
            StateStoreUtil.copyStateStore(virtualDevice.getStateStore(), deviceControlForMQ);
            // TODO simulator
            deviceControlForMQ.setIsLast(virtualDevice.getIsLast());

//            BeanUtils.copyProperties(deviceControlForMQ, virtualDevice);
        }
        return deviceControlForMQ;
    }

    /**
     * List<DeviceForDB> to List<IGenericVirtualDevice>.<BR/>
     *
     * @param deviceForDbList List<DeviceForDB>
     * @return List<IGenericVirtualDevice>
     */
    public List<IGenericVirtualDevice> toVirtualDeviceList(List<DeviceForDB> deviceForDbList) {
        List<IGenericVirtualDevice> virtualDeviceList = null;
        if (deviceForDbList != null && deviceForDbList.size() > 0) {
            virtualDeviceList = new ArrayList<>();
            for (DeviceForDB deviceForDb : deviceForDbList) {
                virtualDeviceList.add(toProcessorModelFromDb(deviceForDb));
            }
        }
        return virtualDeviceList;
    }
}
