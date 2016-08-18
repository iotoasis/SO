package com.pineone.icbms.so.device.pr;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.entity.deviceReleaseMessage;
import com.pineone.icbms.so.device.logic.DeviceManager;
import com.pineone.icbms.so.device.logic.DeviceManagerLogic;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value ="/device")
public class DevicePresentation {

    /**
     * Device 제어 요청
     * Device 등록 요청
     * Device 해제 요청
     * Device 제어 결과 요청
     *
     * Device 검색 요청(By ID)
     * Device 검색 요청(By Location)
     * Device Service들 요청.
     */

    private DeviceManager deviceManager = new DeviceManagerLogic();

    public String deviceControl(String deviceId, String deviceService, String deviceCommand){
        // NOTE : Device Control
        return deviceManager.deviceExecute(deviceId, deviceCommand);
    }

    public Device findDeviceById(String deviceId){
        // Search Device By Id
        return deviceManager.deviceSearchById(deviceId);
    }

    public List<Device> findDeviceByLocation(String location){
        // Search Device By Location
        return deviceManager.deviceSearchByLocation(location);
    }

    @RequestMapping(value = "/service/{location}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> deviceServiceList(@PathVariable String location) {
        return deviceManager.requestDeviceServiceList(location);
    }


    @RequestMapping(value = "/enable",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deviceEnableNotification(@RequestBody deviceReleaseMessage message) {
        // NOTE : Register the device memory
        deviceManager.deviceRegister(message);
    }


    @RequestMapping(value ="/disable",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deviceDisableNotification(@RequestBody deviceReleaseMessage message) {
        // NOTE : Unregister the device memory
        deviceManager.deviceRelease(message.getDeviceId());
    }


    @RequestMapping(value ="/moniter",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String asynchronousControlResult(@RequestBody ResultMessage message){
        // NOTO : Device the result is stored in the data memory.
        return deviceManager.deviceControlResult(message);
    }
}
