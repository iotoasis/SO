package com.pineone.icbms.so.device.pr;

import com.pineone.icbms.so.device.entity.DeviceResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pineone.icbms.so.device.entity.ReleaseResultMessage;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.store.DeviceResultStore;
import com.pineone.icbms.so.device.store.memory.DeviceResultMemory;
import com.pineone.icbms.so.device.util.ClientProfile;


@RestController
public class ReceivePresentation {

    @RequestMapping(value ="/resource/dcm/",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String asynchronousControlResult(@RequestBody ResultMessage message){

        DeviceResultStore deviceResultStore;
        DeviceResult deviceResult;

        deviceResultStore = new DeviceResultMemory();
        deviceResult = deviceResultStore.retrieve(message.get_commandId());

        if(deviceResult != null && deviceResult.getCommandId() != null){
            // It has been confirmed for the linked data.
            if(ClientProfile.RESPONSE_SUCCESS_CODE.equals(message.get_resultCode()) ||
                    ClientProfile.RESPONSE_SUCCESS.equals(message.get_resultCode()) ||
                    ClientProfile.RESPONSE_SUCCESS_ONEM2MCODE.equals(message.get_resultCode())){

                deviceResult.setResult2(message.get_resultCode());
            }
            return message.get_resultCode();
        } else {
            // No device control message.
            return "fail";
        }
    }

    @RequestMapping(value ="",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String deviceDisableNotification(@RequestBody  ReleaseResultMessage message) {
        // NOTE : Erase the memory on the device.
        // TODO : Planned implementation.
        return message.getDeviceId();
    }
}
