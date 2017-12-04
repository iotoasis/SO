package com.pineone.icbms.so.serviceutil.modelmapper;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.DefaultVirtualDevice;
import com.pineone.icbms.so.virtualobject.AGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.DefaultAspect;
import com.pineone.icbms.so.virtualobject.function.DefaultFunction;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;

import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.AspectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.DeviceControlForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.FunctionForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.VirtualObjectForMQ;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.conversion.IModelMapper;
//import org.springframework.beans.BeanUtils;

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
    private static FunctionalityMapper functionalityMapper = new FunctionalityMapper();

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
     
            AspectForMQ aspectMq = deviceControlForMQ.getAspect();
            DefaultAspect aspect = null;
            if (aspectMq!=null) {
            	aspect = new DefaultAspect(aspectMq.getId());
            	aspect.setUri(aspectMq.getUri());
            	virtualDevice.setAspect(aspect);
            }

            FunctionForMQ funcMq = deviceControlForMQ.getFunction();
            DefaultFunction function = null;
            if (funcMq!=null) {
            	function = new DefaultFunction(funcMq.getId());
            	function.setUri(funcMq.getUri());
            	virtualDevice.setFunction(function);
            }
            
            AGenericVirtualObject virtualObject = (AGenericVirtualObject)virtualDevice;
            virtualObject.setDeviceId(deviceControlForMQ.getDeviceId());
            virtualObject.setVoVale(deviceControlForMQ.getVoValue());
            
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
            
            //virtualDevice.setFunction(functionalityMapper.toProcessorModelFromDb(deviceForDB.getFunction()));
            DefaultFunction function =
                    new DefaultFunction(deviceForDB.getFunctionalityId(), "functionality name", "", deviceForDB.getFunctionalityId());
            virtualDevice.setFunction(function);

            // virtualDevice.setAspect(aspectMapper.toProcessorModelFromDb(deviceForDB.getAspect()));
            DefaultAspect aspect = 
            		new DefaultAspect(deviceForDB.getAspectId(), "aspect name", "", deviceForDB.getAspectId());
            virtualDevice.setAspect(aspect);
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
    	VirtualObjectForMQ deviceControlForMQ = null;
        if(virtualDevice != null) {
            deviceControlForMQ = new DeviceControlForMQ();
            deviceControlForMQ.setId(virtualDevice.getId());
            deviceControlForMQ.setName(virtualDevice.getName());
            deviceControlForMQ.setDescription(virtualDevice.getDescription());
            deviceControlForMQ.setFunction(functionalityMapper.toMqModelFromPs(virtualDevice.getFunction()));
            deviceControlForMQ.setAspect(aspectMapper.toMqModelFromPs(virtualDevice.getAspect()));
            
            deviceControlForMQ.setDeviceId(virtualDevice.getDeviceId());
            deviceControlForMQ.setVoValue(virtualDevice.getVoVale());
            
            StateStoreUtil.copyStateStore(virtualDevice.getStateStore(), deviceControlForMQ);

            // simulator
            ((DeviceControlForMQ)deviceControlForMQ).setIsLast(virtualDevice.getIsLast());

//            BeanUtils.copyProperties(deviceControlForMQ, virtualDevice);
        }
        return (DeviceControlForMQ)deviceControlForMQ;
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
