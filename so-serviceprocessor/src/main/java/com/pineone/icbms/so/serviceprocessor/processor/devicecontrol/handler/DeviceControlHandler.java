package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.handler;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.DeviceControlValue;
import com.pineone.icbms.so.devicecontrol.model.virtualdevice.driver.IGenericDeviceDriver;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlForDB;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.tracking.handler.TrackingHandler;
import com.pineone.icbms.so.interfaces.sda.handle.SdaClient;
import com.pineone.icbms.so.interfaces.sda.handle.itf.ISdaManager;
import com.pineone.icbms.so.interfaces.si.handle.DeviceManager;
import com.pineone.icbms.so.interfaces.si.ref.ClientProfile;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.pineone.icbms.so.interfaces.si.model.ResultMessage;

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

        getTracking().setProcess(getClass().getSimpleName());
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        // get DeviceDriver from sda by virtual object features
        // retrieve device list
//        SdaClient sdaClient = new SdaClient();
//        List<IGenericVirtualDevice> deviceList = sdaClient.retreiveDeviceList(null, locationUri);

        //create control list
        // TODO 개발버전은 개발용 데이터베이스에서 조회처리하나, 운영은 SDA에 API로 각 디바이스별 제어값을 조회해서 처리해야 함
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

        // set TrackingEntity
        //tracking = getTracking();

        if (deviceControlForDB != null) {
            // TODO simulator
            if ("Y".equals(virtualDevice.getIsLast())) {
                getTracking().setStatusCd("F");
            }

            if (getTracking().getSimulatorType() == null || "".equals(getTracking().getSimulatorType())) {
                // TODO 실제 디바이스 실행
                //control device
                controlDevice(virtualDevice, deviceControlForDB.getValue());
            }
            else {
                // TODO 시뮬레이션
                log.warn("Simulate controlDevice: {}, {}", virtualDevice.getName(), virtualDevice.getId());
                getTracking().setProcessId(virtualDevice.getId());
                getTracking().setProcessName(virtualDevice.getName() + ", 디바이스제어");
                getTracking().setProcessValue(deviceControlForDB.getValue());
                TrackingHandler.send(getTracking());
            }
        }
        else
        {
            log.warn("DeviceControlForDB NOT exist: virtualdevice: {}, location: {}, contextmodel: {}", virtualDevice, locationUri, contextModelId);
            getTracking().setProcessId(String.format("deviceId : %s, contextModelId : %s", virtualDevice.getId(), contextModelId));
            getTracking().setProcessName("디바이스 제어정보 없음");
            TrackingHandler.send(getTracking());
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
    private void controlDevice(IGenericVirtualDevice virtualDevice, String deviceControlValue) {

        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        try {
            String commandId = ClientProfile.SI_COMMAND_ID + System.nanoTime();

            ResultMessage resultMessage = deviceManager.deviceExecute(commandId, virtualDevice.getId(), deviceControlValue);

//            getTracking().setCommandId(commandId);
//            getTracking().setProcessId(resultMessage.getCode());
//            getTracking().setProcessName(resultMessage.getMessage());
//            getTracking().setProcessValue(deviceControlValue);
            // 정상응답이 아닌경우
            if (!"2000".equals(resultMessage.getCode())) {
                getTracking().setProcessId(resultMessage.getCode());
                getTracking().setProcessName("Response ERROR");
                getTracking().setProcessValue(deviceControlValue);
                getTracking().setProcessError(resultMessage.getMessage());
            } else {
                getTracking().setCommandId(commandId);
                getTracking().setProcessId(resultMessage.getCode());
                getTracking().setProcessName(resultMessage.getMessage());
                getTracking().setProcessValue(deviceControlValue);
            }
            TrackingHandler.send(getTracking());
            log.warn("ResultMessage controlDevice: {}", resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            getTracking().setProcessId("");
            getTracking().setProcessName("Exception");
            getTracking().setProcessError(e.getMessage());
            TrackingHandler.send(getTracking());
        }
    }
}
