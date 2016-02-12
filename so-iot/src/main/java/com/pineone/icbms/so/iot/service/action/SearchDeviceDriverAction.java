package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.iot.devicedriver.DefaultDeviceDriverMapper;
import com.pineone.icbms.so.iot.resources.context.DefaultDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pahnj on 2016-01-12.
 */
public class SearchDeviceDriverAction extends DefaultAction{

    private final Logger log = LoggerFactory.getLogger(SearchDeviceDriverAction.class);

    /**
     * search device driver action
     * @param context
     */
    @Override
    public void execute(IGenericContext context) {
        log.info("device driver search");
        System.out.println("device driver search");
        DefaultDeviceDriverMapper defaultDeviceDriverMapper = new DefaultDeviceDriverMapper();

        DefaultDeviceContext defaultDeviceContext = new DefaultDeviceContext();
        defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI,context.getValue(IotServiceContext.ACTION_DEVICE_URI));
        defaultDeviceDriverMapper.getDeviceDrivers(defaultDeviceContext);
        context.addValue(IotServiceContext.ACTION_DEVICE_DRIVER,defaultDeviceContext.getValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_DRIVER));

    }
}
