package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.iot.devicedriver.DefaultDeviceDriver;
import com.pineone.icbms.so.iot.resources.context.DefaultDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pahnj on 2016-01-12.
 */
public class ExecuteDriverAction extends DefaultAction {

    private final Logger log = LoggerFactory.getLogger(ExecuteDriverAction.class);
    /**
     * driver execute action
     * @param context
     */
    @Override
    public void execute(IGenericContext context) {
        log.info("device execute action");
        ArrayList<DefaultDeviceDriver> deviceDrivers = new ArrayList<>();
        ArrayList<DefaultPhysicalDevice> defaultPhysicalDevices = new ArrayList<>();

        DefaultDeviceContext defaultDeviceContext = new DefaultDeviceContext();
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_OPERATION_VALUE,context.getValue(IotServiceContext.ACTION_OPERATION_VALUE));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE,context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE));
        defaultDeviceContext.setOntologyReference((DefaultOntologyReference)context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE));
//        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI_ONE,"/herit-in/herit-cse/Humidifier_LR0001HU0001");

        /**
         * add emergencynoti data
         */
        if(context.getValue(IotServiceContext.ACTION_EMERGENCYNOTI_ZONE) != null)
        {
            setEmergencyNotiData(context, defaultDeviceContext);
        }
        /**
         * add alarminfo data
         */
        else if(context.getValue(IotServiceContext.ACTION_ALARMINFO_ALARMID) != null)
        {
            setAlarmInfoData(context, defaultDeviceContext);
        }


        // TestCode Start DeviceExecute 실행 하기 위한 임의 데이터.START
        /*
        try {
            DefaultDeviceDriver deviceDriver = (DefaultDeviceDriver) Class.forName("com.pineone.icbms.so.iot.devicedriver.SSHumidifierDeviceDriver").newInstance();
            deviceDriver.execute(defaultDeviceContext);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */
        // TestCode Start DeviceExecute 실행 하기 위한 임의 데이터. END

        for(DefaultDeviceDriver driver : (ArrayList<DefaultDeviceDriver>)context.getValue(IotServiceContext.ACTION_DEVICE_DRIVER))
        {
            deviceDrivers.add(driver);
        }

        for(DefaultPhysicalDevice device : (List<DefaultPhysicalDevice>)context.getValue(IotServiceContext.ACTION_DEVICE_URI))
        {
            defaultPhysicalDevices.add(device);
        }

        System.out.println("[[deviceDrivers]] = " + deviceDrivers.toString());
        System.out.println("[[deviceDrivers]] size = " + deviceDrivers.size());

        for(int i=0; i < deviceDrivers.size(); i++)
        {
            log.info("\n\ndevice execute action start " + i);
            log.info("device execute action start " + defaultPhysicalDevices.get(i).getUri() + "\n\n");
            defaultDeviceContext.removeValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI_ONE);
            defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI_ONE,defaultPhysicalDevices.get(i).getUri());
            deviceDrivers.get(i).execute(defaultDeviceContext);
        }

        // TestCode End
        /*
        for(IGenericDeviceDriver driver : (ArrayList<DefaultDeviceDriver>)context.getValue(IotServiceContext.ACTION_DEVICE_DRIVER))
        {
            defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI_ONE,"com.pineone.icbms.so.iot.devicedriver.SSHumidifierDeviceDriver");
            driver.execute(defaultDeviceContext);
        }
        */
    }

    private void setAlarmInfoData(IGenericContext context, DefaultDeviceContext defaultDeviceContext) {
        log.info("set AlarmInfo Data");
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_USERID,context.getValue(IotServiceContext.ACTION_USERID));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_ALARMID,context.getValue(IotServiceContext.ACTION_ALARMINFO_ALARMID));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_WAKETIME,context.getValue(IotServiceContext.ACTION_ALARMINFO_WAKETIME));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_PERIOD,context.getValue(IotServiceContext.ACTION_ALARMINFO_PERIOD));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_TYPE,context.getValue(IotServiceContext.ACTION_ALARMINFO_TYPE));
    }

    private void setEmergencyNotiData(IGenericContext context, DefaultDeviceContext defaultDeviceContext) {
        log.info("set EmergencyNoti Data");
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_ZONE,context.getValue(IotServiceContext.ACTION_EMERGENCYNOTI_ZONE));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_KIND,context.getValue(IotServiceContext.ACTION_EMERGENCYNOTI_KIND));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_USERID,context.getValue(IotServiceContext.ACTION_USERID));
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_CAMURL,context.getValue(IotServiceContext.ACTION_EMERGENCYNOTI_CAMURL));
    }
}
