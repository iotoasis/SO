package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.iot.devicedriver.DefaultDeviceDriverMapper;
import com.pineone.icbms.so.iot.resources.context.DefaultDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Action to search the device<BR/>
 * Created by pahnj on 2016-01-12.
 */
public class SearchDeviceDriverAction extends DefaultAction{

    public static final String SI_COMMAND_ALARM_INFO_DEVICE_URI = "http://www.pineone.com/herit-in/herit-cse/AlarmInfo_2";
    public static final String SI_COMMAND_EMERGENCYNOTI_DEVICE_URI = "http://www.pineone.com/herit-in/herit-cse/EmergencyNoti";

    private final Logger log = LoggerFactory.getLogger(SearchDeviceDriverAction.class);

    /**
     * search device driver action
     * @param context
     */
    @Override
    public void execute(IGenericContext context) {
        log.info("device driver search");
        DefaultDeviceDriverMapper defaultDeviceDriverMapper = new DefaultDeviceDriverMapper();
        List<DefaultPhysicalDevice> defaultPhysicalDevices = new ArrayList<>();
        DefaultPhysicalDevice physicalDevice = new DefaultPhysicalDevice();
        DefaultDeviceContext defaultDeviceContext = new DefaultDeviceContext();

        /**
         * The device is treated as an exception to the alarm information.<BR/>
         */
        if(IGenericOntologyReference.REF_ALARMINFO_CONTROL.equals(((DefaultOntologyReference)context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE)).getReference()))
        {
            log.info(">> Search AlarmInfo get data");
            physicalDevice.setUri(SI_COMMAND_ALARM_INFO_DEVICE_URI);
            defaultPhysicalDevices.add(physicalDevice);
            defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI,defaultPhysicalDevices);
            context.addValue(IotServiceContext.ACTION_DEVICE_URI,defaultPhysicalDevices);
        }
        /**
         * The device is treated as an exception to the emergencynoti.<BR/>
         */
        else if(IGenericOntologyReference.REF_EMERGENCYNOTI_CONTROL.equals(((DefaultOntologyReference)context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE)).getReference()))
        {
            log.info(">> Search EmergencyNoti get data");
            physicalDevice.setUri(SI_COMMAND_EMERGENCYNOTI_DEVICE_URI);
            defaultPhysicalDevices.add(physicalDevice);
            defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI,defaultPhysicalDevices);
            context.addValue(IotServiceContext.ACTION_DEVICE_URI,defaultPhysicalDevices);
        } else
        {
            log.info(">> Search get data");
            defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI,context.getValue(IotServiceContext.ACTION_DEVICE_URI));
        }
        defaultDeviceDriverMapper.getDeviceDrivers(defaultDeviceContext);
        context.addValue(IotServiceContext.ACTION_DEVICE_DRIVER,defaultDeviceContext.getValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_DRIVER));

    }
}
