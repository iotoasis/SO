package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.interfaces.sda.collector.provider.LocationInfoProvider;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.vo.location.DefaultLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * get Scheduled Location Information<BR/>
 * Created by Melvin on 2016. 1. 12..
 */
public class MakeScheduledLocationAction extends DefaultAction{

    private final Logger log = LoggerFactory.getLogger(MakeScheduledLocationAction.class);

    @Override
    public void execute(IGenericContext context) {

        LocationInfoProvider locationInfoProvider = new LocationInfoProvider();

        String date = "monday";

        String time= "0850";

        /**
         * Use LocationInfoProvider that has SDA Interface(get Location Info) <BR/>
         * add Information of location to ACTION_TARGET_LOCATION  <BR/>
         */

        List<DefaultLocation> locationList =
                locationInfoProvider.getLocationInfoFromSDA(date, time);

        for(DefaultLocation location : locationList){
            log.info(" >> LocationList : " + location.getUri());
        }

        context.addValue(IotServiceContext.ACTION_TARGET_LOCATION, locationList );

    }
}
