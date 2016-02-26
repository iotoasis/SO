package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.DeviceInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * get Devices about Luminosity in Under Luminosity Location than optimum<BR/>
 * Created by Melvin on 2016. 1. 12..
 */

public class MakeLocationInfoUnderLightValueAction extends DefaultAction{

    private final Logger log = LoggerFactory.getLogger(MakeLocationInfoUnderLightValueAction.class);

    DefaultLocation location ;

    @Override
    public void execute(IGenericContext context) {

        DeviceInfoProvider deviceInfoProvider = new DeviceInfoProvider();

        /**
         * Use DeviceInfoProvider that has SDA Interface(get Under Light Value Device) <BR/>
         * add Information of Devices to ACTION_DEVICE_URI  <BR/>
         */

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);

        List<DefaultPhysicalDevice> deviceList = deviceInfoProvider.getUnderLightValueDevice(location);

        for(DefaultPhysicalDevice device : deviceList){
            log.info(" >> deviceList : " + device.getUri());
        }

        context.addValue(IotServiceContext.ACTION_DEVICE_URI, deviceList);
    }
}
