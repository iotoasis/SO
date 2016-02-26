package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.OptimalValueInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.value.DefaultValue;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * get Optimum Temperature Value
 * Created by Melvin on 2016. 1. 12..
 */
public class MakeValueInfoAction extends DefaultAction{

    private final Logger log = LoggerFactory.getLogger(MakeValueInfoAction.class);

    DefaultLocation location ;

    @Override
    public void execute(IGenericContext context) {

        OptimalValueInfoProvider valueInfoProvider = new OptimalValueInfoProvider();

        location = (DefaultLocation) context.getValue(IotServiceContext.ACTION_TARGET_LOCATION);


        /**
         * Use ValueInfoProvider that has SDA Interface(get Optimal Value) <BR/>
         * add Information of Optimal Temperature Value to ACTION_OPTIMAL_TEMP <BR/>
         */

        DefaultValue value = valueInfoProvider.getOptimalValueFromSDA(location);

        log.info(" >> Maxvalue : " + value.getMaxValue());
        log.info(" >> Minvalue : " + value.getMinValue());

        context.addValue(IotServiceContext.ACTION_OPTIMAL_TEMP, value);

    }
}
