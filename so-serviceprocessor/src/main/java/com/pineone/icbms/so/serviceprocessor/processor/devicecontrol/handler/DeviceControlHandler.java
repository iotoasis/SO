package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.handler;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.DeviceControlValue;
import com.pineone.icbms.so.devicecontrol.model.virtualdevice.driver.IGenericDeviceDriver;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlForDB;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.tracking.handler.TrackingHandler;
import com.pineone.icbms.so.interfaces.si.handle.DeviceManager;
import com.pineone.icbms.so.interfaces.si.ref.ClientProfile;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * device control handler.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 20..
 */
public class DeviceControlHandler extends AProcessHandler {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Database Manager
     */
    //protected IDatabaseManager databaseManager;

    /**
     * device manager
     */
    protected DeviceManager deviceManager = new DeviceManager();

    /**
     * constructor
     *
     * @param databaseManager DatabaseManager
     */
    public DeviceControlHandler(IDatabaseManager databaseManager)  {
        super(databaseManager);
    }

    /**
     * handle a message.<BR/>
     *
     * @param handleMessage
     */
    @Override
    public void handle(Object handleMessage) {

    }

    /**
     * device control handle.<BR/>
     *
     * @param virtualDevice  IGenericVirtualDevice
     * @param contextModelId context model id
     */
    public void handle(IGenericVirtualDevice virtualDevice, String locationUri, String contextModelId) {
        //get DeviceDriver from sda by virtual object features
//        SdaClient sdaClient = new SdaClient();
//        List<IGenericVirtualDevice> deviceList = sdaClient.retreiveDeviceList(null, locationUri);
        //create control list
        DeviceControlForDB deviceControlForDB = databaseManager.getDeviceControlByDeviceIdAndContextModelID(virtualDevice.getId(), contextModelId);
        log.debug("DeviceControlForDB: {}", deviceControlForDB);
//        DeviceControlValue deviceControlValue = ModelMapper.toDeviceControlValue(deviceControlForDB);
//        LinkedHashMap<IGenericDeviceDriver, List<DeviceControlValue>> controlList = new LinkedHashMap<>();
//        if (deviceList != null) {
//            for (IGenericVirtualDevice device : deviceList) {
//                String driverClassName = device.getDriverClassName();
        //load devicemapper driver
//                IGenericDeviceDriver deviceDriver = new DeviceDriverLoader().loadDeviceDriver(Settings.DEVICE_DRIVER_PATH, driverClassName);
        //retreive values from sda
//                List<DeviceControlValue> values = sdaClient.retreiveDeviceControlValues(null, null, locationUri, device.getId());
//                controlList.put(deviceDriver, values);
//            }
        if (deviceControlForDB != null) {
            TrackingEntity tracking = getTracking();

            // TODO 시뮬레이션인 경우 실제 디바이스 실행을 위해
            if (tracking.getSimulatorType() == null) {
                try {
                    //control device
                    String commandId = controlDevice(virtualDevice, deviceControlForDB.getValue());

                    tracking.setCommandId(commandId);
                    TrackingHandler.send(tracking
                            , getClass().getSimpleName() + " " + virtualDevice.getName() + " " + virtualDevice.getId());
                    log.warn("controlDevice: {}, {}", virtualDevice.getName(), virtualDevice.getId());
                } catch (Exception e) {
                    TrackingHandler.send(tracking
                            , getClass().getSimpleName() + " " + "Failure controlDevice" + e.getMessage());
                    log.warn("Failure controlDevice: {}, {}", virtualDevice.getName(), virtualDevice.getId());
                }
            }
        }
        else
        {
            log.warn("DeviceControlForDB NOT exist: virtualdevice: {}\n, location: {}, contextmodel: {}", virtualDevice, locationUri, contextModelId);
            TrackingHandler.send(getTracking(), getClass().getSimpleName()+" : DeviceControlForDB NOT exist");
        }
    }

    /**
     * control a device.<BR/>
     *
     * @param deviceControlList device control list
     */
    private void controlDevice(LinkedHashMap<IGenericDeviceDriver, List<DeviceControlValue>> deviceControlList) {
        //TODO: thread process 고민 필요
        if (deviceControlList != null) {
            for (Map.Entry<IGenericDeviceDriver, List<DeviceControlValue>> entry : deviceControlList.entrySet()) {
                IGenericDeviceDriver deviceDriver = entry.getKey();
                List<DeviceControlValue> values = entry.getValue();
                //
                //databaseManager.getDeviceControlByDeviceIdAndContextModelID
                deviceDriver.control(values);
            }
        }
    }

    /**
     * control a device.<BR/>
     *
     * @param virtualDevice      control device
     * @param deviceControlValue device control value
     */
    private String controlDevice(IGenericVirtualDevice virtualDevice, String deviceControlValue) throws Exception {
        String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();
        deviceManager.deviceExecute(commandId, virtualDevice.getId(), deviceControlValue);
        return commandId;
    }
}
